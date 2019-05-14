# Java程序设计作业：MUD游戏开发
## 程序架构
- Server.java: 服务器程序
```
public void run();
- 接管客户端线程，输出登录提示信息，获取登录账户名，将其加入onlinePlayers中
- 调用dealInput响应客户端上传的操作指令
- 退出登录后进行收尾处理，如从onlinePlayers中移除该账户等

public static Player login(BufferedWriter out, String name);
- 从客户端读入登录账户名name，在数据库中创建该用户，通过缓冲流out输出提示信息，回传该用户实例
```
- Client.java: 客户端程序
```

```
- UserInput.java: 响应操作指令方法
```
public static void dealInput(Player p, String inputMessage);
- 对于账户p输入的指令inputMessage，根据if-else语句调用Player的相关方法，如move, look等
```
- Player.java: 登录账户类
```
static private Hashtable<Integer, Player> onlinePlayers = new Hashtable<Integer, Player>();
- 将全部已登录账户移入内存中，配合look, quit, move方法等
- 由服务器负责增加删除

public static void addOnlinePlayers(Integer key, Player p);
public static void delOnlinePlayers(Integer key);
- 服务器用来更改Hashtable元素的方法

public void move(CommonContent.DIRECTION direction);
- 从数据库选取当前玩家所在的房间，获取周围方向，判断是否可行，若可行则更新this.location，将其写入Hashtable中，再更新到数据库中
- 调用新房间的发送消息的方法

```
- Area.java: 区域类
```
static private Hashtable<Integer, Player> playerList = new Hashtable<Integer, Player>();
- 存储区域内部所有玩家

static private Hashtable<String, Player> roomList = new Hashtable<String, Player>();
- 存储同一区域内的许多房间，任何玩家进入其中一个房间即被视为进入该区域，需要调用进入区域的方法

public void move(Player player, CommonContent.DIRECTION direction);
- 当有玩家在区域内移动时，向区域内部所有玩家发送消息

public void enter(Player player, CommonContent.DIRECTION direction);
- 当有玩家从区域外进入区域内时，向区域内部所有玩家发送消息

public void leave(Player player, CommonContent.DIRECTION direction);
- 当有玩家从区域内移动至区域外时，向区域内部所有玩家发送消息
```
- MessageManagement.java: 
```
static Hashtable<Integer,BufferedWriter> playerChannels = new Hashtable<Integer,BufferedWriter>();
- 保存玩家频道，实现私聊功能

public static void showToPlayer(Player p, String message);
- 服务器调用，向客户端账户输出信息
```
- CommonContent.java: 保存枚举变量——方向
- MySQLData.java: 保存连接数据库的个人信息
- StaticFunctions.java: 保存获取面对方向和相反方向的方法
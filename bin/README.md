# Java程序设计作业：MUD游戏开发
> 21172501 孙博文
## 实现功能
- 基本要求：
   - 全部实现
- 扩展内容：
   - 实现群聊chat功能，实现私聊tell功能，能够查看在线玩家（who)
   - 将创建的游戏世界信息在数据库中存储，设计合理的结构
   - 实现玩家状态信息，可以查看（hp），并能够存储玩家状态（退出后记录当前状态，下次登录后以当前状态连线
---
## 程序架构
- Server.java: 服务器程序
```
public void run();
- 接管客户端线程，输出登录提示信息，获取登录账户名，将其加入onlinePlayers中
- 调用dealInput响应客户端上传的操作指令
- 退出登录后进行收尾处理，如从onlinePlayers中移除该账户等

public static Player login(BufferedWriter out, String name);
- 从客户端读入登录账户名name，在数据库中创建该用户，通过缓冲流out输出提示信息，回传该用户指针
```
- Client.java: 客户端程序
```
class MonitorThread extends Thread;
- 客户端建立一个新线程，不断接收服务器消息并显示于屏幕上

class JTextField;
- 输入文本框，按Enter后将文本框的信息发送给服务器

class JTextArea;
- 输出文本框，输出服务器发送的消息

private void appendText(String text);
- 将文本text显示于屏幕上，焦点设置于JTextArea，使其滚到最底部，使用户方便查看最新消息，再将焦点设置于JTextField，使用户方便输入下一条指令
```
- UserInput.java: 响应操作指令方法
```
public static void dealInput(Player p, String inputMessage);
- 对于账户p输入的指令inputMessage，根据if-else语句调用Player的相关方法
```
- Player.java: 登录账户类
```
static private Hashtable<Integer, Player> onlinePlayers = new Hashtable<Integer, Player>();
- 将全部已登录账户移入内存中
- 由服务器负责增加删除

public static void addOnlinePlayers(Integer key, Player p);
public static void delOnlinePlayers(Integer key);
- 服务器用来更改Hashtable元素的方法

public void move(CommonContent.DIRECTION direction);
- 从数据库选取当前玩家所在的房间，获取周围方向，判断是否可行，若可行则更新this.location，将其写入Hashtable中，再更新到数据库中
- 调用相关发送消息的方法

public void hp();
- 查看当前hp

public void look();
- 向玩家展示当前房间的描述，出口信息等

private String getLocationLook(String[] directions);
- 根据directions环视房间，获取各个出口房间的名称，默认的directions为{"north", "northeast", "east", "southeast", "south", "southwest", "west", "northwest", "up", "down"};

public void chat(String message);
- 群聊，对附近Area的玩家发送消息，也用于服务器发送玩家上下线消息

public void tell(String name, String message);
- 私聊，对任意远的名字为name的玩家发送消息

public void who();
- 查询其他在线玩家，显示其姓名
```
- MessageManagement.java: 发送消息方法
```
private static Hashtable<Integer,BufferedWriter> playerChannels = new Hashtable<Integer,BufferedWriter>();
- 保存玩家频道

public static void showToPlayer(Player p, String message);
- 向某个指定的客户端输出信息

public static void chat(Player speaker, String message);
- 向与发言者在同一区域内的所有玩家发送消息

public static void tellAllPlayers(Player speaker, String message);
- 给所有在线玩家发speaker的消息

public static void broadcast(String message);
- 给所有在线玩家发消息，适用于系统通知
```
- CommonContent.java: 保存枚举变量——方向
- MySQLData.java: 保存连接数据库的个人信息
- StaticFunctions.java: 保存获取方向的方法

## 数据库构造语句
- 创建数据库
```

```
- 创建表单
```

```
- 创建世界
```

```
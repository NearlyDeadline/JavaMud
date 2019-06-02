# Java程序设计作业：MUD游戏开发
> 已隐藏的个人信息
## 实现功能
- 基本要求：
   - 全部实现
- 扩展内容：
   - 实现群聊chat功能，实现私聊tell功能，能够查看在线玩家（who)
   - 将创建的游戏世界信息在数据库中存储，设计合理的结构
   - 实现玩家状态信息，可以查看（hp），并能够存储玩家状态（退出后记录当前状态，下次登录后以当前状态连线
- 其他内容：
   - 将群聊chat功能的范围限定于一些房间内，抽象出新的Area区域：只有在同一区域内的群聊才可被互相看到
---
## 程序架构
- 类图：
![avatar](https://github.com/NearlyDeadline/JavaMud/blob/master/forstudent/ClassDiagram.jpg)

- Server.java: 服务器程序
```
public void run();
- 接管客户端线程，输出登录提示信息，获取登录账户名，调用login创建用户，并将其加入onlinePlayers中，为其创建输出频道BufferedWriter
- 不断调用dealInput响应客户端上传的操作指令，直至客户端退出
- 退出登录后进行收尾处理，从onlinePlayers中移除该账户，移除对应的BufferedWriter等

private static Player login(BufferedWriter out, String name);
- 由run调用的方法，从客户端读入登录账户名name，在数据库中创建该用户，通过缓冲流out向客户端输出提示信息，回传该用户指针

static public void main(String[] args);
- 主函数，启动服务器，调用World.CreateWorld方法构建世界，等待客户端连接
```
- Client.java: 客户端程序
```
class JButton;
- 连接服务器按钮，点击后会与服务器连接，完成BufferedReader与BufferedWriter的初始化，创建一个新的MonitorThread并使其运行

class MonitorThread extends Thread;
- 客户端建立的一个新线程，不断接收服务器消息并调用appendText()方法，将信息显示于客户端窗口JTextArea上

class JTextField;
- 输入文本框，按Enter后将文本框的信息发送给服务器，并清空文本框，准备下一次输入

class JTextArea;
- 输出文本框，显示服务器发送的消息

private void appendText(String text);
- 将文本text显示于屏幕上，焦点设置于JTextArea，使其滚到最底部，使用户方便查看最新消息，再将焦点设置于JTextField，使用户方便输入下一条指令
```
- UserInput.java: 响应操作指令
```
public static void dealInput(Player p, String inputMessage);
- 对于账户p输入的指令inputMessage，根据if-else语句调用Player的相关方法
```
- World.java: 创建所有房间
```
private static Hashtable<String, Room> Rooms;
- 保存所有Room的enter,leave方法

public static void CreateWorld();
- 创建各个Room
```
- Player.java: 登录账户
```
static private Hashtable<Integer, Player> onlinePlayers = new Hashtable<Integer, Player>();
- 将全部已登录账户移入内存中，由服务器负责增加删除

public static void addOnlinePlayers(Integer key, Player p);
public static void delOnlinePlayers(Integer key);
- 服务器用来更改Hashtable元素的方法

public void move(CommonContent.DIRECTION direction);
- 从数据库选取当前玩家所在的房间，获取周围方向，判断是否可行，若可行则更新this.location，将其写入Hashtable中，再更新到数据库中
- 调用相关离开与进入房间的方法

public void hp();
- 查看当前hp

public void look();
- 调用getLocationLook()方法，向玩家展示当前房间的描述，以及各个出口的信息

public void chat(String message);
- 群聊，对附近Area的玩家发送消息，也用于服务器发送玩家上下线消息

public void tell(String name, String message);
- 私聊，对任意远的名字为name的玩家发送消息

public void who();
- 查询其他在线玩家，显示其姓名
```
- Room.java: 房间接口，给出所有房间应有的一些操作
```
public String getRoomId();
public void setRoomId(String roomId);
- 内存中只保留RoomId，其余信息移入数据库保存	

public void enter(Player p);
- 玩家p进入房间时调用该方法
	
public void leave(Player p);
- 玩家p离开房间时调用该方法

String[] directions = {"north", "northeast", "east", "southeast", "south", "southwest", "west", "northwest", "up", "down"};
- 常量，用来设置getLocationLook()方法中的方向顺序	

public default String getLocationLook();	
public default String getLocationLook(String[] directions)
- 得到房间信息的方法给出了默认实现	
```
- CommonRoom.java: 通常房间类，特点是进入、离开时向同area内所有玩家发送消息
```
- 调用MessageManagement.chat方法发送相关消息
```
- MessageManagement.java: 发送消息
```
private static Hashtable<Integer,BufferedWriter> playerChannels = new Hashtable<Integer,BufferedWriter>();
- 保存所有在线玩家消息频道

public static void showToPlayer(Player p, String message);
- 向某个指定的玩家p输出信息message

public static void chat(Player speaker, String message);
- 向与发言者speaker在同一区域area内的所有玩家发送消息message

public static void tellAllPlayers(Player speaker, String message);
- 给所有在线玩家发speaker的消息message

public static void broadcast(String message);
- 给所有在线玩家发消息message，适用于系统通知
```
- CommonContent.java: 保存枚举变量——方向
- MySQLData.java: 保存连接数据库的个人信息，包括驱动程序，数据库名称，用户名，密码等
- StaticFunctions.java: 保存获取中文方向的方法，和通过RoomId查询房间Name的方法
---
## 数据库构造
- 直接导入数据库文件——见附件

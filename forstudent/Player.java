package forstudent;
import java.util.Hashtable;

public class Player {

	private int hp;
	private Integer id;
	private String name;
	private String location;
	
	static private Hashtable<Integer, Player> onlinePlayers = new Hashtable<Integer, Player>();//所有在线玩家保存到内存中

	public static void addOnlinePlayers(Integer key, Player p) {
		onlinePlayers.put(key, p);
	}
	public static void delOnlinePlayers(Integer key) {
		onlinePlayers.remove(key);
	}
	public void move(CommonContent.DIRECTION direction) {//从数据库选取所在房间，获取周围方向，判断是否可行。若可行则更新this.location并将其写入Hashtable和数据库中，否则回复不可行
		
	}
	public void look(String something){
		if(something.equals(""))
			;//查看当前房间;
		else
			;//查看其它物品
	}
	
	public void leave(){
		//告诉房间退出了，释放资源
		
		
		//save添加在这里
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}

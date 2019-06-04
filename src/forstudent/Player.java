package forstudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

public class Player {

	private int hp;
	private Integer id;
	private String name;
	private Room location;
	
	Player(String Location){
		location = World.getRoom(Location);
		location.setRoomId(Location);
	}
	static private Hashtable<Integer, Player> onlinePlayers = new Hashtable<Integer, Player>();//所有在线玩家保存到内存中
	public static void addOnlinePlayers(Integer key, Player p) {
		getOnlinePlayers().put(key, p);
	}
	public static void delOnlinePlayers(Integer key) {
		getOnlinePlayers().remove(key);
	}
	public void move(CommonContent.DIRECTION direction) {
		String dir = direction.toString().toLowerCase();
		try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "SELECT " + dir + " FROM mud.rooms WHERE id=?;";//搜索玩家当前所在房间的对应方向是否可行，返回null或者对应方向的房间id
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.location.getRoomId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String target = rs.getString(1);
				if (target == null) {//对应方向不可通行
					MessageManagement.showToPlayer(this, CommonContent.CANNOTMOVE);
				}
				else {
					sql = "SELECT name FROM mud.rooms WHERE id=?;";//根据房间id搜索该房间的名称和描述
					ps = con.prepareStatement(sql);
					ps.setString(1, target);
					rs = ps.executeQuery();
					while (rs.next()) {
						location.leave(this);
						location.setRoomId(target);;//更改内存中的玩家所在地
						sql = "UPDATE mud.users SET location=? WHERE id=?;";
						ps = con.prepareStatement(sql);
						ps.setString(1, this.location.getRoomId());
						ps.setInt(2, this.id);
						ps.executeUpdate();	//更改数据库中的玩家所在地
						location.enter(this);
					}
				}
			}
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void look(){
		String description = null;
		try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "SELECT description FROM mud.rooms WHERE id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.location.getRoomId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				description = rs.getString("description") + "\n";//输出当前房间的描述信息
			}
			description += location.getLocationLook();
			MessageManagement.showToPlayer(this, description);
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void hp() {
		MessageManagement.showToPlayer(this, "当前生命值为：" + hp);
	}
	public void chat(String message) {
		MessageManagement.chat(this, name + "说：" + message);
	}
	public void tell(String name, String message) {//不在线或者不存在
		Player target = null;
		try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "SELECT id FROM mud.users WHERE name=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {//如果在数据库里的话，就判断是否在线
				boolean isOnline = false;//判断是否是在线玩家
				Enumeration<Integer> e = Player.getOnlinePlayers().keys();//遍历所有在线玩家
				while(e.hasMoreElements()){
					target = Player.getOnlinePlayers().get(e.nextElement());
					if (target.getName().equals(name)){//在线
						MessageManagement.showToPlayer(target, name + "对你说：" + message);
						MessageManagement.showToPlayer(this, "你对" + target.getName() + "说：" + message);
						isOnline = true;
						break;
					}
				}
				if (!isOnline)
				    MessageManagement.showToPlayer(this, "目标玩家不在线");
			}
			else {//不在数据库里
				MessageManagement.showToPlayer(this, "目标玩家不存在");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void who() {
		String message = "查询其他在线玩家\n";
		Player target = null;
		Enumeration<Integer> e = Player.getOnlinePlayers().keys();//遍历所有在线玩家
		while(e.hasMoreElements()){
			target = Player.getOnlinePlayers().get(e.nextElement());
			if (!(target.getName().equals(name))){//不算自己
				message += target.getName() + "   ";
			}
		}
		MessageManagement.showToPlayer(this, message);
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
		return location.getRoomId();
	}
	
	public void setLocation(String location) {
		this.location.setRoomId(location);
	}
	public void setRoom(CommonRoom r) {
		this.location = r;
	}
    public static Hashtable<Integer, Player> getOnlinePlayers() {
		return onlinePlayers;
	}
}

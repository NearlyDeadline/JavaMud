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
	private String location;
	
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
			String sql = "SELECT (?) FROM mud.rooms WHERE id=?;";//搜索玩家当前所在房间的对应方向是否可行，返回null或者对应方向的房间id
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dir);
			ps.setString(2, this.location);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {//如果可行
				sql = "SELECT (name, description) FROM mud.rooms WHERE id=?;";//根据房间id搜索该房间的名称和描述
				ps = con.prepareStatement(sql);
				String target = rs.getString(1);
				ps.setString(1, target);
				rs = ps.executeQuery();
				while (rs.next()) {
					MessageManagement.chat(this, name + "离开了" + location);
					this.location = target;
					sql = "UPDATE mud.users SET location=? WHERE id=?;";
					ps = con.prepareStatement(sql);
					ps.setString(1, this.location);
					ps.setString(2, this.id.toString());
					ps.executeUpdate();
					//更新数据库
					MessageManagement.chat(this, name + "进入了" + location);
				}
			}
			else {//对应方向没有房间
				dir = CommonContent.CANNOTMOVE;
				MessageManagement.showToPlayer(this, dir);
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
			String sql = "SELECT (description) FROM mud.rooms WHERE id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.location);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				description = rs.getString("description");
			}
			
			
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
		MessageManagement.chat(this, message);
	}
	public void tell(String name, String message) {//有可能不在线
		Player target = null;
		Enumeration<Integer> e = Player.getOnlinePlayers().keys();//遍历所有在线玩家
		while(e.hasMoreElements()){
			target = Player.getOnlinePlayers().get(e.nextElement());
			if (target.getName().equals(name)){
				MessageManagement.showToPlayer(target, message);
				return;
			}
		}
		MessageManagement.showToPlayer(this, "未找到该玩家，可能不在线或无此账户");
	}
	public void who() {
		String message = "查询在线玩家\n";
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
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
    public static Hashtable<Integer, Player> getOnlinePlayers() {
		return onlinePlayers;
	}
}

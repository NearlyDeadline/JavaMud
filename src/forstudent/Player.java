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
	static private Hashtable<Integer, Player> onlinePlayers = new Hashtable<Integer, Player>();//����������ұ��浽�ڴ���
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
			String sql = "SELECT " + dir + " FROM mud.rooms WHERE id=?;";//������ҵ�ǰ���ڷ���Ķ�Ӧ�����Ƿ���У�����null���߶�Ӧ����ķ���id
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.location.getRoomId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String target = rs.getString(1);
				if (target == null) {//��Ӧ���򲻿�ͨ��
					MessageManagement.showToPlayer(this, CommonContent.CANNOTMOVE);
				}
				else {
					sql = "SELECT name FROM mud.rooms WHERE id=?;";//���ݷ���id�����÷�������ƺ�����
					ps = con.prepareStatement(sql);
					ps.setString(1, target);
					rs = ps.executeQuery();
					while (rs.next()) {
						location.leave(this);
						location.setRoomId(target);;//�����ڴ��е�������ڵ�
						sql = "UPDATE mud.users SET location=? WHERE id=?;";
						ps = con.prepareStatement(sql);
						ps.setString(1, this.location.getRoomId());
						ps.setInt(2, this.id);
						ps.executeUpdate();	//�������ݿ��е�������ڵ�
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
				description = rs.getString("description") + "\n";//�����ǰ�����������Ϣ
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
		MessageManagement.showToPlayer(this, "��ǰ����ֵΪ��" + hp);
	}
	public void chat(String message) {
		MessageManagement.chat(this, name + "˵��" + message);
	}
	public void tell(String name, String message) {//�����߻��߲�����
		Player target = null;
		try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "SELECT id FROM mud.users WHERE name=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {//��������ݿ���Ļ������ж��Ƿ�����
				boolean isOnline = false;//�ж��Ƿ����������
				Enumeration<Integer> e = Player.getOnlinePlayers().keys();//���������������
				while(e.hasMoreElements()){
					target = Player.getOnlinePlayers().get(e.nextElement());
					if (target.getName().equals(name)){//����
						MessageManagement.showToPlayer(target, name + "����˵��" + message);
						MessageManagement.showToPlayer(this, "���" + target.getName() + "˵��" + message);
						isOnline = true;
						break;
					}
				}
				if (!isOnline)
				    MessageManagement.showToPlayer(this, "Ŀ����Ҳ�����");
			}
			else {//�������ݿ���
				MessageManagement.showToPlayer(this, "Ŀ����Ҳ�����");
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
		String message = "��ѯ�����������\n";
		Player target = null;
		Enumeration<Integer> e = Player.getOnlinePlayers().keys();//���������������
		while(e.hasMoreElements()){
			target = Player.getOnlinePlayers().get(e.nextElement());
			if (!(target.getName().equals(name))){//�����Լ�
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

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
			ps.setString(1, this.location);
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
						MessageManagement.chat(this, name + "�뿪��" + location);
						this.location = target;//�����ڴ��е�������ڵ�
						sql = "UPDATE mud.users SET location=? WHERE id=?;";
						ps = con.prepareStatement(sql);
						ps.setString(1, this.location);
						ps.setInt(2, this.id);
						ps.executeUpdate();	//�������ݿ��е�������ڵ�
						MessageManagement.chat(this, name + "������" + location);
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
			ps.setString(1, this.location);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				description = rs.getString("description") + "\n";//�����ǰ�����������Ϣ
			}
			description += getLocationLook();
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
	private String getLocationLook(){
		String[] directions = {"north", "northeast", "east", "southeast", "south", "southwest", "west", "northwest", "up", "down"};
		return getLocationLook(directions);
	}
	private String getLocationLook(String[] directions){
		//directions��Ϊ{"north", "northeast", "east", "southeast", "south", "southwest", "west", "northwest", "up", "down"}��Ԫ�ص��������У����ܶ�Ҳ������
		String result = "";
		try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "";
			PreparedStatement ps = null;
			ResultSet rs = null;
			for (int i = 0; i<directions.length; ++i) {
				sql = "SELECT " + directions[i] + " FROM mud.rooms WHERE id =?;";
				ps = con.prepareStatement(sql);
				ps.setString(1, this.location);
				rs = ps.executeQuery();
				if (rs.next()) {
					if (rs.getString(1) != null)
						result += StaticFunctions.getDirection(directions[i]) + "����" + StaticFunctions.getRoomName(rs.getString(1)) + "��";
					else//�������ͨ
						result += StaticFunctions.getDirection(directions[i]) + "����ͨ��";
				}
			}
			ps.close();
			rs.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int lastComma = result.lastIndexOf('��');//�����һ�����ű�ɾ��
		if (lastComma != -1)
			result = result.substring(0, result.length()-1) + "��";
		return result;
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
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
    public static Hashtable<Integer, Player> getOnlinePlayers() {
		return onlinePlayers;
	}
}

package src.forstudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public final class World {
	private static Hashtable<String, Room> Rooms = new Hashtable<String, Room>();//��������Room��enter,leave����
	public static Room getRoom(String RoomId) {
		return Rooms.get(RoomId);
	}
	public static void CreateWorld() {
		try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "SELECT (id) FROM mud.rooms;";//MySQL���
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();//ִ�в�ѯ���������ؽ����
			CommonRoom each = new CommonRoom();//��ʱȫ����ΪCommonRoom
			while (rs.next()) {//���������
				each.setRoomId(rs.getString("id"));
				Rooms.put(rs.getString("id"), each);
				each = new CommonRoom();
			}
			rs.close();
			ps.close();
			con.close();//�رո������ݿ����
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

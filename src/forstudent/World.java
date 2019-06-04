package src.forstudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public final class World {
	private static Hashtable<String, Room> Rooms = new Hashtable<String, Room>();//保存所有Room的enter,leave方法
	public static Room getRoom(String RoomId) {
		return Rooms.get(RoomId);
	}
	public static void CreateWorld() {
		try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "SELECT (id) FROM mud.rooms;";//MySQL语句
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();//执行查询操作，返回结果集
			CommonRoom each = new CommonRoom();//暂时全创建为CommonRoom
			while (rs.next()) {//遍历结果集
				each.setRoomId(rs.getString("id"));
				Rooms.put(rs.getString("id"), each);
				each = new CommonRoom();
			}
			rs.close();
			ps.close();
			con.close();//关闭各个数据库变量
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

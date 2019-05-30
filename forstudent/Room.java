package forstudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Room {
	public String getRoomId();
	
	public void setRoomId(String roomId);
	
	public void enter(Player p);
	
	public void leave(Player p);
	
	String[] directions = {"north", "northeast", "east", "southeast", "south", "southwest", "west", "northwest", "up", "down"};
	
	public default String getLocationLook(){
		String[] directions = {"north", "northeast", "east", "southeast", "south", "southwest", "west", "northwest", "up", "down"};
		return getLocationLook(directions);
	}
	
	public default String getLocationLook(String[] directions){
		//directions需为{"north", "northeast", "east", "southeast", "south", "southwest", "west", "northwest", "up", "down"}中元素的任意排列，不能多也不能少
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
				ps.setString(1, getRoomId());
				rs = ps.executeQuery();
				if (rs.next()) {
					if (rs.getString(1) != null)
						result += StaticFunctions.getDirection(directions[i]) + "方是" + StaticFunctions.getRoomName(rs.getString(1)) + "，";
					else//这个方向不通
						result += StaticFunctions.getDirection(directions[i]) + "方不通，";
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
		int lastComma = result.lastIndexOf('，');//把最后一个逗号变成句号
		if (lastComma != -1)
			result = result.substring(0, result.length()-1) + "。";
		return result;
	}
	
}

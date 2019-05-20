package forstudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaticFunctions {
	public static String getDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "东";break;
		case WEST:
			chinese = "西";break;
		case SOUTH:
			chinese = "南";break;
		case NORTH:
			chinese = "北";break;
		case NORTHEAST:
			chinese = "东北";break;
		case NORTHWEST:
			chinese = "西北";break;
		case SOUTHEAST:
			chinese = "东南";break;
		case SOUTHWEST:
			chinese = "西南";break;
		case UP:
			chinese = "上";break;
		case DOWN:
			chinese = "下";break;
		}
		return chinese;
	}
	public static String getDirection(String direction){
		String chinese = "";
		switch(direction.toLowerCase()){
		case "east":
			chinese = "东";break;
		case "west":
			chinese = "西";break;
		case "south":
			chinese = "南";break;
		case "north":
			chinese = "北";break;
		case "northeast":
			chinese = "东北";break;
		case "northwest":
			chinese = "西北";break;
		case "southeast":
			chinese = "东南";break;
		case "southwest":
			chinese = "西南";break;
		case "up":
			chinese = "上";break;
		case "down":
			chinese = "下";break;
		default: 
			chinese = "这个方向";break;
		}
		return chinese;
	}
	public static String getReverseDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "西";break;
		case WEST:
			chinese = "东";break;
		case SOUTH:
			chinese = "北";break;
		case NORTH:
			chinese ="南";break;
		case NORTHEAST:
			chinese = "西南";break;
		case NORTHWEST:
			chinese = "东南";break;
		case SOUTHEAST:
			chinese = "西北";break;
		case SOUTHWEST:
			chinese = "东北";break;
		case UP:
			chinese = "下";break;
		case DOWN:
			chinese = "上";break;
		}
		return chinese;
	}
    public static String getRoomName(String RoomId) {
    	String result = "";
    	try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "SELECT name FROM mud.rooms WHERE id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, RoomId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString("name");
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
    	return result;
    }
}

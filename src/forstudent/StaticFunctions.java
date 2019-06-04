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
			chinese = "��";break;
		case WEST:
			chinese = "��";break;
		case SOUTH:
			chinese = "��";break;
		case NORTH:
			chinese = "��";break;
		case NORTHEAST:
			chinese = "����";break;
		case NORTHWEST:
			chinese = "����";break;
		case SOUTHEAST:
			chinese = "����";break;
		case SOUTHWEST:
			chinese = "����";break;
		case UP:
			chinese = "��";break;
		case DOWN:
			chinese = "��";break;
		}
		return chinese;
	}
	public static String getDirection(String direction){
		String chinese = "";
		switch(direction.toLowerCase()){
		case "east":
			chinese = "��";break;
		case "west":
			chinese = "��";break;
		case "south":
			chinese = "��";break;
		case "north":
			chinese = "��";break;
		case "northeast":
			chinese = "����";break;
		case "northwest":
			chinese = "����";break;
		case "southeast":
			chinese = "����";break;
		case "southwest":
			chinese = "����";break;
		case "up":
			chinese = "��";break;
		case "down":
			chinese = "��";break;
		default: 
			chinese = "�������";break;
		}
		return chinese;
	}
	public static String getReverseDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "��";break;
		case WEST:
			chinese = "��";break;
		case SOUTH:
			chinese = "��";break;
		case NORTH:
			chinese ="��";break;
		case NORTHEAST:
			chinese = "����";break;
		case NORTHWEST:
			chinese = "����";break;
		case SOUTHEAST:
			chinese = "����";break;
		case SOUTHWEST:
			chinese = "����";break;
		case UP:
			chinese = "��";break;
		case DOWN:
			chinese = "��";break;
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

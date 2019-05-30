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
				ps.setString(1, getRoomId());
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
	
}

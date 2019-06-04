package src.forstudent;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

public class MessageManagement {
	private static Hashtable<Integer,BufferedWriter> playerChannels = new Hashtable<Integer,BufferedWriter>();
	public static void showToPlayer(Player p, String message){//给某玩家发消息
		try {
			playerChannels.get(p.getId()).write(message + "\n");
			playerChannels.get(p.getId()).flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void chat(Player speaker, String message) {//玩家群聊；也可用来发系统消息，适用于给同一area内的玩家发消息，将speaker置为该area内任一玩家即可
		try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "SELECT area FROM mud.rooms WHERE id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, speaker.getLocation());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {//选择在线玩家里所有在此区域玩家，发消息
				String speakerArea = rs.getString("area");
				Player eachPlayer = null;
				String eachArea = null;
				Enumeration<Integer> e = Player.getOnlinePlayers().keys();//遍历所有在线玩家
				while(e.hasMoreElements()){
					eachPlayer = Player.getOnlinePlayers().get(e.nextElement());
					sql = "SELECT (area) FROM mud.rooms WHERE id=?;";//获取每一位在线玩家的location，进而获取其area
					ps = con.prepareStatement(sql);
					ps.setString(1, eachPlayer.getLocation());
					rs = ps.executeQuery();
					if (rs.next()) {
						eachArea = rs.getString("area");
					}
					if (speakerArea.equals(eachArea)){
						MessageManagement.showToPlayer(eachPlayer, message);
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
	public static void tellAllPlayers(Player speaker, String message) {//备用函数，给所有在线玩家发speaker的消息
		Enumeration<Integer> e = Player.getOnlinePlayers().keys();//遍历所有在线玩家
		Player eachPlayer = null;
		while(e.hasMoreElements()){
			eachPlayer = Player.getOnlinePlayers().get(e.nextElement());
			showToPlayer(eachPlayer, speaker.getName() + "对你说：" + message);
		}
	}
	public static void broadcast(String message) {//备用函数，给所有在线玩家发消息，适用于系统通知
		Enumeration<Integer> e = Player.getOnlinePlayers().keys();//遍历所有在线玩家
		Player eachPlayer = null;
		while(e.hasMoreElements()){
			eachPlayer = Player.getOnlinePlayers().get(e.nextElement());
			showToPlayer(eachPlayer, message);
		}
	}
	public static void addPlayerChannels(Integer playerId,BufferedWriter bw){
		playerChannels.put(playerId, bw);
	}
	public static void removePlayerChannels(Integer playerId){
		playerChannels.remove(playerId);
	}
}

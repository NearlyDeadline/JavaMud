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
	public static void showToPlayer(Player p, String message){//��ĳ��ҷ���Ϣ
		try {
			playerChannels.get(p.getId()).write(message + "\n");
			playerChannels.get(p.getId()).flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void chat(Player speaker, String message) {//���Ⱥ�ģ�Ҳ��������ϵͳ��Ϣ�������ڸ�ͬһarea�ڵ���ҷ���Ϣ����speaker��Ϊ��area����һ��Ҽ���
		try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "SELECT area FROM mud.rooms WHERE id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, speaker.getLocation());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {//ѡ����������������ڴ�������ң�����Ϣ
				String speakerArea = rs.getString("area");
				Player eachPlayer = null;
				String eachArea = null;
				Enumeration<Integer> e = Player.getOnlinePlayers().keys();//���������������
				while(e.hasMoreElements()){
					eachPlayer = Player.getOnlinePlayers().get(e.nextElement());
					sql = "SELECT (area) FROM mud.rooms WHERE id=?;";//��ȡÿһλ������ҵ�location��������ȡ��area
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
	public static void tellAllPlayers(Player speaker, String message) {//���ú�����������������ҷ�speaker����Ϣ
		Enumeration<Integer> e = Player.getOnlinePlayers().keys();//���������������
		Player eachPlayer = null;
		while(e.hasMoreElements()){
			eachPlayer = Player.getOnlinePlayers().get(e.nextElement());
			showToPlayer(eachPlayer, speaker.getName() + "����˵��" + message);
		}
	}
	public static void broadcast(String message) {//���ú�����������������ҷ���Ϣ��������ϵͳ֪ͨ
		Enumeration<Integer> e = Player.getOnlinePlayers().keys();//���������������
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

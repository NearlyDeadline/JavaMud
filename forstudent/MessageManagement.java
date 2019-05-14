package forstudent;
import java.io.*;
import java.util.*;
public class MessageManagement {
	static Hashtable<Integer,BufferedWriter> playerChannels = new Hashtable<Integer,BufferedWriter>();
	public static void showToPlayer(Player p, String message){
		BufferedWriter out = (BufferedWriter)playerChannels.get(new Integer(p.getId()));
		try {
			out.write(message);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void addPlayerChannels(Integer playerId,BufferedWriter bw){
		playerChannels.put(new Integer(playerId), bw);
	}
	public static void removePlayerChannels(Integer playerId){
		playerChannels.remove(new Integer(playerId));
	}
}

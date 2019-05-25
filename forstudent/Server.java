package forstudent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class Server {

	static class ServerThread extends Thread {
		
		private Socket socket;
		
		public ServerThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader (socket.getInputStream()));
				BufferedWriter out= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				String input = "";
				out.write("成功连接至服务器\n");
				do {
					out.write("输入用户名\n");
					out.flush();
					input = in.readLine();//读取用户名
				} while (input.isEmpty());
				Player p = null;
				String room = "初始出生地";
				p = login(out, input);//注册用户
				MessageManagement.addPlayerChannels(p.getId(), out);
				System.out.println(p.getName()+"上线进入游戏");
				room = StaticFunctions.getRoomName(p.getLocation());
				MessageManagement.chat(p, p.getName() + "进入了" + room);
				boolean quit = false;
				while (!quit) {
					input = in.readLine();
					if (input.equals("quit")) {
						quit = true;
						System.out.println(p.getName()+"下线退出游戏");
						MessageManagement.chat(p, p.getName() + "退出了游戏");
					}
					UserInput.dealInput(p, input);
				}
				in.close();
				out.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static Player login(BufferedWriter out, String name) {
			Player player = null;
			try {
				Class.forName(MySQLData.driver);
				Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
				String sql = "INSERT IGNORE INTO mud.users (name) values (?);";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				if (ps.executeUpdate() == 0) {
					out.write(name + "是注册过的用户，按上次状态登录\n");//若数据库已有，则直接在内存中创建
				}
				else {
					out.write(name + "是新用户，将为其创建新账户\n");
				}
				out.flush();
				sql = "SELECT * FROM mud.users WHERE name=?;";
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					int id = rs.getInt("id");
					player = new Player(rs.getString("location"));
					player.setId(id);
					player.setName(rs.getString("name"));
					player.setHp(rs.getInt("hp"));
					Player.addOnlinePlayers(id, player);
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
			return player;
		}
	}

	public static int PORT_NUM = 1888;
	
	static public void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT_NUM);
		CreateRooms();
		System.out.println("服务器开始运行");
		while (true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}
	}
	private static void CreateRooms() {
		try {
			Class.forName(MySQLData.driver);
			Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
			String sql = "SELECT (id) FROM mud.rooms;";//MySQL语句
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();//执行查询操作，返回结果集
			Room each = new Room();
			while (rs.next()) {//遍历结果集
				each.setRoomId(rs.getString("id"));
				Rooms.put(rs.getString("id"), each);
				each = new Room();
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
	private static Hashtable<String, Room> Rooms = new Hashtable<String, Room>();//保存所有Room的enter,leave方法
	public static Room getRoom(String RoomId) {
		return Rooms.get(RoomId);
	}
}

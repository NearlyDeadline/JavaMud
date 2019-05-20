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
				player = new Player();
				while (rs.next()) {
					int id = rs.getInt(1);
					player.setId(id);
					player.setName(rs.getString(2));
					player.setHp(rs.getInt(3));
					player.setLocation(rs.getString(4));
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
		System.out.println("服务器开始运行");
		while (true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}
	}
}

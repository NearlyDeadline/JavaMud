package forstudent;
import java.io.*;
import java.net.*;
import java.sql.*;

public class Server {

	static class ServerThread extends Thread {
		
		Socket socket;
		
		public ServerThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader (socket.getInputStream()));
				BufferedWriter out= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				String input;
				out.write("�ɹ�������������");
				do {
					out.write("�����û���������45�ֽڵĲ��ֻᱻ�ض�");
					out.flush();
					input = in.readLine();
				} while (input.isEmpty());
				Player p = null;
				p = insert(out, input);//���Ψһ�ı�ʶ�������ں�������
				MessageManagement.addPlayerChannels(p.getId(), out);
				RoomManagement.cityMap.get(p.getLocation()).addPlayer(p);
				System.out.println(p.getName()+"���߽�����Ϸ");
				
				boolean quit = false;
				while (!quit) {
					input = in.readLine();
					
					if (input.equals("quit")) {
						quit = true;
						System.out.println(p.getName()+"�����˳���Ϸ");
					}
					UserInput.dealInput(p, input);
				}
				MessageManagement.showToPlayer(p, "�ɹ������˳���Ϸ");
				MessageManagement.removePlayerChannels(p.getId());
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static Player insert(BufferedWriter out, String name) {
			Player player = null;
			try {
				Class.forName(MySQLData.driver);
				Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
				if (!con.isClosed())
					System.out.println("�ɹ����������ݿ�");
				String sql = "INSERT INTO sys.users (name, location) values (?,'init');";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				//INSERT INTO sys.users (name, location) values ('$name', 'init');����$nameΪ������û�������
				if (ps.executeUpdate() == 0) {
					out.write("��¼�����û������ϴ�״̬��¼");//�����ݿ����У���ֱ�����ڴ��д���
				}
				else {
					out.write("δ��¼�û����Ѵ������˻�");
				}
				sql = "SELECT * FROM sys.users WHERE name=?;";
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				player = new Player();
				int id = rs.getInt(1);
				player.setId(id);
				player.setName(rs.getString(2));
				player.setHp(rs.getInt(3));
				player.setLocation(rs.getString(4));
				out.write("Ψһ��ʶ����" + id);
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
		}//end insert
	}//end ServerThread

	public static int PORT_NUM = 1888;
	
	static public void main(String[] args) throws IOException {
		RoomManagement.creatRooms();
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT_NUM);
		while (true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}
	}//end main

}//end Server class

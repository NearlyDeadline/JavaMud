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
		
		Socket socket;
		
		public ServerThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader (socket.getInputStream()));
				BufferedWriter out= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				String input = "";
				out.write("�ɹ�������������\n");
				do {
					out.write("�����û���������45�ֽڵĲ��ֻᱻ�ض�\n");
					out.flush();
					input = in.readLine();
				} while (input.isEmpty());
				Player p = null;
				p = login(out, input);//�û���¼
				
				MessageManagement.addPlayerChannels(p.getId(), out);
				System.out.println(p.getName()+"���߽�����Ϸ");
				MessageManagement.chat(p, p.getName() + "�뿪��" + p.getLocation() + "\n");
				
				boolean quit = false;
				while (!quit) {
					input = in.readLine();
					if (input.equals("quit")) {
						quit = true;
						System.out.println(p.getName()+"�����˳���Ϸ\n");
						MessageManagement.chat(p, p.getName() + "�˳�����Ϸ\n");
					}
					UserInput.dealInput(p, input);
				}
				MessageManagement.showToPlayer(p, "�ɹ������˳���Ϸ\n");
				MessageManagement.removePlayerChannels(p.getId());
				Player.delOnlinePlayers(p.getId());
				p = null;
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
				if (!con.isClosed())
					System.out.println("�ɹ����������ݿ�");
				String sql = "INSERT INTO mud.users (name) values (?);";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				//INSERT INTO sys.users (name) values ('$name');����$nameΪ������û�������
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
				Player.addOnlinePlayers(id, player);
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
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT_NUM);
		System.out.println("�������ѵ�¼\n");
		while (true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}
	}//end main

}//end Server class

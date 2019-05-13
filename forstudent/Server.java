package forstudent;
import java.io.*;
import java.net.*;

public class Server {

	static class ServerThread extends Thread {
		
		Socket socket;
		
		public ServerThread(Socket socket) {
			//���
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				//��������Ӧ�������������
				Writer output = new OutputStreamWriter(socket.getOutputStream());
				DataInputStream input = new DataInputStream(socket.getInputStream());
				output.write("�����¼��Ϣ\n");
				output.flush();
				System.out.println("�ͻ��������ӣ����Ϊ��" + input.readInt());
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}//end ServerThread

	public static int PORT_NUM = 1888;

	static public void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(PORT_NUM);
		while (true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();

		}
	}//end main

}//end Server class

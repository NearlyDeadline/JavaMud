package forstudent;
import java.io.*;
import java.net.*;

public class Server {

	static class ServerThread extends Thread {

		public ServerThread(Socket socket) {
			//���
		}

		Socket socket;

		@Override
		public void run() {
			try {
				//��������Ӧ�������������
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static int PORT_NUM = 1888;

	static public void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(PORT_NUM);
		for (;;) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();

		}
	}//end main

}//end Server class

package forstudent;
import java.io.*;
import java.net.*;

public class Server {

	static class ServerThread extends Thread {
		
		Socket socket;
		
		public ServerThread(Socket socket) {
			//添加
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				//服务器响应代码在这里添加
				Writer output = new OutputStreamWriter(socket.getOutputStream());
				DataInputStream input = new DataInputStream(socket.getInputStream());
				output.write("输入登录信息\n");
				output.flush();
				System.out.println("客户端已连接！编号为：" + input.readInt());
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

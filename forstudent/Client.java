package forstudent;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6450215156697251411L;
	private JTextArea screen;
	private JTextField input;
	private JButton connection;

	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;
	
	private String ipaddress = "127.0.0.1";
	private int port = 1888;
	private boolean connected = false;

	class MonitorThread extends Thread {
		public MonitorThread(BufferedReader br) {
			//添加
			this.br = br;
		}

		BufferedReader br;

		@Override
		public void run() {
			//接收服务器消息的控制在这里添加

		}
	};

	public Client() {
		super("客户端");
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		container.add(BorderLayout.CENTER, leftPanel);
		container.add(BorderLayout.EAST, rightPanel);
		leftPanel.setLayout(new BorderLayout());
		screen = new JTextArea();
		screen.setEditable(false);
		screen.setAutoscrolls(true);
		screen.setLineWrap(true);
		JScrollPane jsp = new JScrollPane(screen);
		input = new JTextField();
		connection = new JButton("登录");
		leftPanel.add(BorderLayout.CENTER, jsp);
		leftPanel.add(BorderLayout.SOUTH, input);
		rightPanel.add(connection);
		this.setSize(600, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				// TODO Auto-generated method stub
				//用户键盘输入在这里添加
				
			}
		});
		connection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					//连接服务器在这里添加
					socket = new Socket(ipaddress, port);
					in = new BufferedReader(new InputStreamReader (socket.getInputStream()));
					out= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					screen.append(in.readLine() + "\r\n");
					screen.append(in.readLine() + "\r\n");
					connected = true;
				} catch (Exception e) {
					e.printStackTrace();
					screen.setText(screen.getText() + "链接服务器失败！请重试");
					System.exit(1);
				}
			}
		});
	}

	public void setDefaultCloseOperation(int arg0) {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (connected) {
			try {
				connected = false;
				socket.close();
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setText(JTextArea screen, String Message) {
		String[] temp = Message.split("\t");
		for (int i = 0; i < temp.length; i++) {
			screen.setText(screen.getText() + temp[i] + "\n");
			// System.out.print(temp[i]+"\n");
		}
		screen.setCaretPosition(screen.getDocument().getLength());
	}

	public static void main(String[] args) {
		new Client();
	}
}

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

	class MonitorThread extends Thread {//新建线程负责接受服务器传回的消息
		public MonitorThread(BufferedReader br) {
			this.br = br;
		}

		BufferedReader br;

		@Override
		public void run() {
			String text = "";
			try {
				while ((text = br.readLine()) != null) {
					appendText(text + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		connection = new JButton("连接服务器");
		leftPanel.add(BorderLayout.CENTER, jsp);
		leftPanel.add(BorderLayout.SOUTH, input);
		rightPanel.add(connection);
		this.setSize(600, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (event.getKeyChar() == KeyEvent.VK_ENTER) {//按Enter将命令发送给服务器
					try {
						out.write(input.getText() + "\n");
						out.flush();	
					} catch (Exception e) {
						appendText("服务器未启动\n");
					} finally {
						input.setText("");
					}
				}
			}
		});
		connection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {//连接服务器
			    	socket = new Socket(ipaddress, port);
					in = new BufferedReader(new InputStreamReader (socket.getInputStream()));
					out= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					MonitorThread monitor = new MonitorThread(in);
					monitor.start();
					connected = true;
				} catch (Exception e) {
					appendText("服务器未启动\n");
				}
			}
		});
	}

	public void setDefaultCloseOperation(int arg0) {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (connected) {//关闭窗口和输入输出流
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

	private void appendText(String text) {
		screen.append(text);
		screen.selectAll();
		if (screen.getSelectedText() != null) {
			screen.setCaretPosition(screen.getSelectedText().length());
			screen.requestFocus();
			input.requestFocus();
		}
	}

	public static void main(String[] args) {
		new Client();
	}
}

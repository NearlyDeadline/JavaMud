package forstudent;
import java.util.Hashtable;

public class Player {

	private int hp;
	private Integer id;
	private String name;
	private String location;
	
	static private Hashtable<Integer, Player> onlinePlayers = new Hashtable<Integer, Player>();//����������ұ��浽�ڴ���

	public static void addOnlinePlayers(Integer key, Player p) {
		onlinePlayers.put(key, p);
	}
	public static void delOnlinePlayers(Integer key) {
		onlinePlayers.remove(key);
	}
	public void move(CommonContent.DIRECTION direction) {//�����ݿ�ѡȡ���ڷ��䣬��ȡ��Χ�����ж��Ƿ���С������������this.location������д��Hashtable�����ݿ��У�����ظ�������
		
	}
	public void look(String something){
		if(something.equals(""))
			;//�鿴��ǰ����;
		else
			;//�鿴������Ʒ
	}
	
	public void leave(){
		//���߷����˳��ˣ��ͷ���Դ
		
		
		//save���������
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}

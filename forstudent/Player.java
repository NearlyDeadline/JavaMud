package forstudent;
public class Player {

	private int hp;
	private int id;
	private String name;
	private String location;

	public void move(CommonContent.DIRECTION direction) {
		
	}
	public void look(String something){
		if(something.equals(""))
			;//�鿴��ǰ����;
		else
			;//�鿴������Ʒ
	}
	
	public void quit(){
		//���߷����˳��ˣ��ͷ���Դ
		
		
		//save���������
	}
	
	public void hp() {
		//�鿴hp
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

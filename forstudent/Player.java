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
			;//查看当前房间;
		else
			;//查看其它物品
	}
	
	public void quit(){
		//告诉房间退出了，释放资源
		
		
		//save添加在这里
	}
	
	public void hp() {
		//查看hp
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

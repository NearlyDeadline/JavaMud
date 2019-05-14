package forstudent;
import java.util.*;

public class Area {

	private HashMap<CommonContent.DIRECTION, Area> neighbor = new HashMap<CommonContent.DIRECTION, Area>();

	void setRoom(CommonContent.DIRECTION d, Area r) {
		neighbor.put(d, r);
		// assert r.getRoom(d) == this;
	}

	public Area getRoom(CommonContent.DIRECTION d) {
		return neighbor.get(d);

	}

	private String roomDescription;
	private String roomLooking;
	private String roomId;
	private String roomName;
	static private Hashtable<Integer, Player> playerList = new Hashtable<Integer, Player>();
	static private Hashtable<String, Player> roomList = new Hashtable<String, Player>();
	public void move(Player player, CommonContent.DIRECTION direction) {
		

	}

	public void enter(Player player, CommonContent.DIRECTION direction) {
		
	}
	
	public void leave(Player player, CommonContent.DIRECTION direction) {
		
	}

	public void setDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public String getDescription() {
		return roomDescription;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void SetRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	/*public String getRoomLooking() {
		return roomLooking;
	}*/

	public String getRoomLooking() {
		// ������
		roomLooking = roomName + "\t";
		// ��������
		// Ӧ����Client�����������������ַ����趨���壬ÿ��������
		int roomDescriptionLength = roomDescription.length();
		if (roomDescriptionLength <= CommonContent.CHARS_PER_LINE)
			roomLooking += roomDescription + "\t";
		else {
			int i;
			for (i = 0; i <= roomDescriptionLength
					- CommonContent.CHARS_PER_LINE; i = i
					+ CommonContent.CHARS_PER_LINE) {
				roomLooking += roomDescription.substring(i, i
						+ CommonContent.CHARS_PER_LINE)
						+ "\t";
			}
			roomLooking += roomDescription.substring(i, roomDescriptionLength)
					+ "\t";
		}

		// �������
		roomLooking += getChuKou() + "\t";
		// ����npc

		// ����player
		roomLooking += listRoomPlayers();
		// ����obj
		return roomLooking;
	}
	private String listRoomPlayers(){
		//�г���������е��������
		String temp = "";
		
		return temp;
	}
	private String getChuKou() {
		/*����ÿ������ĳ���
		 * 
		 * 
		 * */
		String temp = "";
		
		return temp;
	}
}

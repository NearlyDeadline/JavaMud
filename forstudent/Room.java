package forstudent;
import java.util.*;

public class Room {

	private HashMap<CommonContent.DIRECTION, Room> neighbor = new HashMap<CommonContent.DIRECTION, Room>();

	void setRoom(CommonContent.DIRECTION d, Room r) {
		neighbor.put(d, r);
		// assert r.getRoom(d) == this;
	}

	public Room getRoom(CommonContent.DIRECTION d) {
		return neighbor.get(d);

	}

	private String roomDescription;
	private String roomLooking;
	private String roomId;
	private String roomName;

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

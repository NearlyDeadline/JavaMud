package forstudent;

public class CommonRoom implements Room{
	private String RoomId;
	
	public String getRoomId() {
		return RoomId;
	}

	public void setRoomId(String roomId) {
		RoomId = roomId;
	}
	
	public void enter(Player p) {
		MessageManagement.chat(p, p.getName() + "������" + StaticFunctions.getRoomName(RoomId));
	}
	
	public void leave(Player p) {
		MessageManagement.chat(p, p.getName() + "�뿪��" + StaticFunctions.getRoomName(RoomId));
	}
}
 

package forstudent;

public class StaticFunctions {
	public static String getDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "��";break;
		case WEST:
			chinese = "��";break;
		case SOUTH:
			chinese = "��";break;
		case NORTH:
			chinese ="��";break;
		case NORTHEAST:
			chinese = "����";break;
		case NORTHWEST:
			chinese = "����";break;
		case SOUTHEAST:
			chinese = "����";break;
		case SOUTHWEST:
			chinese = "����";break;
		case UP:
			chinese = "��";break;
		case DOWN:
			chinese = "��";break;
		}
		return chinese;
	}
	public static String getReverseDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "��";break;
		case WEST:
			chinese = "��";break;
		case SOUTH:
			chinese = "��";break;
		case NORTH:
			chinese ="��";break;
		case NORTHEAST:
			chinese = "����";break;
		case NORTHWEST:
			chinese = "����";break;
		case SOUTHEAST:
			chinese = "����";break;
		case SOUTHWEST:
			chinese = "����";break;
		case UP:
			chinese = "��";break;
		case DOWN:
			chinese = "��";break;
		}
		return chinese;
	}
}

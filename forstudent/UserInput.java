package forstudent;
public class UserInput {
	public static void dealInput(Player p, String inputMessage) {
		/*
		 * 可以处理的命令 l,look,hp,e,east,w,west,n,north,s,south,
		 */

		String[] inputs = inputMessage.split(" ");
		if (inputs[0].equals("l") || inputs[0].equals("look")) {
			p.look(inputs[0]);
			return;
		}
		if (inputs[0].equals("quit")) {
			return;
		}
		if (inputs[0].equals("hp")) {
			MessageManagement.showToPlayer(p, "当前生命值为：" + p.getHp() + '\n');
			return;
		}
		if (inputs[0].equals("e") || inputs[0].equals("east")) {
			p.move(CommonContent.DIRECTION.EAST);
			return;
		}
		if (inputs[0].equals("w") || inputs[0].equals("west")) {
			p.move(CommonContent.DIRECTION.WEST);
			return;
		}
		if (inputs[0].equals("s") || inputs[0].equals("south")) {
			p.move(CommonContent.DIRECTION.SOUTH);
			return;
		}
		if (inputs[0].equals("n") || inputs[0].equals("north")) {
			p.move(CommonContent.DIRECTION.NORTH);
			return;
		}
		if (inputs[0].equals("ne") || inputs[0].equals("northeast")) {
			p.move(CommonContent.DIRECTION.NORTHEAST);
			return;
		}
		if (inputs[0].equals("nw") || inputs[0].equals("northwest")) {
			p.move(CommonContent.DIRECTION.NORTHWEST);
			return;
		}
		if (inputs[0].equals("se") || inputs[0].equals("southeast")) {
			p.move(CommonContent.DIRECTION.SOUTHEAST);
			return;
		}
		if (inputs[0].equals("sw") || inputs[0].equals("southwest")) {
			p.move(CommonContent.DIRECTION.SOUTHWEST);
			return;
		}
		if (inputs[0].equals("u") || inputs[0].equals("up")) {
			p.move(CommonContent.DIRECTION.UP);
			return;
		}
		if (inputs[0].equals("d") || inputs[0].equals("down")) {
			p.move(CommonContent.DIRECTION.DOWN);
			return;
		}	
		MessageManagement.showToPlayer(p, "什么？\n");
	}
}

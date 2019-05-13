package forstudent;
import java.util.HashMap;
import java.util.Map;

public class RoomManagement {
	static Map<String, Room> cityMap = new HashMap<String, Room>();

	public static void creatRooms() {
		/*����*/
		Room room_guangchang = new Room();	
		room_guangchang.setRoomId("yangzhou_guangchang");
		room_guangchang.SetRoomName("���ݹ㳡");
		room_guangchang.setDescription(" �����ǳ��е������ģ�һ���ܿ����Ĺ㳡��������ʯ���档һЩ���ֺ��е��������������������������������ݡ�����Կ������ߺ��ϱ��Ǽ��Ҵ�ĵ��̣�����ȫ�����ص����������������������������ر�Ӫ����������������Ѳ�����ŵ����ڡ��㳡������һ��������(tree)���Ե�ʮ�ֵĴ�׳�����ϵ���Ҷ(leaf)�ǳ�ï�ܣ��м�ֻ��֪����С�������д�����ȥ�������Ա���һ������������(sign)");
		
		//�����
		Room room_beidajie1 = new Room();
		room_beidajie1.setRoomId("yangzhou_beidajie1");
		room_beidajie1.SetRoomName("�����");
		room_beidajie1.setDescription("������һ����æ�Ľֵ��ϣ����Ų�����ǻ������������ɫ�Ҵң�����˶����ϱ���ȥ������ͨ��һ�����ֵĹ㳡��������һ��������¡�ı�����ջ�����Ը��ص����ǽ�������������������һ�����ֺŵ�Ǯׯ����Ϊ���ի�������������������Ľ���������");
		
		Room room_beidajie2 = new Room();
		room_beidajie2.setRoomId("yangzhou_beidajie2");
		room_beidajie2.SetRoomName("�����");
		room_beidajie2.setDescription("������һ����æ�Ľֵ��ϣ�������������վ�����Ծ��ǵĿ�����ʱ�ἱʻ���룬Ƭ�̺��ֻ�����һƥ�������������������ȥ���ϱ���һ�Ҵ���̣���ǰ�����������д��һ������ġ������֣���ϸ��������������ѹ�͵��ּۻ��۵�������");
		
		Room room_beidajie3 = new Room();
		room_beidajie3.setRoomId("yangzhou_beidajie3");
		room_beidajie3.SetRoomName("�����");
		room_beidajie3.setDescription("����һ����������ʯ�ֵ������ϱ���ͷ���졣�����Ǳ�����ͨ����⡣�ϱ��Եúܷ�æ��������һ��С�Ե꣬����խС������ֻ��Ӧ���������߾��ǳ�����");
		
		Room room_beimen = new Room();
		room_beimen.setRoomId("yangzhou_beimen");
		room_beimen.SetRoomName("����");
		room_beimen.setDescription("���Ǿ������ݳǵı������ˣ����ſ�һ�Ӷӹٱ������̲�����İ��գ�һ���ֳֳ����������佫�����ſ�Ѳ�ӣ���Щʱ����Ϊ����һ���ķ�˽���������ң����Գ�͢�԰��²��������������˳�Ҫ���Ĺ�ְ���Ͼ���ն�ˣ�����һλ�µ�Ѳ�����ˣ�Ū���������Ļ̻̣�˭�������ڳ��ž��������ŵ�ǽ��������һֽ��ʾ(gaoshi)��");
		
		Room room_qianzhuang = new Room();
		room_qianzhuang.setRoomId("yangzhou_qianzhuang");
		room_qianzhuang.SetRoomName("���ի");
		room_qianzhuang.setDescription("����һ�����ֺŵ�Ǯׯ�����м��������ʷ����ȫ�����ض��зֵꡣ�����е���Ʊ�����ǳ��ã�ͨ��ȫ����");
		
		Room room_kezhan = new Room();
		room_kezhan.setRoomId("yangzhou_kezhan");
		room_kezhan.SetRoomName("������ջ");
		room_kezhan.setDescription("������������¡�ı�����ջ������������οͶ�ѡ���ڴ���š�һ������ĵ�С����������æ������ת���Ӵ�����ǻ�����Ŀ��ˡ���ջ�����ǽ�Ϲ���һ����Ŀ������(zhaopai)�������ǽ�Ϲ���һ�����Ϲ��ڵĸ�ʾ(gaoshi)��");
		
		Room room_yizhan = new Room();
		room_yizhan.setRoomId("yangzhou_yizhan");
		room_yizhan.SetRoomName("��վ");
		room_yizhan.setDescription("����������վ�������ͺ;�������������ġ�ÿ�������ƥ��������������������Ρ������������Ҫ�ĵ����ֻҪ�ŵ����һ��֮�ھ��Կ����͵���������һ���¿��Ļ��꣬��˵������ϰ�ǳ�Ư����");
		
		Room room_dangpu = new Room();
		room_dangpu.setRoomId("yangzhou_dangpu");
		room_dangpu.SetRoomName("����");
		room_dangpu.setDescription("����һ������������ƽ���Ƶ����ֺŵ��̣�һ���ĳ߸ߵĹ�̨���������ǰ����̨�ϰ���һ������(paizi)�� ��̨�����ŵ��̵��ϰ壬һ˫��������۾��������´�������.");
		
		Room room_chmiao = new Room();
		room_chmiao.setRoomId("yangzhou_chmiao");
		room_chmiao.SetRoomName("������");
		room_chmiao.setDescription(" �������ݳǱ��ĳ���������ƽ�����ϡ�٣���������ڡ�����ڻ�������ʱ�򣬲Ż���Щ������з���һ��������������������˻ҳ���ǽ�����и���֩��������ֻ֩�����ĵض������档");
		
		Room room_xiaochidian = new Room();
		room_xiaochidian.setRoomId("yangzhou_xiaochidian");
		room_xiaochidian.SetRoomName("С�Ե�");
		room_xiaochidian.setDescription("����һ��С�꣬���⵹��ͦ��¡�ģ���λ���������ģ�����Щ����վ�ŵ������������Ҳ�����棬���������ӡ����ȡ���Ѽ���׾Ƹ�·�ɡ�");
		
		
		//�ϴ��
		Room room_nandajie1 = new Room();
		room_nandajie1.setRoomId("yangzhou_nandajie1");
		room_nandajie1.SetRoomName("�ϴ��");
		room_nandajie1.setDescription("�ϴ���������ݳ���ķ����ضΣ�һ�����ϣ�һ�ɵƺ���̣���Ϊ���֡�Ц�������������������������һƬ��������Ķ��䣬�㲻����Գ��������ʹ�ͣ������һ����������һ�����ֵĹ㳡��������һƬ�����������š�һ��һʮ������Ǯ����ԭ�������Ƿ�Բǧ��֮������һ�Ҷĳ�");
		
		Room room_nandajie2 = new Room();
		room_nandajie2.setRoomId("yangzhou_nandajie2");
		room_nandajie2.SetRoomName("�ϴ��");
		room_nandajie2.setDescription("������һ�������Ľֵ��ϣ����ϱ���ͷ���졣�ϱ��Ǽ���԰�֣�����ͨ�������ģ�������һ�ҹ˿��ڶ�Ĳ�ݣ����̾�����ʿ��������̸��˵�ء��������ǹٸ�����������ξ֡�");
		
		Room room_nandajie3 = new Room();
		room_nandajie3.setRoomId("yangzhou_nandajie3");
		room_nandajie3.SetRoomName("�ϴ��");
		room_nandajie3.setDescription("������һ�������Ľֵ��ϣ����ϱ���ͷ���졣�ϱ����ϳ��ţ�����ͨ�������ģ��������߸���һ��С԰��");
		
		Room room_nanmen = new Room();
		room_nanmen.setRoomId("yangzhou_nanmen");
		room_nanmen.SetRoomName("����");
		room_nanmen.setDescription("�������ݵ��ϳ��ţ���ǽ������һ�Źٸ��ĸ�ʾ(gaoshi)�������ǻ��εļ�ɢ�أ��������η��Ӻܶ࣬�����ٱ���������ؼ����������ˣ��鿴�����Ƿ�Я����˽�Ρ��ϱߵ���ʯ�������һֱͨ��Զ��������������ͷ��");
		
		Room room_duchang = new Room();
		room_duchang.setRoomId("yangzhou_duchang");
		room_duchang.SetRoomName("�ĳ�");
		room_duchang.setDescription("�����Ƕĳ��Ĵ��ã����ܵķ����ﴫ����ߺ������ĶĲ����������Ƕġ���С���ķ��䣬�����Ƕġ��ƾš��ķ��䣬¥�ϵĹ����ƻ�ͨ����");
		
		Room room_bingying = new Room();
		room_bingying.setRoomId("yangzhou_bingying");
		room_bingying.SetRoomName("��Ӫ");
		room_bingying.setDescription("�����Ǳ�Ӫ���������鵽�����ǹٱ����е����佫��ָ�����жӲ������еĶ������������е����š�����������Ϣ����ǽ��������˧��������ɫ��Ѱ�������ܡ����������������ȫ�������Χ�˹��������ƿ�����̫�������һ��С����(window)��Ϊ�˷���̽��İ��գ����Դ�������ڴ���(pass)Щ�ԵĶ�����");
		
		Room room_chaguan = new Room();
		room_chaguan.setRoomId("yangzhou_chaguan");
		room_chaguan.SetRoomName("���");
		room_chaguan.setDescription("�������ݳ��ϵ�һ�Ҳ�ݡ�һ�߽��������ŵ�һ�ɲ���������Ƣ����ľ���Ϊ֮һˬ�����Ű�����һ���ſ��������˿��ˣ������̸Ц����ͷ�Ӷ������Ϲ���һ������(zitie) ���������һ˵��֮�ˣ���������־��ˮ䰴�������Ӣ�Ҵ��ȵ�Ӣ�۹��¡��販ʿ����ߺ�����к�����, �ݲ���ˮ, æ�Ĳ����ֺ���");
		
		Room room_yanju = new Room();
		room_yanju.setRoomId("yangzhou_yanju");
		room_yanju.SetRoomName("�ξ�");
		room_yanju.setDescription("������һ���ṹ��ΰ�Ľ���ǰ������ʯ̳�ϸ�����һ�����ɶ�ߵ���ˣ����Ͻ���Ʈ�����������д�š������ξ֡��ĸ����֣������������˵��Ǹվ��Ƿ�������һ���ǽ������εļ�ɢ֮�أ��ٸ�Ϊ�˱��ڹ���, ������˰, �����������ξ�, ֱ�������ڳ�͢����ڴ���һ��������˵Ц��ǽ�Ƕ���һ��ʯ�ҷۣ��Ա߷��Ÿ�������");
		
		Room room_geyuan = new Room();
		room_geyuan.setRoomId("yangzhou_geyuan");
		room_geyuan.SetRoomName("��԰");
		room_geyuan.setDescription("�ݴ����������̻�Ӧ̩�޽���԰������ǧ�ˣ�����Ҷ���硰�����֣�������������԰����ת�������ȣ�ӭ�滨̳���������ʯ�񡣱���԰�����С���԰������ʯ�԰����Ϊ������ǰֲ����������ˮ�أ������ܼ�ʽСͤ��ˮ�������ӱ�ǽ�Ǻ�ʯ��ɽ��ɽ�Ϲ��ɣ�ɽ��������ˮ���������꣬ˮ�е�Ӱ��䣬����ɽ֮�ơ�");
		
		Room room_xiaopangu = new Room();
		room_xiaopangu.setRoomId("yangzhou_xiaopangu");
		room_xiaopangu.SetRoomName("С�̹�");
		room_xiaopangu.setDescription("С�̹�������һ��������СͥԺ��԰���Ժ�ʯ��ɽΪ����ɽ�����������ƶ����У�����һ�����ȣ���ͷ������ɽ�ȡ�ɽ���£�ˮ���ϣ���ռ�һʯ����ͨ��ˮ����ͤ��");
		
		
		//�����
		Room room_xidajie1 = new Room();
		room_xidajie1.setRoomId("yangzhou_xidajie1");
		room_xidajie1.SetRoomName("�����");
		room_xidajie1.setDescription("����һ����������ʯ��ֵ���������ͷ���졣������һƬ��ɫ��¥������Լ�����������Ц���Ǿ����������������񷻡���������������һ�����ֵĹ㳡��");
		
		Room room_xidajie2 = new Room();
		room_xidajie2.setRoomId("yangzhou_xidajie2");
		room_xidajie2.SetRoomName("�����");
		room_xidajie2.setDescription("����һ����������ʯ��ֵ���������ͷ���졣�����������������������Ҽ������ַǳ����Ӷ��治ʱ�ش���Ц��ݺ�裬�������ˣ�������������һ������������ϱ������������ݴ����£����߾������������ˡ�");
		
		Room room_xidajie3 = new Room();
		room_xidajie3.setRoomId("yangzhou_xidajie3");
		room_xidajie3.SetRoomName("�����");
		room_xidajie3.setDescription("������������ϣ��е�����Ľ���Ҫ�ȱ𴦵ĸɾ������ࡣ���ϵ����˱���ǰҲ������࣬���������ݵ������羰�����������������������š��ϱ���һ���Ѿ������˵��鱦�꣬����������һ���Ѿ������˵Ĵ��¥������һ���������Ŷ�������һ�����ң��ڰ�֮�п���̫�����ϵ��ּ���");
		
		Room room_ximen = new Room();
		room_ximen.setRoomId("yangzhou_ximen");
		room_ximen.SetRoomName("����");
		room_ximen.setDescription("���������ţ���ǽ�����ż���ͨ����ʾ(gaoshi)��������������ıؾ�֮�أ��ٱ��ǽ䱸ɭ�ϡ�һ����ֱ����ʯ���������������졣�����ǽ��⣬����ġ����εġ���·�ģ����˴Ҵҡ������ǳ��������֡�");
		//�����
		Room room_dongdajie1 = new Room();
		room_dongdajie1.setRoomId("yangzhou_dongdajie1");
		room_dongdajie1.SetRoomName("�����");
		room_dongdajie1.setDescription(" ����һ����������ʯ��ֵ���������ͷ���졣���߲�ʱ�ش������ʵ�������������һ�����ֵĹ㳡���ϱ�����һ�ҹ����ɫ�ĵ��̣���߲�ʱ���������������������");
		
		Room room_dongdajie2 = new Room();
		room_dongdajie2.setRoomId("yangzhou_dongdajie2");
		room_dongdajie2.SetRoomName("�����");
		room_dongdajie2.setDescription("����һ����������ʯ��ֵ���������ͷ���졣���߲�ʱ�ش�������ײ�����������������ӡ�������һ�����ӵ�Ժ�ӣ������εĴ����Ϸ�д�š���¹��Ժ���ĸ��̽���֣��Ծ����������治ʱ�ش���ѧ��������ٴ�Ķ��������ϱ���һ���ӻ��̡�");
		
		Room room_dongdajie3 = new Room();
		room_dongdajie3.setRoomId("yangzhou_dongdajie3");
		room_dongdajie3.SetRoomName("�����");
		room_dongdajie3.setDescription("�����ڶ�����ϣ����ż�ʵ����ʯ����档�����Ǵ����̺������꣬��ʱ�������ô��Ľ���ײ���������������ʵĶ�����������һ�𡣱�����һ�����ֺŵ�ҩ�̣��ϱ��ǼҼ����ꡣ");
		
		Room room_dongmen = new Room();
		room_dongmen.setRoomId("yangzhou_dongmen");
		room_dongmen.SetRoomName("����");
		room_dongmen.setDescription("���������ݶ����ţ��������Ϸ����š����š�����������֣���ǽ�����ż��Źٸ��ĸ�ʾ(gaoshi)���ٱ��Ǿ����ע���Ź������ˣ�ͨ�������С��Ϊ�һ����ֱ����ʯ���������������졣�����ǽ��⣬��Լ�ɼ�һƬһ���޼ʵ����֣�����Ī�⡣");
		
		//��������������
		//�㳡
		room_guangchang.setRoom(CommonContent.DIRECTION.NORTH, room_beidajie1);
		room_guangchang.setRoom(CommonContent.DIRECTION.SOUTH, room_nandajie1);
		room_guangchang.setRoom(CommonContent.DIRECTION.EAST, room_dongdajie1);
		room_guangchang.setRoom(CommonContent.DIRECTION.WEST, room_xidajie1);
		//�����
		room_beidajie1.setRoom(CommonContent.DIRECTION.NORTH, room_beidajie2);
		room_beidajie1.setRoom(CommonContent.DIRECTION.SOUTH, room_guangchang);
		room_beidajie1.setRoom(CommonContent.DIRECTION.EAST, room_kezhan);
		room_beidajie1.setRoom(CommonContent.DIRECTION.WEST, room_qianzhuang);
		
		room_beidajie2.setRoom(CommonContent.DIRECTION.NORTH, room_beidajie3);
		room_beidajie2.setRoom(CommonContent.DIRECTION.SOUTH, room_beidajie1);
		room_beidajie2.setRoom(CommonContent.DIRECTION.EAST, room_yizhan);
		room_beidajie2.setRoom(CommonContent.DIRECTION.WEST, room_dangpu);
		
		room_beidajie3.setRoom(CommonContent.DIRECTION.NORTH, room_beimen);
		room_beidajie3.setRoom(CommonContent.DIRECTION.SOUTH, room_beidajie2);
		room_beidajie3.setRoom(CommonContent.DIRECTION.EAST, room_xiaochidian);
		room_beidajie3.setRoom(CommonContent.DIRECTION.WEST, room_chmiao);
		
		room_beimen.setRoom(CommonContent.DIRECTION.SOUTH, room_beidajie3);
		room_qianzhuang.setRoom(CommonContent.DIRECTION.EAST, room_beidajie1);
		room_kezhan.setRoom(CommonContent.DIRECTION.WEST, room_beidajie1);
		room_dangpu.setRoom(CommonContent.DIRECTION.EAST, room_beidajie2);
		room_yizhan.setRoom(CommonContent.DIRECTION.WEST, room_beidajie2);
		room_chmiao.setRoom(CommonContent.DIRECTION.EAST, room_beidajie3);
		room_xiaochidian.setRoom(CommonContent.DIRECTION.WEST, room_beidajie3);
		
		//�ϴ��
		room_nandajie1.setRoom(CommonContent.DIRECTION.NORTH, room_guangchang);
		room_nandajie1.setRoom(CommonContent.DIRECTION.SOUTH, room_nandajie2);
		room_nandajie1.setRoom(CommonContent.DIRECTION.WEST, room_duchang);
		room_nandajie1.setRoom(CommonContent.DIRECTION.EAST, room_bingying);
		
		room_nandajie2.setRoom(CommonContent.DIRECTION.NORTH, room_nandajie1);
		room_nandajie2.setRoom(CommonContent.DIRECTION.SOUTH, room_nandajie3);
		room_nandajie2.setRoom(CommonContent.DIRECTION.WEST, room_chaguan);
		room_nandajie2.setRoom(CommonContent.DIRECTION.EAST, room_yanju);
		
		room_nandajie3.setRoom(CommonContent.DIRECTION.NORTH, room_nandajie2);
		room_nandajie3.setRoom(CommonContent.DIRECTION.SOUTH, room_nanmen);
		room_nandajie3.setRoom(CommonContent.DIRECTION.WEST, room_geyuan);
		room_nandajie3.setRoom(CommonContent.DIRECTION.EAST, room_xiaopangu);
		
		room_nanmen.setRoom(CommonContent.DIRECTION.NORTH, room_nandajie3);
		room_duchang.setRoom(CommonContent.DIRECTION.EAST, room_nandajie1);
		room_bingying.setRoom(CommonContent.DIRECTION.WEST, room_nandajie1);
		room_chaguan.setRoom(CommonContent.DIRECTION.EAST, room_nandajie2);
		room_yanju.setRoom(CommonContent.DIRECTION.WEST, room_nandajie2);
		room_geyuan.setRoom(CommonContent.DIRECTION.EAST, room_nandajie3);
		room_xiaopangu.setRoom(CommonContent.DIRECTION.WEST, room_nandajie3);
		
		cityMap.put(room_guangchang.getRoomId(), room_guangchang);
		cityMap.put(room_beidajie1.getRoomId(), room_beidajie1);
		cityMap.put(room_beidajie2.getRoomId(), room_beidajie2);
		cityMap.put(room_beidajie3.getRoomId(), room_beidajie3);
		cityMap.put(room_beimen.getRoomId(), room_beimen);
		cityMap.put(room_qianzhuang.getRoomId(), room_qianzhuang);
		cityMap.put(room_kezhan.getRoomId(), room_kezhan);
		cityMap.put(room_dangpu.getRoomId(), room_dangpu);
		cityMap.put(room_yizhan.getRoomId(), room_yizhan);
		cityMap.put(room_chmiao.getRoomId(), room_chmiao);
		cityMap.put(room_xiaochidian.getRoomId(), room_xiaochidian);
		cityMap.put(room_nandajie1.getRoomId(), room_nandajie1);
		cityMap.put(room_nandajie2.getRoomId(), room_nandajie2);
		cityMap.put(room_nandajie3.getRoomId(), room_nandajie3);
		cityMap.put(room_nanmen.getRoomId(), room_nanmen);
		cityMap.put(room_duchang.getRoomId(), room_duchang);
		cityMap.put(room_bingying.getRoomId(), room_bingying);
		cityMap.put(room_chaguan.getRoomId(), room_chaguan);
		cityMap.put(room_yanju.getRoomId(), room_yanju);
		cityMap.put(room_geyuan.getRoomId(), room_geyuan);
		cityMap.put(room_xiaopangu.getRoomId(), room_xiaopangu);
	}
}

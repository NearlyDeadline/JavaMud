package src.forstudent;

public final class MySQLData {
	static public final String driver = "com.mysql.cj.jdbc.Driver";
	static public final String url = "jdbc:mysql://127.0.0.1:3306/mud?serverTimezone=UTC";
	static public final String user = "root";
	static public final String password = "nfs9mw";
}
/*�÷���
    String name = "";
	try {
		Class.forName(MySQLData.driver);
		Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
		String sql = "SELECT (name) FROM mud.rooms WHERE id=?;";//MySQL���
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, this.location);//�趨ռλ��
		ResultSet rs = ps.executeQuery();//ִ�в�ѯ���������ؽ����
		while (rs.next()) {//���������
			name = rs.getString("name");//��������������ָ����ȡ������
		}
		rs.close();
		ps.close();
		con.close();//�رո������ݿ����
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
*/

package src.forstudent;

public final class MySQLData {
	static public final String driver = "com.mysql.cj.jdbc.Driver";
	static public final String url = "jdbc:mysql://127.0.0.1:3306/mud?serverTimezone=UTC";
	static public final String user = "root";
	static public final String password = "nfs9mw";
}
/*用法：
    String name = "";
	try {
		Class.forName(MySQLData.driver);
		Connection con = DriverManager.getConnection(MySQLData.url, MySQLData.user, MySQLData.password);
		String sql = "SELECT (name) FROM mud.rooms WHERE id=?;";//MySQL语句
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, this.location);//设定占位符
		ResultSet rs = ps.executeQuery();//执行查询操作，返回结果集
		while (rs.next()) {//遍历结果集
			name = rs.getString("name");//尽量输入列名来指明获取的数据
		}
		rs.close();
		ps.close();
		con.close();//关闭各个数据库变量
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
*/

import java.sql.*;
class  Testing
{
	public static void main(String[] args) throws Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
		System.out.println("Hello World!"+con);
	}
}

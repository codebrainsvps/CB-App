import java.sql.*;

public class Conn
 {
	
	public static Statement getConn() throws Exception
	{
		
				
		/*Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:venky","system","oracle10g");
		Statement st=con.createStatement();
		*///System.out.println("connected guru..");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","oracle10g");
		Statement st=con.createStatement();
	
		return st;
	}
	
 }
package fianl_nccuchecknote;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	static final String url="jdbc:mysql://140.119.19.79/TG03";
	static final String username="TG03";
	static final String password="ca476f";
	
	public static Connection getConnection() throws SQLException 
	{
		Connection conn=null;
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
}

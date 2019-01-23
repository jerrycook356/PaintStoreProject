package application.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

	
	public ConnectionHelper() {
		try {
		 Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection()
	{
		try 
		{	
			Connection con = DriverManager.getConnection("jdbc:derby:paintshopdatabase;create = true");
		    return con;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}

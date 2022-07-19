package khairat.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConnectionManager {
	static Connection con;
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_a23d7145f219ef1?reconnect=true";   // //jdbc:mysql://localhost/khairat-test
	private static final String DB_USER = "bafb594aaeadfb";    //root
	private static final String DB_PASSWORD = "c24bd303";   
	
	public static Connection getConnection() { 
		try {
		
			//1. load the driver
			Class.forName(DB_DRIVER);
					
			try {
				//2. create connection 
				con = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
				System.out.println("Connected");
				
			}catch(SQLException e) {
				e.printStackTrace();
			}		
		}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		return con;
	}
	
}

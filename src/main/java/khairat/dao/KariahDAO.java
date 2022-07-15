package khairat.dao;

import java.sql.*;
import java.util.*;
import khairat.connection.ConnectionManager;
import khairat.model.*;



public class KariahDAO {

	static Connection con = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	static String username, maritalstat, gender;
	static int id, phoneno;

	public static List<Kariah> getAllKariah() {
		List<Kariah> kariahs = new ArrayList<Kariah>();
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement  
			stmt = con.createStatement();
			//4. execute query
			rs = stmt.executeQuery("select * from kariah order by userid");

			while (rs.next()) {
				Kariah kariah = new Kariah();
				kariah.setUserid(rs.getInt("userid"));
				kariah.setUsername(rs.getString("username"));
				kariah.setMaritalstat(rs.getString("maritalstat"));
				kariah.setPhoneno(rs.getInt("phoneno"));
				kariah.setGender(rs.getString("gender"));
				kariahs.add(kariah);
			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return kariahs;
	}
	
	public static Kariah getKariahById(int userid) {
		Kariah kariah = new Kariah();
		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();
			//3. create statement 
			ps=con.prepareStatement("select * from kariah where userid=?");
			ps.setInt(1, userid);
			 //4. execute query
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {	            
				kariah.setUserid(rs.getInt("userid"));
				kariah.setUsername(rs.getString("username"));
				kariah.setGender(rs.getString("gender"));
				kariah.setMaritalstat(rs.getString("maritalstat"));
				kariah.setPhoneno(rs.getInt("phoneno"));
			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return kariah;
	}
	
	public void updateKariah(Kariah bean) {

		id = bean.getUserid();
		username = bean.getUsername();
		gender = bean.getGender();
		maritalstat = bean.getMaritalstat();
		phoneno = bean.getPhoneno();	

		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();
			//3. create statement  
			ps=con.prepareStatement("update kariah set username=?,gender=?,maritalstat=?,phoneno=? WHERE userid=?"); 		  
			ps.setString(1,username);//1 specifies the first parameter in the query i.e. name
			ps.setString(2,gender);		
			ps.setString(3,maritalstat);
			ps.setInt(4,phoneno);
			ps.setInt(5,id);
			//4. execute query
			ps.executeUpdate();

			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}
	
	public static List<Kariah> getAllUserKariah() {
		List<Kariah> kariahs = new ArrayList<Kariah>();
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement  
			stmt = con.createStatement();
			//4. execute query
			rs = stmt.executeQuery("select * from kariah inner join user using (userid)");

			while (rs.next()) {
				Kariah kariah = new Kariah();
				kariah.setUserid(rs.getInt("userid"));
				kariah.setUsername(rs.getString("username"));
				kariah.setMaritalstat(rs.getString("maritalstat"));
				kariah.setPhoneno(rs.getInt("phoneno"));
				kariah.setGender(rs.getString("gender"));
				kariah.setUser(UserDAO.getUserById(rs.getInt("userid")));
				
				kariahs.add(kariah);
			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return kariahs;
	}

}

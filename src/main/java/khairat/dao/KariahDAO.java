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
	static String maritalstat, gender, icNo, dob, address, phoneNo, userDeathDate;
	static int id, mosqueId;

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
				kariah.setIcNo(rs.getString("icNo"));
				kariah.setDob(rs.getString("dob"));
				kariah.setAddress(rs.getString("address"));
				kariah.setPhoneNo(rs.getString("phoneNo"));
				kariah.setMaritalstat(rs.getString("maritalstat"));
				kariah.setGender(rs.getString("gender"));
				kariah.setUserDeathDate(rs.getString("userDeathDate"));
				kariah.setMosqueId(rs.getInt("mosqueId"));
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
				kariah.setIcNo(rs.getString("icNo"));
				kariah.setDob(rs.getString("dob"));
				kariah.setAddress(rs.getString("address"));
				kariah.setPhoneNo(rs.getString("phoneNo"));
				kariah.setMaritalstat(rs.getString("maritalstat"));
				kariah.setGender(rs.getString("gender"));
				kariah.setUserDeathDate(rs.getString("userDeathDate"));
				kariah.setMosqueId(rs.getInt("mosqueId"));
				kariah.setMosque(MosqueDAO.getmosqueById(rs.getInt("mosqueId")));
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
		address = bean.getAddress();
		phoneNo = bean.getPhoneNo();
		maritalstat = bean.getMaritalstat();	
		userDeathDate = bean.getUserDeathDate();
		mosqueId = bean.getMosqueId();

		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();
			//3. create statement  
			ps=con.prepareStatement("update kariah set address=?,phoneNo=?,maritalstat=?,userDeathDate=?,mosqueId=? WHERE userid=?"); 		  
			ps.setString(1,address);//1 specifies the first parameter in the query i.e. name
			ps.setString(2, phoneNo);
			ps.setString(3,maritalstat);		
			ps.setString(4,userDeathDate);
			ps.setInt(5,mosqueId);
			ps.setInt(6,id);
			//4. execute query
			ps.executeUpdate();

			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}
	
	public void updateDeathDate(Kariah bean) {

		id = bean.getUserid();
		userDeathDate = bean.getUserDeathDate();	

		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();
			//3. create statement  
			ps=con.prepareStatement("update kariah set userDeathDate=? WHERE userid=?"); 		  
			ps.setString(1,userDeathDate);//1 specifies the first parameter in the query i.e. name
			ps.setInt(2,id);
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
				kariah.setIcNo(rs.getString("icNo"));
				kariah.setDob(rs.getString("dob"));
				kariah.setAddress(rs.getString("address"));
				kariah.setPhoneNo(rs.getString("phoneNo"));
				kariah.setMaritalstat(rs.getString("maritalstat"));
				kariah.setGender(rs.getString("gender"));
				kariah.setUserDeathDate(rs.getString("userDeathDate"));
				kariah.setMosqueId(rs.getInt("mosqueId"));
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

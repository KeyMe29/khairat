package khairat.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import khairat.connection.ConnectionManager;
import khairat.model.Staff;

public class StaffDAO {

	static Connection con = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	String name, position;
	int staffid; 	

	//get all staff
	public static List<Staff> getAllStaff() {
		List<Staff> staff = new ArrayList<Staff>();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			//3. create statement 
			stmt = con.createStatement();
			//4. execute query
			rs = stmt.executeQuery("select * from admin order by userid");

			while (rs.next()) {
				Staff st = new Staff();
				st.setStaffid(rs.getInt("userid"));
				st.setName(rs.getString("admin_name"));
				st.setPosition(rs.getString("admin_position"));
				//st.setId(rs.getInt("id"));
				staff.add(st);

			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return staff;
	}


	//get staff by staffid
	public static Staff getStaffById(int staffid) {
		Staff st = new Staff();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			//3. create statement 
			ps=con.prepareStatement("select * from admin where userid=?");
			ps.setInt(1, staffid);
			//4. execute query
			rs = ps.executeQuery();
			if (rs.next()) {	            
				st.setStaffid(rs.getInt("userid"));
				st.setName(rs.getString("admin_name"));
				st.setPosition(rs.getString("admin_position"));
			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return st;
	}

	//update staff
	public void updateStaff(Staff bean) {

		staffid = bean.getStaffid();
		name = bean.getName();	
		position = bean.getPosition();

		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement 
			ps=con.prepareStatement("update admin set admin_name=?, admin_position=?  WHERE userid=?"); 		  
			ps.setString(1,name); //1 specifies the first parameter in the query i.e. name	
			ps.setString(2, position);
			ps.setInt(3,staffid);
			//4. execute query
			ps.executeUpdate();
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}

	
}

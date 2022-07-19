package khairat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import khairat.connection.ConnectionManager;
import khairat.model.*;


public class MosqueDAO {
	static Connection con = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	static String mosqueName, mosqueAddress;
	static int mosqueId, supervisorId;
	static String query;
	
	public void addMosque(Mosque bean){		
		mosqueName = bean.getMosqueName();
		mosqueAddress = bean.getMosqueAddress();
		supervisorId = bean.getSupervisorId();

		try {
			//call getConnection() method //3. create statement //4. execute query
			con = ConnectionManager.getConnection();
			//3. create statement
			ps=con.prepareStatement("insert into mosque(mosqueName,mosqueAddress,supervisorId)values(?,?,?)");
			ps.setString(1,mosqueName);
			ps.setString(2,mosqueAddress);
			ps.setInt(3, supervisorId);
			//4. execute query
			ps.executeUpdate();
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Mosque> getAllmosque() {
		List<Mosque> mosques = new ArrayList<Mosque>();
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement  
			stmt = con.createStatement();
			//4. execute query
			rs = stmt.executeQuery("select * from mosque order by mosqueId");

			while (rs.next()) {
				Mosque mosque = new Mosque();
				mosque.setMosqueId(rs.getInt("mosqueId"));
				mosque.setMosqueName(rs.getString("mosqueName"));
				mosque.setMosqueAddress(rs.getString("mosqueAddress"));
				mosque.setSupervisorId(rs.getInt("supervisorId"));
				mosque.setSuperv(MosqueDAO.getmosqueById(rs.getInt("supervisorId")));
				mosques.add(mosque);
			}
			//5. close connection
			//con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return mosques;
	}
	
	public static Mosque getmosqueById(int mosqueid) {
		Mosque mosque = new Mosque();
		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();
			//3. create statement 
			ps=con.prepareStatement("select * from mosque where mosqueId=?");
			ps.setInt(1, mosqueid);
			 //4. execute query
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {	            
				mosque.setMosqueId(rs.getInt("mosqueId"));
				mosque.setMosqueName(rs.getString("mosqueName"));
				mosque.setMosqueAddress(rs.getString("mosqueAddress"));
				mosque.setSupervisorId(rs.getInt("supervisorId"));
			}
			//5. close connection
			//con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return mosque;
	}
	
	public static Mosque getMosqueSuperv(int mosqueid) {
		Mosque mosque = new Mosque();
		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();
			//3. create statement 
			ps=con.prepareStatement("select * from mosque where mosqueId=?");
			ps.setInt(1, mosqueid);
			 //4. execute query
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {	            
				mosque.setMosqueId(rs.getInt("mosqueId"));
				mosque.setMosqueName(rs.getString("mosqueName"));
				mosque.setMosqueAddress(rs.getString("mosqueAddress"));
				mosque.setSupervisorId(rs.getInt("supervisorId"));
				mosque.setSuperv(MosqueDAO.getmosqueById(rs.getInt("supervisorId")));
			}
			//5. close connection
			//con.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return mosque;
	}
	
	//update mosque
		public void updateMosque(Mosque bean) {

			mosqueId = bean.getMosqueId();
			mosqueName = bean.getMosqueName();	
			mosqueAddress = bean.getMosqueAddress();
			supervisorId = bean.getSupervisorId();

			try {
				//call getConnection() method
				con = ConnectionManager.getConnection();
				//3. create statement 
				ps=con.prepareStatement("update mosque set mosqueName=?, mosqueAddress=?, supervisorId=?  WHERE mosqueId=?"); 		  
				ps.setString(1,mosqueName); //1 specifies the first parameter in the query i.e. name	
				ps.setString(2, mosqueAddress);
				ps.setInt(3, supervisorId);
				ps.setInt(4,mosqueId);
				
				//4. execute query
				ps.executeUpdate();
				//5. close connection
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			
			}
		}
		
		//delete mosque
		public void deleteMosque(int mosqueid) {
			
			try {
				//call getConnection() method from ConnectionManager class
				con = ConnectionManager.getConnection();
				
				//3. create statement
				String sql = "DELETE FROM mosque WHERE mosqueid=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, mosqueid);
				
				//4. execute query
				ps.executeUpdate();
				
				//5. close connection
				con.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
}

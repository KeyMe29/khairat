package khairat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import khairat.connection.ConnectionManager;
import khairat.model.Dependent;
import khairat.model.Payment;

public class DependentDAO {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
    int depid, userid;
	String depName,depIcNo,depGender,depPhoneNo,depDeathDate,relation;
	
	
	
	//add dependent
	public void addDependent(Dependent bean) {
		
		//get dependent
		depName = bean.getDepName();
		depIcNo= bean.getDepIcNo();
		depGender = bean.getDepGender();
		depPhoneNo = bean.getDepPhoneNo();
		depDeathDate = bean.getDepDeathDate();
		relation = bean.getRelation();
		userid = bean.getUserid();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "INSERT INTO dependent(depName,depIcNo,depGender,depPhoneNo,depDeathDate,relation,userid)VALUES(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, depName);
			ps.setString(2, depIcNo);
			ps.setString(3, depGender);
			ps.setString(4, depPhoneNo);
			ps.setString(5, depDeathDate);
			ps.setString(6, relation);
			ps.setInt(7, userid);
			
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//get dependent by id 
	public static Dependent getDependentById(int depid) {
		Dependent dependent = new Dependent();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "SELECT * FROM dependent WHERE depid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, depid);
			
			
			//4. execute query
			rs = ps.executeQuery();
			if(rs.next()) {
				dependent.setDepid(rs.getInt("depid"));
				dependent.setDepName(rs.getString("depName"));
				dependent.setDepIcNo(rs.getString("depIcNo"));
				dependent.setDepGender(rs.getString("depGender"));
				dependent.setDepPhoneNo(rs.getString("depPhoneNo"));
				dependent.setDepDeathDate(rs.getString("depDeathDate"));
				dependent.setRelation(rs.getString("relation"));
				dependent.setUserid(rs.getInt("userid"));
				
			}	
			//5. close connection
			//con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dependent;
	}
	
	//get all dependents with head family
	public static List<Dependent> getAllDependents(){
		List<Dependent> dependents = new ArrayList<Dependent>();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "SELECT * FROM dependent INNER JOIN kariah using (userid) order by depid";
			stmt = con.createStatement();
			
			//4. execute query
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Dependent dependent = new Dependent();
				dependent.setDepid(rs.getInt("depid"));
				dependent.setDepName(rs.getString("depName"));
				dependent.setDepIcNo(rs.getString("depIcNo"));
				dependent.setDepGender(rs.getString("depGender"));
				dependent.setDepPhoneNo(rs.getString("depPhoneNo"));
				dependent.setDepDeathDate(rs.getString("date"));
				dependent.setRelation(rs.getString("relation"));
				dependent.setUserid(rs.getInt("userid"));
				dependent.setKariah(KariahDAO.getKariahById(rs.getInt("userid")));
				dependents.add(dependent);
			}
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dependents;
	}
	
	//get EACH kariah's dependents
		public static List<Dependent> getKariahDependent(int userid){
			List<Dependent> dependents = new ArrayList<Dependent>();
				
			try {
				con = ConnectionManager.getConnection();
				String sql = "SELECT * FROM dependent INNER JOIN kariah using (userid) where userid = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, userid);
				//stmt = con.createStatement();
				
				//4. execute query
				rs = ps.executeQuery();
				
				while(rs.next()) {
					Dependent dep = new Dependent();
					dep.setDepid(rs.getInt("depid"));
					dep.setDepName(rs.getString("depName"));
					dep.setDepIcNo(rs.getString("depIcNo"));
					dep.setDepGender(rs.getString("depGender"));
					dep.setDepPhoneNo(rs.getString("depPhoneNo"));
					dep.setDepDeathDate(rs.getString("date"));
					dep.setRelation(rs.getString("relation"));
					dep.setUserid(rs.getInt("userid"));
					dep.setKariah(KariahDAO.getKariahById(rs.getInt("userid")));
					dependents.add(dep);
					
				}
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return dependents;
		}
	
	
	//update dependent
	public void updateDependent(Dependent bean) {
		//get bill 
		depid=bean.getDepid();
		depName = bean.getDepName();
		depPhoneNo = bean.getDepPhoneNo();
		depDeathDate = bean.getDepDeathDate();
		relation = bean.getRelation();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "UPDATE dependent SET depName=?, depPhoneNo=?, depDeathDate=?, relation=? WHERE depid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, depName);
			ps.setString(2, depPhoneNo);
			ps.setString(3, depDeathDate);
			ps.setString(4, relation);
			ps.setInt(5, depid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//delete dependent
	public void deleteDependent(int depid) {
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "DELETE FROM dependent WHERE depid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, depid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

package khairat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import khairat.connection.ConnectionManager;
import khairat.model.Welfare;

public class WelfareDAO {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	int welfareid, userid, adminid;
	String welfareDate, note;
	double welfareAmount;
	
	
	//add welfare
	public void addWelfare(Welfare bean) {
		
		//get welfare 
		welfareAmount = bean.getWelfareAmount();
		welfareDate = bean.getWelfareDate();
		note = bean.getNote();
		userid = bean.getUserid();
		adminid = bean.getAdminid();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "INSERT INTO welfare(welfareAmount,welfareDate,note,userid,adminid)VALUES(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setDouble(1, welfareAmount);
			ps.setString(2, welfareDate);
			ps.setString(3, note);
			ps.setInt(4, userid);
			ps.setInt(5, adminid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//get welfare by id 
	public static Welfare getWelfareById(int welfareid) {
		Welfare welfare = new Welfare();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "SELECT * FROM welfare WHERE welfareid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, welfareid);
			
			
			//4. execute query
			rs = ps.executeQuery();
			if(rs.next()) {
				welfare.setWelfareid(rs.getInt("welfareid"));
				welfare.setWelfareAmount(rs.getDouble("welfareAmount"));
				welfare.setWelfareDate(rs.getString("date"));
				welfare.setNote(rs.getString("note"));
				welfare.setUserid(rs.getInt("userid"));
				welfare.setAdminid(rs.getInt("adminid"));
			}
			
			//5. close connection
			//con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return welfare;
	}
	
	//get all welfares
	public static List<Welfare> getAllWelfares(){
		List<Welfare> welfares = new ArrayList<Welfare>();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "SELECT * FROM report ORDER BY rid";
			stmt = con.createStatement();
			
			//4. execute query
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Welfare welfare = new Welfare();
				welfare.setWelfareid(rs.getInt("welfareid"));
				welfare.setWelfareAmount(rs.getDouble("welfareAmount"));
				welfare.setWelfareDate(rs.getString("welfareDate"));
				welfare.setNote(rs.getString("note"));
				welfare.setUserid(rs.getInt("userid"));
				welfare.setAdminid(rs.getInt("adminid"));
				welfares.add(welfare);		
			}
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return welfares;
	}
	
	
	//update welfare
	public void updateWelfare(Welfare bean) {
		//get welfare 
		welfareid = bean.getWelfareid();
		welfareAmount = bean.getWelfareAmount();
		welfareDate = bean.getWelfareDate();
		note = bean.getNote();
		userid = bean.getUserid();
		adminid = bean.getAdminid();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "UPDATE welfare SET welfareAmount=?, welfareDate=?, note=?, userid=?, adminid=? WHERE welfareid=?";
			ps = con.prepareStatement(sql);
			ps.setDouble(1, welfareAmount);
			ps.setString(2, welfareDate);
			ps.setString(3, note);
			ps.setInt(4, userid);
			ps.setInt(5, adminid);
			ps.setInt(6, welfareid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//delete welfare
	public void deleteWelfare(int welfareid) {
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "DELETE FROM welfare WHERE welfareid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, welfareid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


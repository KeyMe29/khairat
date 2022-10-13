package khairat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import java.util.*;

import java.sql.ResultSetMetaData;

import khairat.connection.ConnectionManager;
import khairat.model.*;

public class ReportDAO {

	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static int bid, userid;
	static String method, paymentstatus, refid;
	LocalDate payDate;
	
	public static List<ReportA> getReportApprovedPayment(){
		List<ReportA> reports = new ArrayList<ReportA>();
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement  
			stmt = con.createStatement();
			//4. execute query
			rs = stmt.executeQuery("select method, count(method), sum(amount), avg(amount) from payment JOIN bill using (bid) where payStatus like \"APPROVED\" group by method;");
			while (rs.next()) {
				ReportA report = new ReportA();
				report.setTypePayment(rs.getString("method"));
				report.setCountMethod(rs.getInt("count(method)"));
				report.setSumAmount(rs.getInt("sum(amount)"));
				report.setAvgAmount(rs.getInt("avg(amount)"));
				reports.add(report);
			}
			//5. close connection
			//con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return reports;
	}
	
	public static List<ReportA> getReportPendingPayment(){
		List<ReportA> reports = new ArrayList<ReportA>();
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement  
			stmt = con.createStatement();
			//4. execute query
			rs = stmt.executeQuery("select method, count(method), sum(amount) from payment JOIN bill using (bid) where payStatus like \"PENDING\" group by method;");
			while (rs.next()) {
				ReportA report = new ReportA();
				report.setTypePayment(rs.getString("method"));
				report.setCountMethod(rs.getInt("count(method)"));
				report.setSumAmount(rs.getInt("sum(amount)"));
				reports.add(report);
			}
			//5. close connection
			//con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return reports;
	}
	
	public static int getAliveUser() {
		int alive = 0;
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement  
			ps=con.prepareStatement("select count(userid) as \"Alive User\" from kariah where userDeathDate is null");
			 //4. execute query
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				alive = rs.getInt("Alive User");
			}
			//5. close connection
			//con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return alive;
	}
	
	public static int getDeceasedUser() {
		int dead = 0;
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement  
			ps=con.prepareStatement("select count(userid) as \"Deceased User\" from kariah where userDeathDate is not null");
			 //4. execute query
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				dead = rs.getInt("Deceased User");
			}
			//5. close connection
			//con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return dead;
	}
	
	public static double totalApprovedPayment() {
		double total = 0;
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement  
			ps=con.prepareStatement("select sum(amount) from payment JOIN bill using (bid) where payStatus like \"APPROVED\";");
			 //4. execute query
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				total = rs.getInt("sum(amount)");
			}
			//5. close connection
			//con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return total;
	}
	
	
	public static ReportA getTotalGender(){
		ReportA report = new ReportA();
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			//3. create statement  
			stmt = con.createStatement();
			//4. execute query
			rs = stmt.executeQuery("select (select count(gender) \"Count\" from kariah where gender = \"male\" and userDeathDate is null) \"Male\", (select count(gender) \"Count\" from kariah where gender = \"Female\" and userDeathDate is null) \"Female\" from dual;");
			ResultSetMetaData rsMetaData = (ResultSetMetaData) rs.getMetaData();
			int i = 1;
			if(rs.next()) {
				
				report.setGender(rsMetaData.getColumnName(0));
				report.setTotalGender(rs.getInt(0));
				report.setGender2(rsMetaData.getColumnName(1));
				report.setTotalGender2(rs.getInt(1));
			}
			//5. close connection
			//con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return report;
	}
	
	

}

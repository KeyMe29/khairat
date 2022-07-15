package khairat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import khairat.connection.ConnectionManager;
import khairat.model.Payment;

public class PaymentDAO {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	int pid, bid, userid;
	String method;
	
	//add payment
	public void addPayment(Payment bean) {
		
		//get report 
		bid = bean.getBid();
		method = bean.getMethod();
		userid = bean.getUserid();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "INSERT INTO payment(bid,method,userid)VALUES(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.setString(2, method);
			ps.setInt(3, userid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//get payment by id 
	public static Payment getPaymentById(int pid) {
		Payment payment = new Payment();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "SELECT * FROM payment WHERE pid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pid);
			
			
			//4. execute query
			rs = ps.executeQuery();
			if(rs.next()) {
				payment.setPid(rs.getInt("pid"));
				payment.setBid(rs.getInt("bid"));
				payment.setMethod(rs.getString("method"));
			}
			
			//5. close connection
			//con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return payment;
	}
	
	//get all payments
	public static List<Payment> getAllPayments(){
		List<Payment> payments = new ArrayList<Payment>();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "SELECT * FROM payment INNER JOIN kariah using (userid) order by pid";
			stmt = con.createStatement();
			
			//4. execute query
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Payment payment = new Payment();
				payment.setPid(rs.getInt("pid"));
				payment.setBid(rs.getInt("bid"));
				payment.setMethod(rs.getString("method"));
				payment.setUserid(rs.getInt("userid"));
				payment.setKariah(KariahDAO.getKariahById(rs.getInt("userid")));
				
				payments.add(payment);		
			}
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return payments;
	}
	
	//get ALL payment's users
	public static List<Payment> getUserPayment(int userid){
		List<Payment> payments = new ArrayList<Payment>();
			
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT * FROM payment INNER JOIN user using (userid) where userid = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			//stmt = con.createStatement();
			
			//4. execute query
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Payment payment = new Payment();
				payment.setPid(rs.getInt("pid"));
				payment.setBid(rs.getInt("bid"));
				payment.setMethod(rs.getString("method"));
				payment.setUserid(rs.getInt("userid"));
				payment.setUser(UserDAO.getUserById(rs.getInt("userid")));
				
				payments.add(payment);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return payments;
	}
	
	//update payment
	public void updatePayment(Payment bean) {
		//get payment 
		pid = bean.getPid();
		bid = bean.getBid();
		method = bean.getMethod();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "UPDATE payment SET bid=?, method=? WHERE pid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.setString(2, method);
			ps.setInt(3, pid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//delete payment
	public void deletePayment(int pid) {
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "DELETE FROM payment WHERE pid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


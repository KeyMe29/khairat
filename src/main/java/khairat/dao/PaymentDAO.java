package khairat.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

import khairat.connection.ConnectionManager;
import khairat.model.Payment;

public class PaymentDAO {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static int bid, userid;
	static String method, paymentstatus, refid;
	LocalDate payDate;
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
	
	//add payment
	public void addPayment(Payment bean) {
		
		//get report 
		bid = bean.getBid();
		userid = bean.getUserid();
		method = bean.getMethod();
		refid = bean.getRefid();
		paymentstatus = bean.getPayStatus();
		//Date dt = Date.valueOf(payDate);
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "INSERT INTO payment(bid,method,refid,userid)VALUES(?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.setString(2, method);
			ps.setString(3, refid);
			ps.setInt(4, userid);
			//ps.setDate(5,dt);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//get payment by id 
	public static Payment getPaymentById(Payment bean) {
		Payment payment = new Payment();
		
		bid = bean.getBid();
		userid = bean.getUserid();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "SELECT * FROM payment WHERE bid=? and userid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.setInt(2, userid);
			
			
			//4. execute query
			rs = ps.executeQuery();
			if(rs.next()) {
				payment.setBid(rs.getInt("bid"));
				payment.setUserid(rs.getInt("userid"));
				payment.setMethod(rs.getString("method"));
				payment.setRefid(rs.getString("refid"));
				payment.setPayStatus(rs.getString("payStatus"));
				//payment.setPayDate((rs.getDate("payDate")).toLocalDate());
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
			String sql = "SELECT * FROM payment INNER JOIN kariah using (userid)";
			stmt = con.createStatement();
			
			//4. execute query
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Payment payment = new Payment();
				payment.setBid(rs.getInt("bid"));
				payment.setMethod(rs.getString("method"));
				payment.setRefid(rs.getString("refid"));
				payment.setUserid(rs.getInt("userid"));
				payment.setKariah(KariahDAO.getKariahById(rs.getInt("userid")));
				payment.setPayStatus(rs.getString("payStatus"));
				//payment.setPayDate((rs.getDate("payDate")).toLocalDate());
				
				payments.add(payment);		
			}
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return payments;
	}
	
	//get EACH users' payments
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
				payment.setBid(rs.getInt("bid"));
				payment.setMethod(rs.getString("method"));
				payment.setRefid(rs.getString("refid"));
				payment.setUserid(rs.getInt("userid"));
				payment.setUser(UserDAO.getUserById(rs.getInt("userid")));
				payment.setPayStatus(rs.getString("payStatus"));
				//payment.setPayDate((rs.getDate("payDate")).toLocalDate());
				
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
		bid = bean.getBid();
		userid = bean.getUserid();
		paymentstatus = bean.getPayStatus();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "UPDATE payment SET userid=?, payStatus=? WHERE bid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setString(2, paymentstatus);
			ps.setInt(3, bid);
			
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


package khairat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import khairat.connection.ConnectionManager;
import khairat.model.Bill;

public class BillDAO {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	int bid, userid;
	String billname, date;
	Double amount;
	
	
	//add bill
	public void addBill(Bill bean) {
		
		//get bill 
		billname = bean.getBillname();
		date = bean.getDate();
		amount = bean.getAmount();
		userid = bean.getUserid();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "INSERT INTO bill(billname,date,amount,userid)VALUES(?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, billname);
			ps.setString(2, date);
			ps.setDouble(3, amount);
			ps.setInt(4, userid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//get bill by id 
	public static Bill getBillById(int bid) {
		Bill bill = new Bill();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "SELECT * FROM bill WHERE bid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bid);
			
			
			//4. execute query
			rs = ps.executeQuery();
			if(rs.next()) {
				bill.setBid(rs.getInt("bid"));
				bill.setBillname(rs.getString("billname"));
				bill.setDate(rs.getString("date"));
				bill.setAmount(rs.getDouble("amount"));
			}
			
			//5. close connection
			//con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bill;
	}
	
	//get all bills
	public static List<Bill> getAllBills(){
		List<Bill> bills = new ArrayList<Bill>();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "SELECT * FROM bill ORDER BY bid";
			stmt = con.createStatement();
			
			//4. execute query
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Bill bill = new Bill();
				bill.setBid(rs.getInt("Bid"));
				bill.setBillname(rs.getString("billname"));
				bill.setDate(rs.getString("date"));
				bill.setAmount(rs.getDouble("amount"));
				bills.add(bill);
			}
			
			//5. close connection
			//con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return bills;
	}
	
	
	//update bill
	public void updateBill(Bill bean) {
		//get bill 
		bid = bean.getBid();
		billname = bean.getBillname();
		date = bean.getDate();
		amount = bean.getAmount();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "UPDATE bill SET billname=?, date=?, amount=? WHERE bid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, billname);
			ps.setString(2, date);
			ps.setDouble(3, amount);
			ps.setInt(4, bid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//delete bill
	public void deleteBill(int bid) {
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "DELETE FROM bill WHERE bid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

package khairat.dao;

import java.security.*;
import java.sql.*;
import java.util.*;

import khairat.model.User;
import khairat.connection.ConnectionManager;

public class UserDAO {

	static Connection con = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	static String email, password, user_type;
	static int userid;
	static String query;
	
	//add new user (register)
	public void add(User bean) throws NoSuchAlgorithmException{
		
		email = bean.getEmail();
		password = bean.getPassword();
		user_type = bean.getUser_type();
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		//convert the byte to hex format
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		String password = sb.toString();
		
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			//3. create statement  
			ps=con.prepareStatement("insert into user(email,password,user_type)values(?,?,'Kariah')");
			ps.setString(1, email);
			ps.setString(2, password);
			//4. execute query
			ps.executeUpdate();			
			
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}
	}
	
	//method for login
		public static User login(User bean) throws NoSuchAlgorithmException{
			//get email and password
			email = bean.getEmail();
			password = bean.getPassword();

			//convert the password to MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());

			byte byteData[] = md.digest();

			//convert the byte to hex format
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			query = "select * from user where email='" + email + "'AND password='" + sb.toString() + "'";

			try {
				//call getConnection() method //3. create statement  //4. execute query
				con = ConnectionManager.getConnection();
				//3. create statement
				stmt = con.createStatement();
			    //4. execute query
				rs = stmt.executeQuery(query);
				boolean more = rs.next();

				// if user exists set the isValid variable to true
				if (more) {
					int id = rs.getInt("userid");
					String email = rs.getString("email");
					String user_type = rs.getString("user_type");
					bean.setUserid(id);
					bean.setEmail(email);
					bean.setUser_type(user_type);

					System.out.println(user_type);
					bean.setValid(true);
				}
				// if user does not exist set the isValid variable to false
				else if (!more) {
					bean.setValid(false);
				}

				//5. close connection
				con.close();
			}catch(Exception e) {
				e.printStackTrace();		
			}

			return bean;
		}
	
	//method to get user
		public static User getUser(User bean)  {   
			//get email
			email = bean.getEmail();
			query = "select * from user where email='" + email + "'";
			try {
				//call getConnection() method 
				con = ConnectionManager.getConnection();
				//3. create statement  
				stmt = con.createStatement();
				//execute statement
				rs = stmt.executeQuery(query);

				boolean more = rs.next();

				// if user exists set the isValid variable to true
				if (more) {
					String email = rs.getString("email");
					bean.setEmail(email);
					bean.setValid(true);
				}
				// if user does not exist set the isValid variable to false
				else if (!more) {
					bean.setValid(false);
				}
				//5. close connection
				con.close();	
			}catch(Exception e) {
				e.printStackTrace();		
			}
			return bean;
		}
		
		//get user by email
		public static User getUserByEmail(String email) {
			User us = new User();
			try {
				//call getConnection() method 
				con = ConnectionManager.getConnection();
				//3. create statement  
				ps=con.prepareStatement("select * from user where email=?");
				ps.setString(1, email);
				//4. execute query
				rs = ps.executeQuery();

				if (rs.next()) {	            
					us.setUserid(rs.getInt("userid"));
					us.setEmail(rs.getString("email"));				
					us.setPassword(rs.getString("password"));

				}
				//5. close connection
				con.close();
			}catch(Exception e) {
				e.printStackTrace();		
			}

			return us;
		}
		
		//get user by id
		public static User getUserById(int id) {
			User us = new User();
			try {
				//call getConnection() method 
				con = ConnectionManager.getConnection();
				//3. create statement  
				ps=con.prepareStatement("select * from user where userid=?");
				ps.setInt(1, id);
				//4. execute query
				rs = ps.executeQuery();

				if (rs.next()) {
					us.setUserid(rs.getInt("userid"));
					us.setEmail(rs.getString("email"));
					us.setPassword(rs.getString("password"));

				}
				//5. close connection
				con.close();
			}catch(Exception e) {
				e.printStackTrace();		
			}

			return us;
		}
		
		//get ALL users
		public static List<User> getUserId() {
			List<User> users = new ArrayList<User>();
			try {
				//call getConnection() method 
				con = ConnectionManager.getConnection();
				//3. create statement  
				stmt = con.createStatement();
				//4. execute query
				rs = stmt.executeQuery("select userid, email from user");

				while (rs.next()) {
					User us = new User();
					us.setUserid(rs.getInt("userid"));
					us.setEmail(rs.getString("email"));
					users.add(us);

				}
				//5. close connection
				con.close();
			}catch(Exception e) {
				e.printStackTrace();		
			}

			return users;
		}
}

package khairat.model;

public class User {

	private int userid;
	private String name;
	private String email;
	private String password;
	private String user_type;
	private boolean activeStatus;
	private boolean valid;
	private String activeStatusName;
	private int activeStatusNo;
	
	public int getActiveStatusNo() {
		return activeStatusNo;
	}

	public void setActiveStatusNo(int activeStatusNo) {
		this.activeStatusNo = activeStatusNo;
	}

	public String getActiveStatusName() {
		return activeStatusName;
	}

	public void setActiveStatusName(String activeStatusName) {
		this.activeStatusName = activeStatusName;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public User() {}
	
	
}

package khairat.model;

public class Kariah {
	private int userid;
	private String icNo;
	private String dob;
	private String address;
	private String phoneNo;
	private String maritalstat;
	private String gender;
	private String userDeathDate;
	private int mosqueId;
	private User user;
	private Mosque mosque;
	
	public Mosque getMosque() {
		return mosque;
	}

	public void setMosque(Mosque mosque) {
		this.mosque = mosque;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Kariah() {}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getIcNo() {
		return icNo;
	}

	public void setIcNo(String icNo) {
		this.icNo = icNo;
	}
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getMaritalstat() {
		return maritalstat;
	}

	public void setMaritalstat(String maritalstat) {
		this.maritalstat = maritalstat;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getUserDeathDate() {
		return userDeathDate;
	}

	public void setUserDeathDate(String userDeathDate) {
		this.userDeathDate = userDeathDate;
	}
	
	public int getMosqueId() {
		return mosqueId;
	}

	public void setMosqueId(int mosqueId) {
		this.mosqueId = mosqueId;
	}


}
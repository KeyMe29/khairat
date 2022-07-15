package khairat.model;

public class Kariah {
	private int userid;
	private String username;
	private int phoneno;
	private String maritalstat;
	private String gender;
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Kariah() {}
	
	public Kariah(int userid, String username, int phoneno, String maritalstat, String gender) {
		super();
		this.setUserid(userid);
		this.setUsername(username);
		this.setPhoneno(phoneno);
		this.setMaritalstat(maritalstat);
		this.setGender(gender);
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(int phoneno) {
		this.phoneno = phoneno;
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


}
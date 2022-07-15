package khairat.model;

public class Payment {
	private int pid;
	private int bid;
	private String method;
	private int userid;
	private User user;
	private Kariah kariah;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Payment() {}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public Kariah getKariah() {
		return kariah;
	}

	public void setKariah(Kariah kariah) {
		this.kariah = kariah;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

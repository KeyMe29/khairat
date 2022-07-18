package khairat.model;

import java.time.LocalDate;
import java.util.Date;

public class Payment {
	private int bid;
	private String method;
	private String refid;
	private int userid;
	private User user;
	private Kariah kariah;
	private String payStatus;
	private LocalDate payDate;

	public LocalDate getPayDate() {
		return payDate;
	}

	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Payment() {}

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
	
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}
}

package khairat.model;

public class Welfare {
	private int welfareid;
	private double welfareAmount;
	private String welfareDate;
	private String note;
	private int userid;
	private int adminid;
	
	public Welfare() {}

	public int getWelfareid() {
		return welfareid;
	}

	public void setWelfareid(int welfareid) {
		this.welfareid = welfareid;
	}

	public double getWelfareAmount() {
		return welfareAmount;
	}

	public void setWelfareAmount(double welfareAmount) {
		this.welfareAmount = welfareAmount;
	}
	
	public String getWelfareDate() {
		return welfareDate;
	}

	public void setWelfareDate(String welfareDate) {
		this.welfareDate = welfareDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getAdminid() {
		return adminid;
	}

	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
}
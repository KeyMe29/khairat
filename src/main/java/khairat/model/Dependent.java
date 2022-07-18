package khairat.model;

public class Dependent {
	private int depid;
	private String depName;
	private String depIcNo;
	private String depGender;
	private String depPhoneNo;
	private String depDeathDate;
	private String relation;
	private int userid;
	private Kariah kariah;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}	
	
	public Dependent () {}

	public int getDepid() {
		return depid;
	}

	public void setDepid(int depid) {
		this.depid = depid;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepIcNo() {
		return depIcNo;
	}

	public void setDepIcNo(String depIcNo) {
		this.depIcNo = depIcNo;
	}

	public String getDepGender() {
		return depGender;
	}

	public void setDepGender(String depGender) {
		this.depGender = depGender;
	}

	public String getDepPhoneNo() {
		return depPhoneNo;
	}

	public void setDepPhoneNo(String depPhoneNo) {
		this.depPhoneNo = depPhoneNo;
	}

	public String getDepDeathDate() {
		return depDeathDate;
	}

	public void setDepDeathDate(String depDeathDate) {
		this.depDeathDate = depDeathDate;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}	
	
	public Kariah getKariah() {
		return kariah;
	}

	public void setKariah(Kariah kariah) {
		this.kariah = kariah;
	}

}

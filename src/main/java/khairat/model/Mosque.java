package khairat.model;

public class Mosque {
	private int mosqueId;
	private String mosqueName;
	private String mosqueAddress;
	private int supervisorId;
	private Mosque superv;
	
	public Mosque getSuperv() {
		return superv;
	}

	public void setSuperv(Mosque superv) {
		this.superv = superv;
	}

	public Mosque() {}
	
	public int getMosqueId() {
		return mosqueId;
	}

	public void setMosqueId(int mosqueId) {
		this.mosqueId = mosqueId;
	}

	public String getMosqueName() {
		return mosqueName;
	}

	public void setMosqueName(String mosqueName) {
		this.mosqueName = mosqueName;
	}

	public String getMosqueAddress() {
		return mosqueAddress;
	}

	public void setMosqueAddress(String mosqueAddress) {
		this.mosqueAddress = mosqueAddress;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}
}

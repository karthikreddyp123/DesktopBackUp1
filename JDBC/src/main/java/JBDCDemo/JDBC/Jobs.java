package JBDCDemo.JDBC;

public class Jobs {
	int jobId;
	String jobTitle;
	String companyName;
	String location;
	public Jobs() {}
	public Jobs( String jobTitle, String companyName, String location) {
		this.jobTitle = jobTitle;
		this.companyName = companyName;
		this.location = location;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}

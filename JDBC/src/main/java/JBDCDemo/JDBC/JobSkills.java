package JBDCDemo.JDBC;

public class JobSkills {
	int ID;
	Jobs Job;
	String Skill;
	public JobSkills() {}
	public JobSkills( Jobs jobId,String Skill) {
		this.Job=jobId;
		this.Skill=Skill;
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public Jobs getJob() {
		return Job;
	}
	public void setJob(Jobs jobId) {
		this.Job = jobId;
	}
	public String getSkill() {
		return Skill;
	}
	public void setSkill(String Skill) {
		this.Skill = Skill;
	}
}

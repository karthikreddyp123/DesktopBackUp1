

import javax.persistence.*;

@Entity
@Table(name= "Jobs")
public class JobSkills {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int ID;
    @ManyToOne
    @JoinColumn(name="JobID")
    private Jobs Job;
    @Column(name = "Skill")
    private String Skill;
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

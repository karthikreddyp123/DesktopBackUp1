
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "Jobs")

public class Jobs {
    @Id
    @GeneratedValue
    @Column(name = "JobID")
    int jobId;
    @Column(name = "JobTitle")
    String jobTitle;
    @Column(name = "CompanyName")
    String companyName;
    @Column(name = "Location")
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

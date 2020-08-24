package com.epam.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name= "Jobs")
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JobID")
    private int jobId;
    @Column(name = "JobTitle")
    private String jobTitle;
    @Column(name = "CompanyName")
    private String companyName;
    @Column(name = "Location")
    private String location;
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "jobs")
    List<JobSkills> skillSet=new ArrayList<JobSkills>();
   
    
    public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public List<JobSkills> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(List<JobSkills> skillSet) {
    	skillSet.forEach(skill -> skill.setJobs(this));
    	this.skillSet=skillSet;
    	
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", skillSetList=" + skillSet +
                '}';
    }

   
    public Jobs() {}
    public Jobs( String jobTitle, String companyName, String location) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.location = location;
    }
    public int getJobId() {
        return jobId;
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

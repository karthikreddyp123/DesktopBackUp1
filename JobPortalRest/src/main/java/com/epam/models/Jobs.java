package com.epam.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name= "Jobs")
public class Jobs implements Serializable{
	private static final long serialVersionUID = 1L;
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
    private List<JobSkills> skillSet=new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="createdBy",referencedColumnName = "userId")
    private User user;
    @Column(name = "CreatedDate")
    private LocalDate createdDate;
    public Set<User> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(Set<User> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}
	@ManyToMany(mappedBy = "registeredJobs")
    private Set<User> registeredUsers=new HashSet<>();
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

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
		return "Jobs [jobId=" + jobId + ", jobTitle=" + jobTitle + ", companyName=" + companyName + ", location="
				+ location + "]";
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

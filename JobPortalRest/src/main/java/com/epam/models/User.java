package com.epam.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "userdetails")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userId;

	@Column(name="fname")
    private String fname;
    
    @Column(name="lname")
    private String lname;
    
    @Column(name="username")
    private String username;
    
    public List<Jobs> getJobs() {
		return jobs;
	}

	public void setJobs(List<Jobs> jobs) {
		this.jobs = jobs;
	}

	public Set<Jobs> getRegisteredJobs() {
		return registeredJobs;
	}

	public void setRegisteredJobs(Set<Jobs> registeredJobs) {
		this.registeredJobs = registeredJobs;
	}
	@Column(name="password")
    private String password;
    
    @Column(name="role")
    private int role;
    
    @OneToMany(mappedBy = "user")
    private List<Jobs> jobs;
    
    @ManyToMany
    @JoinTable(
    		  name = "user_registeredjobs", 
    		  joinColumns = @JoinColumn(name = "user_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "registed_jobs_id"))
    private Set<Jobs> registeredJobs=new HashSet<>();
    public User() {
	}
    
    public User(String fname, String lname, String username, String password, int role) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.role = role;
	}
   
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

	public void setLname(String lname) {
        this.lname = lname;
    }

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    } 
    @Override
	public String toString() {
		return "User [userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", username=" + username
				+ ", password=" + password + ", registeredJobs=" + registeredJobs
				+ "]";
	}
}
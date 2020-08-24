package com.epam.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name= "JobSkills")
public class JobSkills implements Serializable{
	private static final long serialVersionUID =1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int skillId;
    @Column(name = "Skill")
    private String skill;

    public Jobs getJobs() {
        return jobs;
    }

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }
    @ManyToOne(targetEntity = Jobs.class)
    @JoinColumn(name="JobID")
    private Jobs jobs;
    
    public JobSkills() {}
    public JobSkills(String skill) {
        this.skill =skill;
    }

    @Override
    public String toString() {
        return "JobSkills{" +
                "id=" + skillId +
                ", skill='" + skill + '\'' +
                '}';
    }

    
    public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
}

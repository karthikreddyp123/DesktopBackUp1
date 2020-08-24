package com.example.demo.Models;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	@Column(name = "fname")
	private String fname;
	@Column(name = "lname")
	private String lname;
	public int getId() {
		return Id;
	}
	public UserDetails() {
	}
	public UserDetails(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	public UserDetails(int id, String fname, String lname) {
		Id = id;
		this.fname = fname;
		this.lname = lname;
	}
	public void setId(int id) {
		Id = id;
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
	@Override
	public String toString() {
		return "UserDetails [Id=" + Id + ", fname=" + fname + ", lname=" + lname + "]";
	}
}

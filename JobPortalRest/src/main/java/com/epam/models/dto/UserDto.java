package com.epam.models.dto;

public class UserDto {

    private int userId;

    private String fname;
    
    private String lname;
    
    private String username;
    
    private String password;
    
    public UserDto(int userId, String fname, String lname, String username, int role) {
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.role = role;
	}

	private int role;

	public UserDto() {
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

    
}

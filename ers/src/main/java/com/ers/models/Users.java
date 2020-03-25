package com.ers.models;

import java.io.Serializable;
import java.util.Objects;

public class Users implements Serializable{

	private int userId;
	private String username;
	private String password;
	private String fName;
	private String lName;
	private String email;
	private Role role;
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int userId, String username, String password, String fName, String lName, String email,
			Role role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getType() {
		return role;
	}

	public void setType(Role type) {
		this.role = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, fName, lName, password, role, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Users)) {
			return false;
		}
		Users other = (Users) obj;
		return Objects.equals(email, other.email) && Objects.equals(fName, other.fName)
				&& Objects.equals(lName, other.lName) && Objects.equals(password, other.password) && role == other.role
				&& userId == other.userId && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", password=" + password + ", fName=" + fName
				+ ", lName=" + lName + ", email=" + email + ", role=" + role + "]";
	}
}

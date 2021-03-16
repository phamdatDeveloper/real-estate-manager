package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;

public class UserEntity extends BaseEntity {
	@Column(name = "username")
	private String userName;

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "password")
	private String passWord;
	
	@Column(name = "status")
	private String status;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

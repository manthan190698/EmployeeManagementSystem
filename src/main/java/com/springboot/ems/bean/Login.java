package com.springboot.ems.bean;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login_master")
public class Login {
	@Id
	private long userid;
	private String password;
	private String role;
	
	public long getUserid()
	{
		return userid;
	}
	public void setUserid(long userid)
	{
		this.userid = userid;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getRole()
	{
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}	
}
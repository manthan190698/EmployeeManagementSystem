package com.springboot.ems.bean;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee
{
	@Id
	private long empid;
	private String firstname;
	private String lastname;
	private Date dob;
	private String email;
	private long department_id;
	
	public long getEmpid()
	{
		return empid;
	}
	public void setEmpid(long empid)
	{
		this.empid = empid;
	}
	public String getFirstname()
	{
		return firstname;
	}
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	public String getLastname()
	{
		return lastname;
	}
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	public Date getDob()
	{
		return dob;
	}
	public void setDob(Date dob)
	{
		this.dob = dob;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public long getDepartment_id()
	{
		return department_id;
	}
	public void setDepartment_id(long department_id)
	{
		this.department_id = department_id;
	}
	/*public boolean isSameAs(Employee emp)
	{
		return this.empid==emp.getEmpid() && this.firstname==emp.getFirstname() && this.lastname==emp.getLastname() && this.dob==emp.getDob() && this.email==emp.getEmail() && this.department_id==emp.getDepartment_id(); 
	}*/
}

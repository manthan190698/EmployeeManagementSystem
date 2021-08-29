package com.springboot.ems.bean;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statusreport")
public class StatusReport
{
	private long complianceid;
	@Id
	private long statusrptid;
	private long empid;
	private String comments;
	private Date createdate;
	private long department_id;
	
	public long getComplianceid()
	{
		return complianceid;
	}
	public void setComplianceid(long complianceid)
	{
		this.complianceid = complianceid;
	}
	public long getStatusrptid()
	{
		return statusrptid;
	}
	public void setStatusrptid(long statusrptid)
	{
		this.statusrptid = statusrptid;
	}
	public long getEmpid()
	{
		return empid;
	}
	public void setEmpid(long empid)
	{
		this.empid = empid;
	}
	public String getComments()
	{
		return comments;
	}
	public void setComments(String comments)
	{
		this.comments = comments;
	}
	public Date getCreatedate()
	{
		return createdate;
	}
	public void setCreatedate(Date createdate)
	{
		this.createdate = createdate;
	}
	public long getDepartment_id()
	{
		return department_id;
	}
	public void setDepartment_id(long department_id)
	{
		this.department_id = department_id;
	}
}

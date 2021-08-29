package com.springboot.ems.bean;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "compliance")
public class Compliance
{
	@Id
	private long complianceid;
	private String rltype;
	private String details;
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
	public String getRltype()
	{
		return rltype;
	}
	public void setRltype(String rltype)
	{
		this.rltype = rltype;
	}
	public String getDetails()
	{
		return details;
	}
	public void setDetails(String details)
	{
		this.details = details;
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


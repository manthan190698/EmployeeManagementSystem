package com.springboot.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ems.bean.Department;
import com.springboot.ems.dao.DepartmentDao;



@Service
public class DepartmentService
{
	@Autowired
	DepartmentDao departmentDao;
	
	public String storeDepartmentDetails(Department dpt) throws Exception
	{
		return departmentDao.storeDepartmentDetails(dpt)?"Department details added successfully":"Unable to add department details";
	}
	public List<Department> getAllDepartmentsDetails() throws Exception
	{
		return departmentDao.getAllDepartmentsDetails();
	}
}

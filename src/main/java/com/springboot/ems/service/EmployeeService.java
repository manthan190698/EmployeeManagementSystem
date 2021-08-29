package com.springboot.ems.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ems.bean.Employee;
import com.springboot.ems.dao.EmployeeDao;


@Service
public class EmployeeService
{
	@Autowired
	EmployeeDao employeeDao;
	
	public String storeEmployeeDetails(Employee emp) throws Exception
	{
		return employeeDao.storeEmployeeDetails(emp)?"Employee details added successfully":"Unable to add employee details";
	}
	public List<Employee> getAllEmployeesDetails() throws Exception
	{
		return employeeDao.getAllEmployeesDetails();
	}
	public Employee getEmployeeDetails(long empid) throws Exception
	{
		return employeeDao.getEmployeeDetails(empid);
	}
	public String updateEmployeeDetails(Employee emp) throws Exception
	{
		return employeeDao.updateEmployeeDetails(emp)?"Employee details updated successfully":"Unable to update employee details";
	}
	public String deleteEmployeeDetails(long empid) throws Exception
	{
		return employeeDao.deleteEmployeeDetails(empid)?"Employee details deleted successfully":"Unable to delete employee details";
	}
}

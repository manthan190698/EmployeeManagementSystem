package com.springboot.ems.controller;

import java.sql.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.ems.bean.Department;
import com.springboot.ems.bean.Employee;
import com.springboot.ems.service.DepartmentService;
import com.springboot.ems.service.EmployeeService;

@Controller
public class EmployeeController
{
	@Autowired
	DepartmentService departmentService;
	@Autowired
	EmployeeService employeeService;
	
		
	@RequestMapping(value = "addEmployeeDetails", method = RequestMethod.GET)
	public ModelAndView addEmployeeDetailsPage(HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if(session.getAttribute("role")==null)
			{
				mav.setViewName("index");
				return mav;
			}
			if(!session.getAttribute("role").equals("admin"))
			{
				mav.setViewName("employeeHome");
				return mav;
			}
			session.setAttribute("allDepartmentsDetails", departmentService.getAllDepartmentsDetails());
			mav.setViewName("addEmployeeDetails");
		}
		catch(Exception e)
		{
//			PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			mav.setViewName("error");
		}
		return mav;
	}
	@RequestMapping(value = "storeEmployeeDetails", method = RequestMethod.POST)
	public ModelAndView addEmployeeDetails(HttpServletRequest req, HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			Employee emp = new Employee();
			emp.setFirstname(req.getParameter("firstname"));
			emp.setLastname(req.getParameter("lastname"));
			emp.setDob(Date.valueOf(req.getParameter("dob")));
			emp.setEmail(req.getParameter("email"));
			emp.setDepartment_id(Long.parseLong(req.getParameter("department_id")));
			mav.addObject("msg", employeeService.storeEmployeeDetails(emp));
			mav.setViewName("adminHome");
		}
		catch(Exception e)
		{
//			PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			mav.setViewName("error");
		}
		return mav;
	}
	@RequestMapping(value = "viewEmployeesDetails")
	public ModelAndView viewAllEmployeesDetails(HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if(session.getAttribute("role")==null)
			{
				mav.setViewName("index");
				return mav;
			}
			if(!session.getAttribute("role").equals("admin"))
			{
				mav.setViewName("employeeHome");
				return mav;
			}
			session.setAttribute("allEmployeesDetails", employeeService.getAllEmployeesDetails());
			HashMap<Long, String> hm = new HashMap<Long, String>();
			for(Department dpt : departmentService.getAllDepartmentsDetails())
			{
				hm.put(dpt.getDepartment_id(), dpt.getDepartment_nm());
			}
			session.setAttribute("allDepartmentsDetails", hm);
			mav.setViewName("listEmployees");
		}
		catch(Exception e)
		{
//			PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			mav.setViewName("error");
		}
		return mav;
	}
	@RequestMapping(value = "inputEmployeeId")
	public ModelAndView editEmployeeDetailsPage(HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if(session.getAttribute("role")==null)
			{
				mav.setViewName("index");
				return mav;
			}
			if(!session.getAttribute("role").equals("admin"))
			{
				mav.setViewName("employeeHome");
				return mav;
			}
			mav.setViewName("inputEmployeeId");
		}
		catch(Exception e)
		{
//			PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			mav.setViewName("error");
		}
		return mav;
	}
	@RequestMapping(value = "submitEmployeeId", method = RequestMethod.POST)
	public ModelAndView loadEmployeeDetailsInEditPage(HttpServletRequest req, HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			session.setAttribute("employeeDetails", employeeService.getEmployeeDetails(Long.parseLong(req.getParameter("empid"))));
			session.setAttribute("allDepartmentsDetails", departmentService.getAllDepartmentsDetails());
			mav.setViewName("editEmployeeDetails");
		}
		catch(Exception e)
		{
//			PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			mav.setViewName("error");
		}
		return mav;
	}
	@RequestMapping(value = "editEmployeeDetails", method = RequestMethod.POST)
	public ModelAndView editEmployeeDetails(HttpServletRequest req, HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			Employee emp = new Employee();
			emp.setEmpid(Long.parseLong(req.getParameter("empid")));
			emp.setFirstname(req.getParameter("firstname"));
			emp.setLastname(req.getParameter("lastname"));
			emp.setDob(Date.valueOf(req.getParameter("dob")));
			emp.setEmail(req.getParameter("email"));
			emp.setDepartment_id(Long.parseLong(req.getParameter("department_id")));
			mav.addObject("msg", employeeService.updateEmployeeDetails(emp));
			mav.setViewName("adminHome");
		}
		catch(Exception e)
		{
//			PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			mav.setViewName("error");
		}
		return mav;
	}
	@RequestMapping(value = "deleteEmployeeDetails")
	public ModelAndView deleteEmployeeDetailsPage(HttpServletRequest req, HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if(session.getAttribute("role")==null)
			{
				mav.setViewName("index");
				return mav;
			}
			if(!session.getAttribute("role").equals("admin"))
			{
				mav.setViewName("employeeHome");
				return mav;
			}
			mav.setViewName("deleteEmployeeDetails");
		}
		catch(Exception e)
		{
//			PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			mav.setViewName("error");
		}
		return mav;
	}
	@RequestMapping(value = "removeEmployeeDetails", method = RequestMethod.POST)
	public ModelAndView deleteEmployeeDetails(HttpServletRequest req, HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			mav.addObject("msg", employeeService.deleteEmployeeDetails(Long.parseLong(req.getParameter("empid"))));
			mav.setViewName("adminHome");
		}
		catch(Exception e)
		{
//			PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			mav.setViewName("error");
		}
		return mav;
	}
}

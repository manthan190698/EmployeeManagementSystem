package com.springboot.ems.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.ems.bean.Department;
import com.springboot.ems.service.DepartmentService;



@Controller
public class DepartmentController
{
	@Autowired
	DepartmentService departmentService;
	
	
	@RequestMapping(value = "createDepartment", method = RequestMethod.GET)
	public ModelAndView addDepartmentDetailsPage(HttpSession session)
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
			mav.setViewName("createDepartment");
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
	@RequestMapping(value = "addDepartmentDetails", method = RequestMethod.POST)
	public ModelAndView addDepartmentDetails(HttpServletRequest req, HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			Department dpt = new Department();
			dpt.setDepartment_nm(req.getParameter("department_nm"));
			mav.addObject("msg", departmentService.storeDepartmentDetails(dpt));
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
	@RequestMapping(value = "viewDepartmentsDetails", method = RequestMethod.GET)
	public ModelAndView viewAllDepartmentsDetails(HttpSession session)
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
			mav.setViewName("listDepartments");
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

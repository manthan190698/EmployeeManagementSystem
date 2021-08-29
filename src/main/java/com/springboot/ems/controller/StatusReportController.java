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
import com.springboot.ems.bean.StatusReport;
import com.springboot.ems.service.ComplianceService;
import com.springboot.ems.service.DepartmentService;
import com.springboot.ems.service.StatusReportService;

@Controller
public class StatusReportController
{
	@Autowired
	StatusReportService statusreportService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	ComplianceService complianceService;
	
	
	@RequestMapping(value = "viewStatusReportsDetails")
	public ModelAndView viewAllStatusReportsDetails(HttpSession session)
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
			session.setAttribute("allStatusReportsDetails", statusreportService.getAllStatusReportsDetails());
			HashMap<Long, String> hm = new HashMap<Long, String>();
			for(Department dpt : departmentService.getAllDepartmentsDetails())
			{
				hm.put(dpt.getDepartment_id(), dpt.getDepartment_nm());
			}
			session.setAttribute("allDepartmentsDetails", hm);
			mav.setViewName("listStatusReports");
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
	@RequestMapping(value = "submitComplianceComments", method = RequestMethod.POST)
	public ModelAndView submitStatusReport(HttpServletRequest req, HttpSession session)
	{
		/*Compliance c = new Compliance();
		c.setRltype(req.getParameter("rltype"));
		c.setDetails(req.getParameter("details"));
		c.setCreatedate(Date.valueOf(req.getParameter("createdate")));
		c.setDepartment_id(Long.parseLong(req.getParameter("department_id")));*/
		ModelAndView mav = new ModelAndView();
		try
		{
			StatusReport sr = new StatusReport();
			sr.setComplianceid(Long.parseLong(req.getParameter("complianceid")));
			sr.setEmpid((Long)session.getAttribute("userid"));
			sr.setComments(req.getParameter("comments"));
			sr.setDepartment_id(Long.parseLong(req.getParameter("department_id")));
			mav.addObject("msg", statusreportService.storeStatusReport(sr));
			mav.setViewName("employeeHome");
		}
		catch(Exception e)
		{
//			PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			mav.setViewName("error.jsp");
		}
		return mav;
	}
	@RequestMapping(value = "submitUpdatedComplianceComments", method = RequestMethod.POST)
	public ModelAndView updateStatusReport(HttpServletRequest req, HttpSession session)
	{
		/*Compliance c = new Compliance();
		c.setRltype(req.getParameter("rltype"));
		c.setDetails(req.getParameter("details"));
		c.setCreatedate(Date.valueOf(req.getParameter("createdate")));
		c.setDepartment_id(Long.parseLong(req.getParameter("department_id")));*/
		ModelAndView mav = new ModelAndView();
		try
		{
			StatusReport sr = new StatusReport();
			sr.setStatusrptid(Long.parseLong(req.getParameter("statusrptid")));
			sr.setComplianceid(Long.parseLong(req.getParameter("complianceid")));
			sr.setEmpid((Long)session.getAttribute("userid"));
			sr.setComments(req.getParameter("newcomments"));
			sr.setCreatedate(Date.valueOf(req.getParameter("submitdate")));
			sr.setDepartment_id(Long.parseLong(req.getParameter("department_id")));
			mav.addObject("msg", statusreportService.storeUpdatedStatusReport(sr));
			session.setAttribute("submittedCompliancesDetails", complianceService.getEmployeeCompliancesDetails((Long)session.getAttribute("userid")));
			mav.setViewName("listEmployeeStatusReports");
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

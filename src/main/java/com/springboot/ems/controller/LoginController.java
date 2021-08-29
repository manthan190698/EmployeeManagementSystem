package com.springboot.ems.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.ems.bean.Login;
import com.springboot.ems.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	//private static Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public ModelAndView openLoginPage()
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			mav.setViewName("login");
		}
		catch(Exception e)
		{
			//PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			System.out.println(e);
			mav.setViewName("error");
		}
		return mav;
	}
	@RequestMapping(value = "loginCheck",method = RequestMethod.POST)
	public ModelAndView loginRedirect(HttpServletRequest req, Login login, HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			login.setUserid(Long.parseLong(req.getParameter("userid")));
			login.setPassword(req.getParameter("password"));
			String employeeResult = loginService.checkEmployeeUser(login);
			System.out.println(employeeResult);
			if(employeeResult==null)
			{
				mav.addObject("msg", "Invalid username or password");
				mav.setViewName("index");
				System.out.println(mav);
				return mav;
			}
			login.setRole(employeeResult);
			session.setAttribute("userid", login.getUserid());
			session.setAttribute("role", login.getRole());
			mav.setViewName(login.getRole().equals("admin")?"adminHome":"employeeHome");
		}
		catch(Exception e)
		{
			//PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			System.out.println(e);
			mav.setViewName("error");
		}
		return mav;
	}
	@RequestMapping(value = "adminDashboard",method = RequestMethod.GET)
	public ModelAndView adminDashboard(HttpSession session)
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
			mav.setViewName("adminHome");
		}
		catch(Exception e)
		{
			//PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
//			logger = Logger.getLogger(getClass());
//			logger.error("Error occurred. Please check logs.", e);
			mav.setViewName("error");
		}
		return mav;
	}
	@RequestMapping(value = "employeeDashboard",method = RequestMethod.GET)
	public ModelAndView employeeDashboard(HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			if(session.getAttribute("role")==null)
			{
				mav.setViewName("index");
				return mav;
			}
			if(!session.getAttribute("role").equals("user"))
			{
				mav.setViewName("adminHome");
				return mav;
			}
			mav.setViewName("employeeHome");
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
	@RequestMapping(value = "logout",method = RequestMethod.GET)
	public ModelAndView logOut(HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		try
		{
			session.invalidate();
			mav.setViewName("index");
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

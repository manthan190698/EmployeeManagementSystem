package com.springboot.ems.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ems.bean.Login;
import com.springboot.ems.dao.LoginDao;


@Service
public class LoginService {
	@Autowired
	LoginDao loginDao;
	
	public String checkEmployeeUser(Login login) throws Exception
	{
		return loginDao.checkLoginDetails(login);
	}
}

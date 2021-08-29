package com.springboot.ems.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.ems.bean.Login;


@Repository
public class LoginDao
{
	@Autowired
	EntityManagerFactory emf;
	
	public static String getSHA512(String input) throws Exception
	{
		String toReturn = null;
		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return toReturn;
	}
	public String checkLoginDetails(Login ll) throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("select l from Login l where l.userid=:userid and l.password=:password");
		qry.setParameter("userid", ll.getUserid());
		qry.setParameter("password", getSHA512(getSHA512(ll.getPassword())));
		List<Login> list = qry.getResultList();
		return list.size()>0?list.get(0).getRole():null;
	}
	public boolean addLoginCredentials(Login ll) throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		ll.setPassword(getSHA512(getSHA512(ll.getPassword())));
		tran.begin();
		manager.persist(ll);
		tran.commit();
		return manager.find(Login.class, ll.getUserid())!=null;
	}
	public boolean deleteLoginCredentials(long userid) throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		Login ll = manager.find(Login.class, userid);
		if(ll.getRole().equals("admin"))
		{
			return false;
		}
		tran.begin();
//		manager.remove(manager.find(Login.class, userid));
		manager.remove(ll);
		tran.commit();
		return manager.find(Login.class, userid)==null;
	}
}
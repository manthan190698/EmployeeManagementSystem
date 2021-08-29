package com.springboot.ems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.ems.bean.Department;


@Repository
public class DepartmentDao
{
	@Autowired
	EntityManagerFactory emf;
	
	public boolean storeDepartmentDetails(Department dpt) throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		tran.begin();
		manager.persist(dpt);
		tran.commit();
		return manager.find(Department.class, dpt.getDepartment_id())!=null;
	}
	public List<Department> getAllDepartmentsDetails() throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("select d from Department d");
		return qry.getResultList();
	}
}

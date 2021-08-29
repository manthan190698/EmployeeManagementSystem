package com.springboot.ems.dao;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.ems.bean.StatusReport;

@Repository
public class StatusReportDao
{
	@Autowired
	EntityManagerFactory emf;

	public boolean storeStatusReport(StatusReport sr) throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		sr.setCreatedate(Date.valueOf(LocalDate.now()));
		tran.begin();
		manager.persist(sr);
		tran.commit();
		return manager.find(StatusReport.class, sr.getStatusrptid())!=null;
	}
	public List<StatusReport> getAllStatusReportsDetails() throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("select sr from StatusReport sr");
		return qry.getResultList();
	}
	public List<StatusReport> getEmployeeStatusReportsDetails(long empid) throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("select sr from StatusReport sr where sr.empid=:empid");
		qry.setParameter("empid", empid);
		return qry.getResultList();
	}
	public boolean storeUpdatedStatusReport(StatusReport sr) throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		tran.begin();
		manager.merge(sr);
		tran.commit();
		return manager.find(StatusReport.class, sr.getStatusrptid())!=null;
	}
}
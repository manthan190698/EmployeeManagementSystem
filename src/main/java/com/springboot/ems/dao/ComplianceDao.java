package com.springboot.ems.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.ems.bean.Compliance;
import com.springboot.ems.bean.Employee;
import com.springboot.ems.bean.StatusReport;



@Repository
public class ComplianceDao
{
	@Autowired
	EntityManagerFactory emf;
	@Autowired
	StatusReportDao statusreportDao;
	
	public boolean storeComplianceInformation(Compliance c) throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		tran.begin();
		manager.persist(c);
		tran.commit();
		return manager.find(Compliance.class, c.getComplianceid())!=null;
	}
	public List<Compliance> getAllCompliancesDetails() throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("select c from Compliance c");
		return qry.getResultList();
	
	}
	public List<Compliance> getEmployeeRemainingCompliancesDetails(long empid) throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("select c from Compliance c where c.department_id=:department_id");
		qry.setParameter("department_id", manager.find(Employee.class, empid).getDepartment_id());
		List<Compliance> remainingCompliancesDetails = new ArrayList<Compliance>();
		for(Compliance c: (List<Compliance>)qry.getResultList())
		{
			if(manager.createQuery("select sr from StatusReport sr where sr.empid=:empid and sr.complianceid=:complianceid").setParameter("empid", empid).setParameter("complianceid", c.getComplianceid()).getResultList().size()==0)
			{
				remainingCompliancesDetails.add(c);
			}
		}
		return remainingCompliancesDetails;
	}
	public HashMap<StatusReport, Compliance> getEmployeeCompliancesDetails(long empid) throws Exception
	{
		EntityManager manager = emf.createEntityManager();
		List<StatusReport> employeeStatusReports = statusreportDao.getEmployeeStatusReportsDetails(empid);
		HashMap<StatusReport, Compliance> submittedCompliances = new HashMap<StatusReport, Compliance>();
		for(StatusReport sr: employeeStatusReports)
		{
			submittedCompliances.put(sr, manager.find(Compliance.class, sr.getComplianceid()));
		}
		return submittedCompliances;
	}
}

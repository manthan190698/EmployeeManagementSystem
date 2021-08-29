package com.springboot.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ems.bean.StatusReport;
import com.springboot.ems.dao.StatusReportDao;


@Service
public class StatusReportService
{
	@Autowired
	StatusReportDao statusreportDao;
	
	public String storeStatusReport(StatusReport sr) throws Exception
	{
		return statusreportDao.storeStatusReport(sr)?"Comments submitted successfully":"Unable to submit comments";
	}
	public List<StatusReport> getAllStatusReportsDetails() throws Exception
	{
		return statusreportDao.getAllStatusReportsDetails();
	}
	public String storeUpdatedStatusReport(StatusReport sr) throws Exception
	{
		return statusreportDao.storeUpdatedStatusReport(sr)?"Comments updated successfully":"Unable to update comments";
	}
}

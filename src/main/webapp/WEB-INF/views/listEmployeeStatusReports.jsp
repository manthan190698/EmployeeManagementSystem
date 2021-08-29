<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("userid")==null || session.getAttribute("role")==null)
{
	response.sendRedirect(request.getContextPath() + "/index.jsp");
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Compliances</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<script>
			//if ( window.history.replaceState )
			if(<%request.getSession().getAttribute("role");%> == null)
			{
				//window.history.replaceState( null, null, window.location.href );
				window.history.replaceState( null, null, "${pageContext.request.contextPath}/index.jsp" );
			}
		</script>
		<style>
			table
			{
				width:100%;
			}
			table, th, td
			{
				border: 1px solid black;
				border-collapse: collapse;
			}
			th, td
			{
				padding: 15px;
				text-align: left;
			}
			#t01 tr:nth-child(even)
			{
				background-color: #eee;
			}
			#t01 tr:nth-child(odd)
			{
				background-color: #fff;
			}
			#t01 th
			{
				background-color: black;
				color: white;
			}
		</style>
	</head>
	<body>
		<h2 style="text-align: center; background:black; padding:20px;color:white">Employee Management System</h2>

		<a class="nav-link" href="adminDashboard">  Return to Dashhboard</a>
		<div class="container">
			
			<h2>View/Update Submitted Compliances</h2>
			<table id="t01">
				<tr>
					<th>Status Report ID</th>
					<th>Compliance ID</th>
					<th>RL Type</th>
					<th>Details</th>
					<th>Date Created</th>
					<th>Date Submitted</th>
					<th>Your Comments</th>
					<th>Update Comments</th>
				</tr>
				<core:forEach items="${sessionScope.submittedCompliancesDetails}" var="compliancedetails">
					<tr>
						<td>${compliancedetails.key.statusrptid}</td>
						<td>${compliancedetails.key.complianceid}</td>
						<td>${compliancedetails.value.rltype}</td>
						<td>${compliancedetails.value.details}</td>
						<td>${compliancedetails.value.createdate}</td>
						<td>${compliancedetails.key.createdate}</td>
						<td>${compliancedetails.key.comments}</td>
						<td>
							<form action="updateComplianceComments" method="post">
								<input type="hidden" name="statusrptid" value="${compliancedetails.key.statusrptid}"/>
								<input type="hidden" name="complianceid" value="${compliancedetails.key.complianceid}"/>
								<input type="hidden" name="rltype" value="${compliancedetails.value.rltype}"/>
								<input type="hidden" name="details" value="${compliancedetails.value.details}"/>
								<input type="hidden" name="createdate" value="${compliancedetails.value.createdate}"/>
								<input type="hidden" name="submitdate" value="${compliancedetails.key.createdate}"/>
								<input type="hidden" name="comments" value="${compliancedetails.key.comments}"/>
								<input type="hidden" name="department_id" value="${compliancedetails.key.department_id}"/>
								<input type="submit" class="btn btn-primary " value="Update comments"/>
							</form>
						</td>
					</tr>
				</core:forEach>
			</table>
		</div>
		<div style="position: relative; top: 25px">
			<span style="color: red">${requestScope.msg}</span>
		</div>
	</body>
</html>
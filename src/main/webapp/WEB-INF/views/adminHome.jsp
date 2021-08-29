<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<title>Employee Management System - Admin Home</title></br>
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
	</head>
	<body>
		<h2 style="text-align: center; background:black; padding:20px;color:white">Employee Management System</h2>

		<div class="inline-block" style="border-style: solid;">
			<h2 style="display: inline-block; text-align: left; margin : 15px;">Admin Console</h2>
			<a class="btn btn-primary" href="logout" style="display: inline-block; position: relative; float:right; margin: 15px;">Logout</a>
		</div></br>
		<div class="container" style="border-style: solid;">
		<h3 style="position: relative; left: 10px; top: 20px;text-align: center;">Departments</h3>
		<ul class="nav" style="position: relative; left: 10px; top: 10px;">
			<li class="nav-item" style="margin:10px;text-align: center">
				<a class="nav-link" href="createDepartment">Create Department</a>
			</li>
			<li class="nav-item" style="margin:10px;text-align: center">
				<a class="nav-link" href="viewDepartmentsDetails">List Departments</a>
			</li>
		</ul>
		<h3 style="position: relative; left: 10px; top: 20px;text-align: center">Employees</h3>
		<ul class="nav" style="position: relative; left: 10px; top: 10px;">
			<li class="nav-item" style="margin:10px">
				<a class="nav-link" href="addEmployeeDetails">Add Employee Information</a>
			</li>
			<li class="nav-item" style="margin:10px">
				<a class="nav-link" href="viewEmployeesDetails">List Employees</a>
			</li>
			<li class="nav-item" style="margin:10px">
				<a class="nav-link" href="inputEmployeeId">Edit Employee Details</a>
			</li>
			<li class="nav-item" style="margin:10px">
				<a class="nav-link" href="deleteEmployeeDetails">Delete Employee Details</a>
			</li>
		</ul>
		<h3 style="position: relative; left: 10px; top: 20px;text-align: center">Regulations</h3>
		<ul class="nav" style="position: relative; left: 10px; top: 10px;">
			<li class="nav-item" style="margin:10px">
				<a class="nav-link" href="createCompliance">Create Regulation</a>
			</li>
			<li class="nav-item" style="margin:10px">
				<a class="nav-link" href="viewCompliancesDetails">List Regulations</a>
			</li>
		</ul>
		<h3 style="position: relative; left: 10px; top: 20px;text-align: center">Compliance Tracking</h3>
		<ul class="nav" style="position: relative; left: 10px; top: 10px;">
			<!-- <li class="nav-item">
				<a class="nav-link" href="createStatusReport">Create Status Report</a>
			</li> -->
			<li class="nav-item" style="margin:10px">
				<a class="nav-link" href="viewStatusReportsDetails">View Status Reports</a>
			</li>
		</ul>
		<div style="position: relative; top: 25px; text-align:cente">
			<span style="color: red">${requestScope.msg}</span>
		</div>
	</div>
	</body>
</html>
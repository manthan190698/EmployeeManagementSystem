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
		<title>Edit Employee Details</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link href="styles.css" rel="stylesheet" type="text/css">
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
		<!-- <h2>Add Employee Details<h2> -->
			<h2 style="text-align: center; background:black; padding:20px;color:white">Employee Management System</h2>
			
			<a class="nav-link" href="adminDashboard">  Return to Dashhboard</a>
		<div class="container" style="border: 2px solid black; padding: 30px; margin: 30px;">
			<form action="editEmployeeDetails" style="position: relative; top: 20px;" method="post">
				<div class="form-group" style="margin: 10px;">
					<label for="empid">Employee ID</label>
					<input name="empid" type="text" class="form-control" id="empid" value=${employeeDetails.empid} maxlength="10" readonly>
				</div>
				<!-- <div class="form-group" style="position: relative; top: 0px"> -->
				<div class="form-group" style="margin: 10px;">
					<label for="firstname">First Name</label>
					<input name="firstname" type="text" class="form-control" id="firstname" value=${employeeDetails.firstname} maxlength="45" required>
				</div>
				<div class="form-group" style="margin: 10px;">
					<label for="lastname">Last Name</label>
					<input name="lastname" type="text" class="form-control" id="lastname" value=${employeeDetails.lastname} maxlength="45" required>
				</div>
				<div class="form-group" style="margin: 10px;">
					<label for="dob">Date of Birth</label>
					<input name="dob" type="date" class="form-control" id="dob" value=${employeeDetails.dob} required>
				</div>
				<div class="form-group" style="margin: 10px;">
					<label for="email">Email id</label>
					<input name="email" type="email" class="form-control" id="email" value=${employeeDetails.email} maxlength="100">
				</div>
				<div class="form-group" style="margin: 10px;">
					<label for="department_id">Department ID</label>
					<select name="department_id" class="form-control" id="department_id" required>
						<core:forEach items="${sessionScope.allDepartmentsDetails}" var="department">
							<core:if test="${employeeDetails.department_id == department.department_id}">
								<option value=${department.department_id} selected>${department.department_nm}</option>
								<br />
							</core:if>
							<core:if test="${employeeDetails.department_id != department.department_id}">
								<option value=${department.department_id}>${department.department_nm}</option>
								<br />
							</core:if>
						</core:forEach>
					</select>
					<!-- <input name="department_id" type="text" class="form-control" id="department_id" required> -->
				</div>
				<div style="position: relative; top: 10px; margin: 10px;">
					<input type="submit" class="btn btn-primary " value="Submit"/>
				</div>
			</form>
		</div>
	</body>
</html>
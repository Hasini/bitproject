<!DOCTYPE html>
<%@include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
	<head>
		<title>Home Page</title>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/commonFunctions.js"></script>
		
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<link rel="icon" href="images/favic.jpg">
		
		<style type="text/css">
			.link {
				background-color: #0000ff;
				border: none;
				color: white;
				padding: 5px;
				text-align: center;
				text-decoration: none;
				display: inline-block;
				font-size: 12px;
				margin: 2px 2px;
				height: 30%;
				width: 60%;
				border-radius: 12px;
			}
		</style>
	</head>
<body >
	<div id="main" style=" padding-bottom: 20%;">
	<p style="color: white; background-color: green;float:inherit;font-size:large;font-size: 20px; border-left-width: 5%; border-right-width: 20% ; margin-left: 10%; margin-right: 10%;height: 15%">
 		<c:out value="${param.message}"/>
 	</p>
		<h2>Admin Module</h2>
		
		<!-- DIL -->
		 <%
      	if (session != null) {
	         if (session.getAttribute("user") != null) {
	            String name = (String) session.getAttribute("user");
	            out.print("Hello, " + name + "  Welcome to ur Profile");
	         } else {
	           //  response.sendRedirect("login.jsp");
	         }
    	  }
   		%> 
		<div id="admin" style="float: left">
			<a href="user-type.jsp" class="link">User Type</a><br>
			<a href="branch-details.jsp" class="link">Branch Details</a><br>
			<a href="credit-limitation.jsp" class="link">Credit Limit Definition</a><br>
			<a href="expense-type.jsp" class="link">Expense Type</a><br>
			<a href="incomeType.jsp" class="link">Income Type</a><br>
			<a href="delete&updateUser.jsp" class="link">System User Maintain</a><br>
			<!-- <a href="accessLevels.jsp" class="link">Access Levels</a><br> -->
			<a href="day-end.jsp" class="link">Day End</a><br>
			<a href="Reports.jsp" class="link">Report Generation</a><br>
		</div>
	</div>
</body>
</html>
<%@include file="footer.jsp" %>
<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
	<head>
		<title>Home Page</title>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/commonFunctions.js"></script>
		
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<link rel="icon" href="images/favic.jpg">
	</head>
<body>


<div id="main">
	<div>
	<h2>Admin Module</h2>
	</div>
	
	<div id="admin" style="float: left">
		<a href="user-type.jsp" class="link">User Type</a><br>
		<a href="branch-details.jsp" class="link">Branch Details</a><br>
		<a href="expense-type.jsp" class="link">Expense Type</a><br>
		<a href="incomeType.jsp" class="link">Income Type</a><br>
		<a href="delete&updateUser.jsp" class="link">System User Maintain</a><br>
		<a href="accessLevels.jsp" class="link">Access Levels</a><br>
		<a href="day-end.jsp" class="link">Day End</a><br>
		<a href="AllUsers.jsp" class="link">List All Users</a><br>
		
	</div>
	
	
	
	<div id="right" style="float: right">
		
		<button type="submit" value="Sign Out" id="signout" onclick="window.location.href='login.jsp'">Sign Out</button>
	</div>
</div>

</body>
</html>
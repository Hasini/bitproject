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
		<h2>Admin Module</h2>
		<div id="admin" style="float: left">
			<a href="user-type.jsp" class="link">User Type</a><br>
			<a href="branch-details.jsp" class="link">Branch Details</a><br>
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
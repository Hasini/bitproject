<!DOCTYPE html>
<html>
	<head>
		<title>Home Page</title>
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<link rel="icon" href="images/favicon-facebook_400x400.png">
		

	</head>
<body>


<div id="main">
	<div>
	<h2>Welcome to JSL</h2>
	</div>
	<div id="left" style="float: left">
		<h3>Admin Module</h3>
		<a href="user-type.jsp" class="link">User Type</a><br>
		<a href="branch-details.jsp" class="link">Branch Details</a><br>
		<a href="expense-type.jsp" class="link">Expense Type</a><br>
		<a href="incomeType.jsp" class="link">Income Type</a><br>
		<a href="customer-registration.jsp" class="link">Customer Registration</a><br>
		<a href="delete&updateUser.jsp" class="link">System User Maintain</a><br>
		<a href="accessControl.jsp" class="link">Access Control</a><br>
		<a href="day-end.jsp" class="link">Day End</a><br>
		<a href="AllUsers.jsp" class="link">List All Users</a><br>
		
		<h3>Finance Module</h3>
		<a href="income.jsp" class="link">Income</a><br>
		<a href="expences.jsp" class="link">Expences</a>
		
		<h3>Lending Book</h3>
		<a href="customer-lending-details.jsp" class="link">Lending Book</a><br>
		<a href="lend-details-view.jsp" class="link">View</a>
		
		<h3>View</h3>
	</div>
	
	<div id="right" style="float: right">
		
		<button type="submit" value="Sign Out" id="signout" onclick="window.location.href='login.jsp'">Sign Out</button>
	</div>
</div>

</body>
</html>
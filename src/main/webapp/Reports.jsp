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
<body style="width: 80%; height: 70%; margin-left: 0.5%;">
	<div id="main">
		<div>
		<h2>Reports Generation</h2>
		</div>
		
		<div id="admin" style="float: left">
			<a href="AllUsers.jsp" class="link">All Users</a><br>
			<a href="blacklisted_customers.jsp" class="link">Blacklisted Customer Details</a><br>
			<a href="customerArrears.jsp" class="link">Customer Arrears</a><br>
			<a href="branchIncome.jsp" class="link">Branch Income</a><br>
			<a href="branchExpense.jsp" class="link">Branch Expense</a><br>
			<a href="totalncome.jsp" class="link">Total Income</a><br>
			<a href="totaExpense.jsp" class="link">Total Expenses</a><br>
		</div>
		
	</div>

</body>
<footer style="width: 80%; height: 5%; border-right: 20%; background-image: url(images/background.jpg); color:white; margin-left: 10%">KGM</footer>
</html>
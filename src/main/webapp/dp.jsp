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
<body style="width: 80%; height: 70%; margin-left: 0.5%;">

	<div id="main" style="width: 70%; height: 70%; margin-left: 10%">
		<div>
		<h2>DP Module</h2>
		</div>
		<div id="db" style="float: left">
			<a href="customer-registration.jsp" class="link">Customer Registration</a><br>
			<a href="cbnew.jsp" class="link">Cash Book</a><br>
			<a href="lending_shedule.jsp" class="link">Lending Schedule</a><br>
			<a href="lend-details-view.jsp" class="link">View</a>
		</div>
		
	</div>

</body>

</html>
<%@include file="footer.jsp" %>
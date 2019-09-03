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
		
		<script type="text/javascript">
		$(document).ready(function(){
			$.ajax({
					type : 'post',
					url : 'userservlet',
					data : {
						method : "login"
					},
					success : function(responseText) {
						if (responseText.success){
							alert(responseText.usertypeid);
						}
					}   
			});
	});
	</script>
	</head>
<body style="width: 80%; height: 70%; margin-left: 0.5%;">
	<div id="main" style="width: 70%; height: 70%; margin-left: 10%">
		<div>
		<h2>Cashier Module</h2>
		</div>
		
		<div id="cas" style="float: left">
			<a href="income.jsp" class="link">Income</a><br>
			<a href="expences.jsp" class="link">Expenses</a><br>
			<!--<a href="billInfo.jsp" class="link">Bill Info</a><br>  -->
		</div>
	
	</div>

</body>
<footer style="width: 80%; height: 5%; border-right: 20%; background-image: url(images/background.jpg); color:white; margin-left: 10%">KGM</footer>
</html>
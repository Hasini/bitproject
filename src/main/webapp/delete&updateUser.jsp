<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
	<head>
		<title>Home Page</title>
		<script src="js/jquery.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
		
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<link rel="icon" href="images/favicon-facebook_400x400.png">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet" href="/resources/demos/style.css">
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#delete").click(function(){
				var email=document.getElementById("email").value;
				var r = confirm("Are you sure to inactivate the user ?");
				var method;
				if(email=="" || email == null){
					alert("Values are Empty");
					document.getElementById("email").focus();
				}else {
					if (r==true){
						$.ajax({
							type : 'post',
							url : 'userservlet',
							data : {
								username : email,
								method : "deleteUser",
							},
							success: function (responseText) {
								if (responseText.error){
									alert (responseText.error);
									$('input[type="text"]').val('');
								}else {
									alert (responseText.success);
									$('input[type="text"]').val('');
								}
							}
						});
					} else {
						window.location.assign('/bitproject/admin.jsp');
					}
				}
			});
			$("#update").click(function(){
				var username=document.getElementById("username").value;
				var password=document.getElementById("password").value;
				var method;
				if(username=="" || username == null || password == "" || password == null){
					alert("Values are Empty");
					document.getElementById("username").focus();
				}else {
					$.ajax({
						type : 'post',
						url : 'userservlet',
						data : {
							username : username,
							password : password,
							method : "passwordchange"
						},
						success: function (responseText) {
							if (responseText.error){
								alert (responseText.error);
								$('input[type="text"]').val('');
							}else {
								alert (responseText.success);
								$('input[type="text"]').val('');
								loginFunction();
							}
						}
					});
				}
				});
			
			$(function() {
				var icons = {
					header : "ui-icon-circle-arrow-e",
					activeHeader : "ui-icon-circle-arrow-s"
				};
				$("#accordion").accordion({
					icons : icons
				});
				$("#toggle").button().on("click", function() {
					if ($("#accordion").accordion("option", "icons")) {
						$("#accordion").accordion("option", "icons", null);
					} else {
						$("#accordion").accordion("option", "icons", icons);
					}
				});
				});
			});
	
	
	function loginFunction() {
		var r = confirm("Password Changed..! Need to proceed");
	    if (r == true) {
	    	window.location.assign('/bitproject/delete&updateUser.jsp');
	    } else {
	    	window.location.assign('/bitproject/login.jsp');
	    }
	}
	

</script>

	</head>
<body style="width: 80%; height: 90%; margin-left: 0.5%;">
	<div id="accordion" style="width: 70%; height: 90%; margin-left: 10%">
		
		<h3>Recover Password</h3>
		<div id ="forgetpassword">
			<label> User Name :</label> <input type="text" id="username" name="username"/><br>
			<label> New Password :</label> <input type="text" id="password" name="password"/><br>
			<button class="submit" type="submit" name="update" id="update" style="height: 18%; width: 20%">Change Password</button>
		</div>
		<h3>Inactivate User</h3>
		<div class="container">
	  		<input type="text" placeholder="Enter User Name" name="email" id="email" required><br>
	    	<button class="submit" type="submit" name="delete" id="delete" style="height: 18%; width: 20%; background-color: maroon;">Inactivate</button>
  		</div>
	</div>
</body>


</html>
<%@include file="footer.jsp" %>
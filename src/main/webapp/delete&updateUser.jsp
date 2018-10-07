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
		



<script type="text/javascript">
	
	
	$(document).ready(function(){
		
		$("#delete").click(function(){
			var username=document.getElementById("email").value;
			var method;
				$.ajax({
					type : 'post',
					url : 'userservlet',
					data : {
						username : username,
						method : "deleteUser",
					},
					success: function (responseText) {
						if (responseText.error){
							alert (responseText.error);
						}else {
							alert (responseText.success);
						}
							
					}
				});
			
		});
		
		
		$("#update").click(function(){
				var username=document.getElementById("username").value;
				var password=document.getElementById("password").value;
				var method;
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
								
							}else {
								alert (responseText.success);
								loginFunction();
							}
								
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

<style type="text/css">
	.button {
	    background-color: blue; /* Green */
	    border: none;
	    color: white;
	    padding: 10px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 14px;
	    margin: 4px 2px;
	    cursor: pointer;
	}
	.button4{
		border-radius: 12px;
	}
</style>
	</head>
<body>


<body>
<div id="main" style="float:left">
<fieldset>
	<legend>Recover Password</legend>
	<div id ="forgetpassword">
		<label> Username :</label> <input type="text" id="username" name="username"/><br>
		<label> New Password :</label> <input type="text" id="password" name="password"/>
		<button class="button button4" type="submit" name="update" id="update">Change password</button>
		
	</div>
</fieldset>
	<div id ="etpassword" style="height: 60px;">
		
		
	</div>
	
<fieldset>
	<legend>Delete User</legend>
	<div class="container">
  		<input type="text" placeholder="Enter Email" name="email" id="email" required>
    	<button class="button button4" type="submit" name="delete" id="delete">Delete</button>
  	</div>

</fieldset>

  
</div>
</body>
</html>
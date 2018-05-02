<!DOCTYPE html>
<html>
<head>


<title>Home Page</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/favicon-facebook_400x400.png">




<style>

/* body {font-family: Arial;}
* {box-sizing: border-box}

form {
	border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 50%;
} */
#signup {
	background-color: blue;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 50%;
}

button:hover {
	opacity: 0.8;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

img.avatar {
	width: 40%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

#usertype{
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}
</style>

<script type="text/javascript">
	
	
	$(document).ready(function(){
		$("#submit").click(function(){
			
			var username=document.getElementById("uname").value;
			var password=document.getElementById("psw").value;
			var method;
			
			
				$.ajax({
					type : 'post',
					url : 'userservlet',
					data : {
						username : username,
						password : password,
						method : "login",
						usertypeid : 1
					},
					success: function (responseText) {
						if (responseText.error){
							alert(responseText.error);
						}
						else{
							alert(responseText.success);	
						}
					}
				});
			
		});
	});

</script>


</head>

<body >


	<div id="main" style="float:left">
		<div>
			<h2>Welcome to JSL</h2>
		</div>

		<div>
			
				<div class="imgcontainer">
					<img src="images/login.jpg" alt="Avatar" class="avatar">
				</div>

				<div class="container">
					<label><b>Username</b></label> 
					<input type="text" placeholder="Enter Username" name="uname" id="uname" required="required"> 
					<label><b>Password</b></label>
					<input type="password" placeholder="Enter Password" name="psw" id="psw" required="required"> 
					<label><b>User Type </b> </label>
					<select id="usertype" name="usertype" onchange="">
						<option value="0">Select</option>
						<option value="1">Admin</option>
					</select>

					<button type="submit" class ="submit" id="submit">Login</button>
					<label> <input type="checkbox" checked="checked">
						Remember me
					</label>
					

				</div>
		</div>
		
	</div>
	<div style="float: right">
			<p>Register a new system user</p>
			<button type="submit" id="signup" value="signup"
						onclick="window.location.href='sign-up.jsp'">Register</button>
	</div>

</body>
</html>






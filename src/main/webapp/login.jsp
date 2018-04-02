<!DOCTYPE html>
<html>
<head>


<title>Home Page</title>
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
</style>


</head>

<body onload="getProduct()">


	<div id="main" style="float:left">
		<div>
			<h2>Welcome to JSL</h2>
		</div>

		<div>
			<form action="login" method="post">
				<div class="imgcontainer">
					<img src="images/login.jpg" alt="Avatar" class="avatar">
				</div>

				<div class="container">
					<label><b>Username</b></label> <input type="text"
						placeholder="Enter Username" name="uname" required> <label><b>Password</b></label>
					<input type="password" placeholder="Enter Password" name="psw"
						required> <select id="usertype" name="usertype"
						onchange="" style="width: 150px">
						<option value="0">Select</option>
					</select>

					<button type="submit">Login</button>
					<label> <input type="checkbox" checked="checked">
						Remember me
					</label>
					

				</div>
			</form>

		</div>
		
	</div>
	<div style="float: right">
			<p>Register a new system user</p>
			<button type="submit" id="signup" value="signup"
						onclick="window.location.href='sign-up.jsp'">Register</button>
		</div>

</body>
</html>






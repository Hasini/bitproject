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

button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 50%;
}

input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
}

hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
}


button:hover {
    opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
    padding: 14px 20px;
    background-color: #f44336;
}

/* Add padding to container elements */
.container {
    padding: 16px;
}

/* Clear floats */
.clearfix::after {
    content: "";
    clear: both;
    display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
    .cancelbtn, .signupbtn {
       width: 100%;
    }
}
</style>


<script type="text/javascript">
	
	
	$(document).ready(function(){
		$("#submit").click(function(){
			
			var username=document.getElementById("email").value;
			var password=document.getElementById("psw").value;
			var repeatpassword=document.getElementById("pswr").value;
			var method;
			
			if (password != repeatpassword){
				alert ("Password mismatched");
			}else {
				$.ajax({
					type : 'post',
					url : 'userservlet',
					data : {
						username : username,
						password : password,
						method : "signup",
						usertypeid : 1
					},
					success: function (responseText) {
						if (responseText.error){
							alert (responseText.error);
						}else {
							alert (responseText.success);
						}
							
					}
				});
			}
		});
	});

</script>
	</head>
<body>


<body>


  <div class="container">
    <h1>Sign Up</h1>
    <hr>

    <label><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" id="email" required>

    <label><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" id="psw"required>

    <label><b>Repeat Password</b></label>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="pswr" required>
    
    <label><b>User Type </b> </label>
		<select id="usertype" name="usertype" onchange="">
			<option value="0">Select</option>
			<option value="1">Admin</option>
		</select>
    
    <label>
      <input type="checkbox" checked="checked" style="margin-bottom:15px"> Remember me
    </label>
    
   <div class="clearfix">
      <button type="button" class="cancelbtn" onclick="window.location.href='index.html'">Cancel</button>
      <button type="submit" class="submit" id ="submit">Sign Up</button>
    </div>
  </div>

</body>
</html>
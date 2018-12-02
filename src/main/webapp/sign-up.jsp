<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
	<head>
		<title>User Registration</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/commonFunctions.js"></script>
		
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<link rel="icon" href="images/favic.jpg">
		
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
		
		 $(window).load(function(){
			 var optionsAsString;
			 	$.ajax({
					type : 'post',
					url : 'userservlet',
					data : {
						method : "view"
					},
					success : function(responseText) {
						for(var i=0;i<responseText.usertypeobj.length;i++){
							var idvalue = responseText.usertypeobj[i]["id"];
							var usertypedescr = "<option value="+idvalue+">"+responseText.usertypeobj[i]["descr"]+"</option>";
							$("#usertype").append (usertypedescr);
						 }
						
					}   
					
				}); 
		    });
		$("#submit").click(function(){
			
			var username=document.getElementById("email").value;
			var password=document.getElementById("psw").value;
			var repeatpassword=document.getElementById("pswr").value;
			var method;
			var e = document.getElementById("usertype");
			var userType = e.options[e.selectedIndex].value;
			
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
						usertypeid : userType
					},
					success: function (responseText) {
						
						if (responseText.successx){
							alert (responseText.successx);
							window.location.assign('/bitproject/login.jsp');
						}else {
							alert (responseText.error);
						}
							
					}
				});
				//alert("Record Successfully Created..!");
				//window.location.assign('/bitproject/main.jsp');
			}
		});
	});

</script>
	</head>
<body>


<body>


  <div class="main">
    <h1>Sign Up</h1>
    <hr>

    <label><b>Email</b></label><br>
    <nobr><input type="text" placeholder="Enter Email" name="email" id="email" required><font style="color: red;">*</font></nobr><br>
    <label><b>Password</b></label>
    <nobr><input type="password" placeholder="Enter Password" name="psw" id="psw"required><font style="color: red;">*</font></nobr><br>
	<label><b>Repeat Password</b></label>
    <nobr><input type="password" placeholder="Repeat Password" name="psw-repeat" id="pswr" required><font style="color: red;">*</font></nobr><br>
    
    <label><b>User Type </b> </label>
	<nobr>	<select id="usertype" name="usertype" onchange="">
			<option value="0">Select</option>
		</select><font style="color: red;">*</font></nobr>
    
   <div class="clearfix">
      <button type="button" class="cancelbtn" onclick="window.location.href='index.html'">Cancel</button>
      <button type="submit" class="submit" id ="submit">Sign Up</button>
    </div>
  </div>

</body>
</html>
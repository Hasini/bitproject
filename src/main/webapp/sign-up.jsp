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

#usertype{
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    
}

/* Extra styles for the cancel button */
#cancel {
    color: black;
	padding: 12px 20px;
	margin: 8px 0;
	border: 1px;
	cursor: pointer;
	width: 30%;
    border-color:#20B2AA;
    border-radius: 12px;
}
#reset{
color: #DC143C;
	padding: 12px 20px;
	margin: 8px 0;
	border: thin;
	cursor: pointer;
	width: 30%;
    border-color:#DC143C;
    border-radius: 12px;
}

/* Add padding to container elements */
.container {
    padding: 5px;
}

/* Clear floats */
.clearfix::after {
    content: "";
    clear: both;
    display: table;
}
#signup{
	background-color: #20B2AA;
	color: white;
	padding: 12px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 30%;
}


.main {
	width:800px;
    border-top:30%;
    padding-top:5%;
    margin-top:5%;
    padding-left:35%;
    border-left:10%;
    min-height: 100px;
    overflow: hidden;
    border-style: hidden;
}
input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 5px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}


</style>


<script type="text/javascript">
	$(document).ready(function(){
		$('#cancel').click(function(){
			window.location.assign('/bitproject/index.html');
		});
		$('#reset').click(function(){
			$('input[type="text"]').val('');
			$('input[type="password"]').val('');
		});
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
			 	
			 	$("#signup").click(function(){
					var username=document.getElementById("email").value;
					var password=document.getElementById("psw").value;
					var repeatpassword=document.getElementById("pswr").value;
					var method;
					var e = document.getElementById("usertype");
					var userType = e.options[e.selectedIndex].value;
					
					if (password != repeatpassword){
						alert ("Password mismatched");
					} else if (username=="" || username == null || password == ""|| password == null || repeatpassword == ""|| repeatpassword == null || userType=="Select"){
						alert("Please input values");
						document.getElementById("username").focus();
					} else {
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
								$('input[type="text"]').val('');
								if (responseText.signupSuc){
									alert (responseText.signupSuc);
									alert("User registered..!");
									window.location.assign('/bitproject/login.jsp');
								}else {
									alert (responseText.error);
									$('input[type="text"]').val('');
								}
									
							}
						});
					}
				});
		    });
		});

</script>
	</head>
<body style="width: 80%; height: 70%; margin-left: 0.5%;"><div class="main">
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
   		<button type="submit" class="submit" id ="signup">Sign Up</button>
      <button type="button" id="cancel" onclick="window.location.href='index.html'">Cancel</button>
      <button type="button" id="reset">Reset</button>
      
    </div>
  </div>
</body>
</html>
<%@include file="footer.jsp" %> 
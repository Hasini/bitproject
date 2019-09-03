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

	

<style>

#signup {
	background-color: #20B2AA;
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
	width: 55%;
	border-radius: 50%;
}

.container {
	padding: 5px;
}

span.psw {
	float: right;
	padding-top: 20%;
}

#usertype{
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    
}
 #submit {
    background-color: #20B2AA;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 200px;
}
input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 5px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
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
				
				var username=document.getElementById("uname").value;
				var password=document.getElementById("psw").value;
				if (username=="" || username == null || password == ""|| password == null){
					alert("Please input values");
					document.getElementById("username").focus();
				} else {
					var method;
					$.ajax({
						type : 'post',
						url : 'userservlet',
						data : {
							username : username,
							password : password,
							method : "login",
						},
						success: function (responseText) {
							if (responseText.success){
								alert(responseText.success);
								if(responseText.userType ==1){
									window.location.assign('/bitproject/admin.jsp');
								}
								if(responseText.userType ==2){
									window.location.assign('/bitproject/dp.jsp');
								}
								if(responseText.userType ==3){
									window.location.assign('/bitproject/cashier.jsp');
								}
								if(responseText.userType ==4){
									window.location.assign('/bitproject/authorization.jsp');
								}
							}
							else {
								alert(responseText.error);
								
							}
						}
					});
				}
				
				
			});
	});

</script>
</head>

<body>
	<div class="main">
		<div>
			<div class="imgcontainer">
					<img src="images/login.jpg" alt="Avatar" class="avatar">
				</div>
				<div class="container">
					<label><b>Username</b></label> 
					<input type="text" placeholder="Enter Username" name="uname" id="uname" required="required"> 
					<label><b>Password</b></label>
					<input type="password" placeholder="Enter Password" name="psw" id="psw" required="required"> 
					
					<button type="submit" id="submit" class="submit">Login</button>
					<!-- <label> <input type="checkbox" checked="checked">
						Remember me
					</label> -->
				</div>
				<br><br>
				<div>
					<p style="font-size: 16px;"><strong>Register New System User</strong></p>
						<button type="submit" id="signup" value="signup" class="submit" onclick="window.location.href='sign-up.jsp'">Register</button>
				</div>
		</div>
		
	
	
</div>
</body>
</html>
<%@include file="footer.jsp" %>

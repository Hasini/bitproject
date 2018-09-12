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
		
		 $(window).load(function(){
			$.ajax({
					type : 'post',
					url : 'DailyIncome',
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
		 
		 $(window).load(function(){
			$.ajax({
					type : 'post',
					url : 'DailyIncome',
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
			var method;
			/* var incomeType = e.options[e.selectedIndex].value;
			var submitteduser = e.options[e.selectedIndex].value; */
			
			if (password != repeatpassword){
				alert ("Password mismatched");
			}else {
				$.ajax({
					type : 'post',
					url : 'DailyIncome',
					data : {
						username : document.getElementById("billid").value,
						password : document.getElementById("amt").value,
						method : "create"
						//incometypeid : incomeType,
						//user : submitteduser
					},
					success: function (responseText) {
						
						if (responseText.successx){
							alert (responseText.successx);
							window.location.assign('/bitproject/main.jsp');
							
						}else {
							alert (responseText.error);
						}
							
					}
				});
				alert("Record Successfully Created..!");
				window.location.assign('/bitproject/main.jsp');
			}
		});
	});

</script>
	</head>
<body>

<div class="container">
    <h1>Daily Income</h1>
    <hr>
	<label><b>Income Type </b> </label>
		<select id="incometype" name="incometype" onchange="">
			<option value="0">Select</option>
			
		</select><br>
		
		<label><b>Payment Id</b></label>
    <input type="text" name="billid" id="billid" required><br>

    <label><b>Amount</b></label>
    <input type="text" name="amt" id="amt"required><br>

    <label><b>Submitted User</b></label>
    <select id="users" name="users" onchange="">
			<option value="0">Select</option>
	</select><br>

	<button type="submit" name="submitbtn" value="submitbtn" id="submitbtn">Submit</button>
	<button type="button" name="cancel" id="cncl">Cancel</button>

</body>
</html>
<!DOCTYPE html>
<html>
<head>
	<title>Branch Details</title>
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="js/commonFunctions.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/favicon-facebook_400x400.png">
	
	<!-- <script type="text/javascript">
		function validate() {
			var code = document.getElementById("code").value;
			var descr = document.getElementById("descr").value;
			
			if(code=="" || code==null,descr == ""|| descr == null){
				alert("Values are Empty");
				return false;
			}
		}
	</script> -->
	<!-- <script type="text/javascript">
	$ (document).ready (function(){
		$('#submit').click(function() {
			var code = document.getElementById("code").value;
			var descr = document.getElementById("descr").value;
			
			$.ajax (function(){
				type : "post",
				url : "usertype",
				datatype :{
					dispatch : "usertype",
					code : code,
					descr : descr,
				},
				success : function(data) {
					alert(data);
				}
			});
		});
	});
	</script> -->
	
</head>
<body>
<div id="main">
	<form action="branchDetails" method="get" onsubmit="validate();">
		<h2>Branch Details</h2>
		Branch Code : <input type="text" name="code" id="code"> <font color="red">*</font><br>
		Branch Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
		
		<button type="submit" name="submit" value="Submit" id="submit">Submit</button>
	</form>
</div>	
</body>
</html>
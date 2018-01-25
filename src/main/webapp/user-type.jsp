<!DOCTYPE html>
<html>
<head>
	<title>User Type</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Type</title>
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="js/commonFunctions.js"></script>
	
	
	<script type="text/javascript">
		function validate() {
			alert ("aasa")
			var code = document.getElementById("code").value;
			var descr = document.getElementById("descr").value;
			
			if(code=="" || code==null,descr == ""|| descr == null){
				alert("Values are Empty");
				return false;
			}
		}
	</script>
	<script type="text/javascript">
	/* $ (document).ready (function(){
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
	}); */
	
	
	$(document).ready(function() {
		$('#submit').click(function() {
			var code = document.getElementById("code").value;
			var descr = document.getElementById("descr").value;
			
			$.ajax ({
				type : "post",
				url : "usertype",
				datatype :{
					dispatch : "usertype",
					code : code,
					descr : descr,
				},
				success : function(datatype) {
					alert ("hiii");
					alert(datatype);
				}
			});
		});
		
	});
	</script>
	
</head>
<body>
	<form action="usertype" method="get" onsubmit="validate();">
		<h2>User Type</h2>
		Code : <input type="text" name="code" id="code"> <font color="red">*</font><br>
		Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
		
		<input type="submit" name="submit" value="Submit" id="submit">
	</form>
	
</body>
</html>
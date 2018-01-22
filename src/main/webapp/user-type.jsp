<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Type</title>
	
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
</head>
<body>
	<form action="usertype" method="get" onsubmit="validate();">
		<h2>User Type</h2>
		Code : <input type="text" name="code" id="code"> <font color="red">*</font><br>
		Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
		
		<input type="submit" name="submit" value="Submit">
	</form>
	
</body>
</html>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Type</title>
	
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/favicon-facebook_400x400.png">
	
	<script type="text/javascript">
	$(document).ready(function() {
		function getVal() {
			alert("it works");
			
		}
	});
	
	
	
	/* $(document).ready(function() {
		
			alert("ajax test");
			/* var code = document.getElementById("code").value;
			var descr = document.getElementById("descr").value;
			alert(code);
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
		
	}); */
	</script>
	<script type="text/javascript">
	/* function clear() {
		alert("aa");
		document.getElementbyid("code").value = " ";
		document.getElementbyid("descr").value = " ";
	} */
	
	</script>

	
	<style>
/* 		body {font-family: Arial;}
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
	
#clear {
    background-color: red;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 50%;
}	
	</style>
	
</head>
<body>
	<div id="main">
		<form method="post" onsubmit="getVal()">
			<h2>User Type</h2>
			Code : <input type="text" name="code" id="code"> <font color="red">*</font><br>
			Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
			
			<button type="submit" name="submit" value="Submit" id="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="clear" onclick="clear();">Clear</button>
		</form>
	</div>
	
	
</body>
</html>
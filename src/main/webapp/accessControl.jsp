<!DOCTYPE html>
<html>
<head>
	<title>Access Control</title>
	
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/favicon-facebook_400x400.png">
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			$("#submitbtn").click(function() {
				var codepr = document.getElementById("codepr").value;
				var descrpr = document.getElementById("descrpr").value;
				$.ajax ({
					type : "POST",
					url : "usertype?codepr="+codepr+"&descrpr="+descrpr,
					
					success : function(responseText) {
						if(responseText.success){
							alert(responseText.success +" Successfully Created..!");
							window.location.assign('/bitproject/main.jsp');
						}else{
							
						}
						
					}
				}); 
			});
	}); 
	</script>

	
</head>
<body>
	<div id="main">
		<form action="">
			<fieldset>Access Control</fieldset>
			
			Program Code : <input type="text" name="codepr" id="codepr"> <font color="red">*</font><br>
			Program Name : <input type="text" name="descrpr" id="descrpr"><font color="red">*</font><br>
			
			Admin <input type="checkbox" id="adm"> Delivery Person <input type="checkbox" id="dp"> Cashier <input type="checkbox" id="casir"><br>
			
			
			<button type="submit" name="submit" value="Submit" id="submitbtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
		</form>
	</div>
	
	
</body>
</html>
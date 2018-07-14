<!DOCTYPE html>
<html>
<head>
	<title>User Type</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/favicon-facebook_400x400.png">
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			$("#submitbtn").click(function() {
				var codeut = document.getElementById("codeut").value;
				var descrut = document.getElementById("descrut").value;
				alert (codeut+descrut);
				//"exam_reg?regNo="+name+"&methode="+"loadDep"
				$.ajax ({
					type : "GET",
					url : "usertype?codeut="+codeut+"&descrut="+descrut,
					/* datatype :{
						dispatch : "usertype",
						codeut : codeut,
						descrut : descrut
					}, */
					success : function(responseText) {
						alert("suc fun");
						if(responseText.success){
							alert(responseText.success +" Successfully Created..!");
							window.location.assign('/bitproject/main.jsp');
						}else{
							//alert(responseText.error);
						}
						
					}
				}); 
			});
	}); 
	</script>

	
</head>
<body>
	<div id="main">
		
			<h2>User Type</h2>
			Code : <input type="text" name="codeut" id="codeut"> <font color="red">*</font><br>
			Description : <input type="text" name="descrut" id="descrut"><font color="red">*</font><br>
			
			
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
			<button type="submit" name="submit" value="Submit" id="submitbtn" class="submit">Submit</button>
	
	</div>
	
	
</body>
</html>
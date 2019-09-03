<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
<head>
	<title>User Type</title>
	
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/commonFunctions.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/favicon-facebook_400x400.png">
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#cncl').click(function(){
				if(confirm("do you want to reset text fields?")){
					$('input[type="text"]').val('');
				}					
			});
			$("#submitbtn").click(function() {
				var codeut = document.getElementById("codeut").value;
				var descrut = document.getElementById("descrut").value;
				if(codeut=="" || codeut == null || descrut == ""|| descrut == null){
					alert("Values are Empty");
					document.getElementById("codeut").focus();
				}else {
					$.ajax ({
						type : "GET",
						url : "usertype?codeut="+codeut+"&descrut="+descrut,
						
						success : function(responseText) {
							if(responseText.success){
								alert(responseText.success +" Successfully Created..!");
								$('input[type="text"]').val('');
								window.location.assign('/bitproject/admin.jsp');
							}else{
								alert(responseText.error +" Error Occured..!");
								window.location.assign('/bitproject/user-type.jsp');
								$('input[type="text"]').val('');
							}
							
						}
					});
				}
			});
	}); 
	</script>
</head>
<body >
	<div id="main">
			<h2>Credit Limitation</h2>
			Customer Type : <br><select id="cusT" name="cusT" >
			<option>select</option>
		</select> <font color="red">*</font><br>
			Credit Limit (LKR) : <br><input type="text" name="cl" id="cl"><font color="red">*</font><br>
			<br>
			<button type="submit" name="submit" value="Submit" id="submitbtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
	</div>
</body>

</html>
<%@include file="footer.jsp" %>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<html>
	<head>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/commonFunctions.js"></script>
			
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<link rel="icon" href="images/favicon-facebook_400x400.png">
		
		<script type="text/javascript">
	
	$(document).ready(function(){
		$("#details").hide();
		$("#search").click(function(){
			
			var cus_name=document.getElementById("cus_no").value;
			var cus_nic=document.getElementById("cus_nic").value;
			$("#cus_no").click(function(){
			$.ajax({
					type : 'post',
					url : 'Lend_Details_View_Controller',
					data : {
						cus_no : cus_no
						
					},
					success: function (responseText) {
						if (responseText.success){
							alert("yeeeeeeeeeeeeeeee");
							$("#details").show();
						}
						else {
							alert("Please retry another search criteria..!");
						}
					}
			});
		});
			$("#cus_nic").click(function(){
				$.ajax({
						type : 'post',
						url : 'Lend_Details_View_Controller',
						data : {
							cus_nic : cus_nic
						},
						success: function (responseText) {
							if (responseText.success){
								alert("yeeeeeeeeeeeeeeee niccc");
								$("#details").show();
							}
							else {
								alert("Please retry another search criteria..!");
							}
						}
				});
			});
		});	
	});

</script>
	</head>
<body>


<div id="main">
	<div >
		<h2>Search by</h2>
		<input type="radio" name="cussearch" id="cus_no">By Contact Number <pre><input type="text"> </pre><br><br>
		<input type="radio" name="cussearch" id="cus_nic">By NIC<pre><input type="text"> </pre> <br>
		<input type="image" src="images/search.png" alt="Search" width="48" height="48" id="search">
			<font color="blue" size="12cm">Search</font>
	</div>
	
	<div id="details">
		<P>Testtt</P>
	</div>
		
</div>

</body>
</html>
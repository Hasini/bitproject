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
			/* $(window).load(function(){
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
							$("#cusT").append (usertypedescr);
						 }
					}   
				});
			}); */
			$("#submitbtn").click(function() {
				var e = document.getElementById("cusT");
	        	var cusTypeID = e.options[e.selectedIndex].value;
	        	alert( "cusTypeID" +cusTypeID);
	        	
				var creditLimit = document.getElementById("cl").value;
				if(creditLimit=="" || creditLimit == null){
					alert("Values are Empty");
					document.getElementById("cl").focus();
				}else {
					$.ajax ({
						type : "GET",
						url : 'CreditLimitController',
						data :{
							cusTypeID : cusTypeID ,
							creditLimit : creditLimit
						},
						success : function(responseText) {
							if (responseText.success) {
								alert(responseText.success +" Successfully Created..!");
								$('input[type="text"]').val('');
								window.location.assign('/bitproject/admin.jsp');
							} else {
								alert(responseText.error +" Error Occured..!");
								window.location.assign('/bitproject/credit-limitation.jsp');
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
			<label><b>Customer Category </b> </label>
			<nobr>	
			<select id="cusT" name="cusT">
				<option value="0">Select</option>
				<option value="1">Small</option>
				<option value="2">Medium</option>
				<option value="3">Large</option>
			</select>
			<font style="color: red;">*</font>
			</nobr><br>
			Credit Limit (LKR) : <br><input type="text" name="cl" id="cl"><font color="red">*</font><br>
			<br>
			<button type="submit" name="submit" value="Submit" id="submitbtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl"">Reset</button>
	</div>
</body>

</html>
<%@include file="footer.jsp" %>
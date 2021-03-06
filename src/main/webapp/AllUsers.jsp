<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/commonFunctions.js"></script>
			
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<link rel="icon" href="images/favicon-facebook_400x400.png">


		<script type="text/javascript">
		$(document).ready(function(){
			$(window).load(function(){
		 $("#view").click(function() {
			 $.ajax({
					type : 'GET',
					url : 'CustomerRegistrationController',
					data : {
						method : "viewUsers"
					},
					success : function(responseText) {
						$('#tablediv').empty();
				    	var tbl=$("<table/>").attr("id","mytable");
				        $("#tablediv").append(tbl);
				        var t="<tr bgcolor='#33E9FF'><td width='20%'>Customer Name</td></tr>"
				       
				        for(var i=0;i<responseText.userObj.length;i++){
				        	 	var tr="<tr width='30%'>";
					            var td1="<td width='15%'>"+responseText.userObj[i]["name"]+"</td>";
					            var td2="<td width='15%'>"+responseText.userObj[i]["id"]+"</td>";
					          
					            
					           	$("#mytable").append(tr+td1+td2);
				        }
				    }
				});
			});
			});
		 
		});
		
		</script>

</head>
<body>
	<h2>LIST ALL CUSTOMERS</h2>
			<div style="float: left;">
				<button type ="button" id = "view"><span>View All Users</span> </button>
			</div>
			
			<table style="float: right;">
				
				<th style="background-color: #000099; color:#ffffff; width: 15%;">User</th>
				<th style="background-color: #000099; color:#ffffff; width: 15%;">User Type</th>
				
			</table>
			<div id="tablediv" style="float: right;"></div>
			<div id = "emptydiv"></div>
			
			

</body>
</html>
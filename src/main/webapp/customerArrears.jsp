<!DOCTYPE html>
<%@include file="header.jsp" %> 

<html>
<head>
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
		
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/favicon-facebook_400x400.png">
	
	<script>
		$(document).ready(function(){
			$(window).load(function(){
				  $("#view").click(function() {
			        	$.ajax({
							type : 'GET',
							url : 'createaction',
							data : {
							checkboxVal : "view"
							},
							success : function(responseText) {
								$('#tablediv').empty();
						    	var tbl=$("<table/>").attr("id","mytable");
						        $("#tablediv").append(tbl);
						        var t = "<tr bgcolor='#33E9FF'><td width='20%'>Branch Code</td><td width='20%'>Branch Description</td></tr>"
						       
						        for(var i=0;i<responseText.branchListObject.length;i++){
						        	 	var tr="<tr width='30%'>";
							            var td1="<td width='15%'>"+responseText.branchListObject[i]["code"]+"</td>";
							            var td2="<td width='15%'>"+responseText.branchListObject[i]["desc"]+"</td></tr>";
							            $("#mytable").append(tr+td1+td2);
						        }
						    }
						});
					});
				});
			});
	</script>
<body>
	<div id="main" style="padding-bottom: 20%;">
	<div id="updateDiv">
		<table style="float: right;">
			<th style="background-color: #000099; color:#ffffff; width: 5%;">Customer </th>
			<th style="background-color: #000099; color:#ffffff ;width: 15%;">Arrears</th>
		</table>
		<div id="tablediv" style="float: right;"></div>
	</div>
	</div>
</body>
</html>
<%@include file="footer.jsp" %>
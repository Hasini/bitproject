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
			var cus_no=document.getElementById("cus_no_txt").value;
			var cus_nic=document.getElementById("cus_nic_txt").value;
			if($("#cus_no").is(':checked')){
				$.ajax({
					type : 'post',
					url : 'Lend_Details_View_Controller',
					data : {
						cus_no : cus_no
					},
					success : function(responseText) {
						$("#details").show();
						$('#tablediv').empty();
				    	var tbl=$("<table/>").attr("id","mytable");
				        $("#tablediv").append(tbl);
				        var t="<tr bgcolor='#33E9FF'><td width='20%'>test</td><td width='20%'>Arreersa</td></tr>"
				       
				        for(var i=0;i<responseText.cusObj.length;i++){
				        	 	var tr="<tr width='60%'>";
					            var td1="<td width='5%'>"+responseText.cusObj[i]["customer_id"]+"</td>";
					            var td2="<td width='5%'>"+responseText.cusObj[i]["total_paid"]+"</td></tr>";
					            var td3="<td width='5%'>"+responseText.cusObj[i]["total_extraPay"]+"</td></tr>";
					            var td4="<td width='5%'>"+responseText.cusObj[i]["arr"]+"</td></tr>";
					           	$("#mytable").append(tr+td1+td2+td3+td4);
				        }
				    }
				});
	  		}
		});
	});

</script>
</head>
<body style="width: 80%; height: 70%; margin-left: 0.5%;">
	<div id="main" style="width: 70%; height: 70%; margin-left: 10%">
		<div >
			<h2>Search by</h2>
			<input type="radio" name="cussearch" id="cus_no">By Contact Number <pre><input type="text" id="cus_no_txt"> </pre><br><br>
			<input type="radio" name="cussearch" id="cus_nic">By NIC<pre><input type="text" id="cus_nic_txt"> </pre> <br>
			<input type="image" src="images/search.png" alt="Search" width="48" height="48" id="search">
				<font color="blue" size="12cm">Search</font>
		</div>
		
		<div id="details">
			<table style="float: right;">
				<th style="background-color: #000099; color:#ffffff; width: 15%;">NIC</th>
				<th style="background-color: #000099; color:#ffffff ;width: 15%;">Total payments</th>
				<th style="background-color: #000099; color:#ffffff ;width: 15%;">Total extra payments</th>
				<th style="background-color: #000099; color:#ffffff ;width: 15%;">Total Arrears</th>
				</table>
				<div id="tablediv" style="float: right;"></div>
				<div id ="emptydiv"></div>
		</div>
			
	</div>
</body>
<footer style="width: 80%; height: 5%; border-right: 20%; background-image: url(images/background.jpg); color:white; margin-left: 10%">KGM</footer>
</html>
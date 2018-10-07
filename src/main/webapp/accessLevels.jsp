<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
<head>
	<title>Access Control</title>
	
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/favic.jpg">
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			 $("#submitbtn").click(function() {
				 var checkitem;
				$('#adm').on('change', function(){
						checkitem = this.checked ? 1 : 0;
					   alert(checkitem);
					}).change();
				
				 $.ajax({
					 type : 'POST',
						url : 'accessright',
						data : {
							code : document.getElementById("codepr").value,
							descr : document.getElementById("descrpr").value,
							checkitem :checkitem
						},
						error: function (responseText) {
							alert(responseText.error+"response");
							
	                	},

	  					success: function (responseText) {
	  						if (responseText.success ){
	  							alert(responseText.success);
	  						}else 
	  							alert(responseText.error);
	  					}   
				 });
			 });
			
	}); 
	</script>

	
</head>
<body>
	<div id="main">
		
			<fieldset>
			
				<legend>Access Control</legend>
				
				Program Code : <input type="text" name="codepr" id="codepr"> <font color="red">*</font><br>
			Program Name : <input type="text" name="descrpr" id="descrpr"><font color="red">*</font><br>
			
			Admin <input type="checkbox" id="adm" checked="checked" value="1"> <br>
			Delivery Person <input type="checkbox" id="dp"> <br>
			Cashier <input type="checkbox" id="casir"><br>
			<label for= "admanddp">Admin and DP</label> <input type="checkbox" id="admanddp" name="admanddp"  value="0"><br>
			<label for= "admandcas">Admin and CAS</label> <input type="checkbox" id="admandcas" name="admandcas" value="0"><br>
			
			<button type="submit" name="submit" value="Submit" id="submitbtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
			</fieldset>
			
			
		
	</div>
	
	
</body>
</html>
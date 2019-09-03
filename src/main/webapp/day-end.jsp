<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
<head>
	<title>Day end</title>
	
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/home.png">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<script type="text/javascript">
		$(document).ready(function(){
			 $(window).load(function(){
					
				 	$.ajax({
						type : 'GET',
						url : 'CustomerRegistrationController',
						data : {
							method : "view"
						},
						success : function(responseText) {
							for(var i=0;i<responseText.branchobj.length;i++){
								var idvalue = responseText.branchobj[i]["id"];
								var branch = "<option value="+idvalue+">"+responseText.branchobj[i]["branch_descr"]+"</option>";
								$("#branch").append (branch);
							 }
						}   
				}); 
			});
			
				$("#branchSummary").click(function() {
					var br = document.getElementById("branch");
					var branch = br.options[br.selectedIndex].value;
					alert(branch);
					$.ajax({
						type : 'GET',
						url : 'DayEndController',
						data :{
							method : "getBranchIncome",
							branch : branch
						},
						success: function (responseText) {
							if (responseText.success){
								alert(responseText.success);
								alert(responseText.branchProfit);
							}else {
								alert("error");
							}
					}
				});
			});
		});
	</script>
	
	<style type="text/css">
		#progress {
	    	width: 10%;
	    	height: 30px;
	    	background-color: blue;
	    	text-align: center; /* To center it horizontally (if you want) */
	    	line-height: 30px; /* To center it vertically */
	    	color: white; 
		}
	</style>
</head>
<body style="width: 80%; height: 70%; margin-left: 0.5%;">
	<fieldset>
	<legend>DayEnd Processes</legend>
	<label><b>Branch :</b> </label> <select id="branch" name="branch" class="dropdownfields">
				<option>select</option>
			</select>
	<br><br><br><br><br><br>
	<div id="calBranchWiseIncome">
  		<div id="progress">10%</div>
	</div>
	<br>
	<button type="submit" id="branchSummary" onclick="move()">Branch wise income calculation</button> <button onclick="">View Summary</button>
	</fieldset>
	<script>
		function move() {
		  var elem = document.getElementById("progress");   
		  var width = 10;
		  var id = setInterval(frame, 10);
		  function frame() {
			    if (width >= 100) {
			      clearInterval(id);
			    } else {
			      width++; 
			      elem.style.width = width + '%'; 
			      elem.innerHTML = width * 1  + '%';
			    }
  			}
		}
</script>
</body>
</html>
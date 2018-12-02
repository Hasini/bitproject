<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
<head>
	<title>Cash Book</title>
	
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/home.png">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">

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
<body>
	<fieldset>
	<legend>DayEnd Processes</legend>
	<br><br><br><br><br><br>
	<div id="calBranchWiseIncome">
  		<div id="progress">10%</div>
	</div>
	<br>
	<button onclick="move()">Branch wise income calculation</button> <button onclick="">View Summary</button>
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
<!DOCTYPE html>
<%@include file="header.jsp" %>
<html>
	<head>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/commonFunctions.js"></script>
			
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<link rel="icon" href="images/favicon-facebook_400x400.png">
	</head>
	
	<style>
body {font-family: Arial;}

/* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  display: none;
  padding: 6px 12px;
  border: 1px solid #ccc;
  border-top: none;
}
</style>
</head>
<body>


<div class="tab">
  <button class="tablinks" onclick="getInfo(event, 'Basic_Info')">Basic Info</button>
  <button class="tablinks" onclick="getInfo(event, 'Financial_Info')">Financial Info</button>
  <button class="tablinks" onclick="getInfo(event, 'Lend_Info')">Lend Info</button>
</div>

<div id="Basic_Info" class="tabcontent">
  <h3>Basic Info</h3>
  
</div>

<div id="Financial_Info" class="tabcontent">
  <h3>Financial Info</h3>
  <p>Paris is the capital of France.</p> 
</div>

<div id="Lend_Info" class="tabcontent">
  <h3>Lend Info</h3>
  <p>Tokyo is the capital of Japan.</p>
</div>

<script>
function getInfo(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}
</script>
<body>


</body>
</html>
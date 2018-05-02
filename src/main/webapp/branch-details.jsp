<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
		
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/favicon-facebook_400x400.png">.
	
	<script>
		$(document).ready(function(){
			var checkboxVal;
			
			$("#createDiv").hide();
		    $("#deleteDiv").hide();
		    $("#updateDiv").hide();
		    
		    $("#create").click(function(){
		    	$("p").hide();
		        $("#createDiv").show();
		        $("#deleteDiv").hide();
		        $("#updateDiv").hide();
		        
		        $("#submitbtn").click(function() {
		        	alert("createaction");
					$.ajax({
						type : 'GET',
						url : 'createaction',
						data : {
							code : document.getElementById("code").value,
							descr : document.getElementById("descr").value,
							checkboxVal : "create"
							//alert(checkboxVal+"checkboxVal");
						},
						error: function (responseText) {
							alert(responseText.error+"response");
							
	                	},

	  					success: function (responseText) {
	  						alert(responseText.success+"response success");
	  						alert(responseText.error+"response");
	        			}   
						
					});
				});    
		        
		    });
		    $("#update").click(function(){
		  		$("p").hide();
		        $("#updateDiv").show();
		        $("#createDiv").hide();
		        $("#deleteDiv").hide();
		        
		        $("#view").click(function() {
		        	alert("aa");
		        	$.ajax({
						type : 'GET',
						url : 'createaction',
						data : {
						checkboxVal : "update"
						},
						success : function(responseText) {
							alert(responseText.descr);
							alert(responseText.error);
						}
					});
				});
			});
		    
		    /* $("#update").click(function(){
		    	$("p").hide();
		        $("#updateDiv").show();
		        $("#createDiv").hide();
		        $("#deleteDiv").hide();
		        
		        $("#submitbtnu").click(function() {
					$.ajax({
						type : 'GET',
						url : 'createaction',
						data : {
							code : document.getElementById("code").value,
							descr : document.getElementById("descr").value,
							checkboxVal : "update" 
						},
						success : function(responseText) {
							alert(responseText);
						}
					});
				});
			}); */
		    
		    $("#delete").click(function(){
		    	$("#deleteDiv").show();
		    	$("#createDiv").hide();
		    	$("#updateDiv").hide();
		    	
		    	$("#submitbtnd").click(function() {
					$.ajax({
						type : 'GET',
						url : 'createaction',
						data : {
							code : document.getElementById("code").value,
							descr : document.getElementById("descr").value,
							checkboxVal : "delete" 
						},
						success : function(responseText) {
							alert(responseText);
						}
					});
				});
		    	
		    });
		 });
	</script>
</head>
<body>


<fieldset>
	<legend>Branch Details</legend>


<div id="main">
	<div id = "createDiv">
		
			<h2>Create Branch Details</h2>
			Branch Code : <input type="text" name="code" id="code"> <font color="red">*</font><br>
			Branch Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
			
			<button type="submit" name="submitbtn" value="submitbtn" id="submitbtn" class="submit">Submit</button>
			<button type="button" name="cancel" id="cncl">Cancel</button>
			
		
		
	</div>
	
	<div id="updateDiv">
		
			<h2>Update Branch Details</h2>
			<div>
			<button type ="button" id = "view">View </button>
			
			</div>
			
 			
			Branch Code : <input type="text" name="code" id="code"> <font color="red">*</font><br>
			Branch Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
			
			<button type="submit" name="submitbtnu" value="submitbtnu" id="submitbtnu" class ="submit">Update</button>
			<button type="button" name="cancel" value="Cancel" id="cncl">Cancel</button>
		
	</div>
	
	<div id="deleteDiv">
		<form action="createaction" method="get" onsubmit="validate();">
			<h2>Delete Branch Details</h2>
			Branch Code : <input type="text" name="code" id="code" disabled="disabled"> <font color="red">*</font><br>
			Branch Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
			
			<button type="submit" name="submitbtnd" value="submitbtnd" id="submitbtnd" class = "submit">Delete</button>
			<button type="button" name="cancel" id="cncl">Cancel</button>
		</form>
	</div>


	<div id="btngroup">
		<input type ="radio" name ="create" id ="create" value ="create">Create
		<input type ="radio" name ="update" id ="update" value ="update">Update
		<input type ="radio" name ="delete" id ="delete" value ="delete">Delete
	</div>
</div>

</fieldset>



</body>
</html>
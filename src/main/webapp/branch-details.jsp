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
				var checkboxVal;
				
				$("#createDiv").hide();
			    $("#deleteDiv").hide();
			    $("#updateDiv").hide();
				
			});
			
		    
		    $("#create").click(function(){
		    	$("p").hide();
		    	$("#createDiv").show();
		        $("#deleteDiv").hide();
		        $("#updateDiv").hide();
		        
		        $("#submitbtn").click(function() {
		        	$.ajax({
						type : 'GET',
						url : 'createaction',
						data : {
							code : document.getElementById("code").value,
							descr : document.getElementById("descr").value,
							checkboxVal : "create"
						},
						success: function (responseText) {
	  						if (responseText.success){
	  							alert(responseText.success+" Branch Successfully Created...!");
	  						}else {
	  							alert(responseText.error);
	  						}
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
					        var t="<tr bgcolor='#33E9FF'><td width='20%'>Branch Code</td><td width='20%'>Branch Description</td></tr>"
					       
					        for(var i=0;i<responseText.branchListObject.length;i++){
					        	 	var tr="<tr width='30%'>";
						            var td1="<td width='15%'>"+responseText.branchListObject[i]["code"]+"</td>";
						            var td2="<td width='15%'>"+responseText.branchListObject[i]["desc"]+"</td></tr>";
						            
						           	$("#mytable").append(tr+td1+td2);
					        }
					    }
					});
				});
		        
		        $("#submitbtnu").click(function() {
		        	$.ajax({
						type : 'GET',
						url : 'createaction',
						data : {
							codeu : document.getElementById("codeu").value,
							descru : document.getElementById("descru").value,
							checkboxVal : "update" 
						},
						success : function(responseText) {
							if (responseText.success){
								alert(responseText.success+" Branch Description Successfully Updated..!");
								window.location.assign('/bitproject/main.jsp');
							}else {
								alert(responseText.error);
							}
							
						}
					});
				});
			});
		    
		    /* $("#update").click(function(){
		    	$("#updateDiv").show();
		        $("#createDiv").hide();
		        $("#deleteDiv").hide();
		        
		        $("#submitbtnu").click(function() {
		        	$.ajax({
						type : 'GET',
						url : 'createaction',
						data : {
							codeu : document.getElementById("codeu").value,
							descru : document.getElementById("descru").value,
							checkboxVal : "update" 
						},
						success : function(responseText) {
							if (responseText.success){
								alert(responseText.success+" Branch Description Successfully Updated..!");
								window.location.assign('/bitproject/main.jsp');
							}else {
								alert(responseText.error);
							}
							
						}
					});
				});
			}); */
		    
		    $("#delete").click(function(){
		    	$("p").hide();
		    	$("#deleteDiv").show();
		    	$("#createDiv").hide();
		    	$("#updateDiv").hide();
		    	
		    	$("#submitbtnd").click(function(){
		    		$.ajax({
						type : 'GET',
						url : 'createaction',
						data : {
							coded : document.getElementById("coded").value,
							descrd : document.getElementById("descrd").value,
							checkboxVal : "delete" 
						},
						success : function(responseText) {
							if (responseText.success){
								alert(responseText.success+" Branch deleted successfully..!");
								window.location.assign('/bitproject/main.jsp');
							}else {
								alert(responseText.error);
							}
						}
					});
				});
		    	
		    });
		 });
	</script>
	
	<style type="text/css">
	
	#view {
	  display: inline-block;
	  border-radius: 4px;
	  float:right;
	  background-color: silver#f4511e;
	  border: none;
	  color: #f4511e;
	  text-align: center;
	  font-size: 20px;
	  padding: 15px;
	  width: 250px;
	  height:1%;
	  transition: all 0.5s;
	  cursor: pointer;
	  margin: 5px;
	}

	#view span {
	  cursor: pointer;
	  display: inline-block;
	  position: relative;
	  transition: 0.5s;
	}

	#view span:after {
	  content: '\00bb';
	  position: absolute;
	  opacity: 0;
	  top: 0;
	  right: -20px;
	  transition: 0.5s;
	}

	#view:hover span {
	  padding-right: 25px;
	}
	
	#view:hover span:after {
	  opacity: 1;
	  right: 0;
	}

	

	</style>
</head>
<body>


<fieldset>
	<legend>Branch Details</legend>


<div id="main">
	<div id = "createDiv">
		
			<h2>Create Branch Details</h2>
			Branch Code : <input type="text" name="code" id="code"> <font color="red">*</font><br>
			Branch Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
			
			
			<button type="button" name="cancel" id="cncl">Cancel</button>
			<button type="submit" name="submitbtn" value="submitbtn" id="submitbtn" class="submit">Submit</button>
	</div>
	
	<div id="updateDiv">
		
			<h2>Update Branch Details</h2>
			<div style="float: left;">
				<button type ="button" id = "view"><span>View Branch Details</span> </button>
			</div>
			
			<table style="float: right;">
				
				<th style="background-color: #000099; color:#ffffff; width: 5%;">Code</th>
				<th style="background-color: #000099; color:#ffffff ;width: 15%;">Description</th>
			</table>
			<div id="tablediv" style="float: right;"></div>
			<div id = "emptydiv"></div>
			
			</div>
			<input type="text" name="code" id="codeu" placeholder="Branch Code"> <font color="red">*</font><br>
			<input type="text" name="descr" id="descru" placeholder="Branch Description"><font color="red">*</font><br>
			
			<button type="button" name="cancel" value="Cancel" id="cncl">Cancel</button>
			<button type="submit" name="submitbtnu" value="submitbtnu" id="submitbtnu" class ="submit">Update</button>
			
	</div>
	
	<div id="deleteDiv">
			<h2>Delete Branch Details</h2>
			Branch Code : <input type="text" name="code" id="coded"> <font color="red">*</font><br>
			Branch Description : <input type="text" name="descrd" id="descrd"><font color="red">*</font><br>
			
			<button type="button" name="cancel" id="cncl">Cancel</button>
			<button type="submit" name="submitbtnd" value="submitbtnd" id="submitbtnd" class ="submit">Delete</button>
			
		
	</div>

	<div id="btngroup">
		<input type ="radio" name ="create" id ="create" value ="create">Create
		<input type ="radio" name ="update" id ="update" value ="update">Update
		<input type ="radio" name ="delete" id ="delete" value ="delete">Delete
	</div>


</fieldset>



</body>
</html>
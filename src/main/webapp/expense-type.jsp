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
		        	$.ajax({
						type : 'GET',
						url : 'expenseTypeController',
						data : {
							code : document.getElementById("code").value,
							descr : document.getElementById("descr").value,
							checkboxVal : "create"
						},
						error: function (responseText) {
							alert(responseText.error+"response");
							
	                	},

	  					success: function (responseText) {
	  						alert(responseText.success);
	  						window.location.assign('/bitproject/main.jsp');
	  						if (responseText.error )
	  							alert(responseText.error);
	  						window.location.assign('/bitproject/main.jsp');
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
						url : 'expenseTypeController',
						data : {
						checkboxVal : "view"
						},
						success : function(responseText) {
							$('#tablediv').empty();
					    	var tbl=$("<table/>").attr("id","mytable");
					        $("#tablediv").append(tbl);
					        var t="<tr bgcolor='#33E9FF'><td width='20%'>Branch Code</td><td width='20%'>Branch Description</td></tr>"
					       
					        for(var i=0;i<responseText.ListObject.length;i++){
					        	 	var tr="<tr width='30%'>";
						            var td1="<td width='15%'>"+responseText.ListObject[i]["code"]+"</td>";
						            var td2="<td width='15%'>"+responseText.ListObject[i]["desc"]+"</td></tr>";
						            
						           	$("#mytable").append(tr+td1+td2);
					        }
					    }
					});
				});
		        
		         $("#submitbtnu").click(function() {
		        	$.ajax({
						type : 'GET',
						url : 'expenseTypeController',
						data : {
							codeu : document.getElementById("codeu").value,
							descru : document.getElementById("descru").value,
							checkboxVal : "update" 
						},
						success : function(responseText) {
							if (responseText.success){
								alert(responseText.success);
								window.location.assign('/bitproject/main.jsp');
							}else {
								alert(responseText.error);
							}
							
						}
					});
				}); 
			}); 
		    $("#delete").click(function(){
		  		$("p").hide();
		        $("#updateDiv").hide();
		        $("#createDiv").hide();
		        $("#deleteDiv").show();
		        
		     /*    $("#viewbtnd").click(function() {
		        	alert("deeeeee");
		        	$.ajax({
						type : 'GET',
						url : 'expenseTypeController',
						data : {
							checkboxVal : "view"
						},
						success : function(responseText) {
							$('#tablediv').empty();
					    	var tbl=$("<table/>").attr("id","mytable");
					        $("#tablediv").append(tbl);
					        var t="<tr bgcolor='#33E9FF'><td width='20%'>Branch Code</td><td width='20%'>Branch Description</td></tr>"
					       
					        for(var i=0;i<responseText.ListObject.length;i++){
					        	 	var tr="<tr width='30%'>";
						            var td1="<td width='15%'>"+responseText.ListObject[i]["code"]+"</td>";
						            var td2="<td width='15%'>"+responseText.ListObject[i]["desc"]+"</td></tr>";
						            
						           	$("#mytable").append(tr+td1+td2);
					        }
					    }
					});
				}); */
		        
		        $("#submitbtnd").click(function(){
		    		$.ajax({
						type : 'GET',
						url : 'expenseTypeController',
						data : {
							coded : document.getElementById("coded").value,
							checkboxVal : "delete" 
						},
						success : function(responseText) {
							if (responseText.success){
								alert(responseText.success+" Type deleted successfully..!");
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
	</style>
</head>
<body>


<fieldset>
	<legend>Expense Type</legend>


<div id="main">
	<div id = "createDiv">
		
			<h2>Create Expense Type</h2>
			Expense Type Code : <input type="text" name="code" id="code"> <font color="red">*</font><br>
			Expense Type Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
			
			<button type="submit" name="submitbtn" value="submitbtn" id="submitbtn" class="submit">Submit</button>
			<button type="button" name="cancel" id="cncl">Cancel</button>
	</div>
	
	<div id="updateDiv">
			<h2>Update expense Type</h2>
			<div>
			<button type ="button" id="view">View </button>
			
			</div>
			<table style="float: right;" >
				
				<th style="background-color: #000099; color:#ffffff; width: 15%;">Code</th>
				<th style="background-color: #000099; color:#ffffff ;width: 15%;">Description</th>
			</table>
			<div id="tablediv" style="float: right; height: 25%;"></div>
			<div id="emptydiv"></div>
			
 			
			expense Type Code : <input type="text" name="code" id="codeu"> <font color="red">*</font><br>
			expense Type Description : <input type="text" name="descr" id="descru"><font color="red">*</font><br>
			
			<button type="submit" name="submitbtnu" value="submitbtnu" id="submitbtnu" class ="submit">Update</button>
			<button type="button" name="cancel" value="Cancel" id="cncl">Cancel</button>
		
	</div>
	
	<div id="deleteDiv">
			<div style="float: left;">
				<button type ="button" id ="viewbtnd"><span>View Details</span> </button>
			</div>
			
			<table style="float: right;" >
				
				<th style="background-color: #000099; color:#ffffff; width: 15%;">Code</th>
				<th style="background-color: #000099; color:#ffffff ;width: 15%;">Description</th>
			</table>
			<div id="tablediv" style="float: right; height: 25%;">
				
			</div>
			<div id="emptydiv">
				
			</div>
			<h2>Delete expense Type</h2>
			Enter Code : <input type="text" name="code" id="coded"> <font color="red">*</font><br>
			
			
			<button type="submit" name="submitbtnd" value="submitbtnd" id="submitbtnd" class = "submit">Delete</button>
			<button type="button" name="cancel" id="cncl">Cancel</button>
		
	</div>


	<div id="btngroup">
		<input type ="radio" name ="btng" id ="create" value ="create">Create
		<input type ="radio" name ="btng" id ="update" value ="update">Update
		<input type ="radio" name ="btng" id ="delete" value ="delete">Delete
	</div>
</div>

</fieldset>



</body>
</html>
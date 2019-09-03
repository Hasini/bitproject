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
				
				$("#main").hide();
				$("#createDiv").hide();
			    $("#deleteDiv").hide();
			    $("#updateDiv").hide();
			});
			$("#create").click(function(){
				$("#main").show();
		    	$("#createDiv").show();
		        $("#deleteDiv").hide();
		        $("#updateDiv").hide();
		        
		        $("#submitbtn").click(function() {
		        	var code = document.getElementById("code").value;
		        	var descr = document.getElementById("descr").value;
		        	if(code=="" || code == null || descr == ""|| descr == null){
						alert("Values are Empty");
						document.getElementById("code").focus();
					} else {
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
		  							$('input[type="text"]').val('');
		  							window.location.assign('/bitproject/admin.jsp');
		  						}else {
		  							alert(responseText.error);
		  							$('input[type="text"]').val('');
		  							window.location.assign('/bitproject/branch-details.jsp');
		  						}
		  					}   
						});
					}
				});    
		   });
		    
		    $("#update").click(function(){
		    	$("#main").show();
		    	$("#updateDiv").show();
		        $("#createDiv").hide();
		        $("#deleteDiv").hide();
		        $.ajax({
					type : 'GET',
					url : 'createaction',
					data : {
					checkboxVal : "view"
					},
					success : function(responseText) {
						for(var i=0;i<responseText.branchListObject.length;i++){
							var idvalue = responseText.branchListObject[i]["id"];
							var branch = "<option value="+idvalue+">"+responseText.branchListObject[i]["code"]+"</option>";
							$("#bcode").append (branch);
						}
				    }
				});
		        
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
		        	var e = document.getElementById("bcode");
		        	var id = e.options[e.selectedIndex].value;
		        	alert("id" + id);
		        	var descru = document.getElementById("descru").value
		        	if(descru == ""|| descru == null || id < 1){
						alert("Values are Empty");
						document.getElementById("descru").focus();
					} else {
						$.ajax({
							type : 'GET',
							url : 'createaction',
							data : {
								id : id,
								descru : descru,
								checkboxVal : "update" 
							},
							success : function(responseText) {
								if (responseText.success){
									alert("Branch Description Successfully Updated..!");
									$('input[type="text"]').val('');
		  							window.location.assign('/bitproject/main.jsp');
								}else {
									alert(responseText.error);
									$('input[type="text"]').val('');
									window.location.assign('/bitproject/branch-details.jsp');
								}
							}
						});
					}
				});
			});
		   $("#delete").click(function(){
			   $("#main").show();
		    	$("#deleteDiv").show();
		    	$("#createDiv").hide();
		    	$("#updateDiv").hide();
		    	$.ajax({
						type : 'GET',
						url : 'createaction',
						data : {
						checkboxVal : "view"
						},
						success : function(responseText) {
							for(var i=0;i<responseText.branchListObject.length;i++){
								var idvalue = responseText.branchListObject[i]["id"];
								var branch = "<option value="+idvalue+">"+responseText.branchListObject[i]["code"]+"</option>";
								$("#DCode").append (branch);
								
							}
					    }
				});
		    	
		    	$("#submitbtnd").click(function(){
		    		var e = document.getElementById("DCode");
		        	var idBranch = e.options[e.selectedIndex].value;
		    		$.ajax({
						type : 'GET',
						url : 'createaction',
						data : {
							id : idBranch,
							checkboxVal : "delete" 
						},
						success : function(responseText) {
							if (responseText.success){
								alert(responseText.success+" Branch deleted successfully..!");
								window.location.assign('/bitproject/admin.jsp');
							}else {
								alert(responseText.error);
								window.location.assign('/bitproject/branch-details.jsp');
							}
						}
					});
				});
		    	
		    });
		    $('#cncl').click(function(){
				if(confirm("do you want to reset text fields?")){
					$('input[type="text"]').val('');
					
				}					
			});
		 });
	</script>

</head>
<body style="width: 80%; height: 70%; margin-left: 0.5%;">
<div id= "main">
<fieldset >
	<legend>Branch Details</legend>
		<div id = "createDiv">
			Branch Code : <br><input type="text" name="code" id="code"> <font color="red">*</font><br>
			Branch Description : <br><input type="text" name="descr" id="descr"><font color="red">*</font><br>
			<label>Branch Address : </label><input type="text" name="add" id="add" placeholder="Address"><font color="red">*</font><br>
			<label>Branch Contact Number: </label><input type="text" name="contact" id="contact" placeholder="Contact Number"><font color="red">*</font><br>
		
			<button type="submit" name="submitbtn" value="submitbtn" id="submitbtn" class="submit">Submit</button>
			<button type="button" name="cancel" id="cncl">Reset</button>		
		</div>
	
	<div id="updateDiv">
		<label>Branch Code : </label>
		<select id="bcode" name="bcode" >
			<option>select</option>
		</select>
		<br>
		<label>Branch Name : </label><input type="text" name="descr" id="descru" placeholder="Branch Description"><font color="red">*</font><br>
		<label>Branch Address : </label><input type="text" name="add" id="add" placeholder="Address"><font color="red">*</font><br>
		<label>Branch Contact Number: </label><input type="text" name="contact" id="contact" placeholder="Contact Number"><font color="red">*</font><br>
		<button type="submit" name="submitbtnu" value="submitbtnu" id="submitbtnu" class ="submit">Update</button>
		<button type="button" name="cancel" value="Cancel" id="cncl">Reset</button>
		<button type ="submit" class ="submit" id ="view">View</button>
		<br>
		<table style="float: right;">
			<th style="background-color: #000099; color:#ffffff; width: 5%;">Code</th>
			<th style="background-color: #000099; color:#ffffff ;width: 15%;">Description</th>
		</table>
		<div id="tablediv" style="float: right;"></div>
	</div>
	<div id="deleteDiv">
		<label>Branch Code : </label>
		<select id="DCode" name="DCode" >
			<option>select</option>
		</select><font color="red">*</font>
		<br>
		
		<button type="submit" name="submitbtnd" value="submitbtnd" id="submitbtnd" class ="submit">Delete</button>
		<button type="button" name="cancel" id="cncl">Reset</button>
	</div>
</fieldset>
</div>
</body>
</html>
<%@include file="btngrp.jsp" %>
<%@include file="footer.jsp" %>
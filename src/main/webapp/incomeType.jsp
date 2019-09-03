<!DOCTYPE html>
<%@include file="header.jsp"%>

<html>
<head>
<script src="js/jquery.min.js"></script>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/commonFunctions.js"></script>

<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="icon" href="images/favicon-facebook_400x400.png">

<script>
		$(document).ready(function(){
			$('#cncl').click(function(){
				if(confirm("do you want to reset text fields?")){
					$('input[type="text"]').val('');
				}					
			});
			
			var checkboxVal;
			
			$("#createDiv").hide();
		    $("#deleteDiv").hide();
		    $("#updateDiv").hide();
		    
		    $("#create").click(function(){
		    	 $("#createDiv").show();
		        $("#deleteDiv").hide();
		        $("#updateDiv").hide();
		        
		        $("#submitbtn").click(function() {
		        	var code = document.getElementById("code").value;
					var descr = document.getElementById("descr").value;
		        	if(code=="" || code == null || descr == ""|| descr == null){
						alert("Values are Empty");
						document.getElementById("code").focus();
					}else {
			        	$.ajax({
							type : 'GET',
							url : 'incomeTypeController',
							data : {
								code : document.getElementById("code").value,
								descr : document.getElementById("descr").value,
								checkboxVal : "create"
							},
							success: function (responseText) {
		  						alert(responseText.success);
		  						window.location.assign('/bitproject/admin.jsp');
		  						if (responseText.error )
		  						alert(responseText.error);
		  						window.location.assign('/bitproject/incomeType.jsp');
		        			}   
						});
					}
				});    
		        
		    });
		    
		    $("#update").click(function(){
		    	 $("#updateDiv").show();
		        $("#createDiv").hide();
		        $("#deleteDiv").hide();
		        
		       /*  $("#view").click(function() {
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
					        var t="<tr bgcolor='#33E9FF'><td width='10%'>Branch Code</td><td width='10%'>Branch Description</td></tr>"
					       
					        for(var i=0;i<responseText.ListObject.length;i++){
					        	 	var tr="<tr width='30%'>";
						            var td1="<td width='15%'>"+responseText.ListObject[i]["code"]+"</td>";
						            var td2="<td width='15%'>"+responseText.ListObject[i]["desc"]+"</td></tr>";
						            
						           	$("#mytable").append(tr+td1+td2);
					        }
					    }
					});
				}); */
		        
		        $.ajax({
					type : 'GET',
					url : 'incomeTypeController',
					data : {
					checkboxVal : "view"
					},
					success : function(responseText) {
						for(var i=0; i<responseText.ListObject.length; i++){
							var idvalue = responseText.ListObject[i]["id"];
							var et = "<option value="+idvalue+">"+responseText.ListObject[i]["code"]+"</option>";
							$("#eT").append (et);
						 }
				    }
				});
		        $("#submitbtnu").click(function() {
		        	var e = document.getElementById("eT");
					var expenseTypeId = e.options[e.selectedIndex].value;
					var descru = document.getElementById("descru").value;
					
		        	if(descru=="" || descru == null || expenseTypeId == "select"){
						alert("Values are Empty");
						document.getElementById("descru").focus();
					}else {
					$.ajax({
						type : 'GET',
						url : 'incomeTypeController',
						data : {
							expenseTypeId : expenseTypeId,
							descru : document.getElementById("descru").value,
							checkboxVal : "update" 
						},
						success : function(responseText) {
							if (responseText.success){
								alert("Record successfully Updated..!");
								window.location.assign('/bitproject/admin.jsp');
							}else {
								alert(responseText.error);
								window.location.assign('/bitproject/incomeType.jsp');
							}
							
						}
					});
					}
				}); 
			}); 
		    $("#delete").click(function(){
		  		$("#updateDiv").hide();
		        $("#createDiv").hide();
		        $("#deleteDiv").show();
		        $.ajax({
					type : 'GET',
					url : 'incomeTypeController',
					data : {
					checkboxVal : "view"
					},
					success : function(responseText) {
						for(var i=0; i<responseText.ListObject.length; i++){
							var idvalue = responseText.ListObject[i]["id"];
							var et = "<option value="+idvalue+">"+responseText.ListObject[i]["code"]+"</option>";
							$("#eTD").append (et);
						 }
				    }
				});
		     	$("#submitbtnd").click(function(){
		     		var e = document.getElementById("eTD");
					var expenseTypeId = e.options[e.selectedIndex].value;
					
					if(expenseTypeId=="select"){
						alert("Values are Empty");
					}else {
			    		$.ajax({
							type : 'GET',
							url : 'incomeTypeController',
							data : {
								expenseTypeId : expenseTypeId,
								checkboxVal : "delete" 
							},
							success : function(responseText) {
								if (responseText.success){
									alert(responseText.success+"Record deleted successfully..!");
									window.location.assign('/bitproject/admin.jsp');
								}else {
									alert(responseText.error);
								}
							}
						});
					}
				});
			});

		 });
	</script>

<style type="text/css">
#view {
	background-color: #0000ff;
	border: none;
	color: white;
	padding: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	margin: 2px 2px;
	height: 2%;
	width: 10%;
	border-radius: 12px;
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

.submit {
	background-color: #0000ff;
	border: none;
	color: white;
	padding: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	margin: 2px 2px;
	height: 2%;
	width: 10%;
	border-radius: 12px;
}

#cncl {
	background-color: #cc0000;
	border: none;
	color: white;
	padding: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	margin: 2px 2px;
	height: 2%;
	width: 10%;
	border-radius: 12px;
}
</style>
</head>
<body style="width: 80%; height: 70%; margin-left: 0.5%">
	<div id="main">
		<fieldset>
			<legend>Income Type</legend>
				<div id="createDiv">
					Income Type Code : <br>
					<input type="text" name="code" id="code"> <font color="red">*</font><br>
					Income Type Description :<br> <input type="text" name="descr" id="descr"><font color="red">*</font><br>
					<button type="submit" name="submitbtn" value="submitbtn" id="submitbtn" class="submit">Submit</button>
					<button type="button" name="cancel" id="cncl">Reset</button>
				</div>

				<div id="updateDiv">
					
				<div>
					<label>Income Type Code : </label> 
					<select id="eT" name="eT">
						<option>select</option>
					</select>
					<font color="red">*</font><br> <br> 
					Income Type Description : <br>
					<input type="text" name="descr" id="descru"><font
						color="red">*</font><br>
					<button type="submit" name="submitbtnu" value="submitbtnu"
						id="submitbtnu" class="submit">Update</button>
					<button type="button" name="cancel" value="Cancel" id="cncl">Reset</button>
					<!-- <button type="button" id="view">View</button> -->
				</div>
				<!-- <table style="float: left;">
					<th style="background-color: #000099; color: #ffffff; width: 15%;">Code</th>
					<th style="background-color: #000099; color: #ffffff; width: 15%;">Description</th>
					<div id="tablediv" style="float: right; height: 25%;"></div>
				</table> -->
				
			</div>

			<div id="deleteDiv">
				<label>IncomeType Code : </label> 
				<select id="eTD" name="eTD">
						<option>select</option>
					</select>
					<font color="red">*</font><br> <br>
				<button type="submit" name="submitbtnd" value="submitbtnd"
					id="submitbtnd" class="submit">Delete</button>
				<button type="button" name="cancel" id="cncl">Reset</button>
				
			</div>

		</fieldset>
	</div>
</body>
</html>
<%@include file="btngrp.jsp" %>
<%@include file="footer.jsp" %>
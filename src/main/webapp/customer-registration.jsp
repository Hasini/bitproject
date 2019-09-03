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
		
		/*function clearvalues(){
			location.reload();
		}*/
		
		function validateEmail($email) {
			  if ($email != undefined){
				  var emailReg = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,}$/;
				  return emailReg.test($email);
			  }
		}
		
		function validatephn(value){
			if (value!= undefined){
				var phnno = /^[0-9 ()+-]{10,}$/;
				return phnno.test(value);
			}
		} 
		
		function validatenic(nic){
			if (nic!= undefined ){
				var nics = /[0-9]{9}[V]$/;
				return nics.test(nic);
			}
		}
		
		$(document).ready(function(){
			
			$(window).load(function(){
				 var optionsAsString;
				 var checkboxVal;
					
					$("#createDiv").hide();
				    $("#deleteDiv").hide();
				    $("#updateDiv").hide();
				    
			})
			$("#create").click(function(){
				$("#createDiv").show();
			    $("#deleteDiv").hide();
			    $("#updateDiv").hide();
			    
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
			    
			    $("#submitbtn").click(function() {
					var nic = document.getElementById("nic").value ;
					var snic = document.getElementById("snic").value ;
					var cus_initials = document.getElementById("inithc").value ;
					var sp_initials = document.getElementById("spinithc").value ;
					
					var e = document.getElementById("branch");
					var branch = e.options[e.selectedIndex].value;
					
					var shtel = document.getElementById("shtel").value;
					var hmtel = document.getElementById("hmtel").value;
					var mob = document.getElementById("mob").value;
					var stel = document.getElementById("stel").value;
					var smob = document.getElementById("smob").value;
					var fname = document.getElementById("fname").value;
					
					if( !validateEmail(document.getElementById("emal").value)){ 
						alert("Can not proceed ..invalid email");
					}else if (!validatephn(shtel) || !validatephn(hmtel) || !validatephn(mob) || !validatephn(stel) || !validatephn(smob)){
						alert("Can not proceed ..invalid phone number");
					 } else if(!validatenic(nic) || !validatenic(snic)){
						
						alert("Can not proceed ..invalid nic"); 
					} else if (nic == snic){
						alert("Can not proceed ..Main NIC is equal to other");
					}else if (fname == null || fname == undefined || fname ===""){
						$("#errormsg").html("Please fill mandotary values");
					}else {
						$.ajax({
							type : 'GET',
							url : 'CustomerRegistrationController',
							data : {
								method : "createCus",
								branch : branch,
								fname : document.getElementById("fname").value,
								cus_initials : cus_initials,
								cus_othername : document.getElementById("cus_othername").value,
								shad1 : document.getElementById("shad1").value,
								shad2 : document.getElementById("shad2").value,
								shad3 : document.getElementById("shad3").value,
								hmad1 : document.getElementById("hmad1").value,
								hmad2 : document.getElementById("hmad2").value,
								shtel : document.getElementById("shtel").value,
								hmtel : document.getElementById("hmtel").value,
								mob : document.getElementById("mob").value,
								hmad3 : document.getElementById("hmad3").value,
								nic : nic, 
								emal : document.getElementById("emal").value,
								sfname : document.getElementById("sfname").value,
								sp_initials : sp_initials,
								sp_othername : document.getElementById("sp_othername").value, 
								sad1 : document.getElementById("sad1").value,
								sad2 : document.getElementById("sad2").value,
								sad3 : document.getElementById("sad3").value,
								stel : document.getElementById("stel").value,
								smob : document.getElementById("smob").value,
		 						 snic: snic,
								semail : document.getElementById("semail").value
							},
							error: function (responseText){
								alert("An error occured..!");
								
			            	},
							success: function (responseText) {
								if (responseText.error){
									alert(responseText.error);
									$('input[type="text"]').val('');
								}
								if (responseText.suc){
									alert(responseText.suc);
									$('input[type="text"]').val('');
									window.location.assign('/bitproject/login.jsp');
									location.reload();
									
								}
							}   
							
						});
					}
				}); 
			})
			
			$("#update").click(function(){
				$("#createDiv").hide();
			    $("#deleteDiv").hide();
			    $("#updateDiv").show();
			    
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
							$("#branchu").append (branch);
						 }
						
						for(var i=0;i<responseText.cusObj.length;i++){
							var idvalue = responseText.cusObj[i]["customer_id"];
							var nic = "<option value="+idvalue+">"+responseText.cusObj[i]["cus_nic"]+"</option>";
							$("#customers").append (nic);
						 }
						
					}   
				}); 
			    
			    function loadAllcustomers() {
			    	  var cus_nic = document.getElementById("customers").value;
			    	  
			    	  $.ajax({
							type : 'GET',
							url : 'CustomerRegistrationController',
							data : {
								method : "loadCustomersByNic",
								nic : cus_nic
								
							},
							success : function(responseText) {
								
							}   
					});
			    	  
			    }
			    
			    $("#upbtn").click(function() {
					var e = document.getElementById("customers");
					var nic = e.options[e.selectedIndex].value;
					alert(nic);
					var shoptel = document.getElementById("shtel").value; 
					var hometel = document.getElementById("hmtel").value;
					var mobileno = document.getElementById("mob").value;
					var spouseAdd1 = document.getElementById("sad1").value;
					var spouseAdd2 = document.getElementById("sad2").value;
					var spouseAdd3 = document.getElementById("sad3").value;
					var spouseHomeTel = document.getElementById("stel").value;
					var spouseMobileNo = document.getElementById("smob").value;
					
					$.ajax({
						type : 'GET',
						url : 'CustomerRegistrationController',
						data : {
							method : "deleteCus",
							nic : nic,
							
							
						},
						error: function (responseText){
							alert("An error occured..!");
							
		            	},
						success: function (responseText) {
							if (responseText.error){
								alert(responseText.error);
								$('input[type="text"]').val('');
							}
							if (responseText.succ){
								alert(responseText.suc);
								window.location.assign('/bitproject/login.jsp');
								$('input[type="text"]').val('');
								location.reload();
							}
						}   
						
					});
					
				});
			    
			    
				
			});
			
			$("#delete").click(function(){
				$("#createDiv").hide();
			    $("#deleteDiv").show();
			    $("#updateDiv").hide();
			    
			    $.ajax({
					type : 'GET',
					url : 'CustomerRegistrationController',
					data : {
						method : "view"
					},
					success : function(responseText) {
						alert (responseText);
						for(var i=0;i<responseText.cusObj.length;i++){
							var idvalue = responseText.cusObj[i]["customer_id"];
							var nic = "<option value="+idvalue+">"+responseText.cusObj[i]["cus_nic"]+"</option>";
							$("#customers").append (nic);
						 }
						
					}   
				});
			    
			    $.ajax({
					type : 'GET',
					url : 'CustomerRegistrationController',
					data : {
						method : "viewallCus"
					},
					success : function(responseText) {
						for (var i = 0; i < responseText.cusObj.length; i++) {
							var cusId = responseText.cusObj[i]["customer_id"];
							var customer = "<option value=" + cusId + ">" + responseText.cusObj[i]["cus_fullname"] + "</option>";
							$("#customer").append(customer);
						}
					}
				});
				
			});
			$("#delbtn").click(function() {
				var reason = document.getElementById("reason").value  ;
				
				var e = document.getElementById("customers");
				var nic = e.options[e.selectedIndex].value;
				
				$.ajax({
					type : 'GET',
					url : 'CustomerRegistrationController',
					data : {
						method : "deleteCus",
						nic : nic,
						reason : reason
						
					},
					error: function (responseText){
						alert("An error occured..!");
						
	            	},
					success: function (responseText) {
						if (responseText.error){
							alert(responseText.error);
							$('input[type="text"]').val('');
							window.location.assign('/bitproject/customer-registration.jsp');
						}
						if (responseText.succ){
							alert(responseText.suc);
							window.location.assign('/bitproject/dp.jsp');
							$('input[type="text"]').val('');
							location.reload();
						}
					}   
					
				});
				
			});
			$('#cncl').click(function(){
				if(confirm("do you want to reset text fields?")){
					$('input[type="text"]').val('');
					
				}					
			});
		});
		</script>
		
		<style type="text/css">
		
		
		</style>
		
		
	</head>
<body style="width: 80%; height: 70%; margin-left: 0.5%;">
<div id="main">
	<div>
		<fieldset>
		<legend>Customer Registration</legend>
		<div id="createDiv">
			Branch : <select id="branch" name="branch" >
				<option>select</option>
			</select>
			<br><br>
			Initials :<br><input type="text" class="minitext" id="inithc"><br>
			Name Denoted by Initials :<br> <input type="text" class="middletext" id="cus_othername"><br>
			First Name : <br><nobr><input type="text" id="fname" class="mandotaryInputs" ><font style="color: red;">*</font>
			</nobr><br><br>
			<hr>
			<b>Shop Details :</b><br><br><br>
			Address Line 1:<br><nobr><input type="text" id="shad1"><font style="color: red;">*</font></nobr><br>
			Address Line 2:<br><nobr><input type="text" id="shad2"><font style="color: red;">*</font></nobr><br>
			Address Line 3:<br><nobr><input type="text" id="shad3"><font style="color: red;">*</font></nobr><br>
			Home Address :<br>
			Address Line 1:<br><nobr><input type="text" id="hmad1"><font style="color: red;">*</font></nobr><br>
			Address Line 2:<br><nobr><input type="text" id="hmad2"><font style="color: red;">*</font></nobr><br>
			Address Line 3:<br><nobr><input type="text" id="hmad3"><font style="color: red;">*</font></nobr><br><br>
			Shop Tel : <br><nobr><input type="tel" id="shtel" class="mandotaryInputs" maxlength="10"><font style="color: red;">*</font></nobr><br>
			Home Tel : <br><nobr><input type="tel" id="hmtel"><font style="color: red;">*</font></nobr><br><br><br>
			Mobile No : <br><nobr><input type="tel" id="mob"><font style="color: red;">*</font></nobr><br>
			NIC Number :<br>
			<input type="text" class="minitext" id="nic">  
			<br>
			E-Mail : <br><nobr><input type="email" id="emal"></nobr><br><br>
			<hr>
			<h4>Spouse / Shareholder (main) Details</h4>
			
			First Name : <br><nobr><input type="text" id="sfname"><font style="color: red;">*</font></nobr><br>
			Initials : <br><input type="text" class="minitext" style="width: 60px;" id="spinithc"><br>
			Name Denoted by Initials :<br> <input type="text" class="middletext" id="sp_othername"><br><br>
			
			Home Address :
			Address Line 1:<br><nobr><input type="text" id="sad1"><font style="color: red;">*</font></nobr><br>
			Address Line 2:<br><nobr><input type="text" id="sad2"><font style="color: red;">*</font></nobr><br>
			Address Line 3:<br><nobr><input type="text" id="sad3"><font style="color: red;">*</font></nobr><br>
			Home Tel : <br><nobr><input type="tel" id="stel"><font style="color: red;">*</font></nobr><br>
			Mobile No : <br><nobr><input type="tel" id="smob"><font style="color: red;">*</font></nobr><br>
			Spouse/Shareholder NIC Number :<br><input type="text" class="minitext" id="snic">  
			<br>
			E-Mail :<br><nobr> <input type="email" id="semail"></nobr>
			<br/>
			<button type="submit" class = "submit" id="submitbtn"> Submit</button>
			<button type="button" id="cncl" onclick="clearvalues();">Reset</button>
			<br>
			<div id="errormsg" style="color:red;"></div>
		</div>	
		
		<div id="updateDiv">
			Branch : <select id="branchu" name="branchu" >
				<option>select</option>
			</select>
			
			Customer : <select id="customers" name="customers" onchange="loadAllcustomers()">
				<option>select</option>
			</select>
			
			<br><br>
			Initials :<br> <input type="text" class="minitext" style="width: 60px;" id="inioc" disabled="disabled"> 
			<br>
			Name Denoted by Initials :<br> <input type="text" class="middletext" id="cus_othername" disabled="disabled"><br>
			First Name : <br><nobr><input type="text" id="fname" class="mandotaryInputs" disabled="disabled"><font style="color: red;">*</font></nobr><br>
			
			<b>Shop Address :</b>><br>
			Address Line 1:<br><nobr><input type="text" id="shad1" disabled="disabled"><font style="color: red;">*</font></nobr><br>
			Address Line 2:<br><nobr><input type="text" id="shad2" disabled="disabled"><font style="color: red;">*</font></nobr><br>
			Address Line 3:<br><nobr><input type="text" id="shad3" disabled="disabled"><font style="color: red;">*</font></nobr><br>
			Home Address :<br>
			Address Line 1:<br><nobr><input type="text" id="hmad1" disabled="disabled"><font style="color: red;">*</font></nobr><br>
			Address Line 2:<br><nobr><input type="text" id="hmad2" disabled="disabled"><font style="color: red;">*</font></nobr><br>
			Address Line 3:<br><nobr><input type="text" id="hmad3" disabled="disabled"><font style="color: red;">*</font></nobr><br><br>
			Shop Tel : <br><nobr><input type="tel" id="shtel" class="mandotaryInputs" maxlength="10"><font style="color: red;">*</font></nobr><br>
			Home Tel : <br><nobr><input type="tel" id="hmtel"><font style="color: red;">*</font></nobr><br><br><br>
			Mobile No : <br><nobr><input type="tel" id="mob"><font style="color: red;">*</font></nobr><br>
			NIC Number :<br>
			<input type="text" class="minitext" id="o" style="width: 50px;"disabled="disabled" ><br>
			E-Mail : <br><nobr><input type="email" id="emal" disabled="disabled"></nobr><br>
			<hr>
			<h4>Spouse / Shareholder (main)</h4> <br>
			
			First Name : <br><nobr><input type="text" id="sfname" disabled="disabled"><font style="color: red;">*</font></nobr><br>
			Initials : <br><input type="text" class="minitext" style="width: 60px;" id="iniosp" disabled="disabled"> 
			<br>
			Name Denoted by Initials :<br> <input type="text" class="middletext" id="sp_othername" disabled="disabled"><br><br>
			
			Home Address :
			Address Line 1:<br><nobr><input type="text" id="sad1"><font style="color: red;" disabled="disabled">*</font></nobr><br>
			Address Line 2:<br><nobr><input type="text" id="sad2"><font style="color: red;" disabled="disabled">*</font></nobr><br>
			Address Line 3:<br><nobr><input type="text" id="sad3"><font style="color: red;" disabled="disabled">*</font></nobr><br>
			Home Tel : <br><nobr><input type="tel" id="stel"><font style="color: red;" >*</font></nobr><br>
			Mobile No : <br><nobr><input type="tel" id="smob"><font style="color: red;">*</font></nobr><br>
			NIC Number :<br><input type="text" class="minitext" id="os" style="width: 50px;" maxlength="2" disabled="disabled">   
			<br>
			E-Mail :<br><nobr> <input type="email" id="semail" disabled="disabled"></nobr>
			<br/>
			
			<button type="submit" class = "submit" id="upbtn"> Update</button>
			<button type="button" id="cncl" onclick="clearvalues();">Cancel</button>
			<br>
			<div id="errormsg" style="color:red;"></div>
		</div>
		
		
		<div id="deleteDiv">
			Customer NIC : <select id="customers" name="customers" onchange="loadAllcustomers()">
				<option>select</option>
			</select>
			Customer Name: 
			<select id="customer" name="customer" >
				<option>select</option>
			</select>
			<br><br>
			<label>Customer arrears <br></label><input type="text" name="ap" id="ap" disabled><br><br>
			Reason:
			<br><nobr><input type="text" id="reason"><font style="color: red;">*</font></nobr><br> 
			
			<button type="submit" class = "submit" id="delbtn"> Delete</button>
			<button type="button" id="cncl">Reset</button>
			<br>
			
		</div>
	</fieldset>
	</div>
</div>
<body>

</body>
</html>
<%@include file="btngrp.jsp" %>
<%@include file="footer.jsp" %>
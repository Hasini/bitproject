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
	
	<script>
		$(document).ready(function(){
			$('#cncl').click(function(){
				$('input[type="text"]').val('');
			});
			$('#back').click(function(){
				window.location.assign('/bitproject/cshbook.jsp');
			});
			
			$("#submain").show();
		    $("#btngroup").show();
			$("#inspayDiv").hide();
		    $("#partiallypayDiv").hide();
		    $("#reshedulingDiv").hide();
		    $(".alert").hide();
		    
		    $(window).load(function() {
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
		    
		    $('#customer').change(function() {
				var method;
				var cus = document.getElementById("customer");
				var cusid = cus.options[cus.selectedIndex].value;
				var br = document.getElementById("branch");
				var branch = br.options[br.selectedIndex].value;
				$.ajax({
					type : 'GET',
					url : 'CshbookCotroller',
					data :{
						method : "viewMP",
						customer : cusid
					},
					success: function (responseText) {
						document.getElementById("ar").value=responseText.totarr;
						if (responseText.totarr == 0){
							document.getElementById("mp").value = 0;
						}else {
							document.getElementById("mp").value=responseText.mp;
						}
						if (responseText.totarr >= 100000.00){
							if(!confirm("Customer is blacklisted. Do you agree to pay before buy?")){
								window.location.assign('/bitproject/cbnew.jsp');
							}
							
							
						}
					}
				});
			});
		    
		    $("#inspaychx").click(function(){
		    	
		        $("#inspayDiv").show();
		        $("#partiallypayDiv").hide();
		        $("#reshedulingDiv").hide();
		        
		        $("#inspaybtn").click(function() {
					var method;
					var paytype ;
					
					var br = document.getElementById("branch");
					var branch = br.options[br.selectedIndex].value;
					var cus = document.getElementById("customer");
					var cusid = cus.options[cus.selectedIndex].value;
					var inspay = document.getElementById("inspay").value;
					var noofins = document.getElementById("noofins").value;
					var installmentamt = document.getElementById("mp").value;
					
					if (!inspay.length || !noofins.length || cusid == 0 || branch == 'select'){
						alert ("Please fill all mandotory fields to do transactions..!");
					}else{
						$.ajax({
							type : 'GET',
							url : 'CshbookCotroller',
							data :{
								method : "createCB",
								inspay :inspay,
								branch :branch,
								customer :cusid,
								noofins :noofins,
								paytype:"I"
							},
							success: function (responseText) {
								if (responseText.success){
									document.getElementById("inst").value=responseText.re;
									document.getElementById("nd").value=responseText.nd;
									//todo
									document.getElementById("remainpy").value = responseText.remainpay;
									$('input[type="text"]').val('');
									if (responseText.remainpay == 0){
										alert("Customer has totally paid the installment amounts");
										$('input[type="text"]').val('');
									}else{
										$("#alert").show();
										$('input[type="text"]').val('');
									}
								}else {
									alert (responseText.error);
									$('input[type="text"]').val('');
								}
							}
						});
					}
						
			 		});
		    });
		    
			$("#dailytrans").click(function(){
				
		        $("#inspayDiv").hide();
		        $("#partiallypayDiv").show();
		        $("#reshedulingDiv").hide();
		       	$("#ppbtn").click(function() {
		       		alert("daiy transasas");
					var method;
					
					var br = document.getElementById("branch");
					var branch = br.options[br.selectedIndex].value;
					var cus = document.getElementById("customer");
					var cusid = cus.options[cus.selectedIndex].value;
					var bamt = document.getElementById("bamt").value;
					var payamt = document.getElementById("tp").value;
					var totarr = document.getElementById("ar").value;
					var paytype;
					var creditLimit = getCreditLimit(cusid);
					
					if (branch == 'select'){
						alert ("Please select customer's branch..!");
					}
						
					if (cusid == 0)
						alert ("Please select a customer..!");
					if (!bamt.length)
						alert ("Please enter bill amount..!");
					if (!payamt.length)
						alert ("Please enter today pay amount..!");
					
					if (!payamt.length && !bamt.length && cusid == 0 && branch == 'select'){
						alert ("Please fill all mandotory fields to do transactions..!");
					}if (totarr > creditLimit){
						alert("Arrears amount is exceeded creditLImit. Please shedule a payment plan to proceed..!");
					}else{
						alert("asas");
						$.ajax({
							type : 'GET',
							url : 'CshbookCotroller',
							data :{
								method : "createCB",
								billAmt : bamt,
								payAmt : payamt,
								branch :branch,
								customer : cusid,
								paytype : "P"
							},
							success: function (responseText) {
								document.getElementById("ndt").value=responseText.nd;
								document.getElementById("arr").value=responseText.todarr;
								document.getElementById("epaid").value=responseText.extrapay;
								var exarr = parseFloat($("#ar").val()) || 0;
				        		var todarr = parseFloat($("#arr").val()) || 0;
				        		var extrapay = parseFloat($("#epaid").val()) || 0;
				        		var totarr = 0;
				        		var tott = exarr+todarr;
				        		if (tott <= extrapay){
				        			totarr = 0;
				        		}else{
				        			totarr = exarr+todarr-extrapay;
				        		}
								if (responseText.success){
									$("#alert").show();
									document.getElementById("ar").value=totarr;
									//window.location.assign('/bitproject/cbnew.jsp');
									document.getElementById("bamt").value = "";
									document.getElementById("tp").value="";
									//$('input[type="text"]').val('');
									
								}else {
									alert (responseText.error);
									$('input[type="text"]').val('');
								}
						}
					});
					}
							
			 		});
		 		});
			
			
				$("#rs").click(function(){
					$("#inspayDiv").hide();
		        	$("#partiallypayDiv").hide();
		        	$("#reshedulingDiv").show();
		        	$("#showbtn").click(function() {
		        		var cus = document.getElementById("customer");
						var cusid = cus.options[cus.selectedIndex].value;
						var m = document.getElementById("mode");
						var mode =m.options[m.selectedIndex].value;
						var arr = document.getElementById("ar").value
						var newins = arr/mode;
						document.getElementById("reschins").value = newins;
						$("#resbtn").click(function() {
							$.ajax({
								type : 'POST',
								url : 'PaymentResheduleController',
								data :{
									method : "createLendReschdule",
									newins : newins,
									mode : mode,
									cusid : cusid
								},
								success: function (responseText) {
									if (responseText.success){
										alert(responseText.success)
										$('input[type="text"]').val('');
									}else {
										alert("error");
										$('input[type="text"]').val('');
									}
							}
						});
					});
		        });		
		 	});
				
				
		});
		
		</script>
		
		<style type="text/css">
			body{
  				font-family:sans-serif;
			}
			text{
				width: 600px;
			}
			label{
				width: 200px;
			}
			
			#wrapper{
  			
  				display: inline-block;
  				padding:20px;
  			}
  			
			#btngroup{
				border: 1px solid #888;
  				display: inline-block;
  				padding:20px;
			}
		
a.button {
    		-webkit-appearance: button;
    		-moz-appearance: button;
    		appearance: button;

    		text-decoration: none;
    		color: initial;
}

	.alert {
		  padding: 20px;
		  background-color: #00ab66;
		  color: white;
		  height: 10%;
		  width: 60%;
		  margin-left: 10%;
	}

	.closebtn {
	  margin-left: 20px;
	  color: white;
	  font-weight: bold;
	  float: right;
	  font-size: 22px;
	  line-height: 20px;
	  cursor: pointer;
	  transition: 0.3s;
	}

	.closebtn:hover {
  		color: black;
	}
		
		</style>
</head>
<body style="width: 80%; height: 70%; margin-left: 0.5%;">
<div class="alert">
  <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
   Transaction successfully done..!
</div>
	<div id="main">
	<fieldset>
		<legend>Transaction Summary</legend>
		<div id = "submain">
			<label><b>Branch :</b> </label> <select id="branch" name="branch" class="dropdownfields">
					<option>select</option>
				</select>
				
				<label><b>Customer/Shop :</b> </label> 
				<select id="customer" name="customer" class="dropdownfields">
					<option value="0">Select</option>
				</select>
				<br><br><br>
				<nobr><label><b>Customer Current Installment:</b> <br>
				</label><input type="text" name="mp" id="mp" disabled>
				<font color="red">*</font></nobr></br>
				<nobr><label><b>Customer Current Arrears Amount:</b><br> </label><input type="text" name="ar" id="ar" disabled>
				<font color="red">*</font></nobr>
		</div>
	</fieldset>
	<div style="height: 30px;"></div>
	
	<div id="inspayDiv">
	<fieldset>
		<legend>Installment Payment</legend>
			<nobr><label><b>Today Installment Payment Amount:</b> <br></label><input type="text" name="inspay" id="inspay">
			<font color="red">*</font></nobr><br>
			<nobr><label><b>No of installments:</b> <br></label><input type="text" name="noofins" id="noofins">
			<font color="red">*</font></nobr><br>
			<br><br>
			<button type="button" name="inspaybtn" value="inspaybtn" id="inspaybtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl">Reset</button>
			<button type="button" name="refrsh" value="refrsh" id="refrsh" onclick="" class="submit">Refresh</button>
			<br><br>
			<nobr><label style="color: gray;"><b>Remaining Installment Payment Amount:</b> <br></label><input type="text" name="remainpy" id="remainpy" >
			</nobr><br>
			<nobr><label style="color: gray;"><b>Number of Remaining Installments:</b> <br></label><input type="text" name="inst" id="inst" >
			</nobr><br>
			<nobr><label style="color: gray;"><b>Next Due Date:</b> <br></label><input type="text" name="nd" id="nd">
			</nobr><br>
	</fieldset>
	</div>
	<div id="partiallypayDiv">
	<fieldset>
	<legend>Daily Transaction</legend>
		<nobr><label><b>Today Bill Amount	:</b> </label><br><input type="text" name="bamt" id="bamt">
		<font color="red">*</font></nobr><br>
		<nobr><label><b>Today Payment	:</b> </label><br><input type="text" name="tp" id="tp">
		<font color="red">*</font></nobr><br>
		<button type="button" name="ppbtn" value="ppbtn" id="ppbtn" class="submit">Submit</button>
		<button type="button" name="clear" value="clear" id="cncl">Reset</button>
		
		<div id="wrapper">
  			<label>Do you wish to schedule an installment plan to arrears amount?</label>
  			<a href="lending_shedule.jsp" class="button" style="background-color: blue;color: white;">Yes</a>
  			<a href="dp.jsp" class="button" style="background-color: red;color: white;">No</a>
		</div>
		<div id = "inq">
			<label style="color: gray;"><b>Next Delivery Date:</b> </label><br><input type="text" name="ndt" id="ndt" disabled="disabled">
			<br>
			<label style="color: gray;"><b>Today Arrears Amount:</b> </label><br><input type="text" name="arr" id="arr" disabled="disabled">
			<br>
			<label style="color: gray;"><b>Extra payment:</b> </label><br><input type="text" name="epaid" id="epaid" disabled="disabled">
		</div>
		</fieldset>
	</div>
	
	<div id="reshedulingDiv">
	<fieldset>
		<legend>Reschedule</legend>
		<nobr><label><b>Rescheduled No of installments:</b> </label>
			<select id="mode" name="mode" class="dropdownfields">
				<option value="0">Select</option>
				<option value="1">01</option>
				<option value="2">02</option>
				<option value="3">03</option>
				<option value="4">04</option>
				<option value="5">05</option>
				<option value="6">06</option>
				<option value="7">07</option>
				<option value="8">08</option>
				<option value="9">09</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
			<font color="red">*</font></nobr><br><br>
			<button type="button" name="showbtn" value="showbtn" id="showbtn" class="showbtn">Show me my Rescheduled amount</button><br>
			
			<label><b>Rescheduled Installment :</b> </label></br><input type="text" name="reschins" id="reschins" disabled>
			<br>
			<button type="button" name="resbtn" value="resbtn" id="resbtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="back">Back</button>
			<br>
		</fieldset>
	</div>
</div>
</body>
</html>
<%@include file="lendingbtngrp.jsp" %>
<%@include file="footer.jsp" %>
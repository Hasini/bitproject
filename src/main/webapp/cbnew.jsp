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
			
			$("#submain").show();
		    $("#btngroup").show();
			$("#inspayDiv").hide();
		    $("#partiallypayDiv").hide();
		    
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
				$.ajax({
					type : 'GET',
					url : 'CashBkController',
					data :{
						method : "viewMP",
						customer : cusid
					},
					success: function (responseText) {
						
						document.getElementById("mp").value=responseText.mp;
						document.getElementById("ar").value=responseText.totarr;
						if (responseText.totarr>=100000.00){
							alert("Customer is blacklsted until settle the arrears...!")
						}
					}
				});
			});
		    
		    $("#aa").click(function(){
		    	
		        $("#inspayDiv").show();
		        $("#partiallypayDiv").hide();
		        
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
						$.ajax({
								type : 'GET',
								url : 'CashBkController',
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
										//inst rinpa nd
										var sucmsg = $('#msg'); 
										sucmsg.show().html(responseText.success);
										document.getElementById("inst").value=responseText.re;
										document.getElementById("nd").value=responseText.nd;
										//todo
										document.getElementById("rinpa").value = 0;
									}else {
										alert (responseText.error);
									}
							}
						});
			 		});
		    });
		    
			$("#bb").click(function(){
				
		        $("#inspayDiv").hide();
		        $("#partiallypayDiv").show();
		        
		        $("#ppbtn").click(function() {
					var method;
					
					var br = document.getElementById("branch");
					var branch = br.options[br.selectedIndex].value;
					
					var cus = document.getElementById("customer");
					var cusid = cus.options[cus.selectedIndex].value;
					var bamt = document.getElementById("bamt").value;
					var payamt = document.getElementById("tp").value;
					var paytype;
					//var arr = document.getElementById("ar").value;
					
							$.ajax({
								type : 'GET',
								url : 'CashBkController',
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
									var totarr = exarr+todarr-extrapay;
									
									if (responseText.success){
										var sucmsg = $('#msg'); 
										sucmsg.show().html(responseText.success);
										document.getElementById("ar").value=totarr;
										//window.location.assign('/bitproject/cbnew.jsp');
									}else {
										alert("error");
										alert (responseText.error);
									}
							}
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
		
		</style>
</head>
<body>
	<fieldset>
	<legend>Cash Book</legend>

<div id = "msg" style="color: green;"></div>
<div id="main">
	
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
	
	<div style="height: 10px;"></div>
	<div id="inspayDiv">
	<fieldset>
		<legend>Installment Payment</legend>
		
			<nobr><label><b>Today Installment Payment Amount:</b> <br></label><input type="text" name="inspay" id="inspay">
			<font color="red">*</font></nobr><br>
			<nobr><label><b>No of installments:</b> <br></label><input type="text" name="noofins" id="noofins">
			<font color="red">*</font></nobr><br>
			<br><br>
			<button type="button" name="inspaybtn" value="inspaybtn" id="inspaybtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button><br><br>
			<nobr><label><b>Number of Remaining Installments:</b> <br></label><input type="text" name="inst" id="inst" >
			</nobr><br>
			<nobr><label><b>Remaining Installment Payment Amount:</b> <br></label><input type="text" name="rinpa" id="rinpa" >
			</nobr><br>
			<nobr><label><b>Next Due Date:</b> <br></label><input type="text" name="nd" id="nd" >
			</nobr><br>
			
			
	</fieldset>
	</div>
	
	<div id="partiallypayDiv">
	<fieldset>
		<legend>Partial Payment / Daily Transactions</legend>
	
			<nobr><label><b>Today Bill Amount	:</b> </label><input type="text" name="bamt" id="bamt">
			<font color="red">*</font></nobr><br>
			<nobr><label><b>Today Payment	:</b> </label><input type="text" name="tp" id="tp">
			<font color="red">*</font></nobr><br>
			

			<button type="button" name="ppbtn" value="ppbtn" id="ppbtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
			<br>
			<label><b>Next Due Date:</b> </label><input type="text" name="ndt" id="ndt" disabled="disabled">
			<br>
			<label><b>Today Arrears Amount:</b> </label><input type="text" name="arr" id="arr" disabled="disabled">
			<br>
			<label><b>Extra payment:</b> </label><input type="text" name="epaid" id="epaid" disabled="disabled">
			<br>
			<div id="wrapper">
  				<label>Do you wish to schedule an installment plan to arrears amount?</label>
  				<a href="lending_shedule.jsp" class="button">Yes</a>
  				<a href="dp.jsp" class="button">No</a>
			</div>
	
	</fieldset>
	</div>

	<div id="btngroup">
			<input type="radio" name="aa" id="aa" checked>Installment Payment
			<input type="radio" name="aa" id="bb">Partial Payment
	</div>
</div>

</fieldset>

</body>
</html>
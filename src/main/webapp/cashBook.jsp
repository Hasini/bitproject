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


<script type="text/javascript">

	$(document).ready(function() {
		
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
				url : 'CashBookController',
				data :{
					method : "viewMP",
					customer : cusid
				},
				success: function (responseText) {
						document.getElementById("mp").value=responseText.mp;
						document.getElementById("ar").value=responseText.ta;
						if (responseText.ta>=100000.00){
							alert("Customer is blacklsted until settle the arrears...!")
						}
					
				}
			});
			
			
		});
		
		$( "#tobp" ).hover(function() {
			
	        var mp = parseFloat($("#mp").val()) || 0;
	        var arr = parseFloat($("#ar").val()) || 0;
	        var bill = parseFloat($("#bamt").val()) || 0;
	        var pay = parseFloat($("#pamt").val()) || 0;

	        var tobp = (mp+arr+bill)-pay;

	        if (!isNaN(tobp) && tobp !== Infinity) {
	        	document.getElementById("tobp").value=tobp;
	            
	        }
	    });
		
		$("#cbbtn").click(function() {
			var method;
			
			var br = document.getElementById("branch");
			var branch = br.options[br.selectedIndex].value;
			
			var cus = document.getElementById("customer");
			var cusid = cus.options[cus.selectedIndex].value;
			var mp = document.getElementById("mp").value;
			var payamt = document.getElementById("pamt").value;
			var arr = document.getElementById("ar").value;
			
			if (payamt<mp){
				alert("Payment should be greater than installment payment.");
			}else if(arr >=100000){
				alert("Sorry..!Can not do further transactions");
			}else{
					$.ajax({
						type : 'GET',
						url : 'CashBookController',
						data :{
							method : "createCB",
							billAmt : document.getElementById("bamt").value,
							payAmt : document.getElementById("pamt").value,
							branch :branch,
							customer : cusid,
							/////passing values to arrears table
							modepayment :document.getElementById("mp").value,
							arrears :arr,
							tobepaid:document.getElementById("tobp").value
						},
						success: function (responseText) {
							if (responseText.success){
								alert (responseText.success);
								window.location.assign('/bitproject/dp.jsp');
							}else {
								alert (responseText.error);
							}
					}
				});
			}
		});
 	});
</script>


<style type="text/css">

.dropdownfields{
	 width: 75%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}



label{
	width: 15%;
}


</style>


</head>
<body>
	<div id="main">
		
			<fieldset>
				<legend>Cash Book</legend>
			
			 <label><b>Branch :</b> </label> <select id="branch" name="branch" class="dropdownfields">
				<option>select</option>
			</select>
			<br/>
			<label><b>Customer/Shop :</b> </label> 
			<select id="customer" name="customer" class="dropdownfields">
				<option value="0">Select</option>
			</select>
			
			<nobr><label><b>Today Bill Amount	:</b> </label><input type="text" name="bamt" id="bamt">
			<font color="red">*</font></nobr>
			<nobr><label><b>Installment Payment:</b> </label><input type="text" name="mp" id="mp" disabled>
			<font color="red">*</font></nobr>
			<nobr><label><b>Total Arrears Amount:</b> </label><input type="text" name="ar" id="ar" disabled>
			<font color="red">*</font></nobr>
			<nobr><label><b>Total Paid Amount	:</b></label><input type="text" name="pamt" id="pamt" required><font color="red">*</font></nobr><br>
			<nobr><label><b> To be paid:</b> </label><input type="text" name="tobp" id="tobp" disabled><font color="red">*</font></nobr><br>
			
			<button type="button" name="cbbtn" value="cbbtn" id="cbbtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
			</fieldset>
		
	</div>


</body>
</html>
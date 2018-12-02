<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
<head>
<title>Bill Info</title>

<script src="js/jquery.min.js"></script>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/commonFunctions.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="icon" href="images/favic.jpg">
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
						var cshbid = "<option value=" + cusId + ">" + responseText.cusObj[i]["cus_nic"] + "</option>";
						$("#cshbid").append(cshbid);
					}
				}
			});
		});
		
		$('#cshbid').change(function() {
			var method;
			var custcashbookid = $('#cshbid :selected').text();
			$.ajax({
				type : 'POST',
				url : 'LendingSheduleController',
				data :{
					method : "viewDet",
					custcashbookid : custcashbookid
				},
				success: function (responseText) {
					if (responseText.arr){
						document.getElementById("aramt").value=responseText.arr;
					}else {
						alert("No arrears for the customer..")
						//alert (responseText.error);
					}
				}
			});
			
			
		});
		
		$("#mode").bind("change", function(e) {
	        var arr = parseFloat($("#aramt").val()) || 0;
	        var m = document.getElementById("mode");
			var mode =m.options[m.selectedIndex].value || 0;

	        var mpval = arr / mode;

	        if (!isNaN(mpval) && mpval !== Infinity) {
	        	document.getElementById("mpay").value=mpval;
	            
	        }
	    });
		$("#cbbtn").click(function() {
			var method;
			var custcashbookid = $('#cshbid :selected').text();
			
			var m = document.getElementById("mode");
			var mode =m.options[m.selectedIndex].value;
			
			$.ajax({
				type : 'POST',
				url : 'LendingSheduleController',
				data :{
					method : "sheduleLoan",
					mode:mode,
					custcashbookid : custcashbookid
				},
				success: function (responseText) {
					if(responseText.msg1){
						alert(responseText.msg1);
					}else if (responseText.msg){
						alert (responseText.msg);
					}else {
						alert("as");
					}
					window.location.assign('/bitproject/dp.jsp');
				}
			});
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
		<form >
			<fieldset>
				<legend>Lend Shedule</legend>
			
			
			<label><b>Cash Book Id:</b> </label> 
			<select id="cshbid" name="cshbid" class="dropdownfields">
				<option value="0">Select</option>
			</select>
			
			<nobr><label><b>Total Arrears Amount :</b> </label><input type="text" name="aramt" id="aramt">
			<font color="red">*</font></nobr>
			<nobr><label><b>Number of installments:</b> </label> 
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
			<font color="red">*</font></nobr></br>
			<label><b>Installment :</b> </label><input type="text" name="mpay" id="mpay" disabled>
			
			
			
			<button type="submit" name="cbbtn" value="cbbtn" id="cbbtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
			</fieldset>
		</form>
	</div>


</body>
</html>
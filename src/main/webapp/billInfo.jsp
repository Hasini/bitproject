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
				type : 'post',
				url : 'BillInfoController',
				data : {
					method : "viewUsers"
				},
				success : function(responseText) {
					for (var i = 0; i < responseText.userObj.length; i++) {
						var userId = responseText.userObj[i]["id"];
						var subusers = "<option value=" + userId + ">" + responseText.userObj[i]["username"] + "</option>";
						$("#subusers").append(subusers);
						
					}

				}
			});
		});
		
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
		
		$(window).load(function() {
			$.ajax({
				type : 'post',
				url : 'BillInfoController',
				data : {
					method : "viewExpenseTypes"
				},
				success : function(responseText) {
					for (var i = 0; i < responseText.expenseTypeObj.length; i++) {
						var Id = responseText.expenseTypeObj[i]["et_id"];
						var expensetype = "<option value=" + Id + ">" + responseText.expenseTypeObj[i]["et_code"] + "</option>";
						$("#expensetype").append(expensetype);
					}

				}
			});
		});
		
		$(window).load(function() {
			$.ajax({
				type : 'post',
				url : 'BillInfoController',
				data : {
					method : "viewIncomeTypes"
				},
				success : function(responseText) {
					for (var i = 0; i < responseText.incomeTypeObj.length; i++) {
						var Id = responseText.incomeTypeObj[i]["id"];
						alert(Id)
						var incometype = "<option value=" + Id + ">" + responseText.incomeTypeObj[i]["code"] + "</option>";
						$("#incometype").append(incometype);
					}
				}
			});
		});
		
		$("#sbillinfobtn").click(function() {
			var method;
			
			var e = document.getElementById("incometype");
			var it = e.options[e.selectedIndex].value;
			alert (it+"it2");
			
			var ex = document.getElementById("expensetype");
			var exid = ex.options[ex.selectedIndex].value;
			
			var usr = document.getElementById("subusers");
			var usrid = usr.options[usr.selectedIndex].value;
			
			var cus = document.getElementById("customer");
			var cusid = cus.options[cus.selectedIndex].value;
			
			$.ajax({
				type : 'post',
				url : 'BillInfoController',
				data :{
					method : "createBill",
					billcode : document.getElementById("bcode").value,
					amount : document.getElementById("amt").value,
					submitteduser : usrid,
					billdate : document.getElementById("bdte").value,
					subtime : document.getElementById("sdte").value,
					/* if(exid){
						
					}, */
					expensetypeid :exid,
					incometypeId : it,
					/* if (it){
						
					}, */
					customer : cusid
				},
				success: function (responseText) {
					alert(responseText);
					alert("Success");
					if (responseText.suc){
						alert (responseText.suc);
						window.location.assign('/bitproject/cashier.jsp');
					}else {
						alert (responseText.error);
					}
				}
			});
		});
	});
</script>

<script>
  $( function() {
    $( "#bdte" ).datepicker({
      showOn: "button",
      buttonImage: "images/calendar.png",
      buttonImageOnly: true,
      buttonText: "Select date"
    });
  } );
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
				<legend>Bill Info</legend>
			
			
			<nobr><label><b>Expense Type : </b> </label> <select id="expensetype"
				name="expensetype" onchange="" class="dropdownfields">
				<option value="0">Select</option>
			</select></nobr>
			<label><b>Income Type :</b> </label> 
			<select id="incometype" name="incometype" onchange="" class="dropdownfields">
				<option value="0">Select</option>
			</select>
			<label><b>Customer/Shop :</b> </label> 
			<select id="customer" name="customer" class="dropdownfields">
				<option value="0">Select</option>
			</select>
			<label><b>Submitted By :</b> </label> 
			<select id="subusers" name="subusers" onchange="" class="dropdownfields">
				<option value="0">Select</option>
			</select>
			
			<nobr><label><b> Bill Code	:</b> </label><input type="text" name="bcode" id="bcode">
			<font color="red">*</font></nobr>
			<nobr><label><b>Amount	:</b></label><input type="text" name="amt" id="amt" required><font color="red">*</font></nobr><br>
			<nobr><label><b> Bill Name	:</b> </label><input type="text" name="descrpr" id="descrpr"><font color="red">*</font></nobr><br>
			<nobr><label><b> Bill Date  :  </b> </label><input type="text" name="bdte" id="bdte" required><font color="red">*</font></nobr><br>
			<nobr><label><b> Submitted Time	:</b> </label><input type="text" name="sdte" id="sdte" required></nobr> <br>
			<label><b>Bill Image	:</b> </label><input type="file" name="billimg" id="billimg" accept="image/*"><br>
			<button type="submit" name="sbillinfobtn" value="sbillinfobtn" id="sbillinfobtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
			</fieldset>
		</form>
	</div>


</body>
</html>
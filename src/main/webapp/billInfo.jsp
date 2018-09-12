<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
<head>
<title>Bill Info</title>

<script src="js/jquery.min.js"></script>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/commonFunctions.js"></script>

<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="icon" href="images/favic.jpg">

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
						var Id = responseText.incomeTypeObj[i]["et_id"];
						var expensetype = "<option value=" + Id + ">" + responseText.incomeTypeObj[i]["et_code"] + "</option>";
						$("#incometype").append(expensetype);
					}

				}
			});
		});
		$("#submitbtn").click(function(){
			var method;
			var user = document.getElementById("subusers").value;
			var submitteduser = user.options[user.selectedIndex].value;
			
			var et = document.getElementById("expensetype").value;
			var expensetype = et.options[et.selectedIndex].value;
			alert(submitteduser);
				$.ajax({
					type : 'post',
					url : 'BillInfoController',
					data : {
						billid : document.getElementById("billid").value,
						amount : document.getElementById("amt").value,
						method : "create",
						expensetypeid : expensetype,
						submitteduser : submitteduser
					},
					success: function (responseText) {
						
						if (responseText.successx){
							alert (responseText.successx);
							window.location.assign('/bitproject/cashier.jsp');
							
						}else {
							alert (responseText.error);
						}
				}
			});
		});

	});
</script>

<style type="text/css">

.dropdownfields{
	 width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}
</style>


</head>
<body>
	<div id="main">
		<form >
			<fieldset>
				<legend>Bill Info</legend>
			
			
			<label><b>Expense Type : </b> </label> <select id="expensetype"
				name="expensetype" onchange="" class="dropdownfields">
				<option value="0">Select</option>
			</select>
			<label><b>Income Type :</b> </label> 
			<select id="incometype" name="incometype" onchange="" class="dropdownfields">
				<option value="0">Select</option>
			</select>
			<label><b>Submitted By :</b> </label> 
			<select id="subusers" name="users" onchange="" class="dropdownfields">
				<option value="0">Select</option>
			</select><br>
			
			 
			
			<br> Bill Code : <input type="text" name="codepr" id="codepr">
			<font color="red">*</font><br> 
				<label><b>Amount</b></label>
			<input type="text" name="amt" id="amt" required><br>
			Bill Name : <input type="text"
				name="descrpr" id="descrpr"><font color="red">*</font><br>
			
			Bill Date : <br>
			Submitted Time : <br>
			Bill Image : <input type="file" name="billimg" id="billimg" accept="image/*">
			<button type="submit" name="submit" value="Submit" id="submitbtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
			</fieldset>


			
		</form>
	</div>


</body>
</html>
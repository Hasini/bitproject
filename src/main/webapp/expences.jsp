<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
<head>
<title>Expenses</title>

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
				url : 'DailyExpensesController',
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
				url : 'DailyExpensesController',
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
		
		$("#sbillinfobtn").click(function() {
			var method;
			
			
			var ex = document.getElementById("expensetype");
			var exid = ex.options[ex.selectedIndex].value;
			
			var usr = document.getElementById("subusers");
			var usrid = usr.options[usr.selectedIndex].value;
			
			var cus = document.getElementById("customer");
			var cusid = cus.options[cus.selectedIndex].value;
			
			$.ajax({
				type : 'post',
				url : 'DailyExpensesController',
				data :{
					method : "createExpenses",
					billcode : document.getElementById("bcode").value,
					amount : document.getElementById("amt").value,
					submitteduser : usrid,
					billdate : document.getElementById("bdte").value,
					subtime : document.getElementById("sdte").value,
					expensetypeid :exid,
					customer : cusid
				},
				success: function (responseText) {
					
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
		
<style>

button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 50%;
}

input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
}

hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
}


button:hover {
    opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
    padding: 14px 20px;
    background-color: #f44336;
}

/* Add padding to container elements */
.container {
    padding: 16px;
}

/* Clear floats */
.clearfix::after {
    content: "";
    clear: both;
    display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
    .cancelbtn, .signupbtn {
       width: 100%;
    }
}
</style>

</head>
<body>
	<fieldset>
		<legend>Expenses</legend>
			
			
			<nobr><label><b>Expense Type : </b> </label> <select id="expensetype"
				name="expensetype" onchange="" class="dropdownfields">
				<option value="0">Select</option>
			</select></nobr>
			
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
			
			<nobr><label><b> Bill Date  :  </b> </label><input type="text" name="bdte" id="bdte" required><font color="red">*</font></nobr><br>
			<nobr><label><b> Submitted Time	:</b> </label><input type="text" name="sdte" id="sdte" required></nobr> <br>
			<label><b>Bill Image	:</b> </label><input type="file" name="billimg" id="billimg" accept="image/*"><br>
			<button type="submit" name="sbillinfobtn" value="sbillinfobtn" id="sbillinfobtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
		</fieldset>
	

</body>
</html>
<!DOCTYPE html>
<%@include file="header.jsp" %> 
<html>
<head>
<title>Income</title>

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
		$('#back').click(function(){
			window.location.assign('/bitproject/cashier.jsp');
		});
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
					method : "view"
				},
				success : function(responseText) {
					for (var i = 0; i < responseText.cusObj.length; i++) {
						var idvalue = responseText.branchobj[i]["id"];
						var branch = "<option value="+idvalue+">"+responseText.branchobj[i]["branch_descr"]+"</option>";
						$("#branch").append (branch);
					}
				}
			});
		});
		
	
		$(window).load(function() {
			$.ajax({
				type : 'post',
				url : 'DailyIncomeController',
				data : {
					method : "viewIncomeTypes"
				},
				success : function(responseText) {
					for (var i = 0; i < responseText.incomeTypeObj.length; i++) {
						var Id = responseText.incomeTypeObj[i]["it_id"];
						
						var incometype = "<option value=" + Id + ">" + responseText.incomeTypeObj[i]["it_code"] + "</option>";
						$("#incometype").append(incometype);
					}
				}
			});
		});
		
		$("#sbillinfobtn").click(function() {
			
			var method;
			
			var e = document.getElementById("incometype");
			var it = e.options[e.selectedIndex].value;
			
			var usr = document.getElementById("subusers");
			var usrid = usr.options[usr.selectedIndex].value;
			
			var br = document.getElementById("branch");
			var branch = br.options[br.selectedIndex].value;
			
			var billimg = document.getElementById("billimg").serialize();
			alert(it + usrid + "branch" +branch + billimg);
			
			$.ajax({
				type : 'post',
				url : 'DailyIncomeController',
				data :{
					method : "createIncomes",
					billcode : document.getElementById("bcode").value,
					amount : document.getElementById("amt").value,
					submitteduser : usrid,
					billdate : document.getElementById("bdte").value,
					subdate : document.getElementById("sdte").value,
					incometypeId : it,
					branch : branch,
					billimg : billimg
				},
				success: function (responseText) {
					
					if (responseText.suc){
						alert (responseText.suc);
						window.location.assign('/bitproject/cashier.jsp');
					}else {
						alert (responseText.error);
						$("#incometype").focus();
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

<script>
  $( function() {
    $( "#bdte").datepicker({
      showOn: "button",
      buttonImage: "images/calendar.png",
      buttonImageOnly: true,
      buttonText: "DP"
    });
    $("#sdte").datepicker({
        showOn: "button",
        buttonImage: "images/calendar.png",
        buttonImageOnly: true,
        buttonText: "DP"
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
<body style="width: 80%; height: 70%; margin-left: 0.5%;">
<div id="main">
	<fieldset>
		<legend>Income</legend>
			<nobr><label><b>Income Type : </b> </label> <select id="incometype"
				name="incometype" onchange="" class="dropdownfields">
				<option value="0">Select</option>
			</select></nobr>
			
			<label><b>Branch:</b> </label> 
			<select id="branch" name="branch" class="dropdownfields">
				<option value="0">Select</option>
			</select>
			<label><b>Submitted By :</b> </label> 
			<select id="subusers" name="subusers" onchange="" class="dropdownfields">
				<option value="0">Select</option>
			</select>
			</br></br>
			<nobr><label><b> Bill Code	:</b> </label></br><input type="text" name="bcode" id="bcode">
			<font color="red">*</font></nobr></br>
			<nobr><label><b>Amount	:</b></label></br><input type="text" name="amt" id="amt" required><font color="red">*</font></nobr><br>
			
			<nobr><label><b> Bill Date  :  </b> </label></br><input type="text" name="bdte" id="bdte" required><font color="red">*</font></nobr><br>
			<nobr><label><b> Submitted Date	:</b> </label></br><input type="text" name="sdte" id="sdte" required></nobr> <br>
			<label><b>Bill Image	:</b> </label></br><input type="file" name="billimg" id="billimg" accept="image/*"><br>
			<button type="submit" name="sbillinfobtn" value="sbillinfobtn" id="sbillinfobtn" class="submit">Submit</button>
			<button type="button" name="clear" value="clear" id="cncl" onclick="clearinputs();">Reset</button>
			<button type="button" name="clear" value="clear" id="back">Back</button>
		</fieldset>
</div>

</body>
</html>
<%@include file="footer.jsp" %>
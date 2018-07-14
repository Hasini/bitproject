<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/commonFunctions.js"></script>
			
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<link rel="icon" href="images/favicon-facebook_400x400.png">
		
		
		
		<script type="text/javascript">
		
		//function getMandotaryFields(mandotaryInputs){
			
// 			$.each($('.MyClass'),function() {
// 				   if ($(this).val().length == 0) {
// 				    alert('empty');
// 				   }
// 				});

			/* var mandaFields = $('.mandotaryInputs').val;
			var sts ;
			
			if(jQuery.trim(mandaFields).length > 0){
				sts= "ok";
			}else {
				sts = "nok";
			}
			alert(sts);
				return sts; */
		//}
		
		//testt
		
		/* var x = document.querySelectorAll(".example");
var i;
for (i = 0; i < x.length; i++) {
    x[i].style.backgroundColor = "red"; 
}*/
		
		function getMandotaryFields(mandotaryInputs){
			var mdfield = Array.prototype.slice.call(document.getElementsByClassName("mandotaryInputs").value,0);
			
			for (var i = 0; i < mdfield.length; i++) {
				if(mdfield [i] != null)
					{
						alert ("mandotary fileds are not empty");
					}else {
						alert ("mandotary fileds are empty");
					}
			    
			}
			
		}
		
		function validateEmail($email) {
			  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			  return emailReg.test( $email );
		}
		
		function textLength(value){
			var maxLength = 10;
			if(value.length > maxLength) {
			   return false;
			} else if (value.length < maxLength){
				return false;
			}
			   return true;
		} 
		
		$(document).ready(function(){
			$(window).load(function(){
				 var optionsAsString;
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
			
			$("#submitbtn").click(function() {
				
				getMandotaryFields(document.getElementsByClassName("mandotaryInputs").value);
				var nic = $('#o').val() +$('#t').val()+$('#th').val()+$('#fr').val()+$('#fi').val()+$('#si').val()+$('#se').val()+$('#eig').val()+$('#ni').val()+$('#v').val();
				var snic = $('#os').val() +$('#ts').val()+$('#ths').val()+$('#frs').val()+$('#fis').val()+$('#sis').val()+$('#ses').val()+$('#eigs').val()+$('#nis').val()+$('#vs').val();
				var cus_initials = $('#inioc').val() + ' ' + $('#initc').val()+ ' ' + $('#inithc').val();
				var sp_initials = $('#iniosp').val() + ' ' + $('#initsp').val()+ ' ' +$('#inithsp').val();
				var e = document.getElementById("branch");
				var branch = e.options[e.selectedIndex].value;
				
				if( !validateEmail(document.getElementById("emal").value)){ 
					alert("Can not proceed ..invalid email");
				}else if(!textLength(nic) || !textLength(snic)){
					alert("Can not proceed ..invalid nic");
// 				} else if (getMandotaryFields(document.getElementsByClassName("mandotaryInputs").value)== 'ok'){
// 					alert("Please fill all mandotory fields..!");
// 				} 
					/* else if(document.getElementsByClassName("mandotaryInputs").value == '' || document.getElementsByClassName("mandotaryInputs").value == null ||){
					alert("Can not proceed ..Please fill all mandory values");
				} */
				
			   }
			
				else if (nic == snic){
					alert("Can not proceed ..Main NIC is equal to other");
				}
				else {
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
						error: function (responseText) {
							alert(responseText.error+"response");
							
		            	},
						success: function (responseText) {
								alert(responseText.success);
								if (responseText.error )
									alert(responseText.error);
		    			}   
						
					});
				}
			}); 
		});
		</script>
		
		
	</head>
<body>


<div id="main">
	<a href="login.jsp">
		<button type="button" >HOME</button>
	</a>
	
	<div>
		<h2>Welcome to JSL </h2>
	</div>
	
	<div>
		<fieldset>
		<legend>Customer Registration</legend>
		
			Branch : <select id="branch" name="branch" >
				<option>select</option>
			</select>
			
			<br><br>
			First Name : <nobr><input type="text" id="fname" class="mandotaryInputs" ><font style="color: red;">*</font></nobr><br>
			Initials : <input type="text" class="minitext" style="width: 60px;" id="inioc"> <input type="text" class="minitext" style="width: 60px;" id="initc">
			 <input type="text" class="minitext" style="width: 60px;" id="inithc"><br>
			Name Denoted by Initials : <input type="text" class="middletext" id="cus_othername"><br>
			Shop Address :<br>
			Address Line 1:<nobr><input type="text" id="shad1"><font style="color: red;">*</font></nobr>
			Address Line 2:<nobr><input type="text" id="shad2"><font style="color: red;">*</font></nobr>
			Address Line 3:<nobr><input type="text" id="shad3"><font style="color: red;">*</font></nobr>
			Home Address :<br>
			Address Line 1:<nobr><input type="text" id="hmad1"><font style="color: red;">*</font></nobr>
			Address Line 2:<nobr><input type="text" id="hmad2"><font style="color: red;">*</font></nobr>
			Address Line 3:<nobr><input type="text" id="hmad3"><font style="color: red;">*</font></nobr><br>
			Shop Tel : <nobr><input type="tel" id="shtel" class="mandotaryInputs" maxlength="10"><font style="color: red;">*</font></nobr>
			Home Tel : <nobr><input type="tel" id="hmtel"><font style="color: red;">*</font></nobr><br><br>
			Mobile No : <nobr><input type="tel" id="mob"><font style="color: red;">*</font></nobr><br>
			NIC Number :<br>
			<input type="text" class="minitext" id="o" style="width: 50px;"> <input type="text" class="minitext" id="t" style="width: 50px;"> 
			<input type="text" class="minitext" id="th" style="width: 50px;"><input type="text" class="minitext" id="fr" style="width: 50px;"> 
			<input type="text" class="minitext" id="fi" style="width: 50px;"> <input type="text" class="minitext" id="si" style="width: 50px;">  
			<input type="text" class="minitext" id="se" style="width: 50px;"> <input type="text" class="minitext" id="eig" style="width: 50px;"> 
			<input type="text" class="minitext" id="ni" style="width: 50px;"> <input type="text" class="minitext" id="v" value="V" style="width: 50px;"><br>
			E-Mail : <nobr><input type="email" id="emal"><font style="color: red;">*</font></nobr>
			
			<br>
			Spouse / Shareholder (main) 
			
			First Name : <nobr><input type="text" id="sfname"><font style="color: red;">*</font></nobr>
			Initials : <input type="text" class="minitext" style="width: 60px;" id="iniosp"> <input type="text" class="minitext"style="width: 60px;" id="initsp"> 
			<input type="text" class="minitext" style="width: 60px;" id="inithsp"><br>
			Name Denoted by Initials : <input type="text" class="middletext" id="sp_othername"><br><br>
			
			Home Address :
			Address Line 1:<nobr><input type="text" id="sad1"><font style="color: red;">*</font></nobr><br>
			Address Line 2:<nobr><input type="text" id="sad2"><font style="color: red;">*</font></nobr><br>
			Address Line 3:<nobr><input type="text" id="sad3"><font style="color: red;">*</font></nobr><br>
			Home Tel : <nobr><input type="tel" id="stel"><font style="color: red;">*</font></nobr>
			Mobile No : <nobr><input type="tel" id="smob"><font style="color: red;">*</font></nobr><br>
			NIC Number :<br><input type="text" class="minitext" id="os" style="width: 50px;" maxlength="2"> <input type="text" class="minitext" id="ts" style="width: 50px;"> 
			<input type="text" class="minitext" id="ths" style="width: 50px;"><input type="text" class="minitext" id="frs" style="width: 50px;"> 
			<input type="text" class="minitext" id="fis" style="width: 50px;"> <input type="text" class="minitext" id="sis" style="width: 50px;">  
			<input type="text" class="minitext" id="ses" style="width: 50px;"> <input type="text" class="minitext" id="eigs" style="width: 50px;"> 
			<input type="text" class="minitext" id="nis" style="width: 50px;"> <input type="text" class="minitext" id="vs" value="V" style="width: 50px;">  
			<br>
			E-Mail :<nobr> <input type="email" id="semail"><font style="color: red;">*</font></nobr>
			<br/>
			<button type="submit" class = "submit" id="submitbtn"> Submit</button>
			<button type="reset" id="cncl">Reset</button>
			<br>
			<div id="errormsg" style="color:red;"></div>
		</fieldset>
	</div>
</div>
<body>

</body>
</html>
<!DOCTYPE html>

<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/commonFunctions.js"></script>
		
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="icon" href="images/favicon-facebook_400x400.png">
<script>
$(document).ready(function(){
	
	$("#createDiv").hide();
    $("#deleteDiv").hide();
    $("#updateDiv").hide();
    
    $("#create").click(function(){
        $("p").hide();
        $("#createDiv").show();
        $("#deleteDiv").hide();
        $("#updateDiv").hide();
    });
    
    $("#update").click(function(){
        $("p").hide();
        $("#updateDiv").show();
        $("#createDiv").hide();
        $("#deleteDiv").hide();
        
    });
    $("#delete").click(function(){
    	$("#deleteDiv").show();
    	$("#createDiv").hide();
    	$("#updateDiv").hide();
    });
});
</script>
</head>
<body>


<fieldset>
	<legend>Branch Details</legend>


<div id="main">
	<div id = "createDiv">
		<form action="crea" method="get" onsubmit="validate();">
			<h2>Branch Details</h2>
			Branch Code : <input type="text" name="code" id="code"> <font color="red">*</font><br>
			Branch Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
			
			<button type="submit" name="submit" value="submit" id="submit">Submit</button>
			<button type="button" name="cancel" id="cncl">Cancel</button>
		</form>
	</div>
	
	<div id="updateDiv">
		<form >
			<h2>Branch Details</h2>
			Branch Code : <input type="text" name="code" id="code" disabled="disabled"> <font color="red">*</font><br>
			Branch Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
			
			<button type="submit" name="submit" value="Submit" id="submit">Update</button>
			<button type="button" name="cancel" value="Cancel" id="cncl">Cancel</button>
		</form>
	</div>
	
	<div id="deleteDiv">
		<form >
			<h2>Branch Details</h2>
			Branch Code : <input type="text" name="code" id="code" disabled="disabled"> <font color="red">*</font><br>
			Branch Description : <input type="text" name="descr" id="descr"><font color="red">*</font><br>
			
			<button type="submit" name="submit" value="Submit" id="submit">Delete</button>
			<button type="button" name="cancel" id="cncl">Cancel</button>
		</form>
	</div>


	<div id="btngroup">
		<input type ="radio" name ="create" id ="create" value ="create">Create
		<input type ="radio" name ="update" id ="update" value ="update">Update
		<input type ="radio" name ="delete" id ="delete" value ="delete">Delete
	</div>
</div>

</fieldset>


</body>
</html>
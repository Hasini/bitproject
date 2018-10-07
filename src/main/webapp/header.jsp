<!DOCTYPE html>
<html>
	<article style="width:100%;height:15%;border-right: 20%;background-image: url(images/background.jpg);  background-repeat: no-repeat;
     background-size: cover; border-left-width: 5%;border-right-width: 20%">
		
		<header>
			<p style="float: right; color:#FFFFE0;"><span id="datetime"></span></p>
			<h1 style="font-family:Helvetica;font-size: 30px;color:#FFFFE0;" align="left"><b>Kasun Grinding Mills - Hambantota </b></h1>
			<button type="submit" id="sas" value="signup" style="background-color:#0000ff;height: 2%;width: 10%;color: white;align:right ;float: right;">Log Out</button>
			
			<H2 style="float:left ;font-size: 10px;font-family:serif;">@C JSL - WE EASY YOUR TRANSACTIONS</H2>
			<script>
				var dt = new Date();
				document.getElementById("datetime").innerHTML = dt.toLocaleString();
			</script>
		</header>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/commonFunctions.js"></script>
	</article>
	
	<body>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#sas").click(function(){
					$.ajax({
							type : 'post',
							url : 'userservlet',
							data : {
								method : "logout",
								
							},
							success: function (responseText) {
								window.location.href='main.jsp';
							}
						});
					
				});
			});
		</script>
	
	</body>
	
</html>
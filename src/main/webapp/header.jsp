<!DOCTYPE html>
<html>
<body>
<form action="userservlet" method="post">
	<article
	style="width: 80%; height: 15%; border-right: 20%; background-image: url(images/background.jpg);float:center; 
	background-repeat: no-repeat; background-size: cover; border-left-width: 5%; border-right-width: 20% ; margin-left: 10%">
		
		<header>
			<p style="float: right; color:#FFFFE0;"><span id="datetime"></span></p>
			<h1 style="font-family:Helvetica;font-size: 30px;color:#FFFFE0;" align="left"><b>Kasun Grinding Mills - Hambantota </b></h1>
			<input type="hidden" name="method" id="method" value="logout">
			<button type="submit" id="out" value="logout" style="color:#20B2AA;height: 2%;width: 10%;border-color: #20B2AA;align:right ;float: right;margin-right: 5px;">Log Out</button>
			<script>
				var dt = new Date();
				document.getElementById("datetime").innerHTML = dt.toLocaleString();
			</script>
		</header>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/commonFunctions.js"></script>
	</article>
	</form>
	</body>
		 <script type="text/javascript">
			$(document).ready(function(){
				$("#out").click(function(){
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
	
	
	
</html>
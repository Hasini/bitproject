<!DOCTYPE html>
<html>
    <head>
        <title>Hello World</title>
        <style>
            html, body {
                width: 100%;
                height: 100%;
                margin: 0;
                padding: 0;
                background-color: green;
            }
            .container {
                width: inherit;
                height: inherit;
                margin: 0;
                padding: 0;
                background-color: pink;
            }
            h1 {
                margin: 0;
                padding: 0;
            }
        </style>
    </head>
    <body>
        <div id="accordion" class="container">
		
		<h3>Already a member</h3>
		<div>
			<span id="login"><a href="login.jsp">Login</a></span>
		</div>
		<h3>Sign Up</h3>
		<div>
			<span id="signup"><a href="sign-up.jsp">Sign-Up</a></span>
		</div>
	</div>
    </body>
</html>
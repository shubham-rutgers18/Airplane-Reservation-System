<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width = device-width, initial-scale = 1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js">
	
</script>
</head>
<body>

	<nav>
	<div class="nav-wrapper">
		<center>
			<a href="index.jsp" class="brand-logo">FlyWithMe</a>
		</center>
	</div>
	</nav>

	<div class="container">
		<p>Please fill in this form to create an account.</p>
		<hr>
		<div style="margin-top: 30px"></div>
		<div class="row">
			<form class="col s12" action="register" method="post">
				<div class="row">
					<div class="input-field col s6">
						<input id="firstName" type="text" class="validate"
							name="firstName" /> <label for="firstName">First Name</label>
					</div>
					<div class="input-field col s6">
						<input id="lastName" type="text" class="validate" name="lastName" />
						<label for="lastName">Last Name</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<textarea id="address" class="materialize-textarea" name="address"></textarea>
						<label for="address">Address</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<input type="Number" id="Zipcode" name="zipcode" /> <label
							for="Zipcode"><b> Zipcode </b></label>

					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<input type="number" name="contactNo" id="Contact_No" /> <label
							for="Contact_No"><b> Contact_No </b></label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<input type="Number" name="creditCardNo" id="CreditCard_No" /> <label
							for="CreditCard_No"><b> CreditCard_No </b></label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<input type="text" name="email" id="email" /> <label for=><b>Email</b></label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<input type="password" name="password" id="psw" /> <label
							for="psw"><b>Password</b></label>
					</div>
				</div>
				<p>By creating an account you agree to our Terms & Privacy</p>

				<div class="row">
					<div class="col s12">
						<input id="submit" type="submit" class="validate" value="register" />
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>
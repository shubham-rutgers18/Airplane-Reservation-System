<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta name="viewport" content="width = device-width, initial-scale = 1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src=" https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>

<script type="text/javascript">
	function clearAddCustFields() {
		$('#firstName').val("");
		$('#lastName').val("");
		$('#address').val("");
		$('#zipcode').val("");
		$('#contactNo').val("");
		$('#creditCardNo').val("");
		$('#email').val("");
		$('#password').val("");
	}

	$(document).ready(function() {
		$('#addUser').click(function() {
			var firstName = $('#firstName').val();
			var lastName = $('#lastName').val();
			var address = $('#address').val();
			var zipcode = $('#zipcode').val();
			var contactNo = $('#contactNo').val();
			var creditCardNo = $('#creditCardNo').val();
			var email = $('#email').val();
			var password = $('#password').val();
			$.ajax({
				type : 'POST',
				data : {
					firstName : firstName,
					lastName : lastName,
					address : address,
					zipcode : zipcode,
					contactNo : contactNo,
					creditCardNo : creditCardNo,
					email : email,
					password : password
				},
				url : 'addCustomer',
				success : function(result) {
					$('#result').html(result);
					clearAddCustFields();
				}
			});
		});
	});

	$(document).ready(function() {
		$('#revenueCust').click(function() {
			var email = $('#revenueEmail').val();
			$.ajax({
				type : 'POST',
				data : {
					email : email
				},
				url : 'revenue',
				success : function(result) {
					$('#revenueResult').html(result);
					$('#revenueEmail').val("");
				}
			});
		});
	});
</script>
<style>
body {
	display: flex;
	min-height: 100vh;
	flex-direction: column;
}

main {
	flex: 1 0 auto;
}

.page-footer {
	position: absolute;
	bottom: 0;
	width: 100%;
}

#m1 {
	display: none;
}

#m2 {
	display: none;
}

#m3 {
	display: none;
}

#m4 {
	display: none;
}

#m21 {
	display: none;
}

#m22 {
	display: none;
}

#m23 {
	display: none;
}

#m24 {
	display: none;
}

#m25 {
	display: none;
}
</style>

</head>

<body>
	<%
		request.setAttribute("fromPage", "admin.jsp");
	%>
	<nav>
	<div class="nav-wrapper">
		<a href="index.jsp" class="brand-logo center">FlyWithMe-System
			Manager</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="logout" accesskey="1">Logout</a></li>
		</ul>
	</div>
	</nav>

	<div class="container">
		<!-- <h3>Tabs Demo</h3> -->
		<div class="row">
			<div class="col s12">
				<ul class="tabs">
					<li class="tab col s4"><a href="#m1">Customer Data</a></li>
					<li class="tab col s4"><a class="active" href="#m2">
							Flight Details</a></li>
					<li class="tab col s4 "><a href="#m3"> Sales Summary</a></li>

				</ul>
			</div>

			<div id="m1" class="col s12">

				<center>
					<h5>Manage Customer Data</h5>
					<ul class="collapsible" data-collapsible="accordion">
						<li>
							<div class="collapsible-header">ADD</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<div class="row">
											<div class="input-field col s6">
												<input id="firstName" type="text" class="validate" /> <label
													for="firstName">First Name</label>
											</div>
											<div class="input-field col s6">
												<input id="lastName" type="text" class="validate" /> <label
													for="lastName">Last Name</label>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s12">
												<textarea id="address" class="materialize-textarea"></textarea>
												<label for="address">Address</label>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s12">
												<input type="number" name="zipcode" id="zipcode" /> <label
													for="zipcode"><b> Zipcode </b></label>

											</div>
										</div>
										<div class="row">
											<div class="input-field col s12">
												<input type="number" name="contactNo" id="contactNo" /> <label
													for="contactNo"><b> Contact_No </b></label>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s12">
												<input type="number" name="creditCardNo" id="creditCardNo" />
												<label for="creditCardNo"><b> CreditCard_No </b><label>
											</div>
										</div>

										<div class="row">
											<div class="input-field col s12">
												<input type="text" name="email" id="email" /> <label
													for="email"><b>Email</b></label>
											</div>
										</div>

										<div class="row">
											<div class="input-field col s12">
												<input type="password" name="password" id="password" /> <label
													for="password"><b>Password</b></label>
											</div>
										</div>

										<button class="btn waves-effect waves-teal" id="addUser">Add</button>
										<div id="result"></div>

									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="collapsible-header">EDIT</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<div class="input-field col s12">
											<input type="text" name="email" id="editemailid" /> <label
												for="editemailid"><b> Email</b></label>
										</div>
										<button class="btn waves-effect waves-teal" id="editCustomer">SUBMIT</button>


										<div class="container" style="display: none;" id="editdiv">
											<form class="col s12">
												<div class="row">
													<div class="input-field col s6">
														<input id="firstName1" type="text" class="validate" /> <label
															for="firstName">First Name</label>
													</div>
													<div class="input-field col s6">
														<input id="lastName1" type="text" class="validate" /> <label
															for="lastName">Last Name</label>
													</div>
												</div>
												<div class="row">
													<div class="input-field col s12">
														<textarea id="address1" class="materialize-textarea"></textarea>
														<label for="address">Address</label>
													</div>
												</div>
												<div class="row">
													<div class="input-field col s12">
														<input type="number" name="zipcode" id="zipcode" /> <label
															for="zipcode"><b> Zipcode </b></label>

													</div>
												</div>
												<div class="row">
													<div class="input-field col s12">
														<input type="number" name="contactNo" id="contactNo1" />
														<label for="contactNo"><b> Contact_No </b></label>
													</div>
												</div>
												<div class="row">
													<div class="input-field col s12">
														<input type="number" name="creditCardNo"
															id="creditCardNo1" /> <label for="creditCardNo"><b>
																CreditCard_No </b><label>
													</div>
												</div>

												<div class="row">
													<div class="input-field col s12">
														<input type="text" name="email" id="email1" /> <label
															for="email"><b>Email</b></label>
													</div>
												</div>

												<div class="row">
													<div class="input-field col s12">
														<input type="password" name="password" id="password1" />
														<label for="password"><b>Password</b></label>
													</div>
												</div>
												<button class="btn waves-effect waves-teal">Edit &
													Save</button>
											</form>
										</div>
									</div>

								</div>
							</div>
						</li>
						<li>
							<div class="collapsible-header">DELETE</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<form action="deleteCustomer">
											<div class="input-field col s12">
												<input type="text" name="email" id="deleteEmailid" /> <label
													for="deleteEmailid"><b> Email</b></label>
											</div>
											<button class="btn waves-effect waves-teal"
												onclick="deletebutton()">SUBMIT</button>
										</form>
									</div>

								</div>
							</div>
						</li>

						<li>
							<div class="collapsible-header">CHECK RESERVATION FOR
								CUSTOMER</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<form action="checkReservation">
											<div class="input-field col s12">
												<input type="text" name="email" id="emailid" /> <label
													for="emailid"><b> Email</b></label>
											</div>
											<button class="btn waves-effect waves-teal">SUBMIT</button>
										</form>
									</div>

								</div>
							</div>
						</li>

						<li>
							<div class="collapsible-header">BEST CUSTOMER</div>
							<div class="collapsible-body">
								<form action="getBestCustomer">
									<button class="btn waves-effect waves-teal"
										onclick="bestCustomer()">SUBMIT</button>
								</form>
							</div>
						</li>


					</ul>
			</div>
			<div id="m2" class="col s12">
				<center>
					<h5>Get Flight Details</h5>

					<ul class="collapsible" data-collapsible="accordion">
						<li>
							<div class="collapsible-header">Most Active FLights</div>
							<div class="collapsible-body">
								<button class="btn waves-effect waves-teal">SUBMIT</button>
							</div>
						</li>

						<li>
							<div class="collapsible-header">List of all customers who
								have seats reserved on a given flight</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<form action="listCustomers">
											<div class="input-field col s12">
												<input type="number" placeholder="ENTER flight id"
													name="flightid" id="flightid" /> <label for="flightid"><b>
														flight id</b></label>
											</div>
											<button class="btn waves-effect waves-teal">SUBMIT</button>
										</form>
									</div>

								</div>
							</div>
						</li>


						<li>
							<div class="collapsible-header">Airport Flight Details</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<form>
											<div class="input-field col s12">
												<input type="number" placeholder="ENTER airport id"
													name="airportid" id="airportid" /> <label for="airportid"><b>
														airport id</b></label>
											</div>
											<button class="btn waves-effect waves-teal">SUBMIT</button>
										</form>
									</div>

								</div>
							</div>
						</li>

						<li>
							<div class="collapsible-header">On Time/Delay</div>
							<div class="collapsible-body">
								<button class="btn waves-effect waves-teal">SUBMIT</button>
							</div>
						</li>

						<li>
							<div class="collapsible-header">list of flights</div>
							<div class="collapsible-body">
								<button class="btn waves-effect waves-teal">SUBMIT</button>
							</div>
						</li>

						<li>
							<div class="collapsible-header">Reservation List by flight
								number</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<form>
											<div class="input-field col s12">
												<input type="number" placeholder="ENTER flight id"
													name="flightid" id="flightid" /> <label for="flightid"><b>
														Flight id</b></label>
											</div>

											<button class="btn waves-effect waves-teal">SUBMIT</button>
										</form>
									</div>

								</div>
							</div>
						</li>


					</ul>
			</div>
			<div id="m3" class="col s12">
				<center>
					<h5>Get Sales Details</h5>

					<ul class="collapsible" data-collapsible="accordion">
						<li>
							<div class="collapsible-header">Sales report for particular
								month</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<form>
											<div class="input-field col s12">
												<input type="text"
													placeholder="ENTER FIRST 3 CHAR MONTH(JAN,FEB,ETC)"
													name="month" id="month" /> <label for="month"><b>
														Month</b></label>
											</div>


											<div class="input-field col s12">
												<input type="number" placeholder="ENTER year" name="year"
													id="year" /> <label for="year"><b> Year</b></label>
											</div>

											<button class="btn waves-effect waves-teal">SUBMIT</button>
										</form>
									</div>

								</div>

							</div>
						</li>

						<li>
							<div class="collapsible-header">Revenue by flightid</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<form>
											<div class="input-field col s12">
												<input type="number" placeholder="ENTER flight id"
													name="flightid" id="flightid" /> <label for="flightid"><b>
														Flight id</b></label>
											</div>

											<button class="btn waves-effect waves-teal">SUBMIT</button>
										</form>
									</div>

								</div>

							</div>
						</li>

						<li>
							<div class="collapsible-header">Revenue by destination city</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<form>
											<div class="input-field col s12">
												<input type="text" placeholder="ENTER destination city"
													name="city" id="city" /> <label for="city"><b>
														Destination City</b></label>
											</div>

											<button class="btn waves-effect waves-teal">SUBMIT</button>
										</form>
									</div>

								</div>

							</div>
						</li>

						<li>
							<div class="collapsible-header">Revenue by customer</div>
							<div class="collapsible-body">
								<div class="container">
									<div class="row">
										<div class="input-field col s12">
											<input type="text" name="email" id="revenueEmail" /> <label
												for="emailid"><b> Email</b></label>
										</div>
										<button class="btn waves-effect waves-teal" id="revenueCust">GET
											REVENUE</button>
										<div id="revenueResult" />
									</div>

								</div>

							</div>
						</li>
					</ul>
			</div>
		</div>
</body>

</html>
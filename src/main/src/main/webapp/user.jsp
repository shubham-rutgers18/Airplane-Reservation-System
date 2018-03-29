<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script>
	$('.datepicker').pickadate({
		selectMonths : true, // Creates a dropdown to control month
		selectYears : 15, // Creates a dropdown of 15 years to control year,
		today : 'Today',
		clear : 'Clear',
		close : 'Ok',
		closeOnSelect : false
	// Close upon selecting a date,
	});

	function myFunction() {
		document.getElementById("demo").disabled = false;
	}
	function myFunction2() {
		document.getElementById("demo").disabled = true;
	}
</script>
</head>
<body>
	<nav>
	<div class="nav-wrapper">
		<a href="index.jsp" class="brand-logo center">FlyWithMe</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="logout" accesskey="1">Logout</a></li>
		</ul>
	</div>
	</nav>
	<center>
		<h5 class="indigo-text">
			<%
				out.println("Welcome " + request.getSession().getAttribute("firstName") + " "
						+ request.getSession().getAttribute("lastName"));
			%>
		</h5>
	</center>

	<div class="container">
		<!-- <h3>Tabs Demo</h3> -->
		<div class="row">
			<div class="col s12">
				<ul class="tabs">
					<li class="tab col s2"><a href="#m1">Search</a></li>
					<li class="tab col s2"><a class="active" href="#m2">
							Current Bookings</a></li>
					<li class="tab col s2 "><a href="#m3"> All Bookings</a></li>

					<li class="tab col s2 "><a href="#m5"> Account</a></li>
					<li class="tab col s2 "><a href="#m6"> Best Seller</a></li>

				</ul>
			</div>
			<div id="m1" class="col s12">
				<div class="container">
					<div class="row"></div>
					<div class="row">
						<form class="col s12" action="searchFlights">
							<div class="row">
								<div class="input-field col s6">
									<i class="material-icons prefix">flight_takeoff</i> <input
										placeholder="City" id="origin" type="text"
										class="active validate" required /> <label for="origin">Origin</label>
								</div>

								<div class="input-field col s6">
									<i class="material-icons prefix">flight_land</i> <label
										for="destination">Destination</label> <input id="destination"
										type="text" placeholder="City" class="active validate"
										required />
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<p>
										<input id="oneway" type="radio" name="waytrip" value="oneway"
											checked onchange="myFunction2()" /> <label for="oneway">One
											way</label>
									</p>
								</div>
								<div class="input-field col s6">
									<p>
										<input id="roundway" type="radio" name="waytrip"
											value="roundway" onchange="myFunction()" /> <label
											for="roundway">Round way</label>
									</p>
								</div>
							</div>

							<div class="row">
								<label>Departing</label> <input type="date" class="datepicker" />
							</div>

							<div class="row">
								<label>Returning</label> <input type='date' class='datepicker'
									id="demo" disabled />
							</div>
							<div class="row">
								<label>Number of seats</label> <select class="browser-default">
									<option value="1" disabled selected>1</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
								</select>

							</div>

							<button class="btn waves-effect waves-teal">Search</button>

						</form>
					</div>
				</div>

			</div>
			<div id="m2" class="col s12">
				<center>
			</div>

		</div>
	</div>
</body>
</html>
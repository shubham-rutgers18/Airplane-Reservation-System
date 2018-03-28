<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
<style>
body {
	display: flex;
	min-height: 100vh;
	flex-direction: column;
}

main {
	flex: 1 0 auto;
}

body {
	background: #fff;
}

.input-field input[type=date]:focus+label, .input-field input[type=text]:focus+label,
	.input-field input[type=email]:focus+label, .input-field input[type=password]:focus+label
	{
	color: #e91e63;
}

.input-field input[type=date]:focus, .input-field input[type=text]:focus,
	.input-field input[type=email]:focus, .input-field input[type=password]:focus
	{
	border-bottom: 2px solid #e91e63;
	box-shadow: none;
}

table {
	width: 100%;
}

table, th, td {
	border: 0px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
	text-align: left;
}

table#t01 tr:nth-child(even) {
	background-color: #eee;
}

table#t01 tr:nth-child(odd) {
	background-color: #fff;
}

table#t01 th {
	background-color: black;
	color: white;
}
</style>
<nav>
<div class="nav-wrapper">
	<a href="index.jsp" class="brand-logo center">FlyWithMe</a>
</div>
</nav>
</head>
<body>
	<h2>List Of Available Flights to Destination Name</h2>
	<p>Prices are one way per person, include all taxes and fees</p>
	<table id="t01">
		<tr>
			<th>Airline</th>
			<th>Departure Time</th>
			<th>Arrival Time</th>
			<th>Total Time And Stops</th>
			<th>Fare</th>
			<th>Select</th>
		</tr>
		<tr>
			<td>Delta</td>
			<td>11.00</td>
			<td>13.24</td>
			<td>2hr 24 min, Nonstop</td>
			<td>$100</td>
			<td><a href="ConfirmBooking.html"><button type="button"
						class="btn btn-primary btn-lg" onclick="myFunction()">Select</button></a></td>
		</tr>
		<tr>
			<td>Emirates</td>
			<td>02.00</td>
			<td>04.35</td>
			<td>2 hr 35 min Nonstop</td>
			<td>$105</td>
			<td><a href="ConfirmBooking.html"><button type="button"
						class="btn btn-primary btn-lg" onclick="myFunction()">Select</button></a></td>
		</tr>


		<script>
			function myFunction() {
				var table = document.getElementById("myTable");
				var row = table.insertRow(-1);
				var cellInstruction = row.insertCell(-1);
				cellInstruction.innerHTML = '<button class="btn btn-primary btn-xs my-xs-btn" type="button" onClick="changeRec(".$num.")" >'
						+ '<span class="glyphicon glyphicon-pencil"></span> Edit</button>';
			}
		</script>
	</table>
</body>
</html>
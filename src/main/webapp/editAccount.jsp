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

<script type="text/javascript">
	 function setEditForm(fn, ln, add, zip, cno, cred) { 
		$('#firstName1').val(fn);
		$('#lastName1').val(ln);
		$('#address1').val(add);
		$('#zipcode1').val(zip);
		$('#contactNo1').val(cno);
		$('#creditCardNo1').val(cred);
	}
	$(document).ready(function() {
		var fname="<%=session.getAttribute("firstName")%>";
		console.log("<%=session.getAttribute("lName")%>")
		console.log("<%=session.getAttribute("lName")%>");
		var lname = "<%=session.getAttribute("lastName")%>";
		var add = "<%=session.getAttribute("address")%>";
		var zip = Number("<%=session.getAttribute("zipcode")%>");
		var cno = Number("<%=session.getAttribute("contactNo")%>");
		var cred = Number("<%=session.getAttribute("creditCardNo")%>");
		setEditForm(fname,lname, add,zip, cno,cred);
	});

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
		<form class="col s12" action="editAccountInfoSave" method="post" id="editForm">
												<div class="row">

													<div class="input-field col s6">
														<input id="firstName1" name="firstName1" type="text"
															class="validate" /> <label for="firstName1">First
															Name</label>
													</div>
													<div class="input-field col s6">
														<input id="lastName1" name="lastName1" type="text"
															class="validate" /> <label for="lastName1">Last
															Name</label>
													</div>
												</div>
												<div class="row">
													<div class="input-field col s12">
														<textarea id="address1" name="address1"
															class="materialize-textarea"></textarea>
														<label for="address1">Address</label>
													</div>
												</div>
												<div class="row">
													<div class="input-field col s12">
														<input type="number" name="zipcode1" id="zipcode1" /> <label
															for="zipcode1"><b> Zipcode </b></label>

													</div>
												</div>
												<div class="row">
													<div class="input-field col s12">
														<input type="number" name="contactNo1" id="contactNo1" />
														<label for="contactNo1"><b> Contact Number </b></label>
													</div>
												</div>
												<div class="row">
													<div class="input-field col s12">
														<input type="number" name="creditCardNo1"
															id="creditCardNo1" /> <label for="creditCardNo1"><b>
																CreditCard Number </b></label>
													</div>
												</div>

												<button class="btn waves-effect waves-teal" type="submit">Edit
													& Save</button>
			</form>
			
	</div>

</body>
</html>
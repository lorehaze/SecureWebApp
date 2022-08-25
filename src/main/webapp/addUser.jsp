<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="assets/scripts.jsp" />
<link rel="stylesheet" href="assets/styles.css">
</head>

<body bgcolor="#00FFFF">

	<form action="UserServlet" method="post" class="validateForm">
		<h2>Register A New User</h2>

		<table class="table table-borderless" align="center" cellpadding="5">
			<tr>
				<td><label>Email</label></td>
				<td><input id="email" type="email" name="email" required="required"></td>
			</tr>
			<tr>
				<!-- <td>Password</td>
				 <td><input type="password" name="password"></td> -->
				<td><label>password :</label>
				<td><input name="password" id="password" type="password"
					required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" /></td>
			</tr>
			<tr>
				<td><label>confirm password:</label></td>
				<td><input type="password" name="confirm_password"
					id="confirm_password" required="required"
					pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" /></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td></td>
				<td><span id='message'></span></td>
			<tr>

				<td><button id="btn-add" type="submit" name="Action"
						value="Add"
						class="btn btn-outline-success btn-xs custom-1 custom-3"
						disabled="true">Add User</button></td>

				<td><a href="index.jsp"
					class="btn btn-outline-secondary btn-xs custom-1 custom-3">Back</a></td>
			</tr>
		</table>

	</form>


	<script src="assets/js/passwordVerification.js"></script>

</body>
</html>
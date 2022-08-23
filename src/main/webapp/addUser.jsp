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
	<form action="UserServlet" method="post">
		<h2 style="text-align: center">Register A New User</h2>
		<a href="view.jsp">List Users</a>



		<table class="table table-borderless" align="center" cellpadding="5">
			<tr>
				<td><label>Email</label></td>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<!-- <td>Password</td>
				 <td><input type="password" name="password"></td> -->
				<td><label>password :</label>
				<td><input name="password" id="password" type="password"
					required="required" /></td>
			</tr>
			<tr>
				<td><label>confirm password:</label></td>
				<td><input type="password" name="confirm_password"
					id="confirm_password" required="required" /></td>
			</tr>
			<tr>
				<td></td>
				<td><span id='message'></span></td>
			<tr>

				<td><button id="btn-add" type="submit" name="Action" value="Add"
						class="btn btn-outline-success btn-xs custom-1" disabled="true">Add User
					</button></td>

				<td><a href="index.jsp"
					class="btn btn-outline-secondary btn-xs custom-1">Back</a></td>
			</tr>
		</table>

	</form>

	<!-- Password Matching -->
	<script>
		$('#password, #confirm_password').on('keyup', function() {
			if ($('#password').val() == $('#confirm_password').val()) {
				$('#message').html('Matching').css('color', 'green');
			} else
				$('#message').html('Not Matching').css('color', 'red');
		});
	</script>

	<!-- Unlock button if passwords match -->

	<script>
		$("#confirm_password").blur(function() {
			var user_pass = $("#password").val();
			var user_pass2 = $("#confirm_password").val();
			//var enter = $("#enter").val();

			if (user_pass.length == 0) {
				alert("Please, fill password first");
				$("#btn-add").prop('disabled', true)//use prop()
			} else if (user_pass == user_pass2) {
				$("#btn-add").prop('disabled', false)//use prop()
			} else {
				$("#btn-add").prop('disabled', true)//use prop()
				alert("Password doesn't match!");
			}

		});
	</script>

</body>
</html>
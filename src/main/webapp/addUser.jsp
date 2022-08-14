<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="WEB-INF/assets/scripts.jsp" />
</head>

<body bgcolor="#00FFFF">
	<form action="UserServlet" method="post">
		<h1 style="text-align: center">Register A New User</h1>
		<a href="view.jsp">List Users</a>



		<table class="table table-borderless" border="1" align="center"
			cellpadding="5"
			style="font-size: 100%; font-family: inherit; font-style: normal; background-color: window; width: auto;">
			<tr>
				<td><label>Email</label></td>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<!-- <td>Password</td>
				 <td><input type="password" name="password"></td> -->
				<td><label>password :</label>
				<td><input name="password" id="password" type="password" /></td>
			</tr>
			<tr>
				<td><label>confirm password:</label></td>
				<td><input type="password" name="confirm_password"
					id="confirm_password" /></td>
			</tr>
			<td></td>
			<td>	<span id='message'></span> </td>
			<tr>
				<td><input type="submit" name="Action" value="Add"></td>
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

</body>
</html>
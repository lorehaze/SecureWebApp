<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="assets/scripts.jsp" />
<link rel="stylesheet" href="assets/styles.css">
</head>
<body>
	<br>
	<br>
	<form action="LoginServlet" method="post">
		<table class="table table-borderless" align="center" cellpadding="5">

			<tr>
				<td><label>Email: </label>
				<td><input type="email" name="email" id="email"
					placeholder="Insert your email"></td>
			</tr>
			<tr>
				<td><label>Password: </label>
				<td><input name="password" id="password" type="password"
					placeholder="Insert your password" /></td>
			</tr>
			<tr>
				<td></td>
				<td><div class="form-check form-switch">
						<input class="form-check-input" type="checkbox"
							id="flexSwitchCheckChecked" name="rememberme"> <label
							class="form-check-label" for="flexSwitchCheckChecked">Remember
							me</label>
					</div></td>
			</tr>
			<tr>
				<td><button type="submit" name="Action" value="Login"
						class="btn btn-outline-success btn-xs custom-1 custom-3">Login</button></td>
				<td><a href="index.jsp"
					class="btn btn-outline-secondary btn-xs custom-1 custom-3">Back</a></td>
			</tr>

		</table>
	</form>

	<script>
		$(document).ready(function() {
			$('button[type="submit"]').attr('disabled', true);
			$('input[type="email"],input[type="password"]').on('keyup', function() {
				var email_value = $("#email").val();
				var pwd_value = $('#password').val();
				if (email_value != '' && pwd_value != '') {
					$('button[type="submit"]').attr('disabled', false);
				} else {
					$('button[type="submit"]').attr('disabled', true);
				}
			});
		});
	</script>

</body>
</html>
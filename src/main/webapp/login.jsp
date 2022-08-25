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
<br><br>
	<form action="LoginServlet" method="post">
		<table class="table table-borderless" align="center"
			cellpadding="5">

			<tr>
				<td><label>Email: </label>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<td><label>Password: </label>
				<td><input name="password" id="password" type="password" /></td>
			</tr>
			<tr>
				<td><button type="submit" name="Action" value="Login"
						class="btn btn-outline-success btn-xs custom-1 custom-3">Login</button></td>
				<td><a href="index.jsp"
					class="btn btn-outline-secondary btn-xs custom-1 custom-3">Back</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
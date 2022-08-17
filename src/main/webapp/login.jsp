<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="WEB-INF/assets/scripts.jsp" />
</head>
<body>

	<form action="LoginServlet" method="post">
		<table class="table table-borderless" border="1" align="center"
			cellpadding="5"
			style="font-size: 100%; font-family: inherit; font-style: normal; background-color: window; width: auto;">

			<tr>
				<td><label>Email: </label>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<td><label>Password: </label>
				<td><input name="password" id="password" type="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="Action" value="Login"></td>
			</tr>
		</table>
	</form>
</body>
</html>
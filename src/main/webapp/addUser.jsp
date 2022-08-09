<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="WEB-INF/assets/scripts.jsp" />

<title>SecureWebApp</title>
</head>
<body bgcolor="#00FFFF">
	<form action="UserServlet" method="post">
		<h1 align="center">Register A New User</h1>
		<h1>
			<a href="view.jsp">List Users</a>
		</h1>



		<table class="table table-borderless" border="1" align="center"
			cellpadding="5"
			style="font-size: 100%; font-family: inherit; font-style: normal; background-color: window; width:30">
			<tr>
				<td>Email</td>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="Action" value="Add"></td>
			</tr>
		</table>

	</form>
</body>
</html>
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

	<script type="text/javascript"></script>

	<h1>Welcome to your profile!</h1>
	<br>
	<h2>You're currently logged in as: ${cookie['email'].getValue()}</h2>



	<center>
		<a href="LogoutServlet"
			class="btn btn-outline-secondary btn-xs custom-1 custom-3">Logout</a>
	</center>
	<center>
		<a href="index.jsp"
			class="btn btn-outline-secondary btn-xs custom-1 custom-3">Back</a>
	</center>
</body>
</html>
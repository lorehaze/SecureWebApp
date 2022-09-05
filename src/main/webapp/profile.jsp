<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="assets/scripts.jsp" />
<link rel="stylesheet" href="assets/styles.css">
<meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=login.jsp">
</head>
<body>

	<script type="text/javascript"></script>

	<h1>Welcome to your profile!</h1>
	<br>
	<h2>You're currently logged in as: ${cookie['email'].getValue()}</h2>

	<br>


	
	
	<center>
			<a href="LogoutServlet"
				class="btn btn-outline-dark btn-sm custom-1 custom-2" role="button"
				aria-disabled="true">Log Out</a> <br>
		</center>
</body>
</html>
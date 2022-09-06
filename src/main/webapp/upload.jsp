+<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="assets/scripts.jsp" />
<link rel="stylesheet" href="assets/styles.css">
<meta http-equiv="refresh"
	content="${pageContext.session.maxInactiveInterval};url=login.jsp">
</head>
<body>

	<script type="text/javascript"></script>

	<h1>Welcome to your profile!</h1>
	<br>
	<h2>You're currently logged in as: ${cookie['email'].getValue()}</h2>

	<br>
	<br>

	<br>
	<br>
	<br>
	<br>

	<form action="ProfileUpload" method="post"
		enctype="multipart/form-data">
		<center>
			<input type="file" placeholder="Upload your profile photo"
				name="photo">
			<button type="submit">Submit</button>
		</center>
	</form>


</body>
</html>
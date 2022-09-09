<%@ page language="java" contentType="text/html; charset=UTF-8"
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

	<form action="ProfileUpload" method="post"
		enctype="multipart/form-data">
		<div class="mb-3">
			<label for="formFile" class="form-label">Upload your profile
				picture: </label> <br> <input class="form-control" type="file"
				name="photo" id="formFile" accept=".jpg,.jpeg,.png,.gif"> <br>
			<br>
			<br>
			<br>
			<button type="submit"
				class="btn btn-outline-dark btn-xs custom-1 custom-3 mb-3">Submit</button>
			<a href="ProfileServlet"
				class="btn btn-outline-secondary btn-xs custom-1 custom-3 mb-3">Back</a>
		</div>
	</form>
</body>
</html>
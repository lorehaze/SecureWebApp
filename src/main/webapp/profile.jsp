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

	<table class="table table-borderless" align="center" cellpadding="5">

		<tr>
			<!-- 	<td><button a href="PictureUploadServlet" type="submit"
					name="Action" value="UploadPicture"
					class="btn btn-outline-dark btn-xs custom-1 custom-3">
					Profile Picture</button></td>-->
			<form action="PictureUploadServlet" method="post">
				<td><input type="submit" name="Action" value="UploadPicture"
					value="Upload Picture"
					class="btn btn-outline-dark btn-sm custom-1 custom-3" role="button">
				</td>
			</form>

		</tr>

		<tr>
			<td><button type="submit" name="Action" value="UploadProject"
					class="btn btn-outline-dark btn-xs custom-1 custom-3">New
					Project</button></td>
		</tr>

		<tr>
			<td><button type="submit" name="Action" value="ShowAll"
					class="btn btn-outline-dark btn-xs custom-1 custom-3">All
					Projects</button></td>
		</tr>
		<tr>
			<td>
				<form action="LogoutServlet" method="post">
					<input type="submit" value="Logout"
						class="btn btn-outline-dark btn-sm custom-1 custom-2"
						role="button">
				</form>
			<td>
		</tr>
	</table>


</body>
</html>
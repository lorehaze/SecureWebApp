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
			<td><button type="submit" name="Action" value="UploadPicture"
					class="btn btn-outline-dark btn-xs custom-1 custom-3">
					Profile Picture</button></td>
		</tr>

		<tr>
			<td><button type="submit" name="Action" value="UploadProject"
					class="btn btn-outline-dark btn-xs custom-1 custom-3">
					New Project</button></td>
		</tr>

		<tr>
			<td><button type="submit" name="Action" value="ShowAll"
					class="btn btn-outline-dark btn-xs custom-1 custom-3">
					All Projects</button></td>
		</tr>
		<tr>
			<td>
				<form action="LogoutServlet" method="post">
					<center>
						<input type="submit" value="Logout"
							class="btn btn-outline-dark btn-sm custom-1 custom-2"
							role="button">
					</center>
				</form>
			<td>
		</tr>
	</table>


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
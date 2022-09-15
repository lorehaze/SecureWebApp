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
<%@page import="java.sql.Connection"%>
<%@page import="com.swa.dbconnection.Database"%>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.SQLException"%>
<%@page import="com.swa.session.SessionManagement" %>;
</head>
<body>

	<script type="text/javascript"></script>

	<h1>Welcome to your profile!</h1>
	<br>
	<h2>You're currently logged in as: ${cookie['email'].getValue()}</h2>

	<br>

		<%
		Cookie[] cookies = request.getCookies();
		
		SessionManagement sessionman = new SessionManagement();
		
		String email = sessionman.retrieveEmail(cookies);
		
		Connection conn = Database.getConn_read();
		String sql = "SELECT * FROM user where email=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		%>
	
			<img src=ShowPictureServlet?user_id=<%=rs.getInt(1)%>
				alt="No image uploaded" width="100px" height="100px" class="img-profile">
		<%
		}
		%>



	<table class="table table-borderless" align="center" cellpadding="5">

		<tr>
			<!-- 	<td><button a href="PictureUploadServlet" type="submit"
					name="Action" value="UploadPicture"
					class="btn btn-outline-dark btn-xs custom-1 custom-3">
					Profile Picture</button></td>-->
			<form action="PictureUploadServlet" method="post">
				<td><input type="submit" name="Action" value="UploadPicture"
					class="btn btn-outline-dark btn-sm custom-1 custom-3" role="button">
				</td>
			</form>

		</tr>

		<tr>
			<!-- <td><button projectUpload.jsp" type="submit" name="Action" value="UploadProject" 
					class="btn btn-outline-dark btn-xs custom-1 custom-3">New
					Project</button></td> -->
					<td><a href="projectUpload.jsp"
				class="btn btn-outline-dark btn-sm custom-1 custom-3"
				role="button" value="UploadProject">New Project</a></td>
		</tr>

		<tr>
			<td><form action="ShowProjectsServlet" method="get">
					<input type="submit" value="Show Projects"
						class="btn btn-outline-dark btn-sm custom-1 custom-3"
						role="button">
				</form></td>
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
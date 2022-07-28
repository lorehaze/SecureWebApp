<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.swa.dbconnection.Database"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SecureWebApp</title>
</head>
<style>
<!--
a {
	text-decoration: none;
}
-->
</style>
<body bgcolor="#00FFFF">
	<form action="">
		<h1 align="center">Users Management</h1>
		<h2 align="center">
			<a href="addUser.jsp">Add New User</a><br>
		</h2>
		<!--  All DOctor shows-->
		<h1 align="center">List of Users</h1>
		<table border="1" align="center" cellpadding="5"
			style="font-size: 200%; font-family: inherit; font-style: normal; background-color: window;">
			<tr>
				<th>ID</th>
				<th>Email</th>
			</tr>

			<%
			Connection con = Database.getConn();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
			while (resultSet.next()) {
			%>

			<tr>
				<td>
					<%
					out.print(resultSet.getInt(1));
					%>
				</td>
				<td>
					<%
					out.print(resultSet.getString(2));
					%>
				</td>
				<td>
					<%
					out.print(resultSet.getString(3));
					%>
				</td>
				<td>
					<%
					out.print(resultSet.getString(4));
					%>
				</td>
				<td>
					<%
					out.print(resultSet.getString(5));
					%>
				</td>
				<td>
					<%
					out.print(resultSet.getString(6));
					%>
				</td>

			</tr>
			<%
}
%>


		</table>
	</form>
</body>
</html>
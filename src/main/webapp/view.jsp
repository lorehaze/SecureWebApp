<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.swa.dbconnection.Database"%>
<%@page import="java.sql.Connection"%>
<!--  All DOctor shows-->
<h1 align="center">List of Users</h1>
<table border="1" align="center" cellpadding="5"
	style="font-size: 200%; font-family: inherit; font-style: normal; background-color: window;">
	<tr>
		<th>ID</th>
		<th>Email</th>
	</tr>

	<%
	Connection con = Database.getConn_read();
	Statement statement = con.createStatement();
	ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
	while (resultSet.next()) {
	%>

	<tr>
		<td>
			<%
			out.print(resultSet.getInt("user_id"));
			%>
		</td>
		<td>
			<%
			out.print(resultSet.getString("email"));
			%>
		</td>

	</tr>
</table>
<%
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="assets/scripts.jsp" />
<link rel="stylesheet" href="assets/styles.css"></head>
<body>


<form action="/UploadServlet" method="post" enctype="multipart/form-data">
Select Image to Upload:<input type="file" name="photo">
<br>
<input type="submit" value="Upload">
</form>


</body>
</html>
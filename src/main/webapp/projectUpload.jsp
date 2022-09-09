<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="assets/scripts.jsp" />
<link rel="stylesheet" href="assets/styles.css">
</head>
<body>

<br>
<br>
<br>
<br>
<br>

	<form action="ProjectUploadServlet" method="post"
		enctype="multipart/form-data">
		<div class="mb-3">
			<label for="formFile" class="form-label text-center">Upload your project
				proposal in txt format: </label> <input class="form-control" type="file"
				name="fileToUpload" accept=".txt"> <br> <br>
			<button type="submit"
				class="btn btn-outline-dark btn-xs custom-1 custom-3 mb-3">Submit</button>
			<a href="ProfileServlet"
				class="btn btn-outline-secondary btn-xs custom-1 custom-3 mb-3">Back</a>
		</div>
	</form>


</body>
</html>
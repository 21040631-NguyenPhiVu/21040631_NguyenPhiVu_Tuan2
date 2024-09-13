<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	form {
		width: 500px;
		height: 400px;
		margin: 0 auto;
	}
</style>
</head>
<body>
	<form action="fileUpload" method="post" enctype="multipart/form-data" >
		<input type="file" name="myFiles[]" multiple="multiple">
		<input type="submit" value="Upload" name="upload" id="upload" />
	</form>
</body>
</html>
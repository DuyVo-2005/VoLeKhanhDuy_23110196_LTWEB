<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- cac tag hay dung trong jsp: if, else, for, while, ... -->
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
</head>
<body>
	<form action="/BaiTap2/forgotpassword" method="post">
		<c:if test="${not empty message}">
			<h3 style="color: green;">${message}</h3>
		</c:if>

		<c:if test="${not empty error}">
			<h3 style="color: red;">${error}</h3>
		</c:if>

		<div class="container">
			<label for="uname"><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="uname" required> <label
				for="email"><b>Email</b></label> <input type="text"
				placeholder="Enter Email" name="email" required>

			<button type="submit">Lấy lại mật khẩu</button>
		</div>
	</form>
</body>
</html>
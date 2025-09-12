<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:url value="/admin/edit" var="edit"></c:url>
<form role="form" action="${edit}" method="post"
	enctype="multipart/form-data">
	<input name="id" value="${user.id }" hidden="">
	<div class="form-group">
		<label>Full name:</label> <input type="text" class="form-control"
			value="${user.fullname }" name="fullname" />
	</div>
	<div class="form-group">
		<label>Phone:</label> <input type="text" class="form-control"
			value="${user.phone }" name="phone" />
	</div>
	<div class="form-group">
		<c:url value="/image?fname=${user.image }" var="imgUrl"></c:url>
		<img class="img-responsive" width="100px" src="${imgUrl}" alt="">
		<label>Image:</label> <input type="file" name="image"
			value="${user.image }" />
	</div>
	<button type="submit" class="btn btn-default">Edit</button>
	<button type="reset" class="btn btn-primary">Reset</button>
</form>

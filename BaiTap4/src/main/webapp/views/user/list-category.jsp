<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Category List</title>
</head>
<body>
	<table border="1" cellpadding="5" cellspacing="0">
		<thead>
			<tr>
				<th>STT</th>
				<th>Ảnh</th>
				<th>Tên danh mục</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cateList}" var="cate" varStatus="STT">
				<tr>
					<td>${STT.index + 1}</td>
					<c:url value='/image' var="imgUrl">
						<c:param name="fname" value="${cate.image}" />
						<c:param name="t" value="${System.currentTimeMillis()}" />
					</c:url>
					<td><img height="150" width="200" src="${imgUrl}" /></td>
					<td>${cate.categoryName}</td>
					<td><a href="<c:url value='/user/edit?id=${cate.id}'/>">Sửa</a>
						| <a href="<c:url value='/user/delete?id=${cate.id}'/>">Xóa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button type="button"
		onclick="location.href='<c:url value='/user/add' />'"
		class="btn btn-primary">Thêm</button>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页测试</title>
http://localhost:8080/basicWebDemo/page.do
</head>
<body>
	<table border="1">
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>密码</th>
			<th>照片</th>
		</tr>
		<c:forEach items="${PageInfo.list }" var="pi">
			<tr>
				<td>${pi.id }</td>
				<td>${pi.username }</td>
				<td>${pi.password }</td>
				<td>${pi.photo }</td>
			</tr>
		</c:forEach>
	</table>
	<a href="page.do?pageNumber=${PageInfo.pageNumber-1 }&pageSize=${PageInfo.pageSize}" 
		<c:if test="${PageInfo.pageNumber<=1 }"> onclick="javascript:return false;" </c:if> >上一页
	</a>		
	<a href="page.do?pageNumber=${PageInfo.pageNumber+1 }&pageSize=${PageInfo.pageSize}" 
		<c:if test="${PageInfo.pageNumber>=PageInfo.total }"> onclick="javascript:return false;" </c:if> >下一页
	</a>
</body>
</html>
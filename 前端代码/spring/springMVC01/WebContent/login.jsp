<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>登陆</title>
</head>
<body>
	<h1>登陆</h1>
	<!--  http://localhost:8080/springMVC01/login ,login其实是这个注解的方法名  -->
	<form action="login" method="post">
		用户名:<input type="text" name="name"/><br/>
		密码:<input type="password" name="pwd"/><br/>
		<input type="submit" value="登陆"/>
	</form>
	<hr>
	<h1><a href="register.jsp">注册<a/></h1>
	<hr>
	<h1>传值测试</h1>
	<form action="test" method="post">
		名字:<input type="text" name="name"/><br/>
		年龄:<input type="text" name="age"/><br/>
		复选框：
		<input type="checkbox" name="sex" value="1">
		<input type="checkbox" name="sex" value="2">
		<input type="checkbox" name="sex" value="3">		
		<input type="submit" value="提交后台"/>		
	</form>
	<h3>${rst}</h3>
	
	<br>
	<form action="test1" method="post">
		<!-- <input type="text" name="peo.name"/><br>
		<input type="text" name="peo.age"/> -->
		<input type="text" name="peo[0].name"/><br>
		<input type="text" name="peo[0].age"/><br>
		<input type="text" name="peo[1].name"/><br>
		<input type="text" name="peo[1].age"/><br>
		<input type="submit" value="提交后台2"/>	
	</form>		
	<c:forEach var="peo" items="${datalist}">
		<p><span>${peo.name}</span>   <span>${peo.age}</span></p>
	</c:forEach>
	
	<hr>
	<a href="dwn?fileName=a.png">下载</a>
	<hr>
	<form action="up" enctype="multipart/form-data" method="post">
	上传文件:<input type="file" name="file"/><br/>
	<input type="submit" value="提交"/>
</form>
	
</body>
</html>
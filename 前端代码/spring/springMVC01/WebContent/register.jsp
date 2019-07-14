<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <url-pattern>/</url-pattern><url-pattern>/</url-pattern>默认会过滤所有资源，所以出现jquery无效
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
 -->
<script src="https://cdn.staticfile.org/jquery/3.1.1/jquery.min.js"></script>
<script  type="text/javascript">
$(function(){
	var username = false;
	var password = false;
	var passwordSure = false;
	//用户名的验证
	$(":text:eq(0)").blur(function(){
		if($(this).val()==""){
			$(this).next().css("color","red").html("X");
			username = false;
		}else{
			$(this).next().css("color","green").html("√");
			username = true;
		}
	});
	//密码的验证
	$(":password:eq(0)").blur(function(){
		//在js中要求正则两侧//
		if(!$(this).val().match(/^\w{1,3}$/)){
			$(this).next().css("color","red").html("X");
			password= false;
		}else{
			$(this).next().css("color","green").html("√");
			password = true;
		}
	});
	//确认密码
	$(":password:eq(1)").blur(function(){
		if($(this).val()==""||$(this).val()!=$(":password:eq(0)").val()){
			$(this).next().css("color","red").html("X");
			passwordSure = false;
		}else{
			$(this).next().css("color","green").html("√");
			passwordSure = true;
		}
	});
	
	
	$(":submit").click(function(){
		if(username==false||password==false||passwordSure==false||$(":file").val()==""){
			alert("请添加完整信息");
			return false;
		}
	})
});
</script>
</head>
<body>
<h1>注册</h1>
<form action="register" method="post" enctype="multipart/form-data">
	用户名:<input type="text" name="username"/><span></span><br/>
	密码:<input type="password" name="password"/><span></span><br/>
	确认密码:<input type="password" name="passwordSure"/><span></span><br/>
	头像:<input type="file" name="file"/><br/>
	<input type="submit" value="注册"/>
</form>
</body>
</html>
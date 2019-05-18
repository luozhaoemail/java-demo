<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajax学习</title>
<!-- http://localhost:8080/basicWebDemo/ajax/myajax.jsp -->
<script type="text/javascript">
	//请求原理
	function getData(){		
		var showdiv=document.getElementById("showdiv");
		showdiv.innerHTML="Ajax的原理就是这样一个js函数getData()，通过一个函数去请求数据，这个函数是这个页面的，因此不必重新加载页面";
	}
	
	//ajax的基本流程
	function getData2(){		
		//创建ajax引擎对象XMLHttpRequest
		var ajax;
		if(window.XMLHttpRequest){//火狐
			ajax=new XMLHttpRequest();
		}else if(window.ActiveXObject){//IE
			ajax=new ActiveXObject("Msxml2.XMLHTTP");
		}		
			
		//复写onreadystatement函数
		ajax.onreadystatechange=function(){
			//判断Ajax状态码: 4表示成功接收数据
			if(ajax.readyState==0){
				alert("ajax请求0: 表示XMLHttpRequest已建立，但还未初始化，这时尚未调用open方法 ");
			}
			if(ajax.readyState==1){
				alert("ajax请求1: 表示open方法已经调用，但未调用send方法（已创建，未发送） ");
			}
			if(ajax.readyState==2){
				alert("ajax请求2: 表示send方法已经调用，其他数据未知 ");
			}
			if(ajax.readyState==3){
				alert("ajax请求3: 表示请求已经成功发送，正在接受数据 ");
			}
			if(ajax.readyState==4){
				alert("ajax请求4: 表示数据已经成功接收。");
				//判断响应状态码
				if(ajax.status==200){
					//3============获取响应内容
					var result=ajax.responseText;
					//处理响应内容
						//获取元素对象
						var showdiv=document.getElementById("showdiv");
						showdiv.innerHTML=result;
				}else if(ajax.status==404){
					//获取元素对象
					var showdiv=document.getElementById("showdiv");
					showdiv.innerHTML="请求资源不存在:404";
				}else if(ajax.status==500){
					//获取元素对象
					var showdiv=document.getElementById("showdiv");
					showdiv.innerHTML="服务器繁忙:500";
				}
			}else{
				//获取元素对象
				var showdiv=document.getElementById("showdiv");
				showdiv.innerHTML="<img src='../img/2.gif' width='200px' height='100px'/>";
			}
		}

		/**发送请求 
		   ajax.open(method, url, async, username, password); //1 打开请求
		   ajax.open("get","http://localhost:8080/basicWebDemo/ajax",true);
		
		      当前myajax.jsp的完整路径是：http://localhost:8080/basicWebDemo/ajax/myajax.jsp， AjaxServlet的路径是：/ajax
		     而WebContent是/, WebContent/ajax/myajax.jsp(二级子目录中)请求AjaxServlet应该是 ../ajax
		*/
		//get方式:请求实体拼接在URL后面
		//ajax.open("get", "../ajax", true);		
		//ajax.open("get", "../ajax?uname=张三&pwd=123", true); 
		//ajax.send(null); //2 发送数据给服务器
		
		//post方式：请求实体需要单独的发送
		ajax.open("post", "../ajax");
		//ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");//数据被编码为键值对
		//ajax.send("name=张三&pwd=123");
		
		ajax.setRequestHeader("Content-Type","application/json");//数据被编码为json
		ajax.send("{'uname':'张三','pwd':'123'}");
		
		alert("false同步请求先出结果再弹出本框，true异步先弹出本框再出结果");				
	}
</script>
<style type="text/css">
	#showdiv{
		border:solid 1px;
		width:200px;
		height:100px; 
	}
</style>
</head>

<body>
	<input type="button" value="js函数 " onclick="getData()"/>
	<input type="button" value="ajax函数" onclick="getData2()"/>
	<div id="showdiv"></div>
<!-- 
	Ajax学习：
		1、ajax的概念
			局部刷新技术。不是一门新技术，是多种技术的组合。是浏览器端的技术。
		2、ajax的作用
			实现在当前结果页中显示其他请求的响应内容
		3、ajax的使用
			ajax的基本流程
				//创建ajax引擎对象
				//复写onreadystatement函数
					//判断ajax状态码
						//判断响应状态码
							//获取响应内容(响应内容的格式)
								//普通字符串：responseText
								//json(重点)：responseText
									其实就是讲述数据按照json的格式拼接好的字符串，方便使用eval方法
									将接受的字符串数据直接转换为js的对象
									
									json格式：
										var 对象名={
												属性名:属性值,
												属性名:属性值,
												……
											}
									
								//XML数据：responseXML.返回document对象
									通过document对象将数据从xml中获取出来
							//处理响应内容(ajax本质是通过js代码操作jsp文档结构)
				//发送请求
					//get请求
						get的请求实体拼接在URL后面，？隔开，键值对
						ajax.open("get","url");
						ajax.send(null);
					//post请求
						有单独的请求实体
						ajax.open("post", "url");
						ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
						ajax.send("name=张三&pwd=123");
			ajax的状态码
				ajax状态码:
					readyState：0,1,2,3,4
						4: 表示响应内容被成功接收
				响应状态码:
					status
					200:表示一切OK
					404:资源未找到
					500：内部服务器错误
			ajax的异步和同步
				ajax.open(method,urL,async)
				async：设置同步代码执行还是异步代码执行
					  true代表异步，默认是异步
					  false代表同步
 -->	
</body>
</html>
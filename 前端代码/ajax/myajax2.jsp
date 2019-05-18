<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax获取响应数据</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/ajaxUtil.js"></script>
<script type="text/javascript">
function getData(){
	var name=document.getElementById("uname").value;
	var data="uname="+name;
	// 调用自己封装的方法
	myAjax("post", "../user.do", data, function(a){ //参数a是ajax对象
 		//获取响应数据
 		/**返回xml格式：
	 		<user>
				<name>李四</name>
				<pwd>123</pwd>
			</user>
 		 var doc=a.responseXML;
		 alert(doc.getElementsByTagName("name")[0].innerHTML);
 		*/
		var rs = a.responseText; 
 		var json = JSON.parse(rs);
		//处理响应数据
		//[{"uid":1,"uname":"德玛西亚","fav":"地地道道的"},{"uid":2,"uname":"艾希","fav":"等等等等等等"},{"uid":3,"uname":"天使","fav":"反反复复分"},{"uid":4,"uname":"盲僧","fav":"福福福福福福福福福福福"},{"uid":5,"uname":"剑圣","fav":"快快快快快快"}]		
		//alert("json数组长度="+json.length); 
				
		var trStr = '';//动态拼接table
		for(var i=0; i<json.length; i++){			
			trStr += '<tr>';			
			trStr += '<td width="10%">' + json[i].uid + '</td>';//数据表的主键值
			trStr += '<td width="20%">' + json[i].uname + '</td>';//字段值
			trStr += '<td width="40%">' + json[i].fav + '</td>';//字段值
			/*经典之处，要将主键对应的值以json的形式进行传递，才能在后台使用*/
			trStr += "<td width='30%' ><a href='#' style='text-decoration:none' onclick='Delete(\"" + json[i].uid + "\")'>删除</a><td>";
			trStr += '</tr>';
		}
		//document.write(trStr);
		//alert(trStr);
		$("tbody").html(trStr); //jQuery的追加方式
		//var tb=document.getElementById("tb");//获取table表格对象
		
	});
}

function Delete(uid){
	var flag=window.confirm("你确定要删除吗?");
	alert(flag);
}

function getjQueryAjax(){
	var name=document.getElementById("uname").value;
	var parm="uname="+name;
	// 调用jQuery的ajax方法: jQuery 底层 AJAX 实现,$.ajax()可以不带任何参数直接使用。
	$.ajax({
		type : "get",
		async : true,
		//url : "${pageContext.request.contextPath}/user.do?parm, //带参数
		url : "${pageContext.request.contextPath}/user.do", //不带参数
		success :function(result){			
			alert("1 get请求："+result);
		},		
		statusCode: { //可选参数
			404: function() {
		    	alert('page not found');
		  	}
		},
	});
	
	$.ajax({
		type : "post",
		async : true,		
		url : "${pageContext.request.contextPath}/user.do",
		data: parm,
		success :function(result){			
			alert("2 post请求："+result);
		}
	});	
	
	$.get(
		"../user.do", //待载入页面的URL地址
		{ uname: parm, time: "123456" }, //传送2个参数,待发送 Key/value 参数
		function(result){    //载入成功时回调函数。
			alert("3 get: " + result[0].uname);
	 	},
	 	"json" //返回内容格式，xml, html, script, json, text, _default
	 );
	
	$.getJSON(
		"../user.do", //待载入页面的URL地址
		{ uname: parm, time: "123456" }, //传送2个参数,待发送 Key/value 参数
		function(result){    //载入成功时回调函数。
			alert("4 getJSON: " + result.length);
	 	}
	 );
	
	$.post(
		"${pageContext.request.contextPath}/user.do", //待载入页面的URL地址
		{ uname: parm, time: "123456" }, //传送2个参数,待发送 Key/value 参数
		function(result){    //载入成功时回调函数。
			alert("5 post: " + result[1].uname);
	 	},
	 	"json" //返回内容格式，xml, html, script, json, text, _default
	 );
	
	
}
</script >
</head>
<body>
<h3>欢迎访问LOL英雄商店</h3>
	<hr>
	英雄名称：<input type="text" name="uname" value="" id="uname"/>
		   <input type="button" value="搜索" onclick="getData()">
	<hr>
	<table id="tb" border="1px"  width="500px">
		<thead>
			<tr>
				<th>编号</th>
				<th>英雄名称</th>
				<th>英雄爱好</th>
				<th>操作栏</th>
			</tr>
		</thead>
		<tbody id="tbody">
		</tbody>
	</table>
</body>
</html>
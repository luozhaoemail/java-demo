<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import = "pojo.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL表达式</title>
</head>
<body>

<!-- 使用传统方式获取作用域对象的数据 
直接走jsp页面,接收不到str：  http://localhost:8080/basicWebDemo/el.jsp?uname=张三&pwd=123 
走servlet处理,才能接收后台的值：http://localhost:8080/basicWebDemo/ElServlet?uname=张三&pwd=123
-->
<h3>EL表达式学习：使用传统方式获取作用域对象的数据</h3>
<b>用户名：<%=request.getParameter("uname")%></b><br />
<b>密码：<%=request.getParameter("pwd")%></b><br />
<b>获取后台属性"str"：<%=request.getAttribute("str")%></b><br />

<b>user对象： <%=request.getAttribute("user").toString() %></b><br />
<b><%=((User)request.getAttribute("user")).getAddr().getTown() %></b><br />

<b>获取list.get(2): <%=((ArrayList)request.getAttribute("list")).get(2)%></b><br />
<b>获取list2.get(0):<%=((User)(((ArrayList)request.getAttribute("list2")).get(0))).getAddr().getPre() %></b><br />
<b>获取HashMap: <%=((HashMap)request.getAttribute("map")).get("c") %></b><br />
<b>获取HashMap: <%=((User)(((HashMap)request.getAttribute("map2")).get("a1"))).getAddr().getCity() %></b><br />

<b>获取空值：<%=request.getAttribute("s") %></b><br />
<b>获取空值：<%=request.getAttribute("s1") %></b><br />
<b>获取空值：<%=request.getAttribute("s2") %></b><br />
<b>获取空值：<%=request.getAttribute("s3") %></b><br />


<!-- 使用EL表达式获取作用域对象数据 -->
<hr/>
<h3>EL表达式学习：使用EL表达式获取作用域对象的数据</h3>
<b>用户名：${param.uname}</b><br />
<b>密码：${param.pwd}或者：${paramValues.pwd[0] }</b><br />
<b>获取后台属性"str"：${str}</b><br />

<b>user对象： ${user }</b><br />
<b> ${user.addr.town }</b><br />

<b>获取list.get(2): ${list[2] }</b><br />
<b>获取list2.get(0):${list2[0].addr.pre }</b><br />
<b>获取HashMap: ${map.c }</b><br />
<b>获取HashMap: ${map2.a1.addr.city }</b><br />

<b>获取空值：${s }  =${empty s}</b><br /> <!-- EL表达式的空值判断 -->
<b>获取空值：${s1 } =${empty s1}</b><br />
<b>获取空值：${s2 } =${empty s2}</b><br />
<b>获取空值：${s3 } =${empty s3}</b><br />

<%
	String str2="哈哈哈，普通作用域里面获取不到值"; //这种普通作用域里面获取不到值
%>
<b>普通作用域里面获取不到值："str2"=${str2}</b><br />


<hr />
<!-- EL的四大作用域查找顺序：pageContext、request、session、application -->
<h3>EL的作用域查找顺序</h3>
<%
	pageContext.setAttribute("hello","1-->hello pageContext");
	request.setAttribute("hello","2-->hello request");
	session.setAttribute("hello","3-->hello session");
	application.setAttribute("hello","4-->hello application");
%>
<b>获取pageContext作用域：${hello }</b><br /> <!--最先获取pageContext-->
<b>获取pageContext作用域：${hello }</b><br /> <!--最先获取pageContext-->
<b>获取pageContext作用域：${hello }</b><br /> <!--最先获取pageContext-->
<b>获取pageContext作用域：${hello }</b><br /> <!--最先获取pageContext-->
全部获取：${pageScope.hello}， ${requestScope.hello}， ${sessionScope.hello}， ${applicationScope.hello}


<hr />
<!-- EL的逻辑运算 -->
<h3>EL的逻辑运算</h3>
1+2*3=${1+2*3 } <br/>
4==4 ： ${4==4}  <br/>
4%3 = ${4%3}  <br/>
sex==1?'男':'女' 三目运算 =  ${sex==1?'男':'女'} <br/>
+表示加法运算，不表示字符链接：1+'2' = ${1+'2'} <br/>

<hr />
<h3>EL获取请求头数据和Cookie数据</h3>
请求头数据: ${header}<br />
获取字段的头数据: ${headerValues["accept-language"][0]} <br /><br />
获取Cookie数据：${cookie}<br />
指定的cookie对象： ${cookie.JSESSIONID}<br />
指定的cookie对象存储的数据的键名： ${cookie.JSESSIONID.name}<br />
指定的cookie对象存储的数据的值： ${cookie.JSESSIONID.value}<br />

<%--**************
	传统方式获取作用域数据:
		缺点一:导入包
		缺点二:需要类型强转
		缺点三:获取数据的代码过于麻烦。
		
	**************	
	使用El表达式获取作用域数据:
		作用：获取作用域对象中的数据。
			注意：获取的是pageContext、request、session、application四个对象中的数据，其他数据一概不理会。
				找到了则获取返回，找不到则什么都不做，也不报错。
		语法：
			${表达式}
			表达式：
				获取请求数据:http://localhost:8080/basicWebDemo/ElServlet?uname=张三&pwd=123
					request对象存储了请求数据--->param.键名	   	返回值
					request对象存储了请求数据--->paramvalues.键名 	返回的是数组
				通过setAttribute方法存储到作用域对象中的数据
					${键名} 返回键名所对应的值。
					注意：
						如果存储的是普通字符串则直接返回
						如果存储的是对象，则返回的是对象
									获取对象中的数据：
										普通对象
											${键名.属性名.属性名....}  嵌套的引用成员变量
										集合对象
											list集合--->${键名[角标]}
											map集合--->${键名.map集合存储的键名}
		**************
		作用域查找顺序：
			默认查找顺序：
			pageConext-->request--->session--->application
			注意：
				每次查找都是从小到大进行查找，找到了则获取，不再继续找了。
			指定查找：
				${pageScope.键名}---${requestScope.键名}--${sessionScope.键名}--${applicationScope.键名}
		
		**************
		El表达式的逻辑运算：
			${逻辑表达式}：&& || !
			${算术表达式}：+，-，*，/
			${关系表达式}：>,<,>=,==,!=,%
			特殊:
				三目运算
			注意：
				+表示加法运算，不表示字符链接。使用EL表达式进行字符链接会报错。
		EL的空值判断:
			${empty 键名}   作用:判断键名对象的值是否存有数据。
			
		EL获取请求头数据和Cookie数据：
			请求头数据：
				${header}-->返回所有的请求头数据
				${header["键名"]}--->返回指定的键名的请求头数据
				${hedaerValues["键名"]}--->返回指定的键名(同键不同值)的值的数组。
			获取Cookie数据：
				${cookie}--->返回存储了所有的cookie对象的map集合
				${cookie.键名}---->返回指定的cookie对象
				${cookie.键名.name}--->返回指定的cookie对象存储的数据的键名。
				${cookie.键名.value}--->返回指定的cookie对象存储的数据的值。		
--%>

</body>
</html>
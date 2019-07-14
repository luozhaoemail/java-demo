<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL 标签库</title>
</head>
<body>
<!--基本标签学习  
 http://localhost:8080/basicWebDemo/jstl.jsp
-->
<%
	request.setAttribute("str","今天天气真好，适合学习");
%>
<h3>基本标签学习</h3>
<c:out value="out标签示例"></c:out><br />
<c:out value="${str}" default="嘿嘿"></c:out><br />

<c:set var="hello" value="1--> hello pageContext" scope="page"></c:set><br />
<c:set var="hello" value="2--> hello request" scope="request"></c:set><br />
<c:set var="hello" value="3--> hello session" scope="session"></c:set><br />
<c:set var="hello" value="4--> hello application" scope="application"></c:set><br />
pageContext: <c:out value="${pageScope.hello}"></c:out><br />
request: <c:out value="${requestScope.hello}"></c:out><br />
session: <c:out value="${sessionScope.hello}"></c:out><br />
application: <c:out value="${applicationScope.hello}"></c:out><br />

不加作用域：<c:out value="${hello}"></c:out>
<c:remove var="hello" scope="page"/><br />
不加作用域: <c:out value="${hello}"></c:out><br />


<hr />
<h3>逻辑标签学习</h3>
<!--传统方式：  -->
<%
	int  a=4;
	if(a>3){
%>
<b>传统方式：今天的天气有点热哦</b><br/>
<%} %>


<!--jstl方式： -->
<c:set var="a" value="4"></c:set>
<c:if test="${a>3}">
	<b>jstl方式：今天的天气有点热哦</b><br/>
</c:if>


多分支choose-when：
<c:set var="score" value="58"></c:set>
<c:choose>
	<c:when test="${score >= 90}">
		<i>奖励吃鸡装配一套</i>
	</c:when>
	<c:when test="${score<90 && score>=80}">
		<i>奖励空投箱</i>
	</c:when>
	<c:when test="${score<80 && score>=70}">
		<i>无奖励无惩罚</i>
	</c:when>
	<c:otherwise>
		<i>男女混合双打</i>
	</c:otherwise>
</c:choose>


<hr />
<h3>JSTL的循环标签</h3>
<!--传统方式的表格  -->
传统方式的表格：
<table border="1px">
	<tr>
		<td>课程名称</td>
		<td>教师</td>
		<td>价格</td>
		<td>重要性</td>
	</tr>
	<%
		for(int i=1; i<=4; i++){
			if(i%2 == 0){
	%>
	<tr>
		<td>java</td>
		<td>张老师</td>
		<td>99.00</td>
		<td>非常重要</td>
	</tr>
	<%}} %>
</table>


<br/>
<!-- 使用JSTL方式完成循环 -->
<!--创建表格数据  -->
<%
	ArrayList<String> list=new ArrayList<String>();
	list.add("a");
	list.add("b");
	list.add("c");
	list.add("d");
	request.setAttribute("list",list);
	
	HashMap<String,String> map=new HashMap<String,String>();
	map.put("a1", "哈哈哈");
	map.put("b1", "嘿嘿");
	request.setAttribute("map",map);
%>
jstl创建表格:
<table border="1px">
	<tr>
		<td>课程名称</td>
		<td>教师</td>
		<td>价格</td>
		<td>重要性</td>
	</tr>
	<c:forEach items="${list}" var="s">
		<tr>
			<td>${s}</td>
			<td>${s}</td>
			<td>${s}</td>
			<td>${s}</td>
		</tr>
	</c:forEach>
</table>

<hr>
常量循环:
<!--常量循环  -->
	(角标，循环次数，是否是第一次循环，是否是最后一次循环)<br />
<c:forEach begin="0" end="4" step="1" varStatus="vs">	
	${vs.index}--${vs.count}--${vs.first}--${vs.last}<br />
</c:forEach>

<!--动态循环  -->
<c:forEach items="${list}" var="str">
	遍历ArrayList: ${str}<br />
</c:forEach>

<!--遍历map集合  -->
<c:forEach items="${map}" var="m">
	遍历HashMap: ${m.key}--${m.value} <br />
</c:forEach>


<h3>日期格式化:</h3>
<c:set var="time" value="<%=new java.util.Date()%>" />
<p>当前日期: <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${time}" /></p>
<p>当前日期: <fmt:formatDate type="both"  dateStyle="long" timeStyle="long"  value="${time}" /></p>
解析日期：
<c:set var="now" value="20-10-2015" />
<fmt:parseDate value="${now}" var="parsedEmpDate" pattern="dd-MM-yyyy" />
<p>解析后的日期为: <c:out value="${parsedEmpDate}" /></p>

<h3>数字格式化:</h3>
<c:set var="balance" value="120000.230911" />
<p>人民币: <fmt:formatNumber value="${balance}" type="currency"/></p>
<p>美元 :<fmt:setLocale value="en_US"/>
	    <fmt:formatNumber value="${balance}" type="currency"/></p>
<p>小数：<fmt:formatNumber type="number" groupingUsed="false" value="${balance}" /></p></p>

<h3>JSTL SQL标签库:</h3>
<sql:setDataSource var="myDBname" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/test"  user="root"  password="root"/>
     
<h5>select<h5>     
<sql:query dataSource="${myDBname}" sql="select * from wordcount" var="result" /> 
<!--可选参数： maxRows="5" startRow="1"/ -->
结果集的实质是：${result}<br>
得到的行数为：${result.rowCount}<br>
是否收到了maxRows的限制：${result.limitedByMaxRows}<br>
<table border="1">
	<tr>
		<c:forEach var="colname" items="${result.columnNames}">
			<td>
				<c:out value="${colname}"/>
			</td>
		</c:forEach> 
	</tr> <!--生成表头  -->

	<c:forEach var="row" items="${result.rowsByIndex}"> <!--生成行  -->
		<tr>
			<c:forEach var="column" items="${row}">	 <!--生成该行的单元格  -->	
				<td><c:out value="${column}"/></td>		
			</c:forEach>	
		</tr>
	</c:forEach>
</table>

<h5>update<h5> 
<sql:update var="result1" dataSource="${myDBname}" >
	create table if not exists c_user (id int primary key, name varchar(80), sex varchar(80))
</sql:update>
<!--两种语法都可以  -->
<sql:update var="result1" dataSource="${myDBname}" 
	sql="create table if not exists c_user (id int primary key, name varchar(80), sex varchar(80))" />

<c:catch var="error">
	<fmt:requestEncoding value="GBK" />
	<sql:update var="result2" dataSource="${myDBname}" >
		insert c_user values(05,'Linda','girl')
	</sql:update>
	<sql:update var="result2" dataSource="${myDBname}" sql="insert c_user values(01,'Jack','男');" />	
	<sql:update var="result2" dataSource="${myDBname}" sql="update c_user set sex='女' where name='Linda'" />		
影响的记录数为（不准）：<c:out value="${result2}"></c:out>
</c:catch>
异常捕获：<c:out value="${error}"></c:out><br>


<%--
	JSTL学习:
		作用:
			提高在jsp中的逻辑代码的编写效率，使用标签。
		使用：
			JSTL的核心标签库（重点）
			JSTL的格式化标签库（讲解）
			JSTL的SQL标签库(了解)
			JSTL的函数标签库(了解)
			JSTL的XML标签库(了解)
		JSTL的核心 标签库：
			1、导入jar包到  WebContent/WEB_INF/lib/jstl-1.2.jar
			2、声明jstl标签库的引入(核心标签库)
				<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			3、内容:
				基本标签：
					<c:out value="数据" default="默认值"></c:out>
						数据可以为常量值也可以是EL表达式。
						作用：将数据输出给客户端。
					<c:set var="hello" value="hello pageContext" scope="page"></c:set>
						作用：存储数据到作用域对象中
						var：表示存储的键名
						value：表示存储的数据
						scope：表示要存储的作用域对象 page request session application
					<c:remove var="hello" scope="page"/>
						作用：删除作用域中的指定键的数据。
						var：表示要删除的键的名字
						scope：表示要删除的作用域（可选）
						注意：
							如果在不指定作用域的情况使用该标签删除数据，会将四个作用域对象中的符合要求的数据全部删除。
				逻辑标签：
					<c:if test="${表达式}">
							前端代码
					</c:if>
						作用：进行逻辑判断，相当于java代码的单分支判断。
						注意：
							逻辑判断标签需要依赖于EL的逻辑运算，也就是表达式中涉及到的数据必须从作用域中获取。
					<c:choose>
						<c:when test="">执行内容</c:when>
						<c:when test="">执行内容</c:when>
						...
						<c:otherwise>执行内容</c:otherwise>
					</c:choose>
						作用：用来进行多条件的逻辑判断，类似java中的多分支语句
						注意：
							条件成立只会执行一次，都不成立则执行otherwise	
				
				循环标签:
					<c:forEach begin="1" end="4" step="2">
							循环体
					</c:forEach>
					作用：
						循环内容进行处理
					使用:
						begin:声明循环开始位置
						end:声明循环结束位置
						step：设置步长
						varStatus:声明变量记录每次循环的数据(角标，次数，是否是第一次循环，是否是最后一次循环)
								注意:数据存储在作用域中，需要使用EL表达式获取。
								例如：${vs.index}--${vs.count}--${vs.first}--${vs.last}	
						items:声明要遍历的对象。结合EL表达式获取对象
						var:声明变量记录每次循环的结果。存储在作用域中，需要使用EL表达式获取。类似于i
--%>
</body>
</html>
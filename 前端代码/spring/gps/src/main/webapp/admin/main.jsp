<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>GPS数据实时流同步系统</title>
<%@ include file="/comm/com_public.jsp" %>

<%
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>

<style type="text/css">
.top{
	hight:100%;
	background:url(${pageContext.request.contextPath}/static/images/top.jpg);
	background-repeat: no-repeat; 
}
</style>

<script type="text/javascript">
<!--id：绑定节点的标识值。text：显示的节点文本。iconCls：显示的节点图标CSS类ID。checked：该节点是否被选中。state：节点状态，'open' 或 'closed'。attributes：绑定该节点的自定义属性。target：目标DOM对象。-->
$(function(){
	$('#tree').tree({   
		lines:true,
		animate:true,
	    url: '${pageContext.request.contextPath}/admin/auth/menu.do?parentId=-1',    
	    onLoadSuccess:function(){
			$("#tree").tree('expandAll');
		},
		onClick:function(node){
			//alert(node.text);  // 在用户点击的时候提示
			//alert(node.attributes.authPath);
			if(node.id == 13){//安全退出
				logout();
			}else if(node.id == 12){//修改密码
				openPasswordModifyDialog();
			}else if(node.attributes.authPath){//绑定菜单路径
				//打开选项卡
				openTab(node);
			}
		}
	});
});	
function openTab(node){
	if($("#tabs").tabs("exists",node.text)){
		$("#tabs").tabs("select",node.text);
	}else{
		var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/"+node.attributes.authPath+"'></iframe>";
		$("#tabs").tabs("add",{
			title:node.text,
			iconCls:node.iconCls,
			closable:true,
			content:content
		});
	}
}  
	//var url;
	function openPasswordModifyDialog(){
		$("#dlg").dialog("open").dialog("setTitle","修改密码");
		//url="${pageContext.request.contextPath}/admin/user/modifyPassword.do";
	}
	
	function modifyPassword(){
		var oldPassword = $("#oldPassword").val();
		var newPassword=$("#newPassword").val();
		var newPassword2=$("#newPassword2").val();
		if(newPassword!=newPassword2){
			$.messager.alert("系统提示","确认密码输入错误！");
			return false;
		}
		
		$.get("${pageContext.request.contextPath}/user/modifypwd.do?oldPassword="+oldPassword+"&newPassword="+newPassword,
		       function(data,status){
			
			var obj = JSON.parse(data);
			var succ = obj.old_md5;			
			if(succ=="true"){
				$.messager.alert("系统提示","密码修改成功,下一次登录生效！");
				resetValue();
				$("#dlg").dialog("close");
			}			
			else{
				$.messager.alert("系统提示","密码修改失败！");
				return;
			}				
		});
	}
	
	function closePasswordModifyDialog(){
		resetValue();
		$("#dlg").dialog("close");
	}
	
	function resetValue(){
		$("#oldPassword").val("");
		$("#newPassword").val("");
		$("#newPassword2").val("");
	}

	function refreshSystem(){
		$.post("${pageContext.request.contextPath}/admin/system/refreshSystem.do",{},function(result){
			if(result.success){
				$.messager.alert("系统提示","已成功刷新系统缓存！");
			}else{
				$.messager.alert("系统提示","刷新系统缓存失败！");
			}
		},"json");
	}
	
	function logout(){
		$.messager.confirm("系统提示","您确定要退出系统吗?",function(r){
			if(r){
				//window.location.href="${pageContext.request.contextPath}/admin/blogger/logout.do";
				window.location.href="${pageContext.request.contextPath}/user/logout.do";
			}
		});
	}
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height:82px;background-color: #E0ECFF" >
	<table width="100%">
		<tr>
			<td width="80%">
				<img class="top" src="${pageContext.request.contextPath}/static/images/top.jpg">
			</td>
			<td valign="middle" align="center" width="20%">
				<h3><span class="label label-default">欢迎：${currentUser.userName}</span></h3>
			</td>
		</tr>
	</table>
</div>
<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="首页" data-options="iconCls:'icon-home'">
			<div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
		</div>
	</div>
</div>
<div region="west" style="width: 220px;padding: 5px;" title="导航菜单" split="true">
	<ul id="tree" class="easyui-tree">
	
	</ul>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 250px;padding: 10px 20px" closed="true" buttons="#dlg-buttons">
	<form id="fm" method="post">
		<table cellspacing="8px">
			<tr>
				<td>用户名：</td>
				<td>
					<input type="text" id="userName" name="userName" value="${currentUser.userName }" readonly="readonly" style="width: 200px"/>
				</td>
			</tr>
			<tr>
	 			<td>原密码：</td>
	 			<td><input type="password" class="easyui-validatebox" name="oldPassword" id="oldPassword" style="width: 200px;" required="true" /></td>
	 		</tr>
			<tr>
				<td>新密码：</td>
				<td>
					<input type="password" id="newPassword" name="newPassword" class="easyui-validatebox" required="true" style="width: 200px"/>
				</td>
			</tr>
			<tr>
				<td>确认新密码：</td>
				<td>
					<input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox" required="true" style="width: 200px"/>
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function searchRole(){
		$('#dg').datagrid('load',{
			s_roleName:$("#s_roleName").val()
		});
	}
	
	function deleteRole(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert('系统提示','请选择要删除的数据！');
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].roleId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/role/delete.do",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert('系统提示',"您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
 					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].roleName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	var url
	<!--打开角色弹出框-->
	function openRoleAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加角色信息");
		url="${pageContext.request.contextPath}/admin/role/save.do";
	}
	<!--修改角色-->
	function openRoleModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert('系统提示','请选择一条要编辑的数据！');
			return;
		}
		var rowdata = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","修改角色信息");
		$("#fm").form("load",rowdata);
		url="${pageContext.request.contextPath}/admin/role/save.do&roleId="+row.roleId;
	}
	<!--保存角色-->
	function saveRole(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.errorMsg){
					$.messager.alert('系统提示',"<font color=red>"+result.errorMsg+"</font>");
					return;
				}else{
					$.messager.alert('系统提示','保存成功');
					closeRoleSaveDialog();
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	<!--关闭角色保存框-->
	function closeRoleSaveDialog(){
		$("#dlg").dialog("close");
		$("#fm").form('clear');//清除表单数据。
	}
	<!--选择角色授权-->
	function openAuthDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert('系统提示','请选择一条要授权的角色！');
			return;
		}
		var row = selectedRows[0];
		roleId = row.roleId;//获得角色id
		$("#dlg2").dialog("open").dialog("setTitle","角色授权");
		url="${pageContext.request.contextPath}/admin/auth/authMenu.do?parentId=-1&roleId="+roleId;
		$("#tree").tree({
			lines:true,
			url:url,
			checkbox:true,//定义是否在每一个借点之前都显示复选框。
			cascadeCheck:false,//定义是否层叠选中状态。默认是true
			onLoadSuccess:function(){
				$("#tree").tree('expandAll');
			},
			onCheck:function(node,checked){//在用户点击勾选复选框的时候触发。
				if(checked){
					checkNode($('#tree').tree('getParent',node.target));
				}
			}
		});
	}
	//是否选择节点
	function checkNode(node){
		if(!node){
			return;
		}else{
			checkNode($('#tree').tree('getParent',node.target));//获取父节点，'target'参数代表节点的DOM对象。
			$('#tree').tree('check',node.target);//选中指定节点。
		}
	}
	<!--角色正式授权-->
	function saveAuth(){
		var nodes = $('#tree').tree('getChecked');//获取所有选中的节点。获取选择节点并返回它，如果未选择则返回null。
		var authArrIds=[];
		for(var i=0;i<nodes.length;i++){
			authArrIds.push(nodes[i].id);
		}
		var authIds = authArrIds.join(",");
		$.post("${pageContext.request.contextPath}/admin/role/auth.do",{authIds:authIds,roleId:roleId},function(result){
			if(result.success){
				$.messager.alert('系统提示','授权成功！');
				closeAuthDialog();
			}else{
				$.messager.alert('系统提示',result.errorMsg);
			}
		},"json");
	}
	<!--关闭授权框-->
	function closeAuthDialog(){
		$("#dlg2").dialog("close");
	}
</script>
<title>角色管理</title>
</head>
<body style="margin: 1px;">
	<table id="dg" title="角色管理" class="easyui-datagrid" fitColumns="true" 
	    pagination="true" rownumbers="true" url="${pageContext.request.contextPath}/admin/role/list.do" fit="true" toolbar="#tb">
	    <thead>
	    	<tr>
	    		<th field="cb" checkbox="true" align="center"></th>
	    		<th field="roleId" width="50" align="center">编号</th>
	    		<th field="roleName" width="100" align="center">角色名称</th>
	    		<th field="roleDescription" width="200" align="center">备注</th>
	    	</tr>
	    </thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openRoleAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openRoleModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteRole()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			<a href="javascript:openAuthDialog()" class="easyui-linkbutton" iconCls="icon-roleManage" plain="true">角色授权</a>
		</div>
		<div>
			&nbsp;角色名称：&nbsp;<input type="text" name="s_roleName" id="s_roleName" size="20" onkeydown="if(event.keyCode==13) searchRole()"/>
			<a href="javascript:searchRole()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
		  closed="true" buttons="#dlg-buttons">
		  <form id="fm" method="post">
		  	<table cellspacing="5px;">
		  		<tr>
		  			<td>角色名称：</td>
		  			<td width="80%"><input type="text" id="roleName" name="roleName" class="easyui-validatebox" required="true"/></td>
		  		</tr>
		  		<tr>
		  			<td valign="top">备注：</td>
		  			<td colspan="2">
		  				<textarea rows="7" cols="50" name="roleDescription" id="roleDescription"></textarea>
		  			</td>
		  		</tr>
		  	</table>
		  </form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:saveRole()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
		<a href="javascript:closeRoleSaveDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
	</div>
	
	<!-- 授权弹出框 -->
	<div id="dlg2" class="easyui-dialog" style="width: 300px;height: 450px;padding: 10px 20px"
	  		closed="true" buttons="#dlg2-buttons">
		<ul id="tree" class="easyui-tree"></ul>
	</div>

	<div id="dlg2-buttons">
		<a href="javascript:saveAuth()" class="easyui-linkbutton" iconCls="icon-ok" >授权</a>
		<a href="javascript:closeAuthDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
	</div>
</body>
</html>
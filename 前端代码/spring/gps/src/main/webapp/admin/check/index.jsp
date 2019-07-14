<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GPS数据实时流同步系统</title>

<%@ include file="/comm/com_public.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cloudstorage/Cloud_storage.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cloudcompute/Cloud_computing.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/echarts2.2.7/echarts-all.js"></script>

<script type="text/javascript">
$(function() {
	listTables();
});//document ready 

//查看所有表
function listTables(){	
	$.getJSON(
		"${pageContext.request.contextPath}/admin/check/listTables.do", 		
		function(result){ 	
			$("#tbNum").html(result.length);
			var trStr = '<ul class="list-group">';//动态拼接table
			for(var i=0; i<result.length; i++){			
				trStr += '<li class="list-group-item">';				
				trStr += result[i];
				trStr += '</li>';
			}
			trStr += '</ul>';
			$("#tblist").html(trStr); //jQuery的追加方式				
		 }
	);	
}

function createtable(){
	var a1 = $("#tbname1").val();
	var a2 = $("#clfmaily").val();
	$.get(
		"${pageContext.request.contextPath}/admin/check/createTable.do", //待载入页面的URL地址
		{ tbname: a1, clfmaily:a2  },  //传参数
		function(result){  
			$("#info1").html(result);
			listTables();			
		 }
	);
}

function deletetable(){
	var a1 = $("#tbname2").val();
	$.get(
		"${pageContext.request.contextPath}/admin/check/deleteTable.do", //待载入页面的URL地址
		{ tbname: a1},  //传参数
		function(result){  
			$("#info2").html(result);
			listTables();		
		}
	);
	
}

</script>

</head>

<body>
<!--总div开始-->
<div class="container">	
<div class="page-header">
  <h1>Hbase客户端管理
   <small>   		
        <button type="button" class="btn btn-default btn-sm"  onclick="location.reload()">
          <span class="glyphicon glyphicon-refresh"></span> Refresh
        </button>
        
   </small>
  </h1>
</div>


	<div class="row">    
        <div class="col-md-4">           
        	<button class="btn btn-primary" type="button">
        		所有表<span class="badge" id="tbNum"></span>
        	</button>			
			<div id="tblist"></div>				
        </div>
       <div class="col-md-4" id="div1">         
          <button class="btn btn-primary" type="button" onclick="createtable();">创建表</button>		 
		  <div class="form-group">
		    <label for="exampleInputEmail1">表名</label>
		    <input class="form-control" id="tbname1" placeholder="table name">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">列簇</label>
		    <input class="form-control" id="clfmaily" placeholder="cf1,cf2">
		  </div>
		  <span id="info1" class="label label-success"></span>	
       </div>  
       
       <div class="col-md-4" id="div2">         
            <button class="btn btn-primary" type="button" onclick="deletetable();">删除表</button>
            <div class="form-group">
	    		<label for="exampleInputPassword1">表名</label>
	   			<input class="form-control" id="tbname2" placeholder="table name">
	 		</div>
           <span id="info2" class="label label-success"></span>	
       </div>   
        
	</div>	
	

				
</div><!--总div结束-->

</body>
</html>
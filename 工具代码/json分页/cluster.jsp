<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spark集群监控</title>

<%@ include file="/comm/com_public.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cloudcompute/Cloud_computing.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/echarts2.2.7/echarts-all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layer/layer.js"></script>

<style type="text/css">
.datagrid-header-row{
	height:40px;
}
.datagrid-row {
    height: 40px;
}


.wingb{
	background-color: #424242;  /*背景颜色*/
	font-size:14px;
	font: "微软雅黑";
	text-align:left; 
	width:700px;
	height:400px;
	overflow:auto;
}

.myul {
	margin: 0px;
	padding: 0px;
	list-style: none;
	color:#7CFC00; /*显示字体颜色*/
}

.incmd {
	background-color: #9FB6CD; /*输入行颜色*/
	border: 0;
	color: #FFFFFF; /*输入字体颜色*/
	outline: none;
	font-size:14px; 
	width: 99%;
}



</style>

<script type="text/javascript">
$(function() {		
	$.get("${pageContext.request.contextPath}/admin/spark/total.do",function(data,status){   
	     var obj = JSON.parse(data);	
	  	 $("#masterUrl").html(obj.masterUrl);
	  	 $("#status").html(obj.status);
	  	 $("#aliveWorkersNum").html(obj.aliveWorkersNum);
	  	 $("#cores").html("总共："+obj.coresNum+",  已使用："+obj.coresusedNum);
	  	 $("#memorys").html("总共："+obj.memory+",  已使用："+obj.memoryused);
	  	 $("#runningApp").html("正在运行数："+obj.runningAppsNum+",  已完成数： "+obj.completedAppsNum); 
	  	 
	  	 //加载饼图
	  	 load(obj.runningAppsNum, obj.completedAppsNum);
	  	 
	  	$.ajax({
	 		type : "get",
			url : "${pageContext.request.contextPath}/admin/spark/worker.do",
			async : true,
			dataType : "json",
			success :function (result){
				$("#tb_work").datagrid({
					data:result
				});
			}
	 		
	 	});
	 	
	 	$.ajax({
	 		type : "get",
			url : "${pageContext.request.contextPath}/admin/spark/runningapp.do",
			async : true,
			dataType : "json",
			success :function (result){
				$("#tb_run").datagrid({
					data:result
				});
			}
	 		
	 	});
	 	
	 	$.ajax({
	 		type : "get",
			url : "${pageContext.request.contextPath}/admin/spark/completedapp.do",
			async : true,
			dataType : "json",
			success :function (result){
				$("#tb_cmp").datagrid({
					data:result
				});
			}
	 		
	 	});
	  	 
 	});//total初始化结束 
 	
	 
});//document ready 



function load(v1,v2){	
//基于准备好的dom，初始化echarts图表
var myChart = echarts.init(document.getElementById('pie')); 
var option = {
	    title : {
	        text: '应用程序概况',
	        x:'left'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient : 'vertical',
	        x : 'right',
	        y : 'bottom', 
	        data:['正在运行','已经完成']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel'],
	                option: {
	                    funnel: {
	                        x: '25%',
	                        width: '50%',
	                        funnelAlign: 'left',
	                        max: 1548
	                    }
	                }
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'程序运行状态',
	            type:'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data: [	                
					{value:v1, name:'正在运行'},
					{value:v2, name:'已经完成'}
	            ]
	        }//
	    ]
	};
	
// 为echarts对象加载数据 
myChart.setOption(option);

}//function



/***************************************/
 
//状态展示样式
function statusFormatter(status){
	if(status=='ALIVE'){
		var value ='<span style="background-color: #2bb673;color: #FFFFFF; padding: 5px 10px;">'+status+'</span>';
		return value;
		
	}else if(status=='RUNNING'){
		var value ='<span style="background-color: #B452CD;color: #FFFFFF; padding: 5px 10px;">'+status+'</span>';
		return value;
		
	}else if(status=='WAITING'){
		var value ='<span style="background-color: #EE3B3B;color: #FFFFFF; padding: 5px 10px;">'+status+'</span>';
		return value;
		
	}
	else if(status=='KILLED'){
		var value ='<span style="background-color: #FF6347;color: #FFFFFF; padding: 5px 10px;">'+status+'</span>';
		return value;
		
	}
	else if(status=='FINISHED'){
		var value ='<span style="background-color: #616161;color: #FFFFFF; padding: 5px 10px;">'+status+'</span>';
		return value;
		
	}else{
		var value ='<span style="background-color: #EE3B3B;color: #FFFFFF; padding: 5px 10px;">'+status+'</span>';
		return value;
	}
}

//详情、停止按钮
function serverFormatter(v,row,index) {
	var str = '';
	str += '<a href="javascript:void(0)" onClick="go_details(\''+ row['appID'] + '\',\''+row['port']+'\')" style="color:blue">详情</a>';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" style="color:red" onClick="go_stopIt(\''+ row['serverStatus'] + '\')">停止</a>';

	return str;
}

//跳转到详情页
var data = {
	text : "Spark作业管理",
	attributes : {
		authPath:"spark/gopage.do"
	}
};

function go_details(appid,port) {//1、行号，2、v1，3、v2
	//console.info(appid+"-------------"+port);
	openpage(data,appid,port);
}

function openpage(node,appid,port){
	var jq = top.jQuery;
	if(jq("#tabs").tabs("exists",node.text)){
		jq("#tabs").tabs("select",node.text);
	}else{
		var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"+node.attributes.authPath+"?appid="+appid+"&port="+port+"'></iframe>";
		jq("#tabs").tabs("add",{
			title:node.text,
			iconCls:node.iconCls,
			closable:true,
			content:content
		});
	}
}


//杀掉进程
function go_stopIt(v){
	if(v == 2){
		xajax.alert('亲！状态已停止，无需再停止');
		return false;
	}
	var selectedRows = $("#dg_server").datagrid('getSelections');
	if(selectedRows.length==0){
		xajax.alert('请先选择要停止的一行数据！');
		return;
	}
	if (selectedRows.length != 1) {
		 xajax.alert('一次只能选择一行数据进行操作！');
			return;
	}
	 var newData = $('#dg_server').datagrid('getData');
//		 var rows = $('#dg_server').datagrid('getRows');
	 var rowIndex = $('#dg_server').datagrid('getRowIndex',$('#dg_server').datagrid('getSelected'));
	 console.info("选中的行号："+rowIndex);
	 newData.rows[rowIndex]["serverStatus"] = 2;
	 $('#dg_server').datagrid('refreshRow', rowIndex);
	 //如果是2就隐藏按钮或者是不能点击按钮
	 var linkNum = newData.rows[rowIndex]["serverStatus"];
	 if(linkNum == 2){
		 xajax.tip('亲！状态已经停止了');
	 }
	//取到当前选中行并且是后台需要的命令，然后向后台发送命令进行程序控制停止此条进程  后续完成
}
/***************************************/

//创建按钮 弹出框
function establishDialog(){
	xajax.iframeLAYER('${pageContext.request.contextPath}/admin/spark/addWindow.do',
			"linux控制台", 
			'680px', 
			'420px',
			function(){	
			//alert("连接已建立！");
	});
	//接下来的操作跳转到box.jsp	，问题是滚动条不能自动滑到最底部	
}

function establishDialog2(){
	$.ajax({
		url : "${pageContext.request.contextPath}/admin/spark/addWindow.do",
		success :function (){
			alert("连接已建立！"); //第一次加载会调用，滚动条可以自动滑到最底部
		}
	});
	 
	$('#myModal').modal({
		keyboard: true
	});
	$('#myModal').on('show.bs.modal', function () {
		/* $.ajax({
	 		type : "get",
			url : "${pageContext.request.contextPath}/admin/spark/addWindow.do",
			async : true,
			success :function (){
				alert("连接已建立！");
			}
	 		//第一次加载不会调用，启用该方法
	 	}); */
	})	
	$("#myModal").on("hidden.bs.modal", function() {
   		$(this).removeData("bs.modal");
	});
	$("#in")[0].focus();		
}
</script>

</head>

<body>
<!--总div开始-->
<div class="container">
	<!--云计算标题开始-->
	<div class="yunjistitle"><strong>云计算列表</strong></div><!--云计算标题结束-->
					
	<!--云计算内容1开始-->
	<div class="conttitle container">		
		
		<!--云服务器概况图开始-->
		<div class="col-md-4 " >									
			<div class="topdiv_left" >						  	
			 <div id="pie" style="height:250px"></div>
			</div>
		</div><!--云服务器概况图结束-->
		<!--云服务器概况图结束-->
		 
		 <!--云服务器概况内容开始-->
		 <div class="col-md-8" >				   	  
			  <div class="topdiv_right" >
			  	<div class="divright_title">
		    		<p>主控节点地址：</p>
		    		<p>集群状态：</p>
		    		<p>工作节点数量：</p>
		    		<p>Cpu核使用情况：</p>
		    		<p>内存使用情况：</p>
		    		<p>运行程序情况：</p>
		    	</div>
		    	<div class="divright_content">
		    		<p id="masterUrl"></p>
		    		<p id="status"></p>
		    		<p id="aliveWorkersNum"></p>
		    		<p id="cores"></p>
		    		<p id="memorys"></p>
		    		<p id="runningApp"></p>
		    	</div>
			  </div>
		</div><!--云服务器概况内容结束-->		
	</div><!--云计算内容1结束-->
	


	 <!--云计算内容2开始-->
	<div class="" style="height:350px;background: #F8F8F8;">
		<!--工作节点标题开始-->
		<div class="infoxieyi">
			<div class="infoxieyicenter">&nbsp;&nbsp;工作节点<i class="pull-left infoxieyicti"></i></div>
		</div><!--工作节点标题结束-->									
				
							
		<!--工作节点table表格-->
		<div class="table-responsive" style="height:300px;">
			<!--表格开始-->
			<table id="tb_work" class="easyui-datagrid" rownumbers="true" fit="true" fitColumns="false" 
				   pagination="true"  pageSize="10" >
				<thead>
					<tr>
						<th field="workerId" width="300" align="center">工作节点ID</th>
						<th field="address" width="200" align="center">地址</th>
						<th field="state"  width="100" align="center" formatter="statusFormatter">状态</th>
						<th field="coresNum" width="120" align="center" >节点Cpu核数</th>
						<th field="coresusedNum" width="100" align="center">已使用Cpu核数</th>
						<th field="memory" width="100" align="center">节点内存总量</th>
						<th field="memoryused" width="100" align="center">已使用内存</th>						
					</tr>
				</thead>
			</table><!--表格结束-->		
		</div><!--工作节点table表格-->				
	</div><!--云计算内容2结束-->			

				
	 <!--云计算内容3开始-->
	<div class="" style="height:300px;background: #F8F8F8;">
		<!--正在运行的进程标题开始-->
		<div class="infoxieyi">
			<div class="infoxieyicenter">&nbsp;&nbsp;正在运行的进程<i class="pull-left infoxieyicti"></i>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- 按钮触发模态框 -->
				<span class="annnys">
					<a id="boxWindow" class="pve-tabletack active-tabletack" href="javascript:establishDialog()" style="cursor:pointer;">boxWindow</a>
				</span>
				<span class="annnys">
					<!-- <button type="button" id="modalWindow" class="btn btn-primary" href="javascript:void(0);" onclick="establishDialog2();">选择</button> -->				
				    <a id="modalWindow" class="pve-tabletack active-tabletack" href="javascript:establishDialog2()" style="cursor:pointer;">modalWindow</a>
				</span>
				<!-- 模态框（Modal） -->
				<div class="modal fade" id="myModal"
					 tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog"  style="width:702px;height:410px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
								<p class="modal-title" id="myModalLabel">Linux 控制台</p>
							</div>
							<div class="modal-body" style="padding:0px;">
								<!-- <iframe id="box" src="box.jsp" style="width:600px;height:400px;"></iframe> -->
                    			
                    			<div class="wingb" id="msg">
									<ul class="myul">
									<li>----------------------命令Demo----------------------------</li>
									<li>Ctrl+q或 ESC 关闭ssh连接</li>	
									<li>关闭进程方式1：</li>	
									<li>ps -aux | grep test.jar		//根据提交的jar包名字来查找进程，再kill -9 [pid]杀掉进程</li>
									<li>关闭进程方式2：</li>	
									<li>ps -aux | grep test.jar | kill -9 `awk '{print $2}'`	//查找进程id并立即杀掉</li>
									<li>关闭进程方式3：</li>
									<li>kill -9 $(cat my.pid)	//前提：提交任务时获取进程号: spark-submit --class demo.WordCount test.jar & echo $!>my.pid</li>
									<li>----------------------开始操作----------------------------</li>
									</ul>								
									<!-- <div class="incmd"  contentEditable="true"  id='in'>$</div> -->
									<input class="incmd" type="text" value="$" id='in'>
								</div>
								
							</div><!-- modal-body -->
						</div><!-- /.modal-content -->
					</div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
				<script>
					$("#in").keyup(function(event) {					
						if(event.keyCode == 13) {	//回车			
							$.ajax({
								async: true,
								type : "GET",
								url : "${pageContext.request.contextPath}/admin/spark/linux.do",
								data : "code=" + $("#in").val().substring(1),
								success : function(data) {
									$("ul").append("<li>" + $("#in").val() + "</li>");  //将输入的输出到界面
									$("ul").append("<li>" + data + "</li>"); //获取返回值并输出
									$("#in").val("$"); //清空输入框
									$("#msg").scrollTop($("#msg").scrollTop() + 9999);//滚动条拉到最下面，显示出输入框
								}
							});
						}
						else  if(event.ctrlKey && event.which == 81){ //ctrl+Q 中断
				        	//alert("ctrl+Q 中断");
				        	close();
				        }
						else  if(event.which == 27){ //ESC 退出			
				        	//alert("ESC终端");
				        	close();
				        	CloseWebPage();
				        }						
					});	
					$("#in")[0].focus();
					
					function close(){
						$.ajax({							
							url : "${pageContext.request.contextPath}/admin/spark/close.do",
							success : function(data) {
								alert(data);
							}	
						});
					};

					function CloseWebPage() {
						if (navigator.userAgent.indexOf("MSIE") > 0) {
							if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
								window.opener = null;
								window.close();
							} else {
								window.open('', '_top');
								window.top.close();
							}
						} else if (navigator.userAgent.indexOf("Firefox") > 0) {
							window.location.href = 'about:blank ';
						} else {
							window.opener = null;
							window.open('', '_self', '');
							window.close();
						}
					};
				</script>
								
			</div>
		</div><!--正在运行的进程标题结束-->
					
		<!--正在运行的进程table表格开始-->
		<div class="table-responsive" style="width:100%;height:250px;">
			<table id="tb_run" class="easyui-datagrid" fit="true" fitColumns="false" 
				   pagination="true" pageSize="10" rownumbers="true" >
				<thead>
					<tr>
						<th field="appID" width="170" align="center" >应用进程ID</th>
						<th field="appName" width="90" align="center">应用名称</th>
						<th field="port" width="70" align="center">作业端口</th>
						<th field="cores" width="70" align="center">使用cpu核</th>
						<th field="memoryPerNode" width="70" align="center">节点内存</th>
						<th field="submittedTime" width="125" align="center">应用提交时间</th>
						<th field="user" width="60" align="center">提交用户</th>
						<th field="state" width="120" align="center" formatter="statusFormatter">应用状态(运行/等待)</th>
						<th field="duration" width="160" align="center">应用执行时间</th>
						<th title="操作" field="id" width="110" align="center" formatter="serverFormatter" width="200">操作</th>						
					</tr>
				</thead>
			</table>			
		</div><!--正在运行的进程table表格结束-->
	</div> <!--云计算内容3结束-->	
	
	
	
	<!--云计算内容4开始-->
	<div class="" style="height:400px;background: #F8F8F8;">
		<!--已经结束的进程标题开始-->
		<div class="infoxieyi">
			<div class="infoxieyicenter">&nbsp;&nbsp;已经结束的进程<i class="pull-left infoxieyicti"></i></div>
		</div><!--已经结束的进程标题结束-->
					
		<!--已经结束的进程table表格开始-->
		<div class="table-responsive" style="width:100%;height:370px">
			<table id="tb_cmp" class="easyui-datagrid" fit="true" fitColumns="false" 
				   pagination="true" pageSize="10" rownumbers="true" >
				<thead>
					<tr>
						<th field="appID" width="180" align="center">应用进程ID</th>
						<th field="appName" width="100" align="center">应用名称</th>
						<th field="cores" width="100" align="center">使用cpu核数</th>
						<th field="memoryPerNode" width="100" align="center">节点内存</th>
						<th field="submittedTime" width="150" align="center">应用提交时间</th>
						<th field="user" width="100" align="center">提交用户</th>
						<th field="state" width="135" align="center" formatter="statusFormatter">应用状态(完成/杀死)</th>
						<th field="duration" width="160" align="center">应用执行时间</th>	
					</tr>
				</thead>			
			</table>			
		</div><!--已经结束的进程table表格结束-->
	</div> <!--云计算内容4结束-->			

</div><!--总div结束-->

</body>
</html>
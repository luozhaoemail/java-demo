<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GPS数据实时流同步系统</title>

<%@ include file="/comm/com_public.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cloudstorage/Cloud_storage.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cloudcompute/Cloud_computing.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dataopen/cloudcompute/datagrid-detailview.js"></script>

<script type="text/javascript">
//http://localhost:8080/hottopic/admin/check/page.jsp

function pass(){
	var time1 = $("#time1").val();
	var time2 = $("#time2").val();
	if(time1!="" && time2!=""){	
		loadJob(time1,time2);
	}	
};

function loadJob(time1,time2){	
	var param = "?time1="+time1+"&time2="+time2;
	
	$('#tb_detail').datagrid({
		async : true,
		autoRowHeight:false,
		nowrap:true,
		striped:true,
		singleSelect:true,
		fitColumns:false,
		pagination:true,
		pageSize:10,		
		method:"GET",
		url:"${pageContext.request.contextPath}/admin/check/page.do"+param,
		columns:[[
					{field:'id', align:'center', width:'5%', title:'ID'},
					{field:'carflag', align:'center', width:'15%', title:'车辆标识'},
					{field:'touchevent', align:'center', width:'10%', title:'触发事件',
						formatter:function statusFormatter(status){						
							if(status=='0'){
								var value ='<span style="background-color: #2bb673;color: #FFFFFF; padding: 5px 10px;">0=变空车</span>';
								return value;
								
							}
							else if(status=='1'){
								var value ='<span style="background-color: #436EEE;color: #FFFFFF; padding: 5px 10px;">1=变载客</span>';
								return value;
							}
							else if(status=='2'){
								var value ='<span style="background-color: #436EEE;color: #FFFFFF; padding: 5px 10px;">2=设防</span>';
								return value;
							}
							else if(status=='3'){
								var value ='<span style="background-color: #436EEE;color: #FFFFFF; padding: 5px 10px;">3=撤防</span>';
								return value;
							}
							else if(status=='4'){
								var value ='<span style="background-color: #EE3B3B;color: #FFFFFF; padding: 5px 10px;">4=其它</span>';
								return value;
							}
						}	
						
					},		    		
					{field:'opstatus', align:'center', width:'10%', title:'运营状态',
						formatter:function statusFormatter(status){						
							var value ='<span style="background-color: #436EEE;color: #FFFFFF; padding: 5px 10px;">'+status+'</span>';
							return value;
						}
					},
					{field:'gpstime', align:'center', width:'15%', title:'GPS时间',
						formatter:function statusFormatter(status){
							var value ='<span style="background-color: #EE3B3B;color: #FFFFFF; padding: 5px 10px;">'+status+'</span>';
							return value;
						}
					},
					{field:'gpslongitude', align:'center', width:'15%', title:'GPS经度'},
					{field:'gpslatitude', align:'center', width:'15%', title:'GPS纬度'},
					{field:'gpsspeed', align:'center', width:'15%', title:'GPS速度'},
					{field:'gpsorientation', align:'center', width:'15%', title:'GPS方位'},
					{field:'gpsstatus', align:'center', width:'15%', title:'GPS状态'}
		  	]],
		view: detailview,
		detailFormatter: function(rowIndex, rowData){			 
			 return '<div style="width:100%; height:auto; color:#436EEE">' + rowData.gpstime + '</div>';				
		}		
    });
};

</script>

</head>

<body>
<!--总div开始-->
<div class="container">	
	<div class="yunjistitle"><strong>分页检索</strong></div>
	<div class="row">  	   		
		<div class="col-xs-4" >	
			<div class="input-group">
				<span class="input-group-addon col-sm-offset-2">开始时间</span> 					
				<input class='form-control' type='datetime-local' id='time1' /><!-- placeholder="2012-11-30 00:00:00" -->
			</div>	
		</div>
		<div class="col-xs-4 " >
			<div class="input-group col-sm-offset-1">
				<span class="input-group-addon ">结束时间</span>
				<input class='form-control' type='datetime-local' id='time2' />
			</div> 
		</div>		
		<div class="col-xs-4 " >	
			<button type="button" class="btn btn-primary" href="javascript:void(0);" onclick="pass();">查询</button>	
		</div>
	</div>
	
	
	<!--故障结果内容开始-->
		<div class="pve-listbox">		
			<div class="pve-listtitle">
				<span class="pev-tlep">检测结果</span>
			</div>
			
			<!--表格开始-->
			<table id="tb_detail" class="easyui-datagrid"></table>
			<!--表格结束-->											
			
		</div><!--故障结果内容开始-->
					
</div><!--总div结束-->

</body>
</html>
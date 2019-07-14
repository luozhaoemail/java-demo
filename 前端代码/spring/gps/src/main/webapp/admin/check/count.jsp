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
function pass(){	
	$.getJSON(
		"${pageContext.request.contextPath}/admin/check/count1.do",
		function(result){  
			var xlist = new Array();
			var ylist = new Array();
			
			for(var i=0; i<result.length; i++){			
				xlist.push(result[i].xdata);
				ylist.push(result[i].ydata);
					
			}		
			loadBar("bar1","GPS时间统计", xlist, ylist);
			
		 }
	);
	
	$.getJSON(
		"${pageContext.request.contextPath}/admin/check/count2.do",
		function(result){  
			var xlist = new Array();
			var ylist = new Array();
			
			for(var i=0; i<result.length; i++){			
				xlist.push(result[i].xdata);
				ylist.push(result[i].ydata);
					
			}		
			loadBar("bar2","GPS速度统计", xlist, ylist);
			
		 }
	);
}

function loadBar(id,title, xlist, ylist){	
	// 基于准备好的dom，初始化echarts图表
	var myChart = echarts.init(document.getElementById(id)); 
	
	var option = {
		tooltip: {
			show: true
		},
		toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
		legend: {
			data: [title]
		},
		xAxis : [
			{
				type : 'category',
				data : xlist
			}
		],
		yAxis : [
			{
				type : 'value'
			}
		],
		series : [
			{
				"name":"数量",
				"type":"bar",
				"data": ylist
			}
		]
	};
	// 为echarts对象加载数据 
	myChart.setOption(option);	
}///loadBar

</script>

</head>

<body>
<!--总div开始-->
<div class="container">
	<!--查询参数开始-->
	<div class="yunjistitle"><strong>gps分类统计</strong></div>
	<!--查询参数结束-->
	<div class="row">  	
		<div class="col-xs-12 " >	
			<button type="button" class="btn btn-primary" href="javascript:void(0);" onclick="pass();">点击开始统计</button>	
		</div>
	</div>
	
	
	<div class="row">
       	 <div class="col-md-12">
             <div class="card">
               	<div class="card-header bg-light">GPS时间统计</div>					
				<div id="bar1" style="width:1000px; height:400px"></div>	
             </div>
         </div>
	</div><!--row-->
	<div class="row">
         <div class="col-md-12">
             <div class="card">
                 <div class="card-header bg-light">GPS速度统计</div>
                 <div id="bar2" style="width:1000px; height:400px"></div>
             </div>
          </div>       
	</div><!--row-->
						
</div><!--总div结束-->

</body>
</html>
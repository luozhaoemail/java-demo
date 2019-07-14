/**************************************************************

	大数据开放平台第一期v1.0.0

***************************************************************/
var xajax = {};

/**
 * Ajax 封装，有可能用到导出，同步异步请求的公共js
 */
function buildPostPath( location, command ) {
	var prefix = location.toString().indexOf( "?" ) == -1 ? "?" : "&" ;
	return location + prefix + command ;
};
/**
 * HTTP Ajax 处理
 * @param url 处理url
 * @param param 参数
 * @param callback 处理成功回调函数 参数function(result, args) 其中result是返回的结果, args与方法参数args相同
 * @param isValid 请求后台时的验证 返回 true, false主要用于表单提交前验证, true继续发起请求, false 直接返回
 * @param loadingMessage 请求时的进度条显示的信息, 
 * @param successMessage 处理成功时提示信息
 * @param isAsync 是否异步请求 默认 (true:异步）
 * @param _dataType 服务器返回的数据格式 默认json
 */
function httpReqSubmit(url, param, callback, isValid, loadingMessage, successMessage, isAsync, _dataType) { 
	if(!isValid) return;
	if(loadingMessage == null) loadingMessage = '亲,小二正在努力处理中, 请稍等...';
	if(successMessage == null) successMessage = '数据处理成功';
	top.xajax.tip(loadingMessage, 'loading');
	if(_dataType == null) _dataType = 'json';
	if(isAsync == null) isAsync = true;
	//$.param(param)
	$.ajax({ 
		url:url,
		data:param,
		type:'POST',
		async:isAsync,
		dataType: _dataType,
		success: function(result) { 
			top.xajax.tip('<strong>'+successMessage+'</strong>', 'success');
			callback(result);
		},      
		error:function(e, textStatus, errorThrown){
			if(e.status == 403) {
				top.xajax.tip('<strong>403错误:</strong>你无权进行此操作, 请联系管理员', 'error');
				top.xajax.error('<strong style="color:red">403错误:</strong><p> 你无权进行此操作, 请联系管理员...');
			} else if(e.status == 404) {
				top.xajax.tip('<strong>404错误:</strong>这个你懂的......', 'error');
				top.xajax.error('<strong style="color:red">404错误:</strong><p> 这个你懂的......');
			} else {
				top.xajax.tip('<strong>处理错误</strong>', 'error');
				top.xajax.error('<strong style="color:red">处理错误: </strong><p>' + '<div style="height:200px">'+e.responseText+'</div>');
			} 
		}
	});
}

/**
 * Ajax 异步请求
 * @param url 处理url
 * @param param 参数
 * @param callback 处理成功回调函数 参数function(result, args) 其中result是返回的结果, args与方法参数args相同
 * @param isValid 请求后台时的验证 返回 true, false主要用于表单提交前验证, true继续发起请求, false 直接返回
 * @param loadingMessage 请求时的进度条显示的信息
 * @param _dataType 服务器返回的数据格式 默认json
 */
xajax.submit = function(url, param, callback, isValid, loadingMessage, successMessage, dataType) {
	httpReqSubmit(url, param, callback, isValid, loadingMessage, successMessage, true, dataType);
}
/**
 * 异步无提示
 */
xajax.submitNoMsg = function(url, param, callback, dataType) {
	if(dataType == null) dataType = 'json';
	$.ajax({ 
		url:url,
		data:param,
		type:'POST',
		async:true,
		dataType: dataType,
		success: function(result) { 
			callback(result);
		},      
		error:function(e, textStatus, errorThrown){
			if(e.status == 403) {
				top.xajax.tip('<strong>403错误:</strong>你无权进行此操作, 请联系管理员', 'error');
				top.xajax.error('<strong style="color:red">403错误:</strong><p> 你无权进行此操作, 请联系管理员...');
			} else if(e.status == 404) {
				top.xajax.tip('<strong>404错误:</strong>这个你懂的......', 'error');
				top.xajax.error('<strong style="color:red">404错误:</strong><p> 这个你懂的......');
			} else {
				top.xajax.tip('<strong>处理错误</strong>', 'error');
				top.xajax.error('<strong style="color:red">处理错误: </strong><p>' + '<div style="height:200px">'+e.responseText+'</div>');
			} 
		}
	});
}
/**
 * Ajax 同步请求
 * @param url 处理url
 * @param param 参数
 * @param callback 处理成功回调函数 参数function(result, args) 其中result是返回的结果, args与方法参数args相同
 * @param isValid 请求后台时的验证 返回 true, false主要用于表单提交前验证, true继续发起请求, false 直接返回
 * @param loadingMessage 请求时的进度条显示的信息
 * @param _dataType 服务器返回的数据格式 默认json
 */
window.submit = function(url, param, callback, isValid, loadingMessage,successMessage, dataType) {
	httpReqSubmit(url, param, callback, isValid, loadingMessage, successMessage, false, dataType);
}
/**
 * 同步无提示
 */
xajax.submitSyncNoMsg = function(url, param, callback, dataType) {
	if(dataType == null) dataType = 'json';
	$.ajax({ 
		url:url,
		data:param,
		type:'POST',
		async:false,
		dataType: dataType,
		success: function(result) { 
			callback(result);
		},      
		error:function(e, textStatus, errorThrown){
			if(e.status == 403) {
				top.xajax.tip('<strong>403错误:</strong>你无权进行此操作, 请联系管理员', 'error');
				top.xajax.error('<strong style="color:red">403错误:</strong><p> 你无权进行此操作, 请联系管理员...');
			} else if(e.status == 404) {
				top.xajax.tip('<strong>404错误:</strong>这个你懂的......', 'error');
				top.xajax.error('<strong style="color:red">404错误:</strong><p> 这个你懂的......');
			} else {
				top.xajax.tip('<strong>处理错误</strong>', 'error');
				top.xajax.error('<strong style="color:red">处理错误: </strong><p>' + '<div style="height:200px">'+e.responseText+'</div>');
			} 
		}
	});
}
//弹出框
function openWindow(propertyName, url, t) {
	var submitData = {};
	var fields = $('#searchForm').serializeArray(); //自动序列化表单元素为JSON对象  
	$.each( fields, function(i, field){ 
	    submitData[field.name] = field.value; //设置查询参数  
	}); 
	submitData['inputText'] = propertyName;
	var title = '';
	if(submitData['dateType'] == 1) { 
		title = '当前时间模式：[<span style="color:red;font-weight: bold;">5分钟</span>]，'+t+'：'+propertyName;
	} else if(submitData['dateType'] == 2) { 
		title = '当前时间模式：[<span style="color:red;font-weight: bold;">小时</span>]，'+t+'：'+propertyName;
	} else if(submitData['dateType'] == 3) { 
		title = '当前时间模式：[<span style="color:red;font-weight: bold;">天</span>]，'+t+'：'+propertyName;
	} else if(submitData['dateType'] == 4) { 
		title = '当前时间模式：[<span style="color:red;font-weight: bold;">月</span>]，'+t+'：'+propertyName;
	}  
	url = url+$.param(submitData);
	top.xajax.iframe(url, title, 800, 400);
}
//查询按钮
function onSearchHandler() { 
	if(!$("#searchForm").form('validate')) { 
		return ;
	}
	var submitData = {};
	var fields = $('#searchForm').serializeArray(); //自动序列化表单元素为JSON对象  
	$.each( fields, function(i, field){ 
	    submitData[field.name] = field.value; //设置查询参数  
	}); 
	$('#mainList').datagrid('load', submitData);
}
//导出
function onExportClickHandler() { 
	top.xajax.tip('努力导出数据中, 请稍等...', 'loading');
	var submitData = new Object();
	var fields = $('#searchForm').serializeArray(); //自动序列化表单元素为JSON对象  
	$.each( fields, function(i, field){ 
	    submitData[field.name] = field.value; //设置查询参数  
	});
	
	submit( buildPostPath( window.location.pathname, "command=exp" ), submitData, 
		function(result){
			var p = '导出成功：<a href="'+result+'">点击下载</a>';
			top.xajax.success(p);
		}, true, '努力导出数据中, 请稍等...', '导出成功', 'text') ;
}
/*******************************************************************************
 * 弹出框
 ******************************************************************************/
xajax.alert = function(content) {
	layer.alert(content, {
		title : false
	});
};
xajax.info = function(content) {
	layer.alert(content, {
		title : false
	});
};
xajax.error = function(content) {
	layer.alert(content, {
		title : false,
		icon : 2
	});
};
xajax.warning = function(content) {
	layer.alert(content, {
		title : false,
		icon : 0
	});
};
xajax.success = function(content) {
	layer.alert(content, {
		title : false,
		icon : 1
	});
};
xajax.confirm = function(content, okEvent, cancelEvent) {
	layer.confirm(content, {
		icon : 3,
		title : false
	}, function(index) {
		if (okEvent != null)
			okEvent();
		layer.close(index);
	}, function(index) {
		if (cancelEvent != null)
			cancelEvent();
		layer.close(index);
	});
};

xajax.tip = function(content, info) {
	layer.msg(content, {
		time : 2000
	});
};

xajax.loading = function(content, time) {
	if(time){
		return layer.msg(content, {
			icon:16,
			time : time
		});
	}else{
		return layer.msg(content, {
			icon:16,
			time:0
		});
	}
};

xajax.close = function(index){
	layer.close(index);
}
xajax.iframe = function(url, title, w, h, okEvent, ca) {
	var _id = new Date().getTime();
	$.jBox.open('iframe:' + url, title, w, h, {
		id : 'jbox-iframe-' + _id,
		top : '12%',
		buttons : {
			'确定' : true,
			'取消' : false
		},
		submit : function(v, h, f) {
			var iframeName = h.find('iframe').attr("name");
			var win = window.frames[iframeName];
			if (v == true) {
				if (okEvent)
					okEvent(win);
			} else {
				if (ca)
					ca(win);
			}
			return true;
		}
	});
};
/**
 * layer版本
 * 示例：
 * top.xajax.iframeLAYER('business/manager.do?command=go', "编辑区域", '1000px', '500px',function(iframewin){
		top.xajax.tip('正在保存中，请稍后','loading');
		return iframewin.save(window);
	},function(iframewin){return true;});
 */
xajax.iframeLAYER = function(url, title, w, h, okEvent, ca) {
	layer.open({
		type: 2,
		area: [w, h],
		title : title,
		content :url,
		btn : [ '确定', '关闭' ],
		btn1 : function(index, layero) {
			var iframeName = layero.find('iframe').attr("name");
			var win = window.frames[iframeName];
			if (okEvent){
				if(okEvent(win)){
					layer.close(index);
				}}
			else {
				layer.close(index);
			}
		},
		btn2 : function(index, layero) {
			var iframeName = layero.find('iframe').attr("name");
			var win = window.frames[iframeName];
			if (ca)
				return ca(win);
			else {
				return true;
			}
		}
	});

};
/*******************常用辅助方法*********************/
/**
	保留两位小数点, 不足补0
*/
function changeTwoDecimal(floatvar) {
	var f_x = parseFloat(floatvar);
	if (isNaN(f_x)) {
		//alert('function:changeTwoDecimal->parameter error');
		return '0.00';
	}
	var f_x = Math.round(f_x*100)/100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2){
		s_x += '0';
	}
	return s_x;
}
//子页面选项卡
function openSubTab(node){
	var jq = top.jQuery;
	if(jq("#tabs").tabs("exists",node.text)){
		jq("#tabs").tabs("select",node.text);
	}else{//${pageContext.request.contextPath}/admin/
		var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"+node.attributes.authPath+"'></iframe>";
		jq("#tabs").tabs("add",{
			title:node.text,
			iconCls:node.iconCls,
			closable:true,
			content:content
		});
	}
}
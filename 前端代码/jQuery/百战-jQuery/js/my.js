/*function test(){
	alert("我是test：外部引入声明");
}
*/


//声明对象
/*var bjsxt={};
bjsxt.test=function(){
	alert("我是test：外部引入声明");
}*/

//使用工厂模式
(function(obj){
	//var bjsxt={};
	obj.test=function(){
		alert("工厂");
	}
	alert("匿名自调用");
})(window)
//将数据一次性挂载到window对象下，window是一个全局变量，去接收局部变量(闭包
//obj是形参，window是实参

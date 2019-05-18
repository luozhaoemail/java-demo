var img=document.createElement("img");
img.src="img/wdfj.gif";
img.style.left="100px";
img.style.top="100px"
document.getElementById("showdiv").appendChild(img);

//创建函数
	function planemove(event){
		//创建event对象
		var eve =event ||window.event;
		//获取坐标
		var x=eve.clientX-400;
		var y=eve.clientY;
		//动态修改飞机的位置
		img.style.left=x-33+"px";
		img.style.top=y-40+"px";
		if(x<0){
			document.removeEventListener("mousemove",planemove,true);
		}
	}
document.addEventListener("mousemove",planemove,true);

























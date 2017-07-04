---
layout: post
title: "学习3D标签云"
description: ""
category: 
tags: []
---
{% include JB/setup %}
#### 起因

在朋友的博客发现一个很酷炫的3D标签云，决定学习一下。

于是就在网上搜索了一下，发现了 WAxes 对3D标签云的讲解（参见参考资料）

#### 3D标签云效果如下
<style>
	.tagBall{
		width: 250px;
		height: 250px;
		margin:25px auto;
		position: relative;
	}
	.tag{
		display: block;
		position: absolute;
		left: 0px;
		top: 0px;
		color: #000;
		text-decoration: none;
		font-size: 8px;
		font-family: "微软雅黑";
		font-weight: bold;
	}
	.tag:hover{border:1px solid #666;}
</style>
<div class="tagBall">
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">3D标签云</a>
	<a class="tag" href="#">WAXES</a>
</div>
<script>
	var tagEle = "querySelectorAll" in document ? document.querySelectorAll(".tag") : getClass("tag"),
		paper = "querySelectorAll" in document ? document.querySelector(".tagBall") : getClass("tagBall")[0];
		RADIUS =110,
		fallLength = 250,
		tags=[],
		angleX = Math.PI/250,
	    angleY = Math.PI/250,
		CX = paper.offsetWidth/2,
		CY = paper.offsetHeight/2,
		EX = paper.offsetLeft + document.body.scrollLeft + document.documentElement.scrollLeft,
		EY = paper.offsetTop + document.body.scrollTop + document.documentElement.scrollTop;
	function getClass(className){
		var ele = document.getElementsByTagName("*");
		var classEle = [];
		for(var i=0;i<ele.length;i++){
			var cn = ele[i].className;
			if(cn === className){
				classEle.push(ele[i]);
			}
		}
		return classEle;
	}
	function innit(){
		for(var i=0;i<tagEle.length;i++){
			var a , b;
			var k = -1+(2*(i+1)-1)/tagEle.length;
			var a = Math.acos(k);
			var b = a*Math.sqrt(tagEle.length*Math.PI);
			var x = RADIUS * Math.sin(a) * Math.cos(b);
			var y = RADIUS * Math.sin(a) * Math.sin(b); 
			var z = RADIUS * Math.cos(a);
			var t = new tag(tagEle[i] , x , y , z);
			tagEle[i].style.color = "rgb("+parseInt(Math.random()*255)+","+parseInt(Math.random()*255)+","+parseInt(Math.random()*255)+")";
			tags.push(t);
			t.move();
		}
	}
	Array.prototype.forEach = function(callback){
		for(var i=0;i<this.length;i++){
			callback.call(this[i]);
		}
	}
	function animate(){
		setInterval(function(){
			rotateX();
			rotateY();
			tags.forEach(function(){
				this.move();
			})
		} , 17)
	}
	if("addEventListener" in window){
		paper.addEventListener("mousemove" , function(event){
			var x = event.clientX - EX - CX;
			var y = event.clientY - EY - CY;
			angleY = x*0.0003;
			angleX = y*0.0003;
		});
	}
	else {
		paper.attachEvent("onmousemove" , function(event){
			var x = event.clientX - EX - CX;
			var y = event.clientY - EY - CY;
			angleY = x*0.0003;
			angleX = y*0.0003;
		});
	}
	
	function rotateX(){
		var cos = Math.cos(angleX);
		var sin = Math.sin(angleX);
		tags.forEach(function(){
			var y1 = this.y * cos - this.z * sin;
			var z1 = this.z * cos + this.y * sin;
			this.y = y1;
			this.z = z1;
		})
		
	}
	function rotateY(){
		var cos = Math.cos(angleY);
		var sin = Math.sin(angleY);
		tags.forEach(function(){
			var x1 = this.x * cos - this.z * sin;
			var z1 = this.z * cos + this.x * sin;
			this.x = x1;
			this.z = z1;
		})
	}
	var tag = function(ele , x , y , z){
		this.ele = ele;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	tag.prototype = {
		move:function(){
			var scale = fallLength/(fallLength-this.z);
			var alpha = (this.z+RADIUS)/(2*RADIUS);
			this.ele.style.fontSize = 10 * scale + "px";
			this.ele.style.opacity = alpha+0.5;
			this.ele.style.filter = "alpha(opacity = "+(alpha+0.5)*100+")";
			this.ele.style.zIndex = parseInt(scale*100);
			this.ele.style.left = this.x + CX - this.ele.offsetWidth/2 +"px";
			this.ele.style.top = this.y + CY - this.ele.offsetHeight/2 +"px";
		}
	}
	innit();
	animate();
</script>
#### 个人总结

1.  建立一个3D球，抽象出3维的坐标系	
2.  通过数学方法，在3D球上求出均匀分布的点，以便后面将标签均匀定位
3.  通过字体大小，透明度，层次等级（z-index）等，将3D球2D化
4.  在3维坐标系中，计算球绕X,Y轴旋转的坐标变化
5.  绑定鼠标事件

#### 吐槽

果然，学好数理化，走遍天下 T-T

#### 参考资料
[解析3D标签云，其实很简单](http://www.cnblogs.com/axes/p/3501424.html)

[大神github源码](https://github.com/whxaxes/canvas-test/blob/gh-pages/src/3D-demo/3Dtag.html)
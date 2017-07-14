---
layout: post
title: "学习3D标签云"
author: "kkk"
description: ""
category: 
tags: []
---
{% include JB/setup %}
### 起因

在朋友的博客发现一个很酷炫的3D标签云，决定学习一下。

于是就在网上搜索了一下，发现了 WAxes 对3D标签云的讲解（参见参考资料）

### 3D标签云效果如下

{% include  KKK/3DTags %}

### 个人总结

1.  建立一个3D球，抽象出3维的坐标系	
2.  通过数学方法，在3D球上求出均匀分布的点，以便后面将标签均匀定位
3.  通过字体大小，透明度，层次等级（z-index）等，将3D球2D化
4.  在3维坐标系中，计算球绕X,Y轴旋转的坐标变化
5.  绑定鼠标事件

### 吐槽

果然，学好数理化，走遍天下 T-T

### 参考资料
[解析3D标签云，其实很简单](http://www.cnblogs.com/axes/p/3501424.html)

[大神github源码](https://github.com/whxaxes/canvas-test/blob/gh-pages/src/3D-demo/3Dtag.html)

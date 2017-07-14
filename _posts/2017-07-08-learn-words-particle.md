---
layout: post
title: "酷炫的文字粒子化"
author: "kkk"
description: ""
category: 
tags: []
---
{% include JB/setup %}

### 起因

通过[上一篇笔记](/2017/07/04/3D-tags-cloud.html)的参考资料，我发现了那个大神更多酷炫的技术，这里就来简单的总结一下

### 文字粒子化效果

{% include KKK/Particle %}

### 个人总结

1. 用`getimagedata`扫描canvas中的像素（rgba表示），如果像素的a值大于128（不透明），则放入点数组（这里就有了原始3D坐标）。
2. 将点数组随机化，保存粒子化后的3D坐标（随机3D坐标）
3. 动画过程，将原始3D坐标不断移动到随机3D坐标，然后还原
4. 其中，3D坐标通过2D化展现出来，这里跟[上一篇笔记](/2017/07/04/3D-tags-cloud.html)中的一样
5. 这里注意，因为我们是用圆来表示像素点（矩形），故看起来可能不连续；如果我们用矩形来表示的话，则会出现锯齿化。

### 参考资料

[随便谈谈用canvas来实现文字图片粒子化](http://www.cnblogs.com/axes/p/3500655.html)

[作者github源码](https://github.com/whxaxes/canvas-test/tree/gh-pages/src/Particle-demo/imgdata)

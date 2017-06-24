---
layout: post
title: "PL/SQL小技巧"
description: ""
category: 
tags: []
---
{% include JB/setup %}

### Tips1: 

利用`autoreplace`快速补充，设置路径如下：

Tools -> Preferences -> User Interface:Editor -> AutoReplace

#### 常见的配置(只举几个简单的例子)
```
sf=select * from
u=update	set	where
de=delete from
```

#### 具体使用

在 SQL Window 中输入`sf`按下空格，自动替换成`select * from`

#### 友情提示
常用的一些长语句感觉可以提高效率，但是缺点是只在本地有效。

---
layout: post
title: "详解 HashMap HashSet Entry"
description: ""
category: Java
tags: []
---
{% include JB/setup %}

#### 初识 HashMap
Entry 看成一个 <K,V>。

Map<K,V> 可以看成 Entry 的一个数组，即 Entry[]。

HashMap<K,V> 可以看成是 Entry\[Hash(K)\]。

HashMap 使用头插法链表解决 Hash 冲突。

HashMap 默认的负载因子(load factor)为 0.75

#### 源码解析


`this  is some code`

#### 参考资料
[java中HashMap详解](http://blog.csdn.net/caihaijiang/article/details/6280251)



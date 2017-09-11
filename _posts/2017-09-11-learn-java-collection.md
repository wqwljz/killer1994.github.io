---
layout: post
title: "学习Java基础-Collection系列"
description: ""
category: 
tags: []
---
{% include JB/setup %}

### 参考资料

[Java 集合系列目录(Category)](http://www.cnblogs.com/skywang12345/p/3323085.html)

### 01 总体框架

Java集合（collection）是java提供的工具包，包含了常用的数据结构：集合、链表、队列、栈、数组、映射等。Java集合工具包位置是java.util.*

主要划分为4个部分：列表(List)、集合(Set)、映射(Map)、工具类(Iterator迭代器、Enumeration枚举类、Arrays和Collections)

总体的框架图如下所示：

![Collection总体框架图](/Resources/pics/java-collection.jpg)

可以发现上图的主干是 **Collection** 和 **Map** 。

#### Collection

**Collection**是一个高度抽象出来的集合，它包含了集合的基本操作和属性。Collection包含了List和Set两大分支。

1. List 是一个有序的队列，每一个元素都有索引值。第一个元素的索引值是0。List的实现类有LinkedList,ArrayList,Vector,Stack。
2. Set 是一个不允许重复元素的集合。实现类有HashSet和TreeSet，大都利用Map的键不能重复来实现。其中，HashSet依赖于HashMap，实际上是通过HashMap实现的；TreeSet依赖于TreeMap，实际上是通过TreeMap实现的。


#### Map

**Map**是一个映射接口，即键值对。Map中的每个元素包含“一个key”和“key对应的value”。

AbstractMap是个抽象类，它实现了Map接口中的大部分API。

HashMap，TreeMap，WeakHashMap都是继承于AbstractMap。

Hashtable虽然继承于Dictionary，但它实现了Map接口。

#### 其他

**Iterator**是遍历集合的工具，即我们通常通过Iterator迭代器来遍历集合。我们说Collection依赖于Iterator，是因为Collection的实现类都要实现iterator()函数，返回一个Iterator对象。

**ListIterator**是专门为遍历List而存在的。

**Enumeration**，它是JDK 1.0引入的抽象类。作用和Iterator一样，也是遍历集合；但是Enumeration的功能要比Iterator少。在上面的框图中，Enumeration只能在Hashtable, Vector, Stack中使用。

**Arrays**和**Collections**，它们是操作数组、集合的两个工具类。

### 02 Collection详解

Collection分为List和Set,List和Set都有它们各自的实现类。

为了方便，我们抽象出了AbstractCollection抽象类，它实现了Collection中的绝大部分函数；这样，在Collection的实现类中，我们就可以通过继承AbstractCollection省去重复编码。AbstractList和AbstractSet都继承于AbstractCollection，具体的List实现类继承于AbstractList，而Set的实现类则继承于AbstractSet。

另外，Collection中有一个iterator()函数，它的作用是返回一个Iterator接口。通常，我们通过Iterator迭代器来遍历集合。ListIterator是List接口所特有的，在List接口中，通过ListIterator()返回一个ListIterator对象。

**Collection**的定义如下：

`public interface Collection<E> extends Iterable<E> {}`

它是一个接口，是高度抽象出来的集合，它包含了集合的基本操作：添加、删除、清空、遍历(读取)、是否为空、获取大小、是否保护某元素等等。

**List**的定义如下：

`public interface List<E> extends Collection<E> {}`










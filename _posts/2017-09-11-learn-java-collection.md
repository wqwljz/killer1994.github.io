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

Collection分为List和Set，List和Set都有它们各自的实现类。

为了方便，我们抽象出了AbstractCollection抽象类，它实现了Collection中的绝大部分函数；这样，在Collection的实现类中，我们就可以通过继承AbstractCollection省去重复编码。AbstractList和AbstractSet都继承于AbstractCollection，具体的List实现类继承于AbstractList，而Set的实现类则继承于AbstractSet。

另外，Collection中有一个iterator()函数，它的作用是返回一个Iterator接口。通常，我们通过Iterator迭代器来遍历集合。ListIterator是List接口所特有的，在List接口中，通过ListIterator()返回一个ListIterator对象。

**Collection**的定义如下：

{% highlight java linenos %} 
public interface Collection<E> extends Iterable<E> {}
{% endhighlight %}

它是一个接口，是高度抽象出来的集合，它包含了集合的基本操作：添加、删除、清空、遍历(读取)、是否为空、获取大小、是否保护某元素等等。

**List**的定义如下：

{% highlight java linenos %} 
public interface List<E> extends Collection<E> {}
{% endhighlight %}

List是一个继承于Collection的接口，即List是集合中的一种。List是有序的队列，List中的每一个元素都有一个索引；第一个元素的索引值是0，往后的元素的索引值依次+1。和Set不同，List中允许有重复的元素。

**Set**的定义如下：

{% highlight java linenos %} 
public interface Set<E> extends Collection<E> {}
{% endhighlight %}

Set是一个继承于Collection的接口，即Set也是集合中的一种。Set是没有重复元素的集合。

**AbstractCollection**的定义如下：

{% highlight java linenos %} 
public abstract class AbstractCollection<E> implements Collection<E> {}
{% endhighlight %}

AbstractCollection是一个抽象类，它实现了Collection中除iterator()和size()之外的函数。

AbstractCollection的主要作用：它实现了Collection接口中的大部分函数。从而方便其它类实现Collection，比如ArrayList、LinkedList等，它们这些类想要实现Collection接口，通过继承AbstractCollection就已经实现了大部分的接口了。

**AbstractList**的定义如下：

{% highlight java linenos %} 
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {}
{% endhighlight %}

AbstractList是一个继承于AbstractCollection，并且实现List接口的抽象类。它实现了List中除size()、get(int location)之外的函数。

AbstractList的主要作用：它实现了List接口中的大部分函数。从而方便其它类继承List。

另外，和AbstractCollection相比，AbstractList抽象类中，实现了iterator()接口。

**AbstractSet**的定义如下：

{% highlight java linenos %} 
public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E> {}
{% endhighlight %}

AbstractSet是一个继承于AbstractCollection，并且实现Set接口的抽象类。由于Set接口和Collection接口中的API完全一样，Set也就没有自己单独的API。和AbstractCollection一样，它实现了List中除iterator()和size()之外的函数。

AbstractSet的主要作用：它实现了Set接口中的大部分函数。从而方便其它类实现Set接口。

**Iterator**的定义如下：

{% highlight java linenos %} 
public interface Iterator<E> {}
{% endhighlight %}

Iterator是一个接口，它是集合的迭代器。集合可以通过Iterator去遍历集合中的元素。Iterator提供的API接口，包括：是否存在下一个元素、获取下一个元素、删除当前元素。

注意：Iterator遍历Collection时，是fail-fast机制的。即，当某一个线程A通过iterator去遍历某集合的过程中，若该集合的内容被其他线程所改变了；那么线程A访问集合时，就会抛出ConcurrentModificationException异常，产生fail-fast事件。关于fail-fast的详细内容，我们会在后面专门进行说明??

**ListIterator**的定义如下：

{% highlight java linenos %} 
public interface ListIterator<E> extends Iterator<E> {}
{% endhighlight %}

ListIterator是一个继承于Iterator的接口，它是队列迭代器。专门用于便利List，能提供向前/向后遍历。相比于Iterator，它新增了添加、是否存在上一个元素、获取上一个元素等等API接口。






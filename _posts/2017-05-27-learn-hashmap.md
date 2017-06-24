---
layout: post
title: "详解 HashMap"
description: ""
category: Java
tags: []
---
{% include JB/setup %}

### 初识 HashMap
Entry 看成一个 <K,V>。

Map<K,V> 可以看成 Entry 的一个数组，即 Entry[]。

HashMap<K,V> 可以看成是 Entry\[Hash(K)\]。

HashMap 使用头插法链表解决 Hash 冲突。

Hash 若key值冲突，覆盖之前的 value值。

HashMap 默认的负载因子(load factor)为 0.75

HashMap 的存储示意图(网图，侵删)

![HashMap 的存储示意](/Resources/pics/hashmap.jpg)


### 源码解析

1. HashMap的put(K key, V value)方法

```
public V put(K key, V value) 
{ 
    // 如果 key 为 null，调用 putForNullKey 方法进行处理
    if (key == null) 
   	 return putForNullKey(value); 
    // 根据 key 的 keyCode 计算 Hash 值
    int hash = hash(key.hashCode()); 
    // 搜索指定 hash 值在对应 table 中的索引
	 int i = indexFor(hash, table.length);
    // 如果 i 索引处的 Entry 不为 null，通过循环不断遍历 e 元素的下一个元素
    for (Entry<K,V> e = table[i]; e != null; e = e.next) 
    { 
   	 Object k; 
   	 // 找到指定 key 与需要放入的 key 相等（hash 值相同
   	 // 通过 equals 比较放回 true）
   	 if (e.hash == hash && ((k = e.key) == key 
   		 || key.equals(k))) 
   	 { 
   		 V oldValue = e.value; 
   		 e.value = value; 
   		 e.recordAccess(this); 
   		 return oldValue; 
   	 } 
    } 
    // 如果 i 索引处的 Entry 为 null，表明此处还没有 Entry 
    modCount++; 
    // 将 key、value 添加到 i 索引处
    addEntry(hash, key, value, i); 
    return null; 
} 
```
上面代码的补充函数
```
static int hash(int h) 
{ 
    h ^= (h >>> 20) ^ (h >>> 12); 
    return h ^ (h >>> 7) ^ (h >>> 4); 
} 

static int indexFor(int h, int length) 
{ 
    return h & (length-1); 
}

void addEntry(int hash, K key, V value, int bucketIndex) 
{ 
    // 获取指定 bucketIndex 索引处的 Entry 
    Entry<K,V> e = table[bucketIndex]; 	 // ①
    // 将新创建的 Entry 放入 bucketIndex 索引处，并让新的 Entry 指向原来的 Entry 
    table[bucketIndex] = new Entry<K,V>(hash, key, value, e); 
    // 如果 Map 中的 key-value 对的数量超过了极限
    if (size++ >= threshold) 
        // 把 table 对象的长度扩充到 2 倍。
        resize(2 * table.length); 	 // ②
} 
```
<code>hash()</code> 只是纯数学的计算哈希值，

<code>indexFor()</code> 相当于 哈希值 对 数组长度 求余数，

`void addEntry(int hash, K key, V value, int bucketIndex)` 若原 bucketIndex 处有 Entry 则形成链表，即新插入的 Entry 为头结点，指向之前的 Entry ; 否则，直接放入 bucketIndex 处。

#### 参考资料
[java中HashMap详解](http://blog.csdn.net/caihaijiang/article/details/6280251)



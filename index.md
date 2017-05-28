---
layout: page
title: 无知
tagline: Supporting tagline
---
{% include JB/setup %}

### 文章列表

<ul class="posts" style="padding-right:80px">
  {% for post in site.posts %}
    <li style="list-style:none;"> <a href="{{ BASE_PATH }}{{ post.url }}">{{ post.title }}</a> 
		
    <span style="float:right">{{ post.date | date_to_string }}</span></li>
  {% endfor %}


</ul>



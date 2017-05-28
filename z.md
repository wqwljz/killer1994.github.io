---
layout: page
title: 和平
permalink: /about/
---

{% for category in site.categories %}
<h3 class="can-fix">{{ category | first }} ({{ category | last | size }})</h3>

<ul class="posts" style="padding-right:80px;margin-top:-10px">
    {% for post in category.last %}
        <li style="list-style:none;"> 
        	<a href="{{ BASE_PATH }}{{ post.url }}">{{ post.title }}</a> 
			<span style="float:right">{{ post.date | date_to_string }}</span>
		</li>
    {% endfor %}
</ul>
{% endfor %}

<style>
@-webkit-keyframes fadeIn {
0% {
opacity: 0.2; /*初始状态 透明度为0*/
}
50% {
opacity: 0.7; /*中间状态 透明度为0*/
}
100% {
opacity: 1; /*结尾状态 透明度为1*/
}
}
@-webkit-keyframes fadeOut {
0% {
opacity: 0.7; /*初始状态 透明度为0*/
}
50% {
opacity: 0.2; /*中间状态 透明度为0*/
}
100% {
opacity: 0; /*结尾状态 透明度为1*/
}
}
.posts
{
-webkit-animation-name: fadeIn; /*动画名称*/
-webkit-animation-duration: 2s; /*动画持续时间*/
-webkit-animation-iteration-count: 1; /*动画次数*/
-webkit-animation-delay: 0s; /*延迟时间*/
}
</style>
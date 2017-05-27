---
layout: page
title: 和平
permalink: /about/
---

{% for category in site.categories %}
<h3 class="can-fix">{{ category | first }} ({{ category | last | size }})</h3>

<ul class="posts" style="padding-right:80px;margin-top:-10px">
    {% for post in category.last %}
        <li style="list-style:none;"> <a href="{{ BASE_PATH }}{{ post.url }}">{{ post.title }}</a> 
		
    <span style="float:right">{{ post.date | date_to_string }}</span></li>
    {% endfor %}
</ul>
{% endfor %}

<script>

</script>

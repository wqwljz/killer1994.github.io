---
layout: page
title: 无知
tagline: Supporting tagline
---
{% include JB/setup %}
<table width="83%" style="margin-left:50px">
	<tbody>
		<tr>
			<td width="70%">
				<ul class="posts">
					{% for post in site.posts %}
						{% if post.category <> "private" %}
							<li class="list-group-item title" style="list-style:none;">
						 		<a href="{{ BASE_PATH }}{{ post.url }}">{{ post.title }}</a> 
								<!-- <span style="float:right">{{ post.date | date_to_string }}</span> -->
							</li>
						{% endif %}
					{% endfor %}
					
				</ul>
			</td>
		</tr>
	</tbody>
</table>

<script>


</script>

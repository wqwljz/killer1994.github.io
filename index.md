---
layout: page
title: 无知
tagline: Supporting tagline
---
{% include JB/setup %}
<table width="96%" style="margin:0 auto;max-width:700px;">
	<tbody width="100%">
		<tr>
			<td>
				<ul class="posts" style="margin-left:0;">
					{% for post in site.posts %}
						{% if post.category <> "private" and post.category <> "leetcode" %}
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

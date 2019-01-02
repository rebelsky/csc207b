---
title: Current Lab
permalink: /labs/current.html
---
{% assign current = site.labs | where: "current", true | first %}
{% if current %}
Current Laboratory
==================
<script language="javascript">
  document.location = "{{ site.baseurl }}{{ current.url }}";
</script>
<p>
  Our current lab is 
  <a href="{{ site.baseurl }}{{ current.url }}">{{ current.title }}</a>.
</p>
{% else %}
No lab today
============

There is no laboratory today.  Be prepared to discuss ideas, to
listen to new concepts, to ask questions and to respond to questions.

{% endif %}

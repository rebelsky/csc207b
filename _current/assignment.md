---
title: Current assignment
permalink: /assignments/current.html
---
{% assign current = site.assignments | where: "current", true | first %}
{% if current %}
Current Assignment
==================
<script language="javascript">
  document.location = "{{ site.baseurl }}{{ current.url }}";
</script>
<p>
  Our current assignment is 
  <a href="{{ site.baseurl }}{{ current.url }}">{{ current.title }}</a>.
</p>
{% else %}
No current assignment
=====================

Whether or not it seems possible to you, there is no current assignment
in this section of CSC 151.

If you are looking for something to do related to this course, go through 
recent "Extras" and find some fun ones to attempt.

Of course, you should also take time to relax and recuperate.

{% endif %}

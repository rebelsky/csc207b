---
title: Current exam
permalink: /exams/current.html
---
{% assign current = site.exams | where: "current", true | first %}
{% if current %}
Current Examination
===================
<script language="javascript">
  document.location = "{{ site.baseurl }}{{ current.url }}";
</script>
<p>
  Our current examination is 
  <a href="{{ site.baseurl }}{{ current.url }}">{{ current.title }}</a>.
</p>
{% else %}
No current examination
======================

We do not currently have an examination in progress.  You should look
at the [schedule]({{ site.baseurl }}/schedule) to see what work
is currently due.

{% endif %}

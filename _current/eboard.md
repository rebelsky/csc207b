---
title: Current EBoard
permalink: /eboards/current.html
---
{% assign current = site.eboards | where: "current", true | first %}
{% if current %}
Current eboard
==============
<script language="javascript">
  document.location = "{{ site.baseurl }}{{ current.url }}";
</script>
<p>
  Our current eboard is 
  <a href="{{ site.baseurl }}{{ current.url }}">{{ current.title }}</a>.
</p>
{% else %}
No current eboard
=================

For unknown reasons, there is not a current eboard.  Contact SamR to
get this issue fixed.
{% endif %}

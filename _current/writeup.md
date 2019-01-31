---
title: Current Lab Writeup
permalink: /writeups/current.html
---
{% assign current = site.writeups | where: "current", true | last %}
{% if current %}
Current Lab Writeup
===================
<script language="javascript">
  document.location = "{{ site.baseurl }}{{ current.url }}";
</script>
<p>
  Our current lab writeup is 
  <a href="{{ site.baseurl }}{{ current.url }}">{{ current.title }}</a>.
</p>
{% else %}
No current lab writeup found
============================

There does not seem to be a current lab writeup.  However, Sam is
known to screw things up.  Make sure to check the [class
news](../news/) for official information.

{% endif %}

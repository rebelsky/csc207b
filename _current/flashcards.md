---
title: Current Flashcard Assignment
permalink: /flashcards/current.html
---
{% assign current = site.flashcards | where: "current", true | first %}
{% if current %}
Current flashcard assignment
============================
<script language="javascript">
  document.location = "{{ site.baseurl }}{{ current.url }}";
</script>
<p>
  Our current flascard assignment is 
  <a href="{{ site.baseurl }}{{ current.url }}">{{ current.title }}</a>.
</p>
{% else %}
No current flashcard assignment found
=====================================

Could not find the current flashcard assignment.  However, Sam is known
to screw things up.  Make sure to check the [class news](../home/news)
or the [class schedule](../home/schedule) for official information.

{% endif %}

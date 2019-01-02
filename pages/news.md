---
title: Class news
permalink: /news/
---
Class news
==========

*This information should be automatically copied from the daily eboards.*

{% assign dates = "" | split: "|" %}
{% for week_data in site.data.dates %}
  {% for day in week_data.days %}
    {% if day.class %}
      {% assign dates = dates | push: day.date %}
    {% endif %}
  {% endfor %}
{% endfor %}
{% assign prelim = site.prelim %}

{% assign numbers = (1..prelim.size) | reverse %}
{% for counter in numbers %}
  {% if counter < 10 %}
    {% capture num %}0{{ counter }}{% endcapture %}
  {% else %}
    {% capture num %}{{ counter }}{% endcapture %}
  {% endif %}
  {% capture filepath %}_prelim/prelim{{ num }}.md{% endcapture %}
  {% assign info = site.prelim | where: "path", filepath | first %}
  {% if info %}
     {% if info.link %}
       {% assign index = counter | minus: 1 %}
       {% assign held = dates[index] %}
## Class {{ counter }} ({{ held | date: '%A, %-d %B %Y' }})

{{ info.content }}
<hr/>
     {% endif %}
  {% else %}
**Can't find daily preliminaries!**

counter: {{ counter }}

num: {{ num }}

info: {{ info }}

filepath: {{ filepath }}

<hr/>
  {% endif %}
{% endfor %}

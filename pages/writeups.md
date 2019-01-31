---
title: Writeups
permalink: /writeups/
---
# Lab writeups

The following writeups are required, and should be completed before
class on the date listed.  When submitting your lab writeup, make
sure to carbon-copy your partner(s).

<dl class="dl-horizontal ">
  {% assign writeups = site.writeups | shift | reverse %}
  {% for writeup in writeups %}
    {% if writeup.link %}
      {% include lab_date.html url=writeup.lab %}
      <dt><a href="../{{ writeup.url }}">Writeup for lab {{ writeup.number }}</a></dt>
        {% if lab-due %}
        <dd>Due: {{ lab-due | date: "%A, %-d %B %Y" }}</dd>
        {% else %}
        <dd>Unknown due date</dd>
        {% endif %}
    {% endif %}
  {% endfor %}
</dl>


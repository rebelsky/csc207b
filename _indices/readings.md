---
title: Readings
permalink: /readings/
---
# Readings
The following readings are required, and should be completed before class on the date listed.

<dl class="dl-horizontal">
  {% assign daynum = 0 %}
  {% for week in site.data.dates %}
    {% for day in week.days %}
      {% assign class = site.data.classes[daynum] %}
      {% if class.reading %}
        <dt>{{ day | date: '%-d %B %Y' }}</dt>
        <dd>
          <ul class='list-unstyled'>
            {% for item in class.reading %}
              {% assign itemlong = item | append: ".html" | replace: ".html.html", ".html" %}
              {% assign reading = site.documents | where: "url", itemlong | first %}
              {% if reading %}
                <li>{% include schedule_item.html item=reading show-due-time=false %}</li>
              {% else %}
                <li>{{ item | markdownify | remove: '<p>' | remove: '</p>' }}</li>
              {% endif %}
            {% endfor %}
          </ul>
        </dd>
      {% endif %}
      {% assign daynum = daynum | plus: 1 %}
    {% endfor %}
  {% endfor %}
</dl>

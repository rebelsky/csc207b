---
title: Assignments
permalink: /assignments/
---
# Assignments
We will have assignments almost every week of the semester, except when you have a take-home exam. You will complete assignments outside of class time with your assigned partner(s). Generally your assignment group will be the same group you were assigned to in class on the Wednesday the assignment was released.

<dl>
  {% assign assignments = site.assignments | sort: 'due' %}
  {% for assignment in assignments %}
    {% if assignment.due %}
      <dt>{% include schedule_item.html item=assignment show-due-time=false show-subtitle=true %}</dt>
      <dd>
        <ul class="list-inline">
          {% if assignment.assigned %}
            <li>Assigned {{ assignment.assigned | date: '%B %-d, %Y' }}</li>
          {% endif %}
          {% if assignment.due %}
            <li>Due {{ assignment.due | date: '%B %-d, %Y' }}{% if assignment.due-time %} <i>(before {{ assignment.due-time | remove: ' Tuesday' }})</i>{% endif %}</li>
          {% endif %}
        </ul>
      </dd>
    {% endif %}
  {% endfor %}
</dl>

---
title: Object-oriented problem solving, data structures, and algorithms
permalink: /home/
---
# Object-oriented problem solving, data structures, and algorithms
<dl class="dl-horizontal">
  <dt>Instructor</dt>
  <dd>
    <p><a href="{{ site.instructor_homepage }}">{{ site.instructor }}</a></p>
  </dd>

  <dt>Meeting Times</dt>
  <dd>
    <ul class="list-unstyled">
      {% for item in site.meeting_times %}
        <li>{{ item | markdownify | remove: "<p>" | remove: "</p>" }}</li>
      {% endfor %}
    </ul>
  </dd>

  <dt>Office Hours</dt>
  <dd>
    <ul class="list-unstyled">
      {% for item in site.office_hours %}
        <li>{{ item | markdownify | remove: "<p>" | remove: "</p>" }}</li>
      {% endfor %}
    </ul>
  </dd>

  {% if site.review_sessions %}
    <dt>Review Sessions</dt>
    <dd>
      <ul class="list-unstyled">
        {% for session in site.review_sessions %}
          <li>{{ session }}</li>
        {% endfor %}
      </ul>
    </dd>
  {% endif %}
  
  {% if site.textbook %}
    <dt>Textbook</dt>
    <dd>
      {{ site.textbook | markdownify | remove: "<p>" | remove: "</p>" }}
    </dd>
  {% endif %}
  
  {% if site.mentors %}
    <dt>Class Mentors</dt>
    <dd>
      <ul class="list-unstyled">
        {% for mentor in site.mentors %}
          <li>{{ mentor }}</li>
        {% endfor %}
      </ul>
    </dd>
  {% endif %}
  
  {% if site.mentor_sessions %}
    <dt>Mentor Sessions</dt>
    <dd>
      <ul class="list-unstyled">
        {% for session in site.mentor_sessions %}
          <li>{{ session | markdownify | remove: "<p>" | remove: "</p>" }}</li>
        {% endfor %}
      </ul>
    </dd>
  {% endif %}
  
  {% if site.tutors %}
    <dt>CS Tutors</dt>
    <dd>
      <ul class="list-unstyled">
        {% for tutor in site.tutors %}
          <li>{{ tutor }}</li>
        {% endfor %}
      </ul>
    </dd>
  {% endif %}
</dl>

## About this course

Welcome to CSC 207-{{ site.section_number }}, a section of the third
course in Grinnell College's introductory sequence.  In CSC 207 you
will explore the design and implementation of abstract data types,
data structures, and algorithms.  (It's okay if you don't yet know
the difference betwee abstract data types and data structures; we'll
talk about it.)  We'll also explore basic issues of object-oriented
design.  We use the Java programming language for our work, along
with the Eclipse integrated development environment and the Git
version control system.

There are two sections of the course this semester.  I'm teaching
both of them.  That means that they are likely to be more or less
the same.  (Nope, I'm not trying a control group and a treatment
group.)  Nonetheless, please make sure you refer to your section's
course website when checking due dates and course policies.

Although we sometimes have "themes" to courses in our introductory
sequence, this course has no explicit theme.  (I have tried to adapt
many of PM's assignments, and he notes that the implicit theme to
those assignments is "_projects that you could extend to make them your
own_" or something like that.)

I'm trying an experiment this semester.  Since I'm using PM's
assignments, I'm also using some of his readings and labs.  You'll
find that the Rebelsky readings and labs and the Osera readings and
labs will feel somewhat different.  I hope that's okay.

Learn more about the course in [the syllabus](../syllabus/) and 
[the schedule](../schedule/).

{% if site.extra_homepage_text %}
{{ site.extra_homepage_text | markdownify }}
{% endif %}

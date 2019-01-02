---
title: Lab flashcards
permalink: /flashcards/
---
# {{ page.title }}

Lab flashcards are generally due at the start of the next class.  You should
attempt to finish lab flashcards in class.  If you cannot, I would prefer that
you finish the lab flashcard with your partner.  If you cannot, you may
submit lab flashcards on your own.

When submitting your lab flashcard, make sure to carbon copy your partner.

<dl>
  {% assign flashcards = site.flashcards | sort: 'due' %}
  {% for flashcard in flashcards %}
    {% if flashcard.link %}
      {% if flashcard.due %}
        <dt>{% include schedule_item.html item=flashcard show-due-time=false show-subtitle=true %}</dt>
        <dd>
          <ul class="list-inline">
            {% if flashcard.assigned %}
              <li>Assigned {{ flashcard.assigned | date: '%A, %-d %B %Y' }}</li>
            {% endif %}
            {% if flashcard.due %}
              <li>Due {{ flashcard.due | date: '%A, %-d %B %Y' }}{% if flashcard.due-time %} <i>(before {{ flashcard.due-time | split: ' ' | first }})</i>{% endif %}</li>
            {% endif %}
          </ul>
        </dd>
      {% endif %}
    {% endif %}
  {% endfor %}
</dl>

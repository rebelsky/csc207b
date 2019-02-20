---
title: Eboard 12  Linear structures
number: 12
section: eboards
held: 2019-02-20
link: true
current: true
---
CSC 207.02 2019S, Class 12:  Linear structures
==============================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Preparation
* Lab
* Debrief (if time)

Preliminaries
-------------

### News / Etc.

* Whee!  More snow!
* I brought food.  (No particular reason.)
* Today is a lab day.  I *think* it's the right length.  (Morning
  went pretty well.)
* Today and Friday will mostly be a chance to re-explore some things
  you should have down in C, but in a slightly new context.
    * You may see these strange things called `Iterator`s.  We'll
      discuss those on Monday.
* You should be getting an email about the CPUS.  Let me know if you have
  questions.  (Computing Peers for Understanding and Support)
    * Informal program.
    * Helps provide mentorship/social support from an upper-level student.
    * Builds community.
    * Can be helpful for people from underrepresented groups (first-gen, 
      low-SES, gender minorities, people of color, etc.)
    * Free food at Grill or Saints Rest!
* Because assignment 3 was such a bear, I'm giving everyone full credit.
  (Comments will come later, perhaps much later.)
* I _think_ I've set up the system so that our eboard auto-updates every
  five minutes or so during class.

### Upcoming work

* [Assignment 4](../assignments/assignment04) due Thursday night.
    * Q&A below
* Readings for Friday
    * [Wrapping objects and classes](../readings/wrappers)
      (Not available until tomorrow night.)
    * [Priority queues](../readings/priority-queues)
      (Will be updated tonight.)
* Quiz Monday!  Inheritance, lists, linear structures.
* [Lab writeup](../writeups/writeup12): Exercise 4
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.02 Writeup for Class 12 (Your names)**
    * Please put your code in the body of the message.

### Extra credit

#### Extra credit (Academic/Artistic)

* Grinnell Symphony, Wednesday, 7:30 p.m., in Sebring-Lewis
* CS Extras, Thursday, 4:15 p.m. Science 3821: Sam on CSC 321/22.
  (Snacks at 4pm in the CS Commons.)
* Iowa Flautists in Concert.  Saturday, 23 February 2019.
  3:45 p.m. Sebring-Lewis Hall.

#### Extra credit (Peer)

* Indoor Track and Field, Friday and Saturday, at Monmouth.
* Paint Younker Lounge, 7-0 pm, Friday night.

#### Extra credit (Wellness)

* 30 Minutes of Mindfulness at SHACS every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Examples include "read for pleasure" or "take a
  walk" or "make snow angels".  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends, such as 
  making a meal, having a snowball fight, or playing a board game.
  Your email reflection must explain how the activity contributed to
  your wellness.

#### Extra credit (Misc)

### Other good things

* Traveling Smithsonian Exhibit at Drake.

### Questions

Should we use private fields?

> If you'd like.  But remember that subclasses can't access private fields.

If we decide on the grid approach, what happens if the combined sizes
don't equal the size of the grid?

> Up to you.  Give up.  Fill it in with dead organisms (don't cooperate,
  breed, gain energy, or lose energy).  Fill it with nulls (probably 
  dangerous).

If something randomly gives to eight other cells, should we avoid
duplicates?

> I'd prefer that you avoid duplicates.

Are all the organism types necessarily specified in the array?

> Nope.

Are there clever ways to do the distribution to avoid duplication?

> Yes.

> For example, you might divide the population into eight more-or-less
  equally sized groups and give one to a random element of each group.

> You might sort the indices randomly and pull off the first eight.

What should I do if I find that I'm spending an unreasonable amount
of time on the homework?

> Let me know, I'll try to work on figuring out why and making it more
  reasonable.

Why might we manually set the seed of our `Random` object?

> So that you can rerun an identical experiment (e.g., to debug)

What should I do in the 1/3 case when there are ten things or 100 things?

> An extra partial cooperator?

Preparation
-----------

_Talk to your partner: What seemed familiar in the readings?  What
seemed new?  What was confusing?_

Familiar

* Queue and stack "stuff" seemed familiar: Policies, operations
* Linked nodes and arrays as implementations. 

New

* It's a different language.  But most of it seems the same.
* Seems simpler in Java; no need to *free*.

Confusing

* Why doesn't `Stack` extend `LinearStructure`?  Because Sam wasn't
  using real code.  Today's code does so.

Other

* What about the middle of linked structures?  You'll find that it's
  much the same as it was in C.  You can switch references, just like
  you switched pointers.  (references are quite similar to pointers
  in many respects, it's just that you can't turn an arbitrary number
  into a pointer and you can't add to pointers to look elsewhere
  in memory)

Lab
---

Write up exercise 4.

Debrief
-------

_Nope._


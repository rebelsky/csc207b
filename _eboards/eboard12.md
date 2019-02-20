---
title: Eboard 12  Linear structures
number: 12
section: eboards
held: 2019-02-20
link: true
current: true
---
CSC 207.01 2019S, Class 12:  Linear structures
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

* I brought food.  (No particular reason.)
* Today is a lab day.  I *think* it's the right length.
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
  (Comments will come later.)

### Upcoming work

* [Assignment 4](../assignments/assignment04) due Thursday night.
* Readings for Friday
    * [Wrapping objects and classes](../readings/wrappers)
      (Not available until tomorrow night.)
    * [Priority queues](../readings/priority-queues)
      (Will be updated tonight.)
* [Lab writeup](../writeups/writeup12): Exercise 6b
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.01 Writeup for Class 12 (Your names)**
    * Please put your code for `LinkedQueue` in the body of the message.

### Extra credit

#### Extra credit (Academic/Artistic)

* Grinnell Symphony, Wednesday, 7:30 p.m., in Sebring-Lewis
* CS Extras, Thursday, 4:15 p.m. Science 3821: Sam on CSC 321/22.
  (Snacks at 4pm in the CS Commons.)
* Iowa Flautists in Concert.  Saturday, 23 February 2019.
  3:45 p.m. Sebring-Lewis Hall.

#### Extra credit (Peer)

* Indoor Track and Field, Friday and Saturday, at Monmouth.

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

> Talk to your CPUS.

> Let me know, I'll try to work on figuring out why and making it more
  reasonable.

Why might we manually set the seed of our `Random` object?

> So that you can rerun an identical experiment (e.g., to debug)

Preparation
-----------

* Talk to your partner: What seemed familiar in the readings?  What
  seemed new?  What was confusing?
* "It's what we've done before, but with Java"
* New thing: Parametric polymorphism.  We don't just need lists of
  integers!

Lab
---

Writeup 6b.

Note:

* '(' is character 40, ')' is character 41.
* '<' is character 60, '>' is character 62.
* '[' is character 91, ']' is character 93.
' '{' is character 123, '}' is character 125.

Debrief
-------

_Hah!_

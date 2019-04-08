---
title: Eboard 25  Pause for breath
number: 25
section: eboards
held: 2019-04-05
link: true
---
CSC 207.01 2019S, Class 25:  Pause for breath
=============================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Friday PSA
    * Questions
* Sorted linked priority queues (continued)
* Array-based queues
* More testing

Preliminaries
-------------

### News / Etc.

* Sit where you'd like.
* Donuts!
* If you would like my notes on the things that I do not put on the 
  eboard and promise not to distribute them to others, I can send them
  to you.  (I need an email from you indicating that you promise not
  to distribute them to others.)
* Welcome to John Gouwar, our new class mentor.

### Upcoming work

* Readings for Monday: Lots!
    * [List ADTs](../readings/list-adts) (may get updated)
    * "[Lists with 'current' considered harmful](http://csis.pace.edu/~bergin/papers/ListsWithCurrent.html)"
    * [java.util.List](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html)
    * [java.util.ListIterator](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ListIterator.html)
* [Makeup exam 1](../exams/makeup01) due Thursday the 11th
    * **Required** prologue due **TONIGHT!**
    * I will report back on exam times on Saturday morning
    * I will also distribute unit tests on Saturday morning

### Extra credit

#### Extra credit (Academic/Artistic)

* Isabelle Demers concert, Saturday at 3:00 p.m. in Herrick

#### Extra credit (Peer)

* Singers concert, April 7 at 2pm

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* Wednesday the 10 at 4pm on Mac Field: Giant Laurel Leaf.  (Free t-shirt!)
* Scarlet and Give Back Day next Wednesday/Thursday (I think).  If you
  don't have money to donate, let me know and I will give you $5 to donate
  on Monday.

### Other good things

* Because you live in Iowa, presidential candidates come through.  Beto
  O'Rourke is coming to Hotel Grinnell today at 5:30.  Ask about the
  hacker collective he was in.

### Friday PSA

* Don't park in the loading zone.

### Questions

Sorted linked priority queues
-----------------------------

* Where we were: We'd written something, but hadn't tested it because
  Eclipse is evil.
* What I did: Fixed the Eclipse problem by changing the order of
  loading of libraries.  (I'll show you.)
* What next?  Run tests, Correct code.
* Detour: Eclipse remains evil.

Array-based queues
------------------

* Issue one: Expansion
* Issue two: Remove method for iterator

Question: When you are removing a value, why set things to null?

* Helps us catch errors earlier; when you see a null, something is probably
  wrong.
* Allows the Java garbage collector to get rid of unnecessary stuff.
* "Feels right"

Question: Sometimes it's faster to shift right, rather than left.  Should
we do that?

* Perhaps.  But I'd rather get something that I'm confident is correct
  first.

Important morals:

* Draw pictures
* Run code by hand
    * Figure out what you want to do
    * Check some parts of correctness
    * Find missing parts
* Debugger is your friend (especially if you make predictions)
    * And probably easier than print statements
* It's easy to make minor mistakes; having ways to find them are important
* Having other eyes is normally good (less applicable on exams, but you
  can rely on my eyes)
* Writing tests that you can observe easily is useful
    * Arrays of strings are easier to look at than arrays of ints
    * (Arrays of ints may be easier to build)
* Lots of different examples can be helpful

More testing
------------

[Skipped]


---
title: Eboard 19  Searching
number: 19
section: eboards
held: 2019-03-08
link: true
---
CSC 207.02 2019S, Class 19:  Searching
======================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Friday PSA
    * Questions
* Lab

Preliminaries
-------------

### News / Etc.

* Mentor session Sunday night.
* I believe daylight savings time goes into effect on Sunday morning.
  Be prepared for a shift in sleep schedules.
* Do we think that the Grinnell admissions data breach is due to a
  technical hack or a social hack?
    * <https://www.reddit.com/r/ApplyingToCollege/comments/aybu9i/alert_grinnell_college_hacked/>
* Do we think that the Grinnell admissions breach is the first step
  in a more complicated chain?

### Upcoming work

* [Exam 1](../exams/exam01) due Thursday
    * Prologue was due last night.  If you have not submitted it, do so asap.
    * **Do not discuss the exam!**
* Readings for Monday: 
    * [Loop invariants](../readings/loop-invariants)
      (to be updated over the weekend)
    * [Osera 3.2 and 3.3](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap03.pdf)
* Lab writeup: TBD

### Extra credit

#### Extra credit (Academic/Artistic)

* Euphor contra dance tonight in GAAC
* Euphor workshop, 1 p.m. Saturday, March 9, Bucksbaum
* Euphor performance 7:30 p.m. Saturday, March 9, Herrick
* CS Table, Tuesday, noon: Unknown topic
* March 8-10 (7:30 7:30 2:00), Twelfth Night.  

#### Extra credit (Peer)

* Grinnell Singers March 10 at 2pm.

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

### Other good things

* Rosencrantz and Guildenstern at GHS, Friday and Saturday night.

### Friday PSA

* Be sensible in all that you do, including CS exams.
* Consent is essential.
* Remember: Folks care about you.

### Some more exam 1 notes

* Your average should work as if there was not overflow.  (That is, you
  should produce the same value as we'd get by adding up all of the values
  and then dividing by the length.)
    * `average` will need to round in the way longs normally round.  
* For the testing of average, don't worry only about the edge cases.
  Many of you suggested approaches that would fail on `[3,3]`.
* Converting to double is a bad idea because you lose precision.
  (Oooh!  That's a good test.)
* Randomness is hard for testing.  If you like randomness, start with
  an array in which you know the average and randomly change pairs
  of elements in equal, but opposite, directions.
* `remove` is likely to be hard on problem 3; think about edge cases.
* `remove` may also be hard on problem 4 and is likely to be expensive.
* Remember that `remove` does not affect the rest of the collection
  or the iteration; it just removes the element `next` just returned.

### Questions

_Can we convert the longs to strings and then write all of the operations over strings of digits?_

> Sure, but it will likely take a long time, unless you work in double time.
  Or am I just stringing you along?

_What's the average supposed to be?_

> What you'd get if you *could* add up all of the elements and then
  divide by the length.

_Can we assume that the array is "full"?_

> Sure.

Lab
---

Note that `for` loops can be your friend.

        for (T t : values) {
          ...
        } // for

I'm glad to see that some of you have started to learn why I recommend
commenting end braces.


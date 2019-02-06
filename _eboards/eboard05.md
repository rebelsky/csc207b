---
title: Eboard 05  Unit testing and debugging
number: 5
section: eboards
held: 2019-02-04
link: true
current: true
---
CSC 207.01 2019S, Class 05:  Unit testing and debugging
=======================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
* Testing lab
* Debugging lab

Preliminaries
-------------

### News / Etc.

* Mentor sessions at 7:00 p.m. Sunday nights.
* Ramadan begins during Week 14 this semester.  I will do my best to make 
  appropriate accommodations during week 14 and finals week.  College policy
  suggests that you must notify me by the end of this week if you need
  such accomodations, but I will make them no matter when you notify me.
* The "Current Writeup" link should now be working.
* I graded any lab 4's I received by 7pm Sunday night.  Make sure you
  let me know if you know you're submitting non-working code on labs.
* To make sure that you have enough time for lab, I'll try to avoid
  much talking today.
* JUnit's API changed significantly from JUnit4 to JUnit5.  I think I've
  caught all of the code that needed changing, but there may be a few left.
* Due to a confluence of circumstances, it appears we will have few MAPs
  in the department this summer.  Check with JW and CC for details on 
  their MAPs.

### Upcoming work

* [Assignment 2](../assignments/assignment02) due Thursday night.
* Reading due before class Wednesday
    * Osera 3.1: Mental Models of Computation
* Lab writeup: Problem 1 from the Debugging Lab
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.01 Writeup for Class 5 (Your names)**
    * Submit the corrected code for the `removeAs` method.

### Extra credit

#### Extra credit (Academic/Artistic)

* CS Table, Tuesday, Noon, CS Commons: Machine learning and criminal justice.
    * Simonite, Tom (2017).  How to upgrade judges with machine learning.  
    MIT Technology Review.
      <https://www.technologyreview.com/s/603763/how-to-upgrade-judges-with-machine-learning/>
    * Angwin, Julia, Larson, Jeff, Mattu Surya, and Kirchner, Lauren.  
      Machine bias.  Pro Publica.
      <https://www.propublica.org/article/machine-bias-risk-assessments-in-criminal-sentencing>
* CS department advising forum, Thursday, 11am.
* _Once Upon a Time Wolf_ (tickets required), Bucksbaum.
  Friday, 8 February, 7pm.
* _Once Upon a Time Wolf_ (tickets required), Bucksbaum.
  Saturday, 9 February, 7pm

#### Extra credit (Peer)

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)

#### Extra credit (Misc)

* Leadership Institute info session, Tuesday, 7 p.m. JRC 2nd.

### Other good things

### Questions

> Private.

_Does our program have to close the drawing panel, or should we make
the user do so?_

> Either.

Quiz
----

If you finish early, take the time to mediate quietly.  (Yes, you can also
_Should our repos be public or private?_


Lab: Testing
------------

What's wrong with `c2f`?

* The formula is backwards.
* Integer division!

What should we take from the sum example?

* Good pre/posts are hard, particularly in languages with fixed precision.
* Designing tests requires some thought.

What should we take from the expt example?

* There's a divide-and-conquer way to do exponentiation that is
  significantly faster.
* Tests with double values are a bit more complicated.
* Loops help you do more comprehensive tests.

Lab: Debugging
--------------

* Writeup!

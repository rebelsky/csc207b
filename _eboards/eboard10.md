---
title: Eboard 10  Inheritance
number: 10
section: eboards
held: 2019-02-15
link: true
---
CSC 207.01 2019S, Class 10:  Inheritance
========================================

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

* Quick survey: How long did HW3 take you?  
    * 4-6 (0), 7-9 (5), 10-12 (6), 13+ (0)
    * I apologize for a too-long HW.
    * Note I'm switching back to an Osera/Vostinar HW for HW4.
* Quick survey: Would you be willing to be partnered with someone
  from the other section?
* We'll debrief from today's lab on Monday.
* We may try to end class a few minutes early to give time for a guest
  speaker for 151 to get set up.

### Upcoming work

* [Assignment 4](../assignments/assignment04) due Thursday night.
    * Partners distributed via email.
* No reading for Monday!
* [Lab writeup](../writeups/writeup10): Exercise 7a
)
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.01 Writeup for Class 10 (Your names)**
    * Please put your code in the body of the message.
* Quiz Monday: Polymorphism 

### Extra credit

#### Extra credit (Academic/Artistic)

* Any Data Week activity this week.  (There's a lunchtime talk and a
  late-afternoon seminar.)
* HackGC weekend of 15-17 February 2019.
* CS Extras, Thursday, 4:15 p.m. Science 3821: Sam on CSC 321/22.
  (Snacks at 4pm in the CS Commons.)

#### Extra credit (Peer)

* Conference Swim and Dive meet, 16-17 February 2019.  Watch your classmate
  fly!

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* HIIT training, 10:00 am, Saturday, Dance Studio, Bear (Same Cap.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)
* Any sex week activity this week.  (If you are not comfortable writing
  to me about the particulars, feel free to submit a generic EC report.)

#### Extra credit (Misc)

* Lunar New Year Celebration, February 17, 6pm, Harris Gym

### Other good things

### Friday PSA

* You are awesome.  Continue to be so to yourselves and to others.

### Questions

Lab
---

*No GitHub repo!  Yay!*

* Sam screwed up on the design of the experiment.  Sorry about that.
  It's fixed now.
* Subclasses can access the `public`, `protected`, and (sometimes) package 
  fields of their superclasses, but not the `private` fields.
* Important discoveries by one group
    * If you get rid of the call to `super(start)` altogther, Java
      won't compile the code.
    * However, if you add a zero-parameter constructor to the superclass,
      Java implicitly calls that if you *don't* write `super`.
    * If you don't create any constructors in a class, Java creates a 
      zero-param constructor.
    * If you explicitly create any constructors in a class, Java does 
      not add a zero-param constructor to that class.
      constructor.
* Java makes sure that methods exist at compile time, but determines
  which implementation to use at run time.  (Sam will draw a diagram
  on Monday.)


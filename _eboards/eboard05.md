---
title: Eboard 05  Unit testing and debugging
number: 5
section: eboards
held: 2019-02-04
link: true
---
CSC 207.02 2019S, Class 05:  Unit testing and debugging
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
* Lab writeup: TBD
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.01 Writeup for Class 5 (Your names)**

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
* HackGC
* Ilovedataweektoo

#### Extra credit (Peer)

* Home track meet, 10am-6pm Saturday (30 min counts)

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)
* Sex Week events.

#### Extra credit (Misc)

* Leadership Institute info session, Tuesday, 7 p.m. JRC 2nd.

### Other good things

### Questions

_Should our repos be public or private?_

> Private.

_Does our program have to close the drawing panel, or should we make
the user do so?_

> Either.

_What is agile software development?_

> A "new" methodology for developing software, based on the idea that
  client expectations change regularly, we adapt to those changes.
  Lots of practices, including pair programming, test-driven-development,
  "stand ups", refactoring, ...  (We use it in 324.)

_Tell me more about static functions_

> "Normal" (object) functions are associated with individual objects.

        Thingy t = new Thingy(...);
        t.explode();

> Some functions shouldn't need objects.  We name these "static" functions.
  We notate them with the `static` keyword.  We call this `ClassName.function()`

        public class Helpers {
          /**
           * Convert from centigrade to fahrenheit ...
           */
          public static int c2f(int f) {
            ...
          }
        } // class Helpers

        ...
        pen.println("If it's 32 degrees centigrade, it's " + Helpers.c2f(32)
                    + " fahrenheit.");

> It's a lot like the procedures we learned in C, with the difference that
  we put them in classes rather than libraries.. (And we write `static`)

_Are fields accessible to static methods?_

> No.  Not even the `main` method.   

> (Static fields are accessible to static methods.)

_What about private fields?_

> Only accessible to objects in that class.

_Can I ask a followup question?_

> Yes

_Since `c2f` is public, I can access it anywhere, right?_

> More or less.

_Must `main` be static?_

> Yes.

Quiz
----

If you finish early, take the time to meditate quietly.  (Yes, you can also
go off to the bathroom or get some water.)

Lab: Testing
------------

So that we have enough time for the debugging lab, read, but do not do, 
exercise 4 of the testing lab.

What do you expect as output for the following program?

```java
public class Experiment {
  public static void main(String[] args) {
    System.out.println(9/5);
  } // main
} // class Experiment
```

Lab: Debugging
--------------

Writeup: Exercise 1 from Debugging lab

* To `csc207-01-grader@grinnell.edu`
* Subject: **CSC 207.01 Writeup for Class 5 (Your names)**
* Submit code for `removeAs`

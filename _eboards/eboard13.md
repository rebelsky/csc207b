---
title: Eboard 13  Array-based linear structures
number: 13
section: eboards
held: 2019-02-22
link: true
---
CSC 207.02 2019S, Class 13: Array-based linear structures
==========================================================

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

* Continue partners from Wednesday!
* Beware!  Today is Friday the 13th (Class)
* Today is a lab day.  I *think* it's the right length.
* Quiz 4 returned.
    * _Quizzes go to the mentor, not the recycling bin._
    * Don't write `if ((val % this.div) == 0) { return true; } else { return fal
se; }`
      Write `return (val % this.div) == 0;`
    * Subtype polymorphism and parameteric polymorphism don't always mix well.
      If `C` implements `I`, you cannot assign a value of type
      `Box<C>` to a variable of type `Box<I>`.
* Recommended approach to starting homework (after reading)
    * Create a project in Eclipse, choosing an appropriate place for
      the directory.
    * Add your primary package (if there is one).
    * Create a repo on GitHub (no README)
    * cd to that directory
    * Follow the GitHub instructions for linking the directory to
      the repo.
    * Add all the files Eclipse generates.
    * Push.
    * Share the repository with your partner and me.
* Some of you are getting messages like *Integer type is not visible*
  and *String type is not visible* in Eclipse.  I have no idea why.
  The following hack, which should not be necessary, seems to address
  the issue: add `import java.lang.String;` at the top of your file.
  Copying to another project also works in some cases.

### Upcoming work

* [Assignment 5](../assignments/assignment05) due Thursday night.
* Readings for Monday
    * [Iterators](../readings/iterators)
      (Not available until tomorrow night.)
    * [Anonymous inner classes](../readings/anonymous-inner-classes)
      (Not available until tomorrow night.)
* [Lab writeup](../writeups/writeup13): Exercise 6
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.01 Writeup for Class 13 (Your names)**
    * Put your testing code in the body of the message.

### Extra credit

#### Extra credit (Academic/Artistic)

* Iowa Flautists in Concert.  Saturday, 23 February 2019.
  3:45 p.m. Sebring-Lewis Hall.

#### Extra credit (Peer)

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

### Friday PSA

Be good.  Be well.  Be true to yourself.

Consent is essential.

### Questions

_I'm going to try to skip questions today so that there's enough time for 
lab._

_How should we partner on a GitHub repo?_

> Normal 207 practice vs. Normal "experienced" practice

> Normal 207 practice: When I stop, push everything to the repo.  When
  I start again, pull from the repo.  Issue: If you push stuff that doesn't
  work, no one has working code.

> Better practice: If you've done work that you don't want to push to the
  main branch, but don't want to lose, you can create another branch.

> You could also use forks, but that could be a bit more complicated.

Lab
---

The debugger is your friend!  (or it can be your friend)

Checking for exceptions
-----------------------

Here's an example about testing for exceptions from
[the JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/).
It uses a feature that you don't quite know yet (the `() ->`), but just
assume it's necessary syntax and we'll go from there.

```java
    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
            calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }
```

Here's how Sam traditionally tests for exceptions.

```java
    @Test
    void testException() {
      try {
        calculator.divide(1,0);
        fail("Division did not throw an exception");
      } catch (ArithmeticException ae) {
        assertEquals("/ by zero", ae.getMessage());
      } // try/catch
    } // testException
```

Note that you may note necessarily want to check the particular text
of the exception.

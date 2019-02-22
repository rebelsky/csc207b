---
title: Eboard 13  Array-based linear structures
number: 13
section: eboards
held: 2019-02-22
link: true
current: true
---
CSC 207.01 2019S, Class 13: Array-based linear structures
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
* Mentor Session Sunday at 8 p.m.
* Beware!  Today is Friday the 13th (Class)
* Today is a lab day.  I *think* it's the right length.
* Quiz 4 returned.
    * Don't write `if ((val % this.div) == 0) { return true; } else { return false; }`
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
  and *String type is not visible* in Eclipse.  I have no idea why and
  can't usually reproduce the issue.  I've found that creating a new repo
  and copying files into that often fixes it.  Once again, I'm not sure
  why.
    * My suspicion: Some of the code came from copying-and-pasting from
      Office365, and that sometimes inserts hidden characters.

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

Get consent.

### Questions

Lab
---

Remember that the debugger is your friend!

Things you will likely want to fix/update, particularly as you implement
wrap-around.

* The calculation of `this.back()`.  See below for a thought question.
* How to keep `this.front` in the range 0..`this.values.length`-1.  See
  below for some notes.
* The `isFull()` method.

```text
if (this.front is 5) and
   (this.size is 6) and
   (this.values.length is 8)
what should this.back() return?
```

Ways to handle wrap-around with `this.front`

* Strategy one: Always use `this.front % this.values.length`
* Strategy two: When you increment `this.front`, mod by `this.values.length`.

Sam thinks strategy two is much better.

### Checking for exceptions

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

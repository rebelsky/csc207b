---
title: Eboard 06  Object semantics
number: 6
section: eboards
held: 2019-02-06
link: true
---
CSC 207.02 2019S, Class 06:  Object semantics
=============================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Some notes on yesterday's labs
* Discussion
* Lab (paper)

Preliminaries
-------------

### News / Etc.

* Mentor sessions at 7:00 p.m. Sunday nights.
* Ramadan begins during Week 14 this semester.  I will do my best to make 
  appropriate accommodations during week 14 and finals week.  College policy
  suggests that you must notify me by the end of this week if you need
  such accomodations, but I will make them no matter when you notify me.
* Make sure that you're using the latest version of Eclipse (2018-12).
  Many of the errors I've helped fix have had to do with old versions.
* I'll be using the whiteboard for a lot of today's class.  Sorry that
  things won't be recorded in the eboard.  (ASCII art takes too long.)

### Upcoming work

* [Assignment 2](../assignments/assignment02) due tomorrow night.
* Reading due before class Friday
    * [Interfaces](../readings/interfaces)
    * [Subtype polymorphism](..//readings/subtype-polymorphism)
* Lab writeup: The whole worksheet.

### Extra credit

#### Extra credit (Academic/Artistic)

* Tonight's privacy reading group.  (See email to csstudents.)
* _Once Upon a Time Wolf_ (tickets required), Bucksbaum.
  Friday, 8 February, 7pm.
* _Once Upon a Time Wolf_ (tickets required), Bucksbaum.
  Saturday, 9 February, 7pm
* Any Data Week activity next week.
* HackGC weekend of 15-17 February 2019.
* African Acrobats, Feb. 15 at 7pm.

#### Extra credit (Peer)

* Home track meet, Saturday, 9 Feb 2019, all-day and beyond.  (30 min suffices)

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)
* Any Sex Week activity next week.

#### Extra credit (Misc)

* Being a male ally, Monday, 8pm JRC 101
* Math/Stats vs. CS Ping-Pong tournament.

### Other good things

* Conference Swim and Dive meet, 15-17 February 2019.  

### Questions

How should we submit HW2?

> Same as last time.  Share github repo.  Send a note to the grader
  (csc207-01-grader@grinnell.edu).  The grader's account is `csc207-grader`
  on GitHub.  (At least I hope it is.)

> Alternately, attach the files.

Some thoughts on Wednesday's labs
---------------------------------

Wednesday's labs were about ways to find/deal with incorrect code.

`c2f` issues

* Use the correct version of Eclipse.
* Sam should have taught us the debugger before he taught us unit testing.
* You need to know language details.  Both C and Java treat 9/5 as 1, not
  1.8.
* More than one test!
* Sometimes programmers don't get the formulae right.  

`sum` issues

* Umuh - Using math usually helps.
* Ways of working with arrays in Java.
* Be careful on initial and final values of loop variables, be
  careful about where you increment loop variables.
* You know that tests fail because you get RED in the window.
* When dealing with integer math, you should worry about integer overflow.
    * Preconditions!
    * Throw errors
    * Writing those preconditions may be hard.
* Integer overflow: When you add integer and they get larger than some
  value (`Integer.MAX_VALUE` in Java, `INT_MAX` in C), strange things 
  happen.   It's a consequence of how integers are stored in Java (and
  C), which is two's complement.
* You should write tests that involve these large (and largely negative) 
  values.
* Are you better off using BigIntegers?  It depends on the use case.
  BigIntegers are expensive.
* What's wrong with the following?

        // Compute the average of two non-negative integers, rounding down
        int average(int a, int b) {
          return (a+b)/2;
        }

* Fixing it: Use BigIntegers.  Use longs.  Use `a+(b-a)/2`

`expt` issues

* We had a fast version of exponentiation.  About log_n multiplications
  to compute x^n.
* But the programmer did not implement one of their cases right.
  (The math was right, the implementation was not.)
* Rather than testing "2^2 is 4", "2^5 is 32", "2^10 is 1024",
  "3^5 = 243", test x^0, x^1, x^2, x^3, ... x^? using a for loop

        for (int x = 1, x < 10; x++) {
          int result = 1;
          for (int i = 0; i < n; i++) {
            assertEquals(result, expt(x,i));
            result = result*x;
          }
        } // for

Other issues

* If your tests are in separate methods, you get separate errors.
  If your tests are in the same method, it stops as soon as you
  get the first error.

Discussion - Modeling Memory
----------------------------

Claim: Drawing pictures helps us understand code.  For example, it's
much easier to write a linked-list implementation if you draw the
pairs.

We're going to draw pictures of the state of your Java programs.

Pictures will have two (or three) parts

* Stack
* Heap
* (Code)

Stack: Contains the local variables in each procedure.  They are
either primtive values (ints, etc.) or references to the objects
and arrays we build.

When you call a method, an implicit `this` variable gets added
to the stack, pointing to the corresponding object.


Heap: Contains the objects we build throughout the program.



Lab (paper)
-----------

What are "pass by value" and "pass by reference"?

> In "pass by value", you put the *value* of each actual parameter into
  the slot on the stack for the formal.

> In "pass by reference", you put the *address* of each actual parameter
  into the slot on the stack for the formal.

```
int i;
void f(int x) { ... }
...
f(i);
```

Pass-by-value

```text
Stack
  +----------+
x |    5     |
  +----------+
i |    5     |
  +----------+
```

Pass-by-reference

```text
Stack
  +----------+
x |    &i    |
  +----------+
i |    5     |
  +----------+
```


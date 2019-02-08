---
title: Eboard 06  Object semantics
number: 6
section: eboards
held: 2019-02-06
link: true
---
CSC 207.01 2019S, Class 06:  Object semantics
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
* Sorry for the mis-installation of our course Web site.  I think that's
  resolved.
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

#### Extra credit (Peer)

* Home track meet, Saturday, 9 Feb 2019, all-day and beyond.  (30 min suffices)
* Conference Swim and Dive meet, 15-17 February 2019.  

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)
* Any sex week activity next week.

#### Extra credit (Misc)

### Other good things

### Questions

How should we submit HW2?

> Same as last time.  Share github repo.  Send a note to the grader
  (csc207-01-grader@grinnell.edu).

Some thoughts on yesterday's labs
---------------------------------

Yesterday's labs were about ways to find/deal with incorrect code.

`c2f` issues

* Make sure your formula is right.
* Know how math works on your system (9/5) is 1, not 1.8.
* Multiple tests are necessary.

`sum` issues

* In C and Java, integers can overflow.
* We have some responsibility to document what our procedure does
  when they overflow.
    * Throw an error
    * Proceed as normal; client must accept that things will be screwy.
    * Specifying what "okay" for inputs means is sometimes difficult.
* Even Sam misinterprets what should happen in some cases.

`expt` issues

* Check your cases carefully.
* We can do better/more comprehensive testing with loops
    * You could say "I know that 2^3 is 8.  Check that.  I know that
      2^10 is 1024.  Check that.  I know that 3^5 is is 243.  Check
      that."
    * We could also do a for loop that builds a, a*a, a*a*a, a*a*a*a,
      and check each of those.
    * We could nest that in another for loop to try different values
      of a.
* There's a cool O(log_2(n)) algorithm for computing a^n.
    * Back to searching (from 151).
    * Sequential search: Look at the first element, look at the second,
      ....  If we have n elements, we may have to look at n elements.
      That's kinda what O(n) means.
    * Binary search: Look in the middle, throw away half.  (Only works
      for sorted arrays.)  The number of elements you have to look at
      is about log_2(n).

Discussion
----------

* It is often easier to understand code when you draw pictures.
* For example, linked lists make a lot more sense when you draw
  the nodes (particularly when you're trying to figure out something
  like remove).
* We will therefore use some standard practices for drawing objects
  (and the state of our system).
* Issue one: Separate the stack and the heap.
* The stack stores local procedure variables and global variables
   * Each time we call a procedure, we put a new set of variables
     on the stack.
   * When we finish the procedure, we remove those variables.
   * Example, factorial  `int fact(n) { if (n == 0) 1 else n*fact(n-1); }`
   * We will draw the stack as an array of memory that grows upward
     (or maybe downward).
* The heap stores dynamically allocated values (in C, the things we create
  with malloc, in Java, the objects we create with new).
* If I say `x = new Counter();`
   * We allocate space on the heap for a counter
   * We make the x variable on the stack point to that object
   * We'll draw objects with their name and a set of their fields
     (and, sometimes, their methods).
* In contrast, we do not need to allocate memory for primitive types
  (e.g., integers, doubles), so those are stored on the stack.
  `z = 2`
* When we call an object's method, `this` also gets put on the stack
  and points to the object whose method we called.
* It gets a little bit more complicated when objects contain objects.

Lab (paper)
-----------

What are "pass by value" and "pass by reference"?

> In "pass by value", you put the *value* of each actual parameter into
  the slot on the stack for the formal.

> In "pass by reference", you put the *address* of each actual parameter
  into the slot on the stack for the formal.

```java
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


---
title: Eboard 08  Parametric polymorphism
number: 8
section: eboards
held: 2019-02-11
link: true
current: true
---
CSC 207.01 2019S, Class 08:  Parametric polymorphism
====================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Subtype polymorphism, revisited
* Parametric polymorphism
* Lab

Preliminaries
-------------

### News / Etc.

* Please remember that our grader is `csc207-01-grader@grinnell.edu` 
  *not* `csc207.01-grader@grinnell.edu`.  (No dots, just dashes.)
* Folks seem to be having some difficulties with Eclipse.  We'll try to
  go through those issues on Wednesday.  It would help if you let me
  know what fixes things in different situations.
* Quizzes 1-3 will be returned on Wednesday, when we'll discuss them.
* Apologies for the failure to update the generics reading in a timely
  fashion.  It was a crazy weekend.  I'm doing my best to get caught up,
  but failing.

### Upcoming work

* [Assignment 3](../assignments/assignment03) due Thursday night.
* Reading for Wednesday:
    * [Exceptions in Java](../readings/exceptions)
* [Lab writeup](../writeups/writeup08): Exercise 1n, 2i, 3e (What did you learn?)
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.01 Writeup for Class 8 (Your names)**
    * Please put your code in the body of the message.

### Extra credit

#### Extra credit (Academic/Artistic)

* Any Data Week activity this week.
* HackGC weekend of 15-17 February 2019.

#### Extra credit (Peer)

* Conference Swim and Dive meet, 15-17 February 2019.  

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* HIIT training, 10:00 am, Saturday, Dance Studio, Bear (Same Cap.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)
* Any sex week activity this week.  (If you are not comfortable writing
  to me about the particulars, feel free to submit a generic EC report.)

#### Extra credit (Misc)

* Lunar New Year Celebration, February 17, 6pm, Harris Gym

### Other good things

### Questions

Subtype polymorphism, revisited
-------------------------------

### Some basics

Key idea: If an A is a B, then we can use an A anywhere we expect a B.

* In type terms, "A is a B" means "A is a subtype of B".  That's
  why the *subtype*.
* Polymorphism - "Many forms".  When we write polymorphic procedures,
  we've effectively written many forms of the same procedure.  If we
  write a procedure for "things that can add and multiply", we effectively
  have something that works for BigIntegers and BigDecimals and BigFractions
  and anything else we come up with that implements those two methods.

In Java terms: If Class A implements interface B, an object of class A
can be used wherever we expect a parameter or variable of interface B.

```java
  B thing = new A();
  public static void whatever(B param) { ... }
  whatever(new A());
```

`TextBlock tb` means that `tb` references some object that implements
the `TextBlock` interface.

`dump(PrintWriter pen, TextBlock tb)` means that the procedure takes
as input a PrintWriter and any object that implements the `TextBlock`
interface.

### Interfaces vs. inheritance and superclasses

We haven't covered superclasses and inheritance yet, but some of you
have been asking about the difference.

In Java,

* Interfaces do not contain fields; superclasses do.
* Interfaces do not contain method implementations; superclasses do.
* A class may implement multiple interfaces, but can only inherit from
  one superclass.

Different contexts call for different choices.

Parametric polymorphism
-----------------------

A different model of polymorphism.  We acheive multiple forms by adding
*type parameters* to class definitions and method definitions.

```
public class NamedInteger {
  String name;
  Integer val;
  public NamedInteger(String name, Integer val) {
    this.name = name;
    this.val = val;
  } // NamedInteger(String,Integer)

  public String toString() {
    return this.name + ": " + this.val;
  } // toString()
}
```

If we want NamedDoubles, we could use copy/paste/change.

```
public class NamedDouble {
  String name;
  Double val;
  public NamedDouble(String name, Double val) {
    this.name = name;
    this.val = val;
  } // NamedDouble(String,Double)

  public String toString() {
    return this.name + ": " + this.val;
  } // toString()
}
```

But copy/paste/change is a bad strategy:

* Figuring out which type should change and which should not can be
  difficult.
* We've all seen how bad Sam is at copy/paste/change, often we get
  small errors.
* If you have a mistake in your original code, you have lots of
  things to change.
* If you add something to your original code, you have to add it everywhere.

Generalize the code by adding a type parameter

```
public class NamedThing<T> {
  String name;
  T val;
  public NamedInteger(String name, T val) {
    this.name = name;
    this.val = val;
  } // NamedInteger(String,Integer)

  public String toString() {
    return this.name + ": " + this.val;
  } // toString()
}
```

```
NamedThing<Integer> thing = new NamedThing<Integer>("i", 3);
NamedThing<String> thing = new NamedThing<String>("s", "str");
```

Gives us power, can lead to confusion.

**There are a bunch of subtleties not all covered in the reading.**

* Arrays
* Combining subtype and parameteric polymorphism

_When do you cast things with parametric polymorphism?_

> As little as possible.  Mostly to deal with the array weirdness.

Lab
---

I appreciate the creativity and seriousness I've seen in your approaches 
to the lab.

* What happens if I store multiple types in the ExpandableArray?
* What happens if I store my own type in an ExpandableArray?
* What happens if I cast things in different ways?


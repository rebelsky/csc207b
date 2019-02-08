---
title: Eboard 07  Interfaces and subtype polymorphism
number: 7
section: eboards
held: 2019-02-08
link: true
current: true
---
CSC 207.02 2019S, Class 07:  Interfaces and subtype polymorphism
================================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Friday PSA
    * Questions
* Detour: Equality in Racket
* Key ideas from readings
* Lab

Preliminaries
-------------

### News / Etc.

* Mentor sessions at 7:00 p.m. Sunday nights.

### Upcoming work

* [Assignment 3](../assignments/assignment03) due next Thursday night.
    * Partners to be assigned before the end of class.
* Reading due before class Monday
    * [Generics and parametric polymorphism](../readings/generics)
    * PM on parametric polymorphism (optional)
* Lab writeup: Exercise TBD
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.01 Writeup for Class 7 (Your names)**
    * Please put your code in the body of the message.
* Quiz Monday
    * Object modeling
    * Subtype polymorphism and interfaces
    * Maybe a bit about testing or debugging

### Extra credit

#### Extra credit (Academic/Artistic)

* _Once Upon a Time Wolf_ (tickets required), Bucksbaum.
  TONIGHT, 7pm.
* _Once Upon a Time Wolf_ (tickets required), Bucksbaum.
  TOMOROW, 7pm
* Any Data Week activity next week.
* HackGC weekend of 15-17 February 2019.

#### Extra credit (Peer)

* Home track meet, Saturday, 9 Feb 2019, all-day and beyond.  (30 min suffices)
* Conference Swim and Dive meet, 15-17 February 2019.  

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* HIIT training, 10:00 am, Saturday, Dance Studio, Bear (Same Cap.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)
* Any sex week activity next week.

#### Extra credit (Misc)

### Other good things

### Friday PSA


### Questions

How do we write `equals` methods?
---------------------------------

Recall the `Cell` class, which has an int field called `x`.  

```java
public class Cell {
  int x;
  public Cell(int x) {
    this.x = x;
  } // Cell(int)
```

What do you expect for the following?

```
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Cell c1 = new Cell(1);
    Cell c01 = new Cell(1);
    Cell c2 = new Cell(2);

    pen.println(c1.equals(c01));
    pen.println(c1.equals(c2));
    pen.println(c1.equals("1"));
  } // main
```

Suppose we decide that two cells are equal if their `x` field is equal.
How would we implement that?

`int` values vs. `Integer` objects
----------------------------------

```java
int i = 1;
```

```text
  +---------+
i |    1    |
  +---------+
```

```java
Integer j = new Integer(2); // deprecated
```

```text
  +---------+        +Integer---+
j |    *-----------> | val: 2   |
  +---------+        +----------+
```

```java
Integer k = 3; // Automatic conversion
  +---------+        +Integer---+
k |    *-----------> | val: 3   |
  +---------+        +----------+
```


Key ideas from readings
-----------------------

Lab
---

* Today's lab
    * Explore a simple example: `sqrt`.  We can do "generic" math.
    * Explore a more complex example: Text blocks.  Provides something
      similar to what happens in GUIs.
* For the `sqrt` example, you need not do a loop for the other types.
  E.g.,
        BigInteger bi = new BigInteger("12345678901234567890");
        double sqrt_bi = MathUtils.sqrt(bi);
        pen.println("The square root of " + bi + " ~= " + sqrt_bi);
        pen.println(sqrt_bi + "^2 = " + (sqrt_bi * sqrt_bi));


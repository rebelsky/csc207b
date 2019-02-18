---
title: Eboard 07  Interfaces and subtype polymorphism
number: 7
section: eboards
held: 2019-02-08
link: true
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
* Detour: Equality in Java
* Key ideas from readings
* Lab

Preliminaries
-------------

### News / Etc.

* Mentor sessions at 7:00 p.m. Sunday nights.
* I wish our department communicated better.
* Please turn in Wednesday's lab writeup.

### Upcoming work

* [Assignment 3](../assignments/assignment03) due next Thursday night.
    * Partners to be assigned before the end of class.
* Reading due before class Monday
    * [Generics and parametric polymorphism](../readings/generics)
    * PM on parametric polymorphism (optional)
* Lab writeup: Exercise 5
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.02 Writeup for Class 7 (Your names)**
    * Please put your notes in the body of the message.
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

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* HIIT training, 10:00 am, Saturday, Dance Studio, Bear (Same Cap.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)
* Any sex week activity next week.  (Free STI testing.)

#### Extra credit (Misc)

* Math vs. CS Ping Pong, tonight, 7-8pm, JRC Game Room

### Other good things

* Conference Swim and Dive meet, 15-17 February 2019.  

### Friday PSA

* Laugh.  And then take care of your self.

### Questions

When are we getting our quizzes back?

> Monday, I hope.

When will we get HW1 back?

> Monday, I hope.

Can you discuss more about what happens in the examples in problem 4?

> Two kinds of objects equality in Java, `==` and `x.equals(y)`.  `==` 
  means "same memory location".  `.equals` method can be implemented
  by individual classes, works how those classes have implemented it.
  (Default `.equals` is `==`.)  `.equals` is generally slower but
  broader.

> Java standard says (I think) that all the identical string constants 
  (string literals) that appear in a program share the same memory
  location.

> Java standard does not say what happens with other strings.  (Identical
  strings read at run time can share memory locations or may not.)

> We've discovered that our implementation of Java seems to give a new
  location to each new string it encounters at runtime.

> New strings: `scanner.next()`, `x + "hjello"`, ...

> Side note: For primitive types, like `int`, == behaves the way it
  behaved in C.

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

We might say "We'd like two cells to be equal if their x values are equal."

Suppose we have not implemented that policy.  What do you expect for
the following?

```
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Cell c1 = new Cell(1);
    Cell c01 = new Cell(1);
    Cell c2 = new Cell(2);

    pen.println(c1.equals(c1));  // true, would like true
    pen.println(c1.equals(c01)); // false, would like true
    pen.println(c1.equals(c2));  // false, would like false
    pen.println(c1.equals("1")); // false, would like false
    pen.println("1".equals(c1)); // false, would like false
  } // main
```
Suppose we implement that policy as follows.

```java
  public boolean equals(Cell other) {
    return (this.x == other.x);
  } // equals(?)
```

* We got the right result for `c1.equals(c01)`.
* Why does `==` work?  Because we're comparing two `int` values; two
  primitive types.  (numeric types, booleans, characters)
* Why do we get a result for `c1.equals("1")`?  After all `"1"` is not a
  Cell.  Because Java permits overloading (multiple methods with the
  same name, but different parameter types).  Since we called `equals`
  without a `Cell`, it uses the default `equals`.

New `equals`.

```java
  public boolean equals(Object other) {
    return this.toString().equals(other.toString());
  } // equals
```

* Note: Every class has an implicit `toString()` method.  If you don't
  implement it, you get something like `CLASS<location>`.  If you do
  implement it, it does whatever the method says.

Separate lesson: Java permits *overloading*  You can have multiple methods
with the same name and different parameter types (or number of parameters),
and it figures out which one you mean.

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
        BigInteger bi = new BigInteger("12345");
        double sqrt_bi = MathUtils.sqrt(bi);
        pen.println("The square root of " + bi + " ~= " + sqrt_bi);
        pen.println(sqrt_bi + "^2 = " + (sqrt_bi * sqrt_bi));


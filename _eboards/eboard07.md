---
title: Eboard 07  Interfaces and subtype polymorphism
number: 7
section: eboards
held: 2019-02-08
link: true
---
CSC 207.01 2019S, Class 07:  Interfaces and subtype polymorphism
================================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Friday PSA
    * Questions
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
    * [Generics and parametric polymorphism](../generics)
    * PM on parametric polymorphism (optional)
* Lab writeup: Exercise 7
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.01 Writeup for Class 7 (Your names)**
    * Please put your code in the body of the message.
* Quiz Monday
    * Object modeling
    * Subtype polymorphism and interfaces
    * Maybe a bit about testing or debugging

### Extra credit

#### Extra credit (Academic/Artistic)

* Noon today: CS Alumni talk about careers.
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

* I care.  Take care.

### Questions

Can we talk about problem 2 from the writeup?

> I hope you discovered that (a) If you use a field name without `this`,
  and there no local variable or parameter with the same name, Java
  interprets it as `this.name` (b) If you use a field name without `this`
  and you have a local variable or parameter with the same name, Java
  interprets it as parameter or local variable.

> This can lead to confusion for the reader.  So we recommend that you
  always include `this` when referring to fields.

> In C, if you use a name, the compiler checks for (a) a local variable
  or parameter, in which case it uses that, or (b) a global variable.

> Java vs. C: Both look at local/param first, Java then goes to fields
  (which C does not have), C then goes to globals (which Java doesn't
  really have).

What was happening with string literals and objects?

> `==` with objects means "same area of memory".

> `obj1.equals(obj2)` uses the equals method of `obj1`

> Strings are objects in Java.

> Identical string literals occupy the same area of memory.

> The treatment of other strings is left more open in the Java standard.
  We have discovered that our Java compiler uses new memory for each
  string you read with `Scanner.next()`.


How do we write `equals` methods?
---------------------------------


Suppose we decide that two cells are equal if their `x` field is equal.

```java
public class Cell {
  int x;
  public Cell(int x) {
    this.x = x;
  } // Cell(int)

  public boolean equals(Cell other) {
    return this.x == other.x;
  }

  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Cell c1 = new Cell(1);
    Cell c01 = new Cell(1);
    Cell c2 = new Cell(2);

    pen.println(c1.equals(c01));
    pen.println(c1.equals(c2));
    pen.println(c1.equals("1"));
  } // main
} // class Cell
```

What's happening here?  For the first two examples, it appears that our
`equals` method worked correctly.  For the third, it makes no sense that
we were able to pass a string.

Note: Every class automaticaly gets a default `equals(Object other)` method,
which is almost certainly `this == other`.

`c1.equals(c2)` - c2 is a Cell, I can use `equals(Cell)` method.

`c1.equals("1")` - c2 is not a Cell, must use the `equals(Object)` method.

The ability to have multiple methods with the same name and different
parameters is called "overloading".  We tend to overload constructors.

Key ideas from readings
-----------------------

* An interface defines a promise of methods that objects will provide.
* We can then make classes that implement that interface; they *must*
  provide all the listed methods.
* Benefits: Clients need not know implementation.  We can change
  implementations based on the usage patterns.
* (Once again, separate *what* from *how*.)
* Interfaces support *subtype polymorphism*
    * The same interface can be implemented in multiple ways.
    * The methods that use objecgts that match that interface need not
      know the underlying implementation. 
    * So they can work with *any* class that implements the interface,
      including ones that don't yet exist.

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

### `int` values vs. `Integer` objects

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



---
title: Eboard 04  Objects and classes
number: 4
section: eboards
held: 2019-02-01
link: true
current: true
---
CSC 207.02 2019S, Class 04:  Objects and classes
================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * PSA
    * Questions
* Modeling objects with classes
* An exercise
* Lab

Preliminaries
-------------

### News / Etc.

* We'll follow the new partner methodology.
    * Partner names on the board.
    * See email about "Do not partner me with" option.
* Please turn in your academic honesty policy if you have not done so
* Mentor sessions at 7:00 p.m. Sunday nights.
* I apologize for the confusion on turning in HW 1.  I hope to get things
  set up for classroom over the weekend.
* Comment from one of my children: "I know my Dad is a geek because
  he went to Atlanta for Super Bowl week and spent the whole time in
  a CS conference."

### Upcoming work

* [Assignment 2](../assignments/assignment02) due Thursday night.
    * Partners to be posted at end of class.
* Reading due before class Monday
    * [Unit testing](../readings/unit-testing)
    * [Debugging](../readings/debugging)
* Quiz Monday
    * Maybe a little git
    * Class design issues
* Lab writeup: Exercise 3, sent to csc207-01-grader@grinnell.edu with
  a subject of **CSC 207.02 Lab for Class 4 (Your Names)**.

### Extra credit

#### Extra credit (Academic/Artistic)

* Curator's Talk for _Dread and Delight_.
  Friday, 1 February 2019, 4pm.
* Opening Reception for _Dread and Delight_.
  Friday, 1 February 2019, 4:15pm. 
* Neo Futurists, Flangan Theatre.
  Friday, 1 February 2019, 7:30 pm, 
* Met Opera Live in HD, Harris Cinema.
  Saturday, 2 February 2019: 
* _Once Upon a Time Wolf_ (tickets required), Bucksbaum.
  Friday, 8 February, 7pm.
* _Once Upon a Time Wolf_ (tickets required), Bucksbaum.
  Saturday, 9 February, 7pm

#### Extra credit (Peer)

#### Extra credit (Wellness)

* HIIT tomorrow morning at 10 am in Dance Studio.

#### Extra credit (Misc)

### Other good things

* Men's Tennis, Sat, 9am, Fieldhouse.
* Men's Tennis, Sat, 2pm, Fieldhouse.
* Swim and Dive, Osgood Natatorium.
  Saturday, 2 February 2019, 2pm.

### PSA

* Please take care of yourselves. (and others)

### Questions

Modeling objects with classes
-----------------------------

_What Sam hopes you took from the reading, or at least what Sam thinks
you should know about the basics._

* Goal: Describe objects.  An object is a computational "thing" that 
  has characteristics (fields, attributes, state, associated values)
  and methods (procedures)
* Java's approach to describing objects: Classes.  Classes are much
  like C structs ...
    * The struct describes what fields the value has
    * Classes describe what fields each object has PLUS what methods
      we can write using those fields.
* For the philosophers, classes are a bit like Platonic ideals, and
  objects are like the shadows in the cave.
* In Java, classes can also have their own attributes and methods.
  We identify these with the `static` modifier.
* In Java, classes can also have protection levels to help ensure
  encapsulation.

Question: What's the difference between encapsulation and abstraction.

* Both talk about a separate of responsibilities (what vs how).
* Encapsulation is one of the techniques by which we achieve abstraction.
     * Encapsulation involves grouping and adding protection.

Clarify with example.

Suppose we've designed a list struct in C.

```C
// listlib.c
struct list {
  struct node* head;
  struct node* tail;
  int len;
}
// listlib.h
int length(struct list *lst);
// Client code
struct list *students;
...
if (length(students) < 5) {
  cancelClass();
}
```

* Abstraction: client knows methods, doesn't need to know implementation.
* Challenge: client *can* know implementation.  Can write
  `if (students->length < 5) { ... };`
* Encapsulation: Prevents clients from doing the things they shouldn't.
  (Technical/language means of achieving abstraction.)

Let's look at Java's BigInteger class.

An exercise
-----------

Suppose we want to design a class to represent Fractions (rationals).

* What design decisions do we need to make?
* What fields will we want?
* What constructors will we want? (How do we build new fractions?)
* What methods do we want?

Design choices we may have to make

```
public class Fraction {
// +------------------+----------------------------------------------
// | Design Decisions |
// +------------------+

/*

When do we detect "invalid" fractions?

* Constructors should fail when asked to create an invalid fraction. <-
* Let the client deal with issues.  Pretend it's okay.

Do we store them in simplified state?

* If someone creates 2/4, do we store that as 1/2 or 2/4?  If they
  ask for the numerator, what do they get?

How do we deal with signs?

* Associated with the numerator.  `new Fraction(1,-2)` => -1/2.

Mutable or immutable?

* Mutable objects can be changed.  Vectors in Racket.  
* Immutable objects cannot be changed.  Lists in Racket.
* Mutable objects are (usually) more efficient; immutable objects are
  (usually) easier to reason about.

What accuracy do we want? (may influence types for fields, 
methods we write, etc.)

* Shorts, longs, ints, BigIntegers, ...

*/

// +--------+--------------------------------------------------------
// | Fields |
// +--------+

BigInteger numerator;

BigInteger denominator;

// +--------------+--------------------------------------------------
// | Constructors |
// +--------------+

public Fraction(int num, int denom) throws MathException {
  this.numerator = new BigInteger(num);
  this.denominator = new BigInteger(denom):
}

// Create the fraction whose numerator is num and denominator is 1.
public Fraction(int num) {
  ...
} // Fraction(int)

public Fraction(BigInteger num, BigInteger denom) throws MathException {
  this.numerator = num;
  this.denominator = denom;
}

// Create a fraction close to d
public Fraction(double d) {
  ...
} // Fraction(double)

// Parse a string to create a fraction
//  e.g., new Fraction("123125612312321/12312380390234");
public Fraction(String description) throws ParseException,MathException {
}
// +---------+-------------------------------------------------------
// | Methods |
// +---------+

public Fraction add(int addend) { ... }

public Fraction add(Fraction other) { ... }

public BigInteger numerator() { ... }

public BigInteger denominator() { ... }

public BigInteger wholepart() { ... }
public Fraction fractional() { ... }

public Fraction reciprocal() throws MathException { ... }

public double toDouble();


} // class Fraction
```

Lab
---

Note: One of the main goals of this lab is to get you used to thinking
in Java object style.  `x.multiply(y)` rather than multiply(

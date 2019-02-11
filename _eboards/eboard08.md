---
title: Eboard 08  Parametric polymorphism
number: 8
section: eboards
held: 2019-02-11
link: true
current: true
---
CSC 207.02 2019S, Class 08:  Parametric polymorphism
====================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
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
* Responses offline: We've written a code of conduct for 151.  Should
  we create one for 207?
* Does anyone know what happened in the commons last night?

### Upcoming work

* [Assignment 3](../assignments/assignment03) due Thursday night.
* Reading for Wednesday:
    * [Exceptions in Java](../readings/exceptions)
* [Lab writeup](../writeups/writeup08): Exercises 1n (or 1j, depending on the version), 2j, and 3e.
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.01 Writeup for Class 8 (Your names)**
    * Please put your code in the body of the message.

### Extra credit

#### Extra credit (Academic/Artistic)

* Any Data Week activity this week.
* HackGC weekend of 15-17 February 2019.
* Kesho Scott Tuesday at 11am in Faulconer.

#### Extra credit (Peer)

* CS Extra, Thursday: Glimmer Teams present on their summer code camps.

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* HIIT training, 10:00 am, Saturday, Dance Studio, Bear (Same Cap.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)
* Any sex week activity this week.  (If you are not comfortable writing
  to me about the particulars, feel free to submit a generic EC report.)
    * Including #MeToo for men (all genders permitted)

#### Extra credit (Misc)

* Lunar New Year Celebration, February 17, 6pm, Harris Gym
* Conference Swim and Dive meet, 15-17 February 2019.  

### Other good things

* 7:30pm Tuesday, JRC 101 Disparities in Pregnancy Outcomes

### Questions

Quiz
----

* Pass quizzes East.

Subtype polymorphism, revisited
-------------------------------

### Some basics

Key idea: If an A is a B, then we can use an A anywhere we expect a B.

In Java terms: If Class A implements interface B, an object of class A
can be used wherever we expect a parameter or variable of interface B.

```java
interface Shuffler {
  void shuffle(Deck d);
}

class Student implements Shuffler {
  void shuffle(Deck d) {
    ...
  }
}

class Gravity implements Shuffler {
  void shuffle(Deck d) {
    ..
  }
}

```

```java
  B thing = new A();
  public static void whatever(B param) { ... }
  whatever(new A());
```

Why have interfaces?

> Because the client code need not know which object is implementing
  the method,

> Interfaces serve as a kind of prototype for a set of functions.

Can a class implement multiple interfaces, or only one?

> A class can implement an arbitrarily large number of interfaces.

> `class Student implements Shuffler, TextBlock` needs `shuffle` and
  `row` and `width`, and `height`.

Do interfaces get separate files?

> Definitely.

`TextBlock tb` means that `tb` references some object that implements
the `TextBlock` interface.  (The Java compiler will do its best
to enforce that rule.)

`dump(PrintWriter pen, TextBlock tb)` means that the procedure takes
as input a PrintWriter and any object that implements the `TextBlock`
interface.

* In type terms, "A is a B" means "A is a subtype of B".  That's
  why the *subtype*.
* *polymorphism* - "Many forms".  When we write polymorphic procedures,
  we've effectively written many forms of the same procedure.  If we
  write a procedure for "things that can add and multiply", we effectively
  have something that works for BigIntegers and BigDecimals and BigFractions
  and anything else we come up with that implements those two methods.

What are static and dynamic binding?

> In statically-typed language, the type is bound at compile time.
  In dynamically-typed languages, the type is bound at runtime.

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

```java
public class NamedInteger {
  String name;
  Integer val;
  static Integer numNamedInts; // A counter
  public NamedInteger(String name, Integer val) {
    this.name = name;
    this.val = val;
    ++numNamedInts;
  } // NamedInteger(String,Integer)

  public String toString() {
    return this.name + ": " + this.val;
  } // toString()
}
```

If we want a `NamedDouble` class, we could use copy/paste/change.

```java
public class NamedDouble {
  String name;
  Double val;
  static Integer numNamedInts;
  public NamedDouble(String name, Double val) {
    this.name = name;
    this.val = val;
    ++numNamedInts;
  } // NamedDouble(String,Double)

  public String toString() {
    return this.name + ": " + this.val;
  } // toString()
}
```

Why is copy/paste/change a bad idea, even though it seems really fast?

* If we change the original code, we now have to change it everywhere.
    * To improve the code.
    * To correct an error.
    * To change the way it behaves because our clients change their minds.
    * To add capabilities.
* Can be time consuming, unless you are a competent programmer.
    * It is possible to write scripts that do the work for you.
* Careless copy/paste/change may do some extra changes.

Java provides generics as a way of dealing with this issue.

```java
public class NamedThing<T> {
  String name;
  T val;
  static Integer numNamed; // A counter
  public NamedThing(String name, T val) {
    this.name = name;
    this.val = val;
    ++numNamedInts;
  } // NamedThing(String,Thing)

  public String toString() {
    return this.name + ": " + this.val;
  } // toString()
}
```

```java
NamedThing<Integer> i = new NamedThing<Integer>("eye", 3);
NamedThing<Double> j = new NamedThing<Double>("dub", 3.3);
NamedThing<Double> k = new NamedThing<Double>("bud", 3.7);
NamedThing<Student> l = new NamedThing<Student>("sam", new Student(...));
```

We have multiple forms, where the type parameter lets us choose between
forms.

**Warning: There are a lot of subtleties with generics in Java.  We'll
discuss them as they become important.**

* Generic arrays are a pain in the neck. (PITA)

Lab
---

```java
  for (int i = 0; i < 10; i++) {
    strings.set(i, "x" + i);
  } // for
```

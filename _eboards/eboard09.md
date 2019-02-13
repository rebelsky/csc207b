---
title: Eboard 09  Pause for Breath
number: 9
section: eboards
held: 2019-02-13
link: true
current: true
---
CSC 207.01 2019S, Class 09:  Pause for Breath
=============================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Memory layout and quiz 3
* Another way to think about memory layout and class variables
* Object methods and quiz 2
* LIROs on quiz 1
* Reversal on quiz 1
* Assignment in generics
* Exceptions
* Everything else

Preliminaries
-------------

### News / Etc.

* Sit where you like.
* I'm returning quizzes at the end of class.
* Our graders tell me that they are working on the homework.

### Upcoming work

* [Assignment 3](../assignments/assignment03) due Thursday night.
* Reading for Friday:
    * [Inheritance](../readings/inheritance)
* No lab writeup.
* Quiz Monday: Polymorphism and inheritance.

### Extra credit

#### Extra credit (Academic/Artistic)

* Any Data Week activity this week.
* HackGC weekend of 15-17 February 2019.
* Convo Thursday at 11 (JRC 101): John Hassard, founding associate director
  of the Institute for Security, Science, and Technology at Imperial
  College London, will present, "Envisioning the Post-Hydrocarbon World"
* CS Extras, Thursday, 4:15 p.m. Science 3821: Summer Code Camps.
  (Snacks at 4pm in the CS Commons.)

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

Memory layout and quiz 3
------------------------

```java
public class Counter {
  public static int num = 0;
  public int val = 0;
  public Counter(int init) { val = init; ++num; } 
  public increment() { val += 1; } 
  public int compareTo(Counter other) { return this.val - other.val; } 
} // class Counter
```

```
public static void copy(Counter a, Counter b) { a = b; } 
public static void experiment01() {
  Counter c1 = new Counter(5);
  Counter c2 = new Counter(7);
  c1.increment();
  int compare = c1.compareTo(c2);
} // experiment01
```

Detour: Another way to think about memory layout
------------------------------------------------

Object methods and quiz 2
-------------------------

```java
/**
 * The polynomal ax^2 + bx + c
 */
public class Quadratic {
  BigInteger a; // Coefficient of quadratic term
  BigInteger b; // Coefficient of linear term
  BigInteger c; // Constant addition
  // ...
  public BigInteger evaluate(BigInteger x) {
    ...
  } // evaluate
} // class Quadratic
```

LIROs on quiz 1
---------------

Reminder: "Last in, random out."

Implement `get`.

```C
struct liro {
  int capacity; // The number of values we can hold
  int size;     // The number of values currently in the collection
  char **values;// An array of strings
};
```

Standard solution ...

Reversal on quiz 1
------------------

```java
public static void rev(int[] vals) {
  int target = 0;
  int source = vals.length - 1;
  while (target < vals.length) {
    vals[target++] = vals[source--];
  } // while
} // rev
```

Assignment in generics
----------------------

We know that we can assign instances of implementing classes to
interfaces.

```java
Integer i = 5;
Number n = i;
```

Suppose we have a `Box<T>` type that provides `T get()` and 
`set(T val)` methods.  Why might each of the following 
be illegal?

Example 1: From specific to generic

```java
Box<Integer> bi = new Box<Integer>(5);
Box<Number> bn = bi;
```

Example 2: From generic to specific

```java
Box<Number> bn = new Box<Integer>(5);
Box<Integer> bi = bn;
```

Exceptions
----------

Everything else
---------------

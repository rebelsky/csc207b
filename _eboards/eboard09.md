---
title: Eboard 09  Pause for Breath
number: 9
section: eboards
held: 2019-02-13
link: true
---
CSC 207.02 2019S, Class 09:  Pause for Breath
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
* Charlie subs on Friday.

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

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* HIIT training, 10:00 am, Saturday, Dance Studio, Bear (Same Cap.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)
* Any sex week activity this week.  (If you are not comfortable writing
  to me about the particulars, feel free to submit a generic EC report.)

#### Extra credit (Misc)

* Conference Swim and Dive meet, 15-17 February 2019.  
* Lunar New Year Celebration, February 17, 6pm, Harris Gym

### Other good things

### Questions

What should happen if we center and then right justify?

> You need not dig into the contents of a block, so if the block is
  centered and then right justified, you'll just get the centered
  thing (depending on the size of the blocks).

        new HCompose(new TextLine("["), new HCompose(new RightJustified(new Centered("Hello", 7), 7), new TextLine("]")))

        [ Hello ]

        new HCompose(new TextLine("["), new HCompose(new RightJustified(new Centered("Hello", 7), 10), new TextLine("]")))

        [    Hello ]

Is the `merge` method likely to be inefficient, say n^2?

> Sure.

Can the `remove` method also be inefficient?

> Sure.

Sam!  Don't forget to link the 7th eboard.

> I'll do my best.

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

What's the difference between `num` and `val`?  `num` is static and
`val` is not.   Static fields are shared between all of the objects
in a class.

What happens if we call `copy(c1,c2)` *before* we call `c1.increment()`?
Nothing!

If we wanted to copy the contents, we'd use 
`public static void cpy(Counter a, Counter b) { a.val = b.val }`

We *can't* change what `c1` refers to in a separate method.

Detour: Another way to think about memory layout
------------------------------------------------

Where in memory are the static fields of a class?  Three models that
might make sense

* A designated space for static fields.
* At the bottom of the stack.
* In a object of type Class.  (Probably the most realistic.)

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
    // Only with primitives: return ((x*x)*a + x*b + c);
    BigInteger tmp1 = a.multiply(x);
    BigInteger tmp2 = tmp1.multiply(x)
    BigInteger tmp3 = b.multiply(x);
    BigInteger tmp4 = tmp2.add(tmp3);
    return tmp4.add(c);
    // Or
    // return a.multiply(x).multiply(x).add(b.multiply(x)).add(c);
  } // evaluate
} // class Quadratic
```

If my polynomial is 3x^2 + 2x +1, evaluate(1) = 6, evaluate(2) = 17.

Unfortunately, `*` and `+` only work with primitive types.  (Well `+`
also works with strings.)  (No, we can't overload them.)

So we  have to use methods, which we learned were called `multiply` and
`add`.

What is "overloading"?  

* In Java, you can have multiple methods with the same name as long as
  they have different parameter types.

        static int square(int x) { return x*x; }
        static double square(double d) { return d*d; }
        static String square(String st) { return "[" + st + "]"; }

* The ability to use the same name in multiple context is called overloading.
* Some languages allow you to overload infix operators.  That can lead to
  horrendously unreadble code.  (or more redadable code)

How does overloading relate to subtype polymorphism?

* In subtype polymorphism, we write one procedure and it works with a
  variety of types.  (E.g., if we knew that lots of types had a multiply
  method, we could write

        static Mult square(Mult x) { return x.multiply(x); }

* In overloading, we have to write separate procedures.

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

```C
char * 
get () 
{
  if (size <= 0) return NULL;
  int index = random() % size;
  char *result = values[index];
  // Clear out the element
  return result;
}
```

How do we clear out the element?

* Shift everything afterwards to the left, then decrease size by 1.
  Unfortunately, that's expensive.

Are there alternatives?

* Make a new array.  That's equally expensive.  No!
* Hire someone else to solve it.  No!
* Use a linked list.  No!  Now it's expensive to find the element.
* Move the last element into that space.  It doesn't matter where
  things are in an array.

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
// Because every integer is a number
```

Suppose we have a `Box<T>` type that provides `T get()` and 
`set(T val)` methods.  Why might each of the following 
be illegal?

Example 1: From specific to generic

```java
Box<Integer> bi = new Box<Integer>(5);
Box<Number> bn = bi;
```

`Box<Integer>` is not a subtype of `Box<Number>`.

Consider the following

```
bn.set(new BigInteger("23"));
```

Now `bi` refers to a box that contains something other than an integer.


Example 2: From generic to specific

```java
Integer i = 5;
Number n = i;
i = n;
```

The Java compiler does not keep track of the values associated with
variables, only their types.  Not all numbers can be assigned to
integers.


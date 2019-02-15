---
title: Eboard 09  Pause for Breath
number: 9
section: eboards
held: 2019-02-13
link: true
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
    * Optional PM on Inheritance
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

* Conference Swim and Dive meet, 15-17 February 2019.  Watch your classmate
  fly!

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

_Can you explain a bit more about part B?_

> Original model: Once you create a text block, it's fixed.

> New model: They are (somewhat) mutable.  We will allow mutation
  of `TextLine` object, replacing its contents with new contents.

> Your goal: Deal with the fallout.

> If we've defined `line` as `new TextLine("Hello");`

> If we've defined `bl` as new `BoxedBlock(line);`. 

        +-----+
        |Hello|
        +-----+

> If we set the contents of `line` to `Hi`, what should happen?

        +--+
        |Hi|
        +--+

> If we set the contents of `line` to `Greetings`, what should happen?

        +---------+
        |Greetings|
        +---------+

> Observation: The width of the BoxedBlock changes.

> What about `TruncatedBlock` objects?  Since we've specified a width
  for the truncated block, it should not change.

> Similarly, `Centered` and `RightJustified` objects maintain their
  width.

> If the underlying block is now too large, we're in trouble.  Throw
  an exception.

How do you determine what type of item you're adding?

        if (item instanceof ManyPackages) {
          ManyPackages mp = (ManyPackages) item;
        }

        try {
          ManyPackages mp = (ManyPackages) item;
          ...
        } catch (ClassCastException cce) {
          ...
        }

Do I have to combine similar items of different weights?

> No.

What's the BulkContainer in the code?

> A relic of an older version of the assignment.  Ignore it.

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

How do `num` and `val` differ?  `val` is the thing we're counting.
`num` is a static field.  That is, each `Counter` object has its
own `val`, but they share the same `num`.

Note: Counter objects all have a `.num` field, which they share.
You can refer to it as `Counter.num` or as `c1.num` or as `c2.num`.

What happens when we do `copy(c1,c2)`  **Nothing** (well, we
copy a reference), but that doesn't affect the underlying objects)

In C, it's like

```C
  void copy(int *a, int *b) {
    a = b;
  }
  void copy2(int *a, int *b) {
    *a = *b;
  }
  int *c1 = ...;
  int *c2 = ...;
  copy(c1,c2)
```

Can I change what `c1` points to in a method call?

> Nope.  To achieve that effect, you need `c1` to be a field that
  you change.

Detour: Another way to think about memory layout
------------------------------------------------

What do we do about static fields?

* Write them in a "somewhere" column.
* Put them on the stack, near the bottom.
* We can have class objects in the heap.  These objects contain
  the implementations of the methods, the static fields, and
  so on and so forth.

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

Incorrect solution: `return a*x*x + b*x + c;` because we can only
use `*` and `+` with primitive types.

Correct solution: `return a.multiply(x).multiply(x).add(b.multiply(x)).add(c);`

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

Our goal: Remove and return a random element.

```
char * get() {
  if (size <= 0) return NULL;
  int pos = random() % size;
  char *result = values[pos];
  // Clear out values[pos];
  return result;
} // get
```

How do we clear out the values?

* Option 1: Move everything to the right left one, decrease size.
  **Inefficient!**.
* Option 2: Put a null in the location and update the code so that
  it generates a new random number if it finds a null.  Can make
  it harder to figure out where to add new stuff.  How do we figure
  out where to look?  If we've decreased size, we won't look at
  the end.  Also inefficient.
* Option 3: Use an implementation other than an array, such as a
  linked list.
* Option 4: We don't care about the order of elements in the array,
  so just grab the last element in the array, put it in the newly
  created space in the array, and decrease the size.  Fast!

Moral: Sometimes data structure design requires us to challenge our
assumptions and look for something faster.

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

What is `rev([1,2,3,4,5])`?

Remember: `x++` means "add 1 to x, but use the old value." 

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
be illegal?  What makes it dangerous?

Example 1: From specific to generic

```java
Box<Integer> bi = new Box<Integer>(5);
Box<Number> bn = bi;
```

Here's the danger ...

```java
bn.set(new BigDecimal("2.3"));
```

`bi` and `bn` reference the same box.  We should be able to assume that
`bi` contains an `Integer`.  But we've just put something other than an
`Integer` in the box.  To prevent situations like that, we disallow
the assignment of `bi` to `bn`.

Example 2: From generic to specific

```java
Box<Number> bn = new Box<Integer>(5);
Box<Integer> bi = bn;
```

The first line is illegal for the reasons mentioned above.  But even
if it were legal, the second line is also illegal because Java is
conservative and only knows that bn is a `Box<Number>`, even though
you know it's a `Box<Integer>`.

```
Number n = new Integer(5);
Integer i = n;
// error: incompatible types: Number cannot be converted to Integer
```

Java is conservative.  Because you've said it's a `Number`, it doesn't
look any further, and not all Numbers are Integers.

Exceptions
----------

Why don't we just rely on "check if an error happened" rather than
using Exceptions?

A typical C programmer, when told to read an integer, will write
something like

```C
int i;
scanf("%d", &i);
```

Where's the code to deal with the potential failure of `scanf`?  Almost
no programmers write it.

```C
int i;
int sval = scanf("%d", &i);
while (0 == sval) {
  printf("You idiot, that's not an integer.  Try again.\n");
  getchar();
  sval = scanf("%d", &i);
} // while
```

Note that that's approximate.

Rather than letting this happen, Java forces the client programmer
to acknowledge the error and indicate what to do when an error
happens.

What happens? 

* Pass on the exception.
* Catch the exception and figure out how to resolve the issue.

Remember: Java is your nanny!
Everything else
---------------

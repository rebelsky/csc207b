---
title: Arrays in Java
summary: |
  We consider one of the central building blocks for data structures: 
  the array.  Arrays group values and permit fast access by numeric index.  
prepreqs: Basics of Java.  For loops.
---
Background: Grouping data
-------------------------

As you build larger and larger programs, you will soon find that
you have many situations in which you need to group large amounts
of similar data.  For example, in a grading program, you may need
to group students and, for each student, you may need to group
grades.  Because grouping data is at the center of so many programs,
computer scientists study different ways to group data.

As you may have seen in the past, there are two initial approaches
one can take.  One can describe *abstract data types* (ADTs), which
specify the *what* of data.   That is, ADTs describe the overall
philosophy of the collection of data and the primary methods that
client algorithms and programs use to access the data.  One can
also focus on *data structures*, which specify the *how* of data.
That is, data structures provide the implementation for the methods
that ADTs describe.

Arrays play an odd role in this classification.  From one perspective,
we can think of them as ADTs, in that the design of arrays is guided by
a central philosophy, and programmers usually rely on them to provide
certain operations.  However, from another perspective, arrays are
implemented in a particular way, and many programmers take advantage
of that implementation as they build other data structures.  We will
consider both aspects.

Array basics
------------

We begin with the central philosophy or purpose of arrays: Arrays
group similar kinds of values and allow clients to access those
values by number.  If we want to use specialized terminology, we say
that arrays are *homogeneous collections of values that are indexed by
non-negative integers*.

What operations do we expect to have for such a collection?

* We should be able to find out determine how many elements are 
  in the collection.
* We should be able to find out what value is associated with a 
  particular index.
* We should be able to change what value is associated with a 
  particular index.
* We should be able to create a new collection of a specified size 
  (and, perhaps, with a specified range of indices).

Because arrays are both simple and central, we also expect most of
these operations to be comparatively quick (and, except for the case
of creating a collection, to take an amount of time or computation
independent of the number of values in the array).

To provide fast access to elements, most languages store arrays as
a contiguous area of memory.  If the array starts at memory location
*l*, and each value in the array takes space *s*, and the indices
start at 0, then the value in position *i* can be found at memory
location *l*+*is*.

Syntax of arrays in Java
------------------------

In Java, arrays have a special syntax.  In order to use arrays, you
will need to mamster this syntax (and master it fairly quickly).

The *type* of an array is specified by giving the type of the
elements followed by an open square bracket and a close square
bracket.  For example, the type of an array of integers is `int[]`
and the type of an array of exceptions is `Exception[]`.

We most typically *construct* an array with the `new` operator,
followed by the type of each element, an open bracket, an integer
that gives the size of the array, and a close bracket.  To create
an array of five integers called `ratings`, we would write

```java
    int[] ratings = new int[5];
```

Similarly, to create an array of ten objects in class `Student`,
we would write

```java
    Student[] class = new Student[10];
```

  We can also *initialize* an array during construction
  by eliding the array size and following the right bracket with a
  sequence of values separated by commas and surrounded by curly braces.
  For example, to create an array of the names Jane, Jack, Julie, and
  John, we would write

```java
    String[] names = new String[] { "Jane", "Jack", "Julie", "John" };
```

Similarly, to create an array of the ratings 5, 4, 3, 4, and 2,
we might write

```java
    int[] ratings = new int[] { 5, 4, 3, 4, 2 };
```

When writing these array initializers, we might also construct
objects within the braces.  For example,

```java
    Fraction[] thirds = new Fraction[] { new Fraction ("1/3"),
                                         new Fraction ("2/3"),
                                         new Fraction ("3/3"),
                                         new Fraction ("4/3") };
```

To *obtain the number of values* in an array, we use the name of
the array, followed by a period, followed by the keyword `length`.
For example,

```java
    pen.println ("There are " + students.length + " students.");
```

To *get* a particular value in the array, we use the name of the
array, followed by an open bracket, followed by an expression that
gives the index, followed by a close bracket.  For example, here
is some simple code to print all the fractions in the array of
fractions declared above.

```java
  for (int i = 0; i < thirds.length; i++) {
    pen.println(thirds[i]);
  } // for
```

Similarly, to *set* a particular value in the array, we assign to
the name of the array, followed by an open bracket, followed by an
expression that gives the index, followed by a close bracket.

For example, here is some code that doubles all the ratings in an array.

```java 
for (int i = 0; i < ratings.length; i++) {
  ratings[i] = ratings[i] * 2;
} // for 
```

The design of arrays
--------------------

Let's return to the original philosophy of arrays: *an array is an
indexed collection of homogenous values*.  Do the arrays we've just
looked at meet that goal?  Yes, it seems they do.  But Java arrays
also add one more implicit characteristic: Java arrays are *fixed
sized* indexed collections of homogenous values.

Do arrays have to have a fixed size?  No.  One
can design and implement arrays that expand dynamically when
necessary.  Instead of asking about the length of such arrays,
you would probably ask about the current capacity of the array
or what the largest indexed used is (or something similar).  You
might also need to consider when the array expands.  Does it expand
automatically when a client requests a value outside of the bounds,
or is there an explicit command to expand the array?

In Java, the standard arrays have a fixed size.  However, there are
a variety of container types (e.g., [`java.util.Vector`]({{
site.java_api }}/java/util/Vector.html) and [`java.util.ArrayList`]({{
site.java_api }}/java/util/ArrayList.html) that provide dynamic
arrays.  Feel free to read the Javadoc for those classes to learn
more about them.


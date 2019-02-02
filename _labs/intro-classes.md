---
title: Designing your own classes
summary: |
  In this laboratory, you will extend your knowledge of numeric
  values in Java.  You'll also explore the creation of classes
  in Java.
repo: <https://github.com/Grinnell-CSC207/intro-classes-2019>
javadoc:
  - "[`java.lang.Integer`]({{ site.java_api }}/java/lang/Integer.html)"
  - "[`java.lang.String`]({{ site.java_api }}/java/lang/String.html)"
  - "[`java.math.BigInteger`]({{ site.java_api }}/java/math/BigInteger.html)"
current: true
todo: 
  - Add a few examples in which they learn about static access to
    fields (or fail to access those fields).  Maybe something as
    simple as giving them a procedure.
current: true
---

Preparation
-----------

a. Fork and clone the repository.

b. Import your clone into Eclipse.

* Select **File** > **Import...**
* Click on **General** and then **Existing Projects into Workspace**
* Click **Next**
* Under "Select root directory", enter the path to your clone of the
  repository on the local filesystem.
* Click **Finish**

c. Read through the code to make sure that you understand what it does.
You don't need to know all the details, but a big-picture view is  helpful.

d. Bring up the documentation for the three standard classes we'll be
using (links above).

Exercises
---------

### Exercise 1: Multiplication

a. Extend the `Fraction` class so that it permits multiplication
of two fractions.  That is, you should add an appropriate `multiply`
method to the class.

b. Write an experiment that allows you to explore the behavior of
the new method.  (The experiment is simply code in `FractionExpt.java`
that shows what happens when you multiply two numbers.)

### Exercise 2: Fractional portions

As you may know, we can represent every non-negative rational number as
a whole number plus a fractional value no smaller than 0 and 
strictly less than 1.

a. Write a method of the `Fraction` class, `fractional`, that
identifies and returns this fractional value as a `Fraction`.  Your
procedure need only work for positive numbers.  

Here are some examples that illustrate what it's supposed to do.

```java
  Fraction f;

  f = new Fraction(11,3);
  pen.println(f.fractional());  // 2/3

  f = new Fraction(1,2);
  pen.println(f.fractional());  // 1/2

  f = new Fraction (5,2);
  pen.println(f.fractional());  // 1/2

  f = new Fraction(4,2);
  pen.println(f.fractional());  // 0/2 or 0
```

b. Check/test your procedure and correct any errors.

### Exercise 3: From string to fraction

Write and check/test a third constructor for the `Fraction` class.  This
constructor should accept a string as a parameter, parse that string,
and generate the appropriate fraction.  For example,

```java
  Fraction f;
  f = new Fraction("1/4");
  pen.println(f.numerator());   // 1
  pen.println(f.denominator()); // 4
  f = new Fraction("11/5");
  pen.println(f.numerator());   // 11
  pen.println(f.denominator()); // 5
  f = new Fraction("120/3");
  pen.println(f.toReal());      // Approximately 40
```

You can expect that the string will have two positive integers
separated by a slash.  You may find it useful to reflect on the
`indexOf` method of the `java.lang.String` class and on various
static methods of the `java.lang.Integer` class.

### Exercise 4: A counter class

Write and experiment with a class, `Counter`, that generates objects
that can count.  Objects in class `Counter` should provide two
methods: `increment`, which adds 1 to the counter, and `get`, which
gets the current value of the counter.  Your class needs one zeroary
constructor which initializes the counter to 0.

Make sure to verify that if you create two separate 
`Counter` objects, you can change the two objects separately.

### Exercise 5: Printing counters

a. If you've included a `toString` method in `Counter`, comment it
out.

b. What do you expect to happen if we print out a `Counter` using
instructions like the following?

```
  PrintWriter pen = new PrintWriter(System.out, true);
  Counter c1 = new Counter();
  Counter c2 = new Counter();
  c1.increment();
  c1.increment();
  c2.increment();
  pen.println("c1: " + c1);
  pen.println("c2: " + c2);
```

c. Check your answer experimentally.

d. As you should have discovered, when a class lacks a `toString`
method, Java chooses a fairly naive representation for printing
objects in that class.  Add an appropriate `toString` method (e.g.,
one that returns the counter surrounded by angle brackets).  Then
verify that the lines above work as you expect.

### Exercise 6: Enhancing counters

a. Update your `Counter` class to include a second constructor that
allows the user to specify a starting value.

b. Update your `Counter` class to include a `reset` method that
reset the counter to the starting value.

c. Test (or experiment with) both updates to ensure that they work
appropriately.

For those with extra time
-------------------------

### Extra 1: Simplifying fractions

Update the fraction class so that we simplify each fraction when
we create it.  In case you've forgotten, the simplified version of
a fraction has a numerator and denominator with a greatest common
divisor of 1; you can create the simplified version by finding the
gcd and dividing both numerator and denominator by that gcd.

### Extra 2: Further enhancing counters

Identify other methods that would be useful to include in the
`Counter` class and add them.

### Extra 3: Further enhancing fractions

Identify other methods that would be useful to include in the
`Fraction` class and add them.


---
title: Java Generics
repo: <https://github.com/Grinnell-CSC207/lab-generics-2019>
summary: |
  We explore some basic use of generic values in Java.
prereqs: |
  Classes and interfaces.
current: true
---

Preparation
-----------

a. Fork and clone the repository.

b. Import the repository into Eclipse.

Exercises
---------

### Exercise 1: Simple expandable arrays

The [reading on generics](../readings/generics.html)
shows how we build a generic "expandable array" class.
You'll find that generic class in the repository for this lab.

a. Read through `SEAExpt.java` and predict what the output
will be.

b. Compile and run `SEAExpt.java` to see what the output is.

c. `SimpleExpandableArray` is supposed to expand the array when
you set a value larger than the size.  Do you expect it to do
so for this example?

d.  Within the portion of the `set` method of `SimpleExandableArray`
that expands the array, add a call to `System.err.println` so that
you can tell when the array expands.

e. Create an expandable array of strings, assign some values to it,
and print them out.  Here's a start.

```java
  ExpandableArray<String> strings = new SimpleExpandableArray<String>();
  ...
  for (int i = 0; i < 10; i++) {
    pen.println("strings[" + i + "] = " + strings.get(i));
  } // for
```

f. What do you expect to happen if you assign a string to an element
of `numbers` or a number to an element of strings??

g. Check your answer experimentally.

h. What do you expect to happen if we leave out the type when we
construct `numbers`, as in the following?

```java
  ExpandableArray<BigInteger> numbers = new SimpleExpandableArray();
```

i. Check your answer experimentally.

j. What do you expect to happen if we leave out the type when we
declare `strings`, as in the following?

```
  ExpandableArray strings = new SimpleExpandableArray<String>();
```

k. Check your answer experimentally.

l. What do you expect to happen if we leave out the type on both
sides of the declaration, as in the following?

```
  ExpandableArray strings = new SimpleExpandableArray();
```

m. Check your answer experimentally.

n. Summarize what you've learned in these exercises.

### Exercise 2: Searching

The [reading on generics](../readings/generics.html) shows how we
build a generic search method.  You'll find that code in the
repository.

a. Read through `SearchExpt.java` and predict what the output
will be.

b. Compile and run `SearchExpt.java` to see what the output is.

d. What do you expect to happen if you try to search `strings`
with `OddInteger.PRED` or `numbers` with `SmallString.PRED`?

e. Check your answer experimentally.

f. What do you expect to happen if we try to generalize the
declaration of `strings`, as in the following?

```java
    Object[] strings = new Object[] { ... };
```

g. Check your answer experimentally.

h. Create a new `SmallObject` predicate whose `hold` method takes
an object as a parameter, converts it to a string, and sees if that
string has fewer than five characters.  Do you expect that new
predicate to work with the updated `strings`?

i. Check your answer experimentally.

j. Summarize what you've learned in these exercises.

### Exercise 3: Predicates

a. What do you expect to happen if we restore the original
declaration of `strings` and use the new comparator we just 
developed?

```java
    String[] strings = new String[] { ... };
    pen.println("A small string: " + SearchUtils.search(strings, SmallObject.PRED));
```

b. Check your answer experimentally.

c. What do you expect to happen if we use the new `SmallObject.PRED`
predicate to search `numbers`?

```java
    pen.println("A small integer: " + SearchUtils.search(numbers, SmallObject.PRED));
```

d. Check your answer experimentally.

e. Summarize what you've learned in this exercise.

### Exercise 4: Boxes

As you've noted, when two variables refer to the same mutable object,
we can change the object through one variable and see the effect 
through the other variable.  At times, that effect is undesirable, but
at others it is desirable.

Unfortunately, if the two variables refer to the same immutable object,
such as a string, we can't propagate the change to one variable to the
other variable.  The typical solution to this problem is to have
what is typically called a "box".  You can set the value
in a box or get the value in a box.

```java
  Box<String> s1 = new Box<String>("Hello");
  Box<String> s2 = s1;
  pen.println(s1.get());        // Prints "Hello"
  pen.println(s2.get());        // Prints "Hello"
  s1.set("Goodbye");
  pen.println(s1.get());        // Prints "Goodbye"
  pen.println(s2.get());        // Prints "Goodbye"
  s2.set("Whatever");
  pen.println(s1.get());        // Prints "Whatever"
  pen.println(s2.get());        // Prints "Whatever"

  Box<Integer> i1 = new Box<Integer>(42);
  Box<Integer> i2 = i1;
  pen.println(i2.get() + 3);          // Prints 45
  i2.set(21);
  pen.println(i1.get() * 2);          // Prints 42
```

Implement `Box` using generics.  You should include a
constructor that sets the initial value; a mutator, `set`,
that changes the value; and an observer, `get`, that extracts
the value.

For those with extra time
-------------------------

Finish the following alternate implementation of
`ExpandableArray`

```java
public class VectorBasedExpandableArray {
  Vector<T> values;
  ...
} // class VectorBasedExpandableArray
```


---
title: Java Generics
repo: <https://github.com/Grinnell-CSC207/lab-generics>
summary: |
  We explore some basic use of generic values in Java.
prereqs: |
  Classes and interfaces.
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

  c. Create an expandable array of strings, assign some values to it,
  and print them out.  Here's a start.

<pre>
  ExpandableArray&lt;String&gt; strings =
      new SimpleExpandableArray&lt;String&gt;();
  ...
  for (int i = 0; i &lt; 10; i++)
    {
      pen.println("strings[" + i + "] = " + strings.get(i));
    } // for
</pre>

  d. What do you expect to happen if you assign a string to an element
  of `numbers` or a number to an element of strings??

  e. Check your answer experimentally.

  f. What do you expect to happen if we leave out the type when we
  construct `numbers`, as in the following?

<pre>
  ExpandableArray&lt;BigInteger&gt; numbers =
      new SimpleExpandableArray();
</pre>

  g. Check your answer experimentally.

  h. What do you expect to happen if we leave out the type when we
  declare `strings`, as in the following?

<pre>
  ExpandableArray strings =
      new SimpleExpandableArray();
</pre>

  i. Check your answer experimentally.

  j. Summarize what you've learned in these exercises.

### Exercise 2: Searching

  The [reading on generics](../readings/generics.html)
  shows how we build a generic search method.  You'll find that code in
  the repository.

  a. Read through `SearchExpt.java` and predict what the output
  will be.

  b. Compile and run `SearchExpt.java` to see what the output is.

  d. What do you expect to happen if you try to search `strings`
  with `odd` or `numbers` with `small`?

  e. Check your answer experimentally.

  f. What do you expect to happen if we try to generalize the
  declaration of `strings`, as in the following?

<pre>
    Object[] strings = new Object[] { ... };
</pre>

  g. Check your answer experimentally.

  h. Revise the `short` predicate so that it takes an
  object as a parameter, converts it to a string, and sees if it
  has fewer than five characters.  Do you expect that new predicate
  to work with the updated `strings`?

  i. Check your answer experimentally.

  j. Summarize what you've learned in these exercises.

### Exercise 3: Predicates

  a. What do you expect to happen if we restore the original
  declaration of `strings` and use the new version of
  `small`?

<pre>
    String[] strings = new String[] { ... };
    ...
    Predicate&lt;Object&gt; small = 
      new Predicate&lt;Object&gt;()
        {
          @Override
          public boolean holds(Object val)
          {
            return (val.toString().length() &lt; 5);
          } // holds(Object)
        }; // new Predicate&lt;Object&gt;
    ...
    pen.println("A small string: " + SearchUtils.search(strings, small));
</pre>

  b. Check your answer experimentally.

  c. What do you expect to happen if we use the new `small`
  predicate to search `numbers`?

<pre>
    pen.println("A small integer: " + SearchUtils.search(numbers, small));
</pre>

  d. Check your answer experimentally.

  e. Summarize what you've learned in this exercise.

### Exercise 4: A Box

  As you've noted, when two variables refer to the same mutable object,
  we can change the object through one variable and see the effect 
  through the other variable.  At times, that effect is undesirable, but
  at others it is desirable.

  Unfortunately, if the two variables refer to the same immutable object,
  such as a string, we can't propagate the change to one variable to the
  other variable.  The typical solution to this problem is to have
  what is typically called a "box".  You can set the value
  in a box or get the value in a box.

<pre>
  Box&lt;String&gt; s1 = new Box&lt;String&gt;("Hello");
  Box&lt;String&gt; s2 = s1;
  pen.println(s1.get());        // Prints "Hello"
  pen.println(s2.get());        // Prints "Hello"
  s1.set("Goodbye");
  pen.println(s1.get());        // Prints "Goodbye"
  pen.println(s2.get());        // Prints "Goodbye"
  s2.set("Whatever");
  pen.println(s1.get());        // Prints "Whatever"
  pen.println(s2.get());        // Prints "Whatever"

  Box&lt;Integer&gt; i1 = new Box&lt;Integer&gt;(42);
  Box&lt;Integer&gt; i2 = i1;
  pen.println(i2.get() + 3);          // Prints 45
  i2.set(21);
  pen.println(i1.get() * 2);          // Prints 42
</pre>

  Implement `Box` using generics.  You should include a
  constructor that sets the initial value; a mutator, `set`,
  that changes the value; and an observer, `get`, that extracts
  the value.

For Those With Extra Time
-------------------------

  Finish the following alternate implementation of
  `ExpandableArray`

<pre>
public class VectorBasedExpandableArray
{
  Vector&lt;T&gt; values;
  ...
} // class VectorBasedExpandableArray
</pre>


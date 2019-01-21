---
title: Implementing Lists with Arrays
repo: <https://github.com/Grinnell-CSC207/lab-array-based-lists>.
summary: |
  We extend our understanding of a list ADT by considering an
  implementation of lists in which the values are stored in an
  array.
prereqs: |
  Arrays.
  [Interfaces](../readings/interfaces).
  [Generics](../readings/generics).
  [Anonymous inner classes](../readings/anonymous-inner-classes).
javadoc:
  - "[`java.util.Iterator`]({{ site.java_api }}/java/util/Iterator.html)"
  - "[`java.util.ListIterator`]({{ site.java_api }}/java/util/ListIterator.html)"
---
Preparation
-----------

a. Fork and clone the repository at 

b. In separate windows or tabs, open the documentation for
[`Iterator`]({{ site.java_api }}/java/util/Iterator.html) and
[`ListIterator`]({{ site.java_api }}/java/util/ListIterator.html).

Exercises
---------

### Exercise 1: Understanding Iterators

Skim through the documentation for
[`Iterator`](http://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html) and
[`ListIterator`](http://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html).  You should identify the primary
methods and their meanings and any other subtleties.  Make sure that
you can answer the following questions.

a. Where, conceptually, is an iterator relative to the elements
of a list?

b. What element does `remove` remove?

c. Suppose we have a list iterator, `lit`, and call `lit.add(x)`
and then immediately after call `lit.add(y)`.  In what order will
`x` and `y` appear in the list?

d. Suppose we have a list iterator, `lit`, between the
a and b in the list [a,b,c].  Suppose we then call
`lit.remove()` and then immediately after call
`lit.remove()` again.  What can or should happen?

e. Can we add an element immediately after creating a new list
iterator?

f. Can we remove an element immediately after creating a new list
iterator?

g. Can we set an element immediately after creating a new list
iterator?

h. Suppose we've created two list iterators, `lit1` and `lit2` for
the list [a,b,c], and both are between the a and the b in the list.
If we call `lit1.add(d)`, what should `lit2.next()` return?

i. Suppose we've created two list iterators, `lit1` and `lit2` for
the list [a,b,c], and both are between the a and the b in the list.
If we call `lit1.remove()`, what should `lit2.next()` return?

j. Are there any other subtleties that you noticed about these
two kinds of iterators?

### Exercise 2: A simple experiment

  Read through the code of `SimpleListExpt.java` and
  `SALExpt.java`.

  a. Sketch the output you expect to see from `SALExpt`.

  b. Check your sketch experimentally.

### Exercise 3: Implementation Details

  How are array-based-lists implemented?  It's time to look.  In the file
  `SimpleArrayList.java`, you'll see that we have a field,
  `values`, that stores the values in a list and a field,
  `size`, that stores the size of the list.

  You may also note that because iterators will need to access fields of 
  the list, iterators are implemented as an anonymous inner class.  
  Each iterator for the list has a field, `pos`,
  that stores the position of the value to be returned by the next call
  to `next()`.

  a. Sketch how you would implement the ``next``
  method.

  b. Compare your answer to that in the code.  You can ignore the
  call to `failFast`.  We'll come back to that
  in another exercise.

  c. Sketch how you would implement the ``hasNext`()`
  method.

  d. Compare your answer to the answer in the code.

  e. Sketch how you would implement the ``add`(T val)` method.

  f. Compare your answer to the answer in the code.

### Exercise 4: Implement `set`

  You'll note that the `set` method is not yet
  implemented.  

  a. Write a small experiment that will let you check if 
  `set` works.  For example, you might create
  a list of five values and set values 0, 2, and 4 to other values.
  Don't bother checking the extreme edge cases, such as what happens
  when there has not been a prior call to `next`
  or `prev`.  

  b. Here's a simple strategy for implementing `set`.
  Since `pos` represents the location of the value to be
  returned by `next`, `pos-1` represents the location 
  of the value that was last returned.  Hence, all `set`
  needs to do is set the value in the array at that location.

  What flaws, if any, do you see in this strategy?

  c. Implement `set` using this strategy and,
  through your experiment, determine whether or not it seems to work.

  d. If we use this strategy, one time that `set`
  will fail is when `pos` is 0.  Update your program so that
  it throws an exception in such cases.

  e. As you may have noted, a possible flaw in this implementation is that,
  while it works when we move forward with `next`,
  it will likely fail when we use `prev`.  Sketch
  a strateguy for dealing with this problem.

### Exercise 5: Failing Fast

  You may recall that in exercise 1, we asked what happens when we
  mutate a list using one iterator and then try to access it using another
  iterator for the same list.  You probably found that the documentation
  for iterators is vague on this issue.  You should have also noted that
  the vagueness is problematic.  So, what should we do?

  Let's see what the designers of Java did by looking at the standard
  [`java.util.ArrayList`](http://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html) class.

>   The iterators returned by this class's iterator and listIterator
  methods are fail-fast: if the list is structurally modified at
  any time after the iterator is created, in any way except through
  the iterator's own `remove` or
  `add` methods, the iterator will throw a
  `ConcurrentModificationException`. Thus,
  in the face of concurrent modification, the iterator fails quickly
  and cleanly, rather than risking arbitrary, non-deterministic
  behavior at an undetermined time in the future.

  a. If you look at `SimpleListExpt.java`, you'll see a 
  method called `failFastExpt`.  Explain to
  your partner how this allows us to understand fast failure.

  b. Add the following line to the `main` method
  of `SALExpt.java` so that we can see if our simple array-based
  lists fail fast, at least in a simple situation.  (After adding the line,
  you should recompile and run `SALExpt`.

<pre>
    SimpleListExpt.failFastExpt(pen, new SimpleArrayList&lt;String&gt;());
</pre>

  c. Suppose you were called upon to implement the "fail-fast"
  policy.  How would you achieve that goal?

  d. Read through the code for `SimpleArrayList.java` to see
  how it achieves the "fail-fast" policy.

### Exercise 6: Moving Backwards

  You'll note that the `previous` method is not
  implemented.  Implement it.

For Those With Extra Time
-------------------------

  Once we implement `previous`, we are likely to
  break the `set` method we defined earlier.  Fix
  that method.


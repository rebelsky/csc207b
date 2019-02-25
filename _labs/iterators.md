---
title: Iterators
repo: <https://github.com/Grinnell-CSC207/linear-structures>
summary: |
  We explore mechanisms for constructing iterators in Java.  We
  also touch a bit on anonymous inner classes.
javadoc:
  - "[`java.util.Comparator`]({{ site.java_api }}/java/util/Comparator.html)"
  - "[`java.util.PriorityQueue`]({{ site.java_api }}/java/util/PriorityQueue.html)"
  - "[`java.util.Iterator`]({{ site.java_api }}/java/util/Iterator.html)"
  - "[`java.util.Iteratable`]({{ site.java_api }}/java/util/Iteratable.html)"
current: true
---

Preparation
-----------

If you have not done so already, fork and clone the repo.

Exercises
---------

### Exercise 1: Anonymous comparators

Write a new class with a `main` method that creates a priority queue
of strings that is ordered by string length, adds a few strings,
and then remove sthe strings.  

You may use either `java.util.PriorityQueue` or `BuiltinPriorityQueue`
for your priority queue.

You should use an anonymous inner class to build the comparator for the
priority queue.  If your `compare` method is presented with two 
equal-length strings, it should compare them alphabetically.

### Exercise 2: Iterating queues

As you may recall, the `LinkedQueue` class has two fields,
`front` and `back`, each of which reference a node.

a. Sketch (that is, make notes on but do not write the Java code for)
an iterator for the `LinkedQueue` class.

b. Compare your answer to the iterator presented in `LinkedQueue.java`.

### Exercise 3: Iterating queues, revisited

You may have noted that the `ArrayBasedQueue` class lacks an iterator.
Write one.  Your iterator should present the elements starting and
the front of the queue and ending at the back.

### Exercise 4: Implementing `remove` in array-based queues

You may have noted that `java.util.Iterator` provides a `remove` method.
Implement that method for your iterator for `ArrayBasedQueue`.

### Exercise 5: Implementing `remove` in linked queues

Implement `remove` for `LinkedQueue`.  Make sure that your code
will work correctly if we remove the first or last element in the
queue.

### Exercise 6: Anonymous iterators

Convert your iterator for array-based queues to an anonymous inner
class.

For those with extra time
-------------------------

It seems unlikely that you will have extra time.  If you do, you
may leave early.

Acknowledgements
----------------

This lab was newly written in spring 2019.  (Can you tell?)

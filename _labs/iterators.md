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

### Exercise 2: Iterating array-based structures

As you may recall, the `ArrayBasedStack` class has two fields, an
array called `values` and an integer called `size`.

a. Sketch (that is, make notes on but do not write the Java code for)
an iterator for the `ArrayBasedStack` class.

b. Compare your answer to the iterator presented in `ArrayBasedStack.java`.

### Exercise 3: Iterating linked structures

As you may recall, the `LinkedQueue` class has two fields,
`front` and `back`, each of which reference a node.

a. Sketch (that is, make notes on but do not write the Java code for)
an iterator for the `LinkedQueue` class.

b. Compare your answer to the iterator presented in `LinkedQueue.java`.

### Exercise 4: Iterating array-based queues

You may have noted that the `ArrayBasedQueue` class lacks an iterator.
Write one.  Your iterator should present the elements starting and
the front of the queue and ending at the back.

Your code will likely look something like the following.

```java
public ArrayBasedQueue<T> implements ... {
  // ...
  Iterator<T> iterator() {
    return new ArrayBasedQueueIterator<T>(this);
  } // iterator()
  // ...
} // ArrayBasedQueue<T>

public ArrayBasedQueueIterator<T> implements Iterator<T> {
  ArrayBasedQueue<T> abq;
  int i;

  public ArrayBasedQueueIterator(ArrayBasedQueue<T> abq) {
    this.abq = abq;
    this.i = 0;
  }

  // ... this.abq.values[this.pos] ...
}
```

### Exercise 5: Implementing `remove` in array-based queues

You may have noted that `java.util.Iterator` provides a `remove` method.
Implement that method for your iterator for `ArrayBasedQueue`.

### Exercise 6: Anonymous iterators

Convert your iterator for array-based queues to an anonymous inner
class.  You should now be able to do without the field that refers
back to the associated array-based queue.

For those with extra time
-------------------------

It seems unlikely that you will have extra time.  If you do, you
may leave early.

Acknowledgements
----------------

This lab was newly written in spring 2019.  (Can you tell?)

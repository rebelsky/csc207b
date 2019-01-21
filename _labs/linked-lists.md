---
title: Implementing lists with linked nodes
repo: <https://github.com/Grinnell-CSC207/lab-linked-lists>
summary: |
  We extend our understanding of a list ADT by considering an
  implementation of lists in which the values are stored in nodes
  that are linked together.
prereqs: |
  References in Java. 
  [Interfaces](../readings/interfaces).
  [Generics](../readings/generics).  
  [Anonymous inner classes](../readings/anonymous-inner-classes).
javadoc:
  - "[`java.util.Iterator`]({{ site.java_api }}/java/util/Iterator.html)"
  - "[`java.util.ListIterator`]({{ site.java_api }}/java/util/ListIterator.html)"
---
Preparation
-----------

a. Fork and clone the repository, then load it into Eclipse.

b. In a separate window or tab, open the documentation for `Iterator`
   and `ListIterator`.

c. Review your notes on iterators and list iterators.

Exercises
---------

### Exercise 1: A Simple Experiment

  Read through the code of `SimpleListExpt.java` and
  `SLLExpt.java`.

  a. Sketch the output you expect to see from `SLLExpt`.

  b. Check your sketch experimentally.

### Exercise 2: Implementation Details

  How are linked lists implemented?  It's time to look.  In the file
  `SimpleLinkedList.java`, you'll see that we have a field,
  `front`, that stores a dummy node that precedes the front
  of the list.  Each node contains two fields, `data`, which
  refers to the value in the node, and `next`, which refers
  to the next node (or to `null` at the end of the list).
  Nodes are implemented as named inner classes.  

  You may also note that because iterators will need to access fields of 
  the list, iterators are implemented as an anonymous inner class.  
  Each iterator for the list has a field, `cursor`, that
  refers to the node that precedes the node containing the value to
  be returned by `next`, and a field `pos`,
  that stores the index that value would have in an array-based implementation.
  (No, I don't think it's sensible to have such a field.  But the
  list iterator interface expects us to be able to return integer indices.)

  a. Sketch how you would implement the ``next``
  method.

  b. Compare your answer to that in the code.  

  c. Sketch how you would implement the ``hasNext`()`
  method.

  d. Compare your answer to the answer in the code.

  e. Sketch how you would implement the ``add`(T val)` method.

  f. Compare your answer to the answer in the code.

### Exercise 3: Implement `set`

  Here's a simple experiment to test the set method while iterating
  forward through a list.

<pre>
    SimpleLinkedList&lt;String&gt; vm = new SimpleLinkedList&lt;String&gt;();
    SimpleListExpt.add(pen, vm, 
            new String[] { "Hey", "Where", "Did", "We", "Go?" });
    SimpleListExpt.setForwardExpt(pen, vm);
</pre>

  a. Suppose the `set` method is correctly
  implemented.  What should the output of the experiment be?

  b. Look at the current implementation of `set`.
  What do you expect the output to be?

  c. Check your answer experimentally.

  d. You've probably noted that `set` is
  not yet implemented.  Assume that we're only going to iterate
  the list from front to back.  Sketch how you might implement
  `set`.

  e. Here's a simple strategy for implementing `set`,
  assuming that we only iterate lists forward.  Since `cursor`
  refers to the node that immediately precedes the next value, it must
  refer to the node last returned by `next`.  Hence,
  we can implement `set` by setting the
  `data` field of `cursor`.

  Implement `set`, using your approach
  or this approach (or both, if they are the same).

  f. Rerun the experiment to see if the approach works.

### Exercise 4: Moving Backwards

  You'll note that the `previous` method and the
  `hasPrevious` method are not implemented.

  a. Add the following line to your experiments.

<pre>
    SimpleListExpt.prevExpt(pen, new SimpleLinkedList&lt;String&gt;());
</pre>

  b. Read through `SimpleListExpt.java`
  to see how `prevExpt`
  exercises the `previous` and
  `hasPrevious`.

  c. Sketch a strategy for implementing `previous`
  and `hasPrevious`.

  d. Here's one strategy: To find the previous element, start at the front
  of the list and move forward until you're immediately before the cursor.
  You have a previous element if you're not the front of the list.

  What do you think about this strategy?

  e. Implement `previous` and
  `hasPrevious` using one those strategies.

  f. Check whether the methods work by using the experiment from the
  beginning of this problem.

### Exercise 5: Setting Elements

  When we first implemented `set`, we assumed that
  it only had to work if we iterated the list from front to back.  Now
  we can iterate the list in both directions.

  a. Look at `SimpleListExpt.setBackwardsExpt`
  to see one way to exercise `set` while iterating
  the list from back to front.

  b. What do you expect the results of the following experiment to be?

  c. Check your answer experimentally.

<pre>
    SimpleListExpt.addToEnd(pen, vm, 
            new String[] { "Days", "When", "The", "Rain", "Came" });
    SimpleListExpt.setBackwardExpt(pen, vm);
</pre>

  d. If the experiment suggests that `set` is no
  longer implemented correctly, find a way to make it work correctly.

  One strategy is to add a field to the iterator that refers to the last
  node visited.

For Those With Extra Time
-------------------------

  Consider how our lists might change if we included a previous link in
  addition to a next link.


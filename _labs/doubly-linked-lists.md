---
title: Doubly-linked lists and circularly-linked lists
summary: |
  We explore the implications of doubly- and circularly-linked lists.
repo: <https://github.com/Grinnell-CSC207/lab-linked-lists-2019>.
prereqs: |
  [Interfaces](../readings/interfaces).
  [Generics](../readings/generics).
  [Anonymous inner classes](../readings/anonymous-inner-classes).
  [Iterating lists](../readings/list-iterators).
---
Preparation
-----------

a. Fork and clone the repository.

b. Load it into Eclipse.

Exercises
---------

### Exercise 1: Exploring doubly linked nodes

As you may recall, `Node2` is our class for doubly-linked nodes.
Each `Node2` has three fields:

```java
  /**
   * The previous node.
   */
  Node2<T> prev;

  /**
   * The stored value.
   */
  T value;

  /**
   * The next node.
   */
  Node2<T> next;
```

a. Suppose we decided to add a `remove()` method to `Node2`, which is
supposed to remove the current node from its enclosing list.  Sketch
how you would implement that method.  (I'd suggest sketching both the
list and the code.)

b. Suppose we decided to add an `insertAfter(T val)` method to `Node2`, which
is supposed to add a value after the current node, creating a new
node to hold it.  Sketch how you would implement that method.

c. Suppose we decided to add an `insertBefore(T val)` method to `Node2`,
which is supposed to add a value before the current node, creating a
new node to hold it.  Sketch how you would implement that method.

d. Given that we've decided to provide these three methods for the
`Node2` class, are there any similar methods you would add?

e. Compare your answers to a-c to the implementation in `Node2.java`.
What differences, if any, did you see?

### Exercise 2: Iterating doubly-linked lists

As you may recall, we've decided that our lists should provide
two methods, `iterator()`, which returns a normal `Iterator`, and
`listIterator()`, which returns a `ListIterator`.  List iterators
support a variety of operations, including `next`, `previous`,
`hasNext`, `hasPrevious`, `add`, `remove`, `set`, `nextIndex`,
and `previousIndex`.  List iterators are also conceptually
"between" nodes.  

Let's assume that our primary doubly-linked list class has two fields.

```java
  /**
   * The front of the list
   */
  Node2<T> front;

  /**
   * The number of values in the list.
   */
  int size;
```

a. What fields would you expect the list iterator for doubly-linked
lists to have?

b. How would you initialize each of those fields?

c. Compare your answers to those in those in `SimpleDLL.java`.

d. Explain the purpose of the `update` field.

e.  When would you expect to use `update`?  When would you expect
to change `update`?

### Exercise 3: Advancing the cursor

a. Sketch (picture and code) how you would implement the `next` method.

b. Compare your answer to that in `SimpleDLL.java`

### Exercise 4: Adding elements

Note that the "heavy lifting" of adding elements can be handled
by `Node2.insertBefore()` and `Node2.insertAfter()`.  However,
that does not mean that the `add` method is trivial.

a. In the "normal" case, when the cursor is in the middle of a list,
how should the iterator add an element?

b. Other than adding the element, what fields do you need to update
in the "normal" case?

c. What are the special cases you might have to handle?

d. One possible special case is when the cursor is at the back of the
list.  How does that differ from the "normal" case?

e. Another possible special case is when the cursor is at the front
of a nonempty list.  How should we handle that differently than the
"normal" case?

f. Another possible special case is when the list is empty.  How
should we handle that case differently than the other cases?  (Or should
we handle it differently than the previous cases?)

g. What other special cases did you identify and how should you handle
them?

h. Compare your design to that of `add`.

### Exercise 5: Removing elements

Note that the "heavy lifting" of removing elements can be handled
by `Node2.remove()`.  However, that does not mean that the `remove`
method is completely trivial.

a. In the "normal" case, when the cursor is in the middle of a list,
how should the iterator remove an element?

b. Other than removing that element, what fields do you need to update
in the "normal" case?

c. What are the special cases you might have to handle?

d. One possible special case is when we are removing the last element
in the list.  How does that differ from the "normal" case?

e. Another possible special case is when we are removing the front
of a nonempty list.  How should we handle that differently than the
"normal" case?

f. Another possible special case is when we are removing the only
element in a list.  How should we handle that case differently than
the other cases?  (Or should we handle it differently than the
previous cases?)

g. What other special cases did you identify and how should you handle
them?

h. Compare your design to that of `remove`.

### Exercise 6: Experiments

The files `SDLLExpt.java` and `SimpleListExpt.java` contain a collection
of tests for simple doubly-linked lists (or doubly-linked simple lists,
depending on how you think of them).

a. Skim through the two files to identify what the primary tests are.

b. Sketch the output from `SDLLExpt`.  (Note that it is not completely
predictable.)

c. Run the experiments and compare to your output.  Where did things
work differently than you expected?

### Exercise 7: Implementing `previous`

As you may have noted, we have some isues with the random walk 
experiment.  That's because `previous` is not imlemented.

a. Write experiments, comparable to `expt2` and `expt3`, that experiment
with removing elements using `previous`.  You can take advantage of
`removeBackwardsExpt` in writing those experiments.

b. Implement the `previous` method.

c. Run the newly extended tests and correct any errors you discover.

### Exercise 8: Additional testing

We've seen that iterators sometimes have difficulty when we remove
sequences of values.  While `expt3` is intended to capture some
such situations, we should have something a bit more systematic.

Write an experiment that tests the effect of alternately removing
three elements in a row, and then keeping two elements.  Your experiment
should remove elements 0, 1, 2, 5, 6, 7, 10, 11, ....

*Hint*: Use `removeForwardExpt` and a `Counter`.

For those with extra time
-------------------------

_If you find that you have extra time, consider how you would change
the `SimplDLL` class if we decided to use a dummy node for the front
of the list, and make the list circular.  (Once we have that dummy node,
we should be able to have the cursor point to a node in the list,
rather than a specially created node.)_

Acknowledgements
----------------

This lab is all new for spring 2019.

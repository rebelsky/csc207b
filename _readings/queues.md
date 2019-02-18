---
title: Queues
summary: |
  We consider the essential features of *queues*, one of the forms
  of linear structures.  We also consider a novel implementation
  of queues.
prereqs: |
  [Linear structures](../readings/linear-structures).
  Arrays.
  [Subtype polymorphism](../readings/subtype-polymorphism).
  [Inheritance](../readings/inheritance).
  [Stacks](../readings/stacks).
---
Queue basics
------------

Now that you understand about linear structures and stacks, you will
find that queues are also fairly simple.  Queues are linear structures
structures that implement that *first in, first out*
(FIFO) policy.  That is, the value returned by `get` is
the value least recently added to the stack.

Why do we have queues in addition to stacks?  Because for many
applications (such as to-do lists), stacks are inherently "unfair".
In particular, if you do a lot of `put` and `get` operations, you
will not get the first element you put until after you've dealt
with everything else.  (Yes, that's the intent of stacks, but it's
not very fair if, for example, you use a stack to deal with incoming
email messages or phone calls.)

As you may recall, we often use object-oriented programs to model
real-world systems.  There are many places in which things end up
"in line" (or "in the queue", if you're in Great Britain).  For
example, if you are modeling a retail store, the lines at each cash
register can be modeled by queues.  (It's a little bit harder to
deal with people who cut in line, but we won't worry about such
issues right now.)

An interface
------------

```java
/**
 * A linear structure that follows the last-in, first-out policy.
 *
 * @author Samuel A. Rebelsky
 */
public interface Queue<T> extends LinearStructure<T> {
  /**
   * Add an element to the queue.
   *
   * @param val
   *   the value to add.
   * @pre
   *   !this.isFull()
   * @post
   *   The queue now contains an additional element of val.
   * @exception Exception
   *   If the structure is full.
   */
  public void put(T val) throws Exception;

  /**
   * Remove the least recently added element that is still in the
   * queue.
   *
   * @return
   *   val, a value.
   * @pre
   *   !this.isEmpty()
   * @post
   *   The structure contains one fewer copy of val.
   * @post
   *   Every element that remains in the queue was added more recently
   *   than val.
   * @exception Exception
   *   If the structure is empty.
   */
  public T get() throws Exception;

  /**
   * Determine what element will next be removed by get.
   *
   * @return
   *   val, a value.
   * @pre
   *   !this.isEmpty()
   * @post
   *   Every other value in the queue was added more recently than val.
   * @exception Exception
   *   If the structure is empty.
   */
  public T peek() throws Exception;

  /**
   * Determine if the structure is empty.
   */
  public boolean isEmpty();

  /**
   * Determine if the structure is full.
   */
  public boolean isFull();

  /**
   * Put a value at the end of the queue.  (An alias for put.)
   */
  public void enqueue(T val) throws Exception;

  /**
   * Remove the value from the front of the queue.  (An alias for get.)
   */
  public T dequeue() throws Exception;
} // interface Queue<T>
```

Implementing queues with arrays
-------------------------------

At first glance, it may seem as easy to implement queues with arrays
as it was to implement stacks with arrays.  Unfortunately, because
queues behave differently than do stacks, we encounter some significant
problems.

In particular, if we follow the stack policy of putting new values
at the end of the array, we'll need to implement get by removing
values from the front of the array.  That, in istelf, is not a
problem.  However, we'll soon find that we run off the end of the
array, even if we have empty space at the front of the array.  In
stacks, we simply create a new array when we run off the end of the
array.  However, it seems wasteful to create a new array when there's
empty space, available for new elements in the current array.  It
is possible to "wrap around", and add new elements to the empty
spaces at the beginning of the array.  However, such an implementation
has many subtleties that novice programmers (and even some expert
programmers) tend to handle incorrectly.  You may have an opportunity
to explore those subtleties in lab, on a homework assignment, or
in an exam.

A linked implementation of queues
---------------------------------

*The following was written for someone who had not yet encountered
linked lists.  If you have already seen linked lists, you may
consider it a refresher.*

Is there an easier way to implement queues?  It turns out that we
can implement queues as a form of *linked structure*.  Linked
structures are typically built by creating small objects, which we
call nodes, which contain a value and *links* (connections) to other
nodes.  You can think of each node as a position in the queue.

As a first step in creating a queue, we create a `Node` class with
two fields, `value`, which refers to the contents of the node (the
value at that position), and `next`, which refers to the next node.
For example, here is the queue `{ A, B, C }`.

```text
+---+---+     +---+---+     +---+---+
| * | *------>| * | *------>| * | / |
+-|-+---+     +-|-+---+     +-|-+---+
  |             |             |
  v             v             v
  A             B             C
```

Note that we use a slash to represent "no next element".  In practice,
we use the value `null` to represent "no next element", although there
are some variants to that practice.

If we were to add the value `D` to the queue, we would get the following:

```text
+---+---+     +---+---+     +---+---+     +---+---+
| * | *------>| * | *------>| * | *------>| * | / |
+-|-+---+     +-|-+---+     +-|-+---+     +-|-+---+
  |             |             |             |
  v             v             v             v
  A             B             C             D
```

If we remove the A from the beginning of the list, we then delete that node.

```text
              +---+---+     +---+---+     +---+---+
              | * | *------>| * | *------>| * | / |
              +-|-+---+     +-|-+---+     +-|-+---+
                |             |             |
                v             v             v
                B             C             D
```

How do we add to the back and remove from the front?  We typically
keep track of two fields, `front` and `back`.  As the pictures above
suggest, adding to the queue simply involves setting the next link
of the current back and then moving the back.

```java
this.back.next = new Node(val, null);
this.back = this.back.next;
```

Getting the value at the front is equally easy.  We extract it from
the node and then change the front to use the next node.

```java
Object tmp = this.front.value;
this.front = this.front.next;
return tmp;
```

When the queue is empty, front is null (or vice versa).

There are, of course, some special situations we need to worry about.
You'll consider some of these special situations in the lab.

Terminology
-----------

Unfortunately, some bright computer scientist designed the queue
before some other bright computer scientist designed the more general
linear structure.  Hence, the terms that many folks use for the
basic queue operations are not `put` and `get`, but rather `enqueue`
and `dequeue`.  To make life even more fun, there's an ADT called
a "Dequeue" (for "doubly-ended queue").

To make our code more usable, we will generally stick with the 
linear structure terms, although you'll note that we added the
other terms for convenience.

Citation
--------

This reading is closely based on a similar reading I wrote for
_The TAO of Java_.  That reading last appeared in [the course web for
CSC 207 2014F](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2014F/readings/queues.html).

That reading was based on a similar reading I created as a part of
*Espresso*, although I rewrote the code for the _TAO_ version.


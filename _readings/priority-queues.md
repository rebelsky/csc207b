---
title: Priority queues
summary: |
  We consider the essential features of *priority queues*, our third
  form of linear structure.
prereqs: |
  [Linear structures](../readings/linear-structures).
  Arrays.
  [Subtype polymorphism](../readings/subtype-polymorphism).
  [Inheritance](../readings/inheritance).
  [Stacks](../readings/stacks).
  [Queues](../readings/queues).
--- 
Priority queue basics
---------------------

You've seen two kinds of linear structures.  Stacks are linear
structures with a "last in, first out" policy.  Queues are linear
structures with a "first in, first out" policy.  But sometimes
neither policy suffices.  For example, if we're using a linear
structure to keep track of tasks, we typically want neither the
first task we added nor the most recent task.  Rather, we want the
*highest priority* task.  Linear structures that implement the
"highest priority first" policy  are called *priority queues*.

How do we represent priority?  Typically, with a `Comparator`.  You
can decide whether the "smallest" or "largest" value has the highest
priority.  (For our sample code, we've chosen to choose the smallest
value, since that mimics the decision in the standard Java
implementation of priority queues.) Where does the comparator come
from?  We'll supply it as a parameter to the constructor.  (Of
course, that's a bit dodgy to describe in the interface, so we'll
choose the hack of having the interface also mention the comparator.)

An interface
------------

What methods do we expect a priority queue to provide?  Mostly,
just the linear structure methods, but with a new interpretation.  
We'll also include a method that grabs the Comparator (again, mostly
so that we can reference it).  

What's left?  Well, we need to think about the order in which the
iterator will iterate elements.  We could require that they be
iterated in priority order, but that presupposes an implementation.
So, we'll just require that the iterator provide all the elements
in some order.

Putting it all together, we get the following:

```java
import java.util.Comparator;
import java.util.Iterator;

/**
 * A linear structure that follows the "best first" policy.
 *
 * @author Samuel A. Rebelsky
 */
public interface PriorityQueue<T>
    extends LinearStructure<T>
{
  /**
   * Add an element to the queue.
   *
   * @param val
   *   the value to add.
   * @pre
   *   !this.isFull()
   * @post
   *   The queue now contains an additional copy of val.
   * @exception Exception
   *   If the structure is full.
   */
  public void put(T val) throws Exception;

  /**
   * Remove the highest-priority element that is still in the queue.
   *
   * @return
   *   val, a value.
   * @pre
   *   !this.isEmpty()
   * @post
   *   The structure contains one fewer copy of val.
   * @post
   *   For all values, v, in the queue,
   *     comparator().compare(val, v) <= 0
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
   *   For all values, v, in the queue,
   *     comparator().compare(val, v) <= 0
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
   * Get an iterator that returns all of the elements in some order
   * (that is, not necessarily in priority order).
   */
  public Iterator<T> iterator();

  /**
   * Determine what comparator is used by this priority queue.
   */
  public Comparator<T> comparator();
} // interface PriorityQueue<T>
```

Array-based implementions of priority queues
--------------------------------------------

Take a minute to think about how you might implement priority queues
using arrays.

Are you done?  Why don't you take another moment and reflect a bit more.

Now are you done?  See if you can come up with a second approach.

Sorry for the delay, but we really wanted you to think about
implementation before we gave you some approaches.  In fact, there
are two standard approaches to implementing priority queues with
arrays:

* We can store the elements in order, typically lowest-priority to
  highest-priority.
* We can store the elements in no particular order.

In the first case, the `put` method will need to spend some effort
making sure that the underlying array remains in order.  However,
the `get` method is trivial: just remove and return the element at
the end of the array.

In the second case, the `put` method is easy: just shove the element
at the end of the array.  However, the `get` and `peek` methods
will need to search the array for the smallest element.  For `get`,
you will then want to swap the smallest element to the end of the
array and return and remove it.

Which method is better?  In the first case, we spend O(n) work
putting the new element at the next place and O(1) work getting
that element.  In the second case, we spend O(n) work finding the
smallest element and O(1) work adding elements.  If we find that
our program is dominated by adding elements (e.g., because we add
more tasks than we can ever hope to accomplish), we'll find the
second is better.  However, if we expect to get every element and
also do a lot of peeking, the first approach is better.

A linked implementation of priority queues
------------------------------------------

Sometimes, we gain benefits in convenience and efficiency by using
a linked implementation of abstract data types.  Unfortunately,
the linked implementation of priority queues is not much different
than the array-based implementation, at least in terms of efficiency.  

Once again, we can store the elements in order (in this case,
probably highest-priority at the front) or we can store them in an
arbitrary order and search and delete when we need the smallest.
But maintaining them in order means extra effort when adding an
element, and leaving them unarranged means extra effort searching
for the largest.  

You can probably come up with some "tweaks" to the disordered model
that might help make it a bit faster (e.g., moving higher-priorty
elements toward the front of the priority queue when we look through
it).  However, it is unclear whether that effort is appropriate,
given that there are some much more efficient implementations, which
we will cover later.

If you do choose to store the elements in a linked structure (e.g.,
because you have few enough elements that the extra cost is not
onerous), my experience is that it is easier to store the elements
in sorted order.  Among other things, that also allows you to iterate
them in sorted order.

Sorting with priority queues
----------------------------

If you have a priority queue, sorting is pretty easy: Add all of the
elements, then read them back out of the queue in order.

What's the running time?  Either put or get is O(n).  And we call
both operation O(n) times, so it's O(n<sup>2</sup>).  Hmmm ...
can we implement priority queues in a new way that allows us to put
and get faster?  That's a topic for the future.


---
title: Linear structures
summary: |
  We consider the essential features of a class of abstract data
  types known as *linear structures*.  Linear structures permit you
  to collect information and retrieve one piece of information at
  a time.
prereqs: |
  [Designing ADTs](../readings/designing-adts).
  [Interfaces](../readings/interfaces).
---

Introduction: Collections
-------------------------

As you may have learned earlier, computer scientists regularly
design, analyze, and implement mechanisms for collecting information.
Why do we have more than one general "Collection" data type?  Because
it turns out that we can often make the implementation more efficient
if we focus on certain operations.  (More precisely, we can make
the implementation of those operations more efficient if we focus
on those operations.)

You have probably already encountered a variety of collections in
your computer science, mathematics, or even "real world" education.
For example, a *set* collects values and provides one central
operation of membership.  In contrast, a *list*, such as a list of
students in a class, collects values and provides a central operation
of visiting each value in the list in turn.  And an *array* or
*vector* organizes values with an integer index.  Over the next few
readings and labs, we will consider a variety of collections and
their implementations.

Linear structures
-----------------

Among the simplest collections we encounter are the so-called *linear
structures*.  Linear structures are collections to which you can
add elements, and from which you can remove elements, in both cases,
with one element at a time.  You can think of a to-do list or a job
jar as kinds of linear structures: As you come up with new tasks,
you add them to the list or jar.  When you have time to undertake
a new task, you grab one from the list or jar.

Linear structures therefore provide two central operations:

* `put(T value)`, which adds an object to the structure.
* `T get()`, which removes and returns one object from
the structure.

You may be asking yourself (or the page, or the computer screen,
or your professor) what determines which object `get()` returns.
In the most generic form of linear structure, the particular object
returned by `get` is not specified.  However, we do build a variety
of kinds of linear structures, each with its own *policy* for
specifying which object `get` returns.

* *Stacks* return objects using a last-in, first-out policy.  That
  is, `get` removes and returns the object that has been most
  recently added to the stack.  We can use stacks to model the
  typical set of plates in a cafeteria or the calling structure of
  a program at run time.
* *Queues* return objects using a first-in, first-out policy.  That
  is, `get` removes and returns the object that has been least
  recently added to the stack.  We can use queues to model the line
  at at grocery store or to keep track of actions that must be done
  in order.
* *Priority Queues* return objects based on some ordering.  In this
  case, the `get` operation removes and returns the object of highest
  priority.  As you may have learned, in Java, we typically specify
  the priority using the `compareTo` operation or a separate object
  that implements `Comparator`.  Priority queues are a great way to
  implement to-do lists.
* *Randomized Linear Structures* return objects "randomly".  In
  this case, it should be difficult to predict which value the
  structure will return.

We will visit more details about these structures, particularly the
implementations of such structurees, over the next few readings and labs.

Additional operations
---------------------

So far, we've only specified two operations for linear structures.
Are there others we might want or expect them to provide?  Certainly.

One principle many designers employ is that any precondition that
a client must meet must also have a mechanism for checking that
precondition.  Since it is a bad idea to try to `get` a value from
an empty structure, clients probably need access to a `isEmpty`
predicate.

Experience also suggests that there are times in which it is useful
to check what value `get` will return, but not to remove
that value.  (Note that while we can remove and re-add the value in a
stack or priority queue, if we remove and re-add it in a queue, it moves
to the end.)  For such situations, many designers of linear structures
include a `peek` operation.

Some designers prefer as much symmetry in their structures as they
can provide.  Others worry about implementation and note that we
will eventually run out of room as we add values to a collection.
Both classes of designers provide an `isFull` method.

Some designers like to add functionality by permitting clients to
determine how many values are in the structure.  Others note that
determining the size of a linear structure is not central to the
mission of linear stuctures and do without it.  In the interests
of minimalism, we follow the latter strategy.

When we get more experienced at designing collections, we will find
that we should also add an `iterator()` method.  We'll leave that 
method to a future reading.

A Linear Structure interface
----------------------------

We have considered the primary purpose of linear structures (to
collect values and then to extract them one at a time) and the key
methods one provides for linear structures (`put`, `get`, `isEmpty`,
and `peek`).  We are now ready to put everything together into a
Java interface that specifies the details.

```java
/**
 * Simple linear structures, which let you add and remove items one 
 * at a time.
 *
 * @author Samuel A. Rebelsky
 */
public interface LinearStructure<T> {
  /**
   * Add an element to the structure.
   *
   * @param val
   *   the value to add.
   * @pre
   *   !this.isFull()
   * @post
   *   The element has been added to the structure.  At some point,
   *   a call to get() will remove the element.
   * @exception Exception
   *   If the structure is full.
   */
  public void put(T val) throws Exception;

  /**
   * Remove an element from the structure according to the underlying
   * policy.
   *
   * @return
   *   val, a value.
   * @pre
   *   !this.isEmpty()
   * @post
   *   The structure contains one fewer copy of val.
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
   *   The next call to this.get() returns val.
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
} // interface LinearStructure
```

Acknowledgements
----------------

This reading is closely based on a similar reading I wrote for
_The TAO of Java_, which last appeared in [the course web for
CSC 207 2014F](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2014F/readings/linear-structures.html).

That reading is, in turn, based on a reading I originally wrote for
*Espresso*.  


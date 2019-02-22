---
title: Wrapping objects and classes
summary: |
  We consider a technique for adding functionality to classes.
prereqs: |
  [Interfaces](../readings/interfaces).
  [Polymorphism](../readings/polymorphism).
---
Introduction
------------

In a [recent lab](../labs/linear-structures), you explored a class that
we called `ReportingLinearStructure`.  In case you've forgotten, the
idea of these structures was that they provided all of the methods
of `LinearStructure`, but that the implementation of those methods
was basically "call another linear structure to do the real work".

Here's a simplified version, using our go-to `Counter` interface.

```java
public interface Counter {
  public int get();
  public void increment();
  public void reset();
} // Counter

public class ReportingCounter implements Counter {
  PrintWriter pen;
  String name,
  Counter c;

  /**
   * Build a counter that reports all of its actions.
   */
  public ReportingCounter(PrintWriter pen, String name, Counter c) {
    this.pen = pen;
    this.name = name;
    this.c = c;
  } // ReportingCounter

  public int get() {
    this.pen.print(this.name + ".get(): ");
    this.pen.flush();

    int result = this.c.get();

    this.pen.println(result);
    return result;
  } // get()

  public void increment() {
    this.pen.println(this.name + "increment()");
    this.c.increment();
  } // increment()

  public void reset() {
    this.pen.println(this.name + "reset()");
    this.c.reset();
  } // reset()
} // ReportingCounter
```

What's the "big picture" idea here?  We can add functionality (in
this case, the ability to log all of the calls to an object) by
"wrapping" the object inside another object that adds some functionality
to each call.  Many programmers, software designers, and computer
scientists refer to this technique as "wrapping" an object.  The
steps are usually as follows.

* We represent a set of capabilities through an interface.
* Our wrapper class implements the interface.
* Our wrapper class has a field that refers to another object
  that also implements the interface.  We might refer to that as
  a "wrapped" object.
* When a client calls the wrapper's `fun` method, that method
  provides some additional functionality and then calls the  
  wrapped object's `fun` method.

Some think about wrapping with a picture something like the following.
(This is not the normal stack/heap representation; just a broader
conceptualization.)

```text
          Wrapper
         +-----------------------+
Client ---> fun(): extra_stuff() |
         |         wrapped.fun() |
         |              |        |
         |     wrapped  |        |
         |    +---------|-+      |
         |    | fun() <-/ |      |
         |    +-----------+      |
         +-----------------------+
```


As you progress as a designer, you will discover many instances in
which you find it useful to write wrappers.  We will consider a few
here, as well as some related issues.

Another application: Tallying
-----------------------------

Our `ReportingCounter` is useful for seeing the pattern of behaviors
of our object.  Is it better than a debugger?  It depends on the
situation.  Sometimes we just want a log of operations to gain some
understanding of how a class is used.  Sometimes such a log lets us
identify busy times in a continuously-running application.  But the
`ReportingCounter` generates a *lot* of information.  For some analyses,
it may be just as useful to get a count of the number of times each
operation is called.  (If you've read about priority queues, you may
know that the implementation choice for a priority queue can be
affected by the pattern of calls.)

Here's a wrapper class that provides that functionality.  

```java
public class TalliedCounter implements Counter {
  // The thing we wrap
  Counter c;

  // The things we count
  int gets = 0;
  int increments = 0;
  int resets = 0;

  /**
   * Build a counter that reports all of its actions.
   */
  public TalliedCounter(Counter c) {
    this.c = c;
  } // TalliedCounter

  public int get() {
    ++this.gets;
    return this.c.get();
  } // get()

  public void increment() {
    ++this.increments;
    this.c.increment();
  } // increment()

  public void reset() {
    ++this.resets;
    this.c.reset();
  } // reset()

  public int countGets() {
    return this.gets();
  } // countGets

  // ...
} // TalliedCounter
```

There are, of course, other features we might add to our `TalliedCounter`
class.  For example, we might want to turn our counter off temporarily
for some portions of the code.  We might want to reset our tally of
procedure calls.  I suppose we could even use a `Counter` object for
each of the tallies, but that is likely to become confusing.

Using wrappers to adapt classes
-------------------------------

At times, you will find that you have a set of requirements for an object,
most frequently specified by an interface, and a class that conceptually
provides methods that meet those requirements, but don't have quite the
right form.  For example, you may recall that our `LinearStructure`
interface indicates that linear structures should provide `put`, `peek`,
and `get` methods.

As you might expect, the Java library provides a variety of classes
that have similar functionality to that we expect in our `LinearStructure`
interface.  For example, [java.util.PriorityQueue]({{ site.java_api }}/java/util/PriotityQueue.html) is a linear structure that supports a "first in,
highest-priority-out" policy.  Unfortunately, the designers of the Java
API did not bow to our wishes and failed to provide `put` and `get` methods.
However, they do provide very similar methods called `add` and `poll`.
Hence, we can *adapt* one of those objects to meet our interface, writing
methods that just call the corresponding method of a `java.util.PriorityQueue`.

Here's a sketch.

```java
public class JUPQadapter<T> implements LinearStructure<T> {
  java.util.PriorityQueue<T> pq;

  /**
   * Create a new priority queue that holds up to capacity elements and 
   * uses order to compare elements.
   */
  public JUPQadapter(int capacity, Comparator<T> order) throws Exception {
    this.pq = new java.util.PriorityQueue<T>(capacity, order);
  } // JUPQadapter(int, Comparator<T>)

  @Override
  public boolean isEmpty() {
    return pq.size() <= 0;
  } // isEmpty()

  @Override
  public boolean isFull() {
    // It looks like the standard ones don't fill
    return false;
  } // isFull()

  @Override
  public void put(T val) throws Exception {
    pq.add(val);
  } // put(T)

  @Override
  public T get() throws Exception {
    return (T) pq.remove();
  } // get(T)

  // ...
} // class JUPQadapter<T>
```

Additional reflections on the `ReportingCounter` class
------------------------------------------------------

Let us return to the `ReportingCounter` class that began the reading.
You may wonder why we've provided a `PrintWriter` field in the
`ReportingCounter` class.  Why not just use `System.err.print` or
something equivalent?  Because not every client will want the
output logged to the same place.  Some might want it on a screen.
Others might want it in a file with a particular name.  (And, perhaps,
we want logs from different objects in different files.)  By
including that field, we give the client that control.

But what if the client just wants to say "report about this object to
the normal report output stream".  We can set up a second constructor that 
takes only the class to be wrapped.

```java
  /**
   * Build a counter that reports all of its actions.
   */
  public ReportingCounter(Counter c) {
    this.pen = new PrintWriter(System.err, true);
    this.name = c.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(c));
    this.c = c;
  } // ReportingCounter(Counter)
```

Wrappers and subclassing
------------------------

Our original description of wrapper classes relied on interfaces.  What
do we do if we want to wrap an existing class, rather than an interface?
We can still achieve the same goals, although through a slightly different
mechanism.

Suppose we have a `Box` class, as follows.

```java
public class Box<T> {
  T contents;

  public Box(T val) {
    this.contents= val;
  } // Box(T)

  public T get() {
    return this.contents;
  } // get()

  public void set(T newval) {
    this.contents = newval;
  } // set()
} // class Box<T>
```

We can develop a `ReportingBox` as follows.

```java
public class ReportingBox<T> extends Box<T> {
  PrintWriter pen;
  String name;

  public ReportingBox(PrintWriter pen, String name, T val) {
    super(val);
    this.pen = pen;
    this.name = name;
  } // ReportingBox(PrintWriter, String, T)

  public ReportingBox(T val) {
    super(val);
    this.pen = new PrintWriter(System.err, true);
    this.name = "Box@" + Integer.toHexString(System.identityHashCode(this));
    // More generally, we could get the name of the superclass with 
    // this.getClass().getSuperClass().getName() 
  } // ReportingBox(T)

  @Override
  public T get() {
    this.pen.print(this.name + ".get(): ");
    this.pen.flush();
    T result = super.get();
    this.pen.println(result);
    return result;
  } // get()

  @Override
  public void set(T newval) {
    this.pen.println(this.name + ".set(" + newval + ")");
    super.set(newval);
  } // set(T)
} // class ReportingBox<T>
```

Meta-programming and wrappers
-----------------------------

As you learn new object-oriented languages, you will also discover
some that provide more direct support for this kind of behavior,
most often through approaches that require us to write a bit less
code.

But even when you don't have such language features directly available,
you can probably find a way to make it easier to generate some kinds
of wrapper classes.  For example, suppose we find that we regularly
want to generate reporting versions of structures.  By now, you've
probably figured out that the code is relatively straightforward.

* We create a constructor that takes a `PrintWriter` for saving
  output, a string for the name, and the object we wrap.
* For each method in the interface, we write a method that prints
  out the method name and the parameters.  If the method returns
  a value, we also store and print that value.

That seems to be an *algorithmic* transformation of the underlying
code.  Hence, we can probably write a program that does the work
for us.  That is, we could write a program `MakeReporter` that takes
as input the code for an interface and generates the code for a
reporter for that interface.  Some work parsing the Java code would
be required, but it should be within our capabilities.

Alternately, we can take advantage of Java's *reflection* capabilities
to programmatically query the interface.  That is, we can call
`object.getClass()` to get an object of type [java.lang.Class]({{
site.java_api }}/java/lang/Object.html) and then use methods like
`class.getDeclaredMethods()` to find out all of the methods.  We'll
leave the details of that approach to another day.

Terminology
-----------

Although we use the term `Wrapper` for this approach, there are a few
other common terms.  Some call this approach an `Adapter`, in that
we are adapting the behavior of another object.  Others call it 
`Delegate` or `Delegator`, in that the wrapper class delegates most
of the work to another class.  There are also some subtle differences
that some associate with the different terms; we will leave those as
an issue for you to explore on your own.

Acknowledgements
----------------

This reading appeared in Spring 2019 as a new addition to Samuel
A.  Rebelsky's material for CSC 207.  Some parts are based on [a
presentation Rebelsky gave to students about these issues in fall
2014](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2014F/outlines/outline.39.html).
For example, the diagram of a wrapped class is derived from a diagram
in that presentation.

I found the details of how to implement the "default name" code for
`ReportingCounter` and `ReportingBox` in answer by [Andrew Logvinov](https://stackoverflow.com/users/966590/andrew-logvinov) to [a similar question on 
StackOverflow](https://stackoverflow.com/questions/18396927/how-to-print-the-address-of-an-object-if-you-have-redefined-tostring-method).  (I knew that
it was possible to do this; I had just forgotten how and was too lazy
to look in the Java documentation.)

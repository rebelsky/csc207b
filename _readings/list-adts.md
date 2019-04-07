---
title: List ADTs
summary: |
  We consider ways to think about lists, which are among the simplest
  collections of values.
---
Introduction
------------

While abstract data types (ADTs) serve a variety of purposes, most 
ADTs are used to store collections of values.  What distinguishes
ADTs is how the ADT organizes and provides access to the elements in
the collection.  We'll also see other issues, such as whether the
ADT is homogeneous or heterogeneous, mutable or immutable, and
dynamic or static.  But most of these additional design issues are
secondary to the primary design question: How does the ADT organize
and access the elements?

*Lists* are conceptually among the simplest abstract data types.
In essence, a list is a collection of values that you can visit one
by one, with the order in which the elements are visited is controlled
by the client.

How do clients control the order in which elements are visited?
Typically, the instructions to add elements (and to remove elements,
if removal is permitted) allow clients to clearly specify where in
the list each new element goes.

What are we listing?  The "type" of a list
------------------------------------------

We now have a definition of list that suggests two primary operations:
Clients should be able to *add* elements to lists (with control
over positioning of elements) and clients should be able to *visit*
elements of the list.  Figuring out how to express each of those
operations is an interesting design issue, one that we will get to
in a moment.  However, before looking at the details of these
operations, let's consider a few of the design issues we raised
above.

*What types does the list store?*  Before you learned about writing
generic data types, you probably would have picked a type: "our
lists will store strings" or "our lists will store integers".  You
might also have thought about *heterogeneous lists*: "our lists
will store any type of values".  And, as you've seen, allowing
collections to store multiple types of values can be useful.  And
that utility should lead us to design *heterogeneous lists*.

However, heterogeneity can cause us to lose important benefits of
using Java.  In particular, many programmers use Java because Java
provides compile-time type checking, and they know that compile-time
type checking helps catch a lot of potential program bugs.  If our
lists our heterogeneous, we need to do run-time type checking.
Hence, Java provides the *generics* that you've seen before.  If
we parameterize our lists with the type of value they store, we can
still write generic code, but we can support *homogeneous lists*.

```java
public interface MyList<T> {
  ...
} // interface MyList<T>
```

What if our client wants heterogeneous lists?  That's one of the
nice things about Java's generics: A list of `Object` values is
heterogeneous as any Java value is either already an object or can
be boxed into an object.

Because the homogeneous/heterogeneous question is so nicely solved
by Java's generics, we won't return to that design question again.
(You should, however, revisit these issues if you're working in
other languages or if other design decisions prevent you from using
generics.)

Of course, the question of whether lists should be homogeneous or
heterogeneous is not the only question you should ask.  Let's move
on to a few more.

How should lists change?  Exploring Lisp Lists
----------------------------------------------

A natural next question in the design of our list ADT might be
*Should lists be mutable or immutable?*  It may be strange to think
about immutable lists.  However, there are many situations in which
it is convenient to make lists immutable.  You may want to ensure
that the same sequence is used in every situation.  You may find
that making lists immutable improves certain core operations.  You
may just know that mutable structures lead to complexity in program
design and analysis.

It is certainly possible to think about lists as immutable
structures.  In fact, Lisp, one of the earliest programming
languages, provides lists that many programmers treat as immutable.
(Lisp lists are mutable; the latest versions of Scheme, a popular
descendant of Lisp, provides both mutable and immutable lists.)

Let's start by exploring the immutable model in a little more
depth.  Basic Lisp lists are built from a simple recursive definition of
list.

* The empty list is a list.
* Adding an element to the front of a list produces a new list.

How does that typically translate into methods?

* We need a constructor to build empty lists.  We might call
  this `empty` or we might just use a 
  zero-parameter constructor.  (It's hard to specify constructors
  in interfaces, so we might settle for `empty`.)
  We might also make a design decision that `null`
  represents the empty list, although that will likely make
  our code less object-oriented.
* We need a method to create a new value to the front of a list.
  We might have a method `prepend(T val)` or
  we might have a two parameter constructor.  Once again, it's
  hard to specify constructors in interfaces, so we'll stick
  with the method.
* We need a way to get the first element in a list.  Traditionally,
  the operation is called `car`, but we'll use the clearer `head`.
* We need a way to step through the list.  The tradition in Lisp
  is to have a method that returns everything but the front of the
  list.  Traditionally, the operation is called `cdr`, but we'll
  use the clearer `tail`.
* We need a way to determine if a list is empty.  We'll use
  `isEmpty`.

Putting that all together, we get the following interface.

```java
/** 
 * Lists that follow the traditional Lisp/Scheme model.
 */
public interface LispList<T> {
  /**
   * Create the empty list.
   */
  public LispList<T> empty();

  /**
   * Create a new list by prepending a new element to the front of
   * this list.
   *
   * @param val a value
   * @return lst list
   * @post
   *   lst.head() == val
   * @post
   *   lst.tail() == this
   */
  public LispList<T> prepend(T value);

  /**
   * Get the first element of the list.
   */
  public T head();

  /**
   * Get a list that contains all but the head of this list.
   */
  public LispList<T> tail();

  /**
   * Determine if the list is empty.
   */
  public boolean isEmpty();
} // interface LispList<T>
```

With these methods, it's relatively straightforward to iterate through
the elements of a list.  Here's a simple procedure that prints the
elements of a list, one by one.

```java
   /**
    * Print all the elements in a list.
    */
   public static <T> void printList(PrintWriter pen, LispList<T> lst) {
     while (!lst.isEmpty()) { 
       pen.println(lst.head());
       lst = lst.tail();
     } // while
   } // printList(PrintWriter, LispList<T>)
```

Of course, in addition to iterating lists, we need to provide a way
for clients to control the order of elements in the list.  And they
can do so by building the list from back to front.  Rearranging
  the elements involves building new lists, but it's not that hard.
For example, if we have the list `[a, b, c]` and want to replace
the `b` with some new value, we might write something like the
following:

```java
  newlst = lst.tail().tail().prepend(newval).prepend(lst.head());
```

Are there other methods we could include in the interface?  Certainly.
We might want methods that get the _i_th element of a list, that
reverse a list, that extract sublists, that replace elements of the
list, and so on and so forth.  However, we are striving to start
with minimalist interfaces, so we'll start with the five basic
methods.

While Lisp lists are conceptually simple, they also have some
significant drawbacks.  For example, there are many problems in
which you want to change the elements of the list without building
a new list.  For example, we might be concerned with the storage
requirements of our lists.  In addition, Lisp Lists are closely
tied to a particular implementation, one involving simple structures
that link together the front of the list and the rest of the list.
In practice, we might like the freedom to choose between implementations.
Hence, while Lisp lists were a useful detour, we will continue our
exploration by designing an ADT for mutable lists.

Categories of methods
---------------------

We've now made two major design decisions: Our list ADT will use
generics so that we can support homogeneous lists of various types
and our list ADT will support mutation.  These decisions, along with
our overall philosophy that lists are iterable collections, suggest
four basic categories of methods.

* We need methods that the client can use to *add* elements to the list.
* We need methods that the client can use to *remove* elements from the 
  list.  (We might also choose to make these methods optional.)
* We might want methods that the client can use to *replace* elements 
  of the list.  Why not just use the methods to add and remove elements?  
  Because it might be much more efficient to replace elements.  (Again, 
  we might choose to make this methods optional.)
* We need methods to *iterate* through the elements of the list.

For the first two categories of methods, we might just allow people
to work at the front and back of the list, generalizing the design
of Lisp lists, although in a more mutable form.  But it is clearly
more useful to indicate *positions* in the list.  That is, we might
say that we want to remove an element at a particular position, or
to add an element at, before, or after that position.

But how should we represent positions?  There are a variety of
approaches that designers use.  We'll consider each, and then
explore Java's standard technique.

Positions - numeric and generalized
-----------------------------------

Most programmers start by thinking of positions as numbers.  "I
want to be able to remove the element at position 5." In some ways,
that design works well.  Numbers are easy for people to understand,
and most programmers are used to the numeric positions in arrays.

But there are also some significant disadvantages to using numeric
positions.  First, the semantics can be difficult.  For example,
what does it mean to remove the element at position 5?  Do we end
up with nothing there?  Does everything shift left?  Can we only
remove at a position when it's the beginning or end?  What happens
to the other positions?  And so on and so forth.  As importantly,
using numbers can bias the implementation: There are implementations
of lists, such as Lisp Lists, in which using numbers as positions
leads to some inefficient implementations.

It's also good practice to look at ways to generalize things.
Hence, rather than saying "positions are integers",
we can say "we use positions", and allow implementors
to decide what form of position is best.  If we choose this 
approach, we might first define a `Position` interface.

```java
public interface Position {
} // interface Position
```

Now, in our `MutableList` interface, we can use these
values.

```java
public interface MutableList<T> {
  ...

  /**
   * Get the value at a particular position.
   */
  public T get(Position pos);

  /**
   * Remove the element at a particular position.
   *
   * @pre the position is valid
   */
  public void remove(Position pos);

  /**
   * Determine if a position is valid.
   */
  public void isValid(Position pos);

  ...
} // interface Mu<T>tableList
```

How do we use positions?  That is, how do we get a position in the
middle of the list?  One option is to have the list interface
provide mechanisms for dealing with positions.

```java
public interface MutableList<T> {
  ...

  /**
   * The front of the list
   */
  public Position front();

  /**
   * Get the position that immediately follows pos.
   *
   * @pre pos is not at the end of the list.
   */
   public Position next(Position pos);

  /**
   * Determine if a position is at the end of the list.
   */
  public boolean atEnd(Position pos);

  ...
} // interfaceMutableList<T>
```

We now have enough methods that we can iterate lists, as well
as mutate them.

```java
  public static <T> void printList(PrintWriter pen, MutableList<T> lst) {
    Position here = lst.front();
    while (!lst.atEnd(here)) {
      pen.println(lst.get(here));
      here = list.next(here);
    } // while
  } // printList(PrintWriter, MutableList<T>)
```

Lists with a current element
----------------------------

Some designers (including the designers of some textbooks) decide that
rather than having a separate position type, they'll just keep track
of the "current" element of the list.

```java
public interface MutableListWithCurrent<T> {
  ...

  /**
   * Get the current element.
   */
  public T get();

  /**
   * Advance to the next element.
   */
  public void next();

  /**
   * Reset to the beginning of the list.
   */
  public void reset();

  /**
   * Determine if we've reached the end of the list.
   */
  public boolean atEnd();

  ...
} // interface MutableListWithCurrent<T>
```

With this interface, it's equally easy to iterate lists.

```java
  public static <T> void printList(PrintWriter pen, MutableListWithCurrent<T> lst) {
    lst.reset();
    while (!lst.atEnd()) {
      pen.println(lst.get());
      lst.next();
    } // while
  } // printList(PrintWriter, MutableListWithCurrent<T>
```

It all sounds great, doesn't it?  But, as Joseph Bergin suggests
in [Lists with Current Considered
Harmful](http://csis.pace.edu/~bergin/papers/ListsWithCurrent.html), it's
not a very good design.
For example, if we have more than one subprogram that's interacting
with a list, each might have its own notion of the current position.
And, if we're sorting a list in place, we will almost certainly
need to keep track of positions.  Hence, our lists will not have a
current element.

List cursors
------------

You may have found the position interface a bit puzzling.  After all,
why are we having one object (the list) do all the work with another
object (the position).  Wouldn't it make more sense to have the object
that we're getting information about do all the work?  Alternately,
might we generalize the notion of "current".

I've found it useful to think of a "cursor" that we move through
the list.  Once we create a cursor, we can get the value at the
cursor and move the cursor, and we leave the list implicit.

```java
public interface ListCursor<T> {
  /**
   * Get the current element.
   *
   * @pre
   *   This cursor is valid.
   */
  public T get();

  /**
   * Advance to the next element.
   *
   * @pre
   *   The cursor is not at the end of the list.
   */
  public void next();

  /**
   * Determine if the cursor is valid.
   */
  public boolean isValid();

  /**
   * Determine if the cursor is at the end of the list.
   */
  public boolean atEnd();
} // interface ListCursor<T>

public interface BidirectionalListCursor<T> {
  /**
   * Retreat to the previous element.
   * 
   * @pre
   *   The cursor is not at the beginning of the list.
   */
   public void prev();

   /**
    * Determine if the cursor is at the beginning of the list.
    */
   public boolean atFront();
} // interface BidirectionalListCursor<T>

public interface MutableList<T> {
  ...

  /**
   * Get a cursor for the front of the list.
   */
  public ListCursor<T> front();

  ...
} // MutableList<T>
```

Once again, it's easy to iterate using this design.

```java
  public static <T> void printList(PrintWriter pen, MutableList<T> lst) {
    Cursor here = lst.front();
    while (!here.atEnd()) {
      pen.println(here.get());
      here.next();
    } // while
  } // printList(PrintWriter, MutableList<T>)
```

How do we use these cursors for adding or removing elements?  Here's
a case in which we might make the cursor a parameter to the method.

```java
public interface MutableList<T> {
  ...

  /**
   * Add an element immediately after the cursor.
   *
   * @pre
   *   The cursor was created by this list.
   * @pre
   *   The cursor remains valid.
   */
  public void addAfter(T val, ListCursor<T> cursor);
  
  ...
} // MutableList<T>
```

All seems well and good, doesn't it?  However, given your experience
with the other designs above, you're probably waiting for a criticism.
Believe it or not; I don't have one.  When I design my own list
interfaces, I tend to include some form of cursor.  Of course, there
are still a host of design decisions: Do we allow cursor to retreat?
What methods do we support for adding and removing elements?  And
so on and so forth.

Iterators
---------

While cursors provide a wonderful strategy for iterating lists, and
one that I recommend, it's also useful to know what the designers of
Java came up with.  In Java, clients iterate lists with objects that
are in the class `java.util.Iterator`.  Iterators are much
like cursors, in the sense that you can build multiple iterators for
a list, that you use them to get and advance, and that you can use
them to add and remove elements.  The differences are in the particular
decisions.

First, Java's iterators combine our `get` and `next` method.  That
is, when you call `next`, you get the next unvisited value *and*
you advance beyond that value.

Second, Java's iterators use `hasNext` to
indicate whether or not we've reached the end of the list.  (Hey,
it's just a name.)

Third, interfaces and classes that provide iterators traditionally
do so with an `iterator` method and indicate that they implement
the `Iterable<T>` interface.

Given those design decisions, iteration is easy.

```java
  public static <T> void printList(PrintWriter pen, MutableList<T> lst) { 
    Iterator<T> it = lst.iterator();
    while (lst.hasNext()) {
      pen.println(lst.next());
    } // while
  } // printList(PrintWriter, MutableList<T>)
```

In fact, this pattern is so common that Java provides a syntax for
iterating any class that implements `Iterator`.  One can use `for
(<varname>variable</varname> : <varname>collection</varname>)` to
set <varname>variable</varname> to each element of
<varname>collection</varname> in turn.  For example,

```java
  public static <T> void printList(PrintWriter pen, MutableList<T> lst) { 
    for (T val : lst) {
      pen.println(val);
    } // for
  } // printList(PrintWriter, MutableList<T>)
```

It's almost not worth writing the function any more.

But iterators differ from cursors in other important ways, too.
You may recall that we made cursors parameters to list methods that
mutate the list.  Iterators, on the other hand, expect that you will
use the iterator to mutate the underlying list.

For reasons that I don't completely understand, iterators provide
only an *optional* `remove` method which removes the value most
recently returned by `next`

```java
package java.util;

public interface Iterator<T> {
  ...

  /**
   * Remove the last element returned by next.
   */
  public void remove()
    throws UnsupportedOperationException, IllegalStateException;
} // interface Iterator<T>
```

That's right.  The implementor can indicate that the `remove` method
is not available by throwing an exception.  Clearly, whoever designed
this interface was not sold on compile-time type checking.

What happens if we decide to call `remove` twice in a row, without
a call to `next` in between?  The semantics of `remove` are such
that such a sequence of calls is considered invalid, and hence
should be avoided.

What if we want to add a value?  It turns out that the Java designers
didn't think addition was as important as removal.  Hence, `add`
is part of a subinterface called `ListIterator`.  The `add` method
adds a value immediately before the last element visited by `next`.
List iterators also provide a `prev` that allows us to back up in
the list.  Finally, for no clear reason, list iterators also provides
two methods that grab the index of the next or previous element,
`nextIndex` and `prevIndex`.

Putting it together
-------------------

Where are we?  We've considered a wide variety of design issues
that one might consider while designing a list ADT.  We ended up
deciding that most of the work in a list can be done through the
`ListIterator` interface.  Putting it all together,
this is what we seem to get.

```java
/**
 * Very simple lists.
 */
public interface SimpleList<T>
  extends Iterable<T>
{
  public Iterator<T> iterator();
  public ListIterator<T> listIterator();
} // interface SimpleList<T>
```

Where are all the details?  They're in the
`ListIterator` interface.  We'll consider the details
in the next reading.


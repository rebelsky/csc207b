---
title: Iterators
summary: |
  We consider iterators in Java.  Iterators are objects that let us
  inspect the elements of a collection.
javadoc: 
  - "[`java.util.Iterator`]({{ site.java_api }}/java/util/Iterator.html)"
  - "[`java.util.Iteratable`]({{ site.java_api }}/java/util/Iteratable.html)"
---
As you've learned by this point, one of the most common requirement for
a new abstract data type is that it collects a group of values.  Arrays,
lists, queues, stacks, and priority queues all collect values.  You can
probably think of others: sets, dictionaries (or association lists),
trees, etc.

No matter what type of collection you design, you will likely find that
you reach a point at which you realize that your client would like to
be able to see all of the elements of the collection, one at a time.
Perhaps they want to print them out.  Perhaps they want a naive search.
Perhaps they want to put them into a different kind of collection.

How do you give your client the ability to, say, see all of the values
in a linked list?  One possibility is that you could give them access 
the individual nodes in the list.  But that strategy breaks encapsulation;
our clients should be able to use our structures without knowing how
they are implemented.

Having the client focus on the particular implementation also complicates
the client's work, particularly as they consider replacing one collection
with another (e.g., because they realize they want slightly different
behavior).  If the client has just spent the effort the get "visit each"
working for a linked list, they'll have a lot of code to replace when
they switch the implementation to an array-based list.

In reflecting on these issues, the computer scientist [Barbara
Liskov](https://cra.org/cra-w/barbara-liskov-wins-acm-a-m-turing-award/)
developed *iterators* as a way to permit clients to visit the
elements of any collection in a uniform way while preserving
encapsulation.

Basically, an interator is an object that gives you access to the
elements of a collection in a systematic way.  There are a few
different ways to design iterators, but the most common is to provide
two basic methods: `next()`, which grabs the next element of the
collection (provided it has one), and `hasNext()`, which one uses
to check the precondition of `next()`.

```java
/**
 * A simplified iterator.
 */
public interface Iterator<T> {
  /**
   * Get the next value in the collection.
   */
  public T next() throws NoSuchElementException;;

  /**
   * Determine if there are more elements.
   */
  public boolean hasNext();
} // interface Iterator<T>
```

Classes that can provide iterators typically implement the `Iterable`
interface.

```java
/**
 * A simplified version of the iteratable interface.
 */
public interface Iterable<T> {
  /**
   * Return a new iterator for this collection.
   */
  public Iterator<T> iterator();
} // interface Iterable<T>
```

A client can then process *any* iterable class with code like the
following.

```java
  Iterator<String> it = stuff.iterator();
  while (it.hasNext() {
    process(it.next();
  } // while
```

Note that this code has achieved both of our primary goals: The client
does not need to know anything about the implementation of `stuff`, and
the client can use the same code no matter which kind of collection they
are using.

To make things even easier, the designers of Java added a special syntax
for this type of situation, the for-each loop.

```java
  for String val : stuff {
    process(val);
  } // for
```

Behind the scenes, the for-each loop behaves the same way that the
`next`/`hasNext` loop we wrote  earlier works.  It's just a bit easier
for most of us to read.

Implementing iterators
----------------------

How do we implement iterators?  It depends on the particular class that 
we are iterating.  Let's start with parts of a simple `ArrayBasedStack` class.

```java
public class ArrayBasedStack<T> implements Iterable<T> {
  T[] values;
  int size;
  // ...
} // class ArrayBasedStack<T>
```

An iterator for this class will likely need to keep track of the
current index into the array and either step forward from 0 to
size-1 or backward from size-1 to 0.  We'll use the latter technique.

```java
// The following is not correct Java code; it primarily exists
// for illustrative purposes.
class ABSIterator<T> implements Iterator<T> {
  int index;

  public ABSIterator() {
    this.index = size-1;
  } // ABSIterator()

  @Override
  public T next() throws NoSuchElementException {
    if (! this.hasNext()) {
      throw new NoSuchElementException();
    }
    return values[this.index--];
  } // next()

  @Override
  public boolean hasNext() {
    return this.index >= 0;
  } // hasNext()
} // class ABSIterator<T>
```

There's a slight issue with this code: How does the iterator have access
to the `size` and `values` fields of `ArrayBasedStack`?  The solution is
to give the iterator access to the underlying stack.

```java
class ABSIterator<T> implements Iterator<T> {
  int index;
  ArrayBasedStack<T> stack;

  public ABSIterator(ArrayBasedStack<T> stack) {
    this.stack = stack;
    this.index = this.stack.size-1;
  } // ABSIterator()

  @Override
  public T next() throws NoSuchElementException {
    if (! this.hasNext()) {
      throw new NoSuchElementException();
    }
    return this.stack.values[this.index--];
  } // next()

  @Override
  public boolean hasNext() {
    return this.index >= 0;
  } // hasNext()
} // class ABSIterator<T>
```

We can now write the `iterator` method for our `ArrayBasedStack` class.

```java
public class ArrayBasedStack<T> implements Iterable<T> {
  // ...
  public Iterator<T> iterator() {
    return new ABSIterator<T>(this);
  } // iterator()
  // ...
} // class ArrayBasedStack<T>
```

Iterating linked structures
---------------------------

If you use arrays to implement your linear structure, you'll find that
an approach like the one we used for `ArrayBasedStack` works reasonably
well.  You can iterate from the front, the back, or even the middle
(which you may have to do for array-based queues).

What about linked structures?  Iterators should be relatively straightforward,
at least for linear linked structures.  We'll have our iterator keep track
of the current node.  `next()` advances to the next node.  `hasNext()`
verifies that the node is not null.

```java
public class LinkedStack<T> implements Iterable<T> {
  public Node<T> top;
  // ...
  public Iterator<T> iterator() {
    return new LSIterator(this.top);
  } // iterator()
  // ...
} // class LinkedStack<T>

class LSIterator<T> implements Iterator<T> {
  public Node<T> current;
  public LSIterator(Node<T> start) {
    this.current = start;
  } // LSIterator(Node<T>)

  @Override
  public T next() throws NoSuchElementException {
    if (! this.hasNext()) {
      throw new NoSuchElementException();
    }
    T val = this.current.value;
    this.current = this.current.next;
    return val;
  } // next()

  @Override
  public boolean hasNext() {
    return this.current != null;
  } // hasNext()
} // LSIterator<T>    
```

Iterators, complexified
-----------------------

If your iterators need only provide `next` and `hasNext` methods, they
can be fairly straightforward.  However, in practice, Java's iterators
often provide a variety of other methods, some of which may "complexify"
your implementation.

One *optional* method in `java.util.Iterator` is `remove`.  This
method is supposed to remove the value that was *just* returned by
`next`.  If we're to do that with linked structures, we'll need to
make sure that we keep a reference to the node that comes before
the node we just returned (therefore, one that is *two* back from
the current node).  If we might remove the element at the start of
the linked structure, we may even have to refer back to the enclosing
class.

How can you have an optional method in an interface?  We have it
throw an `UnsupportedOperationException`.

Are there other complexities in iterators?  Here's an important one:
What happens if we modify the underlying collection while we're iterating
it?  For example, what happens if we have two iterators, and call
`remove` with one of those iterators?  Alternately, what happens
if, while iterating a stack, we push or pop some elements?

There are a variety of techniques one can use.  The easiest is to
make it clear that your code is not guaranteed to work if the
underlying structure changes.  But this is Java; we try to be as
careful as we can.  Hence, you may want to have your program keep
track of whether or not the underlying structure has been modified
(e.g., by keeping track of the number of modifications in the
structure, and storing that number when you create the iterator)
and then throwing a `ConcurrentModificationException` if the structure
has been modified during iteration.  `ConcurrentModificationException`
exceptions are runtime exception and hence need not be described
in the method signature.

Writing iterators with anonymous inner classes
----------------------------------------------

You may recall that way back at the start of [the reading on anonymous
inner classes](../readings/anonymous-inner-classes), we considered
problems in which we create one-off classes, classes that only need
to be used only in one situation.  How will anonymous inner classes
be useful when we write iterators?  Well, most of the times we've
written iterators, we've need to refer to fields and of the enclosing
class.  And so it's much easier to write iterators as anonymous
inner classes, rather than as helper classes.  Here's a portion
of a typical array-based stack class that uses this technique.

```java
public class ArrayBasedStack<T> implements Iterable<T> {
  T[] values;
  int size;

// ...

  public Iterator<T> iterator() {
    return new Iterator<T>() {
      int i = ArrayBasedStack.this.size - 1;

      @Override 
      public T next() throws NoSuchElementException {
        if (!this.hasNext()) {
          throw new NoSuchElementException();
        } // if (hasNext())
        return ArrayBasedStack.this.values[this.i--];
      } // next()

      @Override
      public boolean hasNext() {
        return this.i >= 0;
      } // hasNext()

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      } // remove()
    }; // new Iterator<T>
  } // iterator()
} // class Stack<T>
```

Concluding remarks
------------------

At their core, iterators are a relatively straightforward concept: Iterators
are objects that allow the client to iterate the elements of a collection
using `next` and `hasNext` or for-each loops.  Their complexities occur
mostly in terms of how we implement those two operations and other
optional operations.

Acknowledgements
----------------

Most of this reading was newly written in spring 2019.  The section
on anonymous iterators is closely based on a section in [the reading
on anonymous inner classes from the fall 2014 version of CSC
207](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2014F/readings/anonymous-inner-classes.html).

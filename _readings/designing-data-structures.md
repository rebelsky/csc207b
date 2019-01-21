---
title: Designing data structures
link: true
---
*Summary:* 
As the name suggests, *data structures* are mechanisms for organizing
("structuring") data.  Data structures are closely related to
abstract data types.  ADTs typically specify *what* you want to do
with data.  Because data structures indicate how we organize the
information in memory, data structures also describe *how* you
achieve those goals.  We also consider the LIA (layout, implementation,
analysis) approach to data structure design.

*Prerequisites:*
Programming experience, including arrays and references or pointers.
A basic understanding of abstract data types.

Introduction
------------

As you have likely learned, computer scientists study algorithms
and information.  That is, they consider how to build and understand
instructions that accomplish tasks or solve problems and they think
about how to represent and organize information.  There are clearly some
close relationships between algorithms and representation/organization:
The choice of representation or organization often affects the
algorithms we can write, and we often need to write algorithms that
make it possible to use a particular organization or representation.

As an example of the way in which organization affects algorithms,
consider how you might arrange a collection of books.  If you arrange
the collection alphabetically by author, it's very easy to find a
book if you remember the author's name.  Given such an organization,
we can easily predict approximately where the book will be, move to
that location in the collection, and look nearby.  (Even if we're
wrong, the author names we see there will suggest where to look next.)
We can also use the binary search algorithm to find the book.  However,
if you need to add a book to the collection, it may require a bit of
work, particularly if you have fairly packed shelves.  Adding a book
to one shelf means that you have to take out the last book on the shelf,
shift the other books over, take out the last book on the next shelf,
shift those books over, insert the book from the previous shelf, and
so on and so forth.  In addition, if you remember the title or genre
of a book, but not the author, the only way to find the book will be
to look at each title in turn.  In contrast, if we don't organize
the books at all, every search will be equally hard, but we can just
add books wherever there is free space.  Finally, we could number
the books as we added them, and create indices (by author, by
title, by genre, etc.) that tell us where to find particular books,
we can now efficiently search in multiple ways.  We can also add
the physical book quickly, but updating the indices will be 
expensive.  The choice you make about organization depends closely on 
how you expect to use the information.

[If this were a real textbook, you'd probably see some pictures
of books arranged in shelves on different ways.  Aren't you glad
that I'm too lazy to insert those?]

How can algorithms support representations or organizations?
Certainly, the "number the books" strategy above assumes that you
understand the algorithm for quickly finding a book (admittedly,
you could also find books a bit more slowly if you looked at them
one by one).  At a lower level, we make make decisions on how to
represent values as sequences of 0's and 1's and need algorithms
to extract the underlying values.

Because the ways in which we represent and organize information can be 
so closely tied to the algorithms that we write, computer scientists
think a lot about organization and representation, and about the
relationships between algorithms and data.  The discipline has certainly
evolved over the years.

In the early days of computing, computer programmers and computer
scientists started to design *data structures* that provided
particular ways to organize and access information.  Arrays provided
fast indexed access to elements, but with some limitations in our
ability to add elements beyond a certain point.  In contrast, linked
lists provided mechanisms for dynamic collections of values, but
with limitations on our ability to quickly access any particular
element.

At some point along the way, computer scientists discovered the
benefit of *abstraction* in thinking about organization and
representation (and in many other areas of CS).  One particularly
useful form of abstraction is the separation of the *interface*
(what you want to do with the information) from the *implementation*
(how you achieve that goal).  You've probably thought about such
abstraction as you've written procedures (functions, methods).  In
writing procedures, you think about *what* the procedure is supposed
to accomplish and *how* the procedure accomplishes it.  A client who
uses the procedure should need only know what the procedure does.
If we separate the interface from implementation, we allow ourselves
to change implementations without affecting the client code.

In the world of data representation and organization, I tend to use
the term *Abstract Data Type* when talking about what we want to
do with the data and the term *Data Structure* when talking about
how we achieve those goals.

In [a separate reading](../readings/designing-adts), you considered
a process for designing abstract data types.  Let us now turn our
attention to the design and implementation of data structures.

The LIA Approach to data structure design
-----------------------------------------

When we've completed our ADT analysis using the PUM approach, we should 
know three things about the ADT:

* The primary *philosophy* of the ADT.  That is,
  what are the primary organizing principles?  We might say that we
  have an organization in which we refer to values by numeric index,
  that we have an organization in which it is easy to add or
  remove values, that there is a known order in which we will visit
  values (perhaps specified by the client programmer, perhaps an
  implicit order), and so on and so forth.
* Some *use cases* that show how the ADT might
  be used.  One important aspect of use cases is that they also
  tell you how often certain methods are likely to be used.  
  Such information is quite valuable when we start to implement
  the ADT, as we will want to make sure that the implementations of
  frequently used methods are efficient, even if we may have to
  make infrequently used methods less efficient.
* A list of *methods* that we need in order to
  achieve the use cases.  From the implementation perspective, these
  are the most important part of the ADT, since they tell us exactly
  what functionality we need to build.

How do we decide how to implement an ADT (build a data structure)?  As
in the case of ADT's, you'll find that you may have to consider
different approaches and explore the benefits and drawbacks of each.
In doing so, I find it useful to employ an approach that I call
LIA, for "layout, implement, analyze".

First, you need to choose a way to *lay out* the data in memory.
There are two basic approaches, with some variants in each.  You
might reserve a large area of memory to hold lots of values, and
have some policy that says where in memory each value goes.  I will
usually refer to this as an "*array-based*" approach, since arrays
are usually just large area of memory.  You might also reserve a
separate pieces of memory for each value (or perhaps each few
values), and add information about the relationships between these
different pieces of memory.  I will usually refer to this as a
"*linked*" approach, since we make links between different pieces.

Your choice of a basic approach should then guide how you 
*implement* each of the methods from the ADT.  If you have chosen
the array-based approach, you will need to consider how to achieve
each method by indexing into the array and perhaps moving elements
around in the array.  If you have chosen the linked approach,
you will need to consider how to implement each method in by
implementing small pieces or making or changing links between
pieces.

Finally, you need to *analyze* each of the implementations you
decided on.  Is it likely to be slow or fast?  (We'll look at more
precise meanings of "slow" and "fast" later in the semester; one
simple one is to think about how many values you need to look at
in order to achieve the method's goal.)

An example: Immutable lists
---------------------------

You may recall the "Immutable List" ADT we designed in 
the [reading on ADT design](../readings/designing-adts).
That ADT had four basic methods.

* `ImmutableList list(Object[] values)` - Create an immutable list
  from an array of values.
* `Object car(ImmutableList list)` - Get the first value in a
  non-empty immutable list.
* `ImmutableList cdr(ImmutableList list)` - Get an immutable
  list with all but the first value in non-empty immutable list 
  `list`
* `boolean nullp(ImmutableList list)` - Determine if a list
  is empty.

How do we implement these methods?  We have two choices (or at least
two normal starting points): We could use arrays or we could use
linked structures.  Let's try each.

### Implementing immutable lists with arrays

We'll start with arrays.  It turns out that there are a variety of ways
we can think about arranging the elements of a list in an array.  The
most straightforward is that the size of array is exactly the size of
the list, and the elements are in the array are in the order of the
elements in the list.

We're likely to need to know the size of the array (at least in order
to decide if it contains no elements).  Some languages, like Java,
will store the size of the array for us.  Other languages, like C,
will require we store the size of the array ourselves.

We have the basic layout.  How do we implement each of the methods?

* The `list` method should be straightforward.  We simply
  make a copy of the array (noting the size of the array if necessary).
* The `car` method is also straightforward.  We grab the first
  element in the array (in most languages, the element at index 0).
* The `cdr` method may require a bit more thought.  We don't
  want to affect the original array, since we may need to use it later
  (after all, we did call these *Immutable* lists.
  Hence, we probably need to build a new array that is one smaller,
  and copy over all but the first element.
* Fortunately, the `nullp` method is also straightforward,
  particularly because we thought about it a bit in advance.  A list
  is empty if the array has size 0, so we need only get the size of
  the array and compare it to 0.

In pseudocode,

```java
ImmutableList list(Object[] values) {
  ImmutableList result = allocate(ImmutableList);
  result.array = allocate(values.size);
  for (i = 0; i < values.size; i++) {
    result.array[i] = values[i];
  } // for
  return result;
} // list

Object car(ImmutableList list) {
  return list.array[0];
} // car

ImmutableList cdr(ImmutableList list) {
  ImmutableList result = allocate(ImmutableList);
  result.array = allocate(list.array.size-1);
  for (i = 1; i < list.array.size; i++) {
    result.array[i-1] = values[i];
  }
  return result;
} // cdr

boolean nullp(ImmutableList list) {
  return (list.array.size == 0);
} // nullp
```

Let's analyze each of these methods in terms of the number of elements
in the list.

* `list`.  Allocating memory is (usually) a single step.
  If there are N values in the list, copying them from one the input
  array to the new array will require N copies.  The cost of this
  method is directly proportional the number of values in the list.
* `car`.  Referencing an element in an array is fast.
  So this method is fast, a constant number of steps that
  is independent of the size of the array.
* `cdr`.  Once again, allocating memory is usually a single
  step.  And once again, we'll need to copy almost all of the elements.
  So `cdr` is also directly proportional to the number of
  values left in the list.
* `boolean nullp(ImmutableList list)`.  Getting the size
  of an array should be a fast method, whether that size is provided
  by the language or we've stored the size in a field.  So, finding
  out whether an array is empty should be fast, a constant number of
  steps that is independent of the size of the array.

That's not too bad.  Two of the methods are really fast.  Two methods
are slow, but at least one probably has to be.  That is, we expect
that since creating a list may require looking at each element of
the list, the number of steps to create a list will always be
directly proportional to the number of values in the list.

Can we do better?  We might be able to if we focus on ways to
improve `cdr`.

### Another implementation of immutable lists with arrays

The decision to use an array does not have to be the end of our design
of the layout of a data structure.  One issue we discovered in our first
approach was that using a separate array for each immutable list in
turn required a lot of effort (and, presumably, a lot of memory).  What
if instead of making a copy each time, we used the same array?

Since the list is supposed to be immutable, we probably shouldn't
rearrange the values in the array.  However, we could store an additional
piece of information.  In particular, we might note which element in
the array is the start of the current list.  Let's consider that
approach.  Recall that we need to store two values for each immutable
list: An underlying array and an index into that array.

* For `list`, we will once again have to build a new array
  and copy the values from the parameter array.  We'll also need to
  initialize the index to 0.  This method will take a number of
  steps proportional to the number of elements in the array.
* For `car`, instead of always looking at the first position
  in the list, we will instead look at the position given by the index.
  This method should only take a constant number of steps, independent
  of the number of elements in the array.
* For `cdr`, we won't copy the array.  Instead, we will increment
  the index.  More precisely, we will make a new structure that holds the
  same array, but with an index field that is one higher than the index
  field of the parameter.  This method should only take a constant 
  number of steps.
* The `nullp` method will be more complex, since we are
  not changing the size of the array.  Instead, we will note that the
  list is empty if the index is greater than the largest possible
  index in the array (in Java and C, that's when the index is greater
  than or equal to the size of the array).  Even though this implementation
  is more complex, it should still be a constant number of steps that
  is independent of the number of elements in the array or list.

For those who prefer to think about these steps in code, some
pseudocode follows.

```java
ImmutableList list(Object[] values) {
  ImmutableList result = allocate(ImmutableList);
  result.array = allocate(values.size);
  for (i = 0; i < values.size; i++) {
    result.array[i] = values[i];
  } // for
  result.index = 0;
  return result;
} // list

Object car(ImmutableList list) {
  return list.array[list.index];
} // car

ImmutableList cdr(ImmutableList list) {
  ImmutableList result = allocate(ImmutableList);
  result.array = list.array;
  result.index = list.index + 1;
  return result;
} // cdr

boolean nullp(ImmutableList list) {
  return (result.index >= list.array.size);
} // nullp
```

This implementation is a bit more complex, and probably requires
more documentation for the programmers who may have to maintain or
extend it.  However, the improved speed of `cdr` (and the implicit
reduction in memory usage) probably makes it worth it.

### Implementing immutable lists as linked structures

We have what seems to be a successful implementation of immutable
lists using arrays.  Is it still worth looking at a linked
implementation?  Yes, primarily because we should consider what a
linked implementation looks like.

For each element in the list, we will store two values: The element,
and a link to the next element in the list.  We'll use a special
value, `null` to represent no remaining elements.  We typically use
"node" to name the combination of element and link in a linked
structure.

Our `LinkedList` structure can be an alias for the
node structure, or it can combine a node and some other information.
For now, let's just make it an alias for the node structure.

* To implement `list`, we'll need to build and link a lot
  of nodes.  First, we build a node for the last element of the array
  and set its next link to `null`.  After that, we build a node for
  the all-but-last element of the array, and set its next link to the node
  we just created.  After that, we build a node for the all-but-all-but-last
  element of the array, and set its next link to the node we just created.
  We continue this process until we reach the start of the array.
* To implement `car`, we take the element portion of the node.
* To implement `cdr`, we can just return the linked next node.
* To implement `nullp`, we check whether or not the node is the special 
  value `null`.

This implementation seems about as efficient as the revised array
implementation.  The `list` method does work directly proportional
to the number of elements in the list, and everything else should
take a constant number of steps.  However, the linked approach may
also require more memory, since we store not only the values, but
also the links.  (Of course, the revised array implementation
required an index for each list, so the size is likely similar.)
For those who worry about other details of memory usage, the array
implementation also keeps values closer together in memory.

Which should you use?  For many programmers, the array-based
implementations are simpler and less error prone, particularly in
languages like C that require you to be very careful in keeping
track of the memory you have allocated.  However, if you are
comfortable building linked structures (and that's a skill set
you should develop), the linked approach is quite straightforward.

Wrapping up
-----------

What might you have learned in this reading?  I hope that you've
started to understand the LIA (Layout, Implement, Analyze) approach
to implementing ADTs.  As we look at each new ADT, I'll ask you for
these steps and we'll think about each in turn.  Once again, I also
hope you've learned that there are often multiple approaches to
solving problems, and that it's worth the effort to think about your
alternatives.


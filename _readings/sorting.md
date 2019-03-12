---
title: Sorting basics
summary: |
  We consider some basic issues pertaining
  to sorting and explore a bit of an object-oriented approach to sorting.
prereqs: |
  [Interfaces](../readings/interfaces).
  Classes and inheritance.
  Asymptotic analysis.
---
Introduction
------------

Sorting a collection of values -- arranging them in a fixed order, usually
alphabetical or numerical -- is one of the most common computing
applications.  When the number of values is even moderately large, sorting
is such a tiresome, error-prone, and time-consuming process for human
beings that the programmer should automate it whenever possible.  For this
reason, computer scientists have studied this application with extreme care
and thoroughness.

One of the clear results of their investigations is that no one
algorithm for sorting is best in all cases.  Which approach is best
depends on whether one is sorting a small collection or a large
one, on whether the individual elements occupy a lot of storage (so
that moving them around in memory is time-consuming), on how easy
or hard it is to compare two elements to figure out which one should
precede the other, and so on.  In this course we will examine a
variety of useful algorithms for sorting, including *insertion
sort*, *selection sort*, *merge sort*, *quick sort*, and *heap sort*.

Because this course also emphasizes issues in object-oriented design
and general software design, we will also explore ways to incorporate
more general design concepts in the software ecosystem we build.

Designing a `sort` method
--------------------------

### Issue: What do we sort?

Clearly, sorting is an operation that we apply to collections of
values.  But what kinds of collections?  It makes sense to sort an
array, because we know the relative positions of the elements in
the array.  What happens after we've finished sorting an array?
Most likely, the value in position i is less than or equal to the
value in position i+1 for all reasonable values of i.  We'll return
to the question of what it means to be "less than or equal to" in
a subsequent section.

It also makes sense to sort a list, because lists have an implicit
ordering.  In effect, sorting a list takes the user-defined ordering
and replaces it with the "sort order".  When we're done sorting,
we know that the list is sorted because when we iterate the list,
we see the values in order (usually from smallest to largest).

Does it make sense to sort other data structures or ADTs?  It may,
but sorting does imply a linear ordering of elements, and lists and
arrays are the primary collections in which we order elements
linearly.  We may eventually design ADTs like "sorted list" in which
the elements are always iterated in sort order, but we'll leave
that as a topic for a future reading.

### Other parameters

Other than the collection to sort, are there other parameters we
should include in our sorting method?  It depends a bit on the
values we want to sort.  Some values, such as Integers, have a
natural order.  But others, such as People, don't have a natural
order.

When the Java collections framework was added to the Java programming
language, the designers acknowledge the two situations by creating
two interfaces.  Objects that implement the `Comparable<T>` interface
have a `compare(T other)` method that we can use to compare them
to other objects.  The `compare` method returns a negative number
if this object should precede the other object, zero if the two
objects are the same, and a positive number if this object should
follow the other object.

If we wanted to write a static method to sort comparable objects,
we might declare it something like the following.

```java
public static <T extends Comparable<T>> void sort(T[] values)
```

That is, the `sort` sorts arrays of values of a type whose objects
are comparable to each other.  (Yes, there are some strange
complexities to Java's generics.  You'll get used to them eventually.)

However, more frequently we'll want to work with collections of
values that don't have a natural order.  In that case, we'll want
to use a `Comparator` as a parameter.  `Comparator` objects provide
a ``compare`(Object o, Object p)` method with two parameters, instead
of one.  In this case `compare` returns a negative number if `o`
should precede `p`, zero if the two are equal, and a positive number
of `o` should follow `p`.

Static methods vs. objects
--------------------------

In the discussion above, we wrote the signature for a static sorting
method for sorting arrays of objects that implement the `Comparable`
interface.  But is that really the best approach?  One of the
important aspects of object-oriented design (and, really, any good
design) is that you can easily replace one component of a program
with a component that can achieve similar goals.  Static methods
make that approach much harder.

So, what should we do instead?  We should create objects that know
how to sort.  How can we use them interchangably?  We design a
common interface.  Then, each time we have a new sorting algorithm,
we simply make it meet that interface.  Our programs that need to
sort can easily replace one sorter with another sorter.

Here's the start of such an interface.

```java
/**
 * Things that know how to sort homogeneous collections.
 */
public interface Sorter<T> {
  /**
   * Sort an array in place.
   *
   * @param vals, an array to sort.
   * @param order, the order by which to sort the values.
   * @return
   *    The same array, now sorted.
   * @pre
   *    order can be applied to any two values in vals.
   * @pre
   *    VALS = vals.
   * @post
   *    vals is a permutation of VALS.
   * @post
   *    For all i, 0 < i < vals.length,
   *      order.compare(vals[i-1], vals[i]) <= 0
   */
  public T[] sort(T[] values, Comparator<T> order);
} // interface Sorter<T>
```

That's not too bad, is it?  And since there's only one function in the
interface, we can implement it with a lambda if we'd like.

Additional issues
-----------------

Ah, if life (or at least program design) were only that simple.  However,
there are a number of other issues the designer of a sorting algorithm
must consider, even if the algorithm is only sorting arrays.

* Do we mutate the underlying array (sort "in place") or do
  we return a new array?  Sometimes it is important to maintain the
  original array; perhaps other parts of the program have assumptions
  about the order of those elements, or have cached part of the array.
  On the other hand, building a new array is costly in terms of memory
  as we are now using twice as much memory as we did before.
* What do we do with two elements that are equal?  If the first preceded
  the second before we sorted the array, should the first still precede
  the second?  Sorting algortihms that preserve the order of equal 
  elements are called "stable" sorting algorithms.  
  In some situations, it doesn't matter much whether the algorithm is
  stable or not.  In others, it matters very much.  For example, some
  people sort by multiple keys by first sorting on the secondary key
  and then sorting on the primary key.  This mechanism only works when
  the sorting algorithm is stable.  However, stability can be hard to
  achieve, and may therefore either limit our choice of algorithm or
  complicate our implementation.

The normal approach when specifying a generic sorting algorithm on
arrays is to make the algorithm in-place and to make stability 
optional.  People who implement stable sorting algorithms can 
specify in documentation that the algorithm is stable, and clients who
need stable sorting algorithms must be careful to use only those.

The in-place/out-of-place issue is a bit easier to resolve.  We can
turn an in-place sorting algorithm into an out-of-place sorting algorithm
by cloning the array before sorting.  And we can turn an out-of-place
sorting algorithm into an in-place sorting algorithm by copying the
values back when we are done.  (We don't save any space, but at least
we can match the semantics that a client desires.)

Insertion sort
--------------

Insertion sort is one of the simplest sorting algorithms.  For
in-place sorting of arrays, it conceptually partitions the array
into two parts, the sorted values and the unsorted values.

```text
+----------+----------+
|  Sorted  | Unsorted |
+----------+----------+
|          |          |
0          i          vals.length
```

At each step, we take the first element in the unsorted section,
find the correct space to put the element in the sorted section,
shift things over to make room, and put it in place.

For example, consider the following array

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
```

Any one-element subarray is sorted, so we can start our program
in the following state.

```
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
    |
    i
```

  The first thing in the unsorted section is "l", and
  it's bigger than the largest thing in the sorted section, so we can
  just move the barrier over.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
        |
        i
```

The first thing in the unsorted section is "p", and it's
bigger than the largest thing in the sorted section, so we can move the
barrier over again.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
            |
            i
```

Okay, now things are getting a bit more interesting.  The "h"
is up next.  It belongs in position 1, after the "a" and
before the "l".  So we shift things over.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|   |  l|  p|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
                |
                i
```

And then we put the "h" in its proper place.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  h|  l|  p|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
                |
                i
```

To put that "a" in the right position, we have to shift elements
once again.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|   |  h|  l|  p|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
                    |
                    i
```

And then we put the "a" in place.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  h|  l|  p|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
                    |
                    i
```

Of course, there's no magic operation that quickly shifts a whole
subarray over.  (Well, there are Java operations, but they
still need to copy piece by piece.)  Hence, when we implement the
insertion sort algorithm, we typically just shift the element
left again and again and again.  Let's look at what happens with
the "b"

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  h|  l|  p|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
                      * |
                        i
```

The "b" is out of place, so we swap it left.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  h|  l|  b|  p|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
                  *     |
                        i
```

  The "b" is still out of place, so we swap it left.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  h|  b|  l|  p|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
               *        |
                        i
```

  The "b" is still out of place, so we swap it left.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
           *            |
                        i
```

The "b" is now in place.  We're done with the insert step and are
ready to move on to the next letter to insert.

Note that, in practice, we just want to shift elements, rather than
writing the "b" into each spot, since we're likely to just overwrite
it again a moment later.


Analyzing insertion sort
------------------------

Let's analyze insertion sort to see just how fast (or slow) it is.

The main step (insert a value from the unsorted section into the
sorted section) runs *n* (or maybe *n*-1) times, once for each element in
the array.  If we're really lazy, we might say that each insertion 
could cause us to shift *n*elements.  Hence, we have an O(*n*<sup>2</sup>) algorithm.

But wait!  Inserting the first element took 0 steps - after all, a
one element array is sorted.  And inserting the second element could
have taken at most one swap.  Similarly, inserting the third element
could have taken at most two swaps.  So, the running time (at least
in terms of compare and swap) is bounded above by 0 + 1 + 2 + 3 +
...  + *n*-1.  But we all know that sum is equal to *n*(*n*-1)/2.
So, the algorithm is bounded above by (*n*<sup>2</sup>)/2 - *n*/2.
The *n*<sup>2</sup> term dominates, so this analyis confirms that
it's O(*n*<sup>2</sup>).  Oh well.

Selection Sort
--------------

One concern about insertion sort is that it seems to do a lot of
swapping.  Can we do better?  The *selection sort* algorithm cuts
down on swaps by only swapping elements into the correct position.
How do we know the correct position for an element?  We don't.  But
we can quickly identify the correct element for a position.  In
particular, the smallest element goes at the front of the array.
The next smallest element goes immediately after it.  And so on and
so forth.  That is, we repeatedly select the smallest remaining
element and swap it into the correct place.

How do we visualize the state of the system at the start of each
round of selection sort?  Something like the following.

```text
+-----------------+------------------+
| sorted, smaller | unsorted, larger |
+-----------------+------------------+
|                 |                  |
0                 i                 vals.length
```

When we say the things on the left are sorted, we mean that
for all j, 0 &lt;= j &lt; i-1, vals[j] &lt;= vals[j+1].  (More
formally, we mean that the compare returns a negative number
when comparing vals[j] and vals[j+1].)

When we say that the things on the left are smaller and the things
on the right are larger, we mean that any element on the left is less
than or equal to any element on the right.

So, our algorithm looks something like the following.

```text
For i = 0 to vals.length-1
  let s be the index of the smallest remaining element
  swap(vals, i, s)</literallayout>
```

This algorithm is small and simple, as long as we can easily find the smallest
remaining element.  And that's not so hard to do.  We just keep
going through the array, keeping track of what we've seen.

So, how does selection sort work on our sample array?  Let's look.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|
i
```

The smallest element is in position 0 (or 4, or 10).  To keep the
algorithm stable, we'll use the smallest position.  We swap the
element at position 0 with the element at position 0, and increment
i.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
    |
    i
```

The smallest remaining element is in position 4 and 10.  We swap
the element at position 1 with the element at position 4 and then
increment i.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  p|  h|  l|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
        |
        i
```

The smallest remaining element is in position 10.  We swap again and advance
i.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  h|  l|  b|  e|  t|  i|  c|  p|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
            |
            i
```

 The smallest element is at position 5.  We swap and increment.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  l|  h|  e|  t|  i|  c|  p|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
                |
                i
```

And so on and so forth.

Analyzing selection sort
------------------------

Is selection sort any faster than insertion sort?  If we count only
calls to `swap`, it's much faster.  Once we've swapped a value into
place, it stays there.  Hence, we only do O(*n*) swaps.  However,
finding the smallest element in an array of size *n* still requires
*n* comparisons.  So, we do *n*comparisons for the smallest item,
*n*-1 for the next smallest, *n*-2 for the next smallest, and so
on and so forth.  Yes, it's more or less the same sum!  That is,
we do O(*n*<sup>2</sup>) comparisons.  The algorithm is still
quadratic. It may even be faster in practice because swapping is
likely to be more expensive than comparing.  However, many other
issues are involved, so we can't really say that one is much better
than the other.

Testing sorting algorithms
--------------------------

So, how do we thoroughly test a sorting algorithm?  We could choose
a few sample arrays, sort the by hand and compare them to the result
of sorting them by computer.  That's probably a good starting point,
particularly if we're thoughtful about the design of those arrays.
For example, we might play with repeated elements, elements that are
near to their correct spaces and far from their correct spaces, arrays
that are mostly sorted and arrays that are not so sorted, and so on and
so forth.

At some point, though, it becomes troublesome to generate arrays
for testing by hand.  What if we want to generate really large arrays
(e.g., so that we can actually analyze the running time)?  What if we
don't trust that we've really explored every edge and corner?  In
situations like these, it may be useful to write randomized tests.
But how do we know that a sorting algorithm correctly sorts a randomized
array?  After all, an accident in coding might lead to the accidental
duplication of one element and the loss of another.  And, if the resulting
array is in order, we might not detect the inappropriate modification
of data.

There's a surprisingly easy solution.  Instead of starting with a
completely random array, you start with an array that is already
sorted, but has been generated "randomly".  You then
permute the array, sort it, and compare it to the original.  Magically,
you've tested both that the result is sorted and that elements are 
neither added nor lost.

It's also easy to generate a random sorted array.  For example,
we could put a random value in position 0 of the array and generate
each subsequent value by adding a random number between 0 and 5 to
the previous value.

Over the next few days, you'll have a chance to explore unit tests
that use this approach.

Additional thoughts
-------------------

As the notes above may have suggested, designing a good infrastructure
for sorting is a non-trivial task and draws upon your experience in 
algorithm design, nobject-oriented design, creative problem solving,
and more.  We'll continue exploring these issues in future readings.

Citations
---------

Portions of this reading are based closely on the reading on sorting
designed for Grinnell's CSC 151.  Janet Davis, Samuel A. Rebelsky,
John David Stone, Henry Walker, and Jerod Weinman all contributed
to that reading.


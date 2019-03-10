---
title: Linear and binary search
summary: |
  We consider the standard problem of searching an array for a value
  or values.  That is, we consider algorithms that, given a target
  value, a collection of values, and a notion of equality, find the
  position of the target value.  We explore two common searching
  algorithms, linear search and binary search.  We also consider
  ways in which object-oriented design (and its instantiation in
  the Java programming language) may lead us to think about searching
  in slightly different ways.
---
Introduction
------------

One of the most common tasks computer programs do is *
search* - look through a collection of information for
something matching particular criteria.  Sometimes the search is
huge and illformed, as in Google searches.  Sometimes the search
is small and precise.

There are a host of ways to organize information to make searching
faster and easier.  We'll consider some of those mechanisms 
this semester.  For now, we'll start with one of the simplest
phrasings of the search problem: *given a value to search for
and an array of values to search in, find the index of the value
in that array*.

Linear search
-------------

The simplest searching algorithm is often called *linear
search*, and involves looking at each element in turn
until we find one that matches.

```text
For each index, i, in values
  If values[i] is equal to val
     return i;
If the value was never found, signal that problem
```

Analyzing linear search
-----------------------

How long does linear search take?  Well, it depends.  If the thing
we're searching for appears near the beginning of the array, it takes
only a few steps.  If it appears at the end of the array, we'll need
to look at almost all of the elements of the array.   And, if it doesn't
appear at all, we need to look at every element.

Since, in the worst case, we look at every element, the body of our
loop will run O(n) times.  Each repetition of the loop requires a
constant number of steps (or so we hope - comparison *should* be
fast), so the algorithm takes O(n) time.

Linear search in Java
---------------------

Is there anything special about linear search in Java?  A few things.
Let's start with the "obvious" implementation.

```java
/**
 * Search values for an object equal to value.
 *
 * @param value
 *   A value to search for
 * @param values
 *   An array of values to search
 * @return index
 *   The index of a value equal to value
 * @pre
 *   Elements of values are all comparable to value using equals.
 * @post
 *   value.equals(values[index])
 * @throws Exception
 *   If the value is incomparable to an element of values.
 * @throws NotFound
 *   If no equal value appears.
 */
public static <T> int search(T value, T values[]) throws NotFound, Exception {
  for (int i = 0; i < values.length; i++) {
    if (value.equals(values[i])) {
      return i;
    } // if
  } // for
  throw new NotFound(value + " does not appear");
} // search
```

Experienced functional and object-oriented programmers might note
that an important element seems to be implicit in this procedure.
That is, we use the `equals` method of the value to compare values.
However, it turns out that when we're searching an array, we may
want to search using different criteria.  For example, if we're
searching for a person, we might want to match by last name, id
number, or even some physical characteristics.  Hence, rather than
just relying on the `equals` method, we really should be passing
in an object that knows how to compare things for equality.  (A
functional programmer would just pass in a function, but we need
objects in Java, even if they are objects that represent functions.)
More broadly, we should probably use a predicate.

With predicates, our linear search might look something like the following.
(We've ellided most of the documentation, since it's not much different.)

```java
/**
 * Search values for a value for which pred holds.
 */
public static <T> int search(Predicate<? super T> pred, T[] values)  throws NotFound, Exception {
  for (int i = 0; i < values.length; i++) {
    if (pred.test(values[i])) {
      return i;
    } // if
  } // for
  throw new NotFound(value + " does not appear");
} // search
```

What's the `? super T`?  That's a "type wildcard".  We want a predicate
that works for values of type `T`.  But we'd also accept any predicate 
that works for values that are a superclass or interface of `T`.

Should we really be searching arrays?  There are certainly times that
it is useful to search arrays.  However, if we want to write general
code, we should probably search any iterable collection.  We will 
consider how to do that in the lab.

Random search
-------------

Some folks worry that linear search is biased toward elements that
appear at the start of the array and hence prefer to search arrays
"randomly".  Here's a simple version of a random search
algorithm.

```text
Repeat
  i = a random number in the range 0 .. n-1
  If values[i] is equal to val
     Return i;
Until we've examined all of the values in the array
If the value was not found, signal an error
```

Of course, this implementation has two significant problems.
First, it can be hard to tell if we've examined all of the values
in the array.  (I'm sure that you can figure out some strategies.)
Second, we will often look at some values more than once, which is
inefficient.  What's the solution?  Instead of randomly generating
an index, we start with the list of all possible indices and 
randomly permute it.  We can then just look at the elements in
that order.  We may return to this approach in the future.

Binary search
-------------


The *binary search* algorithm is a standard divide-and-conquer
algorithm.  It requires that the input be sorted.  At the highest
level, binary search does the following

```text
While elements remain
  Look at the middle element
  If it matches our target, return the index of the middle element
  If it is smaller than our target, throw away the bottom half
  If it is larger than our target, throw away the top half
If no elements remain, report failure
```

But how do we "throw away" elements in an array?  Traditionally,
we keep track of the lower and upper bounds of the portion of the
array of interest.  So, initially, the lower bound is 0 and the
upper bound is length - 1.

```text
While lb <= ub
  mid = midpoint(lb, ub);
  If values[mid] equals value
    return mid;
  Else if values[mid] < value
    lb = mid + 1;
  Else
    ub = mid - 1;
Report Failure
```

We can also express this algorithm recursively.

```text
binarySearch(value, values, lb, ub)
  If (lb > ub)
    Report Failure
  mid = midpoint(lb, ub);
  If values[mid] equals value
    return mid;
  Else if values[mid] *lt; value
    return binarySearch(value, values, mid+1, ub);
  Else
    return binarySearch(value, values, lb, mid-1);
```

Why prefer one over the other?  I find the recursive version easier
to analyze.  However, unless your compiler optimizes tail calls,
the iterative version is likely to be slightly faster.

Analyzing binary search
-----------------------

As noted above, the recursive version is probably a little easier
to analyze.

What's the running time for input of size n?  Well, we do a constant
number of tests (no more than three, I believe) and we also have to
compute the midpoint of two values.  And then we probably have to recurse
on about half of the input (maybe exactly half, maybe slightly less.)
That gives the recurrence relation

* time(n) = c + time(n/2)
* time(1) = b

How can we solve this relation?  Let's try a few values.

* time(1) = b
* time(2) = c + time(1) = c + b
* time(4) = c + time(2) = c + c + b = 2c + b
* time(8) = c + time(4) = c + 2c + b = 3c + b
* time(16) = c + time(8) = c + 3c + b = 4c + b

Do you see a pattern yet?  It looks like
time(2<superscript>k</superscript>) = k*c + b.  (You can prove this
via induction, if you'd like.)  Since k grows as the log of
2<superscript>k</superscript>, it looks like binary search has
running in O(log<subscript>2</subscript>n).

But wait!  We've only analyzed powers of 2.  Does that matter?
Probably not.  The upper bound on the running time is clearly 
increasingly monotonically.  So, the number of steps, for any
n is bounded by the number of steps for a power of two greater than
n.  (You can finish this proof, too.)

Binary search in Java
---------------------

In implementing binary search, one important question is how we compare
individual elements.  In linear search, we had to compare only for
equality, which allowed us to use the built-in `equals`
method (or a few alternates).  In binary search, we need to know
if the value we're looking at should appear before or after a
non-equal value.  (We also still need to know if it's equal.)
Fortunately, Java provides an interface for just that purpose,
[`java.util.Comparator`]({{ site.java_api }}/java/util/Comparator.html).
You should read the documentation for that class.  However, here's
a quick snippet of what it might look like.

```java
public interface Comparator<T> {
  /**
   * Compare o1 and and o2 for order.  Return a negative number of o1
   * comes before o2, zero if the two are equal, and a postive number
   * if o1 comes after o2.
   */
  public int compare(T o1, To2);
} // Comparator<T>
```

We'll leave the implementation of the generalized binary search as 
an exercise for you.

Citations
---------

This reading is based on [a similar reading from the fall 2014 section
of CSC 207](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2014F/readings/search.html).

Portions of that reading were based on materials prepared for Grinnell
College's CSC 151 by Janet Davis, Samuel A. Rebelsky, John David
Stone, Henry Walker, and Jerod Weinman.


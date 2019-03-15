---
title: Linear and binary Search in Java
summary: |
  In today's laboratory, you will explore issues pertaining to
  search in Java.  Along the way, you will not only consider the
  search algorithms, but explore some program design issues
  in Java.
---
Preparation
-----------

a. You are likely to find it useful to have <ulink
url='../readings/search.html'>the corresponding reading</ulink>
open in another window.

b. Create a new Eclipse project and Java package for this lab.
(I'd recommend that you also create a Git repository, but it's up
to you.)

c. Create a new class, `Utils`, that will hold much of the library
code that you will write today.

d. Create a new class, `Experiments`, that will hold your experiments
for today's class.  As you might expect, `Experiments` should include
a `main` method.

e. Add the following declaration to the `main` method of
`Experiments`.

```java
    String[] tmp = 
        new String[] { "alpha", "bravo", "charlie", "delta", "echo",
                       "foxtrot", "golf", "hotel", "india",
                       "juliett", "kilo", "lima", "mike", 
                       "november", "oscar", "papa", "quebec",
                       "romeo", "sierra", "tango", "uniform",
                       "victor", "whiskey", "xray", "yankee", "zulu" };
    ArrayList<String> strings = new ArrayList<String>(Arrays.asList(tmp));
```

Exercises
---------

### Exercise 1: Generalized linear search, revisited 

You may recall from the reading that we often search arrays for
values (or just the first value) that meets some predicate.  At
the end of the discussion, we noted that it would be even more general
to implement a linear search for arbitrary iterable objects.

a. Write a procedure that searches an iterable for the first value
for which a predicate holds.

```java
/**
 * Search values for the first value for which pred holds.
 */
public static <T> T search(Predicate<? super T> pred, Iterable<T> values) throws Exception {
  // ...
}
```

b. What do you think the following expression expression does?

```java
    String ex1b = Utils.search((s) -> s.length() == 6, strings);
```

c. Confirm your answer experimentally.

d. Write an expression to find the first element of `strings` that
contains a `u`.  (You may find the `contains` method in the
`String` class useful.)

e. Do you expect to be able to use search with `tmp`?  Why or why
not.

f. Check your answer experimentally.

### Exercise 2: Binary Search in arrays of integers

Although the reading introduced a variety of techniques for designing
generalized search algorithms, it's probably easiest to start by
focusing on a single type.

Implement the following procedure.

```java
/**
 * Search for val in values, return the index of an instance of val.
 *
 * @param val
 *   An integer we're searching for
 * @param values
 *   A sorted array of integers
 * @result
 *   index, an integer
 * @throws Exception
 *   If there is no i s.t. values[i] == val
 * @pre
 *   values is sorted in increasing order.  That is, values[i] <
 *   values[i+1] for all reasonable i.
 * @post
 *   values[index] == val
 */
public static int binarySearch (int i, int[] vals) throws Exception {
  return 0;   // STUB
} // binarySearch
```

### Exercise 3: Testing our algorithm

Evidence suggests that (a) many programmers have difficulty implementing
binary search coorectly and (b) many programmers do only casual testing
of their binary search algorithm.  But it's really easy to write a
relatively comprehensive test suit for binary search. 

```java
For each s from 1 to 32
  Create an array of size s, containing the values 0, 2, 4, ... 2*(s-1)
  For all i from 0 to s-1, inclusive
      // Make sure that value 2*i is in position i
      assert(binarySearch(2*i, array) == i)
      // Make sure that odd values are not in the array
      assertException(binarySearch(2*i+1, array))
  assertException(-1, array)
```

Implement this test.  Then repair any bugs you find in your implementation
of binary search.

Note that I've found this test very useful.  A surprising number of
pieces of code fail just one or two of the many assertions in this test.

*Citation:* This test is closely based on one suggested
by Jon Bentley in a _Programming Pearls_ column.

### Exercise 4: Care In checking midpoints

As binary search is phrased in the reading, when we note that the
middle element is not equal to the target value, we either set `ub`
to `mid-1` or `lb` to `mid+1`.  But programmers often get confused
by the need for the `+1` and `-1`.

Determine experimentally what happens if you leave out the `+1` and
`-1`.  Explain why that result happens.

### Exercise 5: An alternate approach

In implementing binary search, you either wrote a loop or a
recursive procedure.  Write a second version of binary search
that uses the other approach.

### Exercise 6: "Timing" search

In theory, binary search should take O(log<subscript>2</subscript>n)
steps.  Does it really?  Augment each of your methods so that it
counts the number of repetitions (loop) or calls (recursive procedure).
It's probably easiest to create global variables that you set to
0, and then increment at the top of the loop body or at the start
of the procedure.

Build some moderately large arrays (at least 1000 elements) to verify
that you get the expected running times.

### Exercise 7: Searching for the smallest value

a. Implement the following procedure:

```java
/**
 * Find the "smallest" integer in an array of integers
 */
public static Integer smallest(Integer[] values, Comparator<Integer> compare) {
   return null; // STUB
} // smallest(Integer[])
```

b. Run your procedure with a comparator that does the standard
integer comparison.

c. Run your procedure with a comparator that does reverse integer
comparison (e.g., if x < y, `compareTo(x,y)` should 
return a positive number.

d. Run your procedure with a comparator that does closest-to-zero
comparisons.

For those with extra time
-------------------------

_If you find that you have extra time, you might try the following
exercises._

### Extra 1: Generic binary search

Implement a generic binary search that takes a comparator as
a parameter.  Once again, it should return the index of a value that
we've found (or should throw an exception if no such character
exists).

```java
public static <T> int binarySearch(T value, T[] values, Comparator<T> compare) throws Exception {
  // ...
} // binarySearch
```

### Extra 2: Testing generic binary search

How could we test our generic binary search?  We could rewrite our
tests.  Alternately, we could rewrite our integer `binarySearch`
method to call this method.  Try the latter.

### Extra 3: Searching arrays of strings.

Use the generic `binarySearch` procedure to search the array of strings
you created at the start of this lab.

Citations
---------

This lab is closely based on [a similar lab from the Fall 2014 section
of 207](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2014F/labs/search.html).


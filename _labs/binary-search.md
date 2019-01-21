---
title: Binary Search in Java
summary: |
  In today's laboratory, you will explore issues pertaining to
  search in Java.  Along the way, you will not only consider the
  binary search algorithm, but explore some program design issues
  in Java.
---
Preparation
-----------

  a. You are likely to find it useful to have <ulink
  url='../readings/search-reading.html'>the corresponding reading</ulink>
  open in another window.

  b. Create a new Eclipse project and Java package for this lab.
  (I'd recommend that you also create a Git repository, but it's up
  to you.)

  c. Create a new class, `Utils`, that will hold much of
  code that you will write today.

Exercises
---------

### Exercise 1: Binary Search in Arrays of Integers

  Although the reading introduced a variety of techniques for designing
  generalized search algorithms, it's probably easiest to start by
  focusing on a single type.

  Implement the following procedure.

<pre>
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
 *   values is sorted in increasing order.  That is, values[i] &lt;
 *   values[i+1] for all reasonable i.
 * @post
 *   values[index] == val
 */
public static int binarySearch (int i, int[] vals) 
  throws Exception 
{
  return 0;   // STUB
} // binarySearch
</pre>

### Exercise 2: Testing Our Algorithm

  Evidence suggests that (a) many programmers have difficulty implementing
  binary search coorectly and (b) many programmers do only casual testing
  of their binary search algorithm.  But it's really easy to write a
  relatively comprehensive test suit for binary search. 

<literallayout>For each s from 1 to 32
    Create an array of size s, containing the values 0, 2, 4, ... 2*(s-1)
    For all i from 0 to s-1, inclusive
        // Make sure that value 2*i is in position i
        assert(binarySearch(2*i, array) == i)
        // Make sure that odd values are not in the array
        assertException(binarySearch(2*i+1, array))
    assertException(-1, array)
</literallayout>

  Implement this test.  Then repair any bugs you find in your implementation
  of binary search.

  Note that I've found this test very useful.  A surprising number of
  pieces of code fail just one or two of the many assertions in this test.

  *Citation:* This test is closely based on one suggested
  by Jon Bentley in a Programming Pearls column.

### Exercise 3: Care In Checking Midpoints

  As binary search is phrased in the reading, when we note that the
  middle element is not equal to the target value, we either set
  `ub` to `mid-1` or `lb` to
  `mid+1`.  But programmers often get confused by the
  need for the `+1` and `-1`.

  Determine experimentally what happens if you leave out the
  `+1` and `-1`.  Explain why that result
  happens.

### Exercise 4: An Alternate Approach

  In implementing binary search, you either wrote a loop or a
  recursive procedure.  Write a second version of binary search
  that uses the other approach.

### Exercise 5: "Timing" Search

  In theory, binary search should take O(log<subscript>2</subscript>n)
  steps.  Does it really?  Augment each of your methods so that it counts
  the number of repetitions (loop) or calls (procedure).  It's probably
  easiest to create global variables that you set to 0, and then increment
  at the top of the loop body or at the start of the procedure.

  Build some moderately large arrays (at least 1000 elements) to verify
  that you get the expected running times.

### Exercise 6: Searching for the Smallest Value

  a. Implement the following procedure:

<pre>
/**
 * Find the "smallest" integer in an array of integers
 */
public static Integer smallest(Integer[] values, Comparator&lt;Integer&gt; compare) 
{
   return null; // STUB
} // smallest(Integer[])
</pre>

  b. Run your procedure with a comparator that does the standard
  integer comparison.

  c. Run your procedure with a comparator that does reverse integer
  comparison (e.g., if x &lt; y, `compareTo(x,y)` should 
  return a positive number.

  d. Run your procedure with a comparator that does closest-to-zero
  comparisons.

For Those With Extra Time
-------------------------

  Implement a generic binary search that takes a comparator as
  a parameter.

<pre>
public static &lt;T&gt; int binarySearch(T value, T[] values, Comparator&lt;T&gt; compare) 
  throws Exception 
{
} // binarySearch
</pre>


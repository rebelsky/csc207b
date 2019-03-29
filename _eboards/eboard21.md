---
title: Eboard 21  Sorting
number: 21
section: eboards
held: 2019-03-13
link: true
---
CSC 207.02 2019S, Class 21:  Sorting
====================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* A bit more on DNF
* The problem of sorting
* Testing sorting algorithms
* Insertion sort
* Selection sort
* Generalizing DNF (if time)

Preliminaries
-------------

### News / Etc.

* Beware!  Friday the 13th falls on Wednesday this month.
* Today is another lecture/recitation/discussion day.

### Upcoming work

* [Exam 1](../exams/exam01) due Thursday
    * **Do not discuss the exam with anyone (other than me)!**
* Readings for Friday: 
    * [Merge sort](../readings/mergesort)
    * [Osera 8.2: Merge sort](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap08.pdf)
* Lab writeup TBD

### Extra credit

#### Extra credit (Academic/Artistic)

* Convocation Thursday.

#### Extra credit (Peer)

#### Extra credit (Wellness)

* Sub-free spring break.

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

### Questions

_What happens when there's a fractional portion of the average?_

> It should round toward zero.

_Why doesn't my algorithm work?_

> Run it through by hand (with pictures).

> Run it in the debugger.

> Compare the outputs.

A bit more on DNF
-----------------

You have an array with three values, which we'll call red, white, and blue.
They are in no particular order.  Your goal is to rearrange it so that all
of the red are at the left, all the blue are at the right, and all the
white are in between.

Sample input:

```text
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| B | R | W | W | R | B | W | R | W | W | W | R | W | B | R | W | R |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
```

Our invariant

```text
+----------------+--------------+-------------+--------------+
|   red          |  white       |  unknown    |   blue       |
+----------------+--------------+-------------+--------------+
|                |              |             |              |
0                r              i             b              length
```

* For all j, 0 <= j < r, A[j] is red
* For all j, r <= j < i, A[j] is white
* For all j, b <= j < length A[j] is blue

Sam tends to put numbers at the left of the cell the number.

How do you make progress, while maintaining invariant?

* If A[i] is white, add 1 to i.
* If A[i] is red, we swap A[i] and A[r], add 1 to i, add 1 to r.
  `swap(A, i++, r++);`
* If A[i] is blue, we swap A[i] and A[b-1], decrement B.
  `swap(A, i, --b);`

When do you stop?  When i = b (or i >= b, if we're lazy like one of the
other Sam's)

```text
+----------------+----------------------------+--------------+
|   red          |  white                     |   blue       |
+----------------+----------------------------+--------------+
|                |                            |              |
0                r                            i,b            length
```

Initialization: What values of i, r, and b will allow us to ensure
that the invariant is met before we enter the loop?

* r = 0, i = 0, b = length

```text
+----------------+----------------------------+--------------+
|                     unknown                                |
+----------------+----------------------------+--------------+
|                                                            |
0,r,i                                                        b,length
```

Running the algorithm

```text
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| B | R | W | W | R | B | W | R | W | W | W | R | W | B | R | W | R |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|                                                                   |
0,r,i                                                               b,length

A[i] = A[0] is B, swap(A,i,--b)

+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| R | R | W | W | R | B | W | R | W | W | W | R | W | B | R | W | B |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|                                                               |   |
0,r,i                                                           b   length

A[i] = A[0] is R swap(A,i++,r++)

+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| R | R | W | W | R | B | W | R | W | W | W | R | W | B | R | W | B |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|   |                                                           |   |
0   r,i                                                         b   length

A[i] is R swap(A, i++, r++)

+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| R | R | W | W | R | B | W | R | W | W | W | R | W | B | R | W | B |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|       |                                                       |   |
0       r,i                                                     b   length


A[i] is W, increment i

+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| R | R | W | W | R | B | W | R | W | W | W | R | W | B | R | W | B |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|       |   |                                                   |   |
0       r   i                                                   b   length

A[i] is W, increment i

+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| R | R | W | W | R | B | W | R | W | W | W | R | W | B | R | W | B |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|       |       |                                               |   |
0       r       i                                               b   length

A[i] is R, swap(A, i++, r++)

+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| R | R | R | W | W | B | W | R | W | W | W | R | W | B | R | W | B |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|           |       |                                           |   |
0           r       i                                           b   length
```

Invariants can help, particularly with nationalistic array problems!

The problem of sorting
----------------------

Suppose we want a functional interface for a sorter for general arrays.  
What does it look like?

```java
public interface SorterO<T> {
  /**
   * Sort arr, returning a new array.
   */
  public T[] sort(T[] arr, Comparator<? super T> compare);
} // interface SorterO<T>
```

```java
public interface SorterI<T> {
  /**
   * Sort arr in place.
   */
  public void sort(T[] arr, Comparator<? super T> compare);
} // interface SorterI<T>
```

`<? super T>` means "T or any superclass or interface of T".  If I have
an Object comparator, I can use that to sort strings.  If I have a person
comparator, I can use that to sort students.  The ugly question mark
notation lets us write slightly more general code.

This comparator cannot use the parts of T that are custom to T.

In place or out of place?

* Out of place because it keeps the original array intact, which may
  be necessary or useful in some situations. [+1]
* In place uses less memory.
* Some use cases might make it more appropriate to do it in place.
  (E.g., if lots of things refer to the same array and it would
  be beneficial for them all to refer to the mutated array.)
* Some algorithms are easier to do in place.
* Some algorithms are impossible to do in place.

Suppose SSI implements SorterI and we need a SorterO, what do we do?

```java
public SSO<T> implements SorterO<T> {
  public T[] sort(T[] values, Comparator<? super T> comparator) {
    SorterI ssi = new SSI();
    T[] copy = values.clone();
    ssi.sort(copy, comparator);
    return copy;
  } // sort
} // SSO<T>
```

Remember: Preconditions and postconditions are adversarial.  If you
don't put sufficiently many preconditions on, your client will call your
method in such a way that it breaks, and then sue you.  If you don't
put in sufficiently many postconditions, the programmer will take the
lazy way out.

What do you make the preconditions your `sort` method?

* The array must exist.
* The array can't be null.
* The array can be empty.
* The comparator must match the contents of the array.  (But we've
  said this with the type.)
* The comparator must follow the expectations of comparators.
  E.g., consistent and transitive.

What do you make the postconditions your `sort` method?

* Note: We'll call the return value sorted.
* sorted is sorted from least to greatest based on the comparator.
     * Lazy Sam: return new T[0];
     * Lazy Sam: sorted = new T[1]; sorted[0] = arr[0];
     * And "least to greatest" makes assumptions about the comparator.
* Attempt to rewrite that postcondition:  The values in sorted are
  such that when compared by the comparator returns a positive value,
  which kind of depends on the order you sorted in, um.
* Another attempt to rewrite that postconditions:
  For all reasonable i (0 <= i < length-1), comparator.compare(A[i],A[i+1]) <= 0.
* sorted.length = arr.length
* Everything in the original array is in sorted. 
     * Lazy Sam: Suppose input is {2,2,1}, what incorrect output will
       Lazy Sam give?  {1,1,2}
* sorted is a permutation of arr.

Stable sorting algorithms
-------------------------

Sorting algorithms rearrange elements so that "smaller" move to the left
and "larger" elements move to the right.

What about equal elements?  A *stable* sorting algorithm maintains the
order of equal elements.

A benefit to stable sorting algorithms; You can sort by one comparator,
then another, and preserve some aspects of the first comparator.

* Sort by first, then by last: Everyone is now in order by Last, First.

Testing sorting algorithms
--------------------------

What are some particular kinds of cases you'd pay attention to?

How would you test systematically?

* Empty arrays
* Repeated elements
* In order already
* In reverse order

How would you test "randomly"?  (Don't assume you have access to another
`sort` method.)

* Generate an array that is already in order.
* Permute it
* Sort it
* Compare to the original

Insertion sort
--------------

Idea: Divide the array into sorted and unsorted.  Repeatedly insert
the first element from the unsorted part into the sorted part.

Invariant?

Stable?

Selection sort
--------------

Idea: Divide the array into sorted and unsorted.  Repeatedly insert
the first element from the unsorted part into the sorted part.

Invariant?

Stable?

Generalizing DNF (if time)
--------------------------

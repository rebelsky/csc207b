---
title: Eboard 21  Sorting
number: 21
section: eboards
held: 2019-03-13
link: true
---
CSC 207.01 2019S, Class 21:  Sorting
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
* Lab writeup for Class 21:
    * Draw invariants for insertion sort and selection sort.

### Extra credit

#### Extra credit (Academic/Artistic)

* Convocation Thursday.

#### Extra credit (Peer)

#### Extra credit (Wellness)

* Sub-free spring break!

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
| b | r | w | w | r | b | w | r | w | w | w | r | w | b | r | b | w |
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

Our algorithm

How do you make progress while maintaining the invariant?

* If A[i] is red, swap(A, r, i), increment r, increment i.
  `swap(A, r++, i++);`
* If A[i] is white, increment i
* If A[i] is blue, swap(A, i, b-1), decrement b.
  `swap(A, i, --b);`

When do you stop?  When i == b.

Question:

* What happens if the white section is empty and A[i] is red?

Initialization

* r = 0, i = 0, b = length

Running the algorithm

```text
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| B | R | W | W | R | B | W | R | W | W | W | R | W | B | W | B | R |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|                                                                   |
0,i,r                                                               length,b

A[i] is B, swap with A[b-1], decrement b
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| R | R | W | W | R | B | W | R | W | W | W | R | W | B | W | B | B |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|                                                               |   |
0,i,r                                                           b   length

A[i] is R, `swap(A, r++, i++);`
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| R | R | W | W | R | B | W | R | W | W | W | R | W | B | W | B | B |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|   |                                                           |   |
0   i,r                                                         b   length

A[i] is R, `swap(A, r++, i++);`
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| R | R | W | W | R | B | W | R | W | W | W | R | W | B | W | B | B |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|       |                                                       |   |
0       i,r                                                     b   length

A[i] is W
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| R | R | W | W | R | B | W | R | W | W | W | R | W | B | W | B | B |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|       |   |                                                   |   |
0       r   i                                                   b   length
```

The problem of sorting
----------------------

Suppose we want a functional interface for a sorter for arrays.  What does 
it look like?

```java
public Interface Sorter1<T> {
  /** Create a new sorted version of original. */
  public T[] sort(T[] original, Comparator<? super T> comparator);
} // Interface Sorter1
```

What does `? super T` mean?  This means that we'll accept anything that
is a superclass of T or equal to T.  For example, if we have an object
comparator (`Comparator<Object>`), we can use that to sort an array
of strings. (`String[] original`).  If we have a `Person` comparator,
we can use that to sort an array of `Student` objects.

What does `? extends T` mean?  It means we'll accept any subclass of T,
or T.

```java
public Interface Sorter2<T> {
  /** Sort in place. */
  public void (T[] original, Comparator<? super T> comparator);
} // Interface Sorter2
```

* The model expressed by `Sorter2` saves space.
* Sometimes there's a benefit to making a new array (it's more pure).

Can we translate between the two?  That is, if someone writes a class
that implements Sorter2, can you adapt it to implement Sorter1, or
vice versa?

* We'll return to this question if there's time.

What do you make the preconditions of your `sort` method?

* The type restrictions on the variables, particularly that the comparator
  is applicable to the elements in original.  (In Scheme, we made that
  explicit.  Here, the assumption is that the type parameters specify
  that.)
* We may disallow empty arrays (but no one seems in favor of it).

What do you make the postconditions of your `sort` method?  (The one
that returns a new array.)  Call the return value `sorted`.

* The returned array is sorted.  That is, for any reasonable i,
  `comparator(sorted[i], sorted[i+1]) <= 0`.  ("reasonable" means
  between 0 and length-2.)
* original is unmodified.

Breaking the implicit contract.

* The client could give an inconsistent comparator.  (Fortunately, the
  Java standard puts rules for consistency on comparators.)

Can we meet these postconditions without achieving the underlying goal?

* Yes, return a trivially sorted array.  Empty array.  One element array.
  An array that has only copies of the one element from the original.

New additional postconditions

* "The returned array contains all of the elements in the original."
  "And no more."
* Better: "The returned array is a permutation of the original."

Breaking the implicit contract again.  That is, can we return something
that meets these three postconditions that doesn't meet the implicit goal?

* The client may want the sort to be stable.

Stable sorts
------------

"Elements with the same value should stay in the same order."

E.g., if we sort by name (last then first), alphabetical order

```
{("Sam" "Are" 121),("Jane" "Doe" 412),("Sam" "Are" 001),("Homer" "Doh" 123)}
```

Two possible results

```
{("Sam" "Are" 121),("Sam" "Are" 001),("Jane" "Doe" 412),("Homer" "Doh" 123)}
{("Sam" "Are" 001),("Sam" "Are" 121),("Jane" "Doe" 412),("Homer" "Doh" 123)}
```

The one that maintains the order of the two Sam Are's is stable.  The other
is not.

Testing sorting algorithms
--------------------------

How would you test systematically?  (What are some things you would test
in your systematic tests?)

* Array with one element.
* Array with no elements.
* Array with duplicate elements.  (All the same; some duplicates.)
* Array that is already sorted.
* Array that is reverse sorted.
* Array that is partially sorted.

```java
assertEquals({1,2,2,2,3}, s.sort({2,2,2,1,3}), (a,b) -> a - b);
```

vs.

```java
Comparator<Integer> lessthan = (a,b) a-b;
sorted = s.sort({2,2,2,1,3},lessthan);
assertTrue(isSorted(sorted, lessthan));
```

```java
public static <T> boolean isSorted(T[] values, Comparator<? super T> comparator) {
  for (int i = 0; i < values.length-1; i++) {
    if (comparator.compare(values[i], values[i+1]) > 0) {
      return false;
    } // if
  } // for
  return true;
} // isSorted
```

Which do you prefer?

* Second one: It requires less work on our part to predict the final result.
* First one: We have to write `isSorted`, and evidence suggests that even
  Sam will get it wrong the first time, and the second time, and the third
  time.
* First one: We have to declare an extra variable for the second one.
* First one: You can't tell whether or not the result is actually a permutation
  of the original array.
* First one: It's shorter.
* Second one: We tend to screw up when we do things by hand.

How would you test "randomly"?  (Don't assume you have access to another
`sort` method.)

* Pick a random size, pick random elements for the array, use method 2.
* Start with a known sorted array, randomly permute the array, sort it,
  use method 1.

```java
for (int i = 0; i < 100; i++) {
  assertEquals(myArray, sorter.sort(randomPermutation(myArray)));
}
```

* Randomly generate a sorted array of size N of integers.

```java
// Pre: n < Integer.MAX_VALUE/3
public static Integer[] randomSortedArray(int n) {
  Random rng = new Random();
  Integer[] values = new Integer[n];
  values[0] = 500 - rng.nextInteger(1000);
  for (int i = 1; i < n; i++) {
    A[i] = A[i-1] + rng.nextInteger(3);
  } // for
} // randomSortedArray
```

```
rsa = randomSortedArray(1000);
assertEquals(rsa, sorter.sort(permutation(rsa)), () -> Arrays.toString(rsa));
```

Insertion sort
--------------

Idea: Divide the array into sorted and unsorted.  Repeatedly insert
the first element from the unsorted part into the sorted part.

Invariant?

Stable?

Selection sort
--------------

Idea: Divide the array into sorted and unsorted.  Repeatedly insert
the smallest element from the unsorted part at the end of the sorted part.

Invariant?

Stable?

Generalizing DNF (if time)
--------------------------

_Nope, there's not time._

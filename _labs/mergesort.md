---
title: Merge sort
summary: |
  In this lab, you will implement merge sort, a recursive sorting
  procedure that performs better than the previous sorts we have
  seen so far.
copyright:  Copyright &copy; Peter-Michael Osera and Samuel A. Rebelsky.
copyright_message: |
  This work is licensed under a Creative Commons Attribution-NonCommercial
  4.0 International License.  To view a copy of this license, visit
  <http://creativecommons.org/licenses/by-nc/4.0/>.
link: true
---
## Overview of the algorithm

Merge sort over an input list can be pithily described as follows:

1. Divide the list in half.

2. Recursively merge sort the two halves.

3. Merge the two sorted halves together into a sorted whole.

Like binary search, we find ourselves needing to divide an input list in half.
Physically dividing up the list in half is costly, so, like binary search, our implementation will keep track of the `lo` and `hi` indices of the sub-list under consideration.
I recommend designating `lo` to be inclusive and `hi` to be exclusive so that the range `0` and `list.size()` corresponds to the entire list.

Otherwise, two of the three steps of the algorithm are easy to implement.
Dividing the list in half amounts to a midpoint calculation on indices and the recursion is realized with two recursive calls to merge sort, passing in the appropriate ranges.  The difficulty—and where all the work actually lies—is in the merge operation which we will spend our time this lab honing.

## The merge algorithm

At every step of the recursion, we perform a merge operation on two sub-lists.
For example, suppose the list under consideration is:

```text
[1, 4, 8, 3, 5, 9]
```

If we have already sorted the two halves of the list (which we have), we can merge the two halves into a sorted whole using a *two-fingered approach*.
We maintain pointers to the two halves of the list—initially these pointers are pointing to the left-most (minimum) elements in the two halves:

```text
 V        V
[1, 4, 8, 3, 5, 9]
```

We then repeatedly take the minimum of the two sublists (which by definition will be the minimum element of the elements not yet merged) and place that into a second scratch list.
For example, performing one iteration of this process, `1` is smaller than `3` so we place `q` into our scratch list:

```text
    V     V
[1, 4, 8, 3, 5, 9]

    V
[1, ?, ?, ?, ?, ?]
```

Next `3` is less than `4`, so we place `3` in the scratch list and increment the right-hand pointer:

```text
    V        V
[1, 4, 8, 3, 5, 9]

       V
[1, 3, ?, ?, ?, ?]
```

This process continues until all the elements from the two halves of the list are exhausted.
We can describe this process concisely with two invariants:

* The two pointers in the input list point to the smallest elements in each of the sub-lists that have not yet been merged.
* The elements to the left of the pointer in the auxiliary list are the smallest elements of the input list in sorted order.

When we are done, the scratch list contains the merged elements of the original list.
We can finally copy the elements of the scratch list back into the original list to complete the operation.

## Invariants, revisited

Draw a picture of the invariants.  It should look something like
the following (with variable names instead of the X's, and notes
about the content of the different sections of the arrays).

```text
+---   ---+---------+---------+---------+---------+---   ---+
|  . . .  |         |         |         |         |  . . .  |
+---   ---+---------+---------+---------+---------+---   ---+
|         |         |         |         |         |         |
0         X         X         X         X         X         length

+-------------+-------------------------+
|             |                         |
+-------------+-------------------------+
|             |                         |
0             X                         X
```


## Implementing merge

With this algorithm in mind, implement a `merge` operation with the following function signature:

```java
/**
 * Merge the values from positions [lo..mid) and [mid..hi) back into
 * the same part of the array.
 *
 * Preconditions: Each subarray is sorted accorting to comparator.
 */
static <T> void merge(ArrayList<T> vals, int lo, int mid, int hi, Comparator<? super T> comparator);
```

You are likely to need a temporary array to merge into.

## Implementing merge sort

With `merge` implemented, implement `mergesort`:

```java
public static <T> void mergeSort(ArrayList<T> vals, Comparator<? super T> comparator);
```

Because of the need to track bounds explicitly, you'll need a helper version of `mergeSort` that takes this bounds as arguments.
Initially you should pass `0` and `vals.size()` to this helper method to kick off the merge sort process.

Verify that your algorithm works on a number of examples.
Make sure to check corner cases, *e.g.*, zero-length lists, one-length lists, already-sorted lists, *etc.*

## Analyzing merge sort

Analyze the time complexity of merge sort, giving an appropriate big-O bound.
Does merge sort have a best-case or worst-case runtime that is different from this bound?

Analyze the space complexity of merge sort.
How much auxiliary space does merge sort use?
Remember to factor in both the amount of space dedicated to recursive function calls as well as additional heap allocations made by `merge`.

## Merge sort, revisited

You should have found the space complexity of merge sort to be unsatisfactory.
It seems like you should be able to limit the creation of the scratch lists so that you only use O(_n_) space.  Do so.

Acknowledgements
----------------

The original version of this laboratory was written by Peter-Michael
Osera.  Samuel A. Rebelsky made some revisions in spring 2019.

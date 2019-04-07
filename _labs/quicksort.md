---
title: Quicksort
summary: |
  In this lab, you will implement Quicksort, an efficient in-place
  sorting algorithm.
copyright:  Copyright &copy; Anya Vostinar, Peter-Michael Osera, and Samuel A. Rebelsky.
copyright_message: |
  This work is licensed under a Creative Commons Attribution-NonCommercial
  4.0 International License.  To view a copy of this license, visit
  <http://creativecommons.org/licenses/by-nc/4.0/>.
link: true
---
In this lab we will implement the Quicksort algorithm. As a reference, refer to the readings in §8.3 from the schedule.

Let us first review the main steps in Quicksort:

* Choose a pivot element
* Partition the sub-array according to the pivot.
* Recursively Quicksort the two partitioned halves of the sub-array.

## The partition operation

We will start this lab by first implementing the partition operation. For this part of the lab, we will fill in the following definition of the partition operation:

```java
private static int partition(int[] arr, int lo, int hi, int pivotIndex) 
```

The partition operation takes a sub-array (like merge sort, denoted by a `lo` and a `hi` bound) and the index of an element, `pivotIndex` in that sub-array as input. Let's walk through the general steps involved in this operation.

Let's assume the array we want to partition has the shape below. Following the partition operation, we want the modified array to have the following properties (1) all the elements *less than* the pivot are to the left of the pivot, and (2) all the elements *greater than or equal to* the pivot are to the right.

```text
[       ][ ][        ]
        pivot
[< pivot][ ][>= pivot]
        pivot
```

To perform this operation in linear time, we will employ a *two-fingered* approach. Let's visualize this operation with a sample array from the reading.

```text
[3, 9, 2, 8, 6, 4, 1, 7, 5]
```

### Step 1: Placing the pivot

To start, you may choose the midpoint of the array to be the pivot.
Let's say we choose the element 6 as our pivot. Because we are
sorting the array *in-place*, we want to first swap the pivot 6
with the last element of the array (or the sub-array, if we're using
it within Quicksort). Now we can consider partitioning every element
but the last of the array according to the pivot.

```text
[3, 9, 2, 8, 5, 4, 1, 7, 6]
```

### Step 2: Pointer Initialization

Our implementation will use `lo` and `hi` to keep track of the sub-array limits. Then, we can initialize our pointers to keep track of the left and right indices relative to the sub-array limits.

```text
[3, 9, 2, 8, 5, 4, 1, 7, 6]
 ^                    ^  
```

### Step 3: Compare and Swap

Now we will repeatedly compare elements on the left- and right-hand sides of the array and place them in the correct position relative to the pivot. In our array, the first swap occurs when we find an element on the left that is greater than the pivot, and on the right that is lesser than the pivot.

```text
[3, 9, 2, 8, 5, 4, 1, 7, 6]
    ^              ^

[3, 1, 2, 8, 5, 4, 9, 7, 6]
       ^        ^
```

We will maintain the following loop invariant in our program design:

```text
[ <pivot ][    ][    ][ >=pivot ][ ]
           ^        ^           pivot
```

### Step 4: Termination

What would be an appropriate condition to terminate the loop body?

### Step 5: Wrapping up

When you're done, you should swap the pivot into the location it
belongs (in this version, at the left of the >= elements).  You will
thenreturn the index of the pivot.

### Step 6 Test: cases

And now to test our code! Test your code with common-case as well as edge-case scenarios. Does your code return the correct partitioning if the pivot is set to the smallest or the largest element in the list?

## Writing `quickSort`

Now that we have implemented the partition operation, all that remains is to recursively apply the partition operation on our input array! Write an implementation of Quicksort that has the following definition:

```java
private static void quickSort(int[] arr) 
```

Note that we will also need to write a helper quickSort implementation which would take in the array bounds to recursively sort through the left and right sub-arrays.

```java
private static void quickSort(int[] arr, int lb, int ub) 
```

### Testing

Test your code once again across both common-case and edge-case scenarios to make sure your sorting algorithm works.

## Choosing a pivot

Finally, we consider our choice of pivot. We have seen from the readings, that a poor choice of pivot can affect the run time of our Quicksort implementation and that a good choice of pivot would be the median of the list. A computationally efficient implementation is to use Median of three to select our pivot using the first, the middle, and the last elements of the array. Consider the following questions before proceeding to implement Median of three:

* What is the order complexity of Quicksort assuming the pivot is the largest element of the list?
* Is the median a better choice of the pivot than the mean? If so why?
* Is median of three always better than the worst-case scenario?

Write an implementation of median-of-three that has the following signature:

```java
private static int medianOfThree(int[] arr, int left, int right)
```

## Extension: Quickselect

_For the few of you who have extra time._

Let's say we wanted to select the kth smallest element in an unsorted list. How would we go about finding this element? Given a set of *n* distinct numbers and the number *k*, 1 <= *k* <= *n*, the selection problem computes the **kth order statistic**, or kth smallest number in the set of numbers.

We will now proceed to implement Quickselect which has a slight variation on Quicksort. The intuition in Quickselect is that we prune the sub-arrays where we know that the `k`th value will not be found.

1\. Pick a pivot at random from the input array.

2\. Partition the array into sub-arrays as before and count the number of elements, L, in the left sub-array.

```text
    [< pivot][ ][> pivot]
     —— L— pivot
```

3\. Selection: In this operation, we want to use the results of the partition, to select the sub-array where we will find the kth smallest value. a. If L is equal to k-1 then the pivot is the kth smallest value! b. If L is greater than k-1 then recursively call select on the left sub-array. c. If L is less than k-1 then recursively call select on the right sub-array.

Write the `select` operation of Quickselect:

```java
private static int select(int[] arr, int k)
```

## Acknowledgements

This lab is copied nearly verbatim from a similar lab by Anya Vostinar
and Peter-Michael Osera.

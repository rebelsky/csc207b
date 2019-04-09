---
title: Eboard 20  Loop invariants
number: 20
section: eboards
held: 2019-03-11
link: true
---
CSC 207.01 2019S, Class 20:  Loop invariants
============================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
* Reasoning about iterative algorithms
* The state of a program
* Loop invariants
* Loop termination
* An exercise: Partitioning an array
* An exercise: Dutch national flag

Preliminaries
-------------

### News / Etc.

* Sit where you would like.

### Upcoming work

* [Exam 1](../exams/exam01) due Thursday
    * **Do not discuss the exam with anyone (other than me)!**
* Readings for Wednesday: 
    * [Sorting basics](../readings/sorting).  (It's actually ready, for once.)
    * [Osera 8.4: Generic Constraints](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap08.pdf)
* No lab writeup.

### Extra credit

#### Extra credit (Academic/Artistic)

* CS Table, Tuesday, noon: Unknown topic
* Convocation Thursday.

#### Extra credit (Peer)

#### Extra credit (Wellness)

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

### Exam notes

* Remember that `average` must use O(1) space.
* You are dealing with `long` values, so the average is likely to be
  slightly off if the sum of the values is not a multiple of the length.  
  You should round towards zero, just like long division.
* The constructor for your priority queue should take the comparator
  as an input.  (That's probably the only input that constructor needs.)
* You will probably need to create comparators when you run tests or
  experiments.
* You need to keep the nodes in your priority in order by value.  That
  almost certainly happens in the `put` (or `enqueue`) method.

### Questions

_How will remove be called?_

> The client code will call `remove` when they want to remove elements
  they have just seen.  A typical instance.

        Iterator<String> it = pq.iterator();
        while (it.hasNext()) {
          String s = it.next();
          if (some_complicated_logic_that_you_do_not_know(s)) {
            it.remove();
          } // if
        } // while

_Does it also have to work with for-each?

> No, since there's no iterator that the client explicitly accesses.

_Can we have a point of extra credit for the "minor" typo?_

> Sure.

_Should `average` return the same value as if we did it with arbitrary length integers?_

> Yes.

Quiz
----

Reasoning about iterative algorithms
------------------------------------

There's some evidence that programmers make mistakes, particularly
in loops in code.

It is good to have tools that let us

* "Prove" that our code is correct.
* Help us develop better code to start with.
* Reason about code we've written

The state of a program
----------------------

When reasoning about programs, we normally pay attention to the "state".
Most typically, the values of variables on the stack and the heap.
(Pictures help.)

We can go from the start to the finish:

* Here's what we know at the start of our algorithm
* Here's what we know after each step
* Here's what we know at the end
* Hopefully, that last thing is our goal.

We can also work backwards from goal to origin.

* I need to be in this state.
* If I do this operation, I only need to be in this state.
* ...

Loop invariants
---------------

Loops are a common point of failure, so we do this analysis most commonly
with loops.

A loop invariant is a statement about the state of the program

* That we know holds before the first iteration of the loop
* That we can prove holds after each iteration of the loop
* With some associated progress
    * E.g., the first i elements of the array are in order.
* That is useful for goals of procedurea

For arrays, pictures are often more helpful than text.

```text
+---------------+-----------------+
| sorted        | unknown         |
+---------------+-----------------+
                i                 length
```

Loop termination
----------------

So far

```
  Invariant holds
  Loop
  Invariant holds
```

That is, when the loop terminates, the invariant still holds.  We must
also show that the loop terminates.

In the binary search from Friday, some folks had non-terminating code
in a few situations (particularly when I forced it upon you).

```java
  while (lb <= ub) {
    if (vals[mid] < goal)
      lb = mid;
    else ...
  }
```

If `lb` is 0 and `ub` is 1, and the value we are searching for is at
position 1, then, `mid` is 0, `vals[mid] < goal`, so `lb = mid = 0`.

Traditionally, once we prove that the loop has terminated and that
the invariant holds, we can reach an appropriate conclusion.

E.g., If our invariant is "If the value is in the array, then it's
between lb and ub" and our loop test is as above, once the loop exits,
we know that lb > ub, there are no values in that range, so we know
the value is not in the array.

An exercise: Partitioning an array
----------------------------------

Inputs: 

* array of integers
* pivot, an integer

Goal:

* Efficiently (O(n)) rearrange the array so that all the values <= pivot
  are on the left and all the values > pivot are on the right.

Return:

* The index of the last value on the left.

Some questions

* What might the invariant look like?
* How might you approach the problem?

Idea to approach it: Swap elements from the outside of the array,
moving in.

Another idea: Work from the middle

```text
+------+-------+--------+---------+
|unkno.|<=piv  | > piv  | unknown |
+------+-------+--------+---------+
|      |       |        |         |
0      i     center     j         length
```

Look to the left of i and the value at j.

* If the thing to the left of i is bigger than the pivot and the
  the value at j is <= the pivot, swap them.  Decrement i, increment j.
* If the thing to the left of i is <= pivot, decrement i.
* If the thing at j is > pivot, increment j.


When do we stop?

* When i = 0 and j = length
* If i = 0 and j < length, we only care about the right side.

```text
+--------------+--------+---------+
|       <=piv  | > piv  | unknown |
+--------------+--------+---------+
|              |        |         |
0,i          center     j         length
```

It appears that we now need to do something a bit different.  Now,

* If the value at j is > piv, add 1 to j
* If the value at j is <= piv, swap with the center and add 1 to j
  and add 1 to center

Note: We'll also have to do similar work when j = length and i > 0.

Questions to ignore for now

* Do we make progress at each step?
* What do we know when we're done?

A different invariant

```text
+------+----------------+---------+
|<= piv|  unknown       | > piv   |
+------+----------------+---------+
|      |                |         |
0      s                l         length
```

Rephrased in English (Mathish)

* For all i, 0 <= i < s, A[i] <= piv.
* For all j, l <= j < A.length, A[j] > piv.

Policy:

* If A[s] <= piv then increment s
* Else if A[l-1] > piv then decrement l
* Else swap(A, s, l-1), increment s and decrement l

Termination (when l <= s):

* while (s<l)

Progress:

* Our measure of work left is l-s
* Each time through the loop we either increment l or decrement s; both
  reduce l-s.
* Eventually, it must reach 0.

```text
+------+---------+
|<= piv| > piv   |
+------+---------+
|      |         |      
0      s,l       length 
```

Can `s` ever be greater than `l`?  No (Analysis discussed)

Note: How are we sure that the invariant holds when we start our algorithm?

```text
+------+----------------+---------+
|<= piv|  unknown       | > piv   |
+------+----------------+---------+
|      |                |         |
0      s                l         length
```

If `s` starts as 0 and `l` starts as length, there's nothing in the 
two border sections, everything is unknown, and the invariant holds.

An exercise: Dutch national flag
--------------------------------

You have an array with three values, which we'll call red, white, and blue.
They are in no particular order.  Your goal is to rearrange it so that all
of the red are at the left, all the blue are at the right, and all the
white are in between.

Input:

```text
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| b | r | w | w | r | b | w | r | w | w | w | r | w | b | r | b | w |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
```

Output

```text
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| r | r | r | r | r | w | w | w | w | w | w | w | w | b | b | b | b |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
```

Come up with an invariant, do the other anlysis.

```text
+----------------+---------+----------+-------+--------------+
|   red          | unknown | white    |unknown|   blue       |
+----------------+---------+----------+-------+--------------+
|                |         |          |       |              |
0                r         w          x       b              length
```

In Mathglish,

* For all i, 0 <= i < _, ?
* For all j, _ <= j < _, ?
* For all k, _ <= k < _, ?

**The prior invariant turns out not to work well.  So we switch
to the following one.  Note that that one also includes clear
steps for maintaining the invariant while decreasing the size of
the unknown section.**

```text
+----------------+--------------+-------------+--------------+
|   red          |  white       |  unknown    |   blue       |
+----------------+--------------+-------------+--------------+
|                |              |             |              |
0                r              i             b              length
```

* If A[i] is blue, swap to b-1, decrement b.
* If A[i] is white, increment i,
* If A[i] is red, swap A[r] and A[i], increment r and i.

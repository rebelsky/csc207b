---
title: Eboard 20  Loop invariants
number: 20
section: eboards
held: 2019-03-11
link: true
---
CSC 207.02 2019S, Class 20:  Loop invariants
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

* Singers in St. Paul on Saturday, Rochester on Sunday, Madison on Monday
  of break, Chicago on Tuesday of break, Newton on Thursday of break.
* Track and Field are at Emory on the last weekend of Spring break.

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

_Did the morning section ask questions?_

> Yes.  See their eboard for details.

_What is the average of 2 and 3?_

> 2, if we're dealing with longs.

_Are we getting graded work back soon?_

> Yes.  Hopefully tonight for a few homework assignments, Wednesday
  for quizzes.

_Do we have to implement remove for problem 3 or problem 4?_

> Both.

_How much can we be off in the `average` method?_

> abs(average*length - sum) < length

_How should we initialize our arrays for testing?_

> Here are some approaches.  Don't use them all at once.

```java
for (int i = 0; i < a.length; i++) {
  a[i] = 3;
}
for (int i = 0; i < a.length; i++) {
  a[i] = i;
} // average is (length-1)/2, or so Sam thinks
for (int i = 0; i < a.length; i++) {
  a[i] = Long.MAX_VALUE - i;
} // average is
for (int i = 0; i < a.length; i++) {
  a[i] = Long.MIN_VALUE + i;
}
randomlyPermute(a);
randomlyIncrementAndDecrement(a);
```

> I'm also a firm believer in nesting for loops.

_Are you sure about those calculations?_

> Reasonably.

> Suppose length is n.  We are adding 0 + 1 + ... + n-1.  Sum is (n-1)*n/2.
  If there are n elements, the average value is (n-1)*n/2n = (n-1)/2

_What should we do if we don't know the average of the array?_

> I would not recommend that you build arrays whose average you do not know.

> Or you could write an average that uses BigIntegers and compare the
  results of that one to yours.

Quiz
----

Yay!  Big-O.  (or Oh, big-Yay!)

Reasoning about iterative algorithms
------------------------------------

Experience suggests that people have difficulty writing correct code.

We need strategies for

* Showing that a program is correct. ("Proof")a
* Making fewer errors in the first place.
* Some recommend developing the proof of correctness and the code
  side-by-side.

A variety of techniques have been developed to deal with this issue,
we're looking at a high-level, general approach.

The state of a program
----------------------

Often, our reasoning is based on the "state" of the system (stack and heap;
settings of the various variables and parameters).

We know the state of the program at the start of a chunk of code (e.g.,
the start of a procedure).  We know because we have done other analysis
or have preconditions.

We have a goal state at the end of the chunk of code.

We show that the instructions we give go from one to the other.

Sometimes we start with the goal and work backwards.

E.g., our goal is that z is the greater of x and y.

```text
know: x is an integer, y is an integer, z?
if (y > x) {
  z = y
  // z = y
  // z >= y
  // z > x (because of transitivity of >)
} else {
  // x >= y
  z = x;
  // z = x, z >= x, z >= y (by transitivity)
}
goal: z is an integer, z >= x, z >= y, z = x or z = y
```

Loop invariants
---------------

Lots of errors happen in loops.  Loop invariants are our tool.

A loop invariant is a statement about the state of the system

* It holds before we enter the loop.
* If it holds at the start of one iteration, it also holds at the
  end of that iteration.
    * It may not hold in the middle
* It tells us something useful related to our goal

Here's a useful thing to say about an array during sorting.

```text
+-------------------+------------------+
|   sorted          |  unprocessed     |
+-------------------+------------------+
|                   |                  |
0                   i                  length
```

If I can maintain that invariant while increasing i repeatedly, I will
sort the array.

Loop termination
----------------

Of course, not all loops terminate.

We also want to "prove" that our loops terminate.  Measure the "size"
of the remaining work and show that it decreases in each iteration of
the loop.

Termination plus loop invariant generally guarantee our goal.

An exercise: Partitioning an array
----------------------------------

Input: 

* An array of integers, A 
* An integer, pivot

Goal: Rearrange the array so that all the values <= pivot are on the left
and all the values > pivot are on the right.  We want to do this efficiently
O(n).

Return: The number of values <= the pivot.

Questions:

* How might you think about the state of the system midway through
  your program?  (That is, what is your pictorial invariant?)
* How might you approach the problem?


```text
+--------------+--------------------------------------+------------+
|  <= pivot    |           unprocessed                |  > pivot   |
+--------------+--------------------------------------+------------+
|              |                                      |            |
0              p                                      r            length
```

```text
+----------+--------------+----------------------------------------+
| <= pivot |  > pivot     |            unprocessed                 |
+----------+--------------+----------------------------------------+
|          |              |                                        |
0          s              i                                        length
```

```text
+----------------+----------------+--------------+-----------------+
| unprocessed    |   <= pivot     |  > pivot     | unprocessed     |
+----------------+----------------+--------------+-----------------+
|                |                |              |                 |
0                                                                  length
```

We're working with this one.

```text
+--------------+--------------------------------------+------------+
|  <= pivot    |           unprocessed                |  > pivot   |
+--------------+--------------------------------------+------------+
|              |                                      |            |
0              p                                      r            length
```

How can we move p and r?  (Alternately, what can you do with the knowledge
of the values at p and r-1?)

* If A[p] <= pivot then increment p
* Else If A[r-1] > pivot then decrement r
* Else swap A[p] and A[r-1], increment p and decrement r

When does this terminate?  When p and r are equal.

Can we argue that they never cross?  If we are down to one element,
it has to be one of the first two cases, so we will never have a case
in which we increment p and decrement r.

We can return p or r.

How do we initialize p and r so that the invariant holds when we start?
We can let p = 0 and r be length.

An exercise: Dutch national flag
--------------------------------

You have an array with three values, which we'll call red, white, and blue.
They are in no particular order.  Your goal is to rearrange it so that all
of the red are at the left, all the blue are at the right, and all the
white are in between.

Input:

```text
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| r | b | w | w | r | b | w | r | w | w | w | r | w | b | r | b | w |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
```

Output

```text
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| r | r | r | r | r | w | w | w | w | w | w | w | w | b | b | b | b |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
```

Invariant

```text
+---------+------------------------------------------+-------------+
| red     |      unprocessed                         | not red     |
+---------+------------------------------------------+-------------+
```

In effect, we run the partition algorithm twice, once to separate
red and not red and once to separate white and blue.

```text
+---------+-----+---------------------------+--------+-------------+
| red     |white|unprocessed                | white  | blue        |
+---------+-----+---------------------------+--------+-------------+
```

```text
+----------+-----------+-------------------------------+-----------+
|  red     |  white    |         unknown               |  blue     |
+----------+-----------+-------------------------------+-----------+
|          |           |                               |           |
0          r           i                               b           length
```

---
title: Assignment 8
subtitle: Skip lists
summary: |
  In this assignment, you will implement a randomized data structured
  called the "skip list" and experimentally analyze the efficiency
  of that data structure.
current: true
---
Note: As part of Grinnell's commitment to providing each student with
the opportunity to do research, this homework assignment asks you to
implement a data structure from a description in "the literature",
rather than a textbook or other synthesis.

Background: About skip lists
----------------------------

You've learned about a variety of linked structures.  A nice aspect
of linked structures is that it's easy and fast to insert something
in the middle of the structure or to remove something from the middle
of the structure.  However, getting to the right place in the structure
is slow.  Binary search trees provide one approach for fast insertion.

Skip lists solve the problem in another way, by having multiple
forward links from each node, with the links at level 0 stepping
through every element, the links at level 1 skipping about 1/2 the
elements, the links at level 2 skipping about 3/4 the elements, and
so on and so forth.  Skip lists take advantage of a random number
generator to help ensure that we get the appropriate distribution
of values.  Most of the time, skip lists require O(log_n_) steps to
find an element, O(log_n_) steps to insert an element, and O(log_n_)
steps to remove an element.

You can learn more about the design of skip lists 
from the following article.  (And, yes, you must read that article.)

>  William Pugh. 1990. Skip lists: a probabilistic alternative to
balanced trees. *Commun. ACM* 33, 6 (June 1990), 668-676.
DOI=10.1145/78973.78977 <http://doi.acm.org/10.1145/78973.78977>.

Skip Lists are useful in a variety of situations.  One possible use
is for sets, collections of values in which you want to be able to
expand and shrink the collection, and to be able to find values
in the collection.  Skip lists are also useful for "sorted
lists", lists in which you can iterate from smallest to largest.
We'll focus on using skip lists to implement maps.

The assignment
--------------

### Part Zero: Preparation

Fork the repository at
<https://github.com/Grinnell-CSC207/skip-lists-assignment-2019>.
Rename it to `csc207-skip-lists`.  Skim through the files to
understand what code has been provided.

_Note: The repo will not be available until late Friday night.  Spend
your initial time reading the Pugh paper._

### Part One: Implement basic skip lists

Start this assignment by implementing the following class, which
follows the basic structure of skip lists in that it focuses on
insertion, removal, searching, and iteration.

```
/**
 * Skip lists
 */
public class SkipList<K,V> implements SimpleMap<K,V> {
  . . .
} // class SkipList<K,V>
```

You may find it useful to write loop invariants for the core methods
(`set`, `get`, and `remove`).

While you need to implement `SkipList.remove`, you do *not* need to
implement the `remove` method for your iterators.

### Part Two: Test your implementation

You'll see that we've written a randomized test for skip lists.
Randomized tests are good, but it's also useful to have some carefully
designed predicatble tests.  Add at least six useful tests to
`SkipListTest.java`.

### Part Three: Experimentally analyze skip lists

Pugh claims that in practice, skip lists will require O(lg_n_) time
for all three core operations.  Design some experiments that let you
check that assertion.

Citations
---------

This assignment is based on a more general assignment on algorithm
design and analysis that I assigned in a previous semester. 

As the in-text citation suggests, Skip Lists were designed by Bill Pugh.


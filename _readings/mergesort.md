---
title: Merge sort
summary: |
  We consider the merge sort algorithm, one of the important sorting
  algorithms.  Merge sort is the first O(*n*log<sub>2</sub>*n*)
  sorting algorithm we will explore.
prereqs: |
  [Sorting](../readings/sorting).
  [Loop invariants](../readings/loop-invariants).
  Recursion.
---
Introduction
------------

We've now seen two sorting algorithms: insertion sort and selection
sort.  Both algorithms are relatively simple and straightforward.
Both have a running time of O(*n*<sup>2</sup>).  Both seem to be
correct.  Are we done with sorting?  No!

Computer scientists typically follow a natural progression of steps
when they encounter a new problem.  First, they design an algorithm to
solve the problem.  Next, they analyze the algorithm for correctness.
(Sometimes it's an informal analysis, sometimes it's a formal analysis,
sometimes it's a series of experiments.)  Next, they analyze the
running time.  In most cases, they then implement the algorithm and
see if unit tests assure them of correctness and data from running the
algorithm confirm the running time analysis.

But the next question they ask is *Can we do better?* Interestingly,
there are two approaches to that question.  One is practical: Some
computer scientists, upon seeing one solution, try to find a better
solution.  But there's also a theoretical approach: Some computer
scientists try to prove lower bounds on algorithms.  That is, it
may be possible to show that it's not possible to take fewer than
a certain number of steps.  For example, if we want to find the
smallest value in an unordered array, we probably have to look at
every element, and so won't be able to do better than O(*n*) for
any algorithm that finds the smallest value, and it's pointless to
try to do so.

We'll consider each issue in turn.

Lower bounds on sorting algorithms
----------------------------------

Is there a lower bound on how much work a sorting algorithm will
require?  Well, in order to sort an array of values, we probably
have to look at every value.  Hence, we need at least O(*n*) steps to
sort an array.

Are there O(*n*) sorting algorithms?  It depends on the kind of data
that you're working with.  For example, if you have an array of
integers of size n, and all the values are between 1 and *n*(although
some may be duplicated and some may be missing), then we can sort
the array by setting up an array of counters, stepping through the array
counting each value in turn, and then reading the values back from
the counter array.  That takes us about O(2*n*) steps, which is O(*n*).

Of course, that strategy is difficult to generalize.  So we might
ask whether we can sort an array in O(*n*) if our basic operations
are comparing two values and swapping two values that we've determined
need to be reordered.  It turns out that for compare-and-swap
algorithms, we need at least O(*n*log<sub>2</sub>*n*) steps.  Why?
It's a basic counting argument: If we do fewer than that many
comparisons, then we can find two values which have not been compared,
either directly or indirectly (by the transitivity of comparison).
If they haven't been compared, the algorithm can't know that they
are  in order.  But that's a casual description of the argument.
You can learn more in an algorithms course.  Suffice to say, we
should happy if we can write O(*n*log<sub>2</sub>*n*) sorting
algorithms for general data.

Sorting via divide-and-conquer
------------------------------

Our hand-waving argument suggests that we can't do any better than
O(*n*log<sub>2</sub>*n*) steps.  But can we write a sorting
algorithm with that bound?  We answer that question by trying.

In general, algorithms whose running time involves a logarithmic
factor use a *divide-and-conquer* strategy.  You divide the input
into two halves, you solve the problem on one or both halves, and
you combine the solutions.  You've already seen that strategy in
action for binary search.  Let's think about it for sorting.

How can we split an array in half?  The easiest way is to take the
first half of the array and the second half of the array.  So,
suppose we start with the following array, which we used in our
previous explorations of sorting algorithms.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
```

We split the array into two parts.

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  |  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
```

We sort the two parts (usually through the magic of recursion,
although for small-enough arrays, we might use a simpleer algorithm).

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  |  a|  c|  e|  i|  l|  t|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
```

Hmmm ....  How does that help?  Well, we can start to combine
these two subarrays back into a a new array.  

### Merging subarrays

The process of combining the two sorted arrays is called "merging".
We'll start with an empty target array, and keep track of which
elements we've seen in each array as we copy them to that target
array.

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  |  a|  c|  e|  i|  l|  t|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
^                          ^

0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+---+---+---+
^
```

What should we do?  We want to grab the smallest value in either
subarray.  Since the subarrays are sorted, we know the smallest of
the two values is smaller than every other value.  But there are
two "a"s.  Which should we choose?  If we choose the
one on the left, we're more likely to have a stable sorting algorithm,
so we choose that one.

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  |  a|  c|  e|  i|  l|  t|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
    ^                      ^

0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|   |   |   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+---+---+---+
    ^
```

Once again, we take the "a" that appears on the left.

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  |  a|  c|  e|  i|  l|  t|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
        ^                  ^

0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|   |   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+---+---+---+
        ^
```

Now, the "a" in the right half is smaller than the
"b" in the left half, so we grab the "a" from the right.

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  |  a|  c|  e|  i|  l|  t|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
        ^                      ^

0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+---+---+---+
            ^
```

The "b" on the left is the smallest of the two values.

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  |  a|  c|  e|  i|  l|  t|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
            ^                  ^

0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+---+---+---+
                ^
```

  The "c" on the right is smallest.

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  |  a|  c|  e|  i|  l|  t|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
            ^                      ^

0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+---+---+---+
                    ^
```

The "e" on the right is smallest.  (Yes, we can take two elements
in a row from one side.  We did so with the first two "a" values,
too.)

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  |  a|  c|  e|  i|  l|  t|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
            ^                          ^

0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+---+---+---+
                        ^
```

We continue until one of the arrays runs out of elements.

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  |  a|  c|  e|  i|  l|  t|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
                        ^                      ^

0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b| c | e | h | i | l | l | p |   |
+---+---+---+---+---+---+---+---+---+---+---+---+
                                            ^
```

At this point, we can copy over the remaining elements.  In this
case, there's only one element, but there could be a lot.

```text
0   1   2   3   4   5   6  0   1   2   3   4   5   6
+---+---+---+---+---+---+  +---+---+---+---+---+---+
|  a|  a|  b|  h|  l|  p|  |  a|  c|  e|  i|  l|  t|
+---+---+---+---+---+---+  +---+---+---+---+---+---+
                        ^                          ^

0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b| c | e | h | i | l | l | p |  t|
+---+---+---+---+---+---+---+---+---+---+---+---+
                                                ^
```

And we're done!

Analyzing merge sort
--------------------

So, how much time does merge sort take?  Let's see how we can
analyze it.

* It takes about O(*n*) steps to split the array into two halves.
  (We might be able to do the splitting in one step, if we don't
  actually build new arrays, but we'll be pessimistic.)
* It takes some amount of time to sort each subarray.  Each subarray
  has size n/2.
* It takes about O(*n*) steps to merge the two subarrays back together.
* An array of size 1 is sorted.
  So, we now have a nice set of recurrence relations.

* t(1) = 1.
* t(*n*) = *n* + t(*n*/2) + t(*n*/2) + *n*
  You should have already figured out the closed form of this algorithm.
  In particular, t(*n*) is in O(*n*log<sub>2</sub>*n*).

If you don't like the formality of recurrence relations, there's also
a nice informal argument that involves drawing a tree of the steps.

If we start with an array of size n, we split it into two arrays of
size n/2.

```text
             n
            / \
           /   \
         n/2   n/2
```

  We then split each of those into an array of size n/4.

```text
              n
            /   \
           /     \
         n/2     n/2
        /  \     /  \
       /    \   /    \
     n/4   n/4 n/4   n/4
```

  We keep splitting it until we end up with arrays of size 1.

```text
              n
            /   \
           /     \
         n/2     n/2
        /  \     /  \
       /    \   /    \
     n/4   n/4 n/4   n/4
    /  \  / \  / \   /  \
           . . .
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
```

Now, we can think about the effort in merging.  We'll read the tree
bottom up.  We merge all of the singletons into pairs.  Since there
are *n* singletons, that takes us O(*n*) steps.  Next, we merge all
the pairs into four-tples.  That takes us O(*n*) steps.  In fact,
at each level of the tree, we spend O(*n*) to merge up to the next
level.  Since the tree has O(log<sub>2</sub>*n*) levels, the algorithm
is O(*n*log<sub>2</sub>*n*).  (How do we know how many levels the
tree has?  Because log<sub>2</sub>n corresponds informally to "the
number of times you have to divide *n* by 2 before reaching 1".

Both our formal and informal analyses suggest that merge sort is
O(*n*log<sub>2</sub>*n*).  We've found a fast sorting algorithm!
And it might even be stable.

Iterative merge sort
--------------------

The preceding analysis might also give you another approach to
merge sort, particularly if you have concerns about recursive 
procedures.  Rather than recursively dividing the array and then
merging, we can start with the individual subarrays and merge
them into bigger and bigger sets.

We start with our original array and mark every pair.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|       Sorted singletons
+---+---+---+---+---+---+---+---+---+---+---+---+
|       |       |       |       |       |       |
```

  We can then make sure that each pair is in order.
  In this case, we need to flip the h and the p, and the c and the i.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|       Sorted singletons
+---+---+---+---+---+---+---+---+---+---+---+---+
|       |       |       |       |       |       |
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  h|  p|  a|  b|  e|  t|  c|  i|  a|  l|       Sorted pairs
+---+---+---+---+---+---+---+---+---+---+---+---+
```

  We can then use the merge algorithm to merge two pairs into a 4-tuple.

```text
|       |       |       |       |       |       |
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  h|  p|  a|  b|  e|  t|  c|  i|  a|  l|       Sorted pairs
+---+---+---+---+---+---+---+---+---+---+---+---+
|               |               |               |
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  h|  l|  p|  a|  b|  e|  t|  a|  c|  i|  l|       Sorted 4-tuples
+---+---+---+---+---+---+---+---+---+---+---+---+
```

  We can use the merge algorithm to merge 4-tuples into 8-tuples.  We
  have a 4-tuple left over at the end, so we leave it as is.

```text
|               |               |               |
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  h|  l|  p|  a|  b|  e|  t|  a|  c|  i|  l|       Sorted 4-tuples
+---+---+---+---+---+---+---+---+---+---+---+---+
|                               |               |
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  b|  e|  h|  l|  p|  t|  a|  c|  i|  l|       Sorted 8-tuples
+---+---+---+---+---+---+---+---+---+---+---+---+
```

We perform one final merge.

```text
|                               |               |
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  b|  e|  h|  l|  p|  t|  a|  c|  i|  l|       Sorted 8-tuples
+---+---+---+---+---+---+---+---+---+---+---+---+
|                                               |
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  i|  l|  l|  p|  t|       Sorted 
+---+---+---+---+---+---+---+---+---+---+---+---+
```

And we're done!

Acknowledgements
----------------

The origins of this reading are lost in the depth of SamR's memories.
He likely wrote all the versions of this.  In spring 2019, he cleaned
it up slightly.


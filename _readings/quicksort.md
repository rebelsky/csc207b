---
title: Quicksort
summary: |
  We consider Quicksort, an interesting divide-and-conquer sorting algorithm.
prereqs: |
  [Sorting](../readings/sorting).
  [Merge sort](../readings/mergesort).
  [Loop invariants](../readings/loop-invariants).
  Recursion.
---

Alternative strategies for dividing lists
-----------------------------------------

As you may recall, the two key ideas in merge sort are:
(1) use the technique known as of *divide and
conquer* to divide the list into two halves (and
then sort the two halves); (2) merge the halves back together.

Are there better sorting algorithms than merge
sort?  If our primary activity is to compare
values, we cannot do better than some constant times
_n_ log<sub>2</sub> _n_.
steps in the sorting algorithm.  However, that hasn't stopped computer
scientists from exploring alternatives to merge sort.  One reason to
look for better versions is that merge sort is an "out of place"
sorting algorithm - you need to create new arrays in order to do the
merge.  (The obvious merge algorithm requires another array of the same
size as the original.  Some clever techniques allow you to get by with 
another array of half the size of the original.)  Another reason to
look at alternatives is actual, rather than theoretical, speed.  In
practice, the constant multiplier hidden by big-O notation makes a big 
difference.  And so we might want to reduce that multiplier.

One way to develop an alternative to merge sort is to split the
values in the list in a more deliberate way.  For example, instead of
splitting into "about half the elements" and "the remaining
elements", we might choose the to divide into "the smaller
elements" and "the larger elements".

Why would this strategy be better?  Well, if we know that every small
element precedes every large element, then we can significantly
simplify the merge step.  For lists, we can just append the two sorted
lists together.  For arrays, we can sort *in place*
by rearranging the array so that small elements have small indices and
large elements have large indices, and then sort the two halves.

```text
// Sort the whole array, using order to compare elements
Algorithm: sort(A, order)
  sort(A, 0, A.length, order)

// Sort elements [lb..ub) of A using order to compare elements
Algorithm: sort(A, lb, ub, order) 
  if ub-lb <= 1
    // Do nothing!  Subarrays of length 1 or 0 are sorted
  otherwise
    Rearrange the elements so that we achieve the criterion that
      all elements in indices less than mid are small and all elements
      in indices greater than mid are large.  In more formal notation
        For all lb <= i < mid < j < ub
          A[i] <= A[mid] < A[j]
     sort(A, lb, mid)
     sort(A, mid+1, ub)</literallayout>
```

How do we identify the smaller and larger elements?  How do we identify
the midpoint?  Ideally, we would identify the median value of the
subarray, put that at mid, and rearrange so that values less than
the median appear to the left of mid and values to the right appear to
the right.

It may help to think about this algorithm in terms of lists.

```text
// Sort a list
Algorithm: sort(L, order)
  if the length of L <= 1
    return L
  otherwise
    let m be the median value of the list
    append(sort(elementsSmallerThan(L, m, order)),
           sort(elementsEqualTo(L, m, order)),
           sort(elementsGreaterThan(L, m, order)))</literallayout>
```

Identifying small and large elements
------------------------------------

It sounds like a great idea, doesn't it?  Instead of `split` and
`merge`, we can sort by identifying the median and reorganizing the
values into small and large elements.

Unfortunately, the typical way that people identify the median of
a collection of values is to sort the values and look in the middle.
That doesn't work so well if we're identifying the median in order
to sort.  So we need another approach.

So, what do we do?  A computer scientist named C. A. R. Hoare had
an interesting suggestion: *Randomly pick some element of the list
and use that as a simulated median.*  That is, anything smaller
than that element is "small" and anything larger than that element
is "large".  Because it's not the median, we need another name for
that element.  Traditionally, we call it the *pivot*.  Is using a
randomly-selected pivot a good strategy?  You need more probability
and statistics than most of us know to prove formally that it works
well.  However, practice suggests that it works very well, indeed.
(It works a bit better if you randomly pick three elements and let
the median of those three elements be the pivot.)

Partitioning
------------

We know how to find a pivot.  For the list-based version, it's pretty
easy to find the smaller and larger elements: We just iterate through
the list, grabbing the elements that meet the appropriate criterion.

```java
List small;
List medians;
List large;
for (v : L) {
  int o = order.compare(v, p);
  if (o < 0) {
     small.append(v);
  } else if (o == 0) {
     medians.append(v);
  } else {
     large.append(v);
  } // if/else
} // for (v : L)
```

What about for the array-based version?  Hmmm ... this seems like something
closely related to the Dutch National Flag problem, doesn't it?  And so
we can use a similar approach.  The only difference is that we really only
need two sections, rather than three.  The typical implementation leaves
the pivot in element 0 while rearranging, and swaps it into the
correct place only after all the elements have been processed.
(Some folks, such as PM, put that pivot at the end, rather than the
front.)  Visually, the invariant looks like the following:

```text
+--+-----------------+--------------------+----------------+
| p| values <= pivot | unprocessed values | values > pivot |
+--+-----------------+--------------------+----------------+
|  |                 |                    |                |
lb lb+1              small                large            ub
```

Here's the state at the end of the loop.

```text
+--+-----------------+----------------+
| p| values <= pivot | values > pivot |
+--+-----------------+----------------+
|  |                 |                |
lb lb+1              small,large      ub
```

We can then swap the pivot into the end of the small section and
achieve our goal.

Analysis
--------

As noted above, the formal analysis of Quicksort is beyond the scope
of this course.  However, if you believe the claim that "the
randomly selected pivot usually divides the array relatively evently",
then we can use the same analysis that we used for merge sort.  And so
the algorithm is O(n*log<sub>2</sub>n).

Of course, if we choose our pivots badly, then Quicksort devolves
to an O(n<sup>2</sup>) algorithm, since each partition is O(n), and
a badly chosen pivot means that the recursive call is on an array
of size n-1.  Note that Quicksort devolves to this behavior if you
use the first element of the subarray as a pivot and the original
array is sorted (or reverse sorted).

Key ideas
---------

As we hope you noted, there are two key ideas in the design of
Quicksort.  First, as we learned in designing merge sort, using 
*divide and conquer* helps us achieve a faster 
sorting algorithm.  Quicksort adds the new idea that we can sometimes
leverage randomness to achieve our goals.

This exploration of Quicksort may have also reemphasized some other
more general ideas.  For example, you might have noted that loop 
invariants can help us design parts of our algorithm or that the
algorithms we write for lists and arrays are likely to be different.
You may have also noted some utility for higher-order procedures
here, something that Java currently lacks.

Partitioning
------------

Here's an example of partitioning in action.  Supose we start with
the following array of length 12.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|                                               |   
lb                                              ub  
```

We pick a random pivot.  Let's say that it's "h", which
is at position 3.  We swap the pivot to the start of the array so that
we always know where it is.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  h|  l|  p|  a|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|   |                                           |   
lb  sm                                          ub,lg
```

The first unprocessed element is `vals[1]`, or
"l", which is large.  So we swap it to the end of the array,
and update our indication of where the large elements are.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  h|  l|  p|  a|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|   |                                       |   |   
lb  sm                                      lg  ub  
```

Cleverly, we swapped an "l" with an "l",
so it's not necessarily obvious what happened.  Nonetheless, we move
forward.  The next unprocessed element is `vals[1]`, an
"l", which is large.  So we swap it to the end of the
unprocesed elements, and update our indication of where the large
elements are.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  h|  a|  p|  a|  a|  b|  e|  t|  i|  c|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|   |                                   |       |   
lb  sm                                  lg      ub  
```

The next unprocessed element is still `vals[1]`, or "a".  This time
it's small, so we advance our upper boundary on small elements.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  h|  a|  p|  a|  a|  b|  e|  t|  i|  c|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|       |                               |       |   
lb      sm                              lg      ub  
```

The next unprocessed element is `vals[2]`, or "p".
It's large, so we swap it to the end of the unprocessed section.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  h|  a|  c|  a|  a|  b|  e|  t|  i|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|       |                           |           |   
lb      sm                          lg          ub  
```

The next unprocessed element is small.  We advance our small
boundary.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  h|  a|  c|  a|  a|  b|  e|  t|  i|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|           |                       |           |   
lb          sm                      lg          ub  
```

The next unprocessed element is small.  We advance our small
boundary.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  h|  a|  c|  a|  a|  b|  e|  t|  i|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|               |                   |           |   
lb              sm                  lg          ub  
```

The next few unprocessed elements are small.  We advance our small
boundary.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  h|  a|  c|  a|  a|  b|  e|  t|  i|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|                           |       |           |   
lb                          sm      lg          ub  
```

We've encountered another large element.  We swap.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  h|  a|  c|  a|  a|  b|  e|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|                           |   |               |   
lb                          sm  lg              ub  
```

We're left with one unprocessed element.  It's large.  So we
swap it with itself and decrease the large boundary.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  h|  a|  c|  a|  a|  b|  e|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|                           |                   |   
lb                          s,l                 ub  
```

Okay, we're finished rearranging the values.  Now we want
to put the pivot in the middle.  So we swap it just to the
left of the bounardy we created.  (Why to the left? We'll
leave that as something for you to think about.)

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  e|  a|  c|  a|  a|  b|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
                         ***
```

We can now recurse on the two halves.

You may have observed a few places in which we could have made our
partition algorithm a bit more efficient.  And you should probably
make those improvements - we chose a simple partitioning algorithm for
clarity and to help ensure correctness.  Of course, if you do change
the algorithm, you should make sure to analyze its correctness and to
make sure you preserve the loop invariants.

Continuing the example
----------------------

Let's continue the example above.  We started with the array

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  l|  p|  h|  a|  b|  e|  t|  i|  c|  a|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|                                               |   
lb                                              ub  
```

After partitioning, we ended up with the following.
Note that the `***` means "in the correct place".

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  e|  a|  c|  a|  a|  b|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|                        ***                    |   
lb                                              ub  
```

What happens next?  We recurse on the left half.  (And we remember
that we have to recurse on the range 7-12.)

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  e|  a|  c|  a|  a|  b|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|                       |*** 
lb                      ub
```

Suppose we pick "c" as the pivot.  After partitioning,
we end up with the following.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  b|  a|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|                ***    |*** 
lb                      ub
```

Once again, we recurse on the left half.  (We also remember that we have to
process 5-6 when we're done, as well as the 7-12 from before.)

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  b|  a|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|               |***     *** 
lb              ub      
```

Suppose we pick one of the "a"'s as a pivot.  We partition.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|        ***    |***     *** 
lb              ub      
```

And we recurse once again.  We also remember that we have do deal with
3-4 (and 5-6 and 7-12 from before).

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|       |***     ***     *** 
lb      ub      
```

We pick one of the "a"'s as a pivot.  (Yes, you've
probably noted a potential improvement already.)  And we partition.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|    ***|***     ***     *** 
lb      ub      
```

We recurse on the left half.  We also remember that we have to recurse on
the right half, 2-2, as well as 3-4, 5-6, and 7-12.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|   |*** ***     ***     *** 
lb  ub  
```

It's a singleton element.  We know it's sorted.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
|***|*** ***     ***     *** 
lb  ub  
```

The most recently recursion left undone is 2-2.  After that, we'll
do 3-4, 5-6, and 7-12.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
 *** ***|***     ***     *** 
        lb,ub
```

That's an empty subarray, so we're done with that subarray.

Now we do the subarray with indices 3-4.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
 *** *** ***|   |***     *** 
            lb  ub
```

Another singleton array.  Done.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
 *** *** *** *** ***     *** 
```

5-6 is equally trivial, so we won't even show it.  We're now
left with 7-12.

```text
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  i|  t|  p|  l|  l|
+---+---+---+---+---+---+---+---+---+---+---+---+
 *** *** *** *** *** *** ***|                   |
                            lb                  ub
```

We pick a pivot and partition.  Let's say we pick "p".

```
0   1   2   3   4   5   6   7   8   9   10  11  12  
+---+---+---+---+---+---+---+---+---+---+---+---+
|  a|  a|  a|  b|  c|  e|  h|  l|  l|  i|  p|  t|
+---+---+---+---+---+---+---+---+---+---+---+---+
 *** *** *** *** *** *** ***|            ***    |
                            lb                  ub
```

And you can probably figure out the rest of the story.

Citations
---------

The first few sections of this reading are based closely on a reading
from CSC 151.  My sense is that I'm the original author of that
reading, since it seems to follow my normal style (and since I don't
see Quicksort in the earlier versions of 151).  However, I am equally
confident that Janet Davis and Jerod Weinman (and maybe Rhys Price
Jones) helped improve that original reading.

I wrote the rest of the reading and updated the early sections for some 
offering of CSC 207.

Quicksort was developed (discovered?) by C.A.R. Hoare.  There seem
to be at least two early articles by Hoare on Quicksort.

C. A. R. Hoare. 1961. Algorithm 64: Quicksort. *Commun. ACM* 4, 7 (July 1961), 321-. DOI=10.1145/366622.366644 <http://doi.acm.org/10.1145/366622.366644>

C. A. R. Hoare. 1962.  Quicksort.
*Comput. J.* 5, 1, 10–16. doi:10.1093/comjnl/5.1.10
<http://comjnl.oxfordjournals.org/content/5/1/10.full.pdf>


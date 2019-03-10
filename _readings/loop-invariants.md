---
title: Loop invariants
summary: |
  Loop invariants provide an invaluable tool not only for helping ensure
  the correctness of your code, but also for thinking about the structure
  of code in the first place.  We introduce the basics of loop invariants
  and consider both textual and visual invariants.
---

Introduction
------------

Experience shows that as programmers develop more complicated iterative
algorithms, they tend to make subtle mistakes in their design that are
hard to fix.  (Yes, programmers also make mistakes in their recursive
designs, but that's a separate issues.)  As you begin to develop more
complicated algorithms, you will find that a variety of tools can help
you better ensure that these algorithms are correct.  You've already
seen one kind of tool - a systematic test suite can help you identify
potential errors.  

*Loop invariants* provide an equally important starting point -
instead of having you reflect upon incorrect code, loop invariant
help you develop correct code from the start.  Basically, *a loop
invariant is a (relatively formal) statement about the state of
your program.* What do we mean about "the state of your program"?
Basically, the settings of some or all of the variables.

In the most basic form, a loop invariant is a useful statement about 
the values of variables with the following characteristic: If the
invariant holds before you begin the body of the loop, it holds 
after one pass through the body of the loop.

While that "one pass" rule seems simple, it's powerful:
If the invariant holds at the beginning of the first iteration, then
it holds at the end of the first iteration, and therefore at the
beginning of the second iteration.  If the invariant holds at the beginning
of the second iteration, then it holds at the end of the second
iteration, and therefore at the beginning of the third iteration.
If we continue this analysis, we see that if the invariant holds before
we start executing the loop, it still holds when we've finished all of
the iterations of the loop.

What else do we know when the loop has finished?  We know that the loop
test no longer holds.

In an ideal world, we design our loop invariant and our loop test so that
if we know that the loop invariant holds and the loop test doesn't hold,
then we can be sure we've achieved some goal.

A simple example: exponentiation
--------------------------------

Let's start with a simple example, computing x<sup>n</sup> for some
real number x and non-negative integer n.

```java
  /**
   * Compute x^n.
   *
   * @pre n >= 0.
   */
  public static double expt(double x, int n) {
    ....
  } // expt(double, int)
```

You've probably written an implementation of this function already,
using repeated multiplication, and that's the approach we'll use
in this example.  (Note that repeated multiplication is not the
most efficient strategy.  We'll consider some more efficient 
strategies in the future.)  We have a variable, `result`,
that contains our intermediate result.  We probably start that
value at 1.

```
    double result = 1.0;
```

Each time through the loop, we multiply `result` by `x`.  So, if
`result` is `x`<sup>`i`</sup> at the start of the body of the loop,
it will be `x`<sup>`(i+1)`</sup> at the end of the body of the loop.
Hmmm ... it looks like it's changed.  However, if we also increment
`i`, then `result` is `x`<sup>`i`</sup> for the new value of `i`.

So, we can write the body of the loop as

```java
  while (*test*) {
    // State: result = x^i
    result = result * x;
    i = i+1;
    // State: result = x^i
  } // while
```

When do we continue executing the body of the loop?  If we continue
when `i` is not equal to `n`, we know that the loop terminates only
when `i` equals `n`.

```java
  while (i != n) {
    // State: result = x^i
    result = result * x;
    i = i+1;
    // State: result = x^i
  } // while (i != n)
  // State: result = x^i
  // State: i == n
```

As the code suggests, when the loop terminates, we know two things
about the state of the program.  First, we know that `result` is
`x`<sup>`i`</sup>.  Second, we know that `i` is `n`.  Putting these
two facts together, we know that `result`  is `x`<sup>`n`</sup>.

How are we sure that the invariant holds at the start of the loop?
We've set the result to 1.  If `i` is 0, then `x`<sup>`i`</sup> is
1.

Putting it all together, we get the following.

```java
  /**
   * Compute x^n.
   *
   * @pre n >= 0.
   */
  public static double expt(double x, int n) {
    double result = 1.0;
    int i = 0;

    // Invariant: result = x^i
    while (i != n) {
      // State: result = x^i
      result = result * x;
      i = i+1;
      // State: result = x^i
    } // while
    // (1) result == x^i
    // (2) i == n
    // So result == x^n
    return result;
  } // expt(double, int)
```

Yes, that was a bit of extra work when compared to what we normally
do.  But the extra work can be worth it if we're sure that our code
is correct.  And, while we can be pretty sure that this simple code
is correct, even without the formal analysis, getting practice with
formal analysis on simple problems will help you do similar analysis
on more complex problems.

Loop termination
----------------

But wait!  We're only sure that the code our analysis is correct
if we know that the loop terminates.  How do we know that the loop
terminates?  We do a bit more analysis.

In a typical analysis, we come up with a number that measures the
amount of work remaining (the "size" of the remaining working), and
show that the amount of work decreases at each step.  If it decreases
at each step, it will eventually reach zero.

For our exponentiation example, we can use `n-i` as our measure of
the work remaining.  That expression starts with a non-negative
value (since `n` is non-negative and `i` is 0, `n-i` is `n`, which
is non-negative).  And, as `i` increases, the value of the expression
decreases.  Hence, the loop will eventually terminate.

Visual loop invariants
----------------------

In practice, we most frequently write invariants for procedures
that work with arrays of other collections of values?  Why do we
write such invariants?  Because it's easy to introduce subtle errors
into such procedures, and thinking carefully about not just the
expected state of the program after each loop repetition, but also
when it should stop, helps us make sure that we write correct loops.

For arrays, we usually find it easiest to draw a sketch of the state
of the array.  For example, here's an abstracted bit of information
about some array that we've divided into four parts.

```text
  -----+-----------+-----------+-----------+-----------+-----
   ... | property1 | property2 |           | property3 | ...
  -----+-----------+-----------+-----------+-----------+-----
       a           b           c           d           e
```

While the visuals are useful for thinking about the problem, many
programmers find it equally useful to restate them a bit more
formally.  (After all, formality can help with both precision and
clarity.) For this picture, we might write something like the
following.

```java
   // For all i, a <= i < b, property1(i).
   // For all i, b <= i < c, property2(i).
   // For all i, d <= i < e, property3(i).
```

Note that we don't have to state properties of all of the values
in the array.  For example, we haven't said anything about the 
values less than `a`, gerather than `e`,
or between `c` and `d`.

Another example: Finding the smallest value in an array
------------------------------------------------------

Let's do an example to better explore the visual loop invariants.
Consider finding the smallest value in an array of integers.  If
the array is not sorted, we will need to look through all of the
elements.  We can therefore think about a visual invariant that
starts as follows.

```text
  // Invariant:
  //   +-----------+-------------+
  //   | processed | unprocessed |
  //   +-----------+-------------+
  //   |           |             |
  //   0           i             length
```

What do we hope to know about the processed section?  Presumably, that
whatever candidate we have is no larger than the values in that section.
Alternately, that the values in that section are all at least as big
as that candidate, which we'll call `estim`.


```text
  // Invariant:
  //   +-----------+-------------+
  //   | >= estim  | unprocessed |
  //   +-----------+-------------+
  //   |           |             |
  //   0           i             length
```

Now let's think about the next step.  We look at the value at position
`i`.  If it's at least as large as `estim`, we can maintain the invariant.
If it's smaller than `estim`, we need to update `estim` in order to
maintain the invariant.  Will setting `estim` to `values[i]` maintain
the invariant?  Well, we know that `values[i]` is less than `estim`,
and that `estim` is less than or equal to everything in the processed
part of the array.  Transitivity suggests that `values[i]` is therefore
less than everything in that part.

How do we initialize `estim`?  We can initialize it to `values[0]`, and
be sure that it's less than or equal to everything with an index less
than 1.

```java
public int smallest(int[] values) {
  int estim = values[0];

  // Invariant:
  //   +-----------+-------------+
  //   | >= estim  | unprocessed |
  //   +-----------+-------------+
  //   |           |             |
  //   0           i             length
  for (int i = 1; i < values.length; i++) {
    if (values[i] < estim) {
      // Note: values[i] < estim, so values[i] < everything in the left
      estim = values[i];
  } // for

  // Conclusion
  // Invariant:
  //   +-------------------------+
  //   |        >= estim         |
  //   +-------------------------+
  //   |                         |
  //   0                         i, length
  return estim;
} // smallest
```

How do we know that the loop terminates?  Because we add 1 to `i`
each time through the loop.  If you add enough times, you'll reach
any upper bound.

Another example: Binary Search
------------------------------

Let's turn to a somewhat more complex example, that of binary search.

As you may have noted from your explorations of binary search, writing
iterative binary search correctly is difficult for many programmers.
(Writing recursive binary search may also be hard, but we're focusing
on iterative programs.)   As we think about it, binary search
narrows down the search space until we either find the element or
we know it's not there.

Let's start with the declaration.

```java
/**
 * Find the index of val in vals.
 *
 * @return i s.t., vals[i] == val.
 *
 * @pre vals is in order from smallest to largest.
 * @post vals is unmodified.
 *
 * @throws NotFound
 *   if val is not in vals.
 */
public int binarySearch(int[] vals, int val)
```

Midway through binary search, we can think about the state of the
system as follows.

```text
  +-------+-------------+-------+
  | < val | unprocessed | > val |
  +-------+-------------+-------+
  |       |             |       |
  0       lb            ub      length
```

That is,

```java
  // A: For all i, 0 <= i < lb, vals[i] < val.
  // B: For all i, ub < i < length, vals[i] > val.
```

We also need one other condition for binary search to work,
and we might not represent that visually.

```java
  // C: For all i, 0 <= i < length, vals[i] <= vals[i+1]
```

We might also write that condition as follows.

```
  // D: For all i,j, 0 <= i <= j < length, vals[i] <= vals[j]
```

We'll leave it to the mathematicians to prove the equivalence of 
conditions C and D.

Now, how do we guarantee that these invariants hold at the start of binary 
search?  The ordering conditions (C and D) are likely a precondition
to the algorithm.  If we set `lb` to 0, there are no
values between 0 and `lb`, so condition A holds.  If we
set `ub` to `length`, there are no values between
`ub` and `length`, so condition B holds.

When are we done?  Traditionally, we stop looking in one of two
cases: We've found the value or we know that the value can't be
there.  We could also stop when there is only one element left in
the "unknown" section and then determine whether or
not that element is equal to the desired value.  For now, we'll
use the first approach.  In that case, we have two options: We
see a matching value (we'll explore how later) or there's nothing
left, which requires that `lb >= ub`.

```java
  while ((!found) && (lb < ub)) {
    ...
  } // while
```

How do we measure the size of the work remaining, and how are we
sure that we reduce the size?  We'll treat the difference between
`ub` and `lb` as the size.  If, at each step, we either increase
`lb` or decrease `ub`, we know that we decrease the size of the
problem, and will eventually terminate.

How can we achieve these goals?  We can start by choosing any element
in the unprocessed section.  Usually, we choose the "middle" elements.

```java
  int mid = average(lb, ub);
```

Now, we have three options for the value at the midpoint.  It can
be less than `val`, it can be equal to `val`, and it can be greater
than `val`.  In the second case, we're done - we've found the value.
In the first case, we can set `lb` to `mid+1`.  Why?  Because we
know that `vals[mid] < val` (a requirement to be in that area) and
we know that everything to the left of `mid` must be less than or
equal to `vals[mid]` (property D).  In the third case, we can set
`ub` to `mid`.  Why?  Because we know that `vals[mid] > val` (a
requirement to be in the right area).  We also know that everything
to the right of `mid` must be at least as large (property D).

But wait!  You may be wondering why we're not setting `ub` to
`mid-1`, which seems traditional in binary search.  Well, we don't
know anything about the value at position `mid-1`, so we can't
safely use that value for the right range.

If we might set `ub` to `mid`, how do we know that the problem size
decreases?  We have to make sure that `mid` is strictly smaller
than `ub`.  Since we compute `mid` by averaging `lb` and `ub`, and
we know that `lb < ub`, we simply need to ensure that the `average`
function always rounds down.

We're now ready to put everything together.

```java
/**
 * Find the index of val in vals.
 *
 * @return i s.t., vals[i] == val.
 *
 * @pre vals is in order from smallest to largest.
 * @post vals is unmodified.
 *
 * @throws NotFound
 *   if val is not in vals.
 */
public int binarySearch(int[] vals, int val) {
  int lb = 0;           // Guarantee invariant A
  int ub = vals.length; // Guarantee invariant B

  // Invariant:
  //   +-------+-------------+-------+
  //   | < val | unprocessed | > val |
  //   +-------+-------------+-------+
  //   |       |             |       |
  //   0       lb            ub      length
  while (lb < ub) {
    int mid = average(lb, ub);
    if (vals[mid] < val) {
      lb = mid+1;
    } else if (vals[mid] == val) {
      return mid;
    } else {
      ub = mid;
    } // 
  } // while

  // We've finished the loop.  lb == ub.  Hence, our state is

  // +-------+-------+
  // | < val | > val |
  // +-------+-------+
  // |       |       |
  // 0       lb,ub   length

  // The value can't be there!
  throw new NotFound();
} // binarySearch(int[], int)
```

As the code suggests, once we've gotten through the loop, we can
guarantee that the value is not there, and therefore throw the
appropriate exception.

Further explorations
--------------------

You've seen some examples of loop invariants, for a simple loop,
for a somewhat complex array-based loop, and for a simpler loop.
What next?  It's about time for you to start writing your own
invariants.  You'll do so in the corresponding lab.

Citations
---------

The original version of this reading was inspired, in part, by Henry
Walker's two short articles on loop invariants.

* [Introduction to Loop Invariants](http://www.cs.grinnell.edu/~walker/courses/207.sp13/readings/reading-loop-invariants.shtml)
* [Pictorial Loop Invariants](http://www.cs.grinnell.edu/~walker/courses/207.sp13/readings/reading-loop-inv-pic.shtml)

Jon Bentley's reading on binary search remains my primary inspiration
for encouraging my students to think about loop invariants.

Jon Bentley. 1983. Programming pearls: Writing correct programs. *Commun. ACM* 26, 12 (December 1983), 1040-1045. DOI=10.1145/358476.358484 <http://doi.acm.org/10.1145/358476.358484>.


---
title: Loop invariants
summary: |
  Loop invariants provide an invaluable tool not only for helping ensure
  the correctness of your code, but also for thinking about the structure
  of code in the first place.  We introduce the basics of loop invariants
  and consider both textual and visual invariants.
todo:
  - Add another example on finding the smallest value in an array. 
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

  *Loop invariants* provide an equally important
  starting point - instead of having you reflect upon incorrect code,
  loop invariant help you develop correct code from the start.  Basically,
  *a loop invariant is a (formal) statement about the state of 
  your program.*  What do we mean about "the state of
  your program"?  Basically, the settings of all of the 
  variables.  

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

A Simple Example: Exponentiation
--------------------------------

  Let's start with a simple example, computing
  x<superscript>n</superscript> for some real number x and non-negative
  integer n.

<pre>
  /**
   * Compute x^n.
   *
   * @pre n &gt;= 0.
   */
  public static double expt(double x, int n)
  {
    ....
  } // expt(double, int)
</pre>

  You've probably written an implementation of this function already,
  using repeated multiplication, and that's the approach we'll use
  in this exmaple.  (Note that repeated multiplication is not the
  most efficient strategy.  We'll consider some more efficient 
  strategies in the future.)  We have a variable, `result`,
  that contains our intermediate result.  We probably start that
  value at 1.

<pre>
    double result = 1.0;
</pre>

  Each time through the loop, we multiply `result`
  by `x`.  So, if `result` is
  `x<superscript>i</superscript>` at the start of the body of
  the loop, it will be `x<superscript>(i+1)</superscript>` at
  the end of the body of the loop.  Hmmm ... it looks like it's changed.
  However, if we also increment `i`, then `result`
  is `x<superscript>i</superscript>` for the new value of
  `i`.

  So, we can write the body of the loop as

<pre>
  while (*test*)
    {
      // State: result = x^i
      result = result * x;
      i = i+1;
      // State: result = x^i
    } // while
</pre>

  When do we continue executing the body of the loop?  If we continue 
  when `i` is not equal to `n`, we know that
  the loop terminates only when `i` equals
  `n`.

<pre>
  while (i != n)
    {
      // State: result = x^i
      result = result * x;
      i = i+1;
      // State: result = x^i
    } // while (i != n)
  // State: result = x^i
  // State: i == n
</pre>

  As the code suggests, when the loop terminates, we know two things
  about the state of the program.  First, we know that 
  `result` is `x<superscript>i</superscript>`.
  Second, we know that `i` is `n`.  Putting
  these two facts together, we know that `result`  is
  `x<superscript>n</superscript>`.

  How are we sure that the invariant holds at the start of the
  loop?  We've set the result to 1.  If `i` is 0,
  then `x<superscript>i</superscript>` is 1.

  Putting it all together, we get the following.

<pre>
  /**
   * Compute x^n.
   *
   * @pre n &gt;= 0.
   */
  public static double expt(double x, int n)
  {
    double result = 1.0;
    int i = 0;

    // Invariant: result = x^i
    while (i != n)
      {
      } // while
    // (1) result == x^i
    // (2) i == n
    // So result == x^n
    return result;
  } // expt(double, int)
</pre>

  Yes, that was a bit of extra work when compared to what we
  normally do.  But the extra work can be worth it if we're sure
  that our code is correct.  And, while we can be pretty sure that
  this simple code is correct, even without the formal analysis,
  getting practice with formal analysis on simple problems will
  help you do similar analysis on more complex problems.

Loop Termination
----------------

  But wait!  We're only sure that the code our analysis is correct
  if we know that the loop terminates.  How do we know that the loop
  terminates?  We do a bit more analysis.

  In a typical analysis, we come up with a number that measures
  the amount of work remaining (the "size" of the
  remaining working), and show that the amount of work decreases
  at each step.  If it decreases at each step, it will eventually
  reach zero.

  For our exponentiation example, we can use `n-i` as
  our measure of the work remaining.  That expression starts with
  a non-negative value (since `n` is non-negative
  and `i` is 0, `n-i` is `n`,
  which is non-negative).  And, as `i` increases, the
  value of the expression decreases.  Hence, the loop will eventually
  terminate.

Visual Loop Invariants
----------------------

  In practice, we most frequently write invariants for procedures 
  that work with arrays of other collections of values?  Why do we
  write such invariants?  Because it's easy to introduce subtle 
  errors into such procedures, and thinking carefully about not 
  just the expected state of the program after each loop repetition,
  but also when it should stop, helps us make sure that we write
  correct loops.

  For arrays, we usually find it easiest to draw a sketch of the
  state of the array.  For example, here's an abstracted bit of information
  about some array that we've divided into four parts.

<pre>
  -----+-----------+-----------+-----------+-----------+-----
   ... | property1 | property2 |           | property3 | ...
  -----+-----------+-----------+-----------+-----------+-----
       a           b           c           d           e
</pre>

  While the visuals are useful for thinking about the problem, many
  programmers find it equally useful to restate them a bit more
  formally.  (After all, formality can help with both precision and clarity.)
  For this picture, we might write something like the following.

<pre>
   // For all i, a &lt;= i &lt; b, property1(i).
   // For all i, b &lt;= i &lt; c, property2(i).
   // For all i, d &lt;= i &lt; e, property3(i).
</pre>

  Note that we don't have to state properties of all of the values
  in the array.  For example, we haven't said anything about the 
  values less than `a`, gerather than `e`,
  or between `c` and `d`.

Another Example: Binary Search
------------------------------

  As you may have noted from your explorations of binary search, writing
  iterative binary search correctly is difficult for many programmers.
  (Writing recursive binary search may also be hard, but we're focusing
  on iterative programs.)   As we think about it, binary search
  narrows down the search space until we either find the element or
  we know it's not there.

  Let's start with the declaration.

<pre>
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
</pre>

  Midway through binary search, we can think about the state of the
  system as follows.

<pre>
  +-------+-------------+-------+
  | &lt; val | unprocessed | &gt; val |
  +-------+-------------+-------+
  |       |             |       |
  0       lb            ub      length
</pre>

  That is,

<pre>
  // A: For all i, 0 &lt;= i &lt; lb, vals[i] &lt; val.
  // B: For all i, ub &lt; i &lt; length, vals[i] &gt; val.
</pre>

  We also need one other condition for binary search to work,
  and we might not represent that visually.

<pre>
  // C: For all i, 0 &lt;= i &lt; length, vals[i] &lt;= vals[i+1]
</pre>

  We might also write that condition as follows.

<pre>
  // D: For all i,j, 0 &lt;= i &lt;= j &lt; length, vals[i] &lt;= vals[j]
</pre>

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
  left, which requires that `lb &gt;= ub`.

<pre>
  while ((!found) &amp;&amp; (lb &lt; ub))
    {
      ...
    } // while
</pre>

  How do we measure the size of the work remaining, and how are we sure that
  we reduce the size?  We'll treat the difference between `ub`
  and `lb` as the size.  If, at each step, we either increase
  `lb` or decrease `ub`, we know that we decrease
  the size of the problem, and will eventually terminate.

  How can we achieve these goals?  We can start by choosing any element 
  in the unprocessed section.  Usually, we choose the "middle"
  elements.

<pre>
  int mid = average(lb, ub);
</pre>

  Now, we have three options for the value at the midpoint.  It can
  be less than `val`, it can be equal to `val`,
  and it can be greater than `val`.  In the second case,
  we're done - we've found the value.  In the first case, we can set
  `lb` to `mid+1`.  Why?  Because we know that
  `vals[mid] &lt; val` (a requirement to be in that area)
  and we know that everything to the left of `mid` must be
  less than or equal to `vals[mid]` (property D).  In the
  third case, we can set `ub` to `mid`.  Why?
  Because we know that `vals[mid] &gt; val` (a requirement
  to be in the right area).  We also know that everything to the right
  of `mid` must be at least as large (property D).

  But wait!  You may be wondering why we're not setting
  `ub` to `mid-1`, which seems traditional
  in binary search.  Well, we don't know anything about the value
  at position `mid-1`, so we can't safely use that value
  for the right range.

  If we might set `ub` to `mid`, how do we
  know that the problem size decreases?  We have to make sure that
  `mid` is strictly smaller than `ub`.  Since
  we compute `mid` by averaging `lb` and
  `ub`, and we know that `lb &lt; ub`, we
  simply need to ensure that the `average` function
  always rounds down.

  We're now ready to put everything together.

<pre>
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
{
  int lb = 0;           // Guarantee invariant A
  int ub = vals.length; // Guarantee invariant B

  // Invariant:
  //   +-------+-------------+-------+
  //   | &lt; val | unprocessed | &gt; val |
  //   +-------+-------------+-------+
  //   |       |             |       |
  //   0       lb            ub      length
  while (lb &lt; ub)
    {
      int mid = average(lb, ub);
      if (vals[mid] &lt; val)
        {
          lb = mid+1;
        } // if vals[mid] &lt; val
      else if (vals[mid] == val)
        {
          return mid;
        } // if (vals[mid] == val)
      else // (vals[mid] &gt; val)
        {
          ub = mid;
        } // if (vals[mid] &gt; val)
    } // while

  // We've finished the loop.  lb == ub.  Hence, our state is

  // +-------+-------+
  // | &lt; val | &gt; val |
  // +-------+-------+
  // |       |       |
  // 0       lb,ub   length

  // The value can't be there!
  throw new NotFound();
} // binarySearch(int[], int)
</pre>

  As the code suggests, once we've gotten through the loop, we can
  guarantee that the value is not there, and therefore throw the
  appropriate exception.

Further Explorations
--------------------

  You've seen some examples of loop invariants, both for a simple
  loop and for an array-based loop.  What next?  It's about time
  for you to start writing your own invariants.  You'll do so in
  the corresponding lab.

Citations
---------

  This reading was inspired, in part, by Henry Walker's two short articles
  on loop invariants.

* [Introduction to Loop Invariants](http://www.cs.grinnell.edu/~walker/courses/207.sp13/readings/reading-loop-invariants.shtml)
* [Pictorial Loop Invariants](http://www.cs.grinnell.edu/~walker/courses/207.sp13/readings/reading-loop-inv-pic.shtml)
  Jon Bentley's reading on binary search remains my primary inspiration
  for encouraging my students to think about loop invariants.

  Jon Bentley. 1983. Programming pearls: Writing correct programs. *Commun. ACM* 26, 12 (December 1983), 1040-1045. DOI=10.1145/358476.358484 <http://doi.acm.org/10.1145/358476.358484>.


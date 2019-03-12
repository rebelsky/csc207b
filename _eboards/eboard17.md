---
title: Eboard 17   Analyzing recursive algorithms
number: 17
section: eboards
held: 2019-03-04
link: true
---
CSC 207.01 2019S, Class 17:   Analyzing recursive algorithms
============================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
* Big-O, revisited
* Iterative analysis, revisited
* Recurrence relations
* Approaches to recurrence relations

Preliminaries
-------------

### News / Etc.

* Welcome to any prospective students we have.  Thank you for bringing
  warmer weather with you.
* I'm back!  I hope that you had a good time without me.  I apologize
  for the inconsistency in communication.
* I brought you conference swag.  (One of each item per person.)
* Blake says "Be proud that you are able to think technically and talk 
  about it,."

### Upcoming work

* [Assignment 5](../assignments/assignment05) due Tuesday night.
* Exam 1 to be distributed tonight.  Sorry for the delay in getting it out.
    * Prologue due Thursday night
    * Exam due the following Thursday.
* Reading for Wednesday: 
  [Anonymous functions](../readings/anonymous-functions)
  (to be posted tonight)
* Lab writeup: [None]

### Extra credit

#### Extra credit (Academic/Artistic)

#### Extra credit (Peer)

* March 8-10 (7:30 7:30 2:00), Twelfth Night.  
* Grinnell Singers Sunday, March 10 at 2pm.

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

### Questions

_What's the problem with the linear average algorithm?_

> Potential overflow!

Big-O analysis, revisited
-------------------------

What is Big O? 

* A tool to analyze the number of steps a program takes to run.
* Used to describe functions that provide an upper bound on that number.
* Also used to provide bounds on memory usage.
* Something that has a formal definition, but is often used informally.
    * The formal definition supports the informal use.

What is the formal definition of Big O?

* f(n) is in O(g(n)) iff there exist n0 > 0, c > 0 s.t.
  for all n > n0, f(n) <= c*g(n)
    * Informally: "For large enough n" (e.g., 100000000 + n is bounded
      above by 1/100*n^2, but only when n is above 1000000 or so)
    * Informally: We care about the shape more than we care about
      multipliers (e.g. n^2 and n^2/2 and 1000*n^2 are all the same
      as bounds)
    * That lets us ignore piddly little details like the difference
      in cost between addition and multiplication and a function call.
      All of them are "1"
* O(g(n)) is "the set of all functions bounded above by g(n)"

Some important properties

* Transivity: if f(n) is in O(g(n)) and g(n) is in O(h(n)) then
  then f is in O(h(n))
* If f(n) is in O(g(n)) then f(n) + g(n) is in O(g(n))
    * If you conclude, for example, that the running time of a function
      is in O(n + n^2), you might as well say that it's in O(n^2).
* If f(n) is in O(c*g(n)), then f(n) is in O(g(n))

Big Oh in practice

* We tend to strive for tight bounds.  For example, if we decided
  that a function is 3*n + n*logn, we describe it as O(nlogn), not
  as O(n^100).
* When you take 301, you'll learn about a variety of other bounds.a

Iterative analysis, revisited
-----------------------------

```java
// sort an array
public void sort(int[] arr) {
  for (int i = 0; i < arr.length; i++) {
    swap(arr, i, smallest(arr, i));
  }
}

// Find the index of the smallest value in the
// subarray starting at position start
public int smallest(int[] arr, int start) {
  ...
} // smallest 
```

* For a for loop, the easiest approach is as follows
    * Count the number of repetitions (n)
    * Count the cost of the body (?)
    * Multiply the two
* For a function call (e.g., `swap`, `smallest`)
    * Look at the cost of the body of the function
    * `swap` is in O(1)
    * `smallest` is in O(n)
* For a conditional (`if (test) then conseqeuent else alternate`)
    * We can think about best and worst case scenarios, we generally assume 
      worst for big O
    * Add the cost of the test to the larger of the cost of the consequent
      and alternate.
* For "simple" expressions, treat the cost as 1

```
if (test) then
  say "Hello"
else
  for each atom in the universe ...
```

Sometimes it can be useful to "unroll" the loop, particularly if we
are doing something a bit strange.

```
for (i = 1; i < n; i *= 2) {
  swap(arr, i, smallest(arr, arr.length - i));
} 
```

Following the previous strategy, the running time seems to be

* Count the number of iterations: log_2(n)
* Count the cost of the body: O(n)
* Multiply the two: O(nlogn)

Suppose we look at it as follows

* smallest takes 1 step the first iteration
* smallest takes 2 steps the second iteration
* smallest takes 4 steps the third iteration
* smallest takes 8 steps the fourth iteration
* ...
* smallest takes n/2 steps the penultimate iteration
* smallest takes n steps the final iteration

The running time appears to be 1+2+4+8+16+....+n/2+n.

More generally, the sum 1+2+4+...+2^k = ???

Let's look at some patterns

* k=0: sum = 1
* k=1: sum = 3
* k=2: sum = 7
* k=3: sum = 15
* k=4: sum = 31
* k=5: sum = 63
* k=6: sum = 127
* k=7: sum = 255
* k=8: sum = 511

sum is 2^(k+1) - 1

* Proof by induction

When k = log_2n

2^(k+1) - 1 = 2^(log_2n+1) - 1 = 2*2^log_n - 1 = 2*n-1 in O(n)

Does unrolling help with our first algorithm?

```
public void sort(int[] arr) {
  for (int i = 0; i < arr.length; i++) {
    swap(arr, i, smallest(arr, i));
  }
}
```

* n + n-1 + n-2 + n-3 + ... + 3 + 2 + 1
* UM: Use the metric ...
* n(n+1)/2
* O(n^2)
* Unrolling the loop didn't help
* In general, if the loop increments by one, unrolling won't help.

Quiz
----

Recurrence relations
--------------------

How do we analyze recursive functions?

```
public BigInteger factorial(int n) {
  if (n == 0) {
    return new BigInteger(1);
  }
  else {
    return (new BigInteger(n)).multiply(factorial(n-1));
  }
} // factorial
```

Analysis:

* To figure out the running time of factorial, we look at the body.
* The body contains an if.  We should do the cost of the test plus
  the cost of the worst path.
* The cost of the test is 1.
* The cost of the worst path is the cost of
    * Creating a big integer 1
    * Multiplying 1
    * Computing factorial of n-1 (GM hypothesizes that this is O(n))

What do we do?  We write an equation.  T(n) is "the time that this alg.
takes on an input of size n."

* T(n) = 3 + T(n-1)
* T(0) = 2

Our goal is to figure out what function meets those two criteria.

Detour:

```drracket
public BigInteger factorial(int n) {
  if (n == 0) {
    return new BigInteger(1);
  }
  if (n != 0) {
    return (new BigInteger(n)).multiply(factorial(n-1));
  }
} // factorial
```

Note: Java will not compile the latter because it doesn't do the deep
analysis to tell that every path returns something.

Approaches to recurrence relations
----------------------------------

Here's our first recurrence relation.

* T(n) = 3 + T(n-1)
* T(0) = 2

Strategy one: Do repeated substitution, look for a pattern, prove the
pattern correct.

* T(n) = 3 + T(n-1)
* T(n) = 3 + 3 + T(n-2) = 2*3 + T(n-2)
* T(n) = 2*3 + 3 + T(n-3) = 3*3 + T(n-3)
* T(n) = k*3 + T(n-k)
* When k = n, T(n) = n*3 + T(0) = n*3 + 2
* T(n) is in O(n)

What should we do about the fact that the cost of multiplication of big 
integers increases as the integer increases.

* Punt (ignore it)
* Include it in your formula, increasing our complexity somewhat

Here's another recurrence relation.  (Relation 2)

* T(n) = n + T(n-1)
* T(0) = 1

Analysis

* T(n) = n + T(n-1)
* T(n) = n + n-1 + T(n-2)
* T(n) = n + n-1 + n-2 + T(n-3)
* T(n) = n + n-1 + n-2 + ... + k-1 + T(n-k)
* T(n) =~ n(n-1)/2
* T(n) is in O(n^2)

With your partner (or someone nearby), try the repeated expansion to see
what patterns you see.

Relation 3

* T(n) = n + T(n/2)
* T(x) = x + T(x/2)
* T(1) = 1
* T(0) = 1
* T(n) is in O(nlogn)
* T(n) = n + T(n/2)
* T(n) = n + n/2 + T(n/4)
* T(n) = n + n/2 + n/4 + T(n/8)
* T(n) is approximately 2n-1, in O(n)

Detour

* logn and log_2n are often treated as equivalent in cs
* ln(n) is used for the natural log
* It doesn't matter in big-O

Relation 4

* T(n) = n + 2*T(n/2)
* T(1) = 1
* T(0) = 1

Relation 5

* T(n) = 1 + 2*T(n/2)
* T(1) = 1
* T(0) = 1

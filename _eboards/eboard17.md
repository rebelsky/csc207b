---
title: Eboard 17   Analyzing recursive algorithms
number: 17
section: eboards
held: 2019-03-04
link: true
current: true
---
CSC 207.02 2019S, Class 17:   Analyzing recursive algorithms
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
* Exam 1 to be distributed in concrete form tonight.  Sorry for the delay 
  in getting it out.
    * Prologue due Thursday night
    * Exam due the following Thursday.
* Reading for Wednesday: 
  [Anonymous functions](../readings/anonymous-functions)
  (to be posted tonight)
* Lab writeup: [None]

### Extra credit

#### Extra credit (Academic/Artistic)

* March 8-10 (7:30 7:30 2:00), Twelfth Night.  Box office opens today
  at noon.

#### Extra credit (Peer)

* Grinnell Singers March 10 at 2pm.

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

* Environmental talk tonight at 7:30 in Noyce 2021.  Sounds really cool.
  (Appropriate for this weather.)

### Questions

_What's the problem with the linear average algorithm?_

> Potential overflow!

_When will we get assignments back?_

> Soon, I hope, except for the evil assignment (which you all got
  25 on).

> Sam will push on the graders.  (Or maybe Sam will push on Sam.)

Quiz
----

Joy and fun, maybe.

Big-O analysis, revisited
-------------------------

What is Big O and why do we use it?

* A way to analyze programs in terms of how long they take
  (or how much memory they use)
* A way to classify functions (e.g., linear, exponential)
  E.g. A function in O(n) is "linear".
* We write a function that models how long our algorithm takes
  (how much memory it uses)
* We might want to compare our model to actual experiments.
* Big-Oh notation is used to provide upper bounds on functions
* Using a formally defined mechanism
* That lets us describe the overall "shape" of the bound of a function.

Formal definition

* f(n) is in O(g(n)) iff exist c > 0, n0 > 0, s.t. for all n > n0,
  f(n) <= c*g(n).
* <= indicates the upper bound
* n > n0, "for sufficiently large n".  Compare 10000000*n vs n^2/100.
  For small n, you should use the quadratic one, but when n is big
  enough the n^2/100 dominates.
* c > c0, "we don't care about constant multipliers; we care primarily
  about the overall shape"
    * Most of our analyses do not carefully distinguish between the various
      costs of "constant time" operations (e.g., addition, multiply,
      functional call are all "1 unit")

The formal definition of Big O lets us prove a variety of important
properties of the notation.

* If f(n) is in O(g(n)) and g(n) is in O(h(n)), f(n) is in O(h(n))
    * 5*n^2 is in O(n^2); it is also in O(n^44)
    * We try to pick the tightest bound possible.
    * How do we prove this? 
       * Set theory.
       * Given the c and n0 for the first rule, c and n0 for the
         second rule (d and n1), come up with a c and n0 (C and N0) for 
         the third rule.  E.g., C = c*d and N0 = max(n0,n1)
       * f(n) < c*g(n), g(n) < d*h(n), so c*g(n) < c*d*h(n)
       * Then we have transitivity of <.
* If f(n) is in O(g(n)) then f(n)+g(n) is in O(g(n))
    * You can throw away lower-order terms.
    * E.g., 5n+n^2 is in O(n^2)
* c*f(n) is in O(f(n))

Iterative analysis, revisited
-----------------------------

Normal techniques for bounding algorithms.

* Take a structure, have a rule for bounding that structure.

Bound on a sequence of steps is the sum of the bounds of the individual
steps.

```
a[0] = 1 // 1 step
a[0] = largest(a) // n steps
```

Bound on a for loop  E.g.,

* Count the number of times the loop executes
* Count the cost of the body of the loop
* Multiply the two

```
selection_sort(int a) {
  for (int i = 0; i < n; i++) {
    swap(a[i], index_of_smallest(a, i))
  }
}
// Find the location of the smallest element in the array,
// looking starting at start.
int index_of_smallest(int a, int start) {
  ...
}
```

* This loop executes n times
* In the body, we compare (hidden) [1], increment (hidden) [1], 
  swap [1], and compute the index of the smallest [n]
* The running time of this algorihtm is n*(3+n) = 3*n + n^2 in O(n^2)

What is the cost of a conditional?

```
if (test) {
  consequent;
} else {
  alternate;
}
```

```
if (test) {
  x = x+1;
} else {
  for (int i = 0; i < number_of_atoms_in_the_universe; i++) {
  }
}
```

Cost is cost of test + max of cost of consequent and alternative

You can do a lot of analysis like this, but sometimes it's helpful
to unroll your loops.

```
for (int i = n; i > 1; i = i/2) {
  a[i] = smallest(a, i);        // smallest looks at positions 0 ... i
} // for
```

* Number of repetitions: log_2(n) (also written as log(n) or logn)
* Cost per repetition: n
* Product: O(nlogn)

Unroll the loop

* First iteration: n
* Second iteration: n/2
* Third iteration: n/4
* Fourth iteration: n/8
* Kth iteration: n/2^(k-1)

What is n + n/2 + n/4 + n/8 + n/2^k (or what's a bound on it?)

* k=0: n
* k=1: n + n/2 = 3n/2
* k=2: n + n/2 + n/4 = 7n/4
* k=3: n + n/2 + n/4 + n/8 = 15n/8
* k=4: n + n/2 + n/4 + n/8 + n/16 = 31n/16
* k=5: n + n/2 + n/4 + n/8 + n/16 + n/32 = 63n/32
* General: 2n - 1/2^k, approaches 2n

Suggestion

* Our algorithm is in O(n)

Notes

* We just concluded it's in O(n)
* We previously concluded it's in O(nlogn)
* The O(n) is a better (closer) bound.

Recurrence relations
--------------------

As computer scientists, we often write recursive algorithms.

```
merge_sort(A) {
  if (A.length <= 1) {
    // Do nothing, it's sorted
  }
  else {
    split array into two new subarrays A1 and A2
    A1 = merge_sort(A1)
    A2 = merge_sort(A2)
    combine them back together
  }
}
```

To analyze this algorithm, we'll invent a function, T(n), that represents
the running time 

* T(n) = 1 (for test) + n (to split) + T(n/2) + T(n/2) + n (to merge)
* T(n) = 1 + 2n + 2(T(n/2))
* T(1) = 1
* T(0) = 1

How do we figure out what functions this rule indicates?  How do we find
the fixed form of this recursive formulation?

* T(n) = 1 + 2n + 2(T(n/2))

Approaches to recurrence relations
----------------------------------

Let's try a slightly simpler one.  We'll try repeated expansion

* T(x) = x + 2*T(x/2)
* T(n/2) = n/2 + 2*T(n/2/2)
* T(n/4) = n/4 + 2*T(n/4/2)

Following the steps

* T(n) = n + 2*T(n/2)
* T(n) = n + 2(n/2 + 2*T(n/4))
* T(n) = n + n + 4*T(n/4)
* T(n) = 2n + 4*T(n/4)
* T(n) = 2n + 4*(n/4 + 2*T(n/8))
* T(n) = 2n + n + 8*T(n/8) = 3n + 8*T(n/8)
* T(n) = kn + 2^k*T(n/2^k)
* When k is logn, 2^k is n.  T(N) = nlogn + n*T(1) is in O(nlogn)




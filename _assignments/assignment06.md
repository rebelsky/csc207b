---
title: Assignment 6
subtitle: Complexity analysis
copyright:  Copyright &copy; Peter-Michael Osera and Samuel A. Rebelsky.
copyright_message: |
  This work is licensed under a Creative Commons Attribution-NonCommercial
  4.0 International License.  To view a copy of this license, visit
  <http://creativecommons.org/licenses/by-nc/4.0/>.
summary: |
  To get some practice (a) solving small algorithmic problems and (b) analyzing the complexity of solutions to these problems, you'll solve a handful of these problems in Java.
collaboration: |
  Each student should turn in his, her, zir, or their own solutions.
  Students should feel free to support each other, provided they
  cite any help they've received.
link: true
current: true
---
In addition to placing your code in a private GitHub repository, you should also include a `README.md` file as well as a write-up of the complexity analyses and questions described in each problem in a file called `writeup.txt`.

## Problem 1: Contains

Write a static method `contains` that takes three parameters---an array of doubles `dubs`, a double value `eps`, and a double `d`---and returns `true` if there is a element `dubs[i]` in the array such that `abs(dubs[i] - d) < eps`.

Once you have written the `contains` method, analyze its *worst-case* complexity.
Choose an appropriate set of operations to count, define the input of your model, describe what the worst-case scenario is for this method, and give a model _T_ of the time complexity of your method.
Finally, summarize the model with a tight upper bound characterization of _T_ using Big-O notation, proving (by giving an appropriate _c_ and _x_0_) that the upper bound is sound.

## Problem 2: Fast exponentiation

Write a method `fastModExp` that takes three integers, _x_, _y_, and _m_ and returns _x<sup>y</sup>_ mod _m_.
Your method should take advantage of two important identities.

* _x_<sup>_y_</sup> mod _m_ = (_x_<sup>2</sup> mod _m_)<sup>(_y_/2)</sup> mod _m_ when _y_ is even.
* _x_<sup>_y_</sup> mod _m_ = (_x_ * (_x_<sup>(_y_-1)</sup> mod _m_)) mod _m_ when _y_ is odd.

Once you have written the `fastModExp` method, analyze its complexity, assuming that the input _y_ is a power of two.
Choose an appropriate set of operations to count, define the input of your model and give a model _T_ as a recurrence relation of the time complexity of your method.
Solve your recurrence relation using the substitution method described in class to obtain an explicit formula for the time complexity.
Finally, summarize the model with a tight upper bound characterization of _T_ using Big-O notation, proving (by giving an appropriate _c_ and _x<sub>0</sub>_) that the upper bound is sound.

(*Hint*: your complexity model should not be linear!)

## Problem 3: All pairs

With the following class definition for a Pair class (defined in `Pair.java`):

```java
// In IntPair.java
public class IntPair {
  private int fst;
  private int snd;
  public IntPair(int fst, int snd) {
    this.fst = fst;
    this.snd = snd;
  } // IntPair(int,int)

  public int getFst() { 
    return fst; 
  } // getFst()

  public int getSnd() { 
    return snd; 
  } // getSnd()
} // IntPair
```

Write a static method in your `Program` class called `allPairs` that takes an integer array and returns all possible pairs of elements from the input array in a new array (of type `Pair` array).
The set of possible pairs includes pairing each element with itself.
For example, if the input array `arr` has the form `{ 3, 5, 9 }`, then `allPairs(arr)` returns the array `{ (3, 3), (3, 5), (3, 9), (5, 3), (5, 5), (5, 9), (9, 3), (9, 5), (9, 9) }` (although not necessarily in that order).
If the `null` array is passed to `allPairs`, then an `IllegalArgumentException` should be thrown.

Once you have written the `allPairs` method, analyze its complexity.
Choose an appropriate set of operations to count, define the input of your model, and give a model _T_ of the time complexity of your method.
Finally, summarize the model with a tight upper bound characterization of _T_ using Big-O notation, proving (by giving an appropriate _c_ and _x<sub>0</sub>_) that the upper bound is sound.

## Problem 4: Concat-replicate

Write a static method called `concatAndReplicateAll` that takes an array of strings and an integer _n_ and returns a single string that is the result of *replicating* them all _n_ times and then *concatenating* them all together.
For example, if the input array `arr` has the form `{ "hello", "world", "!" }` and _n = 3_ then `concatAndAreplicateAll(arr)` returns the string `"hellohellohelloworldworldworld!!!"`
If the `null` array is passed to `concatAndReplicateAll`, then an `IllegalArgumentException` should be thrown.

Once you have written the `concatAndReplicateAll` method, analyze its complexity *informally*.
Choose an appropriate set of operations to count, define the input of your model, give a tight upper bound characterization of the method's time complexity using Big-O notation, and in a sentence, justify your choice of bound.

Finally, does your analysis change if I tell you that Java string concatenation of two strings of lengths _n_ and _m_ is _O_(_n_+_m_)?
This is because strings are immutable, so that building the resulting string consists of traversing both strings rather than simply appending the second string onto the first.
State how your runtime changes as a result of this new information.

## Problem 5: Interleave

Write a static method called `interleave` that takes two arrays of integers and returns a third array that is the result of interleaving the first array with the second.
For example, if the input arrays `arr1` and `arr2` have values `{0, 1, 2}` and `{3, 4, 5}`, respectively, then `interleave(arr1, arr2)` produces the new array `{0, 3, 1, 4, 2, 5}`.
If one array is longer than the other, then the remains of the longer array are simply placed at the end of the output array after interleaving.
For example, if `arr2` above was `{3, 4, 5, 6, 7, 8}` then `interleave(arr1, arr2)` produces `{0, 3, 1, 4, 2, 5, 6, 7, 8}`.

Once you have written the `interleave` method, analyze its time complexity *informally*.
Choose an appropriate set of operations to count, define the input of your model, give a tight upper bound characterization of the method's time complexity using Big-O notation, and in a sentence, justify your choice of bound.

Next, analyze the `interleave` method's *space complexity* informally.
Recall that the space complexity of a function is how much memory it uses---heap allocations and stack space (for recursive functions).
Give a tight upper bound characterization of the method's space complexity using Big-O notation and in a sentence, justify your choice of bound.

Finally, review the previous problem's functions and their space complexity.
(You do not need to provide them in your write-up, but you should go back and informally analyze their space complexity).
After review, what can you conclude about the *relationship* between time and space complexity?
In other words, if we know the space complexity of a function, can we put a bound on its time complexity?

Acknowledgements
----------------

The original version of this assignment was written by Peter-Michael Osera.
Samuel A. Rebelsky make some updates for Spring 2019.

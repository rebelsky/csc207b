---
title: Eboard 32  Hash tables
number: 32
section: eboards
held: 2019-04-22
link: true
---
CSC 207.02 2019S, Class 2^5:  Hash tables
=========================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
* Hash tables: Key ideas
* An exercise: A simple hash function
* Analyzing efficiency
* From objects to integers
* Improving our hash function
* Complexities

Preliminaries
-------------

### News / Etc.

* Today is a talk day.  Sit where you would like.
* Apologies for continuing to be behind.  I'm still not perfectly well,
  and sleep is trumping other issues.
* Copies of the skip list reading are available at the back of the room.

### Upcoming work

* No additional reading!   (But you may ant to review.)
* [Assignment 8](../assignments/assignment08) due Thursday the 25th
* No lab writeup for today

### Extra credit

#### Extra credit (Academic/Artistic)

* **New**: CS Table, Tomorrow, "The Cathedral and the Bazaar"
* **New**: "Plan your CS Major with WGMC", Tuesday, 7pm, CS Commons
    * People of all genders welcome.
* PBK Convo, Thursday, 11am: "Antievolutionism in Historical Perspective"
* **New**: McKibben lecture, Thursday, 4:15 p.m., JRC 101
* **New**: Math/Stats seminar tomorrow at 11am in 2517.

#### Extra credit (Peer)

* **New/Questionable**: Track and Field at Grand View on Friday.

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* **TODAY** 30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* **TONIGHT** Public speaking workshop - April 22 at 7pm in HSSC S3325, with
  Kathy Clemons-Beasley '05.  Please register.

### Other good things

### Questions

On the homework, when we remove, what do we do?

> The same thing you do with remove in normal linked lists, except
  at greater scale: Every previous pointer skips over the node.

What code should I use for that?

> Something like

> `prev.next.set(level, prev.next.get(level).next.get(level));`

What was your favorite part of this morning's CSC 151?

        (define deep-reverse
          (lambda (val)
            (if (list? val)
                (reverse (map deep-reverse lst))
                val)))

Quiz
----

Hash tables: Key ideas
----------------------

_Suppose I didn't do the reading for today and that I'd never seen hash
tables before.  What would you tell me to give me a high-level understanding
of hash tables?_ [note: I did the reading and I've seen hash tabales.]

* Implements the Map ADT.  (Associates keys and values.)
* We store key/value pairs in an array.
* Uses a hash function to turn a key into an integer.  (We still
  want to have our map store keys and values.)
* We mod the integer by the size of the array.  That gives us an index
  in the array.
* Multiple keys could hash to the same integer, particularly if we
  do the mod.  (A collision)
    * Probing: Keep looking to "the right" (next index)
    * Bucketing: Instead of storing key/value pairs, we store lists
      of key value pairs.

An exercise: A simple hash function
-----------------------------------

Consider the following hash function for strings: 

* Convert each character to an integer.
* Add them up.

For example:

* SAM: S (19) + A (1) + M (13) = 33.
* JEROD: J (10) + E (5) + R (18) + O (15) + D (4) = 52.

What do you think?

* Chance of collisions seem high; if you add a small range of values,
  you're likely to get similar results for the same length strings.
* Anagrams have the same hash.

Run the algorithm on your name (real, nickname).  Make sure you know
the number.

```text
A:1,  B:2,  C:3,  D:4,  E:5,  F:6,  G:7,  H:8,  I:9,  J:10, K:11, L:12, M:13, 
N:14, O:15, P:16, Q:17, R:18, S:19, T:20, U:21, V:22, W:23, X:24, Y:25, Z:26

0:                        10: H5                  20: A4
1:                        11: K5                  21:
2: E5                     12: R8                  22:
3:                        13: S7                  23: M3
4: P7                     14:                     24:
5: C6                     15: A7                  25:
6: K5                     16:                     26: R3
7: Z8                     17:                     27:
8: G5                     18:                     28:
9: M7                     19:                     29:
```

ODO - hashes to 15 + 4 + 15 = 34, 34 mod 30 = 4.  It took a bit to find
out that ODO is not in our class.

Would it be any better with bucketing?  Probably, because we end up
chaining the collisions.  But bucketing adds overhead and complexity.

Note: Probing is generally better if you add 7 (or any number other than
1).

Hash tables: Assessing costs
----------------------------

Approaches to maps.

* Unordered lists/arrays: O(n) get, O(n) set, O(n) remove
* Ordered arrays: O(logn) get, O(n) set, O(n) remove
* Binary search trees: O(logn) get, O(logn) set, O(logn) remove,
  provided they stay balanced.
* Skip lists: Expected O(logn) get, Expected O(logn) set, Expected
  O(logn) remove, no worry about balancing.
* Hash tables:

Costs for `set`.

* Compute the hash code.  Independent of size of array, so O(1)
* Look in the cell.
    * Lucky: Empty.  O(1)
    * Unlucky: O(n) for either (bucket: n values in the same bucket)
      (probing: n values hash to same location, so we keep shifting)
    * Usual: O(1).  "With a good hash function and a sufficiently large
      table (Sam likes 2*n)", we usually won't have more than a few
      collisions at any position.
    * If we ignore the cost of expansion, we can say that `set` is
      *expected* O(1).
* Potentially expand
    * O(n)
    * Whoops!  How do we deal with that?  (See the detour on expansion
      below.)

Additional costs

* We are using a lot of extra space, particularly if you follow the "2*n"
  policy for size.
* If you have a linked structure, you also have space for the links.
  (Each reference in an array of references is the same size as a
  reference in a node.)

Detour on expansion

* Suppose we're working with ArrayLists and we've called `add` n times.
  Suppose also that the initial size of the array is 1 and the policy
  of expanding is "double the size and copy".
* What is the total cost of copying during expansion (from 1 to n)?
  Approximately: 1 + 2 + 4 + 8 + .... + n.
* Guesses: n^2, nlogn
* Sam says: Let's try it
    * n=1: 1 = 1
    * n=2: 1 + 2 = 3
    * n=4: 1 + 2 + 4 = 7
    * n=8: 1 + 2 + 4 + 8 = 15
    * n=16: 1 + 2 + 4 + 8 + 16 = 31
    * n=32: 1 + 2 + 4 + 8 + 16 + 32 = 63
    * n=64: 1 + 2 + 4 + 8 + 16 + 32 + 64 = 127
* Is there a pattern?  Yes.  The sum is 2*n-1.
* If we "amortize" (distribute) the cost across all the additions
    * (2*n-1) / n =~ 2.
* So `set` is *amortized* expected O(1).

Improving that hash function
----------------------------

How would you write a better hash function for strings?  (Hint: What does
PM suggest for an arbitrary object, which I think he took from J. Bloch?)

PM suggests:

* Start with an arbitrary value.  (The same all the time for a particular
  class.)
* For each field
    * Multiply hash by 31.
    * Add hash code of that field.

Why?

* Multiply by 31?
    * Prime; helps ensure some good distribution of values.  (32 would
      put a lot of 0's on the end.)
    * Fast to compute.  (val << 5) - val
* Start with a random value?

For strings.

```drracket
(define hash
  (let ([start (random 1000)])
    (lambda (str)
      (let kernel ([code start]
                   [remaining (string->list str)])
        (if (null? remaining)
            code
            (kernel (+ (* code 31) (char->integer (car remaining)))
                    (cdr remaining)))))))
```

Complexities
------------

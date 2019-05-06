---
title: Eboard 38  Minimum spanning trees
number: 38
section: eboards
held: 2019-05-06
link: true
current: true
---
CSC 207.01 2019S, Class 38:  Minimum spanning trees
===================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Minimum spanning trees
* Kruskal's algorithm
* Prim's algorithm
* Greed as an approach to algorithm design
* Examples
* Lab

Preliminaries
-------------

### News / Etc.

* I'm mostly through grading exam 2; there were enough issues that I'm
  going to talk through some of them and give you a chance to resubmit.
    * Some lost folks points.  (Hence the "resubmit".)
    * However, most are just general issues.
* Since I'm bringing food to the CSC 151 students' presentations, I brought 
  some for you too.  (You get melon and poptarts; they get PB&J and other
  fruit, plus your leftovers.)

### Upcoming work

* Reading for Wednesday
    * [Wikipedia: Dijkstras Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
* No lab writeup today.
* [Exam 2](../exams/exam02) due last night.   (Tuesday is also acceptable.)
    * Don't forget your epilogue!
* Final exam: 9am or 2pm, Thursday or Friday of finals week.
    * I'll try to have a sample final ready early next week.
    * Let me know which of the four times you plan to take the final.

### Extra credit

#### Extra credit (Academic/Artistic)

* Gridshock documentary, 7pm, Wednesday, Strand

#### Extra credit (Peer)

* Wednesday, May 8, 2pm, 
  Science 2022 - ECDLP: Frey-Ruck Attack
* Friday, May 10, 2pm, Science 2022,
  An Exploration of Torsion of Elliptic Curves over Cubic and Quartic Fields.
* Friday, May 10, ?pm, Voice Recital, Students of N. Miguel

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* **Today** 30 Minutes of Mindfulness at SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

### Questions

Some comments on recent code
----------------------------

#### Project name

* I do like to import your projects into Eclipse once in a while.
* Hence, when I tell you to rename the project, I'm serious about it.
  (Demonstration.)

#### Removing elements from BSTs

Here are the statistics:

* Thirteen people turned in the exam by Friday night.
* Five passed the "random remove" test.  Of those, one was O(n) rather
  than O(height).
* Moral: Recheck your `remove` method!

#### Private fields

I intentionally made the fields of various kinds of nodes private.
You were not supposed to directly access those fields.  If you did
so, you lost significant points on a problem.  You may redo, but only
if you choose to do so before I return your exam.

#### Repeated work

At this point in your career, you should be trying to avoid repeated
identical procedure calls.  (We talked about it in 151.  Now that we've
hit 207, it should be natural.)  So please don't do

```java
if (comparator.compare(a,b) < 0) {
  ...
} else if (comparator.compare(a,b) > 0) {
  ...
} // if/else
```

Option one:

```java
int comp = comparator.compare(a,b);
if (comp < 0) {
  ...
} else if (comp > 0) {
  ...
} // if/else
```

Option two:

```java
int comp;
if ((comp = comparator.compare(a,b)) < 0) {
  ...
} else if (comp > 0) {
  ...
} // if/else
```

#### Naming (or not)

```java
Type val = new Type(...);
return val;
```

Why?  Sam would generally prefer

```java
return new Type(...);
```

There's generally not a need to name something that you only use once.

Some exceptions: 

* Sometimes it helps with debugging.  
* Sometimes you realize that you want to factor out the return statement
  in a set of set of nested conditionals.

This issue comes up because I saw a lot of the following.

```java
ImmutableNode<K,V> newNode = new ImmutableNode<K,V>(...);
return newNode;
```

### Repeated code

If you find yourself writing the same code (or nearly the same code)
multiple times, you should find a way to encapsulate it.  For example,
there's a reason there's a `find` utility in `Trie.java`.

Minimum spanning trees
----------------------

Kruskal's algorithm
-------------------

Prim's algorithm
----------------

Greed as an approach to algorithm design
----------------------------------------

Lab
---

---
title: Eboard 38  Minimum spanning trees
number: 38
section: eboards
held: 2019-05-06
link: true
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
    * [Wikipedia: Dijkstra's Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
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

This issue comes up because I saw a lot of the following.

```java
ImmutableNode<K,V> newNode = new ImmutableNode<K,V>(...);
return newNode;
```

Some reasons to choose the latter: 

* It may make your code more readable.
* One doesn't know a better approach.
* Sometimes it helps with debugging.  - You can see the value of newNode
  in the midst of the code.
* There may have been more code, or you want the opportunity for more code.
* Sometimes you realize that you want to factor out the return statement
  in a set of set of nested conditionals.

### Repeated code

If you find yourself writing the same code (or nearly the same code)
multiple times, you should find a way to encapsulate it.  For example,
there's a reason there's a `find` utility in `Trie.java`.

Minimum spanning trees
----------------------

* Smallest tree that connects every vertex in the graph.
    * "Smallest" = "smallest sum of edges"
* Not every graph has an MST because not every graph is connected.
  (We only find MSTs for connected graphs; failure to find an MST is
  also a signal that the graph is not connected.)
* Directed or undirected?  We generally do MSTs in undirected graphs.
  (We may need to play with the directed graph implementation a bit to
  deal with undirected graphs.)

Kruskal's algorithm
-------------------

* Repeatedly take the unused edge with the lowest weight
    * Creates a cycle: Throw it away
    * Doesn't create a cycle: Add it to MST
* Proof of correctness?
    * Sam!  Math 208/218 is not a prereq for this course!
    * One Idea: It can't have fewer edges.  But do we know that they
      are the smallest set of oedges.
    * Proof by contradiction: Assume it's not the smallest.  Look at
      the "first" edge that's in the smallest, but not in the one we
      built; we make some argument about it.  (This is easier if we
      don't allow duplicate edge weights.)
* Important step: Determine if adding an edge creates a cycle 
  (think/pair/share).
* Idea: Treat each unconnected component as a set.  Find some what to
  union the sets.
* Idea: Do a BFS or DFS in the MST from one vertex of the new edge.
  If we find the otehr vertex using that search, the new edge makes a
  cycle.  Otherwise, we can add it.
* What's the running time of this version of Kruskal's?  (Using BFS.)
    * Claim O(n^2): We have to go through each vertex, which is O(n),
      and then check for a cycle, which is also O(n).
    * Claim O(m*n): We go through each edge, which is O(m), and for each,
      we check for a cycle, which is also O(n)
        * Note: Assumes easy to iterate edges in graph 
    * Checking for a cycle is O(m+n); although we're doing it in a tree,
      which has only O(n) edges, so ...
    * Note: Getting the edges in order is O(mlogm).
* If we can determine cycles more quickly ...

Prim's algorithm
----------------

* Start with one vertex
* Repeatedly: Find the lowest-weight edge that exits that point.
    * If it creates a cycle, ignore it.
    * If it does not create a cycle, add it to the MST.
* How expensive is it to determine if an edge creates a cycle?  (How
  would you do so?)
    * "If an edge connects two circled things, it creates a cycle."
    * O(1): Set up an array of "marks" on vertices.  When you add a
      vertex to the MST, set the mark to true.  When you look at an
      edge, check the mark.  (Requires that vertices be numbers or
      something easily convertable to numbers.)
        * Note: O(n) setup (space and time)
* How expensive is Prim's?
    * O(n) to set up the "mark" array.
    * O(1) to check whether an edge creates a cycle
    * Assume that "all the edges from a vertex" does not add extra
      work (beyond processing those edges).
    * How do we keep track of the edges remaining to check?  Priority
      Queue.  
        * If we implement the priority queue as a sorted list, that's
          O(m).
        * If we use balanced BSTs, that's O(logm).  But we don't know
          how to implement balanced BSTs.
        * If we use skip lists, that expected O(logm).
        * If we use heaps, that's O(logm)
   * We add m edges to the priority queue (heap); each add is O(logm).
     The algorithm is O(n + mlogm) = O(mlogm).

Greed as an approach to algorithm design
----------------------------------------

You have a few basic approaches to designing algorithms.

* "Solve an example by hand and then generalize."
* Suppose you can solve a slightly simpler version of the problem and
  use that to solve the original version.  (Aka "recursion".)
* Divide and conquer.  Break the problem into two halves, solve each,
  combine them.
* Look for a similar problem and modify the algorithm you know for solving
  that problem.
* Brute force: Try every possible option.  (Often expensive.)

Other things that help

* Draw a picture
* ...

New idea

* Greed: Given a choice, choice the local best.  See if that gives you 
  a global best.
    * Doesn't always work.
    * Sometimes very expensive.
* Good for optimization.

Lab
---

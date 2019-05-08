---
title: Eboard 38  Minimum spanning trees
number: 38
section: eboards
held: 2019-05-06
link: true
---
CSC 207.02 2019S, Class 38:  Minimum spanning trees
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
* Lab

Preliminaries
-------------

### News / Etc.

* I'm mostly through grading exam 2; there were enough issues that I'm
  going to talk through some of them and give you a chance to resubmit.
    * Some lost folks points.  (Hence the "resubmit".)
    * However, most are just general issues.
* I brought food for the CSC 151 students' presentations; you get leftovers.

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

* Wednesday and Thursday, 7:30 pm, Wall, Theatre performance

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

* Choreography Recital, Wednesday, 4:30ish, Flanagan
* Electronic Music Class, 7:30 p.m. Wednesday, Sebring-Lewis

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

```java
return new ImmutableNode<K,V>(...);
```
Some reasons to choose the two statement version.

* Can make debugging easier.  We can look at the value of `newNode`
  before we return it.
* Not in this situation, but perhaps others: It could make the lines
  shorter.
* It may make your code more readable.
* Exams are stressful; cleaning up your code comes later.
* Part of a broader design, perhaps changed

        ImmutableNode<K,V> newNode;
        if (...) {
          newNode = ...;
        } else if (...)
          newNode = ...;
        } else {
          newNode = ...'
        }
        return newNode;

* There may have been more code, or you want the opportunity for more code.

### Repeated code

If you find yourself writing the same code (or nearly the same code)
multiple times, you should find a way to encapsulate it.  For example,
there's a reason there's a `find` utility in `Trie.java`.

Minimum spanning trees
----------------------

* In an undirected, edge-weighted graph, an MST is a collection of edges
  that connect all their vertices and have the least total weight of all
  such collections.
    * We don't normally do MSTs for directed graphs.
* Not all graphs have MSTs.  In particular, the graph needs to be 
  connected.  (That is, in the original, there is a path between every
  two nodes.)
* Lots of examples
    * Snow plow
    * Telephone wire

Kruskal's algorithm
-------------------

* Sort the edges by weight from smallest to largest.
* While edges remain
    * Grab the lowest weight remaining edge
    * If adding the edge to the MST causes a cycle, ignore it
    * Otherwise, add it to the MST.
* How do you determine whether adding an edge creates a cycle?
    * The Same algorithm: Build an nxn matrix that shows connections.
      Each time you add an edge, add connections for everything each
      vertex connected to that edge is connected to.  O(n)
    * If the edge goes to a node that is already discovered, then
      there is a cycle.  (It appears that this algorithm does not
      work, since different "discovered" vertices can be in
      unconnected subgraphs.)
    * Generalization: Keep track of "sets" of connected nodes.  When
      you add an edge, see if the two nodes are in the same set.
      O(n) to search the set, if the set is implemented as a linked
      list.
* Assume that checking for a cycle is O(n). [Note: You'll learn a better
  strategy in 301].  What is the running time of Kruskal's? O(....)
    * O(mn): O(m) repetitions (one for each edge),  O(n) per edge.  
    * Sorting the edges costs O(mlogm).
    * O(mlogm + mn).  In a connected graph, that's O(mn)
    * Note: An improved cycle test makes this much faster.

Prim's algorithm
----------------

* Key idea: Like Kruskal's, except expand one tree, rather than many
* Start with any vertex in the graph
    * Add all its neighboring edges to a priority queue
    * While edges remain in the priority queue
        * Grab the next edge
        * If it doesn't create a cycle, add it.
        * Add all the edges from the new vertex to the priority queue
* Does this work (find an MST) no matter where we start?  Yes.
* How would we assess this?
    * Incorrect: Find a counter example.
    * Correct: Proof of correctness.
        * Constructive: Why each edge you add has to be in the MST
        * Contradiction: Assume that there's an MST, ...
        * Induction: Assume this works for all graphs of size N; show
          that it works for all graphs of size N+1.
* Is there a better way to determine cycles in this algorithm?  (Better
  than O(n)?)
    * Yes, if we can quickly mark a vertex as "in the tree" and check
      that mark.
    * If we use a linked list, that's O(n) to search and O(1) to add.
    * If we have an array of booleans, indexed by the vertices, add
      is O(1) and search is O(1). [Assumes easy to convert vertices
      to numbers; oh, maybe that's why we represent vertices as
      numbers.]
    * O(n) to set up the array.
* Running time of this version of Prim's
    * Claim: O(mlogm), because each addition of an edge to the priority
      queue is O(logm) (at least for a well-designed priority queue).
      (Also assuming everything else works in the expected way.)

Greed as an approach to algorithm design
----------------------------------------

What approaches do we already know for algorithm design?

* Solve an example by hand and then generalize.
* Look for a similar problem and modify the algorithm you know for solving
  that problem.
* Suppose you can solve a slightly simpler version of the problem and
  use that to solve the original version.  (Aka "recursion".)
* Divide and conquer.  Break the problem into two halves, solve each,
  combine them.  (Often another form of recursion.)
* Brute force: Try every possible option.  E.g., We can sort a list by
  computing every permutation and then choosing the first sorted one.
  (Often expensive.)
* Think about the right way to represent the data; the algorithm may
  stem from that.

A new approach: Greed

* Usually used for optimization problems (smallest/largest)
* Idea: Taking the local optimum leads to a global optimum.
* Selection sort is, arguably, a kind of greedy algorithm.

Graph traversal

* Why would you use depth-first vs. breadth-first?
* Breadth first: Visit all vertices one edge away, then all the vertices
  two edges away, then all the vertices three edges away, ...
* Breadth first: Typically implemented with a queue.  Put the starting
  in the queue (and mark it).  While vertices remain in the queue
    * Remove one
    * Add all of its unmarked neighbors to the queue
    * Mark all of its neighbors
* Depth first: Often implemented with a stack.  See the breadth-first
  approach, substituting a stack for a queue.
* Depth first: Implement with recursion.  Mark the vertex.  Run DFS on
  each unmarked neighbor.  (The stack is implicit.)
* Why use breadth-first: Reaches nearer nodes more quicly.
* Why use depth first: Easier to implement using recursion.  Also reaches
  the "outer limits" quickly.

Lab
---

* To be started on Wednesday.

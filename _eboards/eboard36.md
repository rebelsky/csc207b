---
title: Eboard 36  A Graph ADT
number: 36
section: eboards
held: 2019-05-01
link: true
---
CSC 207.01 2019S, Class 36:  A Graph ADT
========================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Modeling problems with graphs
* Graph terminology
* Weighted graphs
* Directed graphs
* Implementing graphs
* An ADT

Preliminaries
-------------

### News / Etc.

* Today is a talk day.  Sit with your partners from Monday.
* The next three days should be labs (or at least sets of exercises) on 
  graphs.

### Upcoming work

* Reading for Friday: Skim the reading on traversing trees and think
  about how it might apply to graphs.
* No lab writeup today.
* [Exam 2](../exams/exam02) due Thursday.
* Final exam: 9am or 2pm, Thursday or Friday of finals week.
    * I'll try to have a sample final ready next Friday.
    * Let me know which of the four times you plan to take the final.

### Extra credit

#### Extra credit (Academic/Artistic)

* Three talks by Prof. Dr. Yvonne Foerster (<https://yvonnefoerster.com/>)
    * **Today**: May 1, 4:30-6pm, HSSC S3325: _Beyond the Anthropocene: Technology, Innovation, and the (Post-)Human Condition_
    * Thursday, May 2, Noon-12:50pm, HSSC N3110 _Degrees of Freedom: Embodiment, Neuroplasticity, and the Need for a Critical Neuroscience_ (Lunch and beverages provided)
    * Friday, May 3, Noon-12:50pm, Bucksbaum 152: _Designing Future Bodies: Fashion and Technology_ (Lunch and beverages provided)

#### Extra credit (Peer)

* Friday, May 3, 2pm, Science 2022,
  An Exploration of Torsion of Elliptic Curves over Cubic and Quartic Fields.
* Watch your peer compete in one third of a triathalon on Saturday.
  8:30 a.m. Saturday.
* Sunday, May 5, 2pm, Herrick,
  Grinnell Singers and the Grinnell Orchestra
* Wednesday, May 8, 2pm, 
  Science 2022 - ECDLP: Frey-Ruck Attack

#### Extra credit (Wellness)

* CS Picnic, Friday Night.
    * Sign up today!

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

* Some Grinnell band opening for Friday's Gardner show (I think)
* The Grinnellian, Saturday
* Track and Field Saturday, Somewhere.

### Questions

_What will the exam be like?_

> Paper

> Exact Java not necessary.

> Probably including the formal definition of Big-O, or applying that.

_Will the mentors hold a review session in which they discuss the 
gibberish that is the definition of Big-O?_

> Yes.

Modeling problems with graphs
-----------------------------

* Core issue of CS (or at least computer programming): Take real-worl
  problems and solve them computationally.
* We like to build "models" that let us represent the problem in the
  computer, often stripping away extraneous details.
* A lot of problems, particularly geographic problems, lead to 
  structures we call "graphs".

Some sample problems:

* You are a 911 dispatcher and need to send an ambulance to an
  address, from which depot do you send it?
* It has snowed.  A lot.  All of the streets are impassible.
  What's a subset we can clear so that the fire department can
  reach every corner in town? 
* You work for the census.  You need to visit every house in 
  Powesheik county.  What's the most efficient path to visit all
  of those houses?
    * Can you visit all of those houses?

Abstraction

* In all of these situations, we have a bunch of locations and 
  "connections" between the locations.

Graph terminology
-----------------

A graph is a collection of vertices and edges that connect them.

* We typically say that a graph has n vertices and m edges.
* We might say that a graph has v vertices and e edges.
* We allow only one edge from one vertex to another.
* We don't usually allow edges from a vertex to itself.

Weighted graphs

* Every edge has an associated "weight", representing some cost
  associated with the edge.
* In most situations, we require that weights be non-negative.

Directed graphs

* Edges may only go in one direction.

More formal definition (not formal enough)

* A directed weighted graph G, is a pair of sets, (V,E),
  E is a subset of VxVxReal.  V is finite.

Designing ADTS
--------------

* PUM - Philosophy, Use cases, Methods
* P: Represent some situations in which vertices and edges seem natural.
* U: See above.
* M: Methods
* Observer methods
    * `numEdges()` - How many edges are there in the graph
    * `numVertices()` - How many vertices are there in the graph
    * `int edgeWeight(int from, int to)` - What is the weight of the 
      edge from `from` to `to`.  If there's no edge in the graph, we ...
        * Return -1
        * Return 0
        * Throw an exception - have `hasEdge` as an option
           * Avoids problems if we want to have 0, 1, or Integer.MAX_VALUE as
             weights.
           * Makes client code more difficult to write.  (But the client
             has to check the result in the other cases, too.  The
             `Integer.MAX_VALUE` case is particularly problematic because
             when they don't check, they may overflow.)
        * Try to find a path (expensive)
        * Convert the return type to Integer and return null.
        * Infinity, which we simulate with `Integer.MAX_VALUE`
    * `Iterator<Integer> vertices()`
    * `Iterator<Edge> edges()` ; Didn't Sam just say that we should
      hide internal representation?
    * `Iterator<Edge> edgesFrom(int vertex)`
* Mutator methods
    * `addVertex(String name)` or `addVertex(int num)`. - Do we represent 
      vertices with strings or numbers or ...?
        * We may not want to have an externally visible Vertex class 
          so that (a) we can change implementation and (b) our client
          needs to remember less.
    * `addEdge(int from, int to, int weight)`
    * `removeVertex(int num)` - remove a vertex and the edges to/from it.
    * `removeEdge(int from, int to)` - remove an edge
    * `changeEdgeWeight(int from, int to, int newWeight)`
* Things we probably want to write
    * `pathFrom(int from, int to)` - Is there a path from from to to
      in the graph; if so, what is its weight?  See `edgeWeight` for 
      possible return values.
    * `int[] shortestPathBetween(int from, int to)` - Find the 
      shortest path from from to to, return all of the vertices on
      that path.  

Implementing graphs
-------------------

* Assume you get to build it behind the scenes.
* We want to implement `numVertices`, `numEdges`, `edgeWeight`,
  `vertices`, `edges`, and `edgesFrom`.
* We'll assume that there's an Edge object that stores from, to, and
  weight.  (That may be redundant in some cases, but it helps with
  the edges iterator.)

Idea 1: Array of vertices (vertex i is in position i).  Each
vertex contains an ArrayList of edges.  

* `numVertices`: Size of array.
* `numEdges`: Sum the sizes of the arraylists - O(n) - or keep track
  of it in a separate field - O(1)
* `edgeWeight`: 
    * O(m) you may have to look at every edge to find it (NO)
    * O(1) if the ArrayList of edges is such that edges.get(i) is the
      ith edge. (NO)
    * O(n) if we think of the ArrayList as just a list of the edges
      from that node.  (The "Array" part is not so necessary.)  (YES)
* `vertices`: Creating the iterator is easy.  n calls to next will
  cost O(n)
* `edges`: Use the same technique as problem 4 on the exam, O(m) to
  visit all of the edges.
* `edgesFrom`: Iterating will be # of edges.

Idea 2: nxn array of weights (or edges)

* `numVertices` is number of columns
* `numEdges` is either O(n^2) or O(1) depending on whether or not
  we have a separate field.
* `edgeWeight` is O(1)
* `vertices` is O(n) to iterate all of the vertices
* `edges` is O(n^2) to iterate all of the edges (may be much larger
  than m
* `edgesFrom` is O(n)

Idea 3: Hash table, v1

* Instead of an array of ArrayLists, we use an array (or a hash) of hashes.
* Makes `edgeWeight` much faster (we hope). O(1)
* Iteration is similar to the array list

Idea 4: Hash table, v2

* A hash table whose keys are <from,to> pairs.

The one we choose depends on the problem at hand.

Some problems
-------------

Given two vertices, v1 and v2, is there a path from v1 to v2?  
(Advanced version: If so, what is that path?)

Breadth first search

        Enqueue the root (starting vertex)
        Mark the root
        While queue is not empty
          dequeue a vertex, v
          enqueue and mark all of its children (all that you haven't visited)
          process v

Cost of BFS?  O(n+m)

* Each vertex goes in the queue only once
* Each edge gets visited only once (when the from vertex is removed
  from the queue)

Recursive approach

* pathFrom(v1,v2) = edgeFrom(v1,v2) or exists vi (edgeFrom(v1,vi) &&
  pathFrom(vi,v2)

If we represent the graph as an adjacency matrix, there's a cool
approach to finding out whether you can get from one node to another.

* If 1 in the matrix represents "there's an edge" and 0 represents
  "there's not an edge" ...
* matrix*matrix = Things reachable in two steps
* matrix*matrix*matrix = Things reachable in three steps
* matrix^n = Things reachable in n steps

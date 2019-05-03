---
title: Eboard 36  A Graph ADT
number: 36
section: eboards
held: 2019-05-01
link: true
---
CSC 207.02 2019S, Class 36:  A Graph ADT
========================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Modeling problems with graphs
* Graph terminology
* An ADT
* Implementing graphs
* Some more problems

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
* [Exam 2](../exams/exam02) due Thursday (**tomorrow**).
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

* The Grinnellian
* **New/Today**: Voice recital, today, 4:15, S-L.
* **New/Tomorrow**: Break Up Big Ag Panel, Burling Lounge, 4:30
* Friday, 9pm Gardner, Opening Band for Gardner show: "Sorry We're Late".
  Opening for "Girl K" with "Blizzard Babies"
* Sunday, May 5, 2pm, Herrick,
  Grinnell Singers and the Grinnell Orchestra

#### Extra credit (Wellness)

* CS Picnic, Friday Night.
    * Sign up today!
* Pella Tulip Festival

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

* **New**: Debate, Tonight, 7pm, JRC 101, Should Greek Sculpture be 
  repatriated?

### Questions

_What will the final exam be like?_

> Paper

> Exact Java not necessary.

> Probably including the formal definition of Big-O, or applying that.

> No unit tests.

_Will the mentors hold a review session in which they discuss the 
gibberish that is the definition of Big-O?_

> Yes.

_Have you decided on a format?_

> I like "four right is an A, three right is a B, two right is a C,
  one right is a D".

_Will we receive the quizzes back so that we can study from them?_

> I hope so.

_Will we get a grade report before the final?_

> Almost certainly.

Modeling problems with graphs
-----------------------------

Computer scientists are practical folk.  We like to take real problems and
find ways to solve them using algorithms (performed by the computer).

Doing so often requires that we "model" the problems, trying to extract
the key parts, throwing away unnecessary details.

We're going to look at a set of geographical problems and think about
how we might represent them.


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

In the abstract, all of these problems involve a set of "things"
some of which are connected.  Each connection has an associated
"cost" (also "weight"). 

We then have questions (which is closest, which subset do I need
to clear, etc)?

Graph terminology
-----------------

A graph is a collection of vertices and edges.  Edges connect vertices.

* When talking about running time, we care about the number of vertices
  (n) (maybe v) and the number of edges (m) (maybe e)..
* Graphs may be directed or undirected.
    * Undirected: Edges between two nodes, no direction
    * Directed: An edge from a to b does not imply an edge from b to
      a.  Even if there are both edges, they may have different weights.
* Graphs may be weighted (numbers on edges) or unweighted (no numbers)
    * Integer weights are easier to deal with than real weights.
* Our primary focus is likely to be weighted, directed graphs.

Other important graph terminology

* A cyclic graph has a path from one vertex back to that vertex, without
  repeating edges.
* A graph in which each vertex has an edge to every other vertex is
  "complete".

Designing ADTS
--------------

* PUM  - Philosophy, Use Cases, Methods
* Philosophy: Represent data as a collection of vertices that are
  connected by edges.
* Use cases: See above.
* Methods: See below.

Question: How do we represent vertices?

* Common practice: For efficiency, we use small integers (e.g., 0, 1, 2).
    * When drawing, we'll often use letters.

Observers - Extract information

* `Iterator<Edge> edgesFrom(int vertex)`
* `int getWeight(int vFrom, int vTo)`
     * What does this do if there is no edge from `vFrom` to `vTo`?
     * Option 1: Throws an Exception
     * Note: If determining whether there's an edge is expensive, a separate
       `hasEdge` likely adds expense.
* `Edge getEdge(int vFrom, int vTo)`
     * Option 1: Return null
          Edge e = graph.getEdge(alpha,beta);
          if (e != null) {
            doStuff(e);
          } else {
            doOtherStuff();
          }
     * Option 2: Throws an Exception
          try {
            Edge e = graph.getEdge(alpha, beta);
            doStuff(e);
          } catch (Exception ex) {
            doOtherStuff();
          }
     * "Java is your nanny" - Throw an exception
* `hasVertex(int vertex)` - determine if the vertex is in the graph
* `int totalWeight()` - find the total weight (may need a use case)
* `List<Integer> neighbors(int vertex)` - Get a list of the neighboring nodes. 
    * Can be extracted from `edgesFrom`.
* `Iterator<Integer> vertices()`
* `Iterator<Edge> edges()`
* `numVertices()`
* `numEdges()`

Mutators - Change the graph

* `addVertex(int vertex)` - Add a new vertex
    * `int addVertex()` - Returns the number of the new vertex
* `addEdge(int vFrom, int vTo, int weight)`
* `removeVertex(int vertex)` - Guess
* `removeEdge(int vFrom, int vTo)` - Similar
* `changeWeight(int vFrom, int vTo, int newWeight)`
    * Likely to be cheaper than removing then adding.

Additional - Things we might build from the basic operations

* `isConnected()` - is there a path from every vertex to every other
  vertex
* `shortestPath(from,to)` - what is the length of the shortest path
  between from and to (or what are the nodes on that path)
* `ArrayList<ArrayList<Integer>> routesBetween(from, to)`

Implementing graphs
-------------------

How might you represent a Graph in Java?  How much will the basic
operations cost?

Sample Graph

        v1 -----> v2 -----> v3
         |         ^
         v         |
        v4---------/

An Edge is a triplet: From, To, Weight

Version 1: List of all the edges (and a list of vertices)

* Edges: (v1,v2,?), (v2,v3,?), (v1,v4,?), (v4,v2,?)
* Vertices: (v1,v2,v3,v4)

Costs

* `Edge getEdge(int vFrom, int vTo)` - O(m)
* `List<Edge> edges()` - O(1) to get the list, O(m) to visit each
* `List<Integer> vertices()` - O(1) to get the list, O(n) to visit each
* `List<Edge> edgesFrom(int vertex)` - O(m) to get, O(n) to visit

Version 2: An array of lists of edges.  A[i] = edges from vertex i.

* `Edge getEdge(int vFrom, int vTo)` - Get the list at A[vFrom], iterate
  that list until you find one whose target matches v2.  O(n)
* `List<Edge> edges()` - O(m) to build (if we had an iterator, we
  could use the ideas from exam 2, problem 4)
* `List<Integer> vertices()` - O(n) to build, O(n) to visit
* `List<Edge> edgesFrom(int vertex)` - O(1) to build, O(n) to visit

Version 3: A 2D array of weights.  A[i,j] = weight of edge from i to j.

                        To
                  v1  v2  v3  v4
                +---+---+---+---+
              v1|   | w |   | w |
                +---+---+---+---+
              v2|   |   | w |   |
         From   +---+---+---+---+
              v3|   |   |   |   |
                +---+---+---+---+
              v4|   | w |   |   |
                +---+---+---+---+

* `Edge getEdge(int vFrom, int vTo)` - O(1)
* `List<Edge> edges()` - O(n*n)
* `List<Integer> vertices()` - O(n)
* `List<Edge> edgesFrom(int vertex)` - O(n)

Version 4: A hash table of edges (indexed by from/to pairs)

Which should we use?  It depends on what algorithm we have.  If our
algorithm does lots of calls to `getEdge` and fewer to the other methods,
we should use the edge matrix.  Most of the rest of the time, the array
of lists seems to work well.

Some problems
-------------

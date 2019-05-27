---
title: Eboard 39  Shortest paths in graphs
number: 39
section: eboards
held: 2019-05-08
link: true
---
CSC 207.02 2019S, Class 39:  Shortest paths in graphs
=====================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Sample final
* The shortest path problem
* Shortest paths in unweighted graphs
* Shortest paths in weighted graphs
* Dijkstra's algorithm
* Lab

Preliminaries
-------------

### News / Etc.

* Please sit with your partner from Monday.
* While we do not have labs or readings for Friday, you are expected
  to show up (not least, so that you can fill out the EOCE).
* I'm working on getting grades to you as soon as I can.  I'll try to
  have all grades in by Tuesday.
* Review sessions for final: TBD, probably Wednesday evening.
* **DO NOT FILL IN THE END-OF-COURSE EVALUATIONS UNTIL FRIDAY'S CLASS**
    * If you've done so already, please speak with me asap.

### Upcoming work

* No more readings.
* No more lab writeups.
* No more homework assignments.
* Final exam: 9am or 2pm, Thursday or Friday of finals week.
    * Let me know which of the four times you plan to take the final.

### Extra credit

#### Extra credit (Academic/Artistic)

* **Tonight** Gridshock documentary, 7pm, Wednesday, Strand

#### Extra credit (Peer)

* Track and Field, Friday and Saturday at St. Norbert (?)

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

* **Tonight** Digital Music Making class presentation, Wednesday 7:30 pm.
  Sebring-Lewis.  (Rap, noise, pop, more.)
* **Today** 4:30 p.m., Choreography, Flanagan

### Questions

Sample final
------------

The [Sample Final](../exams/sample-final) is now posted.  You can find
it by going to **Current** > **Exam**.  We'll quickly talk through the
policies.

_How will you know that the exam is a reasonable length?_

> Faith?

> I may try doing it and seeing how long it takes.

_How many types of problems are there?_

> I will choose five of the six types mentioned on the sample final.
  (There will almost certainly be a "draw" problem, a "draw graph and
  run algorithm" problem, and an asymptotic analysis problem.)

_Will we get stuff back soon?_

> Yes.

Can the tests be sketchier code than the algorithms?

> Yes.

Will we get some pre-written code?

> It depends on the problem. 

The shortest path problem
-------------------------

Given a graph and two vertices, `source` and `sink`, find the shortest
path from `source` to `sink`, where the length of a path is computed
from the edges in the path.

If there is no path from `source` to `sink`, there is no shortest path.
Different variants handle that issue in different ways.

Shortest paths in unweighted graphs
-----------------------------------

In an unweighted graph, the length of the path is the number of edges
in the path.

Think/Pair/Share: How can you find the shortest path in an unweighted
graph?  (Don't say "Dijkstra's algorithm".)

* Brute force
    * Find every non-looping path from source to sink.
    * Take the shortest
* Breadth first search

Shortest paths in weighted graphs
---------------------------------

In a weighted graph, the length of the path is most frequently the sum of 
the weights of the edges in the path.

* There are other versions, such as the product of the weights, or the
  maximum weight, or ...

Consider the two following approaches:
* If "we can use the algorithm", explain why.
* If "we can't use the algorithm", give a counter-example.

Think/Pair/Share: Can we use the algorithm we used for unweighted
graphs?  Why or why not?

* Yes, if we convert the weighted graph to an unweighted graph using
  the technique of "replace an edge of weight w by w edges of weight 1"
* Important moral: Instead of transforming the algorithm, transform
  the data.
* Is this a good approach?
    * Yes, it's clever.  We like clever things.
    * The new graph may be much larger.  It's expensive to build the
      graph.
    * We've turned an O(m+n) algorithm into a O(sum-weights) algorithm.
    * May put us further from what we are modeling.
    * Won't work for real weights.
    * Harder to update the graph (e.g., changing weights).

Think/Pair/Share: Can we use the obvious "greedy" algorithm: Take the
smallest-weight edge from `start`, then the smallest-weight edge from
there that does not cause a cycle, then the smallest-weight edge from
there that does not cause a cycle, ....  Why or why not?

* Easy to find counter-examples

Dijkstra's algorithm
--------------------

Key insight: Instead of finding the shortest path from `source` to
`sink`, we find the shortest path from `source` to *every* vertex
in the graph.

We'll do an example to think through it.

Lab
---

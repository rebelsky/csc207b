---
title: Graph traversal
repo: <https://github.com/Grinnell-CSC207/graphs-2019S>
summary: |
  We explore techniques for traversing graphs.
current: true
---
Preparation
-----------

Fork and clone the repository.  Import it into eclipse.

Exercises
---------

### Exercise 0: Code Reading

a. Scan through the code so that you understand what methods are
available and what approaches are used.  Make notes on areas
that are likely to be problematic.

b. Summarize the key design decisions in this implementation of
graphs.

c. Suppose we wanted to work with unweighted, directed graphs.
What decisions would we need to make or what strategies would we
have to use in the client?

d. Suppose we wanted to work with weighted, undirected graphs.
What decisions would we need to make or what strategies would we
have to use in the client?

e. Suppose the code was written quickly by a programmer who was
behind on sleep.  What are likely trouble spots in the code?

### Exercise 1: A simple experiment

The file `GraphExperiment.java` contains a series of experiments
with the `Graph` class.

a. Skim the file to make sure you understand what the experiments
are exploring.

b. Run the experiment to see that the correct graphs are built.

c. Add a few experiments of your own to explore the trouble spots
you identified in the prior problems.

### Exercise 2: Improved dumping

You will note that there is a `dump` procedure in the `Graph` class.
That procedure prints out vertices and edges using vertex numbers.
Write a new version of `dump` that prints out vertices and edges
using vertex names.

### Exercise 3: Reading graphs

Write a procedure, `readGraph(String fName)`, that reads in a graph
from a file.  You can assume that the each row of the file is a
pair of vertex names that represent an edge from the first to the
second.

### Exercise 4: What can I reach?

Write a procedure, `reachableFrom(PrintWriter pen, int vertex)`, that
prints out a list of all the vertices reachable from a starting
point.  A vertex, `v`, is reachable from a vertex, `u`, if there is a 
path from `u` to `v`.  

Conduct a few experiments to see if your procedure works correctly.

You can use depth-first search or breadth-first search.

*Note*: You will likely need to find a way to mark vertices to
indicate that you've printed them out (or otherwise processed them)
so that you don't process them a second time.

### Exercise 5: What can I reach? revisited

Write a procedure, `pathsFrom(PrintWriter pen, int vertex)`, that
prints out all the vertices reachable from a vertex *and* a path to
each vertex.  (There may be multiple paths to each vertex; you should
only print out one.)

### Exercise 6: What can I reach? re-revisited

Write a procedure, `Iterator<Integer> reachable(int vertex)`,
that returns an iterator of all the vertices reachable from a vertex.

### Exercise 7: What can I reach? re-re-revisited

Write a variant of `reachableFrom` that uses whichever searching
strategy you did not use in the original version.  (If you used
breadth-first search, implement depth-first search.  If you used
depth-first search, implement breadth-first search.)

For those with extra time
-------------------------

If you find that you have extra time, write a program that uses
breadth-first-search to build word trees.

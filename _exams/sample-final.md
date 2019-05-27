---
title: Sample Final Examination
link: true
current: true
---
*This is a sample examination.  It includes problems similar to those
that have appeared on previous in-class final examinations (and, as
you may note, on recent take-home examinations).  It also includes the
standard examination policies.  These problems are not intended to give
a comprehensive view of topics.  For example, there is likely to be at
least one problem on trees.  As the sample exam suggests, problems may
involve code reading and code analysis in addition to code writing.*

*Although the sample policies indicate that you may not
discuss the actual final exam, you should feel free to discuss
this sample final examination with anyone you'd like.*

Policies for the final examination
----------------------------------

1. This is a *closed book* examination.  You may not rely on
notebooks, papers, textbooks, computers, colleagues, or anything 
similar.  You may, however, refer to one 8.5 x 11 inch, double
sided, hand-written, set of notes that you brought to this exam.

2. This is also a *paper* examination.  Answer all problems in pen or
pencil on blank pieces of paper and then staple them together.  You may
use additional blank pieces of paper as scratch, provided you turn them
in at the end of the examination.

3. You have received a cover sheet for this exam with a number on it.
Please write your name on the cover sheet.  Please write your number
(*not* your name) on each of the non-scratch pages
you turn in.

4. There are five problems on this exam.  They are not necessarily of
equal difficulty.

    Five correct or mostly correct solutions will earn you an A.
    Four correct or mostly correct solutions will earn you a B.
    Three correct or mostly correct solutions will earn you a C.
    Two correct or mostly correct solution will earn you a D.  Fewer
    than two correct or mostly correct solutions will earn you an
    F.  Failure to attempt the exam will earn you a 0.

    Partially correct solutions may or may not earn you a higher grade,
    at the discretion of the grader.

5. Many of the problems ask you to write Java code.  Although you
need not write correct working Java code, your code should be of sufficient
quality that the grader can be confident that you would be able to make it
work correctly with minimal effort when given access to Eclipse and the
Web.

6. Some problems ask you to build a structure.  You will have received a
separate sheet of paper that lists the operations used to build the
structure.  Follow those operations.  [_On the sample final, everyone
gets the same list of instructions._]

7. Other people may be taking this exam (or a similar exam) at another
time.  You may not discuss this examination with anyone. [_Not applicable
for the sample final._]

8. After you have completed the examination, please write, sign, and date the
following statements on the cover sheet:

    *I have neither received nor given inappropriate assistance on this
    examination.*

    *I am not aware of any other students who have given
    or received inappropriate assistance on this examination.*

    If you do not feel that you can sign either of these statements, please
    arrange to meet with me as soon as possible.

9. Please arrange the remaining sheets in order by problem number,
make sure you've written your number and the page number on the top
of each sheet, and then staple them together *without*
the cover sheet.

10. Please turn in this examination, the cover sheet, the stapled pages,
your notes (if used), and your scrap paper.

Problem 1: Iterating graphs
---------------------------

*Topics:* iteration, graphs

As you may recall, our `Graph` structure had the following fields.

```java
  /**
   * The number of vertices in the graph.
   */
  int numVertices;

  /**
   * The number of edges in the graph.
   */
  int numEdges;

  /**
   * The vertices in the graph. The edges from vertex v are stored in
   * vertices[v].
   */
  List<Edge>[] vertices;
```

Write the following method.  You must support the `next`, `hasNext`,
and `remove` methods.  You need not worry about concurrent modifications.

```java
/**
 * Iterate all of the edges in the graph.
 */
Iterator<Edge> edges() {
  ...
} // Iterator<Edge>
```

Problem 2: Testing stacks
-------------------------

*Topics:* Testing, ADTs

Suppose our `Stack` interface is defined as follows.

```java
/**
 * Simple stacks.
 */
public interface SimpleStack<T> extends Iterable<T> {
  /**
   * Look at the top of the stack.
   */
  T top();

  /**
   * Remove the top of the stack.
   */
  T pop();

  /**
   * Add something to the top of the stack.
   */
  void push(T val);

  /**
   * Determine if the stack is empty.
   */
  boolean isempty();

  /**
   * Iterate the values on the stack from top to bottom.
   */
  Iterator<T> iterator();
} // interface SimpleStack<T>
```

Suppose `LinkedStack` implements `Stack`.  Write a test suite for
`LinkedStack`.  Your test suite should include at least one significant
randomized test.

Problem 3: Building skip lists
------------------------------

*Topics:* Skip lists, randomness, linear structures, drawing structurs

Draw the skip list that results from the following sequence of
commands.  (We'll assume that the skip list just stores keys;
don't worry about the values.)

```java
  sl.add("Operating Systems");
  sl.add("Programming Languages");
  sl.add("Architecture");
  sl.add("Human-Computer Interation");
  sl.add("Functional Problem Solving");
  sl.add("Compilers");
  sl.add("Imperative Problem Solving");
  sl.add("Algorithms and Data Structures");
  sl.add("Software Design");
  sl.remove("Programming Languages");
  sl.remove("Compilers");
  sl.add("Programming Language Implementation");
```

You should assume that the random number generator returns the
following sequence of real numbers and that we've capped the height
of the skip list at 5..

```text
0.42241056493050366
0.22337283903303337
0.623327351327075
0.8503900312076152
0.4727338611447819
0.7884035506257645
0.5429298868238499
0.8109907199829048
0.42706668861905844
0.7845592443338416
0.6686026568220353
0.055770926317282185
0.5845337311232035
0.7816998915731855
0.3802419740451339
0.372601694776945
0.5180750749445557
0.4905191792706003
0.02667823516509331
0.07358676807630057
0.6555943266403909
0.11188593629567763
0.726497756576057
0.1645030105059562
0.9001625653435043
0.5768174605858587
0.3542576950708406
0.902286167180986
0.9653944489085222
0.5805734674817141
```

Problem 4: Applying Kruskal's Algorithm
---------------------------------------

*Topics:* Graphs, drawing structures, graph algorithms

Consider the following graph, given as a series of edges

```text
A B 4
A H 8
B C 8
B H 11
C D 7
C F 4
C I 2
D E 9
D F 14
E F 10
F G 2
G H 1
G I 6
H I 7

```

a. Draw the graph.  You'll likely find it easiest to draw it in the
following shape.

```text
    B    C    D

A        I       E

    H    G    F
```

b. Show the MST constructed by Kruskal's algorithm, numbering the
edges in the order they are added.  For example, if you add the
edge between F and G third, you'd draw something like the following.

```text
    B    C    D

A        I       E

    H    G----F
          (3)
```

Problem 5: Asymptotic analysis
------------------------------

*Topics:* Big-O, recurrence relations

a. Compute the fixed form of the following recurrence relation.

```text
T(n) = 2*T(n/2) + 3*n
T(1) = 1
```

b. Determine a *tight* Big-O bound for the following loop.

```text
String str = "a";
for (int i = 0; i < n; i++) {
  for (int j = i; j < n; j++) {
    str = str + "a";
  } // for
} // for
```

Notes
-----

In case you haven't observed it already, this samle exam contains
five different kinds of sample problems that suggest possible kinds
of problems on the final.

* As in problem 1, I may ask you to write an interator or a list iterator
  for a structure (e.g., list, stack, queue, graph, tree, heap).
* As in problem 2, I may ask you to write tests for an algorithm (e.g., sort,
  search, hash, a tree or graph algorithm) or structure (e.g., map,
  priority queue, queue, stack, graph, tree, hash table).
* As in problem 3, I may ask you to draw some structure (e.g., heap, sorted
  list, skip list, linked stack, array-based equeue, doubly-linked
  list, graph, tree, hash table).
* As in problem 4, I may ask you to draw a graph and apply some graph algorithm
  (Kruskal's, Dijkstra's, Prim's, BFS, DFS, others I may specify).
* As in problem 5, I may ask you to solve a recurrence relation and find a
  tight bound on an algorithm or loop.

There are, of course, other kinds of problems that I may include.  For
example, I might choose to have you implement an algorithm similar to
ones we have already seen, such as a new sorting algorithm, a related
graph algorithm, or perhaps one of the hash table algorithms, such as
adding in a probed hash table.

Acknowledgements
----------------

The sample graph was taken from 
<https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/>.

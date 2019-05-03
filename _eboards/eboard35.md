---
title: Eboard 35  Priority queues, heaps, and heap sort
number: 35
section: eboards
held: 2019-04-29
link: true
---
CSC 207.02 2019S, Class 35:  Priority queues, heaps, and heap sort
==================================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Priority queues, revisited
* The heap structure
* Adding elements to heaps
* Removing elements from heaps
* Storing trees/heaps in arrays
* Heap sort

Preliminaries
-------------

### News / Etc.

* I brought food-like substances.
* Today and Wednesday are "talk days".
* Apologies: Grading time this weekend got consumed by test writing 
  and question answering.
    * It sucks when there are bugs in your tests.

### Upcoming work

* No reading for Wednesday.
* No lab writeup today.
* [Exam 2](../exams/exam02) due Thursday.
    * Prologue due **tonight**.

### Exam 2 Notes

* Make sure that your repo is private!
    * And shared with me.
* Don't just hack at it until it works; 
  Take a step back, draw, think, and run by hand.
* "Where does `add` add the element?"  "What should `remove` do if the 
  key is not there?" - Read the documentation.  (For things related to
  `ListIterator`s, you can also see what happens in `ArrayList`s.)
* Some extra credit for inappropriate tests of Tries.

### Extra credit

#### Extra credit (Academic/Artistic)

* **Tomorrow**: CS Table, Tuesday, Facebook Data
* Three talks by Prof. Dr. Yvonne Foerster (<https://yvonnefoerster.com/>)
    * Wednesday: May 1, 4:30-6pm, HSSC S3325: _Beyond the Anthropocene: Technology, Innovation, and the (Post-)Human Condition_
    * Thursday, May 2, Noon-12:50pm, HSSC N3110 _Degrees of Freedom: Embodiment, Neuroplasticity, and the Need for a Critical Neuroscience_ (Lunch and beverages provided)
    * Friday, May 3, Noon-12:50pm, Bucksbaum 152: _Designing Future Bodies: Fashion and Technology_ (Lunch and beverages provided)

#### Extra credit (Peer)

* **New**: Sunday, May 5, 2pm, Herrick (?),
  Grinnell Singers vs. Grinnell Orchestra
* The Grinnellian
* Friday, 9pm Gardner, Opening Band for Gardner show: "Sorry We're Late".
  Opening for "I don't know actually; come see us."

#### Extra credit (Wellness)

* **Newish**: CS Picnic, Friday Night.
* Pella Tulip Festival

#### Extra credit (Wellness, Regular)

* **Today**: 30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

### Questions

Priority queues, revisited
--------------------------

_What is a priority queue?_ (philsophy, primary methods)

* A linear structure in which the policy for `get` is "most important
  first".
* `put`, `get`, `peek`
* `isFull`, `isEmpty`
* `iterator`

_How can we implement priority queues?_ (strategy, cost)

Sorted array of elements, with highest priority at beginning

* put: O(n); may have to shift
* get: O(n); may have to shift
* peek: O(1); just look at the first element

Sorted array of elements, with highest priority at end

* put: O(n); may have to shift
* get: O(1); just decrease the size by one
* peek: O(1); just look at the last element

Sorted linked structure, with highest priority at beginning

* put: O(n); shifting is cheap, finding is expensive
* get: O(1); grab the first element, update
* peek: O(1)

Skip list, ordered highest-priority to lowest

* put: Expected O(lgn)
* get: Expected O(1) (or maybe lgn, for the levels)
* peek: O(1)

_How might a priority queue contribute to sorting?_

* In some implementations, building the priority queue creates a sorted
  structure.
* Put everything in, take everything out.  You've taken things out in
  sorted order, so you've sorted.
* Data structures can be your friend.

_How could we build priority queues with trees?_

The heap structure
------------------

* These have nothing to do with "stack and heap".
* A heap is a binary tree with two properties
    * Nearly complete: Every node has two children (exception; penultimate
      level may have fewer; ultimate level has zero); at the last level,
      everything is at the left.
        * These are fairly balanced.
        * Operations based on the height are lgn.
    * "Heap order": Each node is higher priority (larger than or equal to) 
       its children.
        * No restrictions on right/left
* The top node is the highest priority.

Adding elements to heaps
------------------------

How might we add an element to an heap?

* We know what the shape has to be, so put the new value in a place that 
  maintains the shape.
* "Swap up" - As long as the priority is higher than the parent,
  swap it with its parent.
* It cost us O(lgn) to add an element.

Removing elements from heaps
----------------------------

How might we get and remove the highest priority element in the
tree?

* Remove the root, put the last thing at the last level at the root.
* "Swap down" - Repeatedly swap with higher-priority child (as long
  as its priority is higher).

Note: We don't care about ordering for equal priority elements.

Question: How do we figure out where the last element is?

* If there are n elements in the tree, what is the path to the last
  element?
* You can tell how many elements are in the last level; check whether
  that's in the left or right half, and recurse.

Storing trees (or at least heaps) in arrays
-------------------------------------------

Idea: The elements of a tree go in an array in breadth-first, top-down,
left-to-right order.

* Children of index 0 are at 1 and 2
* Children of index 1 are at 3 and 4
* Children of index 2 are at 5 and 6
* Children of index 3 are at 7 and 8
* Children of index 4 are at 9 and 10
* ...

Pattern

* Children of index i are at 2*i+1 and 2*i+2
* Parent of index i is at (i-1)/2, round down

If there are n elements in the heap/array, the index of the last element
is n-1.  And we add the new element at index n.

Heap sort
---------

Can you use these ideas to do an in-place array sort using heaps?

Phase 1: Turn the array into a heap

        +------+------------------+
        | heap |     unprocessed  |
        +------+------------------+
               i

        for (int i = 0; i < length; i++) {
          swapUp(i);
        } // for

Phase 2: Modified selection sort

       +-----------+--------------+
       |    heap   |  sorted      |
       +-----------+--------------+
                   size
       while (size > 0) {
         swap(0, --size);
         swapDown(0);
       } // while

Let's try it!  (White board)

What's the cost?

* Building the heap: n*add in O(nlgn)
* Undoing the heap: n*get in O(nlgn)

It's an O(nlgn) in-place sorting algorithm.

An improved phase 1

        +------------+------------+
        | sorted     | heap order |
        +------------+------------+
                     i

        for (int i = length-1; i >=0; i--) {
          swapDown(i);
        }

We started with an ADT: Priority Queue

Designed an efficient data structure for the ADT: Heap

Represented a linked data structure more efficiently as an array

Yielded a sorting algorithm

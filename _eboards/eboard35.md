---
title: Eboard 35  Priority queues, heaps, and heap sort
number: 35
section: eboards
held: 2019-04-29
link: true
current: true
---
CSC 207.01 2019S, Class 35:  Priority queues, heaps, and heap sort
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

* **New**: Friday, May 3, 2pm, Science 2022,
  An Exploration of Torsion of Elliptic Curves over Cubic and Quartic Fields.
* **New**: Sunday, May 5, 2pm, Herrick (?),
  Grinnell Singers vs. Grinnell Orchestra
* **New**: Wednesday, May 8, 2pm, 
  Science 2022 - ECDLP: Frey-Ruck Attack
* Watch your peer compete in one third of a triathalon on Saturday.
  8:30 a.m. Saturday.

#### Extra credit (Wellness)

* **Newish**: CS Picnic, Friday Night.

#### Extra credit (Wellness, Regular)

* **Today**: 30 Minutes of Mindfulness at SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

* Some Grinnell band opening for Friday's Gardner show (I think)
* The Grinnellian
* Track and Field Saturday, Somewhere.

### Questions

Priority queues, revisited
--------------------------

Northwest
NE
South
Center
Southwest

_What is a priority queue?_

* A linear structure in which the `get` method returns elements in
  sorted order.  (Using a comparator to determine order.)
* `put` - Adds an element to the queue.
* Maybe `isEmpty`, `isFull`, `size`
* `iterator`
* `peek`

_How might we implement priority queues?_

* Sorted linked list, highest priority at front
    * `get` - O(1)
    * `peek` - O(1)
    * `put` - O(n)
* Sorted array, highest priority at front
    * `put` - O(n), because we may have to move everything
    * `get` - O(n), because we have to move everything
    * `peek` - O(1), just look at the front
* Sorted array, using the confusing "queue in array" strategy
    * `put` - O(n), because we may have to move everything
    * `get` - O(1), because we just have to clear it out and update
      all those other variables.
    * `peek` - O(1), just look at the front
* Sorted array, highest priority at end
    * `put` - O(n), because we may have to move everything
    * `get` - O(1), because we can just remove the last element
    * `peek` - O(1), just look at the front
* Unsorted array (or unsorted list)
    * `put` - O(1)
    * `get` - O(n)
    * `peek` - O(n)
* Skip list
    * `put` - Expected O(lgn)
    * `get` - Expected O(1)
    * `peek` - Expected O(1)

_How might a tree help?_

* Use a binary search tree.  Hope that it's balanced.
    * `put` - O(lgn)
    * `get` - O(lgn)
    * `peek` - O(lgn)

The heap structure
------------------

A heap is a binary tree with two key properties

* Nearly complete: Every level (except, perhaps, the last) is complete, 
  the last is on the left.
    * If operations depend only on height, O(lgn)
* Heap property: The value in a node is higher priority (smaller) than 
  any of its children. (Smaller than descendents.)
* peek is easy: Look at the top.
* We'll need to think about `put` and `get`.

Adding elements to heaps
------------------------

* Since "nearly complete" is difficult, put the new element at the
  position that keeps the tree nearly complete.
* Restore the heap property using the "swap up" technique
    * If a node is smaller than its parent, swap
    * Repeat that previous step until the node is larger than parent
      or we reach the root
* lgn swaps!

Removing elements from heaps
----------------------------

* Maintain "nearly complete": Swap the last element to the root
* Restore the heap property using the "swap down" technique
    * If the node is larger than one child, swap with smaller child
    * Repeat as necessary
* lgn swaps!

Can heaps help us sort?
-----------------------

Yes.  Build the heap from all the elements and then repeatedly call
get.  You now have the elements in sorted order.

* Yet another O(nlgn) sorting algorithm!

Storing trees (or at least heaps) in arrays
-------------------------------------------

Problem: How do we figure out where that "next spot" for `put` is?

* Option 1: If you know the size of the heap, math will tell you.
* Option 2: Put the heap in an array, adding elements breadth-first,
  preorder, left-to-right.

Observation

* Children of index 0 are at 1 and 2
* Children of index 1 are at 3 and 4
* Children of index 2 are at 5 and 6
* Children of index 3 are at 7 and 8
* Children of index 4 are at 9 and 10
* Children of index 5 are at 11 and 12

Formulae:

* Children of index i are at ... 2i+1 and 2i+2
* Parent of index i is at (i-1)/2, round down

If we're storing the heap in an array, and the heap has size n, the
new element goes in index n.

If we want to remove the last element, it's at n-1.

Heap sort
---------

We are sorting by adding to a heap and then removing.

Question: Can we do heap sort "in place"?

Idea 1/phase 1:

        +------+---------------------+
        | heap |    unordered        |
        +------+---------------------+

        for (int i = 1; i < n; i++) {
          swapUp(heap, i);
        } // for

Idea 2/phase 1:

        +----------------+-----------+
        | unordered      |heap prop. |
        +----------------+-----------+

        for (int i = n-2; i >= 0; i--) {
          swapDown(heap, i);
        } // for


Phase 2:

        +----------------+-----------+
        | heap           | sorted    |
        +----------------+-----------+

        while (heap.size > 0) {
          swap(heap, 0, --heap.size);
          swapDown(heap, 0);
        } 

Question: Is your heap sort stable?

* Claim: If you always swap with the equal left subnode, it will be 
  stable.
* Counter claim: Nope: Look at this example.

Why Sam loves heaps and heapsort
--------------------------------

* ADT (priority queue) -> data structure (heap) -> more detailed implementation
  (array-based heap) -> efficient sorting algorithm
* O(nlgn), in-place, sorting algorithm
* This heap is nothing like the heap in the stack and the heap.  Computer
  scientists suck at naming things; we either use too many names for the
  same thing (dictionary, map, table, hash) or the same name for different
  things (dequeue, heap, ...).

What sort does Java use?

* Sam thinks Java uses a variant of timsort.
* What's timsort?
    * The sort designed for Python, and since adapted by other languages.
    * An interesting variant of bottom-up mergesort.

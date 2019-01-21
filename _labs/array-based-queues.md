---
title: Implementing queues with arrays
repo: <https://github.com/Grinnell-CSC207/linear>
summary: |
  In this laboratory, you will have an opportunity to ground your
  understanding of queues, particularly of the array-based
  implementation of queues.
---

Preparation
-----------

  a. Review the [reading on linear structures](../Readings/linear.html).

  b. Review the [reading on queues](../Readings/queues.html).

  c. If you haven't already done so, fork and clone the repo at <https://github.com/Grinnell-CSC207/linear>.

<!-- 

  If you have forked and cloned that repository, pull changes from the upstream repository.

<pre>
$ git remote add upstream https://github.com/Grinnell-CSC207/linear
$ git pull upstream master
</pre>

-->

Exercises
---------

### Exercise 1: Code Reading

  Read through `ArrayBasedQueue.java`.  You will note that
  the iterator is not yet implemented.  You may also note a few subtle
  (or not so subtle) bugs.  If so, write them down.  If not, that's okay,
  we'll work them out in the lab.

### Exercise 2: Basic Experiments

  Look at `ArrayBasedQueueExpt`.  Summarize what the queue
  should look like at each step of the first series of procedure calls.
  You may also want to revisit the `ReportingLinearStructure`
  class.

  Run `ArrayBasedQueueExpt` and see if you get the output that
  you expect.

### Exercise 3: Correcting a Bug

  You've probably determined that there seem to be some significant
  bugs in the queue implementation.  Can you tell where they are?

  You might learn a bit more about the bug by adding a call
  to `expt.put("d")` before the first call to
  `expt.get()`.

  Do your best to correct the first bug: `get` and
  `peek` don't seem to return the correct value after some
  point.  If you need a hint as to where to look, ask your instructor
  or mentor.

### Exercise 4: Wrapping the Queue

  If you uncomment the second section of code and reduce the size of
  the queue to, say, 4, you may find that the queue fills before it
  should.  (You may also have dealt with that issue.)

  How do we fix that problem?  Normally, we "wrap around",
  so that the back of the queue goes to the front of the array.  For
  example, if we have seven items in a queue, and the front is at 4,
  then the item 0 is at 4; item 1 is at 5; item 2 is at 6; item 3 is
  at 7; item 4 can't be at 8 (there is no index 8), so we wrap it
  around to index 0; item 5 is at 1, item 6 is at 2, and the back of
  the queue is at 3.

  Rewrite your code so that elements wrap in the specified way.  You'll
  need to change `back`.  You may also need to change the
  code for `isEmpty` and `isFull`.

### Exercise 5: An Iterator

  Right now, `ArrayBasedQueue` has only a stub iterator.
  Finish implementing the iterator.  You may want to uncomment the
  third set of experiments to see if the iterator works.

For Those with Extra Time
-------------------------

  If you are fortunate enough to have extra time, work on any
  of the regular or extra problems from [
  the lab on stacks](../labs/stacks.html).


---
title: Preliminaries for class 20
section: prelim
link: true
---
### News / Etc.

* Sit where you would like.

### Upcoming work

* [Exam 1](../exams/exam01) due Thursday
    * **Do not discuss the exam with anyone (other than me)!**
* Readings for Wednesday: 
    * [Sorting basics](../readings/sorting).  (It's actually ready, for once.)
    * [Osera 8.4: Generic Constraints](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap08.pdf)
* No lab writeup.

### Extra credit

#### Extra credit (Academic/Artistic)

* CS Table, Tuesday, noon: Unknown topic
* Convocation Thursday.

#### Extra credit (Peer)

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

### Exam notes

* Remember that `average` must use O(1) space.
* You are dealing with `long` values, so the average is likely to be
  slightly off if the sum of the values is not a multiple of the length.  
  You should round towards zero, just like long division.
* The constructor for your priority queue should take the comparator
  as an input.  (That's probably the only input that constructor needs.)
* You will probably need to create comparators when you run tests or
  experiments.
* You need to keep the nodes in your priority in order by value.  That
  almost certainly happens in the `put` (or `enqueue`) method.


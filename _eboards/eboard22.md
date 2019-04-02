---
title: Eboard 22  Merge sort
number: 22
section: eboards
held: 2019-03-15
link: true
---
CSC 207.01 2019S, Class 22:  Merge sort
=======================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Spring break PSA
    * Questions
* Lab

Preliminaries
-------------

### News / Etc.

* We're back to lab days!  Today's lab is (mostly) by PMO.
* I was sorry to see so few of you at convocation.  It was among the
  best convos I've attended in recent years.
* I hope to be caught up on grading by the end of break.
* Do **not** discuss any aspect of the exam with anyone (except me) until 
  after break.

### Upcoming work

* If you have not done so already, please turn in your printed copy of
  exam 1 and your cover sheet.
* [Assignment 6](../assignments/assignment06) due Thursday after break.
    * This is an individual assignment, rather than a group assignment.
    * You may consult with anyone you wish, provided you cite them.
* Readings for Monday after break: 
    * [Quicksort](../readings/quicksort)
    * [Osera 8.3: Quicksort](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap08.pdf)
* Lab writeup for Class 22: 
    * For those who attended class: Nothing
    * For those who missed class: An analysis of the space complexity
      of the basic version of merge sort and an implementation of the
      improved version of merge sort (including merge).

### Extra credit

#### Extra credit (Academic/Artistic)

#### Extra credit (Peer)

#### Extra credit (Wellness)

* Sub-free spring break!

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

### Spring break PSA

* Moderation in everything.
* Have a great time!
* Consent is essential.

### Questions

Lab
---

Note that your pictorial invariant should say something a bit clearer 
than

```java
    V        V
[1, 4, 8, 3, 5, 9]

       V
[1, 3, ?, ?, ?, ?]
```

In particular, I'd like something more like

```text
+---------+---------+---------+---------+
|         |         |         |         |
+---------+---------+---------+---------+
|         |         |         |         |
X         X         X         X         X

+-------------+-------------------------+
|             |                         |
+-------------+-------------------------+
|             |                         |
X             X                         X
```

or better yet

```text
+---   ---+---------+---------+---------+---------+---   ---+
|  . . .  |         |         |         |         |  . . .  |
+---   ---+---------+---------+---------+---------+---   ---+
|         |         |         |         |         |         |
0         X         X         X         X         X         length

+-------------+-------------------------+
|             |                         |
+-------------+-------------------------+
|             |                         |
0             X                         X
```

---
title: Eboard 32  Hash tables
number: 32
section: eboards
held: 2019-04-22
link: true
---
CSC 207.02 2019S, Class 2^5:  Hash tables
=========================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
* Hash tables: Key ideas
* An exercise: A simple hash function
* Analyzing efficiency
* From objects to integers
* Improving our hash function
* Complexities

Preliminaries
-------------

### News / Etc.

* **The computer ate today's eboard.  Sorry.  Look at [the eboard
  from CSC 207.02](../../02/eboards/eboard32) for similar details.**
* Today is a talk day.  Sit where you would like.
* Apologies for continuing to be behind.  I'm still not perfectly well,
  and sleep is trumping other issues.
* Copies of the skip list reading are available at the back of the room.

### Upcoming work

* No additional reading!   (But you may ant to review.)
* [Assignment 8](../assignments/assignment08) due Thursday the 25th
* No lab writeup for today

### Extra credit

#### Extra credit (Academic/Artistic)

* **New**: CS Table, Tomorrow, "The Cathedral and the Bazaar"
* PBK Convo, Thursday, 11am: "Antievolutionism in Historical Perspective"
* **New**: McKibben lecture, Thursday, 4:15 p.m., JRC 101

#### Extra credit (Peer)

* **New/Questionable**: Track and Field at Drake Relays and/or Grand View

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* **TODAY** 30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* **TONIGHT** Public speaking workshop - April 22 at 7pm in HSSC S3325, with
  Kathy Clemons-Beasley '05.

### Other good things

### Questions

Quiz
----

Hash tables: Key ideas
----------------------


An exercise: A simple hash function
-----------------------------------

```text
A:1,  B:2,  C:3,  D:4,  E:5,  F:6,  G:7,  H:8,  I:9,  J:10, K:11, L:12, M:13, 
N:14, O:15, P:16, Q:17, R:18, S:19, T:20, U:21, V:22, W:23, X:24, Y:25, Z:26

0:                              10:                     20:
1:                              11:                     21:
2:                              12:                     22:
3:                              13:                     23:
4:                              14:                     24:
5:                              15:                     25:
6:                              16:                     26:
7:                              17:                     27:
8:                              18:                     28:
9:                              19:                     29:
```

Hash tables; Assessing costs
----------------------------

From objects to integers
------------------------

Improving that hash function
----------------------------

Complexities
------------

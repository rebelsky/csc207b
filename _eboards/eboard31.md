---
title: Eboard 31  Binary search trees II
number: 31
section: eboards
held: 2019-04-19
link: true
current: true
---
CSC 207.01 2019S, Class 31:  Binary search trees II
===================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Lab

Preliminaries
-------------

### News / Etc.

* Today we continue our exploration of binary search trees, focusing
  primarily on how we remove elements.
    * Continue partners from Wednesday.
* **New policy** If your code is not formatted according to Google 
  style guidelines, neither the mentor nor I will help you.
* Do *not* use ArrayLists to implement queues.  That turns 
  `dequeue` into an O(n) algorithm.  I've provied a simple
  queue implementation that should suffice.

### Upcoming work

* Reading for Monday: [chapter 12 of Osera](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap12.pdf)
* [Assignment 8](../assignments/assignment08) due Thursday the 25th
    * Your partner is your partner from today's lab.
    * If you worked alone, find a partner.
* Lab writeup for today: TBD

### Extra credit

#### Extra credit (Academic/Artistic)

* Dartanyan Brown discussion, 4pm Wednesday April 24, HSSC S3325
* Dartanyan Brown concert, 7:30 pm Wednesday April 24, Sebring-Lewis
* **New**: PBK Convo, Thursday, 11am

#### Extra credit (Peer)

* True West, downtown Community Arts Theatre, Friday, Saturday, Sunday
  (sold out, but you could be on the waiting list)
* Track and Field home on Saturday.  

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* Participate in Kinetic Sculpture Competition: Saturday the 27th
    * <https://bit.ly/kineticsculpture19>
    * You'll need to build your sculpture in advance
    * You get reimbursed for up to $200 in supplies, but must present
      to be reimbursed.
* Public speaking workshop - April 22 at 7pm in HSSC S3325, with
  Kathy Clemons-Beasley '05.

### Other good things

* **New** ISO Cultural Show, Saturday 7:00-9:00, Harris Gym

### Friday PSA

* Hopefully your weekend goes better than the start of class.

### Questions

Skip lists
----------

Linked list, with values in order (using comparator).

Nodes have multiple next links, depending on their "height"

Approximately 1/2 have only one next link

Approximately 1/2 of the remaining ones have only two next links

If the heights are relatively evenly distributed, this should be a
log2 n algorithm.

Lab
---

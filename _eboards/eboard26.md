---
title: Eboard 26  Array-based lists
number: 26
section: eboards
held: 2019-04-08
link: true
current: true
---
CSC 207.02 2019S, Class 26: Array-based lists
=============================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Lab
* Debrief

Preliminaries
-------------

### News / Etc.

* If you would like my programs from Friday and promise not to distribute them 
  to others, I can send them to you.  (I need an email from you indicating
  that you promise not to distribute them to others.)
* I am a bit puzzled by the disconnect between "I had difficulty with
  Big O" and attendance at last night's mentor session.

### Upcoming work

* Readings for Wednesday
    * [Iterating lists](../readings/list-iterators)
    * [Doubly and circularly linked lists](../readings/doubly-linked-lists)
* [Makeup exam 1](../exams/makeup01) due Thursday the 11th
    * You can bring your printed version to class on Friday
* Lab writeup: Exercise 6b

### Extra credit

#### Extra credit (Academic/Artistic)

* CS Table Tomorrow - The Autocrat's New Toolkit

#### Extra credit (Peer)

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

* "State of SHAW" talk Thursday at 11am in JRC 101.
    * When did SHACS get renamed?
* Participate in Kinetic Sculpture Competition: Saturday the 27th
    * You'll need to build your sculpture in advance
    * <https://bit.ly/kineticsculpture19>
* Wednesday the 10 at 4pm on Mac Field: Giant Laurel Leaf.  (Free t-shirt!)
* Scarlet and Give Back Day next Wednesday/Thursday (I think).  If you
  don't have money to donate, let me know and I will give you $5 to donate
  on Monday.
* Prosp a Hostie

### Other good things

### Questions

_Do we need comparators for QuicksortDNF?_

> No.  You're only dealing with arrays of integers.

_Did I ask that before?_

> Probably.  But I forgot to put it in the eboard.

_Can't we write our own comparator for integers?_

> Yes.  But you shouldn't use it in problem 2.

_What's the difference between an iterator and a cursor?_

> Both are used for iterating lists.

> Cursor, in my world, is a movable position.  You can look at the
  cursor.  You can advance it.  You can retreat it.

> Iterator, in Java, supports similar operations, but you move the
  iterator each time you ask for an element.

Lab
---

### Setting/removing at the end

How might you end up with the following state?

    A B C
         ^

Option 1: Start with the list [A,B,C]. Make an iterator.  Call `next()`, 
`next()`, `next()`.  (It is legal to call `set` or `remove`.)

Option 2: Start with the list [A,B,C].  Make an iterator.  Call `next()`,
`next()`, `add(D)`.  (It is *not* legal to call `set` or `remove`.)

Option 3: Start with the list [A,B,C,D].  Make an iterator.  Call
`next()`, `next()`, `next()`, `next()`, `remove()`.  (It is *not* legal
to then call `set` or `remove`.)

### Setting in bi-directional lists

When implementing `set` with `previous`, you may find it useful to
have a variable that keeps track of the index of the element to set 
or remove.  (Set it to something like -1 or Int.MIN_VALUE if you can't
set or remove.)

### Writeup

Writeup: Exercise 6b

This is class 26.

Debrief
-------

Moral: Adding and removing elements is likely to be expensive if the
underlying structure is an array.  So be careful about using ArrayLists
when you need an expandable list.

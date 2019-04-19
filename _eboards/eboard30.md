---
title: Eboard 30  Binary search trees I
number: 30
section: eboards
held: 2019-04-17
link: true
current: true
---
CSC 207.01 2019S, Class 30:  Binary search trees I
==================================================

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

* We're going to do two days with binary search trees.  You'll have
  the same partner for both labs.  (Friday will mostly be `remove`.)
* Each group should have received an "R" or an "I" card.  I'll
  explain later.

### Upcoming work

* No additional readings for Friday
* [Assignment 7](../assignments/assignment07) due Thursday the 18th
* Lab writeup for today: TBD

### Extra credit

#### Extra credit (Academic/Artistic)

* Student research extravaganza (some still on Thursday)
* The Magic Flute, Thursday, April 18, 7:00 p.m. Sebring-Lewis
* **New**: Dartanyan Brown discussion, 4pm Wednesday April 24, HSSC S3325
* **New**: Dartanyan Brown concert, 7:30 pm Wednesday April 24, Sebring-Lewis

#### Extra credit (Peer)

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

* **New**: CS Internship Hour, Thursday, 6:30-7:30 p.m., Noyce 3821. 
  _Free Pizza_.
* Participate in Kinetic Sculpture Competition: Saturday the 27th
    * <https://bit.ly/kineticsculpture19>
    * You'll need to build your sculpture in advance
    * You get reimbursed for up to $200 in supplies, but must present
      to be reimbursed.
* Public speaking workshop - April 22 at 7pm in HSSC S3325, with
  Kathy Clemons-Beasley '05.  "Kathy is the Global head of Leadership
  and Manager Development for Blackrock and has been the speaker
  coach for TEDxGC."
* Clothing donation boxes in lounges.  Donate! 

### Other good things

### Questions

What's the best way to create the temporary array for mergesort?

> Option one

        T[] temp = (T[]) new Object[size];

> Option two

        T[] temp = arr.clone(); // Gives a new array, with same values

What events should we logged during merge?

> You will be comparing elements in the two halves of the array.  You
  should log those as comparison events.

> You will copy elements back from the scratch array to the main array.
  You should log those as copy events.

> E.g., [1,5,8,2,3,4]: compare(0,3),compare(1,3),compare(1,4),compare(1,5),
  copy(1,0), copy(2,1), copy(3,2), copy(4,3), copy(5,4), copy(8,5).

> You should only log the primary changes to the original array, since those
  are the only ones we will "visualize".

Lab
---

Note: I thought about using the Anya/PM version of the lab, but decided
against it, so I wrote a brand-new lab.  

If you are assigned the iterative version, here's pseudocode

```text
if (root is null)
  set the root to a new node
else
  set current to root
  while (...) 
    compare key to current.key
    case =:
      replace the value
      return the old value
    case <:
      if there is a left subtree, 
        current = left
      otherwise
        current.left = new node
        add 1 to size
        return null
    case >:
      if there is a right subtree, 
        current = right
      otherwise
        current.right = new node
        add 1 to size
        return null
```

If you are assigned the recursive version, here's pseudocode.

```text
function set(key, value)
  root = setHelper(root, key, value)
  return cache

function setHelper(node, key, value)
  if node == null
    set cache to null
    add 1 to size
    return a new node
  else
    compare key to node.key
    case =:
      set cache to value in node
      update value in node
      return node
    case <:
      node.left = setHelper(node.left, key, value);
      return node;
    else
      node.right = setHelper(node.right, key, value);
      return node;
```


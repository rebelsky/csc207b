---
title: Eboard 30  Binary search trees I
number: 30
section: eboards
held: 2019-04-17
link: true
current: true
---
CSC 207.02 2019S, Class 30:  Binary search trees I
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
  the same partner for both labs.
* Each group should have received an "R" (purple) or an "I" (yellow) card.  
  I'll explain what those mean in a bit.

### Upcoming work

* No additional readings for Friday
* [Assignment 7](../assignments/assignment07) due Thursday the 18th
* Lab writeup for today: Exercise 4 (Class 30)

### Extra credit

#### Extra credit (Academic/Artistic)

* Student research extravaganza (some still on Thursday)
* The Magic Flute, Thursday, April 18, 7:00 p.m. Sebring-Lewis
* **New**: Dartanyan Brown discussion, 4pm Wednesday April 24, HSSC S3325
* **New**: Dartanyan Brown concert, 7:30 pm Wednesday April 24, Sebring-Lewis
    * Read more in Rootstalk

#### Extra credit (Peer)

* Track and Field home on Saturday.  
* ISO Cultural Show, Saturday 7:00-9:00, Harris Gym

#### Extra credit (Wellness)

* Bubble day

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* Internship Hour, Thursday, 6:30-7:30 p.m., Noyce 3821. 
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

What are the indices in part 3 of the homework?

> Numbers from 0 to `n`-1, 1 copy of each.

What are we sorting?

> The array of numbers from 0 to `n`-1, 1 copy of each.

What's that other array?

> Something the system uses to determine what note to play.  You don't
  need to know details, other than that it gets used.

What are the events for?

> You'll have a history of sorting the array of numbers.  We will
  "play back" this history using sound and vision.

What events get added at the end?

> compare(0,1), compare(1,2), compre(2,3), ...

Why?

> Because it plays the notes in ascending order as a final step.

Lab
---

Note: I thought about using the Anya/PM version of the lab, but decided
against it, so I wrote a brand-new lab.   (It basically said, "Starting
from scratch, implement binary search trees."  I like more infrastructure.)

If you are assigned the iterative version of exercise 3, here's pseudocode

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

If you are assigned the recursive version of exercise 3, here's pseudocode.

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


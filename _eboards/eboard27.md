---
title: Eboard 27  Doubly-linked lists
number: 27
section: eboards
held: 2019-04-10
link: true
---
CSC 207.01 2019S, Class 27:  Doubly-linked lists
================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Lab
* Debrief (if time)

Preliminaries
-------------

### News / Etc.

* For the exam, make sure to put each test in the correct directory and 
  the new version of `TestUtils.java` in the `utils` directory.
* Note: A lot of today's lab is "think about how to design code, then
  compare to my design".  You will find the lab most beneficial if you
  actually spend the time thinking and comparing.

### Upcoming work

* Readings for Friday
    * [Osera 11.0-11.3](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap11.pdf)
* [Makeup exam 1](../exams/makeup01) due Thursday the 11th
    * You can bring your printed version to class on Friday
* Lab writeup: Exercise TBD

### Extra credit

#### Extra credit (Academic/Artistic)

#### Extra credit (Peer)

#### Extra credit (Wellness)

* Friday, April 12, 4-6pm: Make fidgets.  Maker lab, 927 Broad street.
  For more information or accommodation, contact [bernalma] or
  [phamanht]
* Monday, April 15, 7:30 p.m., Harris Cinema: From the Munchies to
  Memory Effects: What the Science Says About Cannabis/Marijuana

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* CANCELLED: "State of SHAW" talk Thursday at 11am in JRC 101.
* Wednesday the 10 at 4pm on Mac Field: Giant Laurel Leaf.  (Free t-shirt!)
    * Relocated to Darby Gym
* Scarlet and Give Back Day TODAY.  
* Participate in Kinetic Sculpture Competition: Saturday the 27th
    * <https://bit.ly/kineticsculpture19>
    * You'll need to build your sculpture in advance
    * You get reimbursed for up to $200 in supplies, but must present
      to be reimbursed.

### Other good things

### Questions

Please talk to us about variants.

> You can have doubly-linked lists without dummy nodes.  prev of the
  first element is null, next of last element is null.

> You can have doubly-linked lists with a dummy node, usually at the front.

> You can have circularly-linked lists by setting next of the last element
  to the first element (or the dummy) and prev of the first element (or
  the dummy) to the last element.

Why?

> dummy node: You always have one node, so you don't have to do something
  like "if BLAH==null".

> circular: You no longer need to check if next/prev are null.

Lab
---

Question: Do you really need the cursor to be a node.  Couldn't you just
have two fields, `prev` and `next`?

> Probably.  I didn't think about that in my original design.

Question: Is there a bug in your remove method, in that you don't update
the position when you remove the previous element?

> Almost certainly.  I should have written more tests.  (or any tests,
  for that matter.)

```java
        // Update the cursor
        if (this.cursor.next == this.update) {
          this.cursor.next = this.update.next;
        } // if
        if (this.cursor.prev == this.update) {
          this.cursor.prev = this.update.prev;
          --this.pos;  // <----------------------------- ADD THIS
        } // if
```

Debrief (if time)
-----------------

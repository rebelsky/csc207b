---
title: Eboard 29  Trees and tree traversal
number: 29
section: eboards
held: 2019-04-15
link: true
---
CSC 207.01 2019S, Class 29:  Trees and tree traversal
=====================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
* Tree traversal
* Lab

Preliminaries
-------------

### News / Etc.

* _Please_ make sure that you've installed the Google Style Sheet in Eclipse.
* If you haven't yet submitted your epilogue for the makeup, please do so
  ASAP.

### Upcoming work

* Readings for Wednesday
    * [Osera 10.4](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap10.pdf)
    * [Osera 11.4](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap11.pdf)
* [Assignment 7](../assignments/assignment07) due Thursday the 18th
* No lab writeup for today.

### Extra credit

#### Extra credit (Academic/Artistic)

* The Magic Flute, Thursday, April 18, 7:00 p.m. Sebring-Lewis
* **New**: Student research extravaganza (any one event)

#### Extra credit (Peer)

* Track and Field home on Saturday.  (30 min suffice; your peer runs
  around noon.)

#### Extra credit (Wellness)

* **Today**:
  Monday, April 15, 7:30 p.m., Harris Cinema: From the Munchies to
  Memory Effects: What the Science Says About Cannabis/Marijuana

#### Extra credit (Wellness, Regular)

* **Today**:
  30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* **Tonight**: Prairie Burn, Grimes Farm, tonight at 7:30ish.  See 
  Facebook events and random email.
* Participate in Kinetic Sculpture Competition: Saturday the 27th
    * <https://bit.ly/kineticsculpture19>
    * You'll need to build your sculpture in advance
    * You get reimbursed for up to $200 in supplies, but must present
      to be reimbursed.
* Public speaking workshop - April 22 at 7pm in HSSC S3325, with
  Kathy Clemons=Beasley '05.  "Kathy is the Global head of Leadership
  and Manager Development for Blackrock and has been the speaker
  coach for TEDxGC."
* Clothing donation boxes in lounges.  Donate! 

### Other good things

### Questions

_I hear that there are some questions about removal.  Please ask them._

How do we tell what to remove?

> Semantics: Remove what was returned by the most recent call to
  `previous` or `next`.

> We must therefore have a field that stores the information about
  which direction we just moved.

> Option 1: `int dir; // Use pos for forward and neg for backwards.`

> Option 2: Keep a pointer to the thing we will remove or set when
  we call remove or set.  

> In (all) options, we will still need to update the state of the
  iterator.  If we remove the previous element (because we just
  called `next`), we'll need to decrement the position in the list.

When we remove an element in the array-based implementation, what
should we do?

> We've chosen to shift everything left.

> One could try putting a null there, but that complicates the implementation.

Do we need a pos field in a linked-list iterator?

> Since list iterators have prevIndex and nextIndex methods, it's helpful
  to have one.

You can't call `remove` twice in a row, can you?

> Nope.  You must always have an immediately preceding call to `next` or
  `prev`.  (As long as we don't move the iterator or change the list, 
  we're fine.)

> You've just seen a value; your decision to remove or set is supposed to be
  based on that value.  Historical memory is complicated, so it's just
  the immediate predecessor.

What does `pos` represent?

> That's our choice.  We tend to have it be the index that we will return
  with `nextIndex`.  We could have it have a half-value and round in
  each direction.

What does `previousIndex` return at the start of the list?

> The documentation says -1.

What does `nextIndex` return at the end of the list?

> The documentation says "the list size".


Quiz
----

_Sam needs an editor (no, not `vi`).  The first line of problem 1 says
"each of the two primary list iterations"; it means "each of the two
primary list *implementations*._

Tree traversal
--------------

Trees are like 2D lists (kinda); each node has multiple "next" elements,
but only one preceding element.  In binary trees, there are up to two
next elements, usually designated as "left" and "right".

_How many different systematic orders can we use to traverse a tree?_

Broad decisions

* Left-to-right or right-to-left: In which order do we visit children.
* Ordering
    * Preorder: Node before children 
    * Inorder: Node between children
    * Postorder: Node after childen
* Depth first vs breadth first
    * Do we go deep into the tree or do we go across each level

Detailed list

* Left-to-right, depth-first, preorder
* Right-to-left, depth-first, preorder
* Left-to-right, depth-first, inorder
* Right-to-left, depth-first, inorder
* Left-to-right, depth-first, postorder
* Right-to-left, depth-first, postorder
* Left-to-right, breadth-first, preorder (top-down)
* Right-to-left, breadth-first, preorder (top-down)
* Left-to-right, breadth-first, postorder (bottom-up)
* Right-to-left, breadth-first, postorder (bottom-up)

_Suppose you wanted to build an `Iterator` for a tree.  What would it
look like?  (You can choose the traversal order.)_

* That is, what do you need to keep track of, and how can you use that
  while iterating?
* "We'll need to put the rest of the tree somewhere.  Maybe a list."
* Explore the question in lab.

Lab
---

Don't spend too much time on exercise 3.

No lab writeup.


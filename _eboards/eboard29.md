---
title: Eboard 29  Trees and tree traversal
number: 29
section: eboards
held: 2019-04-15
link: true
current: true
---
CSC 207.02 2019S, Class 29:  Trees and tree traversal
=====================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
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
* Lab Writeup: TBD

### Extra credit

#### Extra credit (Academic/Artistic)

* The Magic Flute, April 18, 7:00 p.m. Sebring-Lewis
* **New**: Student research extravaganza (any one event)

#### Extra credit (Peer)

* Track and Field home meet, Saturday.  Race times tbd.

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

* **New**: CS Internship Hour, Thurday, 6:30-7:30 p.m., Noyce 3821. 
  Free pizza.
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
  If we are using a linked structure, we will likely need to change
  the `prev` link.

What happens when we remove in an array-based list?

> Traditionally, we shift everything to the left.

> If the thing we remove is before the cursor/iterator, we should
  decrement the position by 1.

Did you inform the people who didn't format correctly?

> Thu: Yes.  Sun: No.

Should we turn in new epilogues if we took advantage of the free extension?

> Yes.  Your times probably changed.  Maybe you learned something else.

Where does `pos` start?

> It depends on the interpretation of the pos.  I generally use pos as
  "position of the next element".  I would therefore start it at 0.

> Some folks take the "between elements" way to seriously and start it
  at -0.5.

_How do we remove in a doubly-linked list?_

> Conceptual: The cursor refers to previous and next, for convenience.

> We know which element to remove because we've stored that information.
  Suppose it's the previous element (because we've called `next`).

> We are going to update the list, so we may need to update the cursor's
  prev link.

        prev = update.prev;

> We need to change the surrounding pointers.

        update.prev.next = update.next;
        update.next.prev = update.prev

> We may want to clear the pointers (helps avoid later problems)

        update.prev = null
        update.next = null;

> If we're keeping track of the position (and we should) we decrement
  it by 1.

> Set `update` to null so that we don't allow subsequent updates.

> Special case: If we removed the first element in the list, we need
  to update front.

> The code seems to mimic this approach.

Quiz
----

_Sam needs an editor (no, not `vi`).  The first line of problem 1 says
"each of the two primary list iterations"; it means "each of the two
primary list *implementations*._

Lab
---



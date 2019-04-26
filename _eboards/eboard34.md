---
title: Eboard 34  Chaining in hash tables
number: 34
section: eboards
held: 2019-04-26
link: true
current: true
---
CSC 207.02 2019S, Class 34:  Chaining in hash tables
====================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Friday PSA
    * Questions
* A bit more about traversal and iteration
* Lab

Preliminaries
-------------

### News / Etc.

* *Reminder*: Next week's Science Teaching and Learning Group will 
  discuss student wellness.  I'm hoping to share some of the extra credit
  reports you've submitted (anonymously).  Let me know if you object to
  me sharing yours.
* Alternate final time: Thursday (morning section or afternoon section).

### Upcoming work

* Reading for Monday: Forthcoming.
* Today's lab writeup: Exercise TBD (Class 34)
* [Exam 2](../exams/exam02) due next Thurday
    * Prologue due Monday night.
* Today's lab writeup: Corrected `set` method (after exercise 4)

### Extra credit

#### Extra credit (Academic/Artistic)

* CS Table, next Tuesday, Facebook Data
* Three talks by Prof. Dr. Yvonne Foerster (<https://yvonnefoerster.com/>)
    * Wednesday: May 1, 4:30-6pm, HSSC S3325: _Beyond the Anthropocene: Technology, Innovation, and the (Post-)Human Condition_
    * Thursday, May 2, Noon-12:50pm, HSSC N3110 _Degrees of Freedom: Embodiment, Neuroplasticity, and the Need for a Critical Neuroscience_ (Lunch and beverages provided)
    * Friday, May 3, Noon-12:50pm, Bucksbaum 152: _Designing Future Bodies: Fashion and Technology_ (Lunch and beverages provided)

#### Extra credit (Peer)

* **Today**: Track at Grand View on Friday.  (Attend class first!)
* **Tomorrow**: Kinetic Sculpture Competition, Saturday 1-3pm at the Stew.
* **Tomorrow**: YGB, 4pm Saturday in Sebring-Lewis.
* Sunday at 1pm Food Recovery Network Award and Presentation with EPA Science Person (JRC 226)
* **Tonight**: Swing/Contra with Live Band Friday at 8:00-10:30 in Loose Lounge
* **Tomorrow**: Titular Head Saturday.
* **New**: Gardner Concert next Friday.
* **New**: Grinnellian next week.

#### Extra credit (Wellness)

* **Tomorrow**: Guided Movement Meditations, 12:15 Friday and Saturday, 
  Flanagan Theatre.  Also between performances of the show.
* **New/Today**: Hand-pulled noodle workshop, Apr. 26, 4:30-5:30 pm, 
  Chinese House. RSVP by signing up on the form posted in faculty house.
  (Limited to 15 people).

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* **New**: CS Picnic Next Friday!  (Info forthcoming.)

### Other good things

* **New**: Chamber Ensemble, Saturday, 2pm, Herrick
* **New**: Collegium, Sunday, 2pm, Sebring-Lewis
* **New**: Japanese Movie Night: Quill: The Life of a Guide Dog, Apr. 26, 7 pm, 
JRC 227
* **New**: Japanese Movie Night: Pokemon: The Power of Us, Apr. 27, 7-9pm, HSSC 
N1130

### Friday PSA

* I care about you.  I'm lucky to teach you.  Take care of yourselves.

### Questions

A bit more about traversal and iteration
----------------------------------------

Problem: How do we do depth-first traversal that is not preorder.

* Key idea: Put two kinds of values in the stack: Nodes or Pairs
* Old:
    * Pop
    * Push non-null children (right-to-left)
    * Return what we just popped
* New:
    * Pop
        * If it's a node
            * Push its key/value pair
            * Push non-null right child
            * Push non-null left child
        * If it's a pair
            * Return it
* Depth-first, left-to-right, postorder
* Do inorder: push right, push key/value, push left
* Do preorder push right, push left, push key/value
* Do depth-first right-to-left postorder: push key/value, push left, push right

        public boolean hasNext() {
          return !stack.isEmpty();
        } // hasNext()

        public Pair<K,V> next() {
          while (true) {
            Object o = stack.pop();
            if (o instanceof Node<?,?>) {
              @UnsafeCastOrSomethingLikeThat
              Node<K,V> node = (Node<K,V>) o;
              push o.pair;
              if (o.right != null) { push o.right; }
              if (o.left != null) { push o.left; }
            } // if
            else {
              return (Pair<K,V>) o;
            }
          } // while
        } // next()

Lab
---

Writeup: Corrected `set` method (probably after exercise 4).

---
title: Eboard 34  Chaining in hash tables
number: 34
section: eboards
held: 2019-04-26
link: true
current: true
---
CSC 207.01 2019S, Class 34:  Chaining in hash tables
====================================================

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

* *Reminder*: Next week's Science Teaching and Learning Group will discuss 
  student wellness.  I'm hoping to share some of the extra credit
  reports you've submitted (anonymously).  Let me know if you object to
  me sharing yours.

### Upcoming work

* Reading for Monday: Forthcoming.
* Today's lab writeup: Exercise 5c. (Class 34)
* [Exam 2](../exams/exam02) due next Thursday.
    * Prologue due Monday night.

### Extra credit

#### Extra credit (Academic/Artistic)

* CS Table, next Tuesday, Facebook Data
* Three talks by Prof. Dr. Yvonne Foerster (<https://yvonnefoerster.com/>)
    * Wednesday: May 1, 4:30-6pm, HSSC S3325: _Beyond the Anthropocene: Technology, Innovation, and the (Post-)Human Condition_
    * Thursday, May 2, Noon-12:50pm, HSSC N3110 _Degrees of Freedom: Embodiment, Neuroplasticity, and the Need for a Critical Neuroscience_ (Lunch and beverages provided)
    * Friday, May 3, Noon-12:50pm, Bucksbaum 152: _Designing Future Bodies: Fashion and Technology_ (Lunch and beverages provided)

#### Extra credit (Peer)

* **Today**: Contra tonight at 8pm in Loose (live band + live caller)
* **Today**: Track at Grand View 
* **Tomorrow**: Titular head

#### Extra credit (Wellness)

* Guided Movement Meditations, 12:15 Friday and Saturday, 
  Flanagan Theatre.  Also between performances of the show.
* Hand-made nodel workshop tonight.

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* CS Picnic Next Friday!  (Info forthcoming.)

### Other good things

### Friday PSA

* I want you to be happy and healthy, take care of yourselves.

### Questions

_Do you have a recommended strategy for detecting concurrenct modifications?_

> In the main structure, keep a counter of the number of modifications.

> Each time you create an iterator, copy that counter.

> Each time you modify in the struture, update the counter in the structure.

> If the iterator's counter does not match the structure's, we have a
  concurrent modification, and should throw and exception.

> Each time you modify in an iterator, update the counter in both the
  structure and the iterator.

> This approach is unlikely to be thread safe.  Take OS to find out what
  that means.

Lab
---

_How did you figure out that anteater and buffalo map to the same cell in the hash table?_

> Experimentation.  (That is, I made a large list of animal names and tried to add each until I got a collision.)

_What should `get` do if the key is not there?_

> It should throw an `IndexOutOfBoundsException`.

_How would you suggest we iterate the arraylist in `get`?

        for (Pair<K,V> pair : alist) {
          ...
        } // for

_Why not with `for (int i = 0; i < alist.size(); i++) { ... }`?

> The first is easier to read.  It's also easier to use if we decide
  to change how we represent buckets.

_How would you suggest we iterate the ArrayList in `set`?

> For that, you likely need the `for (int i ...)` pattern.

_Why?_

> So that you can more easily replace the element if you find a match.

_What's today's lab writeup?_

Exercise 5c. (This is Class 34)

---
title: Eboard 37  Traversing graphs
number: 37
section: eboards
held: 2019-05-03
link: true
---
CSC 207.02 2019S, Class 37:  Traversing graphs
==============================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Friday PSA
    * Questions
* Lab

Preliminaries
-------------

### News / Etc.

* We are back to lab days.  Yay!
* Electronic evaluations are live in some classes, but other folks
  (including me) don't want you to do them outside of class, so those
  are marked "unavailable".
* Buffalo buffalo Buffalo bufallo bufallo buffalo Buffalo buffalo.

### Upcoming work

* Reading for Monday
    * [Wikipedia: Prim's Algorithm](https://en.wikipedia.org/wiki/Prim%27s_algorithm)
    * [Wikipedia: Kruskal's Algorithm](url: https://en.wikipedia.org/wiki/Kruskal%27s_algorithm)
* No lab writeup today.
* [Exam 2](../exams/exam02) due last night. 
    * Free extensions until Sunday.  Let me know if you need longer.
    * Those in by tonight will be graded by Monday.
* Final exam: 9am or 2pm, Thursday or Friday of finals week.
    * I'll try to have a sample final ready early next week.
    * Let me know which of the four times you plan to take the final.
* No more homework!
* No more quizzes!

### Extra credit

#### Extra credit (Academic/Artistic)

#### Extra credit (Peer)

* Sunday, May 5, 2pm, Herrick (?),
  Grinnell Singers and the Grinnell Orchestra
* **Tonight**, 9pm Gardner, Opening Band for Gardner show: "Sorry We're Late".
  Opening for "Girl K" with "Blizzard Babies"
* **Tomorrow** The Grinnellian, Saturday.

#### Extra credit (Wellness)

* **Tonight** CS Picnic, Friday Night.
* Pella Tulip Festival.

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

### Friday PSA

* All things moderation
* Pay attention to the law
* Know what is right for you; be comfortable with your choices
* Consent is absolutely necessary

### Questions

So, how do we traverse a graph?

> Um.  Maybe with recursion.

> For breadth-first traversal, we can use a queue.

> For depth-first traversal, we can use a stack.  (We can also use
  recursion.)

Lab
---

What's the default value in a boolean array?

I don't know, let's try.

```shell
$ jshell
|  Welcome to JShell -- Version 11.0.1
|  For an introduction type: /help intro

jshell> boolean[] vals = new boolean[33];
vals ==> boolean[33] { false, false, false, false, false,  ... lse, false, false, false }
```

I love REPLs.  (Read-Eval-Print Loops; the traditional interaction with
Scheme, also available for Java in JShell)

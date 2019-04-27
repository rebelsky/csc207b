---
title: Eboard 23  Quicksort
number: 23
section: eboards
held: 2019-03-30
link: true
---
CSC 207.01 2019S, Class 23:  Quicksort
======================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Lab
* About exam 1

Preliminaries
-------------

### News / Etc.

* Welcome back from break!  And happy April Fools' day!
* We're continuing lab days!  Today's lab is (mostly) by PMO and AV (or vv).
* I will reserve the last twenty-five minutes of class for an initial 
  discussion of exam 1.  I will return the exam at that point.
* Wednesday's class is now a "pause for breath", mostly designed to
  support discussion of exam 1.
* Grading the exams took far longer than I anticipated.  I still do not
  have complete grade reports for other materials.
    * I have decided that I will now drop the lowest *two* quiz grades.
    * It appears I left the quizzes at home.
* Quick survey: When I write the next exam, do you want me to include
  "characters" (e.g., Tessa Ter)?
* I will be unavailable this afternoon and tomorrow due to a professional 
  meeting.
* Detour questions for Wednesday:
    * Do you prefer the PM/AV-style diagrams or the SR-style diagrams?
    * Do you prefer the PM/AV-style labs or the SR-style labs?
* Do you want the traditional "Sam wears a costume on April Fools' Day" jokes?

### Upcoming work

* [Assignment 6](../assignments/assignment06) due Thursday.
    * This is an individual assignment, rather than a group assignment.
    * You may consult with anyone you wish, provided you cite them.
* There is no reading for Wednesday.
* Lab writeup for Class 23
    * Problem: Your partition method.
    * Subject: Lab Writeup for Class 23 (Your Names)
    * To: csc207-01-grader@grinnell.edu

### Extra credit

#### Extra credit (Academic/Artistic)

* Convo Thursday (sorry, I don't know the details yet)
* Foreign service alum presentation: HSSC S3325, 7:30 tonight.

#### Extra credit (Peer)

* Singers concert, April 7

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* Wednesday the 10 at 4pm on Mac Field: Giant Laurel Leaf.  (Free t-shirt!)
* Scarlet and Give Back Day next Wednesday/Thursday (I think).  If you
  don't have money to donate, let me know and I will give you $5 to donate.

### Other good things

### Questions

_I'll take questions on the homework and on Quicksort._

How should we deal with duplicates in the array?

> Assume there are no duplicates.

What are "in parameters" and "out parameters"?

> We can think of returning values from a procedure in two different
  ways:

> There are the ones that we explicitly return (using `return` in C/Java)

> Sometimes, we "return" a value by changing a pointer/reference parameter.
  For example, `scanf("%d", &x);`  

> Parameters that are only used as input are called "input parameters".

> Parameters that are used primarily to get output from the procedure
  (as in the `&x` above) are "output parameters".

Can you explain `public static <T> void copy(List<?  extends T> src, List<?  super T> dst)`?

> Let's consider a situation

> T is a class.

> S is a superclass of T.

> U is a subclass of T.

> I can put values of type U in an array of type S[].  (If U is a subclass
  of T, it's also a subclass of S.)

> The `copy` procedure can therefore put things from a list of U's into
  a list of S's.

> `<? extends T>` is the generic version of U.

> `<? super T>` is the generic version of S.

Quicksort lab
-------------

Writeup:

* Subject: Lab Writeup for Class 23 (Your Names)
* To: csc207-01-grader@grinnell.edu
* Problem: Your partition method

Notes:

* Make sure to put the pivot back where it belongs (at the left end of
  the >= section).
* Return the index of the pivot.

About exam 1
------------

* It appears many of you did not understand the level of correctness
  (and therefore testing/experimentation) I expect on an examination.
* My preferred grade distribution:
    * 100% A.
* My expected grade distribution: 
    * 40% A, 40% B, 20% C.
* My actual grade distribution: 
    * 5 A range: 2 A, 3 A-, 
    * 7 B range: B 3 B+, 1 B/B+, 2 B, 1 B/B-, 
    * 6.5 C range: 1 C+, 2 C/C+, 3 C, 1 C-/D+, 
    * 3.5 D range: 2 D+, 0 D, 1 D-, 
    * 5 F range: 3 F, 2 zero
    * Plus 3 ungraded
* I will be distributing a makeup examination on Wednesday.  It will be
  due the following Thursday (a week and a day later).  The makeup is
  optional.  There will be no homework at the same time.
* I will use most of Wednesday to discuss whatever issues you'd like 
  me to focus on from the exam.  Use one or more of the notecards to
  write down topics you would like me to cover, and I will try to 
  prioritize.
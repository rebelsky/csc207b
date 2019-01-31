---
title: Eboard 02  Programming in the small with Java
number: 2
section: eboards
held: 2019-01-25
link: true
---
CSC 207.01 2019S, Class 02:  Programming in the small with Java
===============================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Friday PSA
    * Questions
* Lab setup
* Lab
* Debrief

Preliminaries
-------------

### News / Etc.

* Sit where you'd like (with reason); we'll move around a bit after the
  preliminaries.
* You should have received one email message from me since last class, 
  a notice about the Data Buddies survey.
* If you're not on the csstudents list, you probably should be.  Let me
  know if you'd like to be added.
* I'll take quick attendance.
* I'm teaching two sections of CSC 207 this semester.  They should be
  similar, but evidence suggests that when I "lecture", I do so differently
  each time.  I may assume that I've said something to you that I haven't;
  let me know when that happens.
* Mentor sessions at time TBD (Sunday nights).  Doodle poll coming.  
* Anh Thu is an SEPC member, feel free to let her know your thoughts
  about the department (or this class) and she will relay them anonymously.

### Upcoming work

* [Assignment 1](../assignments/assignment01) due Thursday night.
* Readings due before class Monday.
    * [Gettings started with Git and GitHub](../readings/git)
    * [The Eclipse IDE](../readings/eclipse)
* Quiz on Monday (and every Monday).
    * Summarize PUM/LIA
    * Simple Java stuff (read code, maybe write a little)

### Extra credit

* Thanks for folks who have been sharing suggestions!
* Getting extra credit.
    * Participate in the activity.
    * Send me an email of the form "CSC 207 Extra Credit (Name)", preferably
      within two days of the event.
    * The body of the email should include a reflective paragraph about
      the activity.
    * I will eventually respond and record the extra credit.

#### Extra credit (Academic/Artistic)

* Opening reception for "The Incident", Bucksbaum Rotunda, 7:00 p.m. 
  tonight. (May be triggering.)

#### Extra credit (Peer)

* Watch some part of the indoor track meet on Saturday (starts at 9:30);
  30 minutes suffices.

#### Extra credit (Wellness)

* Kindness through gratitude, next Monday and Tuesday 11-1, outside DHall.

#### Extra credit (Misc)

* Data Buddies survey (distributed via email)
    * Folks have reported that it was mostly a good use of time.

#### Other good things

* Women's basketball vs. St. Norbert, 5:30 p.m. Friday.
* Men's basketball vs. St. Norbert, 5:30 p.m. Friday.
* Women's basketball vs. Ripon, 1:00 p.m. Saturday.
* Men's basketball vs. Ripon, 3:00 p.m. Saturday.

### Friday PSA

* If you imbibe, do so within reason.
* Whatever decisions you make, you should make them because they are
  right for you!
* If you cohabit, get consent.
* Present: BAC

### Questions

Can I switch sections?

> Sure.  Bring me an add-drop form.

What are the group work policies for this course?

> _Labs_: You can choose whether to work alone or have a randomly assigned
  partner.  (I would recommend the latter.)  Randomly assigned will be 
  mostly on a per-lab basis.  You can switch your preference throughout
  the semester.

> _Homework_: You get a randomly assigned partner.

> _Exams and quizzes_: On your own.

Who is my partner on HW1?

> Whoever I announced at the start of class.  I'll try to record the
  info somewhere.

What username and password do I use?

> Same as elsewhere on campus.

Lab prep
--------

* First, we will figure out how many people want to work alone and
  how many people would like a partner.
    * Alone: Write your name on a red card
    * Partner: Write your name on a yellow card
* Then, we'll assign folks to partners based on those preferences.
* After that, you'll do the lab.

Lab
---

What does `export PATH=/usr/lib/jvm/jdk-11.0.1/bin:$PATH` mean?

> $PATH is a system variable that indicates where the computer looks
  for commands you type.  It's a sequence of directories, separated
  by colons.  We've put the location of the latest version of Java
  at the front, so it looks there first.

Why are we working at the terminal, rather than in Eclipse?

> It's good to have one day of working in the terminal first.  (At least
  according to PM.)

Tell me about class names and file names.

> Java assumes that the class name will match the file name.  `First.java`
  contains `public class First`.

Will we have a lab writeup?

> Probably.  But I won't announce it until ten minutes to go.

What should my fizzbuzz function look like?

> `public static void fizzbuzz(int n) { ... }`

How do I iterate through an array?

> `for (int i = 0; i < arr.length; i++) { ... arr[i] ... }`

> `for val: arr { ... }`

Is there a `length` method in Java?

> Yes.  `arr.length()`.

Debrief
-------

Writeup:
**Email your code for `rev` to `csc207-01-grader@grinnell.edu` with a subject
of "CSC 207 Lab for class 2 (Your Names)".  Please substitute your names.**

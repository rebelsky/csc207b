---
title: Eboard 02  Programming in the small with Java
number: 2
section: eboards
held: 2019-01-25
link: true
---
CSC 207.02 2019S, Class 02:  Programming in the small with Java
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
* Mentor sessions Sunday evenings, time TBA.  Survey coming.

### Upcoming work

* [Assignment 1](../assignments/assignment01) due Thursday night.
    * Sam will announce pairings at the end of class.
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

### Other good things

* Women's basketball vs. St. Norbert, 5:30 p.m. Friday.
* Men's basketball vs. St. Norbert, 7:30 p.m. Friday.
* Women's basketball vs. Ripon, 1:00 p.m. Saturday.
* Men's basketball vs. Ripon, 3:00 p.m. Saturday.

### Friday PSA

* Someone cares about you, please think about taking care of yourself.
* Plan in advance.
* Consent is ABSOLUTELY, POSITIVELY necessary
* A gift to you: BAC card

### Questions

Can I switch sections?

> Sure.  Bring me an add-drop form.

What are the group work policies for this course?

> _Labs_: You can choose whether to work alone or have a randomly assigned
  partner.  (I would recommend the latter.)

> _Homework_: You get a randomly assigned partner.

> _Exams and quizzes_: On your own.

Tell me more about `for (type x: thing)`.

> Here's an example.

```java
import java.io.PrintWriter;

public class Example {
  public static void main(String[] args) {
    int[] stuff = { 5, 1, 2, 4, 8 };
    PrintWriter pen = new PrintWriter(System.out, true);
    for (int x: stuff) {
      pen.println(x  + " squared is " + x*x);
    } // for
  } // main
} // class Example
```

Output

```text
5 squared is 25
1 squared is 1
2 squared is 4
4 squared is 16
8 squared is 64
```

Why do you write `pen.flush()`?

> Output in Java is "buffered" for efficiency.  The computer writes to
  memory (a buffer) until a lot is written, and then dumps everything
  from memory to output.  What's in the buffer generally doesn't appear
  until (a) buffer is full or (b) you flush the buffer.  When you use
  `print` rather than `println`, it does not flush.

How do newlines work in Java?

> There are multiple options.  You can use `println`, which adds the newline
  and (in many cases) flushes the buffer.  You can use `\n`, like in C.  I
  don't know whether or not that flushes.

Why are you making us use `PrintWriter` objects rather than `System.out`?

> I find that `PrintWriter` objects lead to more object-like code.
  In particular, it makes it much easier to change your mind about where
  output goes.  

> PrintWriters are more general, you can make them write to stdout or to
  a file or to an Internet connection or ....  System.out only goes to
  stdout.

What is `System.out`? 

> A name for an object that is a lot like `stdout` in C.

Are there standard packages or classes that we use in Java, like we
used `<stdlib.h>` and `<stdio.h>` in C?

> Nope.  It depends on the program.

Lab prep
--------

* First, we will figure out how many people want to work alone and
  how many people would like a partner.
* Then, we'll assign folks to partners based on those preferences.
* After that, you'll do the lab.

Lab
---

How do I edit my `.bashrc`?

> `emacs ~/.bashrc`

What did you update since this morning?

> I updated the **fizzbuzz** problem.  Please reload the lab when you
  get to that point.

What's the writeup?

> Send the code for your `rev` method to `csc207-01-grader`.  
  (I don't need any of the other parts of that class.)

When's it due?

> Preferably before class on Monday.

Debrief
-------

```java
public class ArrayProblems {
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    int[] vals = { 2, 3, 4, 5, 10, 11, 12 };
    rev(vals);
    ...
  } // main

  public static void rev(int[] vals) {
    ...
  } // rev
} // class ArrayProblems
```

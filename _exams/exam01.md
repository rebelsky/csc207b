---
title: Exam 1
subtitle: ADTs, Algorithms, and Object-Oriented Design
submitting: |
  See the instructions below.
link: true
---
## Preliminaries

### Exam Format

This is a take-home examination. You may use any time or times you
deem appropriate to complete the exam, provided you return it to
me by the due date.

There are four problems on this examination. You must do your best
to answer all of them. The problems are not necessarily of equal
difficulty. Problems may include subproblems. If you complete four
problems correctly or mostly correctly, you will earn an A. If you
complete three problems correctly or mostly correctly, you will earn
a B. If you complete two problems correctly or mostly correctly,
you will earn a C. If you complete one problem correctly or mostly
correctly, you will earn a D. If you complete fewer than one problem
correctly or mostly correctly, you will earn an F. If you do not
attempt the examination, you will earn a 0. Partially correct
solutions may or may not earn you a partial grade, depending on the
discretion of the grader.

I rarely give makeup problems because my experience in past semesters
is that students spend a lot of effort on such problems but do not
significantly improve their grade.

Please read the entire examination before you begin.

I expect that someone who has mastered the material and works at a
moderate rate should have little trouble completing the exam in a
reasonable amount of time. In particular, this exam is likely to
take you about six hours, depending on how well you've learned the
topics and how fast you work. (When I do the problems, I will report
how long each one took me.) 

### Prologue

This examination has a required prologue that must be completed by 10:30 p.m. on Thursday, 7 March 2019.  The prologue is intended to help you get started thinking about the examination.  Failure to turn in the prologue by the deadline will result in a penalty of 1/3 letter grade on the exam.

Send a message to rebelsky@grinnell.edu entitled **CSC 207 Exam 1 Prologue (Your Name)**, substituting your name for "Your Name".  In that message, include the following.

A. An approach that you expect to help you succeed on the exam.
Some answers from the past: Review the outlines, Start early, Get
enough sleep, Work on it a little each day, Identify questions and
email them to Sam. You can use one of these or, better yet, you can
come up with one on your own.

B. For each problem, please include a short note about something that will help you solve the problem. Mostly, we want to see some evidence that you've thought about the problem. You might note some similar procedures you've written or problems you've solved in the past (e.g., in a lab or on a homework assignment). You might note procedures that you expect to use. You might sketch an algorithm. You might pose a question to yourself. (We won't necessarily read this in a timely fashion, so if you have questions for your instructor, you should ask by email or in person.) If, when looking at a problem, you think you already know the answer, you can feel free to write something short like “solved” or “trivial”.

C. A time estimate for how long it will take you to solve each problem.

### Epilogue

This examination has an epilogue that must be completed by the evening after the exam is due. The epilogue is intended to help you reflect carefully on the examination. The epilogue is required. Failure to turn in the epilogue will incur a penalty of 1/3 grade on the exam.

Send a message to rebelsky@grinnell.edu entitled **CSC 207 Exam 1 Epilogue (Your Name)**, substituting your name for "Your Name".  In that message, include the following.

A. For each problem, (a) indicate how long you spent on the problem, (b) describe the key ideas that the problem explored, (c) describe any challenges you faced on the problem, and (d) compare your estimate to your actual time spent and indicate what might account for the disparity.


B. Suggest an approach that will make you more successful on the next examination.

### Academic Honesty

This examination is open book, open notes, open mind, open computer, and open Web. However, it is closed person. That means you should not talk to other people about the exam. Other than as restricted by that limitation, you should feel free to use all reasonable resources available to you.

As always, you are expected to turn in your own work. If you find ideas in a book or on the Web, be sure to cite them appropriately. If you use code that you wrote for a previous lab or homework, cite that lab or homework and the other members of your group. If you use code that you found on the course Web site, be sure to cite that code. You need not cite the code provided in the body of the examination.

Although you may use the Web for this exam, you may not post your answers to this examination on the Web. (You certainly should not post them to GitHub unless you create a private repository for your exam.) And, in case it's not clear, you may not ask others (in person, via email, via IM, via IRC, by posting a “please help” message, or in any other way) to put answers on the Web.

Because different students may be taking the exam at different times, you are not permitted to discuss the exam with anyone until after I have returned it. If you must say something about the exam, you are allowed to say “This is among the hardest exams I have ever taken. If you don't start it early, you will have no chance of finishing the exam.” You may also summarize these policies. You may not tell other students which problems you've finished. You may not tell other students how long you've spent on the exam.

You must include both of the following statements on the cover sheet of the examination.

* I have neither received nor given inappropriate assistance on this examination.
* I am not aware of any other students who have given or received inappropriate assistance on this examination. 

Please sign and date each statement. Note that the statements must be true; if you are unable to sign either statement, please talk to me at your earliest convenience. You need not reveal the particulars of the dishonesty, simply that it happened. Note also that inappropriate assistance is assistance from (or to) anyone other than Professor Rebelsky (that's me).
Presenting Your Work

You must present your exam to me in two forms, physically and electronically. If you fail to turn in both versions, you are unlikely to receive credit for the examination.

*Physical copy*: You must write all of your answers using the computer, print them out, number the pages; put your name on the top of every page, write, sign, and date each of the academic honesty statements (provided you are able to do so); and hand me the printed copy or put it under my office door. If you fail to name and number the printed pages, you may suffer a penalty. If you fail to turn in a legible version of the exam, you are also likely to suffer some sort of penalty.

*Electronic copy*: You must also submit an electronic copy of your exam. You should create the electronic version by making a tarball of any relevant code and emailing me the tarball. Here are the steps for making a tarball.

* Remove any cruft (needless files) from your directory structure. I don't want to see `.class` files, editor backups, or anything similar.
* Switch to the parent directory of your exam directory. (Note that your exam directory should be named `username-exam01`), with your username replacing
`username`.
* Issue the command "`tar cvzf username-exam01.tgz username-exam01`".  Once again, use your username rather than `username`.
* Make sure that the tar file contains the appropriate contents using the command "`tar tf username-exam01.tgz`". For example, if I were to check my tarball, I might see something like the following.

```shell
$ tar tf rebelsky-exam01.tgz
rebelsky-exam01/
rebelsky-exam01/Exam-1-Problems-1-2/
rebelsky-exam01/Exam-1-Problems-3-4/
rebelsky-exam01/Exam-1-Problems-3-4/.classpath
rebelsky-exam01/Exam-1-Problems-3-4/.project
rebelsky-exam01/Exam-1-Problems-3-4/bin/
rebelsky-exam01/Exam-1-Problems-3-4/src/
rebelsky-exam01/Exam-1-Problems-3-4/src/ArrayBasedQueue.java
rebelsky-exam01/Exam-1-Problems-3-4/src/ArrayBasedQueueExpt.java
rebelsky-exam01/Exam-1-Problems-3-4/src/LinearStructure.java
rebelsky-exam01/Exam-1-Problems-3-4/src/Node.java
rebelsky-exam01/Exam-1-Problems-3-4/src/PriorityQueue.java
rebelsky-exam01/Exam-1-Problems-3-4/src/Queue.java
rebelsky-exam01/Exam-1-Problems-3-4/src/ReportingLinearStructure.java
rebelsky-exam01/Exam-1-Problems-3-4/src/SortedLinkedPriorityQueue.java
rebelsky-exam01/Exam-1-Problems-1-2/.classpath
rebelsky-exam01/Exam-1-Problems-1-2/.project
rebelsky-exam01/Exam-1-Problems-1-2/bin/
rebelsky-exam01/Exam-1-Problems-1-2/src/
rebelsky-exam01/Exam-1-Problems-1-2/src/Average.java
rebelsky-exam01/Exam-1-Problems-1-2/src/AverageTest.java
rebelsky-exam01/Exam-1-Problems-1-2/src/MathUtils.java
```

*Code*: In many problems, I ask you to write code. Unless I specify
otherwise in a problem, you should write working code and include
examples that show that you've tested, or at least experimented
with, the code. You should do your best to format that code to the
class formatting standards.

*Documentation*: You should document classes, interfaces, fields,
and methods using Javadoc-style comments. You should specify
preconditions and postconditions for each method.

*Care*: Just as you should be careful and precise when you write
code and documentation, so should you be careful and precise when
you write prose. Please check your spelling and grammar. Since I
should be equally careful, the whole class will likely receive one point
of extra credit for each error in spelling or grammar you identify
on this exam. I will limit that form of extra credit to five points.

*Partial credit*: I may give partial credit for partially correct answers. I am best able to give such partial credit if you include a clear set of work that shows how you derived your answer. You ensure the best possible grade for yourself by clearly indicating what part of your answer is work and what part is your final answer.

### Getting Help

I may not be available at the time you take the exam. If you feel
that a question is badly worded or impossible to answer, note the
issue you have observed and attempt to reword the question in such
a way that it is answerable. You should also feel free to send me
electronic mail at any time of day.

I will also reserve time at the start of each class before the exam
is due to discuss any general questions you have on the exam.

### Obtaining the exam code

You can download [a tarball of the starter code for the 
examination](../files/username-exam01.tgz).  You can unpack
that file with "`tar xvzf username-exam01.tgz`".

## Problems

### Problem 1: Extended averages

Your classmate, Ava Ridge, has decided to write a general procedure
to average arrays of longs.  She's started with the "obvious" solution.

```java
  public static long average(long[] values) {
    long sum = 0;
    for (long val :values) {
      sum += val;
    } // for
    return sum/values.length;
  } // average(long[])
```

Unfortunately, your classmates Tessa Ter and Jay Unit have found some
flaws in her code when writing tests.  Here's one failed test.

```java
  void testSimpleOverflow() {
    long val = (Long.MAX_VALUE / 2) + 3;
    assertEquals(val, MathUtils.average(new long[] { val, val }));
    assertEquals(val, MathUtils.average(new long[] { val, val, val }));
  }
```

They know that their test suite is incomplete, but aren't sure what
other tests to write.

Your test suite should certainly include tests of "corner cases" -
those outliers that many folks will miss. In addition, portions of
your test suite must be systematically constructed. That is, you
must use loops to build an appropriate variety of arrays of different
sizes that are likely to stress the system.

If at all possible, make sure that your test suite indicates the
input for which `average` fails (assuming that it finds an input for
which `average` fails).

I am likely to run your test suite against a variety of implementations
of `average`, some of which are correct and some of which are incorrect.
You will get no credit for this problem if you identify a correct
`average` method as incorrect. You will get partial credit if you
miss errors in erroneous implementations. You will get extra credit
if you find an error in an implementation that I believe is correct.

Note that you do not need to check whether or not `average` correctly
throws an exception when given null or an empty array as input.
Your primary goal is to check that the `average` method correctly
computes the average when given good inputs.

### Problem 2: Extended averages, continued

These tests raise a host of errors.  Unfortunately, Ava is unsure
of what's wrong.  Your instructor, Hugh DeWitt, tells you to correct
the implementation of `average` so that it deals with overflow and
underflow..  Your resulting method should use O(1) additional space
and O(n) time.  You may not convert the `long` values to another
type, such as `BigInteger` or `double`.

### Problem 3: Linked priority queues

Your fellow students, Soren and Theodore, have decided that they would
like to implement priority queues.  They've chosen what they consider
a reasonable implementation strategy: They are going to store the
elements in a linked structure, and keep the elements in "sorted
order", from smallest to largest.

Your colleague, Ida Rader, reminds them that they have a
responsibility to create an iterator that implements not only
`next()` and `hasNext()`, but also `remove()`.

Unfortunately, Soren and Theodore are better at planning than they
are at coding.  Once again, Hugh DeWitt tells you to finish their
work.  Do so.  That is, implement a sorted, linked, priority queue,
including an iterator which you should build as an anonymous inner class.

### Problem 4: Expandable array-based queues

We often create the initial versions of our array-based linear
structures using a fixed-size array.  We did so with our original
array-based queues.  Your classmates, Exene and Panda, suggest
that it is better to have the array grow once it fills.

Hugh DeWitt once again lives up to his name and says "You do it".

This time through, Ida Rader has filled in some of the iterator,
particularly the `next()` and `hasNext()` methods.  However, as
Remy O. Ve mentions, Ida has left the `remove` method unimplemented.
Make sure to finish that method, too.

## Questions and Answers

_Here you will find the questions students ask along the way and
any answers I provide to those questions._

### Problem 1

Why do you want us to write a loop in our testing?
  : Say you wanted to generate 100 different arrays as part of some
    test.  Wouldn't you be better off writing those with a loop
    than manually?

### Problem 2

Can we approximate the result?
  : No.  The only approximate allowed is that given by integer division.

What do you mean by O(1) extra space? _[2019-03-06, 12:30 p.m.]_
  : You can create as many variables as you want as long as the number
    of variables is independent of the size of the array.

## Errata

_Here you will find corrections to the examination, each of which
earns everyone a modicum of extra credit (until a cap of five points
is reached)._

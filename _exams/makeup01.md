---
title: Makeup Exam 1
subtitle: ADTs, Algorithms, and Object-Oriented Design
submitting: |
  See the instructions below.
link: true
current: true
---
**This examination is now released.  You may not discuss it with anyone
(other than Samuel A. Rebelsky).**

## Preliminaries

_Some students did not seem to understand the level of detail and
correctness I expect on my exams.  So, in spite of my general "no
makeups" policy, I am giving a makeup exam for exam 1.  Each problem
replaces the corresponding problem number on exam 1.  That is,
problem 1 on the makeup will replace problem 1 on exam 1, problem 
2 on the makeup will replace problem 2 on exam 1, and so on and
so forth.  You may choose to do as many or as few problems as you
would like.  In each case, I will take the higher of the original
grade for that problem number and the replacement grade._

_Any code that deviates significantly from the Google Java Style
Guidelines (e.g., that uses a tab or four spaces as the indent or
that uses a capital letter to name a method or variable) will receive
a zero.  Any utility code that includes a print statement will
receive a zero._

_My grading of the makeup problems will likely be more cursory than my
grading of the original exam.  In particular, I am likely to run unit
tests and base my grade primarily on the results of those tests._

### Exam Format

This is a take-home examination. You may use any time or times you
deem appropriate to complete the exam, provided you return it to
me by the due date.

This is a makeup examination.  There will not be a further makeup for
missed problems on the examination.

Please read the entire examination before you begin.

I expect that someone who has mastered the material and works at a
moderate rate should have little trouble completing the exam in a
reasonable amount of time. In particular, this exam is likely to
take you about six hours (plus time for the prologue and epilogue),
depending on how well you've learned the topics and how fast you
work. (When I do the problems, I will report how long each one took
me.)

### Prologue

This examination has a required prologue that must be completed by 10:30 p.m. on Friday, 5 April 2019.  The prologue is intended to help you get started thinking about the examination.  Failure to turn in the prologue by the deadline will result in a zero on the makeup.

Send a message to rebelsky@grinnell.edu entitled **CSC 207 Makeup 1 Prologue (Your Name)**, substituting your name for "Your Name".  In that message, include the following.

A. An approach that you expect to help you succeed on the exam.
Some answers from the past: Review the outlines, Start early, Get
enough sleep, Work on it a little each day, Identify questions and
email them to Sam. You can use one of these or, better yet, you can
come up with one on your own.

B. For each problem, please include a short note about something that will help you solve the problem. Mostly, we want to see some evidence that you've thought about the problem. You might note some similar procedures you've written or problems you've solved in the past (e.g., in a lab or on a homework assignment). You might note procedures that you expect to use. You might sketch an algorithm. You might pose a question to yourself. (We won't necessarily read this in a timely fashion, so if you have questions for your instructor, you should ask by email or in person.) If, when looking at a problem, you think you already know the answer, you can feel free to write something short like “solved” or “trivial”.
If you do not plan to solve a particular problem, you need not include such a note.  However, if you have not written about a problem in the prologue, you will not receive credit for it.

C. A time estimate for how long it will take you to solve each problem.

### Epilogue

This examination has an epilogue that must be completed by 5pm on Friday, 12 April 2019. The epilogue is intended to help you reflect carefully on the examination. The epilogue is required. Failure to turn in the epilogue will result in a zero on the makeup.

Send a message to rebelsky@grinnell.edu entitled **CSC 207 Makeup 1 Epilogue (Your Name)**, substituting your name for "Your Name".  In that message, include the following.

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

*Physical copy*: You must write all of your answers using the computer, print them out (you need only print the files you've changed), put them in order by problem, number the pages; put your name on the top of every page, write, sign, and date each of the academic honesty statements (provided you are able to do so); and hand me the printed copy or put it under my office door. If you fail to name and number the printed pages, you may suffer a penalty. If you fail to turn in a legible version of the exam, you are also likely to suffer some sort of penalty.

*Electronic copy*: You must also submit an electronic copy of your exam. You should create the electronic version by making a tarball of any relevant code and emailing me the tarball. Here are the steps for making a tarball.

* Within Eclipse, rename the project by replacing the "Your Name" in
  "Makeup 1 (Your Name)" to your name.
* Switch to the parent directory of your exam directory. 
* Rename the directory containing your makeup examination from 
  `username-makeup-01` by replacing `username` with your username.
  For example, mine would be called `rebelsky-makeup-01`.
* Issue the command "`tar cvzf username-makeup-01.tgz username-makeup-01`",
  except you should replace `username` with your username.
* Make sure that the tar file contains the appropriate contents using 
  the command "`tar tf username-makeup-01.tgz`" (once again, substitute
  your own username). For example, if I were to check my tarball, I might 
  see something like the following.  (Note: To make things easier, I have
  allowed you to include `.class` files.)

```shell
$ tar tf rebelsky-makeup01.tgz
rebelsky-makeup-01/
rebelsky-makeup-01/.classpath
rebelsky-makeup-01/.project
rebelsky-makeup-01/bin/
rebelsky-makeup-01/src/
rebelsky-makeup-01/src/problem1/
rebelsky-makeup-01/src/problem2/
rebelsky-makeup-01/src/problem3/
rebelsky-makeup-01/src/problem4/
rebelsky-makeup-01/src/utils/
rebelsky-makeup-01/src/utils/TestUtils.java
rebelsky-makeup-01/src/problem4/Directory.java
rebelsky-makeup-01/src/problem4/Entry.java
rebelsky-makeup-01/src/problem3/LinkedStack.java
rebelsky-makeup-01/src/problem3/Node.java
rebelsky-makeup-01/src/problem2/Experiment.java
rebelsky-makeup-01/src/problem2/QuicksortDNF.java
rebelsky-makeup-01/src/problem1/Example1.java
rebelsky-makeup-01/src/problem1/Example2.java
rebelsky-makeup-01/src/problem1/FilteredIterator.java
rebelsky-makeup-01/bin/problem1/
rebelsky-makeup-01/bin/problem2/
rebelsky-makeup-01/bin/problem3/
rebelsky-makeup-01/bin/problem4/
rebelsky-makeup-01/bin/utils/
rebelsky-makeup-01/bin/utils/TestUtils.class
rebelsky-makeup-01/bin/problem4/Directory$1.class
rebelsky-makeup-01/bin/problem4/Directory$2.class
rebelsky-makeup-01/bin/problem4/Directory.class
rebelsky-makeup-01/bin/problem4/Entry.class
rebelsky-makeup-01/bin/problem3/LinkedStack.class
rebelsky-makeup-01/bin/problem3/Node.class
rebelsky-makeup-01/bin/problem2/Experiment.class
rebelsky-makeup-01/bin/problem2/QuicksortDNF.class
rebelsky-makeup-01/bin/problem1/Example1.class
rebelsky-makeup-01/bin/problem1/Example2.class
rebelsky-makeup-01/bin/problem1/FilteredIterator.class
```

*Code*: In many problems, I ask you to write code. Unless I specify
otherwise in a problem, you should write working code and include
examples that show that you've tested, or at least experimented
with, the code. 

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

### Unit tests

I will distribute unit tests on Saturday, after I have received the
prologues.  You will have the option of installing them in your
project.

### Obtaining the exam code

You can download [a tarball of the starter code for the 
examination](../files/username-makeup-01.tgz).  You can unpack
that file with "`tar xvzf username-makeup-01.tgz`".

Once you have downloaded the code, you should import that top-level
directory into Eclipse.  You should see a project named "Makeup 1
(Your Name)".

## Problems

### Problem 1: Filtered iterators

_This problem is a makeup for the tests of the `average` method.  However,
the content has no conceptual relation to that previous problem._

Your classmates, Ida, Rae, Tor, Phil, and Ted, have enjoyed many
aspects of object oriented design, but also like to think functionally.
They've started to look at approaches to working with iterators.
Recalling the introduction to lambdas, they start to think about
iterators and predicates, and ways to print out all the values for
which a predicate holds.  For example,

```java
package problem1;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * An example of iteration and filtering with predicates.
 */
public class Example1 {
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    Integer[] values = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8};
    Iterator<Integer> it = Arrays.asList(values).iterator();
    Predicate<Integer> even = (i) -> (i % 2) == 0;

    while (it.hasNext()) {
      Integer i = it.next();
      if (even.test(i)) {
        pen.println(i);
      } // if
    } // while

    pen.close();
  } // main(String[])
} // class Example1
```

As in the lambda tutorial, they've decided they want to think a
bit more about the design, this time using a bit more of an
object-oriented design approach.  In particular, Ida, Rae, Tor,
Phil, and Ted suggest that we create a class that they
call `FilteredIterator`, which incorporates the predicate
more directly in the iterator.

```java
package problem1;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;

/**
 * An example of iteration and filtering with predicates.
 */
public class Example2 {
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    Integer[] values = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8};
    Iterator<Integer> fit =  new FilteredIterator<Integer>(
        Arrays.asList(values).iterator(), (i) -> (i % 2) == 1);

    while (fit.hasNext()) {
      Integer i = fit.next();
      pen.println(i);
    } // while

    pen.close();
  } // main(String[])

} // class Example2
```

Unfortunately, they are struggling to figure out the details.  They've
created a template, but need someone else to fill in the details.  Do
so.

```java
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Iterators that filter elements that meet a predicate.
 * 
 * @author Samuel A. Rebelsky
 */
public class FilteredIterator<T> implements Iterator<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The underlying iterator.
   */
  Iterator<T> iterator;

  /**
   * The predicate used to filter the iterator.
   */
  Predicate<T> pred;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create an iterator that filters the elements of iter using
   * pred.
   */
  public FilteredIterator(Iterator<T> iter, Predicate<T> pred) {
    this.iterator = iter;
    this.pred = pred;
  } // FilteredIterator(Iterator<T>, Predicate<T>)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the next value for which the predicate holds.
   *
   * @pre this.hasNext()
   * @post pred.test(result) hold
   */
  public T next() {
    return this.iterator.next();
  } // next()

  /**
   * Determine if there are any more values for which the predicate
   * holds.
   */
  public boolean hasNext() {
    return this.iterator.hasNext();
  } // hasNext()

  /**
   * Remove the most recent value returned by next.  If the preconditions
   * are not met, throws an `IllegalStateException`.
   *
   * @pre There has been at least one call to this.next()
   * @pre There have been no calls to this.remove() or this.hasNext()
   *      since the last call to this.next()
   */
  public void remove() {
    throw new IllegalStateException("Not yet implemented.");
  } // remove()
} // FilteredIterator
```

As you can tell, they've made a slight, but important, modification
to the semantics of `remove`. They've decided that `remove` can
throw an `IllegalStateException` if (a) the `next` method has not
yet been called, (b) the `remove` method has already been called
after the last call to the `next` method, or (c) the `hasNext`
method has been called after the last call to the `next` method.
(There's a subtle reason to add the third case.)

Note that you do not need to explicitly indicate that `remove` might
throw an `IllegalStateException` because those are a form of runtime
exception.  Not also that you do not need to worry about throwing
a `ConcurrentModificationException`.

### Problem 2: Dutch national Quicksort

_This problem is a makeup for the `average` method.  The primary relationship
of this problem to that problem are that both work with arrays._

Quinn and Sorrell are a bit frustrated by our current implementation
of Quicksort. They say “Quicksort seems to do a lot of extra work
when the pivot appears multiple times in the array. Why can't we
just group all of the elements equal to the pivot together, and
only recurse on the values that are strictly smaller and strictly
larger?”

Duff, Nat, and Fran say “That seems to be a task for the Dutch
National Flag algorithm, which can partition an array into three
sections: red, white, and blue, or, in this case, smaller, equal,
and larger.”

Write a version of Quicksort that uses this approach. That is, it
should partition the array into three parts and only recurse on the
left and right subparts.

You may find it difficult to make the partition process a
separate method, since, in addition to rearranging the items in the
subarray, it now has to "return" two values: the lower bound and upper
bound of the middle part of the subarray.   That means that you should
move the partitioning process into the middle of Quicksort. 

Note: If you've forgotten, we sketched a version of the Dutch National 
Flag algorithm in [class 20](../eboards/eboard20).

### Problem 3: Linked stacks

_This problem is a makeup for the problem on sorted linked priority
queues.  Like that problem, it asks you to deal with linked structures,
including iteration of those structures.  It also asks you to do a
bit more with iteration state._

Linus and Stacy have enjoyed our work with linked structures, and
think we should continue that work.  "We've done linked queues and
linked priority queues, but it looks like we should implement stacks,
too.  That will give us a chance to play more with iterators." Your
instructor, Hugh DeWitt, suggests that Linus and Stacy have already
one enough with linked structures, and perhaps it should be your
turn.  He asks that you implement linked stacks, which should have
`push`, `pop`, `top`, `isEmpty`, `isFull`, and `iterator` methods.
Stack iterators should iterate the stack from top to bottom.  Your
iterators must support the `next`, `hasNext`, and `remove` methods.

Ann, O, and Ying pipe up saying "We haven't followed the full
specifications for iterators.  Aren't iterator methods supposed to
throw more than just `IllegalStateException`s in certain circumstances?"
Hugh DeWitt agrees, and says that you should implement the more
general expected behavior of iterators.

In particular, in addition to having your `remove` method throw an
`IllegalStateException` if there has not been an appropriate prior
call to `next`, you should also ensure that all of the iterator
methods throw a `ConcurrentModificationException` if the stack has
been modified by anything other than their iterator since the
iterator was created.  For example, if we create an iterator for a
stack and then call `push` or `pop` on the underlying stack, requests
to `next` or `hasNext` or `remove` in that iterator  should throw
a `ConcurrentModificationException`.  Similarly, if we create two
iterators for the same stack and call `remove` in one of them, that
iterator should continue to work as expected, but the other iterator
should throw a `ConcurrentModificationException`.

Fortunately, Hal and Phil have an idea for detecting modification.
They suggest that a simple strategy is to have a counter associated
with the stack and with each iterator.  When you create an iterator,
you copy the counter from the stack.  Each time you modify the
stack, you increment the stack's counter.  If you modify the stack
with an iterator, you also increment the iterator's counter.  When
any method in the iterator is called, you compare the counter in
the iterator to the counter in the stack and throw an exception if
they do not match.

### Problem 4: Array-based directories

_This problem is a makeup for the problem on array-based queues.
Like that problem, it asks you to deal with iteration in an array-based
structure.  The order of elements is a bit different in this problem,
and you do not need to handle wraparound.  However, you will have
to worry about ordering and searching._

Dora, Ric, and Tori have decided that they want to to implement a
data structure (or perhaps an ADT) that associates screen names and
real names.  They note that "although each person may have multiple
screen names, there is only one real name per screen name".

After consulting with the rest of the class, they decide that
their structure, which they've decided to call a Directory, should
have the following methods.

* `set(String screen, String real)` - Sets the real name associated
  with a screen name.  If the screen name is already in the directory,
  updates it.  If the screen name is not already in the directory,
  it adds it. 
* `get(String screen)` - Gets the real name given a screen name.
  Throws an exception if their is no real name associated with
  the screen name.
* `screenNames()` - returns an iterator for the screen names that
  presents them in alphabetical order.
* `realNames()` - returns an iterator for the real names with
  an unspecified order.

Soren and Ted, think that we should store directory entries 
(basically, screen name/real name pairs) alphabetically by
screen name.  That way, it's easy to iterate in alphabetical order.
After their experience with sorted linked priority queues, they
suggest that we might be better off using arrays, particularly
because we can then use binary search for `get`.

Implement the `Directory` structure as a sorted array.  Your `get`
method should be O(log<sub>2</sub>_n_).  Your `set` method should be
O(log<sub>2</sub>_n_) if the screen name is already in the 
directory but can be O(_n_) if the screen name is not in the 
directory.  If the array is not big enough, you will need to
expand the array.  When you expand, you should double the size.

Fortunately, Ann, O, and Ying are not in class during this discussion,
so no one suggests that you need to worry about 
`ConcurrentModificationException`s.  However, you do still need to
worry about `IllegalStateException`s.

## Questions and Answers

_Here you will find the questions students ask along the way and
any answers I provide to those questions._

### Problem 1

### Problem 2

### Problem 3

### Problem 4

## Errata

_Here you will find corrections to the examination, each of which
earns everyone a modicum of extra credit (until a cap of five points
is reached)._


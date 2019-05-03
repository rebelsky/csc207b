---
title: Exam 2
subtitle: Trees and other linked structures
submitting: |
  See the instructions below.
link: true
current: true
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

This examination has a required prologue that must be completed by 10:30 p.m. on Monday, 29 April 2019.  The prologue is intended to help you get started thinking about the examination.  Failure to turn in the prologue by the deadline will result in a penalty of 1/3 letter grade on the exam.

Send a message to rebelsky@grinnell.edu entitled **CSC 207 Exam 2 Prologue (Your Name)**, substituting your name for "Your Name".  In that message, include the following.

A. An approach that you expect to help you succeed on the exam.
Some answers from the past: Review the outlines, Start early, Get
enough sleep, Work on it a little each day, Identify questions and
email them to Sam. You can use one of these or, better yet, you can
come up with one on your own.

B. For each problem, please include a short note about something that will help you solve the problem. Mostly, we want to see some evidence that you've thought about the problem. You might note some similar procedures you've written or problems you've solved in the past (e.g., in a lab or on a homework assignment). You might note procedures that you expect to use. You might sketch an algorithm. You might pose a question to yourself. (We won't necessarily read this in a timely fashion, so if you have questions for your instructor, you should ask by email or in person.) If, when looking at a problem, you think you already know the answer, you can feel free to write something short like “solved” or “trivial”.

C. A time estimate for how long it will take you to solve each problem.

### Epilogue

This examination has an epilogue that must be completed by the evening after the exam is due. The epilogue is intended to help you reflect carefully on the examination. The epilogue is required. Failure to turn in the epilogue will incur a penalty of 1/3 grade on the exam.

Send a message to rebelsky@grinnell.edu entitled **CSC 207 Exam 2 Epilogue (Your Name)**, substituting your name for "Your Name".  In that message, include the following.

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

*Electronic copy*: You must also submit an electronic copy of your
exam. You should create the electronic version by downloading the zip
file from your repository for this exam.

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

*Partial credit*: I may give partial credit for partially correct
answers. I am best able to give such partial credit if you include
a clear set of work that shows how you derived your answer. You
ensure the best possible grade for yourself by clearly indicating
what part of your answer is work and what part is your final answer.

### Getting Help

I may not be available at the time you take the exam. If you feel
that a question is badly worded or impossible to answer, note the
issue you have observed and attempt to reword the question in such
a way that it is answerable. You should also feel free to send me
electronic mail at any time of day.

I will also reserve time at the start of each class before the exam
is due to discuss any general questions you have on the exam.

### Obtaining the exam code

_It is a bit complicated to make a private fork of a public repo, but
you should be able to get it done._

1. Create a new private repository at <https://github.com/Username/username-exam-02>.  You should, of
course, substitute your GitHub username for `Username` and your
Grinnell username for `username`.

2. Share that repository with me.

3. In the terminal, make a bare clone of the exam repository with
the following command.

        git clone --bare https://github.com/Grinnell-CSC207/exam-02-2019S.git

    That should create a new directory named `exam-02-2019S.git`.

4. In the terminal, type the following incantation to copy code from
the cloned repo back to your private repo.

        cd exam-02-2019S.git
        git push --mirror https://github.com/Username/username-exam-02.git

5. The cloned repo is no longer necessary.  Remove it.

        cd ..
        rm -rf exam-02-2019S.git

6. Clone your new repository.

7. Try pulling from the master (see the instructions below).  If you
get an error, contact me.

8. Once you have cloned the repository, you should import that top-level
directory into Eclipse.  You should see a project named "Exam 2
(Your Name)".  Change the "Your Name" to your name.  Commit and
push the change.

### Updating the code

I expect to make a few additional changes to the code and will send
you a notification when I do so.  You can pull the changes into your
fork as folows.

1. In the terminal, navigate to the directory for the project.

2. Type the following.

        git pull https://github.com/Grinnell-CSC207/exam-02-2019S

3. You may see conflicts.  If so, you must resolve them.  That is,
edit each file with conflicts, choosing which version of the code
you want, and then use `git add` to add it to the list of things to
commit.

4. Commit the merge.  If there were no conflicts, you will likely
move automatically to this step.

## Problems

### Problem 1: Immutable binary search trees

One reason to use binary search trees rather than hash tables is
that it's much immutable binary search trees are traditionally more
efficient than are hash tables.  In particular, if you add a new
key/value pair to a hash table or remove a key from an immutable
hash table, you'll need to copy all the other values to a new table,
which is an O(n) operation.

In contrast, for insertion or removal in an immutable binary tree,
you only need to rebuild the nodes along the path to the changed
node, since you can share much of the structure of the original
tree.

**Implement immutable binary search trees.**

*Hint*: You should be able to use variants of the recursive `set`
and `remove` methods you wrote, building new nodes along the way
instead of setting the left and right children.

### Problem 2: Circular doubly-linked lists

One of the claimed advantages of circular doubly-linked lists is
that you don't need to worry about the case in which the `next` or
`prev` reference is null.  That suggests that (a) you should be
able to use only one reference for each iterator and (b) you can
avoid a host of `if` statements.

**Finish the associated implementation of such lists.**

You should be able to do without any new conditionals.  (That is,
all the necessary conditionals should be in the file.)

### Problem 3: Tries

As you may recall, a *trie* is a kind of tree used to implement
maps in which the key is a string.  In a trie, each node will have
a child for each of the possible letters.  (In our implementation,
we'll use an array of size 26; most real implementations use a
more efficient strategy.)  You follow the letters of your key down
the child links until either (a) you fall off the end of the tree
or (b) you reach the end of the string and the node you've reached
has a key and a value.

Consider the following trie, which is built from the keys "a", "an",
"ant", "art", "i", "tar", "tea", "tent, and "test"".  We've shown
an uppercase version of the key to represent the end of a key and
an asterisk to represent "not a key".

```text
                    __*__
                  a/  |i \t
                  /   |   \
                 A    I    *__
               n/ \r     a/   \e
               /   \     /     \
              AN    *   *     __*__
            t/      |t  |r  a/  |n \s
            /       |   |   /   |   \
          ANT      ART TAR TEA  *    *
                                |t    \t
                                |      \
                                TENT   TEST
```

Suppose we look up "art".  We follow the edge labeled
`a` from the top-level node, then the edge labeled `r`, then
the edge labeled `t`.  We're out of the string, and we're at a node
that's marked as having a key, so we found "art".

Supppose we look up "ten".  We follow the edge labeled
"t", then the edge labeled, "e", then the edge labeled "n".  We've
run out of string, but our node is not marked as containing the
string.  We've determined that "ten" is not a valid key.

Suppose we look up "tear".  We follow the edge labeled
"t", then the edge labeled "e", then the edge labled "a".  We have
a letter left in the string, but there are no edges left in the
tree (alternately, the edge labeled "r" leads to null).  We've
determined that "tear" is not a valid key.

Suppose we look up "ton".  We follow the edge labeled
"t", then discover that there's no edge labeled "o" (or the edge
labeled "o" leads to null).  We've determined that "ton" is not
a valid key.

Suppose we look up "an".  We follow the edge labeled "a", then
the edge labeled "n".  We've now run out of string.  Although there
are still edges out of the node, the node is marked as having a
key of "an", and we've found the key.

**Finish the implementation of tries.**

### Problem 4: Iterating hash tables

When we implemented chained hash tables, we did not get around to
implementing the primary iterator.

**Implement that iterator.**

You should make sure to implement `hasNext`, `next`, and `remove`.
You should make sure to throw `ConcurrentModificationException`s
if there is concurrent modification.

## Code updates

8:30 a.m., Friday, 26 April 2019
  : Relased first version of code.

5:30 p.m., Friday, 26 April 2019
  : Released corrected version of Trie dump code.

11:59 p.m., Friday, 26 April 2019
  : Released some new tests and a vareity of minor updates.

9:45 p.m., Sunday, 28 April 2019
  : Updated the tests for Tries.
  : Added tests for Circular DLLs.

9:00 p.m., Monday, 29 April 2019
  : Re-updated the tests for tries.

## Questions and Answers

_Here you will find the questions students ask along the way and
any answers I provide to those questions._

### Problem 1

_I assume we are supposed to call setHelper and removeHelper in the set and remove methods which currently just return null? Is this the only thing we have to do beside implement these helpers?_

> You probably need to take the results of those helpers and wrap them in a new tree structure.

_Can you explain what you mean by "you only need to change the nodes along
the path to the new node"_

> Consider the following tree, in which I've added a "node number". (The numbers have no particular meaning; they are just a way of uniquely identifying each node.)

>```text
     03:F
      /   \
  04:C   21:H
    / \     \
23:A 18:D 17:M
            / \
        01:J  18:N
```

> Suppose I add a "B" to this tree.  That will require creating a new node
to replace node 23.

>```text
32:A
    \
  31:B
```

> Now, node 04 needs to be replaced by one that has node 32 as its left
child.  (If we simply changed the link, we would have mutated the tree.)
However, the new node can still use the same right subtree.

>```text
  33:C
    / \
32:A 18:D
    \
  31:B
```

> Finally, node 3 needs to be replaced by one that has node 33 as its left
child.  However, that new node can still use the right subtree rooted
at node 21.

>```text
     34:F
      /   \
  33:C   21:H
    / \     \
32:A 18:D 17:M
    \       / \
  31:B  01:J  18:N
```

> We've changed the nodes above B (the new node) in the tree, but not
any of the other nodes.

> Similarly, suppose we decided to remove the "J" from the tree.
We'll need to build a replacement for node 17 that does not have a
left child, but retains node 17's right child.


>```text
          41:M
              \
              18:N
```

> Now we need to build a replacement for node 21 that has that new
node as its right child.  (If node 21 has a left child, we'd use
that as the left child of the new node.

>```text
         42:H
            \
          41:M
              \
              18:N
```

> Finally, we need to build a replacement for node 34 at the root that
uses the new node as its right child.  However, the new root retains the
left subtree.

>```text
     43:F
      /   \
  33:C   42:H
    / \     \
32:A 18:D 41:M
    \         \
  31:B        18:N
```

> Once again, we've only needed to build new nodes along the path to the
node we've changed (in this case, removed).

> If we remove within the tree, we'll have a slightly more complicated
set of steps, but they will be of similar cost.  (In that case, we
will also have to traverse a bit further, but only along one path.)

_Speaking of that, how do we remove an interior node in the tree?_

> Do what we did in the remove method on the lab.  (Either (a) put the right subtree at the right end of the left subtree, (b) put the left subtree at the left end of the right subtree; (c) promote the rightmost value in the left subtree; or (d) promote the leftmost value in the right subtree.)

### Problem 2

_Do I have to deal with concurrent modifications in this problem?_

> Nope.

_Does `add` add before or after the cursor?_

> You should know the answer from having done [the first lab on lists](../labs/array-based-lists).  I believe question 1c was about just that issue.  But you can also read [the documentation on list iterators](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ListIterator.html) or conduct an experiment using `ArrayList` objects.

_Will you write some unit tests?_

> If I can spare the time.

_If I have a list 1, 2, 3, I call next and get 1, call next and get 2, then should previous return 1 or 2?_

> What does the documentation on `ListIterator` say?  Alternately, what
happens when you use `ArrayList` objects?

### Problem 3

_To clarify, the diagram shows each node having two edges/next nodes, but in reality each node has 27 edges?_

> Yes, that's correct.  I've added some extra edges to make that clear.

_What is the difference between the key and the value of a trie node? The key is the word stored there, so what is the value?_

> We store both the key and the value there.  It's the key/value we'd have in a map.  (I just draw the key in the diagram.)

_If key is not in the trie when get or remove are called, should I throw an exception or return null?_

> What does the documentation for `SimpleMap` say?

### Problem 4

_Do you have a recommended strategy for detecting concurrenct modifications?_

> In the main structure, keep a counter of the number of modifications.

> Each time you create an iterator, copy that counter.

> Each time you modify in the struture, update the counter in the structure.

> If the iterator's counter does not match the structure's, we have
  identified a concurrent modification, and should throw an exception.

> Each time you modify in an iterator, update the counter in both the
  structure and the iterator.

> Note: This approach is unlikely to be thread safe.

## Acknowledgements

Some of the words in the sample trie were taken from [the Wikipedia
article on tries](https://en.wikipedia.org/wiki/Trie).

The instructions for making a private "fork" of a repo are based on those
at <https://help.github.com/en/articles/duplicating-a-repository>.

The instructions for pulling from the upstream repo were based on
those found at <https://help.github.com/en/articles/merging-an-upstream-repository-into-your-fork>.



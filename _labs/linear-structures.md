---
title: Linear structures
summary: | 
  In this laboratory, you will have an opportunity to ground your
  understanding of linear structures, including both array-based
  and linked structures.
repo: <https://github.com/Grinnell-CSC207/linear-structures>
todo:
  - Update the repo.
  - Come up with some linked queue questions.
  - Consider how to address iterators.
---

Preparation
-----------

a. Open [the reading on linear structures](../readings/linear-structures),
[the reading on stacks](../readings/stacks), and [the reading on
queues](../readings/queues) in separate tabs.

b. Fork and clone the repo.

Exercises
---------

### Exercise 1: Basic experiments

Read through `LSExpt.java` and `LinkedStackExpt.java`.  Summarize
what the stack should look like at each step.  (A piece of paper
might help.)  Note that the `info` method will print information
on the stack (is it empty? is it full? what elements are in the
stack) and the `clear` method will repeatedly call `get` until the
stack is empty.

Run `LinkedStackExpt` and see if you get the output that
you expect.

### Exercise 2: A wrapper class

Skim through `ReportingLinearStructure.java`.  Summarize
the main approach of the class.  What ideas from the class might you
apply in other situations?  (Pick at least one or two.)

### Exercise 3: A faulty implementation

The file `ArrayBasedStack.java` has a significant bug.
Identify and correct that bug.

### Exercise 4: Matching parens

One useful application of stacks is matching things.  For example,
we can match the parens in a Scheme expression as follows:

```text
Step through the characters in the expression
    When you encounter an open paren or open square bracket, push it 
      on the stack
    When you encounter a close paren or close square bracket, pop 
      the corresponding opening character off the stack.  If the two
      characters don't match, issue an error.
    If you encounter a closing character with an empty stack, that 
      close paren or bracket is mismatched.
If the stack is not empty, there are unmatched open or closed parens.
```

Implement this algorithm.  (You might also add support for braces
and angle  brackets.)  That is, write and experiment with a static
method, `checkMatching(String str)`, that checks whether the parens,
square brackets, and potentially, other characters, match correctly.

### Exercise 5: Getting started with linked queues

_Forthcoming_.

For those with extra time
-------------------------

_If you find that you finish the lab early, you might consider
undertaking one or more of the following problems.  You will
likely want to consider them in order._

### Extra 1: Matching

Revise your answer from the previous exercise to store the indices of
matching symbols.  That is, you will need to push both symbol and
index.  Use the indices to provide better error messages (e.g., you
can say where the mismatch occurs in the string).

How can you store two kinds of values in stack?  One option is to
make it a stack of Objects, and alternately push Character and
Integer objects.  Another option is to create a simple class that
groups a character and an integer.

### Extra 2: Displaying matching parens

Extend your answer from the previous extra problem to
provide a nice picture of the matching parens.  For example, for
each pair of matching parens, you might draw a line underneath,
as in the following.

```text
(oh (boy) (I am having) ((so) much) fun matching (parens))
    +---+
          +-----------+
	                 +--+
			+---------+
			                         +------+
+--------------------------------------------------------+
```


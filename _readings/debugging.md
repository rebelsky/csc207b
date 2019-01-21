---
title: Debugging
summary: |
  We consider strategies for debugging code
  and begin our exploration of Eclipse's Java debugger.
---

The wonder of program bugs
--------------------------

Unfortunately, as much as we would like to write perfect code, we
sometimes write code that is less than perfect. When that happens,
we can spend more time trying to figure out what is wrong than we
actually spent writing the code itself. In order to make sure our
code is working, we have to make sure it has no bugs (or at least
as few bugs as possible).

How do we know that code is bug-free?  One hint is that it passes
all of our well-designed unit tests.  How do we know that code has
bugs?  It fails to pass a unit test.

How do you avoid bugs?  You can never completely avoid bugs.
Every programmer makes mistakes, fails to predict certain situations,
or misunderstands how a language or library works.  But you can still
work on strategies that help you avoid bugs in the first place.

* Write your tests first so that you think about the special cases
  that you may have to handle.
* Use preconditions and postconditions whenever possible.  In some cases,
  you may find it useful to insert "assertions" directly 
  in your code so that (a) you document what you expect at any point
  and (b) you know where things started to go awry.
* Document each section of a procedure so that you can remember what
  it's supposed to do when you revisit it at a later time (or so that
  a colleague who revisits your code knows what you were trying to
  do).

If your code still ends up failing the tests, what do you do next?
Sometimes just walking through the procedure again helps.  (A
student claims the most valuable lesson he ever got from me was
"Explain your code to someone, even your golden retriever".
That is, if you sit down and explain what you're trying to do and how
you acheive it, you're likely to find the bug.)

More often, though, you'll need to see what's happening while your
code runs, as what's happening is clearly different than what you
expect.  You may be tempted to insert a bunch of print statements,
but you are much better off using a debugger.  And, fortunately,
Eclipse, like most IDEs, has an integrated debugger.

A quick introduction to debuggers
---------------------------------

Debuggers are tools for tracing code to find if there is something
wrong and what that something is.  More precisely, they give you the
opportunity to find out where in your code things start going wrong.
How do they achieve this?  First, they let you *single-step
through your code*, printing values as you go.  They also
let you set *breakpoints* so that your steps can
be large. Using a debugger will be more efficient and informative
than simply putting a bunch of print statements in your code to trace
what is going on.

What capabilities can you expect to find in most debuggers?

* You can *determine the value of variables and
  parameters*.  In some cases, you will need to explicitly
  click on a variable (or type a command in a textual debugger).
  In others, the debugger will print them out automatically.
* You can *set breakpoints* - locations in the
  code in which you want to start exploring behavior and values.  Once
  breakpoints are set, you can simply run your program until it hits a
  breakpoint. In Eclipse you set breakpoints by right-clicking the left
  sidebar of the bottom left window and selecting "Toggle
  Breakpoint".  You can also remove breakpoints after
  you've realized that a particular portion of your code is not at
  fault for the errors.
* You can *single step through a method or function*.
  Once you've reached a breakpoint, you may want to follow control flow
  by executing one line at a time and checking the state of the system
  after each step.  Single stepping can be useful for some loops.
* You can *trace the stack*  Sometimes, it's difficult
  to know how you got into the current place in the current procedure.
  A stack trace gives you the full history of calls (often with
  parameter values).
* You can *watch variables*.  You can set certain
  variables whose values are always displayed.  In the upper right window you
  can see all the variables you have declared in a method and check
  what is happening to them every time the method is called
* In many debuggers, you can also *change variables*.
  You may find that a variable has a value other than you expected, and
  want to explore what happens if it has the right value.  You may
  just want to see what happens if it takes on a different value.
* In many debuggers, you can also *alter control flow*.
  For example, when you hit a breakpoint, you can direct the program to
  call another subroutine next, rather than advance to the next statement.
  
Why not debug with print statements?
------------------------------------

Many programmers debug by inserting print statements throughout
there code.  While there are some situations in which this strategy
is appropriate, most experienced programmers prefer to use a
debugger.

The preceding list of capabilities may suggest many reasons that
debuggers are better than just inserting some print statements.
Here are some other reasons why debugging with print statements is
not the best choice.

* At some point, you'll need to remove those print statements.
  (Well, if you work with a language with macros, or use a
  subroutine that checks a global debug flag, you may not have to,
  but you've still spent time inserting code that is unlikely to
  be necessary over the lifetime of your program.
* In large programs, particularly large programs with loops you might 
  have hundreds of output lines to check.  It's much easier to 
  it is easier to break that output down and focus on a small piece.
* If you change your mind about what you're looking for, you have
  to change the code and recompile.  (So, as you debug, you have 
  to do this many, many time.)
* You can't alter the behavior of the program while it's running
  (e.g., you can't change a variable or calling order).
* You cannot examine memory contents in real time.

Why not use a debugger instead?

Using the Eclipse debugger
--------------------------

It's much easier to understand the Eclipse debugger in practice
than by reading about it, so we will leave most of our discussion
of the debugger to [the corresponding lab](../labs/debugging).

The most important things you should know about the Eclipse
debugger are as follows.

* You will usually want a different Eclipse perspective when
  debugging.  Each perspective has its own arrangement of tabs.
  In the debug perspective, you'll typically see a Debug tab to 
  the left of the code (where the Package Explorer normally is)
  and tabs for variables, breakpoints, and expressions to the 
  right of the code (where the outline normally is).
* The easiest way to enter the Debug perspective is by clicking
  on the little green bug near the top right of the Eclipse window.
  You can also enter the Debug perspective through the Perspectives
  menu.

Some final thoughts
-------------------

While debuggers are useful, and can help you identify the locations
of the errors in your code, they are not a silver bullet.  In the
end, you will still need to analyze your code for errors, both obvious
(well, obvious in retrospect) and subtle. 

Some errors can be quite subtle (and infrequent).  For example, a
debugger is unlikely to help you find the error in the following
code.  (An error which experienced programmers may be more likely
to note.)

```java
 /**
  * Average two integers.  If the average has a fractional portion,
  * may round up or down.
  */
 public static int average(int x, int y) {
   return (x + y) / 2;
 } // average
```

Wrapping up
-----------

### Important Terms

* Debugging
* Debug mode
* Step Into
* Step Over
* Breakpoint
* Stack trace

### Review Questions

* List a few times you've had errors in your programs and how
  you identified the errors.  Would a debugger have been useful?
* What are some techniques you use to write less buggy code?
* When your program doesn't work, how can you determine where
  in the program things are going wrong?
* What's wrong with the `average` procedure at the end of the reading?
  How would you address that problem?

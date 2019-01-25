---
title: Eboard 01  Getting started
number: 1
section: eboards
held: 2019-01-23
link: true
current: true
---
CSC 207.02 2019S, Class 01:  Getting started
============================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Course goals
* Course structure
* Academic honesty
* A quick intro to object-oriented design
* ADTs and data structures
* Designing a stack ADT (an exercise)

Preliminaries
-------------

### News / Etc.

* Welcome to CSC207!
* I'm Sam (or SamR)
* Our class mentors are Anh Thu (section 1) and Shuyi (section 2)
* You should have received one handout at the start of class
    * The [CS Department Academic Honesty Policy](http://www.cs.grinnell.edu/academic-honesty-policy)
* Since it's the first day of class, I will take attendance.  (_It will take
  me a few weeks to learn all of your names._)
    * "Hi, my name is FORENAME SURNAME."
    * "You can call me PRIMARY_NAME."
    * "If you must address me by surname, you can call me
      Mr./Ms./Mx./[nothing] SURNAME."
    * Optional: "During winter break, I ..."
    * Optional: "My pronouns are ...."
* For some reason, I ended up with copies of 151 handouts in the handouts
  section of this course.  I've updated [the page on
  grading](../handouts/grading) and [the page on note-taking](taking-notes)
* I realize that PUM and LIA (from the readings) are horrid acronyms.  If
  you have better ones, I'd appreciate it.
* We'll be using two "books" for this course: The readings I write (or
  wrote) and the material that Peter-Michael wrote.  There's a link
  to his book under the Reference menu.  You can grab either the whole
  book or individual chapters.
* I'm teaching two sections of CSC 207 this semester.  I'm not yet sure
  of all the implications.  One is that I'm likely to assume I've said
  something in one class when I've said it in another.
* I'm trying to be less snarky this semester.  Call me on it.

### Upcoming work

* [Assignment 1](../assignments/assignment01) due Thursday night (not tomorrow).
    * Partners assigned Friday.
* Readings due before class Friday.
    * [An introduction to Java](../readings/intro-java)
    * [Simple Java for C progammers](../readings/simple-java-c)
    * [Arrays in Java](../readings/arrays)
    * Optional: Osera 1: From C to Java
* Quiz on Monday (and every Monday).

### Extra credit (Academic/Artistic)

_None yet._

### Extra credit (Peer)

* Indoor track.  The meet starts at 9:30 am Saturday.  The 5K is like 
  noon.  Just spend 30 minutes for EC.

### Extra credit (Wellness)

* Ten days of kindness.

### Extra credit (Misc)

_None yet._

### Other good things

### Questions

Do we have to buy the printed textbook?
  : No.  It's an optional resource.

What changed?
  : Things not on the syllabus, but rather extra handouts.  
    Flash cards are not required.  Sam has strange grading policies.

Will you drop the lowest quiz score?
  : Yes.

Course goals
------------

CSC 207 is our "bridge course" from doing CS and programming in the small
to "more real" CS and programming (medium/large scale)

* CS is the study of algorithms and data structures
* Goal: Enhance knowledge of algorithms and data structures
    * "The literature" - algorithms and data structures every computer
      scientist should know.
    * Techniques for analyzing algorithms (correctness, efficiency),
      including recursive algorithms.
    * More work on separating ADT from data structure.
        * ADT: What you want to do with the data
        * Data structure: How that's implemented
* Goal: Improve your skill at constructing software
    * A third approach: Object-oriented programming
    * "Professional" practices: Using Git and an IDE, Test-driven design,
      Using a debugger
    * Practice on somewhat realistic projects
* Goal: Learn Java
    * Adds some nice features to C, particularly objects
    * Huge library of features.
    * Popular

Detour: What is the output of the following C program?

    int x = 2;
    int y = x++ - x++;
    fprintf (stdout, "%d %d\n", x, y);

C spec says: "You may make no assumptions about code that includes
multiple increment operators on the same memory address."

Course structure
----------------

How do we help you learn all this?

* Labs
* Lab writeups (0/1)
* Think/pair/share
* Homework (group), hope five hours
* Two take-home exams.  Four medium-size questions.  All mostly right:A.
  Three mostly right: B.  Two mostly right: C.  One mostly right: D.
* Weekly quizzes
* Be here.  (Physically and mentally.)

_Sam discusses schedule_ (consensus: Good use of time.)

Academic honesty
----------------

Typical cases

* I'm overwhelmed and I need to pass this class.
* My parents will disown me if I don't get an A.
* Someone else from my social group really needs my help.
* I hate the professor.
* I forgot to cite.

Citing

* Make it a habit.
* Sam is unlikely to report minor violations.

A quick intro to object-oriented design
---------------------------------------

* Object: Something with state and operations.
* A way of thinking about program design that naturally matches some
  situations.
    * Modeling real-world situations.
    * GUIs
* Something that makes us more efficient as programmers
    * Encapsulation - Information hiding 
    * Inheritance - "Code copying" for similar problems
    * Polymorphism - "Multiple forms" - You can write generic code
      that works in multiple situations.
         * ListOf<X> rather than ListOfInt and ListOfString and ...
             * Parametric polymorphism
             * Generics
         * sort(list, compare) rather than sortInt(listOfInts), ...
             * Subtype polymorphism

ADTs and data structures
------------------------

* ADTs: The "what it does" of data.  PUM
    * P - Philosophy; the general concept of the structure.
          "Scheme Lists are things that support adding to and removing
           from the front, and checking for emptyness."
    * U - Use cases: Why would we want this thing?
        * Model Scheme code.
        * Keep track of students to call on.
    * M - Methods: What procedures support the use case.
       
* Data Structures: The "how it does" LIA
    * L - Layout: How to organize the data structure.  Array-like (chunk
      of memory) or linked nodes.
    * I - Implementation: How to use the layout to support the methods.
    * A - Analyze (efficiency)

Designing a stack ADT (an exercise)
-----------------------------------

* Philosophy: An expandable collection of values with the LIFO policy
  (last-in, first-out)
* Use cases:
    * Model real-world things, such as pile of papers
    * Procedure calls in programming languages that support recursion
    * Evaluate RPN expressions `operand operand operation` 
        * `3 4 5 + *` is 27.  Add 4 and 5, multiply 3 by the result.
        * Unambiguous
* Methods:
    * _Forthcoming_



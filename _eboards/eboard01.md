---
title: Eboard 01  Getting started
number: 1
section: eboards
held: 2019-01-23
link: true
---
CSC 207.01 2019S, Class 01:  Getting started
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
* ADTs and data structures
* A quick intro to object-oriented design
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
    * Something you did during break (if you're willing to share)
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

### Upcoming work

* [Assignment 1](../assignments/assignment01) due Thursday night (not tomorrow).
* Readings due before class Friday.
    * [An introduction to Java](../readings/intro-java)
    * [Simple Java for C progammers](../readings/simple-java-c)
    * [Arrays in Java](../readings/arrays)
    * Optional: Osera 1: From C to Java
* Quiz on Monday (and every Monday).

### Extra credit (Academic/Artistic)

_None yet._

### Extra credit (Peer)

* Watch some part of the indoor track meet on Saturday (starts at 9:30);
  30 minutes suffices.

### Extra credit (Wellness)

_None yet._

### Extra credit (Misc)

_None yet._

### Other good things

### Questions

_None yet._

Course goals
------------

CSC 207: The bridge course in computer science.  Helps you progress
in multiple ways.

* As a computer scientist.
    * Problem solving.  More practice.
    * Algorithms: Learn some of the "literature", techniques for designing
      algorithms, techniques for analyzing algorithms.
    * Data structures: Learn some of the "literature".  Practice.
* As a software developer
    * From small programs to medium/large programs
    * Strategies for implementing medium/large programs
    * A third model of program design: object-oriented programming

Using the Java programming language

* Java is much more strict about things than C or Scheme.
    * A simple case `if (x = 0) ...`, which we ocassionally write
      instead of `if (x == 0) ...` or `if (0 == x) ...`
* Java is a widely popular language.
* Java provides a reasonable object model, that is getting better all
  the time.
* Tradition!

This semester

* Learn Java
* Learn some basics of OOP
* Learn about algorithms
* Learn about data structures - how you store/organize data, implement ADTs
* Learn about abstract data types - Characteristics and behavior, rather
  than underlying mechanism

_Sam goes over the schedule._

Is there a bright line between abstract data type and data structure?
  : Nope.  Generally, we call something a data structure when we're
    focusing on implementation details and a ADT when we're focusing
    on how it's used.

Course structure
----------------

How do you learn this stuff?

* Labs in class.
* Lab writeups.  (0/1)
* Discussions in class (think/pair/share)
* Weekly assignments (group).  Due Thursdays at 10:30.
* Weekly quizzes on Mondays.
* Two take-home exams, different grading methodology.
* Read, think, take notes.
* I expect you in class.  Email me if you're not going to be in class.
  Complex absence policy.

Academic honesty
----------------

* Don't cheat.
* Cite!
* No need to cite the stuff that Eclipse builds for you.
* Including a library provides sufficient citation for that library.
* But if you find code on StackOverflow, cite it.
* If you take code from a reading, cite it.
* If you ask a friend for help, cite them.

ADTs and data structures
------------------------

* How you organize data affects the algorithms you write and the
  efficiency of those algorithms.
* Example: Binary search only works well for sorted arrays.
* The organization can affect how we think about problems.
* In thinking about the organization of data
    * ADT: How we use it (get the ith element, append to front, append to end)
    * Data structure: How we store it (in a contiguous chunk of memory, 
      as lots of small things linked together)
* We will design both ADTs and Data Structures
    * ADTS: PUM: Philosophy, Use Case, Methods
        * Philosophy: The general concept of the structure.  Lists are
          collections of data that are easy to expand and the client
          controls the order.  Arrays are indexed collections of data
          (and the client controls the order).
        * Use cases: What we're going to use it for.  We might use
          this to implement our class list, and we're doing to do this
          with our class list.
        * Methods: What behavior you need to realize the use cases;
          the bridge between the philosophy and the use cases.
    * Data structures: LIA: Layout (array or pointer), implementation
      (details of the implementation of methods), analysis (efficiency)

Designing a stack ADT (an exercise)
-----------------------------------

* Philosophy: A collection of things with a LIFO (last-in, first-out) policy
* Use cases: 
    * To model the papers on Sam's desk
    * Model things that follow this policy, such as containers on a
      container ship or plates in the dhall
    * Maybe if we want to keep the history of something, like weather
      information.  Keep adding the most recent, look at the most recent.
    * Implement recursive procedure calls in a programming language.
* Methods:
    * stack.push(val) - add a new item to the stack
    * stack.top() - look at the top thing
    * stack.pop() - remove and return the top thing
    * stack.isfull() - determine if the stack is full
    * stack.isempty() - determine if the stack is empty
    * stack.size() - maybe, depending on use case

### Implementing

* What do you keep track of?
* What's the general strategy.

As a linked structure

* Keep track of a pointer to the front of the linked structure
* push: Add to the front of the linked structure
* pop: Remove from the front of the linked structure
* isfull: return false or allocate memory and if you can't, it's not full
* isempty: front is null  (in C, `return front;`)
  

As an array

* Keep an array of the elements (and extra space)
* Keep an index into the array of the next element to add
* push: put the thing at the index, increment the index
* pop: decrement the index, grab the thing at the index, clear that entry
* isfull: 
    * return index == capacity
    * If index == capacity, try to allocate a bigger array.  
      If we succeed, return false.  Otherwise, return true.
* isempty:
    * return 0 == index (in C, `return !index;`) _clever beats readable_

A quick intro to object-oriented design
---------------------------------------

* Ideas and goals
    * Model data as "objects", collections of information and capabilities.
    * Sometimes this helps us design more clearly.
    * Sometimes this helps us match situations.
    * Use techniques that reduce programmer effort
* Three main concepts in OO design
    * Encapsulation: Protect details from clients
    * Inheritance: Build new objects based on old
    * Polymorphism: Write general code (with some specs)
        * Parametric polymorphism: Data structures that can be specialized
          for different data types (stack of <X>)
        * Subtype polymorphism: Methods that will work on any objects
          that provide certain methods.  sort(array of <Comparable>)
            * We did this in 151, now we have type checking

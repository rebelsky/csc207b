---
title: Eboard 03  Getting started with Java development
number: 3
section: eboards
held: 2019-01-28
link: true
current: true
---
CSC 207.01 2019S, Class 03:  Getting started with Java development
==================================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
* Eclipse lab
* Git lab

Preliminaries
-------------

### News / Etc.

* We'll follow the new partner methodology.
    * Partner names on the board.
    * See email about "Do not partner me with" option.
* Please turn in your academic honesty policy if you have not done so
  already.
* If you're not on the csstudents list, you probably should be.  Let me
  know if you'd like to be added.
* Mentor sessions at 7:00 p.m. Sunday nights.
* You will have a substitute teacher on Wednesday.  I'll be off learning
  about Fairness, Accountability, and Transparency in Artificial Intelligence.
* If you feel unsafe coming to class on Wednesday, send me an email message
  and I will treat it as an excused absence.  You are, however, responsible
  for making up the work on your own.
* When you send me questions about assignments, please title them
  things like "QUESTION on Assignment 2".  It makes it much easiere
  for me to identify them as questions.
* In case it wasn't clear from the syllabus and such, I will randomly
  call on students in class.  I do this for multiple reasons.
    * _It avoids bias_ (conscious and unconscious). Since I'm using cards,
      I am unlikely to ask more questions of people from certain groups.
    * _It builds skills_.  For better or for worse, many people expect you
      to express your ideas, even when you have not formulated them
      completely.  Practice in a low-stakes environment should help.
    * _It can remind you that you are not alone in confusion or
      misunderstanding_.  While I may sometimes force you to guess, you
      can feel free to answer "I'm not sure" when I call on you.
    * _It sometimes incentivizes you to ask questions_.  If you know
      that I might ask you questions, you may be more inclined to ask
      me questions.
    * And more.
* If being called on in class causes severe anxiety, let me know and
  we'll figure out a way to adjust to support you.
* I've updated the due time for lab writeups; writeups are generally due 
  at the start of class.
* You should be able to access everything on the current eboard by
  substituting `.md` for `.html` at the end of the URL.

### Upcoming work

* [Assignment 1](../assignments/assignment01) due Thursday night.
* Reading due before class Wednesday.
    * Osera, Chapter 2
* No lab writeup for Monday's class.

### Extra credit

#### Extra credit (Academic/Artistic)

#### Extra credit (Peer)

#### Extra credit (Wellness)

* Kindness through gratitude, Monday and Tuesday 11-1, outside DHall.

#### Extra credit (Misc)

#### Other good things

### Questions

_Tell me about the organization of a Java program_

> Java programs consist of multiple classes that work together.

> Each class (normally) resides in its own file.  More precisely,
  each public class has to be in its own file; you can add private
  classes to a file with a public class.

> By default, Java programs need a public, static, `main`, that takes
  an array of strings as parameters.

> We use the `main` method that appears in the command when you
  start the Java program.  `java HelloWorld` means "run the main
  method in the `HelloWorld` class.

> That main method (and other methods) can ... (a) run "normal" code;
  (b) call static methods in the same class; (c) call static methods
  in other classes with `ClassName.method`; (d) create objects
  with `new ClassName(params)`; (e) call methods on those created
  objects.

> For levels of groups, Java also supports packages (a group of classes)
  and modules (a group of packages).  We will not use modules, which
  were just introduced.

Can you have a method that's not static?

> Yes.  Just don't write the word `static`.  That method will then 
  require that you create an object and it can use the fields and
  methods of that object.  (See Wednesday's class.)

How should we format code?

> See the Google Java Style Guide.

> Our goal is consistency, which helps readers (and writers) (and graders).

Quiz
----

Fun and a chance for all of us to learn a bit.  (More or less.)

Eclipse lab
-----------

* Computers are sentient and malicious!

Git lab
-------

What should commit comments look like?

> A note that will remind you what you just did.

> "Add the HelloWorld class."

> "Prepare the project for use in Eclipse."

> "Squash the <describe> bug."

> "Refactor the code in HelloWorld."

Debrief
-------

Things Eclipse gives you ...

* On-the-fly syntax checking.  (You'll see a red x at the left.)
* Contextual documentation.
* Some automation (e.g., for refactoring, moving files).
* Enough that it overwhelms some folks.
* Lots of warnings on the command line.

Things Git gives you ...

* An easy way to share code.
* A history of your project.
* Ways to see changes.

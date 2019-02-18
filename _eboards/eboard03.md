---
title: Eboard 03  Getting started with Java development
number: 3
section: eboards
held: 2019-01-28
link: true
---
CSC 207.02 2019S, Class 03:  Getting started with Java development
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
    * Partner names appear on the board.
    * See email about "Do not partner me with" option.
* I've responded to every lab that has been submitted.
* Please turn in your academic honesty policy if you have not done so
  already.
* If you're not on the csstudents list, you probably should be.  Let me
  know if you'd like to be added.
* Mentor sessions at 7:00 p.m. Sunday nights.
* You will have a substitute teacher on Wednesday.  I'll be off at the
  ACM FAT* conference learning about Fairness, Accountability, and
  Transparency in Artificial Intelligence.
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
* If being called on during class causes severe anxiety, let me know and
  we'll figure out a way to adjust to support you.
* I've updated the due time for lab writeups; writeups are generally due 
  at the start of class.
* You should be able to access everything on the current eboard by
  substituting `.md` for `.html` at the end of the URL.  
    * `md` stands for "markdown", a very simple markup language that
      I use when writing the boards.
    * I'll probably work on ways to automate the update of the HTML page.

### Upcoming work

* [Assignment 1](../assignments/assignment01) due Thursday night.
* Reading due before class Wednesday.
    * Osera, Chapter 2
* No lab writeup.

### Extra credit

#### Extra credit (Academic/Artistic)

* CS Table Tomorrow, Noon, Somewhere.  Trusting trust.

#### Extra credit (Peer)

#### Extra credit (Wellness)

* Kindness through gratitude, Tuesday 11-1, outside DHall.

#### Extra credit (Misc)

#### Other good things

### Questions

In Java, you can't get addresses directly.  Are they still there?

> Yes.  Every object is really a reference to an object.  Every
  array is a reference to a chunk-o-membory.

So when I mutate an object or array within a procedure, I don't have
to return it?

> Yes.  If you mutate an object or array within a procedure, you are
  mutating the object or array the caller knows about.

Talk to me about licenses.  How do I choose one?

> Licenses specify how others may use your code.

> Typically: No warantee of behavior.  Often: You can use this however
  you wish, provided you cite me.

> Some licenses are viral: "You must use the same license on any derivative
  code."

> I tend to use the MIT license or GPL.

> I ask you to choose a license to remind you that you own your code
  and, eventually, have some responsibility to think about ownership.

> MIT: Use and cite.  GPL: Use, make source code available, cite,
  viral.

Style guidelines

> Benefit to everyone using the same approach.

> We're using one that's fairly common.

Quiz
----

_Isn't it joyful to demonstrate knowledge?_

Eclipse lab
-----------

I don't know why the launcher creator has changed.  But it clearly has.
Let me know if you have questions on how to create a launcher.

Git lab
-------

What kind of commit messages should I use?

> Something short that describes what you've just done.

> "Add my first class."

> "Squash the output bug."

> "Remove cruft."

> "Refactor code in HelloWorld."

> "Add some unit tests."

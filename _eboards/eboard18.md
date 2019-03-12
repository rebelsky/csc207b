---
title: Eboard 18  Anonymous functions
number: 18
section: eboards
held: 2019-03-06
link: true
---
CSC 207.01 2019S, Class 18:  Anonymous functions
================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Lab

Preliminaries
-------------

### News / Etc.

### Upcoming work

* [Exam 1](../exams/exam01) was distributed on Monday. Sorry for the delay 
  in getting it out.  (Because of the delay, I cut it from five questions
  to four.)
    * Prologue due Thursday night
    * Exam due the following Thursday.
* Reading for Friday: 
  [Linear and binary search](../readings/searching)
  (to be updated tonight)
* Lab writeup: TBD

### Extra credit

#### Extra credit (Academic/Artistic)

* CS Extras, Thursday, 4:15 pm: Unknown topic
* CS Table, Tuesday, noon: Unknown topic

#### Extra credit (Peer)

* March 8-10 (7:30 7:30 2:00), Twelfth Night.  
* Grinnell Singers March 10 at 2pm.

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

### Questions

_What do you mean by O(1) extra space?_

> You can create as many variables as you want as long as the number
  of variables is independent of the size of the array.

_Can you discuss the idea that a lambda can manipulate variables within
its scope, but outside the scope of whatever uses the variable?_

> See [Scoping.java](Scoping.java)

> Your anonymous functions, like your anonymous inner classes, can refer
  to local variables and even parameters of the method that creates them,
  and those variables stick around even after the method exits.  
  (This breaks in Sam's stack and heap simplified model.)

_Why would you ever do something like that?_

> It lets you build objects on the fly more easily.

        public static Function<Integer,Integer> adder(int addend) {
          return (i) -> i + addend;
        }

        ...
        map(adder(5), grades);
        ...
        map((i) -> i+5, grades);

_Is there a `map` in Java?_

> You get to write one.

> There are certainly similar things, such as the `forEach` in the
  `ArrayList` class and `map` in the `Stream` class.

_Can we do more than one thing in a lambda?_

> Probably.  Give it a try.  (I will, too, while you're starting lab.)

> Yes.  You need braces.  And you now need an explicit `return`.

> I consulted <https://www.oreilly.com/library/view/functional-programming-in/9781941222690/f_0088.html> in developing that answer.

        return (x) -> { 
          System.err.println("Processing " + x);
          if (x < 0) {
            return "negative"; 
          } else if (x > 0) {
            return "positive";
          } else {
            return "zero";
          }
        };

> You can see an example in [MoreLambdas.java](MoreLambdas.java)

Lab
---

Oracle provides code for a `Person` class that you can use.

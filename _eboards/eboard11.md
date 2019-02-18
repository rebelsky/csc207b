---
title: Eboard 11  List ADTs
number: 11
section: eboards
held: 2019-02-18
link: true
current: true
---
CSC 207.02 2019S, Class 11: List ADTs
=====================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Notes from Friday's class
* The design of ADTs, revisited
* Scheme lists
* General lists
* Java lists
* Quick notes on implementation
* Quiz

Preliminaries
-------------

### News / Etc.

* _Welcome to any prospective students who may be here._
    * Since we may have prospectives, the quiz will be at the end of
      class, rather than the beginning.
* Please let me know when you see formatting errors on the class readings;
  it's usually that I've made a last-minute change and broken the
  markdown.
* Sorry for issues on the Web site.  Jekyll was not cooperating.
* Today is a lecture/discussion/recitation day.  We'll be working on
  designing some abstract data types.
* Did you cover implementing queues with arrays and wraparound in CSC 161?
  Yes.  Yay!

### Upcoming work

* [Assignment 4](../assignments/assignment04) due Thursday night.
* Readings for Wednesday
    * [Linear Structures](../readings/linear-structures)
    * [Stacks](../readings/stacks)
    * [Queues](../readings/queues)
* No lab writeup.

### Extra credit

#### Extra credit (Academic/Artistic)

* CS Table, Tuesday, Noon, JRC 224B,   Who Owns 3D Scans of Historic Sites?
  Readings available at the back of the room.
* Grinnell Symphony, Wednesday, 7:30 p.m., in Sebring-Lewis
* CS Extras, Thursday, 4:15 p.m. Science 3821: Sam on CSC 321/22.
  (Snacks at 4pm in the CS Commons.)

#### Extra credit (Peer)

* Indoor Track and Field, Friday and Saturday, at Monmouth.

#### Extra credit (Wellness)

* HIIT training, 4:30 pm, Tuesday, Dance Studio, Bear.  (Cap of two EC units.)
* HIIT training, 10:00 am, Saturday, Dance Studio, Bear (Same Cap.)
* Hatha Yoga, 7:00 pm, Tuesday, Dance Studo, Bear.  (Cap of two EC units.)
* Other Yoga things.  (Find them in the memo or the calendar.)

#### Extra credit (Misc)

### Other good things

### Questions

Notes from Friday's class
-------------------------

* On Friday, it is likely that you realized that Java determines whether
  or not a method exists at compile time (static analysis), but determines 
  which implementation to use at run time (dynamic).
* We'll go through some diagrams to help us understand the issues at play.

Two contexts

* Compile time: Compiler knows the structure of each class (and their
  relationships), the declared type of variables.
* Run time: "Interpreter" knows the stack and the heap.

In case you've forgotten ...

* `Counter` is an interface that specifies `increment`, `get`, and
  `reset` methods.
* `BasicCounter` is a simple implementation of `Counter`.
* `DecrementableCounter` is a subclass of `BasicCounter` that adds
  a `Decrement` method.
* `NamedCounter` is a subclass of `BasicCounter` that overrides the
  `toString` method.

Let's consider some issues.

`Counter a = new BasicCounter(5); a.increment();`

* Static analysis: a is a Counter.  Every BasicCounter is a Counter,
  so we can assign a new BasicCounter to a.
* Static analysis: a is a Counter.  Every counter provides an
  increment method.  We can safely call a.increment()
* Run time: A refers to a reference on the stack.  That reference
  points to a BasicCounter object in the heap.  That object has
  a reference to its Class, which has a reference to the code.
* When we call a.increment() ...
    * `this` goes on the stack
    * Follow references to object then class then code.
    * Execute the code

`Counter b = new DecrementableCounter(5); b.increment();`

* Static analysis.  Assignment is safe because DecrementableCounter
  inherits from BasicCounter and BasicCounter implements Counter.
  (Alternately every DecrementableCounter is a BasicCounter and
  every BasicCounter is a Counter, so every DecrementableCounter
  is a Counter.)
* Static analysis.  Can we call `b.increment()`?  Yes.  b is a Counter,
  Counters implement increment.
* Run time setup: See board.  Note that the DecrementableCounter class has
  a pointer to its superclass.  (A superclass is the thing you 
  inherit from when you say "extends".)
* Run time execution of increment: `this` goes on the stack, we follow
  references to get the code for the current class; it does not include
  increment, so we look in the superclass, that does include increment,
  we run that code.

Detour: If every DecrementableCounter is a BasicCounter; why isn't
every BasicCounter a DecrementableCounter?

* This is like a subset relationship.  (subclasses represent subsets
  of superclasses)

Question: What does `extends` do?

* Gives you the fields of your superclass.
* Gives you the methods of your superclass.

`Counter c = new DecrementableCounter(5); c.decrement();`

* Initialization (see above)
* Is `c.decrement()` legal?  No.  The compiler says "You told me
  c is a Counter.  Counters lack a decrement method.  Error!"

`DecrementableCounter d = new DecrementableCounter(5); d.decrement();`

* Static: Initialization is safe, same types.
* Static: Is `d.decrement()` legal?  Yes.  d is a DC.  Every DC has
  a decrement method.

`BasicCounter e = new NamedCounter("count",5); e.toString();`

* Named Counters are Basic Counters, assignment is legal.
* e.toString() - Every BasicCounter is a Counter, every Counter provides
  a toString(), so the call is legal.
* At run time ... we get the `toString()` method of `NamedCounter`.
  The particular method to run is identified at *run time*, not
  compile time.

Suppose there is no `toString` method in `BasicCounter` (i.e., we use
the "default" method).

`DecrementableCounter f = new DecrementableCounter(5); f.toString();`

* Observation: Superclass of Counter is (implicit) Object class, which
  has toString.  We trace up to that.

`DecrementableCounter g = new Counter(); ...`

* `Counter` is not a class, so we can't call a constructor.
* Not every `Counter` is a `DecrementableCounter` (e.g.,, not every
  `Counter` has a `decrement` method), so it won't allow that.
* In general, you cannot assign a member of a superclass to a variable
  of a subclass.

The design of ADTs, revisited
-----------------------------

* Oh no, it's time for more PUMishment!
    * Philosophy
    * Use cases
    * Methods
* Idea: Write abstractly about our expectations before we think
  about implementation.
* Permits us to choose multiple implementations.

Thinking about list ADTs (Philosophy)
-------------------------------------

* Mutable or immutable
    * A mutable list might, for example, let us replace the second
      element or remove an element.
* Fixed-size or dynamically-sized?

Scheme lists
------------

Philosophy: An extensible, immutable, ordered collection of values

* Yes, when you build a list of length 20, you have effectively built
  20 lists.  But they share memory.

Methods (minimal set):

```
public interface SchemeList {
  SchemeValue car();
  SchemeList cdr();
  SchemeList cons(SchemeValue val);
}
```

General lists
-------------

Java lists
----------

Quick notes on implementation
-----------------------------

Quiz
----

Leave when finished.

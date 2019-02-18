---
title: Eboard 11  List ADTs
number: 11
section: eboards
held: 2019-02-18
link: true
current: true
---
CSC 207.01 2019S, Class 11: List ADTs
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
* Today is a lecture/discussion/recitation day.  We'll be working on
  designing some abstract data types.
* Did you cover implementing queues with arrays and wraparound in CSC 161?
  Yes. Yay!

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

#### Extra credit (Misc)

### Other good things

### Questions

Notes from Friday's class
-------------------------

* On Friday, it is likely that you realized that Java determines whether
  or not a method exists at compile time, but determines which implementation
  to use at run time.
* We'll go through some diagrams to help us understand the issues at play.

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

* Compile time: It's safe.
* Run time: Trace pointers until we find the code for increment, run it.

`Counter b = new DecrementableCounter(5); b.increment();`

* Static analysis: Every DecrementableCounter is a BasicCounter is a
  Counter.
* I can call the increment method because b is a Counter and all
  Counters include an increment method.
* The DecrementableCounter class object has a super reference to
  the BasicCounter class.
* What happens when we say `b.increment()`?  We find the code for
  the class.  We check whether there's an increment method.  There's
  not, so we look in the super class.

`Counter c = new DecrementableCounter(5); c.decrement();`

* Assignment is okay: From specific (subclass of implementer) to general
  (interface)
* Call to decrement is *invalid* because c is a Counter, and not all
  Counters have a decrement method.
* It won't compile.

`DecrementableCounter d = new DecrementableCounter(5); d.decrement();`

* The initialization is okay.  Same types on both side.
* The decrement method is okay because ...
* Pointer chasing is fun.

`Counter e = new DecrementableCounter(5); e.toString();`

* Static: The initialization is fine.
* Static: The `toString` call is fine.
* Runtime: What happens when we call `toString`?
    * Follow the code pointer in the DecrementableCounter class.  Don't
      find toString in the code.
    * Follow the super pointer to the BasicCounter class.  Follow the
      code pointer.  There's no toString :-(
    * Follow the super pointer to the Object class.  Follow the code
      pointer.  Find the amazing method "return this.class.name + "@" + this.address"

`BasicCounter f = new NamedCounter(5); f.toString();`

* Static analysis: Fine
* Run-time

### Questions

Can you eliminate a method in a subclass?

> Not usually.  It violates the assumption that every method available
  in the superclass is available in the subclass.  (That's what supports
  subtype polymorphism.)

> You can override, you may be able to reduce protection, but you can't
  eliminate (and you can't increase the protection).

What if I really want `Counter c = new DecrementableCounter(5); c.decrement();`?

> `((DecrementableCounter) c).decrement();

> However, if, for some reason, `c` is not a DecrementableCounter, the
  cast will through an exception.  (A ClassCastException)

The design of ADTs, revisited
-----------------------------

* Oh no, it's time for more PUMishment!
* PUM stands for: 
    * Philosophy
    * Use Cases
    * Methods
* Idea: We're going to think about how/why we use a particular data type
  before we worry about building it.
* After PUM comes the implementation.

Scheme lists
------------

Philosophy:

* An immutable structure.

Methods:

* cons an element on to the front, creating a new list.
* car returns the first element of the list.
* cdr returns all but the first element of the list.
* null? returns whether or not the list is empty (use a method or use
  == null)
* null is the empty list. (Could use Java's null, could use a specific
  object.)

Approach 1: Very imperative

```java
public interface SchemeList {
  public static SchemeList cons(SchemeValue val, SchemeList lst);
  public static SchemeValue car(SchemeList lst);
  public static SchemeList cdr(SchemeList lst);
  public static boolean isNull(SchemeList lst);
} // class SchemeList
```

Approach 2: More OOP

```java
public interface SchemeList {
   /**
    * Build a new list by adding val to the front of this list.
    */
   SchemeList cons(SchemeValue val);
   /**
    * Get the first element of the list.
    */
   SchemeValue car();
   /**
    * Get all but the first element.
    */
   SchemeList cdr();
} // interface SchemeList
```

Writing Map

```scheme
(define map
  (lambda (fun lst)
    (if (null? lst)
        null
        (cons (fun (car lst))
              (map fun (cdr lst))))))
```

General lists
-------------

Philosophy

* Mutable collections of values to which you can add values and
  from which you can remove values and access values.
* If we want to distinguish lists from arrays, we might say
  "that we can access in sequence, in the order the client
   specifies by calls to `add` and `remove`"
     * Create a new list
     * Add "A" at the front
     * Add "B" at the back
     * Add "C" between "A" and "B"
     * Add "E" after "B"

Methods

* Strive to be minimalistic!  (Enough to meet the use cases, not a
  "kitchen sink" of methods.)
* Determine the number of elements. `size`
* What parameters does `add` take?  Getting that right is hard, particularly
  if we want to keep things efficient.  We'll leave it for another day.
    * Easy solution: Only allow addition at front or back.

```java
public interface List<T> {
} // interface List<T>
```

Java lists
----------

* A bit of a kitchen sink.
* Sam worries that there are so many methods it is hard to implement.
* Sam worries that the use of indexed operations means that people will
  use such operations without understanding the cost.
    * StackOverflow regularly has people who tell you to use `ArrayList`
      and then do things like `remove(i)`.
        * In a linkedlist, that will be expensive because you have
          to get to that point.
        * In an arraylist, that will be expensive because you have
          to shift.
        * Things that take about n steps for a list of n elements
          are generally a bad idea.

Quick notes on implementation
-----------------------------

* Two basic implementations
    * Linked nodes
    * Arrays

```java
public class LinkedSchemeList implements SchemeList {
  // +-------+-------------------------------------------------------
  // | Notes |
  // +-------+
/*
  We use a special value, LinkedSchemeList.NULL to represent the
  empty list.
 */

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  public LinkedSchemeList NULL = new LinkedSchemeList(null,null);

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The value at the front of the list.
   */
  public SchemeValue head;

  /**
   * The rest of the list.
   */
  public LinkedSchemeList rest;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Make a Scheme List with one value.
   */
  public LinkedSchemeList(SchemeValue val) {
    this.head = val;
    this.rest = LinkedSchemeList.NULL;
  } // LinkedSchemeList(ScheemValue)

  /**
   * Make a Scheme List in the traditional way.
   */
  private LinkedSchemeList(SchemeValue val, LinkedSchemeList lst) {
    this.head = val;
    this.rest = lst;
  } // LinkedSchemeList(SchemeValue, LinkedSchemeList)


  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

   /**
    * Build a new list by adding val to the front of this list.
    */
   LinkedSchemeList cons(SchemeValue val) {
     return new LinkedSchemeList(val, this);
   } // cons

   /**
    * Get the first element of the list.
    */
   SchemeValue car() {
     return this.head;
   } // car

   /**
    * Get all but the first element.
    */
   LinkedSchemeList cdr() {
     return this.tail;
   } // cdr

   /**
    * Determine if the list is empty.
    */
   boolean isEmpty() {
     return (this == LinkedSchemeList.NULL);
   } // isEmpty

   /**
    * Convert to a string
    */
   public String toString() {
     if (this.isEmpty()) {
       return "()";
     } else if (this.cdr().isEmpty()) {
       return "(" + this.head + ")";
     } else {
       return "(" + this.head + " " + this.cdr().toString().substring(1);
     }
   } // toString()
} // class LinkedSchemeList
```

Quiz
----

When you finish, you may leave.


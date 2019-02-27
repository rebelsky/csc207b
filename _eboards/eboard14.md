---
title: Eboard 14  Iterators
number: 14
section: eboards
held: 2019-02-25
link: true
---
CSC 207.01 2019S, Class 14:  Iterators
======================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
* Preparation
* Lab

Preliminaries
-------------

### News / Etc.

* I spent the weekend grading a CSC 151 exam.  Our lab may a bit more
  ad-hoc than usual.
* I will be off at SIGCSE for the rest of the week.  Dr. Vostinar will
  be covering our classes.

### Upcoming work

* [Assignment 5](../assignments/assignment05) due Thursday night.
* Exam 1 to be distributed Friday.
* Reading for Wednesday: 
  [Osera, Chapter 4](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap04.pdf)
* [Lab writeup](../writeups/writeup14): 3
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.01 Writeup for Class 14 (Your names)**
    * Include the code for your iterator in the body of the message.

### Extra credit

#### Extra credit (Academic/Artistic)

#### Extra credit (Peer)

* Neverland Players, Friday (8:30), Saturday (2:00 and 7:30), and Sunday (2:00)

#### Extra credit (Wellness)

* Student Wellness Fair, Tuesday, 5-7pm, JRC 1st and 2nd floors.

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

Talk to me about the `toString` method in hashes.

```java
    for (int i = 0; i <= 255; i++) {
      pen.println(String.format("%d: %x", i, i));
    } // for
```

Why did Alice and Bob turn into Anna and Bob?

> Foolish consistency is the hobgoblin of small minds.  I'll fix it.

What's a nonce?

> Something that's hard to explain.

> A bit like a password.

> It's a number that allows us to generate a valid hash.

How does mining relate to nonces?

> Mining is a process in which we repeatedly generate candidate nonces until
  we find one that gives us a valid hash.

What makes a hash valid?

> For this assignment, the first three bytes must be zero.

What's a byte?

> An integer between 0 and 255.

How much effort to mine?

> In this case, it appears that only 1/(256^3) hashes are valid, so about
  256^3 nonces must be generated.

Quiz
----

_If you finish early, take the time to sit quietly.  Alternately, get a
drink of water or use the restroom._

Lab Prep
--------

What is an iterator?

> An object that allows you to visit each element of a collection
  one by one.

How does it do that?

> Through two methods, `next()` and `hasNext()`.

> Client repeatedly calls `next()` to visit the elements.  Knows to
  stop when `hasNext()` returns false.

Why do we use them?

> Allows the client to access the elements in the collection.

> Lets us write more general code.

> Provides encapsulation: Client does not need to know the innards
  of our collection.

What is an anonymous inner class?

> A class that you can make within another class.

> They don't have a name, that's why they're anonymous.

Why do we use them?

> Good for helper classes (just like anonymous functions were usuful
  as helpers in Scheme), particularly when we only want to use
  them once.

How do we write them?

> Something like the following.  Here's a counter that increments by
  ten, starts at 5, and resets to 0.

```java
  new Counter() {
    int i = 5;
    public int get() { return i; }
    public int increment() { i += 10; }
    public int reset() { i = 0; }
  }
```

```java
foo(new Predicate<String>() { public boolean test(String str) return false; });
```

Lab
---

Writeup: Exercise 3

```java
public ArrayBasedQueueIterator<T> implements Iterator<T> {
  ArrayBasedQueue<T> abq;
  int i;

  public ArrayBasedQueueIterator(ArrayBasedQueue<T> abq) {
    this.abq = abq;
  }
  // ...  this.abq.values[pos] ...

}

public ArrayBasedQueue<T> implements Iterable<T> {
  // ...
  Iterator<T> iterator() {
    return new ArrayBasedQueueIterator<T>(this);
  }
  // ...
}
```


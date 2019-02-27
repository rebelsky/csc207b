---
title: Eboard 14  Iterators
number: 14
section: eboards
held: 2019-02-25
link: true
---
CSC 207.02 2019S, Class 14:  Iterators
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

* I spent the weekend grading a CSC 151 exam (and writing some readings).  
  Our lab may a bit more ad-hoc than usual.
* I will be off at SIGCSE for the rest of the week.  Dr. Vostinar will
  be covering our classes.
    * No office hours this week.

### Upcoming work

* [Assignment 5](../assignments/assignment05) due Thursday night.
* Exam 1 to be distributed Friday (or before).
* Reading for Wednesday: 
  [Osera, Chapter 4](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap04.pdf)
* [Lab writeup](../writeups/writeup14): Exercise 4
    * To `csc207-01-grader@grinnell.edu`
    * Subject: **CSC 207.02 Writeup for Class 14 (Your names)**

### Extra credit

#### Extra credit (Academic/Artistic)

* Bucksbaum 152 4:00 p.m. today, talk on Bollywood and Neonationalism.
* Faulconer talk to be determined.

#### Extra credit (Peer)

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

* Neverland players: Friday @ 8:30, Saturday @ 2, 7:30, Sunday @ 2.
  Watch Grinnell student act out stories by elementary/middle students.

### Questions

What's a nonce?

> Short for non-sense?

> An integer that you use in a block to compute a hash.

> You also use the hash of the previous block, an int that represents the
  data (for our assignment, the data are the money transfered between
  Alice/Anna and Bob) and an int that represents the block number.

> The nonce helps us know whether or not a block is valid.

> The nonce is a value that allows the hash to match a desired hash
  pattern.

> This makes it harder to mess with the data.

How does mining relate to nonces?

> Only certain nonces can make valid hashes for a particular set of
  data (prev block, money transferred, valid block).

> We mine for nonces.

> We mine for nonces by trying lots and lots of values until we find one
  that works. (a) random; (b) sequentially  (Works = hash is valid)

What makes a hash valid? (At least for this HW?)

> One that starts with three 0's (hex digits)

What's a byte?  

> A small integer, between 0 and 255.

> Something that happens after you've been bit?

How much effort to mine, assuming three bytes?

>  1/256 for the first 0, 1/256 for the second 0, 1/256 for the third 0

> About 256^3 prospective nonces must be generated 

> Or 16^3

> Traditionally, we make it expensive to mine so that people can't
  easily change the chain.

Why do we put nonces in a byte buffer?

> Because we're using MessageDigest objects to hash, and those reqire
  arrays of bytes.  byte buffers provide the bridge.

What's a hex value?

> Four bits, 0 through 15.

How do we convert our byte to a a hex string?

> `String.format("%x", (int) b)`

Quiz
----

_If you finish early, take the time to sit quietly.  Alternately, get a
drink of water or use the restroom._

Lab Prep
--------

What is an iterator?

> An object that provides `next` and `hasNext`.

> Used to step through the values in a collection.

> Through repeated calls to `next`

Why do we use them?

> Encapuslation: Client can see all values without knowing structure.

> Generality: Client can write code to work with any collection.

What is an anonymous inner class?

> A helper class that is unnamed.

Why do we use them?

> Similar to the reasons we have anonymous helper functions: Use them in
  only one place, so there's no need to name them.

> Give access to the fields of the enclosing (outer) class.

How do we write them?

```
new Interface() { BODY; }
```

E.g.,

```
new Predicate<String>() { 
  boolean holds(String str) { 
    return string.length() < 5; 
  } 
}
```

Lab
---

Writeup: Exercise 4


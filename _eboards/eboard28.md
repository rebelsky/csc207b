---
title: Eboard 28  The Map ADT
number: 28
section: eboards
held: 2019-04-12
link: true
---
CSC 207.01 2019S, Class 28:  The Map ADT
========================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Friday PSA
    * Questions
* Introduction to maps
* Additional map functions
* The Java Map API
* Implementing maps
* Assessing implementations
* Implementing maps, revisited (if time)

Preliminaries
-------------

### News / Etc.

* Today is a discussion day, so I want everyone to sit with someone.
  I've put you with your HW partners.
* You may want to bring up the [the Java Map API](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html), which we will discuss later in class.
* I heard from a few of you that "I did not really understand that comment
  about X, but I didn't want to slow down the class."  Please ask.
* A few of you submitted last-minute (or beyond last-minute requests for
  extensions.  If you'd like an extension until 5pm on Sunday, email me
  and you can have one.  You will have to put your printed copy under my
  door by 5pm on Sunday.
* The giant laurel leaf was depressing, at best.  **Thank you** to those of
  you who came and tried to contribute.
* Printed copy of makeup 1 due now.  (Except as specified above.)

### Upcoming work

* Readings for Monday
    * [Osera 10.0-10.3](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap10.pdf)
* Quiz Monday: Lists and list iterators (including array-based lists and
  linked lists).
* [Assignment 7](../assignments/assignment07) due Thursday the 18th
* No lab writeup.

### Extra credit

#### Extra credit (Academic/Artistic)

* The Magic Flute, April 18, 7:00 p.m. Sebring-Lewis

#### Extra credit (Peer)

#### Extra credit (Wellness)

* TODAY, 4-6pm: Make fidgets.  Maker lab, 927 Broad street.
  For more information or accommodation, contact [bernalma] or
  [phamanht]
* Monday, April 15, 7:30 p.m., Harris Cinema: From the Munchies to
  Memory Effects: What the Science Says About Cannabis/Marijuana

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* Participate in Kinetic Sculpture Competition: Saturday the 27th
    * <https://bit.ly/kineticsculpture19>
    * You'll need to build your sculpture in advance
    * You get reimbursed for up to $200 in supplies, but must present
      to be reimbursed.
* Info session on KSC TODAY at 7pm at MLab.
    * Between 4th and 5th on Broad, on the West side of the street,
      between Jensen Optometrists and the Bike Store.
* Public speaking workshop - April 22 at 7pm in HSSC S3325, with
  Kathy Clemons=Beasley '05.  "Kathy is the Global head of Leadership
  and Manager Development for Blackrock and has been the speaker
  coach for TEDxGC."
* Harold Green "Clean House" Friday at 6:00 in BCC.
* Clothing donation boxes in lounges.  Donate! 

### Other good things

### Friday PSA

* Take care of yourselves.

### Questions

Introduction to maps
--------------------

* An ADT that stores keys and values.  (Some folks say "associates keys
  and values")
* We've worked with pairs before, this seems something like a collection
  of pairs.
* A generalized version of problem 4 on the makeup.
* Inconsitent naming:
    * Maps
    * Dictionaries
    * Associations
    * Hashes // A bad name, because it suggests a particular implementation
    * Tables
* Each key has one associated value.
    * However, it could be a list.
* Each value may have many associated keys.
* We often use strings as keys.
* Integers suggest arrays, but only if the integers are small and
  sequential.

Designing our dictionary ADT
----------------------------

What methods would you expect?

### Associating keys and values

We need a method to add values.

```java
  /**
   * Add a key/value pair to the dictionary.  (Alternately,
   * "associate a key and a value".)
   */
  public void add(K key, V value);
```

* What should it return?  `void` or `boolean`, but we couldn't come up
  with a reason to return false.
* Precondition: Neither key nor value should be null. (or maybe just
  make that restriction on the key)
* If the key is already in the dictionary, that means that we seem to
  have tried to do something that violates our claim about maps, and
  so we should ...
    * Replace the old thing ... oh, maybe that's a reason to return
      T/F.
    * Throw an exception, there can be a separate method for
      replacing values.  ***

### Updating associations

```java
  /**
   * Update the value associated with a particular key.
   */
  public void update(K key, V value);
```

* Return type?  void; we're updating.
* Precondition: key is not null; (we've decided that value can be null)
* Precondition: key is already in the table
* Postcondition: get(key) = value

### Exploring associations

```java
  /**
   * Determine what value is associated with a key.
   */
  V get(K key);
```

* Preconditions: key is inside the dictionary.  Throws an exception
  if not.  `IllegalStateException`

### Checking for keys

```java
  /**
   * Determine if a key is in the dictionary.
   */
  public boolean containsKey(K key);
```

* Preconditions: [No additional], key is not null.

### Size

```java
  /**
   * Determine how many key/value pairs are in the dictionary.
   */
  public int size();
```

* Return type: int (double is approximate/unnecessary, long is too
  big, byte is too short, as is short)

### Lookup keys

```java
  public List<K> lookupByValue(V value);
```

* Preconditions: Value must be in the dictionary.
* Postconditions: 
    * Option one: Return type is K, returns some key for which get(key) = value
    * Option two: Return type is List<K> or Iterator<K>, returns all the keys for which get(key) = value  ***
* If we choose option 2, we no longer need the precondition; we'll just
  return the empty list.

### Removing key/value pairs

```java
  /**
   * Remove the key/value pair with the specified key, return the
   * associated value.
   */
  public V remove(K key);
```

* Pre: key is not null
* Post: !containsKey(key)
* Note: If the key is not in the dictionary, return null.
    * Sam prefers "Throw an exception."

### Iterating

```java
  /** 
   * Get all the keys in the dictionary.
   */
  Iterator<K> keys();

  /**
   * Get all the values in the dictionary.
   */
  Iterator<V> values();
```

* Question: If a value appears twice ...?

The Java Map API
----------------

_How does [the Java Map API](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html) differ from what we just designed or what
PM designed (which is quite similar to ours)?_

Broadly: What did you discover in reading the Java Map API?

* They are much less likely to throw exceptions than we are. 
    * For remove, they don't throw an exception if the key is not there.
    * For replace, they dont' throw an exception if the key is not there.
    * For the don't throw an exception if the key is already there.
* Although they throw various kinds of exceptions
    * Some about characteristics of the key or the value
* Many of the methods are marked as optional
    * clear, put, putAll, remove, replaceAll, putIfAbsent, etc.
    * All throw `UnsupportedOperationException`
    * If you can't put or remove, what good is a map?  Sometimes we don't
      want clients to change things. ("Immutable map")
* put has a return type of V (returns the old value, if there was one, 
  or null otherwise)
* There are a lot of higher-order procedures, such as 
    * `default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)`
    * Computes a new value, based on key and value "in place", which is a bit
      more efficient.
    * Mostly added in 1.8, when they added functions.
    * `forEach(BiConsumer<? super K,? super V> action)`
* Java 9 added `of` (like eight different versions) as a way to make simple
  immutable maps.

Implementing maps
-----------------

_Come up with as many implementations of maps as you can.  Remember that
you have arrays and linked nodes as your primary building blocks._

* Association list (from PMO reading) - a list of key/value pairs
    * Add: Search to make sure it's not there already, put at front or
      back.  (Alternately: Just put at the front.)
* Associative array: An array of key/value pairs (or two arrays, one
  of keys, one of values, that we keep in synch).
    * Potential savings in memory.
    * Less error-prone.
    * Locality.
* Sorted associative array: An array of key/value pairs sorted by key.
    * Makes `get` much faster. (Binary search)
* Sorted association list:
    * Doesn't allow binary search.
    * Compared to non-sorted association list?  Tends to be a bit faster,
      since we know earlier that something isn't there.
* Binary search trees (from 151)
    * We have a tree.
    * Each node has at most two children (binary)
    * Each node has a key and a value.
    * Left subtree is smaller keys
    * Right subtree is larger keys
    * If the tree is built well, it's O(logn) to get, put, update
    * Problem: How do we keep the tree built well
    * Problem: Iteration is much harder
    * Problem: How do we remove?  (`throw new 
      UnsupportedOperationException("too hard")`)
* If the keys are small integers, we can just use an array.
    * If we can convert the keys to small integers, we can also use na
      array.
    * Typical name: Hash table or HashMap.
* If the keys are strings, we can build a trie.


Assessing implementations
-------------------------

Implementing maps, revisited (if time)
--------------------------------------

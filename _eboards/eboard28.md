---
title: Eboard 28  The Map ADT
number: 28
section: eboards
held: 2019-04-12
link: true
current: true
---
CSC 207.02 2019S, Class 28:  The Map ADT
========================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Friday PSA
    * Questions
* Introduction to maps
* The Java Map ADT
* Implementing maps
* Assessing implementations
* Implementing maps, revisited (if time)

Preliminaries
-------------

### News / Etc.

* I heard from a few of you that "I did not really understand that comment
  about X, but I didn't want to slow down the class."  Please ask.
* A few of you submitted last-minute (or beyond last-minute requests for
  extensions.  If you'd like an extension until 5pm on Sunday, email me
  and you can have one.  You will have to put your printed copy under my
  door by 5pm on Sunday.
* The giant laurel leaf was depressing, at best.  **Thank you** to those of
  you who came and tried to contribute.

### Upcoming work

* Readings for Monday
    * [Osera 10.0-10.3](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/osera/chap10.pdf)
* [Assignment 7](../assignments/assignment07) due Thursday the 18th
    * Partners to be distributed after class.
* No lab writeup.

### Extra credit

#### Extra credit (Academic/Artistic)

* The Magic Flute, April 18, 7:00 p.m. Sebring-Lewis
* Are Robots Taking Over Our Jobs?  4:15 p.m.  HSSC S3325 - Large Lecture

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
  Kathy Clemons-Beasley '05.  "Kathy is the Global head of Leadership
  and Manager Development for Blackrock and has been the speaker
  coach for TEDxGC."
* Harold Green "Clean House" Friday at 6:00 in BCC.
* Clothing donation boxes in lounges.  Donate! 

### Other good things

* Go to drag show
* Go to Water Polo Saturday

### Friday PSA

### Questions

What do you mean when you say "Kitchen Sink"?

> There's an aphorism, "everything but the kitchen sink".  Wikimedia
  says "Almost everything, whether needed or not.  Ex: She must have brought 
  everything but the kitchen sink along on the trip, and how she lifted 
  her suitcase, I do not know."

> In the computing realm, you often find designs that exemplify this
  philosophy. ("It even has the kitchen sink.")  Microsoft Word is
  one example (every word processor clearly needs its own embedded 
  spreadsheet and drawing application).  I think of many Java designs
  as embedding a similar philosophy.

> Alternately: I prefer a minimalist view of what you can do; "kitchen
  sink" is an alternative term for a more maximalist view.

> When you see the Java 11 Map interface, you'll understand.

Introduction to maps
--------------------

* Key idea in a map: Associate keys and values
    * Name and phone number
    * ID and student record
    * Letter and an animal
* We will be talking about the design and implementation of such an
  ADT.
* Note: Each key has exactly one value.  One value may have multiple keys.
* Computer scientists are not consisten in naming things that map keys
  to values.
    * Maps
    * Dictionaries
    * Associations
    * Hashes // Not a good idea, since that's a particular implementation
    * Tables
* We have both the general concept and particular specifications
    * We'll look at our own design details
    * But we'll also see what Java specifies

Map functions
-------------

```java
class Dictionary<K,V> {
 ...
} // class Dictionary
```

Philosophy: A Dictionary is a collection of key/value pairs that associates
each key with a particular value.  You look up values with their keys.

Use cases: See above

* In some instances, the value could be a list or array or ...
  (E.g., a phone directory might map a name to a list of phone
   numbers.)

Methods:

### Add an element

```java
/**
 * Add a key/value pair.  (Alternately, associate value with key.)
 */
public void add(K key, V value);
```

* What should we do if the key is already in the dictionary?
    * Replace the key/value pair with the new key/value pair.
    * Add the value to a list of values associated with the key.
      (This changes our model of hash table; now we map keys to lists
      of values.)
    * Throw an exception; only allow addition with new keys.
    * _We would need to make a decision about this._
* Preconditions?
    * key is not null
    * value is not null (debatable; maybe you like storing nulls in your
      dictionary)
    * In one case above, key should not already be in the dictionary.
* Postconditions
    * lookup(key) = value
    * The size may be incremented by one (depending on whether we permit
      replacement.)

### Looking up values


```java
/**
 * Find the value associated with a particular key.  ("Search and Match")
 */
V lookup(K key);
```

* Potential complexities
* Preconditions
    * key is not null
    * key must be in the directory.  (If not, throws an exception of
      some sort; alternately returns null.)
* Postconditions
    * "value" is the value most recently associated with key either by
      a call to `add` or something similar.

### Checking for presence of keys

```java
/** 
 * Determine if a key appears in the dictionary.
 */
public boolean containsKey(K key);
```

* Preconditions: [None]
* Postconditions: [Obvious]

### Removing values

```java
/**
 * Remove a key/value pair.
 */
public void remove(K key);
```

* Return type?
    * void
    * boolean: True if it removed something, False if it did not
      (e.g., because the key was not in the data structure)
        * Could throw an exception (SamRs would prefer)
    * V: return the value associated with the removed key  [The winner!]
* Preconditions:
    * containsKey(key)
* Postconditions:
    * !containsKey(key)
    * The size decreases by one

### Size

```java
/**
 * Figure out how many key/value pairs are in the dictionary.
 */
int size();
```

### Iterator

```java
/**
 * Get an iterator that allows you to visit all of the keys.
 */
Iterator<K> keys();

/**
 * Get an iterator that allows you to visit all of the values (if 
 * a value appears twice in the dictionary, it appears twice in 
 * the iterator).
 */
Iterator<V> values();

/**
 * Get an iterator that returns all the key/value pairs.
 */
Iterator<Entry<K,V>> iterator();
```

Detour: Which exception to throw?
---------------------------------

* Option one: Throw the generic Exception.  Problem: doesn't convey detailed
  information to the rest of the program.
* Option two: Throw the generic RuntimeException.  You don't have to say
  "throws Exception"; you don't have to use a try/catch.  But doesn't convey
  detailed info to the rest of the program.
* Option three: Design a reasonable set of exceptions yourself.
  `public class NoSuchElementException extends Exception`.
* Option four: Use one of the standard Java exceptions.
  `UnimplementedMethodException`, `IllegalStateException`, `NullPointerException`

Sometimes, your choice of option four is dictated by the underlying
design.  For example, list iterators say: When the underlying structure
is modified during iteration, throw a ConcurrentModificationException.


The Java Map ADT
----------------

What does the Java standard map add that we don't have in our list, or
what different decisions did the designers make?  Do a Web search for
"Java 11 java.util.Map".

### `clear`

Woo!  Get rid of everything.  It's nice to wipe the slate clean.

### `getOrDefault`

Provide a default return value for when the key is not present.  Beats
the following.

```java
try {
  whatever = table.get(w);
} catch (NoSuchKeyException nske) {
  whatever = default;
}
```

or

```java
  whatever = table.get(w);
  if (whatever == null) {
    whatever = default;
  }
```

### `of`

Just in case you wanted to build a small map, there are a variety of forms
of `of`.

### Functional methods 

That is, methods that take functions as parameters.  Sam likes `forEach`.

Implementing maps
-----------------

Assessing implementations

Implementing maps, revisited (if time)

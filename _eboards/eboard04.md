---
title: Eboard 04  Objects and classes
number: 4
section: eboards
held: 2019-02-01
link: true
---
CSC 207.01 2019S, Class 04:  Objects and classes
================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * PSA
    * Questions
* Modeling objects with classes
* An exercise
* Lab

Preliminaries
-------------

### News / Etc.

* We'll follow the new partner methodology.
    * Partner names on the board.
    * See email about "Do not partner me with" option.
* Please turn in your academic honesty policy if you have not done so
* Mentor sessions at 7:00 p.m. Sunday nights.
* I apologize for the confusion on turning in HW 1.  I hope to get things
  set up for classroom over the weekend.

### Upcoming work

* [Assignment 2](../assignments/assignment02) due Thursday night.
    * Partners to be posted at end of class.
* Reading due before class Monday
    * [Unit testing](../readings/unit-testing)
    * [Debugging](../readings/debugging)
* Quiz Monday
    * Maybe a lit git
    * Class design issues
* Lab writeup: Exercise 4.  Submit your `Counter.java` file as an attachment
  or in the body of the email.

### Extra credit

#### Extra credit (Academic/Artistic)

* Curator's Talk for _Dread and Delight_.
  Friday, 1 February 2019, 4pm.
* Opening Reception for _Dread and Delight_.
  Friday, 1 February 2019, 4:15pm. 
* Neo Futurists, Flangan Theatre.
  Friday, 1 February 2019, 7:30 pm, 
* Met Opera Live in HD, Harris Cinema.
  Saturday, 2 February 2019: 
* _Once Upon a Time Wolf_ (tickets required), Bucksbaum.
  Friday, 8 February, 7pm.
* _Once Upon a Time Wolf_ (tickets required), Bucksbaum.
  Saturday, 9 February, 7pm

#### Extra credit (Peer)

* Swim and Dive, Osgood Natatorium.
  Saturday, 2 February 2019, 2pm

#### Extra credit (Wellness)

#### Extra credit (Misc)

### Other good things

* Men's Tennis, Sat, 9am, Fieldhouse.
* Men's Tennis, Sat, 2pm, Fieldhouse.

### PSA

* You are awesome, take care of yourselves.

### Questions

Modeling objects with classes
-----------------------------

To do objects in Java, we build classes.

* Much like structs in C, classes provide "templates" for the objects
    * Fields (attributes) describe the kind of data you store in each object
    * Methods (procedures, functions, messages) describe what the objects
      can do.
* Just think of Plato's cave.  The class is an ideal, the object is the
  shadow on the wall.
* Classes are a bit different than structs in that
    * You can have fields associated with the class as a whole. `static`
    * You can have methods associated with the class as a whole. `static`
    * Issues of encapsulation/protection
* Note: Classes are not the only way to design an object-oriented language.
  Another view: You start with one object.  You can then make clones of
  that object (duplicate fields and methods) and then dynamically add new
  fields and methods or change the fields and methods.

An exercise
-----------

Suppose we wanted to model fractions in Java.  What fields and methods
would you put in the class?  What expectations would you have about
those methods?

Design question: Are fractions mutable?

* Mutable: We can change the value of a particular fraction.  (E.g.,
  in Scheme, vectors are mutable, lists are not mutable.)
* Permitting mutation can save memory.
* Permitting mutation complicates analysis.
* Stupid example in C

        int fun(int *x, int *y) {
          *x = 1;
          *y = 2;
          printf("%d\n", *x);
        }

        what if we write

        int c;
        fun(&c, &c);

* That's because we are, in effect, permitting mutation.
* Let's see what Java says about things like integers.
     * Nope, can't mutate java.lang.Integer objects.
* Sam would recommend not allowing numeric classes to be mutable.
* What would it look like if integers were mutable?

        Integer x;
        x = new Integer(5);
        x.plusplus();
        pen.println(x); // 6

Fields in Fraction class

* numerator, an int or a BigInteger (arbitrary precision)
* denominator, an int or a BigInteger
* Note: We might do a Fraction class with ints (or longs) and a
  BigFraction class (with BigIntegers)


Design question: Do we have a field to represent positive/negative?

* Option 1: Yes, both numerator and denominator must be pos, plus sign
* Option 2: Use the sign of the numerator, require denominator is positive.
* Option 3: Compute based on numerator/denominator

Design question: What happens when we try to create a fraction whose
denominator is 0?  (Can you have a fraction whose denominator is 0?)

Design question: Do we store fractions in simplified form, or do we
require that the client explicitly call simplify?

* DrRacket assumed that it should always simplify implicitly.
* If you have context, the numerator and denominator may have additional
  meaning (yes responses over total responses).
* What is 2/3 * 3/4?

Point: Design is not simple, even for things that seem simple.

Constructors

* new Fraction(int num, int denom) throws DivideByZeroException;
* new Fraction(String description) throws InvalidFormatException,
  DivideByZeroException.  E.g., `new Fraction("2/4");
* new Fraction(double d); // Convert to nearby fraction
* new Fraction(int num); // Whole number, denom is 0
* ...

Question: How can we have lots of constructors with the same name
and different types of parameters?

* Java lets you create multiple methods with the same name, as long
  as their parameter types are different.


Other Methods

* Fraction multiply(Fraction other); (and other math operations)
* toInt(), toString(), toDouble(), ... (
* numerator(), denominator();
* simplify();   // If not available
* compareTo(Fraction other)
* ...

Lab
---

Writeup: Exercise 4.  Submit your `Counter.java` class as an attachment.

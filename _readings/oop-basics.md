---
title: Basics of object-oriented problem solving
link: true
---
# {{ page.title }}

*Summary:* We consider, at a high
level, the basic issues involved in object-oriented problem solving.

*Prerequisites:* None.

Background: Problem-Solving Paradigms
-------------------------------------

As you may have heard, Computer Science is the study of
*algorithms* (formalized instructions for solving
problems) and data.  One key aspect of this study involves the ways
in which we represent algorithms.  Early algorithms (by early, I mean
long before computers) were often written as examples.  For example,
in describing how to compute the volume of a rectangular hole, one
might say "Suppose the hole is 3 feet deep, 4 feet wide, and 6
feet long.  We multiply 4 and 6 to get the area of a cross section,
and then by 3 to get the volume of the hole."

With the advent of automated computing devices, it became important to
describe algorithms more carefully, and in such a way that the steps of
the algorithm could be automated.  In thinking about these descriptions,
computer scientists developed four main *paradigms*
for structuring solutions.

In *imperative* (also *procedural*) languages, we express algorithms
as a sequence of individual steps.  Steps traditionally involve basic
computation, input, and output.  To some, imperative algorithms look
very much like the recipes in a cookbook (do this, do that, do this
other thing this many times).

In *functional* languages, we express algorithms
by defining and combining functions.  Functional algorithms tend to
deemphasize precise sequencing of operations; for example, it usually
does not matter which argument to a function you compute first.

In *object-oriented* languages, we express algorithms by defining
"objects" and having the objects interact with each other.

In *declarative* languages, we express algorithms
by specifying a set of facts or goals, and let the computer determine
how to apply those facts or goals to solve the problem at hand.

Why object-oriented?
--------------------

Object-oriented languages currently dominate, although most of these
languages also have a strong imperative aspect.  (The past few years
have also seen strong growth in the use of functional approaches
in languages; most new languages are now multi-paradigm languages.)
Why do programmers like object-oriented languages?  There are a
variety of reasons to like these languages.

First, object-oriented languages are appropriate for programs that
model the real world.  In writing such models, we can have one object in
the program for each object in the world.  If your task is to, say, figure
out optimal cashier placement at Wal-Mart, such modeling is completely
appropriate.

Second, object-oriented languages can simplify parallelization.  Since each
object can, in effect, work independently, it is possible to put different
objects on different processors, and therefore make the program faster.

Third, object-oriented languages are helpful in building for modern
graphical user interfaces (GUIs).  Programmers have found it much
easier to design and implement GUIs when they think of each part
of the interface as an object that communicates with other objects
in the interface and with objects that provide the underlying
computation.  For example, we can think of each menu, each button,
and each window as a separate object and write the code by which
they react to user actions separately.

Finally, it turns out that many core ideas of object-oriented
programming make it much easier to write large programs and to reuse
code written for previous programs (or for other parts of the same
program).  We will visit and revisit these approaches and their
associated efficiencies.

Object Basics
-------------

So, what is an object?  In object-oriented problem solving, we think of
an object as something that collects data and capabilities.  Typically,
the data within the object are categorized.  For example, if we have an
object that represents a book, we would identify part of the data as the
title, part as the author, part as the publisher, and so on and so forth.
Similarly, for an object that represents a menu, we would identify part of
the data as the name of the menu and other parts as the items in the menu.

Traditionally, we call the categorized parts of an object the
*fields* or *attributes* of the object.

But objects do more than collect data.  Most objects also provide
a variety of capabilities.  That is, they can do things, typically
things with their internal data (and with values passed in).  For
example, a book object might provide the text of a page, if you
give it a page number, and a menu object might tell you what item
was last selected.  Some call these capabilities *methods* (the
term we will use).  Others refer to them as *messages* or *message
handlers*.  You may also think of them as *procedures* or *functions*.

Encapsulation: Separating what from how
---------------------------------------

In a well-designed object-oriented program, the clients of an object
(that is, the other objects that use an object or the programmers
who write those objects) interact with an object and the fields of
the object exclusively through the methods of that object.  Why
limit that access?  That is, why not give the client direct access
to the fields?  Experience suggests that giving the client such
access can create problems in the long term.  For example, suppose
the client assumes your object has a field called `lastName`.  If
you later change the name of that field, to, say, `surname`, the
client code will no longer work.  In almost every case, the program
will be just as efficient if the client does not directly access
the fields, so there is little reason to give the client that access.

In fact, there are also other reasons to limit access to the internals
of an object (not just the fields, but also the details of how each
method works).  By limiting such access, you absolve the client
from having to know how each method works.  Clients need only know
*what* your methods do.  Consider a rational number (fraction)
object.  It is likely that you will represent rational numbers as
a pair of integers, one for the numerator and one for the denominator.
A client that uses your numerator and denominator will need to know
not only what names you have chosen for the numerator and denominator,
but also particular other details of your representation, such as
how you deal with negative numbers and whether you always store
rational numbers in simplified form.  Rather than forcing the client
to know such details, we use methods to separate what one might do
with an object (e.g., negate a number) from how we implement those
methods.

Traditionally, we call such the separation of the interface (what an
object does) from the implementation (how it achieves) its goals
*information hiding*.  

Because objects combine methods and data and protect the data from
the outside world, we often say that objects *encapsulate* their
contents.

Classes: Templates for objects
------------------------------

One of the first problems any object-oriented language designer
encounters is how programmers are to describe the objects that
appear in their programs.  Many object-oriented languages rely on
*classes*.  A class is, in effect, a template for objects.  Each
class gives the names (and, often, types) of the fields for related
objects and the names (and, often, instructions) for the related
methods.

For example, a class to represent rational numbers might have two
fields, a numerator (integer) and a denominator (integer).  It might
provide methods to add another rational number to the current
rational, subtract another rational number, multiply by another
rational number, and so on and so forth.

Similarly, a class to represent books might have a field for the
title (a string), a field for the authors (a list of names, where
"name" is a previously defined class), a field for the
pages, and so on and so forth.  That class might provide methods to
access the title and authors (but probably not to change them) and to
get pages by number.

Inheritance: Building new classes from old
------------------------------------------

Object-oriented programmers quickly realize that the new classes
they build often closely relate to previous classes they've built.
For example, if we are called upon to write a class to represent
library books, that class will be very similar to the class for
regular books, except that the library book class will include
additional fields (such as the call number) and additional methods
(such as checking the book in or out).  The technique called
*inheritance* permits you to define a new class in terms of an old
class, and "automatically" inherit all of the fields and methods
of the original class.

At times, when we inherit from another class, we also want to
*override* (change the behavior of) some of the methods of the
original class.  Most object-oriented languages permit such behavior.
For example, suppose we design a square class that inherits from
the rectangle class.  If the rectangle class includes a method that
sets the width, we would want the square class to change that method
to set both width and height.

Although some aspects of inheritance could be implemented by the
legendary technique of copying and pasting code, real inheritance
permits one to make changes to the original class and have those
changes automatically propagate to the inheriting class.  The
automatic reuse and update capabilities associate with inheritance
are one of the reasons programmers so prefer object-oriented
programming.

Polymorphism: Writing general code
----------------------------------

The final key aspect of most object-oriented languages is called
*polymorphism*.  While inheritance lets you
reuse field and method definitions by building new classes from old,
polymorphism lets you write a common piece of code that can be used
in multiple contexts.  

In *subtype polymorphism*, you can reuse methods by applying
them to new objects.  In particular, subtype polymorphism is the
notion that you once you write methods that make particular assumptions
about the objects they take as parameters (most typically, the
methods those objects provide), those methods can then work with
*any* objects that meet those assumptions.

For example, we know that we can square anything that we can multiply
by itself.  The square operation can therefore work with integers,
real numbers, and complex numbers.  It can even work with new numeric
types we develop, such as rational rational numbers, as long as we define 
the multiply operation for those types.

In *parametric polymorphism*, you can design generic types and then
make them work concretely by specifying a "parameter type".  For example,
we can write a generic list type and then make concrete list-of-string and
list-of-integer types.

Summary: What to look for when learning OOP
-------------------------------------------

In this short reading, we've considered some key aspects of
object-oriented programming languages.  When you start to learn a
new object-oriented language, you will need to find out how each
of these features is implemented.  In particular, you will need to
figure out

* how to define a class (and the methods and fields in that class),
* how, given a class, to build objects that belong to that class,
* how to indicate that one class inherits from another, 
* how to write a polymorphic method,
* how to write a polymorphic class or type.

We will cover each of these ideas in our explorations of Java.

Wrapping up
-----------

### Important Terms

* Object
* Class
* Field
* Method
* Encapsulation
* Inherit/Inheritance
* Polymorphism
* Override

### Review Questions

* Why do programmers like object-oriented programming?
* What are two benefits to encapsulation?
* Why does inheritance improve code reuse?
* Why does polymorphism improve code reuse?

### Exploratory Questions

* In the reading, we noted that most, but not all, object-oriented
  languages use classes.  Find an object-oriented language that does
  not use classes and determine how programmers create new objects
  in such languages.
* Find a few examples of polymorphic functions.
* Find a few examples of polymorphic data structures (or ADTs).

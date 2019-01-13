---
title: Designing abstract data types
link: true
---
*Summary:*
We consider a technique for designing abstract data types.

*Prerequisites:*
General programming experience.

Introduction
------------

As you've no doubt heard many too many times, computer science is
the study of algorithms and information.  And, as you've likely
found in your programming and program design work, there is a close
relationship between the ways in which you represent data and the
algorithms you write to work with those data.  Consider, for example,
how a linked list and an array encourage very different approaches
to sorting and searching.

In spite of these close relationships, it can often be useful to 
step back and consider about how you want to use information before
you choose a particular way to represent that information.  That is,
we want to abstract away from the underlying representation to think
about *what* you want to accomplish rather than
*how* you accomplish that task.

When we talk think about data primarily from the perspective of
*what*, we are, in effect, designing what is often called an *abstract
data type*, or ADT.  ADTs are "abstract" in that we eliminate some
unnecessary details (such as the underlying representation).  ADTs
are "data types" in that the provide a way to describe certain kinds
of information.  In some sense, a type says what you can do with
the information.

Once you've designed an ADT, you can proceed in two different
directions.  You can design and implement a *data structure* that
implements the "how" of the ADT.  You can also write code that
assumes that the ADT has been implemented.  In effect, the ADT
provides an abstraction barrier between the main program and the
data.  One advantage of such an abstraction is that we can easily
swap out one implementation of an ADT for another.

Admittedly, this discussion of ADT is, well, a bit abstract.  So
let's go ahead and consider some details in the design of ADTs.  In
a future reading, we'll consider some details in the design of data
structures.

The PUM approach to ADT design
------------------------------

Where do you get started in designing an ADT?  Usually, there are a 
few motivating factors.  You may be designing a large program and need
to think about how you want to use data within the program.  You may
have written a few programs and have noted some common ways of using
data and want to write a library that encapsulates those common
approaches.  You may have simply learned about a "classic"
ADT and want to think more about the details.

In my experience, it's helpful to think about three issues as you
design an abstract data type.  First, you consider an underlying
*philosophy*.  That is, you describe the key factors that organize
your thinking on how you plan to work with the data.  For example,
you might decide that it will be convenient to access data by numeric
indices (e.g., an array or vector) or you might decide that you want
a data structure in which you collect values in some order and can
easily remove values or insert new values "in between" other values
(e.g., a list).

Second, you develop a list of *use cases*.  That is, you think about
problems in which it would be helpful to be able to access data in
those ways.  Sometimes the use cases come first.  That is, you think
about how you want to access data and then you come up with a general
organizing principal.

Third, you describe a collection of *methods* (procedures) that bridge
the philosophy and the use cases.  Your choice of methods is typically
influenced by two questions: What operations will you need to achieve
the requirements of the use cases?  How does the philosophy of the
data type guide the design of those methods?

An example: Simple lists
------------------------

Let's explore an example you might be familiar list: Lists of things.
We'll start with the *philosophy*.  Suppose you had to describe a
list to someone who had not encountered them before.  What might
you say, without describing any underlying implementation details?

Take a minute to think about it.

Okay, maybe another minute.

Ready?

Here's what I might say.

> *List, v1*.  A list is a collection of values in which each value
except the first has a designated successor and each value except
the last has a designated predecessor.  The successor and predecessor
relationships are symmetric: If a is the successor of b, then b is
the predecessor of a.  A list may also be empty.  A list may contain
multiple copies of each value, in which case they are considered
separate values for the predecessor and successor relationships.

We've now described an organization of data.  But how might we
use data organized in this way?  It's time to move on to use
cases.  One obvious case is my class list.  I start each class
with a list of students.  When I take attendance, I check off
each student in turn.  Here's another: For many assignments, I know
that there are common steps that every student should do.  For
example, in writing an essay, a student should pick a topic, gather
sources, read those sources, develop a thesis, make an outline,
write each section, have a peer read the essay, revise, and so on and
so forth.  Once again, we have the notion of building a list with
a fixed set of values and checking off each value in turn.

Are there any other characteristics hidden in these two use cases?
Well, in each case I expect that each time I use the same list,
it is unaffected by my previous actions or by other people's actions.
For my class list, each day I expect to start with a fresh copy of the
same list.  (We'll assume, for now, that no one drops or adds my class.)
For the assignment checklist, if Casey picks a topic, that doesn't mean
that Riley has picked a topic.

Our use cases have suggested a few basic actions: We need to be 
able to create a list of values, to "start off with a fresh
copy", and to check off elements from first to last.  Are these
the only actions we need?  They seem to be the only ones the use cases
suggested, so we'll start with them.  You can certainly think of others,
depending on your experience with lists, or the use cases that you 
come up with.

We're almost ready to move on to designing the methods.  But before
we do so, we might want to think a little bit about the implications
of the "fresh copy" requirement.  We could achieve this by having
a `copy` method that we assume that everyone uses.  And perhaps we
could differentiate between a "master list", which you can copy,
and a "usable list", which you check off.  Alternately, we could
just have *immutable* lists, lists in which checking off the first
item gives you another immutable list with the remaining elements.
Each time you check off an element, you get a smaller list.
Eventually, you end up with the empty list.

Should we use the master list model or the immutable list model?  We'll
try the second for now.  How do we know it's the better approach?  We
don't.  We will only learn how well it works by trying it out.

The most important method is certainly "check off an element".  What
input should it take?  It will take an immutable list as input.
What output do we get?  We get an immutable list with one fewer
value.  Are there any immutable lists which might cause it to fail?
You can't check off any elements in the empty list, so we require
that the input be a non-empty immutable list.  What should we call
the method?  Let's call it `cdr`, for "check-off data reference".
In Java, we might phrase this as follows.  (C programmers can ignore
the `public` and `static` for now.)

<pre>
/**
 * Check off the first element in a list, getting a new list of the
 * remaining elements.
 *
 * @param list, an immutable list
 * @pre list is nonempty
 * @post the result contains all the elements of list, except the
 *   first, and in the same order
 */
public static ImmutableList cdr(ImmutableList lst);
</pre>

If we specify preconditions for a method, we need to think about
how the client programmer can check those preconditions.  There are
certainly multiple ways we might allow one to check if a list is
empty: We could provide a method that returns the length of the list, 
we could provide a predicate that returns whether or not a list is 
empty, we could provide both, or we might find another approach.  
Alternately, we could just let `cdr` signal the
caller if it is given invalid input.  (If you haven't already learned
how Java methods can signal errors, you will in the near future.)
Let's try the simplest: We will provide a predicate method that 
returns true if the list is empty and false otherwise.  We'll call
it `nullp` for "nothing useful left in list"
(the "p" is for "predicate").

<pre>
/**
 * Determine if a list is empty.
 */
public static boolean nullp(ImmutableList list);
</pre>

What's left?  We need a way to build immutable lists.  How might we do
so?  One option is to provide a variable-arity method that wraps all
of its parameters up into a list.  (If you haven't heard the term
"arity" before, it just means "number of 
parameters".  A variable-arity method is one that can take
different numbers of parameters.)  Those of you who have learned
a Lisp-like programming language might also suggest that we have
a method to create an empty list and a method that does the inverse
of `cdr`; that is, a method that builds a new immutable list
by adding another element on the front of an existing immutable list.
We'll try the simpler approach, and just provide a variable-arity method.
Since Java doesn't really allow variable-arity methods, we'll require
that it take an array as a parameter.

<pre>
/**
 * Build a new immutable list.
 */
public static ImmutableList list(Object[] values);
</pre>

Are we done?  We might be.  Let's try writing some not-quite-real code
to see if we can achieve the first use case of taking attendance in a
class.

<pre>
  // Set up the list of students.
  ImmutableList students = list({ "Casey", "Charlie", "Jessie", "Sidney" ... });
  // As long as there are students we haven't called on
  while (! nullp(students)) {
    // Call on the next student
    ???
    // And check that student off
    students = cdr(students);
  } // while
</pre>

You'll note that there's one bit of code missing.  We haven't
inserted code for calling on the next student.  Why not?  Because
none of our methods seem to work.  We don't want to take the `cdr`,
because that removes the students.  Checking if the list is empty
doesn't give us the student.  And making a new list doesn't give
us the student.  It looks like we'll need to add at least one more
method.   Let's call it `car`, for "contents of active reference".

<pre>
/**
 * Get the value that will be removed by cdr.
 */
public Object car(ImmutableList list);
</pre>

We can now finish writing our code to take attendance.

<pre>
  // Set up the list of students.
  ImmutableList students = list(...);
  // As long as there are students we haven't called on
  while (! nullp(students)) {
    // Call on the next student
    callOn(car(students));
    // And check that student off
    students = cdr(students);
  } // while
</pre>

These four methods seems to be enough to achieve that goal.  Would
some of the other approaches also have worked?  Possibly.  Should
we explore some of those other approaches?  It can be instructive
to do so, but we will stick with this simple set of methods for
now.

Minimalist vs. maximalist design
--------------------------------

"But wait", you may ask, "what about all the other
things I usually do with lists, like figure out whether a particular
element is in the list, or add things in the middle of the list, or
remove things from other positions in the list, or ...?"
And you likely have good reasons for asking such questions.  After
all, the typical list library has many more methods.

The choice of how many methods to include in your design of an ADT
is a particularly difficult one.  There are many reasons to provide
a wide variety of methods, most importantly that it avoids your
client programmers from having to repeatedly "reinvent the wheel",
as it were.  That is, if you don't provide a `contains` method or
something similar, you'll find that many clients write it, with
varying success.  As importantly, if clients are choosing between
a variety of ADTs, they may be more likely to choose one that
provides all of the methods they need.

For these and a variety of other reasons, many designers strive for
what I term a *maximalist* approach to ADT design: They try to
predict every possible use of the ADT (or at least a large number
of uses) and include methods that support all of those uses.

However, there are some significant dangers in taking a maximalist
approach.  One is that an implementation of an ADT that tries to do too
many things may not be particularly good at the ones be you want to use.
That is, the methods you choose are likely to affect the implementation
you choose, and implementation decisions that make some methods quick
often make other methods slow.  Think about the difference between the
sorted arrays that you may be accustomed to using with binary search
and an unordered array.  Sorted arrays make searching really fast.
However, changing a value in a sorted array typically means that you
have to move the value elsewhere in the array and shift everything,
which will be very slow.  In contrast, if we don't care about the
order of elements in an array, we can change any element in place.
Of course, unordered arrays are much slower to search.  If we limit
ourselves to the most important operations (e.g., searching in one case,
changing values in another), we are more likely to be able to choose an
implementation that supports those operations well.  Over time, you'll
find that you will sometimes design and implement an ADT yourself,
even though it may be included in the standard libraries of language,
simply because you want to make sure that you have the best possible
implementation of the particular set of methods you use in your program.

There's also one other reason you may want to put fewer methods in
the ADT, particularly as a beginning programmer: You'll have to
implement anything you put in the ADT.

For these reasons, I will generally advocate for a *minimalist*
design of abstract data types.  We will strive to include just
enough methods to accomplish the primary goals of the ADT and to
support the use cases we come up with.  If we find that we are
including too many methods, I may even suggest designing separate
ADTs.

A recap and a look forward
--------------------------

What might you have learned in this reading?  I hope that you've started
to understand the PUM (Philosophy, Use cases, Methods) approach to ADT
design.  As we look at each new ADT, whether it's one you've probably
seen before or one we are designing anew, I'll ask you for the main steps
and then ask you to go through each of the steps.  In the end, I hope 
that such practice will give you skill thinking not just about ADTs,
but about object design in general.

But the PUM approach is not the only key idea in this reading.  As
the reading notes, there is not one single correct design for an 
ADT.  Your design is influenced by your choice of use cases, by how
you approach ideas revealed in your use cases, by your experience in
thinking about such issues, and much much more.

Where do we go next?  We have to *implement* these
ADTs.  I tend to treat the concept of "data structures"
as the basic way in which we implement ADTs, although I admit that I
misuse the term a bit.  So let's move on to look at
[how we design data structures](../readings/designing-data-structures).

A disclaimer
------------

For a bit of amusement, I treated `cdr` as meaning "check off data
reference" and `car` as meaning "contents of active reference".
But those are not the real meanings of the acronyms.  Those two
operations are based on assembly-code instructions on a long-dead
machine, and really mean "contents of decrement register" (although
I have also heard "contents of data register") and "contents of
address register".

This is not the place to go into details about how those two
operations relate to lists, other than to note that the name choice
reveals a failure of abstraction: The names discuss the underlying
implementation of conceptual operations, rather than the conceptual
operations.  Since the names were chosen in the very early days of
programming language design, such failure of abstraction is not
surprising.  As we will see repeatedly, it took a long time for
computer scientists to develop the habit of abstracting away from
the details of the implementation.


--- 
title: Anonymous inner classes
summary: |
  We consider Java's "anonymous inner classes" as a programming
  technique.
todo:
  - Rewrite the main example!   (Or not)
---

Introduction: A problem
-----------------------

As you may have noted, in much of our recent work, we've ended up 
creating classes that (a) are used by only one other class or (b)
are effectively used only once.  For example,

* In searching or filtering a list, we need to build predicates to
  guide the search or filter.
* In sorting a list or array, we need to build comparators that determine
  how values are ordered.  Sometimes we can use predefined orderings,
  but at other times we'll need custom orderings.
* We need to build iterators for most of our collection classes.  (This
  is a case in which we'll build multiple copies of the object, but the 
  corresponding class only gets used by one class, and is not very 
  interesting.)

For those used to functional programming, the first two issues
suggest that we might use *anonymous functions*.  For example, in
Scheme, to select all the values less than 10 in a list
of numbers, we might write `(list-select numbers (lambda (val)
(< val 10)))`
or `(list-select numbers (r-s < 10))`.

Java (at least Java up through version 7) does not include functions
as a first-class data type, so it's unlikely that we'd have anonymous
functions in Java.  However, in version 1.1, Java introduced
*anonymous classes*, which can help serve the same purpose.  In
this reading, we explore the usage of anonymous inner classes.

Detour: A simple problem domain
-------------------------------

While we will eventually explore anonymous inner classes in the
context of predicates, comparators, iterators, and more, let's
start with an invented situation that is simultaneously simpler
and more complex than some of these examples.

At times, we want objects that can greet the user.  We'll call
such objects `Greeter`s, and give them a simple interface.

```java
import java.io.PrintWriter;

/**
 * A very simple interface to be used in illustrating anonymous inner classes.
 */
public interface Greeter {
  /**
   * Print a greeting.
   */
  public void greet(PrintWriter pen);
} // interface Greeter
```

Here's a simple class that implements the `Greeter` interface.

```java
import java.io.PrintWriter;

/**
 * A very simple example of a greeter.
 */
public class SampleGreeter implements Greeter {
  public void greet(PrintWriter pen) {
    pen.println("Hello world.");
  } // greet
} // class SampleGreeter
```

Now, let's try to think like Java designers.  Sometimes, given an
object, we might want to make a greeter for that object.  Why doesn't
the object want to be its own greeter?  Maybe we need more than one
greeter, and want each to behave differently, or at least independently.
(In case you couldn't tell, we're reflecting on iterators and
iterables, as well as some other paired interfaces.)  We'll call
things that generate greeters `Greetable`s.

```java
/**
 * A very simple interface to be used in illustrating anonymous inner classes.
 */
public interface Greetable {
  /**
   * Get the greeter.
   */
  public Greeter greeter();
} // interface Greetable
```

Here's a simple implementation.

```java
/**
 * A very simple class that creates a Greeter.
 */
public class SampleGreetable1 implements Greetable {
  @Override
  public Greeter greeter() {
    return new SampleGreeter();
  } // greeter
} // interface SampleGreetable1
```

Introducing anonymous `Greeter` objects
---------------------------------------

You haven't seen anything too surprising so far, or at least I hope
you haven't.  Now, let's move a step further.  Suppose we want to
create a new Greetable that returns a greeter that returns the
string "Hi!".  We could use the same technique as above.  But we're
creating a `Greeter` class that only gets used once.  What's the
alternative?

The alternative is an *anonymous inner class*.  Rather than writing
down the full class definition, we can use the keyword `new`, the
interface name, and then the body of a class.  That is,

```java
import java.io.PrintWriter;

/**
 * A simple class that builds an anonymous greeter.
 */
public class SampleGreetable2 implements Greetable {
  @Override
  public Greeter greeter() {
    return new Greeter() {
      @Override
      public void greet(PrintWriter pen) {
        pen.println("Hi");
      } // greet(PrintWriter)
    }; // new Greeter
  } // greeter()
} // interface SampleGreetable2
```

What's going on here?  It looks like we're calling a constructor for
an interface, and we know that interfaces don't have constructors.
There's also an open brace after the `new Greeter()`, rather than
a semicolon.

Welcome to the world of anonymous inner classes.  If you use `new` with
an interface (and, in some cases, with classes) and then follow it with
a code block, Java treats that as "I'm simultaneously describing a class
and building an object in the class."

Are there any advantages to this approach?  After all, it's not
that much less code than in the previous example.  The most obvious
benefit is that we've achieved fairly strong *encapsulation* - since
the class is within `SampleGreetable2`, nothing else can access it.
We also have the normal benefit of anonymity - if the class is only
used here, there's no need to come up with a name for it.  But these
two reasons alone are not the only reasons we like anonymous inner
classes.

Referencing fields of the enclosing class
-----------------------------------------

Often, when we build helper objects, like `Greeter` objects or
`Iterator` objects, they need to know something about the object
they are helping.  That usually means we need to have the helper
object reference the object they are helping.

Once of the nice things about inner classes is that they can reference
the fields of the enclosing class without explicitly including it
as a field.  In the following example, when we construct the
`Greeter`, we copy and change a field in the enclosing class.

```java
import java.io.PrintWriter;

/**
 * A simple class that creates an anonymous greeter and references an internal
 * field of the enclosing class during creation.
 */
public class SampleGreetable3 implements Greetable {
  int i = 0;

  @Override
  public Greeter greeter() {
    return new Greeter() {
      int num = ++i;

      @Override
      public void greet(PrintWriter pen) {
        pen.println("Number " + num);
      } // greet(PrintWriter)
    }; // new Greeter
  } // greeter()
} // interface SampleGreetable3
```

Suppose we wrote the following.

```java
PrintWriter pen = new PrintWriter(System.out, true);
Greetable g = new SampleGreetable3();
Greeter[] greeters = new Greeter[] {
  g.greeter(), g.greeter(), g.greeter(), g.greeter(), g.greeter() 
};
for (int i = 0; i < greeters.length; i++) {
  pen.print(i + ": ");
  greeters[i].greet(pen);
} // for
```

Our output will be

```text
0: Number 1
1: Number 2
2: Number 3
3: Number 4
4: Number 5
```

Of course, we could achieve the same effect by writing a separate
class and giving it a constructor that takes `i` as a
parameter, but that's a lot more coding.

But we're not done yet!  Not only can we reference the fields of
an enclosing class when we build the inner class, we can even reference
them when we run the method.

```java
import java.io.PrintWriter;

/**
 * A simple class that creates an anonymous greeter that references a field in
 * the enclosing class at the time is built and at the time it greets.
 */
public class SampleGreetable4 implements Greetable {
  int i = 0;

  @Override
  public Greeter greeter() {
    return new Greeter() {
      int num = ++i;

      @Override
      public void greet(PrintWriter pen) {
        pen.println(num + " of " + i);
      } // greet(PrintWriter)
    }; // new Greeter
  } // greeter()
} // interface SampleGreetable4
```

  If we run code similar to the above, we'll get

```text
0: 1 of 5
1: 2 of 5
2: 3 of 5
3: 4 of 5
4: 5 of 5
```

Referencing parameters of enclosing methods
----------------------------------------------

Things are going pretty well.  We can reference a field of the enclosing
class when we build an object in an inner anonymous class.  We can 
reference a field of the enclosing class when someone invokes the
object's methods.  What else would we want to do?  Well, if we are
used to a functional model, we might even want to be able to reference
a parameter or local variable in an enclosing method.  For example,
we might want to write something like the following:

```java
Greeter makeGreeter(int n) {
  return new Greeter() {
    @Override
    public void greet(PrintWriter pen) {
      pen.println(n);
    } // greet(PrintWriter)
  }; // new Greeter
} // makeGreeter
```

However, if we try that, the Java compiler will greet us with a friendly
message something like the following:

```text
SampleGreetable5.java:19: error: local variable n is accessed from within inner class; needs to be declared final
                pen.println(n);
                            ^
1 error
```

What's going on here?  The Java compiler is worried that `n`
gets referenced in a method that may be called when that method is no
longer in scope.  (And we can be pretty sure that it won't be in scope
when `greet` gets called.)  Some languages like Scheme have
a clever way of dealing with these issues.  Java, on the other hand,
doesn't want to have to deal with it.  So, Java is only willing to let
you reference the parameter if it knows that the parameter won't change,
in which case it can just grab the current value.  The `final`
modifier is how you tell Java that it won't change.  With this update,
we can write the following:

```java
import java.io.PrintWriter;

/**
 * A simple class with an extra method that creates an anonymous Greeter that
 * references a parameter.
 */
public class SampleGreetable5 implements Greetable {
  int i = 0;

  @Override
  public Greeter greeter() {
    return makeGreeter(++i);
  } // greeter()

  Greeter makeGreeter(final int n) {
    return new Greeter() {
      @Override
      public void greet(PrintWriter pen) {
        pen.println(n);
      } // greet(PrintWriter)
    }; // new Greeter
  } // makeGreeter
} // interface SampleGreetable5
```

You'll have a chance to explore this particular issue in lab.

What does "`this`" mean?
------------------------------------------------

You'll note that we have not been using `this` in referencing
fields.  That's because `this` can feel a bit ambiguous
for inner classes - does `this` refer to the inner class 
that we're building or to the enclosing class?  Let's try updating
the 4th example to see what the Java compiler tells us.

```java
public void greet(PrintWriter pen) {
  pen.println(this.num + " of " + this.i);
} // greet(PrintWriter)
```

As you might expect, we get another helpful message.

```text
SampleGreetable6.java:17: error: cannot find symbol
                pen.println(this.num + " of " + this.i);
                                                    ^
  symbol: variable i
1 error
```

We've learned a lot from that message.  The compiler didn't complain about
`this.num`, so it seems that `this` refers to the
inner class.  It did, however, complain about `this.i`, and 
so `this` does not refer to the enclosing class.  It turns
out that to refer to the enclosing class, you prefix `this`
with the class name.  

```java
import java.io.PrintWriter;

/**
 * Explicit references to the location of various fields.
 */
public class SampleGreetable6 implements Greetable {
  int i = 0;

  @Override
  public Greeter greeter() {
    return new Greeter() {
      int num = ++SampleGreetable6.this.i;

      @Override
      public void greet(PrintWriter pen) {
        pen.println(this.num + " of " + SampleGreetable6.this.i);
      } // greet(PrintWriter)
    }; // new Greeter
  } // greeter()
} // interface SampleGreetable6
```

Anonymous predicates
--------------------

At this point you may be saying to yourself "Cool!  I can use
this when working with predicates."  Or you may be asking 
yourself "How can I use this in practice?"  (Guess what,
one answer is "with predicates".)  So let's do a quick
exploration of why anonymous inner classes might be useful for
predicates.

We'll start with a sample use of predicates, printing all of the
values in an array for which the predicate holds.

```java
/**
 * Print all the elements of the array for which pred holds.
 */
public static <T> void printMatching(PrintWriter pen, T[] vals, Predicate<T> pred) {
  for (T val: vals) {
    if (pred.test(val)) {
      pen.println(val + " ");
    } // if
  } // for
  pen.println();
} // printMatching(PrintWriter, T[], Predicate<T>)
```

Now, suppose we have an array of strings and want all of the short
strings (say the strings of length less than or equal to 4).  We could 
write something like the following:

```java
Predicate<String> small = new Predicate<String>() {
  @Override
  public boolean test(String str) {
    return str.length() <= 4;
  } // test(String)
}; // new Predicate<String>
```

Now we can print the small strings in our test with something like the
following.

``java
  printMatching(pen, essay, small);
```

That seems much easier than defining a whole new class, doesn't it?

Concluding thoughts
-------------------

Like anonymous functions and named lets in Scheme, anonymous inner
classes are powerful and convenient for experienced programmers and
a bit overwhelming for novice programmers.  Take it slowly, and you'll
soon find that anonymous inner classes will be a useful tool for many
situations.

As we noted at the start of this reading, anonymous inner classes were
added to Java long before things that resemble anonymous functions ("lambdas", 
in common Java parlance).  In a bit, you'll learn about lambdas in Java
and when they can and cannot be used to replace anonymous inner classes.

Acknowledgements
----------------

Most of this reading is closely based on
[a similar reading from fall 2014](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2a014F/readings/anonymous-inner-classes.html).  I've reformatted
the code and rewritten some text.

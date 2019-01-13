---
title: Simple Java for C Programmers
summary: |
  We take a quick tour through some imperative parts of Java and how
  they relate to what you may know from learning the C programming 
  language.
---

Introduction
------------

Java is a hybrid language.  While it has significant object-oriented
features, it also has many imperative features.  That is, while you
will be building objects and taking advantage of encapsulation,
polymorphism, and inheritance, you will also write programs in which
you sequence instructions and update state through assignment.  (Java
also has some functional features; we'll talk about those a bit later.)
You may note that Java looks a lot like C.  That's intentional.  The
designers of Java thought they would attract more programmers if they
started with a familiar basis.

If you're willing to ignore the object-oriented features and to 
put up with a bit more stuff to type, Java can be a reasonable 
alternative to C for imperative programming.  Why is it better?
You still have some opportunity for information hiding, and it
is much stricter about types, which helps you catch errors 
earlier.  For example, Java distinguishes Boolean values from integers,
and won't let you use one for the other.  (Yes, that's right, the
Java compiler won't let you write the incorrect "`while
(i = 0)`" rather than the intended "`while
(i == 0)`"

Classes and the `main` method
-----------------------------

Java requires that all of your code reside in *classes*.
While classes are primarily intended as templates for making objects,
they also gather together related functions and variables.  Hence,
even if all you want is a `main` method, you will need
to create a class to hold it.

```java
public class MyClass {
  public static void main(String[] args) throws Exception {
    // ...
  } // main(String[])
} // MyClass
```

As you probably noted, the "signature" of `main` is a bit different
in Java than it is in C.  As in C, Java's `main` takes an array of
strings as parameters.  But Java uses the `String` type (or, more
formally, `java.lang.String`) rather than C's `char *`.  In addition,
in Java you can find out the length of any array, so we don't need
to take the length as a parameter.

Since Java is designed, in part, to allow the programmer to protect
pieces of code from other pieces of code (that's part of encapsulation),
you almost always declare the protection level of a method.  If you
don't, Java has a default protection level of "this method may be
called by any other method in the same package", which I will refer
to as "package protection".  Since the operating system will be
starting our program by calling the `main` method, we must make
`main` public.

Java distinguishes between methods that are associated with the
objects in a class and the methods that are associated with the
class as a whole.  The designers of Java chose the keyword `static`
to indicate that a method (or field) is associated with the class
rather than with individual objects.  (The name is somewhat sensible;
objects live in dynamic memory, but static methods can live in
static memory.)

In C, the return type of `main` is `int`.  That's because C returns
information about success or failure using that integer.  (If you
recall, in this case, 0 means "success" and anything else represents
some sort of error.)  In Java, a program signals error in a different
way, and so there is no need to return a value.  Hence, the return
type is `void`.

Finally, that bit of code that says `throws Exception` is our way
of signaling that the program can fail.  You'll learn about throwing
exceptions later.

Coding conventions
------------------

Programmers have strong opinions on how code should be formatted.
Such conventions dictate everything from capitalization and naming
of variables to indentation and placement of braces and parens.
While there are a variety of conventions in Java, almost all of them
share the following capitalization choices.

* Variable names and field names generally start with a 
  lowercase letter and use CamelCase (mixed upper and lower, 
  no underscores).
* Function names also start with a lowercase letter and use CamelCase.
* Class names start with a capital letter and use CamelCase.

We will follow those conventions.  For indentation and other
conventions, please follow the [Google Java Style
Guide](https://google.github.io/styleguide/javaguide.html).

Types
-----

Java, like C, has basic numeric types, including `int`, `short`,
`long`, `float`, and `double`.  Unlike C, Java carefully specifies
what representation and ranges are used for each of these.  (You
can find those specifications online if you look carefully.)

Java also has the `void` type (nothing), and the `char` type (Unicode
characters, I believe).

Java does not have pointers.  (It has references, but it does not
let you reference and dereference like you can in C; it does not
let you get an address.)  Java's strings are therefore their own
type, `String` or `java.lang.String`.

Java also provides a `boolean` type for truth values, along with
the values `true` and `false`.

Declaring functions
-------------------

As you've seen in the case of `main`, declarations of basic functions
(well, methods) in Java require three pieces of additional information
in addition to the method name, return type, and parameter list you
used in C.

* A *protection level*.  In addition to `public` (accessible anywhere)
  and nothing (accessible only via the package), you can make methods
  `private` (only accessible from within the class) and `protected`
  (only within the package and in subclasses).  We'll consider
  protection levels in the future.  For now, feel free to use either
  `public` or nothing.
* An optional `static` specification.  Until you start creating you 
  own template classes, you'll need to make *all* of your methods static.
* An optional `throws` clause to indicate possible errors.
  Until we cover throwing and catching exceptions, you will find it
  easiest to indicate that any method you write throws an exception.

For example,

```java
    /** 
     * Compute the square of x.  
     *
     * @exception Exception 
     *   when the square is larger than the largest int.
     */
    public static int square(int x) throws Exception {
      // ...
    } // square(int)

```

Declaring variables
-------------------

As you may recall, in C there are two primary kinds of variables:
*global* variables, which are available to any procedure in your
program, and *local* variables, which are only available within
your procedure.

You will find that local variables in Java are much like local variables
in C.  That is, you declare them by giving the type and the name of
the variable.

```java
    long result;
```

One advantage of Java over C (or at least earlier versions of C)
is that you can declare local variables at multiple places in your
procedures, including in the initialization part of a for loop.
See the section on loops for more info.

Global variables are a bit more complex.  Once again, you declare
them by giving the type and name.  But you can also add a protection
level like those you use for methods - `public`, `protected`, nothing
("package"), or `private`.  These variables are also a bit less global
than C global variables as each variable must be associated with a class.

```java
  /**
   * A count of the number of errors encountered during testing.
   */
  private int errorCount = 0;
```

Conditionals
------------

Java provides if-then-else and switch statements that essentially
mimic C's statements.

Loops
-----

Java provides while loops, do-while loops, and for loops that look
much like what you've seen in C.  One big difference with these loops
is that you can declare variables in the loop header.  Hence, while
C programmers declare their counter variables in one place and then
use them elsewhere, in Java, programmers tend to write things like
the following:

```java
  for (int i = 0; i < MAX_VALUE; i++)
```

C99 programmers also write for loops that put the declaration in the
loop header.  But many (most?)  C programmers stick to an earlier
version of C and leave the declaration outside of the foor loop.

Java also provides a special loop that makes it much easier to access
every element in a list or array.  (More formally, to access every
element in a collection that supports iteration.)

```java
  for (s : args) ...
```

The one initial change you'll see in your programming is a tendency
to declare variables within the initialization segments of a for loop.

Library code
------------

In C, you need to steps to import library code.  You use `#include` with
a `.h` file to tell the compiler about the specifications for that library.
You must then link your object code to that library code.

In Java, you just mention the library code and the compiler
automatically figures out how to bring it in.  Traditionally, you
give the full name of the library, such as `java.io.PrintWriter`.

Suppose `Util.java` was declared as follows.

```java
package edu.grinnell.csc207.utils;

public class MathUtils {
  public static int square(int x) throws Exception {
   ...
  } // square(int)
} // class MathUtils
```

To call `square`, you would write

```java
  edu.grinnell.csc207.utils.MathUtils.square(...)
```

Note that if we did not put `MathUtils` into a package, we could
just use `MathUtils.square(...)`.  Packages also add some complexities
about where you put files.  Hence, we will likely start your Java
experience without packages, but quickly add them so that you can
get used to organizing code in more sensible ways.

For some library code, particularly using standard libraries, you'll
need to create objects.  For example, to use a `PrintWriter`, I
need to write

```java
  java.io.PrintWriter pen = new java.io.PrintWriter(System.out,true);
```

But that's a lot of extra work.  Hence, Java also lets you "import"
classes using the `import` keyword.  If you've imported classes,
you need only provide their base name, and not the full qualification.

```java
import edu.grinnell.csc207.utils.MathUtils;
import java.io.PrintWriter;

  MathUtils.square(...)
  PrintWriter pen = new PrintWriter(System.out, true);
```

Input and output
----------------

Java provides a host of input and output mechanisms.  While you'll
see a lot of sample code that uses `System.out` to print values, I
prefer that you use the object-oriented ouptut mechanisms.

To print output, first create an object of class `java.io.PrintWriter`.

```java
import java.io.PrintWriter;
// ...
    PrintWriter pen = new PrintWriter(System.out,true);
```

You can then print using that object's `print` and `println` methods.
(There's also a `printf` method that works much like C's `printf`.)
You may also find it useful to use the `flush` method and to `close`
the PrintWriter when you are done with it.

```java
    pen.println("Hello");
```

For input, you probably want to use a `java.io.BufferedReader`,
which you typically create with something like:

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
// ...
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));
```

You can then use the `readLine` method to read input.

```
    pen.print("Please enter your name; ");
    pen.flush();
    String name = eyes.readLine();
```

If you want more sophisticated input, you can use `java.util.Scanner`.
Scanners "tokenize" the input by breaking it up into small pieces,
most often at spaces.  Most frequently, you'll build the Scanner
from standard input.  using `new Scanner(System.in)`.  You can then
read each token as a particular type (e.g., if you want an integer,
you can use `nextInt()`).  Since the next token might not have the
right format for the type, Scanners also provide methods like
`hasNextInt()` that tell you whether or not you can read the
appropriate value.

```
import java.util.Scanner;
// ...

   Scanner in = new Scanner(System.in);
   boolean done = false;
   int i = 0;
    while (!done) {
      pen.print("Please enter an integer: ");
      pen.flush();
      if (!in.hasNextInt()) {
        String tmp = in.next();
        pen.println("'" + tmp + "' is not an integer.  Try again.");
      } else {
        i = in.nextInt();
        done = true;
      } // endif
    } // while
    pen.println(i + "^2 = " + i*i);
    pen.flush();
```

Arrays
------

We'll consider arrays in [a subsequent reading](../readings/arrays).


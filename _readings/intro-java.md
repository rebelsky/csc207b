---
title: An Introduction to Java
summary: |
  We introduce the basics of
  the Java programming language and walk through the first simple program.
prereqs: |
  Basic understanding of object-oriented concepts.
link: true
---
Expectations of object-oriented languages
-----------------------------------------

Now that you know the basics of object-oriented programming, there are
particular characteristics you should look for in any object-oriented
language, including ways to define classes and objects, some protection
mechanisms, and support for inheritance and polymorphism.  Different
languages naturally take different approaches to these issues, both in
the *syntax* that describes how one writes programs
and in the underlying semantics of how objects and classes relate.

There are also some things you may not have thought about, such as the
way in which one invokes a "program" in the particular
language.  In some languages one simply adds new classes and objects
to an existing "world", often through a graphical
user interface.  Other languages require you to type commands in a
textual user interface.  Still others can "automatically"
determine which objects to start (e.g., because an object is embedded
in a Web page).

Classes, object creation, inheritance, polymorphism, and program instantiation
are more subjects than we can cover in a short reading.  Hence, in this
reading we will focus on three basic issues in Java: 

* How to set up a basic Java program.
* How to create and name objects.
* How to tell objects to do things.

Setting up Java programs: Your main class
-----------------------------------------

Java provides a variety of mechanisms for indicating which objects
provide the main components of your program.  The simplest, and
the one we will use for most of this course, is what we typically
call a "Main Class".  Such a class is a class that
typically contains only one method, called `main`.
The `main` method contains a series of instructions to
create objects and to tell those objects to do things.

That theory is fairly simple.  The syntax, however, is daunting to many
novice programmers.  Here is a sketch of a typical Main class.

```java
public class NAME_OF_CLASS {
  public static void main (String[] args) throws Exception {
    INSTRUCTIONS
  } // main(String[])
} // class NameOfClass
```

It is the programmer's responsibility to choose an appropriate name for 
the class and the instructions for the task at hand.  Tradition suggests
that the name of a class begin with an upper-case letter and use mixed
case.

What else is in this sketch?  The outer braces enclose the contents
of this class, in this case, just the main method.  You will see braces
enclosing other things in future readings.  The inner braces enclose the
instructions for the `main` method.  

You'll notice two slashes and a note after the end-braces.  Text
that begins with two slashes is a *comment* for the programmer or
reader.  My experience suggests that you should always comment your
end braces to indicate what you're ending.  Such comments help you
make sure that your code is structured appropriately and helps the
reader more quickly parse your code.  You may (and should) also
insert comments to explain key parts of your code.

You can probably guess what purpose the words that preface *NameOfClass*
serve.  The `public` indicates that the class can be used by any
program or user (or at least any program or user that has system
permission to read the file containing the class).  The `class`
indicates that we're defining a class.

The slew of stuff surrounding `main` is perhaps more confusing.  If
you are willing to live with "just write what you see above", you
need not learn the details right now.  Feel free to skip ahead to
the next section.  However, if you'd like a quick overview, read
on.

You might be able to guess that the `public` means that the method
named `main` is generally accessible (again, provided the person
or program attempting to access it has system permission to access
the associated file).

The `static` indicates that the method can run without having
an associated object created.  You are probably used to running methods
(procedures) without having associated objects.  However, in Java, one
typically associates a method call with a particular object that is to
execute the method call.  Hence, we must indicate that `main`
is an exception to the rule.

In Java, all methods potentially return values.  Programmers therefore
have a responsibility to indicate what kind of value the methods return.
(The designers of Java have found that programs behave more reliably
if the compiler can tell the types of values and check to see that
the programmer is using them consistently.)  We don't want to return
any particular value from this method (we just want it to run), so we
give it return type `void`.

The `String[] args` in parentheses provides the one parameter to
the `main` method, all the strings typed on the command line.  This
parameter is an artifact of Java's designers decision to emulate
some key aspects of the C programming language.  We will return to
it after we learn more about arrays.

Finally, the `throws Exception` is our admission that our
method can fail.  Java expects programmers to indicate when and how
their methods can fail.  As you become a more careful and responsible
programmer and can guarantee that your methods will not fail, you can
possibly remove this line.

Naming objects
--------------

What kinds of things belong in the body of `main` (and in any
other methods you decide to write)?  A few basic things.  First, you
often include *declarations* of names for objects.
The declaration of a name provides the type of object the name will
refer to and the text of the name.  The general form is

```java
TYPE NAME;
```

For example, I might say that `pen` will name objects in
class `java.io.PrintWriter` with

```java
    java.io.PrintWriter pen;
```

Similarly, I might say that `i` is an integer (in Java-ese, 
an `int`) with

```java
    int i;
```

Note that the semicolon at the end of the declaration is necessary.  
You will receive strange errors if you fail to include that semicolon.

Once you have declared the name for a kind of value, you can make that
name refer to a particular value using the assignment operator (an
equals sign).

```java
    NAME = EXPRESSION;
```

Creating New Objects
--------------------

In many cases, particularly at the beginning of the `main` 
method, the expression at the right-hand side of the assignment 
creates a new object.  The form of an expression that creates a new
object is most typically

```java
new NAME_OF_CLASS(PARAMETERS)
```

What class names can you use?  You can use any class that you've defined
(none, yet), any class that someone has provided for you or that you've
downloaded, or any class that comes as part of a standard Java installation.

What parameters do you give when you create a new object?  It depends
on the kind of object you're creating.  You will need to check
the documentation for the object to determine what parameters are
appropriate.

Using Named Objects
-------------------

You've created an object.  You've named that object.  What else can
you do with the object?  You can tell it to do something.  (Some
programmers say that this is "calling a method" others say it is
"sending a message to the object".)  The typical form is

```java
    NAME.METHOD(PARAMETERS);
```

Useful Classes
--------------

For many of your programs, you will find the following classes useful:

[`java.lang.String`]({{ site.java_api }}/java/lang/String.html)
  : Sequences of alphanumeric characters.  You can represent strings
    explicitly by surrounding the characters with double-quote marks.

[`java.io.PrintWriter`]({{ site.java_api }}/java/io/PrintWriter.html)
  : The simplest mechanism for output in Java.  PrintWriters provide
    `print` and `println` methods.  You can create PrintWriters
    with `new java.io.PrintWriter(System.out, true)`.  You can also
    create new PrintWriters with `new java.io.PrintWriter(FILE)`.

[`java.io.File`]({{ site.java_api }}/java/io/File.html)
  : Files for use in `PrintWriter` and similar objects.  You can
    create a new File with `new java.io.File(PATH_TO_FILE)`.  For
    now, you will not use files directly.  Instead, you will create
    them as parameters for PrintWriters and other classes.

[`java.io.BufferedReader`]({{ site.java_api }}/java/io/BufferedReader.html)
  : One of the simple mechanisms for input.  You will often created
    `BufferedReaders` in a multi-step process, first creating a
    reader and then building a `BufferedReader` from it.  For
    standard input, you can use `new java.io.BufferedReader(new
    java.io.InputStreamReader(System.in))`.  For a file, you can
    use `new java.io.BufferedReader(new  java.io.FileReader(FILE))`.
    `BufferedReader` objects provide a `read()` method to read a
    single character and a `readLine()` method to read a full line
    of text.  They also provide some more sophisticated procedures
    for marking points in the input and backing up to those points.

[`java.util.Scanner`]({{ site.java_api }}/java/util/Scanner.html)
  : A more sophisticated input mechanism.  Scanners "tokenize" the
    input, most frequently at spaces, and let you read in a token
    at a time as a particular type.  You can build a new `Scanner`
    object that reads from standard input with 
    `new java.util.Scanner(System.in)`.  You can also read from
    a file or even from a string.  The `next()` operation returns
    the next token as a string.  The `nextInt()`, `nextDouble()`,
    and `nextFloat()` methods return the next token in the
    obvious type.  

Putting it all together: Your first program
-------------------------------------------

We now know all the basic components of a Java program, so we can
write a typical "first program" which writes a greeting
to the screen.  (This one is slightly more complex than one you may
have seen previously, but it also does a better job of revealing
some basic Java issues.)

```java
public class First {
  public static void main (String[] args) throws Exception {
    // Describe the two names of objects used in the program.
    java.io.PrintWriter pen;
    java.lang.String greeting;
    // Create objects and associate the names with those objects.
    pen = new java.io.PrintWriter(System.out, true);
    greeting = "Hello";
    // Tell one object to do something.
    pen.println(greeting);
  } // main(String[])
} // class First
```


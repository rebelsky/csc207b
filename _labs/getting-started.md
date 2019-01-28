---
title: Getting started with Java
summary: |
  In this lab, you'll explore some basics of Java programming using
  commands in the shell.  (We'll soon switch to the Eclipse IDE.)
copyright:  Copyright &copy; Peter-Michael Osera and Samuel A. Rebelsky.
copyright_message: |
  This work is licensed under a Creative Commons Attribution-NonCommercial
  4.0 International License.  To view a copy of this license, visit
  <http://creativecommons.org/licenses/by-nc/4.0/>.
link: true
---

In the spirit of one of the meta-learning goals of the course, we'll
use these first weeks not just to learn the Java programming language
but also the refine our skills at *learning new programming languages*.
The jump from C to Java is no where near as dramatic as the jump
from Racket to C, so we can use this opportunity to develop some
best practices for migrating from language to language whether it's
from Java to C#, Java to Python, or to some more exotic languages.

## Part 0: Preparation

We'll be using Java 11 in this course.  Java 11 is not the default
Java, so you will have to update your `.bashrc` to specify the path
to Java 11.  

a. Open a terminal window.

b. Type `which java`.  You should see something like the followwing

```shell
$ which java
/usr/bin/java
```

c. Edit the file `~/.bashrc` (aka `/home/username/.bashrc`) and add
the following line to the end.

```text
export PATH=/usr/lib/jvm/jdk-11.0.1/bin:$PATH
```

d. In the terminal window, type `source ~/.bashrc`.  This reloads
your updated .bashrc file.

e. In the terminal window, type `which java` again.  This time, you should see something like
the following.

```shell
$ which java
/usr/lib/jvm/jdk-11.0.1/bin/java
```

f. In the terminal window, type `java --version`.    You should see
something like the following.

```shell
$ java --version
java 11.0.1 2018-10-16 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.1+13-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.1+13-LTS, mixed mode)
```

 ## Part 1: Basic compilation pipeline

When learning a new programming language, our first concern before
worrying about how the programming language operates, *i.e.*, its
*semantics*, is how to get stuff to appear on the screen---anything!
Imagine the computer program development process as a *pipeline*,
a series of steps where the end result is a computer program.  For
Racket, the pipeline was very straightforward:

```text
Type definitions        Use those definitions
into DrRacket's   --->  by typing expressions
definitions pane        into DrRacket's
                        interactions pane
```

which is part of the reason we choose Racket/Scheme for CSC 151!
C is a little bit more involved.  In it's simplest version, the
C workflow is as follows.

```text
Write complete        Compile the        Run the
C programs in   --->  program      --->  program
a text file           using gcc
```

More frequently, we include at least one other step.

```text
Write parts of        Compile the        Link the            Run the 
C program in    --->  parts to .o  --->  parts and any --->  program
text files            with gcc           libraries
```

C doesn't have an interactive environment (commonly known as a
*REPL* or a read-eval print loop) to try our C commands or expressions.
Instead, we must write complete programs, compile them using a
*compiler*, and run the resulting executable.

You may have recalled initially having difficult getting a program
to work because you messed up one of these steps---for example,
getting the syntax of a complete program wrong, not having your
source files in the correct place, or invoking `gcc` with the wrong
parameters.  But once you had that template of a basic program and
the series of commands you needed to invoke, you were set!

Being a descendent of C, Java's pipeline is nearly identical to the
basic C pipeline.

```text
Write complete          Compile the        Run the
Java programs in  --->  program      --->  program
a text file             using javac        using java
```

In fact, rather than using `gcc`, you simply use the `javac` program instead which compiles Java programs.
However, unlike `gcc`, the `javac` program produces a *Java class file* as output, a file with a `.class` extension.
This is not a standalone program like what `gcc` produces.
It is a file that contains *Java bytecode* which is your code in a low-level form that the *Java virtual machine* can execute.
The Java virtual machine is located in the `java` program which we can point at a `.class` file to run it.

For example, here is an example workflow for compiling at running the canonical "Hello World!" program:

```{:.shell}
$> ls
HelloWorld.java
$> javac HelloWorld.java
$> ls
HelloWorld.class        HelloWorld.java
$> java HelloWorld
Hello World!
```

Note that when passing the `.class` file to the `java` program, you
do not specify the extension.  `java` looks for a file called
`HelloWorld.class` for you!

---

**(Hello World!)**.  With this in mind, write the "Hello World!" program in Java, compile it, and run it to verify that everything works!

---

### Exploring the negative space

When trying out a new language, you'll run into plenty of errors and mistakes.
This is helpful because while you might burn more time than you'd like fixing those problems initially, they become trivial to fix if you see them in the future ("oh, I recognize this error message from before---you just need to do this to fix it...").
However, once you've established your basic programming pipeline, it's a good idea to explore the space and intentionally try to break it in various ways.
Because you are starting from a good pipeline, you can diagnose the error immediately on top of knowing exactly what you did to cause it!

---

Answer this following set of questions by playing around with your working `Hello World!` program:

1. **(File extensions).**
   Is it necessary to use .java extension for a source file?
   If not, what sort of error do you get when you use a different extension?
2. **(Missing files).**
   What happens if you specify a source file that does not exist or exists elsewhere on disk?
3. **(.class and the java program).**
   What happens if you specify the program-to-run to `java` with the `.class` extension?
4. **(Code formatting).**
   Is Java whitespace-sensitive (*i.e.*, do spaces and newlines matter)?
   Case-sensitive (*i.e.*, is `main` different from `Main`)?
5. **(The main function).**
   Speaking of which, what happens if the signature of `main` is not exactly as presented in the reading, *e.g.*, different function name, return type, or argument name?
5. **(Necessary boilerplate #1: classes).**
   The biggest visual difference between C and Java source code is that Java functions must be housed within a class.
   What happens if you write a free-floating function, *i.e.*, a function not declared within a class?
6. **(Necessary boilerplate #2: public and static).**
   The other major difference is the presence of `public` and `static` on the class and function declarations.
   Which of these `public` and `static` keywords can you remove?
   For the `public` and `static` keywords you can't remove, what errors do you get?

---

## Part 2: Building up your bag of programs

With a basic programming pipeline established, you are now in the position to begin writing real programs.
Many programmers break down what they can do in a programming language into two buckets:

1. What they can do with the language itself.
2. What they can do with the language's libraries.

For solving more interesting problems, we'll need external libraries (either the built-in libraries or some third-party libraries), for example, to perform file I/O or create graphics.
But it is worthwhile to tackle the two buckets independently.
In particular, learning what primitive operations the language provides gives you insight into how you should model your problems and structure your solutions.

Again, Java is a descendant of C, so much of these primitive operations are carried over without any changes.
In particular, minus some slightly different syntax and subtly different semantics (which will be exposed as we write more Java):

* Basic types,
* Variable declarations and assignments,
* Basic expressions and statements, and
* Function declarations.

are identical between Java and C.

With this in mind, try writing a program that solves the following problem:

---

**(Fizzbuzz).**  Write a function `fizzbuzz(PrintWriter pen, int n)` that takes an integer `n` and prints the integers from 0 to `n` (inclusive), one integer per line, using the specified `PrintWriter`.
However:

* When `n` is a multiple of 3, print `fizz`,
* When `n` is a multiple of 5, print `buzz`, and
* When `n` is both a multiple of 3 and 5, print `fizzbuzz`.

You should write this program in a Java file called `Fizzbuzz.java`.
Your `main` function should demonstrate the results of calling `fizzbuzz(100)`.

---

The `fizzbuzz` problem is an example of one of the standard programs PM tries to write whenever he learns a new language.
It's ideal for this purpose because:

1. It is a short program to write, yet is complex enough to be non-trivial.
2. It tests the language's expressiveness.
   In other words, how does one express repetitive and conditional behavior?

Over the course of the next few weeks, we'll be working through some of the canonical programs that PM likes to write when learning a new language.
Feel free to add these to your arsenal whenever you pick up a new language, too!

---

## Part 3: Arrays in Java

We should also explore some of the more significant, heavyweight features of Java and how they improve over what C provides.
One example of this is the `array` which is a data structure that holds a *homogeneous* (*i.e.*, same type), fixed-size collection of values.
When working with a new type of data, you should always ask yourself the following two questions:

1. How do I *create* values of this type?
2. How do I *use* or *consume* values of this type?

Luckily, the array syntax is largely identical to C:

* The type of an array that holds values of type `T` in Java is `T[]`, *e.g.*, `int[]` for an array of integers.
* To create a new array, we use either an *array literal* or a *new* expression passing in the size of the array.

For example, the following code snippet creates an array of 5 elements.
The first initializes the array:

```java
    int[] arr1 = { 0, 1, 2, 3, 4 };     // An array literal
    int[] arr2 = new int[5];            // A "new" expression
```

Array indexing (`arr1[0]`) works identically to C.
Finally, one nice convenience is that Java arrays, unlike C arrays, know their own length:

```java
    pen.println(arr2.length);    // Prints 5
```

With all this in mind, try writing some code to answer the following questions:

---

1. **(Initialization).** Perhaps the largest departure from C is that the following code snippet in C

```c
int arr[5];
```

is how you declare an array with five elements in C.
Note that there is no array literal or new expression present.
What happens if you try this with Java? *i.e.*, declare a variable with an array type, do not use an array literal or new expression to initialize it, and then use that array.

2. **(New Expressions).**  Note that the array literal allows you to specify the contents of the array (if you know them at compile time).
What value(s) does the `new` expression use to initialize each element of an array?

3. **(Out-of-bounds).**  Recall that with C arrays, you are free to walk off the end of the array into arbitrary parts of memory (because an array is morally a pointer)!
Can you do this in Java?
If not, what error(s) do you get when you try to do this?
Do the errors happen at compile time, or runtime?
Can you think of a legitimate use case for walking off the end of an array?

---

Next, exercise your array manipulation skills on these problems.
For all of these problems, write your solutions in a class called `ArrayProblems`.

---

**(Max)**.  Write two functions, `min(arr)` and `max(arr)` that both take an array of integer as input and returns the minimum value and maximum value of the array, respectively.
Your `main` method should demonstrate that your two functions work over the following array `{3, 7, -10, 2, 9, 1}`.

**(Range)**.  Write a function, `range(arr)`, that returns the range of the given array of integers.
The range of an array is the difference between its minimum and maximum value.
Your `main` method should demonstrate that your function works over the following array `{3, 7, -10, 2, 9, 1}`.

**(Rev)**.  Write a function, `rev(arr)`, that takes an array of integers as input and mutates the array so that its elements are reversed.
Your `main` method should demonstrate that your function works over the following array `{3, 7, -10, 2, 9, 1}`.

**(Longest Increasing Subsequence)**.  Write a function `longestIncreasingSubsequence(arr)` that returns the *size* of the longest increasing subsequence found in the given array of integers.
For example, if the input array is `{3, 7, -10, 2, 8, 9, 5, 1}`, then the function returns `4` corresponding to the sub-sequence `{-10, 2, 8, 9}`.
Your `main` method should demonstrate that your function works on this sample array.

**(Fib).**  Write a function `memofib(n)` that takes an integer `n` and returns an integer array of size `n` where the *i*-th element of that array contains the *i*-th fibonacci number.
For example, `memofib(10)` should return an integer array containing the elements `{1, 1, 2, 3, 5, 8, 13, 21, 34, 55}`.
*Use a loop to do this computation instead of recursion.*
Your `main` function should demonstrate the results of calling `memofib(10)`.
Why is a loop preferable to a recursive approach to computing `fib`?

---

## Bonus: Project Euler

For additional problems to help you exercise a new programming language, we recommend checking out [Project Euler](https://projecteuler.net) which is a repository of math-based programming problems for you to solve.
If you have gotten to this point, feel free to head over to Project Euler and try writing Java programs to solve the first couple of problems!

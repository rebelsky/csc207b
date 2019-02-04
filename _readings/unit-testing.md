---
title: Unit testing and JUnit
summary: |
  We consider "unit testing", an approach to software development
  and to the verification of computer programs.  We also introduce
  JUnit, a unit testing framework for Java.
---

Introduction
------------

Most computer programmers strive to write clear, efficient, and correct
code. It is (usually) easy to determine whether code is clear. With some
practice and knowledge of the correct approaches, one can determine
how efficient code is (or should be). However, it is often difficult
to determine whether or not code is correct.

The gold standard of correctness is a formal proof that the
procedure or program is correct. However, in order to prove a program or
procedure correct, one must develop a rich mathematical toolkit and devote
significant effort to writing the proof. Such effort is worth it for
life-critical applications, but for many programs, it is often more than
can be reasonably expected.

There is also a disadvantage of formal proof: Code often changes and the
proof must therefore also change. Why does code change? At times, the
requirements of the code change (e.g., a procedure that was to do three
related things is now expected to do four related things). At other times,
with experience, programmers realize that they can improve the code by
making a few changes. If we require that all code be proven correct, and
if changing code means rewriting the proof, then we discourage programmers
from changing their code.  (Automated proof systems can help with all
of this, but they are beyond the scope of this course.)

Hence, we need other ways to have some confidence that our code is
correct. A typical mechanism is a test suite, a collection of tests
that are unlikely to all succeed if the code being tested is
erroneous. One nice aspect of a test suite is that when you make
changes, you can simply re-run the test suite and see if all the
tests succeed. To many, test suites encourage programmers to
experiment with improving their code, since good suites will tell
them immediately whether or not the changes they have made are
successful.

What should a test suite look like?  First, it should be as
*comprehensive* as possible.  That is, it should poke into the nooks
and crannies of the code, looking at not only special cases, but
also a variety of general cases.  Second, it should be as *automatic*
as possible.  Evidence suggests that programmers are less likely
to take the time to test "by hand" or to compare actual output to
expected output.  (Computers are also really good at comparing
things, so why would you ask a human to do so?)  An ideal test suite
is easy to run (usually just one button), and gives either a quick
affirmation that all tests passed or a summary of what tests failed.
(Some only tell you the first test that failed, which is useful,
too.  You shouldn't use code that doesn't pass all of its tests.)

Testing is also a core component of many agile software development
methodologies.  Agile programmers have found that writing tests early
helps programmers think about what they want their code to achieve and
having convenient and comprehensive tests gives programmers confidence
to try new approaches.

Most agile testing starts with a concept called "Unit Testing".
In unit testing, you focus on testing the small units of a program -
individual procedures, objects, and perhaps small sets of cooperating
objects.  While you will eventually need to test the combination of
smaller units, making sure that your basic units work correctly is
an important place to start.

Most tests resemble the things you would do by hand - When I give
this procedure *this input*, do I get *this output*?  You'd probably
run experiments like that while developing your code, so why not
document them as code?  It doesn't take long (in most cases, less
time than it would to run the program and compare answers by hand).
You can also take advantage of the computer to run more tests and
to automate tests.  For example, if you were writing a square root
procedure, instead of just checking that the square root of 4 is 2
and the square root of 100 is 10, you can confirm that the square
root of the square of i is i for every i from, say, 1 to 100.

Unit testing and JUnit
----------------------

One of the first unit testing frameworks was SUnit, designed for
the SmallTalk language.  It was soon ported to Java, as JUnit.
It remains one of the most popular unit testing frameworks for Java,
and so it will be the framework we use in this course.

 JUnit provides most of what you want in a testing framework:

* It's easy to run a series of tests once you've specified them.
* It's easy to group test suites into bigger test suites.
* You can test a variety of behaviors, not just "are these
  two things equal", but also "does this code throw
  an exception" and such.
* You can easily scaffold your tests, setting up some objects and
  such before each test.
  
Using JUnit in Eclipse
----------------------

We also use JUnit because it is integrated into Eclipse.

To create a new JUnit test for a class in Eclipse, right click the
class that we want to test then choose **New** > **JUnit Test Case**.
A "New JUnit Test Case" window should appear. Once there, click the
**Next** button.  You can now select the methods that you want to
test.  After that, click the **Finish** button.  You now have a
test class, typically in the same package as your original class.

Within the test class that you just made, there will be methods
that are prefixed with the word `test` and the annotation `@Test`.
These are what you will use to test each method you have chosen.
At the moment they all should have a simple body, something like
the following.

```java
        fail("Not yet implemented");
```

We will replace this line with our test code.  To start with, we'll
use the `assertEquals` method, which takes three parameters the
expected value, the expression to test, and a string the describes
the test.  (Note: JUnit 4 put the description first.)  For example, if
I've written a method called `average` in the `Math` class, I might
do a simple test with

```java
        assertEquals(2, Calculator.average(1,3), "averaging 1 and 3");
```

While `assertEquals` will be our primary tools,
JUnit provides a variety of other kinds of assertion tests.  You
can read documentation at 
<https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/Assertions.html>.

You can put as many assertions in a test as you want, but JUnit
will only tell you if they all succeeded - the first one that fails
will trigger an error.

You may also find it useful to split your test into multiple methods.
As long as you annotate each with `@Test` (and, in some
versions of JUnit, make sure the procedure name starts with 
`test`), JUnit will run them.  But even if you have multiple
methods, JUnit will generally still stop with the first one.

For example, suppose we want to test this class.

```java
package csc207.math;

/**
 * A simple set of mathematical methods for testing with JUnit.
 */
public class MyMath {
  /**
   * Compute the average of two integers, rounded down.
   *
   * @param left
   *   One of the two integers
   * @param right
   *   The other integer
   */
  public static int average(int left, int right) {
    return (left + right) / 2;
  } // average(int,int)

  /**
   * Determine if a number, n, is even.
   */
  public static boolean isEven(int n) {
    return ((n % 2) == 0);
  } // isEven
} // class MyMath
```

A preliminary set of tests might look something like the following.

```java
package csc207.math;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests of the MyMath class.
 */
public class MyMathTest {
  @Test
  public void testAverage() {
    assertEquals(0, MyMath.average(0, 0), "zero");
    for (int i = 0; i &lt; 100; i++) {
      assertEquals(0, MyMath.average(i, -i), i + " vs " + -i);
    } // for
  } // testAverage

  @Test
  public void testIsEven() {
    for (int i = -100; i &lt; 100; i += 2) {
      assertEquals(i + " is even", true, 
      assertTrue(MyMath.isEven(i), i + " is even");
      assertFalse(MyMath.isEven(i + 1), (i + 1) + " is odd");
    } // for
  } // testIsEven
} // MyMathTest
```

As you may have noted, in order to use the static procedures from
`MyMath`, we have to precede them with the class name.  Java expects
you to be very careful on naming the methods you use.

Note that all of the tests *must* be preceded by `@Test`.  If you
remove that text, JUNit will no longer run it.  (Try it and see.)

JUnit may not run the tests in the order you expect.  So make sure
not to rely on values you set up in one test in another test.  For
example, if you are working on lists and add an element to the list
in `testA`, you may not find that the element is there in `testB`.
Similarly, at first glance it might seem that these two tests could
not both succeed:

```
    int x = 3;

    @Test
    public void testOne() {
      assertEquals(3, x);
      x += 1;
      assertEquals(4, x);
    } // testOne
   
    @Test
    public void testTwo() {
      assertEquals(3, x);
      x += 1;
      assertEquals(4, x);
    } // testTwo
```

However, JUnit essentially resets the environment between tests.
And that's a good thing.  You should be able to write tests that
are not affected by what happens in other tests.  (Yes, that's
probably another form of encapsulation.)

So, what should we do if we want to do the same setup for all of
our tests, setup that requires additional code.  One possibility
is to create a method and then just call it from each test.  But
JUnit provides another option.  In particular, JUnit provides the
`@Before` annotation which you can add to any procedure you want
called before every test.  Traditionally, that procedure is called
`setUp`, and you should probably use that name for clarity.

*Warning!  On occasion, Eclipse seems to forget about the `@Before`
annotation.  In those cases, creating a new JUnit Test Case and
clicking the box to include `setUp` usually works.*

Wrapping up
-----------

### Important Terms

* Unit test
* Assertion
* `setUp`
* `@Test`
* `@Before`

### Review Questions

* Why might you write test cases before implementing code?
* Why should we test with a testing framework, rather than a bunch
    of print statements?
* How do we set up a JUnit test in Eclipse?

### Exploratory Questions

* What are standard agile approaches to unit testing?
* If you have multiple test classes, how do you group them together
  into a test suite?
* What other annotations does JUnit provide?  What purpose do those
  annotations serve?
* What unit test would you write for binary search?  How about for
  a sorting algorithm?  (Just consider the tests that you would
  write; you don't actually have to write them.)</orderedlist>

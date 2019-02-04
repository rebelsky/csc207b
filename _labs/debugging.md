--- 
title: Debugging with Eclipse
repo: <https://github.com/Grinnell-CSC207/lab-unit-testing-2019>
summary: |
  We begin to explore the ways in which we can use a debugger to
  better understand flaws in our code.
todo:
  - Rewrite so that tests are from experiments, not unit tests.
---

Preparation
-----------

In [the laboratory on unit testing](../labs/unit-testing), you
forked and cloned the repository
<https://github.com/Grinnell-CSC207/lab-unit-testing-2019>.  You'll
work with that same repository.  (So return to the directory if you
have it, and make a new copy if you don't.)

Exercises
---------

### Exercise 1: Removing A's

As you may have noted in the [the laboratory on unit
testing](../labs/unit-testing.html), the procedure `SampleMethods.removeAs`
is not quite successful in its attempt to remove all copies of the
letter 'a' from its parameter string.

If you haven't yet written your test cases, here's one.

```java
public void testRemoveAs() {
  assertEquals("", 
               SampleMethods.removeAs(""),
               "empty string");
  assertEquals("hello", 
               SampleMethods.removeAs("hello"),
               "no as");
  assertEquals("", 
               SampleMethods.removeAs("a"),
               "eliminate one a");
  assertEquals("", 
               SampleMethods.removeAs("aaaa"),
               "eliminate many as");
  assertEquals("pin", 
               SampleMethods.removeAs("pain"),
               "eliminate one a, short string");
  assertEquals("lphbet", 
               SampleMethods.removeAs("alphabet"),
               "eliminate many as, medium string");
  assertEquals("BCDEFGHIJKLMNOPQ",
               SampleMethods.removeAs("aBaaCDaaaEFGaaaaHIJKaaaaLMNaaaOPaaQa"),
               "eliminate many as, silly string");
  assertEquals("bbb",
               SampleMethods.removeAs("aaabbbaaa"),
               "eliminate prefix and suffix as");
} // testRemoveAs
```

You may be able to tell by inspection why the method fails.  But
let's assume that you don't.

Open the code for `removeAs` and right click in the grey bar to the
left of the code to set a breakpoint at the start of the method.

Create a new main class that duplicates the failed calls to
`removeAs` from your unit tests.

Select **Run** > **Debug As** > **Java Application**.

A dialog box should pop up asking you to confirm switching to the
Java perspective.

If all goes well, Eclipse should stop at the point that you inserted
a breakpoint.

a. What do you expect to happen if you click the "Resume" button -
the button that looks like a green triangle.  (Note that in the
future, you can also hit <kbd>F8</kbd>.)

b. Check your answer experimentally.

As you may have noted, Eclipse resumed computation and ran until
the completion of this program.  (Presumably, with incorrect output.)
To see the results, you may need to switch back to the Java perspective.
You can get that perspective by clicking on the downward arrow in the
upper-right-corner of the screen.

c. Start the program again.  This time, let's single step through
the procedure, using the "Step Over" button (also
<kbd>F6</kbd>).  See if you can identify where the code goes wrong.

d. Correct the code to the best of your ability, remove the
breakpoint, run the unit tests again, and see if your code
passes all of the tests.

If so, go on to the next exercise.  If not, repeat the debugging
steps until you find the next bug.

### Exercise 2: Removing B's

The `removeBs` procedure has much the same goals as `removeAs`
although it uses a different (but still buggy) approach.

Use JUnit and the Eclipse debugger to identify and correct the errors.

*Note:* Your goal is to correct the errors in this approach.
Inserting slightly modified code from `removeAs` is not an acceptable
strategy.

### Exercise 3: Exponentiation

The `SampleMethods.expt` method computes
x<sup>p</sup> using a divide-and-conquer approach.

* x<sup>0</sup> = 1
* x<sup>2k</sup> = 
  x<sup>k</sup> * x<sup>k</sup>
* x<sup>2k</sup> =
  (x<sup>2</sup>)<sup>k</sup>
* x<sup>k+1</sup> =
  x * x<sup>k</sup>
  Some people combine the last two when dealing with an odd exponent.

This approach requires only log<subscript>2</subscript>p
multiplications to raise x to the pth power, while the naive loop
requires p multiplications.  (Of course, if you have a book of
tables, or functions that simulate those tables, you can compute
x<sup>p</sup> in two table lookups.)

It's a nice approach, but have we implemented it correctly?

If you haven't done so already write unit tests for 
``SampleMethods.expt`(int,int)`.

a. Add the following assertion at the start of your test.

```java
    assertEquals(1024, expt(2, 10), "1K");
```

b. Run the test.  It will likely fail.

c. Since the test failed, it will be useful to write a short
experiment to just do that one call.  

```java
/**
 * A quick experiment with the expt method.
 */
import java.io.PrintWriter;

public class ExptExpt {
  static final int base = 2;
  static final int expt = 10;

  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println(base + "^" + expt + " = " + SampleMethods.expt(base, expt));
    pen.close();
  } // main(String[])
} // class ExptExpt
```

c. Set a breakpoint at the start of the `expt` method.  (Make sure
that you choose the right one.  There are two!)

d. Start the debugger.  It should bring you to the first line of
`expt`.

e. What do you expect to happen if you click the "Resume" button?
(The button that looks like a green triangle.)

f. You may have discovered that instead of returning to the call
in the unit test, the debugger continued executing the code until
the next call to `expt`, which is a recursive call.  Hit the "Resume"
button another time.

g. You are now three levels deep in the recursive call stack for
`expt`.  In the "Debug" pane, navigate between them to see the
changing values of `x` and `p`.

h. Single step through the code to see if you can identify where
the error occurs.

i. Since intermediate values are not clearly represented in the
code, you may find it difficult, if not impossible, to quickly
identify the error.  So what next?  You could explicitly insert
temporary values for the recursive call.  Instead of calling `return`
in each case, you could set a local values (e.g., `results` and
then exit in the logical case).  Or you could get Eclipse to behave
better.

Choose one approach and see if you can identify the error.  Get 
help if you're not sure which approach you should use or if you
still can't identify the error after trying additional approaches.

For those with extra time
-------------------------

### Extra 1: Exponentiation

Consider the `expt(double, int)`
method.  As you might have noted, it doesn't work any more correctly
than the old version of `expt`

One issue we *may* hit in unit testing is that doubles are approximate.
Hence, slightly different orders of computation can make slight
differences in the result (e.g., in practice `Math.sqrt(2)*Math.sqrt(2)`
is often not the same as `Math.sqrt(2*2)`, even though they are
logically the same.

Write appropriate unit tests for this alternate version.  Then determine
if your corrections from the exercise above suffice.  If not, use the
debugger to figure out why.


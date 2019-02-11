---
title: Reusing and generalizing code with subtype polymorphism
summary: |
  We consider polymorphism, one of the key ideas in object-oriented
  programming. Polymorphism permits us to write methods that can
  use objects in a variety of related classes.
prereqs: |
  Class basics. 
  [Interfaces](../readings/interfaces).
todo:
  - Check that the square root code works.
---
Introduction
------------

As we saw at the end of the reading on [interfaces](../readings/interfaces),
when we write a method that uses an interface as the type of a
parameter, we can call that method with any class that implements
the interface.  The ability to call a method with an object that
belongs to any one of a group of related classes is an aspect of the
idea of polymorphism.

Why have polymorphism?
----------------------

Why include polymorphism in a programming language?  Because it
significantly reduces programming effort.  In particular, polymorphism
allows you to write one method for a variety of types, rather than
to write one method per type.

For example, suppose we don't trust the built-in method `Math.sqrt`
and want to write a method, `squareRoot`, that approximates the
square root of an Integer greater than or equal to one.  We can use
a fairly straightforward technique: Guess a value no greater than
the square root and a value no smaller than the square root and
repeatedly refine those guesses until they are close to each other.
In pseudocode, we might write:

```text
To compute the square root of N, where N >= 1
  Let lower = 1 // 1*1 <= N when N >= 1 
  Let upper = N // N*N >= N when N >= 1 
  while (upper-lower is too large) 
    Let mid = (lower + upper) / 2; 
    // If the new guess is too low, update lower 
    if (mid*mid < N)
      lower = mid;
    // If the new guess is too high, update higher 
    else
      upper = mid;
  return (upper+lower)/2;
```

(Note that is strategy is probably less efficient than one you
may already know, but is a little more straightforward, and
requires less division.)

As we write this in Java, we will find it convenient to convert the
`Integer` to a `double` so that the comparisons are straightforward.
We will also need to put it in a class, say `Helper`.  We would
then write:

```
public class Helper {
  public static double squareRoot(Integer i) {
    double n = i.doubleValue();
    double lower = 1.0;
    double upper = n;
    double mid;
    while (upper-lower > 0.01) {
      mid = (upper+lower)/2.0;
      if (mid*mid < n) 
        lower = mid;
      else
        upper = mid;
    } // while
    return (upper+lower)/2.0;
  } // squareRoot(Integer)

  // ...
} // class Helper
```

If we later need a method to compute the square root of a `BigInteger`
and don't want to use (or don't know about) polymorphism, we can
use cut-and-paste to write the following method:

```
public double squareRoot(BigInteger bi) {
  double n = bi.doubleValue();
  double lower = 1.0;
  double upper = n;
  double mid;
  while (upper-lower > 0.01) {
    mid = (upper+lower)/2.0;
    if (mid*mid &lt; n)
      lower = mid;
    else
      upper = mid;
  } // while
  return (upper+lower)/2.0;
} // squareRoot(BigInteger)
```

Of course, if we made a mistake in the definition of the first
`squareRoot` (as I did while writing this essay), or later realized
that there's a better implementation, we need to rewrite not just
`squareRoot(Integer)`, but also `squareRoot(BigInteger)`.  As these
two short examples suggest, repetition of code is likely to lead
to problems in maintaining and updating your program.

Using subtype polymorphism, we can write one method that serves
both purposes.  In particular, since both `Integer` objects and
`BigInteger` objects are `Number` objects, and since all `Number`
objects provide a `doubleValue` method, we can write

```java
public static double squareRoot(Number num) {
  double n = num.doubleValue();
  double lower = 1.0;
  double upper = n;
  double mid;
  while (upper-lower > 0.01) {
    mid = (upper+lower)/2.0;
    if (mid*mid &lt; n)
      lower = mid;
    else
      upper = mid;
  } // while
  return (upper+lower)/2.0;
} // squareRoot(Number)
```

Now we can call `Helper.squareRoot` on an `Integer`, a `BigInteger`,
a `Double`, a `BigDecimal`, a `Float`, or any of the other types
that implement the `Number` interface.  If someone later designs
another kind of `Number` (say, a `Fraction`), `Helper.squareRoot`
will work on that new kind of number without any additional effort
on the part of the programmer who wrote the `squareRoot` method

This example also suggests a second reason to have polymorphism:
If we write a polymorphic method, it will work not only with all
existing classes that implement an interface, but also with all
future classes that implement that interface.  In effect, we have
programmed for the future.

A third reason to have polymorphic methods is that they typically
require more careful thought than do type-specific methods.  That
is, because the methods must be general, programmers cannot rely
on features of particular types.  Experience suggests that these
general-purpose solutions are often somewhat better.  (Of course,
this better is certainly subjective.  At times, the type-specific
methods are more efficient and clearly easier to write.)

An extended example: Laying out text
------------------------------------

Let us consider an example in which polymorphism significantly
simplifies our task.  Suppose we want to combine "chunks" of text
on the terminal screen.  For example, we might place two columns
of text side-by-side, we might place two rows of text above each
others, or we might outline a piece of text.

How do we print out the combination of two columns?  For each
line, we print the corresponding row from the first column, some
amount of whitespace between the two columns, and the corresponding
row from the second column.  We might phrase this in Java as
follows.

```java
    pen.println(col1.row(i) + separator + col2.row(i));
```

What methods should these blocks of text provide?  As the example
suggests, we should probably be able to get one row from a block
of text.  Let's call that method `row`.  We should also be able to
get the number of rows.  Let's call that method `height`.  Finally,
we should be able to get the width of the block (so that we can
more easily place blocks side-by-side).  Let's call that method
`width`

Putting it all together, we get the following interface:

```
package groupname.layout;

/**
 * Blocks of text.
 *
 * @author Samuel A. Rebelsky
 * @version 1.1 of January 2019
 */
public interface TextBlock {
  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+
 
  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   * @return the ith row
   * @pre
   *   0 <= i < this.height()
   * @exception Exception
   *   if the row number is invalid.
   */
  public String row(int i) throws Exception;

  /**
   * Determine how many rows are in the block.
   *
   * @return The number of rows.
   */
  public int height();

  /**
   * Determine how many columns are in the block.
   *
   * @return The number of columns.
   */
  public int width();
} // interface TextBlock
```

Here's a method (that we can put in a utility class) that knows how
to print `TextBlock` objects.

```java
/**
 * Print a textblock to the specified destination.
 */
public static void print(PrintWriter pen, TextBlock block) {
  for (int i = 0; i < block.height(); i++) {
    pen.println(block.row(i));
  } // for
} // print(PrintWriter, TextBlock)
```

The simplest kind of TextBlock is a single line of text.  We'll use
these single lines as the building block of more complex blocks.

```java
package groupname.layout;

/**
 * One line of text.
 *
 * @author Samuel A. Rebelsky
 * @version 1.3 of January 2019
 */
public class TextLine implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The contents of the line.
   */
  String line;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new line with contents _line.
   */
  public TextLine(String _line) {
    this.line = _line;
  } // TextLine(String)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @pre
   *   i == 0
   * @exception Exception
   *   if i != 0
   */
  public String row(int i) throws Exception {
    if (i != 0) {
      throw new Exception("Invalid row " + i);
    }
    return this.line;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    return 1;
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    return this.line.length();
  } // width()
 
} // class TextLine
```

How can we combine lines (and the combinations of lines)?  We might
combine them horizontally, vertically, or even put a box around
them.  Let's consider the last of those.  To put a box around a
TextBlock, we simply need to figure out what to return for the calls
to `row`, `height`, and `width`.  The height of a boxed block is
only slightly bigger than the height of the underlying block, with
room for a row above and a row beneath.  Similarly, the width of a
boxed block is only slightly bigger than the width of the underlying
block, with room for a character on the left and a character on the
right.

The hardest of the three methods to write is `row`.  If the height
of the underlying block is `h`, for rows 1 to `h`, we surround the
i-1th row of the underlying block with the left and right symbols
of the box (e.g., vertical bars).  For row 0, we return the top row
of the box symbol, such as `+-----+`.  For row `h`, we return a
similar string.

  Putting it all together, we get the following.

```java
package groupname.layout;

/**
 * A text block surrounded by a box.
 *
 * @author Samuel A. Rebelsky
 * @version 1.2 of January 2019
 */
public class BoxedBlock implements TextBlock {
  // +--------------+------------------------------------------------------
  // | Class Fields |
  // +--------------+

  /**
   * A really big sequence of dashes.  This sequence may grow as
   * the program operates.
   */
  String lotsOfDashes = "--";


  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the box
   */
  TextBlock contents;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new line with contents _contents.
   */
  public BoxedBlock(TextBlock _contents) {
    this.contents = _contents;
  } // BoxedBlock(String)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @pre
   *   0 &lt;= i &lt; this.height()
   * @exception Exception
   *   if the precondition is not met
   */
  public String row(int i) throws Exception {
    int h = this.contents.height();
    // The top and bottom of the box
    if ((i == 0) || (i == h+1) ) {
      return "+" + dashes(this.contents.width()) + "+";
    }
    // Stuff within the box
    else if ((i > 0) && (i <= h)) {
      return "|" + this.contents.row(i-1) + "|";
    }
    // Everything else
    else {
      throw new Exception("Invalid row " + i);
    }
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    return 2 + this.contents.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    return 2 + this.contents.width();
  } // width()
 
  // +---------------+-----------------------------------------------------
  // | Class Methods |
  // +---------------+

  /**
   * Build a sequence of dashes of a specified length.
   */
  String dashes(int len) {
    // Note: This method is probably overkill.
    // Make sure the collection of dashes is big enough
    while (lotsOfDashes.length() < len) {
      lotsOfDashes = lotsOfDashes.concat(lotsOfDashes);
    }
    // Extract an appropriate length substring
    return lotsOfDashes.substring(0,len);
  } // dashes(int)

} // class BoxedBlock
```

Because `BoxedBlock` objects can be created from any `TextBlock`
objectss, we can create them from lines of text or even from other
text blocks.  For example, consider the following instruction.

```
TextBlock tb = new BoxedBlock(new BoxedBlock(new TextLine("Hello")));
```

If we print out `tb`, we get something like the following:

```text
+-------+
|+-----+|
||Hello||
|+-----+|
+-------+
```

Once we add in horizontal and vertical composition (which we will
do in [the lab](../labs/subtype-polymorphism)), we can build a wide
variety of layouts.


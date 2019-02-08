---
title: Assignment 3
subtitle: Explorations in object-oriented design
summary: |
  In this assignment, you will explore aspects of object-oriented design,
  particularly some aspects of parametric polymorphism.
purpose: |
  To help you think more carefully about parametric polymorphism.
collaboration: |
  You should do the assignment with your assigned partner.  You may
  discuss this assignment with anyone, provided you credit such
  discussions when you submit the assignment.  Note also that
  discussing a problem is not the same as writing code together -
  You may consider approaches to a problem, help debug each other's
  code, and even look at other people's code for inspiration, but
  you should not develop code together.
submitting: |
  Please put all of your work in a GitHub repository named `csc207-hw03`.
  Email the address of that repository to `csc207-01-grader@cs.grinnell.edu`.
  Please use a subject of "CSC207 2019S Assignment 3 (*Your Name*)".
warning: |
  So that this assignment is a learning experience for everyone, we may
  spend class time publicly critiquing your work.
current: true
---
Preparation
-----------

a. Create a new Eclipse project for this assignment.  You can name
the project whatever you like, provided it's not in bad taste.

b. Create a new package named `csc207.*username*.layout`.  You will
use this package on parts A and B of the assignment.

c. Copy the various classes from [the recent lab on subtype
polymorphism](../labs/subtype-polymorphism) into that package.

d. Create a new package named `com.mcfarevee.groceries`.
You will use this package in part C of the assignment.

e. Create a new package named `com.mcfarevee.shopping`.
You will use this package in part D of the assignment.

f. Create a new package named `com.mcfarevee.tests`.
You will use this package for your experiments and tests.

Assignment
----------

### Part A: More polymorphic compositions of text

In the [reading](../readings/subtype-polymorphism) and
[lab](../labs/subtype-polymorphism) on polymorphism, you explored
a number of related classes that permitted you to build interesting
two-dimensional layouts of characters.  You were able to combine
and recombine text objects because of Java's support of *polymorphism*.
That is, if we treat every combination of text as an object that
implements the `TextBlock` interface, then we can combine these
objects arbitrarily.

The textual layout example also had a hidden agenda: To help you
think about problems in a more object-oriented fashion.  In particular,
most beginning programmers, when asked to do some form of textual
composition, focus on the *methods* that would compose pieces of
text.    In this example, we built *objects* that represent those
composed pieces of text.  The advantages of using objects included
the ability to reuse a composed piece of text and a better ability
to take advantage of polymorphism.

However, we left some aspects of the example unexplored in
both the reading and the lab.  Let's begin by building some new
composition classes.

a. Write a class, `Grid`, which implements `TextBlock` and represents
an w-by-h grid of a single character.  You should provide one
constructor, `Grid(int width, int height, char ch)`.  The `width()`
method of the object you construct should return `width`.  The
`height()` method of the object you construct should return `height`.
And the `row(i)` method should return a string of `width` copies
of `ch` for all non-negative `i` less than `height`.

For example, given the following line,

```java
  TBUtils.print(pen, new BoxedBlock(new Grid(7, 3, '*')));
```

the output will be

```text
+-------+
|*******|
|*******|
|*******|
+-------+
```

You may choose to throw an exception in the constructor if the
width or height are not sensible values.  (If you don't yet know how
to throw exceptions, don't worry about it.)

b. Write a class, `TruncatedBlock`, which implements
`TextBlock` and represents the truncation of a text block
to a desired width.  As you might expect, the constructor for this
class should take a `TextBlock` and a width as parameters.

```java
public class TruncatedBlock implements TextBlock {
  /**
   * Create a new truncated block of the specified width.
   */
  public TruncatedBlock(TextBlock tb, int width) {
    ...
  } // TruncatedBlock(TextBlock, int)

  /**
   * Get the ith row of the block.
   */
  public String row(int i) {
    ...
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    ...
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    ...
  } // width()
} // class TruncatedBlock
```

For example, given the following program fragment,

```java
  TextBlock block = new VComposition(new TextLine("Hello"), new TextLine("Goodbye"));
  TextBlock block2 = new TruncatedBlock(block, 3);
  TBUtils.print(pen, block);
  TBUtils.print(pen, block2);
</pre>
```

we see output something like the following

```text
Hello
Goodbye
Hel
Goo
```

c. Write a class, `CenteredBlock`, which implements
`TextBlock` and represents the result of centering a text
block within a certain width, which is provided as a parameter to
the constructor.  That width should be no less than the
width of the underlying text block, and your constructor should throw
an exception if given an inappropriate width.

Your class should not attempt to examine the contents of the
block which is centered.  If that text is left justified, you
simply center the left-justified text within your block.

For example, given the following program fragment,

```java
  TextBlock block = new VComposition(new TextLine("Hello")), new TextLine("Goodbye"))
  TextBlock block2 = new BoxedBlock(new CenteredBlock(block, 11));
  TBUtils.print(pen, block2);
```

we see output something like the following

```text
+-----------+
|  Hello    |
|  Goodbye  |
+-----------+
```

In contrast, given the following program fragment,

```java
  TextBlock top = new CenteredBlock(new TextLine("Hello"), 11);
  TextBlock bottom = new CenteredBlock(new TextLine("Goodbye"), 11);
  TextBlock block = new BoxedBlock(new VComposition(top,bottom));
  TBUtils.print(pen, block);
```

we should see something like the following

```text
+-----------+
|   Hello   |
|  Goodbye  |
+-----------+
```

d. Write a class, `RightJustified`, which implements `TextBlock`
and represents the result of right-justifying the underlying text
block in a block of a width specified in the constructor.  You may
choose to throw an exception if the underlying block is too large.

e. Write a class, `BlockPair`, which implements `TextBlock` and
represents two copies of the same block, side by side.  For example,
if the third row of `block` is `"Hello"`, then the third row of
`new BlockPair(block)` should be `"HelloHello"`.

### Part B: Mutable text blocks

As written, our various text blocks are immutable.  What would it
mean to make them mutable?  Let's consider those issues.

Add a `setContents(String newContents)` method to the
`TextLine` class.  As the name suggests, this method should
set the contents of that block to the new value.

Some of the other classes may make assumptions about the
immutability of text blocks.  Identify where those assumptions
are made and update the code so that it accommodates blocks
that change.

Note that the only block that you will explicitly make mutable is
`TextLine`.  Your job is to update everything else so that when a
`TextLine` changes, any block that contains that `TextLine` also
changes.

You need only submit one set of code for parts A and B.

Here's a code fragment to help you think about changes.

```java
  TextLine tb1 = new TextLine("Hello");
  TextLine tb2 = new TextLine("World");
  TextBlock compound = new BoxedBlock(new CenteredBlock(new BoxedBlock(new CenteredBlock(new VComposition(tb1, tb2), 7)), 15));
  pen.println("ORIGINAL");
  TBUtils.print(pen, compound);
  tb2.setContents("Someone");
  pen.println("UPDATED");
  TBUtils.print(pen, compound);
  tb1.setContents("Nice to meet you,");
  pen.println("RE-UPDATED");
  TBUtils.print(pen, compound);
```

And here's some corresponding output.

```text
ORIGINAL
+---------------+
|   +-------+   |
|   | Hello |   |
|   | World |   |
|   +-------+   |
+---------------+
UPDATED
+---------------+
|   +-------+   |
|   |Hello  |   |
|   |Someone|   |
|   +-------+   |
+---------------+
RE-UPDATED
*Something that indicates that there was an error, which may just be a missing line.*
```

### Part C: Modeling groceries

McFare-Vee corporation has decided to upgrade the software used in
their grocery stores and has hired you to help implement their
object model.  (You may think it strange that they are hiring a
relatively experienced programmer.  However, the demand for programmers
outstrips supply, and you are fortunate to have a general aura of
competence.)

They've decided to start by having you model the items in the grocery
store, focusing on how the consumer might think of purchasing those
items.  Here's their initial assessment of how they want the data
organized.

```text
+-Item----------------+   +-BulkFood----------+   +-Weight------+
| getWeight(): Weight |   | name: String      |   | unit: Unit  |
| getPrice(): int     |   | unit: Unit        |   | amount: int |
| toString(): String  |   | pricePerUnit: int |   +-------------+
+---------------------+   | supply: int       |
 ^   ^   ^   ^            +-------------------+
 |   |   |   |
 |   |   |   +-----------------------------------+
 |   |   +----------------------------------+    |
 |   +--------------------+                 |    |
 |                        |                 |    |
+-BulkItem------------+  +-Package--------+ | +-ManyPackages---+
| food: BulkFood      |  | name: String   | | | type: Package  |
| unit: Unit          |  | weight: Weight | | | count: int     |
| amount: int         |  | price: int     | | +----------------+
+---------------------+  +----------------+ |
                                            |
                                          +-NonFood--------+
                                          | name: String   |
                                          | weight: Weight |
                                          | price: int     |
                                          +----------------+ 
```

That is,

* `Item` is an interface.  
* `BulkItem`, `Package`, `NonFood`,
  and `ManyPackages` all implement the `Item` interface.  Hence,
  each must include implementations of the `getWeight()`, `getPrice()`,
  and `toString()` methods.
* `BulkFood` and `Weight` are additional
   classes, used mostly to represent objects used by the other
   classes.
* Each class has a variety of fields, some of which are objects
  that belong to other classes.

As you might guess, each of the implementations of `Item` differs
in its implementation of the `toString`, `getWeight`, `getPrice`,
and `equals` methods.  Note that the `getWeight` method returns the
weight in whatever units the item uses.  The `getPrice` method
should return the price in cents.

* `BulkItem`
    * The `toString` method should return a string that
      gives the number of units, the units, and the type of bulk
      food.  For example, "5 pounds of bananas".
    * The `getWeight` method should return the weight
      (built from the unit and quantity).
    * The `getPrice` method should multiply the number
      of units times the price per unit.
    * The `equals` method should only return true if the
      compared object is a `BulkItem` object with equal
      fields.
* `Package`
    * The `toString` method should give the weight,
      the word "package", and the name.  For example,
      "5 oz. package of mac and cheeze".
    * The `getWeight` and `getPrice` methods
      should return the obvious values.
    * The `equals` method should only return true if the
      compared object is a `Package` object with equal
      fields.
* `ManyPackages`
    * The `toString` method should give the quantity, a
      times sign (x), and the description of the package.  For example,
      "4 x 5 oz. package of mac and cheeze".
    * The `getWeight` and `getPrice` methods
      should multiply the corresponding values in the underlying
      package by the quantity.
    * The `equals` method should only return true if
      the compared object occupies the same memory location.
* `NonFood`
    * The `toString` method should give the name.  For example, "can opener".
    * The `getWeight` and `getPrice` methods should return the obvious values.
    * The `equals` method should only return true if the compared object is a `NonFood` object with equal fields.

Create a new package called `com.mcfarevee.groceries` and create
classes for these objects in the package.

Your predecessor has implemented the `Unit` class to
give you a head start.  You can find that class at the end of this
assignment.

Here's a program fragment that shows some of these classes in action.

```java
  // The store has 20 pounds of bananas, priced at 50 cents per pound
  BulkFood bananas = new BulkFood("bananas", Unit.POUNDS, 50, 20);
  // The store has 200 grams of saffron, priced at 1000 cents per gram
  BulkFood saffron = new BulkFood("saffron", Unit.GRAMS, 1000, 200);

  // The customer adds three pounds of bananas to the cart
  cart.addItem(new BulkItem(bananas, Unit.POUNDS, 3);

  // The customer adds a jar of 3 grams of saffron to the cart
  cart.addItem(new BulkContainer("jar", saffron, Unit.GRAMS, 3);

  // The customer adds a bag of 1 gram of saffron to the cart
  cart.addItem(new BulkItem(saffron, Unit.GRAMS, 1);

  // The customer adds a can opener to the cart, priced $3.489.
  cart.addItem(new NonFood("can opener", new Weight(Unit.OUNCES, 2), 349);

  // The customer adds a box of oreos to the cart
  cart.addItem(new Package("oreos", new Weight(Unit.OUNCES, , 12), 399);

  // The customer adds five 6oz packages of macncheez to the cart, each 
  // priced at 77 cents.
  cart.addItem(new ManyPackages(new Package("macncheez", 
      new Weight(Unit.OUNCES, 6), 77), 5));
```

Note that if we were modeling this fully, we would decrement the supply 
of bananas or saffron when we added each to the cart.  You do not need
to worry about doing so, although you should also feel free to include
that action in your code.

### Part D: A shopping cart

McFare-Vee are optimistic about your ability to implement the design above
and have asked you to build a `Cart` class, too.  They ask
that you put that class in package `com.mcfarevee.shopping`
and that you include the following methods.

* `addItem(Item)`.  Add an item to the cart.
* `numThings()`.  Get the number of things in the cart.
   For counting items, you should count most items as one thing,
   except for `ManyPackages`, for which you should use
   the count.  (You may want to add an accessor for that count.)
* `numEntries()`.  Get the number of entries in the cart.
   This is much like `numThings()`, except that you should
   count a `ManyPackages` objects as one entry.
* `printContents(PrintWriter)`.  Print the contents of
   the cart.
* `getPrice()`.  Computes the total price of the order,
   in cents.
* `getWeight()`.  Since there are multiple kinds of weights
   in use at McFare-Vee, they note that this method can return an array
   of weights, but that you should combine similar weights.  (E.g., you
   should combine all of the weights in pounds together, but you should
   not convert from ounces, grams, or kilograms to pounds.)  You may
   assume that we have only the four basic kinds of weight.
* `remove(String name)`.  Removes all of the products whose
  name exactly matches `name`.
* `merge()`.  Finds identical items and merges them into
  a single item.  For example, if you have two `Package`
  items with the same name, weight, and price, you should combine
  them into a single `ManyPackages` object.  If you have a
  `ManyPackages` object and a `Package` object of the same kind,
  you should combine them.

  Continuing, if you have two `ManyPackages` objects that contain
  the same kind of package, you should combine them into a single
  `ManyPackages` object.  Similarly, if you have two `BulkItem`
  objects with the same food and units, you should combine them
  into one `BulkItem`.

  This method does not need to do anything with `NonFood` objects.

  You may use whatever data structure you consider most appropriate
  to store the data in the cart - an array, a linked list you create,
  a `Vector`, an `ArrayList`, a `LinkedList`,
  or whatever else you decide is appropriate.  Note that your choice of
  data structure will have a large effect on the ease of implementing the
  various methods, so choose thoughtfully.

Appendix: A `Unit` Class
-------------------------

```java
/**
 * A Unit of measurement.  The primary units available at 
 * Unit.GRAM, Unit.KILOGRAM, Unit.OUNCE, and Unit.POUND.
 */
public class Unit {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The name of the unit.
   */
  String name;

  /**
   * The abbreviation of the unit.
   */
  String abbrev;

  /**
   * The plural name of the unit.
   */
  String plural;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new unit with a given name.
   */
  private Unit(String name, String abbrev, String plural) {
    this.name = name;
    this.abbrev = abbrev;
    this.plural = plural;
  } // Unit(String, String, String)

  // +-----------+---------------------------------------------------
  // | Accessors |
  // +-----------+

  /**
   * Get the unit name.
   */
  public String toString() {
    return this.name;
  } // toString()

  /**
   * Get the abbreviation of the unit name.
   */
  public String abbrev() {
    return this.abbrev;
  } // abbrev()

  /**
   * Get the plural of the unit name.  (One does not always form a plural
   * by just adding "s", so we provide this additional method.)
   */
  public String plural() {
    return this.plural;
  } // plural

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * Standard unit: Pounds
   */
  public static final Unit POUND = new Unit("pound", "lb.", "pounds");
  
  /** 
   * Standard unit: Ounces
   */
  public static final Unit OUNCE = new Unit("ounce", "oz.", "ounces");

  /**
   * Standard unit: Kilograms
   */
  public static final Unit KILOGRAM = new Unit("kilogram", "kg.", "kilograms");

  /**
   * Standard unit: Grams
   */
  public static final Unit GRAM = new Unit("gram", "gm.", "grams");

} // class Unit
```

Citations
---------

Much of this assignment is based on HW6 from Samuel A. Rebelsky's
Fall 2014 section of Grinnell College's CSC 207, available at
<https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2014F/assignments/assignment.06.html>.  That assignment is, itself, based on two other assignments.

The text block problems are based closely on HW 3 from Samuel A.
Rebelsky's spring 2005 section of Grinnell College's CSC 152, available at
<http://www.cs.grinnell.edu/~rebelsky/Courses/CS152/2005F/Homework/hw.03.html>.

The grocery store problems were inspired by Henry Walker's first
supplemental problem for section 2 of CSC 207 in Fall 2014, available at
<http://www.cs.grinnell.edu/~walker/courses/207.fa14/suppl-prob.shtml>.

Although the history of this assignment is long, the problems remain
relevant.  Manipulating text blocks is much like manipulating GUI
elements and the use of subtype polymorphism for such objects; the
grocery problems exercise skills in object design.

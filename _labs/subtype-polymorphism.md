---
title: Subtype polymorphism in Java
repo: <https://github.com/Grinnell-CSC207/lab-polymorphism-2019>
summary: |
  We explore issues of polymorphism in Java.
---
Preparation
-----------

a. Fork and clone the specified repository.

b. Import that repository into Eclipse.

Exercises
---------

### Exercise 1: Square roots

a. Scan through `MathUtils.java` and verify that the square root
method has the form described in [the
reading](../readings/polymorphism.html).

b. Run the `main` method of `MathExpt` to see that it behaves as
expected.

c. Extend the `main` method of `MathExpt` so that it computes the
square root of

* an `Integer`, 
* a `Float`, 
* a `Double`, 
* a `BigInteger`, 
* a `BigDecimal`, and
* a `double`.

### Exercise 2: Simple lines

As a first step in understanding the layout methods, extend the
`main` method of `TBExpt.java` so that it uses `TBUtils.print` to
print a simple block.   I would suggest something like the following.

```java
    TextBlock block = new TextLine("This is a test.");
    TBUtils.print(pen,block);
```

### Exercise 3: Boxed text

The [reading](../readings/polymorphism.html), claims that it is
possible to combine text blocks together by using one as a parameter
to the constructor of another.  (In fact, that seems to be the only
way to create a `BoxedBlock`.  Try creating and printing out each
of the following:

* a boxed line (of your choice);
* a boxed boxed line (of your choice); and
* a boxed empty line.

### Exercise 4: Composing text

The classes `HCompose` and `VCompose` permit you
to compose pairs of text blocks horizontally and vertically.  For
example, `new HCompose(tb1,tb2)` makes a text block by
putting `tb1` and `tb2` side-by-side, and
`new VCompose(tb1,tb2)` makes a text block by putting
`tb1` above `tb2`.

a. Using `VCompose`, `TextLine`, and
`BoxedBlock`, build the following text block:

```text
+-------+
|Hello  |
|Goodbye|
+-------+
```

b. Using `VCompose`, `TextLine`, and
`BoxedBlock`, build the following text block:

```text
+-----+
|Hello|
+-----+
+-------+
|Goodbye|
+-------+
```

c. Using `HCompose`, `TextLine`, and
`BoxedBlock`, build the following block:

```text
+-----+Goodbye
|Hello|
+-----+
```

d. Using `HCompose`, `TextLine`, and
`BoxedBlock`, build the following block:

```text
Goodbye+-----+
       |Hello|
       +-----+
```

### Exercise 5: Writing HCompose

Assume that `HCompose` has two fields, 

```java
  TextBlock left;
  TextBlock right;
```

a. Sketch how you might write 

* `String row(int rownum)`,
* `int width()`, and
* `int height()`.

You can look at the source code of `BoxedBlock` for ideas,
but please do not look at the source code of `HCompose`.

b. Compare your answer to the source code of `HCompose`.

### Exercise 6: Writing VCompose

Assume that `VCompose` has two fields, 

```java
  TextBlock top;
  TextBlock bottom;
```

a. Sketch how you might write 

* `String row(int rownum)`,
* `int width()`, and

You can look at the source code of `BoxedBlock` and
`HCompose` for ideas, but please do not look at the source
code of `VCompose`.

b. Compare your answer to the source code of `VCompose`.

### Exercise 7: Other composition classes

Pick one of the following composition mechanisms and implement it as a
class.

a. `Truncate`, which, given a text block and a maximum width,
builds a new block that truncates the input block to that width.

b. `Center`, which, given a text block and a width, builds
a new block that centers the block within that width.

c. `RightJustify`, which, given a text block and a width, builds a
new block right-justifies the input block within that width.

For those with extra time
-------------------------

### Extra 1: Other compositions

Use the various implementations of `TextBlock` to make an
"interesting" textual composition.  


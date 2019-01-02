---
title: Inheritance
summary: |
  In today's laboratory, you will explore inheritance in Java by
  building and extending some simple classes.
---
Preparation
-----------

Create an Eclipse Java project for this lab and a Java package in
that project named after your group.  (I'd recommend that you also
create a Git repository, but it's up to you.)

Add the following interface to package `groupname.util` (substituting
the name of your group for `groupname`).

```java
package groupname.util;

/**
 * BasicCounters.
 */
public interface BasicCounter {
  /**
   * Count something.
   *
   * @exception Exception
   *   When the count gets too large.
   */
  public void increment() throws Exception;

  /**
   * Reset the counter.
   */
  public void reset();

  /**
   * Get the value of the counter.
   */
  public int get();
} // interface BasicCounter
```

Exercises
---------

### Exercise 1: Your Base Class

Write a class, `BasicCounter`, in package `groupname.util`, that
implements the `Counter` interface.  The class will allow clients
to build objects that count things, starting at some value.

The class should contain

* Two `int` fields, 
  `count` and `start`.
* One constructor that takes a starting value as a parameter.  The
  constructor should initialize both `count` and
  `start` to that value.
* Four methods:
    * `increment()`, which adds 1 to `count`
     (note that `increment` may throw an exception);
    * `reset()`, which resets `count` to `start`;
    * `toString()`, which returns a string of the
        form `"[" + this.count + "]"`.
    * `get()`, 
        which returns the value of `count`.

Here is a simple, not so systematic, test for that class.

```java
@Test
public void test() throws Exception {
  Counter alpha = new BasicCounter(0);
  Counter beta = new BasicCounter(123);
  Counter gamma = new BasicCounter(-5);
  assertEquals("original alpha", 0, alpha.get());
  assertEquals("original beta", 123, beta.get());
  assertEquals("original gamma", -5, gamma.get());
  for (int i = 0; i &lt; 10; i++) {
    alpha.increment();
    beta.increment();
    gamma.increment();
  } // for
  assertEquals("updated alpha", 10, alpha.get());
  assertEquals("updated beta", 133, beta.get());
  assertEquals("updated gamma", 5, gamma.get());
  alpha.reset();
  beta.reset();
  gamma.reset();
  assertEquals("reset alpha", 0, alpha.get());
  assertEquals("reset beta", 123, beta.get());
  assertEquals("reset gamma", -5, gamma.get());
} // test()
```

And here is an equally simple experiment.

```java
import java.io.PrintWriter

/**
 * A simple experiment to allow us to explore our counter classes.
 */
public class CounterExpt {
  public static void main(String[] args) {
    // Set up output
    PrintWriter pen = new PrintWriter(System.out, true);

    // Set up some counters
    Counter alpha = new BasicCounter(0);
    Counter beta = new BasicCounter(123);
    Counter gamma = new BasicCounter(-5);

    // Print original values
    pen.println("Original alpha = " + alpha);
    pen.println("Original beta = " + beta);
    pen.println("Original gamma = " + gamma);
  
    // Print incremented values
    alpha.increment();
    beta.increment();
    gamma.increment();
    pen.println("Updated alpha = " + alpha);
    pen.println("Updated beta = " + beta);
    pen.println("Updated gamma = " + gamma);

    // And we're done
    pen.close();
  } // main(String[])
} // class BasicCounterExpt
```

### Exercise 2: Tallys

One of the key ideas of inheritance is that you can create new
classes in place of old.  So let's try it.  We'll create a class,
`Tally`, that behaves much like our `BasicCounter` class.

a. Create a new class, `Tally`, that has the following form:

```java
public class Tally extends BasicCounter {
  public Tally(int start) {
    super(start);
  } // Tally(int)
} // class Tally
```

b. Change the initialization of `alpha` so that it reads

```java
  Counter alpha = new Tally(0);
```

c. What effect to you expect this change to have on the tests or
experiments?

d. Check your answer experimentally.

e. How do `Tally` objects differ from `BasicCounter` objects?  Right
now, not at all.  How might they differ?  We might want to make
`Tally` objects always start at 0, rather than a designated start
value.  How can we do that?  With a slightly different constructor.
Replace the constructor of `Tally` with the following.

```java
public Tally() 
{
  super(0);
} // Tally()
```

f. What effect do you expect this change to have?

g. Check your answer experimentally.

h. As you might have predicted, Java issues an error message because
you are calling the constructor with the wrong number of parameters.
Rewrite the initialization to the following and predict the effect.

```java
  Counter alpha = new Tally();
```

i. Check your answer experimentally.

j. Summarize what you learned in this exercise.

### Exercise 3: Decrementable BasicCounters

a. Create a new class, `DecrementableCounter`, that has
the following form:

```java
public class DecrementableCounter extends BasicCounter {
  public DecrementableCounter(int start) {
    super(start);
  } // DecrementableCounter(int)
} // class DecrementableCounter
```

b. Change the initialization of `gamma` so that it reads

```java
    Counter gamma = new DecrementableCounter(-5);
```

c. What effect to you expect this change to have on the tests or
experiments?

d. Check your answer experimentally.

e. Add a `decrement()` method to `DecrementableCounter`  This method
should subtract one from the `count` field.

f. What do you expect to happen if we add the following lines to our 
test?

```java
    gamma.reset();
    assertEquals("reset gamma", -5, gamma.get());
    gamma.decrement();
    assertEquals("decremented gamma", -6, gamma.get());
```

g. Check your answer experimentally.

h. Change the declaration of `gamma` to

```java
  DecrementableCounter gamma = new DecrementableCounter(-5);
```

What effect do you expect this change to have?

i. Check your answer experimentally.

j. Change the initialization of `gamma` so that it reads

```java
  DecrementableCounter gamma = new BasicCounter(-5);
```

k. What effect to you expect this change to have?  

l. Check your answer experimentally.

m. Restore the initialization of `gamma` to

```java
  DecrementableCounter gamma = new DecrementableCounter(-5);
```

n. Summarize what you learned in this exercise.

### Exercise 4: Naming BasicCounters

a. Create a new class, `NamedCounter`, that has the following form

```java
public class NamedCounter extends BasicCounter {
  String name;
  public NamedCounter(String name, int start) {
    super(start);
    this.name = name;
  } // NamedCounter(String, int)
} // class NamedCounter 
```

b. Update your test and experiment so that the initialization of
`alpha` reads

```java
    Counter alpha = new NamedCounter("alfa", 0);
```

c. What effect do you expect this change to have?

d. Check your prediction experimentally.

e. Override the `toString` method by inserting the following
code into `NamedCounter`.

```java
  @Override
  public String toString() {
    return this.name + super.toString();
  } // toString()
```

f. What effect do you expect this change to have?

g. Check your prediction experimentally.

h. Swap the two lines in the constructor for `NamedCounter`
and determine what errors, if any, you get.  

i. Restore the constructor.

j. Summarize what you've learned from this exercise.

### Exercise 5: Named Counters

a. What effect do you expect if we have `NamedCounter` extend
`DecrementableCounter` instead of `BasicCounter`?  For example,
will we still be able to write the following declaration?

```java
    Counter alpha = new NamedCounter("alfa", 0);
```

b. Check your answer experimentally.

c. Add a call to `System.err.println` to each of the constructors
so that you can observe when they are called.  For example, you
might change the `NamedCounter` constructor to read as follows.

```java
  public NamedCounter(String name, int start) {
    super(start);
    System.err.println("NamedCounter(\"" + name + "\", " + start + ")");
    this.name = name;
  } // NamedCounter(String, int)
```

What do you expect to see as output when your create `alpha`?

d. Check your answer experimentally.

e. Summarize what you learned from this exercise.

### Exercise 6: Double Counters

a. Create a new class, `DoubleCounter`, that has the
following form

```java
public class DoubleCounter extends BasicCounter {
} // class DoubleCounter 
```

b. What do you expect to happen when you compile this class?

c. Check your answer experimentally.

d. Insert a constructor for `DoubleCounter` of the following form.

```java
public DoubleCounter(int start) {
  super(start);
} // DoubleCounter(int)
```

e. Update your experiment so that the initialization of
`beta` reads

```java
    Counter beta = new DoubleCounter(123);
```

f. What effect do you expect this change to have on your tests or experiments?

g. Check your prediction experimentally.

h. Override the `increment` method by inserting the following
code into `DoubleCounter`

```java
  @Override
  public void increment() { 
    super.increment();
    super.increment();
  } // increment()
```

i. What effect do you expect this change to have on your tests and experiments?

j. Check your prediction experimentally.

k. Summarize what you've learned from this exercise.

### Exercise 7: Limited BasicCounters

a. Create a subclass of `Counter` called `LimitedCounter` that includes

* an `int` field named `limit`;
* a constructor that takes two parameters: a starting value and
  an upper limit (that is, a value for the `limit` field); 
  and
* a modified `increment` method that throws an exception
  when `count` exceeds limit.

b. In your test, determine the results of changing the initialization of 
`gamma` to

```java
  BasicCounter gamma = new LimitedCounter(-5,3);
```

c. Summarize what you've learned from this exercise.

### Exercise 8: Double Counters, Revisited

*Note that for this exercise, you probably just want to use the
experiment, rather than the test.*

a. Add the following class to your project.

```java
public class DblCtr implements Counter {
  /**
   * The underlying counter.
   */
  Counter base;

  /**
   * Build a new counter that counts twice as fast as counter.
   */
  public DblCtr(Counter counter) {
    this.base = counter;
  } // DblCtr(Counter)

  /**
   * Increment the counter, twice.
   */
  @Override
  public void increment() { 
    this.base.increment();
    this.base.increment();
  } // increment()

  /**
   * Reset the counter.
   */
  @Override
  public void reset() {
    this.base.reset();
  } // reset()

  /**
   * Get the value.
   */
  @Override
  public int get() {
    return this.base.get();
  } // get()

  /**
   * Convert to a string.
   */
  @Override
  public String toString() {
    return this.base.toString();
  } // toString()
} // class DblCtr
```

b. Update your experiment so that the initialization of `beta` reads

```java
    Counter beta = new DblCtr(new BasicCounter());
```

c. What effect do you expect this change to have on the output?

d. Check your prediction experimentally.

e. Update your experiment so that the initialization of
`beta` reads

```java
    Counter beta = new DblCtr(new DblCtr(new BasicCounter()));
```

f. What effect do you expect this change to have on the output?

g. Check your prediction experimentally.

h. Summarize what you learned from this exercise.


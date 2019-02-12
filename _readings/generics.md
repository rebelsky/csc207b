---
title: Generics and parameteric polymorphism
summary: |
  We consider the mechanisms in Java
  for creating "generic" classes, classes that can
  work with a variety of known types of values
prereqs: |
  [Classes](../readings/classes).
  [Interfaces](../readings/interfaces).
  [Subtype polymorphism](../readings/subtype-polymorphism).
todo: 
  - Ernest says talk about multiple type variables.
---
Introduction
------------

When programmers start to design structures that collect values,
they hit one immediate design problem: Should the structure contain
be homogeneous, and contain only one type of values, or should the 
structure be heterogeneous and contain multiple types of values?
There are many situations in which we want homogeneous data
types - e.g., not just any list, but a list of strings or a
list of integers or ...

But, once you've decided to have homogeneous data types, how do we
write *general* data types.  Once we've implemented all of the
methods for a list of strings, the implementation for a list of
integers or a list of people or a list of almost anything should
be essentially the same.

And now we hit an important language design issue: How does the
language allow the programmer to design generalized homogeneous
collections so that we can indicate the type of value in the
collection?  The typical strategy is to allow programmers to design
collections (classes, interfaces) that have a type parameter.  Such
parameterized classes and interfaces are typically called *generics*.
We'll consider how to write generics in Java.

An example: Simple array-like collections
-----------------------------------------

Let's consider a simple example to ground the design.  Let's suppose
we were going to evaluate expandable arrays of strings.  We'll start
with the interface.

```java
/**
 * Expandable arrays of strings.
 */
public interface ExpandableArrayOfStrings {
  /**
   * Set the ith element of the array to str.
   *
   * @pre i >= 0
   * @post get(i) = str
   */
  public void set(int i, String str);

  /**
   * Get the ith element of the array.  If the ith element has not
   * been set, returns null.
   *
   * @pre i >= 0
   */
  public String get(int i);
} // interface ExpandableArrayOfStrings
```

Now, let's see how we might implement that interface.  We'll probably
have a field that stores the contents.

```java
   /**
    * The strings in the array.
    */
   String[] values;
```

When we create a new object, we'll initialize that array.

```java
  /**
   * Create a new expandable array.
   */
  public SimpleExpandableArrayOfStrings() {
    this.values = new String[16];
  } // SimpleExpandableArrayOfStrings()
```

To set the ith element of the expandable array, we make sure that
the underlying array is big enough.  If not, we expand it.  We can
then set using the normal mechanism for setting values.

```java
  public void set(int i, String str) {
    // If the array is not big enough, expand it
    if (this.values.length <= i) {
      int newsize = this.values.length * 2;
      while (newsize <= i) {
        newsize *= 2;
      } // while
      this.values = Arrays.copyOf(this.values, newsize);
    } // if the array is no big enough
    // And set the values
    this.values[i] = str;
  } // set(int, String)
```

To get the ith element of the expandable array, we make sure that
i is not too big.  If it is too big, we just return null.

```
  public String get(int i) {
    // If the array is not big enough, just return null
    if (this.values.length <= i) {
        return null;
      } // if the array is not big enough
    // Get the value
    return this.values[i];
  } // get(int)
```

What happens when we want to store something else, such as `BigInteger`
values?  We don't really want to copy, paste, and change the code.
What do we do?  That's where generics enter the picture.

In essence, we want to say that we have "an expandable array of
values of some type, T".  That is, the type, T, is a parameter
to the class definition.  In Java, we follow the class name with
a less-than sign, the type variable, and a greater-than sign.

```java
public interface ExpandableArray<T> {
  /**
   * Set the ith element of the array to val.
   *
   * @pre i >= 0
   * @post get(i) = val
   */
  public void set(int i, T val);

  /**
   * Get the ith element of the array.  If the ith element has not
   * been set, returns null.
   *
   * @pre i >= 0
   */
  public T get(int i);
} // interface ExpandableArray<T>
```

Not much of a change, is it?

But what should we do with the implementation?  Essentially,
we would hope that all we have to do is replace each instance of
`String` with `T`.

```java
public class SimpleExpandableArray<T> implements ExpandableArray<T> {
  T[] values;

  public SimpleExpandableArray() {
    this.values = new T[16];
  } // SimpleExpandableArray

  public void set(int i, T val) {
    // If the array is not big enough, expand it
    if (this.values.length <= i) {
      int newsize = this.values.length * 2;
      while (newsize <= i) {
        newsize *= 2;
      } // while
      this.values = Arrays.copyOf(this.values, newsize);
    } // if the array is no big enough
    // And set the values
    this.values[i] = val;
  } // set(int, T)

  public T get(int i) {
    // If the array is not big enough, just return null
    if (this.values.length <= i) {
      return null;
    } // if the array is not big enough
    // Get the value
    return this.values[i];
  } // get(int)
} // class SimpleExpandableArray<T>
```

Unfortunately, life isn't quite that simple.  There are some complex
typing issues that correspond to making arrays of a generic type.
Instead, Java requires us to create an array of objects and cast
it to the appropriate type.  But that cast is unsafe, so Java also
requires us to say that we know it's unsafe.  Here's what we have
to write instead.

```java
  @SuppressWarnings({"unchecked"})
  public SimpleExpandableArray() {
    this.values = (T[]) new Object[16];
  } // SimpleExpandableArray
```

Once we've created the generic class, we can create objects in
that class as we would normally, except that we provide a type
to the constructor.

```java
  ExpandableArray<String> strings = new SimpleExpandableArray<String>();
  ExpandableArray<BigInteger> numbers = new SimpleExpandableArray<BigInteger>();
```

An example: Predicates
----------------------

Here's a simple generic class.  Sometimes we want to be able to
apply a true/false function to an object, such as when we're 
searching for a value that meets a certain criterion.

```java
/**
 * A simple predicate.
 */
public interface Predicate<T> {
  public boolean holds(T val);
} // Predicate<T>
```

Generic methods
---------------

We've seen how to make generic interfaces and generic classes.  Can
we also make generic functions?  Yes, you may have already seen one.
We can write static functions that work with a variety of types, but
do appropriate type checking.  In this case, you put the type
variable immediately after the static declaration.  For example, 
given an array of values, we can search it with the following.

```java
  /**
   * Return the first value in vals for which the pred holds.
   * Return null if nothing is found.
   */
  public static <T> T search(T[] vals, Predicate<T> pred) {
    for (int i = 0; i < vals.length; i++) {
      if (pred.holds(vals[i])) {
        return vals[i];
      } // if
    } // for
    return null;
  } // search(T[], Predicate<T>)
```

We'll see how to use this interface in the lab.

Dealing with multiple type variables
------------------------------------

We've seen how to parameterize a class definition with one type.  Can
we do it with more than one type?  Yes.  Here's a very simple class
that can hold two kinds of values.

```java
public class Pair<T,U> {
  T val1;
  T val2;
} // class Pair<T,U>
```

We can now create objects that hold two types of values, but know what
kinds of values they can hold.

```java
  Pair<BigInteger,String> foo = new Pair<BigInteger,String>();
  Pair<BigInteger,BigInteger> bar = new Pair<BigInteger,BigInteger>();
```

We'll see some interesting applications of multi-parameterized
generics in the near future.

Parametric polymorphism
-----------------------

You may recall that we have recently discuss *subtype polymorphism*.
In that kind of polymorphism, a variable or parameter can take on
many forms (e.g., the form of any object that provides the methods
specified in an interface).  Similarly, Java generics can take on
many forms: When you provide a type parameter, you effectively get
a new class or method or whatever.  

To distinguish the two types of polymorphsm, we use *subtype polymorphism*
to describe the idea that we can use an element of one type in place of
another, and we use *parametric polymorphism* to describe the kind
of polymorphism that we get from type parameters.


---
title: Generics and parameteric polymorphism
summary: |
  We consider the mechanisms in Java
  for creating "generic" classes, classes that can
  work with a variety of known types of values
prereqs: |
  [Classes](../readings/classes).
  [Interfaces](../readings/interfaces).
  [Subtype polymorphism](../readings/subtype-polymoprhism).
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

  But, once you've decided to have homogeneous data types, how do we write
  *general* data types.  Once we've implemented all
  of the methods for a list of strings, the implementation for a list
  of integers or a list of people or a list of almost anything should
  be essentially the same.

  And now we hit an important language design issue: How does the
  language allow the programmer to design generalized homogenous
  collections so that we can indicate the type of value in
  the collection?  The typical strategy is to allow programmers to
  design collections (classes, interfaces) that have a type 
  parameter.  Such parameterized classes and interfaces are typically
  called *generics*.  We'll consider how to write
  generics in Java.

An Example: Simple Array-Like Collections
-----------------------------------------

  Let's consider a simple example to ground the design.  Let's suppose
  we were going to evaluate expandable arrays of strings.  We'll start
  with the interface.

<pre>
/**
 * Expandable arrays of strings.
 */
public interface ExpandableArrayOfStrings
{
  /**
   * Set the ith element of the array to str.
   *
   * @pre i &gt;= 0
   * @post get(i) = str
   */
  public void set(int i, String str);

  /**
   * Get the ith element of the array.  If the ith element has not
   * been set, returns null.
   *
   * @pre i &gt;= 0
   */
  public String get(int i);
} // interface ExpandableArrayOfStrings
</pre>

  Now, let's see how we might implement that interface.  We'll probably
  have a field that stores the contents.

<pre>
   /**
    * The strings in the array.
    */
   String[] values;
</pre>

  When we create a new object, we'll initialize that array.

<pre>
  /**
   * Create a new expandable array.
   */
  public SimpleExpandableArrayOfStrings()
  {
    this.values = new String[16];
  } // SimpleExpandableArrayOfStrings()
</pre>

  To set the ith element of the expandable array, we make sure that
  the underlying array is big enough.  If not, we expand it.  We can
  then set using the normal mechanism for setting values.

<pre>
  public void set(int i, String str)
  {
    // If the array is not big enough, expand it
    if (this.values.length &lt;= i)
      {
        int newsize = this.values.length * 2;
        while (newsize &lt;= i)
          {
            newsize *= 2;
          } // while
        this.values = Arrays.copyOf(this.values, newsize);
      } // if the array is no big enough
    // And set the values
    this.values[i] = str;
  } // set(int, String)
</pre>

  To get the ith element of the expandable array, we make sure that
  i is not too big.  If it is too big, we just return null.

<pre>
  public String get(int i)
  {
    // If the array is not big enough, just return null
    if (this.values.length &lt;= i)
      {
        return null;
      } // if the array is not big enough
    // Get the value
    return this.values[i];
  } // get(int)
</pre>

  What happens when we want to store something else, such as 
  `BigInteger` values?  We don't really want to copy,
  paste, and change the code.  What do we do?  That's where
  generics enter the picture.  

  In essence, we want to say that we have an expandable array of
  values of some type, T.  In Java, we follow the class name with
  a less-than sign, the type variable, and a greater-than sign.

<pre>
public interface ExpandableArray&lt;T&gt;
{
  /**
   * Set the ith element of the array to val.
   *
   * @pre i &gt;= 0
   * @post get(i) = val
   */
  public void set(int i, T val);

  /**
   * Get the ith element of the array.  If the ith element has not
   * been set, returns null.
   *
   * @pre i &gt;= 0
   */
  public T get(int i);
} // interface ExpandableArray&lt;T&gt;
</pre>

  Not much of a change, is it?

  But what should we do with the implementation?  Essentially,
  we would hope that all we have to do is replace each instance of
  `String` with `T`.

<pre>
public class SimpleExpandableArray&lt;T&gt;
  implements ExpandableArray&lt;T&gt;
{
  T[] values;

  public SimpleExpandableArray()
  {
    this.values = new T[16];
  } // SimpleExpandableArray

  public void set(int i, T val)
  {
    // If the array is not big enough, expand it
    if (this.values.length &lt;= i)
      {
        int newsize = this.values.length * 2;
        while (newsize &lt;= i)
          {
            newsize *= 2;
          } // while
        this.values = Arrays.copyOf(this.values, newsize);
      } // if the array is no big enough
    // And set the values
    this.values[i] = val;
  } // set(int, T)

  public T get(int i)
  {
    // If the array is not big enough, just return null
    if (this.values.length &lt;= i)
      {
        return null;
      } // if the array is not big enough
    // Get the value
    return this.values[i];
  } // get(int)
} // class SimpleExpandableArray&lt;T&gt;
</pre>

  Unfortunately, life isn't quite that simple.  There are some complex
  typing issues that correspond to making arrays of a generic type.
  Instead, Java requires us to create an array of objects and cast
  it to the appropriate type.  But that cast is unsafe, so Java also
  requires us to say that we know it's unsafe.  Here's what we have
  to write instead.

<pre>
  @SuppressWarnings({"unchecked"})
  public SimpleExpandableArray()
  {
    this.values = (T[]) new Object[16];
  } // SimpleExpandableArray
</pre>

  Once we've created the generic class, we can create objects in
  that class as we would normally, except that we provide a type
  to the constructor.

<pre>
  ExpandableArray&lt;String&gt; strings = 
      new SimpleExpandableArray&lt;String&gt;();
  ExpandableArray&lt;BigInteger&gt; numbers = 
      new SimpleExpandableArray&lt;BigInteger&gt;();
</pre>

An Example: Predicates
----------------------

  Here's a simple generic class.  Sometimes we want to be able to
  apply a true/false function to an object, such as when we're 
  searching for a value that meets a certain criterion.

<pre>
/**
 * A simple predicate.
 */
public interface Predicate&lt;T&gt;
{
  public boolean holds(T val);
} // Predicate&lt;T&gt;
</pre>

Generic Methods
---------------

  We've seen how to make generic interfaces and generic classes.  Can
  we also make generic functions?  Yes, you may have already seen one.
  We can write static functions that work with a variety of types, but
  do appropriate type checking.  In this case, you put the type
  variable immediately after the static declaration.  For example, 
  given an array of values, we can search it with the following.

<pre>
  /**
   * Return the first value in vals for which the pred holds.
   * Return null if nothing is found.
   */
  public static &lt;T&gt; T search(T[] vals, Predicate&lt;T&gt; pred)
  {
    for (int i = 0; i &lt; vals.length; i++)
      {
        if (pred.holds(vals[i]))
          return vals[i];
      } // for
    return null;
  } // search(T[], Predicate&lt;T&gt;)
</pre>

  We'll see how to use this in the lab.

Dealing with Multiple Type Variables
------------------------------------

  We've seen how to parameterize a class definition with one type.  Can
  we do it with more than one type?  Yes.  Here's a very simple class
  that can hold two kinds of values.

<pre>
public class Pair&lt;T,U&gt;
{
  T val1;
  T val2;
} // class Pair&lt;T,U&gt;
</pre>

  We can now create objects that hold two types of values, but know what
  kinds of values they can hold.

<pre>
  Pair&lt;BigInteger,String&gt; foo = new Pair&lt;BigInteger,String&gt;();
  Pair&lt;BigInteger,BigInteger&gt; bar = new Pair&lt;BigInteger,BigInteger&gt;();
</pre>

  We'll see some interesting applications of multi-parameterized
  generics in the near future.


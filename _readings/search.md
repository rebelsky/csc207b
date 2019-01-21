---
title: Linear and binary search
summary: |
  We consider the standard problem of searching an array for a value
  or values.  That is, we consider algorithms that, given a target
  value, a collection of values, and a notion of equality, find the
  position of the target value.  We explore two common searching
  algorithms, linear search and binary search.  We also consider
  ways in which object-oriented design (and its instantiation in
  the Java programming language) may lead us to think about searching
  in slightly different ways.
---
Introduction
------------

  One of the most common tasks computer programs do is *
  search* - look through a collection of information for
  something matching particular criteria.  Sometimes the search is
  huge and illformed, as in Google searches.  Sometimes the search
  is small and precise.

  There are a host of ways to organize information to make searching
  faster and easier.  We'll consider some of those mechanisms 
  this semester.  For now, we'll start with one of the simplest
  phrasings of the search problem: *given a value to search for
  and an array of values to search in, find the index of the value
  in that array*.

Linear Search
-------------

  The simplest searching algorithm is often called *linear
  search*, and involves looking at each element in turn
  until we find one that matches.

<literallayout>For each index, i, in values
    If values[i] is equal to val
        return i;
If the value was never found, signal that problem
</literallayout>

Analyzing Linear Search
-----------------------

  How long does linear search take?  Well, it depends.  If the thing
  we're searching for appears near the beginning of the array, it takes
  only a few steps.  If it appears at the end of the array, we'll need
  to look at almost all of the elements of the array.   And, if it doesn't
  appear at all, we need to look at every element.

  Since, in the worst case, we look at every element, our loop will
  run O(n) times.  Each repetition of the loop requires a constant
  number of steps (or so we hope - comparison *should*
  be fast), so the algorithm takes O(n) time.

Linear Search in Java
---------------------

  Is there anything special about linear search in Java?  A few things.
  Let's start with the "obvious" implementation.

<pre>
/**
 * Search values for an object equal to value.
 *
 * @param value
 *   A value to search for
 * @param values
 *   An array of values to search
 * @return index
 *   The index of a value equal to value
 * @pre
 *   Elements of values are all comparable to value using equals.
 * @post
 *   value.equals(values[index])
 * @throws Exception
 *   If the value is incomparable to an element of values.
 * @throws NotFound
 *   If no equal value appears.
 */
public static int search(Object value, Object values[]) 
  throws NotFound, Exception 
{
  for (int i = 0; i &lt; values.length; i++) 
    {
      if (value.equals(values[i]))  
        return i;
    } // for
  throw new NotFound(value + " does not appear");
} // search
</pre>

  Experienced functional and object-oriented programmers might note
  that an important element seems to be implicit in this procedure.
  That is, we use the `equals` method of the 
  value to compare values.  However, it turns out that when we're
  searching an array, we may want to search using different criteria.
  For example, if we're searching for a person, we might want to 
  match by last name, id number, or even some physical characteristics.
  Hence, rather than just relying on the `equals`,
  we really should be passing in an object that knows how to compare
  things for equality.  (A functional programmer would just pass
  in a function, but we need objects in Java.)  We might call such
  a thing an `EqualityComparator` and define it with an
  interface something like the following.

<pre>
public interface EqualityComparator 
{
  /**
   * Determine if x and y are equal using some comparison metric.
   */
  public boolean equals(Object x, Object y) 
    throws IncomparableException;
} // interface EqualityComparator
</pre>

  Once we've done so, we would rewrite our linear search algorithm
  as follows.

<pre>
/**
 * Search values for an object equal to value.
 *
 * @param value
 *   A value to search for
 * @param values
 *   An array of values to search
 * @param EqualityComparator
 *   The equality metric
 * @return index
 *   The index of a value equal to value
 * @pre
 *   Elements of values are all comparable to value using equals.
 * @post
 *   value.equals(values[index])
 * @throws IncomparableException
 *   If the value is incomparable to an element of values.
 * @throws NotFound
 *   If no equal value appears.
 */
public static int search(Object value, Object values[], EqualityComparator eq) 
  throws NotFound, IncomparableException 
{
  for (int i = 0; i &lt; values.length; i++) 
    {
      if (eq.equals(value,values[i])) 
        return i;
    } // for
  throw new NotFound(value + " does not appear");
} // search
</pre>

  If we want to compare people by last name, we might write something
  like the following.

<pre>
public class ComparePersonByLastName 
  implements EqualityComparator 
{
  public boolean equals(Object x, Object y) 
  {
    return (x instanceof Person) &amp;&amp; (y instanceof Person) &amp;&amp;
           ((Person) x).getLastName.equals((Person) y).getLastname();
  } // equals(Object, Object)
} // ComparePersonByLastName
</pre>

  Then, to search by last name, we might do something like

<pre>
  Utils.search(new Person("Smith", ""), people, new ComparePersonByLastName());
</pre>

  Of course, it's a bit silly to have to make a new comparison object
  each time we search.  Later on, we'll learn the "Singleton"
  design pattern to help with this issue.  As an alternative, we'll
  also ll learn how to write such one-shot classes "on the
  fly", without bothering to name them. (Yes, we have anonymous
  classes in object-oriented programming, just as we have anonymous
  functions in functional programming.)

  You may be wondering why we're building an object to search for 
  and a comparator to search with.  Others have asked the same thing,
  Another alternative is to do away with the value altogether and simply
  pass in a predicate that returns true (representing "yes, this 
  item is acceptable") or false (representing "no, this
  item is not acceptable").

<pre>
public interface Predicate 
{
  /**
   * Test if val meets this predicate.
   */
  public boolean test(Object val);
} // interface Predicate
</pre>

  Now, our linear search might look something like the following.
  (We've ellided most of the documentation, since it's not much different.)

<pre>
/**
 * Search values for a value for which pred holds.
 */
public static int search(Predicate pred, Object values[])  
  throws NotFound, Exception 
{
  for (int i = 0; i &lt; values.length; i++) 
    {
      if (pred.test(values[i])) 
        return i;
    } // for
  throw new NotFound(value + " does not appear");
} // search
</pre>

  And we can build a predicate to search for a last name of Smith
  using something like the following.

<pre>
public class IsSmith 
  implements Predicate 
{
  public boolean test(Object o) 
  {
    return (o instanceof Person) &amp;&amp; 
           ((Person) o).getLastName().equals("Smith");
  } // test(Object)
} // class IsSmith
</pre>

  Isn't writing general code fun?

  *But wait!*  Run-time type tests using
  `instanceof` make many Java programmers nervours.  After all,
  why use a language with compile-time type checking if you still need
  to do run-time type checking?  Wouldn't it be better to say that
  our comparator only works with `Person` objects?  In early
  versions of Java, it was not possible to specify the particular types
  you worked with - you had to do run-time type checking.  But newer
  versions of Java introduced a concept known as 
  "*generics*" which allow you to say
  that you work with a particular type.  In essence, you give a 
  parameter to your interface that specifies a type, and you can then
  use that type in your other specifications.  Here's an example.  We'll
  consider the general strategy in more depth in the near future.

  Let's start with the interface.

<pre>
public interface Predicate&lt;T&gt; 
{
  /**
   * Test if val meets this predicate.
   */
  public boolean test(T val);
} // interface Predicate&lt;T&gt;
</pre>

  You'll note that there are two changes.  We've added the
  `&lt;T&gt;` after the interface name.  The thing in
  the angle brackets is a *type variable*.  When we
  implement the interface, we can specify what type we use.  You'll
  note that we use that type variable in the specification of the
  `test` method.  In effect, when you put
  in a class name for `T`, the Java compiler
  substitutes it into the header.  So, the implicit interface
  `Test&lt;Person&gt;` declares the method

<pre>
  public boolean test(Person val);
</pre>

  We can now rewrite our predicate as follows

<pre>
public class IsSmith 
  implements Predicate&lt;Person&gt; 
{
  public boolean test(Person val) 
  {
    return val.getLastName().equals("Smith");
  } // test(Person)
} // class IsSmith
</pre>

  Isn't that much nicer?

  Of course, we must also rewrite our search method to accept generics.
  Note that we put the type parameter immediately after the 
  `static` keyword.

<pre>
/**
 * Search values for a value for which pred holds.
 */
public static &lt;T&gt; int search(Predicate&lt;T&gt; pred, T values[])  
  throws NotFound, Exception 
{
  for (int i = 0; i &lt; values.length; i++) 
    {
      if (pred.test(values[i])) 
        return i;
    } // for
  throw new NotFound(value + " does not appear");
} // search
</pre>

Random Search
-------------

  Some folks worry that linear search is biased toward elements that
  appear at the start of the array and hence prefer to search arrays
  "randomly".  Here's a simple version of a random search
  algorithm.

<literallayout>Repeat
    i = a random number in the range 0 .. n-1
    If values[i] is equal to val
       Return i;
Until we've examined all of the values in the array
If the value was not found, signal an error
</literallayout>

  Of course, this implementation has two significant problems.
  First, it can be hard to tell if we've examined all of the values
  in the array.  (I'm sure that you can figure out some strategies.)
  Second, we will often look at some values more than once, which is
  inefficient.  What's the solution?  Instead of randomly generate
  an index, we start with the list of all possible indices and 
  randomly permute it.  We can then just look at the elements in
  that order.

Binary Search
-------------

  The *binary search* algorithm is a standard
  divide-and-conquer algorithm.  It requires that the input be
  sorted.  At the highest level, binary search does the following

<literallayout>While elements remain
    Look at the middle element
    If it matches our target, return the index of the middle element
    If it is smaller than our target, throw away the bottom half
    If it is larger than our target, throw away the top half
If no elements remain, report failure
</literallayout>

  But how do we "throw away" elements in an array?  
  Traditionally, we keep track of the lower and upper bounds of the
  portion of the array of interest.  So, initially, the lower bound
  is 0 and the upper bound is length - 1.

<literallayout>While lb &lt;= ub
    mid = midpoint(lb, ub);
    If values[mid] equals value
        return mid;
    Else if values[mid] &lt; value
        lb = mid + 1;
    Else
        ub = mid - 1;
Report Failure
</literallayout>

  We can also express this algorithm recursively.

<literallayout>binarySearch(value, values, lb, ub)
    If (lb &gt; ub)
        Report Failure
    mid = midpoint(lb, ub);
    If values[mid] equals value
        return mid;
    Else if values[mid] *lt; value
        return binarySearch(value, values, mid+1, ub);
    Else
        return binarySearch(value, values, lb, mid-1);
</literallayout>

  Why prefer one over the other?  I find the recursive version easier
  to analyze.  However, unless your compiler optimizes tail calls,
  the iterative version is likely to be slightly faster.

Analyzing Binary Search
-----------------------

  As noted above, the recursive version is probably a little easier
  to analyze.

  What's the running time for input of size n?  Well, we do a constant
  number of tests (no more than three, I believe) and we also have to
  compute the midpoint of two values.  And then we probably have to recurse
  on about half of the input (maybe exactly half, maybe slightly less.)
  That gives the recurrence relation

* time(n) = c + time(n/2)
* time(1) = b
  How can we solve this relation?  Let's try a few values.

* time(1) = b
* time(2) = c + time(1) = c + b
* time(4) = c + time(2) = c + c + b = 2c + b
* time(8) = c + time(4) = c + 2c + b = 3c + b
* time(16) = c + time(8) = c + 3c + b = 4c + b
  Do you see a pattern yet?  It looks like
  time(2<superscript>k</superscript>) = k*c + b.  (You can prove this
  via induction, if you'd like.)  Since k grows as the log of
  2<superscript>k</superscript>, it looks like binary search has
  running in O(log<subscript>2</subscript>n).

  But wait!  We've only analyzed powers of 2.  Does that matter?
  Probably not.  The upper bound on the running time is clearly 
  increasingly monotonically.  So, the number of steps, for any
  n is bounded by the number of steps for a power of two greater than
  n.  (You can finish this proof, too.)

Binary Search in Java
---------------------

  In implementing binary search, one important question is how we compare
  individual elements.  In linear search, we had to compare only for
  equality, which allowed us to use the built-in `equals`
  method (or a few alternates).  In binary search, we need to know if
  the value we're looking at should appear before or after a non-equal
  value.  (We also still need to know if it's equal.)  Fortunately,
  Java provides an interface for just that purpose, 
  `[java.util.Comparator](http://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html)`.  You should read the documentation
  for that class.  However, here's a quick snippet of what it might look
  like.

<pre>
public interface Comparator&lt;T&gt; 
{
  /**
   * Compare o1 and and o2 for order.  Return a negative number of o1
   * comes before o2, zero if the two are equal, and a postive number
   * if o1 comes after o2.
   */
  public int compare(T o1, To2);
} // Comparator&lt;T&gt;
</pre>

  We'll leave it as an exercise for you to write binary search.

Citations
---------

  Portions of this reading are based on materials prepared for 
  Grinnell College's CSC 151 by Janet Davis, Samuel A. Rebelsky,
  John David Stone, Henry Walker, and Jerod Weinman.


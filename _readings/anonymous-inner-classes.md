--- 
title: Anonymous Inner Classes
summary: |
  We consider Java's "anonymous inner classes" as a programming
  technique.
todo:
  - Rewrite the main example!  
  - Move iterator stuff to the iterator reading.
---

Introduction: A problem
-----------------------

As you may have noted, in much of our recent work, we've ended up 
creating classes that (a) are used by only one other class or (b)
are effectively used only once.  For example,

* In searching or filtering a list, we need to build predicates to
  guide the search or filter.
* In sorting a list or array, we need to build comparators that determine
  how values are ordered.  Sometimes we can use predefined orderings,
  but at other times we'll need custom orderings.
* We need to build iterators for most of our collection classes.  (This
  is a case in which we'll build multiple copies, but the class only
  gets used by our class, and is not very interesting.)
  For those used to functional programming, the first two issues
  suggest that we might use *anonymous functions*.
  For example, in Scheme, to select all the values less than 10 in a list
  of numbers, we might write `(list-select numbers (lambda (val)
  (&lt; val 10)))`
   or `(list-select numbers (r-s &lt; 10))`.

  Java (at least Java up through version 7) does not include functions
  as a first-class data type, so it's unlikely that we'd have anonymous
  functions in Java.  However, in version 1.1, Java introduced 
  *anonymous classes*, which can help serve the
  same purpose.  In this reading, we explore the usage of anonymous
  inner classes.

Detour: A Simple Problem Domain
-------------------------------

  While we will eventually explore anonymous inner classes in the
  context of predicates, comparators, iterators, and more, let's
  start with an invented situation that is simultaneously simpler
  and more complex than some of these examples.

  At times, we want objects that can greet the user.  We'll call
  such objects Greeters, and give them a simple interface.

<pre>
  <xi:include href="../git/anonymous-inner-classes/src/taojava/aic/Greeter.java" parse="text" xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

  Here's a very simple object that implements the Greeter interface.

<pre>
  <xi:include href="../git/anonymous-inner-classes/src/taojava/aic/SampleGreeter.java" parse="text" xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

  Now, let's think like Java designers.  Sometimes, given an object,
  we might want to make a greeter for that object.  Why doesn't
  the object want to be it's own greeter?  Maybe we need more than
  one, and want each to behave differently, or at least independently.
  (In case you couldn't tell, we're reflecting on iterators and
  iterables, as well as some other paired interfaces.)  We'll call
  things that generate greeters Greetables.

<pre>
  <xi:include href="../git/anonymous-inner-classes/src/taojava/aic/Greetable.java" parse="text" xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

  And here's a really simple implementation.

<pre>
  <xi:include href="../git/anonymous-inner-classes/src/taojava/aic/SampleGreetable1.java" parse="text" xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

Introducing Anonymous Greeters
------------------------------

  You haven't seen anything too surprising so far, or at least I hope you
  haven't.  Now, let's move a step further.  Suppose we want to create
  a new Greetable that returns a greeter that returns the string
  "Hi!".  We could use the same technique as above.  But
  we're creating a `Greeter` class that only gets used
  once.  What's the alternative?

  The alternative is an anonymous inner class.  Rather than writing down
  the full class definition, we can use the keyword `new`,
  the interface name, and then the body of a class.  That is,

<pre>
  <xi:include href="../git/anonymous-inner-classes/src/taojava/aic/SampleGreetable2.java" parse="text" xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

  Are there any advantages to this approach?  After all, it's not that
  much less code than in the previous example.  The most obvious benefit
  is that we've achieved fairly strong *encapsulation* -
  since the class is within `SampleGreetable2`, nothing else
  can access it.  We also have the normal benefit of anonymity - if the
  class is only used here, there's no need to come up with a name for
  it.  But these two reasons alone are not the only reasons we like
  anonymous inner classes.

Referencing Fields of the Enclosing Class
-----------------------------------------

  Once of the nice things about inner classes is that they can reference
  the fields of the enclosing class.  In the following example, when
  we construct the Greeter, we copy and change a field in the enclosing
  class.

<pre>
  <xi:include href="../git/anonymous-inner-classes/src/taojava/aic/SampleGreetable3.java" parse="text" xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

  Suppose we wrote the following.

<pre>
PrintWriter pen = new PrintWriter(System.out, true);
Greetable g = new SampleGreetable3();
Greeter[] greeters = new Greeter[] 
  {
    g.greeter(), g.greeter(), g.greeter(), g.greeter(), g.greeter() 
  };
for (int i = 0; i &lt; greeters.length; i++) 
  {
    pen.print(i + ": ");
    greeters[i].greet(pen);
  } // for
</pre>

  Our output will be

<pre>
0: Number 1
1: Number 2
2: Number 3
3: Number 4
4: Number 5
</pre>

  Of course, we could achieve the same effect by writing a separate
  class and giving it a constructor that takes `i` as a
  parameter, but that's a lot more coding.

  But we're not done yet!  Not only can we reference the fields of
  an enclosing class when we build the inner class, we can even reference
  them when we run the method.

<pre>
  <xi:include href="../git/anonymous-inner-classes/src/taojava/aic/SampleGreetable4.java" parse="text" xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

  If we run code similar to the above, we'll get

<pre>
0: 1 of 5
1: 2 of 5
2: 3 of 5
3: 4 of 5
4: 5 of 5
</pre>

Referencing Parameters of an Enclosing Methods
----------------------------------------------

  Things are going pretty well.  We can reference a field of the enclosing
  class when we build an object in an inner anonymous class.  We can 
  reference a field of the enclosing class when someone invokes the
  object's methods.  What else would we want to do?  Well, if we are
  used to a functional model, we might even want to be able to reference
  a parameter or local variable in an enclosing method.  For example,
  we might want to write something like the following:

<pre>
Greeter makeGreeter(int n) 
{
  return new Greeter() 
    {
      @Override
      public void greet(PrintWriter pen) 
      {
        pen.println(n);
      } // greet(PrintWriter)
    }; // new Greeter
} // makeGreeter
</pre>

  However, if we try that, the Java compiler will greet us with a friendly
  message something like the following:

<pre>
SampleGreetable5.java:19: error: local variable n is accessed from within inner class; needs to be declared final
                pen.println(n);
                            ^
1 error
</pre>

  What's going on here?  The Java compiler is worried that `n`
  gets referenced in a method that may be called when that method is no
  longer in scope.  (And we can be pretty sure that it won't be in scope
  when `greet` gets called.)  Some languages like Scheme have
  a clever way of dealing with these issues.  Java, on the other hand,
  doesn't want to have to deal with it.  So, Java is only willing to let
  you reference the parameter if it knows that the parameter won't change,
  in which case it can just grab the current value.  The `final`
  modifier is how you tell Java that it won't change.  With this update,
  we can write the following:

<pre>
  <xi:include href="../git/anonymous-inner-classes/src/taojava/aic/SampleGreetable5.java" parse="text" xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

What Does "`this`" Mean?
------------------------------------------------

  You'll note that we have not been using `this` in referencing
  fields.  That's because `this` can feel a bit ambiguous
  for inner classes - does `this` refer to the inner class 
  that we're building or to the enclosing class?  Let's try updating
  the 4th example to see what the Java compiler tells us.

<pre>
public void greet(PrintWriter pen) 
{
  pen.println(this.num + " of " + this.i);
} // greet(PrintWriter)
</pre>

  As you might expect, we get another helpful message.

<pre>
SampleGreetable6.java:17: error: cannot find symbol
                pen.println(this.num + " of " + this.i);
                                                    ^
  symbol: variable i
1 error
</pre>

  We've learned a lot from that message.  The compiler didn't complain about
  `this.num`, so it seems that `this` refers to the
  inner class.  It did, however, complain about `this.i`, and 
  so `this` does not refer to the enclosing class.  It turns
  out that to refer to the enclosing class, you prefix `this`
  with the class name.  

<pre>
  <xi:include href="../git/anonymous-inner-classes/src/taojava/aic/SampleGreetable6.java" parse="text" xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

Anonymous Predicates
--------------------

  At this point you may be saying to yourself "Cool!  I can use
  this when working with predicates."  Or you may be asking 
  yourself "How can I use this in practice?"  (Guess what,
  one answer is "with predicates".)  So let's do a quick
  exploration of why anonymous inner classes might be useful for
  predicates.

  We'll start with a sample use of predicates, printing all of the
  values in an array for which the predicate holds.

<pre>
/**
 * Print all the elements of the array for which pred holds.
 */
public static &lt;T&gt; void printMatching(PrintWriter pen, T[] vals,
        Predicate&lt;T&gt; pred) 
{
  for (T val: vals) 
    {
      if (pred.test(val)) 
        {
            pen.println(val + " ");
        } // if
    } // for
  pen.println();
} // printMatching(PrintWriter, T[], Predicate&lt;T&gt;)
</pre>

  Now, suppose we have an array of strings and want all of the short
  strings (say the strings of length less than or equal to 4).  We could 
  write something like the following:

<pre>
Predicate&lt;String&gt; small = new Predicate&lt;String&gt;() 
{
  @Override
  public boolean test(String str) 
  {
    return str.length() &lt;= 4;
  } // test(String)
}; // new Predicate&lt;String&gt;
</pre>

  Now we can print the small strings in our test with something like

<pre>
  printMatching(pen, essay, small);
</pre>

Anonymous Inner Classes and Iterators
-------------------------------------

  *Students who have not yet learned about iterators can
  ignore this section.  Sorry.*

  You may recall that way back at the start of this reading, we considered
  problems in which we create one-off classes, classes that only need 
  to be used only in one situation.  We've looked at Predicates.  It's
  likely that comparators will be similar.  But how is this all useful
  for iterators?  Well, most of the times we've written iterators,
  we've need to refer to fields and of the enclosing class.  And so
  it's much easier to write iterators as anonymous inner classes, 
  rather than as helper classes.  Here's a fraction of a typical
  stack class.

<pre>
public class Stack&lt;T&gt; implements Iterable 
{
  T[] values;
  int size;

// ...

  public Iterator&lt;T&gt; iterator() 
  {
    return new Iterator&lt;T&gt;() 
      {
        int i = size-1;

        @Override 
        public T next() throws NoSuchElementException 
        {
          if (hasNext()) 
            {
              throw new NoSuchElementException();
            } // if (hasNext())
          return Stack.this.values[this.i--];
        } // next()

        @Override
        public boolean hasNext() 
        {
          return this.i >= 0;
        } // hasNext()

        @Override
        public void remove() 
        {
          throw new UnsupportedOperationException();
        } // remove()
      }; // new Iterator&lt;T&gt;
    } // iterator()
} // class Stack&lt;T&gt;
</pre>

  Like anonymous functions and named lets in Scheme, anonymous inner
  classes are powerful and convenient for experienced programmers and
  a bit overwhelming for novice programmers.  Take it slowly, and you'll
  soon find that anonymous inner classes will be a useful tool for many
  situations.


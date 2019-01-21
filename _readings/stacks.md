---
title: Stacks
summary: |
  We consider the essential features of *stacks*, one of the forms
  of linear structures.  We also consider a straightforward
  implementation of stacks.
prereqs: |
  [Linear structures](../readings/linear-structures).
  Arrays.
  [Subtype polymorphism](../readings/subtype-polymorphism).
  [Inheritance](../readings/inheritance).
---
Stack basics
------------

  Now that you understand about linear structures, stacks are a fairly
  simple abstract data type.  Stacks are simply linear structures
  that implement that *last in, first out*
  (also "LIFO") policy.  That is, the value returned by
  `get` is the value most recently added to the stack.

An Interface
------------

  We might describe the interface for stacks as follows.

<pre>
<xi:include href="../git/linear/src/taojava/util/Stack.java" parse="text"  xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

  One aspect of this code you may find of interest is that we have said
  that `Stack extends LinearStructure`.  This extension is
  similar to the extension you've seen for classes, although it is used
  primarily for polymorphism.  It means that you can use a `Stack`
  whereever code expects a `LinearStructure`

Applications of Stacks
----------------------

  There are a variety of ways in which computer scientists use stacks.
  At times, they use them simply because they need some linear structure,
  and stacks are convenient to use.  More frequently, they identify
  problems for which the last-in, first-out policy is particularly
  appropriate.

  One such class of problems involves matching symbols, such as tags in an
  HTML document or parens in a Scheme program.  In essence, whenever you
  see an opening symbol, such as `&lt;b&gt;` in an HTML
  document or `(` in a Scheme program, you push it on the
  stack.  When you see a closing symbol, such as `&lt;/b&gt;`
  in an HTML document or `)` in a Scheme program, you pop
  the value on the stack and compare it to the closing symbol.  If they
  match, you continue on.  If they fail to match, you report an error.
  Clearly, if you encounter an end symbol with an empty stack, there's
  something seriously wrong with the document or program.  Similarly,
  if the stack contains values at the end, there are also significant
  problems, since not all opening symbols are matched.

  Stacks are also useful for certain kinds of operations.  For
  example, some mathematicians like to use *reverse polish
  notation* (RPN), in which the operation follows the operands.
  In such a system, we would write "add 2 and the product of 3
  and 7" as `2 3 7 * +`.  RPN is useful because you
  don't have to worry about precedence rules.  RPN is also very easy to
  implement using stacks.

* Whenever you encounter a value, you push it.
* Whenever you encounter an operation, pop the parameters and push the result.
* The value on the top of the stack when you are done is the final result.Implementing Stacks with Arrays
-------------------------------

  It is also fairly easy to implement stacks, at least once we have another
  data structure, such as an array.  In an array-based implementation of
  stacks, we typically store the values as they come in, starting at index 0.
  When we pop a value, we use the index of the last value added and decrease
  that value.  (Typically, we use a field called `top` to keep
  track of where to add values.)

  Given that strategy, here's the basic code for `put`.

<pre>
this.contents[this.top] = newvalue;
this.top++
</pre>

  Most programmers would express this more concisely as follows.

<pre>
this.contents[this.top++] = newvalue;
</pre>

  It is equally easy to get the value at the top of the stack: We just
  reverse those two steps.

<pre>
this.top--;
return this.contents[this.top];
</pre>

  Again, we can combine these two lines into a single instruction. 

<pre>
return this.contents[--this.top];
</pre>

  Note that when we combine the operations, we use the prefix version
  of the decrement operation so that `this.top` is decremented
  before we use it as an index.

  To avoid accidental access to removed data, many programmers
  make it a habit to clear out the entry in an array whenever we remove
  a value.  Hence, we will need to temporarily store the value to be
  returned before clearing the cell and then returning that value.

<pre>
T returnme = this.contents[--this.top];
this.contents[this.top] = null;
return returnme;
</pre>

  Determining whether or not the stack is empty is also easy: The stack
  is empty only when `top` is 0.

<pre>
return (top == 0);
</pre>

  The only hard part is what to do when the stack "fills".  The
  simplest thing to do is to make a bigger array and copy the values over.
  The particular code for doing so is left as an exercise for the reader.

A Linked Implementation of Stacks
---------------------------------

  We can also implement stacks using linked nodes.  What is a node?  
  It's a simple data type that contains two parts: a value and a link
  to the next node.

<pre>
<xi:include href="../git/linear/src/taojava/util/Node.java" parse="text"  xmlns:xi="http://www.w3.org/2001/XInclude"
/></pre>

  For a stack, we just need a pointer to the node at the front of the
  stack, which we'll call `front`.

  To put something at the front of the stack, we can write

<pre>
this.front = new Node(val, this.front);
</pre>

  To get something from the front of the stack, we can write

<pre>
T result = this.front.val;
this.front = this.front.next;
return result;
</pre>

  Ideally, the stack never fills.  If it does, we're in pretty big
  trouble, because we can't even create a small object, which means
  that we'll also have trouble creating an exception object.

<pre>
public boolean isFull()
{
  return false;
} // isFull()
</pre>

  We can check if the stack is empty by comparing `front`
  to `null`.

<pre>
public boolean isEmpty()
{
  return this.front == null;
} // isEmpty()
</pre>

Terminology
-----------

  Unfortunately, some bright computer scientists designed the stack ADT
  before some other bright computer scientists designed the more general
  linear structure ADT.  Hence, the terms that many folks use for the
  basic stack operations are not `put` and `get`,
  but rather `push` and `pop`.

  To make our code more general, we will stick with the linear structure
  terms.

Citation
--------

  This reading is based on a similar reading I created as a part of
  *Espresso*.


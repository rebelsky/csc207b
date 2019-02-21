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
that implement that *last in, first out* (also "LIFO") policy.  That
is, the value returned by `get` is the value most recently added
to the stack.

A Stack interface
-----------------

It may be strange that we're defining an interface for stacks, given
that `LinearStructure` is already an interface.  However, we can have
hierarchies of interfaces, just as we have hierarchies of classes.  As
importantly, there are multiple implementations of stacks, so we need
to be careful to separate interface from implementation.

We might describe the interface for stacks as follows.

```java

/**
 * A linear structure that follows the last-in, first-out policy.
 *
 * @author Samuel A. Rebelsky
 */
public interface Stack<T> extends LinearStructure<T> {
  /**
   * Add an element to the stack.
   *
   * @param val
   *   the value to add.
   * @pre
   *   !this.isFull()
   * @post
   *   The stack now contains an additional element of val.
   * @exception Exception
   *   If the structure is full.
   */
  public void put(T val) throws Exception;

  /**
   * Remove the most recently added element that is still in the
   * stack.
   *
   * @return
   *   val, a value.
   * @pre
   *   !this.isEmpty()
   * @post
   *   The structure contains one fewer copy of val.
   * @post
   *   Every element that remains in the stack was added less recently
   *   than val.
   * @exception Exception
   *   If the structure is empty.
   */
  public T get() throws Exception;

  /**
   * Determine what element will next be removed by get.
   *
   * @return
   *   val, a value.
   * @pre
   *   !this.isEmpty()
   * @post
   *   Every other value in the stack was added less recently than val.
   * @exception Exception
   *   If the structure is empty.
   */
  public T peek() throws Exception;

  /**
   * Determine if the structure is empty.
   */
  public boolean isEmpty();

  /**
   * Determine if the structure is full.
   */
  public boolean isFull();

  /**
   * Push a value on the stack.  (An alias for put.)
   */
  public void push(T val) throws Exception;

  /**
   * Pop a value from the stack.  (An alias for get.)
   */
  public T pop() throws Exception;

  /**
   * Get the value at the top of the stack.  (An alias for peek.)
   */
  public T top() throws Exception;
} // interface Stack<T>
```

One aspect of this code you may find of interest is that we have
said that `Stack extends LinearStructure`.  As we just suggested,
this extension is similar to the extension you've seen for classes,
although it is used primarily for polymorphism.  It means that you
can use a `Stack` whereever code expects a `LinearStructure`

Applications of stacks
----------------------

There are a variety of ways in which computer scientists use stacks.
At times, they use them simply because they need some linear structure,
and stacks are convenient to use.  More frequently, they identify
problems for which the last-in, first-out policy is particularly
appropriate.

One such class of problems involves matching symbols, such as tags
in an HTML document or parens in a Scheme program.  In essence,
whenever you see an opening symbol, such as `<b>` in an HTML document
or `(` in a Scheme program, you push it on the stack.  When you see
a closing symbol, such as `</b>` in an HTML document or `)` in a
Scheme program, you pop the value on the stack and compare it to
the closing symbol.  If they match, you continue on.  If they fail
to match, you report an error.  Clearly, if you encounter an end
symbol with an empty stack, there's something seriously wrong with
the document or program.  Similarly, if the stack contains values
at the end, there are also significant problems, since not all
opening symbols are matched.

Stacks are also useful for certain kinds of operations.  For example,
some mathematicians like to use *reverse polish notation* (RPN),
in which the operation follows the operands.  In such a system, we
would write "add 2 and the product of 3 and 7" as `2 3 7 * +`.  RPN
is useful because you don't have to worry about precedence rules.
RPN is also very easy to implement using stacks.

* Whenever you encounter a value, you push it.
* Whenever you encounter an operation, pop the parameters and push the result.
* The value on the top of the stack when you are done is the final result.

Finally, stacks are useful for simulating procedure calls in most
modern programming languages.  As you've likely seen, a "call stack"
keeps track of any local variables and some related information.

Implementing stacks with arrays
-------------------------------

It is fairly easy to implement stacks, at least once we have another
data structure, such as an array.  In an array-based implementation of
stacks, we typically store the values as they come in, starting at index 0.
When we pop a value, we use the index of the last value added and decrease
that value.  (Typically, we use a field called `top` to keep
track of where to add values.  The Java compiler can tell from context
when we mean the `top` field and when we mean the `top` method.)

Given that strategy, here's the basic code for `put`.

```java
this.contents[this.top] = newvalue;
this.top++
```

Most programmers would express this more concisely as follows.

```java
this.contents[this.top++] = newvalue;
```

It is equally easy to get and remove the value at the top of the
stack: We just reverse those two steps.

```java
this.top--;
return this.contents[this.top];
```

Again, we can combine these two lines into a single instruction. 

```java
return this.contents[--this.top];
```

Note that when we combine the operations, we use the prefix version
of the decrement operation so that `this.top` is decremented
before we use it as an index.

To avoid accidental access to removed data, many programmers make
it a habit to clear out the entry in an array whenever we remove a
value.  If we choose that approach, we will need to temporarily
store the value to be returned before clearing the cell and then
returning that value.

```java
T returnme = this.contents[--this.top];
this.contents[this.top] = null;
return returnme;
```

Determining whether or not the stack is empty is also easy: The stack
is empty only when `top` is 0.

```java
return (top == 0);
```

The only hard part is what to do when the stack "fills".  The
simplest thing to do is to make a bigger array and copy the values
over.  The particular code for doing so is left as an exercise for
the reader.

A linked implementation of stacks
---------------------------------

We can also implement stacks using linked nodes.  What is a node?  
It's a simple data type that contains two parts: a value and a link
to the next node.

```java
/**
 * Simple nodes for linked structures.
 */
public class Node<T>
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The value stored in the ndoe.
   */
  T value;

  /**
   * The next node.
   */
  Node<T> next;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new node that contains val and that links to
   * next.
   */
  public Node(T value, Node<T> next)
  {
    this.value = value;
    this.next = next;
  } // Node(T, Node<T>)
} // class Node<T>
```

For a stack, we just need a pointer to the node at the front of the
stack, which we'll call `front`.

To put something at the front of the stack, we can write

```java
this.front = new Node(val, this.front);
```

To get something from the front of the stack, we can write

```java
T result = this.front.val;
this.front = this.front.next;
return result;
```

Ideally, the stack never fills.  If it does, we're in pretty big
trouble, because we can't even create a small object, which means
that we'll also have trouble creating an exception object.

```
public boolean isFull() {
  return false;
} // isFull()
```

We can check if the stack is empty by comparing `front` to `null`.

```java
public boolean isEmpty() {
  return this.front == null;
} // isEmpty()
```

Terminology
-----------

Unfortunately, some bright computer scientists designed the stack
ADT before some other bright computer scientists designed the more
general linear structure ADT.  Hence, the terms that many folks use
for the basic stack operations are not `put` and `get`, but rather
`push` and `pop`.

To make our code more general, we will stick with the linear structure
terms.

Citation
--------

This reading is closely based on a similar reading I wrote for
_The TAO of Java_, which last appeared in [the course web for
CSC 207 2014F](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2014F/readings/stacks.html).


That reading, in turn, is based on a similar reading I created as
a part of *Espresso*.


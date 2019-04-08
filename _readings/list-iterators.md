---
title: Iterating lists
summary: |
  We consider issues in iterating lists and in manipulating lists
  with list iterators.
prereqs: |
  [List ADTs](../readings/list-adts)
---
In reflecting on different ways to think about lists, we've settled
on a relatively basic view of lists:

> Lists are iterable, mutable, collections of values in which the
  client controls the ordering of the values.

In Java, `ListIterator` objects allow clients to iterate the list,
We think of a list iterator as being between elements in a list,
moving forward with `next` and backwards with `previous`.  

We might visualize the state of a `ListIterator` with a diagram like
the following.

```text
    +---+     +---+     +---+
    | L | --- | S | --- | T |
    +---+  ^  +---+     +---+
           |
```

The `L`, `S`, and `T` are the elements of the list and the upward arrow
is the "position" of the iterator.

Note that this diagram is intended to be somewhat implementation-neutral.
In practice we can't point "between" elements, so we will
have a variable to keep track of some aspect of the iteration, most
typically, the next element.  If we store the list in an array, we
might represent that list in a an array as follows

```text
   0   1   2
   +---+---+---+
   | L | S | T |
   +---+---+---+
   next: 1
```

In contrast, if we store the values in a singly-linked structure, we
might represent that as follows

```text
   +---+---+   +---+---+   +---+---+
   | L | *---> | S | *---> | T | / |
   +---+---+   +---+---+   +---+---+
                 ^
                 |
                 next
```

Adding elements with list iterators
-----------------------------------

`add` adds an element immediately before the iterator, between the values
that would be returned by `previous` and `next`. The iterator ends up
between the newly added value (which is now what `previous` will
now return) and the next values.

For example, support our list has the following state, with the cursor between
n `L` and `S`, 

```text
    +---+     +---+     +---+
    | L | --- | S | --- | T |
    +---+  ^  +---+     +---+
           |
```

If we insert the value `E`, our list will now be arranged conceptually
as follows.

```text
    +---+     +---+     +---+     +---+
    | L | --- | E | --- | S | --- | T |
    +---+     +---+  ^  +---+     +---+
                     |
```

If we then insert the value `A`, our list will be arranged conceptually
as follows.

```text
    +---+     +---+     +---+     +---+     +---+
    | L | --- | E | --- | A | --- | S | --- | T |
    +---+     +---+     +---+  ^  +---+     +---+
                               |
```

What about when the iterator is at the front or the end of the list?
For the front, our model would be something like the following.

```text
    +---+     +---+     +---+ 
    | P | --- | I | --- | N | 
 ^  +---+     +---+     +---+
 |
```

When we insert `S` with the iterator there, the new element gets put before
the first element.

```text
    +---+      +---+     +---+     +---+ 
    | S | ---  | P | --- | I | --- | N | 
    +---+  ^   +---+     +---+     +---+
           |
```

What about at the end?  Let's start by visualizing where the iterator is.

```text
    +---+     +---+     +---+ 
    | P | --- | I | --- | N | 
    +---+     +---+     +---+  ^
                               |
```

If we insert `S` before the iterator, it shows up at the end of the
list.

```text
    +---+     +---+     +---+     +---+
    | P | --- | I | --- | N | --- | S |
    +---+     +---+     +---+     +---+ ^
                                        |
```

Removing elements with list iterators
-------------------------------------

As you may recall, traditional iterators include an optional
`remove` method which removes the element last returned by
`next`.  (Calling `remove` twice in a row without an intervening
call to `next` is illegal, as is calling `remove` without first
calling `next`.)

Removal in list iterators is a bit more complicated.  Since the
list iterator can move both forwards (with `next`) and backwards
(with `prev`), the policy becomes that the value removed is the value
last returned, either by `prev` or `next`.  Why is that 
complicated?  Consider the following "state" of the system.

```text
    +---+     +---+     +---+
    | E | --- | A | --- | T |
    +---+  ^  +---+     +---+
           |
```

If we call `remove`, will it remove `E` or `A`?  It depends on how
the iterator reached its current position.  If it had been at the front
of the list and the client just called `next`, we should remove the
`E`.  If it had been between the `A` and the `T`, and the client
had just called `prev`, we should remove the `A`.  Hence, in diagramming
the state of an iterator, we should also add a note as to what value
it just returned.

In the first situation (moving forward), we might draw the following.

```text
    +---+     +---+     +---+
    | E | --- | A | --- | T |
    +---+  ^  +---+     +---+
      *    |
```

In that case `remove` will remove the `E`, leaving us with the following.


```text
    +---+     +---+ 
    | A | --- | T |
 ^  +---+     +---+
 |          
```

Note that we've left out the asterisk to suggest that there is no
value we can remove.

In the second case (moving backwards), the original state would
have been

```text
    +---+     +---+     +---+
    | E | --- | A | --- | T |
    +---+  ^  +---+     +---+
           |    *
```

When we remove the value, the state of the iterator and the list
will be as follows.

```text
    +---+     +---+
    | E | --- | T |
    +---+  ^  +---+
           |  
```


Setting elements with list iterators
------------------------------------

As in the case of removing elements, setting elements requires that we 
keep track of whether the last element was returned by `next` or
`previous`.  In this case, instead of removing the element, we'll just
replace it.  Diagrams should not be necessary.

Acknowledgements
----------------

This reading was newly written in spring 2019.

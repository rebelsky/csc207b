---
title: Doubly-linked lists and circularly-linked lists
summary: |
  We consider an implementation of linked lists in which each node
  has a link to its predecessor.  We also consider benefits of
  adding circular links, from the last node to the first node, to a list
  structure.
prereqs: |
  [List ADTs](../readings/list-adts)
  [Iterating lists](../readings/list-iterators)
---

So far, we've learned two primary mechanisms for implementing
structures: We can put the structure into an array or we can
put together a linked structure.

Consider the following list.

```text
    +---+     +---+     +---+     +---+
    | L | --- | I | --- | S | --- | T |
    +---+     +---+  ^  +---+     +---+
                     |    *
```

If we implement the list as an array, removing and adding elements is 
expensive, as we have to shift the rest of the array left or right.  If
we implement the list as a singly linked structure, we will have
difficulty backing up for calls to `prev`.  (We could keep a pointer
to the previous node, but if there are two calls to `prev` in a row,
we would need to have another pointer to the previous-previous node,
and so on and so forth.)

What's the solution?  Instead of having one link per node, we'll have
two links per node, one forward and one backward.  In Java, we might
write that as follows.

```java
public class Node2<T> {
  T value;
  Node2<T> prev;
  Node2<T> next;
} // Node2<T>
```

On paper, we often draw these nodes horizontally, as follows.  

```text
   prev val next
   +---+---+---+
 <---* | A | *--->
   +---+---+---+
```

However, while we can easily shift the arrows around on paper, it's
more difficult in ASCII.  Hence, for the ASCII diagrams, we'll draw
them vertically, with the next link at the top, the value in the
middle, and the prev link at the bottom.  Here's the list from
above.  We have two links, one to the `next` value we willl return,
and one to the value we most recently returned.  In this case, they
are the same.

```text
    +---+    +---+    +---+    +---+
next| *----->| *----->| *----->| / |
    +---+    +---+    +---+    +---+
 val| L |    | I |    | S |    | T |
    +---+    +---+    +---+    +---+
prev| / |<-----* |<-----* |<-----* |
    +---+    +---+    +---+    +---+
                       ^ ^
                       | |
                    next recent
```

If we call `next`, we'll get the value `S` back and the state of
the system will be as follows.

```text
    +---+    +---+    +---+    +---+
    | *----->| *----->| *----->| / |
    +---+    +---+    +---+    +---+
    | L |    | I |    | S |    | T |
    +---+    +---+    +---+    +---+
    | / |<-----* |<-----* |<-----* |
    +---+    +---+    +---+    +---+
                        ^        ^
                        |        |
                   recent        next
```

With this model, removing a value in the middle of the list is fairly 
straightforward.  We set `recent.next.prev` to `recent.prev`, and
we set `recent.prev.next` to `recent.next`.

```text
                   +--------+
                   |        |
    +---+    +---+ |  +---+ |  +---+
    | *----->| *---+  | / | +->| / |
    +---+    +---+    +---+    +---+
    | L |    | I |    | S |    | T |
    +---+    +---+    +---+    +---+
    | / |<-----* |<-+ | / |  +---* |
    +---+    +---+  | +---+  | +---+
                    |        |   ^
                    +--------+   |
                                 next
```

Removing at the front or the back is a bit more complicated, since when
we're removing the front element, there is no `recent.prev`, and when
we're removing at the back, there is no `recent.next`.  

Circular linked lists
---------------------

There are a number of ways to deal with removal at the front and back.
One can write special cases.  But a better strategy is to add a dummy
node, that serves as the next node of the last node and the previous
node of the first node (or vice versa).  Here's the initial state of
the iterator.

```text
 +--------------------------------------------+
 |                                            |
 |  +---+    +---+    +---+    +---+    +---+ |
 +->| *----->| *----->| *----->| *----->| *---+
    +---+    +---+    +---+    +---+    +---+
    |   |    | L |    | I |    | S |    | T |
    +---+    +---+    +---+    +---+    +---+
  +---* |<-----* |<-----* |<-----* |<-----* |<-+
  | +---+    +---+    +---+    +---+    +---+  |
  |            ^                               |
  |            |                               |
  |            next                            |
  +--------------------------------------------+
```

Suppose we call `next` and get the value `L`.  The state of
the system will be as follows.

```text
 +--------------------------------------------+
 |                                            |
 |  +---+    +---+    +---+    +---+    +---+ |
 +->| *----->| *----->| *----->| *----->| *---+
    +---+    +---+    +---+    +---+    +---+
    |   |    | L |    | I |    | S |    | T |
    +---+    +---+    +---+    +---+    +---+
  +---* |<-----* |<-----* |<-----* |<-----* |<-+
  | +---+    +---+    +---+    +---+    +---+  |
  |            ^        ^                      |
  |            |        |                      |
  |       recent        next                   |
  +--------------------------------------------+
```

If we call `remove`, we can safely set `recent.prev.next` to `recent.next`,
and we can set `recent.next.prev` to `recent.prev.  That is, `recent.prev`
is the special dummy node, so when we set `recent.prev.next` to `recent.next`,
we've now marked the next node as the start of the list, and we've made
the new start of the list refer back to the dummy node.


```text
 +--------------------------------------------+
 |        +--------+                          |
 |  +---+ |  +---+ |  +---+    +---+    +---+ |
 +->| *---+  | / | +->| *----->| *----->| *---+
    +---+    +---+    +---+    +---+    +---+
    |   |    | L |    | I |    | S |    | T |
    +---+    +---+    +---+    +---+    +---+
  +---* |<-+ | / |  +---* |<-----* |<-----* |<-+
  | +---+  + +---+  | +---+    +---+    +---+  |
  |        +--------+    ^                     |
  |                      |                     |
  |                      next                  |
  +--------------------------------------------+
```

Removing at the end of the list is similar.

```text
 +--------------------------------------------+
 |                                            |
 |  +---+    +---+    +---+    +---+    +---+ |
 +->| *----->| *----->| *----->| *----->| *---+
    +---+    +---+    +---+    +---+    +---+
    |   |    | L |    | I |    | S |    | T |
    +---+    +---+    +---+    +---+    +---+
  +---* |<-----* |<-----* |<-----* |<-----* |<-+
  | +---+    +---+    +---+    +---+    +---+  |
  |                                      ^     |
  |                                      |     |
  |                                 recent     |
  +--------------------------------------------+
```

`recent.next` is the dummy node.  So when we set `recent.prev.next` to
`recent.next`, it now points to the dummy node.  Similarly, when we
set `recent.next.prev` to `recent.prev`, it points to the new last
element of the list.

```text
 +-----------------------------------+         
 |                                   |         
 |  +---+    +---+    +---+    +---+ |  +---+  
 +->| *----->| *----->| *----->| *---+  | / |
    +---+    +---+    +---+    +---+    +---+
    |   |    | L |    | I |    | S |    | T |
    +---+    +---+    +---+    +---+    +---+
  +---* |<-----* |<-----* |<-----* |<-+ | / |
  | +---+    +---+    +---+    +---+  | +---+
  |                                   |       
  |                                   |
  |                                   |
  +-----------------------------------+
```

Doubly linked lists also eliminate some special cases when adding
elements to the list.

Acknowledgements
----------------

This reading was newly written in spring 2019.

---
title: Tree Traversal
repo: <https://github.com/Grinnell-CSC207/tree-traverssal>
summary: |
  In this laboratory, you will explore the traversal of trees.
  Although tree traversal can be used for all sorts of trees, you
  will ground your exploration in binary search trees.
---
Preparation
-----------

a. Fork and clone the specified respository.

b. Skim the code to make sure that you understand the structure of
the tree data structure.

Exercises
---------

### Exercise 1: Building a tree

Consider the following set of instructions.  

FORTHCOMING

a. What tree do you expect these instructions to produce?

b. Write a program to check your answer experimentally.

### Exercise 2: Alternate output

Suppose instead of printing the tree in the deeply nested form, we
want to print the elements of the tree on a single (very long) line,
but in more or less the same order that we get from `dump`.

```
FORTHCOMING
```

Clearly, we could just modify the wonderfully recursive `dump`
procedure to get this output.  But `dump` is recursive.  What if
we can't use recursion?

Sketch, but do not implement, an algorithm to print all the values 
in the tree without using explicit recursion.

### Exercise 3: Printing elements

Consider the following potential solution to the previous exercise.

```
/**
 * Print all of the elements in some order or other.
 * 
 * Note: We are trying to avoid recursion.
 */
public void print(PrintWriter pen) {
  // A collection of the remaining things to print
  Stack<Object> remaining = new Stack<Object>();
  remaining.push(this.root);
  // Invariants: 
  //   remaining only contains Strings or Nodes
  //   All key/value pairs in the tree are either
  //     (a) already printed
  //     (b) in remaining
  //     (c) in or below a node in remaining
  while (!remaining.isEmpty()) {
    Object next = remaining.pop();
    if (next instanceof String) {
      pen.print(next);
      pen.print(" ");
    } else {
      // next must be a Node
      @SuppressWarnings("unchecked")
      TreeNode node = (TreeNode) next;
      if (node.larger != null) {
        remaining.push(node.larger);
      } // if (node.larger != null)
      if (node.smaller != null) {
        remaining.push(node.smaller);
      } // if (node.smaller != null)
      remaining.push(node.value);
    } // if it's a node
  } // while
  pen.println();
} // print(PrintWriter)
```

a. How does this code achieve the goal of achieving recursive
traversal without actually recursing?

b. In what order do you expect it to print the values in the tree?

c. Add this code to your program and verify that it works.  If it
does not, fix it.

### Exercise 4: Other Orderings

The traversal strategy current implemented in `print` is what is
typically called *preorder, depth-first, left-to-right traversal*.
It is "preorder" because we print/visit a node before we visit its
children.  It is "depth-first" because we go deep down into the
tree before we go across a particular level.  And it is "left-to-right"
for obvious reasons.

Suppose we want to do *inorder* traversal, in which we print the
value of a node between the children.  (That is, we print the left
subtree, then the node, then the right subtree.)

a. In what order would you expect to see the values printed?  (You
only need list the first six or so.)

b. What changes do we have to make to the code to achieve that goal?

c. Check your answer experimentally.

d. What changes do we need to make in order to achieve *postorder*
traversal, in which we print the value of a node after the children?

e. What changes do we need to make in order to achieve right-to-left
traversal?

### Exercise 5: Breadth-first traversal

So far, we've only explored depth-first traversal.  But what if we
want to do *breadth-first* traveral, wherein we visit/print all of
the values at a particular level before going on to the next level?

a. Sketch what changes you would make to `print` to get it to print
the values in a top-down, postorder, left-to-right, breadth-first
traversal.  (Hint: You probably don't want to use a stack any more.)

b. Implement those changes.

For those with extra time
-------------------------

If you find that you have extra time, try any of the following
problems.

### Extra 1: Numbering levels

Extend your breadth-first traversal algorithm so that each time it
reaches a new level, it prints a new line and the level number 0 for the
root, 1 for the children of the root, 2 for the grandchildren, etc.).
(It's okay if you print one extra level number at the end, even if there
are no values at that level.)

```text
0: monkey
1: gibbon snake
2: dingo koala python viper
...
```

### Extra 2: Bottom-up traversal

Your breadth-first traversal algorithm should start at the top and work
its way down.  Rewrite the algorithm so that it prints the tree from
the bottom up.


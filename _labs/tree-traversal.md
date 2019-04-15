---
title: Tree Traversal
repo: <https://github.com/Grinnell-CSC207/lab-tree-traversal-2019>
summary: |
  In this laboratory, you will explore the traversal of trees.
  Although tree traversal can be used for all sorts of trees, you
  will ground your exploration in binary search trees.
current: true
---
Preparation
-----------

a. Fork and clone the specified respository.

b. Import the repository into Eclipse.

c. Skim the code to make sure that you understand the structure of
the tree data structure.

Exercises
---------

### Exercise 1: Building a tree

Consider the following set of instructions.  

```java
    String[] strings = {"aardvark", "billygoat", "chinchilla", "dingo", "emu",
        "frog", "gnu", "hippo", "iguana", "jackalope", "koala", "llama"};
    BinaryTree<String> tree = new BinaryTree<String>(strings);
```

a. What tree do you expect these instructions to produce?

b. Run `BinaryTreeExperiment` to check your answer.

c. What do you expect to happen to the tree if we use an array with two
more animals?

d. Check your answer experimentally.

### Exercise 2: Alternate output

Suppose instead of printing the tree in the deeply nested form, we
want to print the elements of the tree on a single (very long) line,
but in more or less the same order that we get from `dump`.

```text
gnu dingo billygoat aardvark chinchilla frog emu jackalope iguana hippo llama koala
```

a. Write a procedure, `elements01(PrintWriter pen)`, that creates that output.

b. In what order does that method traverse the tree?

c. Suppose we traversed the tree using a depth-first, left-to-right, 
in-order traversal.  What output would you expect?

d. Write a new procedure, `elements02(PrintWriter pen)`, that prints
the tree using a depth-first, left-to-right, in-order traversal.

### Exercise 3: Avoiding recursion

You have likely written recursive procedures to generate that output.
What if we can't use recursion?  For example, if we're implementing
an iterator, it will be almost impossible to stop the recursion at
each stage.

Sketch, but do not implement, an algorithm to print all the values 
in the tree without using explicit recursion.  You can use either
depth-first, left-to-right, preorder traversal or depth-first,
left-to-right, in-order traversal.

### Exercise 4: Printing elements

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
  //   All valuess in the tree are either
  //     (a) already printed
  //     (b) in remaining
  //     (c) in or below a node in remaining
  while (!remaining.isEmpty()) {
    Object next = remaining.pop();
    if (next instanceof BinaryTreeNode<?>) {
      @SuppressWarnings("unchecked")
      BinaryTreeNode<T> node = (BinaryTreeNode<T>) next;
      if (node.right!= null) {
        remaining.push(node.right);
      } // if (node.right!= null)
      if (node.left != null) {
        remaining.push(node.left);
      } // if (node.left != null)
      remaining.push(node.value);
    } else {
      pen.print(next);
      pen.print(" ");
    } // if/else
  } // while
  pen.println();
} // print(PrintWriter)
```

a. How does this code achieve the goal of achieving recursive
traversal without actually recursing?

b. In what order do you expect it to print the values in the tree?

c. Add this code to your program and verify that it works.  If it
does not, fix it.

### Exercise 5: Other orderings

The `print` method prints the tree using a depth-first, left-to-right,
pre-order strategy.

a. What changes would you have to make in order to have the `print`
method traverse the tree using a depth-first, left-to-right, in-order
strategy?

b. Check your answer experimentally.

c. What changes would you have to make in order to have the `print`
method traverse the tree using a depth-first, left-to-right, postorder
strategy?

d. Check your answer experimentally.

e. What changes would you have to make in order to have the `print
method traverse the tree using a depth-first, right-to-left, inorder
strategy?

f. Check your answer experimentally.

### Exercise 6: Iterating trees

Using the ideas from the `print` method, implement the `iterator`
method.  (You should not implement `remove`.)

You may iterate the tree in any order you consider reasonable.

### Exercise 7: Breadth-first traversal

So far, we've only explored depth-first traversal.  But what if we
want to do *breadth-first* traveral, wherein we visit/print all of
the values at a particular level before going on to the next level?

a. Sketch what changes you would make to `print` to get it to print
the values in a top-down, preorder,  left-to-right, breadth-first
traversal.  (Hint: You probably don't want to use a stack any more.)

b. Implement those changes.

For those with extra time
-------------------------

_If you find that you have extra time, try any of the following
problems._

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


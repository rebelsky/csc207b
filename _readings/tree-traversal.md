---
title: Traversing trees
summary: |
  We consider the basic approaches to visiting or processing all
  of the nodes in a tree.
prereqs: |
  Trees.
---
Introduction
------------

Just as we iterate the elements of a list, so may we want to traverse
all of the elements in a tree.  Sometimes, we traverse a tree just
to print it out.  Sometimes we traverse a tree to look for a
particular value.  Sometimes we traverse a tree to compute a value
based on the tree.

What values might we compute from a tree?  We might simply compute
the size of the tree, or the depth of the tree, or the number of
times a value appears in the tree.  If the tree is not in any particular
order, we might also search a tree for a value that meets particular
criteria.

Options in traversing trees
---------------------------

At first glance, traversing trees seems straightforward:  You simply visit
each node in the tree, processing the node as you go.  But behind the 
obvious "process each node" strategy, there are a wide
variety of options.

Most nodes have more than one child.  Do you process the children
in order from *left to right*, *right to left*, or leave the order
unspecified?  The client of your traversal algorithm will want to
know (and may even want to choose).

When we think recursively, we think about processing all of one
subtree before processing the next subtree.  That means we traverse
down to a leaf in one subtree before we look at even the topmost
node in the other subtree.  However, there are times that it makes
sense to process all of the nodes at one level before going on to
the next level.  An algorithm that goes across each level, one at
a time, is called a *breadth-first* algorithm.  A traversal algorithm
that goes deep into one subtree before processing the next subtree
is called a *depth-first* algorithm.  Once again, a client may want
to know which approach you will use or choose which approach you
will use.

We usually have to *visit* a node before we visit its children -
after all, in many implementations we get the information on the
children from the node.  However, we can choose different orders
in which to *process* nodes.  Two general approaches are *preorder*,
in which we process a node before processing its children and
*postorder*, in which we process a node after processing its children.
Preorder processing is also called *top-down* and postorder processing
is also called *bottom-up*.  For binary trees with a depth-first
approach, it is also possible to support *inorder* processing -
process the left subtree, process the node, then process the right
subtree.

So, when we write algorithms that process trees, we have three 
decisions to make: 

* Do we visit subtrees left-to-right or right-to-left?
* Do we visit nodes breadth-first or depth-first?
* Do we visit notes in preorder, postorder, or (if appropriate), inorder?

That gives about ten different traversals. Why ten and not twelve?
Because inorder breadth-first traversal doesn't make a lot of sense,
given that subtrees are on different levels.

An example
----------

Do these different approaches visit nodes in different orders?
Certainly.  Let's look at a simple binary search tree.

```text
     e
   /   \
  c     h
 / \   / \
a   d f   j
```

Before you read on, make a note to yourself how you'd visit the tree
in each of the orders given above.

Ready?  Fill in the following table.

<table class="table">
  <tr>
    <th>Policy</th>
    <th>Order Elements are Processed</th>
  </tr>
  <tr>
    <td>Depth first, Preorder, Left to right</td>
    <td></td>
  </tr>
  <tr>
    <td>Depth first, Preorder, Right to left</td>
    <td></td>
  </tr>
  <tr>
    <td>Depth first, Postorder, Left to right</td>
    <td></td>
  </tr>
  <tr>
    <td>Depth first, Postorder, Right to left</td>
    <td></td>
  </tr>
  <tr>
    <td>Depth first, Inorder, Left to right</td>
    <td></td>
  </tr>
  <tr>
    <td>Depth first, Inorder, Right to left</td>
    <td></td>
  </tr>
  <tr>
    <td>Breadth first, Preorder, Left to right</td>
    <td></td>
  </tr>
  <tr>
    <td>Breadth first, Preorder, Right to left</td>
    <td></td>
  </tr>
  <tr>
    <td>Breadth first, Postorder, Left to right</td>
    <td></td>
  </tr>
  <tr>
    <td>Breadth first, Postorder, Right to left</td>
    <td></td>
  </tr>
</table>      

Are you done? Here's what we get.

<table class="table">
  <tr>
    <th>Policy</th>
    <th>Order Elements are Processed</th>
  </tr>
  <tr>
    <td>Depth first, Preorder, Left to right</td>
    <td>e c a d h f j</td>
  </tr>
  <tr>
    <td>Depth first, Preorder, Right to left</td>
    <td>e h j f c d a</td>
  </tr>
  <tr>
    <td>Depth first, Postorder, Left to right</td>
    <td>a d c f j h e</td>
  </tr>
  <tr>
    <td>Depth first, Postorder, Right to left</td>
    <td>j f h d a c e</td>
  </tr>
  <tr>
    <td>Depth first, Inorder, Left to right</td>
    <td>a c d e f h j</td>
  </tr>
  <tr>
    <td>Depth first, Inorder, Right to left</td>
    <td>j h f e d c a</td>
  </tr>
  <tr>
    <td>Breadth first, Preorder, Left to right</td>
    <td>e c h a d f j</td>
  </tr>
  <tr>
    <td>Breadth first, Preorder, Right to left</td>
    <td>e h c j f d a</td>
  </tr>
  <tr>
    <td>Breadth first, Postorder, Left to right</td>
    <td>a d f j c h e</td>
  </tr>
  <tr>
    <td>Breadth first, Postorder, Right to left</td>
    <td>j f d a h c e</td>
  </tr>
</table>      

Wasn't that fun?  Surprisingly, there are use cases for each of the
traversal orders.

Evaluating trees, revisited: arithmetic expressions
---------------------------------------------------

We indicated that there are some times that it's useful to use
trees to compute a value.  Here's one of the most common: Computer
scientists often use trees to represent arithmetic expressions.
For example, consider the following might represent the expression
`(3+4)*(-(5+6))`.

```text
      *
     / \
    /   \
   +    -
  / \   |
 /   \  |
3     4 +
       / \
      /   \
     5     6
```

To evaluate this tree, we need to evaluate both subtrees and then
combine them using the operation.  So we evaluate the `3+4`
subtree and the `-(5+6)` and multiply them together.  Arithmetic
evaluation normally requires depth-first, postorder evaluation.


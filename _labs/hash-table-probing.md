---
title: Probing in hash tables
repo: <https://github.com/Grinnell-CSC207/lab-hashtables-2019>.
summary: |
  We explore the consequences of probing in hash tables.
---
Preparation
-----------

Fork and clone the repository.

Exercises
---------

### Exercise 0: Code Reading

Scan through the code so that you understand what methods are
available and what approaches are used.  Make notes on areas
that are likely to be problematic.

a. What methods are not yet implemented?

b. What parts of the code are likely to have problems?  Why?

### Exercise 1: Duplicate keys

As you may have noted, the code for `set` does not check to see if
the key is already being used.  The introductory notes therefore
observe that this is a potential bug that should be fixed.

a. Why is the failure to check whether the key is already used a 
potential bug?  What effect might that failure to check have on the
behavior of the program?

b. Fill in the body of `repeatedSetExpt` with some experiments that
help you see this effect.

c. If you'd like, you can start with the following simple set of 
experiments.

```java
    htab.reportBasicCalls(true);
    htab.set("alpha", "alpha");
    htab.dump(pen);
    htab.set("beta", "beta");
    htab.dump(pen);
    htab.set("bravo", "bravo");
    htab.dump(pen);
    htab.set("beta", "bravo");
    htab.dump(pen);
    htab.reportBasicCalls(false);
    getExpt(pen, htab, "beta");
    pen.println();
```

d. Correct the bug.

### Exercise 2: Verifying that keys match

As you may have noted, the code assumes that `find` returns a cell that
has a pair with a matching key.

a. Is that the case?  Why or why not?

b. In `HashTableExpt`, uncomment the call to `matchingKeysExpt` to
see what happens when two keys hash to the same location.

c. Squash that second bug by updating `get` to check whether the
key in the given cell matches the expected key.

### Exercise 3: Handling collisions

As you may have noted, the code makes no attempt to deal with
collisions.  Hey, it's even in the "bugs to squash" section.  (Some
of you may have been tempted to fix the bug in a previous
exercise.)

a. Uncomment the call to `setExpt` so that you can see other potential
effects of the unimplemented collision detection.

b. Update `find` to use linear probing.  If the current cell is
full, and the keys don't match, try the cell that is `PROBE_OFFSET`
away from the current cells (modulo the capacity of the table).
For example, if the table capacity is 20, the hash code gives us
position 3, and the offset is 6, you should look in positions 3, 9
(3 + 6), 15, 1 (21 mod 20), 7, ....

### Exercise 4: Improving collision handling

Here's a subtle bug that many programmers miss: For some combinations
of table capacity and offset, we may cycle back before we've looked
at every cell in the table.  For example, if the table capacity is
20, the hash code gives us position 3, and the offset is 5, we would
look in positions 3, 8, 13, 18, 3 (23 mod 20), 8, 13, 18, ....  So,
even if the table is only 20% full, we might miss the empty cells.

How do we fix this problem?  There are at least three approaches.

* We can choose a different offset after we cycle back to the
     beginning.  In this case, an offset of 1 is reasonable, because it
     helps ensure that we check every cell in the table.
* We can expand the table and hope that the new capacity works better.
* When we build/expand the table, we can ensure that the capacity of 
  the table and the offset are relatively prime.
* We could choose a different technique for checking.  One such 
  technique is *quadratic probing*.  First we check one element away.
  Then four elements away.  Then nine elements away.  And so on and
  so forth.  (This mechanism also spreads things out a bit, but may
  also cycle strangely.)

Which do you prefer?  Be prepared to explain your decision.

We'll come back and implement one of these choices later.

### Exercise 5: Expanding Hash Tables

As the previous exercise reminded us, at some point we have to figure out
how to expand the table.  Most frequently, we expand the table when it
reaches a certain percentage full. (That percentage is by 
`LOAD_FACTOR` in the current implementation.)

Here's a sketch of a technique some students use to expand the table.
(It's a sketch, in part, because I haven't checked that the code 
compiles correctly.)

```java
   newCapacity = this.pairs.length * 2 + random(20);
   // Create a new table of that capacity
   this.pairs = new Object[newCapacity];
   // Move all the values from the old table to their appropriate 
   // location in the new table.
   for (int i = 0; i < old.length; i++) {
     this.pairs[i] = old[i];
   } // for
```

a. What do you think about this approach?  (Don't critique the failure
to use `Arrays.copyElts`, or whatever it's called.)

b. Try adding these lines to `expand` and resolve any syntax errors.
Shrink the initial capacity of the array a bit so that we know
`expand` gets called.  Run the `setExpt` method to see whether this
technique for expansion works successfully.

c. Correct any errors that you've identified.

d. You may have noted that one approach we came up with for cycle
problem associated with linear hashing problem was to make sure that the
table capacity is not a multiple of the probe offset.  Update your code
to ensure this result.  (Since we've made the probe offset a prime,
all you have to do is to make sure that the table capacity is not a
multiple of the probe offset.)

In case you've forgotten: The cycle problem is that we might cycle
back to the same index without visiting every valid index.  For example,
if the probe offset is 3, the table has size 9, and we start at position
2, we'll look at positions 2, 5, 8, and then 2 again.  So, we'll never
see most of the positions int he table.

### Exercise 6: Removing elements

We're making good progress in our implementation of hash tables.  What's
next?  We should add support for removing elements.  Unfortunately,
as we've learned, removing elements is often the most difficult aspect
of implementing data structures.  So let's think a bit about how we
might approach the problem.

a. Write some experiments that allow us to see what happens when
we remove elements.

b. Here's one approach to removing elements: Find the index of the
key/value pair.  Put a `null` there to mark it as unused.
Decrement the size field.  

What do you think about this approach?

c. Implement the approach and see what happens.

d. Hopefully, you've found that this is a dangerous approach, since
it breaks important assumptions for linear probing.  Come up with an
alternate approach and discuss it with your teacher or class mentor.

### Exercise 7: Checking for keys

Let's take a short break from thinking about how to remove elements
and consider another issue.  The `containsKey`
method is implemented a bit strangely.

a. Read the code for `containsKey`.

b. Do you expect this approach to work?  Why or why not?

c. Conduct a few experiments to check your conclusion.

d. Rewrite `containsKey` to use a more sensible approach.

### Exercise 8: Removing elements

Here's a potentially better mechanism for removing elements: Find
the index of the key/value pair.  Set the key to null.   Decrement
the size field.

This approach is likely to significantly affect the `find` method,
which now has to skip over the cell when searching but should
probably return the cell if the search fails.  Why?  Because we use
`find` for two reasons: to look things up for `get` and `containsKey`,
and to find an empty location for use with `set`.  For the observers,
it's okay that we look until we reach the end.  However, for `set`,
we really want to use the first available spot.

Implement this approach, updating `find`, `set`, `get`, and anything
else you deem appropriate.

For those with extra time
-------------------------

If you find that you have extra time, implement the iterator.

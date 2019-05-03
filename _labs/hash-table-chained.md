---
title: Chaining in hash tables
repo: <https://github.com/Grinnell-CSC207/lab-hashtables-2019>.
summary: |
  We consider the implications of chaining in hash tables.
---
Preparation
-----------

Fork and clone the repository.  Import it into eclipse.

Exercises
---------

### Exercise 0: Code Reading

Scan through the code so that you understand what methods are
available and what approaches are used.  Make notes on areas
that are likely to be problematic.

a. What methods are not yet implemented?

b. What parts of the code are likely to have problems?  Why?

### Exercise 1: Verifying that keys match

As you may have noted, the code assumes that `find` returns a cell that
has a pair with a matching key.

a. Is that the case?  Why or why not?

b. In `ChainedHashTableExperiments`, uncomment the call to
`matchingKeysExpt` to see what happens when two keys hash to the
same location.

c. Squash that bug by updating `get` to check whether the key in
the given cell matches the expected key.

### Exercise 2: Duplicate keys

As you may have noted, the code for `set` does not check to see if
the key is already being used.  (In fact, it essentially assumes
that there is no more than one thing in a bucket.)  The introductory
notes therefore observe that this is a potential bug that should
be fixed.

a. Why is the failure to check whether the key is already used a 
potential bug?  What effect might that failure to check have on the
behavior of the program?

b. Fill in the body of `repeatedSetExpt` in `HashTableExperiments`
with some experiments that might help you see this effect.

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
    htab.set("beta", "bingo");
    htab.dump(pen);
    htab.reportBasicCalls(false);
    checkGet(pen, htab, "beta");
    pen.println();
```

d. Correct the bug.

### Exercise 3: Handling collisions, phase 1

As you may have noted, the code very little no attempt to deal with
collisions.  Hey, it's even in the "bugs to squash" section.  (Some
of you may have already decided to fix the bug in a previous
exercise.)

a. Uncomment the call to `matchingSetExpt` in `ChainedHashTableExperiments`
so that you can see one potential effect of the unimplemented
collision detection.

b. If you have not done so already, update the code in `set` and
`get` to handle this issue.

### Exercise 4: Handling collisions, phase 2

One collision does not a solution make.  We are much better off looking
at a fairly large collection of values.

a. Read through `multipleSetExperiment` and describe what issues it is
trying to explore.

b. Uncomment the call to `multipleSetExperiment` in `ChainedHashTableExperiments`

c. Verify that the experiment succeeds.  (You no see any messages, other
than the table, if the experiment succeeds.)

d. If the experiment does not succeed, fix your `set` and `get` methods.

### Exercise 5: Expanding Hash Tables

As the previous exercise reminded us, at some point we have to figure out
how to expand the table.  Most frequently, we expand the table when it
reaches a certain percentage full. (That percentage is by 
`LOAD_FACTOR` in the current implementation.)

Here's a sketch of a technique some students use to expand the table.

```java
  void expand() {
    // Figure out the size of the new table
    int newSize = 2 * this.buckets.length + rand.nextInt(10);
    if (REPORT_BASIC_CALLS && (reporter != null)) {
      reporter.report("Expanding to " + newSize + " elements.");
    } // if reporter != null
    // Remember the old table
    Object[] oldBuckets = this.buckets;
    // Create a new table of that size.
    this.buckets = new Object[newSize];
    // Move all buckets from the old table to their appropriate
    // location in the new table.
    for (int i = 0; i < oldBuckets.length; i++) {
      this.buckets[i] = oldBuckets[i];
    } // for
  } // expand()
```

a. What do you think about this approach?  (Don't critique the failure
to use `Arrays.copyElts`, or whatever it's called.)

b. Try adding these lines to `expand`.  Shrink the initial capacity
of the array a bit so that we know `expand` gets called.  Run the
`multipleSetExpt` method to see whether this technique for expansion
works successfully.

c. Correct any errors that you've identified.

### Exercise 6: Removing elements

We're making good progress in our implementation of hash tables.  What's
next?  We should add support for removing elements.  Unfortunately,
as we've learned, removing elements is often the most difficult aspect
of implementing data structures.  So let's think a bit about how we
might approach the problem.

a. Write some experiments that allow us to see what happens when
we remove elements.

b. Sketch a strategy for removing elements.

c. Implement that strategy.

### Exercise 7: Checking for keys

The `containsKey` method is implemented a bit strangely.

a. Read the code for `containsKey`.

b. Do you expect this approach to work?  Why or why not?

c. Conduct a few experiments to check your conclusion.

d. Rewrite `containsKey` to use a more sensible approach.

### Exercise 8: Removing elements, revisited

Consider the following experiment for the `remove` method.

```java
  public static void multipleRemoveExpt(PrintWriter pen,
      HashTable<String, String> htab) {
    // Populate the table
    htab.clear();
    multipleSetExpt(pen, htab);

    // Remove words one by one.
    for (int i = 0; i < words.length; i++) {
      htab.remove(words[i]);
      // Make sure that it's removed
      boolean removed = false;
      try {
        htab.get(words[i]);
      } catch (Exception e) {
        removed = true;
      } // try/catch
      if (!removed) {
        pen.println("Failed to remove " + words[i]);
        htab.dump(pen);
        return;
      } // if

      // Make sure that the remaining elements are still there.
      for (int j = i + 1; j < words.length; j++) {
        try {
          String str = htab.get(words[j]);
          if (!str.equals(words[j])) {
            pen.println("After removing " + words[i] + ", " + words[j]
                + " now maps to " + str);
            htab.dump(pen);
            return;
          } // if
        } catch (Exception e) {
          pen.println("After removing " + words[i] + ", " + words[j]
              + " is no longer present");
          htab.dump(pen);
          return;
        } // try catch
      } // for j
    } // for i
  } // multipleRemoveExpt(PrintWriter, HashTable)
```

a. Explain what this experiment appears to be doing.

b. Run the experiment.

c. If the experiment identifies any issues with your `remove` method,
correct them.

### Exercise 9: Testing

Run the tests and correct any errors they identify (other than those
having to do with the lack of an iterator).

For those with extra time
-------------------------

If you find that you have extra time, enjoy the day.

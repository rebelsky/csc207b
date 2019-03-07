---
title: Anonymous functions
summary: |
  We explore the use of anonymous functions in Java.
---
## Preparation

a. Review the documentation for the `Predicate` interface, available
at <{{ site.java_api }}/java/util/function/Predicate.html>.

b. Review the documentation for the `Function` interface, available
at <{{ site.java_api }}/java/util/function/Function.html>.

c. Review the documentation for the `Comparator` interface, available
at <{{ site.java_api }}/java/util/Comparator.html>.

d. Open [the _Java Tutorials_ page on lambda expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html).

e. Create a new class, `ListUtils`, that you will use for some of the
exercises.

f. Create a new class, `LambdaExperiments`, that you will use for
some of the exercises.  Your class should include a `main` method
that begins as follows.

```java
    String[] tmp = 
        new String[] { "alpha", "bravo", "charlie", "delta", "echo",
                       "foxtrot", "golf", "hotel", "india",
                       "juliett", "kilo", "lima", "mike", 
                       "november", "oscar", "papa", "quebec",
                       "romeo", "sierra", "tango", "uniform",
                       "victor", "whiskey", "xray", "yankee", "zulu" };
    ArrayList<String> strings = new ArrayList<String>(Arrays.asList(tmp));
```

## Exercises

### Exercise 1: Reviewing the reading

In the section entitled "Ideal Use Case for Lambda Expressions" in the
reading, the authors present nine different approaches to filtering a
list.  Write a program that experimentally verifies that each of the
nine approaches works as described.  

You will likely need to 

* Make a copy of the [`Person`](https://docs.oracle.com/javase/tutorial/java/javaOO/examples/Person.java) class associated with the reading, which is available at <https://docs.oracle.com/javase/tutorial/java/javaOO/examples/Person.java>.
* Create a new class, `JTLambdaExperiment`, in which you will write
  the various experiments.
* Add a `main` method to `JTLambdaExpermiment`.
* In the `main` method of `JTLambdaExperiment`, create a list of `Person` objects.  (We'd recommend an `ArrayList`.)
  You can see the `createRoster` method in `Person` for an example.
* For each static method in the reading, add the method to
  `JTLambdaExperiment` and then add a call to that method to `main` to see 
  that the method behaves as expected.

*Note*: If you are adding interfaces and classes, you should do so outside
the body of `JTExperiment`.

### Exercise 2: Selecting values

a. In `ListUtils`, write a static generic method, `select(List<T>
vals, Predicate<T> pred)`, that creates a new `ArrayList` that contains
only the elements of `vals` for which the predicate holds.

b. In the `main` method of `LambdaExperiments`, select and print
the values in `strings` (from the preparation) whose length is at
least five.  Make sure to define your predicate anonymously.

c. In the `main` method of `LambdaExperiments`, select the values
in `strings` which contain two vowels in a row.  Once again, make
sure to define your predicate anonymously.

### Exercise 3: Removing values

Rather than building a new list by selecting values, as in the
previous problem, we could also write a method that mutates an
existing list by removing values.

a. In `ListUtils, write a static generic method, `remove(List<T>
vals, Predicate<T>)`, that removes from vals any values for which
the predicate holds.  You may not use any existing methods in the
`List` class other than `iterator`.  You can assume that the `remove`
method is implemented in the given iterator.

b. Extend your program to remove the values in `strings` whose length is 
at least five.

c. Extend your program to instead remove the values in strings which 
contain two vowels in a row.

### Exercise 4: Extrema

Another common generic task is to find the largest (or smallest)
value in a list.

a. Write a static generic method, `largest(List<T> vals, Comparator<T>
compare)`, that finds the largest element in the list, using `compare`
to compare values.

b. Extend your program to find the shortest string in `strings`.  (If
there are multiple shortest strings, you can return any of them.)

c. Extend your program to find the longest string in `strings`.  (If
there are multiple longest strings, you can return any of them.)

d. Extend your program to find the string in `strings` whose sum of
ASCII values is the smallest.

e. Extend your program to find the string in `strings` whose sum of
ASCII values is the largest.

### Exercise 5: Mapping

a. Implement a static generic method, `map(Function<T,R> fun, List<T>
list)` that builds a new `ArrayList<R>` by applying `fun` to each element
of `list`.

b. Using `map`, build a new version of `strings` in which every string is converted to uppercase.

c. Using `map`, build a new version of `strings` in which the first letter in each string is converted to uppercase. 

d. Using `map`, build a new list that contains the lengths of all of the
strings in `strings`.

## For those with extra time

_In the unexpected situation that you find that you have extra time, you
might attempt one or more of the following exercises._

### Extra 1: Updates to lambda

The _Java Tutorials_ page for lambdas notes that 

> The Java Tutorials have been written for JDK 8. Examples and practices described in this page don't take advantage of improvements introduced in later releases.

Determine what improvements have been added in later releases.

### Extra 2: Other functional interfaces

Find at least two other functional interfaces and describe use cases
for those interfaces.

### Extra 3: Iterators

Lambda provide a nice alternative to anonymous inner classes, with
a bit less syntax.  Can we use lambda to implement iterators?  Why
or why not?

## Acknowledgements

Some problems on this lab were inspired by [a lab on anonymous procedures
from the Fall 2014 iteration of CSC 207](https://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2014F/labs/anonymous-functions.html).

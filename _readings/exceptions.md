---
title: "When things go wrong: Exceptions in Java"
summary: |
  We consider the ways in which programmers and languages typically
  handle errors in programs.  We then explore *exceptions*, Java's
  primary error-handling mechanism.
javadoc:
  - "[`java.lang.Exception`]({{ site.java_api }}/java/lang/Exception.html)"
  - "[`java.lang.Integer`]({{ site.java_api }}/java/lang/Integer.html)"
  - "[`java.lang.NumberFormatException`]({{ site.java_api }}/java/lang/NumberFormatException.html)"
  - "[`java.io.IOException`]({{ site.java_api }}/java/io/IOException.html)"
  - "[`java.io.BufferedReader`]({{ site.java_api }}/java/io/BufferedReader.html)"
prereqs: |
  Java basics.  Conditionals.
current: true
---

Problems in programs
--------------------

Experience suggests that many things can (and do) go wrong during
the execution of a program or a piece of code.  At times, the problem
is due to the programmer who wrote the original code.  Perhaps the
programmer made a mistake in the logic of the program.  Perhaps the
programmer misunderstood some aspect of the system (such as the
limitations on fixed-size representation of numbers).  Perhaps the
programmer forgot a special case.

Some problems are also far outside of the programmer's control.  For
example, some programs fail because power fails, because someone
accidentally takes a backhoe to a network cable, or because a disk
becomes nonoperative.

More frequently, however, code fails because some aspect of the input is
invalid.  At times, programmers can predict and check for these problems
in advance (e.g., before dividing by the length of a list of values in
a computation of the average value, the programmer should make sure that
the list is nonempty).  At other times, programmers cannot easily check
for problems.  For example, an exponentiation method working with a
fixed-size numeric representation may produce a number that is larger
than can be stored, and it is both expensive (in terms of computation)
and difficult (in terms of determining a reasonable way to check in
advance whether or not the computation will be successful).  Similarly,
it doesn't seem reasonable to check whether an input can be parsed
before parsing it.

Identifying and recovering from problems
----------------------------------------

As the exponentiation example suggests, programmers must think about
potential problems in a number of ways.  First they must *identify
possible problems*.  Then they must *choose how to respond to the
problems*.  Next they must *determine how and when to check for
those potential problems*.  Finally, they must *implement all of
their prior decisions*.

Let us consider as an example a piece of code that downloads and
installs an update to a program.  This code might call a helper
method to read an IP address from a file, open a connection to that
IP address, read a patch, and install that patch.  We might express
that process in pseudo-Java as

```java
ip = file.getIPAddress();
connection = new FTPConnection(ip);
update = connection.download();
software.update(update);
update.noteSuccessfulUpdate();
```

### Failure Analysis

Let us focus on the `getIPAddress` method.  That method may fail
for a variety of reasons.  For example, the file may not exist, the
program may no longer have read permission for that file, the file
may not contain any more input, or the next line of input may not
contain an IP address.  (You can probably identify a variety of
other issues.)

### Recovery

How can one recover from such an error?  The program could use a
failsafe address (one that we'd prefer not to use, but will do so
if nothing else works).  The program could decide not to update and
try again later.  It all depends on the context of the call.

However, it is rarely the case that your code should recover from
an error by printing an error message for the user to read.  Think
about how annoying you find the error messages you get from programs?
Wouldn't it be better if the programmer found a way to recover or
to delay the issue until later?

### Testing for errors

You now know what can go wrong and what you want to do about it.
What comes next?  You need to decide how you identify when something
has gone wrong.  Unfortunately, there is not a clear answer.  In
fact, one of the central debates in the software engineering community
is when and how the code should check for problems.

One camp believes strongly in preconditions and postconditions.
That is, this camp suggests that programmers should (a) carefully
document what requirements must be met for the code to work
successfully and what results the code produces and (b) verify
(either through analysis or through code that checks that the
preconditions are met) that any requirements are met before executing
that section of code.  One advantage of such a strategy is that it
requires you to think very carefully about your code.  A second
advantage is that you find errors as early as possible.

If we chose such a strategy, we might update the sample code to
something like the following:

```java
if (file.isAvailable() && file.containsMoreInput() && file.nextInputIsAnIPAddress()) {
  ip = file.getIPAddress();
} else {
  // Something to deal with the problem.
} // if something is not okay
connection = new FTPConnection(ip);
update = connection.download();
software.repair(update);
software.noteSuccessfulUpdate();
```

Some programmers worry that the code that checks whether or not
preconditions are met places an unnecessary computational burden
on the program.  Consider, for example, a method that sums the
numbers in a list.  If we must check in advance that the list
contains only numbers, we end up traversing the list twice, once
to check and once to sum.  It is certainly more efficient (and, to
many, more natural) to check each entry in the list when we reach
it and add it to the running sum.  Similarly, it is likely that
`file.nextInputIsAnIPAddress()` in the code above must read the
next input, check whether it's an IP address, and then "unread" it
so that `getIPAddress()` can again do the reading and interpretation.
In addition, programmers often find that there are some preconditions
which one cannot easily check in advance.

Hence, as an alternative, some software engineers advocate having
methods observe errors as they do their work and return a special
value to indicate inability to compute.  For example one might have
the `sum` method return false to indicate some problem occurred (if
the language is so casual about typing) or use some unlikely number,
such as the smallest number, for a similar purpose.  In object-oriented
languages, one common technique is to return the special value
`null` when the operation fails.

For the continuing example, we might write

```java
ip = file.getIPAddress();
if (ip == null) {
  // Something to deal with the problem.
} // if (ip == null)
...
```

As this sample code suggests, a disadvantage of this special-return-value
strategy is that programmers must add extra code *after* any method
call to verify that the result of the method is not an error signal.
The extra code also requires extra computation (although not very
much) to check the result, even when the method works correctly.

Even more unfortunately, history suggests that many programmers are
less careful than they should be, no matter which of these two
techniques they use.  That is, if they employ the precondition
technique, they leave out the precondition tests with a note (usually
implicit) that "I'll add those later." Similarly, if they employ
the special-return-value technique, they leave out the tests for
special value with an assumption that they can add them in the
future.

Exceptions
----------

Has the software engineering community developed another, perhaps
better, solution?  Yes.  Does Java use it?  Yes.  Java uses a variant
of the error signaling technique that (a) provides a more uniform
mechanism for indicating errors and (b) makes it difficult for
programmers to delay the decision of how to handle errors.  Java's
error handling mechanism, the *exception*, is inherited from a
number of other languages, particularly CLU.

In the exception model, when a method is unable to compute a result,
instead of simply crashing or returning a special value, the method
sidesteps the normal method return mechanism and instead invokes
what is called an *exception handler*.  We typically say that a
method "*throws* an exception" when it fails.

In the simplest exception handling form, we might write (in
pseudocode)

```java
ip = file.getIPAddress();
    handle failure in getIPAddress by {
      ip = new IPAddress("localhost");
    } // handle failure
connection = new FTPConnection(ip);
update = connection.download();
software.repair(update);
software.noteSuccessfulUpdate();
```

In some ways, this code is like the special-return-value solution.
That is, it seems that you execute the code and then check afterwards
whether or not it succeeded.  However, there are subtle differences,
particularly in how the program decides whether or not to use the
exception handler.  If the execution of `getIPAddress` concludes
with a command to return a value, then the first assignment to `ip`
is executed and computation skips the handler, moving on to the
next command (in this case, the command to create a connection).
If the execution of `getIPAddress` finishes by throwing an exception,
then the first assignment is *not* executed and computation moves
directly to the exception handler.

Note that this solution does not require `getIPAddress` to return
a special value to indicate failure.  Instead, `getIPAddress` uses
different commands indicate successful computation (e.g., `return`)
and another to indicate failure.  Similarly, the code that calls
`getIPAddress` need not check the result, because it is confident
that the handler gets called automatically on failure and not at
all upon success.

One particularly nice aspect of exception handling in most languages
is that you can permit an exception to escape from a block of code,
and not just a single method.  For example, if we decide to handle
the failure of `getIPAddress` by simply skipping the download, we
can write (again, in pseudocode)

```
// block
    ip = file.getIPAddress();
    connection = new FTPConnection(ip);
    update = connection.download();
    software.repair(update);
    software.noteSuccessfulUpdate();
// end block
    handle failure in block by ...
```

In this case, when `getIPAddress` fails, we don't even
attempt the subsequent lines (creating the connection, downloading
the update, etc.).

Exceptions in Java
------------------

In working with exceptions in Java, you must pay attention to three
different, but related, issues: First, when you write a method that
may fail, you must indicate that it may fail and how it may fail.
Second, when you reach a point in the method in which the method
fails, you must throw the appropriate exception.  Finally, when you
call methods that may fail, you must add handlers (or indicate that
the caller may also fail).

### Indicating potential For failure

To indicate that a method may fail, add `throws Exception` after
the parameter list and before the body of a method.  For example,

```java
public IPAddress getIPAddress() throws Exception {
    ...
} // getIPAddress()
```

### Throwing exceptions

Within the body of the procedure, you will throw an exception when
you encounter an error.  Instead of writing `return XXX`, you write

```java
throw new Exception("description of problem");
```

### Handling exceptions

Whenever your method calls a method that may throw an exception,
you must explicitly state what you want to do when the method throws
an exception.  You have two basic choices: You may deal explicitly
with the exception or you may pass the exception on to whatever method
called your method.  If you do neither, the Java compiler will refuse
to compile your program.

To deal explicitly with an exception, you write a try/catch block, which
has the following form

```java
  try {
    // stuff that may have problems
  } catch (Exception e) {
    // Handle one type of exception
  } // try/catch
```

For example,

```java
try {
  ip = file.getIPAddress();
} catch (Exception e) {
  ip = new IPAddress("localhost");
} // try/catch
```

There are also times in which you will decide that a failure
in a called method leads naturally to a failure in the calling
method.  You can explicitly pass the exception on as follows

```java
  try {
    // stuff that may have problems
  } catch (Exception e) {
    throw new Exception("explanation");
  } // try/catch
```

The designers of Java decided that such code was overly verbose, and
so permit a simpler mechanism for passing along exceptions.  In particular,
if you explicitly state that your method throws exceptions and you don't
put the exceptional method in a try/catch clause, then Java assumes
that when the called method throws an exception, your method should,
too.  For example,

```java
public void myProc() throws Exception {
  // stuff that may have problems
} // myProc()
```

You may note that we've used this strategy in the past when writing
`main` procedures and our first static procedures.  Because I didn't
want you to worry about exceptions until now, the `throws Exception`
prevented Java from complaining that you were calling exceptional
methods without handling their potential exceptions.

Handling more detailed exceptions
---------------------------------

As you may have noted, one disadvantage of the basic exception
handling mechanism is that it does not distinguish between things
that go wrong.  For example, consider the problem of reading an
integer.  We might express such a method as follow:

```java
public static int promptForInt(PrintWriter pw, BufferedReader br, String prompt) throws Exception {
  if (prompt != null) {
    pw.print(prompt);
    pw.flush();
  } // if (prompt != null)
  return Integer.parseInt(br.readLine());
} // promptForInt(PrintWriter, BufferedReader, String)
```

Why might this code fail?  It might fail because the user entered
something other than an integer (e.g., `1.42` or even something
that the user thinks is reasonable, like `two`).  It might fail
because the call to `readLine` failed (e.g., if someone had
accidentally closed the reader).  It might even fail because the
writer is closed (although I believe Java's current implementation
of `PrintWriter` indicates no such error).

The reactions to the different modes of failure may be different.
For example, if the `readLine` failed, we probably have to give up,
because it's unlikely a subsequent call will succeed.  However, if
the user entered something that is not an integer, we can ask the
user to try again.

Java deals with this multiplicity of types of exceptions by permitting
more specific exceptions and exception handlers.  In particular,
you can write

```java
try {
  // Code that may fail 
} catch (FirstKindOfException e1) {
  // recover from the first kind of exception
} catch (SecondKindOfException e2) {
  // recover from the second kind of exception
} catch (Exception e) {
  // recover from any remaining kinds of exceptions
} // try/catch
```

It is then the custom for a method to indicate what particular kinds
of exceptions it may throw.  For `promptForInt`, we might write:

```java
public static int promptForInt(PrintWriter pw, BufferedReader br, String prompt) throws NumberFormatException, IOException {
  if (prompt != null) {
    pw.print(prompt);
    pw.flush();
  } // if prompt is not null
  try {
    return Integer.parseInt(br.readLine());
  } catch (NumberFormatException nfe) {
    if (prompt != null) {
      br.println("Sorry, but that was not an integer.  Try again.");
      return promptForInt(pw, br, prompt);
    } else {
      throw nfe;
    } // if prompt is null
  } catch (IOException ioe) {
    // No way to recover.
    throw ioe;
  } // try/catch
} // readInt(PrintWriter, BufferedReader, String)
```

How did I know that `parseInt` could throw a `NumberFormatException`
and that `readLine` could throw an `IOException`?  I read the
documentation.

Why haven't I worried about any other kinds of exceptions?  Because
no other method I've called has indicated that it can throw an
exception.

Creating your own exceptions
----------------------------

You can also define your own kinds of exceptions.  To do so, you
create a class file, `YourException.java` that looks like the
following:

```java
public class YourException extends Exception {
  public YourException() {
    super();
  } // YourException()

  public YourException(String reason) {
    super(reason);
  } // YourException(String)
} // class YourException
```

We will explore the ideas behind the form of this declaration 
in a future reading.  For now, just follow the form.
Once you have declared your own exception, you may throw it
with 

```java
throw new YourException();
```

or

```java
throw new YourException("some extra notes");
```

Java's optionally-catchable `RuntimeException` objects
------------------------------------------------------

While Java encourages programmers to use standard exceptions (and
subclasses thereof) and therefore to require handlers for those
exceptions, there are also situations in which requiring handlers
seems excessive.  For example, every array index is potentially out
of bounds, and we may not want to require that programmers catch
all of those exceptions.

For situations in which handlers should be optional, Java provides
a class, `RuntimeException`, much like `Exception`.  You can throw
objects in class `RuntimeException`.  You can catch objects in class
`RuntimeException`.  However, you need not declare that you throw
such objects (no `throws` clause is necessary) and you need not
catch such objects (no `try/catch` block is necessary).  However,
if one of these exceptions is issued and not caught, your program
is likely to crash.


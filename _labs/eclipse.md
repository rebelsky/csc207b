---
title: Getting started with Eclipse
summary: |
  In this laboratory, you will start using Eclipse. In particular,
  you will create your first project in Eclipse.
---

Preparation
-----------

a. Make sure you have completed and understood [the reading on IDEs and Eclipse](../readings/eclipse.html).

b. Start Eclipse by typing the following in a terminal window.

```shell
$ /opt/eclipse2018-12/eclipse/eclipse
```

You may recall that the ampersand means that the program will
run in the background and you can continue using the terminal
window.

You will note that we gave the full path to Eclipse.  There are multiple
versions of Eclipse installed in MathLAN.  For this course, we will be
using Eclipse 2018-12, the latest version.

I recommend that you create a launcher for Eclipse.  Right click
on your task bar, click on **Add New Items**, click on **Launcher**,
and follow the instructions.  In MathLAN, the icon appears to be
in `/opt/eclipse/icon.xpm`, rather than the standard location, so
you'll have to navigate there to find it.

Exercises
---------

### Exercise 1: Your first program

a. Make a new Java project in Eclipse and call it `First`.
From the **File** menu, select **New**, and then **Java Project**.
A **New Java Project** window should appear.  Enter the project
name "First".  Do not click any buttons yet.

b. We'll be using Java 11 in this course.  You may therefore need
to configure Eclipse to use Java 11.  You will notice that the
window has a section labeled "JRE" for Java Runtime Environment.
If the default JRE is not something like `jdk 11.0.`, you will need
to configure the JRE.

* Click on **Configure JREs**.  You should see a list of Installed JREs.
* Click on **Add...*.   A dialog requesting the JRE type should appear.
* Click on **Standard VM** and then **Next>**.  A window that asks for the
JRE home should appear.
* Under **JRE home** enter `/usr/lib/jvm/jdk-11.0.1`
* Under **JRE name** enter `JDK-11.0.1`
* Click **Finish**.  You should end up back to your list of installed JREs.
* Select the checkbox next to `JDK-11.0.1`.  The text "(default)" should
  appear next to it.
* Once you've selected the appropriate JRE, click **Apply and Close**.

c. Click the **Finish** button.  You will likely see a window that says
something like "New module-info.java".  Modules are a relatively recent
addition to Java and we will not be using them right now.  Click on
the **Don't Create** button.

d. You should now see your project in the "Package Explorer" window at
the left-hand-side of the screen.

e. Create a package in the class that will hold your class files. 
Name your package `introduction`.

If you are not sure how to create a new package, use **File** >
**New** > **Package**.  (You may also find it useful to right click
on the project and use **New** -> **Package**.)

f. Create a class in the package called `HelloJava`.  

If you are not sure how to create a new class, use 
**File** > **New** > **Class**.  
(You may also find it useful to right click on the
package and choose the obvious menu items.)

g. Copy and paste this code into your class.  Make sure that you use
a package name that corresponds to the one you just created.

```java
package introduction;

import java.io.PrintWriter;

/**
 * A simple introductory Java class.
 */
public class HelloJava {
  /**
   * Print a silly message.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println("I am the 1337 h4x0r. Phear me!");
    pen.flush();
  } // main(String[])
} // class HelloJava
```

h. Run the program and marvel at the words that appear in the console!

### Exercise 2: Switching packages

a. Create a new package named `spare` (still in the same project).

b. Drag `HelloJava` from one package to another in the Package Explorer.

c. Observe what happens.

### Exercise 3: Experiments with output

You've already seen that Java looks a bit like C, except that it's also
a bit more verbose.  For example, instead of `printf` with
a pattern, you can use `pen.println`.  

a. Determine what happens if you remove the call to `pen.flush()`.

b. Determine what happens if you use `println` with an
integer or real number rather than a string.

c. Determine what happens if you use `pen.print` rather
than `pen.println`.  (This may be easier to figure out if
you use multiple calls.)

d. Determine what happens when you try to print `System.out` and
`pen`.  That is, determine the result of

```java
    pen.println(System.out);
    pen.println(pen);
```

### Exercise 4: Your second program

a. Make another class in your new package and call it `SimpleMath`

b. Using the skeleton from above write a program that computes and
prints out a simple sum.  For example, your output might be

```text
Adding 3 and 4 gives us 7.
```

Here's a sketch.

```java
    int x = 3;
    int y = 4;
    pen.print("Adding ");
    pen.print(x);
    ...
    pen.print(x+y);
    pen.println();
```

Presumably, you wrote this program using a sequence of calls to
`pen.print` and `pen.println`.  Can we pack it into a single instruction?  

c. Java "overloads" the `+` operation.
If you combine two strings with `+`, you will get a string.
For example, determine the result of the following instruction.

<pre>
    pen.println("Hello" + "World");
</pre>

d. We saw earlier that `pen.println` accepts things other
than strings, such as integers and reals (and even
`System.out`).  Can `+` also take different
types of parameters?  Determine what happens if you combine a string
and an integer with `+`.  What about combining an integer
and a string?

e. Rewrite your instructions to print a sum using just one call to
`pen.println`.

### Exercise 5: Configuring Eclipse

In this course, we will be using [Google Java Style](https://google.github.io/styleguide/javaguide.html).  There are also other conventions.  Eclipse can
help you follow code conventions if you configure it properly.

* Download [the Google Java Style description (xml)](https://raw.githubusercontent.com/google/styleguide/gh-pages/eclipse-java-google-style.xml).
* Select **Window** > **Preferences**.  (This is the appropriate way to
  load preferences in MathLAN's version of Eclipse.  You may need to do
  something different to bring up preferences elsewhere.)
* Select **Java** > **Code Style** > **Formatter**.
* Click the **Import...** button.
* Select the file you just downloaded.
* Follow the natural steps.

Next, we'll explore the effects.

* Add some spaces and newlines to violate the style guidelines.
* Hit <kbd>Shift</kbd>-<kbd>Ctrl</kbd>-<kbd>F</kbd>

### Exercise 6: Hovering

a. Hover the cursor over one of the instances of `System`
and note what happens.

b. Hover the cursor over one of the instances of `out` and
note what happens.

c. Hover the cursor over one of the instances of `print` or
`println` and observe what happens.

d. Hover the cursor over `main` or `HelloJava` and
observe what happens.

### Exercise 7: Support your partner

If you are working with a partner, your partner should configure
Eclipse too.  (Make sure that you're using Java 11; install the
Google Java formatting guidelines.)

For those with extra time
-------------------------

### Extra 1: Code Preferences

In one of the exercises, you configured Eclipse to format code in
a certain way.  Eclipse is highly configurable, so you might explore
other preferences.  Go back to the Formatter preferences and click
"New...".  In the dialog box that appears, select the course
conventions as the initialization profile and then name your
preferences.  Click "OK" and then explore the various preferences
that you can set.

### Extra 2: Refactoring

You'll note that Eclipse has a "Refactor".  Figure out what at least
one item in that menu does.


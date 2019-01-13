---
title: The Eclipse IDE
summary: |
  We consider a bit about integrated development environments (IDEs) and then
  delve into some details of the Eclipse IDE.
---
A brief introduction to integrated development dnvironments
-----------------------------------------------------------

At this point in your programming career, you've probably learned
that there are a variety of tasks that programmer need to consider.
Among other things, programmers must manage a variety of files,
provide build instructions that combine the files, correct syntax
errors in program code that the compiler identifies, find logic
errors in their programs (sometimes by manual analysis, sometimes
with a debugger), and remember a host of libraries.

Many programmers are quite happy doing all of these things with a
variety of programs and with an editor like vi or emacs.

However, as the power of computers expands, programmers increasingly 
want to harness that power to make their tasks easier, and perhaps
to integrate them into a single application.  For example, why not
have the editor tell you when you make a syntax error (and perhaps
even suggest a solution) when you make the error, rather than much
later, when you try to compile it?  Why not have the editor suggest
what procedure you want, rather than forcing you to scan through lots
of documentation?  And why not have the editor keep your files organized
so that it can automatically figure out what and how to compile?
Environments that combine these various development tasks are called
"Integrated Development Environments" or more frequently
"IDEs".

What features can you expect an IDE to provide?

* Most IDEs provide a *source code organization module*.
  Mostly, this modules allows you view of the various files in your project
  (and, when necessary, to rearrange them).  It may sound like a file
  browser, but a good source code organization module does more than 
  a file browser - for example, if aspects of your program depend on
  its location, when you move it in the code hierarchy, the IDE will
  rewrite the portions that depend upon its location.
* Every IDE provides a *smart source code editor*.
  How is it "smart"?  Usually in a variety of ways.  First, it is likely
  to know the syntax of the language and can not only highlight your code 
  clarity, but also automatically indent (and unindent) your code.
* Most IDEs have editors that do *live source code checking*.  
  That is, in many cases, the IDE editor is
  likely to notice when you have an obvious error (usually syntactic,
  occasionally something deeper) and to flag that error quickly.
  Some will even suggest corrections, although you should
  probably take those suggests with a grain of salt.
* Most IDEs have editors that do *advanced autocomplete*.
  That is, if you type the start of a procedure or value name (and,
  often, hit a special key), the editor will provide you with possible
  completions.  And, if you're working with a library, the editor will
  give you quick access to a list of members of that library (values,
  variables, functions, etc.).
* Most IDEs have *integrated compilers*.  
  Rather than leaving your editor to compile and run your code, you
  just hit a few keystrokes.
* Most IDEs have *integrated debuggers*.  
  Rather than leaving your editor to debug your code, you can do
  so directly within the debugger.
* Many IDEs integrate with *source code management systems*
  like git or Subversion.  Such integration makes it easier to
  grab code from the repository and to upload revised code to the
  repository.  (We've had mixed success with such integrated
  SCMs.)
* Some IDEs provide *features for source code reorganization*.  For 
  example, some make it easy to refactor repetitious code or to
  rename a variable throughout a section of code.

To some of you, all of these features may sound like the return of
Clippy<superscript>&reg;</superscript>.  To some long-time programmers,
these features lead to novice programmers to who don't know the
syntax of their language and easily forget to things they should
memorize,  However, to many, these features combine to provide a
much better way to program.  You should explore various alternatives:
command line, IDE, hybrid, other.  Over the long term, you may have
the opportunity to choose what environment works best for your or
you may have one imposed upon you by your work environment.

Some Eclipse basics 
-------------------

In the Java world, the two most popular IDEs seem to be Eclipse,
from IBM, and NetBeans, from Oracle.  There's also a novice-friendly
IDE called BlueJ.  In this course, we will rely on the Eclipse IDE.

Eclipse is an open source IDE originally created by IBM in November
2001. There are many version of Eclipse that each cater to certain
needs. In this course we’ll be using the somewhat generic version of
Eclipse installed in MathLAN.  

Here's a quick glance at an Eclipse session in an older version of Eclipse.

![An image of the Eclipse IDE.  Most of the details are included in the
following paragraphs](../images/eclipse-java-ui.png){: width="100%"}

Along the left side of the screen, you'll see the **package explorer**.
That may need a bit of background to understand.  While your work
to date has generally used one or two files, most of your Java
projects will involve many more files, and so you will find it
helpful to group them together.  Java's mechanism for grouping
related files is a *package*.  For example, you might want a package
for ADTs you build or for a reuable part of a project.  Java also
comes with a wide variety of packages.  Traditionally, Java packages
are named with a reverse-url syntax, such as
`edu.grinnell.cs.rebelsky.lists` for my list package or
`edu.grinnell.glimmer.ushahidi`, for my research lab's ushahidi
package.

But Eclipse goes a step further.  A typical program involves multiple
packages, and potentially some other things, too.  Eclipse groups
packages and other materials that go together into a "Project".
Each time you start a new assignment in this class, you will likely
create a new project in Eclipse or load a project we've provided.

As you should be able to tell from the picture, the package manager
gives you access to all of your projects.  You can easily shrink and
expand labels to delve deeply into parts of a project, and you can
drag files from place to place.

In the center of the screen you'll see the **primary code editor**.
You'll note that the code has been colored and highlighted to help
the programmer identify the different parts.  You'll also note that
there are multiple tabs, one for each open file.

Along the right hand side of the screen is an **outline of the current
file**.  You can use this to navigate to various parts of the file and
to help remind yourself of its organization.

At the bottom of the screen are a series of tabs that serve a wide
variety of purposes.  Right now, they are open to the **error messages**.
But you'll also **interact** with programs there, **view documentation**, and
more.

And, as always, at the top of the screen are a host of menus that 
you will need to spend a bit of time exploring on your own.

Wrapping up
-----------

### Important Terms

* IDE
* Java Package
* Project

### Review Questions

* What are some features you can expect fo find in an IDE?
* Why might someone choose not to use an IDE?
* Why are we using Eclipse for this course?

### Exploratory Questions

* What is the "JRE System Library" in the explorer?
* What changes if you move a file from one package to another?
  (You may not be able to figure this out until you learn a bit 
  more Java.)
* How can you refactor code in Eclipse?  What does refactoring do?
* How do you get Eclipse to follow the coding conventions your
  employer or professor prefers?

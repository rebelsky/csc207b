---
title: Some tips and tricks for DrRacket
section: Handouts
summary: A few clever tips and tricks that make your life easier.
---
# {{ page.title }}

_This document serves as a compilation of some of the things that you
can do to make yourself a more efficient user of DrRacket._

## Both panes

**ctrl-i** reindents the code.  Reindentation is necessary for work you
turn in.  It also helps you find parenthesization errors.

**ctrl-r** runs the code in the definitions pane.  This is just the
keyboard shortcut for the **Run** button.

When syntax highlighting is working, you can right click (control click
on the Mac) on any identifier and rename all instances of that identifier.
(If this one doesn't make sense, ask someone to demonstrate it.)  This
approach helps avoid errors in renaming.

**Racket -> Comment out with semicolons** does exactly what it says.
(I don't know why it doesn't have a keyboard shortcut.  In the Scheme
editor SamR designed and helped build, ctrl-semicolon did this.)

## Interactions pane

**ctrl-up-arrow** and **ctrl-down-arrow** let you navigate your expression
history.  You can also use **esc-p** and **esc-n**.  The latter two are
particularly useful on the Mac.

**ctrl-enter** evaluates the current expression, even if you are not at
the end of the expression.

**down-arrow** navigates to the end of the expression (at least when
you are on the last line of the expression).

## Definitions pane

**ctrl-t** opens a new tab.

The **(define)** menu (hidden at the top of the definitions pane) lets
you quickly navigate to any definition in that pane.

The **View -> Program Contour** menu item lets you see the big picture
of your program.

The **Insert -> Insert Large Letters...** menu item lets you put some
large "ASCII art" letters in your program.  These are useful primarily
for the program contour, and illustrate what happens when someone with
strong opinions on organizing code gets to work on an editor.



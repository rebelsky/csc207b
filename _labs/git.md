---
title: Getting started with Git on the command line
summary: |
  In this laboratory, you will practice using Git.  You will use
  the command line to manage your git repositories.  (Eclipse has
  is supposed to have some built-in features that make it easier
  to interact directly with GitHub.  However, many programmers find
  it much easier to use Git from the command line, and so we will
  do so.) In case you've forgotten what you learned in the reading,
  there is a list of useful commands at the end of this lab.
---

Preparation
-----------

a. Make sure you’ve read [the Introduction to Git
reading](../readings/git.html).

b. Make sure that you've done [the Introduction to Eclipse
lab](../labs/eclipse.html)

c. Log in and open a terminal window.

d. Create a directory to use for this lab.  I'd suggest something like
`~/CSC207/Git`.

e. Open Eclipse.

Exercises
---------

### Exercise 1: Make an Account

If you haven't already done so, create a GitHub account at [https://github.com/](https://github.com/)

### Exercise 2: Configure Your MathLAN account

As you may recall, your life is easier if you do a bit of configuration
before working with Git.  If you haven't already done so, configure
your account (name, email, editor).

* First, open a new terminal window.
* Next, type these commands
    * `git config --global user.name YOUR_NAME`
    * `git config --global user.email USERNAME@grinnell.edu`
* Finally, set your editor.  You can use `emacs`, `vi` (or `vim`,
  or `vim.basic`), or even `gedit`.
    * `git config --global core.editor EDITOR`

### Exercise 3: Create a new repository

a. Log in to your GitHub account.

b. Somewhere on the page (along the left-hand column, at the time
of this writing, but it changes), there should be a button labeled
"New Repository" (although that name changes, too).  Click that
button.  A dialog should appear.

c. Name your repository (e.g., `git-exercise`).  Click the buttons
to make it public and to initialize the repository with a README
file.  Choose the Java `.gitignore` file.  Choose a license you
like.  Finally, click "Create repository".

### Exercise 4: Cloning your repository

The easiest way to clone a repository is by using the HTTPS
address of your repository.  Get that address from the GitHub
page (either from the address bar or from HTTPS Clone URL
widget).

Open a terminal window and cd to the directory you created in
the preliminaries.  For example,

```shell
$ cd ~/CSC207/Git
```

Once you are in that directory, use `git clone` to copy
the repository you created.

Verify that the repository contains the files that you expected.

### Exercise 5: Importing Into Eclipse

In Eclipse, start the New Java Project Wizard with "File" > "New"
> "Java Project".

Pick a name for your project.

Uncheck "Use Default Location"

Enter the directory for your Git repository.  It should be something
like `/home/*username*/CSC207/Git/git-exercise`.

Click "Finish".

You should now see an Eclipse project that matches your Git repository.
You may want to open one of the files (e.g., the LICENSE or README.md)
and check.

*Warning!*  Eclipse behaves strangely if you put the Git repository
inside the Eclipse directory.  I'd suggest setting up two directories
for this class, one for things created in Eclipse, and one for
things under Git.

### Exercise 6: Identifying changes

a. Return to your terminal window and cd to your Git repository.

```shell
$ cd ~/CSC207/Git/git-exercise
```

b. Issue a `git status` command to see what files Eclipse created.
Git should list them as "Untracked files".  You may also see some
modified files.

c. Use `git add` to add the files and other changes to the repository.

d. Use `git commit` to commit those additions and updates.

e. Use `git push` to send them back to the GitHub repository.

f. Bring up the repository on GitHub and verify that the files have
been added.

### Exercise 7: Add some Java

In [the Eclipse lab](../labs/eclipse.html), you created
a simple "Hello World" Java program.  Create a similar
program in your new repository.  That is,

a. Create a new package, such as `hello`.

b. Create a new class, such as `HelloWorld`.

c. Add a `main` method to the class that has the following form.

```java
  public static void main (String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println ("Hello, GitHub!");
    pen.flush();
  } // main(String[])
```

d. Run your program to make sure that it works as you'd expect.

e. Save your program.

### Exercise 8: Commit and push your code

a. You've updated the repository and so should commit the code to the
repository.  You should also push it back to the main repository.  
So do so now.  (That is, use the sequence of status, add, commit,
and push to get the Java files into the GitHub repository.)
(Note that we usually don't push immediately immediately after each commit,
but it's handy to do so now.  Normally, we do a series of small commits
and only push once we've reached a larger goal.)

b. Go to GitHub and see if the changes you have made are visible.  You may
need to navigate a few layers deep, since Eclipse makes a directory for
each portion of the package.

### Exercise 09: Making Updates on GitHub

c. Although we normally update code in our local copy of the repository,
it is also possible to update code directly on GitHub.  Navigate to
the Java code you just wrote and click the "Edit"
button.

d. Change the output.  Then scroll to the bottom of the page, enter a
commit message, and click "Commit".

### Exercise 10: Pulling updates from GitHub

a. Our primary repository is now updated.  How about our local repository?
Let's see.  Switch back to the terminal window.  Look at the file in
the finder using `less`.  

b. It is unlikely that the Java code changed.  Why?  Because you haven't
told Git to pull the updated version.  Do so now.

c. Once you've issued the `git pull` command, verify that the
file has indeed been updated in your local copy of the repository.

d. Finally, use `git log` to see a list of changes that
have been made.

### Exercise 11: Viewing changes in Eclipse

a. Switch back to Eclipse.  

b. Open the Java code you created and edited.  Has it changed?

c. Your are likely to see a message from Eclipse that your code is now
out of synch, along with instructions for synching.  Follow them.

### Exercise 12: Making changes in Eclipse

a. In Eclipse, change the output of your program slightly (i.e., change
one of the strings).  

b. Save the file.

c. In the terminal, type `git status` to determine whether or
not Git saw the change.  (It should note that your file has been changed.)

d. Type `git diff` to see what the change was.  You should see your
new code prefixed by plus signs, old code by minus signs, and a bit
of context.

e. Commit your change, but don't push it.

### Exercise 13: Conficting Changes

You have a local change that has not been pushed.  Let's see what
happens if someone else also makes a change.

a. On GitHub, update the Java file by adding another print statement.

b. Determine what happens when you try to pull the updated version.

If all goes as expected, you should get a message like the following:

```text
Auto-merging *FILE*
CONFLICT (content): Merge conflict in *FILE*
Automatic merge failed; fix conflicts and then commit the result.
```

c. Open the file in Eclipse.  You are likely to see lines that look
something like this

```text
<<<<<<< HEAD
        *CODE*
=======
        *OTHER CODE*
>>>>>>> 68f7b764bb0e83971245e8db7e58c330c9d9d25c
```

d. These lines show the conflict.  Fix the conflict and save the file.

e. Back in the terminal, add and commit the changed file.  Then try
pulling again.  Git should now be happy.

### Exercise 14: Support your partner

If you are working with a partner, your partner should configure
Git too.  (They should create an account and set appropriate
characteristics in the terminal.)

For those with extra time
-------------------------

### Extra 1: SSH Connections

You did this lab using HTTPS connections to GitHub.  HTTPS connections
are easy and straightforward.  However, they also require you to
type a password each time you push code back to GitHub.

GitHub also permits you to create a pair of private and public RSA
keys and to use SSH connections with those keys.  In that case, you
may have to type the passphrase you associated with your keys, but
you won't have to enter your GitHub account info.

Figure out how to set up an SSH connection with GitHub.

### Extra 2: Shared Repositories

You'll need to do this exercise with a partner.

First, figure out how to give someone else administrative access to
your repository.

Next, verify that they have administrative access by having them
make a change and upload it to the repository.

Finally, figure out what happens if the two of you make changes.

## Some Useful Git Commands

```text
git config --global user.name YOUR_NAME
git config --global user.email YOUR_EMAIL
git config --global core.editor EDITOR

git help
git clone
git add
git commit
git pull
git push
```


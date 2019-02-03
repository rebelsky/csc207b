---
title: Assignment 2
subtitle: Speed reader
copyright:  Copyright &copy; Peter-Michael Osera and Samuel A. Rebelsky.
copyright_message: |
  This work is licensed under a Creative Commons Attribution-NonCommercial
  4.0 International License.  To view a copy of this license, visit
  <http://creativecommons.org/licenses/by-nc/4.0/>.
link: true
javadoc:
  - "[`DrawingPanel`](https://www.buildingjavaprograms.com/code-files/4ed/javadoc/DrawingPanel.html)"
  - "[`java.awt.Graphics`](https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Graphics.html)
  - "[`java.awt.Font`](https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Font.html)
current: true
---

Everyone has wished they could read faster at some point in their life, for example, to get through the required reading for their English class, to cram for an exam, or to simply get through their ever-growing list of novels to read.
Since the 1950s, psychologists, linguists, and educators have devoted significant efforts into [speed reading](http://en.wikipedia.org/wiki/Speed_reading) techniques that can dramatically increase your reading speed with relatively little loss of comprehension.
In this homework, we will prototype one approach to speed reading called [Rapid Serial Visual Presentation (RSVP)](http://en.wikipedia.org/wiki/Rapid_Serial_Visual_Presentation)---recently popularized by [Spritz Inc.](http://www.spritzinc.com/) and apps like [Spreed](http://signup.spreedit.com/)---and run some small user studies to test its effectiveness.

## Background

Many modern speed reading techniques are based on the insights of school teacher Evelyn Wood.
In the 1950s, Wood observed that, among other things, (1) using your finger or some other pointing device to train your eyes and focus while reading and (2) eliminating [subvocalization](http://en.wikipedia.org/wiki/Subvocalization), internally speaking words while reading them, can dramatically increase your reading speed.

Since then, countless speed reading courses have been developed to help students develop these skills.
However, these courses rely on the student's discipline to develop good reading habits, and it is easy for an untrained student to learn "the wrong way" and thus never seen the purported benefits of speed reading.
Computer programs in this context can act a tutor or personal support system, ensuring that students practice the right skills even while learning alone.

RSVP, in essence, takes these ideas of pointing-while-reading and removing sub-vocalization to their limit.
With RSVP, a series of objects—here, words—are presented quickly in succession. By design, the reader is only able to focus on a single word at a time.
And furthermore, the words appear at such a speed that the reader is unable to sub-vocalize like normal.
Such a presentation style is only really practical with a computer program!

Here are some examples of RSVP:

![Spritz @ 250 WPM.  We see a sequence of words, one at a time, at the center of the screen, with a letter near the middle of each word colored red and centered between two short vertical lines segments.  The text reads something like the following: "Welcome to spritzing!  We are starting you out at 250 words per minute, a little over the average reading speed of 220 words per minute.  Don't worry, we'll go faster in a moment.  In fact, people are already spritzing at over 1,000 wpm.  At that rate, you could read a 1000 page novel in about ten hours.  What could you do if you could double your reading speed with the same or better compre- hen- sion?  What if you tripled it?  Our goal is to get the entire world reading with Spritz and have over 15% of the world's textual content read by our method by 2016"](../images/spritz1.gif)

Note that while somewhat disorientating at first, the text becomes readable with a little bit of practice, especially once you consciously try to stop yourself from subvocalizing each word. This text is being presented at 250 words per minute. Here are examples of the technique used to read 350 and 500 words per minute, respectively.

![Spritz @ 350 WPM.  The text begins "Now we are starting to make progress.  Your current reading speed is 350wpm."](../images/spritz2.gif)

![Spritz @ 500 WPM](../images/spritz3.gif)

Note that the average reading speed is approximately [200-260 words per minute](https://en.wikipedia.org/wiki/Words_per_minute#Reading_and_comprehension) for reading adult prose. So with some practice, you can use a speed reader to read at approximately 2x the average reading rate. In contrast the world record for speed reading is a blistering [4251 words per minute!](http://www.mentalworldrecords.com/worldspeedreadingcouncil/).

## Part 1: WordGenerator

We can break up the functionality of the Speed Reader into three components: reading text from a file, rendering that text to the screen, and animating that rendering process.
First, we'll build a class, `WordGenerator`, that reads in text from a text file and logs stats about the text that is read.
The simplest way to read in files in Java is through the [Scanner](https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html) class.
In general, a Scanner breaks up a stream of text into words or *tokens* that can be read from the Scanner.
We can attach a Scanner to a variety of text sources; most commonly, we'll attach the Scanner to [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) objects that represent files on disk.

Here is an example usage of a Scanner where we read in files from a text file and print them to the console, one word per line:

```java
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

// ...

public void printWords(String filename) throws IOException {
  Scanner text = new Scanner(new File(filename));
  while (text.hasNext()) {
    System.out.println(text.next());
  } // while
} // printWords(String)
```

Things to note about using a `Scanner`:

* The classes we must use: `Scanner`, `File`, and `IOException` exist in the standard libraries but appear in different packages.
To use these classes without having to specify the *fully-qualified name* of the class (package + class name), we use `import` declarations to tell Java that, *e.g*, when we reference `Scanner`, we really mean `java.util.Scanner`.
Note that `import` declarations appear at the top of Java source files outside of any class definitions.
* To instantiate a `Scanner`, we pass in a `File` object.
It is tempting to pass in the filename directly, but this is a mistake because the resulting `Scanner` will parse the filename string rather the file it denotes!
* The critical methods of `Scanner` are `hasNext()` to see if the `Scanner` has text left and `next()` to get the next token out of it.
See the API documentation linked above for more information on these methods.
* Note that our function signature has a `throws IOException` clause following the argument list (but before the opening curly brace).
This *throws clause* is necessary because the creation of the `Scanner` object may generate a *checked exception*, namely an `IOException`.
Exceptions can be handled using try-catch blocks or they be explicitly passed on to calling functions using the throws clause.
For now, using the throws clause is easier as we don't have much to do if such an exception occurs; we'll talk about handling exceptions in Java in the coming weeks.

Our `WordGenerator` will act as a wrapper around this functionality of the `Scanner`.
Your `WordGenerator` class needs to have the following constructor and methods:

* `WordGenerator(String filename)`: constructs a new generator that processes text from the given file.
* `boolean hasNext()`: returns `true` iff the underlying `Scanner` of this `WordGenerator` has text left to process.
* `String next()`: returns the next word of the underlying `Scanner`.
  If the `Scanner` does not have words left, then the behavior of `next()` is undefined (*i.e.*, you don't have to check or handle this case).
* `int getWordCount()`: returns the number of words produced by the `WordGenerator` so far.
* `int getSentenceCount()`: returns the number of sentences produced by the `WordGenerator` so far.
  Define a sentence to be the number of occurrences a sentence-ending punctuation mark appears at the end of a word---`'.'`, `'!'`, or `'?'`.

## Part 2: Displaying text

Separate from reading text from the text file is displaying the text to the screen.
Normally, we would use the Java Swing API to do this which is Java's built-in GUI framework.
However, Swing is a complicated (albeit well-engineered) library, so rather than using it directly, we'll use a *wrapper class* that makes drawing stuff to a window easy: the `DrawingPanel` (credit to Stuart Reges and Marty Stepp at the University of Washington who use this class in their book, [Building Java Programs](http://www.buildingjavaprograms.com/)).

Here is the essential usage of `DrawingPanel`:

```java
import java.awt.Graphics;
import java.awt.Font;

public void demonstratePanel() {
  DrawingPanel panel = new DrawingPanel(400, 300);
  Graphics g = panel.getGraphics();
  Font f = new Font("Courier", Font.BOLD, 46);
  g.setFont(f);
  g.drawString("Hello World!", 100, 100);
}
```

* The relevant classes we need to draw to the `DrawingPanel` are found in the `java.awt` package.
  While we might be tempted to import all of the parts of `java.awt`. with `import java.awt.*`, that violates the Google Java Style Guidelines.  Hence, we import each individually. 
* We create a `DrawingPanel` by invoking the `DrawingPanel(width, height)` constructor which creates a panel of the specified dimensions and immediately makes it visible on the screen.
* We grab the *graphics context* object (an instance of the `Graphics`) class with the `getGraphics()` method.
* We set the font by creating a new [`Font`]({{ site.java_api }}/java/awt/Font.html#Font(java.lang.String,%20int,%20int)) object.
  The constructor for a `Font` takes the name of the font, its style, and the size (in points).
  We then set the font for the graphics context with the `setFont` method.
* Finally, we use the [graphics context]({{ site.java_api }}/java/awt/Graphics.html) to render text to the window.
  Here we use the `drawString` method which takes the string to render and where to render it.

The `DrawingPanel` class is available as a separate Java file to include in our project.
Download the file here: [DrawingPanel.java](../files/DrawingPanel.java)

Add this file to your Eclipse project, and you'll be good to go!

## Part 3: Animating Text

The final component to rendering the text is doing so in an animated way.
To do this, we'll simple render the text in a loop but in each iteration of the loop, *delay* execution by causing the program to sleep.
To do this, we'll use the `sleep(milliseconds)` function of the [`Thread`]({{ site.java_api}}/java/lang/Thread.html) class:

```java
public void printStaggered() throws InterruptedException {
  while(true) {
    System.out.println("Hello World!");
    Thread.sleep(1000);
  } // while
} // printStaggered
```

This snippet of code constantly prints `Hello World` to the console but in one second intervals.
The argument to `Thread.sleep` is the amount of time the program should wait in milliseconds.
Note that like the `Scanner` above, `Thread.sleep` throws the checked exception `InterruptedException`.
We use a throws clause here to avoid handling the exception explicitly.

## Part 4: Putting it together

Finally, you should put all these concepts together to create a program `SpeedReader` that exists in a file `SpeedReader.java` which reads in a file and displays it in the RSVP style to a `DrawingPanel`.
Your program should use your `WordGenerator` class to read the file and after displaying the text file, should report the number of words and number of sentences it processed.

Your program should take a number of command-line arguments to customize its behavior.
Here's a description of its usage:

```{:.nohighlight}
Usage: SpeedReader <filename> <width> <height> <font size> <wpm>
```

* `filename` is the text that should be read.
* `width` is the width of the window.
* `height` is the height of the window.
* `font size` is the size of the font used.
* `wpm` is the speed at which the words are displayed in *words per minute*

If the user does not specify this exact amount of arguments, then you should print a usage message and exit.

The text should be rendered in the "Courier" font, and the text can be rendered in a location of your choosing.
Ideally, you would render the text to the middle of the screen, but this is slightly complicated because the size of the font does not necessarily correlate with how large the string is.
Centering the text (and other visual improvements) are extra credit which is discussed below.

## Part 5: Usability testing

Now that your speed reader is complete, you can now run *usability tests* over it.
In particular, we will conduct a small test to answer the question: "does speed reading impact our ability to comprehend what we read?"
In the field of [human-computer interaction](https://en.wikipedia.org/wiki/Human%E2%80%93computer_interaction), researchers use usability tests to understand how real people interact with technology.
In turn, they use this information how to better design software and hardware that people can use.

The setup for the tests proceeds as follows:

1. Develop a corpus of texts to be used by your study participants.
   This should include approximately 8--10 excerpts of texts found on the Internet, e.g., [wikipedia.org](htts://www.wikipedia.org) articles or [gutenberg.org](https://www.gutenberg.org/).
   Each excerpt should contain several paragraphs, ideally enough text so that speed reading at 350 WPM takes a minute or two per excerpt.
2. For four of the excerpts, develop a small comprehension quiz consisting of *five comprehension questions each* that require the reader to comprehend the text they are reading.
   These questions should be about facts regarding the *content* of the excerpts, not the particulars of the text, *e.g.*, do not have questions to the effect of "how many three letter words did the excerpt contain?".
   The remaining texts will be used to help the user *train* on the system.

To run the test, find *at least three participants* that are willing to take your test.
These should be individuals not currently in CSC 207---ideally, they shouldn't be computer science majors!
With each participant, do the following:

1. Tell the participant what the study is about, what you are trying to analyze, and what the procedure for the test is, *i.e.*, these steps below.
   You do not need to hide anything from your participants.
2. Instruct the users on using your speed reader application.
   Show them the application and how it works.
   You may run the application for them to avoid the need to explain how Eclipse works.
3. Give your participants *15 minutes* to train on using the application.
   Using the non-quiz texts in your corpus, let your participant use the speed reader at various speeds to become accustomed to the application.
   At this stage, the participant may go back and re-read texts as much as they would like to gain experience speed reading.
4. When they are ready, choose one of your quiz-texts at random and allow the participant to read the text without the use of the speed reader, *i.e.*, in a plain text editor.
   They may read the text at their own pace.
   Once they are done, administer the quiz—the participant is not allowed to consult the text again at this point.
   Record which text was chosen along with the results of the quiz.
5. Repeat the previous step for the remaining quizes.
   However, rather than letting the participant read the text in a text editor, have them use your speed reader instead.
   Each text should be read at a different speed—250, 350, and 500 WPM.
   Furthermore, the participant should only be allowed to speed read the text once.
   Again, record the text chosen, the WPM the text is read at, and the results of the quiz.
6. After this step, the test is done.
   Make sure to thank your participant!

You should include your excerpts in your repository as additional text files.
In the `README.md` file in your repository, include the following information:

* The sources of each of the excerpts, *i.e.*, the URLs where you found them.
* The questions for the four texts with associated quizzes.
* The names and Grinnell emails of your participants.
* The results of your quizes as described above.

Finally, add a paragraph in your `README.md` file briefly analyzing your results.
Did your participants demonstrate that they were able to comprehend what they were reading with your speed reader?


## Extra credit

There are lots of improvements to be made to your speed reader!
For each improvement that you make to your speed reader, you'll get 1 extra credit point on this homework (up to a maximum of 5 points).
Here are two recommendations.

### Centering the text

To center the text on the screen, you need to appeal to an additional class, [`FontMetrics`](http://docs.oracle.com/javase/8/docs/api/java/awt/FontMetrics.htm), to discover how much space a given string will take up on the screen.
To obtain an appropriate `FontMetrics` object, use the `getFontMetrics()` method of the `Graphics` class.
From there, you'll need to figure out the appropriate methods to use as well as the math to get the text to be centered on the screen.

### Focus letters

Even after centering the text on the screen, you can still improve how words are displayed!
You may have noticed that excessively long words can sometimes disrupt your speed reading when they are naively centered on the screen.
Spritz and other systems use the following heuristic to align text on the screen:

Choose a focus letter based off the length of the overall word and center the word around the focus letter:

* length = 0-1 => first letter
* length = 2-5 => second letter
* length = 6-9 => third letter
* length 10-13 => fourth letter
* length >13 => fifth letter

Also, color the focus letter differently from the other letters, *e.g.*, in red.

### Additional recommendations

Other improvements that you brainstorm are potentially extra credit-worthy!
If you come up with an idea, please talk to me, and I'll let you know if you can get extra credit for it.

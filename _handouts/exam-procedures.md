---
title: Examination procedures
permalink: /exams/procedures
---
# {{ page.title }}

All regular exams for this course are take-home examinations.
You may use any time or times you deem appropriate to complete the exam, provided you return it to me by the due date.
Exams will have six or seven problems.
Each problem is worth the same number of points.
Although each problem is worth the same amount, problems are not necessarily of equal difficulty.

There are two components to each exam: the electronic submission of your code file, and a physical cover sheet. Your electronic submission is due at the time stated on each exam page, while the cover sheet is due at the next class. Your electronic exam submission should have your random number at the beginning, but should not include your name or username. Your cover sheet must include your full name, your random number, as well as two hand-written, signed statements detailed in the academic honesty policy below.

*Read the entire exam before you begin.*

We expect that someone who has mastered the material and works at a moderate rate should have little trouble completing the exam in a reasonable amount of time.
In particular, this exam is likely to take you about five hours, depending on how well you've learned the topics and how fast you work.

If you have worked more than five hours on this exam and you are not finished, you are eligible for the *"There's more to life than CS"* clause.
This will guarantee you a minimum of 70% on this exam, provided you meet the following requirements:

1. You must have completed both the exam prologue and the exam epilogue.
2. You must show evidence of serious effort on at least four exam problems.
3. You must provide a time log for each problem on the exam, and the log should represent total time of at least five hours.

The meaning of "evidence of serious effort" will depend on the problem, but in general this means that you should have written code, provided examples of where that code does and does not work, and you have detailed comments explaining your thinking when you wrote the code.
For problems that do not require code, you should explain your thinking in comments and show example uses of any provided code (for "whatzitdo" problems). If you are unsure whether your work demonstrates serious effort, I am happy to look at your exam and let you know.

Students can earn bonus points for errors on the exam, but these bonuses do not apply if you receive 70% as a result of the "more to life" clause.

## Blind grading and random numbers

While we do not think that bias enters into our grading, to be
absolutely sure that we are not affected by our experience with you
(e.g., "Taylor always attends review sessions; I'm sure they
understand this"), we grade your exams "blindly", without knowing
your identity.  As specified above, you will use a six-digit random
number to identify your exam and to link your exam to your cover
sheet.  

You may generate your random number in DrRacket as follows.

1. Type `(random 1000000)` in the interactions pane.
2. Record the result.
3. If the result has fewer than six digits, add leading zeros.

```
> (random 1000000)
285432 ; Use 285432
> (random 1000000)
85687  ; Use 085687
> (random 1000000)
512    ; Use 000512
```

## Bonus points

Because we expect you to be precise in your answers, it is reasonable
for you to expect the same of us.  Therefore, we will give one bonus
point for each spelling or code error on the exam *identified after
the first 24 hours the exam is available*.  Corrections will be
posted during the exam period, so make sure to check for updates
if you run into an issue with an exam problem.

We will also give you two bonus points for logging the time you
spend on each problem.  You should not include time spent reviewing
readings or preparing to work on the problem in your total, only
time during which you are actively working on a solution.

## Academic honesty
This examination is open book, open notes, open mind, open computer,
open Web.  However, it is closed person.  That means you should not
talk to other people about the exam.  Other than as restricted by
that limitation, you should feel free to use all reasonable resources
available to you.

As always, you are expected to turn in your own work.  If you find ideas
in a book or on the Web, be sure to cite them appropriately.  If you
use code that you wrote for a previous lab or homework, cite that lab
or homework as well as any students who worked with you.  If you use
code that you found on the course Web site, be sure to cite that code.
You need not cite the code provided in the body of the examination.

Although you may use the Web for this exam, you may not post your
answers to this examination on the Web.  And, in case it's not clear,
you may not ask others (in person, via email, via IM, via IRC, by
posting a please help message, or in any other way) to put answers on
the Web.

Because different students may be taking the exam at different
times, you are not permitted to discuss the exam with anyone until
after we have returned it. If you must say something about the
exam, you are allowed to say:

> This is among the hardest exams I have ever taken. If you don't start it early, you will have no chance of finishing.

You may also summarize these policies.
You may not tell other students which problems you've finished.
You may not tell other students how long you've spent on the exam.

You must include both of the following statements on the cover sheet of the examination.

- I have neither received nor given inappropriate assistance on this examination.
- I am not aware of any other students who have given or received inappropriate assistance on this examination.

Please *hand write*, *sign*, and *date* each statement separately.
Note that the statements must be true; if you are unable to sign either statement, please talk to me at your earliest convenience.
You need not reveal the particulars of the dishonesty, simply that it happened.
Note also that "inappropriate assistance" is assistance from (or to) anyone other than the course faculty.

*Exams can be stressful.  Don't let the stress of the exam lead you to make decisions that you will later regret.  Violations of academic honesty and exam policies will be handled through the Committee on Academic Standing, the College Hearing Board, or the Computer Science Department's Academic Honesty Policy, as appropriate.*

## Submitting your work

You must submit your work in two parts: submit your answers electronically and turn in a physical cover sheet.

You must add all your answers, tests, and documentation to the exam starter file.
Rename this file to your secret number, then email it to {{ site.instructor_title }} (not the graders) as an attachment.
For example, if your secret number is "000000", you should send an email with an attachment named "000000.rkt".
*Do not submit your code in a PDF, word document, or in the body of the email*.
Your electronic submission is due at the time and date stated on the exam page.
Please bring your signed cover sheet to the next class after the exam is due.

In many problems, we ask you to write code. Unless we specify
otherwise in a problem, you should write working code and include
examples that show that you've tested the code informally (by looking
at what value you get for various inputs) or formally (by using the
Rackunit testing framework).  In addition to the examples provided in
the exam, you should also provide additional examples.  Do not include
resulting images; we should be able to regenerate those.

Unless we tell you otherwise, you should assume that you need to
provide 6P-style documentation for each primary procedure you write.
If you write any helper procedures, you must document them with 6P-style
documentation using at least the first four P's (Procedure, Purpose,
Parameters, Produces).

Just as you should be careful and precise when you write code and
documentation, so should you be careful and precise when you write
prose. Please check your spelling and grammar. Because we should be
equally careful, the whole class will receive one point of extra credit
for each error in spelling or grammar you identify in the preliminaries
and problems on this exam. We will limit that form of extra credit to
five points.

We will give partial credit for partially correct answers.  We are
best able to give such partial credit if you include a clear set of
work that shows how you derived your answer.  You ensure the best
possible grade for yourself by clearly indicating what part of your
answer is work and what part is your final answer.

## Getting help
I may not be available at the time you take the exam. If you feel
that a question is badly worded or impossible to answer, note the
problem you have observed and attempt to reword the question in
such a way that it is answerable.
You can always email me with specific questions, and I will do my best to respond quickly.

I will also reserve time at the start of classes the week the exam is due to discuss any general questions you have on the exam.

## Checklists

Since many students regularly seem to miss different elements of
the exam, these checklists serve as a way to help you remember
everything that you have to do.

### Big picture

- Have you made a copy of the exam starter code?
- Have you generated a random number?`
- Have you replaced the `000000`{:.nohighlight} in the exam code with your random number?
- Have you completed the exam prologue?
- Does your exam file name end with a `.rkt`{:.nohighlight} extension? *It should!*
- Have you submitted your exam answers by email?
- Have you written and signed your cover sheet?
- Have you completed the exam epilogue?

### Exam contents

- Have you reviewed the question and answer section of the exam?
- Have you removed all identifying materials from your code, other than your assigned id number?
- If you've copied any code, have you also copied the accompanying documentation?
- Have you cited all the code that you've relied upon?
- Have you logged how you spent time on each problem?
- Have you included examples or test suites for each primary procedure you've written?
- Have you documented each procedure you've written, including each helper procedure?  (Your helper procedures only need the first four P's.; local helpers defined with `let` need only a one-line comment.)
- Have you checked your spelling?

### Cover sheet

- Have you written your random number on the cover sheet?
- Have you written your name on the cover sheet?
- Have you hand written, signed, and dated the first academic honesty statement?  (This item assumes you consider it appropriate to sign the first academic honesty statement.)
- Have you hand written, signed, and dated the second academic honesty statement?  (This item assumes you consider it appropriate to sign the second academic honesty statement.)

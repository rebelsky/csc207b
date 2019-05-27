---
title: Eboard 40  Wrapup
number: 40
section: eboards
held: 2019-05-10
link: true
current: true
---
CSC 207.02 2019S, Class 40: Wrapup
==================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Final PSA
    * Questions
* The subject matter(s) of the course
* Course evaluation
* Final notes

Preliminaries
-------------

### News / Etc.

* Sit where you would like.
* I'm working on getting grades to you as soon as I can.  I'll try to
  have all grades in by Tuesday.  (May not include HW8.)
* I've finished grading all of the "on time" exam 2's.  I'll return those
  at the end of class.  (I don't want better EOCEs for high grades, or
  worse EOCEs for low grades.)
* There are still some people who have not turned in exam 2.  Please do
  not discuss it with anyone.  (If you want to discuss it with someone,
  please check with me first to ensure that both of you have turned it
  in.)
* Review sessions for final: TBD.  Tentatively Wednesday.
* Quiz policy: 10% for quizzes is now the best of (a) quiz grade, (b)
  average exam grade, or (c) final grade.

### Upcoming work

* Final exam: 9am or 2pm, Thursday or Friday of finals week.
    * Let me know which of the four times you plan to take the final.

### Extra credit

#### Extra credit (Academic/Artistic)

* CSC 324 presentations, Tuesday (2-4) and Wednesday (9-11) in DLab

#### Extra credit (Peer)

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

* **Tonight** Dance MAP, 4:30 pm, Flanagan

### Other things

This appeared in the _Campus Memo_.  While it is a public presentation,
I am told that it's not geared for students.  However, I will also note
that students who have attended past budget presentations have told me that
they found it enlightening.

The Budget Presentation  
Monday, May 13  
4 p.m., Joe Rosenfield '25 Center, Room 101

Faculty and staff are invited to a presentation that will review the annual budg
et process, the recently approved FY20 budget, and a general budget outlook, pre
pared by Keith Archer, vice president for finance and treasurer, Joe Bagnoli, vi
ce president for enrollment and dean of admission and financial aid, and Jainen 
Thayer, chief investment officer.

### Final PSA of the semester

* Don't let the stress of finals' week negatively affect you.
    * Get enough sleep.
    * If you decide to shut off your brain with substances, do so in moderation.
    * Don't let academic honesty become an issue; our decision-making becomes
      less good at this time of the semester.
* Consent is essential.

### Questions

How long did people spend on exam 2?

> Here's the data for those who turned it in on time.

```text
        P1      P2      P3      P4      Total   (Hours)
Min     30      30      30      30      175     2.9
Max     240     210     360     210     900     15.0
Ave     114     98      119     96      428     7.1
Median  99      98      98      90      368     6
```

Why the variance?

> The person on the low end writes a lot of code for fun.  The one on the
  high end spent three hours on a bug having to do with `==` vs `equals`.

Will there be questions on object-oriented programming and design on the
final?

> I'm not inclined to add a sixth question.  But I am likely to include
  to OOP and design issues in other questions.

The subject matter(s) of the course
-----------------------------------

I thought it would be useful to get an overview of what we've learned
on the board.  In part, that will help you study for the final.

In addition, the end-of-course evaluations have five questions that read
"_blah blah blah_ helped me understand the subject matter of the course".
When we first started using College-wide EOCEs, I asked "How will students
know what the subject matter of the class is?  After all, students call
CSC 151 'The Scheme Course', even though our primary focus is functional
problem solving, and CSC 152 'The Java Course', even though its focus
is algorithms and data structures."  The response was "You can tell them."
But that's not my style.

So we're going to collaboratively develop a list of things you may
have learned in CSC 207 this semester.  I tend to group them into
categories.  

* I'll describe the categories and give an example for each.
* I'll give you some time to come up with suggestions.  (Each group should
  come up with a few for each category.)
* We'll get as many on the board as we can.  Each group should make sure to
  add at least one to each category.
* We'll talk through them for a bit.
* I'll probably take photos and record them in the eboard at a later date.

### ADTs 

* E.g., Lists (things you can iterate)
* PUM - Philosophy, Use Cases, Methods
* Iterators (things that can iterate collections of values)
* Maps
* Queues
* Stacks
* PriorityQueues
* Trees
* Graph
* Heap

### Data structures

* E.g., ArrayBasedLists (lists stored in arrays)
* LIA - Layout Implementation Analysis
* Two basic "layouts" - Linked structures and array-based structures
* SkipLists (implement maps)
* Hash tables (implement naps)
* Block chains (implement ???)
* BSTs (implement maps)
* Linked lists (implement lists)
    * Singly, doubly, circularly
* Tries (implement maps)
* Mutable vs immutable

### Algorithms

* E.g., Quicksort
* Design techniques
    * Greed
    * "Deep recursion" (non-linear recursion)
    * Loop invariants
    * Replace recursion with stacks
    * Use data structures to hold remaining work
* Sorting
    * merge (bottom-up and top-down)
    * selection sort
    * insertion sort
    * bubble sort
    * heap sort
* DNF
* Graph algorithms
    * Prim's
    * Dijkstra's
    * Kruskal's
* Traversal
   * Tree and graph

### Algorithm analysis

* E.g., Big-O notation, formalized
* Runtime and space complexity
* Implicit costs - E.g., in the somewhat puzzling but quite enlightening
  string example.
* Recurrence relations
* Amortized analysis
* Expected-time analysis

### Software development

* E.g., Git basics
* Multiple ways of signaling errors
* Overloading
* Importance of having a common style (and how to make your tools achieve
  it for you)

### Object-oriented design

* E.g., The Iterator pattern
* Inheritance - Code reuse through implicit copying, with overriding
* Parameteric polymorphism - Code reuse through parameterized types
* Subtype polymorphism - Code reuse through general procedures
* Encapsulation
* Classes as patterns for objects
* Initializing objects

### Java

* E.g., Anonymous inner classes
* Exceptions and Try/Catch 
* References and objects

### Broader skills

* E.g., "Thinking on your feet"
* E.g., Working with others
* E.g., Coping with too much stress
* Dealing with uncooperative people
* Dealing with uncooperative software ("Type String not found")
* Navigating Web resources

### Anything else

* E.g., Drawing can help you understand structures and state
    * On white board or on paper
    * For data structures
    * For conceptual structures, like list iterators
    * For memory layout: the stack and the heap
* Sam uses incomprehensible abbreviations, like SW, AIC, DS

Course evaluation
-----------------

Evaluation forms may be found at <https://grinnell.smartevals.com>.

_End-of-course ratings enable you to give responsible feedback for your
professors, and the information you provide enters into future contract
reviews. The agree/disagree responses will be tallied to produce frequency
reports. The instructor will be able to review your unidentified comments
within the electronic course evaluation system. Please note that the
scale starts with “Strongly Disagree” at the top. Be careful not
to inadvertently reverse your responses. **Please provide comments**
but do not write your name in the comment boxes. Instructors receive the
unidentified, completed forms only after grades have been submitted to
the registrar._

Final notes
-----------

Notes on the final (if time)
----------------------------

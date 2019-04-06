---
title: Eboard 24  Pause for breath
number: 24
section: eboards
held: 2019-04-03
link: true
---
CSC 207.01 2019S, Class 24:  Pause for breath
=============================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Quiz
* About the makeup exam
* Code from the Interweb
* Quick notes on average
* Testing our SLPQs
* Linked structures and iterators
* Array-based queues

Preliminaries
-------------

### News / Etc.

* Detour questions:
    * Do you prefer the PM/AV-style diagrams or the SR-style diagrams?
    * Do you prefer the PM/AV-style labs or the SR-style labs?
* I will *not* post the answers we develop in class today.  (I may
  post the tests; feel free to take photographs of eboard or whiteboard.)
* We'll probably do the Array-based queues on Friday
* Oreos!

### Upcoming work

* [Assignment 6](../assignments/assignment06) due Thursday.
    * This is an individual assignment, rather than a group assignment.
    * You may consult with anyone you wish, provided you cite them.
* Readings for Friday: Lots!
    * [List ADTs](../readings/list-adts) (may get updated)
    * "[Lists with 'current' considered harmful](http://csis.pace.edu/~bergin/papers/ListsWithCurrent.html)"
    * [java.util.List](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html)
    * [java.util.ListIterator](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ListIterator.html)
* [Makeup exam 1](../exams/makeup01) due Thursday the 11th
    * **Required** prologue due Friday night
    * I will try to do the exam tonight and report back on times
    * I will distribute unit tests on Saturday
    * You only need do the prologue for the problems you intend to
      do on the makeup.

### Extra credit

#### Extra credit (Academic/Artistic)

* Convo Thursday: Microbes: The links between soil, gut, and health
* Noura Mint Seymali Concert tonight at 7:30 p.m. in Herrick
* Isabelle Demers concert, Saturday at 3:00 p.m. in Herrick

#### Extra credit (Peer)

* Singers concert, April 7 at 2:00 p.m.
* Thursday the 11th, Environment group event.  More to come.

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

* Painting with plants 7:30 p.m. in Younker Lounge
* 30 Minutes of Mindfulness at SHACS every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

* Wednesday the 10 at 4pm on Mac Field: Giant Laurel Leaf.  (Free t-shirt!)
* Scarlet and Give Back Day next Wednesday/Thursday (I think).  If you
  don't have money to donate, let me know and I will give you $5 to donate.
* Public speaking workshop.
* Storytelling workshop.
* Kinetic sculpture competition.

### Other good things

### Questions

Suppose I have two arrays, of sizes n and m, and I have to iterate both.
What's the time complexity?

> O(n + m) is the most common model.

> O(max(n,m)) is also acceptable, although it's the same

Quiz
----

* Sam should not write pseudocode, at least not late at night.

About the makeup exam
---------------------

* Sam's grading:
    * Check: Full credit
    * Check-minus: Not quite full credit (drop by 1/3 letter)
    * Half: Half credit
    * Zero: No credit
    * Add 'em up, follow those policies

Code from the InterWeb
----------------------

Taken from <https://stackoverflow.com/questions/1930454/what-is-a-good-solution-for-calculating-an-average-where-the-sum-of-all-values-e>

```java
double mean(double[] ary) {
  double avg = 0;
  int t = 1;
  for (double x : ary) {
    avg += (x - avg) / t;
    ++t;
  }
  return avg;
}
```

* What works for `double` may not work for `long`.
* This still has an overflow problem.
* The comments on this solution even say that it has an overflow problem.

Taken from <http://www.java2novice.com/data-structures-in-java/queue/dynamic-array-impl/>


```java
  private void increaseCapacity(){
         
        //create new array with double size as the current one.
        int newCapacity = this.queueArr.length*2;
        int[] newArr = new int[newCapacity];
        //copy elements to new array, copy from rear to front
        int tmpFront = front;
        int index = -1;
        while(true){
            newArr[++index] = this.queueArr[tmpFront];
            tmpFront++;
            if(tmpFront == this.queueArr.length){
                tmpFront = 0;
            }
            if(currentSize == index+1){
                break;
            }
        }
        //make new array as queue
        this.queueArr = newArr;
        System.out.println("New array capacity: "+this.queueArr.length);
        //reset front & rear values
        this.front = 0;
        this.rear = index;
    }
```

If you ever turn in code this crappy, you will get a zero.  

Issues

* Declares a pointless variable.
* Prints out random error messages for now reason.
* A for loop masquerading as a while loop.  Ugly!  (Also really
  hard to analyze).
* Lots of +1 and -1, which suggests difficulty getting bounds right.
* Inconsistency in naming.  Sometimes `front`, sometimes `this.front`.
* Includes redundant `rear` field.
* Doesn't update `capacity` field.

Average (quick notes)
---------------------

Testing SLPQs
-------------

Testing is a skill, and takes time to develop.

* You develop the skill by trying again and again and again.
* "I think I'm done"; push a little bit further.
    * "What is likely to be a bit strange?"
    * "What is pretty normal?"
* If you can't think of things, randomized tests are your friend.
    * Cover things you don't think of.
    * When they fail, require you to think a bit more about why,
      giving you a new set of tests to write.
* Randomized tests depend on the particular domain that you're working
  with.  Often: Start with an input with a known solution, randomize
  input in a way that doesn't affect the solution, run the alg.
* Key issues in SLPQs
    * Does the queue end up in order?
    * Does remove work?
        * At the front (multiple times)
        * At the end (multiple times)
            * Neighboring
            * Not neighboring
        * In the middle (multiple times)
        * With subsequent additions

Start with something we know: A randomly generated array of integers
in ascending order, with potential duplicates.

    // Generate an array with random size and elements in increasing
    // order.
    int[] values = new int[1 + rand.nextInt(100)];
    values[0] = 10 - rand.nextInt(20);
    for (int i = 1; i < values.length; i++) {
      values[i] = values[i-1] + rand.nextInt(3);
    } // for

    // Randomly permute the array
    int[] copy = Arrays.copyOf(values);
    permute(copy);      // We have to write permute

    // Add all the values to the queue
    for (int val : copy) {
      q.add(val);
    } // for

    // And check that it's in the right order
    Iterator<Integer> it = q.iterator();
    for (int i = 0; i < values.length; i++) {
      assertTrue(it.hasNext());
      assertEquals(values[i], it.next());
    } // for
    assertFalse(it.hasNext());

    // Systematically remove elements
    Iterator<Integer> it = q.iterator();
    while (it.hasNext()) {
      if (it.next() % 2 == 0) {
        it.remove();
      } // if element is even
    } // it.hasNext()

    // And check to make sure that it contains all of the odd elements
    // of the original array
    Iterator<Integer> it = q.iterator();

    // And check that it's in the right order
    Iterator<Integer> it = q.iterator();
    for (int i = 0; i < values.length; i++) {
      if (values[i] % 2 == 1) {
        assertTrue(it.hasNext());
        assertEquals(values[i], it.next());
      }
    } // for
    assertFalse(it.hasNext());

    // Add some elements back in
    for (int val : copy) {
      if (val % 2 == 0) {
        q.add(val);
      } // 
    }
    // And check the result
      

Utilities

    public static void permute(int[] values) {
      for (int i = 0; i < values.length; i++) {
        swap(values, i, rand.nextInt(values.length));
      }
    } // permute

Note

* Look at some of the tests I wrote.  (The style isn't great, but the
  ideas may be useful.)

SLPQs
-----

Start with pictures!

Array-based queues
------------------

Testing average
---------------

Average
-------

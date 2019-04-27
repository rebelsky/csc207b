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
* Code from the Interweb
* Testing our SLPQs
* Linked structures and iterators
* Array-based queues
* Testing average
* Average

Preliminaries
-------------

### News / Etc.

* Detour questions:
    * Do you prefer the PM/AV-style diagrams or the SR-style diagrams?
    * Do you prefer the PM/AV-style labs or the SR-style labs?
* I will *not* post the answers we develop in class today.  (I may
  post the tests.)
* Oreos: Up to three

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

### Extra credit

#### Extra credit (Academic/Artistic)

* Convo Thursday: Microbes: The links between soil, gut, and health
* Noura Mint Seymali Concert tonight at 7:30 p.m. in Herrick
* Isabelle Demers concert, Saturday at 3:00 p.m. in Herrick

#### Extra credit (Peer)

* Singers concert, April 7

#### Extra credit (Wellness)

#### Extra credit (Wellness, Regular)

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

### Other good things

### Questions

Quiz
----

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

Testing SLPQs
-------------

* Key things
    * Try to think expansively
    * Practice, practice, practice
    * Take advantage of randomness - it can help you catch things
      you've never thought of
* Particular issues in SLPQs
    * Keep the queue in sorted order
    * Remove
* Writing a randomized test
    * Start with an ordered array of integers, generated randomly
        values[i] = values[i] + rand.nextInt(3);
    * Make a copy
    * Permute the copy
        for (int i = 0; i < copy.length; i++) {
          swap(copy, i, rand.nextInt(copy.length));
        }
    * Add to the sorted queue
    * Compare to the order array of integers
        Iterator<Integer> it = slpq.iterator();
        for (int i = 0; i < values.length; i++) {
          assertTrue(it.hasNext());
          assertEquals(values[i], it.next());
        }
        assertFalse(it.hasNext());
    * Remove some values (even)
        Iterator<Integer> it = slpq.iterator();
        while (it.hasNext()) {
          if (it.next() % 2 == 0) {
            it.remove();
          } // if
        } // while
    * Check that it worked
        for (int i = 0; i < values.length; i++) {
          if (values[i] %2 == 1) {
            assertTrue(it.hasNext());
            assertEquals(values[i], it.next());
          } // if
        } // for
        assertFalse(it.hasNext());
    * Add the even values back in
    * And check

SLPQs
-----

Array-based queues
------------------

Testing average
---------------

Average
-------
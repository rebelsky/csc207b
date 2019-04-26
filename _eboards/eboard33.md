---
title: Eboard 33  Probing in hash tables
number: 33
section: eboards
held: 2019-04-24
link: true
current: true
---
CSC 207.01 2019S, Class 33:  Probing in hash tables
===================================================

_Overview_

* Preliminaries
    * Notes and news
    * Upcoming work
    * Extra credit
    * Questions
* Mentor evals
* About traversal and iterators
* Lab

Preliminaries
-------------

### News / Etc.

* I think today sets a new record for the length of the preliminaries.
* **The computer ate yesterday's eboard.  Sorry.  Look at [the eboard
  from CSC 207.02](../../02/eboards/eboard32) for similar details.**
* I plan to return makeup exams on Friday.
* The College has decided to institute electronic end-of-course evaluations.
  You should be getting information about them next week.  We will do
  evaluations in class on the last day of class, so you should not be able
  to access this class's EOCEs before then.
* Next week's Science Teaching and Learning Group will discuss student
  wellness.  I'm hoping to share some of the extra credit reports you've
  submitted (anonymously).  Let me know if you object to me sharing yours.
* Since there were some questions about Monday's quiz, we'll spend
  some time discussing the various issues and approaches.
* Given how quizzes have gone this semester, I'm not counting them in 
  your final grade.  (Or I'm counting them based on the percentage you
  took.)
* You should think about taking HIS 295, which approaches some computational topics from a very different perspective.

    > _HIS 295 Digital History: Investigating the Past_. This course will introduce students to methods used in the digital humanities, with a special emphasis on applications to historical studies. Students will create projects and study existing digital projects, with a special focus on U.S. History in a global context. Readings will include primary sources as well as recent contributions to theory in digital humanities. We will learn general principles of working with humanistic data as well as techniques such as building on-line exhibitions, digital mapping, and computational analysis of text. No technical skills or experience in digital humanities work are required, but willingness to gain both are fundamental to the class. Prerequisite: HIS-100 or second-year standing.

### Upcoming work

* No additional reading!  (But you may want to review.)
* [Assignment 8](../assignments/assignment08) due Thursday the 25th
* Exam 2 to be distributed Friday.
* Today's lab writeup: TBD

### Extra credit

#### Extra credit (Academic/Artistic)

* **Tomorrow**: PBK Convo, Thursday, 11am: "Antievolutionism in Historical Perspective"
* **Tomorrow**: McKibben lecture, Thursday, 4:15 p.m., JRC 101
* **New**/**Tomorrow**: Technology and the Arts:

    > Guest Artist Carol Burch-Brown is the creator of “Salt Marsh Suite” a collaborative inter-media arts installation and dance performance based in fieldwork, data collection, and close observation of a North Carolina coastal estuary.
Join us on Thursday April 25th at 11AM in the Flanagan Theatre to see the installation, and hear Carol talk about the digital art-making processes, specifically theerror. MAX coding environment, and other digital tools she used to make this unique work.

    > Performances: Thurs April 25-Saturday April 27 at 7:00PM and 8:30PM; Sunday April 28 2PM and 3:30PM.

* **New**: CS Table, next Tuesday, Big data and Facebook
* **New**: Three talks by Prof. Dr. Yvonne Foerster (<https://yvonnefoerster.com/>)

    > Wednesday: May 1, 4:30-6pm, HSSC S3325: _Beyond the Anthropocene: Technology, Innovation, and the (Post-)Human Condition_

    > Emergent technologies today are advertised as means to create a better future, while the futures imagined in popular science and culture move rather towards the transcendence of human life. This talk examines the conception of innovation between the technological enthusiasm to overcome human limitations and the necessity to critically reflect on the (post-)human condition.

    > Thursday, May 2, Noon-12:50pm, HSSC N3110 _Degrees of Freedom: Embodiment, Neuroplasticity, and the Need for a Critical Neuroscience_

    > Lunch and beverages provided

    > Neuroplasticity, the ability of the brain to adjust to new affordances and to overcome limitations through damage, has been part of a discourse that celebrated freedom rather than neuro-determinism. My aim is to discuss this concept with regard to the rise of neurocultures (e.g., enhancement strategies, neuromarketing) in a more critical light.

    > Friday, May 3, Noon-12:50pm, Bucksbaum 152: _Designing Future Bodies: Fashion and Technology_

    > Lunch and beverages provided

    > Fashion and technology are inextricably linked in production, marketing, design, and functionality. In this talk I shed some light on the potential of fashion to critically examine the role of technology in shaping bodies, gender, and social relations. I will take a closer look at experimental practices and scientific cooperation in the field of fashion.

#### Extra credit (Peer)

* Track at Grand View on Friday.

#### Extra credit (Wellness)

* **New**: Guided Movement Meditations, 12:15 Friday and Saturday, 
  Flanagan Theatre.  Also between performances of the show.
* **New**: Bread-Making Workshop, Friday, 6-8 p.m.  Sign up through
  grahamj@grinnell.edu.  (Limited to 12 people.)

#### Extra credit (Wellness, Regular)

* 30 Minutes of Mindfulness at SHACS/SHAW every Monday 4:15-4:45
* Any organized exercise.  (See previous eboards for a list.)
* 60 minutes of some solitary self-care activities that are unrelated to 
  academics or work.  Your email reflection must explain how
  the activity contributed to your wellness.
* 60 minutes of some shared self-care activity with friends.  Your email 
  reflection must explain how the activity contributed to your wellness.

#### Extra credit (Misc)

### Other good things

* **Today**: Get to Know Grinnell, Noon, JRC 101 (Sarah Purcell and
  Henry Rietz discuss the transition from student to faculty)
* **Today**: Dartanyan Brown discussion, 4pm Wednesday April 24, HSSC S3325
* **Today**: Dartanyan Brown concert, 7:30pm Wednesday April 24, Sebring-Lewis

### Questions

_Do I really need to understand the analysis starting on p. 671?_

> You should *read* that analysis.  However, I don't expect you to 
  understand it all.  It would be good to understand the early recurrence 
  relations and how they are derived.

_How do I do part 3 of the assignment?_

> You will likely need to augment your various procedures to log the
  number of steps they take.  (I'd count each time you follow an
  edge and each time you switch a level.)

> Pick a variety of sizes (e.g., 1000, 2000, 4000, 8000) and, for each size,
  do a bunch of calls to `add`, `get`, and `remove`.  That should give
  you a minimum, maximum, and average number of steps for each operation.  
  (I'd do each size a few different times.)

> See if the growth appears logarithmic.

_Why can't I call `nodes.add(i, node)`?_

> Most frequently, because the size of `nodes` is less than `i`.

_But I initialized `nodes` with `new ArrayList<Node<K,V>>(16)` and `i` is less than 16._

> That creates an arraylist of *capacity* 16, but size 0.

_Any hints?_

> I found it helpful to write a general `find` procedure that gave
  me an ArrayList of all the previous nodes.  I used that for `set`,
  `get`, and `remove`.

> I found it useful to create a dummy node for the front of the list.
  (The latter required me to customize the comparator to handle the dummy node.)

> I got sick of typing things like `node.next.get(i)`, so I wrote a
  method that just gets the ith element of the next field of a node.
  (Neither of these methods are necessary, but they were helpful.)

```java
  /**
   * Get the next node at the specified level.
   */
  public SLNode<K, V> next(int level) {
    return this.next.get(level);
  } // next

  /**
   * Set the next node at the specified level.
   */
  public void setNext(int level, SLNode<K, V> next) {
    this.next.set(level, next);
  } // setNext(int, SLNode<K,V>)
```

_How might we skip over a node for remove?_

> You will make an arraylist (or array) of previous nodes.  Let's say it's
  called nodes.  For each level, you'll write something like.

        SLNode<K,V> prev = nodes.get(level);
        prev.setNext(level, prev.next(level).next(level));

_What should we count for part 3?_

> You can either count every call to next and every change in level, or
  every time you actually follow a next link and every change in level.

> They will be similar.  (The former is approximately twice as big as the
  latter.)

> If you use the `next` method above, you could just increment the counter
  there.

_Should the height of the skip list change?_

> I tend to allow my skip lists to grow, but you can choose to cap the height
  at something reasonable.

_Can we follow the paper's approach of capping heights at MAX_HEIGHT, starting the list's height at 1, and increasing the height when there's a new node?

> Sure.

Tree traversal and iteration, revisited
---------------------------------------

```text
       __ A __
      /       \
     Q         X
    / \       /
   B   D     L
              \
               M
```

Ten different orders of traversal, guided by three principles

* When do you visit/report a node vs. its children
    * Preorder (node before children) (top-down)
    * In-order (node between children)
    * Postorder (node after children) (bottom-up)
* Do we go across levels (breadth-first) or down into levels (depth-first)?
* Direction: Left-to-right or right-to-left
* But breadth-first, in-order traversal makes no sense (either right-to-left
  or left-to-right)

Breadth-first, postorder (bottom-up), right-to-left: M, L, D, B, X, Q, A

Breadth-first, preorder (top-down), left-to-right: A, Q, X, B, D, L, M

Which seem to be the ones we will most commonly use?

* Depth-first, in-order, left-to-right: In a BST, that gives us the nodes
  in the order determined by the keys.
* Breadth-first, preorder: A natural order; also useful because it gets
  "closer" nodes first.
* Depth-first, pre-order, left-to-right: Easier to implement recursively.
  (See the `dump` method.)

How do we implement breadth-first, preorder?  (Without worrying about the iterator.)

* Recursion is likely to cause depth-first
* So we need to find some other strategy: Putting them into a queue
  may work.

        public void forEach(BiConsumer<K,V> action) {
          SimpleQueue<Node<K,V>> q = new SimpleQueue<Node<K,V>>(this.root);

          while (!q.isEmpty()) {
            Node<K,V> next = q.get();
            action.accept(next.key(), next.value());
            if (next.left() != null) q.put(next.left());
            if (next.right() != null) q.put(next.right());
          } // while

        } // forEach

What about the iterator?

        return new Iterator<Pair<K,V>>() {
          SimpleQueue<Node<K,V>> q = new SimpleQueue<Node<K,V>>(this.root);

          public boolean hasNext() {
            return !q.isEmpty();
          } // hasNext()

          public Pair<K,V> next() {
            Node<K,V> next = q.get();
            if (next.left() != null) q.put(next.left());
            if (next.right() != null) q.put(next.right());
            return new Pair<K,V>(next.key(), next.value());
          } //next()
        }; // new Iterator

Unpacking a loop into an iterator is one of those ideas that shows the thought process of OOP: We go from a control structure to an object.

How would I implement for-each if I already had an iterator?

        public void forEach(BiConsumer<K,V> action) {
          Iterator<Pair<K,V>> it = this.iterator();
          while (it.hasNext()) {
            Pair<K,V> next = it.next();
            action.accept(next.key(), next.value());
          } // while
        } // forEach

Or

        public void forEach(BiConsumer<K,V> action) {
          for (Pair<K,V> next : this) {
            action.accept(next.key(), next.value());
          } // for
        } // forEach
            
Does it matter if the `Pair<K,V> next` declaration is inside or outside
the loop?

> Not if the Java compiler is any good.  And it's pretty good.

What if I want to do bottom up rather than top down?

* Failed attempt one: Use a stack in an unclear way.

Solution to alternate problem

        Create a stack that can hold pairs or nodes
        Add the root
        while (!isEmpty())
          pop
          if it's a node
            push value
            push right
            push left
          else
            process pair
         end while

* This is post-order, depth-first, left-to-right
* We could make it pre-order by ...
* We could make it in-order by ...
* We could make it right-to-left by ...

Back to breadth-first, post-order, left-to-right

        Create a queue of nodes that holds the root
        Create a stack of pairs that is initially empty
        while (!q.isEmpty())
          dequeue
          push value
          enqueue right
          enqueue left
        end while
        while (!stack.isEmpty())
          pop
          process
        end

* Yes, it seems like it violates the basic idea of conserving space
  in iterators.  But it's the best I can do right now.  
* Let's check if it works.  We'll see ... pretty close.

Lab
---

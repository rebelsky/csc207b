---
title: "Separating what from how: Interfaces in Java"
summary: |
  Good programming style suggests that you decide *what* methods a
  class provides before you decide *how* the class will provide
  those methods.  In this reading, we consider Java's *interfaces*,
  which encourage such separation.
prereqs: |
  Basic understanding of classes.
---
Introduction: Separating the what from the how
----------------------------------------------

As you should have discovered by now, a pair of key activities in
object-oriented design are describing classes of objects and using
objects from those classes.  I have also recommended a particular
strategy for designing classes: Start by deciding *what* your class
should do and then figure out *how* to make the class do that.

Why do I (and most object-oriented designers) make the what/how
distinction?  First, because there's good evidence that programmers
cannot think carefully about a wide variety of issues simultaneously.
That is, if programmers have to think from the start about how they
are going to implement a class (and its methods), they may not think
as carefully about what methods it should provide.  Second, because
there's good evidence that programmers often decide upon the methods
they will provide based on their choice of how they will implement
the class.  Third, because it's difficult to decide when rewriting
a class what methods are necessary and what methods are tied to the
implementation.  Finally, because the what/how interface relates
closely to the key object-oriented concept of *encapsulation*.  That
is, because the clients of a class should only know what the class
can do, and not how the class does it, class designers should also
carefully distinguish the two.

Interfaces in Java
------------------

Many programming languages accept this important need to distinguish
between what a piece of code does and how it accomplishes that task,
and incorporate in the language a mechanism for distinguishing these
two key issues.  When you learned C, you probably discovered that the
header files (`.h`) specified the capabilities of libraries and the
library code specified the implementations.  In Java, the primary
mechanism for distinguising what from how is something called an
*interface*.  Interfaces contain only method headers (a method
declaration without the body) and, optionally, static fields.

Interface declarations look much like class declarations, except:
(1) interfaces use the keyword `interface`, rather than
the keyword `class`; (2) interfaces contain no constructors,
object fields, or static methods; (3) method declarations in interfaces
lack bodies.

Hence, an interface declaration looks much like the following:

```java
public interface InterfaceName {
  // +-----------+---------------------------------------------------------
  // | Constants |
  // +-----------+

  // ...

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  // ...
} // interface InterfaceName
```

For example, here is a portion of an interface declaration for a point 
in two-space.

```java
/**
 *  Vectors in two-space.
 *
 *  @author Samuel A. Rebelsky
 *  @version 1.3 of January 2019
 */
public interface Point2D {
  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the X coordinate of the point.
   */
  public double getX();

  /**
   * Get the Y coordinate of the point.
   */
  public double getY();

  /**
   * Get the distance of this point from the origin.
   */
  public double getDistanceFromOrigin();

  /**
   * Translate this point by deltaX in the x direction and 
   * deltaY in the y direction.  Note that this method
   * mutates the underlying point.
   */
  public void translate(double deltaX, double deltaY);

  /**
   * Make a copy of this point.
   */
  public Point2D clone();
// interface Point2D
```

Interfaces and classes
----------------------

What happens after you've defined an interface?  It's time to build
a class that corresponds to the interface.  When we build that class,
we tell Java that the class corresponds to the interface with the
`implements` keyword.  That is, we write

```java
public class ClassName implements InterfaceName*
{
  // ...
} // class ClassName
```

For example, if we chose to implement `Point2D` as a class with a
pair of fields, the x coordinate and the y coordinate, we might
write the following code.

```java
/**
 * An implementation of points in two space using an X coordinate
 * and a Y coordinate.
 *
 * @author Samuel A. Rebelsky
 * @version 1.3 of January 2019
 */
public class Point2DPair implements Point2D {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The x coordinate of the point
   */
  double x;

  /**
   * The y coordinate of the point.
   */
  double y;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create the point (x,y).
   */
  public Point2DPair(double x, double y) 
  {
    this.x = x;
    this.y = y;
  } // Point2DPair(double,double)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the X coordinate of the point.
   */
  public double getX() {
    return this.x;
  } // getX() 
  
  /**
   * Get the Y coordinate of the point.
   */
  public double getY() {
    return this.y;
  } // getY()

  /**
   * Get the distance of this point from the origin.
   */
  public double getDistanceFromOrigin() {
    return Math.sqrt(this.x*this.x + this.y*this.y);
  } // getDistanceFromOrigin()

  /**
   * Translate this point by deltaX in the x direction and
   * deltaY in the y direction.  Note that this method
   * mutates the underlying point.
   */
  public void translate(double deltaX, double deltaY) {
    this.x = this.x + deltaX;
    this.y = this.y + deltaY;
  } // translate(double, double)

  /**
   * Make a copy of this point.
   */
  public Point2D clone() {
    return new Point2DPair(this.x, this.y);
  } // clone()
} // class Point2DPair
```

Alternative implementations
---------------------------

One particularly important aspect of Java's interface mechanism is
that you, as programmer, are not limited to only one implementation
per interface.  That is, you can implement the same interface in
multiple ways and then later choose with implementation is best for
a particular task.  For example, if we found that we were frequently
checking the distance of points from the origin, we might instead
choose to implement them as a radius/theta pair.

```java
/**
 *  An implementation of points in two space using a radius/theta pair
 *  which specify the distance of the point from the origin and the
 *  angle between the positive x-axis and a line from the origin to
 *  the point.
 *
 *  @author Samuel A. Rebelsky
 *  @version 1.3 of January 2019
 */
public class Point2DPolar implements Point2D {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  
  /**
   * The radius, which represents the distance of the point from 
   * the origin.
   */
  double radius;
  
  /**
   * Theta, which represents the angle between the positive X axis
   * and a line from the origin to the point.
   */
  double theta;
  
  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+
  
  /**
   * Create the point (x,y)
   */
  public Point2DPolar(double x, double y) {
    this.radius = Math.sqrt(x*x + y*y);
    this.theta = Math.atan(y/x);
  } // Point2DPolar(double,double)
  
  /**
   * Create a point radius 0 and angle 0 (with the expectation that
   * we'll change it almost immediately).
   */
  private Point2DPolar() {
    this.radius = 0.0;
    this.theta = 0.0;
  } // Point2DPolar()
  
  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+
  
  /**
   * Get the X coordinate of the point.
   */
  public double getX() {
    return this.radius * Math.cos(this.theta);
  } // getX() 
  
  /**
   * Get the Y coordinate of the point.
   */
  public double getY() {
    return this.radius * Math.sin(this.theta);
  } // getY()
  
  /**
   * Get the distance of this point from the origin.
   */ public double getDistanceFromOrigin() 
  {
    return this.radius;
  } // getDistanceFromOrigin()
  
  /**
   * Translate this point by deltaX in the x direction and
   * deltaY in the y direction.  Note that this method
   * mutates the underlying point.
   */
  public void translate(double deltaX, double deltaY) {
    // Left as an exercise to the reader.
  } // translate(double, double)
  
  /**
   * Make a copy of this point.
   */
  public Point2D clone() {
    Point2DPolar tmp = new Point2DPolar();
    tmp.radius = this.radius;
    tmp.theta = this.theta;
    return tmp;
  } // clone()
} // class Point2DPolar
```

As suggested above, we can use *either* of these implementations
and expect that they will behave the same (except, of course, in
terms of efficiency).  How do clients write code that accepts either
implementation?  They write code that uses the interface, rather
than a particular class.  For example, we can write a method to
compare points by distance from the origin as follows:

```java
/**
 * Compare two points.  
 *
 * @return 
 *    A negative number if p1 is closer to the origin than p2, 
 *    0 if the two are the same distance from the origin, and 
 *    a positive number if p1 is further from the origin.
 */
public int compare(Point2D p1, Point2D p2) {
  return Double.compare(p1.distanceFromOrigin(), p2.distanceFromOrigin());
} // compare(Point2D, Point2D)
```

It is now perfectly acceptable to call this `compare` method with
two `Point2DPair` objects, with two `Point2DPolar` objects, or even
with one object of each class.  We return to this idea in the reading
on [subtype polymorphism](../readings/subtype-polymorphism).


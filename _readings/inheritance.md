---
title: Reusing and generalizing code with inheritance
summary: |
  We consider how the object-oriented design principle of *inheritance*
  permits programmers to build new classes from old, and automatically
  incorporate features from those classes.
prereqs: |
  Class basics.  
  [Subtype polymorphism](../readings/subtype-polymorphism).
---

Introduction: Reducing Coding Effort
------------------------------------

  As we saw in [the reading
  on polymorphism](../readings/polymorphism.html), one of the great benefits of polymorphism is
  that the programmer need only write a method once, and that method
  can naturally be used on a variety of kinds of data.  In fact,
  one of the central reasons that object-oriented programming is so
  popular is that it does such a good job at encouraging and supporting
  code reuse.  Polymorphism provides one kind of reuse.  Encapsulation
  provides a second kind of reuse, because it encourages programmers
  to develop self-contained classes and objects.

  Object-oriented languages traditionally provide a third kind of reuse:
  *inheritance*.  The principle of inheritance is
  fairly simple: If we want to build a new class that is similar to an
  existing class, we can automatically incorporate all the fields and
  methods of the existing class.

An Example: Modeling People in Colleges
---------------------------------------

  Let's consider a simple example.  Suppose we are responsible for
  modeling the interactions at a college or university.  We might
  begin by designing a `Person` class.  That class is
  likely to have fields for a name, a gender, an age, physical
  characteristics, and whatever else the designer deems appropriate.

  But colleges and university tend to classify people.  Some people are
  professors.  Some are students.  Some are academic staff.  Some are
  hangers-on.  How do we handle that classification?  One possibility
  is to add a separate field, `classification`.  Unfortunately,
  that field does not suffice for the complexity of the different roles.
  For example, students are likely to have a grade point average, a
  student id number, a major, an accumulated debt, and many other 
  characteristics.  Faculty members are likely to have different
  characteristics, including a department, a salary, a Ph.D. (or
  Masters') institution, and so on and so forth.

  What we'd like to do is say that every student is a person, and every
  faculty member is a person, but that each has different additional
  characteristics.

Strategy One: Generalizing People with Polymorphic Fields
---------------------------------------------------------

  How can we implement such a situation?  One possibility is to include
  a polymorphic field within the `Person` class that
  links to additional information.  In that case, we might write something
  like the following.

<pre>
public class Person 
{
  ...
  String classification;
  Information additionalInfo;
  ...
  public String answer(String question) 
  {
    ...;
  } // answer(String)
  ...
  public void addAccess(RestrictedLocation loc) 
  {
    ...;
  } // addAccess(RestrictedLocation)
  ...
  public void marry(Person mate) 
  {
    ...;
  } // marry(Person)
} // class Person

public interface AdditionalInformation 
{
  ...
} // interface AdditionalInformation

public class StudentInfo 
  implements 
    AdditionalInformation 
{
  ...
  /** 
   * The student's debt to the college, in dollars.  Note that
   * we use a long rather than an int because debt may exceed
   * the largest int.
   */
  long debt;
  ...
} // class StudentInfo

public class FacultyInfo implements Information 
{
  ...
  /** The year in which the faculty member was hired.  */
  int yearHired;
  ...
} // class FacultyInfo
</pre>

  While this strategy seems okay in the abstract, it gets very
  problematic when you start worrying about details.  For example,
  faculty and students are likely to have different methods.  How
  do we distinguish those methods?  In fact, even when they have
  the same methods, they are likely to respond to them differently.
  For example, when asked a question, students often pause and say
  "um", but professors pontificate.

  In addition, this strategy creates some problems with encapsulation
  because the client of a `Person` will need to extract
  the `additionalInfo` field and work directly with that
  object.

Strategy Two: Wrapping People
-----------------------------

  An alternative strategy is to turn things around a bit.  Rather
  than putting the additional information within the `Person`
  class, we can put a `Person` object within the class we
  create for each kind of person.  (This technique is a variant of
  what is typically called a "mixin" class.)

<pre>
public class Professor 
{
  Person personData;
  int yearHired;
  ...
  public String answer(String question) 
  {
    return "The solution is obvious if you simply consider the Church-Rosser theorem.";
  } // answer(String)

  public void marry(Person mate) 
  {
    personData.marry(mate);
  } // marry(Person)
} // class Professor

public class Student 
{
  Person personData;
  long debt;
  ...
  public String answer(String question) 
  {
    return "Ummm ... I'm not sure.";
  } // answer(String)

  public void marry(Person mate) 
  {
    personData.marry(mate);
  } // marry(Person)

  public void enroll(Course c) 
  {
    ...;
  } // enroll(Course)
} // class Student
</pre>

  This solution is somewhat better, in that (a) `Student`
  and `Professor` can provide different methods (e.g.,
  `Student` provides `enroll` and
  `Professor` does not); (b)
  `Student` and `Professor` can provide
  different implementations of the same method (e.g., they implement
  `answer` differently); (c) clients can still use
  the methods of `Person`, provided the implementers
  of `Student` and `Professor` provide
  a mechanism to access those methods, as they've done with
  `marry`.

  Unfortunately, this solution is not polymorphic.  In particular,
  we can't use a `Student` in place of a `Person`,
  so students cannot marry other students.  (It's probably good that
  our model does not permit students to marry professors.)  We can
  make the solution polymorphic by changing `Person` from
  a class to an interface, and then creating a `GenericPerson`
  class that looks like the old `Person` class.

<pre>
public interface Person 
{
  ...
  public void marry(Person other) throws Exception;
  public String answer(String question);
  ...
} // interface Person

public class GenericPerson implements Person 
{
  ...
  String lastName;
  ...
  public void marry(Person other)
    throws Exception
  {
    ...
  } // marry
    ...
} // class GenericPerson

public class Professor 
  implements Person 
{
  GenericPerson personData;
  int yearHired;
  ...
  public void marry(Person mate) throws Exception 
  {
    if (mate instanceof Student) 
      throw new Exception("Professors should not marry students.");
    personData.marry(mate);
  } // marry(Person)
} // class Professor

public class Student 
  implements Person 
{
    Person personData;
    long debt;
    ...
    public void marry(Person mate) throws Exception 
    {
      if (mate instanceof Professor) 
        {
          throw new Exception("Students should not marry professors.");
          // Don't you love having morality enacted in code?
        }
      personData.marry(mate);
    } // marry(Person)
    ...
} // class Student
</pre>

  This strategy is better, but it includes a lot of needless coding.
  In particular, for every method that `GenericPerson`
  provides, `Student` and `Professor`
  *must* provide a method that, in essence, does little
  more than call the corresponding method in `GenericPerson`
  (as was the case in the original implementations of `marry`).

A Better Strategy: Inheritance
------------------------------

  Is there anything better that you can do?  Given the Java you've learned
  so far, no.  However, Java provides a feature designed specifically for
  this kind of problem: *inheritance*.  Inheritance
  permits you to say that one class is based on another, and have that
  class automatically gain the fields and methods of the original class.
  In Java terminology, you say that one class *extends*
  another.  You put the `extends` command in the class header,
  as in the following.

<pre>
public class *NewClass* 
  extends *OldClass* 
{
  ...
} // class *NewClass*
</pre>

  Inheritance also supports polymorphism: You can use a member of
  the new class (also called the subclass) wherever you can use a
  member of the original class (also called the superclass)>

  In our continuing example, we might write

<pre>
public class Person 
{
  ...
  int age;
  Gender gender;
  ...
  public int getAge() 
  {
    return this.age;
  } // getAge()
 
  public String answer(String question) 
  { 
    return "Maybe.";
  } // answer(String)
 
  public void marry(Person mate) 
  {
    ...;
  } // marry(Person)
} // class Person

public class Professor 
  extends Person 
{
} // class Professor

public class Student 
  extends Person 
{
} // class Student
</pre>

  In this example, `Student` and `Professor`
  have `age` and `gender` fields and
  `answer` and `marry` methods, even 
  though neither declares one.

  As importantly, you can use a `Professor` or a 
  `Student` wherever a `Person` is
  required, so it is easy to write polymorphic code.

  Of course, we need to put stuff in the body of a subclass (where
  a subclass is a class that extends another class).  We will consider
  fields, constructors, and methods separately.

Fields in a Subclass
--------------------

  You can create whatever fields you want in the subclass.  However,
  it is generally considered a bad idea to have fields whose names
  match the names in the superclass.  In the continuing example, we
  might want to add simple fields to the `Faculty` and
  `Student` classes.

<pre>
public class Professor 
  extends Person 
{
  int yearHired;
} // class Professor

public class Student 
  extends Person 
{
    long debt;
} // class Student
</pre>

Adding New Methods to Subclasses
--------------------------------

  It is similarly easy to add new methods to a subclass.  You simply
  write a method as you would otherwise.

<pre>
public class Student 
  extends Person 
{
    ...
    public void enroll(Course c) 
    {
      this.debt = this.debt + c.getCost();
    } // enroll(Course)
    ...
} // class Student
</pre>

Overriding: Changing the Behavior of Methods
--------------------------------------------

  Of course, when you write a new class, you want to do more than 
  create new fields and new methods (and reuse old fields and new methods);
  there are also times that you want to change the behavior of the 
  old methods.  The term for such a change is *overriding*.
  To override a method, you simply write the new version of the method.

<pre>
public class Professor 
  extends Person 
{
  ...
  @Override
  public String answer(String question) 
  {
    return "The solution is obvious if you simply consider the Church-Rosser theorem.";
  } // answer(String)
    ...
} // class Professor

public class Student extends Person 
{
  ...
  public String answer(String question) 
  {
    return "Ummm ... I'm not sure.";
  } // answer(String)
    ...
} // class Student
</pre>

  Now, if we call the `answer` method of what appears to be
  a `Person`, Java determines at run time whether it's
  a generic `Person`, a `Professor`, or a
  `Student`, and calls the appropriate `answer`
  method.

  For example, the following code prints `Maybe.`, then
  a long-winded quote, and then "Ummm ... I'm not sure."

<pre>
Person p;
p = new Person(...);
p.answer("Should we evalute the parameters to a function before or after we apply the function?");
p = new Professor(...);
p.answer("Should we evalute the parameters to a function before or after we apply the function?");
p = new Student(...);
p.answer("Should we evalute the parameters to a function before or after we apply the function?");
</pre>

  Your job gets a little bit more difficult when you want to slightly change
  the behavior of a method, but still use parts of the original.  In this
  case, you can refer to the original method, but you must preface the
  name of the method with `super`.  

  For example, here we write a politically safe `marry` method.

<pre>
public class Professor 
  extends Person 
{
    ...
    public void marry(Person mate) 
      throws Exception 
    {
      if (mate instanceof Professor) 
        throw new Exception("Professors should not marry students.");
      super.marry(mate);
    } // marry(Person)
    ...
} // class Professor
</pre>

Constructing Members of Subclasses
----------------------------------

  Of course, we also need to write constructors for our subclasses.  
  Writing constructors turns out to be the most difficult part of
  inheritance.  In particular, *the first line of the constructor
  of a subclass must be a call to the constructor of the superclass*.
  That call uses the keyword `super` as a function in
  place of the name of the superclasses' constructor and without
  an additional `new`.  For example,

<pre>
public class Person 
{
  ...
  public Person(Gender gender, int age) 
  {
    this.gender = gender;
    this.age = age;
  } // Person(Gender, int)
    ...
} // class Person

public class Professor 
  extends Person 
{
  ...
  public Professor(String discipline, Gender gender, int age) 
  {
    super(gender, age);
    this.discipline = discipline;
  } // Professor(String, Gender, int)
} // class Professor
</pre>

  Unfortunately, if you reverse the two lines of that constructor,
  Java will complain.


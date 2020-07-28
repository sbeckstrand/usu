# CS 1440 Assignment 3.1 Instructions

[3.1: Bingo! Implementation](https://usu.instructure.com/courses/547414/assignments/2698428 "3.1: Bingo! Implementation")

Armed with a well-thought out design, now it is your job to realize in Python code the system you have created.

**I have _specifically_  instructed the tutors in the Tutor Lab to  _not_  help you with this portion of the assignment until you have completed your UML class diagram. Don't worry about starting on the code until you've first thought through your design. Many software projects have gone awry because the design step had been neglected. Don't become another statistic - think first, code after.**


## Instructions for Assignment 3.1

1.  Implement your design, paying careful attention to the following points:
    -   You have met all requirements outlined in the previous assignment and
        your program functions accordingly
    -   All unit tests pass
2.  As your design evolves go back and update your UML class diagram, user
    manual and software development plan.  It's okay for your final design to
    be different than the one you submitted last time.  **Your UML class
    diagram must incorporate *all* non-Unittest classes in your program.  This
    means the classes you wrote as well as those that were provided.**


## Test-Driven Development

An important part of this assignment is to learn about unit tests and to gain
experience approaching a problem through the Test-Driven Development
methodology.  Writing your program to comply with the provided unit tests is
meant to guide you toward a correct and robust solution, and is worth a large
proportion of the points on this assignment.

However, you are given latitude in how your solution is structured and are free
to add, remove, or refactor classes to meet the design you crafted in UML.
It is not intended for you to take advantage of this latitude in order to avoid
unit tests.

As you craft your solution, please keep the following guidelines in mind:

*   Do not create  _trivial_  unit tests; a trivial unit test is one which unconditionally passes
*   Do not delete unit tests just because they don't pass; find ways to make your functions compatible with the unit test
*   Do not change unit tests to become less strict or trivial; instead figure out how to make your code more robust
*   If a unit test fails because you renamed a class, a method or a data member, update the unit test accordingly
*   If a unit test fails because you removed a class from your design, you must _replace_  that unit test with another non-trivial unit test
*   If a unit test fails because you moved a piece of functionality from one class to another, move that unit test into the file corresponding to the new class

_**TL;DR**_ You have been given 11 non-trivial unit tests.  Your final submission must contain _at least_  11 non-trivial unit tests.


## Penalties for Assignment 3.1

Review the [Course Rules](https://gitlab.cs.usu.edu/erik.falor/fa19-cs1440-lecturenotes/blob/master/Course_Rules.md)
document to avoid general penalties which apply to all assignments. In
addition, this assignment is subject to the following penalties:

1.  **10 point penalty** if any files are not closed after being processed in
    _ordinary_  situations.  In the event of an error your program will display
    an error message and immediately exit; in such cases you do not need to
    take special measures to close files because they will automatically be
    closed by the OS as your program exits.
2.  **10 point penalty** for each  _trivial_ unit test (i.e. a unit test which unconditionally passes)

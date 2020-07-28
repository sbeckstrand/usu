# CS 1440 Assignment 3: Bingo! Design and Implementation

* [Instructions for part 3.0](doc/Instructions-3.0.md)
* [Instructions for part 3.1](doc/Instructions-3.1.md)
* [Rubric for part 3.0](doc/Rubric-3.0.md)
* [Rubric for part 3.1](doc/Rubric-3.1.md)
* [Hints](doc/Hints.md)


## Important!
**I have _specifically_  instructed the tutors in the Tutor Lab to  _not_  help you with the next portion of the assignment until you have completed your UML class diagram. Don't worry about starting on the code until you've first thought through your design. Many software projects have gone awry because the design step had been neglected. Don't become another statistic - think first, code after.**


## Overview

For your next project at DuckieCorp you are tasked with writing an interactive
Bingo card generator.  This project had been started by our C++ team, but
partway through the customer and the project manager decided that Python would
be a better platform for this system.  The partially-completed C++ program was
translated into Python before the project was assigned to you.

You will create a complete programming product consisting of

*   A program which is extensible (can be easily modified)
*   Documentation (both technical and user-oriented)
*   Unit test cases

As you well know, creating a *programming product* can take up to 3x as much
time as just making a simple program.  Plan for this and carefully manage your
time so that you can meet the deadline.

This assignment is delivered in two parts:

1. **30 points** A UML class diagram describing the starter code plus your additions to complete the design
2. **60 points** A working, tested Python program that meets the customer's requirements


## Objectives

*   Learn to design before you code
*   Study existing resources to identify requirements
    *   Read source code to identify which parts are already implemented
    *   Extract the customer's requirements from the specification
    *   Generate UML Class diagrams from source code
*   Extend a software system with new classes and features
    *   Design classes in UML first, then in code later
    *   Implement code to achieve the design
    *   Validate that your program is "doing the right thing"
*   Support your implementation work with unit tests
    *   Design code to meet the test's specifications
    *   Create or modify tests as your design evolves
    *   Verify that your program is "doing the thing right"
*   Write a users' manual at the appropriate level of detail

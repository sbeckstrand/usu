# CS 1440 Assignment 4 Instructions

## Overview

You have been assigned to a high-priority project at DuckieCorp to convert a
client's prototype program into a clean, well-factored Programming Systems
Product complete with design and user documentation. You will complete this
project over two sprints. In this sprint you will refactor the program into a
cleaner, more pliant code base while keeping the original functionality intact.
You will complete the project in the next sprint by enhancing the system's
functionality.



## Preparation

Begin by cloning and studying this code. Catalogue all code smells you
encounter. Be on the lookout for issues which get in the way of enhancing this
program:

-   Functions which do too many different things
-   Unused functions and variables
-   Unnecessary, duplicated code
-   Dead code
-   Operations which are spread throughout the program instead of being encapsulated in one place
-   Confusing or contradictory comments
-   Variables whose names override other identifiers
-   Poorly named identifiers
-   "Magic" numbers


## Requirements

### Improve the Non-Functional Aspects of this Code Base

While the code that the client has provided doesn't contain any obvious bugs,
it isn't easy to improve upon. Our client has hired us to provide new features,
but before we can deliver on this we must address the non-functional aspects of
the code base. _This isn't a hint for you to go looking for some
cleverly-hidden bug; I honestly don't think there are any problems with this
code as it stands, there aren't easter-eggs for you to find._  Refactor the
code under the assumption that it works correctly.

Things that will improve this code:

#### Dead Code

-   Code positioned after a return statement will never be reached, and can be deleted
-   Variables which are never used may be removed
-   Assignments to variables  _after_  they are read for the last time are unnecessary
-   Extra functions parameters that are not used in the body of the function should be removed
-   Functions which are not called, and have been left behind from previous debugging sessions can be deleted

#### Responsibility

-   Identify functions which have many different responsibilities and separate them into smaller pieces which are focused on one task
-   Unite functionality into one function where it makes sense to do so
-   Extract the mainline driver code from both prototype programs and unify into a single driver called  `main.py`
-   Extract support code and separate into a collection of modules as described below

#### Confusing Constructs

-   Remove or update comments which are no longer accurate or relevant
-   Replace hard-coded  _magic numbers_  with well-named variables
-   Rename carelessly-named variables so that the code is self-documenting
-   Observe how data flows throughout the program and rewrite functions such that global variables are no longer necessary


### Separate Code Into Modules

Each module will encapsulate a certain behavior or aspect of the program and
provide a well-defined interface to the other modules. This will enable you to
easily add new functionality next sprint. These modules  _may_  be implemented
as classes now, but this isn't a requirement for this sprint. It is enough for
each Python file to be a collection of functions and variables. _Keep in mind
that a goal in the next sprint is to apply the Object-Oriented techniques of
Inheritance and Polymorphism to this program._

In the end your project should include the following modules _at minimum:_

1.  `main.py`  - The driver program; imports other modules, accepts
    command-line arguments and calls upon other modules to display a fractal
    on-screen and write a PNG image
2.  `Config.py`  - Contains a Python dictionary composed of fractal
    configuration data
3.  `Mandelbrot.py`  - Given a coordinate in the complex plane, return the
    iteration count of the Mandelbrot function for that point
4.  `Julia.py`  - Given a coordinate in the complex plane, return the iteration
    count of the Julia function for that point
5.  `Gradient.py`  - Contains an array `G` containing `N` colors; when the
    Mandelbrot or Julia fractal function returns an iteration count of a point
    in the complex plane, the corresponding pixel is painted `G[count]`
6.  `ImagePainter.py`  - Creates a Tk window and a  `PhotoImage`  object; the
    `PhotoImage`  stores the pixels and is capable of creating a PNG image file

You may create other modules as you see fit. Reduce the number of import
statements in each module to the minimum absolutely required.  For example, all
image creation and display operations should take place in  `ImagePainter.py`.
Therefore, there is no need for `main.py` to import any identifiers from the
Tk package.

### Fractal Configuration Data

The two starter programs each contain a dictionary of dictionaries of
configuration information which describe a region of the complex plane over
which to paint pixels. You must extract this data from the driver program and
place it into the  `Config.py`  module. In order to distinguish Julia fractals
from Mandelbrot fractals you must add a new key:value pair to each dictionary:

-   `type: julia`
-   `type: mandelbrot`

#### Julia Fractal's complex parameter

Julia fractals take an extra complex number parameter traditionally named  `c`.
Presently,  `c`  is hard-coded into  `src/julia.py`  as  `complex(-1, 0)`. The
`c`  value may be represented within a fractal configuration dictionary like
so:

    creal: -1.0,  
    cimag: 0.0,

Being able to vary this parameter results in more interesting Julia set images.

Missing either of  `creal`  or  `cimag`  is an error when a fractal is  `type:
julia`, but their presence is ignored when  `type: mandelbrot`.


### Create a UML Class Diagram Describing Your Design

You are working alongside another DuckieCorp team who is creating a
user-friendly GUI for our client's program (_not really, just pretend with me
here_). It helps them immensely to understand how your code is organized.
Submit a UML class diagram describing the modules, their relationships, and the
key functions/methods/data members involved. You don't have to use classes in
your implementation in this stage, but be aware that they will required in the
next sprint. For purposes of this diagram, a Python module is equivalent to a
class.

Commit your UML diagram in your repository under the  `doc/`  directory. If you
made your diagram on https://draw.io, uncheck the "Transparent Background"
option when you export your UML class diagram to a PNG image.


### Write a User's Manual Explaining How To Use Your Program

This won't be the final version of the program, nor is this command-line
interface be what will be delivered to our client. However, other
non-programmer employees will need to know how to use this program (Quality
Assurance testers, Client Services, Sales Demo Technicians, Technical Support)
so there is a need for well-written instructions. It should not be long;
between 1 or 2 kilobytes of plain text is plenty, no larger than 4 kilobytes,
please.

Commit your user's manual in your repository as a file named  `doc/manual.md`.


## Penalties

Review the [Course Rules](https://gitlab.cs.usu.edu/erik.falor/fa19-cs1440-lecturenotes/blob/master/Course_Rules.md)
document to avoid general penalties which apply to all assignments.  In
particular, don't forget to include the *software development plan* and *sprint
signature* documents.

# CS 1440 Assignment 4 Rubric

| Points | Criteria
|:------:|--------------------------------------------------------------------------------
|10 pts  | code smells: dead code<br/>unused functions, function parameters and variables are removed. unreachable lines of code are not present. useless variable assignments are cleaned up.
|10 pts  | Code Smells: Responsibility<br/> Each function has one responsibility. The single responsibility principle is extended to modules you create.
|10 pts  | Code Smells: Confusing Constructs<br/> Magic numbers are replaced with well-named variables. Poorly-named variables are improved. Irrelevant comments are removed or re-written. Global variables are not found in your submission.
|10 pts  | One driver program replaces two prototype programs<br/> main.py is a short, simple program which parses user's command-line input and calls upon other modules to do the rest of the work
|15 pts  | Six required modules are present, each covering its area of responsibility<br/> Each module has a clear responsibility. Modules do not import from more libraries than necessary. Modules provide a simple interface by which other modules may use their services
|15 pts  | Two fractal modules, each embodying its own algorithm<br/> The Mandelbrot set algorithm resides in its own module, separate from the logic which controls pixels and their colors. Likewise for the Julia set algorithm.
|10 pts  | Create a UML Class Diagram Describing Your Design<br/> All modules/classes are represented, behaviors and data members are accounted for, and relationships between modules are described.
|10 pts  | Write a User's Manual<br/> Explain how to run your program without delving into unnecessary detail of how the program works.

**Total points: 90**

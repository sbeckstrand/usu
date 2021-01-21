# CS 1440 Assignment 4 Hints

*   Carefully study the Program Requirements Specification and the starter code.

*   Isolate the relevant fractal iteration count function from each starter
    program and place them each into their own module.


*   The driver program will choose which fractal function to run based upon the
    name of a configuration dictionary presented at the command-line.


*   A good driver program (or `main()` function, if your language has them) is
    brief. It will read like a table of contents, giving a brief summary of the
    key responsibilities of the program.


* Each function should deal with as few concepts as possible

    * When you find variables that are unused by a function, remove them

    * When you find lines of code that have no effect, remove them

    * The iteration count functions which define the fractals should not be
      concerned about which color a particular pixel will be; that combines two
      different responsibilities in a way that is not helpful. Simply return
      the count of iterations reached before the algorithm decided whether that
      a point in or out of the set.


* Use git to your advantage while refactoring

    * Make many small commits frequently

    * Test your changes before making a commit

    * Discard your changes when you make a mistake


* An image of the Mandelbrot set is a visualization of the complex plane, formed
  by relating complex numbers to Cartesian coordinates

    * The coordinates of pixels in the `PhotoImage` object are pairs of X, Y coordinates.

    * Complex numbers are ordered pairs of Real and Imaginary components.

    * The X axis of the Mandelbrot set represents the real number line.

    * The Y axis of the Mandelbrot set represents the imaginary number line.


* Do not worry if you do not understand how the fractal is generated or why the
  formula works.
  
    * Remember that the point of this assignment is to learn how to refactor a
      program that you don't entirely understand.

    * You do not need to deeply understand how the pixel coloring algorithm
      works in order to complete this assignment.  You may assume that it works
      correctly, though you will need to change parts of it in the course of
      the assignment.
      
    * Begin by identifying the inputs and outputs to the pixel coloring algorithm

    * Just because the function embodying this algorithm has the word **color**
      in its name doesn't mean that it should be dealing with colors at all

    * The signature of this function isn't set in stone.  If it makes sense to
      you to change it, do so.


* If you are *really* dying to know more about how it all works, the points
  *inside* the cardioid are the points *within* the Mandelbrot set.  These are
  the coordinates on the imaginary plane corresponding to `Z` values whose
  absolute value fails to become greater than `2.0` after repeated iterations.

    * [UsefulJS] (http://usefuljs.net/fractals/docs/julia_mandelbrot.html) has
      a nice explanation of how the Mandelbrot set works and how the Julia set
      is related to it.


* You can find ideas for new fractal configurations by exploring the Mandelbrot
  and Julia sets online.  You can also compare your program's output with other
  Mandelbrot and Julia set visualizers to make sure that you haven't made any
  serious mistakes.

    * https://atopon.org/mandel/

    * https://sciencedemos.org.uk/mandelbrot.php (note: this program produces
      images which are upside down relative to our program)

    * http://bl.ocks.org/syntagmatic/3736720

    * http://usefuljs.net/fractals/

    * Most of these websites define their images in `(minX, minY), (maxX,
      maxY)` coordinates, while our program uses the `(centerX, centerY) +
      axisLength` scheme.  It is helpful to write a small helper program which
      converts between the coordinate formats.
  

## Other fun links

* FractInt: A classic MS-DOS program (which is *still* under development!)
  whose users have made interesting discoveries within the Mandelbrot set and
  other related fractals over the years
    * [FractInt Homepage](https://www.fractint.org/)

* GNU XaoS: A free and open source fractal explorer for Linux, Windows and Mac
    * [GNU XaoS Homepage](http://matek.hu/xaos/doku.php)

* Eyecandy: Turn your computer into an expensive lava lamp.  Contains links to
  other programs you can use to explore fractals and other interesting
  patterns of pixels.
    * [Eyecandy Homepage](http://eyecandyarchive.com/)

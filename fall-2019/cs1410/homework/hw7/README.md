### Homework 7: Conway's Game of Life
Due: November 6, 2019

The purpose of this assignment is to give you more experience in developing Java classes, taking advantage of inheritance and polymorphism through inheritance.  The subject you'll be using for this assignment is Conway's Game of Life (wiki (Links to an external site.)).  You'll write a simulator that updates the state of a word according to a set of rules and then render that state to the console, animating it over time.  I think you'll enjoy this, it is fun to play around with once it is up and going.

Write a program that provides an interesting animation using Conway's Game of Life rules and using at least the required patterns indicated below.

Required Patterns
- Acorn
- Block
- Blinker
- Glider

All of these patterns are shown in the wiki: link (Links to an external site.)

All patterns must be derived from the following class.

```java
public abstract class Pattern {
    public abstract int getSizeX();
    public abstract int getSizeY();
    public abstract boolean getCell(int x, int y);
}
```

`getSizeX` - Returns the horizontal width (in cells) of the pattern.

`getSizeY` - Returns the vertical height (in cells) of the pattern.

`getCell` - Returns true if the cell in the pattern is filled, false otherwise.
The derived classes may add additional private fields and methods, but no changes to the public interface allowed.

#### Simulator
Create a simulator class, named LifeSimulator, that can be initialized with some state (of patterns) and then update the game of life simulation.  Use the following as the (required) public interface for the class.

```java
public class LifeSimulator {
    public LifeSimulator(int sizeX, int sizeY) { ... }

    public int getSizeX() { ... }
    public int getSizeY() { ... }
    public boolean getCell(int x, int y) { ... }

    public void insertPattern(Pattern pattern, int startX, int startY) { ... }
    public void update() { ... }
};
```

The constructor accepts a sizeX and sizeY indicating the size of the world.  You will want to set these equal to the width/height of the console the program runs within.

`insertPattern` - Adds the pattern to the world, with the upper left corner beginning at startX and startY.

`update` - Performs a single step update of the world, following the four rules specified in the wiki article.

`getSizeX` & `getSizeY` - Return the size of the world.

`getCell` - Returns true if the world cell is alive, false otherwise.
You may add any private fields and methods you like, but can not modify the public interface in any way.

#### Renderer
Write a render method in the ConwaysLife class that takes a LifeSimulator and renders it to the console (described below).

```java
public static void render(LifeSimulator simulation, Screen screen, TextGraphics graphics) { ... }
```

It is best to only render cells that are alive.  In other words, loop through the world and when you find a cell that is alive, use `graphics.setCharacter(...)` to draw a character at a specific location in the console. If you draw all cells, dead and alive, the rendering is slower than necessary.

#### Console Rendering
The provided code framework utilizes a third-party library called Lanterna for conosle/text rendering (https://github.com/mabe02/lanterna/blob/master/docs/contents.md (Links to an external site.)).  The code has a basic demonstration for how to use it to render text to any location on the console.  The only method you'll really need to utilize is graphics.setCharacter, as noted above, but you can look at the provided code to see how it is all working together.

#### Animation
Your program will run for some number of steps for the animation for your demonstration.  You can use a simple counted for loop for this, but you'll want to pause for a little bit between each animation step.  You can use a line of code like the following to pause for some number of milliseconds...

`Thread.sleep(50);`` // This will sleep for 50 milliseconds

To add a pattern to your simulator, for your animation demonstration, you'll have code something along these lines...

```java
LifeSimulator mySim = new LifeSimulator(...);
mySim.insertPattern(new PatternBlock(), 0, 0);
mySim.insertPattern(new PatternBlinker(), 0, 10);
mySim.insertPattern(new PatternGlider(), 15, 15);
```

Then go into an animation loop or several loops.  You can have multiple loops, with each demonstrating something different.  You can insert patterns during the animation, etc.  Don't feel limited to only adding patterns at the start and letting it go from there, this is your chance to be creative and have fun.

Be sure to look at the code framework I've provided, it gives a good guideline for how to update and render the simulator state.

#### Submission Notes
Use the following filenames for your code.  If you have additional code files due to other patterns, that is fine, but please following the same naming convention for the files and class names (consistency is king!).

Start with the following code framework for your project: link
Submit all of these files (and any others you create) in a single zip file.  Go inside the /src folder and make a .zip file of everything in there, but not including the /com folder.
```
ConwaysLife.java
LifeSimulator.java
Pattern Files
Pattern.java
PatternAcorn.java
PatternBlinker.java
PatternBlock.java
PatternGlider.java
```

There are no unit tests associated with this assignment.

Your code must compile without any warnings or compiler errors.  See syllabus regarding code that has compiler errors.

Your code must adhere to the CS 1410 coding standard

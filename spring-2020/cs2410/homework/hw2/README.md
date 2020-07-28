### Homework 2 - Polymorphism and Hello World JavaFX GUI
Due: Friday, January 31st 11:59pm

Completed: No

#### Programming Exercise 1 - Polymorphism

Create a parent class Shape.  Create four children classes: Square, Cube, Equilateral Triangle, and Pyramid.  The Pyramid class represents a 4 sided pyramid with a equilateral triangle base, not a 5 sided pyramid with a square base.

- Each child class will have a private double side, with getters and setters.  

- Each child class should also have a method called getArea() which will calculate the area of the shape.  For 3D shapes getArea() should return the surface area.



Create a test class TestShape.  Demonstrate polymorphism by creating an ArrayList of Shapes, and fill the ArrayList with child objects.  The ArrayList should contain at least 8 objects (at least 2 of each child). In a loop, call the getArea() method for each object in the ArrayList.

#### Programming Exercise 2 - JavaFX

Write a Hello World JavaFX GUI program.   

- Your class must extend Application and implement a start method().  

- Your class should have 4 layers; a stage, a scene, a pane, and text.

- Hello and World should be in different colors.



|Criteria  |Points   |
|---|---|
| Program(s) fulfill all the requirements.  All .java files are included and declare the necessary classes.  Code is well organized, and easy to follow (especially for the grader).  Coding stye is well utilized, including well named variables and methods.  Comments are included, well written and descriptive.   | 100%   |
|Program(s) fulfill almost all of the requirements.  All .java files are included and declare the necessary classes.  Code is fairly well organized, and somewhat easy to follow (especially for the grader).  Some comments are included.   | 80%  |
| Program(s) fulfill most of the requirements.  All .java files are included and declare the necessary classes.  Code is fairly well organized, and somewhat easy to follow (especially for the grader).  Some or no comments are included.  | 60%  |
| Program(s) fulfills some of the requirements, or does not run at all.  Some .java files are included and declare some of the necessary classes.  Some or no comments are included.  | 40%  |
| Program(s) does not run at all.  Some .java files are included and declare some of the necessary classes.  Some or no comments are included.  | 20%  |
| Either no attempt was made, or the attempt made shows no progress toward solving the problem.  | 0%  |


### Sprint Signature

| Progress | Date |
|--|--|
| Completed part one of assignment. | January 27, 2020 |
| Setup repository to allow for better tracking of progress and for later reference | January 29, 2020 |


### Resources

| Title/Link | Description of use |
|--|--|
| [Rechner Online: Regular Triangular Pyramid Calculator](https://rechneronline.de/pi/triangular-pyramid.php)| Used to determine the formula used to find surface area of 4 sided pyramid |
| [Stack Overflow: Creating an Arraylist of Objects](https://stackoverflow.com/questions/3982550/creating-an-arraylist-of-objects/3982597) | Used to determine how to create a proper array list of objects to perform a for each loop on. My concern was creating an array of objects specifying the parent class type and not being able to use methods specific to the children classes |

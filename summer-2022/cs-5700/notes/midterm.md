# Midterm Review

## Pitfalls

### Inappropriate Itimacy

Description: One class uses the internal fields and moethods of another class

Consequencies: 
* Low maintainability
* Lower Reusability
* Lower Extensibility

Causes:
* Poor localization of design decisions
* Poor Encapsulation

Easy to avoid. Hard to fix. Requires a lot of code fixes

### Feature Envy

Description: A method access the data of another object more than its own data

Avoidance: 
* Localize Behaviors close to the data they operate on
* Use the tighten possible encapsulation for properties and methods



## Paterns

### Strategy pattern

Should be closest to whatever classes will use it. 

Should have interface (I've been creating them as abstract classes)

Lets you pick an algorithm at run time

Gives you a common vocabulary

Forces you to spend more time at design phase. 


### Command Pattern

Often use for 'undo' features

Helps to avoid using 'null'

Client (View Helper) uses commands. 

---

- Programming to an interface vs implementation 
- 




### Principals

Be able to look at diagrams and review them. 

### St

Observer pattern - You dont need to change the subject to 
Observer pattern - One subject has several observers


Conceptual Modeling - Why do we do UML?
* To visual/conceptulize
* Solve problems beforhand
* To avoid pitfalls
* Identify scope of the system
* Helps you better undrestand the problem


Composition over inheritence
* Composing is more adaptable/flexible

Open/Close principle
- Open for extension, closed for modification
- Apply it wherever possible
- Consequences
  - Loosely Coupled
  - Deals with Complexity


General Goal of Software Engineering - On time, quality, within budget



Pitfalls 
- Class Explosion
- 
# Module 1: Set Theory

## General Notes / Overview

We will not have any textbook but will instead review journal articles. 

Group dynamics are highly encouraged

---

## 0.1 Helping Undergraduates Learn to Read Mathematics

01/19/2020

We are taught to read at a young age and there is an expection to improve reading ability throughout all phases of school. Typically our reading ability is associated with literature and history but rarely is it expected that our ability to read mathematics will improve. 

Typically we rely most on lectures and discussions for learning and consider mathematic reading as supplimentary ("Fill in the gaps"). Because of this, there is a lack of proficiency in reading mathematics in undegraduate students. 

Note that it takes a great deal of practice to improve your abilility to read mathematics. 

###### Reading Theorems

Questions to consider while reading theorems: 

* What kind of theorem is this? (Examples below)
  * Classification of a type of object.
  * Equivalence of definitions
  * Implication between definitions
  * Proof of when a technique is justified
* What's the content of the theorem?
* Why are each of the hypotheses needed? 
  * When considering each hypotheses, consider what counter examples would be available if the hypotheses was not present. 
* How does this theorem relate to other theorems?
  * Does it strengthen another theorem we are arleady aware of or one that has already been proven? 
* What's the motivation for this theorem and what question does it answer?

When reading a proof, consider reading each step as a block on top of the previous step instead of being independent. Make sure each step is legal when considering the previous steps. 

After reading a theorem, you can ask yourself the following questions to help ensure you understand it: 

* Can I write a brief outline of the logic of the argument, explain why the proof is a proof?
* What mathematical raw materials are used in the proof? 
* What does the proof tell you about why the theorem holds? Consier the limitations and considtions necessary for the proof to hold. 
* Where is each hypotheses used in the proof?



###### Reading Definitions

Questions to consider while reading definitions:

* What does this definition apply to? (Ex: integers, matrices, sets, functions, some part of items, etc)

* How do we check that the definition is satisfied? 

* Does anything satisfy this definition? Is there a whole class of things wh ich I am already aware of that satisfy this definition? 

* What doesnt satisfy this definition?

* What properties do these objects have, that would motivate us to make this definition? 

* Is there a classification we are aware of for the things being defined? 

  



##### Thoughts for reflection

We had to write papers for math 1050 (College Algebra) and describing math concepts and definitions helped to get the material down. 

Relying on lecture materials and problems results in a reliance on others to learn and may limit your learning ability. 

## 1.1 General Background of Set Theory & Mathematical Logic

01/19/2020

**Syllogism** - basis of logic. It has a major and minor premise and a conclusion. 

Exercise: Come up with your own syllogism: 

* Major Premise: Glasses contain two lenses
* Minor Premise: Lenses are convex pieces of clear material designed to improve visibility
* Conclusion: Glasses are used to improve visibility of the wearer.

**Statement** - A meaningful declarative sentence that is true or false. 

A statement declares something.



###### Problem 1.1.1: 

Consider the following two statements and ask yourself if they are *logically* saying the same thing. 

"Good food isn't cheap. Cheap food isn't good".

Assuming that the same definition of 'good' and 'cheap' are being applied in both statements, both statements imply the same thing, though the context of the statements will differ in that one is considering good food, while the other is considering cheap food.  Certainly there is some subjection to the terms 'good' and 'cheap'. For example, one may consider something to be 'good' based on its taste, its nutritional value, the environmental impact to acquire the food, etc. Similarly with 'cheap', if just considering a cost of currency, cheap will vary depending on who you ask. However, as stated, as long as the definition of 'good' and 'cheap' is the same in both statements, they will mean the same thing. 

## 1.2 Mathematic Logic (Symbolic Logic)

01/20/2020

Syllogisms have two properties: Validity and Soudness

A Syllogism can be valid but not sound. If a syllogism is sound, it is also valid. 

A syllogism is **sound** if it is not only valid, but if its premise is also true. 

###### Problem 1.2.1:

Complete this syllogism: (Answer italicized)

* All fish can breath underwater
* Socrates was a fish
* *Socrates can breath under water*

In this example, we can see that it is Valid as the conclusion follows off our premise. However, we know it is not sound since Socrates was not a fish. (Also, can all fish breathe under water? I thought Whales had to get water above the surfaec and stored it. Technically they are breathing under water but with oxegen from above water :thinkingface:). In any case, valid, not sound. 

###### Problem 1.2.2

Explain why this syllogism is Valid: (Answer italicized)

* Everybody loves my baby
* My baby loves only me. 
* Therefore, I am my own baby. 

*The conclusion follows the logic of our premise. Concluding that I am my own baby does not contradict either premise that everybody loves my baby and that my baby loves only me. However, as we know you cannot be your own baby our conclusion would not be true, thus the syllogism is not sound.* 

###### Problem 1.2.3

Explain why this syllogism is valid: (Answer italicized)

* Everybody loves a lover
* Romeo Loves Juliet
* Therefore Trump Loves Obama

*The conclusion follows the logic of our premise as Obama has a wife and two daughters, so he is a lover. If everybody loves a love, then it is valid to say that Trump loves obama. However, the syllogism is not sound as not everbody loves a lover.*

## 1.3 Sets

A **Set** is a collection of objects. 

The primary concept or notion of sets is *membership*

A set can be a subset of another set if all items in the first setset are in the other set.

**Problem 1.3.1.**

*Let H be the set of all humans, and W be the set of all women. Is W a subset of H?* 

All women are humans, so every item in W would also be in H. So yes, W is a subset of H. 

**Problem 1.3.2**

*Using the ideas presented so far, how would you describe two sets are equal to one of each other?*

If two sets are equal to each other, they are subsets of each other. 

#### The Empty Set

*Example: Suppose the preisdent of a student club say, "All members with red hair wear berets." But also suppose that there are no members with red hair in the club. Is the president's statement to be considered true, false or neither?*

Since there are not any red haired club members, we would say the set of red haired people is an empty set. Because the answer is not fault, we know it is true. 

**Problem 1.3.3**

Given any property P about the empty set, should it be considered true, false or neither? The choice universally agreed apon by mathematicians is true. How might you defend your conclusion?

Every property of an empty set is considered to be true because the only way for it to be false is if there is an object that does not meet the condition(s) of a property and because its an empty set, there is no object that would fail to mee the condition(s)

##### If-Then

If-Then is a conditional statement in that if one thing occurs, there is a follow-up. Truthhood or Falsehood can result of an If-Then statement

*Example: If you earn an A in Math 3310, your teacher will buy you a Porche*

Possible outcomes: 

* You get an A, teacher buys you a porche: True
* You get an A, the teacher does not buy you a porche: False
* You do not get an A, but the teacher buys them a porche anyway: True (Teacher didnt say they wouldn't buy a Porche if you didnt get an A)
* You do not get an A, and the teacher does not buy you a Porche: True (Teacher didnt break their word, so we assume they kept it)

**Problem 1.3.4**

*Use the proceeding reasoning to explain why the empty set is a subset of every set*

If every element in an empty set possesses a property, then every item in the empty set would meet he propertie(s) of objects in a set, thus making the empty set a subset of every other set. 

## 1.4 Boolean Operations on Sets

**Union**: A set that combines the elements in two other sets

*Note: Elements in a set do not get duplicated*

Ex: A = {1, 2, 3}, B = {2, 3, 4} -> A U B = {1, 2, 3, 4}. You do not have two 2s and two 3s.

**Intersection**: A set of only the elements in the two intersecting sets. 

*Note: If two sets do not share any elements, their intersection is the empty set*

*Note: Empty set != 0*

**Compliment**: A set of items that are *not* shared with the original set. 

Compliments are noted with a line above object.

Ex: A = {1, 2, 3}, B = {2, 3, 4} -> A C B = {4}

**Difference**: A set that only includes elements that are in the original set and not the set we are taking the difference of. 

Ex: A = {1, 2, 3}, B = {2, 3, 4} -> A - B = {1}

Venn Diagrams are a great way to represent boolena operations 

## 1.5 Boolean Equations

A boolean equation in considered valid if it is true for any set. 

*Example:* *A ∪ B = A ∩ B is not always true. However, A ∪ B = B ∪ A is always true*

**Problem 1.5.1**

*A = (1, 2), B = (1, 3)*. What are the indices for A', A ∩ A', and A ∪ A'

A' = (3, 4)

A ∩ = () (Empty Set)

A ∪ A = (1, 2, 3, 4)

#### 1.5.5 Demorgan Law

(A ∪ B)' = A' ∩ B'

Consider you have a venn diagram including 4 overlaping sets. You will have 8 regions. 

**Problem 1.5.3**

*How many regions are created from four sets?*

2^n -> 2^4 = 16

## 1.6 The Size of a Set

Sets can be finite or infinte. 

**Problem 1.6.1**

*If a set A has exactly three elements, how many subsets are there?*

Empty set = 1 subset (The empty set)

{1}. = {1},  Empty set -> Two subsets

{1, 2} = {1, 2}, {1}, {2}, the empty set -> 4 subsets

{1, 2, 3} = {1, 2, 3}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, empty set -> 8 subset

**Problem 1.6.2**

*If a set A has exactly four elements, how many subsets are there?*

total number = 2^n



If you consider how many subsets exist without one of the items, half of them do not have the item and half of them do. 

Suppose a set as X elements, and suppose there are 2^n subsets (just suppose since we do not know for sure. ), let Y an element that is NOT in X. Consider the set {y}. Notice the set {y} is not a subset of X. Now build another set, A = X ∪ {y}. How many elements are in A? 

Well, X had n, and {y} = 1 element. A should have n + 1 elements. We know that if we take 



If you have a set with n objects {1, 2, 3, 4, 5, ... , n }

how many ways can you build a subset with w/ :
=======



0 elements: 1 (empty set) (n choose 0)

1 element: n (n choose 1)

2 elements: (n choose 2)

**Powerset** Set of all subsets

## 1.7 Infinite Sets

**Denumerable**: A set that has a 1-1 correspondance with N. Another way to think about it is 'Countably Infinite'



## 1.8 Sets and Vampires

**Problem 1.8.1** 

*Pretend two vampires, Vladamir and Countess Elizabeth Bathory, are playing a guessing game. The cuontess writes a positive integer on the inside of her coffin. Each day, Vlad gets one guess at the number. What would be a strategy for vlad to eventually guess the correct number the countess has written on the inside of her coffin?*

One option: let k be the number, Vlad starts with 1 and increases by 1 every day. 

**Problem 1.8.2**

*Same as last problem with with any integer*

Option: let k be the number. Vlad starts with 1 and alternates the positive and nagative value of an integer and incriments by one. Example: 1, -1, 2, -2, 3, -3. 

**Problem 1.8.3**

Countess writes TWO positive integers and vlad has to guess both numbers in the same day. 

One option: Create an array where the first number is the x axis and the second number is the y axis. Each cell has both numbers from their axis and move down the list. Example: (1, 1) -> (2, 1) -> (1, 2) -> (3, 1) -> (2, 2) -> (3, 1), etc

**Problem 1.8.4**

*Countess wrtes a fraction on the inside of her coffin instead of an integer).*

Hmm...

**Problem 1.8.5**

*Countess writes a set of numbers**

Example: {7, 12, 3, 8, 21}

**Problem 1.8.6**

*Countess writes or describes a set of positive integers but this time it can be an infinte set. The catch is that Vlad has an infinitely large sized book with the list of sets to guess. The Countess obtains a copy of this book. Is there a way for her to construct a set that is not in this book? 

## 1.9 Infinite Sets and Georg Cantor

*Theorem 1.9.1* (Cantors Theorem):

For any set A, the power set, is numerically larger than A. 

Continuing that theorem, the power set of a power set is larger than the original power set. 

Also, if A is an infinite set, its power set is a larger infinity. 

**Problem 1.9.1**

*Is the union of denumerable sets necessarily denumerable?*

Yes. 

**Problem 1.9.2**

*Is the set of all infinite sets of natural numbers denumerable?*

No. If Natural nubmers are infinite, the powerset of the set of nautral numbers is an even larger infinity where there is no longer a 1-1 relation to elements. 




## A Paper on Relations

A relation links objects and structures. It is considered a fundamental to mathematics. Relations are considered to fall under 6 categories. 

If A has n elements. There are n^2 relations from AxA

Note: Relation is a set!

Note: You can have <=, <, >, >= and = relations

### Equivalence Relations

Special collection of relations 

#### Reflexive relations
R is *reflexive* if ∀x ∈ A[xRx];

*Example:*

A = {2, 3, 4, 5, 6, 7}. 

AxA = {(2,2), (2,3), (2,4), ... , (7,7)}

A relationship is reflexive if x in A exists in the relationship in the form of (x, x)> in the above example, it does contain (2,2), (3,3), (4,4), (5,5), (6,6), and (7,7), so it is reflexive. 

#### Symmetric Relations
R is *symmetric* if ∀x, y ∈ A[xRy → yRx];

Basically this states that if you have (x, y) in the relationship set, (y, x) needs to be in the set too)

#### Transitive
R is *transitive* if ∀x,y,z ∈ A[(xRy∧yRz) → xRz];

Basically, this states that if your have (x, y) AND (y, z) in the relation set, it also needs to have (x,z) for it to be transitive.

### Descriptive Ordering Relations

#### Antisymmetric Relations

R is *antisymmetric if ∀x,y ∈ A[(xRy∧yRx) → x=y];

This relation suggests that if you have (x, y) and (y, z) in your relation, that x equals y. 

#### Asymmetric Relations

R is *asymmetric if ∀x, y ∈ A ∼ [xRy → yRx], or equivalently, ∀x, y ∈ A [xRy → y!Rx]

This relation suggests that if you have (x, y) in your relation, you do not have (y, x)

#### Irreflexive Relations\

R is irreflexive if ∀x ∈ A [x!Rx].∂

This relation suggests that for every x in A, your relation does not contain (x, x)

### Relation Theorems

**Theorem 1**: If R is asymmetric, then it is antisymmetric

**Theorem 2**: If R is asymmetric, then it is irreflexive

**Theorem 3**: If R is reflexive, then it is NOT irreflexive

**Theorem 4**: If R is antisymmetric and irreflexive, then it is asymmetric

**Theorem 5**: If R is irreflexive and transitive, then it is asymmetric.

**Theorem 6**: If R is symmetric and antisymmetric, then it is transitive.

**Theorem 7**: The only relation that is both symmetric and asymmetric is the empty relation. 

## 1.13 Injective, Surjective and Bijective

Think of functions as if they have qualities, attributes and even personalities. 

**Functions**: A releation between X and Y and is denoted as: 

f: X -> Y

### Injective

**Injective**: Multiple different values of x will not result in the same value of y 

If f(x) = f(y), x = y. 

Also worth noting that if x != y, f(x) != f(y)

### Surjective

**Surjective): For all y in the possible outcomes of Y, and there exists an x in the possible values of x such that f(x) = y. 

This means that there is a value that can be supplied into a function to achieve all possible values of y. 

Essentially, all possible values of Y can be achieved. A function is still surjective if multiple values of x have the same value of y. 

## Bijective

Bijective is when the function is both Injective and Surjective. Basically this means there is a one-to-one relation for every value of x to y. 


## Canto's THeorem.. Again

We previously discussed Cantor's theorem and used contradiction to prove it. This time around, we can use the knowledge of functions to prove it using construction. 

Note, for two sets to have the same cardinality, there must be a bijection between them. 

The idea of the construction proof is to show that  you can have an injection from a set to its power set, but there does not exist a surjection, which means there can be no bijection. 


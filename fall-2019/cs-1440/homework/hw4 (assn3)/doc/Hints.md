# CS 1440 Assignment 3 Hints

* Carefully study the Program Requirements Specification and the starter code.

* The Unit Tests should not be included in the Class diagram.

* An easy way to ensure that numeric user input is actually numeric is to use Python's `str.isnumeric()` string method.

* The `NumberSet` class prevents numbers from being re-used on the same card.  For instance, each card can only have the number `7` once.  But it's okay if every card in the deck has the number `7`.

* Each time I ask the program to print card #7 from the same deck, I should see the same numbers in the same positions.  When I generate a new deck, however, card #7 will likely look different.

* Don't re-write or simplify your unit tests just to make them pass.  When a test fails it is telling you important information about failures in your design.  Reconsider your design.

* Instructions for running your Unit Tests can be found in the lecture notes repository on [GitLab](https://gitlab.cs.usu.edu/erik.falor/fa19-cs1440-lecturenotes/blob/master/Module3)

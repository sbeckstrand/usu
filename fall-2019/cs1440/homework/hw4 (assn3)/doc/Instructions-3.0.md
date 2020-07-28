# CS 1440 Assignment 3.0 Instructions

[3.0: Bingo! Design](https://usu.instructure.com/courses/547414/assignments/2698427 "3.0: Bingo! Design")


**I have _specifically_  instructed the tutors in the Tutor Lab to  _not_  help you with the next portion of the assignment until you have completed your UML class diagram. Don't worry about starting on the code until you've first thought through your design. Many software projects have gone awry because the design step had been neglected. Don't become another statistic - think first, code after.**


## Instructions for Assignment 3.0

1.  Carefully study the Program Requirements Specification and the starter code
2.  Create a UML class diagram that describes the starter code
    *   Unit tests are *not* included in the class diagram
3.  Design new classes, methods and data members to satisfy the customer's
    requirements
4.  Add these new components to your UML class diagram
5.  Write your Software Development Plan in tandem with your UML class diagram.
    What you put into words in the plan should match your diagram.
6.  Create a first draft of the user's manual describing the expected
    interface.  The interface may change as you begin implementing the project,
    but you should strive to follow the design described by your manual.


## What to submit for Assignment 3.0

1. Save your UML class diagram as an image (PNG or PDF are preferred) in the
   `doc/` directory of your repository.  Verify that your image is legible by
   looking at it in your browser once it has been pushed to GitLab.  In the
   past, students have submitted images with black or transparent backgrounds
   which render the image unreadable.
2. Your software development plan in the `doc/` directory.
3. The first draft of the user's manual in the `doc/` directory.

As you implement your design in Python for Assignment 3.1 you will undoubtedly
encounter problems you hadn't foreseen.  Update your UML diagram, users manual,
and software design plan to match your code as your design changes. Later
amendments to these documents *are not counted against you* if you had
submitted them on-time for Assignment 3.0.


### Penalties for Assignment 3.0

Review the [Course Rules](https://gitlab.cs.usu.edu/erik.falor/fa19-cs1440-lecturenotes/blob/master/Course_Rules.md)
document to avoid general penalties which apply to all assignments.  In
particular, don't forget to include the *software development plan* and *sprint
signature* documents.  In addition, this assignment is subject to the following
penalty:

**15 point penalty**  if your diagram is unreadable.  Watch out for a
transparent background (in draw.io, click File -> Export as -> PNG..., then
make sure that the option "Transparent background" is left unchecked).  Make
sure that the background isn't black, as this obscures the lines connecting
classes to each other.  Make sure that the file size is large enough to make
the text legible, and that the colors of the diagram stand out in sharp
contrast to the background.


### Drawing a UML class diagram

You may draw your UML class diagram using any software you prefer.  A simple
tool that I recommend is [draw.io](https://www.draw.io/)

1.  Visit https://www.draw.io/
2.  Click "Create New Diagram"
3.  Select the "Basic" "Blank Diagram" - don't use one of the pre-defined UML templates
4.  Find the UML section in the accordion list on the left-hand side of the screen

There are multiple shapes available with names like `Class`, `Class 2`, `Class 5`,
etc.  Make sure that the classes appearing on your diagram have 3 sections as
described in our lectures.


## Preparation

In this assignment, you will design classes that work together to generate a
deck of Bingo cards.  Later, you will follow this design to create a working
program.

Bingo is a simple game where players try to mark all numbers in a row or column
or along one of the two diagonals of a Bingo card. The game and the rules of
the game of actually not important for this assignment.

All you need to know is that a Bingo card is an `N x N` grid of bingo numbers,
where `N` is the size of the card. The typical size is 5, but we'll allow card
to vary in size from 3 to 15. The numbers of cards come from a set of bingo
numbers that contains every number from `1` to `M`, where `M` is a user-specified
max between `2*N2` and `4*N2`. The number of cards in the deck in a user-specified
value between `3` and `10000`.

The starting project contains a text-based user interface that allows a user to
create a deck, print a card from the current deck, display the entire deck, or
save the entire deck to a file.  Unit tests are also provided, though at this
stage many tests fail as the program is incomplete.


## Program Requirements Specification

1.  Your solution must include at least one class, called `Deck.` This class
    must possess the following features and capabilities:
    -   Construct a deck object given the size of the cards, number of cards, and the maximum numbers in a bingo number set
    -   A method to print a specific card in the deck to the screen
    -   A method to print whole deck to the screen
    -   A method to print whole deck to a file of the user's choice
2.  If you don't change the method definitions already provided for you in
    `Deck.py`, you should not have to change `UserInterface.py`. You may add new
    methods and attributes to `Deck.py` as you see fit.
3.  Your solution may contain other classes besides `Deck`. In fact, it should!
    If you try to put all the functionality in the `Deck` class your solution
    will most likely have a poor design. Use the overview given above to
    identify other meaningful classes.
4.  A deck must contain the user-specified number of cards, within the range `[3, 10000]`.
5.  Every card is assigned a unique integer identifier.
6.  The deck should be able to retrieve a card given the identifier, so the
    user can print just that card to the screen.
7.  The bingo numbers on a card must be between 1 and `M`, where `M` is the
    user-specified maximum number in the bingo number set, within the range
    `[2*(N*2), 4*(N*2)]`.
8.  A card cannot contain duplicates of bingo numbers. Bingo numbers may be duplicated between different cards within the same deck.
9.  When a new deck is created the previous deck is lost
10. Bingo cards of odd size must feature a **FREE!** square in the center.
    Even-sized cards don't have a center square and, thus, no **FREE!** square.
11. Once generated a card's appearance does not change.  When a Deck has been
    created, a card that is displayed multiple times appears identical each
    time.
12. When a card is printed to the screen or to a file, it should look like the following example.

    Odd-sized card:

    Card #1
    +-----+-----+-----+-----+-----+
    | 35  | 61  | 32  |  9  | 25  |
    +-----+-----+-----+-----+-----+
    |  8  | 62  | 80  | 10  | 95  |
    +-----+-----+-----+-----+-----+
    | 40  | 81  |FREE!| 73  | 74  |
    +-----+-----+-----+-----+-----+
    | 19  | 33  | 65  |  3  | 89  |
    +-----+-----+-----+-----+-----+
    | 68  | 37  | 79  | 59  | 44  |
    +-----+-----+-----+-----+-----+

    Even-sized card:

    Card #2
    +-----+-----+-----+-----+
    |  2  | 28  | 38  | 56  |
    +-----+-----+-----+-----+
    | 39  | 16  | 30  | 60  |
    +-----+-----+-----+-----+
    | 40  | 50  | 63  | 48  |
    +-----+-----+-----+-----+
    |  1  | 61  |  5  | 49  |
    +-----+-----+-----+-----+

13. When printing all a deck's cards to the screen or to a file, you may simply
    print every card, one after the other.
14. Validate all user input.  Ensure that numeric input is entered as digits.
    Use appropriate messages to remind the user what input is expected and
    redisplay the prompt when invalid data is entered.
15. The program  *should not*  construct any cards if the user-supplied card
    size, number of cards, or maximum of Bingo numbers is invalid.


## Next steps

Once you have settled upon a design and have worked out all foreseeable
problems you may proceed to the [implementation phase](Instructions-3.1.md).

[3.1: Bingo! Implementation](https://usu.instructure.com/courses/547414/assignments/2698428 "3.1: Bingo! Implementation")

# 1. Requirements

Our application should present the user with a user interface that allows them to generate cards for the game Bingo.

Functionality of our application should include:
- Being able to create a deck with as many bingo cards as needed
- Being able to print individual cards or all cards in a Deck
- Save deck to a file for later review

# 2. Design

## Input

After user starts the application a UI will be generated and they will be prompted to create a deck or exit the program. If a deck is generated, they will be prompted with the option to generate a new card or exit back to main menu.

## Output
The application should output menus allowing user to create a deck, add cards, and take actions with specific decks. If menu options are selected to print a card or all cards, this should be output to the screen. If user selects to save a deck to a file, they should be prompted to provide a file name. The cards will not be returned as output.

## Impressions

After reading through the existing code, there is already a great base for functionality, though there are a few methods that are being referenced but have yet to be implimented:

- UserInterface
  - `__GetNumberInput()`
  - `__getStringInput()`

The following method is not being referenced yet but will need to be created:

- NumberSet
  - `__createNumberSet`

The following class and methods will need to be created to allow for testing:

- Testing (Class)
  - `testCard`
  - `testDeck`
  - `testMenu`
  - `testNumberSet`

# 3. Implementation

### Bingo.py
```
- Import UserInterface

- generate new ui object using UserInterface constructor
```

### UserInterface.py
```
- Import Deck, Menu

- Define UserInterface class
  - Define __init__() method/constructor
  - Define run() method
    - Print welcome message
    - Generate new Main Menu using Menu constructor
    - Add a menu item to your menu object to create a Deck
    - Print menu until user provides an invalid option or exits
  - Define __createDeck() method
    - Prompt user to specify the card size, max number on card and the number of cards.
    - Create a new deck using the details provided by user.
    - Display a deck menu related to Deck
  - Define __deckMenu() method
    - Create a new menu object
    - Add three menu items, one to print card to screen, one to display the whole deck on screen, and one to save the deck to a file
    - print menu until user provides an invalid option or exits
  - Define __printCard() method
    - call __getNumberInput method to have user provide the id for the card they want to print
    - If the card id is valid, print the card
  - Define __saveDeck() method
    - call __getStringInput() method to have user provide name of file to save deck to
    - If name is valid, create the file, save the content to it, close the file
    - print Done
  - Define __GetNumberInput() method
    - Takes a prompt and number of cards in deck as arguments.
    - Checks for a card with ID in range of 1 to total number of cards in deck
    - Returns Input ID
  - Define __getStringInput() method
    - Takes a prompt as Input
    - Prints prompt and requests Input for filename
```

### Menu.py

```
- Import MenuOption module
- Define Menu class
  - Define __init__ method/constructor
    - Takes a header as an argument
    - Set header variable to the string passed into the constructor
    - Set default value for optionsCount of object
    - Set default value for options list of object
  - Define addOptions method
    - Takes command and description as arguments
    - Check that a command is provided
    - If so, append it to our 'options' list
  - Define __isValidCommand() method
    - Takes the command as an arguments
    - Cheks to see if command equals "X" to exit application or if one of the options in our menu options list.
  - Define getOption method
    - Takes optionIndex as argument
    - Check that the index option is greater or equal to 0 and then check that it is less than our total number of options
      - If so, return that option
  - Define getHeader() method
    - Return the header of our object
  - Define getOptionsCount() method
    - Return the optionCount variable of our object
  - Define show()
    - Show our menu until the user provides a valid command/option

```
### MenuOption.py
```
- Define MenuOption() class
  - Define __init__() method / constructor
    - Takes command and description as arguments
    - Create variables equal to command and description
  - Define getCommand() method
    - Returns the command
  - Define getDescription() method
    - returns the description of menu option
```

### Deck.py
```
- Import sys, Card and NumberSet

- Define Deck() class
  - Define __init__ method / constructor
    - Takes cardSize, cardCount, and numberMax as arguments
    - Set variables for cardSize, cardCount, and numberMax
  - Define getCardCount() method
    - returns the card count
  - Define getCard() method
    - takes id for card as argument
    - if id falls in range of 0 - max number of cards:
      - return card at that ID
  - Define print() method
    - Takes id of card and fileName as arguments
    - Check if id is given
      - If so, print that cards
      - If not, print all cards in deck
  -

```
### Card.py
```
- Import sys and NumberSet
- Define Card() class
  - Define __init__() method / constructor
    - It takes the id, size and numberSet as arguments
    - Sets variables for id, size and numberSet
  - Define getId() method
    - return the id of that card
  - Define getSize() method
    - return size of that card
  - Define print() method
    - Takes filename as argument
    - if filename is provided
      - Print to file
      - Otherwise, print to screen
```
### NumberSet.py
```
- Import random
- Define NumberSet() class
  - Define __init__() method
    - Takes size as argument
    - Sets size as variable
    - Sets variable for numberSet list
    - Set variable for currentIndex
  - Define __getSize() method
    - returns size of set
  - Define get() method
    - Takes index as argument
    - Returns the number in our numberSet list at the provided index
  - Define randomize() method
    - Shuffle our numberSet list
  - Define getNext() method
    - Checks current index and returns the next Number in our numberSet list
    - If the end is reached, return None
```

# 4. Verification

### runTest.py
```
- Import unittest, testCard, testDeck, testMenu, TestNumberSet
- Loop through each of our test types and perform a unittest to confirm that they succeed.
```

This file should contain several different test units that will be used to confirm that our application is operating as intended.

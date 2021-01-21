# Bingo User Manual

### Description
This program is designed to assist with generating a deck of Bingo cards. When creating a deck it allows for specifying the size of the deck, the size of the bingo cards (3x3, 5x5, etc) and the size of the range of numbers used in the card.

### Usage

Application can be executed by running `Bingo.py` under the `src` directory. Here is an example of how this can be executed from the root directory of the application:

```
python src/Bingo.py
```

Upon launch you will be prompted with a menu to either create a new Deck or exit the application.

```
Welcome to the Bingo! Deck Generator


Main menu:
C - Create a new deck
X - Exit

Enter a Main command (C, X)
```

If you select the option to create a deck, you will be prompted to provide the size of the cards, the maximum number that can appear on cards and the number of cards.

```
Please enter a card size (3-15):
```

Note that the maximum number that can be assigned to a tile lies within the range (2 * <size of card>^2) and (4 * <size of card>^2)
```
Please enter enter the maximum number that can be assigned to card space (50-100):
```


```
Please enter the number of cards to be created in deck (3, 10000)
```

Once created, you will be given a menu specific to your deck with options to print out a single card, all cards or save the cards to a file.

```
Deck menu:
P - Print a card to the screen
D - Display the whole deck to the screen
S - Save the whole deck to a file
X - Exit

Enter a Deck command (P, D, S, X)
```

Exiting this menu will return to main menu.

For any feedback or suggestions, please feel free to reach out to stephen.beckstrand@aggiemail.usu.edu

For any issues with the program, please submit an issue via GibLab so it can be addressed.

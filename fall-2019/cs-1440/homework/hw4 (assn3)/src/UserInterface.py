import Deck
import Menu

# User Interface class generates a user interface object that acts as the container for our application. It is the primary interface that is used to build our menus, create a deck of cards, get input, etc.
class UserInterface():
    def __init__(self):
        self.__ui_cardSize = 0
        self.__ui_maxNumber = 0
        self.__ui_numberOfCards = 0

    # method used to run our user interface. it builds the initial menu giving the user the option to generate a deck of cards or exit the application
    def run(self):
        print("Welcome to the Bingo! Deck Generator\n")
        menu = Menu.Menu("Main")
        menu.addOption("C", "Create a new deck")

        keepGoing = True
        while keepGoing:
            command = menu.show()
            if command == "C":
                self.__createDeck()
            elif command == "X":
                keepGoing = False

    # This method is used to build our deck of cards and prompt user to provide valid values for our Deck. It then builds a deck menu and serves it to the screen
    def __createDeck(self):
        self.__ui_cardSize = self.__getNumberInput("Please enter a card size (3-15): ", 3, 15)

        self.__ui_possibleMin = (2 * (self.__ui_cardSize ** 2))
        self.__ui_possibleMax = (4 * (self.__ui_cardSize ** 2))
        self.__ui_maxNumber = self.__getNumberInput("Please enter enter the maximum number that can be assigned to card space (%d-%d): " % (self.__ui_possibleMin, self.__ui_possibleMax), self.__ui_possibleMin, self.__ui_possibleMax)
        self.__ui_numberOfCards = self.__getNumberInput("Please enter the number of cards to be created in deck (3, 10000): ", 3, 10000)

        self.__m_currentDeck = Deck.Deck(self.__ui_cardSize, self.__ui_numberOfCards, self.__ui_maxNumber)
        self.__deckMenu()
        pass

    # Method used to define our menu specific to our Deck.
    def __deckMenu(self):
        menu = Menu.Menu("Deck")
        menu.addOption("P", "Print a card to the screen")
        menu.addOption("D", "Display the whole deck to the screen")
        menu.addOption("S", "Save the whole deck to a file")

        keepGoing = True
        while keepGoing:
            command = menu.show()
            if command == "P":
                self.__printCard()
            elif command == "D":
                print()
                self.__m_currentDeck.print()
            elif command == "S":
                self.__saveDeck()
            elif command == "X":
                keepGoing = False

    # Method to print a single card to screen
    def __printCard(self):
        cardToPrint = self.__getNumberInput("Id of card to print (%d - %d): " % (1, self.__m_currentDeck.getCardCount()), 1, self.__m_currentDeck.getCardCount())
        if cardToPrint > 0:
            self.__m_currentDeck.print(idx=cardToPrint)

    # Method to save our deck of cards to a file
    def __saveDeck(self):
        fileName = self.__getStringInput("Enter output file name: ")
        if fileName != "":
            outputStream = open(fileName, 'w')
            self.__m_currentDeck.print(outputStream)
            outputStream.close()
            print("Done!")
        else:
            print("Sorry, that is not a valid file name")

    # This method is used any time we need to get an integer input value from user.
    def __getNumberInput(self, prompt, min, max):
        while True:
            output = input(prompt)
            invalidString = "Please enter a valid number in range %d - %d" % (min, max)
            try:
                output = int(output)
                if (output < min) or (output > max):
                    print(invalidString)
                    continue
                else:
                    break
            except(ValueError):
                print(invalidString)
                continue


        return output

    # This method is used any time we need to get a string input value from user.
    def __getStringInput(self, prompt):
        fileName = input(prompt)
        return fileName

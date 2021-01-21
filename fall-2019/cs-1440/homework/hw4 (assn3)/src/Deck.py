import sys

import Card
import NumberSet

# Class used to build the deck object. This class does not call for any input directly, as that is all done in our user interface. Instead, it calls on the Card class several times to build a deck of cards equal to the number and values provided by the user.
class Deck():
    def __init__(self, cardSize, cardCount, numberMax):
        self.__d_cardSize = cardSize
        self.__d_cardCount = cardCount
        self.__d_cards = []

        for i in range (1, self.__d_cardCount + 1):
            # Generate numberSet
            numberSet = NumberSet.NumberSet(numberMax)

            # Generate cards
            newCard = Card.Card(i, cardSize, numberSet)
            self.__d_cards.append(newCard)


    # This method is used to get the total number of cards in our deck
    def getCardCount(self):
        return self.__d_cardCount


    # This method is used to get a single card object from our deck based on a provided index value
    def getCard(self, n):
        card = None
        n -= 1
        if 0 <= n < self.getCardCount():
            card = self.__d_cards[n]
        return card

    # This method is used to print either a single card or all cards if no index value is provided.
    def print(self, file=sys.stdout, idx=None):
        if idx is None:
            for idx in range(1, self.__d_cardCount + 1):
                c = self.getCard(idx)
                c.print(file)
            print('', file=file)
        else:
            self.getCard(idx).print(file)

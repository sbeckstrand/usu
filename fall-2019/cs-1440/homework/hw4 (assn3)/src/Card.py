import sys

import NumberSet

# This class is used to build a single card object. It will be called on several times to build an index of cards in our deck.
class Card():
    def __init__(self, idnum, size, numberSet):
        self.__c_idnum = idnum
        self.__c_size = size
        self.__c_numberSet = numberSet

        self.__c_numberSet.randomize()

    # Returns ID of card
    def getId(self):
        return self.__c_idnum

    # Returns the size of our card
    def getSize(self):
        return self.__c_size

    # This method is used to print our card. If a file name is specified and provided as an argument we will print to it. Otherwise, it will print to the screen.
    def print(self, file=sys.stdout):

        if (len(str(self.__c_numberSet.getSize())) <= 4):
            width = 6
        else:
            width = int(len(str(self.__c_numberSet.getSize()))) + 2

        if (self.__c_size %2 != 0):
            free = True
        else:
            free = False

        print("Card %d" % self.__c_idnum, file=file)
        for i in range (0, self.__c_size):
            print("+" + ("-" * width), end="", file=file)
        print("+", file=file)
        for i in range (1, self.__c_size + 1):
            if (i == int(self.__c_size / 2) + 1):
                cycle = True
            else:
                cycle = False
            for j in range (1, self.__c_size + 1):
                if (free) and (cycle):
                    if (j == int(self.__c_size / 2) + 1):
                        value = "FREE"
                    else:
                        value = self.__c_numberSet.getNext()
                else:
                    value = self.__c_numberSet.getNext()

                print("|" + '{:^{width}}'.format(value, width=width), end="", file=file)

            print("|", file=file)
            for k in range (0, self.__c_size):
                print("+" + ("-" * width), end="", file=file)
            print("+", file=file)

        print("", file=file)

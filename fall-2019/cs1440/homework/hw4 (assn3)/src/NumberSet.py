import random

class NumberSet():
    def __init__(self, size):
        """NumberSet constructor"""
        self.__ns_size = size
        self.__ns_numberSet = []
        self.__ns_currentIndex = 0

        for i in range(1, size + 1):
            self.__ns_numberSet.append(i)

        self.__ns_size = len(self.__ns_numberSet)

    def getSize(self):
        """Return an integer: the size of the NumberSet"""
        return self.__ns_size


    def get(self, index):
        """Return an integer: get the number from this NumberSet at an index"""
        try:
            value = self.__ns_numberSet[index]
        except(IndexError):
            value = None
        return value


    def randomize(self):
        """void function: Shuffle this NumberSet"""
        random.shuffle(self.__ns_numberSet)


    def getNext(self):
        """Return an integer: when called repeatedly return successive values
        from the NumberSet until the end is reached, at which time 'None' is returned"""
        try:
            currentNumber = self.__ns_numberSet[self.__ns_currentIndex]
            self.__ns_currentIndex += 1
        except(IndexError):
            currentNumber = None

        return currentNumber

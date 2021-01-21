from Gradient import Gradient
import colour

class Greyscale(Gradient):
    def __init__(self, n):
        white = colour.Color("white")
        black = colour.Color("black")
        self.__gradient = list(white.range_to(black, n))

    def getColor(self, n):
        return self.__gradient[n]

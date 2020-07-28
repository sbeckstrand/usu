from Gradient import Gradient
import colour

class Warm(Gradient):
    def __init__(self, n):
        yellow = colour.Color("yellow")
        red = colour.Color("red")
        self.__gradient = list(yellow.range_to(red, n))

    def getColor(self, n):
        return self.__gradient[n]

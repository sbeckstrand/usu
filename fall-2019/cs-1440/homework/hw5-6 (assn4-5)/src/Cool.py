from Gradient import Gradient
import colour

class Cool(Gradient):
    def __init__(self, n):
        lightBlue = colour.Color("lightblue")
        purple = colour.Color("purple")
        self.__gradient = list(lightBlue.range_to(purple, n))

    def getColor(self, n):
        return self.__gradient[n]

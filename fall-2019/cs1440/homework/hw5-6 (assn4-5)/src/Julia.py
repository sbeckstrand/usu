from Fractal import Fractal

class Julia(Fractal):
    def __init__(self, config):
        self.__MAX_ITERATIONS = config.getData()['iterations']

    def count(self, c):
        """Return the index of the color of the current pixel within the Julia set
        in the gradient array"""
        z = complex(-1.0, 0.0)

        for i in range(self.__MAX_ITERATIONS):
            z = z * z + c
            if abs(z) > 2:
                return i
                z += z + c

        return self.__MAX_ITERATIONS -1 

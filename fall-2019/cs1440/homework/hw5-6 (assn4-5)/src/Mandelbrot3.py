from Fractal import Fractal

class Mandelbrot3(Fractal):
    def __init__(self, config):
        self.__MAX_ITERATIONS = config.getData()['iterations']

    def count(self, c):
        z = complex(0, 0)

        for i in range(self.__MAX_ITERATIONS):
            z = z **3 + c
            if abs(z) > 2:
                z = 2.0
                return i
        return self.__MAX_ITERATIONS - 1

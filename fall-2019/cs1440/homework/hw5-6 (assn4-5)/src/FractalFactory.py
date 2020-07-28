from Julia import Julia
from Mandelbrot import Mandelbrot
from Mandelbrot3 import Mandelbrot3

class FractalFactory():

    def makeFractal(self, file, config):

        if (file == "default"):
            print("FractalFactory: Creating default fractal")

        if (config.getType() == 'mandelbrot'):
            return Mandelbrot(config)
        elif (config.getType() == 'julia'):
            return Julia(config)
        elif (config.getType() == 'mandelbrot3'):
            return Mandelbrot3(config)
        else:
            raise NotImplementedError("Apologies, the %s algorithm is not implimented into this application yet. Please consider using a configuration using mandelbrot, julia or mandelbrot3." % (config.getType()))

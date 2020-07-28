import sys

from FractalFactory import FractalFactory
from GradientFactory import GradientFactory
from Config import Config
from ImagePainter import ImagePainter


def main():

    # Define our fractal, gradient and configuration
    fFactory = FractalFactory()
    gFactory = GradientFactory()
    painter = ImagePainter()

    if (len(sys.argv) > 1):
        config = Config(sys.argv[1])
    else:
        config = Config('default')

    # Check if a fractal configuration and gradient was provided. If not, use default.
    if len(sys.argv) < 2:
        fractal = fFactory.makeFractal('default', config)
        gradient = gFactory.makeGradient('default', config)
    elif len(sys.argv) < 3:
        fractal = fFactory.makeFractal(sys.argv[1], config)
        gradient = gFactory.makeGradient('default', config)
    elif (len(sys.argv) == 3):
        fractal = fFactory.makeFractal(sys.argv[1], config)
        gradient = gFactory.makeGradient(sys.argv[2], config)


    # Paint fractal to screen and save to .png file
    painter.paint(fractal, gradient, config)

main()

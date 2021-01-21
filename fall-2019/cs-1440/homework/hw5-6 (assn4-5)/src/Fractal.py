class Fractal:
    def __init__(self, config):
        raise NotImplementedError("Concrete subclass of Fractal must implement __init__")

    def count(self, c):
        raise NotImplementedError("Concrete subclass of Fractal must implement count() method")



    # Julia and Mandelbrot classes should inherit this class.


    # Count class
        # SHould take single complex number as argument
        # Should return integer showing how many iterations were tried before fractal formula grew larger than 2.0. If absolute value of 2.0 is never reached, return the number of iterations.
        # This is the defining characteristic of the Fractal object.

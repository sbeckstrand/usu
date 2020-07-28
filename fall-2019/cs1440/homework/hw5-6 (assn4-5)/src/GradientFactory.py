# Only place that Gradient derived classes need to be imported

# When no gradient is specified by user on the command line, a default gradient is used.

# If a non-existant gradient is called for, raise a 'Not Implimented Error'
    #     `raise NotImplementedError("Invalid gradient requested")`

from Cool import Cool
from Warm import Warm
from Greyscale import Greyscale

class GradientFactory():

    def makeGradient(self, name, config):
        availableNames = ['cool', 'warm', 'greyscale']

        if (name == "default"):
            print("GradientFactory: Creating default color gradient")
            return Warm(config.getData()['iterations'])
        elif (name.lower() == "cool"):
            return Cool(config.getData()['iterations'])
        elif (name.lower() == "warm"):
            return Warm(config.getData()['iterations'])
        elif (name.lower() == "greyscale"):
            return Greyscale(config.getData()['iterations'])
        else:
            raise NotImplementedError("Invalid gradient requested. Please ue 'cool', 'warm', 'greyscale'")


class Config():
    def __init__(self, file):
        self.__data = {}
        self.__default = {
            'type': 'mandelbrot',
            'centerx': -1.543577002,
            'centery': -0.000058690069,
            'axislength':  0.000051248888,
            'iterations': 100,
            'pixels': 512
            }

        if (file == 'default'):
            self.__data = self.__default
        else:
            config = {}
            f = open(file, 'r')
            for line in f:
                value = line.lower().split(":")
                if (len(value) > 1):
                    config[value[0]] = value[1].rstrip().replace(" ", "")
            f.close()
            try:
                config['pixels'] = int(config['pixels'])
                config['iterations'] = int(config['iterations'])
                config['centerx'] = float(config['centerx'])
                config['centery'] = float(config['centery'])
                config['axislength'] = float(config['axislength'])
            except:
                raise NotImplementedError("Incorrect format in fractal configuration file")


            self.__data = config

    def getData(self):
        return self.__data

    def getType(self):
        return self.__data['type']

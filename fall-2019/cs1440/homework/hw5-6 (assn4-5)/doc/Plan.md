# 1. Requirements

For this second stage of our application implementation we need to refactor our code again to utilize the benefits of abstract classes and polymorphism. Doing so will make our application more dynamic in that configuration files can be specified instead of using hard coded fractal configuration.



# 2. Design

## Input

Application will not prompt for any input. Instead, the user will provide any needed arguments via the command line. If command line arguments are not provided, default configurations will be used.

## Output

Output will be an image generated based on the configuration passed into the application. Once generated, a .png file will be created and a prompt will be provided stating that the file was created.


# 3. Implementation

### main.py
```
- Import needed modules
- Define main function
  - Define a fractal, gradient and configuration object based on passed in values.

  - paint the fractal to screen based on the objects defined
```

### Config.py
```
- Define Config Class
  - Define init method which is used to generate our configuration data.
    - If a configuation file is specified, our application will go through each line and confirm the data is valid and then add it to our configuration variable
  - define getData() method which is used to return our dictionary
  - define getType() method which is used to get the type value from our dictionary
```

### Fractal.py
```
- Define Fractal class
  - Template __init__ class that raises an error if called since user should not be able to instantiate the Gradient class directly
  - Template count() class which is used to go through fractal algorithm and return an integer
```

### Mandelbrot.py
```
- Import Fractal parent classes
- Define Mandelbrot Class which extends the Fractal classes
  - Define __init__() method which sets a variable for max iterations
  - define count method which is used to go through mandelbrot algorithm and return an integer
```

### Mandelbrot3.py
```
- Import Fractal parent classes
- Define Mandelbrot Class which extends the Fractal classes
  - Define __init__() method which sets a variable for max iterations
  - define count method which is used to go through mandelbrot3 algorithm and return an integer
```

### Julia.py
```
- Import Fractal parent classes
- Define Juilia Class which extends the Fractal classes
  - Define __init__() method which sets a variable for max iterations
  - define count method which is used to go through julia algorithm and return an integer


```

### FractalFactory.py
```
-- Import 3 concrete classes
-- Define FractalFatory class
  - def makeFractal method which will take the config from a config file and return a fractal object based on the type specified in the configuration.
```

### Gradient.py
```
- Define Parent/Abstract Gradient class
  - Template __init__ class that raises an error if called since user should not be able to instantiate the Gradient class directly
  - template getColor() class which is used to get a color from a gradient array. This method will also raise an error but is in place to ensure it is implemented in concrete classes.
```

### Cool.py
```
- import colour and Gradient parent classes
- define Cool Class which extends Gradient.
  - Define init method which creates a list of x number of colors between light Blue and purple.
  - Define getColor method which returns a color from gradient list at x position.
```

### Warm.py
```
- import colour and Gradient parent classes
- define Warm Class which extends Gradient.
  - Define init method which creates a list of x number of colors between yellow and red.
  - Define getColor method which returns a color from gradient list at x position.
```

###Greyscale.py
```
- import colour and Gradient parent classes
- define Greyscale Class which extends Gradient.
  - Define init method which creates a list of x number of colors between light white and black.
  - Define getColor method which returns a color from gradient list at x position.
```

### GradientFactory.py
```
- Import three concrete gradient classes
- define GradientFactory class
  - Define make gradient method which returns a gradient based on the configuration passed in.
```



### ImagePainter.py
```
- Import needed modules
- Define paint function
  - Create a variable that contains our gradient set, size of image, and type of fractal
  - Create a new window to paint to
  - Find our min and max x and y values
  - for each pixel, get the appropriate color of that pixel and paint it to our window
  - Once image is painted, write it to a file and print the file name
```



# 4. Verification

### Case 1: Mandelbrot

#### Input:
```
:>python3 src/main.py data/elephants.frac greyscale
Wrote image image.png
```

#### output
Image.png file and image printed to screen

#### Result: Pass

### case 2: mandelbrot3

#### Input:
```
:>python3 src/main.py data/mandelthree.frac warm
Wrote image image.png
```
#### output
Image.png file and image printed to screen

#### Result: Pass

### case 3: Julia

#### Input:
```
python3 src/main.py data/lakes.frac warm
Wrote image image.png
```
#### output
Image.png file and image printed to screen

#### Result: Pass

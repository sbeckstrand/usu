# Fractal User manual

### description
This program is designed to generate an image based on either a mandelbrot, julia or mandelbrot to the 3rd power based configuration. With the current implementation of this application, you can provide a path to a configuration file as an argument as well as specify a gradient set. If neither of these are specified, a default configuration is used.

After the image is generated, it will be saved to a .png file in the directory the application is run from.

### Usage

To launch the application you use the primary driver file src/main.py. When using the application you can specify the path to a configuration file and gradient set. Existing configurations can be found under the data/ directory. The available gradients are as follows:

```
cool

warm

greyscale

```


. If you run the application without specifying a configuration, an image will be generated using a default fractal configuration and using the 'warm' gradient.

Here is an example of how to generate an image using the 'leaf' configuration and the 'warm' gradient:

```
> python3 src/main.py data/leaf.frac warm
```

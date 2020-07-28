
from tkinter import Tk, Canvas, PhotoImage, mainloop
from Mandelbrot import Mandelbrot
from Julia import Julia

# Paint function used to create a new window. Loops through each pixel and finds its appropriate color
class ImagePainter():
    def paint(self, fractal, gradient, config):


        gradientSet = gradient
        size = config.getData()['pixels']
        type = config.getData()['type']
        window = Tk()
        img = PhotoImage(width=1024, height=1024)



        minx = config.getData()['centerx'] - (config.getData()['axislength'] / 2.0)
        maxx = config.getData()['centerx'] + (config.getData()['axislength'] / 2.0)
        miny = config.getData()['centery'] - (config.getData()['axislength'] / 2.0)
        maxy = config.getData()['centery'] + (config.getData()['axislength'] / 2.0)

        # Display the image on the screen
        canvas = Canvas(window, width=size, height=size, bg=gradient.getColor(0))
        canvas.pack()
        canvas.create_image((512, 512), image=img, state="normal")


        pixelsize = abs(maxx - minx) / size

        portion = int(size / 64)

        for row in range(size, 0, -1):
            for col in range(size):
                x = minx + col * pixelsize
                y = miny + row * pixelsize
                c = complex(x, y)
                color = gradient.getColor(fractal.count(c))
                img.put(color, (col, size - row))
            window.update()

        img.write("image.png")
        print("Wrote image image.png")
        mainloop()

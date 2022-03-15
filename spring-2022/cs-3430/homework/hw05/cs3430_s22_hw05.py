#!/usr/bin/python

#######################################################
# module: cs3430_s22_hw05.py
# description: CS3430: S22: HW05: Detectiong of edges in PIL images.
# Stephen Beckstrand
# A02311346
########################################################

import math
from PIL import Image
import numpy as np

def lumin(rgb, rcoeff=0.2126, gcoeff=0.7152, bcoeff=0.0722):
    """
    Convert rgb pixel to grayscale value.
    """
    return rcoeff*rgb[0]+gcoeff*rgb[1]+bcoeff*rgb[2]

def is_in_pil_range(pil_img, cr):
    """
    Check if 2-tuple cr references to a legal pixel in a PIl image pil_img
    """
    ncols, nrows = pil_img.size
    c, r = cr
    #print('ncols={}; nrows={}'.format(ncols, nrows))
    #print('c={}; r={}'.format(c, r))
    return c > 0 and c < ncols-1 and r > 0 and r < nrows-1

def display_pil_img_row(pil_img, r):
    """
    Prints pixel values in row r in a PIL image pil_img.
    Useful for debugging.
    """
    ncols, _ = pil_img.size
    for c in range(ncols):
        print(pil_img.getpixel((c, r)))

def display_pil_img_col(pil_img, c):
    """
    Prints pixel values in column c in a PIL image pil_img.
    Useful for debugging.
    """
    _, nrows = pil_img.size
    for r in range(nrows):
        print(pil_img.getpixel((c, r)))

## Remember: in PIL images, c = x, r = y
def pil_pix_dxdy(pil_img, cr, default_delta):
    """
    Returns dx, dy values for pixel (c, r) in PIL image pil_img.
    If the luminosity values of the horizontal neighbors are the same,
    dx = default_delta.
    If the luminosity values of the vertical neighbors are the same,
    dy = default_delta.
    """
    assert is_in_pil_range(pil_img, cr)
    c,r = cr
    
    dx = lumin(pil_img.getpixel((c, int(r) - 1))) - lumin(pil_img.getpixel((c, int(r) + 1)))
    dy = lumin(pil_img.getpixel((c + 1, r))) - lumin(pil_img.getpixel((c - 1, r)))

    if dx == 0:
        dx = default_delta
    
    if dy == 0:
        dy = default_delta

    return (dx, dy)

    
def grd_magn(dx, dy):
    """
    Gradient magnitude given dx and dy.
    """
    gmag = math.sqrt(math.pow(dy, 2) + math.pow(dx, 2))

    return int(gmag)

def grd_deg_theta(dx, dy):
    """
    Gradient orientation (in degrees) given dx and dy.
    """

    gtheta = math.degrees(math.atan( dy / dx))

    return gtheta

def depil(pil_img, default_delta=1.0, magn_thresh=20):
    """
    - detects edges in a PIL image pil_img.
    - returns a new binary PIL image where the pixel
    value 255 means that it's a edge pixel and 0 means
    that it's not an edge pixel.
    - default_delta is used in calls to pil_pix_dxdy
    - magn_thresh is a gradient magnitude threshold, i.e.,
    if the computed value is >= magn_thresh, the pixel
    is an edge pixel; otherwise, it's not.
    """
    output_img = Image.new('L', pil_img.size)
    num_cols, num_rows = pil_img.size

    for col in range (1, num_cols - 1):
        for row in range(1, num_rows - 1):
            p_dx, p_dy = pil_pix_dxdy(pil_img, (col, row), 1)
            p_gmag = grd_magn(p_dx, p_dy)

            output_img.putpixel((col, row), p_gmag)
    # your code here
    return output_img




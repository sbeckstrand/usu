###################################
## module: cs3430_s22_midterm02.py
## Stephen Beckstrand  
## A02311346
#####################################

from datetime import datetime
import math
import numpy as np
import matplotlib.pyplot as plt


## YOUR IMPORTS
from tof import tof
from poly_parser import poly_parser
from drv import drv
from nra import nra
from cdd import cdd
from rmb import rmb
from datetime import datetime

## ========= Problem 1 ========================

def lambdify(s):
    ## your code here
    newFunc = tof.tof(poly_parser.parse_sum(s))
    return newFunc

## ========== Problem 2 ========================

def diff(s):

    fr = poly_parser.parse_sum(s)
    diff = tof.tof(drv.drv(fr))
    
    return diff


## ========== Problem 3 ========================

def nra_approx(s, x, num_iters=5):
    
    nra_out = nra.zr1(s, x, num_iters)
    return nra_out

## ========== Problem 4 ========================

def cdd_drv1_ord2(f, x, h):
    ord2_cdd = cdd.drv1_ord2(f, x, h)
    return ord2_cdd

def cdd_drv1_ord4(f, x, h):
    ord4_cdd = cdd.drv1_ord4(f, x, h)
    return ord4_cdd

def cdd_drv2_ord2(f, x, h):
    ord2_cdd2 = cdd.drv2_ord2(f, x, h)
    return ord2_cdd2

def cdd_drv2_ord4(f, x, h):
    ord4_cdd2 = cdd.drv2_ord4(f, x, h)
    return ord4_cdd2

### ========= Problem 5 =========================

'''
R_(4,4) Lattice:

               R(4,4)                   -- Level 4
              /      \
          R(3,3)    R(4,3)              -- Level 3
         /      \  /      \
    R(2,2)     R(3,2)   R(4,2)          -- Level 2
    /    \     /    \   /    \
 T(1,1)   T(2,1)   T(3,1)   T(4,1)      -- Level 1

Level 1 Error:  Error = TV - AV where AV is more accurate with more segments 
                (n). Error for Trapezoidal rule is proportional 1/(n^2)
Level 2 Error: (R_(x,1) - R(1(x-1),1)) / 3
Level 3 Error: (R_(x,2) - R((x-1),2)) / 15
Level 4 Error: (R_(x,2) - R((x-1),2)) / 15
'''



## ========== Problem 6 ========================

def fourier_s_n(f, num_coeffs, rn, x_val):

    a_zero = (1 / math.pi) * rmb.rjl(f, -math.pi, math.pi, rn, rn)

    total = 0
    for i in range(1, num_coeffs):
        extended_cos_func = lambda x: f(x) * math.cos(x * i)
        extended_sin_func = lambda x: f(x) * math.sin(x * i)

        an = (1 / math.pi) * rmb.rjl(extended_cos_func, -math.pi, math.pi, rn, rn)
        bn = (1 / math.pi) * rmb.rjl(extended_sin_func, -math.pi, math.pi, rn, rn)

        total += (an * math.cos(i * x_val)) + (bn * math.sin(i * x_val))

    approx = (a_zero / 2) + total

    return approx

def plot_fourier_nth_partial_sum(f, fstr, num_points=10000, num_coeffs=3, rn=15):

    xvals  = np.linspace(-math.pi, math.pi, num_points)
    yvals1 = np.array([f(x) for x in xvals])
    yvals2 = np.array([fourier_s_n(f, num_coeffs, rn, x) for x in xvals])
    plt.xlabel('x')
    plt.ylabel('y')
    plt.ylim([-2, 2])
    plt.xlim([-math.pi, math.pi])
    plt.grid()
    plt.plot(xvals, yvals1, label=fstr, c='red')
    plt.plot(xvals, yvals2, label='y=nth_partial_sum', c='blue')
    plt.legend(loc='best')
    plt.show()

def plot_fourier_nth_partial_sum_error(f, fstr, num_points=10000, num_coeffs=3, rn=15):
    
    xvals  = np.linspace(-math.pi, math.pi, num_points)
    yvals1 = np.array([abs(f(x) - fourier_s_n(f, num_coeffs, rn, x)) for x in xvals])
    plt.xlabel('x')
    plt.ylabel('y')
    plt.ylim([-2, 2])
    plt.xlim([-math.pi, math.pi])
    plt.grid()
    plt.plot(xvals, yvals1, label="err", c='red')
    plt.legend(loc='best')
    plt.show()

### =============== Problem 7 =========================

"""
Problem 7 Solution:

a
---
The basic trigonometric system refers to the following system of functions:

1, cos(x), sin(x), cos(2x), sin(2x), ..., cos(nx), sin(nx)

These functions have a common period of 2(pi). Functions cos(nx) and sin(nx) however have a smaller period of 2(pi)/n

b
---
Two integrable functions on [a,b] are considered to be orthogonal if the integral from a -> b for f(x)d(x)dx = 0. 


"""

### =============== Problem 8 =========================

"""
2sin(3x + (pi/3)) -> A = 2, ω = 3, φ = pi/3

2sin(3x + (pi/3)) = 2[cos(3x) * sin(pi/3) + sin(3x)cos(pi/3)] = cos(3x) * 2sin(pi/3) + sin(3x) * 2cos(pi/3)

cos(3x) * 2sin(pi/3) + sin(3x) * 2cos(pi/3) = sqrt(3)cos(3x) + sin(3x)


"""



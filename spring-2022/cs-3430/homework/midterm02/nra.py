
#############################################
# Module: nra.py
# Description: Newton-Raphson Algorithm
#############################################

import numpy as np
import math
from maker import maker
from pwr import pwr
from prod import prod
from var import var
from const import const
from plus import plus
from drv import drv
from poly_parser import poly_parser
from tof import tof

class nra(object):

    @staticmethod
    def zr1(fstr, x0, num_iters=3):
        my_fun = tof.tof(poly_parser.parse_sum(fstr))
        diff_fun = tof.tof(drv.drv(poly_parser.parse_sum(fstr)))

        approx_zero = x0
        for i in range(0, num_iters):
            approx_zero -= my_fun(approx_zero) / diff_fun(approx_zero)

        return approx_zero


    @staticmethod
    def zr2(fstr, x0, delta=0.0001):
        iter_count = 0
        diff = delta + 1
        my_fun = tof.tof(poly_parser.parse_sum(fstr))
        diff_fun = tof.tof(drv.drv(poly_parser.parse_sum(fstr)))

        approx_zero = x0
        while diff > delta:
            prev = approx_zero
            approx_zero -= my_fun(approx_zero) / diff_fun(approx_zero)

            diff = abs(approx_zero - prev)
            iter_count += 1

        return approx_zero, iter_count

    @staticmethod
    def check_zr(fstr, zr, err=0.0001):
        return abs(tof.tof(poly_parser.parse_sum(fstr))(zr) - 0.0) <= err 

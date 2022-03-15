
####################################
# module: cdd.py
# Stephen Beckstrand
# YOUR A02311346
####################################

import numpy as np



class cdd(object):

    @staticmethod
    def drv1_ord2(f, x, h): 
        f_one = f(x + h)
        f_minus_one = f(x - h)

        result = (f_one - f_minus_one) / (2 * h)
        return np.longdouble(result)

    @staticmethod
    def drv1_ord4(f, x, h):
        f_one = f(x + h)
        f_minus_one = f(x - h)
        f_two = f(x + (2*h))
        f_minus_two = f(x - (2*h))

        result = (-f_two + (8 * f_one) - (8 *f_minus_one) + f_minus_two) / (12 * h)

        return np.longdouble(result)

    @staticmethod
    def drv2_ord2(f, x, h):
        f_one = f(x + h)
        f_minus_one = f(x - h)
        f_zero = f(x)

        result = (f_one - (2 * f_zero) + f_minus_one) / h ** 2

        return np.longdouble(result)

    @staticmethod
    def drv2_ord4(f, x, h):
        f_one = f(x + h)
        f_minus_one = f(x - h)
        f_zero = f(x)
        f_two = f(x + (2*h))
        f_minus_two = f(x - (2*h))

        result = (-f_two + (16 * f_one) - (30 * f_zero) + (16 * f_minus_one) - f_minus_two) / (12 * (h ** 2))

        return np.longdouble(result)

    

    
        
    

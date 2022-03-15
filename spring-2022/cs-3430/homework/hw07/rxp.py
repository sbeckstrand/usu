####################################
# module: rxp.py
# Stephen Beckstrand
# YOUR A02311346
####################################

import numpy as np

class rxp(object):

    @staticmethod
    def drv1(f, cdd, x, h):
        result = cdd(f, x, h)
        return result

    @staticmethod
    def drv2(f, cdd, x, h):
        x1 = rxp.drv1(f, cdd, x, h/2.0)
        x2 = rxp.drv1(f, cdd, x, h)
        return  np.longdouble(x1 + (x1 - x2)/3.0)

    
    

    

    

    
        
    

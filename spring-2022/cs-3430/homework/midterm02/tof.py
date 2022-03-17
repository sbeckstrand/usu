#!/usr/bin/python

#################################################
# module: tof.py
# bugs to vladimir kulyukin in canvas.
#################################################

from var import var
from pwr import pwr
from const import const
from plus import plus
from prod import prod
import math 

from poly_parser import poly_parser

class tof(object):

    # @staticmethod
    # def const_tof(fr):
    #     """convert a const object to Py function."""
    #     assert isinstance(fr, const)
        
    #     return lambda x: fr.get_val()

    # @staticmethod
    # def var_tof(fr):
    #     """convert a var object to Py function."""        
    #     assert isinstance(fr, var)
        
    #     return lambda x: x

    # @staticmethod
    # def prod_tof(fr):
    #     """convert a prod object to Py function."""                
    #     assert isinstance(fr, prod)

    #     const_part = fr.get_mult1()
    #     pow_part = fr.get_mult2()
        
    #     const_funct = tof.const_tof(const_part)
    #     pwr_func = tof.pwr_tof(pow_part)
    #     return lambda x: const_funct(x) * pwr_func(x)
    
    # @staticmethod
    # def plus_tof(fr):
    #     """convert a plus object to a Py function."""
    #     assert isinstance(fr, plus)
        
    #     sum1_func = tof.tof(fr.get_elt1())
    #     sum2_func = tof.tof(fr.get_elt2())

    #     return lambda x: sum1_func(x) + sum2_func(x)

    # @staticmethod
    # def pwr_tof(fr):
    #     """convert a pwr object to a Py function."""
    #     assert isinstance(fr, pwr)
    #     var_func = tof.var_tof(fr.get_base())
    #     const_pow = tof.const_tof(fr.get_deg())
    #     return lambda x: pow(var_func(x), const_pow(x))

    @staticmethod
    def const_tof(fr):
        """convert a const object to Py function."""
        assert isinstance(fr, const)
        
        return lambda x: fr.get_val()

    @staticmethod
    def var_tof(fr):
        """convert a var object to Py function."""        
        assert isinstance(fr, var)
        
        return lambda x: x

    @staticmethod
    def prod_tof(fr):
        """convert a prod object to Py function."""                
        assert isinstance(fr, prod)

        def f(x):

            p1 = tof.tof(fr.get_mult1())
            p2 = tof.tof(fr.get_mult2())
            return p1(x) * p2(x)
        
        return f
    
    @staticmethod
    def plus_tof(fr):
        """convert a plus object to a Py function."""
        assert isinstance(fr, plus)
        
        def f(x):
            sum1_func = tof.tof(fr.get_elt1())
            sum2_func = tof.tof(fr.get_elt2())
            return sum1_func(x) + sum2_func(x)
        return f

    @staticmethod
    def pwr_tof(fr):
        """convert a pwr object to a Py function."""
        assert isinstance(fr, pwr)
        def f(x):
            base = tof.tof(fr.get_base())
            degree = tof.tof(fr.get_deg())
            return base(x) ** degree(x)

        return f

    @staticmethod
    def tof(fr):
        """convert a const/var/prod/plus/pwr/ object to a Py function."""
        # if isinstance(fr, prod):
        #     func = tof.prod_tof(fr)
        #     return lambda x: func(x)
        
        # if isinstance(fr, plus):
        #     func = tof.plus_tof(fr)
        #     return lambda x: func(x)

        if isinstance(fr, const):
            func = tof.const_tof(fr)
            return lambda x: func(x)
        
        elif isinstance(fr, pwr):
            func = tof.pwr_tof(fr)
            return lambda x: func(x)

        elif isinstance(fr, prod):
            func = tof.prod_tof(fr)
            return lambda x: func(x)

        elif isinstance(fr, plus):
            func = tof.plus_tof(fr)
            return lambda x: func(x)

        elif isinstance(fr, var):
            func = tof.var_tof(fr)
            return lambda x: func(x)

        else:
            raise Exception('Not a valid type')



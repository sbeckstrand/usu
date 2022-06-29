#!/usr/bin/python
# -*- coding: utf-8 -*-

###########################################
# module: crt.py
# description: chinese remainder theorem
# YOUR NAME
# YOUR A#
# bugs to vladimir kulyukin in canvas
###########################################

from cs3430_s22_hw09 import xeuc, make_equiv_class_mod_n

def solve_congs(mvals, avals, num_sols=1):
        '''
        mvals is an array of m's: [m_0, m_1, ..., m_r]
        avals is an array of a's: [a_0, a_1, ..., a_r]
        mvals must be pairwise relatively prime positive integers;
        avals are any integers;
        the algorithm finds num_sols simultaneous solutions x to the
        r+1 congruences:
        x <> a_0 (mod m_0)
        x <> a_1 (mod m_1)
        x <> a_2 (mod m_2)
        ...
        x <> a_r (mod m_r) 
        The solutions returned start with the smallest positive
        representative of x0 modulo m_0 * m_1 * ... * m_r.
        '''
	
        # Step 1: Compute m
        total_m = 1
        
        for i in range(0, len(mvals)):
            total_m *= mvals[i]

        x_0 = 0
         
        for i in range(0, len(mvals)):
            m_div = total_m / mvals[i]
            a_j = avals[i]
            b_j = xeuc(m_div, mvals[i])[1]

            x_0 += m_div * a_j * b_j

        x_0 = x_0 % total_m

        results = []
        for i in range(0, num_sols):
            val = x_0 + (i * total_m)
            results.append(make_equiv_class_mod_n(val, total_m))

        return results


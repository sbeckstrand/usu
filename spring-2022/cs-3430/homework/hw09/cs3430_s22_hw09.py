#!/usr/bin/python
# -*- coding: utf-8 -*-

######################################
# module: cs3430_s22_hw09.py
# description: cs3430 s22 hw09
# YOUR NAME
# YOUR A#
# bugs to vladimir kulyukin in canvas
######################################

def make_equiv_class_mod_n(a, n):
    """
    make_equiv_class_mod_n(a, n) returns a generator object
    to generate [a]_n (i.e., the equivalence class of a modulo n).
    """
    assert n > 0
    def gen_equiv_class(k):
        kk = k
        while True:
            yield a + kk*n
            if kk == 0:
                kk += 1
            elif kk > 0:
                kk *= -1
            elif kk < 0:
                kk *= -1
                kk += 1
    return gen_equiv_class(0)

def xeuc(a,b):
    """
    extended euclid algo that returns g, x, y such 
    g = gcd(a,b) and g = ax + by.
    """
    
    prev_x, x = 1, 0
    prev_y, y = 0, 1

    aa, bb = a, b

    while bb != 0:
        q = aa // bb
        x, prev_x = prev_x - (q * x), x
        y, prev_y = prev_y - (q * y), y
        aa, bb = bb, aa % bb
    
    return aa, prev_x, prev_y

def mult_inv_mod_n(a, n):
    """
    compute the multiplicative inverse of a mod n.
    """
    
    for i in range (1, n):
        if (a * i) % n == 1:
            return make_equiv_class_mod_n(i, n) 

def solve_cong(a, b, m, tmax=10):
    """
    solves the congruence ax <> b (mod m);
    returns at most tmax equivalence classes.
    """
    g, u, v = xeuc(a, m)

    if b % g != 0:
        return None

    list_size = g;
    if list_size > tmax:
        list_size = tmax

    results = []
    for t in range(0, list_size):
        val = ((b / g) * u) + ((t * (m / g)) % m)
        results.append(make_equiv_class_mod_n(val, m))
    
    return results

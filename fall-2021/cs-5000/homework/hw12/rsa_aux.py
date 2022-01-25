#!/usr/bin/python
# -*- coding: utf-8 -*-

#############################################################
# module: rsa_aux.py
# description: auxiliay functions for RSA
# Stephen Beckstrand
# A02311346
##############################################################

import numpy as np
import math

### Assign 12, subproblem 1.1
def xgcd(a,b):
    ''' 
    extended gcd that returns d, x, y such that
    d = ax + by.
    '''
    ### your code here
    oldX, x = 1, 0
    oldY, y = 0, 1
    aa, bb = a, b
    while bb != 0:
        q = aa // bb
        x, oldX = oldX - q*x, x
        y, oldY = oldY - q*y, y
        aa, bb = bb, aa % bb
    
    return aa, oldX, oldY



### Assign 12, subproblem 1.2
def mult_inv(a, n):
    """
    multiplicative inverse of a in Z^{*}_{n}.
    """
    ### your code here
    x = 0
    for i in range(1,n):
        if (a * i) % n == 1:
            x = i;
    
    return x

    


### A tool you may want to use in your code.
### it's used in rsa_uts.py.
def z_star_sub_n(n):
    """
    compute the elements of Z^{*}_{n}.
    """
    return np.array([i for i in range(n) if xgcd(i, n) == 1])

### A tool you may want to use in your code (e.g., euler's totient)
def is_prime(n):
    if n < 2:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    if n > 2:
        for d in range(3, int(math.floor(math.sqrt(n)))+1, 2):
            if n % d == 0:
                return False
        return True

### A tool you may want to use in your tests (e.g., euler's totient)
def find_primes_in_range(a, b):
    return [i for i in range(a, b+1) if is_prime(i)]

### Assign 12, subproblem 1.3.
def mod_exp(a, b, n):
    """
    this function computes a^b mod n.
    """
    ### your code here
    binB = bin(b)[2:]
    d = 1
    for i in binB:
        d = (d * d) % n

        if int(i) == 1:
            d = (d * a) % n 
    
    return d



### Assign 12, subproblem 1.4
def euler_phi(n):
    """ Euler's Totient """
    ### your code here
    x = 1
    for i in range(2, n):
        
        if (math.gcd(i, n) == 1):
            x += 1
        # Could have used xgcd(i,n)[0] instead, but found math.gcd to be a bit faster when hacking rsa keys.
    return x



    

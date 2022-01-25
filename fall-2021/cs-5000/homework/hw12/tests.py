import unittest
import numpy as np
import random
import math
from rsa_aux import xgcd, mult_inv, z_star_sub_n, euler_phi
from rsa_aux import find_primes_in_range, mult_inv, mod_exp
from rsa import rsa
from hack_rsa import hack_rsa

class rsa_uts(unittest.TestCase):

    ### unit test for subproblem 1.1
    def test_xgcd(self, lwr=1, uppr=1000000, ntests=100000):
        for _ in range(ntests):
            a = random.randint(lwr, uppr)
            b = random.randint(lwr, uppr)
            g, x, y = xgcd(a, b)
            assert g == a*x + b*y
            gcd_bound = int(min(math.sqrt(a), math.sqrt(b)))
            for d in range(g+1, gcd_bound+1):
                assert not (a % d == 0 and b % d == 0)

    if __name__ == '__main__':
        unittest.main()
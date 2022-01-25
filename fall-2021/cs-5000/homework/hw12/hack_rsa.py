#!/usr/bin/python
# -*- coding: utf-8 -*-

#############################################################
# module: hack_rsa.py
# description: obtaining RSA's secrete key from messages,
# cryptotexts, and public keys.
# Stephen Beckstrand
# A02311346
#############################################################

import math
from rsa_aux import xgcd, mod_exp
from rsa_aux import mult_inv, euler_phi, is_prime
from rsa import rsa

class hack_rsa(object):

    ### Assign 12, subproblem 1.9
    @staticmethod
    def get_sec_key(message, cryptotext, pub_key):
        ### your code here
        d = mult_inv(pub_key[0], euler_phi(pub_key[1]))
        sec = (d, pub_key[1])

        return sec

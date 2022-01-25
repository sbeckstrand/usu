#!/usr/bin/python
# -*- coding: utf-8 -*-

#############################################################
# module: rsa.py
# description: RSA
# Stephen Beckstrand
# A02311346
##############################################################


import numpy as np
from rsa_aux import xgcd, mod_exp
from rsa_aux import mult_inv, euler_phi, is_prime
import math
import random

class rsa(object):

    ### Assign 12, subproblem 1.5
    @staticmethod
    def choose_e(eu_phi_n):
        ### your code here
        e = 1
        found = False
        while not found:
            e = e + 2
            if (xgcd(e,eu_phi_n)[0] == 1):
                found = True
        
        return e


    ### Assign 12, subproblem 1.6
    @staticmethod
    def generate_keys_from_pqe(p, q, e):
        ### your code here
        n = p * q
        d = mult_inv(e, euler_phi(n))
        pub = (e, n)
        sec = (d, n)

        return pub, sec

    ### Assign 12, subproblem 1.7    
    @staticmethod
    def encrypt(m, pk):
        ### your code here
        encryptedM = mod_exp(m, pk[0], pk[1])
        return encryptedM

    ### Assign 12, subproblem 1.7        
    @staticmethod
    def decrypt(c, sk):
        ### your code here
        decryptedC = mod_exp(c, sk[0], sk[1])
        return decryptedC

    ### Assign 12, subproblem 1.8
    @staticmethod
    def encrypt_text(text, pub_key):
        ### your code here
        cryptotexts = []
        for char in text:
            cryptotexts.append(rsa.encrypt(ord(char), pub_key))

        return cryptotexts

    ### Assign 12, subproblem 1.8        
    @staticmethod    
    def decrypt_cryptotexts(cryptotexts, sec_key):
        ### your code here
        text = ""
        for cryptotext in cryptotexts:
            text += chr(rsa.decrypt(cryptotext, sec_key))
        
        return text
    
    


    

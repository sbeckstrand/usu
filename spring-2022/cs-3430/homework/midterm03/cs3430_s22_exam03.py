
###################################
## module: cs3430_s22_exam03.py
## Stephen Beckstrand
## A02311346
#####################################

import math
import numpy as np
import random

from pyparsing import col

## YOUR IMPORTS
from cs3430_s22_hw09 import xeuc, solve_cong, make_equiv_class_mod_n
from crt import solve_congs
from HuffmanTree import HuffmanTree
from prng import prng
from BinHuffmanTree import BinHuffmanTree
from CharFreqMap import CharFreqMap
from bin_id3 import id3_node, bin_id3

## ========= Problem 1 ========================

'''
Type your solutions to Problem 1 as a multi-line
comment. Your solutions should be in the form of
equivalence class such as [1]_60, which means
the equivalence class of 1 modulo 60.


Admittedly, I am not sure how to solve this. It never bodes well when you cant even answer the first question on an exam. 

I know that an equivalence class modulo n is defined as [a]_n = {a + kn | k in {integers}}
I also know that for each value in [a]_n (Lets say [a]_n = S), that [S_0]_n, [S_1]_n, ... [S_{infinity}]_n are all equivalent, thus the name of equivalence classes. 

Given that we were provided remainders when divisors, my understanding is that we can determine a list of elements that wo uld exist to this equivalence class. However, I am not sure how to find the modulo from this list of numbers.
For example, if I undrestand the nature of the question correctly, all elements from this equivalence class will have a remainder of 7 when divided by 29, a remainder of 13 when dividied by 31, etc. 

As such, I took a brute force attempt to compile a list of elements that would exist to this set by generating a series of numbers that would have these remainders based on these divisors. 
i.e. : `if i % 29 == 7 and i % 31 == 13 and i % 37 == 17 and i % 53 == 6`

This compiled list looks something like this: 
1057144
2820083
4583022
6345961
8108900
9871839
11634778
13397717
15160656
16923595
18686534
20449473
22212412
23975351
25738290
27501229

Unfortunately, I am not sure how you would determine the modulo from this list or if this is even the correct approach or if this is just a brute force approach. 
'''

## ========== Problem 2 ========================

def solve_cong_system_with_crt(m_ary, a_ary):
    ### your code here
    return solve_congs(m_ary, a_ary, 1)[0]

## ========== Problem 3 ========================

def solve_cong_with_xeuc(a, b, m):
    ### your code here
    return solve_cong(a, b, m)[0]

## ========= Problem 4 ========================

def rand_lcg(a, b, m, n, x0=0):
    ### your code here
    return prng.lcg(a, b, m, n, x0)

## ========= Problem 5 ========================

def rand_xorshift(a, b, c, n, x0=1):
    ### your code here
    return prng.xorshift(a, b, c, n, x0)

## ========= Problem 6 ========================

def equidistrib_test(seq, n, lower_bound, upper_bound):
    ### your code here
    return prng.equidistrib(seq, n, lower_bound, upper_bound)

## ========= Problem 7 ========================

'''
Type your answer to this problem here.

1) No [Outlook=Sunny -> Huminity=High -> No]
2) Yes [Outlook=Rain -> Wind=Weak -> Yes]
3) No [Outlook=Rain -> Wind=String -> No]
4) No [Outlook=Sunny -> Humidity=High -> No]
5) No [Outlook=Rain -> Wind=Strong -> No]
'''

## ========= Problem 8 ========================

'''
Type your answers to this problem here.

Entropy for attribute with binary values = -(p_0)(log_2(p_1)) + (-p_1(log_2(p_1))) where p_0 = prop where attr is false, p_1 = prop where attr is true
Informatin Gain = Entropy(target) - (S_val1/S * Entropy(val1) +  S_val2/S * Entropy(val2))
a) -2/5(log_2(2/5)) + (-3/5)log_2(3/5) = 0.97095

b) -1/5(log_2(1/5)) + (-4/5)log_2(4/5) = 0.72192

c)  0.97095 - ( 4/5(-4/5(log_2(4/5)) + (-1/5(log_2(1/5)))) + 1/5(-1/5(log_2(1/5)) + (-4/5(log_2(4/5)))) )= 0.97095 - (0.57754 + 0.14438) = 0.24903

d) 0.97095 - ( 4/5(-4/5(log_2(4/5)) + (-1/5(log_2(1/5)))) + 1/5(-1/5(log_2(1/5)) + (-4/5(log_2(4/5)))) )= 0.97095 - (0.57754 + 0.14438) = 0.24903

'''

## ========= Problem 9 ========================

def display_bin_id3_node(dt_root):
    return bin_id3.display_id3_node(dt_root, '')

def learn_bin_id3_dt_from_csv_file(csv_fp, target_attrib):
    
    examples, colnames = bin_id3.parse_csv_file_into_examples(csv_fp)
    attribs = set(colnames[1:])
    avt = bin_id3.construct_attrib_values_from_examples(examples, attribs)
    dtr = bin_id3.fit(examples, target_attrib, attribs, avt, False)

    return dtr


def classify_csv_file_with_bin_id3_dt(dt_root, csv_fp, target_attrib):
    examples, colnames = bin_id3.parse_csv_file_into_examples(csv_fp)
    accurate_count = 0
    for example in examples:
        pred = bin_id3.predict(dt_root, example)
        if pred == example[target_attrib]:
            accurate_count += 1
    
    accuracy = accurate_count / len(examples)

    return accuracy

## ========= Problem 10 ========================

def build_huffman_tree_from_text(txtstr):
    ### your code here

    # From string, build individual nodes. 
    char_freq_map = {}
    for c in txtstr:
        if c in char_freq_map:
            char_freq_map[c] += 1
        else:
            char_freq_map[c] = 1

    print(char_freq_map)
    nodes = HuffmanTree.freqMapToListOfHuffmanTreeNodes(char_freq_map)
    print(nodes)
    ht = HuffmanTree.fromListOfHuffmanTreeNodes(nodes)
    print(ht)
    return ht

'''
Remember to state the number of saved bytes.
'''
def encode_moby_dick_ch03():
    freq_map = CharFreqMap.computeCharFreqMap('moby_dick_ch03.txt')
    nodes = HuffmanTree.freqMapToListOfHuffmanTreeNodes(freq_map)
    ht = HuffmanTree.fromListOfHuffmanTreeNodes(nodes)
    bht = BinHuffmanTree(root=ht.getRoot())
    bht.encodeTextFromFileToFile('moby_dick_ch03.txt', 'moby_dick_ch03')



## ========= Problem 11 ========================

'''
Type your solution here.

Answer: 

| 0   | 0  | 0 | 0 | 0 |
| 0   | 20 | 0 | 0 | 0 |
| 100 | 0  | 2 | 0 | 0 |
| 0   | 10 | 0 | 0 | 0 |
| 0   | 0  | 0 | 0 | 0 |

Process: 
Lots of 0 multiplication. Rows that werent equal to 0:

c[-1, -1] = 2 * 10  =   20
c[0, -2]  = 10 * 10 =   100
c[0, 0]   = 2 * 1   =   2
c[1, -1]  = 10 * 1  =   10
'''



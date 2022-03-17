####################################
# module: rmb.py
# Stephen Beckstrand
# YOUR A02311346
####################################

import numpy as np

class rmb(object):
    
    @staticmethod
    def rjl(f, a, b, j, l):
        # j-th Romber's approx. at level l

        # if l == 1, apply trapezoidal rule for given value of j
        # If l > 1, rjl is computed as equation in slide 13 of lecture 14. 
        matrix = np.zeros((j, l), dtype=np.longdouble)

        for row in range(0, j):
            n = 2 ** ((row + 1) - 1)
            matrix[row,0] = rmb.t_rule(f, a, b, n)

        for col in range(0, l):

            for row in range(col, j):
                    r1 = matrix[row, col - 1]
                    r2 = matrix[row - 1, col -1]
                    den = (4 ** (col)) - 1
                    matrix[row,col] = r1 + ((r1 - r2) / den)

        return matrix[j - 1, l - 1]

    @staticmethod
    # Trapezoidal Rule
    def t_rule(f, a, b, n):

        h = (b - a) / n

        sum_total = 0
        for i in range(1, n):
            sum_total += f(a + (i * h))
        
        result = np.longdouble(((b - a) / (2 * n)) * (f(a) + (2 * sum_total) + f(b)))

        return result
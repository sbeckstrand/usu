
####################################
# module: cs3430_s22_hw04.py
# Stephen Beckstrand    
# A02311346
####################################

from sqlite3 import OperationalError
from matplotlib.pyplot import table
import numpy as np

import time

### ============== Problem 1 ==================

def simplex(tab):

    tableau = tab[1].copy()
    in_vars = tab[0]
    while True:
        pRow = tableau[len(tableau) -1]
        
        # Find entering variable
        entValue = 0
        entIndex = -1
        for i in range(0, len(pRow)): 
            if (pRow[i] < 0):
                if (entIndex == -1):
                    entValue = pRow[i]
                    entIndex = i
            
                if (pRow[i] < entValue):
                    entValue = pRow[i]
                    entIndex = i
            
            
        if (entIndex == -1):
            return (in_vars,tableau), True

        # Find departing variable
        eCol = tableau[:,entIndex]
        depIndex = -1
        depValue = 0
        for i in range(0, len(eCol)):
            if eCol[i] > 0:
                value = tableau[i,-1] / eCol[i]

                if depIndex == -1:
                    depIndex = i
                    depValue = value
                
                if (depValue > value):
                    depIndex = i
                    depValue = value

        if (depIndex == -1):
            return (in_vars,tableau), False
        
        in_vars[depIndex] = entIndex

        # Pivoting Operation on pivot row
        scalar = 1 / tableau[depIndex,entIndex]
        tableau[depIndex, :] = tableau[depIndex, :] * scalar
        
        

        # Pivoting Operation on all other rows
        for i in range(0, len(tableau)):
            if (i != depIndex):
                scalar = tableau[i, entIndex] / tableau[depIndex, entIndex]
                tableau[i, :] = tableau[i, :] + (tableau[depIndex, :] * (-(1) * scalar))
             

def get_solution_from_tab(tab):
    in_vars, mat = tab[0], tab[1]
    nr, nc = mat.shape
    sol = {}
    for k, v in in_vars.items():
        sol[v] = mat[k,nc-1]
    sol['p'] = mat[nr-1,nc-1]
    return sol

def display_solution_from_tab(tab):
    sol = get_solution_from_tab(tab)
    for var, val in sol.items():
        if var == 'p':
            print('p\t=\t{}'.format(val))
        else:
            print('x{}\t=\t{}'.format(var, val))

### =============== Problem 2 ====================

def problem_2_1():
    
    in_vars = {0:3, 1:4}
    table = np.array([[3, 8, 1, 0, 24],
                    [6, 4, 0, 1, 30],
                    [2, 3, 0, 0, 0]],
                    dtype=float)
    tab = (in_vars, table)
    tab, solved = simplex(tab)
    print('solved={}'.format(solved))
    display_solution_from_tab(tab)

def problem_2_2():
    
    in_vars = {0:3, 1:4}
    table = np.array([[1, -1, 1, 0, 4],
                    [-1, 3, 0, 1, 4],
                    [2,  3, 0, 0, 0]
    ])
    tab = (in_vars, table)
    tab, solved = simplex(tab)
    print('solved={}'.format(solved))
    display_solution_from_tab(tab)

def problem_2_3():

    in_vars = {0:4, 1:5, 2:6}
    table = np.array([[12, 6,   0, 1, 0, 0, 1500],
                      [18, 12, 10, 0, 1, 0, 2500],
                      [15,  8,  0, 0, 0, 1, 2000],
                      [1.5, 0.8,0.25, 0, 0,    0]
    ])
    tab = (in_vars, table)
    tab, solved = simplex(tab)
    print('solved={}'.format(solved))
    display_solution_from_tab(tab)

def problem_2_4():
    in_vars = {0:4, 1:5}
    table = np.array([[120, 80, 50, 1, 0, 1000],
                      [6,   4,  4,  0, 1, 600],
                      [1.0, 1.2,2.0,0, 0, 0]
    ])
    tab = (in_vars, table)
    tab, solved = simplex(tab)
    print('solved={}'.format(solved))
    display_solution_from_tab(tab)
    

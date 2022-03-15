#############################################################
# module: cs3430_s22_midterm01.py
# Stephen Beckstrand
# A02311346
# Time Spent on Midterm: ~ 1 hour. 
##############################################################

### put your imports from your previous/current assignments
from cs3430_s22_hw02 import bsubst, fsubst, lu_decomp, lud_solve
from cs3430_s22_hw01 import gje, cramer
### ================ Problem 01 ========================

def solve_lin_sys_with_bsubst(a, n, b, m):
    
    return bsubst(a, n, b, m)

### ================ Problem 02 ========================

def solve_lin_sys_with_fsubst(a, n, b, m):

    return fsubst(a, n, b, m);

### ================ Problem 03 ========================

def solve_lin_sys_with_gje(a, b):

    return gje(a, b)

### ================ Problem 04 ========================

def solve_lin_sys_with_lud(a, n, b, m):
    
    return lud_solve(a, n, b, m)

### ================ Problem 05 ========================

def solve_lin_sys_with_cramer(a, b):
    
    return cramer(a, b)

### ================ Problem 06 =========================

"""
# STANDARD MAXIMIZATION PROBLEM (SMP)

A Standard Maximization Problem is a linear function with contraints. 
The goal is to find the maximum possible value within the given contraints. 

# OBJECTIVE FUNCTION

An objective function is one of the components necessary to solve a optimization problems. 
It is the function, that given determined variables and constraints, 
is used to find a valid answer to the optimization problem. 

# CORNER POINT

A corner point refers to the intersecting point of lines or equations part of a feasible set.
If we map each of our linear equations as lines, the feasible region 
will be an area contained within intersecting points of the queations based on given constraints. 
Corner points refer to each of the corners or edges of  this feasible region. 

# FEASIBLE SET

Feasible set is a set of solutions for a optimization problem based on given constraints. 

# CONDITIONS WHEN SIMPLEX ALGORITHM STOPS

1) There is no entering variable because all entries in the p-row are non-negative

2) There is an entering variable, but there's no departing variable, 
because there's no positive entries in the column of entering variables

# BOUNDED FEASIBLE SET

A set is bounded if it is contained in some circle with its center at (0,0). 
If a feasible set is bounded, than the min/max value of an objective function is 
located at a corner point of the feasible set. 

# Unbounded Feasible Set

A set is unbounded if it is not containd in some cricle with its center at (0,0)
If a feasible set is unbounded, the min/max value is at a corner point of the feasible set, or 
takes arbitrarily large positive or negative values of the feasible set. 
"""

### ================ Problem 07 =========================

"""
SMP: p = 13x + 7y + 5z

Constraints: 
- x >= 0
- y >= 0
- z >= 0
- 6x + z <=122
- 2y + 5z <= 502
- 9x - 7y + 6z <= 902

# SLACK VARIABLES

General idea behind slack variables is that if x + y <=C , then there is potential for x + y < C, 
where a slack variable is the difference between C and (x + y) so that x + y + u = C. That being said,
the last three contraints in our list of contraints are eligle for slack variables

- 6x + z <=122 -> 6x + z + u = 122
- 2y + 5z <= 502 -> 2y + 5z + v = 502
- 9x - 7y + 6z <= 902 -> 9x - 7y + 6z + w = 902

# TABLEAU

 ||  u | v | w | X | Y | Z | B.S || 
u|   1   0   0   6   0   1   122  |
v|   0   1   0   0   2   5   502  |
w|   0   0   1   9   -7  6   902  |


"""

### ================ Problem 08 =========================

"""
Step 1) Choose the column in the 'p' row with the most negative value: [p, x_1] = -22

Entering variable = x_1

Step 2) 
For each positive value in the column, divide the value into the B.S value in the same row. 
Choose the variable that gives the smallest quotient

x_3 -> 190 / 6 = 31.66
x_4 -> 510 / 7 = 74.xx
x_5 -> 810 / 10 = 81

x_3 = departing variable. 

This means that our pivot is at [x_3, x_1] with a value of 6. 
"""

    
    
    
    







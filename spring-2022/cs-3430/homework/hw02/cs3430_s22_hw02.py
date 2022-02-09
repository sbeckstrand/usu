
#################################################
# Module: cs3430_s22_hw02.py
# Stephen Beckstrand
# A02311346
# bugs to vladimir kulyukin via canvas
#################################################

from re import L
import numpy as np
import pickle
import os

### =============== Problem 1 =============================

def lu_decomp(a, n):
    """ 
    lu_decomp(a, n) returns u, l such that np.dot(l, u) === a.
    a is an nxn matrix that is reduced to the upper and lower triangular matrices. 
    throws exception when there is no pivot in a column or rows must be swapped
    to create a pivot.
    lu_decomp(a, n) is destructive in that a is destructively modified into u.
    """

    # Check that matrix is n x n
    if len(a.shape) == 1 or a.shape[0] != a.shape[1]:
        return None

    # Define matrix/data that will be worked with
    upper = a.copy()
    lower = np.zeros(shape=(n,n))

    # Update lower matrix to be an identify matrix (Put 1s in the diagonals)
    for i in range(0,n):
        lower[i][i] = 1
        
    current_row = 0
    current_col = 0

    # Logic to build/update lower and upper matrix
    for col in range(0, upper.shape[1]):
        

        column = upper[:, col]
        if not np.all((column == 0)):

            first_index = None

            # For each column in matrix, determine where the first non-zero value is
            for i in range(current_col, column.size):
                if first_index == None and column[i] != 0:
                    first_index = i
            
            # Swap rows if necessary. Make same changes in both upper and lower matrices.
            if (first_index != current_row) and first_index != None and upper[current_row, col] == 0 :
                upper[[current_row, first_index]] = upper[[first_index, current_row]]
                lower[[current_row, first_index]] = lower[[first_index, current_row]]

            # Reduce values below pivot to 0 in 'upper' matrix. Track the scaler value used and update the 'lower' matrix
            # to use that value in the same place in the 'lower' matrix that it was used to reduce in the 'upper' matrix.
            for i in range(current_row + 1, upper.shape[0]):
                if upper[i, current_col] != 0:
                    scalar = upper[i, current_col] / upper[current_row, current_col] 
                    upper[i, :] = (scalar * upper[current_row, :]) - upper[i, :]
                    upper[i, :] = -1 * upper[i, :]
                    lower[i, current_col] = scalar

        
        current_col += 1
        current_row += 1


    return upper, lower


### =============== Problem 2 =============================

def bsubst(a, n, b, m):
    """
    bsubst uses back substitution to solve ax = b1, b2, ..., bm.
    a is an nxn upper-triangular matrix, n is its dimension.
    b is an nxm matrix of vectors b1, b2, ..., bm. 
    returns x.
    """
    
    # Define data to be used
    b = b.copy()
    x = np.zeros(shape=(n,m))

    # For each vector/column in b, find a solution vector for x
    for vec in range(0, m):

        # Define a copy of our matrix to update as we calculate x values. 
        temp_matrix = a.copy()

        # Starting with the last row (which only has one variable), find x, 
        # then backtrack through each row filling in the found values of x. 
        for i in range(n - 1, -1, -1):
            total = 0
            for j in range(n - 1, i, -1):
                    total += temp_matrix[i, j] * x[j, vec]
            
            x[i, vec] = (b[i, vec] - total ) / temp_matrix[i, i]

    return x


def fsubst(a, n, b, m):
    """
    fsubst uses forward substitution to solve ax = b1, b2, ..., bm.
    a is an nxn lower-triangular matrix, n is its dimension.
    b is an nxm matrix of vectors b1, b2, ..., bm.
    returns x.
    """

    # Define data to be used
    b = b.copy()
    x = np.zeros(shape=(n,m))

    # For each vector/column in b, find a solution vector for x
    for vec in range(0, m):

        # Define a copy of our matrix to update as we calculate x values. 
        temp_matrix = a.copy()

        # Starting with the first row (which only has one variable), find x, 
        # then process concurrent rows, filling in the found values of x. 
        x[0, vec] = b[0, vec] / temp_matrix[0, 0]
        for i in range(1, n):
            total = 0
            for j in range(0, i):
                total += temp_matrix[i, j] * x[j, vec]
            
            x[i, vec] = (b[i, vec] - total ) / temp_matrix[i, i]
    
    return x

def bsubst2(a, n, b, m):
    """
    bsubst uses back substitution to solve ax = b1, b2, ..., bm.
    a is an nxn upper-triangular matrix, n is its dimension.
    b is an nxm matrix of vectors b1, b2, ..., bm. 
    returns x.
    """

    b1 = []
    b2 = []
    for r in range(n-1, -1, -1):
        if(r == n-1):
            b1.append(b[r][0] / a[r][r])
            if(m > 1):
                b2.append(b[r][1] / a[r][r])
            continue
        if(r == n-2):
            b1 = [(b[r][0] - a[r][r+1] * b1[0]) / a[r][r]] + b1
            if(m > 1):
                b2 = [(b[r][1] - a[r][r+1] * b2[0]) / a[r][r]] + b2
            continue

        b1 = ([b[r][0] - np.dot(a[r][r+1:], b1) / a[r][r]]) + b1
        if(m > 1):
            b2 = [b[r][1] - np.dot(a[r][r+1:], b2) / a[r][r]] + b2
    if(m > 1):
        b = np.array([b1, b2]).T
    else:
        b = np.array([b1]).T
    return b

def lud_solve(a, n, b, m):
    """
    a is an nxn matrix; b is m nx1 vectors.
    Use forward subst to solve Ly = b for y.
    Use back    subst to solve Ux = y for x.
    Then LUx = Ly = b.
    Returns x.
    """

    upper, lower = lu_decomp(a, n)
    y = fsubst(lower, n, b, m)
    x = bsubst(upper, n, y, m)

    print(x)
    print(upper)
    print(lower)
    return x


def lud_solve2(u, l, n, b, m):
    """
    Uses L to transform b to c.
    Then backsubst to solve Ux = c for x.
    Returns x.
    """
    
    # Define the data/matrices that we work with
    upper, lower = u.copy(), l.copy()
    c = b.copy()
    
    # For each column/vector in b, find the solutions for x. 
    for vec in range(0, m):

        # For each column in our lower matrix
        for col in range(0, lower.shape[1]):

            # Grab the values for our columns. Starting with the values under the pivots, update the c vector 
            column = lower[:, col]
            for i in range(col + 1, column.size):
                c[i, vec] += ((-1) * column[i]) * c[col, vec]


    # Calculate x
    x = bsubst(upper, n, c, m)
    return x


# a = np.array([[ 73., 136., 173., 112.],
#                       [ 61., 165., 146.,  14.],
#                       [137.,  43., 183.,  73.],
#                       [196.,  40., 144.,  31.]])

# b = np.array([[4.0],
#                       [-1.0],
#                       [3.0],
#                       [5.0]])

# upper, lower = lu_decomp(a, a.shape[0])

# y = fsubst(lower, lower.shape[0], b, b.shape[1])

# x = bsubst2(upper, 4, y, 1)
# # x = bsubst(upper, 4, y, 1)

# print(x)


a = np.array([[1, 3, -1],
                      [0, 2,  6],
                      [0, 0, -15]],
                 dtype=float)
b = np.array([[-4],
                [10],
                [-30]],
                dtype=float)

x = bsubst(a, 3, b, 1)

print(x)

    

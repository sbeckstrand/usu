######################################################
# module: cs3430_s22_hw01.py
# YOUR NAME
# YOUR A-NUMBER
######################################################

import numpy as np
import numpy.linalg
import random


## If you need to define auxiliary functions, you may define them
## in this file.

### ============= Problem 1 (Gauss-Jordan Elimination) ===============

def gje(a, b):
    
    # Augment (combine) matrix a to include the b vector as a column.
    augmented = np.hstack((a, b))

    # Move rows with 0 in first index to bottom
    zero_arrays = np.where(np.sum(np.abs(augmented), axis=1)==0)[0]

    if zero_arrays.size > 0:
        for row in zero_arrays:
            augmented = np.vstack([augmented, augmented[row]])
            augmented = np.delete(augmented, [row], 0)

    # Traverse the matrix from left -> right one column at a time
    current_row = 0
    current_col = 0
    for col in range(0, augmented.shape[1] - 1):

        # Check if column only contains zeros. If so, skip it. 
        column = augmented[:, col]
        if not np.all((column == 0)):
            
            # If column contains non-zero value. Determine which row has the largest value in this column. 
            largest_index = None
            for i in range(current_col, column.size):
                if largest_index == None:
                    largest_index = i
                else:
                    if abs(column[i]) > abs(column[largest_index]):
                        largest_index = i
            
            # Swap rows (if necessary) so that current_row has the largest coefficient in the columns being processed. (Others will be turned to zero anyway, so no need to sort the next largest)
            if (largest_index != current_row) and largest_index != None:
                augmented[[current_row, largest_index]] = augmented[[largest_index, current_row]]

            # Multiply row by scalar so that the leading coefficient becomes 1. 
            if augmented[current_row, current_col] != 1 and augmented[current_row, current_col] != 0:
                augmented[current_row, :] = augmented[current_row, :] / augmented[current_row, current_col]


            # Reduce values in lower rows to 0
            for i in range(current_row + 1, augmented.shape[0]):
                if augmented[i, current_col] != 0:
                    scalar = augmented[i, current_col] / augmented[current_row, current_col] 
                    augmented[i, :] = (scalar * augmented[current_row, :]) - augmented[i, :]
            
            # Reduce values in higher rows to 0
            for i in range(current_row - 1, -1, -1):
                if augmented[i, current_col] != 0:
                    scalar = augmented[i, current_col] / augmented[current_row, current_col] 
                    augmented[i, :] = (scalar * augmented[current_row, :]) - augmented[i, :]
                    augmented[i, :] = -1 * augmented[i, :]


        # Check if system of equations is inconsistent and if ther is no solution
        for i in range(0, augmented.shape[0]):
            eq = augmented[i, :-1]
            ans = augmented[i, -1]

            if np.all((eq == 0)) and ans != 0:
                return None
        
        # Increment current_col and current_row so we are evaluating a smaller matrix
        current_col += 1
        current_row += 1
            
    # Augmented matrix is now in reduced echelon form. If we have reached this point, each row should have a pivot or free solution. We can return the the last column as our b vector of solutions. 
    return np.array(augmented[:, [-1]])



        

## ============== Problem 2 (Determinants) ========================

def random_mat(nr, nc, lower, upper):
    """ Generate an nrxnc matrix of random numbers in [lower, upper]. """
    m = np.zeros((nr, nc))
    for r in range(nr):
        for c in range(nc):
            m[r][c] = random.randint(lower, upper)
    return m

def leibnitz_det(a):
    a = a.copy()
    # Check if matrix is a 1 x 1 'matrix'. Also acts as the recursion base case. 
    if a.size == 1:
        return a[0][0]
    

    # Check that matrix is n x n
    if len(a.shape) == 1 or a.shape[0] != a.shape[1]:
        return None

    # Grab top row of matrix As our A_1n values
    a_vect = a[0, :]
    
    # For each value in the top row, find the minor matrix, the cofactor and then use leibnitz_formula which finds the summation of a_1n *  c_1n
    total = 0;
    for col in range(0, a_vect.size):
        # Determine minor matricies
        minor = np.delete(np.delete(a, 0, axis=0), col, axis=1)
        cofactor = ((-1)**(1 + (col+1))) * leibnitz_det(minor)
        total += a_vect[col] * cofactor


    # Return the sum
    return total
    
    
def gauss_det(a):
    a = a.copy()
    det = 1

    # Check if matrix is a 1 x 1 'matrix'. 
    if a.size == 1:
        return a[0][0]
    

    # Check that matrix is n x n
    if len(a.shape) == 1 or a.shape[0] != a.shape[1]:
        return None

    # Track number of times rows are swapped
    row_swaps = 0;

    # Track pivots
    pivots = []

    # Check if there are any rows with just 0, if so, determinate is 0
    if np.where(np.sum(np.abs(a), axis=1)==0)[0].size > 0:
        return 0

    current_row = 0
    current_col = 0


    for col in range(0, a.shape[1]):
        
        # Check if column only contains zeros. If so, skip it. 
        column = a[:, col]
        if not np.all((column == 0)):

            # If column contains non-zero value. Determine which row has the first non-zero
            first_index = None
            for i in range(current_col, column.size):
                if first_index == None and column[i] != 0:
                    first_index = i
            
            # Swap rows (if necessary) so that current_row has the largest coefficient in the columns being processed. (Others will be turned to zero anyway, so no need to sort the next largest)
            if (first_index != current_row) and first_index != None and a[current_row, col] == 0 :
                a[[current_row, first_index]] = a[[first_index, current_row]]
                row_swaps += 1
    

            pivots.append(a[current_row, current_col])

            # Reduce values in lower rows to 0
            for i in range(current_row + 1, a.shape[0]):
                if a[i, current_col] != 0:
                    scalar = a[i, current_col] / a[current_row, current_col] 
                    a[i, :] = (scalar * a[current_row, :]) - a[i, :]
                    a[i, :] = -1 * a[i, :]
    
    
        current_col += 1
        current_row += 1

    for pivot in pivots:
        det = det * pivot 
    
    if (row_swaps > 0):
        det = det * ((-1) * row_swaps)

    return det
    


## ============== Problem 3 (Cramer's Rule) ======================

def cramer(A, b):
    solutions = np.array([])
    det_a = gauss_det(A.copy())
    for col in range(0, A.shape[1]):
        B_i = A.copy()
        B_i[:, [col]] = b
        solutions = np.append(solutions, np.array([gauss_det(B_i) / det_a]))

    return np.vstack(solutions)

if __name__ == '__main__':
    pass

A = np.array([[446., 163.,  59., 129., 393., 483., 271.,  27., 384.,  58.],
                      [258., 116., 272., 401., 332., 216., 158., 211., 361., 236.],
                      [ 68., 498., 471.,  81., 178., 167., 166., 399., 484., 422.],
                      [338.,  38., 274., 476.,  99., 370., 322., 459., 151., 163.],
                      [380., 106., 421., 314., 425., 332.,  10., 135., 448., 407.],
                      [456., 270., 317., 268.,   3., 394., 250., 354., 135., 310.],
                      [330., 452., 456., 137., 457.,  37., 421.,   5., 357., 165.],
                      [294., 457., 147.,   1., 278., 474., 368., 138., 222., 122.],
                      [231.,   1., 184., 318., 315., 433., 434.,  76.,  71.,  34.],
                      [129., 307., 479., 350., 103., 485., 243., 160., 245., 407.]],
                     dtype=float)

    
    
              

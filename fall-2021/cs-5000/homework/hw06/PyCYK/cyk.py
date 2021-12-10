###############################################
# module: cyk.py (Assignment 6)
# Stephen Beckstrand
# A02311346
###############################################

import itertools

class CYK(object):

    @staticmethod
    def is_in_cfl(test_string, cnfg, table_display_flag=False):
        comp_table = []

        n = len(test_string)

        # Creates a (n x n) array where n = |x| and x = test_string
        for i in range(len(test_string)):
            comp_table.append([])

            for j in range(0, len(test_string)):
                comp_table[i].append({})
        
        # For each letter in the original test string, populate the cells in the first row of the table with the productions that result in that symbol.
        # Note: This could have easily been included in the above for loop to reduce work, but thought it might help to break up the logic, just for ease of reading code. 
        for s in range(len(test_string)):
            comp_table[0][s] = cnfg.fetch_lhs(test_string[s])

        # Start checking for longer combinations of strings and compare to previous results to determine if still possible to be generated by the grammar. 
        for l in range(2,n + 1):
            for s in range(1, n - l + 2):


                lhs = []
                for k in range(1,l):
                    B = comp_table[k-1][s-1]
                    C = comp_table[(l - k) - 1][(s+k) - 1]


                    for element in itertools.product(B, C):
                        if len(element) == 2:
                            response_set = cnfg.fetch_lhs(element[0], element[1])
                            if len(response_set) > 0:
                                lhs.append(response_set)
                    
                lhs_set = set().union(*lhs)
                comp_table[l - 1][s - 1] = lhs_set
        
        # Debug output. Prints the production table. 
        if table_display_flag:
            for row in comp_table:
                row_string = ""
                for cell in row:
                    cell_items = ""
                    for item in cell:
                        cell_items += "%s " % item
                    row_string += "|%s |" % cell_items
                print(row_string)


                        



        if 'S' in list(comp_table[n -1][0]):
            return True
        else:
            return False




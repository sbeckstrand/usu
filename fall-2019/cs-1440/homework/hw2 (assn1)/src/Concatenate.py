
def cat(args):
    # Verify arguments
        # Check if invalid chars in file name



    # For arg in args (loop through each file included as argument)
    for arg in args:
        # Open file
        file = open(arg, 'r')
        # print contents
        print(file.read().rstrip('\n'))
        # close file
        file.close()



def tac(args):
    for arg in args:
        # Open file
        file = open(arg, 'r')
        lines = []
        #For each line in file
        for line in file:
            lines.append(line.rstrip('\n'))
        # Print line starting bottom up
        for i in range(1, len(lines) + 1):
            print(lines[i * -1])
        # close file
        file.close()

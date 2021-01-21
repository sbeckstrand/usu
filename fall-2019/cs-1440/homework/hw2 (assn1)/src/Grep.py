from Usage import usage
import sys

def grep(args):


    # Check if -v flag is provided, if so, specify that one extra argument is needed. Also specify that we want to print files that are not what was searched for
    if (args[0] == "-v"):
        minimumArguments = 3
        searchTerm = 1
        condition = False
    else:
        minimumArguments = 2
        searchTerm = 0
        condition = True

    # Check that the appropriate number of arguments are provided
    if (len(args) < minimumArguments):
        usage("Error: Invalid number of arguments provided", 'grep')
        sys.exit(1)

    # Create a new list to contain our matches that we will later print out.
    completeList = []

    #For each file specified, check if the file contains the specified phrase. Print out any values that match our conditions.
    for arg in args[(searchTerm +1 ): ]:
        file = open(arg, 'r')
        for line in file:
            completeList.append(line.rstrip('\n'))
        file.close()

    for item in completeList:
        if (args[searchTerm] in item) == condition:
            print(item)

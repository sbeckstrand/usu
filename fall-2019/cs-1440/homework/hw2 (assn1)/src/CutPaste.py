from Usage import usage
import sys

def cut(args):
    # Check if '-f' argument is given which is used to specify which columns to cut. If supplied, specify how many arguments should be supplied and create a list of the specified columns. If not, default to output the first column.
    if (args[0] == "-f"):
        minimumArguments = 3
        selectedColumns = args[1].split(",")
        for i in range(0, len(selectedColumns)):
            selectedColumns[i] = int(selectedColumns[i])
        fileStart = 2
    else:
        minimumArguments = 1
        selectedColumns = [1]
        fileStart = 0

    # Make sure the appropriate number of columns are provided
    if (len(args) < minimumArguments):
        usage("Error: Invalid number of arguments provided", 'cut')
        sys.exit(1)

    # Sort the selected columns, that way the same output is returned regardless of the order they are supplied.
    selectedColumns = sorted(selectedColumns)

    # Open the specified file, For each line in the file, remove the new lines, and then split the items into a list.
    file = open(args[fileStart])
    for line in file:
        line = line.rstrip('\n')
        currentLine = line.split(",")
        filteredLine = []
        # For each of the user specified columns to cut, add the items in that column on each line to a newly created list of the items we should output. First, check to make sure the column they provided exists. If it does not, append "" to the list.
        for item in selectedColumns:

            if (item <= len(currentLine) ):
                filteredLine.append(currentLine[item - 1] + ",")
            else:
                filteredLine.append("")


        # If just a comma exists as a value in our list, remove it
        if ("," in filteredLine):
            filteredLine.remove(',')
        i = 1

        # Find the last item in our list that has a legitmate value and remove the comma from the end of it.
        while i < len(filteredLine):

            if (filteredLine[i * -1] != ''):
                filteredLine[i * -1] = filteredLine[i * -1].rstrip(',')
                break
            i += 1

        #Print out the items in our list.
        for item in filteredLine:
            if (item != ","):
                print(item, end="")

        print("")


    # Close file now that we are done
    file.close()




def paste(args):
    completeList = []

    # Loop through each of the files passed as arguments
    for i in range(0, len(args)):
        file = open(args[i], 'r')

        # Starting with index 0, attempt to append item on each line of the file to a list that we will later print out. If the line does not have a value at that index, append an empty list.
        indexCount = 0
        for line in file:
            while True:
                try:
                    completeList[indexCount].append(line.rstrip('\n') + ",")
                    break
                except IndexError:
                    completeList.append([])
                    if completeList[0]:
                        max = len(completeList[0])

                        for i in range(0, max -1):
                            completeList[-1].append(',')



            indexCount += 1
        file.close()


    # For each of the items in our complete list, print to console
    for i in range(0, len(completeList)):
        completeList[i][-1] = completeList[i][-1].rstrip(',')
        for j in range(0, len(completeList[i])):
            print(completeList[i][j], end='')
        print()

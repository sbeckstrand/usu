def sort(args):
    # For each file specified, combine their contents to a single list, sort the list and then print it out.
    completeList = []
    for arg in args:
        file = open(arg, 'r')
        for line in file:
            completeList.append(line.rstrip('\n'))
        file.close()

    sortedList = sorted(completeList)

    for item in sortedList:
        print(item)

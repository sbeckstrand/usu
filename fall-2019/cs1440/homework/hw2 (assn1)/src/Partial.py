from Usage import usage
import sys

def head(args):
    # Verify arguments
    if (verifyArguments(args, "head")):
        argStart = 2
        headValue = int(args[1])
    else:
        argStart = 0
        headValue = 10

    # For each file specified, output the number of lines specified. If none were specified, output 10. If less lines exist than are specified, output all lines.
    for arg in args[argStart:]:
        file = open(arg)

        lines = []

        for line in file:
            lines.append(line)

        if len(lines) <= headValue:
            headValue = len(lines)

        for i in range(0, headValue):
            print(lines[i].rstrip('\n'))

        file.close()

    # Close File


def tail(args):
    #Verify aruguments
    if (verifyArguments(args, "tail")):
        argStart = 2
        tailValue = int(args[1])
    else:
        argStart = 0
        tailValue = 10
    # For each file specified, output the number of lines specified. If none were specified, output 10. If less lines exist than are specified, output all lines.
    for arg in args[argStart:]:
        file = open(arg)

        lines = []

        for line in file:
            lines.append(line)

        if len(lines) <= tailValue:
            tailValue = len(lines)
        else:
            lines = lines[tailValue:]

        for i in range(0, tailValue):
            print(lines[i].rstrip('\n'))

        file.close()


def verifyArguments(args, tool):
    #Check that if the -n flag is provided and that the propriate number of arguments are provided. If not, return error and output how to use the specified tool.
    if (args[0] == "-n"):
        if (len(args) < 3):
            usage("Error: Invalid number of arguments provided", tool)

        try:
            int(args[1])
            return True
        except (ValueError, IndexError):
            usage("Error: Number of lines is required", tool)
            sys.exit(1)
    else:
        return False

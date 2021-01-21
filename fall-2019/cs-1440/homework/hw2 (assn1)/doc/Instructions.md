# CS 1440 Assignment 1 Instructions

## Description
In this assignment you will write your own versions of classic Unix text-processing programs.  The tools you write for this assignment are not intended to be perfect clones of the programs they are mimicking.  I have relaxed requirements that your code should meet.

This assignment is essentially a re-implementation of simple Unix text-processing programs in Python.  Each tool will be a Python function which takes as input a list of arguments supplied by the user from the command line.



## Requirements

### Command-Line Interface
This program will be run from the command line as a command of the form:

    $ python src/tt.py TOOL [OPTIONS] FILENAME...

This form of command should feel familiar to you after having used `git` over the past weeks.

1.   `python` invokes the Python interpreter
2.   Next comes the name of the driver program `src/tt.py`
3.   `TOOL` is the name of one of the available text tools listed in the table below.  The name of the tool must be spelled exactly as shown in the table, in lower-case.
4.   Some tools may take an option, which is entered in the position of `[OPTIONS]` *(Note that the user does not type the square brackets; in documentation for command-line programs brackets indicate optional arguments)*
5.   Each tool must be given at least one filename.  The ellipsis indicates that one or more filenames are acceptable.

Your program **must not** prompt the user for any input.  Your program **should not** use the `input()` function.

When `src/tt.py` is launched without naming a `TOOL`, or when an invalid `TOOL` is given, a usage message is printed.


### PyCharm Run Configurations
When PyCharm runs your program it invokes it from a command line.  To help PyCharm form a correct command, fill in the run configuration dialog like so:

*   Put the path to `src/tt.py` in "Script path"
*   Text representing `TOOL [OPTIONS] FILENAME...` goes into the field labeled "Parameters"
*   Use your default Python interpreter as the "Python interpreter"
*   Leave "Interpreter options" empty

Create as many "Run Configurations" as you want.  One run configuration may invoke the `grep` tool at the click of a button, while another configuration launches the `paste` tool.


### Code organization
This program consists of several functions grouped into modules of related functionality.

| Tool   | Module                                        | Description
|--------|-----------------------------------------------|--------------------------------------------------
| `cat`  | [Concatenate.py](examples/Concatenate.md#cat) | Concatenate files and print on the standard output
| `tac`  | [Concatenate.py](examples/Concatenate.md#tac) | Concatenate and print files in reverse
| `cut`  | [CutPaste.py](examples/CutPaste.md#cut)       | Remove sections from each line of files
| `paste`| [CutPaste.py](examples/CutPaste.md#paste)     | Merge lines of files
| `grep` | [Grep.py](examples/Grep.md#grep)              | Print lines of files matching a pattern
| `head` | [Partial.py](examples/Partial.md#head)        | Output the first part of files
| `tail` | [Partial.py](examples/Partial.md#tail)        | Output the last part of files
| `sort` | [Sorting.py](examples/Sorting.md#sort)        | Sort lines of text files
| `wc`   | [WordCount.py](examples/WordCount.md#wc)      | Print newline, word, and byte counts for each file


You must not rename functions or modules given in the starter code, nor should you change the parameter list of any functions.  Your job is to fill in the blanks.  You may create extra helper functions as you see fit.


### The `tt.py` driver program
This project uses a single program called a "driver" to unify many tools under a combined interface just like `git`.  The driver's job is to collect the user's input and dispatch control to another tool.  If the driver does not have enough correct information to make this choice it will display a message that helps the user learn how to use the tool.  A good driver is very simple and short, leaving the bulk of processing and decision-making up to the specific tool.


### Error handling
Some errors are easily detected in the driver and may be dealt with immediately before calling on one of the functions contained in a module.  For example, the best place to decide whether or not a valid `TOOL` has been named is in the driver.

Other errors are best left to the individual tool to handle. For example, the `head` tool may be given an `OPTION` formed from two arguments: a flag `-n` followed by a number.  It is an error to supply the flag '-n' without following up with a number.  It is also an error for the `-n` flag to be followed by a non-positive number.  The natural place to handle this is in the `head()` function.  Including this logic in the driver will make it more complex than it needs to be.

For other errors it is acceptable to fall-back to errors raised by Python's internal functions such as `open()`.  For example, when an inaccessible or non-existent file is specified it is permissible for your code to simply allow `open()` to crash the program.

Rely on the `usage()` function defined in `Usage.py` to display consistent error messages.  The usage text in this file is provided to guide you about the correct form of command-line arguments.  Before you ask a question about how a tool is to behave, make sure that your answer isn't already spelled out in `Usage.py`.


#### Too few or invalid arguments

When the `src/tt.py` driver is invoked with an empty or invalid `TOOL` name the `usage()` function should be called with no arguments to output the full usage message:

    $ python src/tt.py
    Error: Too few arguments

    Python Text Tools Usage:
    ========================
    ...


    $ python src/tt.py derp
    Error: derp is not a valid subcommand

    Python Text Tools Usage:
    ========================
    ...



#### File access errors

Let Python's `open()` function signal an error when a non-existent file is named

    $ python src/tt.py cat data/DOES_NOT_EXIST
    Traceback (most recent call last):
      File "src/tt.py", line 69, in <module>
        ops[sys.argv[1]](sys.argv[2:])
      File "/home/fadein/school/Fa19/cs1440/Assn/1/src/Concatenate.py", line 4, in cat
        f = open(fl, 'r');
    FileNotFoundError: [Errno 2] No such file or directory: 'data/DOES_NOT_EXIST'


Let Python's `open()` function signal an error when a directory is named instead of a file:

    $ python src/tt.py cat .
    Traceback (most recent call last):
      File "src/tt.py", line 19, in <module>
        cat(sys.argv[2:])
      File "/home/fadein/school/Fa19/cs1440/Assn/1/src/Concatenate.py", line 14, in cat
        f = open(fil, 'r');
    IsADirectoryError: [Errno 21] Is a directory: '.'


Let Python's `open()` function signal an error when the user does not have permission to access a file:

    $ python src/tt.py cat /dev/mem
    Traceback (most recent call last):
      File "src/tt.py", line 19, in <module>
        cat(sys.argv[2:])
      File "/home/fadein/school/Fa19/cs1440/Assn/1/src/Concatenate.py", line 14, in cat
        f = open(fil, 'r');
    PermissionError: [Errno 13] Permission denied: '/dev/mem'


#### Too few or incorrect arguments to a subcommand

Your program must detect and report the case where too few arguments are given; at a minimum the name of one input file is required for all tools.  Call the `usage()` function with the `error` and `tool` keyword arguments to print an appropriate error message.

For example, when the `cat` tool is invoked without at least one filename, call `usage(error="Too few arguments", tool='cat')` to display the following message:

    $ python src/tt.py cat
    Error: Too few arguments

    tt.py cat|tac FILENAME...
            Concatenate and print files in order or in reverse


## Penalties
Review the [Course Rules](https://gitlab.cs.usu.edu/erik.falor/fa19-cs1440-lecturenotes/blob/master/Course_Rules.md) document to avoid general penalties which apply to all assignments.  In addition, this assignment is subject to the following penalties:

1.  **10 point penalty** for each instance of functionality occurring outside of the appropriate module.
2.  **10 point penalty** if your program prompts the user for any input.  All input to this program comes from command-line arguments or from files.
3.  **10 point penalty** if your program hard-codes names of files or directories into string literals.  Hard-coding the name of a directory or file may be convenient for you, but causes trouble for the graders who aren't using your laptop to run this program.  Names of modules given in `import` statements and used as namespaces do not count as "hard-coded".
4.  **25 point penalty** if data files are not closed after being processed in *ordinary* situations.  In the event of an error your program will display an error message and immediately exit; in such cases you do not need to take special measures to close files because they will automatically be closed by the OS as your program exits.
5.  **85 point penalty** if your program uses external programs to do any work.  The requirements are that you create a pure-Python solution, not a shell script that relies on an external program.
6.  **85 point penalty** if your program imports any modules besides `sys` and those provided by the assignment itself.  Your goal is to have the experience of solving these puzzles for yourself and not to leverage code written by others, no matter how "real-world" it would be to do so.

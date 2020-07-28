# Word Count tool (`wc`)

The `wc` tool counts and prints the number of lines, words, and characters (bytes) present in a text file.  Note that, due to differences in the representation of the end-of-line (EOL) sequence between operating systems, the byte count you see on Windows will vary from these examples.  The line and word counts should remain the same across platforms.

These examples were produced on Linux:

    $ python src/tt.py wc data/num2
    2	2	4	data/num2


    $ python src/tt.py wc data/words200
    200	200	1790	data/words200


Multiple files may be given at once:

    $ python src/tt.py wc data/let3 data/random20 data/people.csv data/dup5 
    3	3	6	data/let3
    20	20	51	data/random20
    9	18	278	data/people.csv
    8	8	16	data/dup5


## Handling errors

The program aborts as soon as a non-existent, invalid or inaccessible file is encountered.  You do not need to pre-screen the arguments before you begin processing:

    $ python src/tt.py wc data/let3 data/random20 data/DOES_NOT_EXIST data/people.csv data/dup5 
    3	3	6	data/let3
    20	20	51	data/random20
    Traceback (most recent call last):
      File "src/tt.py", line 74, in <module>
        ops[sys.argv[1]](sys.argv[2:])
      File "/home/fadein/school/Sp19/cs1440/Assn/1/src/WordCount.py", line 5, in wc
        f = open(file)
    FileNotFoundError: [Errno 2] No such file or directory: 'data/DOES_NOT_EXIST'

# 1. Requirements

Our application should be run in a command line and take arguments to simulate various linux tools such as cat, grep, wc, etc.

Our application will need to be able to import modules for each tool, take arguments and match arguments with the appropriate tools.

# 2. Design


## Input
Our application will not prompt for any input directly. Instead, it will require arguments. We will need to verify that that the appropriate number of arguments are provided including the tool intended to be used, and the file(s) to process using that tool.

## Output
Output will depend on the tool used:

## tt.py (Drive) ##
This should be the main script that is first called and will then call the tool being requested. It should check to make sure at least one extra argument is provided after specifying a tool.

## Concatenate.py ##

This file should contain the cat() and tac() functions. cat() will concatenate the contents of (a) file(s) to the terminal. tac() will output file contents backward.

## CutPaste.yy **** ##

This file should contain the cut() and paste() functions. paste() will combine the contents of files files to columns and rows to be returned as output.

## Grep.py ##

This file will check to see if a search term is found within the specified file(s).

## Partial.py **** ##

This file should contain the head() and tail() functions . The head() function will by default output the first 10 lines of a file but will allow for user to specify the number of lines. The tail() function will by default output the last 10 lines of a file but will allow for user to specify the number of lines.

## Sorting.py **** ##

This file will contain the sort() function which will allow for sorting the contents of a file alphabetically.

## Usage.py ##

This file will output the details on how to use a tool, similar to a help page.

## WordCount.py ##

This file will contain the wc() function used to count the number of words, lines and bytes in the specified file(s).


# 3. Implementation

## tt.py (Driver) ##
```
# Import the functions for each of our modules
# Import sys

# Check if there are at least 2 arguments (driver and tool)
  # If not call on the usage() function to output detail on how to use the application and exit application
  # If there are two or more arguments, check to see what argument exists at index 1 (The tool)
    # If tool exists in one of our modules, call on that module passing all of the arguments after the tool named
    # If tool does not exist, call on usage() and exit script
```


## Concatenate.py ##
```
# Define cat() function
  # Loop through every file argument
    # Open each files
    # Print it out to the terminal
    # Close file
# Define tac() function
```

## CutPaste.py ##
```
# import usage function
# import sys module

# Define cut() function
  # Check if -f argument is given
    # If so, make sure columns are provided
  # Check to make sure the appropriate number of arguments are given
  # Sort the provided columns
  # Open the specified file
    # For each item in our file, split them and add each item to a list.
    # For each item column specified, loop through our list of items and print out the items in those columns
# Define paste() function
  # Loop through provided files passed as arguments.
  # Print out out the contents of each file where each line of each file is combined.
```


## Grep.py ##
```
# Define the grep() function
  # Check if -v flag is provided.
  # Loop through each file and split each line's content to a list.
  # Check through each of these items in the list to see if our search term exists in it. If it does and the -v flag was not specified, print it out. If -v was specified, print out all the words that do not match.
```

## Partial.py ##
```
# Define head() function
  # Verify that the correct number of arguments are provided and are valid
  # For each file specified, output the number of lines specified. If none were specified, output 10. If less lines exist than are specified, output all lines.
# Define tail() function
  # Verify that the correct number of arguments are provided and are valid
  # For each file specified, output the number of lines specified. If none were specified, output 10. If less lines exist than are specified, output all lines.
# Define verify Arguments functions
  # Check if the '-n' flag is provided
  # Check that the number of arguments provided are valid.
  # If both tests pass, return 'True', otherwise, return 'False'
```

## Sorting.py ##
```
# Define the sort() function
  # Create an empty list that will contain all items in all file specified.
  # Loop through each file
    # Append each line's item to our list.
  # Sort our list
  # Print it to console
```

## WordCount.py ##
```
# Define wc() function
  # Loop through each file provided
    # Open file
    # Create new variables for the line, word and byte counts.
    # For each line in our file, count the words and bytes on that line and append our line to a list.
    # Count the number of lines in our lists
    # Print out count of lines, words and bytes
    # Close file
```
# 4. Verification

## cat()

### Expected input/command:

```
python3 src/tt.py cat data/let3 data/num2
```

### Expected Output:

```
a
b
c
1
2
```

PASS

## tac()

### Expected Input/command:
```
python3 src/tt.py tac data/let3 data/num2
```

### Expected Output:
```
c
b
a
2
1
```

PASS

## paste()

### Expected Input/command:
```
python src/tt.py paste data/num2 data/let3
```
### Expected Output:
```
data/words5
1,a,babbles
2,b,sneakiness
,c,trimly
,,agree
,,frankly
```

PASS


## cut()

### Expected Input/command:
```
python3 src/tt.py cut -f 1,2 test.csv
```

### Expected Output:
```
1,Jerry
2,Bailey
3,Frank
4,Kai
5,Angela
6,Mikayla
7,Hazel
8,Karen
9,Alexa
10,Isabel
```

PASS

## grep()

### Expected Input/command:
```
python3 src/tt.py grep a data/ages8 data/colors8 data/let3
```

### Expected Output:
```
Favorite Color
Royal Blue
Light Salmon
DarkSea Green
Dark Goldenrod
a
```

PASS

## head()

### Expected Input/command:
```
python3 src/tt.py head data/words200
```

### Expected Output:
```
social
insomniac
implicitly
cranky
opponents
clubroom
uttering
roughen
easter
dad
```

PASS

## tail()

### Expected Input/command:
```
python3 src/tt.py tail -n 4 data/words200
```

### Expected Output:
```
opponents
clubroom
uttering
roughen
```

## sort()

### Expected Input/command:
```
python3 src/tt.py sort data/words5 data/names8
```

### Expected Output:
```
Abraham
Adrianna
Julian
Julianna
Marcus
Michael
Name
Savannah
Tiffany
agree
babbles
frankly
sneakiness
trimly
```

PASS

## wc()

### Expected Input/command:
```
python3 src/tt.py wc data/words200
```

### Expected Output:
```
200 200 1790 data/words200
```

PASS

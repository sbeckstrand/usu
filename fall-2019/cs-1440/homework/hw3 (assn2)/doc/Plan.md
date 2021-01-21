# 1. Requirements

Our application should process industry statistics from a csv file that includes data such as the the gross annual wages, number of establishments, employees, etc.

Once processed, the application should provide a report that outlines the filtered information.

# 2. Design


## Input
Our application will not prompt for any input directly. Instead, it will require arguments. We will need to ensure that an argument is provided.

## Output
Output will depend on the data being processed.

## main.py ##

Main driver. It is used to calculate how long the application takes to run, handles arguments, populates the report objects

## Report.py ##

Module that includes the constructor for the report object.

## Results.py ##

Module used to loop through each line in the csv file containing data to calculate income, total number of employees, etc.

# 3. Implementation

## main.py ##
```
# Import needed modules
# Create report object
# Check that a data path argument is provided
# Calculate how long the application takes to run
# Create 'all' and 'soft' objects using the Results class
# print how long it took to get data
# populate report
# print report
```

## Results.py ##
```
# Define Results class/constructor.
  # Define variables that will later be used to generate Report
  # Open /area_titles.csv file
  # Creation dictionary containing area codes and area names8
  # Close file
  # Open 2018.annual.singlefile.csv file
  # Loop through each line and populate the variables with the data from the annual csv file.
  # Close file
```



# 4. Verification

## Test Case 1: All US Data

### Expected input

```
python3 src/main.py data/USA_full/
```

### Expected output

```
Reading the databases...
Done in 14.093 seconds!

[============]
[Final Report]
[============]

Statistics over all industries in 2018:
=========================================================
Number of FIPS areas in report       3,273

Gross annual wages                   $8,394,742,237,678
Area with maximum annual wages       New York County, New York
Maximum reported wage                $304,367,452,447

Total number of establishments       10,059,774
Area with most establishments        Los Angeles County, California
Maximum # of establishments          495,918

Gross annual employment level        147,025,175
Area with maximum employment         Los Angeles County, California
Maximum reported employment level    4,445,762


Statistics over the software publishing industry in 2018:
=========================================================
Number of FIPS areas in report       1,242

Gross annual wages                   $66,999,781,360
Area with maximum annual wages       King County, Washington
Maximum reported wage                $15,939,051,863

Total number of establishments       28,935
Area with most establishments        King County, Washington
Maximum # of establishments          790

Gross annual employment level        391,838
Area with maximum employment         King County, Washington
Maximum reported employment level    62,601
```

Pass: Yes

## Test Case 2: UT

### Expected Input:

```
python3 src/main.py data/UT
```

### Expected Output
```
Reading the databases...
Done in 0.147 seconds!

[============]
[Final Report]
[============]

Statistics over all industries in 2018:
=========================================================
Number of FIPS areas in report       29

Gross annual wages                   $71,726,471,120
Area with maximum annual wages       Salt Lake County, Utah
Maximum reported wage                $38,506,959,767

Total number of establishments       103,581
Area with most establishments        Salt Lake County, Utah
Maximum # of establishments          46,269

Gross annual employment level        1,478,496
Area with maximum employment         Salt Lake County, Utah
Maximum reported employment level    701,784


Statistics over the software publishing industry in 2018:
=========================================================
Number of FIPS areas in report       16

Gross annual wages                   $1,287,190,117
Area with maximum annual wages       Utah County, Utah
Maximum reported wage                $670,059,880

Total number of establishments       642
Area with most establishments        Salt Lake County, Utah
Maximum # of establishments          405

Gross annual employment level        10,637
Area with maximum employment         Utah County, Utah
Maximum reported employment level    5,303
```

Pass: Yes

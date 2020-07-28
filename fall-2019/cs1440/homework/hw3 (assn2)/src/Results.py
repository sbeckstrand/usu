class Results:
    def __init__(self, type, path):
        # Define the initial variables that will store our results
        self.areas = 0
        self.fips = 0
        self.grossWages = 0
        self.maxWages = 0
        self.maxWagesCounty = ""
        self.establishments = 0
        self.maxEstablishments = 0
        self.maxEstablishmentsCounty = ""
        self.employees = 0
        self.maxEmployees = 0
        self.maxEmployeesCounty = ""

        # Open the area_titles.csv file, create an empty dictionary and then go through each line splitting up the area code and area' title and add them to the dictionary.
        areaFile = open(path + '/area_titles.csv', 'r')
        areaDict = {}

        for line in areaFile:
            line = line.split(",", 1)
            for i in range(0, len(line)):
                line[i] = line[i].replace('"', "")
                line[i][1].replace("\n", "")

            if (line[0][2:] == "000"):
                continue
            elif (line[0][:2] == "US"):
                continue
            elif (line[0][0] == "C" or line[0][:2] == "ar"):
                continue

            areaDict[line[0]] = line[1]
        # Close file now that we are done
        areaFile.close()

        # Open the annual file that stores our data
        annualFile = open(path + '/2018.annual.singlefile.csv', 'r')
        # Go through each line, split each entry into its own entry into an array. Set filter criteria based on the type of data we are looking for.
        for line in annualFile:
            line = line.split(",")
            currentFips = line[0].replace('"', "")
            if (type == "all"):
                typeValues = ['"0"', '"10"']
            else:
                typeValues = ['"5"', '"5112"']

            # Exclude overlapping data
            if (currentFips[2:] != "000" and currentFips[:2] != "US" and currentFips[0] != "C"):
                # If current line meets our search critera, grab data from line and update variables.
                if (line[1] == typeValues[0] and line[2] == typeValues[1]):
                    if (int(line[10]) > int(self.maxWages)):
                        self.maxWages = int(line[10])
                        self.maxWagesCounty = areaDict[currentFips].rstrip()
                    if (int(line[9]) > int(self.maxEmployees)):
                        self.maxEmployees = int(line[9])
                        self.maxEmployeesCounty = areaDict[currentFips].rstrip()
                    if (int(line[8]) > int(self.maxEstablishments)):
                        self.maxEstablishments = int(line[8])
                        self.maxEstablishmentsCounty = areaDict[currentFips].rstrip()


                    self.grossWages += int(line[10])
                    self.employees += int(line[9])
                    self.establishments += int(line[8])
                    self.areas += 1

        # Close file now that we are done. 
        annualFile.close()

import time
import sys
from Report import Report
from Results import Results

rpt = Report()

# Check if an argument was provided, if not, prit a message on how to use the application.
try:
    dataPath = sys.argv[1]
except (IndexError):
    print("Usage: src/main.py DATA_DIRECTORY")
    sys.exit()


# Start caclulating how long the application takes to run, generate results for all and software industries.
print("Reading the databases...", file=sys.stderr)
before = time.time()
all = Results("all", dataPath)
soft = Results("soft", dataPath)
after = time.time()
print(f"Done in {after - before:.3f} seconds!", file=sys.stderr)

# Populate rpt object with all industry results
rpt.all.num_areas           = all.areas
rpt.all.gross_annual_wages  = all.grossWages
rpt.all.max_annual_wage     = (all.maxWagesCounty, all.maxWages)
rpt.all.total_estab         = all.establishments
rpt.all.max_estab           = (all.maxEstablishmentsCounty, all.maxEstablishments)
rpt.all.total_empl          = all.employees
rpt.all.max_empl            = (all.maxEmployeesCounty, all.maxEmployees)

# Populate rpt object with software industry results
rpt.soft.num_areas          = soft.areas
rpt.soft.gross_annual_wages = soft.grossWages
rpt.soft.max_annual_wage    = (soft.maxWagesCounty, soft.maxWages)
rpt.soft.total_estab        = soft.establishments
rpt.soft.max_estab          = (soft.maxEstablishmentsCounty, soft.maxEstablishments)
rpt.soft.total_empl         = soft.employees
rpt.soft.max_empl           = (soft.maxEmployeesCounty, soft.maxEmployees)


# Print the completed report
print(rpt)

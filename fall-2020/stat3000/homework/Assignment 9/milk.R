setwd("C:/Users/Stephen/git/usu/fall-2020/stat3000/homework/Assignment 9")

milkData <- read.table('milksample.txt', header = TRUE)
length(milkData)
# Estimated mean of protein content based on our sample
mean(milkData$protein)

# standard error of estimated mean
sd(milkData$protein) / sqrt(length(milkData$protein))

# Get estimated proportion of population (1337 cows) on barley diet from sample
barleyDiet = subset(milkData,Diet=="barley")
sampleProportion <- length(barleyDiet$Diet) / length(milkData$Diet)
sampleProportion
sampleProportion * 1337



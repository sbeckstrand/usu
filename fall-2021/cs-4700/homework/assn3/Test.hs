import Lists 

main = do
    print(take 3 countingNumbers)
    print(take 5 (multiplesOfNumbers 5))
    print(take 5 woodallNumbers)
    print(take 20 padovanNumbers)
    print(order (>) [1,3,5] [2,4])
    print(pairUp [1,2,3,4,5])
    print(pruneConsecutive [1,1,1,1,1,1,1,1,2,2])
    print(runLengthEncoding [7, 7, 4, 7, 7, 7])
    print(listPairApply [(+),(*)] (pairUp (take 8 countingNumbers)))
    print((composeList []))
    print ((composeList []))
module Lists where
  
  -- Counting Numbers function. It generates an unbounded list of the counting numbers (1,2,3,...,ꝏ)
  countingNumbers :: [Int]
  countingNumbers = [1..]

  -- This function generates an unbounded list of multiples of input number. 
  multiplesOfNumbers :: Int -> [Int]
  multiplesOfNumbers n = [ x | x <- [1..], (mod x n) == 0]

  -- This function generates an unbounded list of woodall Numbers (Where W_x = x * 2^x - 1). 
  woodallNumbers :: [Int]
  woodallNumbers = [ x * 2^x -1 | x <- [1..]]

  -- Helper function for `PadovanNumbers`. This function takes an integer and outputs the correct Padovan number by using the recursive function to calculate it. 
  getPadovan :: Int -> Int
  getPadovan 0 = 1
  getPadovan 1 = 1
  getPadovan 2 = 1
  getPadovan n = getPadovan (n - 2) + getPadovan (n - 3)

  -- This function generates an unbounded list of padovan Numbers. 
  padovanNumbers :: [Int]
  padovanNumbers = [ getPadovan (x) | x <- [1..]]

  -- This function takes an operator (Ideally <) and two lists and then combines them into a sorted list based on the operator provided. 
  order :: (Int -> Int -> Bool) -> [Int] -> [Int] -> [Int]
  order op (x:xIn) (y:yIn) = if x `op` y
    then x: (order (op) xIn (y:yIn))
    else y: (order (op) (x:xIn) yIn)
  order op [] xIn = xIn
  order op xIn [] = xIn

  -- Function used to prune first n numbers from a list
  prune :: Int -> [Int] -> [Int]
  prune n xs = if n == 0 
      then xs
      else prune (n-1) (tail xs)

  -- This function takes a list and outputs another list with each item paired together. 
  pairUp :: [Int] -> [[Int]]
  pairUp xs = do
    if null xs
      then []
    else if length xs == 1
      then [[head xs]]
    else
      (head xs : [xs !! 1]) : pairUp (prune 2 xs)

  -- Function to count consecutive values in a list
  countConsecutive :: [Int] -> Int
  countConsecutive xs = do
    let count = 1
    let current = head xs
    if length xs < 2
      then count
    else if head (tail xs) == current
      then 1 + countConsecutive (tail xs)
      else count

  -- Function to delete consecutive values in a list. 
  pruneConsecutive :: [Int] -> [Int]
  pruneConsecutive xs = do
    if not (null xs)
      then do 
        let current = head xs
        if not (null (tail xs))
          then do
            if head (tail xs) == current
              then pruneConsecutive (tail xs)
              else tail xs
          else tail xs
      else []
        
  -- This function takes a list, and then outputs a list of how many many times a value appears consecutively. 
  runLengthEncoding :: [Int] -> [(Int,Int)]
  runLengthEncoding xs = do
    let x = []
    if not (null xs)
      then do
        let current = head xs
        let count = countConsecutive xs
        if not (null (pruneConsecutive xs))
          then do
            x ++ [(current,count)] ++ runLengthEncoding (pruneConsecutive xs)
          else x ++ [(current,count)]
      else x

  -- This function takes a list of opperators, and then cycles through them to compute against values in a list of pairs. 
  listPairApply :: [(Int -> Int -> Int)] -> [[Int]] -> [Int]
  listPairApply fs xs = do 
    if not (null xs)
      then do
        let currentPair = head xs
        let currentOp = head fs
        (head currentPair `currentOp` last currentPair) : listPairApply (tail fs ++ [head fs]) (tail xs)
      else []

  -- This is a helper function for composeList that allows us to take an empty list and if so, just output the provided Int.
  pass :: Int -> Int 
  pass x = x

  -- This function takes a list of unary functions and finds the composition of each of the functions processed in provided order.
  composeList :: [Int -> Int] -> Int
  composeList [] = pass
  composeList (f:fs) = f . composeList fs



  
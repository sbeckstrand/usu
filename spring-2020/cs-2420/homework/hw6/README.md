Plan: 

1) Write code for Union Find with following properties
- Uses path compression
- Uses smart union (by size)
2) Create a series of unit tests to make sure things work properly. 

Take file with numbers as input. Change between blue and red to reach each number (start with red. )
Check if space is already taken. If so, go to next number.


Possible Unit Tests: 

Tile being set correctly
Winning Connection
Attempt to fill already filled cell
Check neighbors
 
 
 Each node needs to know the following: 
 
 Who is my neighbor? 
 If Node is at the border. 
 - Example, if using 11 x 11 board, we would need to keep track that 11, 22, 33, 44, 1, 12, 23, 34, etc are on the border. 
 - Track tops and bottoms too. 

 
 
 Need to toString method that outputs the hex board. 
 
 
 Top neighbors: 
 
 
 Bottom Neighbor's: 
 
 left neighbors:
 
 right neighbors:
 
 center neighbors: 
 - (current - length), (current - length - 1), (current - 1), (current + 1), (current + length - 1), (current + length)
 - Example: 11 x 11 --> -11, -10, -1, 10, 11
 
 Check corners
 
 
 getNeighbords
 
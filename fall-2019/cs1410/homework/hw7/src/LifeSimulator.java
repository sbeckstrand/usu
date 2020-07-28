
// Define simulator class used create a grid of cells and to call on our patterns and add them to our grid. It will also include a method to check for cycle updates to our grid.
public class LifeSimulator {
    private int width;
    private int height;
    private boolean[][] cells;


    // Define a grid of cells based on the width and height params passed into the constructor.
    public LifeSimulator(int sizeX, int sizeY) {
        this.width = sizeX;
        this.height = sizeY;

        boolean[][] cells = new boolean[this.width][this.height];

        this.cells = cells;

    }

    //Method to return width
    public int getSizeX() {
        return this.width;
    }

    //Method to return height
    public int getSizeY() {
        return this.height;
    }

    //Method to return if cell is alive or dead
    public boolean getCell(int x, int y) {
        return this.cells[x][y];
    }

    // Add our pattern to our grid.
    public void insertPattern(Pattern pattern, int startX, int startY) {
        for (int i = 0; i < pattern.getSizeX(); i++) {
            for (int j = 0; j < pattern.getSizeY(); j++) {
                cells[startX + i][startY + j] = pattern.getCell(i, j);
            }
        }

    }

    // Loop through each cell and see if it should be updated to be alive or dead based on the following logic:
        //    Any live cell with fewer than two live neighbours dies, as if by underpopulation.
        //    Any live cell with two or three live neighbours lives on to the next generation.
        //    Any live cell with more than three live neighbours dies, as if by overpopulation.
        //    Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    public void update() {
        boolean[][] updatedCells = new boolean[this.width][this.height];


        // Loop through each cell besides the border (which are all set to false)
        for (int i = 1; i < this.width - 1 ; i++) {
            for (int j = 1; j < this.height - 1; j++) {
                // Set initial variables that we will update as we check each cell
                boolean currentState = getCell(i, j);
                int activeNeighbors = 0;

                // For each cell, check the 8 cells around it
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {



                        if ((k != i) || (l != j)) {
                            if (getCell(k, l)) {

                                activeNeighbors +=  1;
                            }
                        }
                    }
                }

                // If our cell is already active
                if (currentState) {
                    // Check if it has 2 or 3 active neighbors, if so, keep it true (alive);
                    if ((activeNeighbors == 2) || (activeNeighbors == 3)) {
                        updatedCells[i][j] = true;
                    }
                    // If it has less than 2, or more than 3 active neighbors, set it to false (dead)
                    else {
                        updatedCells[i][j] = false;
                    }
                }



                // If the cell is dead, check to see if it has 3 active neighbors, if so, set it to true (alive)
                else if (!currentState){

                    if (activeNeighbors == 3) {
                        updatedCells[i][j] = true;
                    }
                }
            }
        }

        // update our primary grid to match our updated grid
        this.cells = updatedCells;

    }


};
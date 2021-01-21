public class PatternAcorn extends Pattern{
    private boolean[][] cells;

    public PatternAcorn() {
        int width = 9;
        int height = 5;
        boolean[][] cells = new boolean[width][height];
        this.cells = cells;

        cells[1][3] = true;
        cells[2][1] = true;
        cells[2][3] = true;
        cells[4][2] = true;
        cells[5][3] = true;
        cells[6][3] = true;
        cells[7][3] = true;
    }

    @Override
    public int getSizeX() {
        return 9;
    }

    @Override
    public int getSizeY() {
        return 5;
    }

    @Override
    public boolean getCell(int x, int y) {
        return this.cells[x][y];
    }
}


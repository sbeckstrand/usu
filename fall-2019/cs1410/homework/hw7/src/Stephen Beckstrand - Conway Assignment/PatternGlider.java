public class PatternGlider extends Pattern{
    private boolean[][] cells;

    public PatternGlider() {
        int width = 5;
        int height = 5;
        boolean[][] cells = new boolean[width][height];
        this.cells = cells;

        cells[1][1] = true;
        cells[1][3] = true;
        cells[2][2] = true;
        cells[2][3] = true;
        cells[3][2] = true;

    }

    @Override
    public int getSizeX() {
        return 5;
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

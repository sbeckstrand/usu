public class PatternBlock extends Pattern{
    private boolean[][] cells;

    public PatternBlock() {
        int width = 4;
        int height = 4;
        boolean[][] cells = new boolean[width][height];
        this.cells = cells;


        cells[1][1] = true;
        cells[1][2] = true;
        cells[2][1] = true;
        cells[2][2] = true;


    }

    @Override
    public int getSizeX() {
        return 4;
    }

    @Override
    public int getSizeY() {
        return 4;
    }

    @Override
    public boolean getCell(int x, int y) {
        return this.cells[x][y];
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Hex {
    ArrayList<String> tiles;
    DisjointSet sets;
    int width;
    int height;
    int turns;
    String winner;
    boolean blueTurn = true;

    public Hex(int w, int h, String file) throws FileNotFoundException {

        width = w;
        height = h;
        tiles = new ArrayList<String>();
        sets = new DisjointSet((width * height) + 4);

        for (int i = 0; i < (width * height) + 4; i++ ) {
            tiles.add("0");
        }

        readMoves(file);

        if (winner == null) {
            System.out.println("Unfortunately, there was no winner!");
        }
    }

    private void readMoves(String file) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(file));
        while (reader.hasNext() && winner == null) {
            String val = reader.nextLine();
            int index = Integer.parseInt(val);
            processTurn(index - 1);
        }
    }

    private void processTurn(int tile) {

        if (!tiles.get(tile).equals("0")) {
            System.out.println("That tile has already been selected. Please select a different one!");
            return;
        }

        ArrayList<Integer> neighbors = findNeighbors(tile);
        if (blueTurn) {
            tiles.set(tile, "B");
        } else {
            tiles.set(tile, "R");
        }

        for (int neighbor: neighbors) {
            if (blueTurn) {
                if (tiles.get(neighbor) == "B" || neighbor == (height * width) || neighbor == (height * width) + 1) {
                    sets.union(tile, neighbor);
                }
            } else {
                if (tiles.get(neighbor) == "R" || neighbor == (height * width) + 2 || neighbor == (height * width) +3) {
                    sets.union(tile, neighbor);
                }
            }
        }

        turns += 1;
        String turn;
        if (blueTurn) {
            turn = "blue";
        } else {
            turn = "red";
        }
        if (hasWon(turn)) {
            winner = turn;
            System.out.println("-------> " + winner + " has won after " + turns + " attempted Moves! Here is the final board.");
        }

        blueTurn = !blueTurn;

    }

    private ArrayList<Integer> findNeighbors(int val) {
        ArrayList<Integer> neighbors = new ArrayList<Integer>();

        // Check corners

        if (val == 0) { // top left;
            Collections.addAll(neighbors,  1, width );

            if (blueTurn) {
                neighbors.add(width * height);
            } else {
                neighbors.add((width * height) + 2);
            }
        }

        else if (val == width -1) { // top right
            Collections.addAll(neighbors, width -2, (width * 2) - 1, (width * 2) -2 );

            if (blueTurn) {
                neighbors.add((width * height) + 1);
            } else {
                neighbors.add((width * height) + 2);
            }
        }

        else if (val == height * (width - 1)) { // bottom left
            int current = height * (width - 1);
            Collections.addAll(neighbors, current + 1, current - width, (current - width) + 1  );

            if (blueTurn) {
                neighbors.add((width * height));
            } else {
                neighbors.add((width * height) + 3);
            }
        }

        else if (val == (height * width) - 1) { // bottom right
            int current = (height * width) - 1;
            Collections.addAll(neighbors, current - 1, current - width  );
            if (blueTurn) {
                neighbors.add((width * height) + 1);
            } else {
                neighbors.add((width * height) + 3);
            }
        }

        //Check edges
        else if (val < height) { // Top edge

            Collections.addAll(neighbors, val -1 , val + 1, val + width, (val + width) - 1 );
            if (!blueTurn) {
                neighbors.add((width * height) + 2);
            }
        }

        else if ((val + 1) % width == 0) { // right edge
            Collections.addAll(neighbors, val -1 , val + width, val - width, (val + width) - 1 );

            if (blueTurn) {
                neighbors.add((width * height) + 1);
            }
        }

        else if (val % height == 0) { // left edge
            Collections.addAll(neighbors, val + 1 , val + width, val - width, (val - width) + 1 );

            if (blueTurn) {
                neighbors.add(width * height);
            }
        }

        else if (val > (height * (width -1))) { // bottom edge
            Collections.addAll(neighbors, val -1 , val + 1, val - width, (val - width) + 1 );
            if (!blueTurn) {
                neighbors.add((width * height) + 3);
            }
        }

        else { // All center pieces
            Collections.addAll(neighbors, val -1 , val + 1, val - width, val + width, (val - width) + 1, (val + width) -1, (val + width) + 1 );
        }


        return neighbors;

    }

    private boolean hasWon(String turn) {
        int blueLeft = (width * height);
        int blueRight = (width * height) + 1;
        int redTop = (width * height) + 2;
        int redBottom = (width * height) + 3;

        if (turn == "red") {
            if (sets.find(redTop) == sets.find(redBottom)) {
                return true;
            }
         }

        else if (turn == "blue") {
            if (sets.find(blueLeft) == sets.find(blueRight)) {
                return true;
            }
        }

        return false;
    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";

        for (int i = 0; i < (width * 3) + 2; i++) {
            output.append(ANSI_RED + "-" + ANSI_RESET);
        }
        output.append("\n");

        for (int i = 0; i < height; i++) {
            int start = i * width;
            for (int s = 0; s < i; s++) {
                output.append(" ");
            }
            output.append(ANSI_BLUE +'\\' + ANSI_RESET + "  ");
            for (int j = 0; j < width; j++){
                String current = tiles.get(start + j);
                if (current == "B") {
                    output.append(ANSI_BLUE + current + ANSI_RESET);
                } else if (current == "R") {
                    output.append(ANSI_RED + current + ANSI_RESET);
                } else {
                    output.append(current);
                }
                output.append("  ");
            }
            output.append(ANSI_BLUE + '\\' + ANSI_RESET);
            output.append("\n");
        }
        for (int s = 0; s < height + 1; s++) {
            output.append(" ");
        }
        for (int i = 0; i < (width * 3) + 2; i++) {
            output.append(ANSI_RED + "-" + ANSI_RESET);
        }
        return output.toString();
    }
}

import java.io.FileNotFoundException;

// Main class acts as our driver. It will instantiate two different Hex Games and output the boards once they have been built/played.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Hex game1 = new Hex(11, 11, "moves.txt");
        String board1 = game1.toString();
        System.out.println(board1);

        Hex game2 = new Hex(11, 11, "moves2.txt");
        String board2 = game2.toString();
        System.out.println(board2);
    }
}

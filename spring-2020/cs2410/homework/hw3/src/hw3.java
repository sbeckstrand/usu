import javafx.application.Application;
import java.util.Scanner;

// Main class used to execute one of the other 3 classes part of this assignment.
public class hw3 {

    public static void main(String[] args) {
        // Prompt user with menu showing the three available applications they can run and prompt them to select which one they would like to run.
        Scanner input = new Scanner(System.in);

        System.out.println("Which Application would you like to use?");
        System.out.println(" [1] Welcome to Java Message");
        System.out.println(" [2] Hangman Drawing");
        System.out.println(" [3] Two Rectangles");
        System.out.print("> ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            Application.launch(Welcome.class);
        } else if (choice.equals("2")) {
            Application.launch(Hangman.class);
        } else if (choice.equals("3")) {
            Application.launch(Rectangles.class);
        } else {
            System.out.println("That is not a valid option.");
        }
    }
}

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Scanner;


public class Rectangles extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        // Request initial input for values related to the rectangle.
        Scanner input = new Scanner(System.in);
        System.out.println("Enter first Rectangle dimensions separated by commas (centerX, centerY, width, height)");
        System.out.println("Example: 200, 200, 30, 50");
        System.out.print("> ");
        String rectangle1Input = input.nextLine();
        String[] r1 = rectangle1Input.split(",");
        System.out.println("Enter second Rectangle dimensions separated by commas (centerX, centerY, width, height)");
        System.out.print("> ");

        String rectangle2Input = input.nextLine();
        String[] r2 = rectangle2Input.split(",");

        int r1X, r1Y, r1Width, r1Height, r2X, r2Y, r2Width, r2Height, r1CenterX, r1CenterY, r2CenterX, r2CenterY;

        // For the values for each rectangle, create two rectangle objects and place them in a Pane to later add to our stage.
        try {
            for (int i = 0; i < r1.length; i++) {
                r1[i] =  r1[i].strip();
            }

            for (int i = 0; i < r2.length; i++) {
                r2[i] =  r2[i].strip();
            }

            r1X = Integer.parseInt(r1[0]);
            r1Y = Integer.parseInt(r1[1]);
            r1Width = Integer.parseInt(r1[2]);
            r1Height = Integer.parseInt(r1[3]);
            r1CenterX = r1X + r1Width;
            r1CenterY = r1Y + r1Height;
            System.out.println(r1CenterX);
            System.out.println(r1CenterY);

            r2X = Integer.parseInt(r2[0]);
            r2Y = Integer.parseInt(r2[1]);
            r2Width = Integer.parseInt(r2[2]);
            r2Height = Integer.parseInt(r2[3]);
            r2CenterX = r2X + r2Width;
            r2CenterY = r2Y + r2Height;
            System.out.println(r2CenterX + " " + r2CenterY);

            Pane root = new Pane();
            root.setPrefSize(1000, 700);

            Rectangle rectangle1 = new Rectangle();
            rectangle1.setX(r1CenterX);
            rectangle1.setY(r1CenterY);
            rectangle1.setWidth(r1Width);
            rectangle1.setHeight(r1Height);
            rectangle1.setFill(Color.TRANSPARENT);
            rectangle1.setStrokeWidth(2);
            rectangle1.setStroke(Color.BLACK);
            root.getChildren().add(rectangle1);

            Rectangle rectangle2 = new Rectangle();
            rectangle2.setX(r2CenterX);
            rectangle2.setY(r2CenterY);
            rectangle2.setWidth(r2Width);
            rectangle2.setHeight(r2Height);
            rectangle2.setFill(Color.TRANSPARENT);
            rectangle2.setStroke(Color.BLACK);
            rectangle2.setStrokeWidth(2);
            root.getChildren().add(rectangle2);

            if (overlap(rectangle1, rectangle2)) {
                System.out.println("Blah");
            }

            Scene scene = new Scene (root);

            stage.setTitle("Rectangles");
            stage.setScene(scene);
            stage.show();
        } catch (NumberFormatException e) {
            System.out.println("Sorry, there was an error handling the provided input, please provide input in the format of integers in the following order separated by commas: centerX, CenterY, width, height");
            Platform.exit();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Sorry, not enough arguments were provided. Please provide a centerx, centery, width and height");
            Platform.exit();
        }
    }

    private boolean overlap(Rectangle r1, Rectangle r2) {


        return true;
    }

    private boolean contains(Rectangle r1, Rectangle r2) {
        return true;
    }
}

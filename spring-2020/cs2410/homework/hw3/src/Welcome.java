import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Welcome extends Application {


    @Override
    public void start(Stage stage) {
        // Build Pane to used to contain our nodes as well as set a font to use for our message.
        Pane root = new Pane();
        root.setPrefSize(900,550);
        Font font = Font.font("Arial", FontWeight.BOLD, 35);

        // Set our message and our position and rotation values.
        String message = "Welcome to Java";
        double angle = 90;
        double rotation = 180;
        double xOffset = root.getPrefWidth() / 2;
        double yOffset = root.getPrefHeight() /  2;
        int circleRadius = 100;

        double x;
        double y;

        // For each letter in our message, add it as a text node to our pane and rotate it so the message is displayed in a circle.
        for (int i = 0; i < message.length(); i++) {
            x = xOffset - Math.cos(Math.toRadians(rotation)) * circleRadius;
            y = yOffset - Math.sin(Math.toRadians(rotation)) * circleRadius;

            Text t = new Text(x, y, String.valueOf(message.charAt(i)));
            t.setFont(font);
            t.setRotate(angle);
            root.getChildren().add(t);
            angle += (360 / (message.length() + 1));
            rotation += (360/ (message.length() + 1));
        }

        // Create a new scene containing our Pane, add it to our stage, and then show our stage.
        Scene scene = new Scene (root);

        stage.setTitle("Circular Welcome Message");
        stage.setScene(scene);
        stage.show();

    }
}
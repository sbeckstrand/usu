import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class HelloWorldGUI extends Application {

    // Start method which takes a stage, creates a text flow node and adds two text objects to it and then add its to the scene.
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        TextFlow root = new TextFlow();
        Text redHello = new Text("Hello ");
        Text blueWorld = new Text("World!");
        redHello.setFill(Color.RED);
        blueWorld.setFill(Color.BLUE);
        root.getChildren().addAll(redHello, blueWorld);
        primaryStage.setScene(new Scene(root, 100, 100));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

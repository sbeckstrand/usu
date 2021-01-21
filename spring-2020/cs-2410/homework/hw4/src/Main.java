import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        Pane gridContent = new Pane();
        Connect4 grid = new Connect4(80);
        Pane emptyPane = new Pane();


        gridContent.getChildren().add(grid.getDiscPane());
        gridContent.getChildren().add(grid.getGrid());
        gridContent.getChildren().addAll(grid.getColumnOverlay());

        TextFlow statusPane = grid.getStatus();

        root.setCenter(gridContent);
        root.setTop(statusPane);



        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


// References:
    // Almas Baimagambetov Connect 4 Project: https://www.youtube.com/watch?v=B5H_t0A_C14
    // Oracle Docs: Point2d: https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Point2D.html
    // Oracle Docs: Timer: https://docs.oracle.com/javase/7/docs/api/javax/swing/Timer.html


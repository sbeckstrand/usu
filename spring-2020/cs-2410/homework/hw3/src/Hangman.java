import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Hangman extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Build Pane to used to contain our nodes\
        Pane root = new Pane();
        root.setPrefSize(900, 550);

        // Create the nodes necessary to make up the beam that will hold the stick figure.
        Arc beamBase = new Arc();
        beamBase.setCenterX(200);
        beamBase.setCenterY(400);
        beamBase.setRadiusX(60);
        beamBase.setRadiusY(60);
        beamBase.setLength(180);
        beamBase.setType(ArcType.ROUND);
        beamBase.setFill(Color.TRANSPARENT);
        beamBase.setStrokeWidth(5);
        beamBase.setStroke(Color.BLACK);
        root.getChildren().add(beamBase);

        Line beamPrimary = new Line(200, 340, 200, 80);
        beamPrimary.setStrokeWidth(5);
        root.getChildren().add(beamPrimary);

        Line beamOverhang = new Line(200, 80, 300, 80);
        beamOverhang.setStrokeWidth(5);
        root.getChildren().add(beamOverhang);

        Line beamSecondary = new Line(300, 80, 300, 110);
        beamSecondary.setStrokeWidth(5);
        root.getChildren().add(beamSecondary);

        Line rope = new Line(300, 110, 300, 120);
        rope.setStrokeWidth(3);
        root.getChildren().add(rope);

        Line noose = new Line( 298, 183, 302, 183);
        noose.setStrokeWidth(3);
        root.getChildren().add(noose);

        // Create the nodes necessary to build the stick figure body.
        Circle head = new Circle();
        head.setCenterX(300);
        head.setCenterY(152);
        head.setRadius(30);
        head.setStrokeWidth(2);
        head.setFill(Color.TRANSPARENT);
        head.setStroke(Color.BLACK);
        root.getChildren().add(head);

        Line body = new Line(300, 182, 300, 280);
        body.setStrokeWidth(2);
        root.getChildren().add(body);

        Line leftArm = new Line(300, 210, 260, 190);
        leftArm.setStrokeWidth(2);
        root.getChildren().add(leftArm);

        Line rightArm = new Line(300, 210, 340, 190);
        rightArm.setStrokeWidth(2);
        root.getChildren().add(rightArm);

        Line leftLeg = new Line(300, 280, 270, 330);
        leftLeg.setStrokeWidth(2);
        root.getChildren().add(leftLeg);

        Line rightLeg = new Line(300, 280, 330, 330);
        rightLeg.setStrokeWidth(2);
        root.getChildren().add(rightLeg);

        // Create a new scene containing our Pane, add it to our stage, and then show our stage.
        Scene scene = new Scene (root);
        stage.setTitle("Hangman");
        stage.setScene(scene);
        stage.show();
    }
}

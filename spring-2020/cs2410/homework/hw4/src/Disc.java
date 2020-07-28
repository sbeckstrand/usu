import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disc extends Circle {
    Boolean red;

    public Disc(Boolean red, int size) {

        super(size / 2, red ? Color.RED: Color.YELLOW);
        this.red = red;

        setCenterX(size / 2);
        setCenterY(size / 2);
    }
}

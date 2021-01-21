import java.util.ArrayList;

public class TestShape {
    public static void main(String[] args) {
        ArrayList<Shape> shapesArray = new ArrayList<Shape>();

        shapesArray.add(new Square(5));
        shapesArray.add(new Square(10));
        shapesArray.add(new Cube(5));
        shapesArray.add(new Cube(10));
        shapesArray.add(new EquilateralTriangle(5));
        shapesArray.add(new EquilateralTriangle(10));
        shapesArray.add(new Pyramid(5));
        shapesArray.add(new Pyramid(10));

        for (Shape item: shapesArray) {
            System.out.println(item.getArea());
        }
    }
}

public class Cube extends Shape {

    public Cube(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return this.side * this.side * this.side;
    }
}

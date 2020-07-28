public class Square extends Shape {

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return this.side * this.side;
    }
}

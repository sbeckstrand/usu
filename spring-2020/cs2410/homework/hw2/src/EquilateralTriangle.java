
public class EquilateralTriangle extends Shape {

    public EquilateralTriangle(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return (Math.sqrt(3) / 4) * (this.side * this.side);
    }
}

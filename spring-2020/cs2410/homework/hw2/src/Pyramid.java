public class Pyramid extends Shape {

    public Pyramid(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return ((Math.sqrt(3) / 4) * (this.side * this.side)) * 4;
    }
}

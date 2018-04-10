class Rectangle {

    private int width, height;

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    int getArea() {
        return height * width;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

class Square extends Rectangle {

    Square(int size) {
        super(size, size);
    }

    // LISKOV violation, overriding common parent behaviour
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}


class LiskovSubstitution {
    private static void testObjects(Rectangle r) {
        r.setWidth(10);
        System.out.println(
                "Expected area of rectangle "
                + 10 * r.getHeight()
                + ", got " + r.getArea());
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(10, 15);
        testObjects(rectangle);

        Rectangle square = new Square(15);
        testObjects(square);
    }

}


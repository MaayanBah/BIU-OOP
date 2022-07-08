// 315112672 Maayan Bahar

/**
 * An object representing a point and includes the location of the point on X-axis and Y-axis.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * @param x The location of the point on X-axis.
     * @param y The location of the point on Y-axis.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other The second Point.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((x - other.getX()), 2) + Math.pow((y - other.getY()), 2));
    }

    /**
     * @param other The second Point.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return Math.abs(x - other.getX()) <= 1E-10 && Math.abs(y - other.getY()) <= 1E-10;
    }

    /**
     * @return the x value of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y value of this point
     */
    public double getY() {
        return y;
    }
}
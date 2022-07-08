// 315112672 Maayan Bahar

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * @param dx The change in position on the `x` axe.
     * @param dy The change in position on the `y` axe.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param angle The angle of the ball movement.
     * @param speed The speed of the ball.
     * @return Velocity by angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(angle * (Math.PI / 180)) * speed;
        double dy = Math.sin(angle * (Math.PI / 180)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return The dx.
     */
    public double getXVelocity() {
        return dx;
    }

    /**
     * @return The dy.
     */
    public double getYVelocity() {
        return dy;
    }

    /**
     * @param point A location which will be altered.
     * @return Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point point) {
        return new Point(point.getX() + dx, point.getY() + dy);
    }
}
// 315112672 Maayan Bahar

import biuoop.DrawSurface;

/**
 * A class for graphical ball.
 */
public class Ball implements Sprite {
    private Velocity velocity;
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private final GameEnvironment environment;

    /**
     * @param center The center point of the ball.
     * @param radius The balls radius.
     * @param color  The balls color.
     * @param gameEnvironment The game environment
     */
    public Ball(Point center, int radius, java.awt.Color color, GameEnvironment gameEnvironment) {
        this(center, radius, color, new Velocity(0, 0), gameEnvironment);
    }


    /**
     * @param center The center point of the ball.
     * @param radius The balls radius.
     * @param color  The balls color.
     * @param velocity  The balls velocity.
     * @param gameEnvironment The game environment
     */
    public Ball(Point center, int radius, java.awt.Color color, Velocity velocity, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = velocity;
        this.environment = gameEnvironment;
    }

    /**
     * @param x      The x value of the balls center.
     * @param y      The y value of the balls center.
     * @param radius The balls radius.
     * @param color  The balls color.
     * @param gameEnvironment The game environment
     */
    public Ball(double x, double y, int radius, java.awt.Color color, GameEnvironment gameEnvironment) {
        this(new Point(x, y), radius, color, gameEnvironment);
    }

    /**
     * Move one step.
     */
    public void timePassed() {
        moveOneStep();
    }


    /**
     * @return The x value of the balls center.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return The y value of the balls center.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return The balls size.
     */
    public int getSize() {
        return (int) (Math.pow(radius, 2) * 3.14);
    }

    /**
     * @return The balls size.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @return The balls color.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * @param surface The drawing surface for the ball to be drawn on.
     * draw the ball on the given DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }

    /**
     * @param velocity The velocity to set.
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * @param x The x value of the point we will set as the new center.
     * @param y The y value of the point we will set as the new center.
     */
    private void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * @param dx The change in position on the `x` axe.
     * @param dy The change in position on the `y` axe.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return Velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     *            Use one step according to the ball velocity.
     */
    public void moveOneStep() {
        Line line = new Line(center, getVelocity().applyToPoint(this.center));
        double angleOfSlope = Math.atan(line.getSlope());
        if ((angleOfSlope < 0) == (line.end().getY() - line.start().getY() > 0)) {
            angleOfSlope += Math.PI;
        }

        line = new Line(line.start(),
                        new Point(line.end().getX() + radius * Math.cos(angleOfSlope),
                                  line.end().getY() + radius * Math.sin(angleOfSlope)));
        CollisionInfo collisionInfo = environment.getClosestCollision(line);
        if (collisionInfo == null) {
            center = getVelocity().applyToPoint(this.center);
        } else {
            velocity = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), velocity, this);
        }
    }

    /**
     * @param g Add the ball to the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @param gameLevel Remove the block from the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
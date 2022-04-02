// 315112672 Maayan Bahar

package com.company;

import biuoop.DrawSurface;

import biuoop.GUI;

/**
 * A class for graphical balls.
 */
public class Ball {
    private Velocity velocity;
    private Point center;
    private final int radius;
    private java.awt.Color color;

    /**
     * @param center The center point of the ball.
     * @param radius The balls radius.
     * @param color  The balls color.
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this(center, radius, color, new Velocity(0, 0));
    }

    /**
     * @param center The center point of the ball.
     * @param radius The balls radius.
     * @param color  The balls color.
     * @param velocity  The balls velocity.
     */
    public Ball(Point center, int radius, java.awt.Color color, Velocity velocity) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = velocity;
    }

    /**
     * @param x      The x value of the balls center.
     * @param y      The y value of the balls center.
     * @param radius The balls radius.
     * @param color  The balls color.
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this(new Point(x, y), radius, color);
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
     *                draw the ball on the given DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), (int) radius);
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

// todo grammar, and also why does it work?
    /**
     * @param right The right bottom point of the rectangle.
     * @param left The left top point of the rectangle.
     */
    public void moveOneStepInside(Point left, Point right) {
        center = getVelocity().applyToPoint(this.center);

        if ((center.getX() + radius) >= right.getX()) {
            setVelocity(velocity.getXVelocity() * -1, velocity.getYVelocity());
            setCenter(right.getX() - (center.getX() - right.getX() + 2 * radius), center.getY());
        }

        if (center.getX() - radius <= left.getX()) {
            setVelocity(velocity.getXVelocity() * -1, velocity.getYVelocity());
            setCenter(left.getX() + (left.getX() - center.getX()  + 2 * radius), center.getY());
        }
        if ((center.getY() + radius) >= right.getY()) {
            setVelocity(velocity.getXVelocity(), velocity.getYVelocity() * -1);
            setCenter(center.getX(), right.getY() - (center.getY() - right.getY() + 2 * radius));
        }
        if (center.getY() - radius <= left.getY()) {
            setVelocity(velocity.getXVelocity(), velocity.getYVelocity() * -1);
            setCenter(center.getX(),  left.getY() + (left.getY() - center.getY() + 2 * radius));
        }

    }
    /**
     * @param gui The graphical user interface.
     *            Use one step according to the ball velocity.
     */
    public void moveOneStep(GUI gui) {
        moveOneStepInside(new Point(0, 0),
                          new Point(gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight()));
    }
}
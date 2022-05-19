// 315112672 Maayan Bahar

package com.company;

import java.util.ArrayList;

/**
 * A class for graphical rectangle.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private final Line top;
    private final Line botoom;
    private final Line left;
    private final Line right;


    /**
     * @param upperLeft The rectangles upper left point.
     * @param width The rectangle's width.
     * @param height The rectangle's height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point downRight = new Point(upperRight.getX(), downLeft.getY());
        this.top = new Line(upperLeft, upperRight);
        this.left = new Line(downLeft, upperLeft);
        this.right = new Line(downRight, upperRight);
        this.botoom = new Line(downLeft, downRight);

    }

    private boolean isIntersecting(Line line) {
        return top.isIntersecting(line)
                || botoom.isIntersecting(line)
                || left.isIntersecting(line)
                || right.isIntersecting(line);
    }

    /**
     * @return The top line.
     */
    public Line getTop() {
        return top;
    }

    /**
     * @return The bottom line.
     */
    public Line getBottom() {
        return botoom;
    }

    /**
     * @return The left line.
     */
    public Line getLeft() {
        return left;
    }

    /**
     * @return The right line.
     */
    public Line getRight() {
        return right;
    }


    /**
     * @param line The line to be examined for intersection.
     * @return A (possibly empty) List of intersection points with the specified line.
     */
        public ArrayList<Point> intersectionPoints(Line line) {
            ArrayList<Point> list = new ArrayList<Point>();

            if (isIntersecting(line)) {
            if (top.intersectionWith(line) != null) {
                list.add(top.intersectionWith(line));
            }
            if (botoom.intersectionWith(line) != null) {
                list.add(botoom.intersectionWith(line));
            }
            if (left.intersectionWith(line) != null) {
                list.add(left.intersectionWith(line));
            }
            if (right.intersectionWith(line) != null) {
                list.add(right.intersectionWith(line));
            }
        }

        return list;
    }

    /**
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return The upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @return The bottom-right point of the rectangle.
     */
    public Point getBottomRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

}
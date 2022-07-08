// 315112672 Maayan Bahar

import java.util.ArrayList;

/**
 * An object representing a Line, includes a start point, an end point, and the slope.
 */
public class Line {
    private final boolean isStartBeforeEnd;
    private final Point start;
    private final Point end;
    private final double slope;
    private final boolean isConstantX;

    /**
     * @param start The start point.
     * @param end The end point.
     */

    public Line(Point start, Point end) {
        // Handling the edge case for lines by the pattern 'x = a'.
        isConstantX = Math.abs(start.getX() - end.getX()) <= 1E-10;

        isStartBeforeEnd = isConstantX ? start.getY() <= end.getY() : start.getX() <= end.getX();
        if (isStartBeforeEnd) {
            this.start = start;
            this.end = end;
        } else {
            this.end = start;
            this.start = end;
        }

        slope = isConstantX ? 0 : ((start.getY() - end.getY())
                                 / (start.getX() - end.getX()));
    }

    /**
     * @param x1 The X value of the start point.
     * @param y1 The Y value of the start point.
     * @param x2 The X value of the end point.
     * @param y2 The Y value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * @return The length of the line.
     */
    public double length() {
        return (Math.sqrt(Math.pow(start.getX() - end.getX(), 2)
                        + Math.pow(start.getY() - end.getY(), 2)));
    }

    /**
     * @return The slope of the line.
     */
    public double getSlope() {
        return slope;
    }

    /**
     * @return The middle of the line.
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2,
                         (start.getY() + end.getY()) / 2);
    }

    /**
     * @return The start point of the line.
     */
    public Point start() {
        return isStartBeforeEnd ? start : end;
    }

    /**
     * @return The end point of the line.
     */
    public Point end() {
        return isStartBeforeEnd ? end : start;
    }

    /**
     * @param xValue The x value of the point.
     * @return The point by the X value, note that the function shouldn't be used
     * for lines by the pattern 'x = a' (obviously).
     */
    private Point pointByXValue(double xValue) {
        return new Point(xValue, slope * (xValue - start.getX()) + start.getY());
    }

    /**
     * @param point The point to be examined.
     * @return True if the line contains the point, False otherwise.
     */
    public boolean contains(Point point) {
        if (isConstantX) {
            return (Math.abs(point.getX() - start.getX()) <= 1E-10
                    && point.getY() >= start.getY()
                    && point.getY() <=  end.getY());
        } else {
            return (pointByXValue(point.getX()).equals(point)
                    && ((start.getX() <= point.getX() && point.getX() <= end.getX())));
        }
    }

    /**
     * @param other The line to be examined for intersection.
     * @return Whether the lines intersect.
     */
    public boolean isIntersecting(Line other) {
        return ((Math.abs(slope - other.slope) <= 1E-10
                && isConstantX == other.isConstantX
                 && (other.contains(start)
                  || other.contains(end)))
                || intersectionWith(other) != null);
    }

    /**
     * @param other The line to be examined for intersection.
     * @return The intersection point.
     *         If the lines don't intersect or intersect at infinite points, null is returned.
     */
    public Point intersectionWith(Line other) {
        Point intersection = null;

        if (Math.abs(other.slope - slope) <= 1E-10 && isConstantX == other.isConstantX) {
            if (start.equals(other.end)) {
                intersection = start;
            } else if (end.equals(other.start)) {
                intersection = end;
            }
        } else {
            Point potentialIntersection;
            if (isConstantX != other.isConstantX) {
                potentialIntersection = isConstantX ? other.pointByXValue(start.getX())
                                                    : pointByXValue(other.start.getX());
            } else {
                potentialIntersection = pointByXValue((pointByXValue(0).getY() - other.pointByXValue(0).getY())
                                                    / (other.slope - slope));
            }

            if (contains(potentialIntersection)  && other.contains(potentialIntersection)) {
                intersection = potentialIntersection;
            }
        }
        return intersection;
    }


    /**
     * @param other A line to be compared with.
     * @return Whether the lines are equal.
     */
    public boolean equals(Line other) {
        return (start.equals(other.start)
             && end.equals(other.end));
    }

    /**
     * @param rect The rectangle to be examined for intersection.
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect)  {
        ArrayList<Point> list = rect.intersectionPoints(this);
        double min = -1;
        Point closest = null;
        for (Point point : list) {
            if (min > start.distance(point)) {
                closest = point;
                min = start.distance(point);
            }
        }

        return closest;
    }
}
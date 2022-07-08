// 315112672 Maayan Bahar

import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * A class for drawing abstract art involving multiple lines, their middles and their intersections.
 */
public class AbstractArtDrawing {

    /**
     * @return A new random line.
     */
    public Line generateRandomLine() {
        Random rand = new Random();
        Point start = new Point(rand.nextInt(400) + 1, rand.nextInt(300) + 1);
        Point end = new Point(rand.nextInt(400) + 1, rand.nextInt(300) + 1);
        return new Line(start, end);
    }

    /**
     * @param line A line to be drawn.
     * @param d The surface we want to draw the line on.
     */
    public void drawLine(Line line, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) line.start().getX(),
                (int) line.start().getY(),
                (int) line.end().getX(),
                (int) line.end().getY());
    }

    /**
     * @param line A line that we wish to draw its middle point.
     * @param d The surface we want to draw the middle on.
     */
    public void drawMiddle(Line line, DrawSurface d) {
        int x = (int) line.middle().getX();
        int y = (int) line.middle().getY();
        int r = 3;
        d.setColor(Color.BLUE);
        d.fillCircle(x, y, r);
    }


    /**
     * @param line A line that we wish to draw its intersection point with the other line.
     * @param otherLine A line that we wish to draw its intersection point with the another line.
     * @param d The surface we want to draw the intersection on.
     */
    public void drawIntersection(Line line, Line otherLine, DrawSurface d) {
        Point intersection = line.intersectionWith(otherLine);
        if (intersection != null) {
            int x = (int) intersection.getX();
            int y = (int) intersection.getY();
            int r = 3;
            d.setColor(Color.RED);
            d.fillCircle(x, y, r);
        }
    }

        /**
         * A function which draws random lines.
         */
    public void drawRandomLines() {
        GUI gui = new GUI("Random Lines", 400, 300);
        DrawSurface d = gui.getDrawSurface();

        Line[] lineArray = new Line[10];
//        lineArray[0] = new Line(276.0, 239.0, 113.0, 277.0);
//        lineArray[1] = new Line(365.0, 285.0, 140.0, 208.0);
        for (int i = 0; i < lineArray.length; ++i) {
            lineArray[i] = generateRandomLine();
            drawLine(lineArray[i], d);
            drawMiddle(lineArray[i], d);
            for (int j = 0; j < i; j++) {
                if (lineArray[i].isIntersecting(lineArray[j])) {
                    drawIntersection(lineArray[i], lineArray[j], d);
                }
            }
        }
        gui.show(d);
    }

    /**
     * @param args Unused.
     */
    public static void main(String[] args) {
        AbstractArtDrawing drawing = new AbstractArtDrawing();
        drawing.drawRandomLines();
    }

}

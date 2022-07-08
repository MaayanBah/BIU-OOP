// 315112672 Maayan Bahar

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * The apple sprite class.
 */
public class Apple implements Sprite {
    private Point center;
    private final int radius;
    private final Color color;
    private boolean isFalling;
    private final int screenHeight;

    /**
     * The constructor of Apple.
     * @param center The center of the apple
     * @param screenHeight The screen's height (where the apple should stop)
     */
    public Apple(Point center, int screenHeight) {
        Random rand = new Random();
        this.radius = rand.nextInt(3) + 5;
        this.center = center;
        this.color = new Color(0x921C1C);
        this.isFalling = false;
        this.screenHeight = screenHeight;
    }

    /**
     * @param surface Draw apple on surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), this.radius);
        surface.setColor(Color.GREEN);
        int rectangleHeight = 3;
        int rectangleWidth = 1;
        surface.fillRectangle((int) (center.getX()) - rectangleWidth / 2,
                (int) (center.getY()) - radius - rectangleHeight / 2,
                rectangleWidth,
                rectangleHeight);
    }

    /**
     * Currently nothing.
     */
    public void timePassed() {
        if (center.getY() >= screenHeight - radius) {
            isFalling = false;
        }

        if (isFalling) {
            center = new Point(center.getX(), center.getY() + 5);
        }
    }

    /**
     * Makes the apple start falling.
     */
    public void fall() {
        isFalling = true;
    }
}


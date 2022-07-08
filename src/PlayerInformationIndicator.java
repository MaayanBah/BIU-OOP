//Maayan Bahar 315112672

import biuoop.DrawSurface;

/**
 * A graphical score indicator.
 */
public class PlayerInformationIndicator implements Sprite {
    private final Counter score;
    private final String string;
    private final int size;
    private final int xPosition;
    private final int yPosition;


    /**
     * @param score The starting score.
     * @param string The message to display.
     * @param size The text size.
     * @param xPosition The text position on the X-axis.
     * @param yPosition The text position on the Y-axis.
     */
    public PlayerInformationIndicator(Counter score, String string, int size, int xPosition, int yPosition) {
        this.score = score;
        this.string = string;
        this.size = size;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (score != null) {
            d.drawText(xPosition, yPosition, string + score.getValue(), size);
        } else {
            d.drawText(xPosition, yPosition, string, size);
        }
    }

    /**
     * Currently do nothing.
     */
    @Override
    public void timePassed() {
    }
}

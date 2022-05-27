//Maayan Bahar 315112672

package com.company;

import biuoop.DrawSurface;

/**
 * A graphical score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * @param score The starting score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(370, 15, "score: " + score.getValue(), 20);
    }

    /**
     * Currently do nothing.
     */
    @Override
    public void timePassed() {
    }
}

// 315112672 Maayan Bahar

package com.company;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The end screen animation.
 */
public class EndScreen implements Animation {
    private final boolean lost;
    private final Counter score;

    /**
     * @param lost Whether the game was lost
     * @param score The ending score
     */
    public EndScreen(boolean lost, Counter score) {
        this.lost = lost;
        this.score = score;
    }

    /**
     * @param d The animation's DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        if (lost) {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 32);
        }
    }

    /**
     * @return Whether the end screen animation should stop
     */
    public boolean shouldStop() {
        return false;
    }

}

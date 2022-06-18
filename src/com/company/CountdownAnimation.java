// 315112672 Maayan Bahar

package com.company;
import biuoop.DrawSurface;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;

    /**
     * @param numOfSeconds Number of seconds that the animation should take place
     * @param countFrom The starting seconds
     * @param gameScreen The sprite collection
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    /**
     * @param d The animation's DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.drawText(370, 300, "" + countFrom, 50);
        this.countFrom -= 1;
    }

    /**
     * @return Whether the animation should stop
     */
    public boolean shouldStop() {
        return countFrom <= 0;
    }

    /**
     * @return The number of seconds that the animation will take
     */
    public double getNumOfSeconds() {
        return numOfSeconds;
    }

    /**
     * @return The current count
     */
    public int getCountFrom() {
        return countFrom;
    }
}

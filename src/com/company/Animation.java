// 315112672 Maayan Bahar

package com.company;
import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * @param d The animation's DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return True if the game should stop, false otherwise.
     */
    boolean shouldStop();

}


// 315112672 Maayan Bahar

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The pause screen animation.
 */
public class PauseScreen implements Animation {
    /**
     * @param d The animation's DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "paused! press space to continue", 32);
    }

    /**
     * @return Whether the animation should stop.
     */
    public boolean shouldStop() {
        return false;
    }
}

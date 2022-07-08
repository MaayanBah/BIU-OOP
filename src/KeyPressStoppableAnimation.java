// 315112672 Maayan Bahar

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * An animation decorator for adding the option to stop the animation using a key press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String key;
    private boolean stop;
    private final Animation innerAnimation;
    private boolean wasAlreadyPressed;

    /**
     * @param sensor The keyboard sensor
     * @param key The key press that should stop the animation
     * @param animation The decorated animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.innerAnimation = animation;
        this.wasAlreadyPressed = true;
    }

    /**
     * @param d The animation's DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        this.innerAnimation.doOneFrame(d);

        if (this.keyboardSensor.isPressed(this.key)) {
            if (!wasAlreadyPressed) {
                this.stop = true;
            }
        } else {
            wasAlreadyPressed = false;
        }
    }

    /**
     * @return Whether the animation should stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
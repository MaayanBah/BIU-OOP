// 315112672 Maayan Bahar


import biuoop.DrawSurface;

/**
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    /**
     * @param animation The animation object to be run.
     * @param gui The animation's gui.
     * @param countdown The countdown display at the beginning of each turn.
     */
    public void run(Animation animation, biuoop.GUI gui, CountdownAnimation countdown) {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        if (!countdown.shouldStop()) {
            double sleepCountdownTime = countdown.getNumOfSeconds() / countdown.getCountFrom();
            while (!countdown.shouldStop()) {
                long startTime = System.currentTimeMillis(); // timing
                DrawSurface d = gui.getDrawSurface();

                countdown.doOneFrame(d);
                gui.show(d);
                long timePassed = System.currentTimeMillis() - startTime;
                sleeper.sleepFor((long) (sleepCountdownTime  * 1000 - timePassed));
            }
        }

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

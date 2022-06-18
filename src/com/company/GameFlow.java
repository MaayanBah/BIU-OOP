// 315112672 Maayan Bahar

package com.company;

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The game flow class. Is in charge of managing and transitioning between all the different game levels.
 */
public class GameFlow {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    private final AnimationRunner animationRunner;
    private final Counter score;
    private boolean lost;
    private GameLevel currentGameLevel;

    /**
     * @param ar The animation runner
     */
    public GameFlow(AnimationRunner ar) {
        this.animationRunner = ar;
        this.score = new Counter(0);
        this.lost = false;
    }

    /**
     * @return The current running game level
     */
    public GameLevel getCurrentGameLevel() {
        return currentGameLevel;
    }

    /**
     * @param levels The list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        GUI gui = new GUI("Game", SCREEN_WIDTH, SCREEN_HEIGHT);

        for (LevelInformation levelInfo : levels) {
            currentGameLevel = new GameLevel(levelInfo, gui, this.animationRunner, score);
            currentGameLevel.initialize();

            while (currentGameLevel.isrunning()) {
                currentGameLevel.run();
            }

            if (currentGameLevel.hasLost()) {
                lost = true;
                break;
            }
        }

        this.animationRunner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY,
                        new EndScreen(lost, score)),
                gui,
                new CountdownAnimation(2, 0, null));
        gui.close();
    }
}


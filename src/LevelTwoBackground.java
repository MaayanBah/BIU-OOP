// 315112672 Maayan Bahar

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * The background of level two.
 */
public class LevelTwoBackground implements Sprite {
    private final ArrayList<RainCloud> rainClouds;

    /**
     * @param game The game flow.
     */
    public LevelTwoBackground(GameFlow game) {
        this.rainClouds = new ArrayList<RainCloud>();
        Random rand = new Random();

        rainClouds.add(new RainCloud(game,
                                     new Point(rand.nextInt(900) - 100, rand.nextInt(100) + 50),
                             (double) (rand.nextInt(85) + 65) / 100));
        rainClouds.add(new RainCloud(game,
                                     new Point(rand.nextInt(900) - 100,
                                               rand.nextInt(100) + 50),
                             (double) (rand.nextInt(85) + 65) / 100));
        rainClouds.add(new RainCloud(game,
                                     new Point(rand.nextInt(900) - 100,
                                               rand.nextInt(100) + 50),
                      (double) (rand.nextInt(85) + 65) / 100));
        rainClouds.add(new RainCloud(game,
                                     new Point(rand.nextInt(900) - 100,
                                               rand.nextInt(100) + 50),
                      (double) (rand.nextInt(85) + 65) / 100));


    }

    /**
     * @param surface The game surface.
     */
    public void drawOn(DrawSurface surface) {
        // Sky
        surface.setColor(new Color(0x56607F));
        surface.fillRectangle(0, 30, GameFlow.SCREEN_WIDTH, GameFlow.SCREEN_HEIGHT);

        // Grass
        int grassHeight = 20;
        surface.setColor(new Color(0x39571F));
        surface.fillRectangle(0, GameFlow.SCREEN_HEIGHT - grassHeight,
                GameFlow.SCREEN_WIDTH, grassHeight);

        // Clouds
        for (RainCloud rainCloud : rainClouds) {
            rainCloud.drawOn(surface);
        }
    }

    /**
     * Currently do nothing.
     */
    public void timePassed() {
        Random rand = new Random();
        for (RainCloud rainCloud : rainClouds) {
            rainCloud.timePassed();
            if (rainCloud.getCloudPosition().getX() >= GameFlow.SCREEN_WIDTH + 100) {
                rainCloud.setCloudPosition(new Point(-100, rand.nextInt(150) + 50));
            }
        }
    }
}

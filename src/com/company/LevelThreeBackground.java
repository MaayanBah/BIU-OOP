// 315112672 Maayan Bahar

package com.company;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The background sprite for level three.
 */
public class LevelThreeBackground implements Sprite {
    private final List<Apple> apples;
    private final int bigTreeX;
    private final int smallTreeX;
    private final int grassHeight;
    private final int bigStumpHeight;
    private final int smallStumpHeight;
    private final int bigTreeY;
    private final List<Cloud> clouds;
    private final GameFlow gameLevel;
    private int collidableNum;
    private final Color skyColor;
    private double skyAdder;

    /**
     * @param bigTreeX The big tree's x value
     * @param smallTreeX The small tree's x value
     * @param gameLevel The game level
     */
    public LevelThreeBackground(int bigTreeX, int smallTreeX, GameFlow gameLevel) {
        Random rand = new Random();
        this.gameLevel = gameLevel;
        this.collidableNum = -1;
        this.apples = new LinkedList<Apple>();
        this.bigTreeX = bigTreeX;
        this.smallTreeX = smallTreeX;
        this.grassHeight = 20;
        this.bigStumpHeight = 215;
        this.smallStumpHeight = 170;
        this.bigTreeY = GameFlow.SCREEN_HEIGHT - bigStumpHeight - grassHeight;
        int smallTreeY = GameFlow.SCREEN_HEIGHT - smallStumpHeight - grassHeight;
        this.clouds = new ArrayList<Cloud>();
        this.skyColor = new Color(204, 255, 255);
        this.skyAdder = 0;
        for (int number = 0; number <= 5; number++) {
            clouds.add(new Cloud(new Point(rand.nextInt(GameFlow.SCREEN_WIDTH),
                    rand.nextInt(140) + 60),
                    (double) (rand.nextInt(100) + 50) / 100));
        }

        // Apples on big tree
        for (int index = 0; index <= 20; index++) {
            apples.add(new Apple(new Point(bigTreeX - 70 + 10 * index,
                                           rand.nextInt(bigTreeY + 50 - (bigTreeY - 80)) + bigTreeY - 80),
                    GameFlow.SCREEN_HEIGHT - grassHeight));
        }

        // Apples on small tree
        for (int index = 0; index <= 15; index++) {
            apples.add(new Apple(new Point(smallTreeX - 50 + 10 * index,
                    rand.nextInt(smallTreeY + 60 - (smallTreeY - 50)) + smallTreeY - 50),
                    GameFlow.SCREEN_HEIGHT - grassHeight));
        }
    }

    /**
     * @param surface The game surface.
     */
    public void drawOn(DrawSurface surface) {
        // Sky
        surface.setColor(new Color(Math.max(skyColor.getRed() - (int) skyAdder, 10),
                                   Math.max(skyColor.getGreen() -  (int) skyAdder, 10),
                                   Math.max(skyColor.getBlue() -  (int) skyAdder, 10)));
        surface.fillRectangle(0, 30, GameFlow.SCREEN_WIDTH, GameFlow.SCREEN_HEIGHT);

        // Grass
        surface.setColor(new Color(0x72C077));
        surface.fillRectangle(0, GameFlow.SCREEN_HEIGHT - grassHeight,
                GameFlow.SCREEN_WIDTH, grassHeight);

        // Big tree Stump
        int stumpWidth = 40;
        surface.setColor(new Color(0x6F5349));
        surface.fillRectangle(bigTreeX,
                            GameFlow.SCREEN_HEIGHT - bigStumpHeight - grassHeight,
                               stumpWidth,
                               bigStumpHeight);
        // Big tree
        surface.setColor(new Color(0x206C34));
        surface.fillCircle(bigTreeX + 15, bigTreeY, 120);

        // small tree Stump
        stumpWidth = 30;
        surface.setColor(new Color(0x6F5349));
        surface.fillRectangle(smallTreeX,
                GameFlow.SCREEN_HEIGHT - smallStumpHeight - grassHeight,
                stumpWidth,
                smallStumpHeight);
        // small tree
        surface.setColor(new Color(0x206C34));
        surface.fillCircle(smallTreeX + 15, GameFlow.SCREEN_HEIGHT - smallStumpHeight - grassHeight, 100);

        // Text
        surface.setColor(new Color(0x513125));
        surface.drawText(bigTreeX + 7, bigTreeY + 130, "Maayan ❤ OOP", 3);
        for (Apple apple: apples) {
            apple.drawOn(surface);
        }

        // Cloud
        for (Cloud cloud : clouds) {
            cloud.timePassed();
            cloud.drawOn(surface);
        }

        // Sign
        int signStandHeight = 60;
        int signStandWidth = 3;
        int signWidth = 120;
        int signHeight = 60;
        surface.setColor(new Color(0x513125));
        surface.fillRectangle((GameFlow.SCREEN_WIDTH - signStandWidth) / 2,
                             GameFlow.SCREEN_HEIGHT - grassHeight - signStandHeight,
                                signStandWidth, signStandHeight);
        surface.setColor(new Color(0x9F6D54));
        surface.fillRectangle((GameFlow.SCREEN_WIDTH - signWidth) / 2,
                              GameFlow.SCREEN_HEIGHT - grassHeight - signHeight - signStandHeight,
                                 signWidth, signHeight);

        // Text
        surface.setColor(new Color(0x4E230D));
        surface.drawText((GameFlow.SCREEN_WIDTH - signWidth) / 2 + 4,
                         GameFlow.SCREEN_HEIGHT - grassHeight - signHeight - signStandHeight + 20,
                         "Collect some apples",
                         12);

        surface.drawText((GameFlow.SCREEN_WIDTH - signWidth) / 2 + 5,
                GameFlow.SCREEN_HEIGHT - grassHeight - signHeight - signStandHeight + 45,
                "before it gets darker",
                12);

        for (Apple apple: apples) {
            apple.drawOn(surface);
        }




    }

    /**
     * Currently do nothing.
     */
    public void timePassed() {
        Random random = new Random();
        if (collidableNum == -1) {
            collidableNum = gameLevel.getCurrentGameLevel().collidables().size();
        }

        skyAdder += 0.1;

        if (collidableNum > gameLevel.getCurrentGameLevel().collidables().size()) {
            collidableNum = gameLevel.getCurrentGameLevel().collidables().size();
            if (!apples.isEmpty()) {
                apples.get(random.nextInt(apples.size())).fall();
            }
        }

        apples.forEach(Apple::timePassed);
    }
}


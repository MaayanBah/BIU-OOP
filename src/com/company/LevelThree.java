// 315112672 Maayan Bahar

package com.company;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The third level.
 */
public class LevelThree implements LevelInformation {
    private final int blockNumber;
    private final int numberOfBalls;
    private final String levelName;
    private final int paddleSpeed;
    private final int paddleWidth;
    private final Sprite background;
    private final List<Block> blocks;
    private final int blockWidth;
    private final int blockHeight;

    /**
     * The level information of level one.
     * @param gameLevel The information of level three.
     */
    public LevelThree(GameFlow gameLevel) {
        Random rand = new Random();
        this.numberOfBalls = 2;
        this.levelName = "Apples";
        this.paddleSpeed = 8;
        this.paddleWidth = 150;
        this.blockWidth = 45;
        this.blockHeight = 30;
        this.blockNumber = 40;
        this.blocks = new ArrayList<Block>();
        for (int rowIndex = 0; rowIndex < 5; ++rowIndex) {
            Color color = new Color(rand.nextInt(0xFFFFFF));
            for (int colIndex = 0; colIndex < 10 - rowIndex; ++colIndex) {
                blocks.add(new Block(new Rectangle(new Point(GameFlow.SCREEN_WIDTH - blockWidth * (colIndex + 1) - 20,
                        100 + blockHeight * rowIndex),
                        blockWidth,
                        blockHeight), color));
            }
        }

        this.background = new LevelThreeBackground(200, 550, gameLevel);
    }

    /**
     * @return The number of balls.
     */
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /**
     * @return The initial velocity of each ball
     */
    public List<Velocity> initialBallVelocities() {
        Random rand = new Random();
        return new LinkedList<Velocity>() {{
            for (int index = 0; index < numberOfBalls; ++index) {
                add(new Velocity(rand.nextInt(2) + 3, rand.nextInt(2) + 3));
            }
        }};
    }

    /**
     * @return The paddle speed;
     */
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /**
     * @return The paddle width;
     */
    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * @return The level name that will be displayed at the top of the screen.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return background;
    }

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    public List<Block> blocks() {
        return blocks;
    }

    /**
     * @return Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    public int numberOfBlocksToRemove() {
        return blockNumber;
    }

    /**
     * @return The block's width.
     */
    public int getBlockWidth() {
        return blockWidth;
    }

    /**
     * @return The block's height.
     */
    public int getBlockHeight() {
        return blockHeight;
    }

    /**
     * @return The number of blocks on the first row.
     */
    public int getBlocksOnFirstRow() {
        return 10;
    }

    /**
     * @return The number of block rows.
     */
    public int blockRows() {
        return 1;
    }
}


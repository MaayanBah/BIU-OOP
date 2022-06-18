// 315112672 Maayan Bahar

package com.company;

import java.util.List;

/**
 * A level info interface.
 */
public interface LevelInformation {
    /**
     * @return The number of balls.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return The paddle speed;
     */
    int paddleSpeed();

    /**
     * @return The paddle width;
     */
    int paddleWidth();

    /**
     * @return The level name that will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();

    /**
     * @return The block's width.
     */
    int getBlockWidth();

    /**
     * @return The block's height.
     */
    int getBlockHeight();

    /**
     * @return The number of blocks on the first row.
     */
    int getBlocksOnFirstRow();

    /**
     * @return The number of block rows.
     */
    int blockRows();
}

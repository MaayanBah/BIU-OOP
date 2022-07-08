// 315112672 Maayan Bahar

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The first level.
 */
public class LevelOne implements LevelInformation {
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
     */
    public LevelOne() {
        Random rand = new Random();
        this.numberOfBalls = 2;
        this.levelName = "Bullseye";
        this.paddleSpeed = 8;
        this.paddleWidth = 150;
        this.blockWidth = 40;
        this.blockHeight = 40;
        this.background = new LevelOneBackground(this);
        this.blocks = new ArrayList<Block>();
        Color color = new Color(rand.nextInt(0xFFFFFF));
        blocks.add(new Block(new Rectangle(new Point((double) (GameFlow.SCREEN_WIDTH - blockWidth) / 2,
                                                     (double) GameFlow.SCREEN_HEIGHT / 3),
                blockWidth,
                blockHeight), color));

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
        return blocks.size();
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
        return 1;
    }

    /**
     * @return The number of block rows.
     */
    public int blockRows() {
        return 1;
    }
}

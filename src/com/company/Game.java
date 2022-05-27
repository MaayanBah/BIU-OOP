package com.company;

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class will be used when we set and run the game.
 */
public class Game implements HitListener {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreTrackingListener scoreTracker;
    private ScoreIndicator scoreIndicator;

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final int WALL_SIZE = 20;
    static final int BALL_NUMBER = 1;
    static final int BALL_RADIUS = 10;
    static final int BALL_Y = 300;
    static final int BALL_X_VELOCITY = 5;
    static final int BALL_Y_VELOCITY = 3;
    static final int PADDLE_WIDTH = 150;
    static final int PADDLE_HEIGHT = 20;
    static final int BLOCK_WIDTH = 40;
    static final int BLOCK_HEIGHT = 25;
    static final int BLOCKS_ON_FIRST_ROW = 19;
    static final int BLOCK_ROWS = 8;


    /**
     * @param c a collidable object to be added to the game.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * @param s a sprite object to be added to the game.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    private void addBallsToGame() {
        for (int ballNumber = 0; ballNumber < BALL_NUMBER; ballNumber++) {
            Ball ball = new Ball(ballNumber * 100 + SCREEN_WIDTH / 2,
                                 BALL_Y,
                                 BALL_RADIUS,
                                 Color.black,
                                 environment);
            ball.setVelocity(BALL_X_VELOCITY, BALL_Y_VELOCITY);
            ball.addToGame(this);
        }
    }

    /**
     * @param beingHit The block which got hit by the ball.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //beingHit.removeFromGame(this);
    }


    private void addBlocksToGame() {
        ArrayList<Block> walls = new ArrayList<Block>() { {
            add(new Block(new Rectangle(new Point(SCREEN_WIDTH - WALL_SIZE, WALL_SIZE), WALL_SIZE, SCREEN_HEIGHT),
                    Color.GRAY));
            add(new Block(new Rectangle(new Point(0, WALL_SIZE), WALL_SIZE, SCREEN_HEIGHT),
                    Color.GRAY));
            add(new Block(new Rectangle(new Point(0, WALL_SIZE), SCREEN_WIDTH, WALL_SIZE),
                    Color.GRAY));
        }};

        Block deathBlock = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT), SCREEN_WIDTH, WALL_SIZE),
                Color.GRAY);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);

        for (Block wall: walls) {
            wall.addToGame(this);
        }
        Random rand = new Random();
        for (int rowNumber = 0; rowNumber < BLOCK_ROWS; rowNumber++) {
            Color color = new Color(rand.nextInt(0xFFFFFF));
            for (int colNumber = 1; colNumber <= BLOCKS_ON_FIRST_ROW - rowNumber; colNumber++) {
                Block block = new Block(new Rectangle(new Point(SCREEN_WIDTH - WALL_SIZE - colNumber * BLOCK_WIDTH,
                                                                2 * WALL_SIZE + rowNumber * BLOCK_HEIGHT),
                                                                BLOCK_WIDTH,
                                                                BLOCK_HEIGHT),
                        color);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracker);
                block.addHitListener(this);
                block.addToGame(this);
            }
        }
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockCounter = new Counter(BLOCKS_ON_FIRST_ROW * BLOCK_ROWS
                                   - BLOCK_ROWS * BLOCK_ROWS / 2 + 1);
        ballCounter = new Counter(BALL_NUMBER);
        blockRemover = new BlockRemover(this, blockCounter);
        ballRemover = new BallRemover(this, ballCounter);
        score = new Counter(0);
        scoreTracker = new ScoreTrackingListener(score);
        scoreIndicator = new ScoreIndicator(score);
        sprites.addSprite(scoreIndicator);


        gui = new GUI("Game", SCREEN_WIDTH, SCREEN_HEIGHT);
        addBallsToGame();
        addBlocksToGame();
        paddle = new Paddle(gui.getKeyboardSensor(),
                            new Rectangle(new Point((float) SCREEN_WIDTH / 2,
                                                   (float) SCREEN_HEIGHT - PADDLE_HEIGHT),
                                         PADDLE_WIDTH,
                                         PADDLE_HEIGHT),
                            Color.DARK_GRAY, SCREEN_WIDTH, WALL_SIZE);
        paddle.addToGame(this);
    }

    /**
     * @param c The collidable to be removed from the game environment.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * @param s The sprite to be removed from the game environment.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }



    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        while (true) {
            if (ballCounter.getValue() <= 0 || blockCounter.getValue() <= 0) {
                return;
            }
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            score = scoreTracker.getCurrentScore();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

        }
    }
}
// 315112672 Maayan Bahar

package com.company;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * This class will be used when we set and run the game.
 */
public class GameLevel implements HitListener, Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final Counter blockCounter;
    private final Counter ballCounter;
    private final BlockRemover blockRemover;
    private final BallRemover ballRemover;
    private Counter score;
    private final ScoreTrackingListener scoreTracker;


    private final AnimationRunner runner;
    private boolean running;
    private final LevelInformation levelInformation;

    static final int WALL_SIZE = 20;
    static final int BALL_RADIUS = 10;
    static final int BALL_Y = 300;
    static final int PADDLE_HEIGHT = 20;

    /**
     * The GameLevel constructor.
     * @param givenLevelInformation The level information by which to start the level
     * @param gui The gui that the game works with
     * @param animationRunner The animation runner that the game works with
     * @param score A score counter
     */
    public GameLevel(LevelInformation givenLevelInformation,
                     GUI gui,
                     AnimationRunner animationRunner,
                     Counter score) {
        this.score = score;
        this.running = true;
        this.runner = animationRunner;
        this.levelInformation = givenLevelInformation;
        this.gui = gui;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockCounter = new Counter(levelInformation.numberOfBlocksToRemove());
        ballCounter = new Counter(levelInformation.numberOfBalls());
        blockRemover = new BlockRemover(this, blockCounter);
        ballRemover = new BallRemover(this, ballCounter);
        scoreTracker = new ScoreTrackingListener(score);
    }


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
        for (int ballNumber = 0; ballNumber < levelInformation.numberOfBalls(); ballNumber++) {
            Ball ball = new Ball(ballNumber * 50 + (double) GameFlow.SCREEN_WIDTH / 3,
                                 BALL_Y + 7 * ballNumber,
                                 BALL_RADIUS,
                                 Color.black,
                                 environment);
            ball.setVelocity(levelInformation.initialBallVelocities().get(ballNumber));
            ball.addToGame(this);
        }
    }

    /**
     * @param beingHit The block which got hit by the ball.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) { }

    private void addBlocksToGame() {
        ArrayList<Block> walls = new ArrayList<Block>() { {
            add(new Block(new Rectangle(new Point(GameFlow.SCREEN_WIDTH - WALL_SIZE, WALL_SIZE),
                                        WALL_SIZE, GameFlow.SCREEN_HEIGHT),
                    Color.GRAY));
            add(new Block(new Rectangle(new Point(0, WALL_SIZE), WALL_SIZE, GameFlow.SCREEN_HEIGHT),
                    Color.GRAY));
            add(new Block(new Rectangle(new Point(0, WALL_SIZE), GameFlow.SCREEN_WIDTH, WALL_SIZE),
                    Color.GRAY));
        }};

        Block deathBlock = new Block(new Rectangle(new Point(0, GameFlow.SCREEN_HEIGHT),
                                                   GameFlow.SCREEN_WIDTH, WALL_SIZE),
                Color.GRAY);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);

        for (Block wall: walls) {
            wall.addToGame(this);
        }

        for (int rowNumber = 0; rowNumber < levelInformation.blockRows(); rowNumber++) {
            for (Block block: levelInformation.blocks()) {
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
        sprites.addSprite(new PlayerInformationIndicator(score, "Score: ", 17, 350, 15));
        sprites.addSprite(new PlayerInformationIndicator(ballCounter, "lives: ", 17, 100, 15));
        sprites.addSprite(new PlayerInformationIndicator(null,
                                                         "Level Name: " + levelInformation.levelName(),
                                                         17, 575, 15));
        sprites.addSprite(levelInformation.getBackground());

        addBallsToGame();
        addBlocksToGame();
        Paddle paddle = new Paddle(gui.getKeyboardSensor(),
                new Rectangle(new Point((float) (GameFlow.SCREEN_WIDTH
                        - levelInformation.paddleWidth()) / 2,
                        (float) GameFlow.SCREEN_HEIGHT - PADDLE_HEIGHT),
                        levelInformation.paddleWidth(),
                        PADDLE_HEIGHT),
                Color.DARK_GRAY, GameFlow.SCREEN_WIDTH, WALL_SIZE);
        paddle.addToGame(this);
    }

    /**
     * @param c The collidable to be removed from the game environment.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * @return The collidables of the level
     */
    public ArrayList<Collidable> collidables() {
        return environment.getCollidableList();
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
        this.running = true;
        this.runner.run(this, gui, new CountdownAnimation(2, 3, sprites));
    }

    /** shouldStop() is in charge of stopping condition.
     * @return True if we ran out of balls or blocks, false otherwise/
     */
    public boolean shouldStop() {
        this.running = !(ballCounter.getValue() <= 0 || blockCounter.getValue() <= 0);
        return !this.running;
    }

    /**
     * @param d The given DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        score = scoreTracker.getCurrentScore();
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.gui.getKeyboardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                                                           KeyboardSensor.SPACE_KEY,
                                                           new PauseScreen()),
                            gui,
                            new CountdownAnimation(2, 0, sprites));
        }
    }

    /**
     * @return Whether the game level is running
     */
    public boolean isrunning() {
        return running;
    }

    /**
     * @return Whether the level was lost
     */
    public boolean hasLost() {
        return this.ballCounter.getValue() <= 0;
    }
}
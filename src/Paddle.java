// 315112672 Maayan Bahar

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The player's paddle.
 */
public class Paddle implements Sprite, Collidable {
    private final int screenWidth;
    static final int PADDLE_STEP_SIZE = 8;
    private final int wallSize;
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle collisionRectangle;
    private final Color color;

    /**
     * @param keyboard The gui's keyboard.
     * @param collisionRectangle The rectangle of the paddle.
     * @param color The color of the paddle.
     * @param wallSize the wall's size.
     * @param screenWidth The screen's width.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle collisionRectangle,
                  Color color, int screenWidth, int wallSize) {
        this.keyboard = keyboard;
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        this.screenWidth = screenWidth;
        this.wallSize = wallSize;
    }

    /**
     * @param keyboard The gui's keyboard.
     * @param collisionRectangle The rectangle of the paddle.
     * @param wallSize the wall's size.
     * @param screenWidth The screen's width.
     */
    public Paddle(biuoop.KeyboardSensor keyboard,
                  Rectangle collisionRectangle, int screenWidth, int wallSize) {
        this(keyboard, collisionRectangle, Color.BLACK, screenWidth, wallSize);
    }

    /**
     * Move the paddle one step to the left.
     */
    public void moveLeft() {
        if (collisionRectangle.getUpperLeft().getX() > wallSize + PADDLE_STEP_SIZE) {
            collisionRectangle = new Rectangle(new Point(collisionRectangle.getUpperLeft().getX()
                    - PADDLE_STEP_SIZE,
                    this.collisionRectangle.getUpperLeft().getY()),
                    collisionRectangle.getWidth(),
                    collisionRectangle.getHeight());
        } else {
            collisionRectangle = new Rectangle(new Point(wallSize,
                    this.collisionRectangle.getUpperLeft().getY()),
                    collisionRectangle.getWidth(),
                    collisionRectangle.getHeight());
        }
    }

    /**
     * Move the paddle one step to the right.
     */
    public void moveRight() {
        if (collisionRectangle.getUpperLeft().getX()
            + collisionRectangle.getWidth() < screenWidth - wallSize - PADDLE_STEP_SIZE) {
            collisionRectangle = new Rectangle(new Point(collisionRectangle.getUpperLeft().getX()
                    + PADDLE_STEP_SIZE,
                    this.collisionRectangle.getUpperLeft().getY()),
                    collisionRectangle.getWidth(),
                    collisionRectangle.getHeight());
        } else {
            collisionRectangle = new Rectangle(new Point(screenWidth - wallSize - collisionRectangle.getWidth(),
                    this.collisionRectangle.getUpperLeft().getY()),
                    collisionRectangle.getWidth(),
                    collisionRectangle.getHeight());
        }
    }

    /**
     * Moves the paddle according the keyboard input.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * @param surface The surface to draw the paddle on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) collisionRectangle.getUpperLeft().getX(),
                              (int) collisionRectangle.getUpperLeft().getY(),
                              (int) collisionRectangle.getWidth(),
                              (int) collisionRectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) collisionRectangle.getUpperLeft().getX(),
                              (int) collisionRectangle.getUpperLeft().getY(),
                              (int) collisionRectangle.getWidth(),
                              (int) collisionRectangle.getHeight());
    }

    /**
     * @return The paddle's collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    /**
     * @param collisionPoint  The point of collision.
     * @param currentVelocity The ball's velocity.
     * @param hitter The hitting ball.
     * @return The new velocity of the ball
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        if (collisionRectangle.getRight().contains(collisionPoint)
                || collisionRectangle.getLeft().contains(collisionPoint)) {
            return new Velocity(currentVelocity.getXVelocity() * -1, currentVelocity.getYVelocity());
        }

        double portion = (collisionPoint.getX() - collisionRectangle.getTop().start().getX())
                         / collisionRectangle.getWidth();
        double velocitySize = Math.sqrt(Math.pow(currentVelocity.getXVelocity(), 2)
                                        + Math.pow(currentVelocity.getYVelocity(), 2));

        if (portion <= 0.2) {
            return new Velocity(velocitySize * Math.cos(150 / 360.0 * 2 * Math.PI),
                                -velocitySize * Math.sin(150 / 360.0 * 2 * Math.PI));
        } else if (portion <= 0.4) {
            return new Velocity(velocitySize * Math.cos(120 / 360.0 * 2 * Math.PI),
                                -velocitySize * Math.sin(120 / 360.0 * 2 * Math.PI));
        } else if (portion <= 0.6) {
            return new Velocity(currentVelocity.getXVelocity(), currentVelocity.getYVelocity() * -1);
        } else if (portion <= 0.8) {
            return new Velocity(velocitySize * Math.cos(60 / 360.0 * 2 * Math.PI),
                                -velocitySize * Math.sin(60 / 360.0 * 2 * Math.PI));
        } else {
            return new Velocity(velocitySize * Math.cos(30 / 360.0 * 2 * Math.PI),
                                -velocitySize * Math.sin(30 / 360.0 * 2 * Math.PI));
        }
    }

    /**
     * @param g Add the paddle to the game.
     */
    // Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
// 315112672 Maayan Bahar

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A rain drop.
 */
public class RainDrop implements Sprite {
    private static final int SIZE = 3;
    private static final int FALL_SPEED = 4;
    private final GameFlow game;
    private final Velocity fallVelocity;
    private Line line;
    private final RainCloud origin;

    /**
     * @param game The current game flow.
     * @param startingPosition The starting point of the rain drop.
     * @param xVelocity The drop's velocity on X-axis.
     * @param origin The drop's rain cloud.
     */
    public RainDrop(GameFlow game,
                    Point startingPosition,
                    double xVelocity,
                    RainCloud origin) {
        this.game = game;
        this.fallVelocity = new Velocity(xVelocity, FALL_SPEED);
        this.line = new Line(startingPosition, new Point(startingPosition.getX(),
                          startingPosition.getY() + SIZE));
        this.origin = origin;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.drawLine((int) this.line.start().getX(),
                (int) this.line.start().getY(),
                (int) this.line.end().getX(),
                (int) this.line.end().getY());
    }

    @Override
    public void timePassed() {
        this.line = new Line(this.fallVelocity.applyToPoint(this.line.start()),
                             this.fallVelocity.applyToPoint(this.line.end()));

        // If we collide with at least one block
        if (this.game.getCurrentGameLevel().collidables().stream().anyMatch(
                collidable -> !collidable.getCollisionRectangle().intersectionPoints(line).isEmpty()
            )) {
            this.origin.removeRainDrop(this);
        }
    }
}

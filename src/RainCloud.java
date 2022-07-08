// 315112672 Maayan Bahar

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A cloud with rain.
 */
public class RainCloud implements Sprite {
    private final GameFlow game;
    private Point cloudPosition;
    private final Velocity velocity;
    private final Set<RainDrop> drops;

    /**
     * @param game The current gameFlow.
     * @param cloudPosition The starting point of the cloud.
     * @param xVelocity The cloud's velocity on X-axis.
     */
    public RainCloud(GameFlow game, Point cloudPosition, double xVelocity) {
        this.cloudPosition = cloudPosition;
        this.velocity = new Velocity(xVelocity, 0);
        this.game = game;
        this.drops = new HashSet<RainDrop>();
    }

    /**
     * @param surface The surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(new Color(0xD6D8DA));
        surface.fillCircle((int) cloudPosition.getX() - 50, (int) cloudPosition.getY() + 10, 30);
        surface.fillCircle((int) cloudPosition.getX() - 30, (int) cloudPosition.getY() + 18, 25);

        surface.setColor(new Color(0xCECECE));
        surface.fillCircle((int) cloudPosition.getX() - 25, (int) cloudPosition.getY() - 5, 30);

        surface.setColor(new Color(0xBABBBC));
        surface.fillCircle((int) cloudPosition.getX(), (int) cloudPosition.getY(), 30);
        surface.fillCircle((int) cloudPosition.getX() - 10, (int) cloudPosition.getY() + 18, 25);

        for (RainDrop drop: drops) {
            drop.drawOn(surface);
        }
    }

    /**
     * Move the cloud.
     */
    public void timePassed() {
        Random random = new Random();

        cloudPosition = velocity.applyToPoint(cloudPosition);
        drops.add(new RainDrop(game,
                               new Point(cloudPosition.getX() + random.nextInt(90) - 30,
                                         cloudPosition.getY() + 40),
                       velocity.getXVelocity() / 2,
                         this));

        Set<RainDrop> dropsClone = new HashSet<>(drops);
        for (RainDrop drop: dropsClone) {
            drop.timePassed();
        }
    }

    /**
     * @return the cloud Position;
     */
    public Point getCloudPosition() {
        return cloudPosition;
    }

    /**
     * @param newPosition The new cloud position.
     */
    public void setCloudPosition(Point newPosition) {
        this.cloudPosition = newPosition;
    }

    /**
     * @param rainDrop The rain drop to be removed.
     */
    public void removeRainDrop(RainDrop rainDrop) {
        this.drops.remove(rainDrop);
    }
}

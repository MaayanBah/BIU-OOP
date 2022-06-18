// 315112672 Maayan Bahar

package com.company;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * The cloud sprite class.
 */
public class Cloud implements Sprite {
    private Point cloudPosition;
    private final Velocity velocity;

    /**
     * @param cloudPosition The cloud's position
     * @param xVelocity The cloud's x velocity
     */
    public Cloud(Point cloudPosition, double xVelocity) {
        this.cloudPosition = cloudPosition;
        this.velocity = new Velocity(xVelocity, 0);
    }

    /**
     * @param surface The drawsurface to draw the cloud on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(new Color(0xF7FEFF));
        surface.fillCircle((int) cloudPosition.getX() - 50, (int) cloudPosition.getY() + 10, 30);
        surface.fillCircle((int) cloudPosition.getX() - 30, (int) cloudPosition.getY() + 18, 25);

        surface.setColor(new Color(0xEFF4F5));
        surface.fillCircle((int) cloudPosition.getX() - 25, (int) cloudPosition.getY() - 5, 30);

        surface.setColor(new Color(0xE8EEEF));
        surface.fillCircle((int) cloudPosition.getX(), (int) cloudPosition.getY(), 30);
        surface.fillCircle((int) cloudPosition.getX() - 10, (int) cloudPosition.getY() + 18, 25);
    }

    /**
     * Move the cloud.
     */
    public void timePassed() {
        Random rand = new Random();

        cloudPosition = velocity.applyToPoint(cloudPosition);
        if (cloudPosition.getX() >= GameFlow.SCREEN_WIDTH) {
            cloudPosition = new Point(-20, rand.nextInt(40) + 60);
        }
    }
}

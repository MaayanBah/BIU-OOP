// 315112672 Maayan Bahar

package com.company;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * dfvfdggfb.
 */
public class Block implements Collidable, Sprite {
    private final Rectangle collisionRectangle;
    private final java.awt.Color color;

    /**
     * @param rectangle Create a new block.
     */
    public Block(Rectangle rectangle) {
        this.color = Color.black;
        this.collisionRectangle = rectangle;
    }

    /**
     * @param rectangle The rectangle of the block
     * @param color The block's color
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.color = color;
        this.collisionRectangle = rectangle;
    }

    /**
     * @return The block's shape, meaning - the block's rectangle object.
     */
    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    /**
     * @param collisionPoint  the point of the colliding object.
     * @param currentVelocity The point's velocity;
     * @return The new velocity expected after the hit (based on
     *         the force the object inflicted on us).
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionRectangle.getRight().contains(collisionPoint)
            || collisionRectangle.getLeft().contains(collisionPoint)) {
            return new Velocity(currentVelocity.getXVelocity() * -1, currentVelocity.getYVelocity());
        }
        return new Velocity(currentVelocity.getXVelocity(), currentVelocity.getYVelocity() * -1);
    }

    /**
     * @param surface The drawing surface for the block to be drawn on.
     * draw the ball on the given DrawSurface.
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
     * Currently do nothing.
     */
    public void timePassed() {
    }

    /**
     * @param g Add the block to the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}

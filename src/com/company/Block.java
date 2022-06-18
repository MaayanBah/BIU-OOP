// 315112672 Maayan Bahar

package com.company;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A class for graphical block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle collisionRectangle;
    private final java.awt.Color color;
    private final List<HitListener> hitListeners;

    /**
     * @param rectangle Create a new block.
     */
    public Block(Rectangle rectangle) {
        this.color = Color.black;
        this.collisionRectangle = rectangle;
        this.hitListeners = new LinkedList<HitListener>();
    }

    /**
     * @param rectangle The rectangle of the block
     * @param color The block's color
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.color = color;
        this.collisionRectangle = rectangle;
        this.hitListeners = new LinkedList<HitListener>();
    }

    /**
     * @param rectangle The rectangle of the block
     * @param color The block's color
     * @param hitListeners The list of the listener to the Block.
     */
    public Block(Rectangle rectangle, java.awt.Color color, List<HitListener> hitListeners) {
        this.color = color;
        this.collisionRectangle = rectangle;
        this.hitListeners = hitListeners;
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
     * @param hitter The ball hitting the block.
     * @return The new velocity expected after the hit (based on
     *         the force the object inflicted on us).
     */
    //todo fix - combine notifyHit
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        if (collisionRectangle.getRight().contains(collisionPoint)
            || collisionRectangle.getLeft().contains(collisionPoint)) {
            notifyHit(hitter);
            return new Velocity(currentVelocity.getXVelocity() * -1, currentVelocity.getYVelocity());
        }
        notifyHit(hitter);
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
     * @param game Add the block to the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * @param game Remove the block from the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * @param hl The hitListener to be added to the HitListeners list of the block.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * @param hl The hitListener to be removed to the HitListeners list of the block.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}


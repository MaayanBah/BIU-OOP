package com.company;

import biuoop.DrawSurface;

/**
 * A Sprite is a game object that can be drawn to the screen.
 */
public interface Sprite {
    /**
     * @param d the given surface.
     *  draw the sprite to the screen.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
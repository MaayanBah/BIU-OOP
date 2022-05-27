// 315112672 Maayan Bahar

package com.company;

/**
 * This interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * @return The "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint the point of the colliding object.
     * @param currentVelocity The point's velocity;
     * @return The new velocity expected after the hit (based on
     *         the force the object inflicted on us).
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
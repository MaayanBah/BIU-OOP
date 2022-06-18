// 315112672 Maayan Bahar

package com.company;

/**
 * A record type class for a given collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * @param collisionPoint The point of collision
     * @param collisionObject The object that was collided
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return The point at which the collision occured
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return The collidable that was involved in the collision
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
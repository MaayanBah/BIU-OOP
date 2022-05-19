// 315112672 Maayan Bahar

package com.company;
import java.util.ArrayList;


/**
 * The game environment (a collection of collidables).
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidableList;

    /**
     * @param collidableList The collidable list
     */
    public GameEnvironment(ArrayList<Collidable> collidableList) {
        this.collidableList = collidableList;
    }

    /**
     * Empty c'tor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * @param c A collidable object to be added to the collidable list.
     *          add the given collidable to the environment.
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * @return The collidable list.
     */
    public ArrayList<Collidable> getCollidableList() {
        return collidableList;
    }


    private Point closeToStart(Collidable collidable, Line trajectory) {
        ArrayList<Point> listOfPoints = collidable.getCollisionRectangle().intersectionPoints(trajectory);
        if (!listOfPoints.isEmpty()) {
            Point closeToStartPoint = listOfPoints.get(0);
            for (Point point : listOfPoints) {
                if (point.distance(trajectory.start()) < closeToStartPoint.distance(trajectory.start())) {
                    closeToStartPoint = point;
                }
            }
            return closeToStartPoint;
        } else {
            return null;
        }
    }

    /**
     * @param trajectory The trajectory
     * @return The closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollisionToStart = null;
        for (Collidable collidable : collidableList) {
            Point potentialCollision = closeToStart(collidable, trajectory);
            if (potentialCollision != null
                && (closestCollisionToStart == null
                    || (potentialCollision.distance(trajectory.start())
                    < closestCollisionToStart.collisionPoint().distance(trajectory.start())))) {
                closestCollisionToStart = new CollisionInfo(potentialCollision, collidable);
            }
        }

        return closestCollisionToStart;
    }
}


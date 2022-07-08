// 315112672 Maayan Bahar

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * A class that draws a bouncing ball animation.
 */
public class Check {

    /**
     * @param start The balls center point.
     * @param dx    The amount to be added to the x value of the ball.
     * @param dy    The amount to be added to the y value of the ball.
     * @param gameEnvironment The game environment
     */
    public static void drawAnimation(Point start, double dx, double dy, GameEnvironment gameEnvironment) {
        GUI gui = new GUI("Bouncing Ball Animation", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 10, Color.black, gameEnvironment);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            for (Collidable collidable : gameEnvironment.getCollidableList()) {
                d.setColor(Color.RED);
                d.drawRectangle((int) collidable.getCollisionRectangle().getUpperLeft().getX(),
                                (int) collidable.getCollisionRectangle().getUpperLeft().getY(),
                                (int) collidable.getCollisionRectangle().getWidth(),
                                (int) collidable.getCollisionRectangle().getHeight());
                int i = (int) collidable.getCollisionRectangle().getUpperLeft().getX();
            }
            gui.show(d);
            sleeper.sleepFor(1000 / 60);  // wait for 1000 / 60 milliseconds.
        }
    }

    /**
     * @param args Takes 4 inputs from the command line, and use it to define the start point
     *             of the ball the dx and the dy.
     */
    public static void main(String[] args) {
        double x = 50;
        double y = 50;
        Block block2 = new Block(new Rectangle(new Point(180, 170), 20, 20));
        Block block3 = new Block(new Rectangle(new Point(30, 70), 10, 50));

        Block block1 = new Block(new Rectangle(new Point(0, 0), 1, 200));
        Block block4 = new Block(new Rectangle(new Point(0, 0), 200, 1));
        Block block5 = new Block(new Rectangle(new Point(199, 0), 1, 200));
        Block block6 = new Block(new Rectangle(new Point(0, 199), 200, 1));


        GameEnvironment gameEnvironment = new GameEnvironment();
        gameEnvironment.addCollidable(block1);
        gameEnvironment.addCollidable(block2);
        gameEnvironment.addCollidable(block3);
        gameEnvironment.addCollidable(block4);
        gameEnvironment.addCollidable(block5);
        gameEnvironment.addCollidable(block6);

        drawAnimation(new Point(x, y),
                Double.parseDouble(args[2]),
                Double.parseDouble(args[3]), gameEnvironment);
    }
}

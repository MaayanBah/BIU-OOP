//// 315112672 Maayan Bahar
//
//package com.company;
//
//import biuoop.DrawSurface;
//import biuoop.GUI;
//
//import java.awt.Color;
//import java.util.Random;
//
///**
// * A class of bouncing balls animation in rectangles.
// */
//public class MultipleFramesBouncingBallsAnimation {
//    static final int SCREEN_WIDTH = 700;
//    static final int SCREEN_HEIGHT = 700;
//    static final int SLOWEST_RADIUS = 50;
//    static final int FRAMES_PER_SECOND = 60;
//
//    private static Ball[] initializeRandomBallsByRadii(String[] radii) {
//        Random rand = new Random();
//        Ball[] balls = new Ball[radii.length];
//
//        for (int i = 0; i < radii.length / 2; i++) {
//            int ballRadius = Integer.parseInt(radii[i]);
//            balls[i] = new Ball(new Point(rand.nextDouble() * (450 - (2 * ballRadius + 51)) + ballRadius + 51,
//                    rand.nextDouble() * (450 - (2 * ballRadius + 51)) + ballRadius + 51),
//                    ballRadius, new Color(rand.nextInt(0xFFFFFF)));
//        }
//        for (int ballIndex = radii.length / 2; ballIndex < radii.length; ballIndex++) {
//            int ballRadius = Integer.parseInt(radii[ballIndex]);
//            balls[ballIndex] = new Ball(new Point(rand.nextDouble() * (151 - (2 * ballRadius)) + ballRadius + 450,
//                    rand.nextDouble() * (151 - (2 * ballRadius)) + ballRadius + 450),
//                    ballRadius, new Color(rand.nextInt(250),
//                    rand.nextInt(250),
//                    rand.nextInt(250)));
//        }
//
//        for (Ball ball : balls) {
//            double randomAngle = rand.nextDouble() * 360;
//            double divisor = (ball.getRadius() < SLOWEST_RADIUS
//                    && ball.getRadius() != 0)
//                    ? ball.getRadius()
//                    : SLOWEST_RADIUS;
//            ball.setVelocity(Velocity.fromAngleAndSpeed(randomAngle,
//                    (double) 25 / divisor));
//        }
//
//        return balls;
//    }
//
//        /**
//         * @param balls A ball array.
//         */
//    public static void drawAnimationsOnTwoFrames(Ball[] balls) {
//        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", SCREEN_WIDTH, SCREEN_HEIGHT);
//        biuoop.Sleeper sleeper = new biuoop.Sleeper();
//
//        while (true) {
//            DrawSurface d = gui.getDrawSurface();
//            d.setColor(Color.GRAY);
//            d.fillRectangle(50, 50, 450, 450);
//            d.setColor(Color.YELLOW);
//            d.fillRectangle(450, 450, 150, 150);
//            for (int index = 0; index < balls.length / 2; index++) {
//                balls[index].moveOneStepInside(new Point(50, 50), new Point(500, 500));
//                balls[index].drawOn(d);
//            }
//            for (int index = balls.length / 2; index < balls.length; index++) {
//                balls[index].moveOneStepInside(new Point(450, 450), new Point(600, 600));
//                balls[index].drawOn(d);
//            }
//            gui.show(d);
//            sleeper.sleepFor(1000 / FRAMES_PER_SECOND);
//        }
//    }
//
//    /**
//     * @param args Takes 4 inputs from the command line for one ball, and use it to define the
//     *              start point of the ball the dx and the dy.
//     */
//    public static void main(String[] args) {
//        Random rand = new Random();
//        Ball[] balls = new Ball[args.length];
//
//        drawAnimationsOnTwoFrames(initializeRandomBallsByRadii(args));
//
//    }
//}

//// 315112672 Maayan Bahar
//
//package com.company;
//
//import biuoop.DrawSurface;
//import biuoop.GUI;
//
//import java.awt.Color;
//import java.util.Arrays;
//import java.util.Random;
//
///**
// * A class that draws multiple bouncing balls animation.
// */
//public class MultipleBouncingBallsAnimation {
//    static final int SCREEN_WIDTH = 200;
//    static final int SCREEN_HEIGHT = 200;
//    static final int FRAMES_PER_SECOND = 60;
//    static final int SLOWEST_RADIUS = 50;
//
//    private static Ball[] initializeRandomBallsByRadii(int[] radii) {
//        Random rand = new Random();
//        Ball[] balls = new Ball[radii.length];
//
//        for (int radiusIndex = 0; radiusIndex < radii.length; radiusIndex++) {
//            double divisor = (radii[radiusIndex] < SLOWEST_RADIUS
//                    && radii[radiusIndex] != 0)
//                    ? radii[radiusIndex]
//                    : SLOWEST_RADIUS;
//
//            balls[radiusIndex] = new Ball(new Point(rand.nextDouble() * (SCREEN_WIDTH - 2 * radii[radiusIndex])
//                                                     + radii[radiusIndex] + 1,
//                                                    rand.nextDouble() * (SCREEN_HEIGHT - 2 * radii[radiusIndex])
//                                                     + radii[radiusIndex] + 1),
//                                          radii[radiusIndex],
//                                          new Color(rand.nextInt(0xFFFFFF)),
//                                          Velocity.fromAngleAndSpeed(rand.nextDouble() * 360,
//                                                               (double) 25 / divisor));
//        }
//
//        return balls;
//    }
//
//    /**
//     * @param balls A ball array.
//     */
//    public static void drawAnimations(Ball[] balls) {
//        GUI gui = new GUI("Multiple Bouncing Balls Animation", SCREEN_WIDTH, SCREEN_HEIGHT);
//        biuoop.Sleeper sleeper = new biuoop.Sleeper();
//
//        while (true) {
//            DrawSurface surface = gui.getDrawSurface();
//
//            for (Ball ball : balls) {
//                ball.moveOneStep(gui);
//                ball.drawOn(surface);
//            }
//
//            gui.show(surface);
//            sleeper.sleepFor(1000 / FRAMES_PER_SECOND);
//        }
//    }
//
//    /**
//     * @param args Takes 4 inputs from the command line for one ball, and use it to define the
//     *             start point of the ball the dx and the dy
//     */
//    public static void main(String[] args) {
//        drawAnimations(initializeRandomBallsByRadii(Arrays.stream(args).mapToInt(Integer::parseInt).toArray()));
//    }
//}

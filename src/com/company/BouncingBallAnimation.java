//// 315112672 Maayan Bahar
//
//package com.company;
//
//import biuoop.DrawSurface;
//import biuoop.GUI;
//
//import java.awt.Color;
//
///**
// * A class that draws a bouncing ball animation.
// */
//public class BouncingBallAnimation {
//
//    /**
//     * @param start The balls center point.
//     * @param dx    The amount to be added to the x value of the ball.
//     * @param dy    The amount to be added to the y value of the ball.
//     */
//    public static void drawAnimation(Point start, double dx, double dy) {
//        GUI gui = new GUI("Bouncing Ball Animation", 200, 200);
//        biuoop.Sleeper sleeper = new biuoop.Sleeper();
//        Ball ball = new Ball(start.getX(), start.getY(), 10, Color.black);
//        ball.setVelocity(dx, dy);
//        while (true) {
//            ball.moveOneStep(gui);
//            DrawSurface d = gui.getDrawSurface();
//            ball.drawOn(d);
//            gui.show(d);
//            sleeper.sleepFor(1000 / 60);  // wait for 1000 / 60 milliseconds.
//        }
//    }
//
//    /**
//     * @param args Takes 4 inputs from the command line, and use it to define the start point
//     *             of the ball the dx and the dy.
//     */
//    public static void main(String[] args) {
//        double x = Double.parseDouble(args[0]) <= 10
//                ? 11 : Double.parseDouble(args[0]);
//        double y = Double.parseDouble(args[1]) <= 10
//                ? 11 : Double.parseDouble(args[1]);
//        drawAnimation(new Point(x, y),
//                           Double.parseDouble(args[2]),
//                           Double.parseDouble(args[3]));
//    }
//}

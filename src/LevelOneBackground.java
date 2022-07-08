// 315112672 Maayan Bahar

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The background sprite for level one.
 */
public class LevelOneBackground implements Sprite {
    private final LevelInformation levelInformation;

    /**
     * @param levelInformation The level information.
     */
    public LevelOneBackground(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }

    /**
     * @param surface The game surface.
     */
    public void drawOn(DrawSurface surface) {
        // Sky
        surface.setColor(new Color(132, 186, 202));
        surface.fillRectangle(0, 30, GameFlow.SCREEN_WIDTH, GameFlow.SCREEN_HEIGHT);

        // Grass
        int grassHeight = 20;
        surface.setColor(new Color(83, 149, 101));
        surface.fillRectangle(0, GameFlow.SCREEN_HEIGHT - grassHeight,
                              GameFlow.SCREEN_WIDTH, grassHeight);

        // Tree Stump
        surface.setColor(new Color(118, 79, 64));
        surface.fillRectangle(355, 30, 100, 550);

        int numberOfCirclesInTarget = 4;
        int smallestCircleRadius = 40;

        surface.setColor(new Color(0x513125));
        surface.drawText(360,
                        (int) levelInformation.blocks().get(0).getCollisionRectangle().getRight().start().getY()
                        + smallestCircleRadius + numberOfCirclesInTarget * 20, "Maayan Loves OOP", 10);

        for (int targetCircleIndex = numberOfCirclesInTarget - 1; targetCircleIndex >= 0; --targetCircleIndex) {
            surface.setColor(targetCircleIndex % 2 == 0 ? Color.WHITE : Color.RED);
            surface.fillCircle((int) levelInformation.blocks().get(0).getCollisionRectangle().getBottom().start().getX()
                            + levelInformation.getBlockWidth() / 2,
                    (int) levelInformation.blocks().get(0).getCollisionRectangle().getRight().start().getY()
                            - levelInformation.getBlockHeight() / 2, smallestCircleRadius + targetCircleIndex * 20);
            surface.setColor(Color.BLACK);
            surface.drawCircle((int) levelInformation.blocks().get(0).getCollisionRectangle().getBottom().start().getX()
                            + levelInformation.getBlockWidth() / 2,
                    (int) levelInformation.blocks().get(0).getCollisionRectangle().getRight().start().getY()
                            - levelInformation.getBlockHeight() / 2, smallestCircleRadius + targetCircleIndex * 20);
        }
    }

    /**
     * Currently do nothing.
     */
    public void timePassed() { }
}

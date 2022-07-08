// 315112672 Maayan Bahar

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Level four's background sprite.
 */
public class LevelFourBackground implements Sprite {
    private final int grassHeight;
    private final GameFlow gameLevel;
    private int collidableNum;
    private final Color skyColor;
    private double skyAdder;
    private final Color lampColor;

    /**
     * @param gameLevel The game level
     */
    public LevelFourBackground(GameFlow gameLevel) {
        this.gameLevel = gameLevel;
        this.collidableNum = -1;
        this.grassHeight = 20;
        this.skyColor = new Color(0x04123F);
        this.lampColor = new Color(0x2E1F09);

    }

    /**
     * @param surface The game surface.
     */
    public void drawOn(DrawSurface surface) {
        // Sky
        surface.setColor(new Color(Math.min(skyColor.getRed() + (int) skyAdder / 2, 188),
                Math.min(skyColor.getGreen() +  (int) skyAdder / 2, 197),
                Math.min(skyColor.getBlue() +  (int) skyAdder, 240)));

        surface.fillRectangle(0, 30, GameFlow.SCREEN_WIDTH, GameFlow.SCREEN_HEIGHT);

        // Grass
        surface.setColor(new Color(0x224925));
        surface.fillRectangle(0, GameFlow.SCREEN_HEIGHT - grassHeight,
                GameFlow.SCREEN_WIDTH, grassHeight);

        // Sign
        int signStandHeight = 60;
        int signStandWidth = 3;
        int signWidth = 120;
        int signHeight = 60;
        surface.setColor(new Color(0x513125));
        surface.fillRectangle((GameFlow.SCREEN_WIDTH - signStandWidth) / 2,
                GameFlow.SCREEN_HEIGHT - grassHeight - signStandHeight,
                signStandWidth, signStandHeight);
        surface.setColor(new Color(0x9F6D54));
        surface.fillRectangle((GameFlow.SCREEN_WIDTH - signWidth) / 2,
                GameFlow.SCREEN_HEIGHT - grassHeight - signHeight - signStandHeight,
                signWidth, signHeight);

        // Text
        surface.setColor(new Color(0x4E230D));
        surface.drawText((GameFlow.SCREEN_WIDTH - signWidth) / 2 + 4,
                GameFlow.SCREEN_HEIGHT - grassHeight - signHeight - signStandHeight + 20,
                "Please turn on",
                14);

        surface.drawText((GameFlow.SCREEN_WIDTH - signWidth) / 2 + 5,
                GameFlow.SCREEN_HEIGHT - grassHeight - signHeight - signStandHeight + 45,
                "the light :(",
                14);

        // first Lamp
        int lampHeight = 150;
        int lampX = 200;
        int lampRadius = 20;

        surface.setColor(new Color(0x3F2909));
        surface.fillRectangle(lampX,
                             GameFlow.SCREEN_HEIGHT - grassHeight - lampHeight,
                                signStandWidth,
                                lampHeight);
        surface.drawCircle(lampX,
                        GameFlow.SCREEN_HEIGHT - grassHeight - lampHeight - lampRadius,
                           lampRadius);

        surface.setColor(new Color(Math.min(lampColor.getRed() + (int) skyAdder *  2, 249),
                Math.min(lampColor.getGreen() +  (int) skyAdder, 244),
                Math.min(lampColor.getBlue() +  (int) skyAdder, 100)));

        surface.fillCircle(lampX,
                GameFlow.SCREEN_HEIGHT - grassHeight - lampHeight - lampRadius,
                lampRadius - 2);

// second Lamp
        lampX = 600;
        surface.setColor(new Color(0x3F2909));
        surface.fillRectangle(lampX,
                GameFlow.SCREEN_HEIGHT - grassHeight - lampHeight,
                signStandWidth,
                lampHeight);
        surface.drawCircle(lampX,
                GameFlow.SCREEN_HEIGHT - grassHeight - lampHeight - lampRadius,
                lampRadius);

        surface.setColor(new Color(Math.min(lampColor.getRed() + (int) skyAdder * 2, 249),
                Math.min(lampColor.getGreen() +  (int) skyAdder, 244),
                Math.min(lampColor.getBlue() +  (int) skyAdder, 100)));

        surface.fillCircle(lampX,
                GameFlow.SCREEN_HEIGHT - grassHeight - lampHeight - lampRadius,
                lampRadius - 2);


    }

    /**
     * Currently do nothing.
     */
    public void timePassed() {
        if (collidableNum == -1) {
            collidableNum = gameLevel.getCurrentGameLevel().collidables().size();
        }
        if (collidableNum > gameLevel.getCurrentGameLevel().collidables().size()) {
            collidableNum = gameLevel.getCurrentGameLevel().collidables().size();
            skyAdder += 10;
        }
    }
}


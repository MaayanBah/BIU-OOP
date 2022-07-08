// 315112672 Maayan Bahar

import java.util.ArrayList;
import biuoop.DrawSurface;


/**
 * A class for SpriteCollection.
 */
public class SpriteCollection {
    private ArrayList<Sprite> spriteList;

    /**
     * Create new SpiteCollection with an empty sprite list.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * @param spriteList A list of sprite objects.
     */
    public SpriteCollection(ArrayList<Sprite> spriteList) {
        this.spriteList = spriteList;
    }

    /**
     * @param s A sprite to be added to the sprite list.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }


    /**
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> copy = new ArrayList<Sprite>(spriteList);
        for (Sprite sprite : copy) {
            sprite.timePassed();
        }
    }

    /**
     * @param d The drawing surface for the sprite to be drawn on.
     * call drawOn(d) on all sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
}
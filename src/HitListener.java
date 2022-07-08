//Maayan Bahar 315112672

/**
 * An interface for listeners.
 */
public interface HitListener {

    /**
     * @param beingHit The block that got hit.
     * @param hitter The Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

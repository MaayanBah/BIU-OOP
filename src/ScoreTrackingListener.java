//Maayan Bahar 315112672

/**
 * A score listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * @param scoreCounter A score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * @param beingHit The block that got hit.
     * @param hitter   The Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(100);
    }

    /**
     * @return The current acore.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}

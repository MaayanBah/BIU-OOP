//Maayan Bahar 315112672

/**
 * A class responsible for ball removal.
 */
public class BallRemover implements HitListener {
    private final GameLevel game;
    private final Counter ballCounter;

    /**
     * @param game The game.
     * @param ballCounter The ball counter - will be updated.
     * a BallRemover is in charge of removing balls from the game, as well as keeping count
     * of the number of balls that remain.
     */
    public BallRemover(GameLevel game, Counter ballCounter) {
        this.game = game;
        this.ballCounter = ballCounter;
    }

    /**
     * @param beingHit A block that remove the balls hitting it from the game.
     * @param hitter The ball which hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        ballCounter.decrease(1);
    }
}

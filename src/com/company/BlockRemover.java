//Maayan Bahar 315112672

package com.company;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * @param game The game.
     * @param remainingBlocks The ball counter - will be updated.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * @param beingHit The block that got hit and will be removed from the game.
     * @param hitter The hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeHitListener(game);
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    }
}

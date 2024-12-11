//David Goldstein 331010835
package Listeners;
import Game.Game;
import Sprites.Ball;
import Sprites.Block;
import Miscellaneous.Counter;
/**
 * Listeners.BlockRemover is a Listeners.HitListener that is in charge of removing
 * blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game currentGame;
    private Counter blockCounter;
    private Counter scoreCounter;
    /**
     * Handles the hit event by removing the block from the game and decreasing the remaining blocks counter.
     * Also removes this listener from the block that is being removed.
     *
     * @param beingHit the block that is being hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(currentGame);
        beingHit.removeHitListener(this);
        this.scoreCounter.increase(5);
        blockCounter.decrease(1);
    }
    /**
     * Constructs a Listeners.BlockRemover with the specified game and remaining blocks counter.
     *
     * @param currentGame the game from which blocks will be removed
     * @param blockCounter the counter for the number of remaining blocks
     */
    public BlockRemover(Game currentGame, Counter blockCounter) {
        this.currentGame = currentGame;
        this.blockCounter = blockCounter;
        this.scoreCounter = new Counter();
    }

}

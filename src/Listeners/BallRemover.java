//David Goldstein 331010835
package Listeners;
import Game.Game;
import Sprites.Ball;
import Sprites.Block;
import Miscellaneous.Counter;
/**
 * The Listeners.BallRemover class is a Listeners.HitListener that is responsible for removing balls
 * from the game when they hit a specific block (typically a "death region").
 * It also updates the count of remaining balls in the game.
 */
public class BallRemover implements HitListener {
    private Game currentGame;
    private Counter ballCounter;
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Sprites.Ball that's doing the hitting.
     * When a ball hits the block, the ball is removed from the game, and the remaining balls counter is decremented.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.currentGame);
        this.ballCounter.decrease(1);
    }
    /**
     * Constructs a Listeners.BallRemover with the specified game and counter for the remaining balls.
     *
     * @param currentGame           the game from which balls will be removed
     * @param ballCounter the counter that keeps track of the remaining balls in the game
     */
    public BallRemover(Game currentGame, Counter ballCounter) {
        this.currentGame = currentGame;
        this.ballCounter = ballCounter;
    }

}


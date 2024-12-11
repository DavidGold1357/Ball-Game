//David Goldstein 331010835
package Listeners;
import Sprites.Ball;
import Sprites.Block;
/**
 * The Listeners.HitListener interface represents objects that
 * listen for collision events between a Sprites.Ball and a Sprites.Block.
 * It defines a method to be called when such a collision occurs.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit by the hitter Sprites.Ball.
     *
     * @param beingHit the Sprites.Block object that was hit
     * @param hitter   the Sprites.Ball object that hit the Sprites.Block
     */
    void hitEvent(Block beingHit, Ball hitter);
}

//David Goldstein 331010835
package Sprites;
import Listeners.HitListener;
import Miscellaneous.Counter;

/**
 * The Sprites.ScoreTrackingListener class implements the Listeners.HitListener interface.
 * It tracks and increases the score whenever a Sprites.Block is hit by a Sprites.Ball.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter scoreCounter;

    /**
     * Constructs a Sprites.ScoreTrackingListener object with the specified Util.Counter for the score.
     *
     * @param scoreCounter the Util.Counter object that tracks the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit by the hitter Sprites.Ball.
     * It increases the score by 5 points.
     *
     * @param blockHit the Sprites.Block that was hit
     * @param hittingBall the Sprites.Ball that hit the Sprites.Block
     */
    @Override
    public void hitEvent(Block blockHit, Ball hittingBall) {
        scoreCounter.increase(5);
    }
}

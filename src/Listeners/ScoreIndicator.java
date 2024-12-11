//David Goldstein 331010835
package Listeners;
import Sprites.Sprite;
import Miscellaneous.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The ScoreIndicator class implements the Sprite interface.
 * It displays the current score on a DrawSurface.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * Constructs a ScoreIndicator object with the specified Counter for the score.
     *
     * @param scoreCounter the Counter object that tracks the current score
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * Draws the current score on the given DrawSurface.
     *
     * @param drawSurface the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawText(350, 20, "Score: " + scoreCounter.getValue(), 20);
    }

    /**
     * This method is not implemented for this class, as there are no ongoing animations or updates needed.
     * It satisfies the Sprite interface requirement.
     */
    @Override
    public void timePassed() {
        // No need to implement for this simple indicator
    }
}

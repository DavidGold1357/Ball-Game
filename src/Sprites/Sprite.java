//David Goldstein 331010835
package Sprites;

import biuoop.DrawSurface;

/**
 * The Sprites.Sprite interface represents a drawable object in the game.
 * Each sprite can be drawn on a given surface and can be notified that time has passed.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     * This method can be used to update the sprite's state or behavior.
     */
    void timePassed();
}

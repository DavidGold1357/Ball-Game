//David Goldstein 331010835
package Sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * The Sprites.SpriteCollection class manages a collection of Sprites.Sprite objects.
 * It provides methods to add sprites, remove sprites, notify all sprites that time has passed,
 * and draw all sprites on a given DrawSurface.
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * Constructs a new Sprites.SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
    /**
     * Calls timePassed() on all sprites in the collection.
     * This method notifies each sprite that time has passed,
     * allowing them to update their state or behavior.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : new ArrayList<>(sprites)) {
            sprite.timePassed();
        }
    }
    /**
     * Calls drawOn(d) on all sprites in the collection.
     * This method draws each sprite on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprites on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}

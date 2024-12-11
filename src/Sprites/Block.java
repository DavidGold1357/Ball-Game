//David Goldstein 331010835
package Sprites;
import Game.Game;
import Listeners.HitListener;
import Listeners.HitNotifier;
import Mech.Collidable;
import Geometry.Point;
import Geometry.Rectangle;
import Mech.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Sprites.Block class represents a block in the game, which can be collided with.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockShape;
    private Color blockColor;
    private List<HitListener> blockHitListeners;

    /**
     * Constructs a Sprites.Block with the specified rectangle and color.
     *
     * @param shape the rectangle representing the block
     * @param color the color of the block
     */
    public Block(Rectangle shape, Color color) {
        this.blockShape = new Rectangle(shape.getUpperLeftPoint(), shape.getWidthOfRectangle(), shape.getHeightOfRectangle());
        this.blockColor = color;
        this.blockHitListeners = new ArrayList<>();
    }

    /**
     * Adds the block to the game by adding it to both the sprite and collidable collections.
     *
     * @param game the game to add the block to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Removes the block from the game by removing it from both the collidable and sprite collections.
     *
     * @param game the game to remove the block from
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Removes a Listeners.HitListener from the block.
     *
     * @param listener the Listeners.HitListener to remove
     */
    public void removeHitListener(HitListener listener) {
        this.blockHitListeners.remove(listener);
    }
    /**
     * Gets the rectangle representing the block's collision area.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.blockShape;
    }

    /**
     * Notifies all registered HitListeners about a hit event.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.blockHitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Adds a Listeners.HitListener to the block.
     *
     * @param listener the Listeners.HitListener to add
     */
    public void addHitListener(HitListener listener) {
        this.blockHitListeners.add(listener);
    }

    /**
     * Checks if the ball's color matches the block's color.
     *
     * @param ball the ball to check
     * @return true if the ball's color matches the block's color, false otherwise
     */
    public boolean doesBallColorMatch(Ball ball) {
        return ball.getBallColor() == this.blockColor;
    }

    /**
     * Handles the collision with the block and updates the velocity accordingly.
     * A collision with the left/right changes the horizontal direction,
     * and a collision with the top/bottom changes the vertical direction.
     *
     * @param hitter the ball which collides
     * @param collisionPoint the point where the collision occurred
     * @param currentVelocity the current velocity of the object colliding
     * @return the new velocity after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDeltaX();
        double dy = currentVelocity.getDeltaY();

        boolean horizontalBoundary = collisionPoint.getX() <= blockShape.getUpperLeftPoint().getX()
                || collisionPoint.getX() >= blockShape.getWidthOfRectangle() + blockShape.getUpperLeftPoint().getX();
        boolean verticalBoundary = collisionPoint.getY() <= blockShape.getUpperLeftPoint().getY()
                || collisionPoint.getY() >= blockShape.getHeightOfRectangle() + blockShape.getUpperLeftPoint().getY();

        if (horizontalBoundary) {
            dx = -dx;
        }
        if (verticalBoundary) {
            dy = -dy;
        }

        if (!doesBallColorMatch(hitter)) {
            this.notifyHit(hitter);
            if (this.blockColor != Color.BLACK) {
                hitter.setBallColor(this.blockColor);
            }
        }

        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface the surface to draw the block on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.blockColor);
        surface.fillRectangle((int) this.blockShape.getUpperLeftPoint().getX(), (int) this.blockShape.getUpperLeftPoint().getY(),
                (int) this.blockShape.getWidthOfRectangle(), (int) this.blockShape.getHeightOfRectangle());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.blockShape.getUpperLeftPoint().getX(), (int) this.blockShape.getUpperLeftPoint().getY(),
                (int) this.blockShape.getWidthOfRectangle(), (int) this.blockShape.getHeightOfRectangle());
    }

    /**
     * Notifies the block that time has passed.
     * This method can be used to update the block's state or behavior.
     * Currently, it does nothing but can be extended for animations.
     */
    @Override
    public void timePassed() {
        // No action for now. Can be used for future animations.
    }

}

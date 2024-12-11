//David Goldstein 331010835
package Sprites;

import Game.Game;
import Game.GameEnvironment;
import Listeners.HitListener;
import Listeners.HitNotifier;
import Mech.CollisionInfo;
import Mech.Velocity;
import Geometry.Point;
import Geometry.Line;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Sprites.Ball class represents a ball in the game.
 * It includes methods to set and get the ball's properties, move the ball, and handle collisions.
 */
public class Ball implements Sprite, HitNotifier {
    private Point ballCenter;
    private int ballRadius;
    private Color ballColor;
    private Velocity ballVelocity;
    private GameEnvironment ballGameEnvironment;
    private List<HitListener> ballHitListeners;

    /**
     * Constructs a Sprites.Ball object with the specified center point, radius, color, and game environment.
     *
     * @param centerPoint the center point of the ball
     * @param radius the radius of the ball
     * @param color the color of the ball
     * @param gameEnv the game environment in which the ball exists
     */
    public Ball(Point centerPoint, int radius, Color color, GameEnvironment gameEnv) {
        this.ballCenter = centerPoint;
        this.ballRadius = radius;
        this.ballColor = color;
        this.ballGameEnvironment = gameEnv;
        this.ballVelocity = new Velocity(0, 0); // Default velocity
        this.ballHitListeners = new ArrayList<>();
    }

    /**
     * Gets the x coordinate of the ball's center.
     *
     * @return the x coordinate of the ball's center
     */
    public int getCenterX() {
        return (int) ballCenter.getX();
    }

    /**
     * Gets the y coordinate of the ball's center.
     *
     * @return the y coordinate of the ball's center
     */
    public int getCenterY() {
        return (int) ballCenter.getY();
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getBallColor() {
        return ballColor;
    }

    /**
     * Sets the color of the ball.
     *
     * @param newColor the new color of the ball
     */
    public void setBallColor(Color newColor) {
        this.ballColor = newColor;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param drawSurface the surface to draw the ball on
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(ballColor);
        drawSurface.fillCircle(getCenterX(), getCenterY(), ballRadius);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param newVelocity the new velocity
     */
    public void setVelocity(Velocity newVelocity) {
        this.ballVelocity = newVelocity;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the change in x direction
     * @param dy the change in y direction
     */
    public void setVelocity(double dx, double dy) {
        this.ballVelocity = new Velocity(dx, dy);
    }

    /**
     * Moves the ball one step, considering possible collisions.
     */
    public void moveOneStep() {
        // Compute the ball's trajectory
        Point endPoint = this.ballVelocity.applyToPoint(this.ballCenter);
        Line trajectory = new Line(this.ballCenter, endPoint);

        // Check for collisions
        CollisionInfo collisionInfo = this.ballGameEnvironment.findClosestCollision(trajectory);

        if (collisionInfo == null) {
            // No collision, move the ball to the end of the trajectory
            this.ballCenter = endPoint;
        } else {
            // Collision detected, move the ball to "almost" the hit point
            Point collisionPoint = collisionInfo.getImpactPoint();
            Velocity newVelocity = collisionInfo.getImpactedObject().hit(this, collisionPoint, this.ballVelocity);

            // Move the ball just before the collision point
            double dx = this.ballVelocity.getDeltaX();
            double dy = this.ballVelocity.getDeltaY();
            double collisionPointX = collisionPoint.getX();
            double collisionPointY = collisionPoint.getY();
            double adjustedX = collisionPointX - dx / (Math.abs(dx) * 0.1);
            double adjustedY = collisionPointY - dy / (Math.abs(dy) * 0.1);

            // Update the ball's center and velocity
            this.ballCenter = new Point(adjustedX, adjustedY);
            if (newVelocity != null) {
                this.setVelocity(newVelocity);
            }
        }
    }

    /**
     * Removes the ball from the game.
     *
     * @param game the game to remove the ball from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * Adds a hit listener to the ball.
     *
     * @param listener the hit listener to add
     */
    public void addHitListener(HitListener listener) {
        this.ballHitListeners.add(listener);
    }

    /**
     * Removes a hit listener from the ball.
     *
     * @param listener the hit listener to remove
     */
    public void removeHitListener(HitListener listener) {
        this.ballHitListeners.remove(listener);
    }

    /**
     * Notifies the ball that time has passed and it should move one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the ball to the game by adding it to the sprite collection.
     *
     * @param game the game to add the ball to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}

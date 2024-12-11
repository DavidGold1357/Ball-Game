//David Goldstein 331010835
package Mech;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;

/**
 * The Collidable interface represents objects that can collide with a Ball.
 * It defines methods to retrieve the collision shape of the object and to handle collisions.
 */
public interface Collidable {

    /**
     * Returns the collision shape (rectangle) of the object.
     *
     * @return the rectangle representing the collision shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that it has been hit by a Ball at a specific collision point with a given velocity.
     * Calculates and returns the new velocity of the Ball after the collision.
     *
     * @param hittingBall      the Ball object that collided with this Collidable
     * @param collisionPoint   the point of collision with the Collidable object
     * @param currentVelocity  the current velocity of the Ball before the collision
     * @return the new velocity of the Ball after the collision
     */
    Velocity hit(Ball hittingBall, Point collisionPoint, Velocity currentVelocity);
}

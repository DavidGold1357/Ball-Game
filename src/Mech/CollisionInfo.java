//David Goldstein 331010835
package Mech;
import Geometry.Point;

/**
 * The CollisionInfo class represents information about a collision.
 * It includes the point at which the collision occurs and the collidable object involved.
 */
public class CollisionInfo {
    private Point impactPoint;
    private Collidable impactedObject;

    /**
     * Constructs a CollisionInfo object with the specified collision point and collidable object.
     *
     * @param impactPoint the point at which the collision occurs
     * @param impactedObject the collidable object involved in the collision
     */
    public CollisionInfo(Point impactPoint, Collidable impactedObject) {
        this.impactPoint = impactPoint;
        this.impactedObject = impactedObject;
    }

    /**
     * Gets the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point getImpactPoint() {
        return impactPoint;
    }

    /**
     * Gets the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable getImpactedObject() {
        return impactedObject;
    }
}

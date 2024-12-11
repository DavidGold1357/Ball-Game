//David Goldstein 331010835
package Game;
import Mech.Collidable;
import Mech.CollisionInfo;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Line;
import java.util.ArrayList;
import java.util.List;
/**
 * The GameEnvironment class represents the environment in which the game objects exist.
 * It keeps track of all collidable objects and can determine the closest collision
 * that will occur along a given trajectory.
 */
public class GameEnvironment {
    private List<Collidable> collidableList;
    /**
     * Constructs a new GameEnvironment.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }
    /**
     * Determines the closest collision that will occur along the given trajectory.
     * If no collision will occur, returns null.
     *
     * @param trajectory the line representing the object's trajectory
     * @return the CollisionInfo of the closest collision, or null if no collision will occur
     */
    public CollisionInfo findClosestCollision(Line trajectory) {
        for (Collidable collidable : collidableList) {
            Rectangle collidableRectangle = collidable.getCollisionRectangle();
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidableRectangle);
            if (collisionPoint != null) {
                return new CollisionInfo(collisionPoint, collidable);
            }
        }

        return null;
    }
    /**
     * Adds the given collidable to the environment.
     *
     * @param collidable the collidable to add
     */
    public void addCollidable(Collidable collidable) {
        collidableList.add(collidable);
    }
    /**
     * Removes the given collidable from the environment.
     *
     * @param collidable the collidable to remove
     */
    public void removeCollidable(Collidable collidable) {
        collidableList.remove(collidable);
    }
}

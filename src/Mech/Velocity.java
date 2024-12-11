//David Goldstein 331010835
package Mech;
import Geometry.Point;

/**
 * The Velocity class represents the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double deltaX;
    private double deltaY;

    /**
     * Constructs a Velocity object with the specified change in x and y directions.
     *
     * @param deltaX the change in x direction
     * @param deltaY the change in y direction
     */
    public Velocity(double deltaX, double deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }


    /**
     * Creates a velocity from an angle and speed.
     *
     * @param angle the angle in degrees
     * @param speed the speed
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle - 90);
        double deltaX = speed * Math.cos(radians);
        double deltaY = speed * Math.sin(radians);
        return new Velocity(deltaX, deltaY);
    }

    /**
     * Calculates and returns the speed.
     *
     * @return the speed calculated from deltaX and deltaY
     */
    public double getSpeed() {
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    /**
     * Returns the change in x direction.
     *
     * @return the change in x direction
     */
    public double getDeltaX() {
        return deltaX;
    }

    /**
     * Returns the change in y direction.
     *
     * @return the change in y direction
     */
    public double getDeltaY() {
        return deltaY;
    }
    /**
     * Applies the velocity to a point and returns the new point.
     *
     * @param point the point to apply the velocity to
     * @return the new point after applying the velocity
     */
    public Point applyToPoint(Point point) {
        return new Point(point.getX() + deltaX, point.getY() + deltaY);
    }

}

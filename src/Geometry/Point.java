//David Goldstein 331010835
package Geometry;
/**
 * The Shapes.Point class represents a point in a 2D space.
 * It includes methods to calculate distance to another point,
 * check for equality with another point, and get or set the x and y coordinates.
 */
public class Point {

    // Coordinates of the point
    private double coordX;
    private double coordY;

    /**
     * Constructs a point with specified x and y coordinates.
     *
     * @param coordX the x-coordinate of the point.
     * @param coordY the y-coordinate of the point.
     */
    public Point(double coordX, double coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }
    /**
     * Gets the x-coordinate of this point.
     *
     * @return the x-coordinate.
     */
    public double getX() {

        return coordX;
    }
    /**
     * Gets the y-coordinate of this point.
     *
     * @return the y-coordinate.
     */
    public double getY() {

        return coordY;
    }
    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point.
     * @return the distance between the two points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.coordX - other.coordX, 2) + Math.pow(this.coordY - other.coordY, 2));
    }
}

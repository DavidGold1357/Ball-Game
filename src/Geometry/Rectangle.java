//David Goldstein 331010835
package Geometry;
import java.util.ArrayList;
import java.util.List;
/**
 * The Shapes.Rectangle class represents a rectangle defined by an upper-left point,
 * a width, and a height.
 */
public class Rectangle {
    private Point topLeft;
    private double rectWidth;
    private double rectHeight;

    /**
     * Constructs a new Shapes.Rectangle with the specified location and dimensions.
     *
     * @param upperLeftPoint the upper-left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeftPoint, double width, double height) {
        this.topLeft = upperLeftPoint;
        this.rectWidth = width;
        this.rectHeight = height;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidthOfRectangle() {
        return rectWidth;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeightOfRectangle() {
        return rectHeight;
    }
    /**
     * Returns a list of intersection points with the specified line.
     * The list may be empty if there are no intersection points.
     *
     * @param lineToCheck the line to check for intersections
     * @return a list of intersection points with the specified line
     */
    public List<Point> intersectionPoints(Line lineToCheck) {
        List<Point> intersectionPoints = new ArrayList<>();

        // Get the four corner points of the rectangle
        Point topRight = new Point(topLeft.getX() + rectWidth, topLeft.getY());
        Point lowerLeft = new Point(topLeft.getX(), topLeft.getY() + rectHeight);
        Point lowerRight = new Point(topRight.getX(), lowerLeft.getY());

        // Define the lines representing the sides of the rectangle
        Line topSide = new Line(topLeft, topRight);
        Line rightSide = new Line(topRight, lowerRight);
        Line bottomSide = new Line(lowerRight, lowerLeft);
        Line leftSide = new Line(lowerLeft, topLeft);

        // Check intersection with each side of the rectangle
        if (lineToCheck.isIntersecting(topSide)) {
            intersectionPoints.add(lineToCheck.intersectionWith(topSide));
        }
        if (lineToCheck.isIntersecting(rightSide)) {
            intersectionPoints.add(lineToCheck.intersectionWith(rightSide));
        }
        if (lineToCheck.isIntersecting(bottomSide)) {
            intersectionPoints.add(lineToCheck.intersectionWith(bottomSide));
        }
        if (lineToCheck.isIntersecting(leftSide)) {
            intersectionPoints.add(lineToCheck.intersectionWith(leftSide));
        }

        return intersectionPoints;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeftPoint() {
        return new Point(this.topLeft.getX(), this.topLeft.getY());
    }
}

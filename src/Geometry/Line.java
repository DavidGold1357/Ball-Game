//David Goldstein 331010835
package Geometry;
import java.util.List;
/**
 * The Shapes.Line class represents a line segment in 2D space defined by a start and an end point.
 * It includes methods to calculate the length, find the middle point, and determine if it intersects with another line.
 */
public class Line {
    private Point start;
    private Point end;
    private final double epsilon = Math.pow(10, -6);
    /**
     * Constructs a line segment given two points.
     *
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }
    /**
     * Returns the start point of the line segment.
     *
     * @return the start point of the line segment
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * Returns the end point of the line segment.
     *
     * @return the end point of the line segment
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Determines if this line segment intersects with another line segment.
     *
     * @param other the other line segment to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }
    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the rectangle to check for intersection
     * @return the closest intersection point to the start of the line, or null if there is no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(new Line(this.start, this.end));
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        double minDistance = Double.MAX_VALUE;
        Point minDistancePoint = null;
        for (Point intersectionPoint : intersectionPoints) {
            if (intersectionPoint.distance(this.start) < minDistance) {
                minDistance = intersectionPoint.distance(this.start);
                minDistancePoint = intersectionPoint;
            }
        }
        return minDistancePoint;
    }
    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other line segment to check for intersection
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
        double startX = this.start.getX();
        double startY = this.start.getY();
        double endX = this.end.getX();
        double endY = this.end.getY();

        double otherStartX = other.start().getX();
        double otherStartY = other.start().getY();
        double otherEndX = other.end().getX();
        double otherEndY = other.end().getY();

        double denominator = (otherEndY - otherStartY) * (endX - startX) - (otherEndX - otherStartX) * (endY - startY);

        if (Math.abs(denominator) < epsilon) {
            return null; // Lines are parallel or coincident
        }

        double ua = ((otherEndX - otherStartX) * (startY - otherStartY) - (otherEndY - otherStartY)
                * (startX - otherStartX)) / denominator;
        double ub = ((endX - startX) * (startY - otherStartY) - (endY - startY) * (startX - otherStartX)) / denominator;

        if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
            double intersectionX = startX + ua * (endX - startX);
            double intersectionY = startY + ua * (endY - startY);
            return new Point(intersectionX, intersectionY);
        }

        return null;
    }

}

package geom.validator;

import geom.point.Point;
import geom.point.Point2D;

import org.apache.commons.lang3.Validate;

/**
 * @author xuch.
 */
public class GeometricValidator {

    public static void validateTriangle(Point a, Point b, Point c) {
        Validate.noNullElements(new Point[]{a, b, c}, "All points passed in should not be null");
        double x = a.distance(b);
        double y = a.distance(c);
        double z = b.distance(c);
        Validate.isTrue(x + y > z && y + z > x && x + z > y,
                "line 1: %f, line 2: %f, line 3: %f, negerated by input points can not make a valid triangle.",
                x, y, z);
    }

    public static void validateLine(Point a, Point b) {
        Validate.noNullElements(new Point[]{a, b}, "All points passed in should not be null");
        Validate.isTrue(a.distance(b) == 0, "The starting point and the end point are at the same location");
    }

    public static void validateRectangle(Point2D leftBottom, double width, double height) {
        Validate.notNull(leftBottom, "Left-bottom point of the rectangle cannnot be null.");
        Validate.isTrue(Double.doubleToRawLongBits(width) > 0, "Width of a rectangle cannot be negative.");
        Validate.isTrue(Double.doubleToRawLongBits(height) > 0, "Height of a rectangle cannot be negative.");
    }
}

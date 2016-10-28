package geom.point;

import lombok.Getter;
import org.apache.commons.lang3.Validate;

/**
 * The Point2D class defines a point representing a location
 * in {@code (x,y)} coordinate space.
 * @author xuch.
 */
public class Point2D implements Point {
    public static final Point2D ZERO = new Point2D(0.0, 0.0);

    @Getter
    private double x;

    @Getter
    private double y;

    /**
     * Cache the hash code to make computing hashes faster.
     */
    private int hash = 0;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /*
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(Point2D p) {
        setLocation(p.x, p.y);
    }
    */

    /**
     * @return the distance between two points.
     */
    public static double distance(double x1, double y1, double x2, double y2) {
        x1 -= x2;
        y1 -= y2;
        return (double) Math.sqrt(x1 * x1 + y1 * y1);
    }

    @Override
    public double distance(Point p) {
        Validate.isInstanceOf(getClass(), p, "Only length of Points in same dimensions can be calculated.");
        return distance((Point2D) p);
    }

    /**
     * @return the distance from this Point2D to a specified point.
     */
    public double distance(double px, double py) {
        px -= x;
        py -= y;
        return (double) Math.sqrt(px * px + py * py);
    }

    /**
     * @return the distance from this Point2D to a specified Point2D.
     */
    public double distance(Point2D pt) {
        double px = pt.x - this.x;
        double py = pt.y - this.y;
        return (double) Math.sqrt(px * px + py * py);
    }

    /**
     * Returns a point with the specified coordinates added to the coordinates
     * of this point.
     */
    public Point2D add(double x, double y) {
        return new Point2D(
                getX() + x,
                getY() + y);
    }

    /**
     * Returns a point with the coordinates of the specified point added to the
     * coordinates of this point.
     */
    public Point2D add(Point2D point) {
        return add(point.getX(), point.getY());
    }

    /**
     * Returns a point with the specified coordinates subtracted from
     * the coordinates of this point.
     */
    public Point2D subtract(double x, double y) {
        return new Point2D(
                getX() - x,
                getY() - y);
    }

    public Point2D subtract(Point2D point) {
        return subtract(point.getX(), point.getY());
    }

    /**
     * Returns a point which lies in the middle between this point and the
     * specified coordinates.
     */
    public Point2D midpoint(double x, double y) {
        return new Point2D(
                x + (getX() - x) / 2.0,
                y + (getY() - y) / 2.0);
    }

    /**
     * Returns a point which lies in the middle between this point and the
     * specified point.
     */
    public Point2D midpoint(Point2D point) {
        return midpoint(point.getX(), point.getY());
    }

    /**
     * @return a hash code for this Point2D.
     */
    public int hashCode() {
        if (hash == 0) {
            long bits = java.lang.Double.doubleToLongBits(x);
            bits ^= java.lang.Double.doubleToLongBits(y) * 31;
            hash = (int) bits;
        }
        return hash;
    }

    /**
     * Determines whether or not two points are equal.
     */
    public boolean equals(Object obj) {
        return (obj == this) && (obj instanceof Point2D) && (((Point2D) obj).x == x) && (((Point2D) obj).y == y);
    }

    /**
     * @return a string representation of this Point2D.
     */
    public String toString() {
        return "Point2D[" + x + ", " + y + "]";
    }

    /**
     * @return a clone from the given one
     */
    @Override
    public Point2D clone() {
        return new Point2D(this.x, this.y);
    }

}
package geom.point;

import lombok.Getter;
import org.apache.commons.lang3.Validate;

/**
 * @author xuch.
 */
public class Point3D implements Point {
    public static final Point3D ZERO = new Point3D(0.0, 0.0, 0.0);

    @Getter
    private double x;

    @Getter
    private double y;

    @Getter
    private double z;
    
    /**
     * Cache the hash code to make computing hashes faster.
     */
    private int hash = 0;


    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /*
    public void setLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setLocation(Point3D p) {
        setLocation(p.x, p.y, p.z);
    }
    */

    @Override
    public double distance(Point p) {
        Validate.isInstanceOf(getClass(), p, "Only length of Points in same dimensions can be calculated.");
        return distance((Point3D) p);
    }

    public double distance(double x1, double y1, double z1) {
        double a = getX() - x1;
        double b = getY() - y1;
        double c = getZ() - z1;
        return Math.sqrt(a * a + b * b + c * c);
    }

    public double distance(Point3D point) {
        return distance(point.getX(), point.getY(), point.getZ());
    }

    /**
     * Returns a point with the specified coordinates added to the coordinates
     * of this point.
     */
    public Point3D add(double x, double y, double z) {
        return new Point3D(
                getX() + x,
                getY() + y,
                getZ() + z);
    }

    /**
     * Returns a point with the coordinates of the specified point added to the
     * coordinates of this point.
     */
    public Point3D add(Point3D point) {
        return add(point.getX(), point.getY(), point.getZ());
    }

    /**
     * Returns a point with the specified coordinates subtracted from
     * the coordinates of this point.
     */
    public Point3D subtract(double x, double y, double z) {
        return new Point3D(
                getX() - x,
                getY() - y,
                getZ() - z);
    }

    /**
     * Returns a point with the coordinates of the specified point subtracted
     * from the coordinates of this point.
     * /
    public Point3D subtract(Point3D point) {
        return subtract(point.getX(), point.getY(), point.getZ());
    }

    /**
     * Returns a point which lies in the middle between this point and the
     * specified coordinates.
     */
    public Point3D midpoint(double x, double y, double z) {
        return new Point3D(
                x + (getX() - x) / 2.0,
                y + (getY() - y) / 2.0,
                z + (getZ() - z) / 2.0);
    }

    /**
     * Returns a point which lies in the middle between this point and the
     * specified point.
     */
    public Point3D midpoint(Point3D point) {
        return midpoint(point.getX(), point.getY(), point.getZ());
    }

    /**
     * Computes the angle (in degrees) between the vector represented
     * by this point and the specified vector.
     */
    public double angle(double x, double y, double z) {
        final double ax = getX();
        final double ay = getY();
        final double az = getZ();

        final double delta = (ax * x + ay * y + az * z) / Math.sqrt(
                (ax * ax + ay * ay + az * az) * (x * x + y * y + z * z));

        if (delta > 1.0) {
            return 0.0;
        }
        if (delta < -1.0) {
            return 180.0;
        }

        return Math.toDegrees(Math.acos(delta));
    }

    /**
     * Computes the angle (in degrees) between the vector represented
     * by this point and the vector represented by the specified point.
     */
    public double angle(Point3D point) {
        return angle(point.getX(), point.getY(), point.getZ());
    }

    /**
     * Computes the angle (in degrees) between the three points with this point
     * as a vertex.
     */
    public double angle(Point3D p1, Point3D p2) {
        final double x = getX();
        final double y = getY();
        final double z = getZ();

        final double ax = p1.getX() - x;
        final double ay = p1.getY() - y;
        final double az = p1.getZ() - z;
        final double bx = p2.getX() - x;
        final double by = p2.getY() - y;
        final double bz = p2.getZ() - z;

        final double delta = (ax * bx + ay * by + az * bz) / Math.sqrt(
                (ax * ax + ay * ay + az * az) * (bx * bx + by * by + bz * bz));

        if (delta > 1.0) {
            return 0.0;
        }
        if (delta < -1.0) {
            return 180.0;
        }

        return Math.toDegrees(Math.acos(delta));
    }

    /**
     * Determines whether or not two points are equal.
     */
    @Override
    public boolean equals(Object obj) {
        return obj == this && obj instanceof Point3D &&
                (getX() == ((Point3D) obj).getX() && getY() == ((Point3D) obj).getY() && getZ() == ((Point3D) obj).getZ());
    }

    /**
     * @return a hash code for this {@code Point3D} object.
     */
    @Override
    public int hashCode() {
        if (hash == 0) {
            long bits = 7L;
            bits = 31L * bits + Double.doubleToLongBits(getX());
            bits = 31L * bits + Double.doubleToLongBits(getY());
            bits = 31L * bits + Double.doubleToLongBits(getZ());
            hash = (int) (bits ^ (bits >> 32));
        }
        return hash;
    }

    /**
     * Returns a string representation of this {@code Point3D}.
     * This method is intended to be used only for informational purposes.
     * The content and format of the returned string might vary between
     * implementations.
     * The returned string might be empty but cannot be {@code null}.
     */
    @Override
    public String toString() {
        return "Point3D [x = " + getX() + ", y = " + getY() + ", z = " + getZ() + "]";
    }

    @Override
    public Point3D clone() {
        return new Point3D(this.x, this.y, this.z);
    }
}

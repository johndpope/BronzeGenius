package geom.shape;

import geom.point.Point2D;

/**
 * @author xuch.
 *
 * TODO
 */
public class Polygon extends Shape {
    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }
}

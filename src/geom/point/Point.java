package geom.point;

import geom.ShapeFactory;

/**
 * @author xuch.
 */
public interface Point {
    double distance(Point p);
    Point clone();
}

package geom.shape;

import geom.Geometric;
import geom.point.Point2D;

import java.util.List;

/**
 * @author xuch.
 */
public abstract class Shape implements Geometric<Point2D> {
    List<Point2D> vertexes;
    public abstract double getArea();
    public abstract double getPerimeter();
    public abstract boolean contains(Point2D p);

    public List<Point2D> getVertexes() {
        return vertexes;
    }

    /*Shape() {
        vertexes = new ArrayList<>();
        vertexNumber = 0;
    }*/
}

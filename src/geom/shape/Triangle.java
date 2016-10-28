package geom.shape;

import geom.point.Point2D;
import geom.validator.GeometricValidator;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.stream.DoubleStream;

/**
 * @author xuch.
 */
public class Triangle extends Shape {
    private static final double delta = 1e-15;

    @Getter
    private double lengthFirst;

    @Getter
    private double lengthSecond;

    @Getter
    private double lengthThird;

    //public Triangle(List<Point2D> vertexes) {}

    public Triangle(Point2D o, Point2D p, Point2D q) {
        GeometricValidator.validateTriangle(o, p, q);
        vertexes = new ArrayList<>();
        lengthFirst = o.distance(p);
        lengthSecond = o.distance(q);
        lengthThird = p.distance(q);
        vertexes.add(o);
        vertexes.add(p);
        vertexes.add(q);
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - lengthFirst) * (s - lengthSecond) * (s - lengthThird));
    }

    @Override
    public double getPerimeter() {
        //return edges.stream().mapToDouble(l -> l.getLength()).sum();
        return lengthFirst + lengthSecond + lengthThird;
    }


    @Override
    public boolean contains(Point2D point) {
        Point2D o = vertexes.get(0);
        Point2D p = vertexes.get(1);
        Point2D q = vertexes.get(2);

        double s = o.getY() * q.getX() - o.getX() * q.getY() +
                point.getX() * (q.getY() - o.getY()) + point.getY() * (o.getX() - q.getX());
        double t = o.getX() * p.getY() - o.getY() * p.getX() +
                point.getX() * (o.getY() - p.getY()) + point.getY() * (p.getX() - o.getX());

        if ((s < 0) != (t < 0)) return false;

        double a = p.getX() * q.getY() - p.getY() * q.getX() +
                o.getY() * (q.getX() - p.getX()) + o.getX() * (p.getY() - q.getY());

        if (a < 0.0) {
            s = -s;
            t = -t;
            a = -a;
        }

        return s > 0 && t > 0 && !(a - s + t > 0);
    }

    public boolean isEquilateral() {
        return (Double.compare(lengthFirst, lengthSecond) == 0 || !(Math.abs(lengthFirst - lengthSecond) > delta))
                && (Double.compare(lengthSecond, lengthThird) == 0 || !(Math.abs(lengthSecond - lengthThird) > delta));
    }

    public boolean isIsosceles() {
        return Double.compare(lengthFirst, lengthSecond) == 0
                || !(Math.abs(lengthFirst - lengthSecond) > delta)
                || Double.compare(lengthSecond, lengthThird) == 0
                || !(Math.abs(lengthSecond - lengthThird) > delta)
                || Double.compare(lengthFirst, lengthThird) == 0
                || !(Math.abs(lengthFirst - lengthThird) > delta);
    }

    public boolean isScalene() {
        return !isIsosceles();
    }
}

package geom.shape;

import geom.point.Point2D;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author xuch.
 */
public class Circle extends Shape {
    private static final double delta = 1e-15;

    @Getter
    private Point2D center;

    @Getter
    private double radius;

    public Circle(Point2D center, double radius) {
        this.center = center;
        this.radius = radius;
        vertexes.add(center);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * radius * 2;
    }

    @Override
    public boolean contains(Point2D p) {
        return center.distance(p) <= radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * Compare with delta or not? or BigDecimal?
     */
    public boolean isSame(Circle circle) {
        return circle != null && center.equals(circle.getCenter()) && Double.compare(radius, circle.getRadius()) == 0;
    }
}

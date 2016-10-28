package geom.shape;

import geom.point.Point2D;
import geom.validator.GeometricValidator;
import lombok.Getter;

/**
 * @author xuch.
 *
 * TODO
 */
public class Rectangle extends Shape {
    @Getter
    private Point2D leftBottom;

    @Getter
    private double width;

    @Getter
    private double height;

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return (height + width) * 2.0;
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    public Rectangle(final Point2D leftBottom, double width, double height) {
        GeometricValidator.validateRectangle(leftBottom, width, height);
        this.leftBottom = leftBottom;
        this.width = width;
        this.height = height;
    }
}

package geom.test;

import geom.point.Point2D;
import geom.shape.Triangle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author xuch.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:spring-configuration/unit-testing-config.xml"})
public class TriangleTest extends AbstractCaseTest {
    @Test
    public void happyCaseIsosceles() {
        final double area = 0.5;
        final double perimeter = 1 + 1 + Math.sqrt(2);
        Triangle triangle = new Triangle(Point2D.ZERO, new Point2D(0, 1), new Point2D(1, 0));
        Assert.assertEquals(area, triangle.getArea(), delta);
        Assert.assertEquals(perimeter, triangle.getPerimeter(), delta);
        Assert.assertFalse(triangle.isEquilateral());
        Assert.assertTrue(triangle.isIsosceles());
        Assert.assertFalse(triangle.isScalene());
    }


    @Test
    public void happyCaseEquilateral() {
        final double height = Math.sqrt(3) / 2.0;
        final double width = 1.0;
        final double perimeter = 3.0;
        final double area = getTriangleAreaByWidthAndHeight(width, height);
        Triangle triangle = new Triangle(new Point2D(-(width / 2.0), 0), new Point2D((width / 2.0), 0), new Point2D(0, height));
        Assert.assertEquals(area, triangle.getArea(), delta);
        Assert.assertEquals(perimeter, triangle.getPerimeter(), delta);
        Assert.assertTrue(triangle.isEquilateral());
        Assert.assertTrue(triangle.isIsosceles());
        Assert.assertFalse(triangle.isScalene());
    }


    @Test
    public void happyCaseScalene() {
        final double height = 1.0;
        final double width = 2.0;
        final double perimeter = getPerimeterOfRightTriangle(width, height);
        final double area = getTriangleAreaByWidthAndHeight(width, height);
        Triangle triangle = new Triangle(Point2D.ZERO, new Point2D(width, 0), new Point2D(0, height));
        Assert.assertEquals(area, triangle.getArea(), delta);
        Assert.assertEquals(perimeter, triangle.getPerimeter(), delta);
        Assert.assertFalse(triangle.isEquilateral());
        Assert.assertFalse(triangle.isIsosceles());
        Assert.assertTrue(triangle.isScalene());
    }


    private double getTriangleAreaByWidthAndHeight(double width, double height) {
        return width * height / 2.0;
    }

    private double getPerimeterOfRightTriangle(double width, double height) {
        return height + width + Math.sqrt(Math.pow(height, 2.0) + Math.pow(width, 2.0));
    }
}

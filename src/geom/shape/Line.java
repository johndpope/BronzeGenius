package geom.shape;

import geom.Geometric;
import geom.point.Point;
import geom.validator.GeometricValidator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuch.
 */
public abstract class Line implements Geometric<Point> {
    @Getter
    private Point start;

    @Getter
    private Point end;

    @Getter
    private double length;

    List<Point> vertexes = new ArrayList<>();

    public Line(Point start, Point end){
        GeometricValidator.validateLine(start, end);
        this.start = start;
        this.end = end;
        vertexes.add(start);
        vertexes.add(end);
        length = start.distance(end);
    }

    public List<Point> getVertexes() {
        return vertexes;
    }

    public void move() {};

    public void rotate(double angle, Point center) {}

}

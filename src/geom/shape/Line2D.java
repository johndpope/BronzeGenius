package geom.shape;

import geom.point.Point;
import geom.point.Point2D;
import geom.shape.Line;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuch.
 */
public class Line2D extends Line {

    public Line2D(Point2D start, Point2D end) {
        super(start, end);
    }
}

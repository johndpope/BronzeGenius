package geom;

import java.awt.*;

/**
 * @author xuch on 10/23/16.
 */
public class ShapeFactory {
     public enum Dimensional {
        TwoD(DimensionalList.TwoD), ThreeD(DimensionalList.ThreeD);

        private int dimensional;
        Dimensional(int dimensional) {
            this.dimensional = dimensional;
        }

        public static class DimensionalList {
            public static final int TwoD = 2;
            public static final int ThreeD = 3;
        }
    }

    public enum ShapPattern {
        Triangle, Rectangle, Polygon
    }

    ShapeFactory getInstance(Dimensional dimensional, ShapPattern shapPattern) {
        switch (dimensional) {
            case TwoD:
                switch (shapPattern) {
                    case Triangle:
                }
        }
        return null;
    }
}

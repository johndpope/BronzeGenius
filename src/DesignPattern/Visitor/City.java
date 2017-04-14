package DesignPattern.Visitor;

import java.util.ArrayList;

/**
 * @author xuch.
 */
public class City extends VisitingElement {
    @Override
    public String toString() {
        return "City from " + super.toString();
    }
}

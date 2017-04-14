package DesignPattern.Visitor;

/**
 * @author xuch.
 */
public class Park extends VisitingElement {
    @Override
    public String toString() {
        return "Park from " + super.toString();
    }
}

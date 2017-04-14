package DesignPattern.Visitor;

/**
 * @author xuch.
 */
public class Museum extends VisitingElement {
    @Override
    public String toString() {
        return "Museum from " + super.toString();
    }
}

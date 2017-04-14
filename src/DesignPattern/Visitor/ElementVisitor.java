package DesignPattern.Visitor;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xuch.
 */
public class ElementVisitor implements Visitor<VisitingElement> {
    @Getter @Setter
    private String name;

    @Override
    public void visit(VisitingElement visitingElement) {
        System.out.println(this.toString() + " is visiting " + visitingElement.toString());
        for (VisitingElement child : visitingElement.ElementChildren) {
            child.accept(this);
        }
    }

    @Override
    public String toString() {
        return "Visitor " + name;
    }
}

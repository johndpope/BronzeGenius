package DesignPattern.Visitor;

import lombok.Setter;
import lombok.Getter;

import java.util.ArrayList;

/**
 * @author xuch.
 */
public abstract class VisitingElement implements Element<Visitor> {
    @Getter @Setter
    protected ArrayList<VisitingElement> ElementChildren = new ArrayList<>();

    @Override
    public void accept(Visitor visitor) {
        System.out.println(this.toString() + " is accepting " + visitor.toString());
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "VisitingElement";
    }
}

package DesignPattern.Visitor;

/**
 * @author xuch.
 */
public class HelloWorld {
    public static void main(String[] args) {
        ElementVisitor visitor = new ElementVisitor();
        visitor.setName("Lyroe Chan");

        City city = new City();
        city.getElementChildren().add(new Museum());
        city.getElementChildren().add(new Park());

        city.accept(visitor);
    }
}
